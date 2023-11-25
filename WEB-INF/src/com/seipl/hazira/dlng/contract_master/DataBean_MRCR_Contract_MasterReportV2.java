package com.seipl.hazira.dlng.contract_master;
import javax.naming.*;
import javax.sql.*;

import java.util.*;
//import java.awt.geom.Arc2D.Double;
import java.sql.*;
import java.text.*;

import com.seipl.hazira.dlng.util.RuntimeConf;
import com.seipl.hazira.dlng.util.UtilBean;
//Coded By          : SB/HarshP
//Code Reviewed by	:  
//CR Date			: 09/09/2021
//Status	  		: Developing

public class DataBean_MRCR_Contract_MasterReportV2
{
    Connection conn; 
	Statement stmt;
	Statement stmt1;
	Statement stmt2;
	Statement stmt3;
	ResultSet rset;
	ResultSet rset_tmpl; //md20111122
	ResultSet rset1;
	ResultSet rset2;
	ResultSet rset3;
	String queryString="";
	String queryString1="";
	String queryString2="";
	
	//Following NumberFormat Object is defined by Samik Shah ... On 8th Dec., 2009 ...
	NumberFormat nf = new DecimalFormat("###########0.00");
	NumberFormat nf2 = new DecimalFormat("###########0.0000");//MD20111121
	NumberFormat nf6 = new DecimalFormat("###########0.0000000000");//MD20111121
	
	//Following util Object is defined by Samik Shah ... On 1st Dec., 2009 ...
	UtilBean util = new UtilBean();
		
	//Following (3) String Variables are defined by Samik Shah ... On 2nd Nov., 2009 ...
	public String callFlag="";	
	public String cargo_ref_no="";
	public String year="";
	
	// variable declaration for fetching data of selected Mandate Request no
	
	String cargo_ref_cd = "";
	String SELLER_NAME = "";
	String Mandate_conf_no = "";		                     
	String ship_cd ="";
	String SHIPNAME= "";
	
	// Varible for the Cargo Nomination Form.
	String from_date ="";
	String to_date = "";
	String tot_commitment="";
	String sup_tot_comitment="";
	String bal_tot_comitment="";
	//Introduce by Milan 20110916
	String tot_commitment_mmscm="";
	String sup_tot_comitment_mmscm="";
	String bal_tot_comitment_mmscm="";
	//for Letter of Agreement
	String loa_tot_commitment_mmscm="";
	String loa_sup_tot_comitment_mmscm="";
	String loa_bal_tot_comitment_mmscm="";
	//FOR RE-GAS
	String re_gas_tot_commit_mmscm="";
	String re_gas_sup_tot_comitment_mmscm="";
	String re_gas_bal_tot_comitment_mmscm="";
	//=============================
	String loa_sup_tot_comitment="";
	String loa_bal_tot_comitment="";
	String re_gas_sup_tot_comitment="";
	String re_gas_bal_tot_comitment="";
	String loa_tot_commitment="";
	String re_gas_tot_commit="";
	
	//TRADER MASTER DETAILS
	Vector MAN_CONF_DT= new Vector();
	Vector TRADER_CD = new Vector();
	Vector TRADER_NAME = new Vector();
	Vector TRADER_ABBR = new Vector();
				
	Vector CARGO_REF_CD = new Vector();
	Vector CARGO_REF_NO = new Vector();
	//Vector CONSUMED_QTY = new Vector();	
	// Variable declaration for the mandate request list : Searching 
	Vector MAN_CD = new Vector();
	Vector MAN_CONF_CD = new Vector();	
	Vector CARGO_ARRIVAL_DATE = new Vector();
	
	Vector SHIP_CD = new Vector(); 
	Vector SHIP_NAME = new Vector();
	
	Vector SN_NO = new Vector();
	Vector SN_REV_NO=new Vector();
	Vector FGSA_REV_NO=new Vector();
	Vector FGSA_NO=new Vector();
	Vector CUSTOMER_CD = new Vector();	
	Vector CUSTOMER_NM =new Vector();
	Vector CUSTOMER_ABBR =new Vector();
	Vector BOOKMMBTU = new Vector();	
	Vector SUPPLIEDMBTU = new Vector(); 
	Vector BALANCEMMBTU = new Vector();
	//Intruduce By Milan 20110916 For MMSCM
	Vector BOOKMMSCM = new Vector();	
	Vector SUPPLIEDMMSCM = new Vector(); 
	Vector BALANCEMMSCM = new Vector();
	
	Vector SN_REF_NO = new Vector();
	//////////////////
	Vector Deal_NO=new Vector();
	Vector Deal_Signing_dt=new Vector();
	Vector Deal_cust_nm=new Vector();
	Vector Deal_cust_cd=new Vector();
	Vector Deal_cust_abbr=new Vector();
	Vector Deal_typ=new Vector();
	Vector Deal_cargo_seq_no=new Vector();
	Vector Deal_ENT_DT=new Vector();
	Vector Deal_ENT_BY=new Vector();
	Vector Deal_Price=new Vector();
	Vector Deal_curr=new Vector();
	Vector Deal_ST_DT=new Vector();
	Vector Deal_END_DT=new Vector();
	Vector Deal_qty_booked=new Vector();
	Vector Deal_DCQ=new Vector();
	Vector Deal_qty_Supplied=new Vector();
	Vector Deal_sts=new Vector();
	Vector Deal_duration=new Vector();
	Vector Deal_amt_wout_tax=new Vector();
	Vector Deal_amt_PAY_NOT_RECV=new Vector();
	Vector Deal_adv_amt=new Vector();
	Vector Deal_cont_no=new Vector();
	Vector Deal_typ_nm=new Vector();
	String sysdate="";
	/////////////////////
	
	//Variable Declaration for LAO Cargo Summary Mgmt, by Achal on 22nd October 2010 ...	
	Vector LOA_NO = new Vector();
	Vector LOA_CUSTOMER_CD = new Vector();
	Vector LOA_CARGO_ARRIVAL_DATE = new Vector();
	Vector LOA_REV_NO = new Vector();
	Vector TENDER_NO = new Vector();
	Vector LOA_BOOKMMBTU = new Vector();
	Vector LOA_SUPPLIEDMBTU = new Vector();
	Vector LOA_BALANCEMMBTU = new Vector();
	//Introduce By Milan 20110917 for MMSCM
	Vector LOA_BOOKMMSCM = new Vector();
	Vector LOA_SUPPLIEDMMSCM = new Vector();
	Vector LOA_BALANCEMMSCM = new Vector();
	
	Vector LOA_CUSTOMER_NM = new Vector();
	Vector LOA_CUSTOMER_ABBR = new Vector();
	Vector LOA_REF_NO = new Vector();
	
	//	Variable Declaration for Re-Gas Cargo Summary Mgmt, by Achal on 22nd October 2010 ...	
	Vector RE_GAS_NO = new Vector();
	Vector RE_GAS_CUSTOMER_CD = new Vector();
	Vector RE_GAS_CARGO_ARRIVAL_DATE = new Vector();
	Vector RE_GAS_SEQ_NO = new Vector();
	Vector RE_GAS_BOOKMMBTU = new Vector();
	Vector RE_GAS_SUPPLIEDMBTU = new Vector();
	Vector RE_GAS_BALANCEMMBTU = new Vector();
	Vector RE_GAS_CUSTOMER_NM = new Vector();
	Vector RE_GAS_CUSTOMER_ABBR = new Vector();
	
	//introduce By Milan 20110916
	Vector RE_GAS_BOOKMMSCM = new Vector();
	Vector RE_GAS_SUPPLIEDMMSCM = new Vector();
	Vector RE_GAS_BALANCEMMSCM = new Vector();
	
	//	Variable Declaration for TCQ Variation, by Achal on 23rd March 2011 ...	
	Vector SN_CLOSURE_DT = new Vector();
	Vector SN_CLOSURE_CLOSE = new Vector();	
	Vector TCQ_REQUEST_QTY = new Vector();
	Vector LOA_TCQ_REQUEST_QTY = new Vector();
	Vector ACTUAL_TCQ = new Vector();
	Vector LOA_ACTUAL_TCQ = new Vector();
	Vector SN_CLOSURE_QTY = new Vector();
	Vector TCQ_REQUEST_SIGN = new Vector();
	Vector LOA_TCQ_REQUEST_SIGN = new Vector();
	
	//Following 6 Vectors have been defined by Priyanka on 23rd March 2011 ...	
	Vector SIGNING_DT = new Vector();
	Vector RATE = new Vector();
	Vector LOA_SIGNING_DT = new Vector();
	Vector LOA_RATE = new Vector();
	Vector REGAS_SIGNING_DT = new Vector();
	Vector REGAS_RATE = new Vector();
	
//	FOLLOWING VECTOR HAVE BEEN DEFINED BY JAIMIN ON 17th SEP 2011...
	String cust_nm="";
	Vector CUSTOMER_CD_FGSA=new Vector();
	Vector CUSTOMER_ABR_FGSA=new Vector();
	Vector FGSA_NO_NEW=new Vector();
	Vector SIGNING_DT_FGSA=new Vector();
	Vector START_DT_FGSA=new Vector();
	Vector END_DT_FGSA=new Vector();
	Vector REV_NO_FGSA=new Vector();
	Vector RENEWAL_DT_FGSA=new Vector();
	Vector REV_DT_FGSA=new Vector();
	Vector EMP_CD_FGSA=new Vector();
	Vector EMP_ABR_FGSA=new Vector();
	Vector CUST_CD=new Vector();
	Vector CUST_NM=new Vector();
	Vector DELV_POINT=new Vector();
	
	//FOLLOWING VECTOR HAVE BEEN DEFINED BY Milan Dalsaniya ON 21st NOV 2011...
	// SUFFIX P TO THE VARIABLE STAND FOR THE PRICE DTL VECTORS FOR SN
	Vector CUSTOMER_CD_SN_P=new Vector();
	Vector CUSTOMER_ABR_SN_P=new Vector();
	Vector SN_NO_P =new Vector();
	
	Vector SN_REV_NO_P =new Vector();
	Vector FGSA_NO_P =new Vector();
	Vector FGSA_REV_NO_P =new Vector();
	Vector START_DT_SN_P=new Vector();
	Vector END_DT_SN_P=new Vector();
	Vector RATE_SN_P=new Vector();

	Vector CARGO_REF_NO_SN_P=new Vector();
	Vector CARGO_CONF_PRICE_DT_SN_P=new Vector();
	Vector CARGO_CUSTOM_DUTY_SN_P=new Vector();
	Vector CARGO_MARGIN_SN_P=new Vector();
	Vector COLOR_FLAG_RATE_SN_P=new Vector();
	Vector COLOR_FLAG_CUSTOM_SN_P=new Vector();
	Vector COLOR_FLAG_CONF_PRICE_SN_P=new Vector();
	Vector COLOR_FLAG_MARGIN_SN_P=new Vector();
	

	//for customer wise allocation
	Vector Vgas_date=new Vector();
	Vector[] SUPPLIEDMBTU1 ; 
	Vector[] SUPPLIEDMMSCM1; 
	Vector[] RE_GAS_SUPPLIEDMBTU1;
	Vector[] RE_GAS_SUPPLIEDMMSCM1;
	Vector[] LOA_SUPPLIEDMBTU1; 
	Vector[] LOA_SUPPLIEDMMSCM1; 
	Vector Regas_cargo_ref_no=new Vector(); 
	
	//Following variable declare for regas cargo energy statment
	public String Buyer_cd = "0";
	public String re_gas_no = "0";
	public String no_of_cargo = "1";
	public String frm_dt = "";//JHP
	public String to_dt="";//JHP
	public Vector count=new Vector();//JHP
	public String re_gas_rev_no="";//JHP 
	
	
	public Vector EDQ_FROM_DT = new Vector();
	public Vector EDQ_TO_DT = new Vector();
	public Vector ACTUAL_RECPT_DT = new Vector();
	public Vector CONTRACT_START_DT = new Vector();
	public Vector CONTRACT_END_DT = new Vector();
	public Vector ADQ_QTY = new Vector();
	public Vector SYS_USE_GAS = new Vector();
	public Vector QTY_TO_BE_SUPPLY = new Vector();
	public Vector DCQ_QTY = new Vector();
	public Vector QTY_UNIT_CD = new Vector();
	public Vector QTY_UNIT_ABR = new Vector();
	public Vector BOE_QTY = new Vector(); 
	public Vector SUPP_CD = new Vector(); 
	public Vector SUPP_NM = new Vector();
	public Vector BOE_NO = new Vector();
	public Vector BOE_DT = new Vector();
	public Vector BOE_DT_1=new Vector();
	
	NumberFormat nf3 = new DecimalFormat("###########0");
	
	public Vector SUPP_SEQ_NO= new Vector();
	public Vector SUPP_CONTACT_PERSON= new Vector();
	public Vector SUPP_CONTACT_PERSON_DESG= new Vector();
	public String setcargo_ref_no="-";
	public Vector cust_plant_cd= new Vector();
	public Vector cust_plant_nm= new Vector();
	public Vector Vdatewise_plant_tot=new Vector();
	public Vector QQ_NO=new Vector();
	public Vector QQ_DT=new Vector();
	
	
	public String contact_Customer_Person_Address = "";
	public String contact_Customer_Person_City = "";
	public String contact_Customer_Person_Pin = "";
	public String contact_Customer_Person_Phone = "";
	public String contact_Customer_Person_Fax = "";
	public String contact_Customer_Person_State = "";
	public String contact_Customer_Person_Country = "";
	
	String Sign_dt="";
	
	String Suppl_Name = "";
	String Suppl_abbr="";
	
	Vector Vplant_inv_no=new Vector();
	
	/////////////////////Fetching extra parameter.....
	
	String kind_attn=new String();
	String subject=new String();
	String dear_sir=new String();
	String para[]=new String[4];
	String tbl_statmnt=new String();
	String quantity_quality_ref_no=new String();
	String vessel_arrival_dt=new String();
	String vessel_name=new String();
	String storage_st_dt=new String();
	String storage_end_dt=new String();
	String boe_no_dated=new String();
	String lng_unloaded=new String();
	String less=new String();
	String sug_unloaded=new String();
	String total_lng[]=new String[2];
	String thanks=new String();
	String faithful=new String();
	String hlpl=new String();
	
	
	///////////////// ADDED FOR LTCORA AND CN
	Vector LTCORA_NO=new Vector();
	Vector LTCORA_CUSTOMER_CD=new Vector();
	Vector LTCORA_CARGO_ARRIVAL_DATE=new Vector();
	Vector LTCORA_SEQ_NO=new Vector();
	Vector LTCORA_BOOKMMBTU=new Vector();
	Vector LTCORA_BOOKMMSCM=new Vector();
	Vector LTCORA_RATE=new Vector();
	Vector LTCORA_cargo_ref_no=new Vector();
	Vector LTCORA_CUSTOMER_NM=new Vector();
	Vector LTCORA_CUSTOMER_ABBR=new Vector();
	Vector LTCORA_SIGNING_DT=new Vector();
	Vector LTCORA_SUPPLIEDMBTU=new Vector();
	Vector LTCORA_SUPPLIEDMMSCM=new Vector();
	Vector LTCORA_BALANCEMMBTU=new Vector();
	Vector LTCORA_BALANCEMMSCM=new Vector();
	Vector[] LTCORA_SUPPLIEDMBTU1;
	Vector[] LTCORA_SUPPLIEDMMSCM1;
	
	String LTCORA_tot_commit="0";
	String LTCORA_tot_commit_mmscm="0";
	String LTCORA_sup_tot_comitment="0";
	String LTCORA_bal_tot_comitment="0";
	String LTCORA_sup_tot_comitment_mmscm="0";
	String LTCORA_bal_tot_comitment_mmscm="0";
	
	Vector CN_NO=new Vector();
	Vector CN_CUSTOMER_CD=new Vector();
	Vector CN_CARGO_ARRIVAL_DATE=new Vector();
	Vector CN_SEQ_NO=new Vector();
	Vector CN_BOOKMMBTU=new Vector();
	Vector CN_BOOKMMSCM=new Vector();
	Vector CN_RATE=new Vector();
	Vector CN_cargo_ref_no=new Vector();
	Vector CN_CUSTOMER_NM=new Vector();
	Vector CN_CUSTOMER_ABBR=new Vector();
	Vector CN_SIGNING_DT=new Vector();
	Vector CN_SUPPLIEDMBTU=new Vector();
	Vector CN_SUPPLIEDMMSCM=new Vector();
	Vector CN_BALANCEMMBTU=new Vector();
	Vector CN_BALANCEMMSCM=new Vector();
	Vector[] CN_SUPPLIEDMBTU1;
	Vector[] CN_SUPPLIEDMMSCM1;
	
	String CN_tot_commit="0";
	String CN_tot_commit_mmscm="0";
	String CN_sup_tot_comitment="0";
	String CN_bal_tot_comitment="0";
	String CN_sup_tot_comitment_mmscm="0";
	String CN_bal_tot_comitment_mmscm="0";
	
	//RG20200704
	Vector ltcora_agr_no=new Vector();
	Vector cn_agr_no=new Vector();
	Vector sn_ent_by=new Vector();
	Vector sn_ent_dt=new Vector();
	Vector loa_ent_by=new Vector();
	Vector loa_ent_dt=new Vector();
	Vector ltcora_ent_by=new Vector();
	Vector ltcora_ent_dt=new Vector();
	Vector cn_ent_by=new Vector();
	Vector cn_ent_dt=new Vector();
	Vector sn_apr_by=new Vector();
	Vector sn_apr_dt=new Vector();
	Vector loa_apr_by=new Vector();
	Vector loa_apr_dt=new Vector();
	Vector ltcora_apr_by=new Vector();
	Vector ltcora_apr_dt=new Vector();
	Vector cn_apr_by=new Vector();
	Vector cn_apr_dt=new Vector();
	
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
	    			stmt1= conn.createStatement();
	    			stmt2= conn.createStatement();	
	    			stmt3= conn.createStatement();	
	    			if(callFlag.equalsIgnoreCase("Cargo_Allocation_Selection"))
	    			{
	    					System.out.println("MR Ver.2: Exposure Report >>>>>>>>>>> Started");
	    					//SB20210409		addColumnsFMS9_MRCR_CONT_PRICE_DTL(); //SB20210308
	    					addColumnsFMS7_MAN_CONFIRM_CARGO_DTL();//SB20210616: To add DOM_BUY_FLAG=Y  for Domestic Buy Deal else International
	    					UpdateFMS9_CURVE2_PRICE_DTL(); //SB20210409
	    					UpdateFMS7_MAN_CONFIRM_CARGO_DTL(); //SB20210705
	    					fetchEligibleCargoList();  	
	    					MRCR_Cargo_Cancelled_Deal();//SB20210805
	    			///SB20210525:No longer required		Fetch_ContExpire_LIST();
	    			}
	    			else if(callFlag.equalsIgnoreCase("fetchDealExposureDtlV2")) //SB20210202
	    			{
	    				clear();//HARSH20210119
	    					fetchDealExposureDtl();
	    					MRCR_MTM();
	    					//SB20210504	MRCR_ContDtlSubTotal();
	    					MRCR_ContDtlTotal();
	    					/////////////////////////////////////
							String DealNo=""; String Report_Dt=to_date;
							DealNo=Cont_Type+DealCustCd+"-"+FgsaNo+"-"+FgsaRevNo+"-"+SnNo+"-"+SnRevNo;//MAPPING_ID
						//	if(Cont_Type.equals("B")) //SB20201127
							//	DealNo=Cont_Type+SnNo+"-"+SnRevNo+"-"+DealRefNo+"-0"; //SB20201127
						//SB20210202	MarketExposureDealWiseFreezeDtl(DealNo, Report_Dt); //SB20201920: Populate Data into Table
							String Mapp_Id=DealCustCd+"-"+FgsaNo+"-"+FgsaRevNo+"-"+SnNo+"-%"; //SB20210528
						////	if(Cont_Type.equals("B")) //SB20201127
							///	Mapp_Id=DealCustCd+"-"+SnNo+"-"+SnRevNo+"-"+CargoRefCd+"-0"; //SB20201127
					///		MRCR_ContDtlFinBreakUp(Mapp_Id, Cont_Type); //SB20210710
	    			}
	    			else if(callFlag.equalsIgnoreCase("fetchDealExposureDtlV3")) //SB20210202
	    			{
	    				clear();//HARSH20210119
	    					fetchDealExposureDtl();	    				
							String Mapp_Id=DealCustCd+"-"+FgsaNo+"-"+FgsaRevNo+"-"+SnNo+"-%"; //SB20210528	
							MRCR_ContDtlFinBreakUp(Mapp_Id, Cont_Type); //SB20210710
	    			}
	    		/*SB20210709: Looks like Not in USE as on date
	    		 	else if(callFlag.equalsIgnoreCase("fetchDealExposureDtlV2Expired")) //SB20210223
	    			{
	    					clear();//HARSH20210119
	    					fetchDealExposureDtlExpired(); //SB20210223
	    					MRCR_MTM();
	    					MRCR_ContDtlSubTotal();
	    					MRCR_ContDtlTotal();	    		
	    			}*/
	    			else if(callFlag.equalsIgnoreCase("DeleteEoDProcessData")) //HARSH20210602
	    			{
	    				String Day_name="";
						String sys_timing = "";
						queryString="select to_char(sysdate-1,'dd/mm/yyyy'),'01/'||to_char(sysdate,'mm/yyyy'),to_char(TO_DATE('"+to_date+"','dd/mm/yyyy'),'Day'),to_char(sysdate,'dd/mm/yyyy hh24:mi') " +
			 	                  " from dual";
						System.out.println(queryString);   
						rset=stmt.executeQuery(queryString);
						if(rset.next())
						{
							Day_name=rset.getString(3)==null?"":rset.getString(3);//HARSH20210323
							sys_timing = rset.getString(4)==null?"":rset.getString(4);//HARSH20210324
						}
						System.out.println(Day_name);
						if(!Day_name.trim().toUpperCase().equalsIgnoreCase("SATURDAY") && !Day_name.trim().toUpperCase().equalsIgnoreCase("SUNDAY"))
						{
							String Report_Dt=to_date;
							deleteEoDProcessData(Report_Dt); //HARSH20210602
						}
	    			}
	    			else if(callFlag.equalsIgnoreCase("fetchDealExposureDtl"))   //SB20210202: For Multi CSV/XLS files
	    			{
	    				
							////////////////////////SB20210410: No Action on SAT/SUN///////////////
							String Day_name="";
							String sys_timing = "";
							queryString="select to_char(sysdate-1,'dd/mm/yyyy'),'01/'||to_char(sysdate,'mm/yyyy'),to_char(TO_DATE('"+to_date+"','dd/mm/yyyy'),'Day'),to_char(sysdate,'dd/mm/yyyy hh24:mi') " +
				 	                  " from dual";
							 System.out.println(queryString);   
							rset=stmt.executeQuery(queryString);
								 if(rset.next())
								 {
									 Day_name=rset.getString(3)==null?"":rset.getString(3);//HARSH20210323
									 sys_timing = rset.getString(4)==null?"":rset.getString(4);//HARSH20210324
								 }
								 System.out.println("Day Name: "+Day_name);
								
								 if(!Day_name.trim().toUpperCase().equalsIgnoreCase("SATURDAY") && !Day_name.trim().toUpperCase().equalsIgnoreCase("SUNDAY"))
									 //	 else
								 {//to_date="01/04/2021";
									 System.out.println("Setected Day: "+Day_name);
									 clear();//HARSH20210119
				    			/*HARSH20210901		fetchDealExposureDtl();
				    					MRCR_MTM();
				    			//SB20210504	MRCR_ContDtlSubTotal();
				    					MRCR_ContDtlTotal();
				    					/////////////////////////////////////
										String DealNo=""; String Report_Dt=to_date;
										DealNo=Cont_Type+DealCustCd+"-"+FgsaNo+"-"+FgsaRevNo+"-"+SnNo+"-"+SnRevNo;//MAPPING_ID
									 MarketExposureDealWiseFreezeDtl(DealNo, Report_Dt); //SB20201920: Populate Data into Table
								HARSH20210901*/
									 
									//HARSH20210828
								 	fetchDealExposureDtl();
								 	String Mapp_Id=DealCustCd+"-"+FgsaNo+"-"+FgsaRevNo+"-"+SnNo+"-%"; 
									MRCR_ContDtlFinBreakUp(Mapp_Id, Cont_Type);
									///////////////////////////
			    					/////////////////////////////////////
									String DealNo=""; String Report_Dt=to_date;
									DealNo=Cont_Type+DealCustCd+"-"+FgsaNo+"-"+FgsaRevNo+"-"+SnNo+"-"+SnRevNo;
									MarketExposureDealWiseFreezeDtl_V2(DealNo, Report_Dt);
								 }
							///////////////SB20210909: QP Files to generate////////////////////////////////////////////////////////
					/*SB20210917: Not Needed indentified by Harsh		ExposureDealWiseFreezeDtlViewForQP_V2MIN_OPAL(to_date);//SB20210901: Multiple rows with SEw_NO for MIN/OPAL
				    		ExposureDealWiseFreezeDtlViewForQP_PriceV2AllSettlePrice(to_date);//SB20210901
*/	    			}
	    			////////////////////SB20210909: added from Market_Risk.java /////////////////////////////////
	    			else if(callFlag.equalsIgnoreCase("fetchExposureForQPV2")) //Sb20210329
	    			{
	    			//	MarketExposureDealWiseFreezeDtlView(to_date);
	    			//HARSH20210901	ExposureDealWiseFreezeDtlViewForQP(to_date);
	    			//HARSH20210901	ExposureDealWiseFreezeDtlViewForQP_PriceV2(to_date); //SB20210329
	    				
	    				ExposureDealWiseFreezeDtlViewForQP_V2MIN_OPAL(to_date);//SB20210901: Multiple rows with SEw_NO for MIN/OPAL
	    				ExposureDealWiseFreezeDtlViewForQP_PriceV2AllSettlePrice(to_date);//SB20210901
	    			}
	    			////////////////^^^^^SB20210909///////////////////////
	    			else if(callFlag.equalsIgnoreCase("Fetch_Deal_Dtls"))
	    			{
	    				Fetch_Deal_Dtls();
	    			}
	    			else if(callFlag.equalsIgnoreCase("TCQ_Variation"))
	    			{
	    				fetchTCQVariationList();	    				
	    			}
	    			else if(callFlag.equalsIgnoreCase("FGSA"))  //JHP20110917
	    			{
	    				fetchcustomer();
	    				fetchfgsaList();
	    			}	
	    			else if(callFlag.equalsIgnoreCase("FGSA_SN_PRICE"))  //MD20111121
	    			{
	    				//System.out.println("MD "+callFlag);
	    				fetchcustomer();
	    				fetchFGSASNPRICEList();
	    				
	    			}
	    			else if(callFlag.equalsIgnoreCase("DATA_FETCH"))  //MD20111122 FOR PORPOSE TO VIEW DATA
	    			{
	    				//System.out.println("MD "+callFlag);
	    				
	    			}
	    			else if(callFlag.equalsIgnoreCase("Cargo_Allocation_customer"))
	    			{
	    				fetchEligibleCargoList_new();	    				
	    			} 
	    			else if(callFlag.equalsIgnoreCase("RE_GAS_CARGO_DTL"))
	    			{
	    				Customer_DTL();
					    Fetch_RE_GAS_CARGO_DTL();
					    Fetch_extra_parameters_pdf_xcl();
					}
	    			else if(callFlag.equalsIgnoreCase("RE_GAS_SERVICES"))
	    			{
	    				Customer_DTL();
					    Fetch_RE_GAS_CARGO_Services();
					//    //System.out.println("setcargo_ref_no"+setcargo_ref_no);
					    if(!setcargo_ref_no.equalsIgnoreCase("0"))
					    {
					    	Fetch_RE_GAS_CARGO_wise();
					    	fetchcargo_allocation_plantwise();
					    }
	    			}
	    			////////////SB20200908///////Added for Price type Change////////////////////
	    			else if(callFlag.equalsIgnoreCase("SN_DCQ_LIST")) 
	    			{
	    			//	Fetch_SN_DCQ_LIST();
	    			//SB20210730	Var_Price_Phys_Fin_LIST();
	    				Var_Price_Phys_Fin_LIST2();
	    			}
	    			//FGSA_SN_PRICE  
	    			conn.close();
	    			conn = null;
	    		}
	    	}
	    }	    
	    catch(Exception e)
	    {
	    	//System.out.println("Exception:(DataBean_Cargo_Processing)-(init()): "+e);
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
					//System.out.println("rset1 is not close " + e);
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
					//System.out.println("rset2 is not close " + e);
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
					//System.out.println("rset2 is not close " + e);
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
					//System.out.println("stmt1 is not close " + e);
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
					//System.out.println("stmt2 is not close " + e);
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
					//System.out.println("stmt2 is not close " + e);
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
					//System.out.println("conn is not close " + e);
				}
			}
	    }
	}	
	
	public void Fetch_extra_parameters_pdf_xcl()
	{
		try
		{
			kind_attn="Kind Attn: The General Manager-Marketing";
			subject="Subject: Energy Statement";
			dear_sir="Dear Sir,";
			para[0]="Please find below the statement showing the LNG unloaded quantities and the energy to be delivered to ";
			para[1]=" as RLNG,under Re-gas agreement dated ";
			para[2]=",Binding Side Letter dated ";
			para[3]=" and subsequent side letters, therein.";
			tbl_statmnt="Statement of Energy delivered under the Regas agreement between ";
			quantity_quality_ref_no="Quantity & Quality Certificate Ref no.";
			vessel_name="Vessel Name";
			vessel_arrival_dt="Vessel Arrival Date";
			storage_st_dt="Storage period Start Date";
			storage_end_dt="Storage period End Date";
			boe_no_dated="BOE NO.";
			lng_unloaded="LNG unloaded as per the certificate of Quantity & Quality";
			sug_unloaded="SUG as per the Re-gas agreement";
			less="LESS:";
			total_lng[0]="LNG to be Regassified and delivered as RLNG to ";
			total_lng[1]=" till end of storage period";
			thanks="Thanking you";
			faithful="Your Faithfully,";
		//RG20190326 for company change */	hlpl="For Hazira LNG Pvt. Ltd.";
			hlpl="For Shell Energy India Pvt. Ltd.";
		}
		catch(Exception e)
		{
			//System.out.println("Exception in Fetching extra Parameters....."+e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void Customer_DTL()
	{
		try
		{
			queryString = "SELECT CUSTOMER_CD,CUSTOMER_NAME,CUSTOMER_ABBR FROM FMS7_CUSTOMER_MST ORDER BY CUSTOMER_CD";
			////System.out.println("FMS7_CUSTOMER_MST Fetch Query = "+queryString);
			rset = stmt.executeQuery(queryString);				
			while(rset.next())
			{
				CUST_CD.add(rset.getString(1)==null?"":rset.getString(1));	
				CUST_NM.add(rset.getString(3)==null?"":rset.getString(3));
			}
//			Query for Contact Name,Telephone,Mobile of Supplier
			queryString1 = "SELECT A.SEQ_NO,A.CONTACT_PERSON,A.CONTACT_DESIG,A.PHONE,A.MOBILE  FROM FMS7_SUPPLIER_CONTACT_MST A " +
			   "WHERE A.NOM_FLAG='Y' AND A.EFF_DT=(SELECT MAX(B.EFF_DT) FROM FMS7_SUPPLIER_CONTACT_MST B WHERE " +
			   "A.SEQ_NO=B.SEQ_NO AND B.EFF_DT<=TO_DATE(to_char(sysdate,'DD/MM/YYYY'),'DD/MM/YYYY'))";
		//	//System.out.println("FMS7_SUPPLIER_CONTACT_MST Query = "+queryString1);
			rset1 = stmt1.executeQuery(queryString1);
			while(rset1.next())
			{
				SUPP_SEQ_NO.add(rset1.getString(1)==null?"0":rset1.getString(1));
				SUPP_CONTACT_PERSON.add(rset1.getString(2)==null?"":rset1.getString(2));
				SUPP_CONTACT_PERSON_DESG.add(rset1.getString(3)==null?"":rset1.getString(3));
				//PHONE.add(rset1.getString(4)==null?"":rset1.getString(4));
				//MOBILE.add(rset1.getString(5)==null?"":rset1.getString(5));
			}
			
			queryString = "SELECT addr,city,pin,phone,fax_1,state,country " +
			  "FROM FMS7_CUSTOMER_ADDRESS_MST A " +
			  "WHERE A.customer_cd="+Buyer_cd+" AND A.address_type='R' AND " +
			  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_ADDRESS_MST B " +
			  "WHERE A.customer_cd=B.customer_cd AND B.address_type='R' AND " +
			  "B.eff_dt<=TO_DATE(to_char(sysdate,'DD/MM/YYYY'),'DD/MM/YYYY'))";
	//		//System.out.println("Customer Address Fetch Query = "+queryString);
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
			
						
			queryString = "SELECT TO_CHAR(signing_dt,'fmddth') " +
			  "|| ' ' || TO_CHAR(signing_dt,'fmMonth') " +
			  "|| ' ' || TO_CHAR(signing_dt,'yyyy') " +
			  "FROM FMS7_RE_GAS_MST WHERE " +
			  "re_gas_no="+re_gas_no+" AND rev_no='"+re_gas_rev_no+"'AND " +
			  "customer_cd="+Buyer_cd+" " +
			  "ORDER BY rev_no DESC";
	//		//System.out.println("queryString:"+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				Sign_dt=rset.getString(1);
			}
			
			queryString = "SELECT supplier_name,SUPPLIER_ABBR " +
			  "FROM FMS7_SUPPLIER_MST A " +
			  "WHERE A.supplier_cd=1 AND " +
			  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_SUPPLIER_MST B " +
			  "WHERE A.supplier_cd=B.supplier_cd AND " +
			  "B.eff_dt<=TO_DATE(to_char(sysdate,'dd/mm/yyyy'),'DD/MM/YYYY'))";
	//		//System.out.println("Supplier Details Fetch Query = "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				Suppl_Name = rset.getString(1)==null?"":rset.getString(1);
				Suppl_abbr = rset.getString(2)==null?"":rset.getString(2);
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
			//System.out.println("RE_GAS Fetch DATA Query = "+queryString);
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
			for(int i=1; i<=Integer.parseInt(no_of_cargo); i++)
			{
				queryString = "SELECT cargo_ref_no, " +
							"TO_CHAR(edq_from_dt,'dd-Mon-yy'), " +
							  "TO_CHAR(edq_to_dt,'dd-Mon-yy')," +
							  " TO_CHAR(actual_recpt_dt,'dd-Mon-yy'), " +
							  "TO_CHAR(contract_start_dt,'dd-Mon-yy')," +
							  " TO_CHAR(contract_end_dt,'dd-Mon-yy'), " +
							  "ship_cd,adq_qty,sys_use_gas," +
							  "qty_to_be_supply,dcq_qty,re_gas_tarif, " +
							  "qty_unit_cd,REGAS_CLOSURE_REQUEST,REGAS_CLOSURE_CLOSE," +
							  "TO_CHAR(REGAS_CLOSURE_DT,'dd/mm/yyyy'),REGAS_CLOSURE_QTY,BOE_NO," +
							  "TO_CHAR(BOE_DT,'dd/mm/yyyy'),BOE_QTY, NVL(SUPP_CD,'0'), " +
							  "NVL(SUPP_NM,''),NVL(QQ_NO,''),TO_CHAR(QQ_DT,'fmMonth') || ' ' || TO_CHAR(QQ_DT,'fmdd') || ',' || TO_CHAR(QQ_DT,'yyyy'), " +
							  "TO_CHAR(BOE_DT,'dd-Mon-yy') " +
							  "FROM FMS7_RE_GAS_CARGO_DTL WHERE customer_cd="+Buyer_cd+" AND " +
							  "re_gas_no="+re_gas_no+" AND cargo_seq_no="+i+"";
			//	//System.out.println("REGAS:QRY-R2001:SELECT:FMS7_RE_GAS_CARGO_DTL: "+queryString);
				////System.out.println("RE-GAS Cargo Contract Detail Fetch Query = "+queryString);
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
					//DCQ_QTY.add(rset.getString(11)==null?"":rset.getString(11));
					//RE_GAS_TARIFF.add(temp_teriff);
					QTY_UNIT_CD.add(rset.getString(13)==null?"1":rset.getString(13));
					
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
					BOE_DT_1.add(rset.getString(25)==null?"":rset.getString(25));

					

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
				//	DCQ_QTY.add("");
				//	RE_GAS_TARIFF.add("");
					QTY_UNIT_CD.add("1");
					BOE_NO.add("");
					BOE_DT.add("");
					BOE_QTY.add("");
					SUPP_CD.add("0");
					SUPP_NM.add("");
				//	count.add("0");
					QQ_NO.add("");
					QQ_DT.add("");
					BOE_DT_1.add("");
				}
				
			}
			
			for(int i=0; i<SHIP_CD.size(); i++)
			{
				queryString = "SELECT ship_name FROM FMS7_SHIP_MST WHERE ship_cd="+SHIP_CD.elementAt(i)+"";
				////System.out.println("REGAS:QRY-R2002:SELECT:FMS7_SHIP_MST: "+queryString);
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
				////System.out.println("REGAS:QRY-R2003:SELECT:FMS7_UNIT_MST: "+queryString);
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
	public void Fetch_RE_GAS_CARGO_Services()
	{
		try
		{
			for(int i=1; i<=Integer.parseInt(no_of_cargo); i++)
			{
				queryString = "SELECT cargo_ref_no, " +
							"TO_CHAR(edq_from_dt,'dd-Mon-yy'), " +
							  "TO_CHAR(edq_to_dt,'dd-Mon-yy')," +
							  " TO_CHAR(actual_recpt_dt,'dd-Mon-yy'), " +
							  "TO_CHAR(contract_start_dt,'dd/mm/yyyy')," +
							  " TO_CHAR(contract_end_dt,'dd/mm/yyyy'), " +
							  "ship_cd,adq_qty,sys_use_gas," +
							  "qty_to_be_supply,dcq_qty,re_gas_tarif, " +
							  "qty_unit_cd,REGAS_CLOSURE_REQUEST,REGAS_CLOSURE_CLOSE," +
							  "TO_CHAR(REGAS_CLOSURE_DT,'dd/mm/yyyy'),REGAS_CLOSURE_QTY,BOE_NO," +
							  "TO_CHAR(BOE_DT,'dd/mm/yyyy'),BOE_QTY, NVL(SUPP_CD,'0'), " +
							  "NVL(SUPP_NM,'') " +
							  "FROM FMS7_RE_GAS_CARGO_DTL WHERE customer_cd="+Buyer_cd+" AND " +
							  "re_gas_no="+re_gas_no+" AND cargo_seq_no="+i+"";
				////System.out.println("REGAS:QRY-R2001:SELECT:FMS7_RE_GAS_CARGO_DTL: "+queryString);
				////System.out.println("RE-GAS Cargo Contract Detail Fetch Query = "+queryString);
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
				
				}
				
			}
							
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void Fetch_RE_GAS_CARGO_wise()
	{
		try
		{
			
				queryString = "SELECT cargo_ref_no, " +
							"TO_CHAR(edq_from_dt,'dd-Mon-yy'), " +
							  "TO_CHAR(edq_to_dt,'dd-Mon-yy')," +
							  " TO_CHAR(actual_recpt_dt,'dd-Mon-yy'), " +
							  "TO_CHAR(contract_start_dt,'dd/mm/yyyy')," +
							  " TO_CHAR(contract_end_dt,'dd/mm/yyyy'), " +
							  "ship_cd,adq_qty,sys_use_gas," +
							  "qty_to_be_supply,dcq_qty,re_gas_tarif, " +
							  "qty_unit_cd,REGAS_CLOSURE_REQUEST,REGAS_CLOSURE_CLOSE," +
							  "TO_CHAR(REGAS_CLOSURE_DT,'dd/mm/yyyy'),REGAS_CLOSURE_QTY,BOE_NO," +
							  "TO_CHAR(BOE_DT,'dd/mm/yyyy'),BOE_QTY, NVL(SUPP_CD,'0'), " +
							  "NVL(SUPP_NM,'') " +
							  "FROM FMS7_RE_GAS_CARGO_DTL WHERE customer_cd="+Buyer_cd+" AND " +
							  "re_gas_no="+re_gas_no+" AND cargo_ref_no='"+setcargo_ref_no+"'";
				//System.out.println("REGAS:QRY-R2001:SELECT:FMS7_RE_GAS_CARGO_DTL: "+queryString);
				////System.out.println("RE-GAS Cargo Contract Detail Fetch Query = "+queryString);
				rset = stmt.executeQuery(queryString);				
				if(rset.next())
				{
					double qty_supply = 0;
					EDQ_FROM_DT.add(rset.getString(2)==null?"":rset.getString(2));
					EDQ_TO_DT.add(rset.getString(3)==null?"":rset.getString(3));
					ACTUAL_RECPT_DT.add(rset.getString(4)==null?"":rset.getString(4));
					CONTRACT_START_DT.add(rset.getString(5)==null?"":rset.getString(5));
					CONTRACT_END_DT.add(rset.getString(6)==null?"":rset.getString(6));
					SHIP_CD.add(rset.getString(7)==null?"0":rset.getString(7));
					ADQ_QTY.add(rset.getString(8)==null?"":rset.getString(8));
					//SYS_USE_GAS.add(temp_sug);
					qty_supply = Double.parseDouble(rset.getString(10)==null?"0":rset.getString(10));
					//QTY_TO_BE_SUPPLY.add(rset.getString(10)==null?"":rset.getString(10));
					//DCQ_QTY.add(rset.getString(11)==null?"":rset.getString(11));
					//RE_GAS_TARIFF.add(temp_teriff);
					QTY_UNIT_CD.add(rset.getString(13)==null?"1":rset.getString(13));
					
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
				}
				else
				{
					EDQ_FROM_DT.add("");
					EDQ_TO_DT.add("");
					ACTUAL_RECPT_DT.add("");
					CONTRACT_START_DT.add("");
					CONTRACT_END_DT.add("");
					SHIP_CD.add("0");
					ADQ_QTY.add("");
					SYS_USE_GAS.add("");
					QTY_TO_BE_SUPPLY.add("");
				//	DCQ_QTY.add("");
				//	RE_GAS_TARIFF.add("");
					QTY_UNIT_CD.add("1");
					BOE_NO.add("");
					BOE_DT.add("");
					BOE_QTY.add("");
					SUPP_CD.add("0");
					SUPP_NM.add("");
				//	count.add("0");
					
				}
				
			
			
			for(int i=0; i<SHIP_CD.size(); i++)
			{
				queryString = "SELECT ship_name FROM FMS7_SHIP_MST WHERE ship_cd="+SHIP_CD.elementAt(i)+"";
				////System.out.println("REGAS:QRY-R2002:SELECT:FMS7_SHIP_MST: "+queryString);
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
				////System.out.println("REGAS:QRY-R2003:SELECT:FMS7_UNIT_MST: "+queryString);
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
	public void fetchEligibleCargoList_new()
	{
		try
		{			
			String tmp_from_dt = " ";
			String temp_dt="";
			String tmp_to_dt = " ";			
			double tot_comit=0.0;
			double sup_tot_comit=0.0;
			double bal_tot_comit=0.0;
			//Introduce By Milan 20110916 FOR MMSCM
			double tot_comit_mmscm=0.0;
			double sup_tot_comit_mmscm=0.0;
			double bal_tot_comit_mmscm=0.0;
			Vector FRM_DT=new Vector();
			Vector TO_DT=new Vector();						
			Vector RE_GAS_REV_NO=new Vector();
			Vector LTCORA_REV_NO=new Vector(); //ADDED FRO LTCORA AND CN
			Vector CN_REV_NO=new Vector(); //ADDED FRO LTCORA AND CN
			
			
			double count=0;
			String query="select to_date('"+to_date+"','dd/mm/yyyy')- to_date('"+from_date+"','dd/mm/yyyy')+1 from dual";
			rset=stmt.executeQuery(query);
			if(rset.next())
			{
				count=rset.getDouble(1);
			}
			
			for(int i=0;i<count;i++)
			{
				String query1="select to_char(to_date('"+from_date+"','dd/mm/yyyy')+ "+i+",'dd/mm/yyyy') from dual";
				rset=stmt.executeQuery(query1);
				if(rset.next())
				{
					Vgas_date.add(rset.getString(1));
				}
				
			}
			
			
			
			/*queryString ="select TO_CHAR(A.START_DT,'DD/MM/YYYY'),TO_CHAR(A.END_DT,'DD/MM/YYYY'),A.SN_NO,A.SN_REV_NO,A.CUSTOMER_CD,A.FGSA_NO,A.FGSA_REV_NO,A.TCQ, " +
			 		"A.SN_REF_NO,to_char(A.SIGNING_DT,'dd/mm/yyyy'),A.RATE from FMS7_SN_MST A where (A.START_DT between to_date('"+from_date+"','dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy')"+ 
			 		"OR A.END_DT between to_date('"+from_date+"','dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy')) AND " +
			 		"A.SN_REV_NO=(SELECT MAX(B.SN_REV_NO) FROM FMS7_SN_MST B where A.SN_NO=B.SN_NO AND A.FGSA_NO=B.FGSA_NO AND " +
			 		"A.CUSTOMER_CD=B.CUSTOMER_CD AND A.FGSA_REV_NO=B.FGSA_REV_NO) order by A.START_DT";*/
			
			//Updated By Milan 20110921
			queryString ="select TO_CHAR(A.START_DT,'DD/MM/YYYY'),TO_CHAR(A.END_DT,'DD/MM/YYYY'),A.SN_NO,A.SN_REV_NO,A.CUSTOMER_CD,A.FGSA_NO,A.FGSA_REV_NO,A.TCQ, " +
	 		"A.SN_REF_NO,to_char(A.SIGNING_DT,'dd/mm/yyyy'),A.RATE from FMS7_SN_MST A where "+ 
	 		"(A.START_DT <= to_date('"+to_date+"','dd/mm/yyyy')"+"AND A.END_DT >= to_date('"+from_date+"','dd/mm/yyyy')) AND " +
	 		"A.SN_REV_NO=(SELECT MAX(B.SN_REV_NO) FROM FMS7_SN_MST B where A.SN_NO=B.SN_NO AND A.FGSA_NO=B.FGSA_NO AND " +
	 		"A.CUSTOMER_CD=B.CUSTOMER_CD AND A.FGSA_REV_NO=B.FGSA_REV_NO) order by A.START_DT";
			
			//(START_DT <= to_date('"+to_date+"','dd/mm/yyyy')"+"AND END_DT >= to_date('"+from_date+"','dd/mm/yyyy'))
			//System.out.println(queryString);
			rset = stmt.executeQuery(queryString);
			////System.out.println("FOr SN : "+to_date+" "+from_date);
			while(rset.next())
			{				
				////System.out.println("FOr SN : "+rset.getString(1)+" "+rset.getString(2));
				SN_NO.add(rset.getString(3)==null?"0":rset.getString(3));
				CUSTOMER_CD.add(rset.getString(5)==null?"0":rset.getString(5));									
				tmp_from_dt = rset.getString(1)==null?"":rset.getString(1);
				tmp_to_dt = rset.getString(2)==null?"":rset.getString(2);
				FRM_DT.add(rset.getString(1)==null?"":rset.getString(1));
				TO_DT.add(rset.getString(2)==null?"":rset.getString(2));
				temp_dt = tmp_from_dt+"   -   "+tmp_to_dt;
				CARGO_ARRIVAL_DATE.add(temp_dt);				
				SN_REV_NO.add(rset.getString(4)==null?"0":rset.getString(4));			  	
			  	FGSA_NO.add(rset.getString(6)==null?"0":rset.getString(6));	
			  	FGSA_REV_NO.add(rset.getString(7)==null?"0":rset.getString(7));	
			  	BOOKMMBTU.add(rset.getString(8)==null?"0.00":nf.format(Double.parseDouble(rset.getString(8))));
			  	BOOKMMSCM.add(rset.getString(8)==null?"0.00":nf.format(Double.parseDouble(rset.getString(8))/38900));//MD20110916
			  	tot_comit += Double.parseDouble(rset.getString(8)==null?"0":nf.format(Double.parseDouble(rset.getString(8))));
			  	
			  	tot_comit_mmscm=(tot_comit/38900);//MD20110916
			  	tot_commitment_mmscm=nf.format(tot_comit_mmscm);
			  	////System.out.println("BOOKEDMMSCM : "+BOOKMMSCM+" TOTAL : "+tot_comit_mmscm);
			  	
			  	tot_commitment=nf.format(tot_comit);
			  	SN_REF_NO.add(rset.getString(9)==null?"0":rset.getString(9));	
			  	SIGNING_DT.add(rset.getString(10)==null?"0":rset.getString(10));
			  	RATE.add(nf.format(Double.parseDouble(rset.getString(11)==null?"0":rset.getString(11))));
			}	
			
			//System.out.println("SN_REF_NO = "+SN_REF_NO);
			for(int i=0;i<CUSTOMER_CD.size();i++)
			{
				queryString ="select CUSTOMER_ABBR,CUSTOMER_NAME,CUSTOMER_CD from FMS7_CUSTOMER_MST where CUSTOMER_CD="+CUSTOMER_CD.elementAt(i)+" "
						+ "AND EFF_DT=(SELECT MAX(EFF_DT) FROM FMS7_CUSTOMER_MST WHERE CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"' AND EFF_DT<=TO_DATE('"+from_date+"','DD/MM/YYYY'))";
				rset = stmt.executeQuery(queryString);
				////System.out.println(queryString);
				if(rset.next())
				{
					CUSTOMER_NM.add(rset.getString(2)==null?"":rset.getString(2));
					CUSTOMER_ABBR.add(rset.getString(1)==null?"":rset.getString(1));	
				}
				else
				{
					CUSTOMER_NM.add("");
					CUSTOMER_ABBR.add("");
				}												
//				queryString ="select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL where CUSTOMER_CD="+CUSTOMER_CD.elementAt(i)+" " +
//							 "AND SN_NO="+SN_NO.elementAt(i)+" AND FGSA_NO="+FGSA_NO.elementAt(i)+" " +
//							 "AND FGSA_REV_NO="+FGSA_REV_NO.elementAt(i)+" AND GAS_DT between to_date('"+FRM_DT.elementAt(i)+"','dd/mm/yyyy')" +
//							 "AND to_date('"+TO_DT.elementAt(i)+"','dd/mm/yyyy') AND CONTRACT_TYPE='S'";
				queryString ="select SUM(QTY_MMBTU),SUM(QTY_SCM) from FMS7_DAILY_ALLOCATION_DTL where CUSTOMER_CD="+CUSTOMER_CD.elementAt(i)+" " +
				 "AND SN_NO="+SN_NO.elementAt(i)+" AND FGSA_NO="+FGSA_NO.elementAt(i)+" " +
				 "AND FGSA_REV_NO="+FGSA_REV_NO.elementAt(i)+" AND GAS_DT between to_date('"+from_date+"','dd/mm/yyyy')" +
				 "AND to_date('"+to_date+"','dd/mm/yyyy') AND CONTRACT_TYPE='S'";

				rset = stmt.executeQuery(queryString);
				//System.out.println(">>>>>>> "+queryString);
				if(rset.next())
				{
					SUPPLIEDMBTU.add(rset.getString(1)==null?"0.00":nf.format(Double.parseDouble(rset.getString(1))));
					//MD20110916
					SUPPLIEDMMSCM.add(rset.getString(2)==null?"0.00":nf.format(Double.parseDouble(rset.getString(2))/1000000));
					if(rset.getString(1)!=null && !(rset.getString(1).equals("")))
					{
						sup_tot_comit+=Double.parseDouble(rset.getString(1));
						sup_tot_comit_mmscm+=(Double.parseDouble(rset.getString(1))/38900);//MD20110916
					}
					else
					{
						sup_tot_comit+=0;
						sup_tot_comit_mmscm+=0;//MD20110916
					}
				}
				else
				{
					SUPPLIEDMBTU.add("0.00");
					SUPPLIEDMMSCM.add("0.00");//MD20110916
					sup_tot_comit+=0;
					sup_tot_comit_mmscm+=0;//MD20110916
				}
				
				BALANCEMMBTU.add(nf.format(Double.parseDouble(""+BOOKMMBTU.elementAt(i))- Double.parseDouble(""+SUPPLIEDMBTU.elementAt(i))));
				//MD20110916
				BALANCEMMSCM.add(nf.format(Double.parseDouble(""+BOOKMMSCM.elementAt(i))- Double.parseDouble(""+SUPPLIEDMMSCM.elementAt(i))));
				bal_tot_comit+=Double.parseDouble(""+BOOKMMBTU.elementAt(i))- Double.parseDouble(""+SUPPLIEDMBTU.elementAt(i));
				bal_tot_comit_mmscm+=Double.parseDouble(""+BOOKMMSCM.elementAt(i))- Double.parseDouble(""+SUPPLIEDMMSCM.elementAt(i));
				//System.out.println("SUPLIEDMMSCM : "+SUPPLIEDMMSCM+" TOTAL : "+sup_tot_comit_mmscm);
				//System.out.println("BALANCEMMSCM : "+BALANCEMMSCM+" TOTAL : "+bal_tot_comit_mmscm);
		   }
			SUPPLIEDMBTU1=new Vector[Vgas_date.size()] ; 
			SUPPLIEDMMSCM1=new Vector[Vgas_date.size()] ;; 
			
			for(int k=0;k<Vgas_date.size();k++)
			{
			SUPPLIEDMBTU1[k] =new Vector(); 
			SUPPLIEDMMSCM1[k]=new Vector(); 
				
				for(int i=0;i<CUSTOMER_CD.size();i++)
				{

					queryString ="select SUM(QTY_MMBTU),SUM(QTY_SCM) from FMS7_DAILY_ALLOCATION_DTL where CUSTOMER_CD="+CUSTOMER_CD.elementAt(i)+" " +
					 "AND SN_NO="+SN_NO.elementAt(i)+" AND FGSA_NO="+FGSA_NO.elementAt(i)+" " +
					 "AND FGSA_REV_NO="+FGSA_REV_NO.elementAt(i)+" AND GAS_DT=to_date('"+Vgas_date.elementAt(k)+"','dd/mm/yyyy') AND CONTRACT_TYPE='S'";
					rset = stmt.executeQuery(queryString);
					////System.out.println(">>>>>>> "+queryString);
						if(rset.next())
						{
							SUPPLIEDMBTU1[k].add(rset.getString(1)==null?"-":nf.format(Double.parseDouble(rset.getString(1))));
							SUPPLIEDMMSCM1[k].add(rset.getString(2)==null?"-":nf.format(Double.parseDouble(rset.getString(2))/1000000));
							
						}
						else
						{
							SUPPLIEDMBTU1[k].add("-");
							SUPPLIEDMMSCM1[k].add("-");
							
						}
				}
			}

			
		   sup_tot_comitment=nf.format(sup_tot_comit);
		   bal_tot_comitment=nf.format(bal_tot_comit);
		   
		   //Introduce by Milan 20010916
		   sup_tot_comitment_mmscm=nf.format(sup_tot_comit_mmscm);
		   bal_tot_comitment_mmscm=nf.format(bal_tot_comit_mmscm);
			
		    //--------Logic for Tender and LOA based Summary Contract based Agreement	
			tot_comit=0.0;
			bal_tot_comit=0.0;
			sup_tot_comit=0.0;
			
			tot_comit_mmscm=0.0;
			sup_tot_comit_mmscm=0.0;
			bal_tot_comit_mmscm=0.0;
			FRM_DT.clear();
			TO_DT.clear();
			/*queryString =   "select TO_CHAR(A.START_DT,'DD/MM/YYYY'),TO_CHAR(A.END_DT,'DD/MM/YYYY'),A.LOA_NO,A.LOA_REV_NO,A.CUSTOMER_CD,A.TENDER_NO,A.TCQ, " +
	 						"A.LOA_REF_NO,to_char(A.SIGNING_DT,'dd/mm/yyyy'),A.RATE from FMS7_LOA_MST A where (A.START_DT between to_date('"+from_date+"','dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy')"+ 
	 						"OR A.END_DT between to_date('"+from_date+"','dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy')) AND " +
	 						"A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM FMS7_LOA_MST B where A.LOA_NO=B.LOA_NO AND A.TENDER_NO=B.TENDER_NO AND " +
	 						"A.CUSTOMER_CD=B.CUSTOMER_CD) order by A.START_DT";*/
			
			//Upadated By Milan 20110921
			queryString =   "select TO_CHAR(A.START_DT,'DD/MM/YYYY'),TO_CHAR(A.END_DT,'DD/MM/YYYY'),A.LOA_NO,A.LOA_REV_NO,A.CUSTOMER_CD,A.TENDER_NO,A.TCQ, " +
				"A.LOA_REF_NO,to_char(A.SIGNING_DT,'dd/mm/yyyy'),A.RATE from FMS7_LOA_MST A where "+ 
				"(A.START_DT <= to_date('"+to_date+"','dd/mm/yyyy')"+"AND A.END_DT >= to_date('"+from_date+"','dd/mm/yyyy')) AND " +
				"A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM FMS7_LOA_MST B where A.LOA_NO=B.LOA_NO AND A.TENDER_NO=B.TENDER_NO AND " +
				"A.CUSTOMER_CD=B.CUSTOMER_CD) order by A.START_DT";
			rset = stmt.executeQuery(queryString);
			//System.out.println(queryString);
			while(rset.next())
			{				
				LOA_NO.add(rset.getString(3)==null?"0":rset.getString(3));
				LOA_CUSTOMER_CD.add(rset.getString(5)==null?"0":rset.getString(5));									
				tmp_from_dt = rset.getString(1)==null?"":rset.getString(1);
				tmp_to_dt = rset.getString(2)==null?"":rset.getString(2);
				FRM_DT.add(rset.getString(1)==null?"":rset.getString(1));
				TO_DT.add(rset.getString(2)==null?"":rset.getString(2));
				temp_dt = tmp_from_dt+"   -   "+tmp_to_dt;
				LOA_CARGO_ARRIVAL_DATE.add(temp_dt);				
				LOA_REV_NO.add(rset.getString(4)==null?"0":rset.getString(4));			  	
			  	TENDER_NO.add(rset.getString(6)==null?"0":rset.getString(6));	
			  	LOA_BOOKMMBTU.add(rset.getString(7)==null?"0.00":nf.format(Double.parseDouble(rset.getString(7))));	
			  	tot_comit += Double.parseDouble(rset.getString(7)==null?"0":nf.format(Double.parseDouble(rset.getString(7))));
			  	loa_tot_commitment=nf.format(tot_comit) ;
			  	
			  	//INTRODUCE BY MILAN 20110917 FOR SCMMBTU
			  	LOA_BOOKMMSCM.add(rset.getString(7)==null?"0.00":nf.format(Double.parseDouble(rset.getString(7))/38900));	
			  	tot_comit_mmscm += Double.parseDouble(rset.getString(7)==null?"0":nf.format(Double.parseDouble(rset.getString(7))/38900));
			  	loa_tot_commitment_mmscm=nf.format(tot_comit_mmscm) ;
			  	
			  	LOA_REF_NO.add(rset.getString(8)==null?"0":rset.getString(8));
			  	LOA_SIGNING_DT.add(rset.getString(9)==null?"":rset.getString(9));
			  	LOA_RATE.add(nf.format(Double.parseDouble(rset.getString(10)==null?"0":rset.getString(10))));			  	
			}
			
			for(int i=0;i<LOA_CUSTOMER_CD.size();i++)
			{
				queryString ="select CUSTOMER_ABBR,CUSTOMER_NAME,CUSTOMER_CD from FMS7_CUSTOMER_MST where CUSTOMER_CD="+LOA_CUSTOMER_CD.elementAt(i)+" ";
				queryString ="select CUSTOMER_ABBR,CUSTOMER_NAME,CUSTOMER_CD from FMS7_CUSTOMER_MST where CUSTOMER_CD="+LOA_CUSTOMER_CD.elementAt(i)+" "
						+ "AND EFF_DT=(SELECT MAX(EFF_DT) FROM FMS7_CUSTOMER_MST WHERE CUSTOMER_CD='"+LOA_CUSTOMER_CD.elementAt(i)+"' AND EFF_DT<=TO_DATE('"+from_date+"','DD/MM/YYYY'))";
				rset = stmt.executeQuery(queryString);
				//System.out.println("LOA_CUSTOMER_CD = "+queryString);
				if(rset.next())
				{
					LOA_CUSTOMER_NM.add(rset.getString(2)==null?"":rset.getString(2));
					LOA_CUSTOMER_ABBR.add(rset.getString(1)==null?"":rset.getString(1));
				}
				else
				{
					LOA_CUSTOMER_NM.add("");
					LOA_CUSTOMER_ABBR.add("");
				}												
//				queryString ="select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL where CUSTOMER_CD="+LOA_CUSTOMER_CD.elementAt(i)+" " +
//							 "AND SN_NO="+LOA_NO.elementAt(i)+" AND FGSA_NO="+TENDER_NO.elementAt(i)+" " +
//							 "AND GAS_DT between to_date('"+FRM_DT.elementAt(i)+"','dd/mm/yyyy')" +
//							 "AND to_date('"+TO_DT.elementAt(i)+"','dd/mm/yyyy') AND CONTRACT_TYPE='L'";
				queryString ="select SUM(QTY_MMBTU),SUM(QTY_SCM) from FMS7_DAILY_ALLOCATION_DTL where CUSTOMER_CD="+LOA_CUSTOMER_CD.elementAt(i)+" " +
				 "AND SN_NO="+LOA_NO.elementAt(i)+" AND FGSA_NO="+TENDER_NO.elementAt(i)+" " +
				 "AND GAS_DT between to_date('"+from_date+"','dd/mm/yyyy')" +
				 "AND to_date('"+to_date+"','dd/mm/yyyy') AND CONTRACT_TYPE='L'";


				rset = stmt.executeQuery(queryString);
				//System.out.println("LOA_QTY_MMBTU = "+queryString);
				if(rset.next())
				{
					LOA_SUPPLIEDMBTU.add(rset.getString(1)==null?"0.00":nf.format(Double.parseDouble(rset.getString(1))));
					LOA_SUPPLIEDMMSCM.add(rset.getString(2)==null?"0.00":nf.format(Double.parseDouble(rset.getString(2))/1000000));
					if(rset.getString(1)!=null && !(rset.getString(1).equals("")))
					{
						sup_tot_comit+=Double.parseDouble(rset.getString(1));
						sup_tot_comit_mmscm+=(Double.parseDouble(rset.getString(1))/38900);
					}
					else
					{
						sup_tot_comit+=0;
						sup_tot_comit_mmscm+=0;
					}
				}
				else
				{
					LOA_SUPPLIEDMBTU.add("0.00");
					LOA_SUPPLIEDMMSCM.add("0.00");
					sup_tot_comit+=0;
					sup_tot_comit_mmscm+=0;
				}				
				LOA_BALANCEMMBTU.add(nf.format(Double.parseDouble(""+LOA_BOOKMMBTU.elementAt(i))- Double.parseDouble(""+LOA_SUPPLIEDMBTU.elementAt(i))));
				bal_tot_comit+=Double.parseDouble(""+LOA_BOOKMMBTU.elementAt(i))- Double.parseDouble(""+LOA_SUPPLIEDMBTU.elementAt(i));
				
				LOA_BALANCEMMSCM.add(nf.format(Double.parseDouble(""+LOA_BOOKMMSCM.elementAt(i))- Double.parseDouble(""+LOA_SUPPLIEDMMSCM.elementAt(i))));
				bal_tot_comit_mmscm+=Double.parseDouble(""+LOA_BOOKMMSCM.elementAt(i))- Double.parseDouble(""+LOA_SUPPLIEDMMSCM.elementAt(i));
		   }
			
			LOA_SUPPLIEDMBTU1=new Vector[Vgas_date.size()] ; 
			LOA_SUPPLIEDMMSCM1=new Vector[Vgas_date.size()] ;; 
			
			for(int k=0;k<Vgas_date.size();k++)
			{
				LOA_SUPPLIEDMBTU1[k] =new Vector(); 
				LOA_SUPPLIEDMMSCM1[k]=new Vector(); 
				
				for(int i=0;i<LOA_CUSTOMER_CD.size();i++)
				{

					queryString ="select SUM(QTY_MMBTU),SUM(QTY_SCM) from FMS7_DAILY_ALLOCATION_DTL where CUSTOMER_CD="+LOA_CUSTOMER_CD.elementAt(i)+" " +
					 "AND SN_NO="+LOA_NO.elementAt(i)+" AND FGSA_NO="+TENDER_NO.elementAt(i)+" " +
					 "AND GAS_DT=to_date('"+Vgas_date.elementAt(k)+"','dd/mm/yyyy')" +
					 " AND CONTRACT_TYPE='L'";
					rset = stmt.executeQuery(queryString);
					////System.out.println(">>>>>>> "+queryString);
						if(rset.next())
						{
							LOA_SUPPLIEDMBTU1[k].add(rset.getString(1)==null?"-":nf.format(Double.parseDouble(rset.getString(1))));
							LOA_SUPPLIEDMMSCM1[k].add(rset.getString(2)==null?"-":nf.format(Double.parseDouble(rset.getString(2))/1000000));
							
						}
						else
						{
							LOA_SUPPLIEDMBTU1[k].add("-");
							LOA_SUPPLIEDMMSCM1[k].add("-");
							
						}
				}
			}


			
			
		   loa_sup_tot_comitment=nf.format(sup_tot_comit);
		   loa_bal_tot_comitment=nf.format(bal_tot_comit);
		   
		   loa_sup_tot_comitment_mmscm=nf.format(sup_tot_comit_mmscm);
		   loa_bal_tot_comitment_mmscm=nf.format(bal_tot_comit_mmscm);
			
			//--------Logic for Re-Gas based Summary Contract based Agreement	
			tot_comit=0.0;
			bal_tot_comit=0.0;
			sup_tot_comit=0.0;
			//MD20110917
			tot_comit_mmscm=0.0;
			sup_tot_comit_mmscm=0.0;
			bal_tot_comit_mmscm=0.0;
			
			FRM_DT.clear();
			TO_DT.clear();
			/*queryString = "select TO_CHAR(A.CONTRACT_START_DT,'DD/MM/YYYY'),TO_CHAR(A.CONTRACT_END_DT,'DD/MM/YYYY'),A.RE_GAS_NO,A.RE_GAS_REV_NO,A.CUSTOMER_CD,A.CARGO_SEQ_NO,A.QTY_TO_BE_SUPPLY,A.RE_GAS_TARIF " +
	 					  "from FMS7_RE_GAS_CARGO_DTL A where (A.CONTRACT_START_DT between to_date('"+from_date+"','dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy')"+ 
	 					  "OR A.CONTRACT_END_DT between to_date('"+from_date+"','dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy')) AND " +
	 		              "A.RE_GAS_REV_NO=(SELECT MAX(B.RE_GAS_REV_NO) FROM FMS7_RE_GAS_CARGO_DTL B where A.RE_GAS_NO=B.RE_GAS_NO AND A.RE_GAS_REV_NO=B.RE_GAS_REV_NO AND " +
	 		              "A.CUSTOMER_CD=B.CUSTOMER_CD AND A.CARGO_SEQ_NO=B.CARGO_SEQ_NO) order by A.CONTRACT_START_DT";*/
			
			//Updated By Milan 20110921
			queryString = "select TO_CHAR(A.CONTRACT_START_DT,'DD/MM/YYYY'),TO_CHAR(A.CONTRACT_END_DT,'DD/MM/YYYY'),A.RE_GAS_NO,A.RE_GAS_REV_NO,A.CUSTOMER_CD,A.CARGO_SEQ_NO,A.QTY_TO_BE_SUPPLY,A.RE_GAS_TARIF ,A.cargo_ref_no " +
			  "from FMS7_RE_GAS_CARGO_DTL A where "+ 
			  "(A.CONTRACT_START_DT <= to_date('"+to_date+"','dd/mm/yyyy')"+"AND A.CONTRACT_END_DT >= to_date('"+from_date+"','dd/mm/yyyy')) AND " +
             "A.RE_GAS_REV_NO=(SELECT MAX(B.RE_GAS_REV_NO) FROM FMS7_RE_GAS_CARGO_DTL B where A.RE_GAS_NO=B.RE_GAS_NO AND A.RE_GAS_REV_NO=B.RE_GAS_REV_NO AND " +
             "A.CUSTOMER_CD=B.CUSTOMER_CD AND A.CARGO_SEQ_NO=B.CARGO_SEQ_NO) order by A.CONTRACT_START_DT";
			rset = stmt.executeQuery(queryString);
			//System.out.println(queryString);
			while(rset.next())
			{				
				RE_GAS_NO.add(rset.getString(3)==null?"0":rset.getString(3));
				RE_GAS_REV_NO.add(rset.getString(4)==null?"0":rset.getString(4));
				RE_GAS_CUSTOMER_CD.add(rset.getString(5)==null?"0":rset.getString(5));									
				tmp_from_dt = rset.getString(1)==null?"":rset.getString(1);
				tmp_to_dt = rset.getString(2)==null?"":rset.getString(2);
				FRM_DT.add(rset.getString(1)==null?"":rset.getString(1));
				TO_DT.add(rset.getString(2)==null?"":rset.getString(2));
				temp_dt = tmp_from_dt+"   -   "+tmp_to_dt;
				RE_GAS_CARGO_ARRIVAL_DATE.add(temp_dt);											  
				RE_GAS_SEQ_NO.add(rset.getString(6)==null?"0":rset.getString(6));				  	
				RE_GAS_BOOKMMBTU.add(rset.getString(7)==null?"0.00":nf.format(Double.parseDouble(rset.getString(7))));	
			  	tot_comit += Double.parseDouble(rset.getString(7)==null?"0":nf.format(Double.parseDouble(rset.getString(7))));
			  	re_gas_tot_commit=""+ nf.format(tot_comit) ;
			  	//MD20110917
			  	RE_GAS_BOOKMMSCM.add(rset.getString(7)==null?"0.00":nf.format(Double.parseDouble(rset.getString(7))/38900));	
			  	tot_comit_mmscm += Double.parseDouble(rset.getString(7)==null?"0":nf.format(Double.parseDouble(rset.getString(7))/38900));
			  	re_gas_tot_commit_mmscm=""+ nf.format(tot_comit_mmscm) ;
			  	
			  	REGAS_RATE.add(nf.format(Double.parseDouble(rset.getString(8)==null?"0":rset.getString(8))));
			  	Regas_cargo_ref_no.add(rset.getString(9));
			}			
			
			for(int i=0;i<RE_GAS_CUSTOMER_CD.size();i++)
			{
				queryString ="select CUSTOMER_ABBR,CUSTOMER_NAME,CUSTOMER_CD from FMS7_CUSTOMER_MST where CUSTOMER_CD="+RE_GAS_CUSTOMER_CD.elementAt(i)+" ";
				queryString ="select CUSTOMER_ABBR,CUSTOMER_NAME,CUSTOMER_CD from FMS7_CUSTOMER_MST where CUSTOMER_CD="+RE_GAS_CUSTOMER_CD.elementAt(i)+" "
						+ "AND EFF_DT=(SELECT MAX(EFF_DT) FROM FMS7_CUSTOMER_MST WHERE CUSTOMER_CD='"+RE_GAS_CUSTOMER_CD.elementAt(i)+"' AND EFF_DT<=TO_DATE('"+from_date+"','DD/MM/YYYY'))";
				rset = stmt.executeQuery(queryString);
				//System.out.println("RE_GAS_CUSTOMER_CD = "+queryString);
				if(rset.next())
				{
					RE_GAS_CUSTOMER_NM.add(rset.getString(2)==null?"":rset.getString(2));
					RE_GAS_CUSTOMER_ABBR.add(rset.getString(1)==null?"":rset.getString(1));
				}
				else
				{
					RE_GAS_CUSTOMER_NM.add("");
					RE_GAS_CUSTOMER_ABBR.add("");	
				}	
				
				queryString = "SELECT TO_CHAR(A.SIGNING_DT,'DD/MM/YYYY') FROM FMS7_RE_GAS_MST A " +
							  "WHERE A.RE_GAS_NO='"+RE_GAS_NO.elementAt(i)+"' AND " +
							  "A.REV_NO='"+RE_GAS_REV_NO.elementAt(i)+"' AND " +
							  "A.CUSTOMER_CD='"+RE_GAS_CUSTOMER_CD.elementAt(i)+"' ";			 
				//System.out.println(queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
				  	REGAS_SIGNING_DT.add(rset.getString(1)==null?"":rset.getString(1));
				}
				else
				{
					REGAS_SIGNING_DT.add("");
				}
	
//				queryString ="select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL where CUSTOMER_CD="+RE_GAS_CUSTOMER_CD.elementAt(i)+" " +
//							 "AND SN_NO="+RE_GAS_SEQ_NO.elementAt(i)+" AND FGSA_NO="+RE_GAS_NO.elementAt(i)+" " +
//							 "AND GAS_DT between to_date('"+FRM_DT.elementAt(i)+"','dd/mm/yyyy')" +
//							 "AND to_date('"+TO_DT.elementAt(i)+"','dd/mm/yyyy') AND CONTRACT_TYPE='R'";
				queryString ="select SUM(QTY_MMBTU),SUM(QTY_SCM) from FMS7_DAILY_ALLOCATION_DTL where CUSTOMER_CD="+RE_GAS_CUSTOMER_CD.elementAt(i)+" " +
				 "AND SN_NO="+RE_GAS_SEQ_NO.elementAt(i)+" AND FGSA_NO="+RE_GAS_NO.elementAt(i)+" " +
				 "AND GAS_DT between to_date('"+from_date+"','dd/mm/yyyy')" +
				 "AND to_date('"+to_date+"','dd/mm/yyyy') AND CONTRACT_TYPE='R'";

				//System.out.println("RE_GAS_QTY_MMBTU = "+queryString);
				rset = stmt.executeQuery(queryString);				
				if(rset.next())
				{
					RE_GAS_SUPPLIEDMBTU.add(rset.getString(1)==null?"0.00":nf.format(Double.parseDouble(rset.getString(1))));
					//MD20110917
					RE_GAS_SUPPLIEDMMSCM.add(rset.getString(2)==null?"0.00":nf.format(Double.parseDouble(rset.getString(2))/1000000));
					if(rset.getString(1)!=null && !(rset.getString(1).equals("")))
					{
						sup_tot_comit+=Double.parseDouble(rset.getString(1));
						sup_tot_comit_mmscm+=(Double.parseDouble(rset.getString(1))/38900);
					}
					else
					{
						sup_tot_comit+=0;
						sup_tot_comit_mmscm+=0;
					}
				}
				else
				{
					RE_GAS_SUPPLIEDMBTU.add("0.00");
					sup_tot_comit+=0;
					
					RE_GAS_SUPPLIEDMMSCM.add("0.00");
					sup_tot_comit_mmscm+=0;
				}				
				RE_GAS_BALANCEMMBTU.add(nf.format(Double.parseDouble(""+RE_GAS_BOOKMMBTU.elementAt(i))- Double.parseDouble(""+RE_GAS_SUPPLIEDMBTU.elementAt(i))));
				bal_tot_comit+=Double.parseDouble(""+RE_GAS_BOOKMMBTU.elementAt(i))- Double.parseDouble(""+RE_GAS_SUPPLIEDMBTU.elementAt(i));
				//MD20110917
				RE_GAS_BALANCEMMSCM.add(nf.format(Double.parseDouble(""+RE_GAS_BOOKMMSCM.elementAt(i))- Double.parseDouble(""+RE_GAS_SUPPLIEDMMSCM.elementAt(i))));
				bal_tot_comit_mmscm+=Double.parseDouble(""+RE_GAS_BOOKMMSCM.elementAt(i))- Double.parseDouble(""+RE_GAS_SUPPLIEDMMSCM.elementAt(i));
		   }
			re_gas_sup_tot_comitment=nf.format(sup_tot_comit);
			re_gas_bal_tot_comitment=nf.format(bal_tot_comit);
			//MD20110917
			re_gas_sup_tot_comitment_mmscm=nf.format(sup_tot_comit_mmscm);
			re_gas_bal_tot_comitment_mmscm=nf.format(bal_tot_comit_mmscm);
			
			
			RE_GAS_SUPPLIEDMBTU1=new Vector[Vgas_date.size()] ; 
			RE_GAS_SUPPLIEDMMSCM1=new Vector[Vgas_date.size()] ;; 
			
			for(int k=0;k<Vgas_date.size();k++)
			{
				RE_GAS_SUPPLIEDMBTU1[k] =new Vector(); 
				RE_GAS_SUPPLIEDMMSCM1[k]=new Vector(); 
				
			for(int i=0;i<RE_GAS_CUSTOMER_CD.size();i++)
			{

				queryString ="select SUM(QTY_MMBTU),SUM(QTY_SCM) from FMS7_DAILY_ALLOCATION_DTL where CUSTOMER_CD="+RE_GAS_CUSTOMER_CD.elementAt(i)+" " +
				 "AND SN_NO="+RE_GAS_SEQ_NO.elementAt(i)+" AND FGSA_NO="+RE_GAS_NO.elementAt(i)+" " +
				 "AND GAS_DT=to_date('"+Vgas_date.elementAt(k)+"','dd/mm/yyyy')" +
				 " AND CONTRACT_TYPE='R'";
					rset = stmt.executeQuery(queryString);
					////System.out.println(">>>>>>> "+queryString);
						if(rset.next())
						{
							RE_GAS_SUPPLIEDMBTU1[k].add(rset.getString(1)==null?"-":nf.format(Double.parseDouble(rset.getString(1))));
							RE_GAS_SUPPLIEDMMSCM1[k].add(rset.getString(2)==null?"-":nf.format(Double.parseDouble(rset.getString(2))/1000000));
							
						}
						else
						{
							RE_GAS_SUPPLIEDMBTU1[k].add("-");
							RE_GAS_SUPPLIEDMMSCM1[k].add("-");
							
						}
				}
			}
			
			
			///////*******ADDED FOR LTCORA AND CN******/////////////
//			--------Logic for LTCORA based Summary Contract based Agreement	
			tot_comit=0.0;
			bal_tot_comit=0.0;
			sup_tot_comit=0.0;
			//MD20110917
			tot_comit_mmscm=0.0;
			sup_tot_comit_mmscm=0.0;
			bal_tot_comit_mmscm=0.0;
			
			FRM_DT.clear();
			TO_DT.clear();
			
			
			
			
			/*queryString = "select TO_CHAR(A.CONTRACT_START_DT,'DD/MM/YYYY'),TO_CHAR(A.CONTRACT_END_DT,'DD/MM/YYYY'),A.RE_GAS_NO,A.RE_GAS_REV_NO,A.CUSTOMER_CD,A.CARGO_SEQ_NO,A.QTY_TO_BE_SUPPLY,A.RE_GAS_TARIF " +
	 					  "from FMS7_RE_GAS_CARGO_DTL A where (A.CONTRACT_START_DT between to_date('"+from_date+"','dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy')"+ 
	 					  "OR A.CONTRACT_END_DT between to_date('"+from_date+"','dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy')) AND " +
	 		              "A.RE_GAS_REV_NO=(SELECT MAX(B.RE_GAS_REV_NO) FROM FMS7_RE_GAS_CARGO_DTL B where A.RE_GAS_NO=B.RE_GAS_NO AND A.RE_GAS_REV_NO=B.RE_GAS_REV_NO AND " +
	 		              "A.CUSTOMER_CD=B.CUSTOMER_CD AND A.CARGO_SEQ_NO=B.CARGO_SEQ_NO) order by A.CONTRACT_START_DT";*/
			
			//Updated By Milan 20110921
			/*queryString = "select TO_CHAR(A.CONTRACT_START_DT,'DD/MM/YYYY'),TO_CHAR(A.CONTRACT_END_DT,'DD/MM/YYYY'),A.mapping_id,A.mapping_id,A.mapping_id,A.CARGO_SEQ_NO,A.QTY_TO_BE_SUPPLY,A.REGAS_TARIF ,A.cargo_ref_no " +
			  "from FMS8_LNG_REGAS_CARGO_DTL A where "+ 
			  "(A.CONTRACT_START_DT <= to_date('"+to_date+"','dd/mm/yyyy')"+"AND A.CONTRACT_END_DT >= to_date('"+from_date+"','dd/mm/yyyy')) AND " +
             "A.RE_GAS_REV_NO=(SELECT MAX(B.RE_GAS_REV_NO) FROM FMS8_LNG_REGAS_CARGO_DTL B where A.RE_GAS_NO=B.RE_GAS_NO AND A.RE_GAS_REV_NO=B.RE_GAS_REV_NO AND " +
             "A.CUSTOMER_CD=B.CUSTOMER_CD AND A.CARGO_SEQ_NO=B.CARGO_SEQ_NO) order by A.CONTRACT_START_DT";
			*/
			queryString="select TO_CHAR(A.CONTRACT_START_DT,'DD/MM/YYYY'),TO_CHAR(A.CONTRACT_END_DT,'DD/MM/YYYY')," +
					" A.mapping_id,A.mapping_id,C.mapping_id,A.CARGO_SEQ_NO,A.QTY_TO_BE_SUPPLY," +
					" A.REGAS_TARIF ,A.cargo_ref_no,C.rev_no from FMS8_LNG_REGAS_CARGO_DTL A, FMS8_LNG_REGAS_MST C " +
					" where (A.CONTRACT_START_DT <= to_date('"+to_date+"','dd/mm/yyyy') AND " +
					" A.CONTRACT_END_DT >= to_date('"+from_date+"','dd/mm/yyyy'))  AND A.MAPPING_ID=C.MAPPING_ID " +
					" AND C.CN_NO='0' AND C.CN_REV_NO='0'" +
					" AND C.REV_NO=(SELECT MAX(B.REV_NO) FROM FMS8_LNG_REGAS_MST B where " +
					" C.agreement_no=B.agreement_no AND C.customer_cd=B.customer_cd AND B.CN_NO='0' AND B.CN_REV_NO='0') AND a.mapping_id=C.mapping_id" +
					" order by A.CONTRACT_START_DT";
			
			
			
			//System.out.println("Fetching LTCORA details..."+queryString);
			rset = stmt.executeQuery(queryString);
			//System.out.println(queryString);
			while(rset.next())
			{		
				String map_id=rset.getString(3)==null?"":rset.getString(3);
				String tempmap_id[]=map_id.split("-");
				
				String temp_regas_no=tempmap_id[1];
				String temp_regas_rev_no=tempmap_id[2];
				String temp_cust_cd=tempmap_id[0];
				
				LTCORA_NO.add(temp_regas_no);
				LTCORA_REV_NO.add(temp_regas_rev_no); 
				LTCORA_CUSTOMER_CD.add(temp_cust_cd);									
				tmp_from_dt = rset.getString(1)==null?"":rset.getString(1);
				tmp_to_dt = rset.getString(2)==null?"":rset.getString(2);
				FRM_DT.add(rset.getString(1)==null?"":rset.getString(1));
				TO_DT.add(rset.getString(2)==null?"":rset.getString(2));
				temp_dt = tmp_from_dt+"   -   "+tmp_to_dt;
				LTCORA_CARGO_ARRIVAL_DATE.add(temp_dt);											  
				LTCORA_SEQ_NO.add(rset.getString(6)==null?"0":rset.getString(6));				  	
				LTCORA_BOOKMMBTU.add(rset.getString(7)==null?"0.00":nf.format(Double.parseDouble(rset.getString(7))));	
			  	tot_comit += Double.parseDouble(rset.getString(7)==null?"0":nf.format(Double.parseDouble(rset.getString(7))));
			  	LTCORA_tot_commit=""+ nf.format(tot_comit) ;
			  	//MD20110917
			  	LTCORA_BOOKMMSCM.add(rset.getString(7)==null?"0.00":nf.format(Double.parseDouble(rset.getString(7))/38900));	
			  	tot_comit_mmscm += Double.parseDouble(rset.getString(7)==null?"0":nf.format(Double.parseDouble(rset.getString(7))/38900));
			  	LTCORA_tot_commit_mmscm=""+ nf.format(tot_comit_mmscm) ;
			  	
			  	LTCORA_RATE.add(nf.format(Double.parseDouble(rset.getString(8)==null?"0":rset.getString(8))));
			  	LTCORA_cargo_ref_no.add(rset.getString(9));
			}			
			
			for(int i=0;i<LTCORA_CUSTOMER_CD.size();i++)
			{
				queryString ="select CUSTOMER_ABBR,CUSTOMER_NAME,CUSTOMER_CD from FMS7_CUSTOMER_MST where CUSTOMER_CD="+LTCORA_CUSTOMER_CD.elementAt(i)+" ";
				queryString ="select CUSTOMER_ABBR,CUSTOMER_NAME,CUSTOMER_CD from FMS7_CUSTOMER_MST where CUSTOMER_CD="+LTCORA_CUSTOMER_CD.elementAt(i)+" "
						+ "AND EFF_DT=(SELECT MAX(EFF_DT) FROM FMS7_CUSTOMER_MST WHERE CUSTOMER_CD='"+LTCORA_CUSTOMER_CD.elementAt(i)+"' AND EFF_DT<=TO_DATE('"+from_date+"','DD/MM/YYYY'))";
				rset = stmt.executeQuery(queryString);
				//System.out.println("LTCORA_CUSTOMER_CD = "+queryString);
				if(rset.next())
				{
					LTCORA_CUSTOMER_NM.add(rset.getString(2)==null?"":rset.getString(2));
					LTCORA_CUSTOMER_ABBR.add(rset.getString(1)==null?"":rset.getString(1));
				}
				else
				{
					LTCORA_CUSTOMER_NM.add("");
					LTCORA_CUSTOMER_ABBR.add("");	
				}	
				
				queryString = "SELECT TO_CHAR(A.SIGNING_DT,'DD/MM/YYYY') FROM FMS8_LNG_REGAS_MST A " +
							  "WHERE A.AGREEMENT_NO='"+LTCORA_NO.elementAt(i)+"' AND " +
							  "A.REV_NO='"+LTCORA_REV_NO.elementAt(i)+"' AND " +
							  "A.CUSTOMER_CD='"+LTCORA_CUSTOMER_CD.elementAt(i)+"' " +
							  "AND CN_NO='0' AND CN_REV_NO='0' ";			 
				//System.out.println(queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					LTCORA_SIGNING_DT.add(rset.getString(1)==null?"":rset.getString(1));
				}
				else
				{
					LTCORA_SIGNING_DT.add("");
				}
	
//				queryString ="select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL where CUSTOMER_CD="+RE_GAS_CUSTOMER_CD.elementAt(i)+" " +
//							 "AND SN_NO="+RE_GAS_SEQ_NO.elementAt(i)+" AND FGSA_NO="+RE_GAS_NO.elementAt(i)+" " +
//							 "AND GAS_DT between to_date('"+FRM_DT.elementAt(i)+"','dd/mm/yyyy')" +
//							 "AND to_date('"+TO_DT.elementAt(i)+"','dd/mm/yyyy') AND CONTRACT_TYPE='R'";
				queryString ="select SUM(QTY_MMBTU),SUM(QTY_SCM) from FMS7_DAILY_ALLOCATION_DTL where CUSTOMER_CD="+LTCORA_CUSTOMER_CD.elementAt(i)+" " +
				 "AND SN_NO="+LTCORA_SEQ_NO.elementAt(i)+" AND FGSA_NO="+LTCORA_NO.elementAt(i)+" " +
				 "AND GAS_DT between to_date('"+from_date+"','dd/mm/yyyy')" +
				 "AND to_date('"+to_date+"','dd/mm/yyyy') AND CONTRACT_TYPE='T'";

				//System.out.println("LTCORA_QTY_MMBTU = "+queryString);
				rset = stmt.executeQuery(queryString);				
				if(rset.next())
				{
					LTCORA_SUPPLIEDMBTU.add(rset.getString(1)==null?"0.00":nf.format(Double.parseDouble(rset.getString(1))));
					//MD20110917
					LTCORA_SUPPLIEDMMSCM.add(rset.getString(2)==null?"0.00":nf.format(Double.parseDouble(rset.getString(2))/1000000));
					if(rset.getString(1)!=null && !(rset.getString(1).equals("")))
					{
						sup_tot_comit+=Double.parseDouble(rset.getString(1));
						sup_tot_comit_mmscm+=(Double.parseDouble(rset.getString(1))/38900);
					}
					else
					{
						sup_tot_comit+=0;
						sup_tot_comit_mmscm+=0;
					}
				}
				else
				{
					LTCORA_SUPPLIEDMBTU.add("0.00");
					sup_tot_comit+=0;
					
					LTCORA_SUPPLIEDMMSCM.add("0.00");
					sup_tot_comit_mmscm+=0;
				}				
				LTCORA_BALANCEMMBTU.add(nf.format(Double.parseDouble(""+LTCORA_BOOKMMBTU.elementAt(i))- Double.parseDouble(""+LTCORA_SUPPLIEDMBTU.elementAt(i))));
				bal_tot_comit+=Double.parseDouble(""+LTCORA_BOOKMMBTU.elementAt(i))- Double.parseDouble(""+LTCORA_SUPPLIEDMBTU.elementAt(i));
				//MD20110917
				LTCORA_BALANCEMMSCM.add(nf.format(Double.parseDouble(""+LTCORA_BOOKMMSCM.elementAt(i))- Double.parseDouble(""+LTCORA_SUPPLIEDMMSCM.elementAt(i))));
				bal_tot_comit_mmscm+=Double.parseDouble(""+LTCORA_BOOKMMSCM.elementAt(i))- Double.parseDouble(""+LTCORA_SUPPLIEDMMSCM.elementAt(i));
		   }
			LTCORA_sup_tot_comitment=nf.format(sup_tot_comit);
			LTCORA_bal_tot_comitment=nf.format(bal_tot_comit);
			//MD20110917
			LTCORA_sup_tot_comitment_mmscm=nf.format(sup_tot_comit_mmscm);
			LTCORA_bal_tot_comitment_mmscm=nf.format(bal_tot_comit_mmscm);
			
			
			LTCORA_SUPPLIEDMBTU1=new Vector[Vgas_date.size()] ; 
			LTCORA_SUPPLIEDMMSCM1=new Vector[Vgas_date.size()] ;; 
			
			for(int k=0;k<Vgas_date.size();k++)
			{
				LTCORA_SUPPLIEDMBTU1[k] =new Vector(); 
				LTCORA_SUPPLIEDMMSCM1[k]=new Vector(); 
				
			for(int i=0;i<LTCORA_CUSTOMER_CD.size();i++)
			{

				queryString ="select SUM(QTY_MMBTU),SUM(QTY_SCM) from FMS7_DAILY_ALLOCATION_DTL where CUSTOMER_CD="+LTCORA_CUSTOMER_CD.elementAt(i)+" " +
				 "AND SN_NO="+LTCORA_SEQ_NO.elementAt(i)+" AND FGSA_NO="+LTCORA_NO.elementAt(i)+" " +
				 "AND GAS_DT=to_date('"+Vgas_date.elementAt(k)+"','dd/mm/yyyy')" +
				 " AND CONTRACT_TYPE='T'";
					rset = stmt.executeQuery(queryString);
					////System.out.println(">>>>>>> "+queryString);
						if(rset.next())
						{
							LTCORA_SUPPLIEDMBTU1[k].add(rset.getString(1)==null?"-":nf.format(Double.parseDouble(rset.getString(1))));
							LTCORA_SUPPLIEDMMSCM1[k].add(rset.getString(2)==null?"-":nf.format(Double.parseDouble(rset.getString(2))/1000000));
							
						}
						else
						{
							LTCORA_SUPPLIEDMBTU1[k].add("-");
							LTCORA_SUPPLIEDMMSCM1[k].add("-");
							
						}
				}
			}
			////*********LTCORA END***********///////
//			--------Logic for CN based Summary Contract based Agreement	
			tot_comit=0.0;
			bal_tot_comit=0.0;
			sup_tot_comit=0.0;
			//MD20110917
			tot_comit_mmscm=0.0;
			sup_tot_comit_mmscm=0.0;
			bal_tot_comit_mmscm=0.0;
			
			FRM_DT.clear();
			TO_DT.clear();
			
			String mapping_id="";
			
			
			/*queryString = "select TO_CHAR(A.CONTRACT_START_DT,'DD/MM/YYYY'),TO_CHAR(A.CONTRACT_END_DT,'DD/MM/YYYY'),A.RE_GAS_NO,A.RE_GAS_REV_NO,A.CUSTOMER_CD,A.CARGO_SEQ_NO,A.QTY_TO_BE_SUPPLY,A.RE_GAS_TARIF " +
	 					  "from FMS7_RE_GAS_CARGO_DTL A where (A.CONTRACT_START_DT between to_date('"+from_date+"','dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy')"+ 
	 					  "OR A.CONTRACT_END_DT between to_date('"+from_date+"','dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy')) AND " +
	 		              "A.RE_GAS_REV_NO=(SELECT MAX(B.RE_GAS_REV_NO) FROM FMS7_RE_GAS_CARGO_DTL B where A.RE_GAS_NO=B.RE_GAS_NO AND A.RE_GAS_REV_NO=B.RE_GAS_REV_NO AND " +
	 		              "A.CUSTOMER_CD=B.CUSTOMER_CD AND A.CARGO_SEQ_NO=B.CARGO_SEQ_NO) order by A.CONTRACT_START_DT";*/
			
			//Updated By Milan 20110921
			/*queryString = "select TO_CHAR(A.CONTRACT_START_DT,'DD/MM/YYYY'),TO_CHAR(A.CONTRACT_END_DT,'DD/MM/YYYY'),A.mapping_id,A.mapping_id,A.mapping_id,A.CARGO_SEQ_NO,A.QTY_TO_BE_SUPPLY,A.REGAS_TARIF ,A.cargo_ref_no " +
			  "from FMS8_LNG_REGAS_CARGO_DTL A where "+ 
			  "(A.CONTRACT_START_DT <= to_date('"+to_date+"','dd/mm/yyyy')"+"AND A.CONTRACT_END_DT >= to_date('"+from_date+"','dd/mm/yyyy')) AND " +
             "A.RE_GAS_REV_NO=(SELECT MAX(B.RE_GAS_REV_NO) FROM FMS8_LNG_REGAS_CARGO_DTL B where A.RE_GAS_NO=B.RE_GAS_NO AND A.RE_GAS_REV_NO=B.RE_GAS_REV_NO AND " +
             "A.CUSTOMER_CD=B.CUSTOMER_CD AND A.CARGO_SEQ_NO=B.CARGO_SEQ_NO) order by A.CONTRACT_START_DT";
			*/
			
			Vector temp_mapping_id=new Vector();
			
			queryString="select TO_CHAR(A.CONTRACT_START_DT,'DD/MM/YYYY'),TO_CHAR(A.CONTRACT_END_DT,'DD/MM/YYYY'), " +
					" A.mapping_id,A.mapping_id,C.mapping_id,A.CARGO_SEQ_NO,A.QTY_TO_BE_SUPPLY, A.REGAS_TARIF ," +
					" A.cargo_ref_no ,C.CN_NO, C.cn_rev_no from FMS8_LNG_REGAS_CARGO_DTL A, FMS8_LNG_REGAS_MST C where" +
					" (A.CONTRACT_START_DT <= to_date('"+to_date+"','dd/mm/yyyy') AND " +
					" A.CONTRACT_END_DT >= to_date('"+from_date+"','dd/mm/yyyy'))  AND A.MAPPING_ID=C.MAPPING_ID" +
					" AND C.CN_NO!=0 AND C.cn_rev_no=(SELECT MAX(B.CN_REV_NO) FROM FMS8_LNG_REGAS_MST B " +
					" where C.agreement_no=B.agreement_no AND C.customer_cd=B.customer_cd AND B.CN_NO!=0 " +
					" AND B.CN_NO=C.CN_NO AND c.rev_no=B.rev_no AND B.REV_NO=(SELECT MAX(D.REV_NO) " +
					" FROM FMS8_LNG_REGAS_MST D WHERE  D.agreement_no=B.agreement_no AND " +
					" B.customer_cd=D.customer_cd AND D.CN_NO=0 AND D.agreement_no=C.agreement_no " +
					" AND D.customer_cd=C.customer_cd )) AND a.mapping_id=C.mapping_id" +
					" order by A.CONTRACT_START_DT ";
			
			
			
			//System.out.println("Fetching CN details..."+queryString);
			rset = stmt.executeQuery(queryString);
			//System.out.println(queryString);
			while(rset.next())
			{		
				String map_id=rset.getString(3)==null?"":rset.getString(3);
				mapping_id=map_id;
				String tempmap_id[]=map_id.split("-");
				
				String temp_regas_no=tempmap_id[3];
				String temp_regas_rev_no=tempmap_id[4];
				String temp_cust_cd=tempmap_id[0];
				
				temp_mapping_id.add(mapping_id);
				CN_NO.add(temp_regas_no);
				CN_REV_NO.add(temp_regas_rev_no); 
				CN_CUSTOMER_CD.add(temp_cust_cd);									
				tmp_from_dt = rset.getString(1)==null?"":rset.getString(1);
				tmp_to_dt = rset.getString(2)==null?"":rset.getString(2);
				FRM_DT.add(rset.getString(1)==null?"":rset.getString(1));
				TO_DT.add(rset.getString(2)==null?"":rset.getString(2));
				temp_dt = tmp_from_dt+"   -   "+tmp_to_dt;
				CN_CARGO_ARRIVAL_DATE.add(temp_dt);											  
				CN_SEQ_NO.add(rset.getString(6)==null?"0":rset.getString(6));				  	
				CN_BOOKMMBTU.add(rset.getString(7)==null?"0.00":nf.format(Double.parseDouble(rset.getString(7))));	
			  	tot_comit += Double.parseDouble(rset.getString(7)==null?"0":nf.format(Double.parseDouble(rset.getString(7))));
			  	CN_tot_commit=""+ nf.format(tot_comit) ;
			  	//MD20110917
			  	CN_BOOKMMSCM.add(rset.getString(7)==null?"0.00":nf.format(Double.parseDouble(rset.getString(7))/38900));	
			  	tot_comit_mmscm += Double.parseDouble(rset.getString(7)==null?"0":nf.format(Double.parseDouble(rset.getString(7))/38900));
			  	CN_tot_commit_mmscm=""+ nf.format(tot_comit_mmscm) ;
			  	
			  	CN_RATE.add(nf.format(Double.parseDouble(rset.getString(8)==null?"0":rset.getString(8))));
			  	CN_cargo_ref_no.add(rset.getString(9));
			}			
			
			for(int i=0;i<CN_CUSTOMER_CD.size();i++)
			{
				queryString ="select CUSTOMER_ABBR,CUSTOMER_NAME,CUSTOMER_CD from FMS7_CUSTOMER_MST where CUSTOMER_CD="+CN_CUSTOMER_CD.elementAt(i)+" ";
				queryString ="select CUSTOMER_ABBR,CUSTOMER_NAME,CUSTOMER_CD from FMS7_CUSTOMER_MST where CUSTOMER_CD="+CN_CUSTOMER_CD.elementAt(i)+" "
						+ "AND EFF_DT=(SELECT MAX(EFF_DT) FROM FMS7_CUSTOMER_MST WHERE CUSTOMER_CD='"+CN_CUSTOMER_CD.elementAt(i)+"' AND EFF_DT<=TO_DATE('"+from_date+"','DD/MM/YYYY'))";
				rset = stmt.executeQuery(queryString);
				//System.out.println("CN_CUSTOMER_CD = "+queryString);
				if(rset.next())
				{
					CN_CUSTOMER_NM.add(rset.getString(2)==null?"":rset.getString(2));
					CN_CUSTOMER_ABBR.add(rset.getString(1)==null?"":rset.getString(1));
				}
				else
				{
					CN_CUSTOMER_NM.add("");
					CN_CUSTOMER_ABBR.add("");	
				}	
				
				queryString = "SELECT TO_CHAR(A.SIGNING_DT,'DD/MM/YYYY') FROM FMS8_LNG_REGAS_MST A " +
							  "WHERE MAPPING_ID='"+temp_mapping_id.elementAt(i)+"'  ";			 
				//System.out.println(queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					CN_SIGNING_DT.add(rset.getString(1)==null?"":rset.getString(1));
				}
				else
				{
					CN_SIGNING_DT.add("");
				}
	
//				queryString ="select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL where CUSTOMER_CD="+RE_GAS_CUSTOMER_CD.elementAt(i)+" " +
//							 "AND SN_NO="+RE_GAS_SEQ_NO.elementAt(i)+" AND FGSA_NO="+RE_GAS_NO.elementAt(i)+" " +
//							 "AND GAS_DT between to_date('"+FRM_DT.elementAt(i)+"','dd/mm/yyyy')" +
//							 "AND to_date('"+TO_DT.elementAt(i)+"','dd/mm/yyyy') AND CONTRACT_TYPE='R'";
				queryString ="select SUM(QTY_MMBTU),SUM(QTY_SCM) from FMS7_DAILY_ALLOCATION_DTL where CUSTOMER_CD="+CN_CUSTOMER_CD.elementAt(i)+" " +
				 "AND SN_NO="+CN_SEQ_NO.elementAt(i)+" AND FGSA_NO="+CN_NO.elementAt(i)+" " +
				 "AND GAS_DT between to_date('"+from_date+"','dd/mm/yyyy')" +
				 "AND to_date('"+to_date+"','dd/mm/yyyy') AND CONTRACT_TYPE='C' " +
				 "AND MAPPING_ID='"+temp_mapping_id.elementAt(i)+"'";

				//System.out.println("CN_QTY_MMBTU = "+queryString);
				rset = stmt.executeQuery(queryString);				
				if(rset.next())
				{
					CN_SUPPLIEDMBTU.add(rset.getString(1)==null?"0.00":nf.format(Double.parseDouble(rset.getString(1))));
					//MD20110917
					CN_SUPPLIEDMMSCM.add(rset.getString(2)==null?"0.00":nf.format(Double.parseDouble(rset.getString(2))/1000000));
					if(rset.getString(1)!=null && !(rset.getString(1).equals("")))
					{
						sup_tot_comit+=Double.parseDouble(rset.getString(1));
						sup_tot_comit_mmscm+=(Double.parseDouble(rset.getString(1))/38900);
					}
					else
					{
						sup_tot_comit+=0;
						sup_tot_comit_mmscm+=0;
					}
				}
				else
				{
					CN_SUPPLIEDMBTU.add("0.00");
					sup_tot_comit+=0;
					
					CN_SUPPLIEDMMSCM.add("0.00");
					sup_tot_comit_mmscm+=0;
				}				
				CN_BALANCEMMBTU.add(nf.format(Double.parseDouble(""+CN_BOOKMMBTU.elementAt(i))- Double.parseDouble(""+CN_SUPPLIEDMBTU.elementAt(i))));
				bal_tot_comit+=Double.parseDouble(""+CN_BOOKMMBTU.elementAt(i))- Double.parseDouble(""+CN_SUPPLIEDMBTU.elementAt(i));
				//MD20110917
				CN_BALANCEMMSCM.add(nf.format(Double.parseDouble(""+CN_BOOKMMSCM.elementAt(i))- Double.parseDouble(""+CN_SUPPLIEDMMSCM.elementAt(i))));
				bal_tot_comit_mmscm+=Double.parseDouble(""+CN_BOOKMMSCM.elementAt(i))- Double.parseDouble(""+CN_SUPPLIEDMMSCM.elementAt(i));
		   }
			CN_sup_tot_comitment=nf.format(sup_tot_comit);
			CN_bal_tot_comitment=nf.format(bal_tot_comit);
			//MD20110917
			CN_sup_tot_comitment_mmscm=nf.format(sup_tot_comit_mmscm);
			CN_bal_tot_comitment_mmscm=nf.format(bal_tot_comit_mmscm);
			
			
			CN_SUPPLIEDMBTU1=new Vector[Vgas_date.size()] ; 
			CN_SUPPLIEDMMSCM1=new Vector[Vgas_date.size()] ;; 
			
			for(int k=0;k<Vgas_date.size();k++)
			{
				CN_SUPPLIEDMBTU1[k] =new Vector(); 
				CN_SUPPLIEDMMSCM1[k]=new Vector(); 
				
			for(int i=0;i<CN_CUSTOMER_CD.size();i++)
			{

				queryString ="select SUM(QTY_MMBTU),SUM(QTY_SCM) from FMS7_DAILY_ALLOCATION_DTL where CUSTOMER_CD="+CN_CUSTOMER_CD.elementAt(i)+" " +
				 "AND SN_NO="+CN_SEQ_NO.elementAt(i)+" AND FGSA_NO="+CN_NO.elementAt(i)+" " +
				 "AND GAS_DT=to_date('"+Vgas_date.elementAt(k)+"','dd/mm/yyyy')" +
				 " AND CONTRACT_TYPE='C' AND MAPPING_ID='"+temp_mapping_id.elementAt(i)+"'";
					rset = stmt.executeQuery(queryString);
					////System.out.println(">>>>>>> "+queryString);
						if(rset.next())
						{
							CN_SUPPLIEDMBTU1[k].add(rset.getString(1)==null?"-":nf.format(Double.parseDouble(rset.getString(1))));
							CN_SUPPLIEDMMSCM1[k].add(rset.getString(2)==null?"-":nf.format(Double.parseDouble(rset.getString(2))/1000000));
							
						}
						else
						{
							CN_SUPPLIEDMBTU1[k].add("-");
							CN_SUPPLIEDMMSCM1[k].add("-");
							
						}
				}
			}
			////*********CN END***********///////
			
			//RE_GAS
			

			
			
		}		
		catch(Exception e)
		{
			  //System.out.println("Exception in Databean_Contract_MasterReport--->fetchEligibleCargoList_new()-->"+e);
			  e.printStackTrace();
		}
	}
	

	public void fetchEligibleCargoList_new_OLD()
	{
		try
		{			
			String tmp_from_dt = " ";
			String temp_dt="";
			String tmp_to_dt = " ";			
			double tot_comit=0.0;
			double sup_tot_comit=0.0;
			double bal_tot_comit=0.0;
			//Introduce By Milan 20110916 FOR MMSCM
			double tot_comit_mmscm=0.0;
			double sup_tot_comit_mmscm=0.0;
			double bal_tot_comit_mmscm=0.0;
			Vector FRM_DT=new Vector();
			Vector TO_DT=new Vector();						
			Vector RE_GAS_REV_NO=new Vector();
			double count=0;
			String query="select to_date('"+to_date+"','dd/mm/yyyy')- to_date('"+from_date+"','dd/mm/yyyy')+1 from dual";
			rset=stmt.executeQuery(query);
			if(rset.next())
			{
				count=rset.getDouble(1);
			}
			
			for(int i=0;i<count;i++)
			{
				String query1="select to_char(to_date('"+from_date+"','dd/mm/yyyy')+ "+i+",'dd/mm/yyyy') from dual";
				rset=stmt.executeQuery(query1);
				if(rset.next())
				{
					Vgas_date.add(rset.getString(1));
				}
				
			}
			
			
			
			/*queryString ="select TO_CHAR(A.START_DT,'DD/MM/YYYY'),TO_CHAR(A.END_DT,'DD/MM/YYYY'),A.SN_NO,A.SN_REV_NO,A.CUSTOMER_CD,A.FGSA_NO,A.FGSA_REV_NO,A.TCQ, " +
			 		"A.SN_REF_NO,to_char(A.SIGNING_DT,'dd/mm/yyyy'),A.RATE from FMS7_SN_MST A where (A.START_DT between to_date('"+from_date+"','dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy')"+ 
			 		"OR A.END_DT between to_date('"+from_date+"','dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy')) AND " +
			 		"A.SN_REV_NO=(SELECT MAX(B.SN_REV_NO) FROM FMS7_SN_MST B where A.SN_NO=B.SN_NO AND A.FGSA_NO=B.FGSA_NO AND " +
			 		"A.CUSTOMER_CD=B.CUSTOMER_CD AND A.FGSA_REV_NO=B.FGSA_REV_NO) order by A.START_DT";*/
			
			//Updated By Milan 20110921
			queryString ="select TO_CHAR(A.START_DT,'DD/MM/YYYY'),TO_CHAR(A.END_DT,'DD/MM/YYYY'),A.SN_NO,A.SN_REV_NO,A.CUSTOMER_CD,A.FGSA_NO,A.FGSA_REV_NO,A.TCQ, " +
	 		"A.SN_REF_NO,to_char(A.SIGNING_DT,'dd/mm/yyyy'),A.RATE from FMS7_SN_MST A where "+ 
	 		"(A.START_DT <= to_date('"+to_date+"','dd/mm/yyyy')"+"AND A.END_DT >= to_date('"+from_date+"','dd/mm/yyyy')) AND " +
	 		"A.SN_REV_NO=(SELECT MAX(B.SN_REV_NO) FROM FMS7_SN_MST B where A.SN_NO=B.SN_NO AND A.FGSA_NO=B.FGSA_NO AND " +
	 		"A.CUSTOMER_CD=B.CUSTOMER_CD AND A.FGSA_REV_NO=B.FGSA_REV_NO) order by A.START_DT";
			
			//(START_DT <= to_date('"+to_date+"','dd/mm/yyyy')"+"AND END_DT >= to_date('"+from_date+"','dd/mm/yyyy'))
			//System.out.println(queryString);
			rset = stmt.executeQuery(queryString);
			////System.out.println("FOr SN : "+to_date+" "+from_date);
			while(rset.next())
			{				
				////System.out.println("FOr SN : "+rset.getString(1)+" "+rset.getString(2));
				SN_NO.add(rset.getString(3)==null?"0":rset.getString(3));
				CUSTOMER_CD.add(rset.getString(5)==null?"0":rset.getString(5));									
				tmp_from_dt = rset.getString(1)==null?"":rset.getString(1);
				tmp_to_dt = rset.getString(2)==null?"":rset.getString(2);
				FRM_DT.add(rset.getString(1)==null?"":rset.getString(1));
				TO_DT.add(rset.getString(2)==null?"":rset.getString(2));
				temp_dt = tmp_from_dt+"   -   "+tmp_to_dt;
				CARGO_ARRIVAL_DATE.add(temp_dt);				
				SN_REV_NO.add(rset.getString(4)==null?"0":rset.getString(4));			  	
			  	FGSA_NO.add(rset.getString(6)==null?"0":rset.getString(6));	
			  	FGSA_REV_NO.add(rset.getString(7)==null?"0":rset.getString(7));	
			  	BOOKMMBTU.add(rset.getString(8)==null?"0.00":nf.format(Double.parseDouble(rset.getString(8))));
			  	BOOKMMSCM.add(rset.getString(8)==null?"0.00":nf.format(Double.parseDouble(rset.getString(8))/38900));//MD20110916
			  	tot_comit += Double.parseDouble(rset.getString(8)==null?"0":nf.format(Double.parseDouble(rset.getString(8))));
			  	
			  	tot_comit_mmscm=(tot_comit/38900);//MD20110916
			  	tot_commitment_mmscm=nf.format(tot_comit_mmscm);
			  	////System.out.println("BOOKEDMMSCM : "+BOOKMMSCM+" TOTAL : "+tot_comit_mmscm);
			  	
			  	tot_commitment=nf.format(tot_comit);
			  	SN_REF_NO.add(rset.getString(9)==null?"0":rset.getString(9));	
			  	SIGNING_DT.add(rset.getString(10)==null?"0":rset.getString(10));
			  	RATE.add(nf.format(Double.parseDouble(rset.getString(11)==null?"0":rset.getString(11))));
			}	
			
			//System.out.println("SN_REF_NO = "+SN_REF_NO);
			for(int i=0;i<CUSTOMER_CD.size();i++)
			{
				queryString ="select CUSTOMER_ABBR,CUSTOMER_NAME,CUSTOMER_CD from FMS7_CUSTOMER_MST where CUSTOMER_CD="+CUSTOMER_CD.elementAt(i)+" ";
				rset = stmt.executeQuery(queryString);
				////System.out.println(queryString);
				if(rset.next())
				{
					CUSTOMER_NM.add(rset.getString(2)==null?"":rset.getString(2));
					CUSTOMER_ABBR.add(rset.getString(1)==null?"":rset.getString(1));	
				}
				else
				{
					CUSTOMER_NM.add("");
					CUSTOMER_ABBR.add("");
				}												
//				queryString ="select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL where CUSTOMER_CD="+CUSTOMER_CD.elementAt(i)+" " +
//							 "AND SN_NO="+SN_NO.elementAt(i)+" AND FGSA_NO="+FGSA_NO.elementAt(i)+" " +
//							 "AND FGSA_REV_NO="+FGSA_REV_NO.elementAt(i)+" AND GAS_DT between to_date('"+FRM_DT.elementAt(i)+"','dd/mm/yyyy')" +
//							 "AND to_date('"+TO_DT.elementAt(i)+"','dd/mm/yyyy') AND CONTRACT_TYPE='S'";
				queryString ="select SUM(QTY_MMBTU),SUM(QTY_SCM) from FMS7_DAILY_ALLOCATION_DTL where CUSTOMER_CD="+CUSTOMER_CD.elementAt(i)+" " +
				 "AND SN_NO="+SN_NO.elementAt(i)+" AND FGSA_NO="+FGSA_NO.elementAt(i)+" " +
				 "AND FGSA_REV_NO="+FGSA_REV_NO.elementAt(i)+" AND GAS_DT between to_date('"+from_date+"','dd/mm/yyyy')" +
				 "AND to_date('"+to_date+"','dd/mm/yyyy') AND CONTRACT_TYPE='S'";

				rset = stmt.executeQuery(queryString);
				//System.out.println(">>>>>>> "+queryString);
				if(rset.next())
				{
					SUPPLIEDMBTU.add(rset.getString(1)==null?"0.00":nf.format(Double.parseDouble(rset.getString(1))));
					//MD20110916
					SUPPLIEDMMSCM.add(rset.getString(2)==null?"0.00":nf.format(Double.parseDouble(rset.getString(2))/1000000));
					if(rset.getString(1)!=null && !(rset.getString(1).equals("")))
					{
						sup_tot_comit+=Double.parseDouble(rset.getString(1));
						sup_tot_comit_mmscm+=(Double.parseDouble(rset.getString(1))/38900);//MD20110916
					}
					else
					{
						sup_tot_comit+=0;
						sup_tot_comit_mmscm+=0;//MD20110916
					}
				}
				else
				{
					SUPPLIEDMBTU.add("0.00");
					SUPPLIEDMMSCM.add("0.00");//MD20110916
					sup_tot_comit+=0;
					sup_tot_comit_mmscm+=0;//MD20110916
				}
				
				BALANCEMMBTU.add(nf.format(Double.parseDouble(""+BOOKMMBTU.elementAt(i))- Double.parseDouble(""+SUPPLIEDMBTU.elementAt(i))));
				//MD20110916
				BALANCEMMSCM.add(nf.format(Double.parseDouble(""+BOOKMMSCM.elementAt(i))- Double.parseDouble(""+SUPPLIEDMMSCM.elementAt(i))));
				bal_tot_comit+=Double.parseDouble(""+BOOKMMBTU.elementAt(i))- Double.parseDouble(""+SUPPLIEDMBTU.elementAt(i));
				bal_tot_comit_mmscm+=Double.parseDouble(""+BOOKMMSCM.elementAt(i))- Double.parseDouble(""+SUPPLIEDMMSCM.elementAt(i));
				//System.out.println("SUPLIEDMMSCM : "+SUPPLIEDMMSCM+" TOTAL : "+sup_tot_comit_mmscm);
				//System.out.println("BALANCEMMSCM : "+BALANCEMMSCM+" TOTAL : "+bal_tot_comit_mmscm);
		   }
			SUPPLIEDMBTU1=new Vector[Vgas_date.size()] ; 
			SUPPLIEDMMSCM1=new Vector[Vgas_date.size()] ;; 
			
			for(int k=0;k<Vgas_date.size();k++)
			{
			SUPPLIEDMBTU1[k] =new Vector(); 
			SUPPLIEDMMSCM1[k]=new Vector(); 
				
				for(int i=0;i<CUSTOMER_CD.size();i++)
				{

					queryString ="select SUM(QTY_MMBTU),SUM(QTY_SCM) from FMS7_DAILY_ALLOCATION_DTL where CUSTOMER_CD="+CUSTOMER_CD.elementAt(i)+" " +
					 "AND SN_NO="+SN_NO.elementAt(i)+" AND FGSA_NO="+FGSA_NO.elementAt(i)+" " +
					 "AND FGSA_REV_NO="+FGSA_REV_NO.elementAt(i)+" AND GAS_DT=to_date('"+Vgas_date.elementAt(k)+"','dd/mm/yyyy') AND CONTRACT_TYPE='S'";
					rset = stmt.executeQuery(queryString);
					////System.out.println(">>>>>>> "+queryString);
						if(rset.next())
						{
							SUPPLIEDMBTU1[k].add(rset.getString(1)==null?"-":nf.format(Double.parseDouble(rset.getString(1))));
							SUPPLIEDMMSCM1[k].add(rset.getString(2)==null?"-":nf.format(Double.parseDouble(rset.getString(2))/1000000));
							
						}
						else
						{
							SUPPLIEDMBTU1[k].add("-");
							SUPPLIEDMMSCM1[k].add("-");
							
						}
				}
			}

			
		   sup_tot_comitment=nf.format(sup_tot_comit);
		   bal_tot_comitment=nf.format(bal_tot_comit);
		   
		   //Introduce by Milan 20010916
		   sup_tot_comitment_mmscm=nf.format(sup_tot_comit_mmscm);
		   bal_tot_comitment_mmscm=nf.format(bal_tot_comit_mmscm);
			
		    //--------Logic for Tender and LOA based Summary Contract based Agreement	
			tot_comit=0.0;
			bal_tot_comit=0.0;
			sup_tot_comit=0.0;
			
			tot_comit_mmscm=0.0;
			sup_tot_comit_mmscm=0.0;
			bal_tot_comit_mmscm=0.0;
			FRM_DT.clear();
			TO_DT.clear();
			/*queryString =   "select TO_CHAR(A.START_DT,'DD/MM/YYYY'),TO_CHAR(A.END_DT,'DD/MM/YYYY'),A.LOA_NO,A.LOA_REV_NO,A.CUSTOMER_CD,A.TENDER_NO,A.TCQ, " +
	 						"A.LOA_REF_NO,to_char(A.SIGNING_DT,'dd/mm/yyyy'),A.RATE from FMS7_LOA_MST A where (A.START_DT between to_date('"+from_date+"','dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy')"+ 
	 						"OR A.END_DT between to_date('"+from_date+"','dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy')) AND " +
	 						"A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM FMS7_LOA_MST B where A.LOA_NO=B.LOA_NO AND A.TENDER_NO=B.TENDER_NO AND " +
	 						"A.CUSTOMER_CD=B.CUSTOMER_CD) order by A.START_DT";*/
			
			//Upadated By Milan 20110921
			queryString =   "select TO_CHAR(A.START_DT,'DD/MM/YYYY'),TO_CHAR(A.END_DT,'DD/MM/YYYY'),A.LOA_NO,A.LOA_REV_NO,A.CUSTOMER_CD,A.TENDER_NO,A.TCQ, " +
				"A.LOA_REF_NO,to_char(A.SIGNING_DT,'dd/mm/yyyy'),A.RATE from FMS7_LOA_MST A where "+ 
				"(A.START_DT <= to_date('"+to_date+"','dd/mm/yyyy')"+"AND A.END_DT >= to_date('"+from_date+"','dd/mm/yyyy')) AND " +
				"A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM FMS7_LOA_MST B where A.LOA_NO=B.LOA_NO AND A.TENDER_NO=B.TENDER_NO AND " +
				"A.CUSTOMER_CD=B.CUSTOMER_CD) order by A.START_DT";
			rset = stmt.executeQuery(queryString);
			//System.out.println(queryString);
			while(rset.next())
			{				
				LOA_NO.add(rset.getString(3)==null?"0":rset.getString(3));
				LOA_CUSTOMER_CD.add(rset.getString(5)==null?"0":rset.getString(5));									
				tmp_from_dt = rset.getString(1)==null?"":rset.getString(1);
				tmp_to_dt = rset.getString(2)==null?"":rset.getString(2);
				FRM_DT.add(rset.getString(1)==null?"":rset.getString(1));
				TO_DT.add(rset.getString(2)==null?"":rset.getString(2));
				temp_dt = tmp_from_dt+"   -   "+tmp_to_dt;
				LOA_CARGO_ARRIVAL_DATE.add(temp_dt);				
				LOA_REV_NO.add(rset.getString(4)==null?"0":rset.getString(4));			  	
			  	TENDER_NO.add(rset.getString(6)==null?"0":rset.getString(6));	
			  	LOA_BOOKMMBTU.add(rset.getString(7)==null?"0.00":nf.format(Double.parseDouble(rset.getString(7))));	
			  	tot_comit += Double.parseDouble(rset.getString(7)==null?"0":nf.format(Double.parseDouble(rset.getString(7))));
			  	loa_tot_commitment=nf.format(tot_comit) ;
			  	
			  	//INTRODUCE BY MILAN 20110917 FOR SCMMBTU
			  	LOA_BOOKMMSCM.add(rset.getString(7)==null?"0.00":nf.format(Double.parseDouble(rset.getString(7))/38900));	
			  	tot_comit_mmscm += Double.parseDouble(rset.getString(7)==null?"0":nf.format(Double.parseDouble(rset.getString(7))/38900));
			  	loa_tot_commitment_mmscm=nf.format(tot_comit_mmscm) ;
			  	
			  	LOA_REF_NO.add(rset.getString(8)==null?"0":rset.getString(8));
			  	LOA_SIGNING_DT.add(rset.getString(9)==null?"":rset.getString(9));
			  	LOA_RATE.add(nf.format(Double.parseDouble(rset.getString(10)==null?"0":rset.getString(10))));			  	
			}
			
			for(int i=0;i<LOA_CUSTOMER_CD.size();i++)
			{
				queryString ="select CUSTOMER_ABBR,CUSTOMER_NAME,CUSTOMER_CD from FMS7_CUSTOMER_MST where CUSTOMER_CD="+LOA_CUSTOMER_CD.elementAt(i)+" ";
				rset = stmt.executeQuery(queryString);
				//System.out.println("LOA_CUSTOMER_CD = "+queryString);
				if(rset.next())
				{
					LOA_CUSTOMER_NM.add(rset.getString(2)==null?"":rset.getString(2));
					LOA_CUSTOMER_ABBR.add(rset.getString(1)==null?"":rset.getString(1));
				}
				else
				{
					LOA_CUSTOMER_NM.add("");
					LOA_CUSTOMER_ABBR.add("");
				}												
//				queryString ="select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL where CUSTOMER_CD="+LOA_CUSTOMER_CD.elementAt(i)+" " +
//							 "AND SN_NO="+LOA_NO.elementAt(i)+" AND FGSA_NO="+TENDER_NO.elementAt(i)+" " +
//							 "AND GAS_DT between to_date('"+FRM_DT.elementAt(i)+"','dd/mm/yyyy')" +
//							 "AND to_date('"+TO_DT.elementAt(i)+"','dd/mm/yyyy') AND CONTRACT_TYPE='L'";
				queryString ="select SUM(QTY_MMBTU),SUM(QTY_SCM) from FMS7_DAILY_ALLOCATION_DTL where CUSTOMER_CD="+LOA_CUSTOMER_CD.elementAt(i)+" " +
				 "AND SN_NO="+LOA_NO.elementAt(i)+" AND FGSA_NO="+TENDER_NO.elementAt(i)+" " +
				 "AND GAS_DT between to_date('"+from_date+"','dd/mm/yyyy')" +
				 "AND to_date('"+to_date+"','dd/mm/yyyy') AND CONTRACT_TYPE='L'";


				rset = stmt.executeQuery(queryString);
				//System.out.println("LOA_QTY_MMBTU = "+queryString);
				if(rset.next())
				{
					LOA_SUPPLIEDMBTU.add(rset.getString(1)==null?"0.00":nf.format(Double.parseDouble(rset.getString(1))));
					LOA_SUPPLIEDMMSCM.add(rset.getString(2)==null?"0.00":nf.format(Double.parseDouble(rset.getString(2))/1000000));
					if(rset.getString(1)!=null && !(rset.getString(1).equals("")))
					{
						sup_tot_comit+=Double.parseDouble(rset.getString(1));
						sup_tot_comit_mmscm+=(Double.parseDouble(rset.getString(1))/38900);
					}
					else
					{
						sup_tot_comit+=0;
						sup_tot_comit_mmscm+=0;
					}
				}
				else
				{
					LOA_SUPPLIEDMBTU.add("0.00");
					LOA_SUPPLIEDMMSCM.add("0.00");
					sup_tot_comit+=0;
					sup_tot_comit_mmscm+=0;
				}				
				LOA_BALANCEMMBTU.add(nf.format(Double.parseDouble(""+LOA_BOOKMMBTU.elementAt(i))- Double.parseDouble(""+LOA_SUPPLIEDMBTU.elementAt(i))));
				bal_tot_comit+=Double.parseDouble(""+LOA_BOOKMMBTU.elementAt(i))- Double.parseDouble(""+LOA_SUPPLIEDMBTU.elementAt(i));
				
				LOA_BALANCEMMSCM.add(nf.format(Double.parseDouble(""+LOA_BOOKMMSCM.elementAt(i))- Double.parseDouble(""+LOA_SUPPLIEDMMSCM.elementAt(i))));
				bal_tot_comit_mmscm+=Double.parseDouble(""+LOA_BOOKMMSCM.elementAt(i))- Double.parseDouble(""+LOA_SUPPLIEDMMSCM.elementAt(i));
		   }
			
			LOA_SUPPLIEDMBTU1=new Vector[Vgas_date.size()] ; 
			LOA_SUPPLIEDMMSCM1=new Vector[Vgas_date.size()] ;; 
			
			for(int k=0;k<Vgas_date.size();k++)
			{
				LOA_SUPPLIEDMBTU1[k] =new Vector(); 
				LOA_SUPPLIEDMMSCM1[k]=new Vector(); 
				
				for(int i=0;i<LOA_CUSTOMER_CD.size();i++)
				{

					queryString ="select SUM(QTY_MMBTU),SUM(QTY_SCM) from FMS7_DAILY_ALLOCATION_DTL where CUSTOMER_CD="+LOA_CUSTOMER_CD.elementAt(i)+" " +
					 "AND SN_NO="+LOA_NO.elementAt(i)+" AND FGSA_NO="+TENDER_NO.elementAt(i)+" " +
					 "AND GAS_DT=to_date('"+Vgas_date.elementAt(k)+"','dd/mm/yyyy')" +
					 " AND CONTRACT_TYPE='L'";
					rset = stmt.executeQuery(queryString);
					////System.out.println(">>>>>>> "+queryString);
						if(rset.next())
						{
							LOA_SUPPLIEDMBTU1[k].add(rset.getString(1)==null?"-":nf.format(Double.parseDouble(rset.getString(1))));
							LOA_SUPPLIEDMMSCM1[k].add(rset.getString(2)==null?"-":nf.format(Double.parseDouble(rset.getString(2))/1000000));
							
						}
						else
						{
							LOA_SUPPLIEDMBTU1[k].add("-");
							LOA_SUPPLIEDMMSCM1[k].add("-");
							
						}
				}
			}


			
			
		   loa_sup_tot_comitment=nf.format(sup_tot_comit);
		   loa_bal_tot_comitment=nf.format(bal_tot_comit);
		   
		   loa_sup_tot_comitment_mmscm=nf.format(sup_tot_comit_mmscm);
		   loa_bal_tot_comitment_mmscm=nf.format(bal_tot_comit_mmscm);
			
			//--------Logic for Re-Gas based Summary Contract based Agreement	
			tot_comit=0.0;
			bal_tot_comit=0.0;
			sup_tot_comit=0.0;
			//MD20110917
			tot_comit_mmscm=0.0;
			sup_tot_comit_mmscm=0.0;
			bal_tot_comit_mmscm=0.0;
			
			FRM_DT.clear();
			TO_DT.clear();
			/*queryString = "select TO_CHAR(A.CONTRACT_START_DT,'DD/MM/YYYY'),TO_CHAR(A.CONTRACT_END_DT,'DD/MM/YYYY'),A.RE_GAS_NO,A.RE_GAS_REV_NO,A.CUSTOMER_CD,A.CARGO_SEQ_NO,A.QTY_TO_BE_SUPPLY,A.RE_GAS_TARIF " +
	 					  "from FMS7_RE_GAS_CARGO_DTL A where (A.CONTRACT_START_DT between to_date('"+from_date+"','dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy')"+ 
	 					  "OR A.CONTRACT_END_DT between to_date('"+from_date+"','dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy')) AND " +
	 		              "A.RE_GAS_REV_NO=(SELECT MAX(B.RE_GAS_REV_NO) FROM FMS7_RE_GAS_CARGO_DTL B where A.RE_GAS_NO=B.RE_GAS_NO AND A.RE_GAS_REV_NO=B.RE_GAS_REV_NO AND " +
	 		              "A.CUSTOMER_CD=B.CUSTOMER_CD AND A.CARGO_SEQ_NO=B.CARGO_SEQ_NO) order by A.CONTRACT_START_DT";*/
			
			//Updated By Milan 20110921
			queryString = "select TO_CHAR(A.CONTRACT_START_DT,'DD/MM/YYYY'),TO_CHAR(A.CONTRACT_END_DT,'DD/MM/YYYY'),A.RE_GAS_NO,A.RE_GAS_REV_NO,A.CUSTOMER_CD,A.CARGO_SEQ_NO,A.QTY_TO_BE_SUPPLY,A.RE_GAS_TARIF ,A.cargo_ref_no " +
			  "from FMS7_RE_GAS_CARGO_DTL A where "+ 
			  "(A.CONTRACT_START_DT <= to_date('"+to_date+"','dd/mm/yyyy')"+"AND A.CONTRACT_END_DT >= to_date('"+from_date+"','dd/mm/yyyy')) AND " +
             "A.RE_GAS_REV_NO=(SELECT MAX(B.RE_GAS_REV_NO) FROM FMS7_RE_GAS_CARGO_DTL B where A.RE_GAS_NO=B.RE_GAS_NO AND A.RE_GAS_REV_NO=B.RE_GAS_REV_NO AND " +
             "A.CUSTOMER_CD=B.CUSTOMER_CD AND A.CARGO_SEQ_NO=B.CARGO_SEQ_NO) order by A.CONTRACT_START_DT";
			rset = stmt.executeQuery(queryString);
			//System.out.println(queryString);
			while(rset.next())
			{				
				RE_GAS_NO.add(rset.getString(3)==null?"0":rset.getString(3));
				RE_GAS_REV_NO.add(rset.getString(4)==null?"0":rset.getString(4));
				RE_GAS_CUSTOMER_CD.add(rset.getString(5)==null?"0":rset.getString(5));									
				tmp_from_dt = rset.getString(1)==null?"":rset.getString(1);
				tmp_to_dt = rset.getString(2)==null?"":rset.getString(2);
				FRM_DT.add(rset.getString(1)==null?"":rset.getString(1));
				TO_DT.add(rset.getString(2)==null?"":rset.getString(2));
				temp_dt = tmp_from_dt+"   -   "+tmp_to_dt;
				RE_GAS_CARGO_ARRIVAL_DATE.add(temp_dt);											  
				RE_GAS_SEQ_NO.add(rset.getString(6)==null?"0":rset.getString(6));				  	
				RE_GAS_BOOKMMBTU.add(rset.getString(7)==null?"0.00":nf.format(Double.parseDouble(rset.getString(7))));	
			  	tot_comit += Double.parseDouble(rset.getString(7)==null?"0":nf.format(Double.parseDouble(rset.getString(7))));
			  	re_gas_tot_commit=""+ nf.format(tot_comit) ;
			  	//MD20110917
			  	RE_GAS_BOOKMMSCM.add(rset.getString(7)==null?"0.00":nf.format(Double.parseDouble(rset.getString(7))/38900));	
			  	tot_comit_mmscm += Double.parseDouble(rset.getString(7)==null?"0":nf.format(Double.parseDouble(rset.getString(7))/38900));
			  	re_gas_tot_commit_mmscm=""+ nf.format(tot_comit_mmscm) ;
			  	
			  	REGAS_RATE.add(nf.format(Double.parseDouble(rset.getString(8)==null?"0":rset.getString(8))));
			  	Regas_cargo_ref_no.add(rset.getString(9));
			}			
			
			for(int i=0;i<RE_GAS_CUSTOMER_CD.size();i++)
			{
				queryString ="select CUSTOMER_ABBR,CUSTOMER_NAME,CUSTOMER_CD from FMS7_CUSTOMER_MST where CUSTOMER_CD="+RE_GAS_CUSTOMER_CD.elementAt(i)+" ";
				rset = stmt.executeQuery(queryString);
				//System.out.println("RE_GAS_CUSTOMER_CD = "+queryString);
				if(rset.next())
				{
					RE_GAS_CUSTOMER_NM.add(rset.getString(2)==null?"":rset.getString(2));
					RE_GAS_CUSTOMER_ABBR.add(rset.getString(1)==null?"":rset.getString(1));
				}
				else
				{
					RE_GAS_CUSTOMER_NM.add("");
					RE_GAS_CUSTOMER_ABBR.add("");	
				}	
				
				queryString = "SELECT TO_CHAR(A.SIGNING_DT,'DD/MM/YYYY') FROM FMS7_RE_GAS_MST A " +
							  "WHERE A.RE_GAS_NO='"+RE_GAS_NO.elementAt(i)+"' AND " +
							  "A.REV_NO='"+RE_GAS_REV_NO.elementAt(i)+"' AND " +
							  "A.CUSTOMER_CD='"+RE_GAS_CUSTOMER_CD.elementAt(i)+"' ";			 
				//System.out.println(queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
				  	REGAS_SIGNING_DT.add(rset.getString(1)==null?"":rset.getString(1));
				}
				else
				{
					REGAS_SIGNING_DT.add("");
				}
	
//				queryString ="select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL where CUSTOMER_CD="+RE_GAS_CUSTOMER_CD.elementAt(i)+" " +
//							 "AND SN_NO="+RE_GAS_SEQ_NO.elementAt(i)+" AND FGSA_NO="+RE_GAS_NO.elementAt(i)+" " +
//							 "AND GAS_DT between to_date('"+FRM_DT.elementAt(i)+"','dd/mm/yyyy')" +
//							 "AND to_date('"+TO_DT.elementAt(i)+"','dd/mm/yyyy') AND CONTRACT_TYPE='R'";
				queryString ="select SUM(QTY_MMBTU),SUM(QTY_SCM) from FMS7_DAILY_ALLOCATION_DTL where CUSTOMER_CD="+RE_GAS_CUSTOMER_CD.elementAt(i)+" " +
				 "AND SN_NO="+RE_GAS_SEQ_NO.elementAt(i)+" AND FGSA_NO="+RE_GAS_NO.elementAt(i)+" " +
				 "AND GAS_DT between to_date('"+from_date+"','dd/mm/yyyy')" +
				 "AND to_date('"+to_date+"','dd/mm/yyyy') AND CONTRACT_TYPE='R'";

				//System.out.println("RE_GAS_QTY_MMBTU = "+queryString);
				rset = stmt.executeQuery(queryString);				
				if(rset.next())
				{
					RE_GAS_SUPPLIEDMBTU.add(rset.getString(1)==null?"0.00":nf.format(Double.parseDouble(rset.getString(1))));
					//MD20110917
					RE_GAS_SUPPLIEDMMSCM.add(rset.getString(2)==null?"0.00":nf.format(Double.parseDouble(rset.getString(2))/1000000));
					if(rset.getString(1)!=null && !(rset.getString(1).equals("")))
					{
						sup_tot_comit+=Double.parseDouble(rset.getString(1));
						sup_tot_comit_mmscm+=(Double.parseDouble(rset.getString(1))/38900);
					}
					else
					{
						sup_tot_comit+=0;
						sup_tot_comit_mmscm+=0;
					}
				}
				else
				{
					RE_GAS_SUPPLIEDMBTU.add("0.00");
					sup_tot_comit+=0;
					
					RE_GAS_SUPPLIEDMMSCM.add("0.00");
					sup_tot_comit_mmscm+=0;
				}				
				RE_GAS_BALANCEMMBTU.add(nf.format(Double.parseDouble(""+RE_GAS_BOOKMMBTU.elementAt(i))- Double.parseDouble(""+RE_GAS_SUPPLIEDMBTU.elementAt(i))));
				bal_tot_comit+=Double.parseDouble(""+RE_GAS_BOOKMMBTU.elementAt(i))- Double.parseDouble(""+RE_GAS_SUPPLIEDMBTU.elementAt(i));
				//MD20110917
				RE_GAS_BALANCEMMSCM.add(nf.format(Double.parseDouble(""+RE_GAS_BOOKMMSCM.elementAt(i))- Double.parseDouble(""+RE_GAS_SUPPLIEDMMSCM.elementAt(i))));
				bal_tot_comit_mmscm+=Double.parseDouble(""+RE_GAS_BOOKMMSCM.elementAt(i))- Double.parseDouble(""+RE_GAS_SUPPLIEDMMSCM.elementAt(i));
		   }
			re_gas_sup_tot_comitment=nf.format(sup_tot_comit);
			re_gas_bal_tot_comitment=nf.format(bal_tot_comit);
			//MD20110917
			re_gas_sup_tot_comitment_mmscm=nf.format(sup_tot_comit_mmscm);
			re_gas_bal_tot_comitment_mmscm=nf.format(bal_tot_comit_mmscm);
			
			
			RE_GAS_SUPPLIEDMBTU1=new Vector[Vgas_date.size()] ; 
			RE_GAS_SUPPLIEDMMSCM1=new Vector[Vgas_date.size()] ;; 
			
			for(int k=0;k<Vgas_date.size();k++)
			{
				RE_GAS_SUPPLIEDMBTU1[k] =new Vector(); 
				RE_GAS_SUPPLIEDMMSCM1[k]=new Vector(); 
				
			for(int i=0;i<RE_GAS_CUSTOMER_CD.size();i++)
			{

				queryString ="select SUM(QTY_MMBTU),SUM(QTY_SCM) from FMS7_DAILY_ALLOCATION_DTL where CUSTOMER_CD="+RE_GAS_CUSTOMER_CD.elementAt(i)+" " +
				 "AND SN_NO="+RE_GAS_SEQ_NO.elementAt(i)+" AND FGSA_NO="+RE_GAS_NO.elementAt(i)+" " +
				 "AND GAS_DT=to_date('"+Vgas_date.elementAt(k)+"','dd/mm/yyyy')" +
				 " AND CONTRACT_TYPE='R'";
					rset = stmt.executeQuery(queryString);
					////System.out.println(">>>>>>> "+queryString);
						if(rset.next())
						{
							RE_GAS_SUPPLIEDMBTU1[k].add(rset.getString(1)==null?"-":nf.format(Double.parseDouble(rset.getString(1))));
							RE_GAS_SUPPLIEDMMSCM1[k].add(rset.getString(2)==null?"-":nf.format(Double.parseDouble(rset.getString(2))/1000000));
							
						}
						else
						{
							RE_GAS_SUPPLIEDMBTU1[k].add("-");
							RE_GAS_SUPPLIEDMMSCM1[k].add("-");
							
						}
				}
			}

			
			
		}		
		catch(Exception e)
		{
			  //System.out.println("Exception in Databean_Contract_MasterReport--->fetchEligibleCargoList_new()-->"+e);
			  e.printStackTrace();
		}
	}
	
	
	
	public void fetchcargo_allocation_plantwise()
	{
		try
		{			
			String tmp_from_dt = " ";
			String temp_dt="";
			String tmp_to_dt = " ";			
			double tot_comit=0.0;
			double sup_tot_comit=0.0;
			double bal_tot_comit=0.0;
			double tot_comit_mmscm=0.0;
			double sup_tot_comit_mmscm=0.0;
			double bal_tot_comit_mmscm=0.0;
			Vector FRM_DT=new Vector();
			Vector TO_DT=new Vector();						
			Vector RE_GAS_REV_NO=new Vector();
			double count=0;
			String query="select to_date('"+CONTRACT_END_DT.elementAt(0)+"','dd/mm/yyyy')- to_date('"+CONTRACT_START_DT.elementAt(0)+"','dd/mm/yyyy')+1 from dual";
			//System.out.println(query);
			rset=stmt.executeQuery(query);
			if(rset.next())
			{
				count=rset.getDouble(1);
			}
			
			for(int i=0;i<count;i++)
			{
				String query1="select to_char(to_date('"+CONTRACT_START_DT.elementAt(0)+"','dd/mm/yyyy')+ "+i+",'dd/mm/yyyy') from dual";
				rset=stmt.executeQuery(query1);
				if(rset.next())
				{
					Vgas_date.add(rset.getString(1));
				}
				
			}
						
			tot_comit=0.0;
			bal_tot_comit=0.0;
			sup_tot_comit=0.0;
			
			tot_comit_mmscm=0.0;
			sup_tot_comit_mmscm=0.0;
			bal_tot_comit_mmscm=0.0;
			
			FRM_DT.clear();
			TO_DT.clear();
			
			queryString = "select TO_CHAR(A.CONTRACT_START_DT,'DD/MM/YYYY'),TO_CHAR(A.CONTRACT_END_DT,'DD/MM/YYYY'),A.RE_GAS_NO,A.RE_GAS_REV_NO,A.CUSTOMER_CD,A.CARGO_SEQ_NO,A.QTY_TO_BE_SUPPLY,A.RE_GAS_TARIF ,A.cargo_ref_no " +
			  "from FMS7_RE_GAS_CARGO_DTL A where cargo_ref_no='"+setcargo_ref_no+"'";
			rset = stmt.executeQuery(queryString);
			//System.out.println(queryString);
			while(rset.next())
			{				
				RE_GAS_NO.add(rset.getString(3)==null?"0":rset.getString(3));
				RE_GAS_REV_NO.add(rset.getString(4)==null?"0":rset.getString(4));
				RE_GAS_CUSTOMER_CD.add(rset.getString(5)==null?"0":rset.getString(5));									
				tmp_from_dt = rset.getString(1)==null?"":rset.getString(1);
				tmp_to_dt = rset.getString(2)==null?"":rset.getString(2);
				FRM_DT.add(rset.getString(1)==null?"":rset.getString(1));
				TO_DT.add(rset.getString(2)==null?"":rset.getString(2));
				temp_dt = tmp_from_dt+"   -   "+tmp_to_dt;
				RE_GAS_CARGO_ARRIVAL_DATE.add(temp_dt);											  
				RE_GAS_SEQ_NO.add(rset.getString(6)==null?"0":rset.getString(6));				  	
					  	
			}			
			
			queryString=" Select DISTINCT(A.SEQ_NO), to_char(A.EFF_DT,'dd/mm/yyyy'), A.PLANT_TYPE, A.PLANT_NAME, A.PLANT_ABBR, A.PLANT_ADDR, A.PLANT_STATE, A.PLANT_ZONE, A.PLANT_CITY,  A.PLANT_PIN, A.PLANT_SECTOR from FMS7_CUSTOMER_PLANT_DTL A WHERE A.CUSTOMER_CD='"+Buyer_cd+"' and A.EFF_DT=(select max(B.eff_dt) from FMS7_CUSTOMER_PLANT_DTL B where A.seq_no=B.seq_no and B.CUSTOMER_CD='"+Buyer_cd+"') order by A.SEQ_NO";	
			//System.out.println("Last date = "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				cust_plant_cd.add(rset.getString(1)==null?"":rset.getString(1));
				cust_plant_nm.add(rset.getString(4)==null?"":rset.getString(4));
				
			}	
			
			RE_GAS_SUPPLIEDMBTU1=new Vector[Vgas_date.size()] ; 
			RE_GAS_SUPPLIEDMMSCM1=new Vector[Vgas_date.size()] ; 
			
			for(int k=0;k<Vgas_date.size();k++)
			{
				RE_GAS_SUPPLIEDMBTU1[k] =new Vector(); 
				RE_GAS_SUPPLIEDMMSCM1[k]=new Vector(); 
                double sum=0;
				
				for(int i=0;i<cust_plant_cd.size();i++)
				{
					queryString ="select SUM(QTY_MMBTU),SUM(QTY_SCM) from FMS7_DAILY_ALLOCATION_DTL where CUSTOMER_CD="+Buyer_cd+" " +
					 "AND SN_NO="+RE_GAS_SEQ_NO.elementAt(0)+" AND FGSA_NO="+re_gas_no+" " +
					 "AND GAS_DT=to_date('"+Vgas_date.elementAt(k)+"','dd/mm/yyyy')" +
					 " AND CONTRACT_TYPE='R' and plant_seq_no='"+cust_plant_cd.elementAt(i)+"'";
					 rset = stmt.executeQuery(queryString);
					////System.out.println(">>>>>>> "+queryString);
					if(rset.next())
					{
						RE_GAS_SUPPLIEDMBTU1[k].add(rset.getString(1)==null?"-":nf.format(Double.parseDouble(rset.getString(1))));
						RE_GAS_SUPPLIEDMMSCM1[k].add(rset.getString(2)==null?"-":nf.format(Double.parseDouble(rset.getString(2))/1000000));
						sum=sum+Double.parseDouble(rset.getString(1)==null?"0":nf.format(Double.parseDouble(rset.getString(1))));		
					}
					else
					{
						RE_GAS_SUPPLIEDMBTU1[k].add("-");
						RE_GAS_SUPPLIEDMMSCM1[k].add("-");
								
					}
				}
				Vdatewise_plant_tot.add(String.valueOf(sum));
			}
			
			
			for(int i=0;i<cust_plant_cd.size();i++)
			{
				String temp="";
				String query1="select hlpl_inv_seq_no,financial_year from FMS7_INVOICE_MST where CUSTOMER_CD="+Buyer_cd+" " +
				 "AND SN_NO="+RE_GAS_SEQ_NO.elementAt(0)+" AND FGSA_NO="+re_gas_no+" " +
				 "AND period_start_dt between to_date('"+Vgas_date.firstElement()+"','dd/mm/yyyy') and to_date('"+Vgas_date.lastElement()+"','dd/mm/yyyy')" +
				 " AND CONTRACT_TYPE='R' and plant_seq_no='"+cust_plant_cd.elementAt(i)+"'";
				rset = stmt.executeQuery(query1);
				while(rset.next())
				{
					if(temp.equalsIgnoreCase(""))
					{
						temp=rset.getString(1)+"/"+rset.getString(2);
					}
					else
					{
						temp=temp+","+rset.getString(1)+"/"+rset.getString(2);
					}
					//System.out.println(cust_plant_nm.elementAt(i)+"-->>"+rset.getString(1)+"/"+rset.getString(2));
					
				}
				Vplant_inv_no.add(temp);
			}
			
			
		}		
		catch(Exception e)
		{
			  //System.out.println("Exception in Databean_Contract_MasterReport--->fetchEligibleCargoList_new()-->"+e);
			  e.printStackTrace();
		}
	}
	
	String Emp_cd = "";
	String Customer_access_flag = "N";
	
	public void fetchEligibleCargoList_WITH_ACCESS()
	{
		try
		{			
			String tmp_from_dt = " ";
			String temp_dt="";
			String tmp_to_dt = " ";			
			double tot_comit=0.0;
			double sup_tot_comit=0.0;
			double bal_tot_comit=0.0;
			//Introduce By Milan 20110916 FOR MMSCM
			double tot_comit_mmscm=0.0;
			double sup_tot_comit_mmscm=0.0;
			double bal_tot_comit_mmscm=0.0;
			Vector FRM_DT=new Vector();
			Vector TO_DT=new Vector();						
			Vector RE_GAS_REV_NO=new Vector();
			Vector LTCORA_REV_NO=new Vector();//ADDED FOR LTCORA AND CN
			Vector LTCORA_MAPPING_ID=new Vector();//ADDED FOR LTCORA AND CN
			Vector CN_REV_NO=new Vector();//ADDED FOR LTCORA AND CN
			Vector CN_MAPPING_ID=new Vector();//ADDED FOR LTCORA AND CN
			
			/*queryString ="select TO_CHAR(A.START_DT,'DD/MM/YYYY'),TO_CHAR(A.END_DT,'DD/MM/YYYY'),A.SN_NO,A.SN_REV_NO,A.CUSTOMER_CD,A.FGSA_NO,A.FGSA_REV_NO,A.TCQ, " +
			 		"A.SN_REF_NO,to_char(A.SIGNING_DT,'dd/mm/yyyy'),A.RATE from FMS7_SN_MST A where (A.START_DT between to_date('"+from_date+"','dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy')"+ 
			 		"OR A.END_DT between to_date('"+from_date+"','dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy')) AND " +
			 		"A.SN_REV_NO=(SELECT MAX(B.SN_REV_NO) FROM FMS7_SN_MST B where A.SN_NO=B.SN_NO AND A.FGSA_NO=B.FGSA_NO AND " +
			 		"A.CUSTOMER_CD=B.CUSTOMER_CD AND A.FGSA_REV_NO=B.FGSA_REV_NO) order by A.START_DT";*/
			
			//Updated By Milan 20110921
			queryString ="select TO_CHAR(A.START_DT,'DD/MM/YYYY'),TO_CHAR(A.END_DT,'DD/MM/YYYY'),A.SN_NO,A.SN_REV_NO,A.CUSTOMER_CD,A.FGSA_NO,A.FGSA_REV_NO,A.TCQ, " +
	 		"A.SN_REF_NO,to_char(A.SIGNING_DT,'dd/mm/yyyy'),A.RATE from FMS7_SN_MST A, "
	 		+ "SEC_EMP_CUSTOMER_ALLOC_MST B where "+ 
	 		"(A.START_DT <= to_date('"+to_date+"','dd/mm/yyyy')"+"AND A.END_DT >= to_date('"+from_date+"','dd/mm/yyyy')) AND " +
	 		"A.SN_REV_NO=(SELECT MAX(B.SN_REV_NO) FROM FMS7_SN_MST B where A.SN_NO=B.SN_NO AND A.FGSA_NO=B.FGSA_NO AND " +
	 		"A.CUSTOMER_CD=B.CUSTOMER_CD AND A.FGSA_REV_NO=B.FGSA_REV_NO) AND A.CUSTOMER_CD=B.CUSTOMER_CD "
	 		+ "AND B.EMP_CD='"+Emp_cd+"' order by A.START_DT";
			
			//(START_DT <= to_date('"+to_date+"','dd/mm/yyyy')"+"AND END_DT >= to_date('"+from_date+"','dd/mm/yyyy'))
			System.out.println(queryString);
			rset = stmt.executeQuery(queryString);
			////System.out.println("FOr SN : "+to_date+" "+from_date);
			while(rset.next())
			{				
				////System.out.println("FOr SN : "+rset.getString(1)+" "+rset.getString(2));
				SN_NO.add(rset.getString(3)==null?"0":rset.getString(3));
				CUSTOMER_CD.add(rset.getString(5)==null?"0":rset.getString(5));									
				tmp_from_dt = rset.getString(1)==null?"":rset.getString(1);
				tmp_to_dt = rset.getString(2)==null?"":rset.getString(2);
				FRM_DT.add(rset.getString(1)==null?"":rset.getString(1));
				TO_DT.add(rset.getString(2)==null?"":rset.getString(2));
				temp_dt = tmp_from_dt+"   -   "+tmp_to_dt;
				CARGO_ARRIVAL_DATE.add(temp_dt);				
				SN_REV_NO.add(rset.getString(4)==null?"0":rset.getString(4));			  	
			  	FGSA_NO.add("S"+rset.getString(6)==null?"0":rset.getString(6));	
			  	FGSA_REV_NO.add(rset.getString(7)==null?"0":rset.getString(7));	
			  	BOOKMMBTU.add(rset.getString(8)==null?"0.00":nf.format(Double.parseDouble(rset.getString(8))));
			  	BOOKMMSCM.add(rset.getString(8)==null?"0.00":nf.format(Double.parseDouble(rset.getString(8))/38900));//MD20110916
			  	tot_comit += Double.parseDouble(rset.getString(8)==null?"0":nf.format(Double.parseDouble(rset.getString(8))));
			  	
			  	tot_comit_mmscm=(tot_comit/38900);//MD20110916
			  	tot_commitment_mmscm=nf.format(tot_comit_mmscm);
			  	////System.out.println("BOOKEDMMSCM : "+BOOKMMSCM+" TOTAL : "+tot_comit_mmscm);
			  	
			  	tot_commitment=nf.format(tot_comit);
			  	SN_REF_NO.add(rset.getString(9)==null?"0":rset.getString(9));	
			  	SIGNING_DT.add(rset.getString(10)==null?"0":rset.getString(10));
			  	RATE.add(nf2.format(Double.parseDouble(rset.getString(11)==null?"0":rset.getString(11))));
			}	
			
//			//System.out.println("SN_REF_NO = "+SN_REF_NO);
			for(int i=0;i<CUSTOMER_CD.size();i++)
			{
				queryString ="select CUSTOMER_ABBR,CUSTOMER_NAME,CUSTOMER_CD from FMS7_CUSTOMER_MST where CUSTOMER_CD="+CUSTOMER_CD.elementAt(i)+" ";
				rset = stmt.executeQuery(queryString);
				////System.out.println(queryString);
				if(rset.next())
				{
					CUSTOMER_NM.add(rset.getString(2)==null?"":rset.getString(2));
					CUSTOMER_ABBR.add(rset.getString(1)==null?"":rset.getString(1));	
				}
				else
				{
					CUSTOMER_NM.add("");
					CUSTOMER_ABBR.add("");
				}	
				System.out.println("CUSTOMER_NM: "+CUSTOMER_NM);
//				queryString ="select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL where CUSTOMER_CD="+CUSTOMER_CD.elementAt(i)+" " +
//							 "AND SN_NO="+SN_NO.elementAt(i)+" AND FGSA_NO="+FGSA_NO.elementAt(i)+" " +
//							 "AND FGSA_REV_NO="+FGSA_REV_NO.elementAt(i)+" AND GAS_DT between to_date('"+FRM_DT.elementAt(i)+"','dd/mm/yyyy')" +
//							 "AND to_date('"+TO_DT.elementAt(i)+"','dd/mm/yyyy') AND CONTRACT_TYPE='S'";
//			
				queryString ="select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL where CUSTOMER_CD="+CUSTOMER_CD.elementAt(i)+" " +
				 "AND SN_NO="+SN_NO.elementAt(i)+" AND FGSA_NO="+FGSA_NO.elementAt(i)+" " +
				 "AND FGSA_REV_NO="+FGSA_REV_NO.elementAt(i)+" AND GAS_DT between to_date('"+FRM_DT.elementAt(i)+"','dd/mm/yyyy')" +
				 "AND to_date('"+to_date+"','dd/mm/yyyy') AND CONTRACT_TYPE='S'";

				rset = stmt.executeQuery(queryString);
//				//System.out.println(">>>>>>> "+queryString);
				if(rset.next())
				{
					SUPPLIEDMBTU.add(rset.getString(1)==null?"0.00":nf.format(Double.parseDouble(rset.getString(1))));
					//MD20110916
					SUPPLIEDMMSCM.add(rset.getString(1)==null?"0.00":nf.format(Double.parseDouble(rset.getString(1))/38900));
					if(rset.getString(1)!=null && !(rset.getString(1).equals("")))
					{
						sup_tot_comit+=Double.parseDouble(rset.getString(1));
						sup_tot_comit_mmscm+=(Double.parseDouble(rset.getString(1))/38900);//MD20110916
					}
					else
					{
						sup_tot_comit+=0;
						sup_tot_comit_mmscm+=0;//MD20110916
					}
				}
				else
				{
					SUPPLIEDMBTU.add("0.00");
					SUPPLIEDMMSCM.add("0.00");//MD20110916
					sup_tot_comit+=0;
					sup_tot_comit_mmscm+=0;//MD20110916
				}
				
				BALANCEMMBTU.add(nf.format(Double.parseDouble(""+BOOKMMBTU.elementAt(i))- Double.parseDouble(""+SUPPLIEDMBTU.elementAt(i))));
				//MD20110916
				BALANCEMMSCM.add(nf.format(Double.parseDouble(""+BOOKMMSCM.elementAt(i))- Double.parseDouble(""+SUPPLIEDMMSCM.elementAt(i))));
				bal_tot_comit+=Double.parseDouble(""+BOOKMMBTU.elementAt(i))- Double.parseDouble(""+SUPPLIEDMBTU.elementAt(i));
				bal_tot_comit_mmscm+=Double.parseDouble(""+BOOKMMSCM.elementAt(i))- Double.parseDouble(""+SUPPLIEDMMSCM.elementAt(i));
//				//System.out.println("SUPLIEDMMSCM : "+SUPPLIEDMMSCM+" TOTAL : "+sup_tot_comit_mmscm);
//				//System.out.println("BALANCEMMSCM : "+BALANCEMMSCM+" TOTAL : "+bal_tot_comit_mmscm);
				
				//////////////SB20200817////////////////////////
				String TCQ=""; String Alloc_Qty=""; String Gen_Dt=to_date; String Cont_End_Dt="";
				TCQ=""+BOOKMMBTU.elementAt(i); Cont_End_Dt=""+TO_DT.elementAt(i);
				Alloc_Qty=""+SUPPLIEDMBTU.elementAt(i); Cont_End_Dt=""+TO_DT.elementAt(i);
				String Mapp_Id=CUSTOMER_CD.elementAt(i)+"-"+FGSA_NO.elementAt(i)+"-"+FGSA_REV_NO.elementAt(i)+"-"+SN_NO.elementAt(i)+"-"+SN_REV_NO.elementAt(i);
				String Phy_CurFrd_Price="0"; String Fin_CurFrd_Price="0";
				String Buy_Sell="SELL"; 
				if(CUSTOMER_ABBR.elementAt(i).equals("SEIPL"))
					Buy_Sell="Buy";
				
				String PriceType="";
				queryString ="SELECT PRICE_TYPE, MAN_CD FROM FMS7_MAN_REQ_MST "+
						" where MAN_CD IN (SELECT CARGO_REF_NO FROM FMS7_SN_CARGO_DTL "
						+ " where FGSA_NO='"+FGSA_NO.elementAt(i)+"' AND FGSA_REV_NO='"+FGSA_REV_NO.elementAt(i)+"' AND SN_NO='"+SN_NO.elementAt(i)+"' "
						+ "AND SN_REV_NO='"+SN_REV_NO.elementAt(i)+"') ";
				rset = stmt.executeQuery(queryString);
//				//System.out.println(">>>>>>> "+queryString);
				if(rset.next())
				{
					PriceType=rset.getString(1);
				}
				if(PriceType.equals(""))
				{
					String NewSalePrice="";
					queryString ="SELECT NEW_SALE_PRICE FROM FMS8_CARGO_ALLOC_REVISED_DTL "+
							" where FGSA_NO='"+FGSA_NO.elementAt(i)+"' AND FGSA_REV_NO='"+FGSA_REV_NO.elementAt(i)+"' AND SN_NO='"+SN_NO.elementAt(i)+"' "
							+ "AND SN_REV_NO='"+SN_REV_NO.elementAt(i)+"' AND CONTRACT_TYPE='S' AND NEW_PRICE_EFF_DT <=TO_DATE('"+Gen_Dt+"','DD/MM/YYYY')";//AND FLAG='A' ";
					rset = stmt.executeQuery(queryString);
				//	System.out.println(">>>>>>> "+queryString);
					if(rset.next())
					{
						NewSalePrice=rset.getString(1);
						PriceType="M";
					}
					else
						PriceType="F";
					System.out.println("NewSalePrice>>>>>>> "+NewSalePrice);
				}
				/*///Price Pulling from Table////////////////////////////
				String tempGen_Dt[]=Gen_Dt.split("/");
				String GenMth=tempGen_Dt[1]; String GenYr=tempGen_Dt[2]; String GenMthYr=GenMth+"/"+GenYr;
				String INDEX_TYPE="LNG_ICE_JKM";
				queryString ="SELECT COMMODITY_VALUE FROM FMS9_COMMODITY_VALUE_DTL WHERE INDEX_TYPE='"+INDEX_TYPE+"' and DELV_MTH=TO_DATE('"+GenMthYr+"','MM/YYYY') ";
				rset = stmt.executeQuery(queryString);
			//	System.out.println(">>>>>>>PRICE PUULING: "+queryString);
				if(rset.next())
				{
					Fin_CurFrd_Price=rset.getString(1);
				}
				INDEX_TYPE="RLNG_PHYS_INDIA";
				queryString ="SELECT COMMODITY_VALUE FROM FMS9_COMMODITY_VALUE_DTL WHERE INDEX_TYPE='"+INDEX_TYPE+"' and DELV_MTH=TO_DATE('"+GenMthYr+"','MM/YYYY') ";
				rset = stmt.executeQuery(queryString);
			//	System.out.println(">>>>>>>PRICE PUULING: "+queryString);
				if(rset.next())
				{
					Phy_CurFrd_Price=rset.getString(1);
				}
				*////^^^^^^^Price Pulling from Table////////////////////////////
				String PriorCurveGasPrice="";
			//	if(Double.parseDouble(""+VMthYrWisePriceICE_JKM.elementAt(i))>0)
					PriorCurveGasPrice="0";
				double LNG_ICE_JKM_EXPOBaseValue=0;
				String DropOff="";
				String DealPrice="";
				DealPrice=""+RATE.elementAt(i);
				String Slope="1.025";
				String GasDate=""+FRM_DT.elementAt(i);
				String PercReduce=""+VEff_Perc.elementAt(i); //SB20201006
				String CumuPercReduce=""+VEff_CumulativePerc.elementAt(i); //SB20201006
				MRCR_ContDtl(Mapp_Id, Buy_Sell,PriceType, DealPrice, TCQ, Alloc_Qty, GasDate, Gen_Dt, Cont_End_Dt, Phy_CurFrd_Price, Fin_CurFrd_Price, Slope, PriorCurveGasPrice, PercReduce, CumuPercReduce, LNG_ICE_JKM_EXPOBaseValue, DropOff);
				////////////////////////////////////////////////
		   }
			
			System.out.println(CUSTOMER_CD.size()+"  >>>>>>>VMthYrWisePriceICE_JKM: "+VMthYrWisePriceICE_JKM);
		   sup_tot_comitment=nf.format(sup_tot_comit);
		   bal_tot_comitment=nf.format(bal_tot_comit);
		   
		   //Introduce by Milan 20010916
		   sup_tot_comitment_mmscm=nf.format(sup_tot_comit_mmscm);
		   bal_tot_comitment_mmscm=nf.format(bal_tot_comit_mmscm);
			
		    //--------Logic for Tender and LOA based Summary Contract based Agreement	
			tot_comit=0.0;
			bal_tot_comit=0.0;
			sup_tot_comit=0.0;
			
			tot_comit_mmscm=0.0;
			sup_tot_comit_mmscm=0.0;
			bal_tot_comit_mmscm=0.0;
			FRM_DT.clear();
			TO_DT.clear();
			/*queryString =   "select TO_CHAR(A.START_DT,'DD/MM/YYYY'),TO_CHAR(A.END_DT,'DD/MM/YYYY'),A.LOA_NO,A.LOA_REV_NO,A.CUSTOMER_CD,A.TENDER_NO,A.TCQ, " +
	 						"A.LOA_REF_NO,to_char(A.SIGNING_DT,'dd/mm/yyyy'),A.RATE from FMS7_LOA_MST A where (A.START_DT between to_date('"+from_date+"','dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy')"+ 
	 						"OR A.END_DT between to_date('"+from_date+"','dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy')) AND " +
	 						"A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM FMS7_LOA_MST B where A.LOA_NO=B.LOA_NO AND A.TENDER_NO=B.TENDER_NO AND " +
	 						"A.CUSTOMER_CD=B.CUSTOMER_CD) order by A.START_DT";*/
			
			//Upadated By Milan 20110921
			queryString =   "select TO_CHAR(A.START_DT,'DD/MM/YYYY'),TO_CHAR(A.END_DT,'DD/MM/YYYY'),A.LOA_NO,A.LOA_REV_NO,A.CUSTOMER_CD,A.TENDER_NO,A.TCQ, " +
				"A.LOA_REF_NO,to_char(A.SIGNING_DT,'dd/mm/yyyy'),A.RATE from FMS7_LOA_MST A, "
				+ "SEC_EMP_CUSTOMER_ALLOC_MST B where "+ 
				"(A.START_DT <= to_date('"+to_date+"','dd/mm/yyyy')"+"AND A.END_DT >= to_date('"+from_date+"','dd/mm/yyyy')) AND " +
				"A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM FMS7_LOA_MST B where A.LOA_NO=B.LOA_NO AND A.TENDER_NO=B.TENDER_NO AND " +
				"A.CUSTOMER_CD=B.CUSTOMER_CD) AND A.CUSTOMER_CD=B.CUSTOMER_CD AND B.EMP_CD='"+Emp_cd+"'  "
				+ "order by A.START_DT";
			rset = stmt.executeQuery(queryString);
//			//System.out.println(queryString);
			while(rset.next())
			{				
				LOA_NO.add(rset.getString(3)==null?"0":rset.getString(3));
				LOA_CUSTOMER_CD.add(rset.getString(5)==null?"0":rset.getString(5));									
				tmp_from_dt = rset.getString(1)==null?"":rset.getString(1);
				tmp_to_dt = rset.getString(2)==null?"":rset.getString(2);
				FRM_DT.add(rset.getString(1)==null?"":rset.getString(1));
				TO_DT.add(rset.getString(2)==null?"":rset.getString(2));
				temp_dt = tmp_from_dt+"   -   "+tmp_to_dt;
				LOA_CARGO_ARRIVAL_DATE.add(temp_dt);				
				LOA_REV_NO.add(rset.getString(4)==null?"0":rset.getString(4));			  	
			  	TENDER_NO.add(rset.getString(6)==null?"0":rset.getString(6));	
			  	LOA_BOOKMMBTU.add(rset.getString(7)==null?"0.00":nf.format(Double.parseDouble(rset.getString(7))));	
			  	tot_comit += Double.parseDouble(rset.getString(7)==null?"0":nf.format(Double.parseDouble(rset.getString(7))));
			  	loa_tot_commitment=nf.format(tot_comit) ;
			  	
			  	//INTRODUCE BY MILAN 20110917 FOR SCMMBTU
			  	LOA_BOOKMMSCM.add(rset.getString(7)==null?"0.00":nf.format(Double.parseDouble(rset.getString(7))/38900));	
			  	tot_comit_mmscm += Double.parseDouble(rset.getString(7)==null?"0":nf.format(Double.parseDouble(rset.getString(7))/38900));
			  	loa_tot_commitment_mmscm=nf.format(tot_comit_mmscm) ;
			  	
			  	LOA_REF_NO.add(rset.getString(8)==null?"0":rset.getString(8));
			  	LOA_SIGNING_DT.add(rset.getString(9)==null?"":rset.getString(9));
			  	LOA_RATE.add(nf2.format(Double.parseDouble(rset.getString(10)==null?"0":rset.getString(10))));			  	
			}
			
			for(int i=0;i<LOA_CUSTOMER_CD.size();i++)
			{
				queryString ="select CUSTOMER_ABBR,CUSTOMER_NAME,CUSTOMER_CD from FMS7_CUSTOMER_MST where CUSTOMER_CD="+LOA_CUSTOMER_CD.elementAt(i)+" ";
				rset = stmt.executeQuery(queryString);
//				//System.out.println("LOA_CUSTOMER_CD = "+queryString);
				if(rset.next())
				{
					LOA_CUSTOMER_NM.add(rset.getString(2)==null?"":rset.getString(2));
					LOA_CUSTOMER_ABBR.add(rset.getString(1)==null?"":rset.getString(1));
				}
				else
				{
					LOA_CUSTOMER_NM.add("");
					LOA_CUSTOMER_ABBR.add("");
				}												
//				queryString ="select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL where CUSTOMER_CD="+LOA_CUSTOMER_CD.elementAt(i)+" " +
//							 "AND SN_NO="+LOA_NO.elementAt(i)+" AND FGSA_NO="+TENDER_NO.elementAt(i)+" " +
//							 "AND GAS_DT between to_date('"+FRM_DT.elementAt(i)+"','dd/mm/yyyy')" +
//							 "AND to_date('"+TO_DT.elementAt(i)+"','dd/mm/yyyy') AND CONTRACT_TYPE='L'";
				queryString ="select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL where CUSTOMER_CD="+LOA_CUSTOMER_CD.elementAt(i)+" " +
				 "AND SN_NO="+LOA_NO.elementAt(i)+" AND FGSA_NO="+TENDER_NO.elementAt(i)+" " +
				 "AND GAS_DT between to_date('"+FRM_DT.elementAt(i)+"','dd/mm/yyyy')" +
				 "AND to_date('"+to_date+"','dd/mm/yyyy') AND CONTRACT_TYPE='L'";


				rset = stmt.executeQuery(queryString);
//				//System.out.println("LOA_QTY_MMBTU = "+queryString);
				if(rset.next())
				{
					LOA_SUPPLIEDMBTU.add(rset.getString(1)==null?"0.00":nf.format(Double.parseDouble(rset.getString(1))));
					LOA_SUPPLIEDMMSCM.add(rset.getString(1)==null?"0.00":nf.format(Double.parseDouble(rset.getString(1))/38900));
					if(rset.getString(1)!=null && !(rset.getString(1).equals("")))
					{
						sup_tot_comit+=Double.parseDouble(rset.getString(1));
						sup_tot_comit_mmscm+=(Double.parseDouble(rset.getString(1))/38900);
					}
					else
					{
						sup_tot_comit+=0;
						sup_tot_comit_mmscm+=0;
					}
				}
				else
				{
					LOA_SUPPLIEDMBTU.add("0.00");
					LOA_SUPPLIEDMMSCM.add("0.00");
					sup_tot_comit+=0;
					sup_tot_comit_mmscm+=0;
				}				
				LOA_BALANCEMMBTU.add(nf.format(Double.parseDouble(""+LOA_BOOKMMBTU.elementAt(i))- Double.parseDouble(""+LOA_SUPPLIEDMBTU.elementAt(i))));
				bal_tot_comit+=Double.parseDouble(""+LOA_BOOKMMBTU.elementAt(i))- Double.parseDouble(""+LOA_SUPPLIEDMBTU.elementAt(i));
				
				LOA_BALANCEMMSCM.add(nf.format(Double.parseDouble(""+LOA_BOOKMMSCM.elementAt(i))- Double.parseDouble(""+LOA_SUPPLIEDMMSCM.elementAt(i))));
				bal_tot_comit_mmscm+=Double.parseDouble(""+LOA_BOOKMMSCM.elementAt(i))- Double.parseDouble(""+LOA_SUPPLIEDMMSCM.elementAt(i));
		   }
		   loa_sup_tot_comitment=nf.format(sup_tot_comit);
		   loa_bal_tot_comitment=nf.format(bal_tot_comit);
		   
		   loa_sup_tot_comitment_mmscm=nf.format(sup_tot_comit_mmscm);
		   loa_bal_tot_comitment_mmscm=nf.format(bal_tot_comit_mmscm);
			
			//--------Logic for Re-Gas based Summary Contract based Agreement	
			tot_comit=0.0;
			bal_tot_comit=0.0;
			sup_tot_comit=0.0;
			//MD20110917
			tot_comit_mmscm=0.0;
			sup_tot_comit_mmscm=0.0;
			bal_tot_comit_mmscm=0.0;
			
			FRM_DT.clear();
			TO_DT.clear();
			/*queryString = "select TO_CHAR(A.CONTRACT_START_DT,'DD/MM/YYYY'),TO_CHAR(A.CONTRACT_END_DT,'DD/MM/YYYY'),A.RE_GAS_NO,A.RE_GAS_REV_NO,A.CUSTOMER_CD,A.CARGO_SEQ_NO,A.QTY_TO_BE_SUPPLY,A.RE_GAS_TARIF " +
	 					  "from FMS7_RE_GAS_CARGO_DTL A where (A.CONTRACT_START_DT between to_date('"+from_date+"','dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy')"+ 
	 					  "OR A.CONTRACT_END_DT between to_date('"+from_date+"','dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy')) AND " +
	 		              "A.RE_GAS_REV_NO=(SELECT MAX(B.RE_GAS_REV_NO) FROM FMS7_RE_GAS_CARGO_DTL B where A.RE_GAS_NO=B.RE_GAS_NO AND A.RE_GAS_REV_NO=B.RE_GAS_REV_NO AND " +
	 		              "A.CUSTOMER_CD=B.CUSTOMER_CD AND A.CARGO_SEQ_NO=B.CARGO_SEQ_NO) order by A.CONTRACT_START_DT";*/
			
			//Updated By Milan 20110921
			queryString = "select TO_CHAR(A.CONTRACT_START_DT,'DD/MM/YYYY'),TO_CHAR(A.CONTRACT_END_DT,'DD/MM/YYYY'),A.RE_GAS_NO,A.RE_GAS_REV_NO,A.CUSTOMER_CD,A.CARGO_SEQ_NO,A.QTY_TO_BE_SUPPLY,A.RE_GAS_TARIF " +
			  "from FMS7_RE_GAS_CARGO_DTL A, SEC_EMP_CUSTOMER_ALLOC_MST B where "+ 
			  "(A.CONTRACT_START_DT <= to_date('"+to_date+"','dd/mm/yyyy')"+"AND A.CONTRACT_END_DT >= to_date('"+from_date+"','dd/mm/yyyy')) AND " +
             "A.RE_GAS_REV_NO=(SELECT MAX(B.RE_GAS_REV_NO) FROM FMS7_RE_GAS_CARGO_DTL B where A.RE_GAS_NO=B.RE_GAS_NO AND A.RE_GAS_REV_NO=B.RE_GAS_REV_NO AND " +
             "A.CUSTOMER_CD=B.CUSTOMER_CD AND A.CARGO_SEQ_NO=B.CARGO_SEQ_NO) AND A.CUSTOMER_CD=B.CUSTOMER_CD "
             + "AND B.EMP_CD='"+Emp_cd+"' order by A.CONTRACT_START_DT";
			rset = stmt.executeQuery(queryString);
//			//System.out.println(queryString);
			while(rset.next())
			{				
				RE_GAS_NO.add(rset.getString(3)==null?"0":rset.getString(3));
				RE_GAS_REV_NO.add(rset.getString(4)==null?"0":rset.getString(4));
				RE_GAS_CUSTOMER_CD.add(rset.getString(5)==null?"0":rset.getString(5));									
				tmp_from_dt = rset.getString(1)==null?"":rset.getString(1);
				tmp_to_dt = rset.getString(2)==null?"":rset.getString(2);
				FRM_DT.add(rset.getString(1)==null?"":rset.getString(1));
				TO_DT.add(rset.getString(2)==null?"":rset.getString(2));
				temp_dt = tmp_from_dt+"   -   "+tmp_to_dt;
				RE_GAS_CARGO_ARRIVAL_DATE.add(temp_dt);											  
				RE_GAS_SEQ_NO.add(rset.getString(6)==null?"0":rset.getString(6));				  	
				RE_GAS_BOOKMMBTU.add(rset.getString(7)==null?"0.00":nf.format(Double.parseDouble(rset.getString(7))));	
			  	tot_comit += Double.parseDouble(rset.getString(7)==null?"0":nf.format(Double.parseDouble(rset.getString(7))));
			  	re_gas_tot_commit=""+ nf.format(tot_comit) ;
			  	//MD20110917
			  	RE_GAS_BOOKMMSCM.add(rset.getString(7)==null?"0.00":nf.format(Double.parseDouble(rset.getString(7))/38900));	
			  	tot_comit_mmscm += Double.parseDouble(rset.getString(7)==null?"0":nf.format(Double.parseDouble(rset.getString(7))/38900));
			  	re_gas_tot_commit_mmscm=""+ nf.format(tot_comit_mmscm) ;
			  	
			  	REGAS_RATE.add(nf2.format(Double.parseDouble(rset.getString(8)==null?"0":rset.getString(8))));
			}			
			
			for(int i=0;i<RE_GAS_CUSTOMER_CD.size();i++)
			{
				queryString ="select CUSTOMER_ABBR,CUSTOMER_NAME,CUSTOMER_CD from FMS7_CUSTOMER_MST where CUSTOMER_CD="+RE_GAS_CUSTOMER_CD.elementAt(i)+" ";
				rset = stmt.executeQuery(queryString);
//				//System.out.println("RE_GAS_CUSTOMER_CD = "+queryString);
				if(rset.next())
				{
					RE_GAS_CUSTOMER_NM.add(rset.getString(2)==null?"":rset.getString(2));
					RE_GAS_CUSTOMER_ABBR.add(rset.getString(1)==null?"":rset.getString(1));
				}
				else
				{
					RE_GAS_CUSTOMER_NM.add("");
					RE_GAS_CUSTOMER_ABBR.add("");	
				}	
				
				queryString = "SELECT TO_CHAR(A.SIGNING_DT,'DD/MM/YYYY') FROM FMS7_RE_GAS_MST A " +
							  "WHERE A.RE_GAS_NO='"+RE_GAS_NO.elementAt(i)+"' AND " +
							  "A.REV_NO='"+RE_GAS_REV_NO.elementAt(i)+"' AND " +
							  "A.CUSTOMER_CD='"+RE_GAS_CUSTOMER_CD.elementAt(i)+"' ";			 
//				//System.out.println(queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
				  	REGAS_SIGNING_DT.add(rset.getString(1)==null?"":rset.getString(1));
				}
				else
				{
					REGAS_SIGNING_DT.add("");
				}
	
//				queryString ="select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL where CUSTOMER_CD="+RE_GAS_CUSTOMER_CD.elementAt(i)+" " +
//							 "AND SN_NO="+RE_GAS_SEQ_NO.elementAt(i)+" AND FGSA_NO="+RE_GAS_NO.elementAt(i)+" " +
//							 "AND GAS_DT between to_date('"+FRM_DT.elementAt(i)+"','dd/mm/yyyy')" +
//							 "AND to_date('"+TO_DT.elementAt(i)+"','dd/mm/yyyy') AND CONTRACT_TYPE='R'";
				queryString ="select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL where CUSTOMER_CD="+RE_GAS_CUSTOMER_CD.elementAt(i)+" " +
				 "AND SN_NO="+RE_GAS_SEQ_NO.elementAt(i)+" AND FGSA_NO="+RE_GAS_NO.elementAt(i)+" " +
				 "AND GAS_DT between to_date('"+FRM_DT.elementAt(i)+"','dd/mm/yyyy')" +
				 "AND to_date('"+to_date+"','dd/mm/yyyy') AND CONTRACT_TYPE='R'";

//				//System.out.println("RE_GAS_QTY_MMBTU = "+queryString);
				rset = stmt.executeQuery(queryString);				
				if(rset.next())
				{
					RE_GAS_SUPPLIEDMBTU.add(rset.getString(1)==null?"0.00":nf.format(Double.parseDouble(rset.getString(1))));
					//MD20110917
					RE_GAS_SUPPLIEDMMSCM.add(rset.getString(1)==null?"0.00":nf.format(Double.parseDouble(rset.getString(1))/38900));
					if(rset.getString(1)!=null && !(rset.getString(1).equals("")))
					{
						sup_tot_comit+=Double.parseDouble(rset.getString(1));
						sup_tot_comit_mmscm+=(Double.parseDouble(rset.getString(1))/38900);
					}
					else
					{
						sup_tot_comit+=0;
						sup_tot_comit_mmscm+=0;
					}
				}
				else
				{
					RE_GAS_SUPPLIEDMBTU.add("0.00");
					sup_tot_comit+=0;
					
					RE_GAS_SUPPLIEDMMSCM.add("0.00");
					sup_tot_comit_mmscm+=0;
				}				
				RE_GAS_BALANCEMMBTU.add(nf.format(Double.parseDouble(""+RE_GAS_BOOKMMBTU.elementAt(i))- Double.parseDouble(""+RE_GAS_SUPPLIEDMBTU.elementAt(i))));
				bal_tot_comit+=Double.parseDouble(""+RE_GAS_BOOKMMBTU.elementAt(i))- Double.parseDouble(""+RE_GAS_SUPPLIEDMBTU.elementAt(i));
				//MD20110917
				RE_GAS_BALANCEMMSCM.add(nf.format(Double.parseDouble(""+RE_GAS_BOOKMMSCM.elementAt(i))- Double.parseDouble(""+RE_GAS_SUPPLIEDMMSCM.elementAt(i))));
				bal_tot_comit_mmscm+=Double.parseDouble(""+RE_GAS_BOOKMMSCM.elementAt(i))- Double.parseDouble(""+RE_GAS_SUPPLIEDMMSCM.elementAt(i));
		   }
			re_gas_sup_tot_comitment=nf.format(sup_tot_comit);
			re_gas_bal_tot_comitment=nf.format(bal_tot_comit);
			//MD20110917
			re_gas_sup_tot_comitment_mmscm=nf.format(sup_tot_comit_mmscm);
			re_gas_bal_tot_comitment_mmscm=nf.format(bal_tot_comit_mmscm);
			
//			//System.out.println("re_gas_sup_tot_comitment_mmscm"+re_gas_sup_tot_comitment_mmscm);
//			//System.out.println("re_gas_bal_tot_comitment_mmscm"+re_gas_bal_tot_comitment_mmscm);
			
			//////**********ADDED FOR LTCORA AND CN
			//--------Logic for LTCORA based Summary Contract based Agreement	
			tot_comit=0.0;
			bal_tot_comit=0.0;
			sup_tot_comit=0.0;
			//MD20110917
			tot_comit_mmscm=0.0;
			sup_tot_comit_mmscm=0.0;
			bal_tot_comit_mmscm=0.0;
			
			
			
			FRM_DT.clear();
			TO_DT.clear();
			/*queryString = "select TO_CHAR(A.CONTRACT_START_DT,'DD/MM/YYYY'),TO_CHAR(A.CONTRACT_END_DT,'DD/MM/YYYY'),A.RE_GAS_NO,A.RE_GAS_REV_NO,A.CUSTOMER_CD,A.CARGO_SEQ_NO,A.QTY_TO_BE_SUPPLY,A.RE_GAS_TARIF " +
	 					  "from FMS7_RE_GAS_CARGO_DTL A where (A.CONTRACT_START_DT between to_date('"+from_date+"','dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy')"+ 
	 					  "OR A.CONTRACT_END_DT between to_date('"+from_date+"','dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy')) AND " +
	 		              "A.RE_GAS_REV_NO=(SELECT MAX(B.RE_GAS_REV_NO) FROM FMS7_RE_GAS_CARGO_DTL B where A.RE_GAS_NO=B.RE_GAS_NO AND A.RE_GAS_REV_NO=B.RE_GAS_REV_NO AND " +
	 		              "A.CUSTOMER_CD=B.CUSTOMER_CD AND A.CARGO_SEQ_NO=B.CARGO_SEQ_NO) order by A.CONTRACT_START_DT";*/
			
			
			/*queryString = "select TO_CHAR(A.CONTRACT_START_DT,'DD/MM/YYYY'),TO_CHAR(A.CONTRACT_END_DT,'DD/MM/YYYY'),A.mapping_id,A.mapping_id,A.mapping_id,A.CARGO_SEQ_NO,A.QTY_TO_BE_SUPPLY,A.REGAS_TARIF " +
			  "from FMS8_LNG_REGAS_CARGO_DTL A where "+ 
			  "(A.CONTRACT_START_DT <= to_date('"+to_date+"','dd/mm/yyyy')"+"AND A.CONTRACT_END_DT >= to_date('"+from_date+"','dd/mm/yyyy')) AND " +
             "A.RE_GAS_REV_NO=(SELECT MAX(B.RE_GAS_REV_NO) FROM FMS7_RE_GAS_CARGO_DTL B where A.RE_GAS_NO=B.RE_GAS_NO AND A.RE_GAS_REV_NO=B.RE_GAS_REV_NO AND " +
             "A.CUSTOMER_CD=B.CUSTOMER_CD AND A.CARGO_SEQ_NO=B.CARGO_SEQ_NO) order by A.CONTRACT_START_DT";
			*/
			/*queryString="select TO_CHAR(A.CONTRACT_START_DT,'DD/MM/YYYY'),TO_CHAR(A.CONTRACT_END_DT,'DD/MM/YYYY'), " +
						" A.mapping_id,A.mapping_id,A.mapping_id,A.CARGO_SEQ_NO,A.QTY_TO_BE_SUPPLY,A.REGAS_TARIF, C.REV_NO " +
						"from FMS8_LNG_REGAS_CARGO_DTL A, FMS8_LNG_REGAS_MST C where " +
						"(A.CONTRACT_START_DT <= to_date('"+to_date+"','dd/mm/yyyy') " +
						"AND A.CONTRACT_END_DT >= to_date('"+from_date+"','dd/mm/yyyy')) AND A.MAPPING_ID=C.MAPPING_ID " +
						"AND CN_NO='0' AND CN_REV_NO='0' AND  C.REV_NO=(SELECT MAX(B.REV_NO) " +
						"FROM FMS8_LNG_REGAS_MST B where C.AGREEMENT_NO=B.AGREEMENT_NO AND C.REV_NO=B.REV_NO " +
						"AND C.CUSTOMER_CD=B.CUSTOMER_CD  AND CN_NO='0' AND CN_REV_NO='0') " +
						"order by A.CONTRACT_START_DT";*/
			/////*** beLOW CODE IS CHANGED FOR PERIOD IN PLCAE OF LTCORA ***////
			queryString="select TO_CHAR(A.CONTRACT_START_DT,'DD/MM/YYYY'),TO_CHAR(A.CONTRACT_END_DT,'DD/MM/YYYY'), " +
			"A.mapping_id,A.mapping_id,A.mapping_id,A.CARGO_SEQ_NO,A.QTY_TO_BE_SUPPLY,A.REGAS_TARIF, " +
			"C.REV_NO from FMS8_LNG_REGAS_CARGO_DTL A, FMS8_LNG_REGAS_MST C, SEC_EMP_CUSTOMER_ALLOC_MST E where " +
			"(A.CONTRACT_START_DT <= to_date('"+to_date+"','dd/mm/yyyy') AND " +
			"A.CONTRACT_END_DT >= to_date('"+from_date+"','dd/mm/yyyy')) AND A.MAPPING_ID=C.MAPPING_ID " +
			"AND C.CN_NO!='0' AND CN_NO >'9999' AND C.CN_REV_NO=(SELECT MAX(B.CN_REV_NO) FROM FMS8_LNG_REGAS_MST B " +
			"where C.agreement_no=B.agreement_no AND C.customer_cd=B.customer_cd AND B.CN_NO!=0 AND CN_NO >'9999' AND " +
			"B.CN_NO=C.CN_NO AND c.rev_no=B.rev_no AND B.REV_NO=(SELECT MAX(D.REV_NO) FROM FMS8_LNG_REGAS_MST D WHERE " +
			"D.agreement_no=B.agreement_no AND B.customer_cd=D.customer_cd AND D.CN_NO=0 " +
			"AND D.agreement_no=C.agreement_no AND D.customer_cd=C.customer_cd )) AND C.CUSTOMER_CD=E.CUSTOMER_CD "
			+ "AND E.EMP_CD='"+Emp_cd+"' " +
			"order by A.CONTRACT_START_DT";
			
			rset = stmt.executeQuery(queryString);
//			//System.out.println(queryString);
			while(rset.next())
			{				
				
				String map_id=rset.getString(3)==null?"":rset.getString(3);
				String tempmap_id[]=map_id.split("-");
				
				String temp_regas_no=tempmap_id[3];
				String temp_regas_rev_no=tempmap_id[4];
				String temp_cust_cd=tempmap_id[0];
				
				LTCORA_MAPPING_ID.add(map_id);
				LTCORA_NO.add(temp_regas_no);
				LTCORA_REV_NO.add(temp_regas_rev_no);
				LTCORA_CUSTOMER_CD.add(temp_cust_cd);									
				tmp_from_dt = rset.getString(1)==null?"":rset.getString(1);
				tmp_to_dt = rset.getString(2)==null?"":rset.getString(2);
				FRM_DT.add(rset.getString(1)==null?"":rset.getString(1));
				TO_DT.add(rset.getString(2)==null?"":rset.getString(2));
				temp_dt = tmp_from_dt+"   -   "+tmp_to_dt;
				LTCORA_CARGO_ARRIVAL_DATE.add(temp_dt);											  
				LTCORA_SEQ_NO.add(rset.getString(6)==null?"0":rset.getString(6));				  	
				LTCORA_BOOKMMBTU.add(rset.getString(7)==null?"0.00":nf.format(Double.parseDouble(rset.getString(7))));	
			  	tot_comit += Double.parseDouble(rset.getString(7)==null?"0":nf.format(Double.parseDouble(rset.getString(7))));
			  	LTCORA_tot_commit=""+ nf.format(tot_comit) ;
			  	//MD20110917
			  	LTCORA_BOOKMMSCM.add(rset.getString(7)==null?"0.00":nf.format(Double.parseDouble(rset.getString(7))/38900));	
			  	tot_comit_mmscm += Double.parseDouble(rset.getString(7)==null?"0":nf.format(Double.parseDouble(rset.getString(7))/38900));
			  	LTCORA_tot_commit_mmscm=""+ nf.format(tot_comit_mmscm) ;
			  	
			  	LTCORA_RATE.add(nf2.format(Double.parseDouble(rset.getString(8)==null?"0":rset.getString(8))));
			}			
			
			for(int i=0;i<LTCORA_CUSTOMER_CD.size();i++)
			{
				queryString ="select CUSTOMER_ABBR,CUSTOMER_NAME,CUSTOMER_CD from FMS7_CUSTOMER_MST where CUSTOMER_CD="+LTCORA_CUSTOMER_CD.elementAt(i)+" ";
				rset = stmt.executeQuery(queryString);
//				//System.out.println("LTCORA_CUSTOMER_CD = "+queryString);
				if(rset.next())
				{
					LTCORA_CUSTOMER_NM.add(rset.getString(2)==null?"":rset.getString(2));
					LTCORA_CUSTOMER_ABBR.add(rset.getString(1)==null?"":rset.getString(1));
				}
				else
				{
					LTCORA_CUSTOMER_NM.add("");
					LTCORA_CUSTOMER_ABBR.add("");	
				}	
				
				/*queryString = "SELECT TO_CHAR(A.SIGNING_DT,'DD/MM/YYYY') FROM FMS8_LNG_REGAS_MST A " +
							  "WHERE A.AGREEMENT_NO='"+LTCORA_NO.elementAt(i)+"' AND " +
							  "A.REV_NO='"+LTCORA_REV_NO.elementAt(i)+"' AND " +
							  "A.CUSTOMER_CD='"+LTCORA_CUSTOMER_CD.elementAt(i)+"' AND CN_NO='0' AND CN_REV_NO='0'";*/
				
				queryString = "SELECT TO_CHAR(A.SIGNING_DT,'DD/MM/YYYY') FROM FMS8_LNG_REGAS_MST A " +
				  "WHERE A.MAPPING_ID='"+LTCORA_MAPPING_ID.elementAt(i)+"' ";
//				//System.out.println(queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					LTCORA_SIGNING_DT.add(rset.getString(1)==null?"":rset.getString(1));
				}
				else
				{
					LTCORA_SIGNING_DT.add("");
				}
	
//				queryString ="select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL where CUSTOMER_CD="+RE_GAS_CUSTOMER_CD.elementAt(i)+" " +
//							 "AND SN_NO="+RE_GAS_SEQ_NO.elementAt(i)+" AND FGSA_NO="+RE_GAS_NO.elementAt(i)+" " +
//							 "AND GAS_DT between to_date('"+FRM_DT.elementAt(i)+"','dd/mm/yyyy')" +
//							 "AND to_date('"+TO_DT.elementAt(i)+"','dd/mm/yyyy') AND CONTRACT_TYPE='R'";
				queryString ="select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL where CUSTOMER_CD="+LTCORA_CUSTOMER_CD.elementAt(i)+" " +
				 "AND SN_NO="+LTCORA_SEQ_NO.elementAt(i)+" AND FGSA_NO="+LTCORA_NO.elementAt(i)+" " +
				 "AND GAS_DT between to_date('"+FRM_DT.elementAt(i)+"','dd/mm/yyyy')" +
				 "AND to_date('"+to_date+"','dd/mm/yyyy') AND CONTRACT_TYPE='C' AND MAPPING_ID='"+LTCORA_MAPPING_ID.elementAt(i)+"'";

//				//System.out.println("LTCORA_QTY_MMBTU = "+queryString);
				rset = stmt.executeQuery(queryString);				
				if(rset.next())
				{
					LTCORA_SUPPLIEDMBTU.add(rset.getString(1)==null?"0.00":nf.format(Double.parseDouble(rset.getString(1))));
					//MD20110917
					LTCORA_SUPPLIEDMMSCM.add(rset.getString(1)==null?"0.00":nf.format(Double.parseDouble(rset.getString(1))/38900));
					if(rset.getString(1)!=null && !(rset.getString(1).equals("")))
					{
						sup_tot_comit+=Double.parseDouble(rset.getString(1));
						sup_tot_comit_mmscm+=(Double.parseDouble(rset.getString(1))/38900);
					}
					else
					{
						sup_tot_comit+=0;
						sup_tot_comit_mmscm+=0;
					}
				}
				else
				{
					LTCORA_SUPPLIEDMBTU.add("0.00");
					sup_tot_comit+=0;
					
					LTCORA_SUPPLIEDMMSCM.add("0.00");
					sup_tot_comit_mmscm+=0;
				}				
				LTCORA_BALANCEMMBTU.add(nf.format(Double.parseDouble(""+LTCORA_BOOKMMBTU.elementAt(i))- Double.parseDouble(""+LTCORA_SUPPLIEDMBTU.elementAt(i))));
				bal_tot_comit+=Double.parseDouble(""+LTCORA_BOOKMMBTU.elementAt(i))- Double.parseDouble(""+LTCORA_SUPPLIEDMBTU.elementAt(i));
				//MD20110917
				LTCORA_BALANCEMMSCM.add(nf.format(Double.parseDouble(""+LTCORA_BOOKMMSCM.elementAt(i))- Double.parseDouble(""+LTCORA_SUPPLIEDMMSCM.elementAt(i))));
				bal_tot_comit_mmscm+=Double.parseDouble(""+LTCORA_BOOKMMSCM.elementAt(i))- Double.parseDouble(""+LTCORA_SUPPLIEDMMSCM.elementAt(i));
		   }
			LTCORA_sup_tot_comitment=nf.format(sup_tot_comit);
			LTCORA_bal_tot_comitment=nf.format(bal_tot_comit);
			//MD20110917
			LTCORA_sup_tot_comitment_mmscm=nf.format(sup_tot_comit_mmscm);
			LTCORA_bal_tot_comitment_mmscm=nf.format(bal_tot_comit_mmscm);
			
			
			//--------Logic for CN based Summary Contract based Agreement	
			tot_comit=0.0;
			bal_tot_comit=0.0;
			sup_tot_comit=0.0;
			//MD20110917
			tot_comit_mmscm=0.0;
			sup_tot_comit_mmscm=0.0;
			bal_tot_comit_mmscm=0.0;
			
			
			
			FRM_DT.clear();
			TO_DT.clear();
			/*queryString = "select TO_CHAR(A.CONTRACT_START_DT,'DD/MM/YYYY'),TO_CHAR(A.CONTRACT_END_DT,'DD/MM/YYYY'),A.RE_GAS_NO,A.RE_GAS_REV_NO,A.CUSTOMER_CD,A.CARGO_SEQ_NO,A.QTY_TO_BE_SUPPLY,A.RE_GAS_TARIF " +
	 					  "from FMS7_RE_GAS_CARGO_DTL A where (A.CONTRACT_START_DT between to_date('"+from_date+"','dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy')"+ 
	 					  "OR A.CONTRACT_END_DT between to_date('"+from_date+"','dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy')) AND " +
	 		              "A.RE_GAS_REV_NO=(SELECT MAX(B.RE_GAS_REV_NO) FROM FMS7_RE_GAS_CARGO_DTL B where A.RE_GAS_NO=B.RE_GAS_NO AND A.RE_GAS_REV_NO=B.RE_GAS_REV_NO AND " +
	 		              "A.CUSTOMER_CD=B.CUSTOMER_CD AND A.CARGO_SEQ_NO=B.CARGO_SEQ_NO) order by A.CONTRACT_START_DT";*/
			
			queryString="select TO_CHAR(A.CONTRACT_START_DT,'DD/MM/YYYY'),TO_CHAR(A.CONTRACT_END_DT,'DD/MM/YYYY'), " +
					"A.mapping_id,A.mapping_id,A.mapping_id,A.CARGO_SEQ_NO,A.QTY_TO_BE_SUPPLY,A.REGAS_TARIF, " +
					"C.REV_NO from FMS8_LNG_REGAS_CARGO_DTL A, FMS8_LNG_REGAS_MST C, SEC_EMP_CUSTOMER_ALLOC_MST E where " +
					"(A.CONTRACT_START_DT <= to_date('"+to_date+"','dd/mm/yyyy') AND " +
					"A.CONTRACT_END_DT >= to_date('"+from_date+"','dd/mm/yyyy')) AND A.MAPPING_ID=C.MAPPING_ID " +
					"AND C.CN_NO!='0' AND CN_NO <'9999' AND C.CN_REV_NO=(SELECT MAX(B.CN_REV_NO) FROM FMS8_LNG_REGAS_MST B " +
					"where C.agreement_no=B.agreement_no AND C.customer_cd=B.customer_cd AND B.CN_NO!=0 AND CN_NO <'9999' AND " +
					"B.CN_NO=C.CN_NO AND c.rev_no=B.rev_no AND B.REV_NO=(SELECT MAX(D.REV_NO) FROM FMS8_LNG_REGAS_MST D WHERE " +
					"D.agreement_no=B.agreement_no AND B.customer_cd=D.customer_cd AND D.CN_NO=0 " +
					"AND D.agreement_no=C.agreement_no AND D.customer_cd=C.customer_cd )) AND C.CUSTOMER_CD=E.CUSTOMER_CD AND "
					+ "E.EMP_CD='"+Emp_cd+"' " +
					"order by A.CONTRACT_START_DT";
//			//System.out.println("CNNNNNNNNNN......"+queryString);
			rset = stmt.executeQuery(queryString);
			//System.out.println(queryString);
			while(rset.next())
			{				
				
				String map_id=rset.getString(3)==null?"":rset.getString(3);
				String tempmap_id[]=map_id.split("-");
				
				String temp_regas_no=tempmap_id[3];
				String temp_regas_rev_no=tempmap_id[4];
				String temp_cust_cd=tempmap_id[0];
				
				CN_MAPPING_ID.add(map_id);
				CN_NO.add(temp_regas_no);
				CN_REV_NO.add(temp_regas_rev_no);
				CN_CUSTOMER_CD.add(temp_cust_cd);									
				tmp_from_dt = rset.getString(1)==null?"":rset.getString(1);
				tmp_to_dt = rset.getString(2)==null?"":rset.getString(2);
				FRM_DT.add(rset.getString(1)==null?"":rset.getString(1));
				TO_DT.add(rset.getString(2)==null?"":rset.getString(2));
				temp_dt = tmp_from_dt+"   -   "+tmp_to_dt;
				CN_CARGO_ARRIVAL_DATE.add(temp_dt);											  
				CN_SEQ_NO.add(rset.getString(6)==null?"0":rset.getString(6));				  	
				CN_BOOKMMBTU.add(rset.getString(7)==null?"0.00":nf.format(Double.parseDouble(rset.getString(7))));	
			  	tot_comit += Double.parseDouble(rset.getString(7)==null?"0":nf.format(Double.parseDouble(rset.getString(7))));
			  	CN_tot_commit=""+ nf.format(tot_comit) ;
			  	//MD20110917
			  	CN_BOOKMMSCM.add(rset.getString(7)==null?"0.00":nf.format(Double.parseDouble(rset.getString(7))/38900));	
			  	tot_comit_mmscm += Double.parseDouble(rset.getString(7)==null?"0":nf.format(Double.parseDouble(rset.getString(7))/38900));
			  	CN_tot_commit_mmscm=""+ nf.format(tot_comit_mmscm) ;
			  	
			  	CN_RATE.add(nf2.format(Double.parseDouble(rset.getString(8)==null?"0":rset.getString(8))));
			}			
			
			for(int i=0;i<CN_CUSTOMER_CD.size();i++)
			{
				queryString ="select CUSTOMER_ABBR,CUSTOMER_NAME,CUSTOMER_CD from FMS7_CUSTOMER_MST where CUSTOMER_CD="+CN_CUSTOMER_CD.elementAt(i)+" ";
				rset = stmt.executeQuery(queryString);
//				//System.out.println("CN_CUSTOMER_CD = "+queryString);
				if(rset.next())
				{
					CN_CUSTOMER_NM.add(rset.getString(2)==null?"":rset.getString(2));
					CN_CUSTOMER_ABBR.add(rset.getString(1)==null?"":rset.getString(1));
				}
				else
				{
					CN_CUSTOMER_NM.add("");
					CN_CUSTOMER_ABBR.add("");	
				}	
				
				queryString = "SELECT TO_CHAR(A.SIGNING_DT,'DD/MM/YYYY') FROM FMS8_LNG_REGAS_MST A " +
							  "WHERE A.MAPPING_ID='"+CN_MAPPING_ID.elementAt(i)+"' ";
							 			 
//				//System.out.println(queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					CN_SIGNING_DT.add(rset.getString(1)==null?"":rset.getString(1));
				}
				else
				{
					CN_SIGNING_DT.add("");
				}
	
//				queryString ="select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL where CUSTOMER_CD="+RE_GAS_CUSTOMER_CD.elementAt(i)+" " +
//							 "AND SN_NO="+RE_GAS_SEQ_NO.elementAt(i)+" AND FGSA_NO="+RE_GAS_NO.elementAt(i)+" " +
//							 "AND GAS_DT between to_date('"+FRM_DT.elementAt(i)+"','dd/mm/yyyy')" +
//							 "AND to_date('"+TO_DT.elementAt(i)+"','dd/mm/yyyy') AND CONTRACT_TYPE='R'";
				queryString ="select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL where CUSTOMER_CD="+CN_CUSTOMER_CD.elementAt(i)+" " +
				 "AND SN_NO="+CN_SEQ_NO.elementAt(i)+" AND FGSA_NO="+CN_NO.elementAt(i)+" " +
				 "AND GAS_DT between to_date('"+FRM_DT.elementAt(i)+"','dd/mm/yyyy')" +
				 "AND to_date('"+to_date+"','dd/mm/yyyy') AND CONTRACT_TYPE='C' AND MAPPING_ID='"+CN_MAPPING_ID.elementAt(i)+"'";

//				//System.out.println("CN_QTY_MMBTU = "+queryString);
				rset = stmt.executeQuery(queryString);				
				if(rset.next())
				{
					CN_SUPPLIEDMBTU.add(rset.getString(1)==null?"0.00":nf.format(Double.parseDouble(rset.getString(1))));
					//MD20110917
					CN_SUPPLIEDMMSCM.add(rset.getString(1)==null?"0.00":nf.format(Double.parseDouble(rset.getString(1))/38900));
					if(rset.getString(1)!=null && !(rset.getString(1).equals("")))
					{
						sup_tot_comit+=Double.parseDouble(rset.getString(1));
						sup_tot_comit_mmscm+=(Double.parseDouble(rset.getString(1))/38900);
					}
					else
					{
						sup_tot_comit+=0;
						sup_tot_comit_mmscm+=0;
					}
				}
				else
				{
					CN_SUPPLIEDMBTU.add("0.00");
					sup_tot_comit+=0;
					
					CN_SUPPLIEDMMSCM.add("0.00");
					sup_tot_comit_mmscm+=0;
				}				
				CN_BALANCEMMBTU.add(nf.format(Double.parseDouble(""+CN_BOOKMMBTU.elementAt(i))- Double.parseDouble(""+CN_SUPPLIEDMBTU.elementAt(i))));
				bal_tot_comit+=Double.parseDouble(""+CN_BOOKMMBTU.elementAt(i))- Double.parseDouble(""+CN_SUPPLIEDMBTU.elementAt(i));
				//MD20110917
				CN_BALANCEMMSCM.add(nf.format(Double.parseDouble(""+CN_BOOKMMSCM.elementAt(i))- Double.parseDouble(""+CN_SUPPLIEDMMSCM.elementAt(i))));
				bal_tot_comit_mmscm+=Double.parseDouble(""+CN_BOOKMMSCM.elementAt(i))- Double.parseDouble(""+CN_SUPPLIEDMMSCM.elementAt(i));
		   }
			CN_sup_tot_comitment=nf.format(sup_tot_comit);
			CN_bal_tot_comitment=nf.format(bal_tot_comit);
			//MD20110917
			CN_sup_tot_comitment_mmscm=nf.format(sup_tot_comit_mmscm);
			CN_bal_tot_comitment_mmscm=nf.format(bal_tot_comit_mmscm);
			//END OF LTCORA AND CN
//			//System.out.println("CN_sup_tot_comitment_mmscm"+CN_sup_tot_comitment_mmscm);
//			//System.out.println("CN_bal_tot_comitment_mmscm"+CN_bal_tot_comitment_mmscm);
			
		}		
		catch(Exception e)
		{
			  //System.out.println("Exception in Databean_Contract_MasterReport--->fetchEligibleCargoList()-->"+e);
			  e.printStackTrace();
		}
	}
	
	
	
	public void fetchEligibleCargoList()
	{
		try
		{			
			String tmp_from_dt = " ";
			String temp_dt="";
			String tmp_to_dt = " ";			
			double tot_comit=0.0;
			double sup_tot_comit=0.0;
			double bal_tot_comit=0.0;
			//Introduce By Milan 20110916 FOR MMSCM
			double tot_comit_mmscm=0.0;
			double sup_tot_comit_mmscm=0.0;
			double bal_tot_comit_mmscm=0.0;
			Vector FRM_DT=new Vector();
			Vector TO_DT=new Vector();						
			Vector RE_GAS_REV_NO=new Vector();
			Vector LTCORA_REV_NO=new Vector();//ADDED FOR LTCORA AND CN
			Vector LTCORA_MAPPING_ID=new Vector();//ADDED FOR LTCORA AND CN
			Vector CN_REV_NO=new Vector();//ADDED FOR LTCORA AND CN
			Vector CN_MAPPING_ID=new Vector();//ADDED FOR LTCORA AND CN
			Vector MAPPING_ID=new Vector();//MAPP_ID for All
////////////////////SB20210209:STORAGE//////////////////////////////////
	String StorageDate="";
	queryString = "SELECT TO_CHAR(TO_DATE('"+to_date+"','DD/MM/YYYY')+1,'DD/MM/YYYY') FROM DUAL";
	//	System.out.println("Get DAY Name: "+queryString);
		rset = stmt.executeQuery(queryString);
		if(rset.next())
		{
			StorageDate = rset.getString(1)==null?"":rset.getString(1); //SB20201128;
		}
	////////////////////^^^SB20210209:STORAGE/////////////////////////////////////////////	
			
			/*queryString ="select TO_CHAR(A.START_DT,'DD/MM/YYYY'),TO_CHAR(A.END_DT,'DD/MM/YYYY'),A.SN_NO,A.SN_REV_NO,A.CUSTOMER_CD,A.FGSA_NO,A.FGSA_REV_NO,A.TCQ, " +
			 		"A.SN_REF_NO,to_char(A.SIGNING_DT,'dd/mm/yyyy'),A.RATE from FMS7_SN_MST A where (A.START_DT between to_date('"+from_date+"','dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy')"+ 
			 		"OR A.END_DT between to_date('"+from_date+"','dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy')) AND " +
			 		"A.SN_REV_NO=(SELECT MAX(B.SN_REV_NO) FROM FMS7_SN_MST B where A.SN_NO=B.SN_NO AND A.FGSA_NO=B.FGSA_NO AND " +
			 		"A.CUSTOMER_CD=B.CUSTOMER_CD AND A.FGSA_REV_NO=B.FGSA_REV_NO) order by A.START_DT";*/
			
			//Updated By Milan 20110921
			queryString ="select TO_CHAR(A.START_DT,'DD/MM/YYYY'),TO_CHAR(A.END_DT,'DD/MM/YYYY'),A.SN_NO,A.SN_REV_NO,A.CUSTOMER_CD,A.FGSA_NO,A.FGSA_REV_NO,A.TCQ, " +
	 		"A.SN_REF_NO,to_char(A.SIGNING_DT,'dd/mm/yyyy'),A.RATE,FCC_BY,TO_CHAR(FCC_DATE,'DD/mm/yyyy'),A.EMP_CD,TO_CHAR(A.ENT_DT,'dd/mm/yyyy') "
	 		+",CUSTOMER_ABBR,CUSTOMER_NAME " //SB20210528
	 		+ "from FMS7_SN_MST A, FMS7_CUSTOMER_MST C where "+  //SB20210528
	 		"A.CUSTOMER_CD=C.CUSTOMER_CD AND "+ //SB20210528
	 		"C.EFF_DT = (SELECT MAX(EFF_DT) FROM FMS7_CUSTOMER_MST WHERE CUSTOMER_CD = A.CUSTOMER_CD) AND "+ //HARSH20210602 GETTING DUPLICATE ENTRY 
	 		///SB20210522 		"(A.SIGNING_DT <= to_date('"+to_date+"','dd/mm/yyyy')"+"AND A.END_DT >= to_date('"+to_date+"','dd/mm/yyyy')) AND " +
			" A.END_DT >= TO_DATE('01/01/2021','DD/MM/YYYY') AND " + ///SB20210522 : ad discussed on 21stMay21 by Shiladitya to Include all Deals irrespective of Expairy on Report Date
	 		"A.SN_REV_NO=(SELECT MAX(B.SN_REV_NO) FROM FMS7_SN_MST B where A.SN_NO=B.SN_NO AND A.FGSA_NO=B.FGSA_NO AND " +
	 		"A.CUSTOMER_CD=B.CUSTOMER_CD AND A.FGSA_REV_NO=B.FGSA_REV_NO) order by CUSTOMER_NAME, A.START_DT";
			
			//(START_DT <= to_date('"+to_date+"','dd/mm/yyyy')"+"AND END_DT >= to_date('"+from_date+"','dd/mm/yyyy'))
		//	System.out.println("MRver2.0 "+queryString);
			rset = stmt.executeQuery(queryString);
			////System.out.println("FOr SN : "+to_date+" "+from_date);
			while(rset.next())
			{				
				////System.out.println("FOr SN : "+rset.getString(1)+" "+rset.getString(2));
				SN_NO.add(rset.getString(3)==null?"0":rset.getString(3));
				SN_NO_HP.add(rset.getString(3)==null?"0":rset.getString(3));//HARSH20210728
				CUSTOMER_CD.add(rset.getString(5)==null?"0":rset.getString(5));
				CUSTOMER_CD_HP.add(rset.getString(5)==null?"0":rset.getString(5));//HARSH20210728									
				tmp_from_dt = rset.getString(1)==null?"":rset.getString(1);
				tmp_to_dt = rset.getString(2)==null?"":rset.getString(2);
				FRM_DT.add(rset.getString(1)==null?"":rset.getString(1));
				TO_DT.add(rset.getString(2)==null?"":rset.getString(2));
				temp_dt = tmp_from_dt+"   -   "+tmp_to_dt;
				CARGO_ARRIVAL_DATE.add(temp_dt);				
				SN_REV_NO.add(rset.getString(4)==null?"0":rset.getString(4));			  	
				SN_REV_NO_HP.add(rset.getString(4)==null?"0":rset.getString(4));//HARSH20210728
			  	FGSA_NO.add("S"+rset.getString(6)==null?"0":rset.getString(6));	
			  	FGSA_NO_HP.add("S"+rset.getString(6)==null?"0":rset.getString(6));//HARSH20210728
			  	FGSA_REV_NO.add(rset.getString(7)==null?"0":rset.getString(7));
			  	FGSA_REV_NO_HP.add(rset.getString(7)==null?"0":rset.getString(7));//HARSH20210728	
			  	BOOKMMBTU.add(rset.getString(8)==null?"0.00":nf.format(Double.parseDouble(rset.getString(8))));
			  	BOOKMMSCM.add(rset.getString(8)==null?"0.00":nf.format(Double.parseDouble(rset.getString(8))/38900));//MD20110916
			  	tot_comit += Double.parseDouble(rset.getString(8)==null?"0":nf.format(Double.parseDouble(rset.getString(8))));
			  	
			  	tot_comit_mmscm=(tot_comit/38900);//MD20110916
			  	tot_commitment_mmscm=nf.format(tot_comit_mmscm);
			  	////System.out.println("BOOKEDMMSCM : "+BOOKMMSCM+" TOTAL : "+tot_comit_mmscm);
			  	
			  	tot_commitment=nf.format(tot_comit);
			  	SN_REF_NO.add(rset.getString(9)==null?"":rset.getString(9));	
			  	SIGNING_DT.add(rset.getString(10)==null?"0":rset.getString(10));
			  	DEAL_SIGN_DT_HP.add(rset.getString(10)==null?"0":rset.getString(10)); //HARSH20210728
			  	RATE.add(nf2.format(Double.parseDouble(rset.getString(11)==null?"0":rset.getString(11))));
			  	String TEMP_apr_by=rset.getString(12)==null?"0":rset.getString(12);
			  	sn_apr_dt.add(rset.getString(13)==null?"-":rset.getString(13));
			  	String temp_ent_by=rset.getString(14)==null?"0":rset.getString(14);
			  	sn_ent_dt.add(rset.getString(15)==null?"-":rset.getString(15));
			  	VContType.add("S"); //SB20201108
			  	VContType_HP.add("S"); //HARSH20210728
			  	CUSTOMER_ABBR.add(rset.getString(16)==null?"":rset.getString(16));	//SB20210528
			  	CUSTOMER_NM.add(rset.getString(17)==null?"":rset.getString(17)); //SB20210528
				
			  	
			  	
			  	String queryString_nm ="SELECT EMP_NM FROM HR_EMP_MST WHERE EMP_CD='"+TEMP_apr_by+"'";
			  	rset1=stmt1.executeQuery(queryString_nm);
			  	if(rset1.next()){
			  		sn_apr_by.add(rset1.getString(1)==null?"-":rset1.getString(1));
			  	}else{
			  		sn_apr_by.add("-");
			  	}
			  	VCont_Start_Dt.add(rset.getString(1)==null?"":rset.getString(1));
			  	VCont_End_Dt.add(rset.getString(2)==null?"":rset.getString(2));
			  	
			  	queryString_nm ="SELECT EMP_NM FROM HR_EMP_MST WHERE EMP_CD='"+temp_ent_by+"'";
			  	rset1=stmt1.executeQuery(queryString_nm);
			  	if(rset1.next()){
			  		sn_ent_by.add(rset1.getString(1)==null?"-":rset1.getString(1));
			  	}else{
			  		sn_ent_by.add("-");
			  	}
			  	MAPPING_ID.add("S"+rset.getString(6)+"-"+rset.getString(7)+"-"+rset.getString(3)+"-"+rset.getString(4));
			}	
//*SB20201108////////////////////////////LOA LIst of Contracts/////////////////////////////////////////////////////////////////
		queryString =   "select TO_CHAR(A.START_DT,'DD/MM/YYYY'),TO_CHAR(A.END_DT,'DD/MM/YYYY'),A.LOA_NO,A.LOA_REV_NO,A.CUSTOMER_CD,A.TENDER_NO,A.TCQ, " +
				"A.LOA_REF_NO,to_char(A.SIGNING_DT,'dd/mm/yyyy'),A.RATE,FCC_BY,TO_CHAR(FCC_DATE,'DD/mm/yyyy'),A.EMP_CD,TO_CHAR(A.ENT_DT,'dd/mm/yyyy'), DCQ, ADV_AMT, TRANSPORTATION_CHARGE  "
				+",CUSTOMER_ABBR,CUSTOMER_NAME " //SB20210528
				+ " from FMS7_LOA_MST A, FMS7_CUSTOMER_MST C  where "+ //SB20210528
				"A.CUSTOMER_CD=C.CUSTOMER_CD AND "+ //SB20210528
				"C.EFF_DT = (SELECT MAX(EFF_DT) FROM FMS7_CUSTOMER_MST WHERE CUSTOMER_CD = A.CUSTOMER_CD) AND "+ //HARSH20210602 GETTING DUPLICATE ENTRY
		//SB20201013		"(A.START_DT <= to_date('"+to_date+"','dd/mm/yyyy')"+"AND A.END_DT >= to_date('"+from_date+"','dd/mm/yyyy')) AND " +
		///SB20210522		"(A.START_DT <= to_date('"+to_date+"','dd/mm/yyyy')"+"AND A.END_DT >= to_date('"+to_date+"','dd/mm/yyyy')) AND " + //SB20201013
				" A.END_DT >= TO_DATE('01/01/2021','DD/MM/YYYY') AND " + ///SB20210522 : ad discussed on 21stMay21 by Shiladitya to Include all Deals irrespective of Expairy on Report Date
				"A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM FMS7_LOA_MST B where A.LOA_NO=B.LOA_NO AND A.TENDER_NO=B.TENDER_NO AND " +
				"A.CUSTOMER_CD=B.CUSTOMER_CD) order by CUSTOMER_NAME, A.START_DT";
			rset = stmt.executeQuery(queryString);
	//		System.out.println("LOA: "+queryString);
			while(rset.next())
			{				

				SN_NO.add(rset.getString(3)==null?"0":rset.getString(3));
				SN_NO_HP.add(rset.getString(3)==null?"0":rset.getString(3));//HARSH20210728
				CUSTOMER_CD.add(rset.getString(5)==null?"0":rset.getString(5));
				CUSTOMER_CD_HP.add(rset.getString(5)==null?"0":rset.getString(5));//HARSH20210728									
				tmp_from_dt = rset.getString(1)==null?"":rset.getString(1);
				tmp_to_dt = rset.getString(2)==null?"":rset.getString(2);
				FRM_DT.add(rset.getString(1)==null?"":rset.getString(1));
				TO_DT.add(rset.getString(2)==null?"":rset.getString(2));
				temp_dt = tmp_from_dt+"   -   "+tmp_to_dt;
				CARGO_ARRIVAL_DATE.add(temp_dt);				
				SN_REV_NO.add(rset.getString(4)==null?"0":rset.getString(4));
				SN_REV_NO_HP.add(rset.getString(4)==null?"0":rset.getString(4));//HARSH20210728
			  	FGSA_NO.add("L"+rset.getString(6)==null?"0":rset.getString(6));	
			  	FGSA_NO_HP.add("L"+rset.getString(6)==null?"0":rset.getString(6));	//HARSH20210728
			  	FGSA_REV_NO.add("0");	 //No Rev no in LoA
			  	FGSA_REV_NO_HP.add("0"); //HARSH20210728
			  	BOOKMMBTU.add(rset.getString(7)==null?"0.00":nf.format(Double.parseDouble(rset.getString(7))));
			  	BOOKMMSCM.add(rset.getString(7)==null?"0.00":nf.format(Double.parseDouble(rset.getString(7))/38900));//MD20110916
			  	tot_comit += Double.parseDouble(rset.getString(7)==null?"0":nf.format(Double.parseDouble(rset.getString(7))));
			  	
			  	tot_comit_mmscm=(tot_comit/38900);//MD20110916
			  	tot_commitment_mmscm=nf.format(tot_comit_mmscm);
			  	
			  	tot_commitment=nf.format(tot_comit);
			  	SN_REF_NO.add(rset.getString(8)==null?"0":rset.getString(8));	
			  	SIGNING_DT.add(rset.getString(9)==null?"0":rset.getString(9));
			  	DEAL_SIGN_DT_HP.add(rset.getString(9)==null?"0":rset.getString(9)); //HARSH20210728
			  	RATE.add(nf2.format(Double.parseDouble(rset.getString(10)==null?"0":rset.getString(10))));
			  	String TEMP_apr_by=rset.getString(11)==null?"0":rset.getString(11);
			  	sn_apr_dt.add(rset.getString(12)==null?"-":rset.getString(12));
			  	String temp_ent_by=rset.getString(13)==null?"0":rset.getString(13);
			  	sn_ent_dt.add(rset.getString(14)==null?"-":rset.getString(14));
			  	VContType.add("L"); //SB20201108
			  	VContType_HP.add("L"); //HARSH20210728
			  	VDealDCQ.add(rset.getString(15)==null?"0":rset.getString(15));
				
			  	CUSTOMER_ABBR.add(rset.getString(18)==null?"":rset.getString(18));	//SB20210528
			  	CUSTOMER_NM.add(rset.getString(19)==null?"":rset.getString(19)); //SB20210528
			  	
			  	String queryString_nm ="SELECT EMP_NM FROM HR_EMP_MST WHERE EMP_CD='"+TEMP_apr_by+"'";
			  	rset1=stmt1.executeQuery(queryString_nm);
			  	if(rset1.next()){
			  		sn_apr_by.add(rset1.getString(1)==null?"-":rset1.getString(1));
			  	}else{
			  		sn_apr_by.add("-");
			  	}
			  	
			  	queryString_nm ="SELECT EMP_NM FROM HR_EMP_MST WHERE EMP_CD='"+temp_ent_by+"'";
			  	rset1=stmt1.executeQuery(queryString_nm);
			  	if(rset1.next()){
			  		sn_ent_by.add(rset1.getString(1)==null?"-":rset1.getString(1));
			  	}else{
			  		sn_ent_by.add("-");
			  	}
			  	MAPPING_ID.add(rset.getString(6)+"-0"+"-"+rset.getString(3)+"-"+rset.getString(4));
			  
			}
//*SB/////This is for BUY Deal////////////////////////////////////////////////////////
			
			queryString = "SELECT TO_CHAR(A.DELV_FROM_DT,'DD/MM/YYYY'), TO_CHAR(A.DELV_TO_DT,'DD/MM/YYYY'),A.MAN_CONF_CD,A.CARGO_SEQ_NO,E.TRD_CD, A.MAN_CD, A.MAN_CD, A.CONFIRM_VOL,"
					+ "A.CARGO_REF_CD,to_char(C.MAN_CONF_DT,'dd/mm/yyyy'), A.PRICE,A.EMP_CD,TO_CHAR(A.ENT_DT,'dd/mm/yyyy'), A.EMP_CD,TO_CHAR(A.ENT_DT,'dd/mm/yyyy'), E.PRICE_TYPE, A.PRICE_TO, A.REMARK  "
					+ ",DOM_BUY_FLAG "+ //SB20210618
					" FROM FMS7_MAN_CONFIRM_CARGO_DTL A, FMS7_MAN_CONFIRM_MST C, FMS7_MAN_REQ_MST E WHERE "+
					///SB20210522		" A.DELV_TO_DT >= TO_DATE('"+to_date+"','DD/MM/YYYY') "
					" A.DELV_TO_DT >= TO_DATE('01/01/2021','DD/MM/YYYY') AND " + ///SB20210522 : ad discussed on 21stMay21 by Shiladitya to Include all Deals irrespective of Expairy on Report Date
					" E.MAN_CD=A.MAN_CD AND "+ 
			///		" A.UNIT_CD=C.UNIT_CD AND A.CARGO_STATUS='T' AND CARGO_REF_CD='"+VCAR_REF_NO_temp.elementAt(i)+"' "+ 
					" A.MAN_CONF_CD=C.MAN_CONF_CD AND A.CARGO_STATUS='T' AND CARGO_REF_CD LIKE '%' "+ 
					" ORDER BY E.TRD_CD, A.MAN_CONF_CD, A.CARGO_REF_CD";
		//	System.out.println("MRver2.0 CARGO: "+queryString);
			rset = stmt.executeQuery(queryString);
			////System.out.println("FOr SN : "+to_date+" "+from_date);
			while(rset.next())
			{				
				tmp_from_dt = rset.getString(1)==null?"":rset.getString(1);
				tmp_to_dt = rset.getString(2)==null?"":rset.getString(2);
				FRM_DT.add(rset.getString(1)==null?"":rset.getString(1));
				TO_DT.add(rset.getString(2)==null?"":rset.getString(2));
				temp_dt = tmp_from_dt+"   -   "+tmp_to_dt;
				CARGO_ARRIVAL_DATE.add(temp_dt);				
				SN_NO.add(rset.getString(3)==null?"0":rset.getString(3));
				SN_NO_HP.add(rset.getString(3)==null?"0":rset.getString(3));//HARSH20210728
				SN_REV_NO.add(rset.getString(4)==null?"0":rset.getString(4));
				SN_REV_NO_HP.add(rset.getString(4)==null?"0":rset.getString(4));//HARSH20210728
				CUSTOMER_CD.add(rset.getString(5)==null?"0":rset.getString(5));
				CUSTOMER_CD_HP.add(rset.getString(5)==null?"0":rset.getString(5)); //HARSH20210728
			  	FGSA_NO.add(rset.getString(6)==null?"0":rset.getString(6));
			  	FGSA_NO_HP.add(rset.getString(6)==null?"0":rset.getString(6)); //HARSH20210728
			 /// 	FGSA_REV_NO.add(rset.getString(7)==null?"0":rset.getString(7));	
			  	FGSA_REV_NO.add("0");	 //No Rev no in LoA
			  	FGSA_REV_NO_HP.add("0"); //HARSH20210728
			  	BOOKMMBTU.add(rset.getString(8)==null?"0.00":nf.format(Double.parseDouble(rset.getString(8))));
			  	BOOKMMSCM.add(rset.getString(8)==null?"0.00":nf.format(Double.parseDouble(rset.getString(8))/38900));//MD20110916
			  	tot_comit += Double.parseDouble(rset.getString(8)==null?"0":nf.format(Double.parseDouble(rset.getString(8))));
			  	
			  	tot_comit_mmscm=(tot_comit/38900);//MD20110916
			  	tot_commitment_mmscm=nf.format(tot_comit_mmscm);
			  	////System.out.println("BOOKEDMMSCM : "+BOOKMMSCM+" TOTAL : "+tot_comit_mmscm);
			  	
			  	tot_commitment=nf.format(tot_comit);
			  	SN_REF_NO.add(rset.getString(9)==null?"":rset.getString(9));	
			  	SIGNING_DT.add(rset.getString(10)==null?"0":rset.getString(10));
			  	DEAL_SIGN_DT_HP.add(rset.getString(10)==null?"0":rset.getString(10)); //HARSH20210728
			  	RATE.add(nf2.format(Double.parseDouble(rset.getString(11)==null?"0":rset.getString(11))));
			  	String TEMP_apr_by=rset.getString(12)==null?"0":rset.getString(12);
			  	sn_apr_dt.add(rset.getString(13)==null?"-":rset.getString(13));
			  	String temp_ent_by=rset.getString(14)==null?"0":rset.getString(14);
			  	sn_ent_dt.add(rset.getString(15)==null?"-":rset.getString(15));
			  	VContType.add("B"); //SB20201108
			  	VContType_HP.add("B"); //HARSH20210728
			  	String DomBuyFlag=rset.getString(19)==null?"":rset.getString(19); //SB20210618
			  	if(DomBuyFlag.equals("Y")) 
			  		DomBuyFlag=" (DOM)";
			  	else
			  		DomBuyFlag=""; //SB20210630
			  
			  	queryString ="select TRADER_ABBR,TRADER_NAME,TRADER_CD from FMS7_TRADER_MST B where TRADER_CD="+rset.getString(5)+" "
						+ "and eff_dt=(select max(eff_dt) from FMS7_TRADER_MST where TRADER_CD='"+rset.getString(5)+"' and eff_dt<=to_date('"+rset.getString(1)+"','dd/mm/yyyy'))";	
			  	rset1 = stmt1.executeQuery(queryString);
				//	System.out.println(queryString);
					if(rset1.next())
					{
						DomBuyFlag=rset1.getString(2)+DomBuyFlag;
				//SB20210618	CUSTOMER_NM.add(rset1.getString(2)==null?"":rset1.getString(2));
						CUSTOMER_NM.add(DomBuyFlag);
						CUSTOMER_ABBR.add(rset1.getString(1)==null?"":rset1.getString(1));	
					}
/*SB20210705			  	String query="SELECT TO_CHAR(MAN_CONF_DT,'DD/MM/YYYY') FROM FMS7_MAN_CONFIRM_MST WHERE MAN_CONF_CD='"+rset.getString(4)+"'";
			  	System.out.println("CARGO-SIGN-DT: "+query);
			  	rset1=stmt1.executeQuery(query);
			  	if(rset1.next()){
			  	//	SIGNING_DT.add(rset1.getString(1)==null?"rset.getString(10)":rset1.getString(1));
			  	}else{
			  	//	SIGNING_DT.add("");
			  	}*/
			  	String queryString_nm ="SELECT EMP_NM FROM HR_EMP_MST WHERE EMP_CD='"+TEMP_apr_by+"'";
			  	rset1=stmt1.executeQuery(queryString_nm);
			  	if(rset1.next()){
			  		sn_apr_by.add(rset1.getString(1)==null?"-":rset1.getString(1));
			  	}else{
			  		sn_apr_by.add("-");
			  	}
			  	VCont_Start_Dt.add(rset.getString(1)==null?"":rset.getString(1));
			  	VCont_End_Dt.add(rset.getString(2)==null?"":rset.getString(2));
			  	
			  	queryString_nm ="SELECT EMP_NM FROM HR_EMP_MST WHERE EMP_CD='"+temp_ent_by+"'";
			  	rset1=stmt1.executeQuery(queryString_nm);
			  	if(rset1.next()){
			  		sn_ent_by.add(rset1.getString(1)==null?"-":rset1.getString(1));
			  	}else{
			  		sn_ent_by.add("-");
			  	}
			 // 	MAPPING_ID.add("C"+rset.getString(6)+"-"+rset.getString(7)+"-"+rset.getString(3)+"-"+rset.getString(4));
			  	MAPPING_ID.add("B"+rset.getString(3)+"-0-"+rset.getString(6)+"-"+rset.getString(7));
			 //as stored into FMS9_MRCR_CONT_PRICE_DTL  	String Mapp_Id=CUSTOMER_CD.elementAt(i)+"-"+SN_NO.elementAt(i)+"-"+SN_REV_NO.elementAt(i)+"-"+SN_REF_NO.elementAt(i)+"-0";
			}	
//*///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//////////////////////////////////STORAGE: ////////////////////////////////////////////
			{	
			FRM_DT.add(StorageDate);
			TO_DT.add(StorageDate);
			temp_dt = StorageDate+"   -   "+StorageDate;
			CARGO_ARRIVAL_DATE.add(temp_dt);				
			SN_NO.add("0");
			SN_REV_NO.add("0");		
			CUSTOMER_CD.add("0");																		  	
		  	FGSA_NO.add("0");	
		 /// 	FGSA_REV_NO.add(rset.getString(7)==null?"0":rset.getString(7));	
		  	FGSA_REV_NO.add("0");	 //No Rev no in LoA
		  	BOOKMMBTU.add("0.00");
		  	BOOKMMSCM.add("0.00");//MD20110916
		  	
		  	SN_REF_NO.add("");	
	//SB	  	SIGNING_DT.add(to_date);
			SIGNING_DT.add("");
		  	RATE.add("0");
		  
		  	sn_apr_dt.add("-");
		 
		  	sn_ent_dt.add("-");
		  	VContType.add("O"); //SB20201108		  	
		  	sn_apr_by.add("-");
		 
		  	VCont_Start_Dt.add(StorageDate);
		  	VCont_End_Dt.add(StorageDate);
		  	sn_ent_by.add("-");
		 
		  	MAPPING_ID.add("");
		  	CUSTOMER_NM.add("STORAGE");
		  	CUSTOMER_ABBR.add("");
		  	
		  	CUSTOMER_CD_HP.add("0"); //HARSH20210728
			FGSA_NO_HP.add("0"); //HARSH20210728
			FGSA_REV_NO_HP.add("0"); //HARSH20210728
			SN_NO_HP.add("0"); //HARSH20210728
			SN_REV_NO_HP.add("0"); //HARSH20210728
			VContType_HP.add("O"); //HARSH20210728
			DEAL_SIGN_DT_HP.add(StorageDate); //HARSH20210728
		 //as stored into FMS9_MRCR_CONT_PRICE_DTL  	String Mapp_Id=CUSTOMER_CD.elementAt(i)+"-"+SN_NO.elementAt(i)+"-"+SN_REV_NO.elementAt(i)+"-"+SN_REF_NO.elementAt(i)+"-0";
		}
		///////////////////////////^^^^STORAGE//////////////////////////////////////////////
		//	System.out.println(CUSTOMER_CD.size()+" CUSTOMER_CD: "+CUSTOMER_CD);
			System.out.println(VContType.size()+" CUSTOMER_CD: "+VContType);
			for(int i=0;i<CUSTOMER_CD.size();i++)
			{				
				queryString ="select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL where CUSTOMER_CD="+CUSTOMER_CD.elementAt(i)+" " +
				 "AND SN_NO="+SN_NO.elementAt(i)+" AND FGSA_NO="+FGSA_NO.elementAt(i)+" " +
				 "AND FGSA_REV_NO="+FGSA_REV_NO.elementAt(i)+" AND GAS_DT between to_date('"+FRM_DT.elementAt(i)+"','dd/mm/yyyy')" +
				 "AND to_date('"+to_date+"','dd/mm/yyyy') AND CONTRACT_TYPE='"+VContType.elementAt(i)+"'";
				rset = stmt.executeQuery(queryString);
			//	System.out.println(">>>>>>> "+queryString);
				if(rset.next())
				{
					SUPPLIEDMBTU.add(rset.getString(1)==null?"0.00":nf.format(Double.parseDouble(rset.getString(1))));
					//MD20110916
					SUPPLIEDMMSCM.add(rset.getString(1)==null?"0.00":nf.format(Double.parseDouble(rset.getString(1))/38900));
					if(rset.getString(1)!=null && !(rset.getString(1).equals("")))
					{
						sup_tot_comit+=Double.parseDouble(rset.getString(1));
						sup_tot_comit_mmscm+=(Double.parseDouble(rset.getString(1))/38900);//MD20110916
					}
					else
					{
						sup_tot_comit+=0;
						sup_tot_comit_mmscm+=0;//MD20110916
					}
				}
				else
				{
					SUPPLIEDMBTU.add("0.00");
					SUPPLIEDMMSCM.add("0.00");//MD20110916
					sup_tot_comit+=0;
					sup_tot_comit_mmscm+=0;//MD20110916
				}
				
				BALANCEMMBTU.add(nf.format(Double.parseDouble(""+BOOKMMBTU.elementAt(i))- Double.parseDouble(""+SUPPLIEDMBTU.elementAt(i))));
				//MD20110916
				BALANCEMMSCM.add(nf.format(Double.parseDouble(""+BOOKMMSCM.elementAt(i))- Double.parseDouble(""+SUPPLIEDMMSCM.elementAt(i))));
				bal_tot_comit+=Double.parseDouble(""+BOOKMMBTU.elementAt(i))- Double.parseDouble(""+SUPPLIEDMBTU.elementAt(i));
				bal_tot_comit_mmscm+=Double.parseDouble(""+BOOKMMSCM.elementAt(i))- Double.parseDouble(""+SUPPLIEDMMSCM.elementAt(i));
				//System.out.println("SUPLIEDMMSCM : "+SUPPLIEDMMSCM+" TOTAL : "+sup_tot_comit_mmscm);
				//System.out.println("BALANCEMMSCM : "+BALANCEMMSCM+" TOTAL : "+bal_tot_comit_mmscm);
					
				//////////////SB20200817////////////////////////
				String TCQ=""; String Alloc_Qty=""; String Gen_Dt=to_date; String Cont_End_Dt="";
				TCQ=""+BOOKMMBTU.elementAt(i); Cont_End_Dt=""+TO_DT.elementAt(i);
				Alloc_Qty=""+SUPPLIEDMBTU.elementAt(i); Cont_End_Dt=""+TO_DT.elementAt(i);
	//SB20210528			String Mapp_Id=CUSTOMER_CD.elementAt(i)+"-"+FGSA_NO.elementAt(i)+"-"+FGSA_REV_NO.elementAt(i)+"-"+SN_NO.elementAt(i)+"-"+SN_REV_NO.elementAt(i);
				String Mapp_Id=CUSTOMER_CD.elementAt(i)+"-"+FGSA_NO.elementAt(i)+"-%"+"-"+SN_NO.elementAt(i)+"-%"; //SB20210528
				if(VContType.elementAt(i).equals("B"))
					Mapp_Id=CUSTOMER_CD.elementAt(i)+"-"+SN_NO.elementAt(i)+"-"+SN_REV_NO.elementAt(i)+"-"+SN_REF_NO.elementAt(i)+"-0";
				String Phy_CurFrd_Price="0"; String Fin_CurFrd_Price="0";
				String Buy_Sell="SELL";
				if(CUSTOMER_ABBR.elementAt(i).equals("SEIPL"))
					Buy_Sell="BUY";
				else if(VContType.elementAt(i).equals("B"))
					Buy_Sell="BUY";
				else if(VContType.elementAt(i).equals("O")) //SB20210209
					Buy_Sell="BUY";
				
				String PriceType="";
				queryString ="SELECT DISTINCT PRICE_TYPE FROM FMS9_MRCR_CONT_PRICE_DTL "+
						//SB20210528		" where MAPPING_ID='"+Mapp_Id+"' AND CONTRACT_TYPE='"+VContType.elementAt(i)+"' AND FLAG='Y' ";
				" where MAPPING_ID LIKE '"+Mapp_Id+"' AND CONTRACT_TYPE='"+VContType.elementAt(i)+"' AND FLAG='Y' "; //SB20210528
				rset = stmt.executeQuery(queryString);
		//		System.out.println(">>>>>>> "+queryString);
				while(rset.next())
				{
					PriceType=PriceType+rset.getString(1);
				}
				
		//		System.out.println(PriceType+" NewSalePrice>>>>>>> ");
				if(PriceType.length()>1)
					PriceType="M";
			
				if(PriceType.equals(""))
					PriceType="F";
		////////Index type Pulling from Table////////////////////////////
				String INDEX_TYPE=""; String HolidayType=""; String PriceFormulaRemark="";
				PricePHYSName=""; //SB20210311
				//SB20210311			queryString ="SELECT PRICE_TYPE, CURVE_NM, RATE, SEQ_NO FROM FMS9_MRCR_CONT_PRICE_DTL "
							queryString ="SELECT PRICE_TYPE, CURVE_NM, RATE, SEQ_NO, PHYS_CURVE_NM, REMARKS, SLOPE, CONST FROM FMS9_MRCR_CONT_PRICE_DTL " //SB20210311
									//SB20210528		+ "WHERE MAPPING_ID='"+Mapp_Id+"' AND CONTRACT_TYPE='"+VContType.elementAt(i)+"' AND FLAG='Y' ";
							+ "WHERE MAPPING_ID LIKE '"+Mapp_Id+"' AND CONTRACT_TYPE='"+VContType.elementAt(i)+"' AND FLAG='Y' "; //SB20210528
					//		System.out.println("FMS9_CURVE_PRICE_DTL: "+queryString);
							rset2 = stmt.executeQuery(queryString);			
							if(rset2.next())
							{
								INDEX_TYPE=rset2.getString(2)==null?"":rset2.getString(2);
								PricePHYSName=rset2.getString(5)==null?"":rset2.getString(5);  //SB20210310	 //SB20210311
								PriceFormulaRemark=rset2.getString(6)==null?"":rset2.getString(6);
								if(PriceFormulaRemark.equals(""))
									PriceFormulaRemark="("+rset2.getString(2)+"*"+rset2.getString(7)+"+"+rset2.getString(8)+")";	
								
								String tempPriceFormulaRemark[]=PriceFormulaRemark.split("@");//MULTI_LEG //OPAL@0@3  : TypeOfDeal@PreviousMonth(Zero)@UptoPreviousMonth(3) 
								//DealName=tempPriceFormulaRemark[0];
								if(tempPriceFormulaRemark[0].equals("MULTI_LEG")) //It was OPAL
								{
									PriceFormulaRemark=PriceFormulaRemark.replace("@", " Leg -M");	
								//SB20210904	PriceFormulaRemark="MULTI_LEG Settlement "+PriceFormulaRemark.substring(5);
								}
								else if(tempPriceFormulaRemark[0].equals("MIN"))
								{
									PriceFormulaRemark=PriceFormulaRemark.replace("@", ", ");	
									PriceFormulaRemark="Min. of "+PriceFormulaRemark.substring(4);
								}
							}
							else
							{
								PriceFormulaRemark="";
							}
		////////////////////////////////////////////////////////////////////////
				////SB20210311: New Logic ////////////SB20210228: For Buy Deal It should be LNG_PHYS_INDIA else RLNG_PHYS_INDIA as per Shiladita 25th Feb 2021////////////////////////////////////////////////////////
							if(PricePHYSName.equals(""))  //SB20210310	
							{
								 if(Buy_Sell.equalsIgnoreCase("BUY"))
									 PricePHYSName="LNG_PHYS_INDIA";
								 else
									 PricePHYSName="RLNG_PHYS_INDIA";
							}
				////////////////^SB20210228////////////////////////////////////////////////////////
				String PriorCurveGasPrice="";
			//	if(Double.parseDouble(""+VMthYrWisePriceICE_JKM.elementAt(i))>0)
					PriorCurveGasPrice="0";
				String DealPrice="";
				DealPrice=""+RATE.elementAt(i);
				String Slope="1.000";
				String GasDate=""+FRM_DT.elementAt(i);
			//SB20210227	MRCR_ContDtl2(HolidayType, Buy_Sell,PriceType, DealPrice, TCQ, Alloc_Qty, GasDate, Gen_Dt, Cont_End_Dt, Phy_CurFrd_Price, Fin_CurFrd_Price, Slope, PriorCurveGasPrice);
				//////////////////SB20210201//////////////////////////////
////////////////////////////////Get Price Type from Existing system //////////////////////
					VBuySell.add(Buy_Sell);
					VBuySell_HP.add(Buy_Sell); //HARSH20210728
					if(PriceType.equals("M"))
					{
						VPriceType.add("FLOAT");
						VPriceType_HP.add("FLOAT");//HARSH20210728
						VDealPriceCurve.add(INDEX_TYPE); //SB20210401
						VPriceFormulaRemark.add(PriceFormulaRemark);
					}
					else if(PriceType.equals("F"))
					{
						VPriceType.add("FIXED");
						VPriceType_HP.add("FIXED");//HARSH20210728
						//SB20210401	VDealPriceCurve.add("RLNG_PHYS_INDIA");
						VDealPriceCurve.add("-"); //SB20210401
						VPriceFormulaRemark.add("");
					}
					else
					{
						//SB20201124	VPriceType.add("FIXED");
						VPriceType.add(PriceType);
						VPriceType_HP.add(PriceType);//HARSH20210728
						//SB20210401	VDealPriceCurve.add("RLNG_PHYS_INDIA");
						VDealPriceCurve.add("*"); //SB20210401
						VPriceFormulaRemark.add("");
					}
					VDealPhysCurve.add(PricePHYSName); //SB20210227
//////////////////////////////////////////////////////////////////////////////////////////
				String Mapp_IdCommon=CUSTOMER_CD.elementAt(i)+"-"+FGSA_NO.elementAt(i)+"-"+FGSA_REV_NO.elementAt(i)+"-"+SN_NO.elementAt(i)+"-"+SN_REV_NO.elementAt(i);
					if(!VContType.elementAt(i).equals("B") && !VContType.elementAt(i).equals("O")) //SB20210528
						 Mapp_IdCommon=CUSTOMER_CD.elementAt(i)+"-"+FGSA_NO.elementAt(i)+"-"+FGSA_REV_NO.elementAt(i)+"-"+SN_NO.elementAt(i)+"-%"; //SB20210528
				String DealNo=""+VContType.elementAt(i)+Mapp_IdCommon; String Report_Dt=to_date; String CustCode=""+CUSTOMER_CD.elementAt(i);//SB20210201
				MarketExposureDealWiseReportView(DealNo, Report_Dt, CustCode);  //SB20210201
				//////////////////^^^SB20210201//////////////////////////////
		   }
			
			//////////////////SB20210201//////////////////////////////
			String Report_Dt=to_date; //SB20210201
			MarketExposureStorage(Report_Dt);  //SB20210201
			//////////////////^^^SB20210201//////////////////////////////
		   sup_tot_comitment=nf.format(sup_tot_comit);
		   bal_tot_comitment=nf.format(bal_tot_comit);
		   
		   //Introduce by Milan 20010916
		   sup_tot_comitment_mmscm=nf.format(sup_tot_comit_mmscm);
		   bal_tot_comitment_mmscm=nf.format(bal_tot_comit_mmscm);
			
		    //--------Logic for Tender and LOA based Summary Contract based Agreement	
			tot_comit=0.0;
			bal_tot_comit=0.0;
			sup_tot_comit=0.0;
			
			tot_comit_mmscm=0.0;
			sup_tot_comit_mmscm=0.0;
			bal_tot_comit_mmscm=0.0;
			FRM_DT.clear();
			TO_DT.clear();
			//////////////////////SB20210528: Count and EoD process Status////////////////////////////
			int EoDCount=0; String StratTime=""; String EndTime="";
			queryString = "SELECT COUNT(DEAL_ID), MIN(APRV_DT), MAX(APRV_DT)   from FMS9_EOD_EXPOSURE_MST "
					+ " where TO_DATE(REPORT_DT, 'DD-MON-YY')=TO_DATE(to_date('"+to_date+"','dd/mm/yyyy'), 'DD-MON-YY') AND FLAG='Y' ";
			System.out.println("FMS9_EOD_EXPOSURE_MST: "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				EoDCount=rset.getInt(1);
				StratTime=rset.getString(2)==null?"":rset.getString(2);
				EndTime=rset.getString(3)==null?"":rset.getString(3);
				if(EoDCount>0)
				{
					EoD_Start_Dt = "Total Deals: "+EoDCount+"  EoD Process Completed: "+StratTime;
					EoD_End_Dt = " -  "+rset.getString(3);
				}
				else
				{
					EoD_Start_Dt = "";
					EoD_End_Dt = "";
				}
			}
			/*if(CUSTOMER_CD.size()>EoDCount & EoDCount>0)
			{
				EoD_Start_Dt = "Deal-->: "+EoDCount+"   EoD Process Started: "+StratTime+"    Please wait.......";
				EoD_End_Dt = "May be to resubmit again";
			}*/
			if(CUSTOMER_CD.size()==EoDCount & EoDCount>0)
			{
				EoD_Start_Dt = "Total Deals: ("+EoDCount+")  EoD Process Completed: "+StratTime;
				EoD_End_Dt = " -  "+EndTime;
			}
			else if(CUSTOMER_CD.size()>EoDCount & EoDCount>0)
			{
				EoD_Start_Dt = "Deals ("+EoDCount+"/"+CUSTOMER_CD.size()+")   EoD Process Completed: "+StratTime;
				EoD_End_Dt = " -  "+EndTime;
			}
			else if(EoDCount==0)
			{
				EoD_Start_Dt = " EoD Process Pending: "+StratTime;
				EoD_End_Dt = "";
			}
			else 
			{
				EoD_Start_Dt = " ***** ";
				EoD_End_Dt = "";
			}
			//////////////////////^^^^SB20210528: Count and EoD process Status////////////////////////////
		System.out.println(VContType+" ******End Of Listing VContType:******** "+VContType.size());
		}		
		catch(Exception e)
		{
			  //System.out.println("Exception in Databean_Contract_MasterReport--->fetchEligibleCargoList()-->"+e);
			  e.printStackTrace();
		}
	}
	public void Fetch_Deal_Dtls()
	{
		try
		{	
			//System.out.println("in it method");
			String tmp_from_dt = " ";
			String temp_dt="";
			String tmp_to_dt = " ";			
			double tot_comit=0.0;
			double sup_tot_comit=0.0;
			double bal_tot_comit=0.0;
			//Introduce By Milan 20110916 FOR MMSCM
			double tot_comit_mmscm=0.0;
			double sup_tot_comit_mmscm=0.0;
			double bal_tot_comit_mmscm=0.0;
			Vector FRM_DT=new Vector();
			Vector TO_DT=new Vector();						
			Vector RE_GAS_REV_NO=new Vector();
			Vector LTCORA_REV_NO=new Vector();//ADDED FOR LTCORA AND CN
			Vector LTCORA_MAPPING_ID=new Vector();//ADDED FOR LTCORA AND CN
			Vector CN_REV_NO=new Vector();//ADDED FOR LTCORA AND CN
			Vector CN_MAPPING_ID=new Vector();//ADDED FOR LTCORA AND CN
			
			queryString ="select TO_CHAR(A.START_DT,'DD/MM/YYYY'),TO_CHAR(A.END_DT,'DD/MM/YYYY'),A.SN_NO,A.SN_REV_NO,A.CUSTOMER_CD,A.FGSA_NO,"
					+ "A.FGSA_REV_NO,A.TCQ, " +
	 		"A.SN_REF_NO,to_char(A.SIGNING_DT,'dd/mm/yyyy'),A.RATE,to_char(A.ENT_DT,'dd/mm/yyyy'),EMP_CD,A.DCQ from FMS7_SN_MST A where "+ 
	 		"(A.START_DT <= to_date('"+to_date+"','dd/mm/yyyy')"+"AND A.END_DT >= to_date('"+from_date+"','dd/mm/yyyy')) AND " +
	 		"A.SN_REV_NO=(SELECT MAX(B.SN_REV_NO) FROM FMS7_SN_MST B where A.SN_NO=B.SN_NO AND A.FGSA_NO=B.FGSA_NO AND " +
	 		"A.CUSTOMER_CD=B.CUSTOMER_CD AND A.FGSA_REV_NO=B.FGSA_REV_NO) order by A.START_DT";
			
			//System.out.println("For SN"+queryString);
			rset = stmt.executeQuery(queryString);
			//System.out.println("FOr SN : "+to_date+" "+from_date);
			while(rset.next())
			{				
				//System.out.println("FOr SN : "+rset.getString(1)+" "+rset.getString(2));
				SN_NO.add(rset.getString(3)==null?"0":rset.getString(3));
				CUSTOMER_CD.add(rset.getString(5)==null?"0":rset.getString(5));
				
				tmp_from_dt = rset.getString(1)==null?"":rset.getString(1);
				tmp_to_dt = rset.getString(2)==null?"":rset.getString(2);
				FRM_DT.add(rset.getString(1)==null?"":rset.getString(1));
				TO_DT.add(rset.getString(2)==null?"":rset.getString(2));
				temp_dt = tmp_from_dt+"   -   "+tmp_to_dt;
				//CARGO_ARRIVAL_DATE.add(temp_dt);	
				SN_REV_NO.add(rset.getString(4)==null?"0":rset.getString(4));			  	
			  	FGSA_NO.add(rset.getString(6)==null?"0":rset.getString(6));
			  	FGSA_REV_NO.add(rset.getString(7)==null?"0":rset.getString(7));	
				//RATE.add(nf2.format(Double.parseDouble(rset.getString(11)==null?"0":rset.getString(11))));
			  	//BOOKMMBTU.add(rset.getString(8)==null?"0.00":nf.format(Double.parseDouble(rset.getString(8))));
			  	//BOOKMMSCM.add(rset.getString(8)==null?"0.00":nf.format(Double.parseDouble(rset.getString(8))/38900));//MD20110916
			  	//SN_REF_NO.add(rset.getString(9)==null?"0":rset.getString(9));	
			  //	SIGNING_DT.add(rset.getString(10)==null?"0":rset.getString(10));
			  	
			  	////////////RG20190319////////For New Report///////////////////////
			  /*	String query1="SELECT CARGO_REF_NO FROM FMS7_SN_CARGO_DTL A WHERE SN_NO='"+rset.getString(9)+"' AND "
					  			+ "CUSTOMER_CD='"+rset.getString(5)+"' AND FGSA_NO='"+rset.getString(6)+"' AND " +
						 		"A.SN_REV_NO=(SELECT MAX(B.SN_REV_NO) FROM FMS7_SN_CARGO_DTL B where A.SN_NO=B.SN_NO AND A.FGSA_NO=B.FGSA_NO AND " +
						 		"A.CUSTOMER_CD=B.CUSTOMER_CD AND A.FGSA_REV_NO=B.FGSA_REV_NO) AND " +
						 		"A.FGSA_REV_NO=(SELECT MAX(c.FGSA_REV_NO) FROM FMS7_SN_CARGO_DTL c where A.SN_NO=c.SN_NO AND A.FGSA_NO=c.FGSA_NO AND " +
						 		"A.CUSTOMER_CD=c.CUSTOMER_CD AND A.FGSA_REV_NO=c.FGSA_REV_NO)  ";
			  System.out.println("query1............"+query1);
			  	rset3=stmt3.executeQuery(query1);
			  	while(rset3.next()){*/
			  	System.out.println("--rset.getString(3)---"+rset.getString(3));
			  	
			  	String queryString1="SELECT SUM(GROSS_AMT_INR) FROM FMS7_INVOICE_MST WHERE FGSA_NO='"+rset.getString(6)+"' AND CONTRACT_TYPE='S' AND "
			  			+ "SN_NO='"+rset.getString(3)+"' AND (PERIOD_START_DT <=to_date('"+to_date+"','dd/mm/yyyy') AND PERIOD_END_DT >= "
			  					+ "TO_DATE('"+from_date+"','dd/mm/yyyy'))";
				System.out.println(""+queryString1);
			  	rset1=stmt1.executeQuery(queryString1);
				if(rset1.next()){
					Deal_amt_wout_tax.add(rset1.getString(1)==null?"0":rset1.getString(1));
				}else{
					Deal_amt_wout_tax.add("0.00");
				}
				
				queryString1="SELECT SUM(NET_AMT_INR) FROM FMS7_INVOICE_MST WHERE FGSA_NO='"+rset.getString(6)+"' AND CONTRACT_TYPE='S' AND "
			  			+ "SN_NO='"+rset.getString(3)+"' AND (PERIOD_START_DT <=to_date('"+to_date+"','dd/mm/yyyy') AND PERIOD_END_DT >= "
			  					+ "TO_DATE('"+from_date+"','dd/mm/yyyy')) AND (PAY_RECV_DT IS NULL AND PAY_RECV_AMT IS NULL)";
				System.out.println(""+queryString1);
			  	rset1=stmt1.executeQuery(queryString1);
				if(rset1.next()){
					Deal_amt_PAY_NOT_RECV.add(rset1.getString(1)==null?"0":rset1.getString(1));
				}else{
					Deal_amt_PAY_NOT_RECV.add("0.00");
				}
			  		
			  		String query="SELECT COUNT(*) FROM FMS7_SN_MST WHERE (sysdate BETWEEN TO_DATE('"+tmp_from_dt+"','dd/mm/yyyy') and TO_DATE('"+tmp_to_dt+"','dd/mm/yyyy')) AND " +
			  				"SN_REV_NO='"+rset.getString(4)+"' and SN_NO='"+rset.getString(3)+"' and FGSA_NO='"+rset.getString(6)+"' and FGSA_REV_NO='"+rset.getString(7)+"' ";
			  	//System.out.println("query........."+query);
			  	rset1=stmt1.executeQuery(query);
			  	if(rset1.next()){
			  		if(rset1.getInt(1)>0){
			  			Deal_sts.add("Active");
			  		}else{
			  		 	String query2="SELECT COUNT(*) FROM FMS7_SN_MST WHERE (sysdate < TO_DATE('"+tmp_from_dt+"','dd/mm/yyyy')) AND " +
				  				"SN_REV_NO='"+rset.getString(4)+"' and SN_NO='"+rset.getString(3)+"' and FGSA_NO='"+rset.getString(6)+"' and FGSA_REV_NO='"+rset.getString(7)+"' ";
				  	//System.out.println("query........."+query2);
			  			rset2=stmt2.executeQuery(query2);
			  			if(rset2.next()){
				  			if(rset2.getInt(1)>0){
					  			Deal_sts.add("Yet To Start");
					  		}else{
					  			Deal_sts.add("InActive");
					  		}
			  			}
			  		}
			  	}
			  	
			  	Deal_cust_cd.add(rset.getString(5)==null?"0":rset.getString(5));
				Deal_ST_DT.add(tmp_from_dt);
				Deal_END_DT.add(tmp_to_dt);
				Deal_NO.add(rset.getString(6)==null?"0":rset.getString(6));
				Deal_typ.add("SN");
				Deal_typ_nm.add("Supply Notice");
			  	Deal_qty_booked.add(rset.getString(8)==null?"0.00":nf.format(Double.parseDouble(rset.getString(8))));
			  	Deal_cargo_seq_no.add("-");
			  	Deal_Signing_dt.add(rset.getString(10)==null?"0":rset.getString(10));
			  	Deal_Price.add(nf2.format(Double.parseDouble(rset.getString(11)==null?"0":rset.getString(11))));
			  	Deal_curr.add("2");
				Deal_ENT_DT.add(rset.getString(12)==null?"":rset.getString(12));
				Deal_DCQ.add(rset.getString(14)==null?"0.00":nf.format(Double.parseDouble(rset.getString(14))));
				Deal_cont_no.add(rset.getString(3)==null?"":rset.getString(3));
				Deal_adv_amt.add("N.A");
				
				query="SELECT EMP_NM FROM HR_EMP_MST WHERE EMP_CD='"+rset.getString(13)+"'";
			  	rset1=stmt1.executeQuery(query);
			  	if(rset1.next()){
			  		Deal_ENT_BY.add(rset1.getString(1)==null?"":rset1.getString(1));
			  	}else{
			  		Deal_ENT_BY.add("");
			  	}
			  	query="	select to_date('"+tmp_to_dt+"','dd/mm/yyyy') - to_date('"+tmp_from_dt+"','dd/mm/yyyy')+1 from dual";
			  	rset1=stmt1.executeQuery(query);
			  	if(rset1.next()){
			  		Deal_duration.add(rset1.getString(1)==null?"":rset1.getString(1));
			  	}else{
			  		Deal_duration.add("");
			  	}
			  	}
			  	
			  	
			  
			  
			//}	
			
			for(int i=0;i<CUSTOMER_CD.size();i++)
			{
				queryString ="select CUSTOMER_ABBR,CUSTOMER_NAME,CUSTOMER_CD from FMS7_CUSTOMER_MST where CUSTOMER_CD="+CUSTOMER_CD.elementAt(i)+" ";
				rset = stmt.executeQuery(queryString);
				//System.out.println(queryString);
				if(rset.next())
				{
					Deal_cust_nm.add(rset.getString(2)==null?"":rset.getString(2));
					Deal_cust_abbr.add(rset.getString(1)==null?"":rset.getString(1));	
				}
				else
				{
					Deal_cust_nm.add("");
					Deal_cust_abbr.add("");
				}												
			
				queryString ="select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL where CUSTOMER_CD="+CUSTOMER_CD.elementAt(i)+" " +
				 "AND SN_NO="+SN_NO.elementAt(i)+" AND FGSA_NO="+FGSA_NO.elementAt(i)+" " +
				 "AND FGSA_REV_NO="+FGSA_REV_NO.elementAt(i)+" AND GAS_DT between to_date('"+FRM_DT.elementAt(i)+"','dd/mm/yyyy')" +
				 "AND to_date('"+to_date+"','dd/mm/yyyy') AND CONTRACT_TYPE='S'";
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					Deal_qty_Supplied.add(rset.getString(1)==null?"0.00":nf.format(Double.parseDouble(rset.getString(1))));
				}
				else
				{
					Deal_qty_Supplied.add("0.00");
				}
		   }
		 
		    //--------Logic for Tender and LOA based Summary Contract based Agreement	
			tot_comit=0.0;
			bal_tot_comit=0.0;
			sup_tot_comit=0.0;
			
			tot_comit_mmscm=0.0;
			sup_tot_comit_mmscm=0.0;
			bal_tot_comit_mmscm=0.0;
			FRM_DT.clear();
			TO_DT.clear();
			
			//Upadated By Milan 20110921
			queryString =   "select TO_CHAR(A.START_DT,'DD/MM/YYYY'),TO_CHAR(A.END_DT,'DD/MM/YYYY'),A.LOA_NO,A.LOA_REV_NO,A.CUSTOMER_CD,A.TENDER_NO,A.TCQ, " +
				"A.LOA_REF_NO,to_char(A.SIGNING_DT,'dd/mm/yyyy'),A.RATE,TO_CHAR(A.ENT_DT,'DD/MM/YYYY'),A.EMP_CD,A.DCQ from FMS7_LOA_MST A where "+ 
				"(A.START_DT <= to_date('"+to_date+"','dd/mm/yyyy')"+"AND A.END_DT >= to_date('"+from_date+"','dd/mm/yyyy')) AND " +
				"A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM FMS7_LOA_MST B where A.LOA_NO=B.LOA_NO AND A.TENDER_NO=B.TENDER_NO AND " +
				"A.CUSTOMER_CD=B.CUSTOMER_CD) order by A.START_DT";
			rset = stmt.executeQuery(queryString);
//			System.out.println(queryString);
			while(rset.next())
			{				
				LOA_NO.add(rset.getString(3)==null?"0":rset.getString(3));
				LOA_CUSTOMER_CD.add(rset.getString(5)==null?"0":rset.getString(5));									
				tmp_from_dt = rset.getString(1)==null?"":rset.getString(1);
				tmp_to_dt = rset.getString(2)==null?"":rset.getString(2);
				FRM_DT.add(rset.getString(1)==null?"":rset.getString(1));
				TO_DT.add(rset.getString(2)==null?"":rset.getString(2));
				temp_dt = tmp_from_dt+"   -   "+tmp_to_dt;
				LOA_CARGO_ARRIVAL_DATE.add(temp_dt);				
				LOA_REV_NO.add(rset.getString(4)==null?"0":rset.getString(4));			  	
			  	TENDER_NO.add(rset.getString(6)==null?"0":rset.getString(6));	
			  	LOA_BOOKMMBTU.add(rset.getString(7)==null?"0.00":nf.format(Double.parseDouble(rset.getString(7))));	
			  	tot_comit += Double.parseDouble(rset.getString(7)==null?"0":nf.format(Double.parseDouble(rset.getString(7))));
			  	loa_tot_commitment=nf.format(tot_comit) ;
			  	LOA_BOOKMMSCM.add(rset.getString(7)==null?"0.00":nf.format(Double.parseDouble(rset.getString(7))/38900));	
			  	tot_comit_mmscm += Double.parseDouble(rset.getString(7)==null?"0":nf.format(Double.parseDouble(rset.getString(7))/38900));
			  	loa_tot_commitment_mmscm=nf.format(tot_comit_mmscm) ;
			  	LOA_REF_NO.add(rset.getString(8)==null?"0":rset.getString(8));
			  	LOA_SIGNING_DT.add(rset.getString(9)==null?"":rset.getString(9));
			  	LOA_RATE.add(nf2.format(Double.parseDouble(rset.getString(10)==null?"0":rset.getString(10))));
			  	
				////////////RG20190319////////For New Report///////////////////////
			  	//System.out.println("---TENDER_NO--"+TENDER_NO+"---LOA_NO---"+LOA_NO);
			  	String queryString1="SELECT SUM(GROSS_AMT_INR) FROM FMS7_INVOICE_MST WHERE FGSA_NO='"+rset.getString(6)+"' AND CONTRACT_TYPE='L' and sn_no='"+rset.getString(3)+"'"
			  			+ " AND (PERIOD_START_DT <=to_date('"+to_date+"','dd/mm/yyyy') AND PERIOD_END_DT >= "
			  					+ "TO_DATE('"+from_date+"','dd/mm/yyyy'))";
				rset1=stmt1.executeQuery(queryString1);
				if(rset1.next()){
					Deal_amt_wout_tax.add(rset1.getString(1)==null?"0":rset1.getString(1));
				}else{
					Deal_amt_wout_tax.add("0.00");
				}
				queryString1="SELECT SUM(NET_AMT_INR) FROM FMS7_INVOICE_MST WHERE FGSA_NO='"+rset.getString(6)+"' AND CONTRACT_TYPE='L' and sn_no='"+rset.getString(3)+"'"
			  			+ " AND (PERIOD_START_DT <=to_date('"+to_date+"','dd/mm/yyyy') AND PERIOD_END_DT >= "
			  					+ "TO_DATE('"+from_date+"','dd/mm/yyyy')) AND (PAY_RECV_DT IS NULL AND PAY_RECV_AMT IS NULL)";
				rset1=stmt1.executeQuery(queryString1);
				if(rset1.next()){
					Deal_amt_PAY_NOT_RECV.add(rset1.getString(1)==null?"0":rset1.getString(1));
				}else{
					Deal_amt_PAY_NOT_RECV.add("0.00");
				}
				
				
			  	String query="SELECT COUNT(*) FROM FMS7_LOA_MST WHERE (sysdate BETWEEN TO_DATE('"+tmp_from_dt+"','dd/mm/yyyy') and TO_DATE('"+tmp_to_dt+"','dd/mm/yyyy')) AND " +
		  				"LOA_REV_NO='"+rset.getString(4)+"' and LOA_NO='"+rset.getString(3)+"' and TENDER_NO='"+rset.getString(6)+"' ";
			  	//System.out.println("query........."+query);
			  	rset1=stmt1.executeQuery(query);
			  	if(rset1.next()){
			  		if(rset1.getInt(1)>0){
			  			Deal_sts.add("Active");
			  		}else{
			  		 	String query1="SELECT COUNT(*) FROM FMS7_LOA_MST WHERE (sysdate < TO_DATE('"+tmp_from_dt+"','dd/mm/yyyy')) AND " +
		  				"LOA_REV_NO='"+rset.getString(4)+"' and LOA_NO='"+rset.getString(3)+"' and TENDER_NO='"+rset.getString(6)+"' ";
				  	//System.out.println("query........."+query1);
			  			rset2=stmt2.executeQuery(query1);
			  			if(rset2.next()){
				  			if(rset2.getInt(1)>0){
					  			Deal_sts.add("Yet To Start");
					  		}else{
					  			Deal_sts.add("InActive");
					  		}
			  			}
			  		}
			  	}
			  	Deal_cust_cd.add(rset.getString(5)==null?"0":rset.getString(5));
				Deal_ST_DT.add(tmp_from_dt);
				Deal_END_DT.add(tmp_to_dt);
				Deal_NO.add(rset.getString(6)==null?"0":rset.getString(6));
				Deal_typ.add("LOA");
				Deal_typ_nm.add("LOA");
			  	Deal_qty_booked.add(rset.getString(7)==null?"0.00":nf.format(Double.parseDouble(rset.getString(7))));
			  	Deal_cargo_seq_no.add("");
			  	Deal_Signing_dt.add(rset.getString(9)==null?"0":rset.getString(9));
			  	Deal_Price.add(nf2.format(Double.parseDouble(rset.getString(10)==null?"0":rset.getString(10))));
				Deal_curr.add("2");
				Deal_ENT_DT.add(rset.getString(11)==null?"":rset.getString(11));
				Deal_DCQ.add(rset.getString(13)==null?"0.00":nf.format(Double.parseDouble(rset.getString(13))));
				Deal_adv_amt.add("N.A");
				Deal_cont_no.add(rset.getString(3)==null?"":rset.getString(3));
				
				query="SELECT EMP_NM FROM HR_EMP_MST WHERE EMP_CD='"+rset.getString(12)+"'";
			  	rset1=stmt1.executeQuery(query);
			  	if(rset1.next()){
			  		Deal_ENT_BY.add(rset1.getString(1)==null?"":rset1.getString(1));
			  	}else{
			  		Deal_ENT_BY.add("");
			  	}
			  	query="	select to_date('"+tmp_to_dt+"','dd/mm/yyyy') - to_date('"+tmp_from_dt+"','dd/mm/yyyy')+1 from dual";
			  	rset1=stmt1.executeQuery(query);
			  	if(rset1.next()){
			  		Deal_duration.add(rset1.getString(1)==null?"":rset1.getString(1));
			  	}else{
			  		Deal_duration.add("");
			  	}
			}
			
			for(int i=0;i<LOA_CUSTOMER_CD.size();i++)
			{
				queryString ="select CUSTOMER_ABBR,CUSTOMER_NAME,CUSTOMER_CD from FMS7_CUSTOMER_MST where CUSTOMER_CD="+LOA_CUSTOMER_CD.elementAt(i)+" ";
				rset = stmt.executeQuery(queryString);
				//System.out.println("LOA_CUSTOMER_CD = "+queryString);
				if(rset.next())
				{
					Deal_cust_nm.add(rset.getString(2)==null?"":rset.getString(2));
					Deal_cust_abbr.add(rset.getString(1)==null?"":rset.getString(1));
				}
				else
				{
					Deal_cust_nm.add("");
					Deal_cust_abbr.add("");
				}		
				
				queryString ="select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL where CUSTOMER_CD="+LOA_CUSTOMER_CD.elementAt(i)+" " +
				 "AND SN_NO="+LOA_NO.elementAt(i)+" AND FGSA_NO="+TENDER_NO.elementAt(i)+" " +
				 "AND GAS_DT between to_date('"+FRM_DT.elementAt(i)+"','dd/mm/yyyy')" +
				 "AND to_date('"+to_date+"','dd/mm/yyyy') AND CONTRACT_TYPE='L'";
				rset = stmt.executeQuery(queryString);
				//System.out.println("LOA_QTY_MMBTU = "+queryString);
				if(rset.next())
				{
					Deal_qty_Supplied.add(rset.getString(1)==null?"0.00":nf.format(Double.parseDouble(rset.getString(1))));
				}
				else
				{
					Deal_qty_Supplied.add("0.00");
				}				
		   }
			//--------Logic for Re-Gas based Summary Contract based Agreement	
			tot_comit=0.0;
			bal_tot_comit=0.0;
			sup_tot_comit=0.0;
			//MD20110917
			tot_comit_mmscm=0.0;
			sup_tot_comit_mmscm=0.0;
			bal_tot_comit_mmscm=0.0;
			
			FRM_DT.clear();
			TO_DT.clear();
			/*queryString = "select TO_CHAR(A.CONTRACT_START_DT,'DD/MM/YYYY'),TO_CHAR(A.CONTRACT_END_DT,'DD/MM/YYYY'),A.RE_GAS_NO,A.RE_GAS_REV_NO,A.CUSTOMER_CD,A.CARGO_SEQ_NO,A.QTY_TO_BE_SUPPLY,A.RE_GAS_TARIF " +
	 					  "from FMS7_RE_GAS_CARGO_DTL A where (A.CONTRACT_START_DT between to_date('"+from_date+"','dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy')"+ 
	 					  "OR A.CONTRACT_END_DT between to_date('"+from_date+"','dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy')) AND " +
	 		              "A.RE_GAS_REV_NO=(SELECT MAX(B.RE_GAS_REV_NO) FROM FMS7_RE_GAS_CARGO_DTL B where A.RE_GAS_NO=B.RE_GAS_NO AND A.RE_GAS_REV_NO=B.RE_GAS_REV_NO AND " +
	 		              "A.CUSTOMER_CD=B.CUSTOMER_CD AND A.CARGO_SEQ_NO=B.CARGO_SEQ_NO) order by A.CONTRACT_START_DT";*/
			
			//Updated By Milan 20110921
			queryString = "select TO_CHAR(A.CONTRACT_START_DT,'DD/MM/YYYY'),TO_CHAR(A.CONTRACT_END_DT,'DD/MM/YYYY'),A.RE_GAS_NO,A.RE_GAS_REV_NO,A.CUSTOMER_CD"
					+ ",A.CARGO_SEQ_NO,A.QTY_TO_BE_SUPPLY,A.RE_GAS_TARIF,DCQ_QTY " +
			  "from FMS7_RE_GAS_CARGO_DTL A where "+ 
			  "(A.CONTRACT_START_DT <= to_date('"+to_date+"','dd/mm/yyyy')"+"AND A.CONTRACT_END_DT >= to_date('"+from_date+"','dd/mm/yyyy')) AND " +
             "A.RE_GAS_REV_NO=(SELECT MAX(B.RE_GAS_REV_NO) FROM FMS7_RE_GAS_CARGO_DTL B where A.RE_GAS_NO=B.RE_GAS_NO AND A.RE_GAS_REV_NO=B.RE_GAS_REV_NO AND " +
             "A.CUSTOMER_CD=B.CUSTOMER_CD AND A.CARGO_SEQ_NO=B.CARGO_SEQ_NO) order by A.CONTRACT_START_DT";
			rset = stmt.executeQuery(queryString);
			//System.out.println(queryString);
			while(rset.next())
			{				
				RE_GAS_NO.add(rset.getString(3)==null?"0":rset.getString(3));
				RE_GAS_REV_NO.add(rset.getString(4)==null?"0":rset.getString(4));
				RE_GAS_CUSTOMER_CD.add(rset.getString(5)==null?"0":rset.getString(5));									
				tmp_from_dt = rset.getString(1)==null?"":rset.getString(1);
				tmp_to_dt = rset.getString(2)==null?"":rset.getString(2);
				FRM_DT.add(rset.getString(1)==null?"":rset.getString(1));
				TO_DT.add(rset.getString(2)==null?"":rset.getString(2));
				temp_dt = tmp_from_dt+"   -   "+tmp_to_dt;
				RE_GAS_CARGO_ARRIVAL_DATE.add(temp_dt);											  
				RE_GAS_SEQ_NO.add(rset.getString(6)==null?"0":rset.getString(6));				  	
				RE_GAS_BOOKMMBTU.add(rset.getString(7)==null?"0.00":nf.format(Double.parseDouble(rset.getString(7))));	
			  	tot_comit += Double.parseDouble(rset.getString(7)==null?"0":nf.format(Double.parseDouble(rset.getString(7))));
			  	re_gas_tot_commit=""+ nf.format(tot_comit) ;
			  	//MD20110917
			  	RE_GAS_BOOKMMSCM.add(rset.getString(7)==null?"0.00":nf.format(Double.parseDouble(rset.getString(7))/38900));	
			  	tot_comit_mmscm += Double.parseDouble(rset.getString(7)==null?"0":nf.format(Double.parseDouble(rset.getString(7))/38900));
			  	re_gas_tot_commit_mmscm=""+ nf.format(tot_comit_mmscm) ;
			  	
			  	REGAS_RATE.add(nf2.format(Double.parseDouble(rset.getString(8)==null?"0":rset.getString(8))));
			  	
			  	///////////RG20190320////for deal summary report////
			  	
				String queryString1="SELECT SUM(GROSS_AMT_INR) FROM FMS7_INVOICE_MST WHERE FGSA_NO='"+rset.getString(3)+"' AND CONTRACT_TYPE='R' and sn_no='"+rset.getString(6)+"'";
				rset1=stmt1.executeQuery(queryString1);
				if(rset1.next()){
					Deal_amt_wout_tax.add(rset1.getString(1)==null?"0":rset1.getString(1));
				}else{
					Deal_amt_wout_tax.add("0.00");
				}
				queryString1="SELECT SUM(NET_AMT_INR) FROM FMS7_INVOICE_MST WHERE FGSA_NO='"+rset.getString(3)+"' AND CONTRACT_TYPE='R'"
						+ " and sn_no='"+rset.getString(6)+"' AND (PAY_RECV_DT IS NULL AND PAY_RECV_AMT IS NULL)";
				rset1=stmt1.executeQuery(queryString1);
				if(rset1.next()){
					Deal_amt_PAY_NOT_RECV.add(rset1.getString(1)==null?"0":rset1.getString(1));
				}else{
					Deal_amt_PAY_NOT_RECV.add("0.00");
				}
				
			  	String query="SELECT COUNT(*) FROM FMS7_RE_GAS_CARGO_DTL WHERE (sysdate BETWEEN TO_DATE('"+tmp_from_dt+"','dd/mm/yyyy') and TO_DATE('"+tmp_to_dt+"','dd/mm/yyyy')) AND " +
			  				"RE_GAS_REV_NO='"+rset.getString(4)+"' and RE_GAS_NO='"+rset.getString(3)+"' ";
			  	//System.out.println("query........."+query);
			  	rset1=stmt1.executeQuery(query);
			  	if(rset1.next()){
			  		if(rset1.getInt(1)>0){
			  			Deal_sts.add("Active");
			  		}else{
			  		 	String query1="SELECT COUNT(*) FROM FMS7_RE_GAS_CARGO_DTL WHERE (sysdate < TO_DATE('"+tmp_from_dt+"','dd/mm/yyyy')) AND " +
			  				"RE_GAS_REV_NO='"+rset.getString(4)+"' and RE_GAS_NO='"+rset.getString(3)+"' ";
				  	//System.out.println("query........."+query1);
			  			rset2=stmt2.executeQuery(query1);
			  			if(rset2.next()){
				  			if(rset2.getInt(1)>0){
					  			Deal_sts.add("Yet To Start");
					  		}else{
					  			Deal_sts.add("InActive");
					  		}
			  			}
			  		}
			  	}
			  	
			  	Deal_cust_cd.add(rset.getString(5)==null?"0":rset.getString(5));
				Deal_ST_DT.add(tmp_from_dt);
				Deal_END_DT.add(tmp_to_dt);
				
				Deal_NO.add(rset.getString(3)==null?"0":rset.getString(3));
				Deal_typ.add("Re-Gas");
				Deal_typ_nm.add("Re-Gas");
			  	Deal_qty_booked.add(rset.getString(7)==null?"0.00":nf.format(Double.parseDouble(rset.getString(7))));
			  	Deal_cargo_seq_no.add(rset.getString(6)==null?"0":rset.getString(6));
			  	//Deal_Signing_dt.add(rset.getString(10)==null?"0":rset.getString(10));
			  	Deal_Price.add(nf2.format(Double.parseDouble(rset.getString(8)==null?"0":rset.getString(8))));
				Deal_curr.add("2");
				//Deal_ENT_DT.add(rset.getString(12)==null?"":rset.getString(12));
				Deal_DCQ.add(rset.getString(9)==null?"0.00":nf.format(Double.parseDouble(rset.getString(9))));
				Deal_adv_amt.add("N.A");
				Deal_cont_no.add(rset.getString(3)==null?"":rset.getString(3));
				
			  	query="	select to_date('"+tmp_to_dt+"','dd/mm/yyyy') - to_date('"+tmp_from_dt+"','dd/mm/yyyy')+1 from dual";
			  	rset1=stmt1.executeQuery(query);
			  	if(rset1.next()){
			  		Deal_duration.add(rset1.getString(1)==null?"":rset1.getString(1));
			  	}else{
			  		Deal_duration.add("");
			  	}
			  	/////////////////////////////////////
			}			
			
			for(int i=0;i<RE_GAS_CUSTOMER_CD.size();i++)
			{
				queryString ="select CUSTOMER_ABBR,CUSTOMER_NAME,CUSTOMER_CD from FMS7_CUSTOMER_MST where CUSTOMER_CD="+RE_GAS_CUSTOMER_CD.elementAt(i)+" ";
				rset = stmt.executeQuery(queryString);
				//System.out.println("RE_GAS_CUSTOMER_CD = "+queryString);
				if(rset.next())
				{
					Deal_cust_nm.add(rset.getString(2)==null?"":rset.getString(2));
					Deal_cust_abbr.add(rset.getString(1)==null?"":rset.getString(1));
				}
				else
				{
					Deal_cust_nm.add("");
					Deal_cust_abbr.add("");	
				}	
				
				queryString = "SELECT TO_CHAR(A.SIGNING_DT,'DD/MM/YYYY'),TO_CHAR(ENT_DT,'DD/MM/YYYY'),EMP_CD FROM FMS7_RE_GAS_MST A " +
							  "WHERE A.RE_GAS_NO='"+RE_GAS_NO.elementAt(i)+"' AND " +
							  "A.REV_NO='"+RE_GAS_REV_NO.elementAt(i)+"' AND " +
							  "A.CUSTOMER_CD='"+RE_GAS_CUSTOMER_CD.elementAt(i)+"' ";			 
				//System.out.println(queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
				  	Deal_Signing_dt.add(rset.getString(1)==null?"":rset.getString(1));
				  	Deal_ENT_DT.add(rset.getString(2)==null?"":rset.getString(2));
				  	
				  	String query="SELECT EMP_NM FROM HR_EMP_MST WHERE EMP_CD='"+rset.getString(3)+"'";
				  	rset1=stmt1.executeQuery(query);
				  	if(rset1.next()){
				  		Deal_ENT_BY.add(rset1.getString(1)==null?"":rset1.getString(1));
				  	}else{
				  		Deal_ENT_BY.add("");
				  	}
				}
				else
				{
					Deal_Signing_dt.add("");
					Deal_ENT_DT.add("");
					Deal_ENT_BY.add("");
				}
	
				queryString ="select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL where CUSTOMER_CD="+RE_GAS_CUSTOMER_CD.elementAt(i)+" " +
				 "AND SN_NO="+RE_GAS_SEQ_NO.elementAt(i)+" AND FGSA_NO="+RE_GAS_NO.elementAt(i)+" " +
				 "AND GAS_DT between to_date('"+FRM_DT.elementAt(i)+"','dd/mm/yyyy')" +
				 "AND to_date('"+to_date+"','dd/mm/yyyy') AND CONTRACT_TYPE='R'";

				//System.out.println("RE_GAS_QTY_MMBTU = "+queryString);
				rset = stmt.executeQuery(queryString);				
				if(rset.next())
				{
					Deal_qty_Supplied.add(rset.getString(1)==null?"0.00":nf.format(Double.parseDouble(rset.getString(1))));
				}
				else
				{
					Deal_qty_Supplied.add("0.00");
				}				
				RE_GAS_BALANCEMMBTU.add(nf.format(Double.parseDouble(""+RE_GAS_BOOKMMBTU.elementAt(i))- Double.parseDouble(""+RE_GAS_SUPPLIEDMBTU.elementAt(i))));
				bal_tot_comit+=Double.parseDouble(""+RE_GAS_BOOKMMBTU.elementAt(i))- Double.parseDouble(""+RE_GAS_SUPPLIEDMBTU.elementAt(i));
				//MD20110917
				RE_GAS_BALANCEMMSCM.add(nf.format(Double.parseDouble(""+RE_GAS_BOOKMMSCM.elementAt(i))- Double.parseDouble(""+RE_GAS_SUPPLIEDMMSCM.elementAt(i))));
				bal_tot_comit_mmscm+=Double.parseDouble(""+RE_GAS_BOOKMMSCM.elementAt(i))- Double.parseDouble(""+RE_GAS_SUPPLIEDMMSCM.elementAt(i));
		   }
			re_gas_sup_tot_comitment=nf.format(sup_tot_comit);
			re_gas_bal_tot_comitment=nf.format(bal_tot_comit);
			//MD20110917
			re_gas_sup_tot_comitment_mmscm=nf.format(sup_tot_comit_mmscm);
			re_gas_bal_tot_comitment_mmscm=nf.format(bal_tot_comit_mmscm);
			
//			System.out.println("re_gas_sup_tot_comitment_mmscm"+re_gas_sup_tot_comitment_mmscm);
//			System.out.println("re_gas_bal_tot_comitment_mmscm"+re_gas_bal_tot_comitment_mmscm);
			
			//////**********ADDED FOR LTCORA AND CN
			//--------Logic for LTCORA based Summary Contract based Agreement	
			tot_comit=0.0;
			bal_tot_comit=0.0;
			sup_tot_comit=0.0;
			//MD20110917
			tot_comit_mmscm=0.0;
			sup_tot_comit_mmscm=0.0;
			bal_tot_comit_mmscm=0.0;
			
			
			
			FRM_DT.clear();
			TO_DT.clear();


			/////*** beLOW CODE IS CHANGED FOR PERIOD IN PLCAE OF LTCORA ***////
			queryString="select TO_CHAR(A.CONTRACT_START_DT,'DD/MM/YYYY'),TO_CHAR(A.CONTRACT_END_DT,'DD/MM/YYYY'), " +
			"A.mapping_id,A.mapping_id,A.mapping_id,A.CARGO_SEQ_NO,A.QTY_TO_BE_SUPPLY,A.REGAS_TARIF, " +
			"C.REV_NO,to_char(C.ENT_DT,'dd/mm/yyyy'),C.ENT_BY,A.DCQ_QTY,A.TARIFF_CUR_FLAG from FMS8_LNG_REGAS_CARGO_DTL A, FMS8_LNG_REGAS_MST C where " +
			"(A.CONTRACT_START_DT <= to_date('"+to_date+"','dd/mm/yyyy') AND " +
			"A.CONTRACT_END_DT >= to_date('"+from_date+"','dd/mm/yyyy')) AND A.MAPPING_ID=C.MAPPING_ID " +
			"AND C.CN_NO!='0' AND CN_NO >'9999' AND C.CN_REV_NO=(SELECT MAX(B.CN_REV_NO) FROM FMS8_LNG_REGAS_MST B " +
			"where C.agreement_no=B.agreement_no AND C.customer_cd=B.customer_cd AND B.CN_NO!=0 AND CN_NO >'9999' AND " +
			"B.CN_NO=C.CN_NO AND c.rev_no=B.rev_no AND B.REV_NO=(SELECT MAX(D.REV_NO) FROM FMS8_LNG_REGAS_MST D WHERE " +
			"D.agreement_no=B.agreement_no AND B.customer_cd=D.customer_cd AND D.CN_NO=0 " +
			"AND D.agreement_no=C.agreement_no AND D.customer_cd=C.customer_cd )) " +
			"order by A.CONTRACT_START_DT";
			
			rset = stmt.executeQuery(queryString);
			//System.out.println(queryString);
			while(rset.next())
			{				
				
				String map_id=rset.getString(3)==null?"":rset.getString(3);
				String tempmap_id[]=map_id.split("-");
				
				String temp_regas_no=tempmap_id[3];
				String temp_ag_no=tempmap_id[1];
				String temp_ag_rev_no=tempmap_id[2];//RG20190530
				String temp_regas_rev_no=tempmap_id[4];
				String temp_cust_cd=tempmap_id[0];
				
				LTCORA_MAPPING_ID.add(map_id);
				LTCORA_NO.add(temp_regas_no);
				LTCORA_REV_NO.add(temp_regas_rev_no);
				LTCORA_CUSTOMER_CD.add(temp_cust_cd);
				tmp_from_dt = rset.getString(1)==null?"":rset.getString(1);
				tmp_to_dt = rset.getString(2)==null?"":rset.getString(2);
				FRM_DT.add(rset.getString(1)==null?"":rset.getString(1));
				TO_DT.add(rset.getString(2)==null?"":rset.getString(2));
				temp_dt = tmp_from_dt+"   -   "+tmp_to_dt;
				LTCORA_CARGO_ARRIVAL_DATE.add(temp_dt);		
				LTCORA_SEQ_NO.add(rset.getString(6)==null?"0":rset.getString(6));
				LTCORA_RATE.add(nf2.format(Double.parseDouble(rset.getString(8)==null?"0":rset.getString(8))));
				
//			  	tot_comit += Double.parseDouble(rset.getString(7)==null?"0":nf.format(Double.parseDouble(rset.getString(7))));
//			  	LTCORA_tot_commit=""+ nf.format(tot_comit) ;
			  	//LTCORA_BOOKMMSCM.add(rset.getString(7)==null?"0.00":nf.format(Double.parseDouble(rset.getString(7))/38900));	
//			  	tot_comit_mmscm += Double.parseDouble(rset.getString(7)==null?"0":nf.format(Double.parseDouble(rset.getString(7))/38900));
			  //LTCORA_BOOKMMBTU.add(rset.getString(7)==null?"0.00":nf.format(Double.parseDouble(rset.getString(7))));
//			  	LTCORA_tot_commit_mmscm=""+ nf.format(tot_comit_mmscm) ;
			  	
			  	///////////////////RG20190320//////For deal summary report//////////////

				String queryString1="SELECT SUM(GROSS_AMT_INR) FROM FMS7_INVOICE_MST WHERE mapping_id='"+map_id+"' AND CONTRACT_TYPE='C' and sn_no='"+rset.getString(6)+"'";
				rset1=stmt1.executeQuery(queryString1);
				if(rset1.next()){
					Deal_amt_wout_tax.add(rset1.getString(1)==null?"0":rset1.getString(1));
				}else{
					Deal_amt_wout_tax.add("0.00");
				}
				queryString1="SELECT SUM(NET_AMT_INR) FROM FMS7_INVOICE_MST WHERE mapping_id='"+map_id+"' AND CONTRACT_TYPE='C' "
						+ "and sn_no='"+rset.getString(6)+"' AND (PAY_RECV_DT IS NULL AND PAY_RECV_AMT IS NULL)";
				rset1=stmt1.executeQuery(queryString1);
				if(rset1.next()){
					Deal_amt_PAY_NOT_RECV.add(rset1.getString(1)==null?"0":rset1.getString(1));
				}else{
					Deal_amt_PAY_NOT_RECV.add("0.00");
				}
				System.out.println("mapp id..."+tempmap_id[0]+"-"+tempmap_id[3]+"-"+tempmap_id[4]+"-"+rset.getString(6)+"-0-C");
				String temp_mappingid=tempmap_id[0]+"-"+tempmap_id[3]+"-"+tempmap_id[4]+"-"+rset.getString(6)+"-0-C";
				
				queryString1="SELECT PRICE_RATE FROM FMS7_CONT_PRICE_DTL WHERE mapping_id='"+temp_mappingid+"' AND LTCORA_NO='"+temp_ag_no+"' AND LTCORA_REV_NO='"+temp_ag_rev_no+"'"
						+ " AND PRICE_CD='1' ";
				rset1=stmt1.executeQuery(queryString1);
				if(rset1.next()){
					Deal_adv_amt.add(rset1.getString(1)==null?"0":rset1.getString(1));
				}else{
					Deal_adv_amt.add("-");
				}
				
				
				String query="SELECT COUNT(*) FROM FMS8_LNG_REGAS_MST WHERE (sysdate BETWEEN TO_DATE('"+tmp_from_dt+"','dd/mm/yyyy') and TO_DATE('"+tmp_to_dt+"','dd/mm/yyyy')) AND " +
		  				"Agreement_no='"+temp_ag_no+"' and CN_NO='"+temp_regas_no+"' and mapping_id='"+rset.getString(3)+"' and customer_cd='"+temp_cust_cd+"'";
			  	//System.out.println("query........."+query);
			  	rset1=stmt1.executeQuery(query);
			  	if(rset1.next()){
			  		if(rset1.getInt(1)>0){
			  			Deal_sts.add("Active");
			  		}else{
			  		 	String query1="SELECT COUNT(*) FROM FMS8_LNG_REGAS_MST WHERE (sysdate < TO_DATE('"+tmp_from_dt+"','dd/mm/yyyy')) AND " +
		  				"Agreement_no='"+temp_ag_no+"' and CN_NO='"+temp_regas_no+"' and mapping_id='"+rset.getString(3)+"' and customer_cd='"+temp_cust_cd+"'";
				  	//System.out.println("query........."+query1);
			  			rset2=stmt2.executeQuery(query1);
			  			if(rset2.next()){
				  			if(rset2.getInt(1)>0){
					  			Deal_sts.add("Yet To Start");
					  		}else{
					  			Deal_sts.add("InActive");
					  		}
			  			}
			  		}
			  	}
			  	Deal_NO.add(temp_ag_no);
				Deal_typ.add("Period");
				Deal_typ_nm.add("Period");
				Deal_cust_cd.add(temp_cust_cd);
				Deal_ST_DT.add(tmp_from_dt);
				Deal_END_DT.add(tmp_to_dt);
				Deal_cargo_seq_no.add(rset.getString(6)==null?"0":rset.getString(6));
				
				Deal_qty_booked.add(rset.getString(7)==null?"0.00":nf.format(Double.parseDouble(rset.getString(7))));
			  	Deal_Price.add(nf2.format(Double.parseDouble(rset.getString(8)==null?"0":rset.getString(8))));
				Deal_curr.add(rset.getString(13)==null?"":rset.getString(13));
			  	Deal_ENT_DT.add(rset.getString(10)==null?"0":rset.getString(10));
			  	Deal_DCQ.add(rset.getString(12)==null?"0.00":nf.format(Double.parseDouble(rset.getString(12))));
			  	Deal_cont_no.add(temp_regas_no);
			  	
			  	query="SELECT EMP_NM FROM HR_EMP_MST WHERE EMP_CD='"+rset.getString(11)+"'";
			  	rset1=stmt1.executeQuery(query);
			  	if(rset1.next()){
			  		Deal_ENT_BY.add(rset1.getString(1)==null?"":rset1.getString(1));
			  	}else{
			  		Deal_ENT_BY.add("");
			  	}
			  	query="	select to_date('"+tmp_to_dt+"','dd/mm/yyyy') - to_date('"+tmp_from_dt+"','dd/mm/yyyy')+1 from dual";
			  	rset1=stmt1.executeQuery(query);
			  	if(rset1.next()){
			  		Deal_duration.add(rset1.getString(1)==null?"":rset1.getString(1));
			  	}else{
			  		Deal_duration.add("");
			  	}
			  	
			}			
			
			for(int i=0;i<LTCORA_CUSTOMER_CD.size();i++)
			{
				queryString ="select CUSTOMER_ABBR,CUSTOMER_NAME,CUSTOMER_CD from FMS7_CUSTOMER_MST where CUSTOMER_CD="+LTCORA_CUSTOMER_CD.elementAt(i)+" ";
				rset = stmt.executeQuery(queryString);
//				System.out.println("LTCORA_CUSTOMER_CD = "+queryString);
				if(rset.next())
				{
					Deal_cust_nm.add(rset.getString(2)==null?"":rset.getString(2));
					Deal_cust_abbr.add(rset.getString(1)==null?"":rset.getString(1));
				}
				else
				{
					Deal_cust_nm.add("");
					Deal_cust_abbr.add("");	
				}	
				
				queryString = "SELECT TO_CHAR(A.SIGNING_DT,'DD/MM/YYYY') FROM FMS8_LNG_REGAS_MST A " +
				  "WHERE A.MAPPING_ID='"+LTCORA_MAPPING_ID.elementAt(i)+"' ";
			//	System.out.println(queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					//LTCORA_SIGNING_DT.add(rset.getString(1)==null?"":rset.getString(1));
					Deal_Signing_dt.add(rset.getString(1)==null?"":rset.getString(1));
				}
				else
				{
					//LTCORA_SIGNING_DT.add("");
					Deal_Signing_dt.add("");
				}
	
//				queryString ="select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL where CUSTOMER_CD="+RE_GAS_CUSTOMER_CD.elementAt(i)+" " +
//							 "AND SN_NO="+RE_GAS_SEQ_NO.elementAt(i)+" AND FGSA_NO="+RE_GAS_NO.elementAt(i)+" " +
//							 "AND GAS_DT between to_date('"+FRM_DT.elementAt(i)+"','dd/mm/yyyy')" +
//							 "AND to_date('"+TO_DT.elementAt(i)+"','dd/mm/yyyy') AND CONTRACT_TYPE='R'";
				
				queryString ="select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL where CUSTOMER_CD="+LTCORA_CUSTOMER_CD.elementAt(i)+" " +
				 "AND SN_NO="+LTCORA_SEQ_NO.elementAt(i)+" AND FGSA_NO="+LTCORA_NO.elementAt(i)+" " +
				 "AND GAS_DT between to_date('"+FRM_DT.elementAt(i)+"','dd/mm/yyyy')" +
				 "AND to_date('"+to_date+"','dd/mm/yyyy') AND CONTRACT_TYPE='C' AND MAPPING_ID='"+LTCORA_MAPPING_ID.elementAt(i)+"'";

			//	System.out.println("LTCORA_QTY_MMBTU = "+queryString);
				rset = stmt.executeQuery(queryString);				
				if(rset.next())
				{
					//LTCORA_SUPPLIEDMBTU.add(rset.getString(1)==null?"0.00":nf.format(Double.parseDouble(rset.getString(1))));
					Deal_qty_Supplied.add(rset.getString(1)==null?"0.00":nf.format(Double.parseDouble(rset.getString(1))));
					//MD20110917
				//	LTCORA_SUPPLIEDMMSCM.add(rset.getString(1)==null?"0.00":nf.format(Double.parseDouble(rset.getString(1))/38900));
//					if(rset.getString(1)!=null && !(rset.getString(1).equals("")))
//					{
//						sup_tot_comit+=Double.parseDouble(rset.getString(1));
//						sup_tot_comit_mmscm+=(Double.parseDouble(rset.getString(1))/38900);
//					}
//					else
//					{
//						sup_tot_comit+=0;
//						sup_tot_comit_mmscm+=0;
//					}
				}
				else
				{
					Deal_qty_Supplied.add("0.00");
					/*sup_tot_comit+=0;
					
					LTCORA_SUPPLIEDMMSCM.add("0.00");
					sup_tot_comit_mmscm+=0;*/
				}				
		   }
			/*LTCORA_sup_tot_comitment=nf.format(sup_tot_comit);
			LTCORA_bal_tot_comitment=nf.format(bal_tot_comit);
			//MD20110917
			LTCORA_sup_tot_comitment_mmscm=nf.format(sup_tot_comit_mmscm);
			LTCORA_bal_tot_comitment_mmscm=nf.format(bal_tot_comit_mmscm);*/
			
			
			//--------Logic for CN based Summary Contract based Agreement	
			tot_comit=0.0;
			bal_tot_comit=0.0;
			sup_tot_comit=0.0;
			//MD20110917
			tot_comit_mmscm=0.0;
			sup_tot_comit_mmscm=0.0;
			bal_tot_comit_mmscm=0.0;
			
			
			
			FRM_DT.clear();
			TO_DT.clear();
			
			queryString="select TO_CHAR(A.CONTRACT_START_DT,'DD/MM/YYYY'),TO_CHAR(A.CONTRACT_END_DT,'DD/MM/YYYY'), " +
					"A.mapping_id,A.mapping_id,A.mapping_id,A.CARGO_SEQ_NO,A.QTY_TO_BE_SUPPLY,A.REGAS_TARIF, " +
					"C.REV_NO,TO_CHAR(C.ENT_DT,'DD/MM/YYYY'),C.ENT_BY,A.DCQ_QTY,A.TARIFF_CUR_FLAG from FMS8_LNG_REGAS_CARGO_DTL A, FMS8_LNG_REGAS_MST C where " +
					"(A.CONTRACT_START_DT <= to_date('"+to_date+"','dd/mm/yyyy') AND " +
					"A.CONTRACT_END_DT >= to_date('"+from_date+"','dd/mm/yyyy')) AND A.MAPPING_ID=C.MAPPING_ID " +
					"AND C.CN_NO!='0' AND CN_NO <'9999' AND C.CN_REV_NO=(SELECT MAX(B.CN_REV_NO) FROM FMS8_LNG_REGAS_MST B " +
					"where C.agreement_no=B.agreement_no AND C.customer_cd=B.customer_cd AND B.CN_NO!=0 AND CN_NO <'9999' AND " +
					"B.CN_NO=C.CN_NO AND c.rev_no=B.rev_no AND B.REV_NO=(SELECT MAX(D.REV_NO) FROM FMS8_LNG_REGAS_MST D WHERE " +
					"D.agreement_no=B.agreement_no AND B.customer_cd=D.customer_cd AND D.CN_NO=0 " +
					"AND D.agreement_no=C.agreement_no AND D.customer_cd=C.customer_cd )) " +
					"order by A.CONTRACT_START_DT,A.CARGO_SEQ_NO";
//			System.out.println("CNNNNNNNNNN......"+queryString);
			rset = stmt.executeQuery(queryString);
//			System.out.println(queryString);
			while(rset.next())
			{				
				
				String map_id=rset.getString(3)==null?"":rset.getString(3);
				String tempmap_id[]=map_id.split("-");
				String temp_ag_no=tempmap_id[1];
				String temp_ag_rev_no=tempmap_id[2];//RG20190530
				String temp_regas_no=tempmap_id[3];
				String temp_regas_rev_no=tempmap_id[4];
				String temp_cust_cd=tempmap_id[0];
				
				CN_MAPPING_ID.add(map_id);
				CN_NO.add(temp_regas_no);
				CN_REV_NO.add(temp_regas_rev_no);
				CN_CUSTOMER_CD.add(temp_cust_cd);	
				tmp_from_dt = rset.getString(1)==null?"":rset.getString(1);
				tmp_to_dt = rset.getString(2)==null?"":rset.getString(2);
				FRM_DT.add(rset.getString(1)==null?"":rset.getString(1));
				TO_DT.add(rset.getString(2)==null?"":rset.getString(2));
				temp_dt = tmp_from_dt+"   -   "+tmp_to_dt;
				CN_CARGO_ARRIVAL_DATE.add(temp_dt);	
				CN_SEQ_NO.add(rset.getString(6)==null?"0":rset.getString(6));				  	
				//CN_BOOKMMBTU.add(rset.getString(7)==null?"0.00":nf.format(Double.parseDouble(rset.getString(7))));
			  	tot_comit += Double.parseDouble(rset.getString(7)==null?"0":nf.format(Double.parseDouble(rset.getString(7))));
			  	CN_tot_commit=""+ nf.format(tot_comit) ;
			  	CN_BOOKMMSCM.add(rset.getString(7)==null?"0.00":nf.format(Double.parseDouble(rset.getString(7))/38900));	
			  	tot_comit_mmscm += Double.parseDouble(rset.getString(7)==null?"0":nf.format(Double.parseDouble(rset.getString(7))/38900));
			  	CN_tot_commit_mmscm=""+ nf.format(tot_comit_mmscm) ;
			  	CN_RATE.add(nf2.format(Double.parseDouble(rset.getString(8)==null?"0":rset.getString(8))));
			  	///////////////RG20190320///////////////deal summery report///////
			  	Deal_NO.add(temp_ag_no);
				Deal_typ.add("CN");
				Deal_typ_nm.add("Confirmation Notice");
				Deal_cust_cd.add(temp_cust_cd);	
				Deal_cargo_seq_no.add(rset.getString(6)==null?"0":rset.getString(6));
				Deal_ST_DT.add(tmp_from_dt);
				Deal_END_DT.add(tmp_to_dt);
				Deal_qty_booked.add(rset.getString(7)==null?"0.00":nf.format(Double.parseDouble(rset.getString(7))));
			  	Deal_Price.add(nf2.format(Double.parseDouble(rset.getString(8)==null?"0":rset.getString(8))));
			  	Deal_curr.add(rset.getString(13)==null?"":rset.getString(13));
			  	Deal_ENT_DT.add(rset.getString(10)==null?"0":rset.getString(10));
			  	Deal_DCQ.add(rset.getString(12)==null?"0.00":nf.format(Double.parseDouble(rset.getString(12))));
			  	Deal_cont_no.add(temp_regas_no);
			  	
			  	String queryString1="SELECT SUM(GROSS_AMT_INR) FROM FMS7_INVOICE_MST WHERE mapping_id='"+map_id+"' AND CONTRACT_TYPE='C' and sn_no='"+rset.getString(6)+"'";
				rset1=stmt1.executeQuery(queryString1);
				if(rset1.next()){
					Deal_amt_wout_tax.add(rset1.getString(1)==null?"0":rset1.getString(1));
				}else{
					Deal_amt_wout_tax.add("0.00");
				}
				queryString1="SELECT SUM(NET_AMT_INR) FROM FMS7_INVOICE_MST WHERE mapping_id='"+map_id+"' AND CONTRACT_TYPE='C' "
						+ "and sn_no='"+rset.getString(6)+"' AND (PAY_RECV_DT IS NULL AND PAY_RECV_AMT IS NULL)";
				rset1=stmt1.executeQuery(queryString1);
				if(rset1.next()){
					Deal_amt_PAY_NOT_RECV.add(rset1.getString(1)==null?"0":rset1.getString(1));
				}else{
					Deal_amt_PAY_NOT_RECV.add("0.00");
				}
				String temp_mappingid=tempmap_id[0]+"-"+tempmap_id[3]+"-"+tempmap_id[4]+"-"+rset.getString(6)+"-0-C";
				//System.out.println("temp_mappingid.."+temp_mappingid);
				queryString1="SELECT PRICE_RATE FROM FMS7_CONT_PRICE_DTL WHERE mapping_id='"+temp_mappingid+"' AND LTCORA_NO='"+temp_ag_no+"' AND"
						+ " LTCORA_REV_NO='"+temp_ag_rev_no+"' AND PRICE_CD='1' ";
				rset1=stmt1.executeQuery(queryString1);
				if(rset1.next()){
					Deal_adv_amt.add(rset1.getString(1)==null?"0":rset1.getString(1));
				}else{
					Deal_adv_amt.add("-");
				}
				String query="SELECT EMP_NM FROM HR_EMP_MST WHERE EMP_CD='"+rset.getString(11)+"'";
			  	rset1=stmt1.executeQuery(query);
			  	if(rset1.next()){
			  		Deal_ENT_BY.add(rset1.getString(1)==null?"":rset1.getString(1));
			  	}else{
			  		Deal_ENT_BY.add("");
			  	}
			  	query="	select to_date('"+tmp_to_dt+"','dd/mm/yyyy') - to_date('"+tmp_from_dt+"','dd/mm/yyyy')+1 from dual";
			  	rset1=stmt1.executeQuery(query);
			  	if(rset1.next()){
			  		Deal_duration.add(rset1.getString(1)==null?"":rset1.getString(1));
			  	}else{
			  		Deal_duration.add("");
			  	}
			  	query="SELECT COUNT(*) FROM FMS8_LNG_REGAS_MST WHERE (sysdate BETWEEN TO_DATE('"+tmp_from_dt+"','dd/mm/yyyy') and TO_DATE('"+tmp_to_dt+"','dd/mm/yyyy')) AND " +
		  				"Agreement_no='"+temp_ag_no+"' and CN_NO='"+temp_regas_no+"' and mapping_id='"+rset.getString(3)+"' and customer_cd='"+temp_cust_cd+"'";
			  	//System.out.println("query........."+query);
			  	rset1=stmt1.executeQuery(query);
			  	if(rset1.next()){
			  		if(rset1.getInt(1)>0){
			  			Deal_sts.add("Active");
			  		}else{
			  		 	String query1="SELECT COUNT(*) FROM FMS8_LNG_REGAS_MST WHERE (sysdate < TO_DATE('"+tmp_from_dt+"','dd/mm/yyyy')) AND " +
		  				"Agreement_no='"+temp_ag_no+"' and CN_NO='"+temp_regas_no+"' and mapping_id='"+rset.getString(3)+"' and customer_cd='"+temp_cust_cd+"'";
				  	//System.out.println("query........."+query1);
			  			rset2=stmt2.executeQuery(query1);
			  			if(rset2.next()){
				  			if(rset2.getInt(1)>0){
					  			Deal_sts.add("Yet To Start");
					  		}else{
					  			Deal_sts.add("InActive");
					  		}
			  			}
			  		}
			  	}
			}			
			
			for(int i=0;i<CN_CUSTOMER_CD.size();i++)
			{
				queryString ="select CUSTOMER_ABBR,CUSTOMER_NAME,CUSTOMER_CD from FMS7_CUSTOMER_MST where CUSTOMER_CD="+CN_CUSTOMER_CD.elementAt(i)+" ";
				rset = stmt.executeQuery(queryString);
//				System.out.println("CN_CUSTOMER_CD = "+queryString);
				if(rset.next())
				{
					Deal_cust_nm.add(rset.getString(2)==null?"":rset.getString(2));
					Deal_cust_abbr.add(rset.getString(1)==null?"":rset.getString(1));
				}
				else
				{
					Deal_cust_nm.add("");
					Deal_cust_abbr.add("");	
				}	
				
				queryString = "SELECT TO_CHAR(A.SIGNING_DT,'DD/MM/YYYY') FROM FMS8_LNG_REGAS_MST A " +
							  "WHERE A.MAPPING_ID='"+CN_MAPPING_ID.elementAt(i)+"' ";
							 			 
//				System.out.println(queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					//CN_SIGNING_DT.add(rset.getString(1)==null?"":rset.getString(1));
					Deal_Signing_dt.add(rset.getString(1)==null?"":rset.getString(1));
				}
				else
				{
					Deal_Signing_dt.add("");
				}
	
//				queryString ="select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL where CUSTOMER_CD="+RE_GAS_CUSTOMER_CD.elementAt(i)+" " +
//							 "AND SN_NO="+RE_GAS_SEQ_NO.elementAt(i)+" AND FGSA_NO="+RE_GAS_NO.elementAt(i)+" " +
//							 "AND GAS_DT between to_date('"+FRM_DT.elementAt(i)+"','dd/mm/yyyy')" +
//							 "AND to_date('"+TO_DT.elementAt(i)+"','dd/mm/yyyy') AND CONTRACT_TYPE='R'";
				queryString ="select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL where CUSTOMER_CD="+CN_CUSTOMER_CD.elementAt(i)+" " +
				 "AND SN_NO="+CN_SEQ_NO.elementAt(i)+" AND FGSA_NO="+CN_NO.elementAt(i)+" " +
				 "AND GAS_DT between to_date('"+FRM_DT.elementAt(i)+"','dd/mm/yyyy')" +
				 "AND to_date('"+to_date+"','dd/mm/yyyy') AND CONTRACT_TYPE='C' AND MAPPING_ID='"+CN_MAPPING_ID.elementAt(i)+"'";

//				System.out.println("CN_QTY_MMBTU = "+queryString);
				rset = stmt.executeQuery(queryString);				
				if(rset.next())
				{
					Deal_qty_Supplied.add(rset.getString(1)==null?"0.00":nf.format(Double.parseDouble(rset.getString(1))));
					//MD20110917
					/*CN_SUPPLIEDMMSCM.add(rset.getString(1)==null?"0.00":nf.format(Double.parseDouble(rset.getString(1))/38900));
					if(rset.getString(1)!=null && !(rset.getString(1).equals("")))
					{
						sup_tot_comit+=Double.parseDouble(rset.getString(1));
						sup_tot_comit_mmscm+=(Double.parseDouble(rset.getString(1))/38900);
					}
					else
					{
						sup_tot_comit+=0;
						sup_tot_comit_mmscm+=0;
					}*/
				}
				else
				{
					Deal_qty_Supplied.add("0.00");
					/*//sup_tot_comit+=0;
					
					CN_SUPPLIEDMMSCM.add("0.00");
					//sup_tot_comit_mmscm+=0;
*/				}	
		   }
			/*CN_sup_tot_comitment=nf.format(sup_tot_comit);
			CN_bal_tot_comitment=nf.format(bal_tot_comit);
			//MD20110917
			CN_sup_tot_comitment_mmscm=nf.format(sup_tot_comit_mmscm);
			CN_bal_tot_comitment_mmscm=nf.format(bal_tot_comit_mmscm);
			//END OF LTCORA AND CN
			System.out.println("CN_sup_tot_comitment_mmscm"+CN_sup_tot_comitment_mmscm);
			System.out.println("CN_bal_tot_comitment_mmscm"+CN_bal_tot_comitment_mmscm);*/
			
		}		
		catch(Exception e)
		{
			  System.out.println("Exception in Databean_Contract_MasterReport--->fetchEligibleCargoList()-->"+e);
			  e.printStackTrace();
		}
	}
	
	
	//TCQ Variation Developed by Achal on 23/03/2011
	public void fetchTCQVariationList()
	{
		//Vector tempMODIFYCLOSURETCQ = new Vector();
		//Vector tempLOA_REV_NO = new Vector();
		Vector tempDt = new Vector();
		try
		{	
			if(Customer_access_flag.equals("Y"))
			{
				queryString ="select TO_CHAR(A.SN_CLOSURE_DT,'DD/MM/YYYY'),A.SN_CLOSURE_CLOSE,A.SN_NO,A.SN_REV_NO," +
						"A.CUSTOMER_CD,A.FGSA_NO,A.TCQ_REQUEST_CLOSE,A.TCQ, " +
						"A.TCQ_REQUEST_QTY,A.SN_CLOSURE_QTY,TO_CHAR(A.REV_DT,'DD/MM/YYYY'),A.TCQ_REQUEST_SIGN,A.START_DT from "
						+ "FMS7_SN_MST A, SEC_EMP_CUSTOMER_ALLOC_MST B " +
						"where (A.REV_DT between to_date('"+from_date+"','dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy') AND A.TCQ_REQUEST_CLOSE='Y')"+ 
						"OR (A.SN_CLOSURE_DT between to_date('"+from_date+"','dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy') AND A.SN_CLOSURE_CLOSE='Y' ) "
						+ " AND A.CUSTOMER_CD=B.CUSTOMER_CD AND B.EMP_CD='"+Emp_cd+"' " +
						"order by A.SN_CLOSURE_DT";
			} else {
				queryString ="select TO_CHAR(A.SN_CLOSURE_DT,'DD/MM/YYYY'),A.SN_CLOSURE_CLOSE,A.SN_NO,A.SN_REV_NO," +
						"A.CUSTOMER_CD,A.FGSA_NO,A.TCQ_REQUEST_CLOSE,A.TCQ, " +
						"A.TCQ_REQUEST_QTY,A.SN_CLOSURE_QTY,TO_CHAR(A.REV_DT,'DD/MM/YYYY'),A.TCQ_REQUEST_SIGN,A.START_DT from FMS7_SN_MST A " +
						"where (A.REV_DT between to_date('"+from_date+"','dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy') AND A.TCQ_REQUEST_CLOSE='Y')"+ 
						"OR (A.SN_CLOSURE_DT between to_date('"+from_date+"','dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy') AND A.SN_CLOSURE_CLOSE='Y' ) " +
						"order by A.SN_CLOSURE_DT";
			}
			
						 
//			//System.out.println(queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{				
				SN_CLOSURE_DT.add(rset.getString(1)==null?rset.getString(11)==null?"":rset.getString(11):rset.getString(1));
				if(rset.getString(2)!=null) //|| !(rset.getString(2).trim().equals("")) )
				{
					if(rset.getString(2).trim().equalsIgnoreCase("Y"))
					{
						SN_CLOSURE_CLOSE.add("Variation due to SN Closure. ");			
					}
					else
					{
						if(rset.getString(7)!=null) // || !(rset.getString(7).trim().equals("")) )
						{
							if(rset.getString(7).trim().equalsIgnoreCase("Y"))
							{
								SN_CLOSURE_CLOSE.add("Variation due to Modification of TCQ. ");
							}
							else
							{
								SN_CLOSURE_CLOSE.add("");
							}
						}
						else
						{
							SN_CLOSURE_CLOSE.add("");
						}
					}
				}
				else
				{
					if(rset.getString(7)!=null) // || !(rset.getString(7).trim().equals("")) )
					{
						if(rset.getString(7).trim().equalsIgnoreCase("Y"))
						{
							SN_CLOSURE_CLOSE.add("Variation due to Modification of TCQ. ");
						}
						else
						{
							SN_CLOSURE_CLOSE.add("");
						}
					}
					else
					{
						SN_CLOSURE_CLOSE.add("");
					}
				}
				SN_NO.add(rset.getString(3)==null?"0":rset.getString(3));
				CUSTOMER_CD.add(rset.getString(5)==null?"0":rset.getString(5));																
				SN_REV_NO.add(rset.getString(4)==null?"0":rset.getString(4));			  	
			  	FGSA_NO.add(rset.getString(6)==null?"0":rset.getString(6));	
			  	BOOKMMBTU.add(rset.getString(8)==null?"0.00":nf.format(Double.parseDouble(rset.getString(8))));
			  	TCQ_REQUEST_QTY.add(rset.getString(9)==null?"0.00":nf.format(Double.parseDouble(rset.getString(9))));
			  	SN_CLOSURE_QTY.add(rset.getString(10)==null?"0.00":nf.format(Double.parseDouble(rset.getString(10))));
			  	TCQ_REQUEST_SIGN.add(rset.getString(12)==null?"-":rset.getString(12));
			  	tempDt.add(rset.getString(13)==null?"":rset.getString(13));
			}	
						
			for(int i=0;i<CUSTOMER_CD.size();i++)
			{
				queryString ="select CUSTOMER_ABBR,CUSTOMER_NAME,CUSTOMER_CD from FMS7_CUSTOMER_MST where CUSTOMER_CD="+CUSTOMER_CD.elementAt(i)+" "
						+ "and eff_dt=(select max(eff_dt) from fms7_customer_mst where customer_cd='"+CUSTOMER_CD.elementAt(i)+"' and eff_dt<=to_date('"+tempDt.elementAt(i)+"','dd/mm/yyyy'))";
				rset = stmt.executeQuery(queryString);
				////System.out.println(queryString);
				if(rset.next())
				{
					CUSTOMER_NM.add(rset.getString(2)==null?"":rset.getString(2));					
				}
				else
				{
					CUSTOMER_NM.add("");
				}
				int sn_rev_no=0;
				if((Integer.parseInt(""+SN_REV_NO.elementAt(i))) > 0)					
				{
					sn_rev_no=Integer.parseInt(""+SN_REV_NO.elementAt(i));
				}
				else
				{
					sn_rev_no=1;
				}
				queryString ="select TCQ from FMS7_SN_MST where " +
				"SN_NO="+SN_NO.elementAt(i)+" AND " +
				//"SN_REV_NO='"+(sn_rev_no - 1)+"' AND " +
				"SN_REV_NO='0' AND " +
				"CUSTOMER_CD="+CUSTOMER_CD.elementAt(i)+" AND " +
				"FGSA_NO="+FGSA_NO.elementAt(i)+" ";
//				//System.out.println(queryString);
				rset = stmt.executeQuery(queryString);				
				if(rset.next())
				{
					ACTUAL_TCQ.add(rset.getString(1)==null?"0.00":nf.format(Double.parseDouble(rset.getString(1))));
				}
				else
				{
					ACTUAL_TCQ.add("0.00");
				}
		   }
			
		    //--------Logic for Tender and LOA based Summary Contract based Agreement	
			Vector tempDt1 = new Vector();
			if(Customer_access_flag.equals("Y"))
			{
				queryString ="select TO_CHAR(A.LOA_CLOSURE_DT,'DD/MM/YYYY'),A.LOA_CLOSURE_CLOSE,A.LOA_NO,A.LOA_REV_NO,A.CUSTOMER_CD,A.TENDER_NO,A.TCQ, " +
				 		"A.TCQ_REQUEST_QTY,A.TCQ_REQUEST_SIGN,A.START_DT from FMS7_LOA_MST A, SEC_EMP_CUSTOMER_ALLOC_MST B "
				 		+ "where (A.START_DT between to_date('"+from_date+"','dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy')"+ 
				 		"OR A.END_DT between to_date('"+from_date+"','dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy')) AND " +
				 		"A.LOA_CLOSURE_CLOSE='Y' AND A.CUSTOMER_CD=B.CUSTOMER_CD AND B.EMP_CD='"+Emp_cd+"' "
				 		+ "order by A.LOA_CLOSURE_DT";
			} else {
				queryString ="select TO_CHAR(A.LOA_CLOSURE_DT,'DD/MM/YYYY'),A.LOA_CLOSURE_CLOSE,A.LOA_NO,A.LOA_REV_NO,A.CUSTOMER_CD,A.TENDER_NO,A.TCQ, " +
				 		"A.TCQ_REQUEST_QTY,A.TCQ_REQUEST_SIGN,A.START_DT from FMS7_LOA_MST A where (A.START_DT between to_date('"+from_date+"','dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy')"+ 
				 		"OR A.END_DT between to_date('"+from_date+"','dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy')) AND " +
				 		"A.LOA_CLOSURE_CLOSE='Y' order by A.LOA_CLOSURE_DT";
			}
						 			
//			//System.out.println(queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{				
				LOA_NO.add(rset.getString(3)==null?"0":rset.getString(3));
				LOA_CUSTOMER_CD.add(rset.getString(5)==null?"0":rset.getString(5));													
				LOA_CARGO_ARRIVAL_DATE.add(rset.getString(1)==null?"":rset.getString(1));				
				LOA_REV_NO.add(rset.getString(4)==null?"0":rset.getString(4));			  	
			  	TENDER_NO.add(rset.getString(6)==null?"0":rset.getString(6));				  	
			  	LOA_BOOKMMBTU.add(rset.getString(7)==null?"0.00":nf.format(Double.parseDouble(rset.getString(7))));				  	
			  	LOA_TCQ_REQUEST_QTY.add(rset.getString(8)==null?"0.00":nf.format(Double.parseDouble(rset.getString(8))));
			  	LOA_TCQ_REQUEST_SIGN.add(rset.getString(9)==null?"":rset.getString(9));
			  	tempDt1.add(rset.getString(10)==null?"":rset.getString(10));
			}
			
			for(int i=0;i<LOA_CUSTOMER_CD.size();i++)
			{
//				queryString ="select CUSTOMER_ABBR,CUSTOMER_NAME,CUSTOMER_CD from FMS7_CUSTOMER_MST where CUSTOMER_CD="+LOA_CUSTOMER_CD.elementAt(i)+" ";	
				queryString ="select CUSTOMER_ABBR,CUSTOMER_NAME,CUSTOMER_CD from FMS7_CUSTOMER_MST where CUSTOMER_CD="+LOA_CUSTOMER_CD.elementAt(i)+" "
						+ "and eff_dt=(select max(eff_dt) from fms7_customer_mst where customer_cd='"+LOA_CUSTOMER_CD.elementAt(i)+"' and eff_dt<=to_date('"+tempDt.elementAt(i)+"','dd/mm/yyyy'))";
//				//System.out.println("LOA_CUSTOMER_CD = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					LOA_CUSTOMER_NM.add(rset.getString(2)==null?"":rset.getString(2));					
				}
				else
				{
					LOA_CUSTOMER_NM.add("");
				}
				int loa_rev_no=0;
				if((Integer.parseInt(""+LOA_REV_NO.elementAt(i))) > 0)					
				{
					loa_rev_no=Integer.parseInt(""+LOA_REV_NO.elementAt(i));
				}
				else
				{
					loa_rev_no=1;
				}				
				queryString ="select TCQ from FMS7_LOA_MST where " +
				"LOA_NO="+LOA_NO.elementAt(i)+" AND " +
				//"LOA_REV_NO='"+(loa_rev_no - 1)+"' AND " +
				"LOA_REV_NO='0' AND " +
				"CUSTOMER_CD="+LOA_CUSTOMER_CD.elementAt(i)+" AND " +
				"TENDER_NO="+TENDER_NO.elementAt(i)+" ";
//				//System.out.println(queryString);
				rset = stmt.executeQuery(queryString);				
				if(rset.next())
				{
					LOA_ACTUAL_TCQ.add(rset.getString(1)==null?"0.00":nf.format(Double.parseDouble(rset.getString(1))));
				}
				else
				{
					LOA_ACTUAL_TCQ.add("0.00");
				}
		   }
		}		
		catch(Exception e)
		{
			  //System.out.println("Exception in Databean_Contract_MasterReport--->fetchTCQVariationList()-->"+e);
			  e.printStackTrace();
		}
	}
	public void fetchcustomer()
	{
		try
		{
			queryString ="select CUSTOMER_ABBR,CUSTOMER_NAME,CUSTOMER_CD from FMS7_CUSTOMER_MST";
			rset = stmt.executeQuery(queryString);
			//System.out.println(queryString);
			while(rset.next())
			{
				CUST_CD.add(rset.getString(3)==null?"":rset.getString(3));
				CUST_NM.add(rset.getString(1)==null?"":rset.getString(1));					
			}
			
		}
		catch(Exception e)
		{
			//System.out.println("Exception in Databean_Contract_MasterReport--->fetchcustomer()-->"+e);
			  e.printStackTrace();	
		}
	}
	public void fetchfgsaList()
	{
		try
		{
			
			if(!cust_nm.equalsIgnoreCase("0"))
			{
		//	queryString ="select CUSTOMER_CD,FGSA_NO,TO_CHAR(SIGNING_DT,'DD/MM/YYYY'),TO_CHAR(START_DT,'DD/MM/YYYY'),TO_CHAR(END_DT,'DD/MM/YYYY'),REV_NO,TO_CHAR(REV_DT,'DD/MM/YYYY'),TO_CHAR(RENEWAL_DT,'DD/MM/YYYY'),EMP_CD from FMS7_FGSA_MST WHERE (START_DT between to_date('"+from_date+"','dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy') " +
		//		"or END_DT between to_date('"+from_date+"','dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy')) and CUSTOMER_CD='"+cust_nm+"' order by START_DT";
		  
			queryString ="select CUSTOMER_CD,FGSA_NO,TO_CHAR(SIGNING_DT,'DD/MM/YYYY'),TO_CHAR(START_DT,'DD/MM/YYYY'),TO_CHAR(END_DT,'DD/MM/YYYY'),REV_NO,TO_CHAR(REV_DT,'DD/MM/YYYY'),TO_CHAR(REN_DT,'DD/MM/YYYY'),EMP_CD from FMS7_FGSA_MST WHERE (START_DT <= to_date('"+to_date+"','dd/mm/yyyy') " +
				"AND END_DT >= to_date('"+from_date+"','dd/mm/yyyy')) and CUSTOMER_CD='"+cust_nm+"' order by START_DT";
			
			}
			else
			{
			queryString ="select CUSTOMER_CD,FGSA_NO,TO_CHAR(SIGNING_DT,'DD/MM/YYYY'),TO_CHAR(START_DT,'DD/MM/YYYY'),TO_CHAR(END_DT,'DD/MM/YYYY'),REV_NO,TO_CHAR(REV_DT,'DD/MM/YYYY'),TO_CHAR(REN_DT,'DD/MM/YYYY'),EMP_CD from FMS7_FGSA_MST WHERE START_DT <= to_date('"+to_date+"','dd/mm/yyyy') " +
				"AND END_DT >= to_date('"+from_date+"','dd/mm/yyyy')  order by START_DT";
		
			}
			//System.out.println(queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				 CUSTOMER_CD_FGSA.add(rset.getString(1)==null?"0":rset.getString(1));
				 FGSA_NO_NEW.add(rset.getString(2)==null?"0":rset.getString(2));
				 SIGNING_DT_FGSA.add(rset.getString(3)==null?"":rset.getString(3));
				 START_DT_FGSA.add(rset.getString(4)==null?"":rset.getString(4));
				 END_DT_FGSA.add(rset.getString(5)==null?"":rset.getString(5));
				 REV_NO_FGSA.add(rset.getString(6)==null?"0":rset.getString(6));
				 REV_DT_FGSA.add(rset.getString(7)==null?"":rset.getString(7));
				 RENEWAL_DT_FGSA.add(rset.getString(8)==null?"":rset.getString(8));
				 EMP_CD_FGSA.add(rset.getString(9)==null?"0":rset.getString(9));
				 
							 
			}
			for(int i=0;i<CUSTOMER_CD_FGSA.size();i++)
			{
				String t2="";
			 queryString ="select PLANT_SEQ_NO FROM FMS7_FGSA_PLANT_MST WHERE CUSTOMER_CD='"+CUSTOMER_CD_FGSA.elementAt(i)+"' AND FGSA_NO='"+FGSA_NO_NEW.elementAt(i)+"' AND REV_NO='"+REV_NO_FGSA.elementAt(i)+"'";
			 //System.out.println(queryString);
			 rset = stmt.executeQuery(queryString);
			 while(rset.next())
				{
				 
				 String t1=rset.getString(1)==null?"":rset.getString(1);
				
				 queryString ="Select A.PLANT_NAME from FMS7_CUSTOMER_PLANT_DTL A WHERE A.CUSTOMER_CD='"+CUSTOMER_CD_FGSA.elementAt(i)+"' and A.EFF_DT=(select max(B.eff_dt) from FMS7_CUSTOMER_PLANT_DTL B where A.seq_no=B.seq_no and B.CUSTOMER_CD='"+CUSTOMER_CD_FGSA.elementAt(i)+"') AND A.SEQ_NO='"+t1+"' order by A.SEQ_NO";
				 //System.out.println(queryString);
				 rset1 = stmt1.executeQuery(queryString);
				 while(rset1.next())
				 {
					 String t3=rset1.getString(1)==null?"":rset1.getString(1); 
					 t2=t2+t3+", ";
				 }
				 
				 
				}
			 DELV_POINT.add(t2); 
			}
			for(int i=0;i<CUSTOMER_CD_FGSA.size();i++)
			{
				queryString ="select CUSTOMER_ABBR,CUSTOMER_NAME,CUSTOMER_CD from FMS7_CUSTOMER_MST where CUSTOMER_CD="+CUSTOMER_CD_FGSA.elementAt(i)+" ";
				rset = stmt.executeQuery(queryString);
				////System.out.println(queryString);
				if(rset.next())
				{
					CUSTOMER_ABR_FGSA.add(rset.getString(2)==null?"":rset.getString(2));					
				}
				else
				{
					CUSTOMER_ABR_FGSA.add("");
				}	
			}
			for(int i=0;i<EMP_CD_FGSA.size();i++)
			{
				queryString ="select EMP_ABR,EMP_NM from HR_EMP_MST where EMP_CD="+EMP_CD_FGSA.elementAt(i)+" ";
				rset = stmt.executeQuery(queryString);
				////System.out.println(queryString);
				if(rset.next())
				{
					EMP_ABR_FGSA.add(rset.getString(2)==null?"":rset.getString(2));					
				}
				else
				{
					EMP_ABR_FGSA.add("-");
				}	
			}
		}
		catch(Exception e)
		{
			  //System.out.println("Exception in Databean_Contract_MasterReport--->fetchfgsaList()-->"+e);
			  e.printStackTrace();
		}
	}
	
	public void fetchFGSASNPRICEList()
	{
		try
		{
			Vector sn_no_tmp = new Vector();
			
			if(!cust_nm.equalsIgnoreCase("0") && !from_date.trim().equalsIgnoreCase("") && !to_date.trim().equalsIgnoreCase("")&& !from_date.equals(null) && !to_date.equals(null))
			{
		  
				queryString ="select A.CUSTOMER_CD, A.SN_NO, A.SN_REV_NO, A.FGSA_NO, A.FGSA_REV_NO," +
							" TO_CHAR(A.START_DT,'DD/MM/YYYY'), TO_CHAR(A.END_DT,'DD/MM/YYYY')," +
							" A.RATE, B.CARGO_REF_NO,  B.SALE_PRICE, B.COST_PRICE, B.MARGIN, A.sn_ref_no " +
							" from FMS7_SN_MST A, FMS7_SN_CARGO_DTL B" +
							" WHERE (A.START_DT <= to_date('"+to_date+"','dd/mm/yyyy') " +
							" AND A.END_DT >= to_date('"+from_date+"','dd/mm/yyyy')) and A.CUSTOMER_CD='"+cust_nm+"' " +
							" AND A.FGSA_NO = B.FGSA_NO AND A.FGSA_REV_NO = B.FGSA_REV_NO " +
							" AND A.SN_NO = B.SN_NO AND A.SN_REV_NO = B.SN_REV_NO" +
							" order by A.FGSA_NO, A.FGSA_REV_NO, A.SN_NO, A.SN_REV_NO";
				
			
			}
			else if(!from_date.trim().equalsIgnoreCase("") && !to_date.trim().equalsIgnoreCase("")&& !from_date.equals(null) && !to_date.equals(null))
			{
				queryString ="select A.CUSTOMER_CD, A.SN_NO, A.SN_REV_NO, A.FGSA_NO, A.FGSA_REV_NO," +
							" TO_CHAR(A.START_DT,'DD/MM/YYYY'), TO_CHAR(A.END_DT,'DD/MM/YYYY')," +
							" A.RATE, B.CARGO_REF_NO,  B.SALE_PRICE, B.COST_PRICE, B.MARGIN, A.sn_ref_no " +
							" from FMS7_SN_MST A, FMS7_SN_CARGO_DTL B" +
							" WHERE (A.START_DT <= to_date('"+to_date+"','dd/mm/yyyy') " +
							" AND A.END_DT >= to_date('"+from_date+"','dd/mm/yyyy')) " +
							" AND A.FGSA_NO = B.FGSA_NO AND A.FGSA_REV_NO = B.FGSA_REV_NO " +
							" AND A.SN_NO = B.SN_NO AND A.SN_REV_NO = B.SN_REV_NO" +
							" order by A.customer_cd,A.FGSA_NO, A.FGSA_REV_NO, A.SN_NO, A.SN_REV_NO";
				
		
			}
			//System.out.println(queryString);
			rset = stmt.executeQuery(queryString);
			String sn_no = "";
			String sn_ref_no = "";
			while(rset.next())
			{
				CUSTOMER_CD_SN_P.add(rset.getString(1)==null?"0":rset.getString(1));
				sn_no_tmp.add(rset.getString(2)==null?"0":rset.getString(2));
			//	SN_NO_P.add(rset.getString(2)==null?"0":rset.getString(2));
				SN_REV_NO_P.add(rset.getString(3)==null?"0":rset.getString(3));
				FGSA_NO_P.add(rset.getString(4)==null?"0":rset.getString(4));
				FGSA_REV_NO_P.add(rset.getString(5)==null?"0":rset.getString(5));
				START_DT_SN_P.add(rset.getString(6)==null?"-":rset.getString(6));
				END_DT_SN_P.add(rset.getString(7)==null?"-":rset.getString(7));
				RATE_SN_P.add(rset.getString(8)==null?"-":rset.getString(8));
				CARGO_REF_NO_SN_P.add(rset.getString(9)==null?"-":rset.getString(9));
				CARGO_MARGIN_SN_P.add(rset.getString(12)==null?"-":rset.getString(12));
				
				sn_no = rset.getString(2)==null?"0":rset.getString(2);
				sn_ref_no = rset.getString(13)==null?"0":rset.getString(13);
				
				if(!sn_ref_no.equals("0"))
				{
					SN_NO_P.add(sn_ref_no);
				}
				else
				{
					SN_NO_P.add(sn_no);
				}
				
			}

			for(int i=0;i<CUSTOMER_CD_SN_P.size();i++)
			{
				queryString ="select CUSTOMER_ABBR,CUSTOMER_NAME,CUSTOMER_CD from FMS7_CUSTOMER_MST where CUSTOMER_CD="+CUSTOMER_CD_SN_P.elementAt(i)+" ";
				rset = stmt.executeQuery(queryString);
				////System.out.println(queryString);
				if(rset.next())
				{
					CUSTOMER_ABR_SN_P.add(rset.getString(2)==null?"":rset.getString(2));					
				}
				else
				{
					CUSTOMER_ABR_SN_P.add("");
				}	
			}
			
			for(int i=0; i<sn_no_tmp.size(); i++)
			{
				
				queryString = "select PRICE, TO_CHAR(DELV_TO_DT,'DD/MM/YYYY')"+
				   		"FROM FMS7_MAN_CONFIRM_CARGO_DTL WHERE " +
				   		"CARGO_REF_CD='"+cargo_ref_no+"'";
				
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					CARGO_CONF_PRICE_DT_SN_P.add(rset.getString(1)==null?"":rset.getString(1));
					String conf_price = rset.getString(1)==null?"0":rset.getString(1);
					String delv_to_dt = rset.getString(2)==null?"":rset.getString(2);
					////System.out.println("OK 1 "+CARGO_REF_NO_SN_P.elementAt(i));
//					Following Logic Has Been Introduced To Calculate Custom Tax Amount ...
				  	String tax_amt = "";
				  	String tax_str_cd = "0";
				  	double cd_charge_per_mmbtu = 0;
					
					String queryString1 = "SELECT A.TAX_STR_CD FROM FMS7_CARGO_TAX_MST A " +
								  		  "WHERE A.APP_DATE=(SELECT MAX(B.APP_DATE) FROM FMS7_CARGO_TAX_MST B " +
								  		  "WHERE B.APP_DATE<=TO_DATE('"+delv_to_dt+"','DD/MM/YYYY'))";
					////System.out.println("Custom Duty Details Query = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						
						tax_str_cd = rset1.getString(1)==null?"0":rset1.getString(1);
					}
					
					////System.out.println("tax_str_cd = "+tax_str_cd);
					
					queryString1 = "SELECT NVL(A.tax_code,'0'), NVL(A.factor,'0.00'), NVL(A.tax_on,'1'), NVL(A.tax_on_cd,'0'), " +
								   "TO_CHAR(A.app_date,'DD/MM/YYYY') FROM FMS7_TAX_STRUCTURE_DTL A WHERE A.tax_str_cd="+tax_str_cd+" AND " +
								   "A.app_date=(SELECT MAX(B.app_date) FROM FMS7_TAX_STRUCTURE_DTL B WHERE B.tax_str_cd="+tax_str_cd+" AND " +
								   "B.app_date<=TO_DATE('"+delv_to_dt+"','DD/MM/YYYY')) ORDER BY A.tax_code DESC";
					
					rset1=stmt1.executeQuery(queryString1);
					while(rset1.next())
					{
						if(rset1.getString(3).equals("1"))
						{
							////System.out.println("OK 2 "+CARGO_REF_NO_SN_P.elementAt(i));
							tax_amt = nf2.format((Double.parseDouble(""+conf_price)*Double.parseDouble(rset1.getString(2)))/100);
							////System.out.println("OK 2.1 "+CARGO_REF_NO_SN_P.elementAt(i));
						}
						else if(rset1.getString(3).equals("2"))
						{
							String queryString2 = "SELECT NVL(A.tax_code,'0'), NVL(A.factor,'0'), NVL(A.tax_on,'1'), NVL(A.tax_on_cd,'0'), " +
										   		  "TO_CHAR(A.app_date,'DD/MM/YYYY') FROM FMS7_TAX_STRUCTURE_DTL A WHERE A.tax_str_cd="+tax_str_cd+" AND " +
										   		  "A.app_date=(SELECT MAX(B.app_date) FROM FMS7_TAX_STRUCTURE_DTL B WHERE B.tax_str_cd="+tax_str_cd+" AND " +
										   		  "B.app_date<=TO_DATE('"+delv_to_dt+"','DD/MM/YYYY')) AND A.tax_code="+rset1.getString(4)+"";
							////System.out.println("Query For Finding Out Tax Which Is Dependent On Other Tax Value = "+queryString2);
					 		rset2=stmt2.executeQuery(queryString2);
					 		if(rset2.next())
					 		{
					 			if(rset2.getString(3).equals("1"))
								{
					 				////System.out.println("OK 3 "+CARGO_REF_NO_SN_P.elementAt(i));
									tax_amt = ""+(Double.parseDouble(""+conf_price)*Double.parseDouble(rset2.getString(2))/100);
								}
								
					 			tax_amt = ""+(Double.parseDouble(tax_amt)*Double.parseDouble(rset1.getString(2)))/100;
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
						////System.out.println("OK 4 "+CARGO_REF_NO_SN_P.elementAt(i));
						cd_charge_per_mmbtu += Double.parseDouble(tax_amt);
						
					}
					
					////System.out.println("OK 5 "+CARGO_REF_NO_SN_P.elementAt(i));
					CARGO_CUSTOM_DUTY_SN_P.add(nf2.format(cd_charge_per_mmbtu));
				}
				else
				{
					CARGO_CUSTOM_DUTY_SN_P.add("-");
					CARGO_CONF_PRICE_DT_SN_P.add("-");
				}
				
				
			}
			
			int j =1;
			for(int i = 0; i<CARGO_REF_NO_SN_P.size()-1; i++)
			{
				
				if(sn_no_tmp.elementAt(i).toString().equals(sn_no_tmp.elementAt(j).toString()) && 
						FGSA_NO_P.elementAt(i).toString().equals(FGSA_NO_P.elementAt(j).toString()) && 
						FGSA_REV_NO_P.elementAt(i).toString().equals(FGSA_REV_NO_P.elementAt(j).toString()))
					{
						if(!RATE_SN_P.elementAt(i).toString().equals(RATE_SN_P.elementAt(j).toString()))
						{
							COLOR_FLAG_RATE_SN_P.add("red");
						}
						else
						{
							COLOR_FLAG_RATE_SN_P.add("");
						}
					}
				else
				{
					COLOR_FLAG_RATE_SN_P.add("");
					
				}
					j++;
				
			}
			COLOR_FLAG_RATE_SN_P.add("");
			
			
			//System.out.println(CUSTOMER_CD_SN_P.size());
			//System.out.println(CUSTOMER_ABR_SN_P.size());
			//System.out.println(SN_REV_NO_P.size());
			//System.out.println(FGSA_NO_P.size());
			//System.out.println(FGSA_REV_NO_P.size());
			//System.out.println(START_DT_SN_P.size());
			//System.out.println(END_DT_SN_P.size());
			//System.out.println(RATE_SN_P.size());
			//System.out.println(CARGO_REF_NO_SN_P.size());
			//System.out.println(CARGO_CONF_PRICE_DT_SN_P.size());
			//System.out.println(CARGO_CUSTOM_DUTY_SN_P.size());
			//System.out.println(CARGO_MARGIN_SN_P.size());
			//System.out.println(COLOR_FLAG_CONF_PRICE_SN_P.size());
			//System.out.println(COLOR_FLAG_CUSTOM_SN_P.size());
			//System.out.println(COLOR_FLAG_CUSTOM_SN_P);
			
		
		}
		catch(Exception e)
		{
			  //System.out.println("Exception in Databean_Contract_MasterReport--->fetchFGSASNPRICEList()-->"+e);
			  e.printStackTrace();
		}
	}	
	/////////////////////////Find Out Price Out for JKM ////////////////////////
	public void MRCR_PriceOut2(String ReportDt, String INDEX_TYPE, String GenDate, String MappId, String ContRate, String ContinueReducePerc, int ContinueReducePercDay)
	{
		try
		{
	////Price Pulling from Table////////////////////////////
	String tempGen_Dt[]=GenDate.split("/");
	String GenMth=tempGen_Dt[1]; String GenYr=tempGen_Dt[2]; 
	//String GenMthYr=GenDate; 
	String GenMthYr=GenMth+"/"+GenYr;
	//INDEX_TYPE="JKM";
	String LNG_ICE_JKM_PriceOut="0"; String Phy_CurFrd_Price="";

	String Slope=""; String Constant="";
	
		////////Price Pulling from Table////////////////////////////
	String PriceType="F"; String CurveName=""; 
	String UserAvgPriceStartDt="";//SB20201128
	String UserAvgPriceEndDt="";//SB20210511
	PPACPriceFlag="0";
	queryString ="SELECT PRICE_TYPE, CURVE_NM, RATE, SEQ_NO, PRICE_RANGE, TO_CHAR(PRICE_START_DT,'DD/MM/YYYY'), PHYS_CURVE_NM, TO_CHAR(PRICE_END_DT,'DD/MM/YYYY') FROM FMS9_MRCR_CONT_PRICE_DTL "  //SB20210504
	//SB20210528		+ "WHERE MAPPING_ID='"+MappId+"' AND FROM_DT<=TO_DATE('"+GenDate+"','DD/MM/YYYY') AND TO_DT>=TO_DATE('"+GenDate+"','DD/MM/YYYY') AND FLAG='Y' ";//AND CURVE_NM='"+INDEX_TYPE+"' ";
	+ "WHERE MAPPING_ID LIKE '"+MappId+"' AND FROM_DT<=TO_DATE('"+GenDate+"','DD/MM/YYYY') AND TO_DT>=TO_DATE('"+GenDate+"','DD/MM/YYYY') AND FLAG='Y' ";//SB20210528
//	System.out.println("FMS9_CURVE_PRICE_DTL: "+queryString);
	rset2 = stmt.executeQuery(queryString);			
	if(rset2.next())
	{
		//VPriceTypeDeal.add(rset2.getString(1)==null?"":rset2.getString(1));  //CR-DATED_BRENT
		PriceType=rset2.getString(1)==null?"":rset2.getString(1);
		if(PriceType.equals("F"))
		{
			VPriceTypeDeal.add("Fixed");
			VPriceTypeDealRate.add(rset2.getString(3)==null?ContRate:rset2.getString(3));  //CR-DATED_BRENT
		}
		else if(PriceType.equals("M"))
		{
			VPriceTypeDeal.add("Float");
		//SB20210505	VPriceTypeDealRate.add(rset2.getString(3)==null?"":rset2.getString(3));  //CR-DATED_BRENT
///////////////SB20210504: PPAC Logic/////////////////////////
		//System.out.println(INDEX_TYPE+":INDEX_TYPE: "+rset2.getString(7));
		PPACPriceFlag=rset2.getString(3)==null?"0":rset2.getString(3);//SB20210506 System.out.println(INDEX_TYPE+":PPACPriceFlag: "+PPACPriceFlag
			priceSettle=rset2.getString(5)==null?"A":rset2.getString(5);  //SB20201128: Dynamically picked up for Avg. Calculation (priceSettle=Global Variable from JSP)
			UserAvgPriceStartDt=rset2.getString(6)==null?"":rset2.getString(6);  //SB20201128: Dynamically picked up the start date for Avg. Calculation (UserAvgPriceStartDt=PRICE_START_DT from Table)
			UserAvgPriceEndDt=rset2.getString(8)==null?"":rset2.getString(8);  //SB20201128: Dynamically picked up the End date for Avg. Calculation (UserAvgPriceEndDt=PRICE_END_DT from Table)
		///	PPAC_Price=rset2.getString(3)==null?"0":rset2.getString(3); //SB20210416
		}
		else
		{
			VPriceTypeDeal.add("N.A.");
			VPriceTypeDealRate.add(rset2.getString(3)==null?"":rset2.getString(3));  //CR-DATED_BRENT
		}
		INDEX_TYPE=rset2.getString(2)==null?"":rset2.getString(2);
		
	//	VCurve_Nm.add(rset2.getString(2)==null?"JKM":rset2.getString(2));  //CR-DATED_BRENT
		VPriceTypeDealSeQNo.add(rset2.getString(4));
	}
	else
	{
			VPriceTypeDeal.add("FIXED");
	//	INDEX_TYPE=rset2.getString(2)==null?"":rset2.getString(2);
		VPriceTypeDealRate.add(ContRate);  //Contract Rate SN_MST
		VPriceTypeDealSeQNo.add("0");

	//	VCurve_Nm.add(rset2.getString(2)==null?"JKM":rset2.getString(2));  //CR-DATED_BRENT
	}
	//System.out.println("VPriceTypeDealRate: "+VPriceTypeDealRate);
		/*SB20210330	queryString ="SELECT SLOPE, CONST FROM FMS9_MRCR_CONT_PRICE_DTL "
					+ "WHERE  MAPPING_ID='"+MappId+"' AND FROM_DT<=TO_DATE('"+GenDate+"','DD/MM/YYYY') AND TO_DT>=TO_DATE('"+GenDate+"','DD/MM/YYYY') AND PRICE_TYPE='"+PriceType+"' AND CURVE_NM='"+INDEX_TYPE+"' ";
			*/
			queryString ="SELECT SLOPE, CONST FROM FMS9_MRCR_CONT_PRICE_DTL "
		//SB20210528			+ "WHERE  MAPPING_ID='"+MappId+"' AND TO_DATE(TO_DATE('"+GenDate+"','DD/MM/YYYY'),'DD-MON-YY') >=TO_DATE(FROM_DT,'DD-MON-YY') AND TO_DATE(TO_DATE('"+GenDate+"','DD/MM/YYYY'),'DD-MON-YY') <=TO_DATE(TO_DT,'DD-MON-YY') AND PRICE_TYPE='"+PriceType+"' AND CURVE_NM='"+INDEX_TYPE+"' ";
			+ "WHERE  MAPPING_ID LIKE '"+MappId+"' AND TO_DATE(TO_DATE('"+GenDate+"','DD/MM/YYYY'),'DD-MON-YY') >=TO_DATE(FROM_DT,'DD-MON-YY') AND TO_DATE(TO_DATE('"+GenDate+"','DD/MM/YYYY'),'DD-MON-YY') <=TO_DATE(TO_DT,'DD-MON-YY') AND PRICE_TYPE='"+PriceType+"' AND CURVE_NM='"+INDEX_TYPE+"' "; //SB20210528
		//	System.out.println("FMS9_MRCR_CONT_PRICE_DTL: "+queryString);
			rset2 = stmt.executeQuery(queryString);			
			if(rset2.next())
			{
			///	VSlope_BRENT.add("");  //CR-DATED_BRENT
				VSlope_ICE_JKM.add(rset2.getString(1)==null?"1":rset2.getString(1));  //LNG_ICE_JKM
			///	VSlope_JKM.add("");  //LNG_JKM
			///	VSlope_LNG_PHYS.add("");  //LNG_PHYS_INDIA
			///	VSlope_RLNG_PHYS.add("");  //RLNG_PHYS_INDIA
				VSlope.add(rset2.getString(1)==null?"1":rset2.getString(1));  //Default: LNG_ICE_JKM 
				Slope=rset2.getString(1)==null?"1":rset2.getString(1);
				VSlope_FLAG.add("Y");
				Slope_ICE_JKM=Slope;
				Slope_FLAG="Y";
				
				Const_ICE_JKM=rset2.getString(2)==null?"0":rset2.getString(2); 
		///		Const_RLNG_PHYS=rset2.getString(2)==null?"0":rset2.getString(2);
				Const_ICE_JKM_FLAG="Y";
				VConst_ICE_JKM.add(Const_ICE_JKM);  //ICE_JKM_FORWARD
				VConst_RLNG_PHYS.add(Const_RLNG_PHYS);  //RLNG_PHYS_FORWARD
				Constant=rset2.getString(2)==null?"0":rset2.getString(2);
				VConst_ICE_JKM_FLAG.add(Const_ICE_JKM_FLAG);
				Constant=Const_ICE_JKM;
				//SB20210429	if(PriceType.equals("F"))
				//SB20210429	 VMthYrWise.add("");
					//SB20210429else
					//SB20210429	VMthYrWise.add(GenMthYr);
				VMthYrWise.add(GenMthYr); //SB20210429
			}
			else
			{
			///	VSlope_BRENT.add("1.0");
				VSlope_ICE_JKM.add("1.0");
			///	VSlope_JKM.add("1.0");
			///	VSlope_LNG_PHYS.add("1.0");
			///	VSlope_RLNG_PHYS.add("1.0");
			//SB20201102	VSlope.add("1.0");
				VSlope.add("1"); //SB20201102
				Slope="1";
				VSlope_FLAG.add("N");
				Slope_ICE_JKM=Slope;
				Slope_FLAG="N";
				
				VConst_ICE_JKM.add("0"); 
			///	VConst_RLNG_PHYS.add("0");
				Constant="0";
				VConst_ICE_JKM_FLAG.add("N");
				Const_ICE_JKM="0"; 
				Const_RLNG_PHYS="0";
				Const_ICE_JKM_FLAG="N";
				//SB20210429		if(Cont_Type.equals("O")) {VMthYrWise.add(GenMthYr);}//SB20210203
				//SB20210429	VMthYrWise.add("");
				VMthYrWise.add(GenMthYr); //SB20210429
			}
		//System.out.println(VPriceTypeDealRate.size()+">>>>>>>VPriceTypeDealRate: "+PPAC_Price);
	///////////////////////////SB20201006: New Logic To Calculate Pricinging Start-End Date and No. of Days in between ///////////////////////////////		
		//	String IndexSettlePrice="F";  //Price as on Final Settlement Date
			String IndexSettlePrice=priceSettle;//"A";  //Average Between Start and end date excluding Holidays & Weekly off as Price Settlement Date
			int NoOfDays=1; String IndexSettlePriceAvgStr="";
			if(IndexSettlePrice.length()>1)
			{
				NoOfDays=Integer.parseInt(IndexSettlePrice.substring(1,IndexSettlePrice.length())); //System.out.println("NoOfDays Avg. Days: "+NoOfDays);
				IndexSettlePriceAvgStr="A"+NoOfDays;
			}
		//	System.out.println(GenDate+">>>>>>>INDEX_TYPE: "+INDEX_TYPE);	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
			///////////////////////////////HOLIDAY CALCULATION///////////////////////////////////
			String HolidayType="";
	//SB20210402		if(INDEX_TYPE.equals("LNG_ICE_JKM"))
	//SB20210407		if(INDEX_TYPE.equals("LNG_ICE_JKM") || INDEX_TYPE.equals("JKM_PLATTS_SETTLEMENT")) //SB20210402
			if(INDEX_TYPE.equals("LNG_ICE_JKM") || INDEX_TYPE.equals("JKM_PLATTS_SETTLEMENT") || INDEX_TYPE.equals("ICE_JKM")) //SB20210402
				HolidayType="J";
	//SB20210407		else if(INDEX_TYPE.equals("CR_DATED_BRENT") || INDEX_TYPE.equals("ICE_BRENT_FIRST_LINE")) //SB20210402 //SB20201025
			else if(INDEX_TYPE.equals("CR_DATED_BRENT") || INDEX_TYPE.equals("ICE_BRENT_FIRST_LINE")  || INDEX_TYPE.equals("ICE_BRENT")) //SB20210407 //SB20210402 //SB20201025
				HolidayType="B";
			else
				HolidayType="J";
				
	/////////////////////SB20201116: Prev Settlement Date: to find the start date for Contract Month////////////////////////////////////////
			String PrevContMthYr="01/"+GenMthYr;
			String ContMthYr2="01/"+GenMthYr;
			queryString = "SELECT TO_CHAR(TO_DATE('"+ContMthYr2+"','DD/MM/YYYY')-1,'DD/MM/YYYY') FROM DUAL";
			//	System.out.println("Get DAY Name: "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					PrevContMthYr = rset.getString(1)==null?"":rset.getString(1); //SB20201128;
				}
				
				String tempPrevContMthYr[]=PrevContMthYr.split("/");
				PrevContMthYr="01/"+tempPrevContMthYr[1]+"/"+tempPrevContMthYr[2];
		
			String ContMthSettleDt="";
			queryString = "SELECT TO_CHAR(SETTLE_DT,'DD/MM/YYYY'), TO_CHAR(SETTLE_END_DT,'DD/MM/YYYY') FROM FMS9_SETTLE_CALND_DTL "
			//SB20210708		+ "WHERE CONT_MM_YYYY=TO_DATE('"+ContMthYr2+"','DD/MM/YYYY') AND SETTLE_TYPE='"+HolidayType+"' ";
			+ "WHERE CONT_MM_YYYY=TO_DATE('"+ContMthYr2+"','DD/MM/YYYY') AND CURVE_NM='"+INDEX_TYPE+"' ";
			//	System.out.println(" SETTLE: FMS9_SETTLE_CALND_DTL: "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next()) {
					ContMthSettleDt = rset.getString(1);		
				}
				else //SB20210425
					ContMthSettleDt = ContMthYr2; //SB20210425
				String PrevMthSettleEndDtPlusOne="";
				if(IndexSettlePrice.equals("A"))
				{
					queryString = "SELECT TO_CHAR(TO_DATE(SETTLE_END_DT,'DD/MM/YYYY')+1,'DD/MM/YY') FROM FMS9_SETTLE_CALND_DTL "
							//SB20210708		+ "WHERE CONT_MM_YYYY=TO_DATE('"+PrevContMthYr+"','DD/MM/YYYY') AND SETTLE_TYPE='"+HolidayType+"' ";
					+ "WHERE CONT_MM_YYYY=TO_DATE('"+PrevContMthYr+"','DD/MM/YYYY') AND CURVE_NM='"+INDEX_TYPE+"' ";
				//	System.out.println(" SETTLE: FMS9_SETTLE_CALND_DTL: "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next()) {
						PrevMthSettleEndDtPlusOne = rset.getString(1)==null?"":rset.getString(1); //SB20201128;	
					}
					else //SB20210425
						PrevMthSettleEndDtPlusOne = ContMthYr2; //SB20210425
					if(!UserAvgPriceStartDt.equals(""))  //SB20201128
							PrevMthSettleEndDtPlusOne=UserAvgPriceStartDt; //SB20201128
					if(!UserAvgPriceEndDt.equals(""))  //SB20201128
						ContMthSettleDt=UserAvgPriceEndDt; //SB20210511
					
				}
				///else if(IndexSettlePrice.equals("A7"))
				else if(IndexSettlePrice.equals("A"+NoOfDays))
				{
					int NoOfDays2=1;
					NoOfDays2=NoOfDays-1;
					queryString = "SELECT TO_CHAR(TO_DATE('"+ContMthSettleDt+"','DD/MM/YYYY')-"+NoOfDays2+",'DD/MM/YYYY') FROM DUAL";
				//	System.out.println("Get DAY Name: "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						PrevMthSettleEndDtPlusOne = rset.getString(1)==null?"":rset.getString(1); //SB20201128;
					}
				}
	////^^/SB20201116: Prev Settlement Date: to find the start date for Contract Month////////////////////////////
				//System.out.println(UserAvgPriceEndDt+" :ContMthSettleDt: "+ContMthSettleDt);
		Vector Veff_dt = new Vector();Vector Veff_dtWeeklyOff = new Vector();
		Vector temp_Vcurren_cd = new Vector();
		Vector temp_Vcurren_cd_from = new Vector();
		
		String date_arr = ""; String PriceBegMthDt="";
	
//SB20210401	if((INDEX_TYPE.equals("LNG_ICE_JKM")|| INDEX_TYPE.equals("CR_DATED_BRENT")) && IndexSettlePrice.equals("F")) //SB20201027: For BRENT Settlement  Date
	if((!INDEX_TYPE.equals("")) && IndexSettlePrice.equals("F")) //SB20201027: For BRENT Settlement  Date
		{
			String ContMthYr="01/"+GenMthYr;
			queryString = "SELECT TO_CHAR(SETTLE_DT,'DD/MM/YYYY'), TO_CHAR(SETTLE_END_DT,'DD/MM/YYYY') FROM FMS9_SETTLE_CALND_DTL "
			//SB20210708		+ "WHERE CONT_MM_YYYY=TO_DATE('"+ContMthYr+"','DD/MM/YYYY') AND SETTLE_TYPE='"+HolidayType+"' ";
			+ "WHERE CONT_MM_YYYY=TO_DATE('"+ContMthYr+"','DD/MM/YYYY') AND CURVE_NM='"+INDEX_TYPE+"' ";
			//	System.out.println(" SETTLE: FMS9_SETTLE_CALND_DTL: "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next()) {
					date_arr = rset.getString(1)==null?"":rset.getString(1);					
				}
				else
				{
					date_arr=ContMthYr;
				}
				PriceBegMthDt =date_arr;
				date_arr = PriceBegMthDt;
				Veff_dt.add(date_arr);
		}
		///////////////////For BRENT 30 Days Avg. ///////////////////
	//SB20210401	else if((INDEX_TYPE.equals("LNG_ICE_JKM") || INDEX_TYPE.equals("CR_DATED_BRENT")) && (IndexSettlePrice.equals("A") || IndexSettlePrice.equals("A"+NoOfDays)))//if(IndexSettlePrice.equals("A"))
		else if((!INDEX_TYPE.equals("")) && (IndexSettlePrice.equals("A") || IndexSettlePrice.equals("A"+NoOfDays)))//if(IndexSettlePrice.equals("A"))
		{
			///String start_dt =""; String delv_mth ="01/"+GenMth+"/"+GenYr; int businessMonth=0; int businessYear=0; String StartMonth="";String EndMonth="";
			String ContMthYr="01/"+GenMthYr;
			
			double count=0;		
			String query="select to_date('"+ContMthSettleDt+"','DD/MM/YY')- to_date('"+PrevMthSettleEndDtPlusOne+"','DD/MM/YY')+1 from dual";
			//System.out.println("Date Difference: dual: "+query);
			rset=stmt.executeQuery(query);
			if(rset.next())
			{
				count=rset.getInt(1);
			}
			//PriceBegMthDt =ContMthYr;
			int StratDayCount=0; int StratDayCount2=0; //SB20201017: to check Starting SAT/SUN/HOLIDAY
			
			PriceBegMthDt =PrevMthSettleEndDtPlusOne;
					for(int i=0;i<count;i++)
					{
						String query1="select to_char(to_date('"+PrevMthSettleEndDtPlusOne+"','dd/mm/yy')+ "+i+",'dd/mm/yyyy') from dual";
					///	System.out.println(">>>>>>>dual: "+query1);
						rset=stmt.executeQuery(query1);
						if(rset.next())
						{
							date_arr=rset.getString(1)==null?"":rset.getString(1); //SB20201128
						}		
					///////////////////////SB20201017: to check Starting SAT/SUN/HOLIDAY//////////////////////////////					
					if(StratDayCount==StratDayCount2)
					{
						queryString ="SELECT to_char(HOLIDAY_DT,'dd/mm/yyyy') FROM FMS9_CURVE_HOLIDAY_DTL "
					//SB20210708			+ " WHERE HOLIDAY_DT =to_date('"+date_arr+"','dd/mm/yyyy') AND FLAG='Y' AND HOLIDAY_TYPE='"+HolidayType+"' ORDER BY HOLIDAY_DT";
						+ " WHERE HOLIDAY_DT =to_date('"+date_arr+"','dd/mm/yyyy') AND FLAG='Y' AND CURVE_NM='"+INDEX_TYPE+"' ORDER BY HOLIDAY_DT";
						rset = stmt.executeQuery(queryString);
					//	System.out.println(">>>>>>>PRICE PUULING: "+queryString);
						if(rset.next())
						{
							StratDayCount++; //System.out.println("Get HOLI DAY Name: ");		
						}
						else
						{
						String WeeklyOff="";
						queryString = "SELECT TO_CHAR(TO_DATE('"+date_arr+"','DD/MM/YYYY'),'DAY') FROM DUAL";
						//	System.out.println("Get DAY Name: "+queryString);
							rset = stmt.executeQuery(queryString);
							if(rset.next())
							{
								WeeklyOff = rset.getString(1)==null?"":rset.getString(1); //SB20201128;
							}
							if(WeeklyOff.trim().equals("SATURDAY") || WeeklyOff.trim().equals("SUNDAY")) 
							{
								StratDayCount++;
							}
							else
							{							
								Veff_dt.add(date_arr);
								PriceBegMthDt=date_arr; //System.out.println("Get DAY Name: "+WeeklyOff);							
							}
						}
					}
					else
						Veff_dt.add(date_arr); //Ori Line
					StratDayCount2++; //System.out.println(StratDayCount+" : "+StratDayCount2);
					//////////////////////////////////////////////////////////////////////////////////////////
				//SB20201017	Veff_dt.add(date_arr);
				}
				queryString = "SELECT TO_CHAR(SETTLE_DT,'DD/MM/YYYY'), TO_CHAR(SETTLE_END_DT,'DD/MM/YYYY') FROM FMS9_SETTLE_CALND_DTL "
				//SB20210708		+ "WHERE CONT_MM_YYYY=TO_DATE('"+ContMthYr+"','DD/MM/YYYY') AND SETTLE_TYPE='"+HolidayType+"' ";
				+ "WHERE CONT_MM_YYYY=TO_DATE('"+ContMthYr+"','DD/MM/YYYY') AND CURVE_NM='"+INDEX_TYPE+"' ";
			//	System.out.println(" SETTLE: FMS9_SETTLE_CALND_DTL: "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next()) {
					date_arr = rset.getString(1)==null?"":rset.getString(1); //SB20201128;		
				}
		}//EoF Avg 7 days	
		/////////////////^^^/////////////////////////////////////////
	//	System.out.println(Veff_dt+">>********FINAL LIST*********>>>>>Veff_dt: "+Veff_dt.size());
				String PriceEndMthDt=date_arr; String PriceSettleDt=date_arr;
//////////////////////////////////Check For Price Range///////////////////////
				int ReportDays=0; int ReportDays2=0;
				queryString = "SELECT TO_DATE('"+ReportDt+"','DD/MM/YYYY')-TO_DATE('"+PriceBegMthDt+"','DD/MM/YYYY') FROM DUAL" ;
			//	System.out.println(" DUAL: LAST: "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next()) {
				ReportDays = rset.getInt(1);
				}	
			//	System.out.println(PriceBegMthDt+" DUAL: Begining of ReportDays: "+ReportDt+" ReportDays: "+ReportDays);
				queryString = "SELECT TO_DATE('"+PriceEndMthDt+"','DD/MM/YYYY')-TO_DATE('"+ReportDt+"','DD/MM/YYYY') FROM DUAL" ;
			//	System.out.println(" DUAL: LAST: "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next()) {
				ReportDays2 = rset.getInt(1);
				}	
		//		System.out.println(ReportDays+" DUAL: Begining of ReportDays: "+ReportDt+" ReportDays: "+ReportDays2);


//////////////////////////SB20200930///////////////////////////////////////////////////////			
			
/////////////////////////////WEEKDAY LIST///////////////////////////////////////////////
	int NoOfSatSun=0; int NoOfHoliday=0; int StratDayCount=0; int StratDayCount2=0;
	for(int i=0;i<Veff_dt.size();i++)
	{
	///	if(VWeekDayHoliDay.size()<Veff_dt.size())
		{
			String WeeklyOff="";
			queryString = "SELECT TO_CHAR(TO_DATE('"+Veff_dt.elementAt(i)+"','DD/MM/YYYY'),'DAY') FROM DUAL";
			//	System.out.println("Get DAY Name: "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					WeeklyOff = rset.getString(1)==null?"":rset.getString(1); //SB20201128;
				}
				
			queryString ="SELECT to_char(HOLIDAY_DT,'dd/mm/yyyy') FROM FMS9_CURVE_HOLIDAY_DTL "
					+ " WHERE HOLIDAY_DT BETWEEN to_date('"+Veff_dt.elementAt(i)+"','dd/mm/yyyy') AND to_date('"+Veff_dt.elementAt(i)+"','dd/mm/yyyy') AND FLAG='Y' "
					//SB20210708	+ "AND HOLIDAY_TYPE='"+HolidayType+"' ORDER BY HOLIDAY_DT";
					+ "AND CURVE_NM='"+INDEX_TYPE+"' ORDER BY HOLIDAY_DT";
			rset = stmt.executeQuery(queryString);
		//	System.out.println(">>>>>>>PRICE PUULING: "+queryString);
			if(rset.next())
			{
				NoOfHoliday++;
			}
			else
			{
				if(WeeklyOff.trim().equals("SATURDAY") || WeeklyOff.trim().equals("SUNDAY")) 
				{
					NoOfSatSun++;
				}
			}
		}
		//SB20201015///////////////To get Weekly Off & Holiday/////////////////////////////////
		String WeeklyOffStartDate=""; 
		if(StratDayCount==StratDayCount2)
		{
		queryString = "SELECT TO_CHAR(TO_DATE('"+Veff_dt.elementAt(i)+"','DD/MM/YYYY'),'DAY') FROM DUAL";
		//	System.out.println("Get DAY Name: "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				WeeklyOffStartDate = rset.getString(1)==null?"":rset.getString(1); //SB20201128;
			}
			
			if(WeeklyOffStartDate.trim().equals("SATURDAY") || WeeklyOffStartDate.trim().equals("SUNDAY")) 
			{
				StratDayCount++;
			}
			else
			{
				queryString ="SELECT to_char(HOLIDAY_DT,'dd/mm/yyyy') FROM FMS9_CURVE_HOLIDAY_DTL "
						+ " WHERE HOLIDAY_DT= to_date('"+Veff_dt.elementAt(i)+"','dd/mm/yyyy') AND FLAG='Y' AND "
						//SB20210708	+ "HOLIDAY_TYPE='"+HolidayType+"' ORDER BY HOLIDAY_DT";
						+ "CURVE_NM='"+INDEX_TYPE+"' ORDER BY HOLIDAY_DT";
				rset = stmt.executeQuery(queryString);
			//	System.out.println(">>>>>>>PRICE PUULING: "+queryString);
				if(rset.next())
				{
					StratDayCount++;
				}
			}
		}
			StratDayCount2++;
	//////////////////////////////////////////////////////////////////////////////	
	}

	double ReducingPerc=0; double CumulativeReducingPerc=0;
	if(ReportDays>=0 && ReportDays2>=0 && ContinueReducePerc.equals("Y"))
	{
		ReducingPerc=100/(Double.parseDouble(""+Veff_dt.size())-(NoOfSatSun+NoOfHoliday));	//System.out.println(Veff_dt.size()+" :**************ReducingPerc"+ReducingPerc);
	///SB	ReducingPerc=BaseValue/(Double.parseDouble(""+Veff_dt.size())-(NoOfSatSun+NoOfHoliday));
	}
	//System.out.println(Veff_dt.size()+" :**************No of Days: "+(Double.parseDouble(""+Veff_dt.size())-NoOfSatSun+NoOfHoliday));
	//System.out.println(StratDayCount+" :No of SAT/SUN: "+(NoOfSatSun+NoOfHoliday));
	//System.out.println(ReportDays+">>*****************>>>>>VWeekDayHoliDayFlag: "+ReportDays);

	if(ReportDays>=0 && ReportDays2>=0)
		VLNG_ICE_JKM_EXPODropOff.add("Y");
	else
		VLNG_ICE_JKM_EXPODropOff.add("N");
	
	//	System.out.println(ReducingPerc+">>*****************>>>>>WeeklyOffValue: "+WeeklyOffValue);
		
		if(ContinueReducePerc.equals("N")) //SB20201011
			CumalativePerc="0";
	//SB20210101	if(WeeklyOffValue.equals("1"))
		{	
		
			CumalativePerc=""+(ReducingPerc);
			
			CumulativeReducingPerc=ReducingPerc;
			FinancialCumalativePerc=""+CumulativeReducingPerc;
		}
	//	System.out.println(PriceType+" :PriceType: " +VEff_Perc.size()+" : "+IndexSettlePrice);
	//	System.out.println(ReducingPerc+">>>>>>>CumalativePerc: "+CumalativePerc);
	//	System.out.println(CumulativeReducingPerc+">>>>>>>***********************CumulativeReducingPerc: "+ContinueReducePerc);
		if(PriceType.equals("M") && (IndexSettlePrice.equals("A") || IndexSettlePrice.equals("A"+NoOfDays)))
		{
			VEff_CumulativePerc.add(CumulativeReducingPerc); //SB20201006
			VEff_Perc.add((Double.parseDouble(CumalativePerc))); 
		}
	//////////^^^On every Day basis///////////////////////////////////////////////////////////////////////
//	System.out.println(CumulativeReducingPerc+">>>>>>>***********************CumulativeReducingPerc: "+ContinueReducePerc);
		
	else if(PriceType.equals("M") && IndexSettlePrice.equals("F"))
	{		
		VEff_CumulativePerc.add(CumalativePerc); //SB20201006
		VEff_Perc.add(nf.format(Double.parseDouble(CumalativePerc))); 
		VWeekDayHoliDayFlag.add("1");
	}
	else if(PriceType.equals("F")) //Fixed Pricing: Do Not Remove. If you remove comment it will disturbs droff of value atlernatively
	{
		VEff_CumulativePerc.add("0"); //SB20201006
		VEff_Perc.add("0"); 
		VWeekDayHoliDayFlag.add("1");
	}
	//	System.out.println(VEff_Perc+">>>>>>>VEff_Perc: "+VEff_Perc.size());System.out.println(VEff_CumulativePerc+">>>>>>>VEff_CumulativePerc: "+VEff_CumulativePerc.size());
//	System.out.println(VWeekDayHoliDay+">>>>>>>VWeekDayHoliDay: "+VWeekDayHoliDay.size());
//	System.out.println(VWeekDayHoliDayFlag+">>>>>>>VWeekDayHoliDayFlag: "+VWeekDayHoliDayFlag.size());
//	System.out.println(VEff_CumulativePerc+">>>>>>>VEff_CumulativePerc: "+VEff_CumulativePerc.size());
//	System.out.println(VEff_Perc+">>>>>>>VEff_CumulativePerc: "+VEff_Perc.size());

			//	System.out.println(PriceEndMthDt+">>>>>>>PRICE 2.VHoliday_Dt: "+VHoliday_Dt);

					PriceSettleDt=PriceEndMthDt;
					String ContMthYr="01/"+GenMthYr;
					queryString = "SELECT TO_CHAR(SETTLE_DT,'DD/MM/YYYY'), TO_CHAR(SETTLE_END_DT,'DD/MM/YYYY') FROM FMS9_SETTLE_CALND_DTL "
							//SB20210708		+ "WHERE CONT_MM_YYYY=TO_DATE('"+ContMthYr+"','DD/MM/YYYY') AND SETTLE_TYPE='"+HolidayType+"' ";
							+ "WHERE CONT_MM_YYYY=TO_DATE('"+ContMthYr+"','DD/MM/YYYY') AND CURVE_NM='"+INDEX_TYPE+"' ";
				//	System.out.println(" SETTLE: FMS9_SETTLE_CALND_DTL: "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next()) {
						PriceSettleDt = rset.getString(1)==null?"":rset.getString(1); //SB20201128;
						PriceEndMthDt = rset.getString(2)==null?"":rset.getString(2); //SB20201128;
						PriceEndMthDt = PriceSettleDt;  //Need varification
					}
					if(!UserAvgPriceEndDt.equals(""))  //SB20201128
					{
						PriceEndMthDt=UserAvgPriceEndDt; //SB20210511
						PriceSettleDt=PriceEndMthDt;
					}
				double AvgPrice=0; int PriceAvlCount=1;
	/*SB20201025			queryString = "select SUM(LNG_ICE_JKM), COUNT(*) from FMS9_CURVE_PRICE_DTL "
						+ " where LNG_ICE_JKM>0 AND CURVE_DD_MM_YR BETWEEN to_date('"+PriceBegMthDt+"','dd/mm/yyyy') AND to_date('"+PriceEndMthDt+"','dd/mm/yyyy') AND CURVE_TYPE='P' ";
				System.out.println("COMMODITY: "+queryString);
				*/
	
			//	System.out.println(AvgPrice+" :AvgPrice: "+PriceAvlCount);
				//String CurveName="";
			//	 if(INDEX_TYPE.equals("LNG_ICE_JKM")) //SB20201117
			/*	//SB20210409	 if(INDEX_TYPE.equals("LNG_ICE_JKM") || INDEX_TYPE.equals("ICE_JKM")) //SB20210402
				 if(INDEX_TYPE.equals("LNG_ICE_JKM") || INDEX_TYPE.equals("JKM_PLATTS_SETTLEMENT") || INDEX_TYPE.equals("ICE_JKM")) //SB20210409
					 CurveName="PLATTS_JKM"; 
				else 	 if(INDEX_TYPE.equals("CR_DATED_BRENT") || INDEX_TYPE.equals("ICE_BRENT_FIRST_LINE")  || INDEX_TYPE.equals("ICE_BRENT")) //SB20210409 //SB20210402 //SB20201025
					 CurveName="ICE_BRENT";
				 else //SB20210407
					 CurveName=INDEX_TYPE; //SB20210407
				*/
				 if(INDEX_TYPE.equals("LNG_ICE_JKM") || INDEX_TYPE.equals("JKM_PLATTS_SETTLEMENT") || INDEX_TYPE.equals("ICE_JKM")) //SB20210409
						 CurveName="('ICE_JKM')";
					//SB20210409	 else if(INDEX_TYPE.equals("CR_DATED_BRENT"))  
				 else 	 if(INDEX_TYPE.equals("CR_DATED_BRENT") || INDEX_TYPE.equals("ICE_BRENT_FIRST_LINE")  || INDEX_TYPE.equals("ICE_BRENT")) //SB20210409 //SB20210402 //SB20201025
					 CurveName="('ICE_BRENT')";
				 else //SB20210407
					 CurveName="('"+INDEX_TYPE+"')";
				 
			double sumCommodityValue=0; int countNonZero=0; double avgCommodityValue=0; double Eff_avgCommodityValue=0; 
		/*SB	if(PriceAvlCount>0)
				avgCommodityValue=AvgPrice/PriceAvlCount; 
			else
				avgCommodityValue=0;*/
			queryString = "select AVG(SETTLE_PRICE) from FMS9_CURVE2_PRICE_DTL "
					+ " where SETTLE_PRICE>0 AND CURVE_DD_MM_YR BETWEEN to_date('"+PriceBegMthDt+"','dd/mm/yyyy') AND to_date('"+PriceEndMthDt+"','dd/mm/yyyy') "
					//SB20210409		+ "AND CURVE_TYPE='Spot' AND CURVE_NM='"+CurveName+"' ";
			+ "AND CURVE_TYPE='Spot' AND PHYS_FIN IN "+CurveName+" "; //SB20210409
		//	System.out.println("Eff. AVG: FM S9_CURVE_PRICE_DTL: "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				avgCommodityValue = rset.getDouble(1);
			}
			else
			{
				avgCommodityValue = 0;
			}	

			int noOfReportDays=0;
			queryString = "SELECT TO_DATE('"+ReportDt+"','DD/MM/YYYY')-TO_DATE('"+PriceEndMthDt+"','DD/MM/YYYY') FROM DUAL" ;
		//	System.out.println(" DUAL: LAST: "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next()) {
				noOfReportDays = rset.getInt(1);
			}		
			
		//	System.out.println(PriceEndMthDt+" noOfReportDays: "+noOfReportDays);
			///////SB20200924/: Based on feedback 24thSep///////////////////////////////////
				if(noOfReportDays<0)  //SB20200915 To Control the availabilty of Settle Price: If Report Dt is less than PriceEndMthDate
					avgCommodityValue=0;	
			////////////////////////////////////////////////////////
			String rate_value=""; String AvgCommodityValue=""; String ContPriceBasedCommodityValue="";
			
			//SB20200918: As per Shiladitya		Eff_avgCommodityValue=avgCommodityValue*Double.parseDouble(""+Slope)-Double.parseDouble(""+Constant);
			Eff_avgCommodityValue=avgCommodityValue*Double.parseDouble(""+Slope)+Double.parseDouble(""+Constant); //SB20200918: As per Shiladitya

			////////////////////////////////////SB20201006: Decide Price start Date & Price end Date /////////////////////////////////////////
							if(IndexSettlePrice.equals("F"))  //F: If Settle Price Date is Final Settle Date
								{
									PriceBegMthDt=PriceSettleDt;
									PriceEndMthDt=PriceSettleDt;
								}
							
							int NoOfWeekOff=0; String WeeklyOff="";
							for(int i=0; i<Veff_dt.size(); i++)
							{
							queryString = "SELECT TO_CHAR(TO_DATE('"+Veff_dt.elementAt(i)+"','DD/MM/YYYY'),'DAY') FROM DUAL";
							//	System.out.println("Get DAY Name: "+queryString);
								rset = stmt.executeQuery(queryString);
								if(rset.next())
								{
									WeeklyOff = rset.getString(1)==null?"":rset.getString(1); //SB20201128;
									if(WeeklyOff.trim().equals("SATURDAY") || WeeklyOff.trim().equals("SUNDAY")) 
										NoOfWeekOff++;
								}
								Veff_dtWeeklyOff.add(NoOfWeekOff);
							}	
							
					//		System.out.println(PriceBegMthDt+">>>>>>>Veff_dt"+Veff_dt.size());
						///	System.out.println(Veff_dtWeeklyOff+"NoOfWeekOff "+NoOfWeekOff);	
					//		System.out.println(GenDate+">>>>>>>PRICE VHoliday_Dt: "+VHoliday_Dt.size());

			AvgCommodityValue=nf2.format(avgCommodityValue);
		//	System.out.println(GenMthYr+" :AvgCommodityValue: "+AvgCommodityValue);
/////////////SB20210702: New Logic based on discussion on 28th Jun 2021/////////////////
			String FloatContMth="Y";String PriceIndex_SettleMth=""; //SB20210702
			if(avgCommodityValue>=0 && PriceType.equals("M"))
			{
			
			queryString = "SELECT TO_CHAR(MAX(TO_DATE(CONT_MM_YYYY,'DD-MON-YY')),'MM/YYYY') FROM FMS9_SETTLE_CALND_DTL "
					+ "WHERE SETTLE_DT BETWEEN TO_DATE(TO_DATE('"+PrevMthSettleEndDtPlusOne+"','DD/MM/YYYY'),'DD-MON-YY') AND TO_DATE(TO_DATE('"+ContMthSettleDt+"','DD/MM/YYYY'),'DD-MON-YY') "
				//Remove this checking from every wher	+ "AND SETTLE_TYPE='"+HolidayType+"' ";
					+ "AND CURVE_NM='"+INDEX_TYPE+"' ";
		//		System.out.println(" SETTLE: FMS9_SETTLE_CALND_DTL: "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next()) {
					PriceIndex_SettleMth = rset.getString(1)==null?GenMthYr:rset.getString(1); //SB20201128;
				}
				else
					PriceIndex_SettleMth=GenMthYr;
					/////////////SB20210702/////////////////	
				PriceIndex_SettleMth="01/"+PriceIndex_SettleMth;  //"01" added for Insert into FMS9_EOD_EXPOSURE_DTL table
					if(FloatContMth.equals("")) //SB20210702 OLD Version No Float ContMth.
					{
						VPriceIndex_SettleDate.add(PriceSettleDt); 
						PriceIndex_SettleDate=PriceSettleDt;
					}
					else //SB20210702
					{
						VPriceIndex_SettleDate.add(PriceIndex_SettleMth);	//SB20210702: New Version for FloatContMth
						PriceIndex_SettleDate=PriceSettleDt;
					}
					/////////////^^^SB20210702/////////////////
			}//System.out.println(GenMthYr+"GenMthYr: PriceIndex_SettleMth "+PriceIndex_SettleMth);
	/////////////^^^SB20210702/////////////////	
			if(avgCommodityValue>0 && PriceType.equals("M"))
			{//System.out.println(PriceType+"FLOAT-If: VEff_Price "+avgCommodityValue);
				VMthYrWisePriceICE_JKM.add(AvgCommodityValue);
				PrvCurvePrice=""+AvgCommodityValue;
				//SB20210702	PriceIndex_SettleDate=PriceSettleDt;
				//SB20210702 VPriceIndex_SettleDate.add(PriceSettleDt);
				
				PriceIndex_StartDate=PriceBegMthDt;
				VPriceIndex_StartDate.add(PriceBegMthDt);
				PriceIndex_EndDate=PriceEndMthDt;
				VPriceIndex_EndDate.add(PriceEndMthDt);
				EffPriceSlopeConst=nf2.format(Eff_avgCommodityValue);
				VEff_Price.add(nf2.format(Eff_avgCommodityValue));
			}
			else if(avgCommodityValue==0 && PriceType.equals("M"))
			{//System.out.println(Eff_avgCommodityValue+"FLOAT-else: VEff_Price "+avgCommodityValue);
				VMthYrWisePriceICE_JKM.add("0");
				PrvCurvePrice=""+avgCommodityValue;
				//SB20210702	PriceIndex_SettleDate=PriceSettleDt;
				//SB20210702	VPriceIndex_SettleDate.add(PriceSettleDt);
				/*/////////////SB20210702/////////////////
					
					if(FloatContMth.equals("")) //SB20210702
						VPriceIndex_SettleDate.add(PriceSettleDt);
					else //SB20210702
						VPriceIndex_SettleDate.add(PriceIndex_SettleMth);	//SB20210702	
				/////////////^^^SB20210702/////////////////
*/				PriceIndex_StartDate=PriceBegMthDt;
				VPriceIndex_StartDate.add(PriceBegMthDt);
				PriceIndex_EndDate=PriceEndMthDt;
				VPriceIndex_EndDate.add(PriceEndMthDt);
				EffPriceSlopeConst=nf2.format(Eff_avgCommodityValue);
				VEff_Price.add("");
			}
			else if(PriceType.equals("F"))
			{// System.out.println(PriceType+"FIXED: VEff_Price "+VEff_Price.size());
				VMthYrWisePriceICE_JKM.add("0");
				PrvCurvePrice=""+avgCommodityValue;
				PriceIndex_SettleDate="";
				VPriceIndex_SettleDate.add("");
				PriceIndex_StartDate="";
				VPriceIndex_StartDate.add("");
				PriceIndex_EndDate="";
				VPriceIndex_EndDate.add("");
				EffPriceSlopeConst="";
				VEff_Price.add("");
			}
	//  System.out.println("VEff_Price "+VEff_Price.size());
		//	System.out.println(sumCommodityValue+" :COMMODITY: ContPriceBasedCommodityValue  "+ContPriceBasedCommodityValue);
	}
	catch(Exception e)
	{
		  //System.out.println("Exception in Databean_Contract_MasterReport--->fetchFGSASNPRICEList()-->"+e);
		  e.printStackTrace();
	}
}	
	////////////////////////////////////////////////////////////////////////////
	
	/////////////////////SB20200817////////////////////////////
	public void MRCR_ContDtl(String IndexType, String BuySell, String PriceTypeCargo, String DealPrice, String TCQ, String AllocQty, String GasDt, String GenDt, String ContEndDt, String PhyCurFrdPrice, String FinCurFrdPrice, String Slope, String PriorMthCurvePrice, String PercReduce,String CumuPercReduce, double BaseValue, String DropOff)
	{
		try
		{
			String PriceType=""; 
			if(PriceTypeCargo.equalsIgnoreCase("Fixed"))
			{
				PriceTypeCargo="F";
				PriceIndexName="-";
			}
			else
				PriceTypeCargo="M";
			double U_Phy_Leg=0;
			double U_Fin_Leg=0;
			double R_Fin_Leg=0;
		////	MappId="J";
			VBuySell.add(BuySell);
	//		System.out.println(GasDt+" PriceTypeCargo****"+PriceTypeCargo);
		
			String tempGasDt[]=GasDt.split("/");
			String GasMth=tempGasDt[1]; String GasYr=tempGasDt[2]; String GasMthYr=GasMth+"/"+GasYr;
			
			String Gen_Dt=to_date; 
			String tempGen_Dt[]=Gen_Dt.split("/");
			String GenMth=tempGen_Dt[1]; String GenYr=tempGen_Dt[2]; String GenMthYr=GenMth+"/"+GenYr;
		
			////Settlement Price Pulling from Table////////////////////////////
			String FirstDateGasMthYr="01/"+GasMthYr; String LastDateGasMthYr="";
			queryString = "SELECT TO_CHAR(LAST_DAY(TO_DATE('"+FirstDateGasMthYr+"','DD/MM/YYYY')),'DD/MM/YYYY') FROM DUAL";
			//	System.out.println("Query for generating Last Date of the selected Month = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					LastDateGasMthYr = rset.getString(1);
				}
			
	//////////////SB20201105: Introduced due to multiple record on Curve Price Tbl////////////////////////////
				int CountPlattEntDt=0; String PlattEntDt="";
				queryString = "select COUNT(ENT_DT) from FMS9_FORWARD_PRICE_DTL "
						+ " where ENT_DT= to_date('"+Gen_Dt+"','dd/mm/yyyy') AND CURVE_TYPE='F' AND FLAG='Y' ";
		//		System.out.println("FMS9_CURVE_PRICE_DTL: "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					CountPlattEntDt = rset.getInt(1);
				}
				if(CountPlattEntDt>0)
				{
				//	PlattEntDt=Gen_Dt; 
				//	System.out.println("1. PlattEntDt: "+PlattEntDt);
					queryString = "SELECT DISTINCT TO_CHAR(ENT_DT, 'dd/mm/yyyy')  from FMS9_FORWARD_PRICE_DTL "
							+ " where ENT_DT= to_date('"+Gen_Dt+"','dd/mm/yyyy') AND CURVE_TYPE='F' AND FLAG='Y' ";
				//	System.out.println("A. FMS9_CURVE_PRICE_DTL: "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						PlattEntDt = rset.getString(1)==null?"":rset.getString(1);
					}
				}
				else
				{
					queryString = "SELECT TO_CHAR(MAX(ENT_DT), 'dd/mm/yyyy')  from FMS9_FORWARD_PRICE_DTL "
							+ " where ENT_DT<= to_date('"+Gen_Dt+"','dd/mm/yyyy') AND CURVE_TYPE='F' AND FLAG='Y' ";
				//	System.out.println("B. FMS9_CURVE_PRICE_DTL: "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						PlattEntDt = rset.getString(1)==null?"":rset.getString(1);
					}
					if(PlattEntDt.equals(""))
					{
						queryString = "SELECT TO_CHAR(MIN(ENT_DT), 'dd/mm/yyyy')  from FMS9_FORWARD_PRICE_DTL "
								+ " where ENT_DT>= to_date('"+Gen_Dt+"','dd/mm/yyyy') AND CURVE_TYPE='F' AND FLAG='Y' ";
					//	System.out.println("C. FMS9_CURVE_PRICE_DTL: "+queryString);
						rset = stmt.executeQuery(queryString);
						if(rset.next())
						{
							PlattEntDt = rset.getString(1)==null?"":rset.getString(1);
						}
						if(PlattEntDt.equals(""))
						{
							queryString = "SELECT TO_CHAR(MAX(ENT_DT), 'dd/mm/yyyy')  from FMS9_FORWARD_PRICE_DTL "
									+ " where ENT_DT<= to_date('"+Gen_Dt+"','dd/mm/yyyy') AND CURVE_TYPE='F' AND FLAG='Y' ";
					//		System.out.println("D. FMS9_CURVE_PRICE_DTL: "+queryString);
							rset = stmt.executeQuery(queryString);
							if(rset.next())
							{
								PlattEntDt = rset.getString(1)==null?"":rset.getString(1);
							}
						}
					}
				}
			//	System.out.println("2. PlattEntDt: "+PlattEntDt);
				//////////////////////////^^^^SB20201105////////////////////////////////////////////////////////////////////

				
					queryString ="SELECT CR_DATED_BRENT, LNG_ICE_JKM, LNG_JKM, LNG_PHYS_INDIA, RLNG_PHYS_INDIA FROM FMS9_FORWARD_PRICE_DTL "+ 
							" WHERE CURVE_DD_MM_YR=TO_DATE('"+FirstDateGasMthYr+"','DD/MM/YYYY') AND CURVE_TYPE='F' AND FLAG='Y' AND ENT_DT=TO_DATE('"+PlattEntDt+"','DD/MM/YYYY') ";
				//	System.out.println(IndexType+" :SETTLEMENT PRICE: FMS9_CURVE_PRICE_DTL: "+queryString);
						rset2 = stmt.executeQuery(queryString);			
						if(rset2.next())
						{
							if(PriceTypeCargo.equals("M") && Double.parseDouble(FinCurFrdPrice)==0)
							{
								if(IndexType.endsWith("J"))
								{
									VSettle_ICE_JKM.add(rset2.getString(2)==null?"0":rset2.getString(2));  //ICE_JKM_FORWARD
									FinCurFrdPrice=rset2.getString(2);
									VSettle_ICE_JKM_FLAG.add("JKM");
								}
								else
								{
									VSettle_ICE_JKM.add(rset2.getString(1)==null?"0":rset2.getString(1));  //ICE_JKM_FORWARD
									FinCurFrdPrice=rset2.getString(1);
									VSettle_ICE_JKM_FLAG.add("BRENT");
								}									
							}
							else
							{
								VSettle_ICE_JKM.add("0");
								FinCurFrdPrice="0";
								VSettle_ICE_JKM_FLAG.add("NA");
							}
							if(BuySell.equalsIgnoreCase("BUY"))
							{
								VSettle_RLNG_PHYS.add(rset2.getString(4)==null?"0":rset2.getString(4));  //RLNG_PHYS_FORWARD //SB20210227
								VSettle_RLNG_PHYS_FLAG.add("LNG");//System.out.println("IF ****"+VSettle_ICE_JKM);
								PhyCurFrdPrice=rset2.getString(4)==null?"0":rset2.getString(4); //SB20210406;
							}
							else
							{
								VSettle_RLNG_PHYS.add(rset2.getString(5)==null?"0":rset2.getString(5));  //RLNG_PHYS_FORWARD  //SB20210227
								VSettle_RLNG_PHYS_FLAG.add("RLNG");//System.out.println("IF ****"+VSettle_ICE_JKM);
								PhyCurFrdPrice=rset2.getString(5)==null?"0":rset2.getString(5); //SB20210406;
							}
							
						
						
							
							FrdPriceEntDt=""+PlattEntDt;
						}
					else
						{
							queryString ="SELECT CR_DATED_BRENT, LNG_ICE_JKM, LNG_JKM, LNG_PHYS_INDIA, RLNG_PHYS_INDIA FROM FMS9_FORWARD_PRICE_DTL "
									+ "WHERE CURVE_DD_MM_YR>=TO_DATE('"+FirstDateGasMthYr+"','DD/MM/YYYY') AND CURVE_DD_MM_YR<=TO_DATE('"+LastDateGasMthYr+"','DD/MM/YYYY') AND ENT_DT=(SELECT MAX(ENT_DT) FROM FMS9_FORWARD_PRICE_DTL WHERE CURVE_DD_MM_YR>=TO_DATE('"+FirstDateGasMthYr+"','DD/MM/YYYY') "
									+ " AND CURVE_DD_MM_YR<=TO_DATE('"+LastDateGasMthYr+"','DD/MM/YYYY') AND CURVE_TYPE='F' AND FLAG='Y') AND CURVE_TYPE='F' AND FLAG='Y' "; 
						//	System.out.println("222*****SETTLEMENT PRICE: FMS9_CURVE_PRICE_DTL: "+queryString);
							rset2 = stmt.executeQuery(queryString);			
							if(rset2.next())
							{
								if(PriceTypeCargo.equals("M") && Double.parseDouble(FinCurFrdPrice)==0)
								{
									if(IndexType.endsWith("J"))
									{
										VSettle_ICE_JKM.add(rset2.getString(2)==null?"0":rset2.getString(2));  //ICE_JKM_FORWARD
										FinCurFrdPrice=rset2.getString(2)==null?"0":rset2.getString(2);
										VSettle_ICE_JKM_FLAG.add("JKM");
									}
									else
									{
										VSettle_ICE_JKM.add(rset2.getString(1)==null?"0":rset2.getString(1));  //ICE_JKM_FORWARD
										FinCurFrdPrice=rset2.getString(1)==null?"0":rset2.getString(1);
										VSettle_ICE_JKM_FLAG.add("BRENT");
									}
								}
								else
								{
									VSettle_ICE_JKM.add("0");
									FinCurFrdPrice="0";
									VSettle_ICE_JKM_FLAG.add("NA");
								}
								if(BuySell.equalsIgnoreCase("BUY"))
								{
									VSettle_RLNG_PHYS.add(rset2.getString(4)==null?"0":rset2.getString(4));  //RLNG_PHYS_FORWARD //SB20210227
									VSettle_RLNG_PHYS_FLAG.add("LNG");//System.out.println("IF ****"+VSettle_ICE_JKM);
									PhyCurFrdPrice=rset2.getString(4)==null?"0":rset2.getString(4); //SB20210406;
								}
								else
								{
									VSettle_RLNG_PHYS.add(rset2.getString(5)==null?"0":rset2.getString(5));  //RLNG_PHYS_FORWARD  //SB20210227
									VSettle_RLNG_PHYS_FLAG.add("RLNG");//System.out.println("IF ****"+VSettle_ICE_JKM);
									PhyCurFrdPrice=rset2.getString(5)==null?"0":rset2.getString(5); //SB20210406;
								}
								/*VSettle_RLNG_PHYS.add(rset2.getString(5)==null?"0":rset2.getString(5));  //RLNG_PHYS_FORWARD							
								PhyCurFrdPrice=rset2.getString(5)==null?"0":rset2.getString(5);
								VSettle_ICE_JKM_FLAG.add("Y");
								VSettle_RLNG_PHYS_FLAG.add("Y");*/
							}
							else
							{
								VSettle_ICE_JKM.add("");
								VSettle_RLNG_PHYS.add("0");
								FinCurFrdPrice="0";
								PhyCurFrdPrice="0";
								VSettle_ICE_JKM_FLAG.add("N");
								VSettle_RLNG_PHYS_FLAG.add("N");//System.out.println("ELSe ****"+VSettle_ICE_JKM);
							}
						}
						
			////^^^^^^^Constant Pulling from Table////////////////////////////
						String Constant="";

			////^^^^^^^Price Pulling from Table////////////////////////////
			if(BuySell.equalsIgnoreCase("Sell"))
				TCQ="-"+TCQ;
			double Ori_Exposure=0;
			////////////////////////////////Get Price Type from Existing system //////////////////////
			if(PriceTypeCargo.equals("M"))
			{
				VPriceType.add("FLOAT");
				VDealPriceCurve.add("LNG_ICE_JKM");
				if(BuySell.equalsIgnoreCase("BUY"))
					VDealPhysCurve.add("LNG_PHYS_INDIA"); //SB20210227
				else
					VDealPhysCurve.add("RLNG_PHYS_INDIA"); //SB20210227
				VDealTCQ.add(TCQ);
				VRLNG_PHYS_IN_EXPO.add(TCQ);
				RLNG_PHYS_IN_EXPO_Sum=""+(Double.parseDouble(RLNG_PHYS_IN_EXPO_Sum)+Double.parseDouble(TCQ));
				VRLNG_PHYS_IN_EXPO_Sum.add(RLNG_PHYS_IN_EXPO_Sum);
				VLNG_ICE_JKM_EXPO.add(nf.format((-1)*Double.parseDouble(TCQ)*Double.parseDouble(Slope)));
				Ori_Exposure=(-1)*Double.parseDouble(TCQ)*Double.parseDouble(Slope);
			}
			else if(PriceTypeCargo.equals("F"))
			{
				VPriceType.add("FIXED");
				VDealPriceCurve.add("RLNG_PHYS_INDIA");
				if(BuySell.equalsIgnoreCase("BUY"))
					VDealPhysCurve.add("LNG_PHYS_INDIA"); //SB20210227
				else
					VDealPhysCurve.add("RLNG_PHYS_INDIA"); //SB20210227
				VDealTCQ.add(TCQ);
				VRLNG_PHYS_IN_EXPO.add(TCQ);
				RLNG_PHYS_IN_EXPO_Sum=""+(Double.parseDouble(RLNG_PHYS_IN_EXPO_Sum)+Double.parseDouble(TCQ));
				VRLNG_PHYS_IN_EXPO_Sum.add(RLNG_PHYS_IN_EXPO_Sum);
				//SB20201102	VLNG_ICE_JKM_EXPO.add(0);
				VLNG_ICE_JKM_EXPO.add(""); //SB20201102
				Slope="1"; //Sb20200921
			}
			else
			{
				VPriceType.add("FIXED");
				VDealPriceCurve.add("RLNG_PHYS_INDIA");
				if(BuySell.equalsIgnoreCase("BUY"))
					VDealPhysCurve.add("LNG_PHYS_INDIA"); //SB20210227
				else
					VDealPhysCurve.add("RLNG_PHYS_INDIA"); //SB20210227
				VCargoRefNo.add("0");
				VDealTCQ.add(0);
				VRLNG_PHYS_IN_EXPO.add(0);
				VRLNG_PHYS_IN_EXPO_Sum.add("0");
				//SB20201102 VLNG_ICE_JKM_EXPO.add(0);
				VLNG_ICE_JKM_EXPO.add(""); //SB20201102
			}
			//////////////////////////////////////////////////////////////////////////////////////////
			int no_of_days = -1; int ContEndNoOfDays = -1; int ContPriorGasNoOfDays = -1;
			queryString = "SELECT TO_DATE('"+GenDt+"','DD/MM/YYYY')-TO_DATE('"+from_date+"','DD/MM/YYYY')+1 FROM DUAL" ;
		//	System.out.println(" DUAL: "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next()) {
				no_of_days = rset.getInt(1);   //SB20201109: May not be in use
			}
			//SB20201109: NOTE:  "+ContEndDt+"  means Gas date or Delivery Date
			queryString = "SELECT TO_DATE('"+ContEndDt+"','DD/MM/YYYY')-TO_DATE('"+GenDt+"','DD/MM/YYYY') FROM DUAL" ;
		//	System.out.println(" DUAL: "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next()) {
				ContEndNoOfDays = rset.getInt(1);
			}
			queryString = "SELECT TO_DATE('"+GasDt+"','DD/MM/YYYY')-TO_DATE('"+GenDt+"','DD/MM/YYYY') FROM DUAL" ;
			//	System.out.println(" DUAL: "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next()) {
					ContPriorGasNoOfDays = rset.getInt(1);
				}
				
		//SB20201109	if(ContEndNoOfDays<0)
			if(ContEndNoOfDays<=0)  //SB20201109: As per feedback from Shiladitya dt:9th Nov 2020
			{
				VRU_Physical_Leg.add("R");
				VRLNG_PHYS_INDIA_EXPOSURE_U.add("0");
			}
			else
			{
				VRU_Physical_Leg.add("U");
				VRLNG_PHYS_INDIA_EXPOSURE_U.add(TCQ);
			}
			double LNG_ICE_JKM_EXPO=0; //SB20201019
	//		LNG_ICE_JKM_EXPO=(-1)*Double.parseDouble(TCQ)*Double.parseDouble(Slope);
			if(BuySell.equalsIgnoreCase("Sell"))
				LNG_ICE_JKM_EXPO=(-1)*Double.parseDouble(TCQ)*Double.parseDouble(Slope);
			else
				LNG_ICE_JKM_EXPO=Double.parseDouble(TCQ)*Double.parseDouble(Slope);
	//		System.out.println(LNG_ICE_JKM_EXPO+" LNG_ICE_JKM_EXPO: "+LNG_ICE_JKM_EXPO);
		//	System.out.println(CumuPercReduce+" PercReduce: "+PercReduce);
			double FinancialReduction=Double.parseDouble(PercReduce)/100; //SB20201006

		//	System.out.println(BaseValue+" BaseValue: "+DropOff);
			/////////////////////BASE VALUE Episode//////////////////
			int factor=21;
			FinancialReduction=Double.parseDouble(CumuPercReduce)/100; 
			if(BaseValue>0 && DropOff.equals("Y"))  //SB: Dropoff Logic: if Y multiply by BaseValue i.e. days(excluding SAT/SUN & HOLI)
			//SB20201019	LNG_ICE_JKM_EXPO=BaseValue-BaseValue*FinancialReduction; //SB20201006
				LNG_ICE_JKM_EXPO=LNG_ICE_JKM_EXPO-(LNG_ICE_JKM_EXPO*FinancialReduction)*BaseValue; //SB20201006
			LNG_ICE_JKM_EXPO=Double.parseDouble(nf.format(LNG_ICE_JKM_EXPO)); //SB20201025
		//	System.out.println(LNG_ICE_JKM_EXPO+" LNG_ICE_JKM_EXPO: "+LNG_ICE_JKM_EXPO);
			/////////////////////////////////////////////////////////
			Ori_Exposure=(-1)*Double.parseDouble(TCQ)*Double.parseDouble(Slope);
	////SB		BaseValue=BaseValue-Double.parseDouble(CumuPercReduce);
	///SB		LNG_ICE_JKM_EXPO=BaseValue;
		///	System.out.println(DealPrice+" ContEndNoOfDays: "+ContEndNoOfDays);
			//SB20201109	if(PriceTypeCargo.equals("M") && ContEndNoOfDays<0)
			if(PriceTypeCargo.equals("M") && ContEndNoOfDays<=0) //SB20201109: As per feedback from Shiladitya dt:9th Nov 2020
			{
				VRU_Financial_Leg.add("R");
				VLNG_ICE_JKM_EXPOSURE_U.add("0");
			///	VLNG_ICE_JKM_EXPOSURE_U.add(nf.format((-1)*Double.parseDouble(TCQ)*Double.parseDouble(Slope)));
					U_Phy_Leg=(Double.parseDouble(TCQ)*Double.parseDouble(PhyCurFrdPrice));
//SB20201026				VU_Phy_Leg.add(nf.format(Double.parseDouble(TCQ)*Double.parseDouble(PhyCurFrdPrice)));
					U_Fin_Leg=(LNG_ICE_JKM_EXPO*Double.parseDouble(FinCurFrdPrice));
//SB20201026				VU_Fin_Leg.add(nf.format(LNG_ICE_JKM_EXPO*Double.parseDouble(FinCurFrdPrice)));
					R_Fin_Leg=(Double.parseDouble(AllocQty)*Double.parseDouble(DealPrice));
				VR_Fin_Leg.add(nf.format(Double.parseDouble(AllocQty)*Double.parseDouble(DealPrice)));
				VPNL_PHYS_REALISED.add(Double.parseDouble(TCQ) - 0); //SB20201022
				VPNL_ICE_JKM_REALISED.add(nf.format(Ori_Exposure - 0)); //SB20201022: (i.e., OriExpo-DropOff)
				
			}
			//SB20201109	else if(PriceTypeCargo.equals("M") && ContEndNoOfDays>=0)
			else if(PriceTypeCargo.equals("M") && ContEndNoOfDays>0) //SB20201109: As per feedback from Shiladitya dt:9th Nov 2020
			{/// System.out.println(PriceTypeCargo+" FinCurFrdPrice: "+FinCurFrdPrice);
				if(Double.parseDouble(FinCurFrdPrice)==0)
				{
					VRU_Financial_Leg.add("R");
					VLNG_ICE_JKM_EXPOSURE_U.add("0");
					VPNL_PHYS_REALISED.add(Double.parseDouble(TCQ) - Double.parseDouble(TCQ)); //SB20201022
					VPNL_ICE_JKM_REALISED.add(nf.format(Ori_Exposure - 0)); //SB20201022 (i.e., OriExpo-DropOff)
				}
				else
				{
					VRU_Financial_Leg.add("U");
					if(BuySell.equalsIgnoreCase("Buy"))  //SB20201203:Based on UAT by Shiladitya For BUY deal calcultion and reverse sign , else giving wrong sign(in VLNG_ICE_JKM_EXPOSURE_U) and value(in VPNL_ICE_JKM_REALISED)
						LNG_ICE_JKM_EXPO=(-1)*LNG_ICE_JKM_EXPO;
					VLNG_ICE_JKM_EXPOSURE_U.add(nf.format(LNG_ICE_JKM_EXPO)); //SB20201006
					VPNL_PHYS_REALISED.add(Double.parseDouble(TCQ) - Double.parseDouble(TCQ)); //SB20201022
					VPNL_ICE_JKM_REALISED.add(nf.format(Ori_Exposure - LNG_ICE_JKM_EXPO)); //SB20201022 (i.e., OriExpo-DropOff)
				}
				
					U_Phy_Leg=(Double.parseDouble(TCQ)*Double.parseDouble(PhyCurFrdPrice));
					//SB20201026			VU_Phy_Leg.add(nf.format(Double.parseDouble(TCQ)*Double.parseDouble(PhyCurFrdPrice)));
					U_Fin_Leg=(LNG_ICE_JKM_EXPO*Double.parseDouble(FinCurFrdPrice));
					//SB20201026			VU_Fin_Leg.add(nf.format(LNG_ICE_JKM_EXPO*Double.parseDouble(FinCurFrdPrice)));
					R_Fin_Leg=(Double.parseDouble(AllocQty)*Double.parseDouble(DealPrice));
				VR_Fin_Leg.add(nf.format(Double.parseDouble(AllocQty)*Double.parseDouble(DealPrice)));
				
			}
			else //if(PriceTypeCargo.equals("F"))
			{
			//SB20210211	if(ContPriorGasNoOfDays<0) //SB20200921
				if(ContPriorGasNoOfDays<=0) //SB20210211: observed 1 day less Reliased count
				{
					//SB20201102 VRU_Financial_Leg.add("R"); //SB20200921
					VRU_Financial_Leg.add(""); //SB20201102
					U_Fin_Leg=0;//(LNG_ICE_JKM_EXPO*Double.parseDouble(DealPrice));
				//Sb20200923	VU_Fin_Leg.add(nf.format(LNG_ICE_JKM_EXPO*Double.parseDouble(DealPrice)));
					//SB20201026			VU_Fin_Leg.add("0");
					U_Phy_Leg=0;//(Double.parseDouble(TCQ)*Double.parseDouble(PhyCurFrdPrice)); //SB20200924
				//SB20200924	VU_Phy_Leg.add(nf.format(Double.parseDouble(TCQ)*Double.parseDouble(PhyCurFrdPrice)));
					//SB20201026			VU_Phy_Leg.add("0");
					VPNL_PHYS_REALISED.add(TCQ); //SB20210211 
				}
				else
				{
					//SB20201102 VRU_Financial_Leg.add("U");
					VRU_Financial_Leg.add(""); //SB20201102 
					U_Fin_Leg=(LNG_ICE_JKM_EXPO*Double.parseDouble(DealPrice));
					//SB20201026		VU_Fin_Leg.add(nf.format(LNG_ICE_JKM_EXPO*Double.parseDouble(DealPrice)));
					U_Phy_Leg=(Double.parseDouble(TCQ)*Double.parseDouble(PhyCurFrdPrice));
					//SB20201026			VU_Phy_Leg.add(nf.format(Double.parseDouble(TCQ)*Double.parseDouble(PhyCurFrdPrice)));
					VPNL_PHYS_REALISED.add("0"); //SB20210211
				}
		
				//SB20201102	VLNG_ICE_JKM_EXPOSURE_U.add("0");
					VLNG_ICE_JKM_EXPOSURE_U.add(""); //SB20201102
				//SB20200916 U_Fin_Leg=(LNG_ICE_JKM_EXPO*Double.parseDouble(FinCurFrdPrice));
				//SB20200916VU_Fin_Leg.add(nf.format(LNG_ICE_JKM_EXPO*Double.parseDouble(FinCurFrdPrice)));
		
				//	System.out.println(DealPrice+" DealPrice: "+AllocQty);	
				
				R_Fin_Leg=(Double.parseDouble(AllocQty)*Double.parseDouble(DealPrice));
				VR_Fin_Leg.add(nf.format(Double.parseDouble(AllocQty)*Double.parseDouble(DealPrice)));
				//SB20210115	VPNL_PHYS_REALISED.add("0"); //SB20201022 
				//SB20210211    VPNL_PHYS_REALISED.add(TCQ); //SB20210115 
				VPNL_ICE_JKM_REALISED.add(""); //SB20201022
			}
			///System.out.println(U_Phy_Leg+"+" +U_Fin_Leg+ "+"+R_Fin_Leg);	
			VTotal.add(nf.format(U_Phy_Leg+U_Fin_Leg+R_Fin_Leg));
			////////////////////////////////////////////////////////////
		}
		catch(Exception e)
		{
			//System.out.println("Exception in Databean_Contract_MasterReport--->fetchcustomer()-->"+e);
			  e.printStackTrace();	
		}
	}
	/////////////////////SB20200817////////////////////////////
	public void MRCR_ContDtlNew(String IndexType, String BuySell, String PriceTypeCargo, String DealPrice, String TCQ, String AllocQty, String GasDt, String GenDt, String ContEndDt, String PhyCurFrdPrice, String PhyCurFrdPriceNm, String FinCurFrdPrice, String FinCurFrdPriceNm, String Slope, String PriorMthCurvePrice, String PercReduce,String CumuPercReduce, double BaseValue, String DropOff, String Const, String FinPriceConMth)
	{
		try
		{
			String PriceType=""; 
			if(PriceTypeCargo.equalsIgnoreCase("Fixed"))
			{
				PriceTypeCargo="F";
				PriceIndexName="-";
			}
			else
				PriceTypeCargo="M";
			double U_Phy_Leg=0;
			double U_Fin_Leg=0;
			double R_Fin_Leg=0;
		////	MappId="J";
			VBuySell.add(BuySell);
	//		System.out.println(GasDt+" PriceTypeCargo****"+PriceTypeCargo);
		
			String tempGasDt[]=GasDt.split("/");
			String GasMth=tempGasDt[1]; String GasYr=tempGasDt[2]; String GasMthYr=GasMth+"/"+GasYr;
			
			String Gen_Dt=to_date; 
			String tempGen_Dt[]=Gen_Dt.split("/");
			String GenMth=tempGen_Dt[1]; String GenYr=tempGen_Dt[2]; String GenMthYr=GenMth+"/"+GenYr;
		
			////Settlement Price Pulling from Table////////////////////////////
			String FirstDateGasMthYr="01/"+GasMthYr; String LastDateGasMthYr="";
			queryString = "SELECT TO_CHAR(LAST_DAY(TO_DATE('"+FirstDateGasMthYr+"','DD/MM/YYYY')),'DD/MM/YYYY') FROM DUAL";
			//	System.out.println("Query for generating Last Date of the selected Month = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					LastDateGasMthYr = rset.getString(1);
				}
			
	//////////////SB20201105: Introduced due to multiple record on Curve Price Tbl////////////////////////////
				int CountPlattEntDt=0; String PlattEntDt="";
				/*SB20210324 queryString = "select COUNT(ENT_DT) from FMS9_FORWARD_PRICE_DTL "
						+ " where ENT_DT= to_date('"+Gen_Dt+"','dd/mm/yyyy') AND CURVE_TYPE='F' AND FLAG='Y' ";*/
				queryString = "select COUNT(ENT_DT) from FMS9_FORWARD2_PRICE_DTL "
						+ " where ENT_DT= to_date('"+Gen_Dt+"','dd/mm/yyyy') AND CURVE_TYPE='Forward' AND FLAG='Y' ";
			//	System.out.println("FMS9_CURVE_PRICE_DTL: "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					CountPlattEntDt = rset.getInt(1);
				}
				if(CountPlattEntDt>0)
				{
				//	PlattEntDt=Gen_Dt; 
				//	System.out.println("1. PlattEntDt: "+PlattEntDt);
					/*SB20210324	queryString = "SELECT DISTINCT TO_CHAR(ENT_DT, 'dd/mm/yyyy')  from FMS9_FORWARD_PRICE_DTL "
							+ " where ENT_DT= to_date('"+Gen_Dt+"','dd/mm/yyyy') AND CURVE_TYPE='F' AND FLAG='Y' "; */
					queryString = "SELECT DISTINCT TO_CHAR(ENT_DT, 'dd/mm/yyyy')  from FMS9_FORWARD2_PRICE_DTL "
							+ " where ENT_DT= to_date('"+Gen_Dt+"','dd/mm/yyyy') AND CURVE_TYPE='Forward' AND FLAG='Y' ";
				//	System.out.println("A. FMS9_CURVE_PRICE_DTL: "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						PlattEntDt = rset.getString(1)==null?"":rset.getString(1);
					}
				}
				else
				{
					/*SB20210324	queryString = "SELECT TO_CHAR(MAX(ENT_DT), 'dd/mm/yyyy')  from FMS9_FORWARD_PRICE_DTL "
							+ " where ENT_DT<= to_date('"+Gen_Dt+"','dd/mm/yyyy') AND CURVE_TYPE='F' AND FLAG='Y' "; */
					queryString = "SELECT TO_CHAR(MAX(ENT_DT), 'dd/mm/yyyy')  from FMS9_FORWARD2_PRICE_DTL "
							+ " where ENT_DT<= to_date('"+Gen_Dt+"','dd/mm/yyyy') AND CURVE_TYPE='Forward' AND FLAG='Y' ";
				//	System.out.println("B. FMS9_CURVE_PRICE_DTL: "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						PlattEntDt = rset.getString(1)==null?"":rset.getString(1);
					}
					if(PlattEntDt.equals(""))
					{
						/*SB20210324	queryString = "SELECT TO_CHAR(MIN(ENT_DT), 'dd/mm/yyyy')  from FMS9_FORWARD_PRICE_DTL "
								+ " where ENT_DT>= to_date('"+Gen_Dt+"','dd/mm/yyyy') AND CURVE_TYPE='F' AND FLAG='Y' "; */
						queryString = "SELECT TO_CHAR(MIN(ENT_DT), 'dd/mm/yyyy')  from FMS9_FORWARD2_PRICE_DTL "
								+ " where ENT_DT>= to_date('"+Gen_Dt+"','dd/mm/yyyy') AND CURVE_TYPE='Forward' AND FLAG='Y' ";
					//	System.out.println("C. FMS9_CURVE_PRICE_DTL: "+queryString);
						rset = stmt.executeQuery(queryString);
						if(rset.next())
						{
							PlattEntDt = rset.getString(1)==null?"":rset.getString(1);
						}
						if(PlattEntDt.equals(""))
						{
							/*SB20210324	queryString = "SELECT TO_CHAR(MAX(ENT_DT), 'dd/mm/yyyy')  from FMS9_FORWARD_PRICE_DTL "
									+ " where ENT_DT<= to_date('"+Gen_Dt+"','dd/mm/yyyy') AND CURVE_TYPE='F' AND FLAG='Y' "; */
							queryString = "SELECT TO_CHAR(MAX(ENT_DT), 'dd/mm/yyyy')  from FMS9_FORWARD2_PRICE_DTL "
									+ " where ENT_DT<= to_date('"+Gen_Dt+"','dd/mm/yyyy') AND CURVE_TYPE='Forward' AND FLAG='Y' ";
					//		System.out.println("D. FMS9_CURVE_PRICE_DTL: "+queryString);
							rset = stmt.executeQuery(queryString);
							if(rset.next())
							{
								PlattEntDt = rset.getString(1)==null?"":rset.getString(1);
							}
						}
					}
				}
			//	System.out.println("V2. FMS9_FORWARD2_PRICE_DTL: PlattEntDt: "+PlattEntDt);
				//////////////////////////^^^^SB20201105////////////////////////////////////////////////////////////////////
				/*SB20210324	queryString ="SELECT "+FinCurFrdPriceNm+", "+PhyCurFrdPriceNm+" FROM FMS9_FORWARD_PRICE_DTL "+ 
							" WHERE CURVE_DD_MM_YR=TO_DATE('"+FirstDateGasMthYr+"','DD/MM/YYYY') AND CURVE_TYPE='F' AND FLAG='Y' AND ENT_DT=TO_DATE('"+PlattEntDt+"','DD/MM/YYYY') "; 
				//	System.out.println(IndexType+" :SETTLEMENT PRICE: FMS9_CURVE_PRICE_DTL: "+queryString);
						rset2 = stmt.executeQuery(queryString);			
						if(rset2.next())
						{
							if(PriceTypeCargo.equals("M") && Double.parseDouble(FinCurFrdPrice)==0)
							{
								VSettle_ICE_JKM.add(rset2.getString(1)==null?"0":rset2.getString(1));  //ICE_JKM_FORWARD
								FinCurFrdPrice=rset2.getString(1);
								VSettle_ICE_JKM_FLAG.add(IndexType);
							}
							else
							{
								VSettle_ICE_JKM.add("0");
								FinCurFrdPrice="0";
								VSettle_ICE_JKM_FLAG.add("NA");
							}
							VSettle_RLNG_PHYS.add(rset2.getString(2)==null?"0":rset2.getString(2));  //RLNG_PHYS_FORWARD //SB20210227
							VSettle_RLNG_PHYS_FLAG.add(PhyCurFrdPriceNm);//System.out.println("IF ****"+VSettle_ICE_JKM);
							PhyCurFrdPrice=rset2.getString(2)==null?"0":rset2.getString(2);
						
							FrdPriceEntDt=""+PlattEntDt;
						}
					else
						{
							queryString ="SELECT "+FinCurFrdPriceNm+", "+PhyCurFrdPriceNm+" FROM FMS9_FORWARD_PRICE_DTL "
									+ "WHERE CURVE_DD_MM_YR>=TO_DATE('"+FirstDateGasMthYr+"','DD/MM/YYYY') AND CURVE_DD_MM_YR<=TO_DATE('"+LastDateGasMthYr+"','DD/MM/YYYY') AND ENT_DT=(SELECT MAX(ENT_DT) FROM FMS9_FORWARD_PRICE_DTL WHERE CURVE_DD_MM_YR>=TO_DATE('"+FirstDateGasMthYr+"','DD/MM/YYYY') "
									+ " AND CURVE_DD_MM_YR<=TO_DATE('"+LastDateGasMthYr+"','DD/MM/YYYY') AND CURVE_TYPE='F' AND FLAG='Y') AND CURVE_TYPE='F' AND FLAG='Y' "; 
						//	System.out.println("222*****SETTLEMENT PRICE: FMS9_CURVE_PRICE_DTL: "+queryString);
							rset2 = stmt.executeQuery(queryString);			
							if(rset2.next())
							{
								if(PriceTypeCargo.equals("M") && Double.parseDouble(FinCurFrdPrice)==0)
								{
									VSettle_ICE_JKM.add(rset2.getString(1)==null?"0":rset2.getString(1));  //ICE_JKM_FORWARD
									FinCurFrdPrice=rset2.getString(1);
									VSettle_ICE_JKM_FLAG.add(IndexType);
								}
								else
								{
									VSettle_ICE_JKM.add("0");
									FinCurFrdPrice="0";
									VSettle_ICE_JKM_FLAG.add("NA");
								}
								VSettle_RLNG_PHYS.add(rset2.getString(2)==null?"0":rset2.getString(2));  //RLNG_PHYS_FORWARD //SB20210227
								VSettle_RLNG_PHYS_FLAG.add(PhyCurFrdPriceNm);//System.out.println("IF ****"+VSettle_ICE_JKM);
								PhyCurFrdPrice=rset2.getString(2)==null?"0":rset2.getString(2);
							}
							else
							{
								VSettle_ICE_JKM.add("");
								VSettle_RLNG_PHYS.add("0");
								FinCurFrdPrice="0";
								PhyCurFrdPrice="0";
								VSettle_ICE_JKM_FLAG.add("N");
								VSettle_RLNG_PHYS_FLAG.add("N");//System.out.println("ELSe ****"+VSettle_ICE_JKM);
							}
						}
						*/
				if(PriceTypeCargo.equals("M") && Double.parseDouble(FinCurFrdPrice)==0) 
				{
					queryString ="SELECT SETTLE_PRICE FROM FMS9_FORWARD2_PRICE_DTL "+ 
					//SB20210708:Old Ver. Same Phy&Fin		" WHERE CURVE_DD_MM_YR=TO_DATE('"+FirstDateGasMthYr+"','DD/MM/YYYY') AND CURVE_TYPE='Forward' AND FLAG='Y' AND ENT_DT=TO_DATE('"+PlattEntDt+"','DD/MM/YYYY') "
							" WHERE CURVE_DD_MM_YR=TO_DATE('"+FinPriceConMth+"','DD/MM/YYYY') AND CURVE_TYPE='Forward' AND FLAG='Y' AND ENT_DT=TO_DATE('"+PlattEntDt+"','DD/MM/YYYY') "
							+ "AND CURVE_NM='"+FinCurFrdPriceNm+"' ";
				//	System.out.println(IndexType+" :FORWARD2 PRICE: FMS9_FORWARD2_PRICE_DTL: "+queryString);
						rset2 = stmt.executeQuery(queryString);			
						if(rset2.next())
						{
							VSettle_ICE_JKM.add(rset2.getString(1)==null?"0":rset2.getString(1));  //ICE_JKM_FORWARD
							FinCurFrdPrice=rset2.getString(1);
							VSettle_ICE_JKM_FLAG.add(IndexType);
						}
						else
						{
							/////////////////////SB20210703: Incase of Forward Price of Curve Nm not in latest date///////////////////////
							queryString ="SELECT SETTLE_PRICE, TO_CHAR(TO_DATE(ENT_DT,'DD-MON-YY'),'DD/MM/YYYY') FROM FMS9_FORWARD2_PRICE_DTL "+ 
						//SB20210708:Old Ver. Same Phy&Fin					" WHERE CURVE_DD_MM_YR=TO_DATE('"+FirstDateGasMthYr+"','DD/MM/YYYY') AND CURVE_TYPE='Forward' AND FLAG='Y' AND CURVE_NM='"+FinCurFrdPriceNm+"' "
									" WHERE CURVE_DD_MM_YR=TO_DATE('"+FinPriceConMth+"','DD/MM/YYYY') AND CURVE_TYPE='Forward' AND FLAG='Y' AND CURVE_NM='"+FinCurFrdPriceNm+"' "
									+ "AND ENT_DT=(SELECT TO_DATE(MAX(ENT_DT),'DD-MON-YY') FROM FMS9_FORWARD2_PRICE_DTL  WHERE CURVE_DD_MM_YR=TO_DATE('"+FinPriceConMth+"','DD/MM/YYYY') AND " 
									+ "CURVE_TYPE='Forward' AND FLAG='Y' AND ENT_DT<=TO_DATE('"+PlattEntDt+"','DD/MM/YYYY') AND CURVE_NM='"+FinCurFrdPriceNm+"')";
							//	System.out.println(IndexType+" :Incase of Forward Price of Curve Nm not in latest date "+PlattEntDt+":FORWARD2 PRICE: FMS9_FORWARD2_PRICE_DTL: "+queryString);
							rset2 = stmt.executeQuery(queryString);			
							if(rset2.next())
							{
								VSettle_ICE_JKM.add(rset2.getString(1)==null?"0":rset2.getString(1));  //ICE_JKM_FORWARD
								FinCurFrdPrice=rset2.getString(1);
								VSettle_ICE_JKM_FLAG.add(IndexType+": "+rset2.getString(2));
							}
							else /////////////////////^^^^^SB20210703: Incase of Forward Price of Curve Nm not in latest date///////////////////////
							{
								VSettle_ICE_JKM.add("0");
								FinCurFrdPrice="1";  ///FinCurFrdPrice="0" was showing as "R" thus FinCurFrdPrice="1" so that Status can be made "U" as applicable , if Forward Price is not available in System 
								VSettle_ICE_JKM_FLAG.add("NA");
							}
						}
				}	
				else
				{
					VSettle_ICE_JKM.add("0");
					VSettle_ICE_JKM_FLAG.add("NA");
					FinCurFrdPrice="0";
				}
				queryString ="SELECT SETTLE_PRICE FROM FMS9_FORWARD2_PRICE_DTL "+ 
						" WHERE CURVE_DD_MM_YR=TO_DATE('"+FirstDateGasMthYr+"','DD/MM/YYYY') AND CURVE_TYPE='Forward' AND FLAG='Y' AND ENT_DT=TO_DATE('"+PlattEntDt+"','DD/MM/YYYY') "
						+ "AND CURVE_NM='"+PhyCurFrdPriceNm+"' ";
			//	System.out.println(IndexType+" :FORWARD PRICE: FMS9_FORWARD2_PRICE_DTL: "+queryString);
					rset2 = stmt.executeQuery(queryString);			
					if(rset2.next())
					{
						VSettle_RLNG_PHYS.add(rset2.getString(1)==null?"0":rset2.getString(1));  //RLNG_PHYS_FORWARD //SB20210227
						VSettle_RLNG_PHYS_FLAG.add(PhyCurFrdPriceNm);//System.out.println("IF ****"+VSettle_ICE_JKM);
						PhyCurFrdPrice=rset2.getString(1)==null?"0":rset2.getString(1);
					}
					else
					{
	/////////////////////SB20210703: Incase of Forward Price of Curve Nm not in latest date///////////////////////
									queryString ="SELECT SETTLE_PRICE, TO_CHAR(TO_DATE(ENT_DT,'DD-MON-YY'),'DD/MM/YYYY') FROM FMS9_FORWARD2_PRICE_DTL "+ 
											" WHERE CURVE_DD_MM_YR=TO_DATE('"+FirstDateGasMthYr+"','DD/MM/YYYY') AND CURVE_TYPE='Forward' AND FLAG='Y' AND CURVE_NM='"+PhyCurFrdPriceNm+"' "
											+ "AND ENT_DT=(SELECT TO_DATE(MAX(ENT_DT),'DD-MON-YY') FROM FMS9_FORWARD2_PRICE_DTL  WHERE CURVE_DD_MM_YR=TO_DATE('"+FirstDateGasMthYr+"','DD/MM/YYYY') AND " 
											+ "CURVE_TYPE='Forward' AND FLAG='Y' AND ENT_DT<=TO_DATE('"+PlattEntDt+"','DD/MM/YYYY') AND CURVE_NM='"+PhyCurFrdPriceNm+"')";
								//		System.out.println(IndexType+" :Incase of Forward Price of Curve Nm not in latest date "+PlattEntDt+":FORWARD2 PRICE: FMS9_FORWARD2_PRICE_DTL: "+queryString);
									rset2 = stmt.executeQuery(queryString);			
									if(rset2.next())
									{
										VSettle_RLNG_PHYS.add(rset2.getString(1)==null?"0":rset2.getString(1));  //RLNG_PHYS_FORWARD //SB20210227
										VSettle_RLNG_PHYS_FLAG.add(PhyCurFrdPriceNm+": "+rset2.getString(2));//System.out.println("IF ****"+VSettle_ICE_JKM);
										PhyCurFrdPrice=rset2.getString(1)==null?"0":rset2.getString(1);
										PlattEntDt=rset2.getString(2)==null?"":rset2.getString(2);
									}
									else /////////////////////^^^^^SB20210703: Incase of Forward Price of Curve Nm not in latest date///////////////////////
									{
										VSettle_RLNG_PHYS.add("0");
										VSettle_RLNG_PHYS_FLAG.add("NA");
										PhyCurFrdPrice="0";
									}
					}
			/////////////SB20210506: Get PPAC Price from FORWARD2///////////////////////
					double ppacPriceApplicable=0;
					if(PriceTypeCargo.equals("M") && PPACPriceFlag.equals("1"))
					{
					queryString ="SELECT SETTLE_PRICE FROM FMS9_FORWARD2_PRICE_DTL "+ 
							" WHERE CURVE_DD_MM_YR=TO_DATE('"+FirstDateGasMthYr+"','DD/MM/YYYY') AND CURVE_TYPE='Forward' AND FLAG='Y' AND ENT_DT=TO_DATE('"+PlattEntDt+"','DD/MM/YYYY') "
							+ "AND CURVE_NM='PPAC' ";
				//	System.out.println(IndexType+" :FORWARD PRICE: FMS9_FORWARD2_PRICE_DTL: "+queryString);
						rset2 = stmt.executeQuery(queryString);			
						if(rset2.next())
						{
							VPriceTypeDealRate.add(rset2.getString(1)==null?"1.11":rset2.getString(1));  //RLNG_PHYS_FORWARD //SB20210227
							ppacPriceApplicable=rset2.getDouble(1);
						}
						else
						{
							VPriceTypeDealRate.add("0");
							ppacPriceApplicable=0;
						}
					}
					else if(PriceTypeCargo.equals("M") && PPACPriceFlag.equals("0")) //SB20210530
						VPriceTypeDealRate.add("");//SB20210530
					else if(PriceTypeCargo.equals("F")) //SB20210530
						VPriceTypeDealRate.add(DealPrice);  ////SB20210530 Contract Rate SN_MST
					else 
						VPriceTypeDealRate.add("");
			//////////////^^SB20210506: Get PPAC Price from FORWARD2////////////////////
					
				FrdPriceEntDt=""+PlattEntDt;
			////^^^^^^^Constant Pulling from Table////////////////////////////
						String Constant="";

			////^^^^^^^Price Pulling from Table////////////////////////////
			if(BuySell.equalsIgnoreCase("Sell"))
				TCQ="-"+TCQ;
			double Ori_Exposure=0; 
			String FinPrice="0";//SV20210510
			////////////////////////////////Get Price Type from Existing system //////////////////////
			if(PriceTypeCargo.equals("M"))
			{
				VPriceType.add("FLOAT");
				VDealPriceCurve.add("LNG_ICE_JKM");
				VDealTCQ.add(TCQ);
				VRLNG_PHYS_IN_EXPO.add(TCQ);
				RLNG_PHYS_IN_EXPO_Sum=""+(Double.parseDouble(RLNG_PHYS_IN_EXPO_Sum)+Double.parseDouble(TCQ));
				VRLNG_PHYS_IN_EXPO_Sum.add(RLNG_PHYS_IN_EXPO_Sum);
				//SB20210504	VLNG_ICE_JKM_EXPO.add(nf.format((-1)*Double.parseDouble(TCQ)*Double.parseDouble(Slope)));
				//SB20210504 Ori_Exposure=(-1)*Double.parseDouble(TCQ)*Double.parseDouble(Slope);
				//////SB20210504/////LOGIC for PPAC//////////////
				
				if(Double.parseDouble(FinCurFrdPrice)>0) 
					FinPrice=nf.format((Double.parseDouble(""+FinCurFrdPrice)*Double.parseDouble(Slope))+Double.parseDouble(Const)); //SB20210514
				else
					FinPrice=DealPrice;
				if(PPACPriceFlag.equals("1"))
				{//System.out.println(DealPrice+" :DealPrice/FinPrice: "+FinPrice);
						if(Double.parseDouble(FinPrice)<(ppacPriceApplicable))
						{
							VLNG_ICE_JKM_EXPO.add(nf.format((-1)*Double.parseDouble(TCQ)*Double.parseDouble(Slope)));
							Ori_Exposure=(-1)*Double.parseDouble(TCQ)*Double.parseDouble(Slope);
						}
						else
						{
							VLNG_ICE_JKM_EXPO.add("0");
							Ori_Exposure=0;
						}
				}
				else
				{
					VLNG_ICE_JKM_EXPO.add(nf.format((-1)*Double.parseDouble(TCQ)*Double.parseDouble(Slope)));
					Ori_Exposure=(-1)*Double.parseDouble(TCQ)*Double.parseDouble(Slope);
				}
				///////////////////////////////////////
				
			}
			else if(PriceTypeCargo.equals("F"))
			{
				VPriceType.add("FIXED");
				VDealPriceCurve.add("RLNG_PHYS_INDIA");
				VDealTCQ.add(TCQ);
				VRLNG_PHYS_IN_EXPO.add(TCQ);
				RLNG_PHYS_IN_EXPO_Sum=""+(Double.parseDouble(RLNG_PHYS_IN_EXPO_Sum)+Double.parseDouble(TCQ));
				VRLNG_PHYS_IN_EXPO_Sum.add(RLNG_PHYS_IN_EXPO_Sum);
				//SB20201102	VLNG_ICE_JKM_EXPO.add(0);
				VLNG_ICE_JKM_EXPO.add(""); //SB20201102
				Slope="1"; //Sb20200921
			}
			else
			{
				VPriceType.add("FIXED");
				VDealPriceCurve.add("RLNG_PHYS_INDIA");
				VCargoRefNo.add("0");
				VDealTCQ.add(0);
				VRLNG_PHYS_IN_EXPO.add(0);
				VRLNG_PHYS_IN_EXPO_Sum.add("0");
				//SB20201102 VLNG_ICE_JKM_EXPO.add(0);
				VLNG_ICE_JKM_EXPO.add(""); //SB20201102
			}
			VDealPhysCurve.add(PhyCurFrdPriceNm); //SB20210312
			//////////////////////////////////////////////////////////////////////////////////////////
			int no_of_days = -1; int ContEndNoOfDays = -1; int ContPriorGasNoOfDays = -1;
			queryString = "SELECT TO_DATE('"+GenDt+"','DD/MM/YYYY')-TO_DATE('"+from_date+"','DD/MM/YYYY')+1 FROM DUAL" ;
		//	System.out.println(" DUAL: "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next()) {
				no_of_days = rset.getInt(1);   //SB20201109: May not be in use
			}
			//SB20201109: NOTE:  "+ContEndDt+"  means Gas date or Delivery Date
			queryString = "SELECT TO_DATE('"+ContEndDt+"','DD/MM/YYYY')-TO_DATE('"+GenDt+"','DD/MM/YYYY') FROM DUAL" ;
		//	System.out.println(" DUAL: "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next()) {
				ContEndNoOfDays = rset.getInt(1);
			}
			queryString = "SELECT TO_DATE('"+GasDt+"','DD/MM/YYYY')-TO_DATE('"+GenDt+"','DD/MM/YYYY') FROM DUAL" ;
			//	System.out.println(" DUAL: "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next()) {
					ContPriorGasNoOfDays = rset.getInt(1);
				}
				
		//SB20201109	if(ContEndNoOfDays<0)
			if(ContEndNoOfDays<=0)  //SB20201109: As per feedback from Shiladitya dt:9th Nov 2020
			{
				VRU_Physical_Leg.add("R");
				VRLNG_PHYS_INDIA_EXPOSURE_U.add("0");
			}
			else
			{
				VRU_Physical_Leg.add("U");
				VRLNG_PHYS_INDIA_EXPOSURE_U.add(TCQ);
			}
			double LNG_ICE_JKM_EXPO=0; //SB20201019
			if(BuySell.equalsIgnoreCase("Sell"))
				LNG_ICE_JKM_EXPO=(-1)*Double.parseDouble(TCQ)*Double.parseDouble(Slope);
			else
				LNG_ICE_JKM_EXPO=Double.parseDouble(TCQ)*Double.parseDouble(Slope);
	
			double FinancialReduction=Double.parseDouble(PercReduce)/100; //SB20201006

			/////////////////////BASE VALUE Episode//////////////////
			int factor=21;
			FinancialReduction=Double.parseDouble(CumuPercReduce)/100; 
			if(BaseValue>0 && DropOff.equals("Y"))  //SB: Dropoff Logic: if Y multiply by BaseValue i.e. days(excluding SAT/SUN & HOLI)
				LNG_ICE_JKM_EXPO=LNG_ICE_JKM_EXPO-(LNG_ICE_JKM_EXPO*FinancialReduction)*BaseValue; //SB20201006
	//SB20210504		LNG_ICE_JKM_EXPO=Double.parseDouble(nf.format(LNG_ICE_JKM_EXPO)); //SB20201025
			///////////////////SB20210504////////////////////////////////
			if(PPACPriceFlag.equals("1"))
			{
					if(Double.parseDouble(FinPrice)<(ppacPriceApplicable))
					{
						LNG_ICE_JKM_EXPO=Double.parseDouble(nf.format(LNG_ICE_JKM_EXPO)); //SB20201025
					}
					else
						LNG_ICE_JKM_EXPO=0; //SB20201025
			}
			else
				LNG_ICE_JKM_EXPO=Double.parseDouble(nf.format(LNG_ICE_JKM_EXPO)); //SB20201025
			///////////////////^^^SB20210504////////////////////////////////
		//	System.out.println(LNG_ICE_JKM_EXPO+" LNG_ICE_JKM_EXPO: "+LNG_ICE_JKM_EXPO);
			/////////////////////////////////////////////////////////
			//SB20210504	Ori_Exposure=(-1)*Double.parseDouble(TCQ)*Double.parseDouble(Slope);
			if(PriceTypeCargo.equals("M") && ContEndNoOfDays<=0) //SB20201109: As per feedback from Shiladitya dt:9th Nov 2020
			{
				VRU_Financial_Leg.add("R");
				VLNG_ICE_JKM_EXPOSURE_U.add("0");
					U_Phy_Leg=(Double.parseDouble(TCQ)*Double.parseDouble(PhyCurFrdPrice));
					U_Fin_Leg=(LNG_ICE_JKM_EXPO*Double.parseDouble(FinCurFrdPrice));
					R_Fin_Leg=(Double.parseDouble(AllocQty)*Double.parseDouble(DealPrice));
				VR_Fin_Leg.add(nf.format(Double.parseDouble(AllocQty)*Double.parseDouble(DealPrice)));
				VPNL_PHYS_REALISED.add(Double.parseDouble(TCQ) - 0); //SB20201022
				VPNL_ICE_JKM_REALISED.add(nf.format(Ori_Exposure - 0)); //SB20201022: (i.e., OriExpo-DropOff)
				
			}
			//SB20201109	else if(PriceTypeCargo.equals("M") && ContEndNoOfDays>=0)
			else if(PriceTypeCargo.equals("M") && ContEndNoOfDays>0) //SB20201109: As per feedback from Shiladitya dt:9th Nov 2020
			{ //System.out.println(PriceTypeCargo+" FinCurFrdPrice: "+FinCurFrdPrice);
				if(Double.parseDouble(FinCurFrdPrice)==0)
				{
					VRU_Financial_Leg.add("R");
					VLNG_ICE_JKM_EXPOSURE_U.add("0");
					VPNL_PHYS_REALISED.add(Double.parseDouble(TCQ) - Double.parseDouble(TCQ)); //SB20201022
					VPNL_ICE_JKM_REALISED.add(nf.format(Ori_Exposure - 0)); //SB20201022 (i.e., OriExpo-DropOff)
				}
				else
				{
					VRU_Financial_Leg.add("U");
					if(BuySell.equalsIgnoreCase("Buy"))  //SB20201203:Based on UAT by Shiladitya For BUY deal calcultion and reverse sign , else giving wrong sign(in VLNG_ICE_JKM_EXPOSURE_U) and value(in VPNL_ICE_JKM_REALISED)
						LNG_ICE_JKM_EXPO=(-1)*LNG_ICE_JKM_EXPO;
					VLNG_ICE_JKM_EXPOSURE_U.add(nf.format(LNG_ICE_JKM_EXPO)); //SB20201006
					VPNL_PHYS_REALISED.add(Double.parseDouble(TCQ) - Double.parseDouble(TCQ)); //SB20201022
					VPNL_ICE_JKM_REALISED.add(nf.format(Ori_Exposure - LNG_ICE_JKM_EXPO)); //SB20201022 (i.e., OriExpo-DropOff)
				}
				
					U_Phy_Leg=(Double.parseDouble(TCQ)*Double.parseDouble(PhyCurFrdPrice));
					U_Fin_Leg=(LNG_ICE_JKM_EXPO*Double.parseDouble(FinCurFrdPrice));
					R_Fin_Leg=(Double.parseDouble(AllocQty)*Double.parseDouble(DealPrice));
				VR_Fin_Leg.add(nf.format(Double.parseDouble(AllocQty)*Double.parseDouble(DealPrice)));
				
			}
			else //if(PriceTypeCargo.equals("F"))
			{
			//SB20210211	if(ContPriorGasNoOfDays<0) //SB20200921
				if(ContPriorGasNoOfDays<=0) //SB20210211: observed 1 day less Reliased count
				{
					VRU_Financial_Leg.add(""); //SB20201102
					U_Fin_Leg=0;//(LNG_ICE_JKM_EXPO*Double.parseDouble(DealPrice));
					U_Phy_Leg=0;//(Double.parseDouble(TCQ)*Double.parseDouble(PhyCurFrdPrice)); //SB20200924
					VPNL_PHYS_REALISED.add(TCQ); //SB20210211 
				}
				else
				{
					VRU_Financial_Leg.add(""); //SB20201102 
					U_Fin_Leg=(LNG_ICE_JKM_EXPO*Double.parseDouble(DealPrice));
					U_Phy_Leg=(Double.parseDouble(TCQ)*Double.parseDouble(PhyCurFrdPrice));
					VPNL_PHYS_REALISED.add("0"); //SB20210211
				}
		
					VLNG_ICE_JKM_EXPOSURE_U.add(""); //SB20201102
				
				R_Fin_Leg=(Double.parseDouble(AllocQty)*Double.parseDouble(DealPrice));
				VR_Fin_Leg.add(nf.format(Double.parseDouble(AllocQty)*Double.parseDouble(DealPrice)));
				VPNL_ICE_JKM_REALISED.add(""); //SB20201022
			}
			
			VTotal.add(nf.format(U_Phy_Leg+U_Fin_Leg+R_Fin_Leg));
			////////////////////////////////////////////////////////////
		}
		catch(Exception e)
		{
			//System.out.println("Exception in Databean_Contract_MasterReport--->fetchcustomer()-->"+e);
			  e.printStackTrace();	
		}
	}
	////////////////////////////^^^SB20210312: New logic for Phys & Fin/////////////////////////////////////////
	public void MRCR_ContDtl2(String MappId, String BuySell, String PriceTypeCargo, String DealPrice, String TCQ, String AllocQty, String GasDt, String GenDt, String ContEndDt, String PhyCurFrdPrice, String FinCurFrdPrice, String Slope, String PriorMthCurvePrice)
	{
		try
		{
			String PriceType=""; //FinCurFrdPrice=".5"; //PhyCurFrdPrice="2.5";
		
			double U_Phy_Leg=0;
			double U_Fin_Leg=0;
			double R_Fin_Leg=0;
			
			VBuySell.add(BuySell);
			
		
			String tempGasDt[]=GasDt.split("/");
			String GasMth=tempGasDt[1]; String GasYr=tempGasDt[2]; String GasMthYr=GasMth+"/"+GasYr;
			
			String Gen_Dt=to_date; 
			String tempGen_Dt[]=Gen_Dt.split("/");
			String GenMth=tempGen_Dt[1]; String GenYr=tempGen_Dt[2]; String GenMthYr=GenMth+"/"+GenYr;
		//SB	queryString ="SELECT CURVE_VALUE1, CURVE_VALUE2, CURVE_VALUE3, CURVE_VALUE4, CURVE_VALUE4 FROM FMS9_CURVE_VALUE_DTL WHERE CURVE_DD_MM_YR=TO_DATE('"+GenMthYr+"','MM/YYYY') AND CURVE_TYPE='S' ";
			queryString ="SELECT CR_DATED_BRENT, LNG_ICE_JKM, LNG_JKM, LNG_PHYS_INDIA, RLNG_PHYS_INDIA FROM FMS9_CURVE_PRICE_DTL WHERE CURVE_DD_MM_YR=TO_DATE('"+GasMthYr+"','MM/YYYY') AND CURVE_TYPE='S' ";
		//	System.out.println("FMS9_CURVE_PRICE_DTL: "+queryString);
			rset2 = stmt.executeQuery(queryString);			
			if(rset2.next())
			{
				VSlope_BRENT.add(rset2.getString(1)==null?"1":rset2.getString(1));  //CR-DATED_BRENT
				VSlope_ICE_JKM.add(rset2.getString(2)==null?"1":rset2.getString(2));  //LNG_ICE_JKM
				VSlope_JKM.add(rset2.getString(3)==null?"1":rset2.getString(3));  //LNG_JKM
				VSlope_LNG_PHYS.add(rset2.getString(4)==null?"1":rset2.getString(4));  //LNG_PHYS_INDIA
				VSlope_RLNG_PHYS.add(rset2.getString(5)==null?"1":rset2.getString(5));  //RLNG_PHYS_INDIA
				VSlope.add(rset2.getString(2)==null?"1":rset2.getString(2));  //Default: LNG_ICE_JKM 
				Slope=rset2.getString(2)==null?"1":rset2.getString(2);
				VSlope_FLAG.add("Y");
			}
			else
			{
				VSlope_BRENT.add("1.0");
				VSlope_ICE_JKM.add("1.0");
				VSlope_JKM.add("1.0");
				VSlope_LNG_PHYS.add("1.0");
				VSlope_RLNG_PHYS.add("1.0");
				VSlope.add("1.0");
				Slope="1.025";
				VSlope_FLAG.add("N");
			}
			////Settlement Price Pulling from Table////////////////////////////
			//SB	queryString ="SELECT CURVE_VALUE1, CURVE_VALUE2, CURVE_VALUE3, CURVE_VALUE4, CURVE_VALUE4 FROM FMS9_CURVE_VALUE_DTL WHERE CURVE_DD_MM_YR=TO_DATE('"+GenMthYr+"','MM/YYYY') AND CURVE_TYPE='F' ";
					queryString ="SELECT CR_DATED_BRENT, LNG_ICE_JKM, LNG_JKM, LNG_PHYS_INDIA, RLNG_PHYS_INDIA FROM FMS9_CURVE_PRICE_DTL WHERE CURVE_DD_MM_YR=TO_DATE('"+GasMthYr+"','MM/YYYY') AND CURVE_TYPE='F' ";
					//	System.out.println("SETTLEMENT PRICE: FMS9_CURVE_PRICE_DTL: "+queryString);
						rset2 = stmt.executeQuery(queryString);			
						if(rset2.next())
						{
							VSettle_ICE_JKM.add(rset2.getString(2)==null?"1":rset2.getString(2));  //ICE_JKM_FORWARD
							VSettle_RLNG_PHYS.add(rset2.getString(5)==null?"1":rset2.getString(5));  //RLNG_PHYS_FORWARD
							FinCurFrdPrice=rset2.getString(2);
							PhyCurFrdPrice=rset2.getString(5)==null?"0":rset2.getString(5); //SB20210406
							VSettle_ICE_JKM_FLAG.add("Y");
							VSettle_RLNG_PHYS_FLAG.add("Y");//System.out.println("IF ****"+VSettle_ICE_JKM);
						}
					else
						{
							VSettle_ICE_JKM.add("0");
							VSettle_RLNG_PHYS.add("0");
							FinCurFrdPrice="0";
							PhyCurFrdPrice="0";
							VSettle_ICE_JKM_FLAG.add("N");
							VSettle_RLNG_PHYS_FLAG.add("N");//System.out.println("ELSe ****"+VSettle_ICE_JKM);
						}
						
			////^^^^^^^Constant Pulling from Table////////////////////////////
						String Constant="";
				//SB		queryString ="SELECT CURVE_VALUE1, CURVE_VALUE2, CURVE_VALUE3, CURVE_VALUE4, CURVE_VALUE4 FROM FMS9_CURVE_VALUE_DTL WHERE CURVE_DD_MM_YR=TO_DATE('"+GenMthYr+"','MM/YYYY') AND CURVE_TYPE='C' ";
						queryString ="SELECT CR_DATED_BRENT, LNG_ICE_JKM, LNG_JKM, LNG_PHYS_INDIA, RLNG_PHYS_INDIA FROM FMS9_CURVE_PRICE_DTL WHERE CURVE_DD_MM_YR=TO_DATE('"+GasMthYr+"','MM/YYYY') AND CURVE_TYPE='C' ";
				//		System.out.println("CONSTANT PRICE: FMS9_CURVE_PRICE_DTL: "+queryString);
						rset2 = stmt.executeQuery(queryString);			
						if(rset2.next())
						{
							VConst_ICE_JKM.add(rset2.getString(2)==null?"0":rset2.getString(2));  //ICE_JKM_FORWARD
							VConst_RLNG_PHYS.add(rset2.getString(5)==null?"0":rset2.getString(5));  //RLNG_PHYS_FORWARD
							Constant=rset2.getString(2)==null?"0.15":rset2.getString(2);
							VConst_ICE_JKM_FLAG.add("Y");
						}
						else
						{
							VConst_ICE_JKM.add("0.15");
							VConst_RLNG_PHYS.add("0.15");
							Constant="0.15";
							VConst_ICE_JKM_FLAG.add("N");
						}
			////^^^^^^^Price Pulling from Table////////////////////////////
			if(BuySell.equalsIgnoreCase("Sell"))
				TCQ="-"+TCQ;
			if(TCQ.equalsIgnoreCase(""))
				TCQ="0";
			////////////////////////////////Get Price Type from Existing system //////////////////////
			if(PriceTypeCargo.equals("M"))
			{
				VPriceType.add("FLOAT");
				if(MappId.equals("J"))
					VDealPriceCurve.add("LNG_ICE_JKM");
				else
				//SB20201117	VDealPriceCurve.add("CR_DATED_BRENT");
					VDealPriceCurve.add("ICE_BRENT"); //SB20201117
				VDealPhysCurve.add("RLNG_PHYS_INDIA");
				VDealTCQ.add(TCQ);
				VRLNG_PHYS_IN_EXPO.add(TCQ);
				VLNG_ICE_JKM_EXPO.add(nf.format((-1)*Double.parseDouble(TCQ)*Double.parseDouble(Slope)));
			}
			else if(PriceTypeCargo.equals("F"))
			{
				VPriceType.add("FIXED");
				VDealPriceCurve.add("RLNG_PHYS_INDIA");
				VDealPhysCurve.add("RLNG_PHYS_INDIA");
				VDealTCQ.add(TCQ);
				VRLNG_PHYS_IN_EXPO.add(TCQ);
				VLNG_ICE_JKM_EXPO.add(0);
			}
			else
			{
			//SB20201124	VPriceType.add("FIXED");
				VPriceType.add(PriceTypeCargo);
				VDealPriceCurve.add("RLNG_PHYS_INDIA");
				VDealPhysCurve.add("RLNG_PHYS_INDIA");
				VCargoRefNo.add("0");
				VDealTCQ.add(0);
				VRLNG_PHYS_IN_EXPO.add(0);
				VLNG_ICE_JKM_EXPO.add(0);
			}
			//////////////////////////////////////////////////////////////////////////////////////////
			int no_of_days = -1; int ContEndNoOfDays = -1; int ContPriorGasNoOfDays = -1;
			queryString = "SELECT TO_DATE('"+GenDt+"','DD/MM/YYYY')-TO_DATE('"+from_date+"','DD/MM/YYYY')+1 FROM DUAL" ;
		//	System.out.println(" DUAL: "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next()) {
				no_of_days = rset.getInt(1);
			}
			queryString = "SELECT TO_DATE('"+ContEndDt+"','DD/MM/YYYY')-TO_DATE('"+GenDt+"','DD/MM/YYYY') FROM DUAL" ;
		//	System.out.println(" DUAL: "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next()) {
				ContEndNoOfDays = rset.getInt(1);
			}
			queryString = "SELECT TO_DATE('"+GasDt+"','DD/MM/YYYY')-TO_DATE('"+GenDt+"','DD/MM/YYYY') FROM DUAL" ;
			//	System.out.println(" DUAL: "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next()) {
					ContPriorGasNoOfDays = rset.getInt(1);
				}
				
	/*SB20200901		for(int i=no_of_days;i>=0;i--) {
				queryString = "SELECT TO_CHAR(TO_DATE('"+GenDt+"','DD/MM/YYYY')-"+i+",'DD/MM/YYYY') FROM DUAL";
//				System.out.println("Fetching all dates..."+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next()) {
					VAll_Dt.add(rset.getString(1));
				} 
			}*/
			if(ContEndNoOfDays<0)
			{
				VRU_Physical_Leg.add("R");
				VRLNG_PHYS_INDIA_EXPOSURE_U.add("0");
			}
			else
			{
				VRU_Physical_Leg.add("U");
				VRLNG_PHYS_INDIA_EXPOSURE_U.add(TCQ);
			}
			
			double LNG_ICE_JKM_EXPO=(-1)*Double.parseDouble(TCQ)*Double.parseDouble(Slope);
			
			if(PriceType.equals("M") && ContEndNoOfDays<0)
			{
				VRU_Financial_Leg.add("R");
				VLNG_ICE_JKM_EXPOSURE_U.add("0");
				U_Phy_Leg=(Double.parseDouble(TCQ)*Double.parseDouble(PhyCurFrdPrice));
				VU_Phy_Leg.add(nf.format(Double.parseDouble(TCQ)*Double.parseDouble(PhyCurFrdPrice)));
				U_Fin_Leg=(LNG_ICE_JKM_EXPO*Double.parseDouble(FinCurFrdPrice));
				VU_Fin_Leg.add(nf.format(LNG_ICE_JKM_EXPO*Double.parseDouble(FinCurFrdPrice)));
				R_Fin_Leg=(Double.parseDouble(AllocQty)*Double.parseDouble(DealPrice));
				VR_Fin_Leg.add(nf.format(Double.parseDouble(AllocQty)*Double.parseDouble(DealPrice)));
			}
			else
			{
				VRU_Financial_Leg.add("U");
				if(Double.parseDouble(PriorMthCurvePrice)==0)
					VLNG_ICE_JKM_EXPOSURE_U.add(nf.format((-1)*Double.parseDouble(TCQ)*Double.parseDouble(Slope)));
				else
					VLNG_ICE_JKM_EXPOSURE_U.add("");
				U_Phy_Leg=(Double.parseDouble(TCQ)*Double.parseDouble(PhyCurFrdPrice));
				VU_Phy_Leg.add(nf.format(Double.parseDouble(TCQ)*Double.parseDouble(PhyCurFrdPrice)));
				U_Fin_Leg=(LNG_ICE_JKM_EXPO*Double.parseDouble(FinCurFrdPrice));
				VU_Fin_Leg.add(nf.format(LNG_ICE_JKM_EXPO*Double.parseDouble(FinCurFrdPrice)));
				R_Fin_Leg=(Double.parseDouble(AllocQty)*Double.parseDouble(DealPrice));
				VR_Fin_Leg.add(nf.format(Double.parseDouble(AllocQty)*Double.parseDouble(DealPrice)));
			}
			VTotal.add(nf.format(U_Phy_Leg+U_Fin_Leg+R_Fin_Leg));
			////////////////////////////////////////////////////////////
		}
		catch(Exception e)
		{
			//System.out.println("Exception in Databean_Contract_MasterReport--->fetchcustomer()-->"+e);
			  e.printStackTrace();	
		}
	}
	
	public void MRCR_ContDtlTotal()
	{
		try
		{
			String PriceType=""; //FinCurFrdPrice=".5"; //PhyCurFrdPrice="2.5";
		
			double SumTotal=0; 
			double SumTotalRealised=0; double SumTotalFinUnRealised=0; double SumTotalPhyUnRealised=0; //SB20201228
			double TotalDCQTCQ=0;
			double TotalAllocQty=0;
			double TotalRealizedQty=0;
			double TotalUnRealizedQty=0;
			double TotalExposureQty=0;
			double TotalExposureJKMQty=0;
			
			for(int i=0; i<VTotal.size(); i++)
			{
				TotalDCQTCQ=TotalDCQTCQ+Double.parseDouble(""+VDealDCQ.elementAt(i));
				if(!VDealAllocQty.elementAt(i).equals(""))
					TotalAllocQty=TotalAllocQty+Double.parseDouble(""+VDealAllocQty.elementAt(i));
				if(!VRLNG_PHYS_INDIA_EXPOSURE_U.elementAt(i).equals(""))
					TotalRealizedQty=TotalRealizedQty+Double.parseDouble(""+VRLNG_PHYS_INDIA_EXPOSURE_U.elementAt(i));
				if(!VLNG_ICE_JKM_EXPOSURE_U.elementAt(i).equals(""))
					TotalUnRealizedQty=TotalUnRealizedQty+Double.parseDouble(""+VLNG_ICE_JKM_EXPOSURE_U.elementAt(i));
			//SB20201026	SumTotal=SumTotal+Double.parseDouble(""+VTotal.elementAt(i));
				SumTotal=SumTotal+Double.parseDouble(""+VTotalMtmRealised.elementAt(i));
				SumTotalRealised=SumTotalRealised+Double.parseDouble(""+VRealised.elementAt(i)); //SB20201228
				SumTotalFinUnRealised=SumTotalFinUnRealised+Double.parseDouble(""+VU_Fin_Leg.elementAt(i)); //SB20201228
				SumTotalPhyUnRealised=SumTotalPhyUnRealised+Double.parseDouble(""+VU_Phy_Leg.elementAt(i)); //SB20201228
				
				if(!VLNG_ICE_JKM_EXPO.elementAt(i).equals(""))
					TotalExposureJKMQty=TotalExposureJKMQty+Double.parseDouble(""+VLNG_ICE_JKM_EXPO.elementAt(i));
			}
			FinalTotal=nf.format(SumTotal);
			FinalTotalRealised=nf.format(SumTotalRealised); //Sb20201229
			FinalTotalFinUnRealised=nf.format(SumTotalFinUnRealised); //Sb20201229
			FinalTotalPhyUnRealised=nf.format(SumTotalPhyUnRealised); //Sb20201229
			Total_DCQ_TCQ = nf.format(TotalDCQTCQ);
			Total_AllocQty = nf.format(TotalAllocQty);
			Total_RealizedQty = nf.format(TotalRealizedQty);
			Total_UnRealizedQty = nf.format(TotalUnRealizedQty);
			Total_ExposureJKMQty = nf.format(TotalExposureJKMQty);
		}
		catch(Exception e)
		{
			//System.out.println("Exception in Databean_Contract_MasterReport--->fetchcustomer()-->"+e);
			  e.printStackTrace();	
		}
	}
	public void MRCR_ContDtlSubTotal()
	{
		try
		{
			double SumTotalRLNG_PHYS=0; double SumTotalLNG_JKM=0; double SumTotalRLNG_PHYS_INDIA_EXPOSURE_U=0; 
			double SumTotalLNG_ICE_JKM_EXPOSURE_U=0; double SumTotalU_Phy_Leg=0; double SumTotalU_Fin_Leg=0; double SumTotalTotal=0;
			String PrevSeqNo=""+VPriceTypeDealSeQNo.elementAt(0);
			
			for(int i=0; i<VPriceTypeDealSeQNo.size(); i++)
			{				
				if(!VPriceTypeDealSeQNo.elementAt(i).equals(PrevSeqNo))
				{
					VRLNG_PHYS_IN_EXPO_SubTotal.add(nf.format(SumTotalRLNG_PHYS));
					SumTotalRLNG_PHYS=0;
					SumTotalRLNG_PHYS=SumTotalRLNG_PHYS+Double.parseDouble(""+VRLNG_PHYS_IN_EXPO.elementAt(i));
					
					VLNG_ICE_JKM_EXPO_SubTotal.add(nf.format(SumTotalLNG_JKM));
					SumTotalLNG_JKM=0;
					if(!VLNG_ICE_JKM_EXPO.elementAt(i).equals(""))
						SumTotalLNG_JKM=SumTotalLNG_JKM+Double.parseDouble(""+VLNG_ICE_JKM_EXPO.elementAt(i));
					
					VRLNG_PHYS_INDIA_EXPOSURE_U_SubTotal.add(nf.format(SumTotalRLNG_PHYS_INDIA_EXPOSURE_U));
					SumTotalRLNG_PHYS_INDIA_EXPOSURE_U=0;
					SumTotalRLNG_PHYS_INDIA_EXPOSURE_U=SumTotalRLNG_PHYS_INDIA_EXPOSURE_U+Double.parseDouble(""+VRLNG_PHYS_INDIA_EXPOSURE_U.elementAt(i));
					
					VLNG_ICE_JKM_EXPOSURE_U_SubTotal.add(nf.format(SumTotalLNG_ICE_JKM_EXPOSURE_U));
					SumTotalLNG_ICE_JKM_EXPOSURE_U=0;
					if(!VLNG_ICE_JKM_EXPOSURE_U.elementAt(i).equals(""))
						SumTotalLNG_ICE_JKM_EXPOSURE_U=SumTotalLNG_ICE_JKM_EXPOSURE_U+Double.parseDouble(""+VLNG_ICE_JKM_EXPOSURE_U.elementAt(i));
					
					VU_Phy_Leg_SubTotal.add(nf.format(SumTotalU_Phy_Leg));
					SumTotalU_Phy_Leg=0;
					SumTotalU_Phy_Leg=SumTotalU_Phy_Leg+Double.parseDouble(""+VU_Phy_Leg.elementAt(i));
					
					VU_Fin_Leg_SubTotal.add(nf.format(SumTotalU_Fin_Leg));
					SumTotalU_Fin_Leg=0;
					SumTotalU_Fin_Leg=SumTotalU_Fin_Leg+Double.parseDouble(""+VU_Fin_Leg.elementAt(i));
					
					VTotal_SubTotal.add(nf.format(SumTotalTotal));
					SumTotalTotal=0;
					SumTotalTotal=SumTotalTotal+Double.parseDouble(""+VTotal.elementAt(i));
				}
				else
				{
					if(!VRLNG_PHYS_IN_EXPO.elementAt(i).equals(""))
						SumTotalRLNG_PHYS=SumTotalRLNG_PHYS+Double.parseDouble(""+VRLNG_PHYS_IN_EXPO.elementAt(i));
					if(i>0) VRLNG_PHYS_IN_EXPO_SubTotal.add("");
					if(!VLNG_ICE_JKM_EXPO.elementAt(i).equals(""))
						SumTotalLNG_JKM=SumTotalLNG_JKM+Double.parseDouble(""+VLNG_ICE_JKM_EXPO.elementAt(i));
					if(i>0) 
						VLNG_ICE_JKM_EXPO_SubTotal.add("");
					if(!VRLNG_PHYS_INDIA_EXPOSURE_U.elementAt(i).equals(""))
						SumTotalRLNG_PHYS_INDIA_EXPOSURE_U=SumTotalRLNG_PHYS_INDIA_EXPOSURE_U+Double.parseDouble(""+VRLNG_PHYS_INDIA_EXPOSURE_U.elementAt(i));
					if(i>0) VRLNG_PHYS_INDIA_EXPOSURE_U_SubTotal.add("");
					if(!VLNG_ICE_JKM_EXPOSURE_U.elementAt(i).equals(""))
						SumTotalLNG_ICE_JKM_EXPOSURE_U=SumTotalLNG_ICE_JKM_EXPOSURE_U+Double.parseDouble(""+VLNG_ICE_JKM_EXPOSURE_U.elementAt(i));
					if(i>0) VLNG_ICE_JKM_EXPOSURE_U_SubTotal.add("");
					
					SumTotalU_Phy_Leg=SumTotalU_Phy_Leg+Double.parseDouble(""+VU_Phy_Leg.elementAt(i));
					if(i>0) VU_Phy_Leg_SubTotal.add("");
					
					SumTotalU_Fin_Leg=SumTotalU_Fin_Leg+Double.parseDouble(""+VU_Fin_Leg.elementAt(i));
					if(i>0) VU_Fin_Leg_SubTotal.add("");
					
					SumTotalTotal=SumTotalTotal+Double.parseDouble(""+VTotal.elementAt(i));
					if(i>0) VTotal_SubTotal.add("");
					
				}
				PrevSeqNo=""+VPriceTypeDealSeQNo.elementAt(i);
			}
			VRLNG_PHYS_IN_EXPO_SubTotal.add(nf.format(SumTotalRLNG_PHYS));
			VLNG_ICE_JKM_EXPO_SubTotal.add(nf.format(SumTotalLNG_JKM));
			VRLNG_PHYS_INDIA_EXPOSURE_U_SubTotal.add(nf.format(SumTotalRLNG_PHYS_INDIA_EXPOSURE_U));
			VLNG_ICE_JKM_EXPOSURE_U_SubTotal.add(nf.format(SumTotalLNG_ICE_JKM_EXPOSURE_U));
			VU_Phy_Leg_SubTotal.add(nf.format(SumTotalU_Phy_Leg));
			VU_Fin_Leg_SubTotal.add(nf.format(SumTotalU_Fin_Leg));
			VTotal_SubTotal.add(nf.format(SumTotalTotal));
			
			FinalTotal=nf.format(SumTotalRLNG_PHYS);
		}
		catch(Exception e)
		{
			//System.out.println("Exception in Databean_Contract_MasterReport--->fetchcustomer()-->"+e);
			  e.printStackTrace();	
		}
	}
	public void MRCR_MTM()
	{
		try
		{
			String PriceType=""; //FinCurFrdPrice=".5"; //PhyCurFrdPrice="2.5";
			
			for(int i=0; i<VRLNG_PHYS_INDIA_EXPOSURE_U.size(); i++)
			{			
				double mtm=0; double mtm2=0; double PriceConst=0; 
				double realised=0; double realised2=0; double PriceConst2=0;  double Total=0;
				mtm=Double.parseDouble(""+VRLNG_PHYS_INDIA_EXPOSURE_U.elementAt(i))*Double.parseDouble(""+VSettle_RLNG_PHYS.elementAt(i));//
			
				if(!VSettle_ICE_JKM.elementAt(i).equals("") && !VConst_ICE_JKM.elementAt(i).equals("")) //SB20201119
					//SB20201126: As discussed by Shiladitya dt26thNov20 PriceConst=Double.parseDouble(""+VSettle_ICE_JKM.elementAt(i))+Double.parseDouble(""+VConst_ICE_JKM.elementAt(i));
					PriceConst=Double.parseDouble(""+VSettle_ICE_JKM.elementAt(i))+(Double.parseDouble(""+VConst_ICE_JKM.elementAt(i))/Double.parseDouble(""+VSlope.elementAt(i)));  //SB20201126: As discussed by Shiladitya dt26thNov20
			//	System.out.println(PriceConst+" PriceConst "+VSettle_ICE_JKM.elementAt(i));
				if(!VLNG_ICE_JKM_EXPOSURE_U.elementAt(i).equals(""))
					mtm2=Double.parseDouble(""+VLNG_ICE_JKM_EXPOSURE_U.elementAt(i))*PriceConst;
			//	System.out.println(mtm2+" VLNG_ICE_JKM_EXPOSURE_U "+VLNG_ICE_JKM_EXPOSURE_U.elementAt(i));
					VMTM.add(nf.format(mtm+mtm2)); //Sb20201023
					VU_Phy_Leg.add(nf.format(mtm));
					VU_Fin_Leg.add(nf.format(mtm2));
					
				if(!VSettle_ICE_JKM_AVG.elementAt(i).equals(""))
					//SB20201126: As discussed by Shiladitya dt26thNov20	PriceConst2=Double.parseDouble(""+VSettle_ICE_JKM_AVG.elementAt(i))+Double.parseDouble(""+VConst_ICE_JKM.elementAt(i));
					PriceConst2=Double.parseDouble(""+VSettle_ICE_JKM_AVG.elementAt(i))+(Double.parseDouble(""+VConst_ICE_JKM.elementAt(i))/Double.parseDouble(""+VSlope.elementAt(i))); //SB20201126: As discussed by Shiladitya dt26thNov20
				//System.out.println(INDEX_TYPE+":INDEX_TYPE: "+rset2.getString(7));
				
				/*SB20210505//////ORIGINAL LOGIC/////////^SB20210505///////////////////
				if(!VPNL_ICE_JKM_REALISED.elementAt(i).equals("") && VRU_Physical_Leg.elementAt(i).equals("U"))
				{
					realised=Double.parseDouble(""+VPNL_ICE_JKM_REALISED.elementAt(i));
					realised2=realised*PriceConst2;
				}
				else if(!VPNL_ICE_JKM_REALISED.elementAt(i).equals("") && VRU_Physical_Leg.elementAt(i).equals("R"))
				{
					realised=(-1)*Double.parseDouble(""+VDealAllocQty.elementAt(i)); //SB20201102: As discussed on 2nd Nov 2020 "ActualQty" to be reversed for Realised Financial Leg(Last Column)
					if(!VEff_Price.elementAt(i).equals(""))
						realised2=realised*Double.parseDouble(""+VEff_Price.elementAt(i));
				}
				*/////////////NEW Logic to accomodate PPAC PRice/////////////////SB20210505///////////////////////////////////////////
				////////////////SB20210505///////////////////
				String EffPrice=""+VEff_Price.elementAt(i);
				double SettleAvgPriceSlopCost=0;
//SB20210514: was wrong due to BODMAS				SettleAvgPriceSlopCost=Double.parseDouble(""+VSettle_ICE_JKM.elementAt(i))*(Double.parseDouble(""+VSlope.elementAt(i))+Double.parseDouble(""+VConst_ICE_JKM.elementAt(i)));  //SB20201126: As discussed by Shiladitya dt26thNov20
				//System.out.println(SettleAvgPriceSlopCost+" SettleAvgPriceSlopCost "+VSettle_ICE_JKM.elementAt(i)+" : "+VSlope.elementAt(i)+" : "+VConst_ICE_JKM.elementAt(i));
				SettleAvgPriceSlopCost=(Double.parseDouble(""+VSettle_ICE_JKM.elementAt(i))*Double.parseDouble(""+VSlope.elementAt(i)))+Double.parseDouble(""+VConst_ICE_JKM.elementAt(i));  //SB20201126: As discussed by Shiladitya dt26thNov20
				//System.out.println(SettleAvgPriceSlopCost+" 2. SettleAvgPriceSlopCost "+VSettle_ICE_JKM.elementAt(i)+" : "+VSlope.elementAt(i)+" : "+VConst_ICE_JKM.elementAt(i));
				if(VPriceTypeDeal.elementAt(i).equals("Float") && VRU_Physical_Leg.elementAt(i).equals("U"))
				{
					if(!VPNL_ICE_JKM_REALISED.elementAt(i).equals(""))
					 realised=Double.parseDouble(""+VPNL_ICE_JKM_REALISED.elementAt(i));
					////SB20210513//////////Added for PPAC Logic: It may change in future////////////////////////
						if(PPACPriceFlag.equals("1"))
						{
								if(Double.parseDouble(""+SettleAvgPriceSlopCost)<Double.parseDouble(""+VPriceTypeDealRate.elementAt(i)))
								{
									realised=Double.parseDouble(""+VPNL_ICE_JKM_REALISED.elementAt(i));
								//	EffPrice=""+VEff_Price.elementAt(i);
								}
								else
								{
									realised=(-1)*Double.parseDouble(""+VRLNG_PHYS_INDIA_EXPOSURE_U.elementAt(i));
							//		EffPrice=""+VPriceTypeDealRate.elementAt(i); //SB20210504
									PriceConst2=Double.parseDouble(""+VPriceTypeDealRate.elementAt(i));   //SB20210504
								}								
						}
					////^^^^^SB20210513//////////Added for PPAC Logic: It may change in future////////////////////////
					realised2=realised*PriceConst2;
				}
				else if(VPriceTypeDeal.elementAt(i).equals("Float") && VRU_Physical_Leg.elementAt(i).equals("R"))
				{
					realised=(-1)*Double.parseDouble(""+VDealAllocQty.elementAt(i)); //SB20201102: As discussed on 2nd Nov 2020 "ActualQty" to be reversed for Realised Financial Leg(Last Column)
				////SB20210513//////////Added for PPAC Logic: It may change in future////////////////////////
					if(!VEff_Price.elementAt(i).equals("") && PPACPriceFlag.equals("1"))
					{
						if(Double.parseDouble(""+VEff_Price.elementAt(i))<Double.parseDouble(""+VPriceTypeDealRate.elementAt(i)))
						{
							EffPrice=""+VEff_Price.elementAt(i);
						}
						else
						{
							EffPrice=""+VPriceTypeDealRate.elementAt(i); //SB20210504
						}
					}
				////^^^^^SB20210513//////////Added for PPAC Logic: It may change in future////////////////////////
					if(!EffPrice.equals(""))
						realised2=realised*Double.parseDouble(""+EffPrice);
				}
				//////ORIGINAL LOGIC/////////^SB20210505///////////////////
				//////////////SB20201028////////////////
				if(VPriceTypeDeal.elementAt(i).equals("Fixed") && VRU_Physical_Leg.elementAt(i).equals("U"))
				{
					realised=(-1)*Double.parseDouble(""+VRLNG_PHYS_INDIA_EXPOSURE_U.elementAt(i));  //SB20201102: As discussed on 2nd Nov 2020 "ActualQty" to be reversed for Realised Financial Leg(Last Column)
					realised2=realised*Double.parseDouble(""+RATE.elementAt(i)); 
				}
				else if(VPriceTypeDeal.elementAt(i).equals("Fixed") && VRU_Physical_Leg.elementAt(i).equals("R"))
				{
					realised=(-1)*Double.parseDouble(""+VDealAllocQty.elementAt(i));  //SB20201102: As discussed on 2nd Nov 2020 "ActualQty" to be reversed for Realised Financial Leg(Last Column)
					realised2=realised*Double.parseDouble(""+RATE.elementAt(i)); 
				}
				//////////////^^^^SB20201028////////////////		
			//	System.out.println(realised+" VPNL_ICE_JKM_REALISED "+VPNL_ICE_JKM_REALISED.elementAt(i));
				
			//	System.out.println(PriceConst2+" VSettle_ICE_JKM_AVG "+VSettle_ICE_JKM_AVG.elementAt(i));
		//		System.out.println(realised2+" realised2 "+Total);
				
					VRealised.add(nf.format(realised2)); //Sb20201023
					
					Total=(mtm+mtm2)+(realised2);	
						VTotalMtmRealised.add(nf.format(Total)); //Sb20201023
			}
			
		}
		catch(Exception e)
		{
			//System.out.println("Exception in Databean_Contract_MasterReport--->fetchcustomer()-->"+e);
			  e.printStackTrace();	
		}
	}
	////////////////////NEW Fn for Deal Exposure Dtl/////////
	public void fetchDealExposureDtl()
	{
		try
		{
			String tmp_from_dt = " ";
			String temp_dt="";
			String tmp_to_dt = " ";			
			double tot_comit=0.0;
			double sup_tot_comit=0.0;
			double bal_tot_comit=0.0;
			//Introduce By Milan 20110916 FOR MMSCM
			double tot_comit_mmscm=0.0;
			double sup_tot_comit_mmscm=0.0;
			double bal_tot_comit_mmscm=0.0;
			Vector FRM_DT=new Vector();
			Vector TO_DT=new Vector();	
			String Buy_Sell="Sell";
			Vector RE_GAS_REV_NO=new Vector();
			Vector LTCORA_REV_NO=new Vector();//ADDED FOR LTCORA AND CN
			Vector LTCORA_MAPPING_ID=new Vector();//ADDED FOR LTCORA AND CN
			Vector CN_REV_NO=new Vector();//ADDED FOR LTCORA AND CN
			Vector CN_MAPPING_ID=new Vector();//ADDED FOR LTCORA AND CN
			/////////////////////////////////Count No of Mth/Days of Contract/////////////////////////////////////
			String ContMthWise_tmp_from_dt = " ";
			String ContMthWise_temp_dt="";
			Vector VContMthWise_FRM_DT=new Vector();
			Vector VContMthWise_TO_DT=new Vector();	
			Vector VContMthWise_gas_date=new Vector();	
			String Cont_Rate="";
			String CargoRefCd=""; //SB20201127
			////////////////////SB20210209:STORAGE//////////////////////////////////
			String StorageDate="";
			queryString = "SELECT TO_CHAR(TO_DATE('"+to_date+"','DD/MM/YYYY')+1,'DD/MM/YYYY') FROM DUAL";
			//	System.out.println("Get DAY Name: "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					StorageDate = rset.getString(1)==null?"":rset.getString(1); //SB20201128;
				}
			////////////////////^^^SB20210209:STORAGE/////////////////////////////////////////////	
			System.out.println("Cont_Type: "+Cont_Type);
			if(Cont_Type.equals("S"))  //For SN
			{
			queryString ="select TO_CHAR(A.START_DT,'DD/MM/YYYY'),TO_CHAR(A.END_DT,'DD/MM/YYYY'),A.SN_NO,A.SN_REV_NO,A.CUSTOMER_CD,A.FGSA_NO,A.FGSA_REV_NO,A.TCQ, " +
			 //SB20210528		"A.SN_REF_NO,to_char(A.SIGNING_DT,'dd/mm/yyyy'),A.RATE,FCC_BY,TO_CHAR(FCC_DATE,'DD/mm/yyyy'),EMP_CD,TO_CHAR(ENT_DT,'dd/mm/yyyy') from FMS7_SN_MST A where "+ 
			 		"A.SN_REF_NO,to_char(A.SIGNING_DT,'dd/mm/yyyy'),A.RATE,FCC_BY,TO_CHAR(FCC_DATE,'DD/mm/yyyy'),EMP_CD,TO_CHAR(DEAL_ENT_DT,'dd/mm/yyyy') from FMS7_SN_MST A where "+  //SB20210528
			 		"A.CUSTOMER_CD='"+DealCustCd+"' AND A.FGSA_NO='"+FgsaNo+"' AND A.FGSA_REV_NO='"+FgsaRevNo+"' AND A.SN_NO='"+SnNo+"' AND A.SN_REV_NO='"+SnRevNo+"' AND "+
			 		"A.END_DT >= TO_DATE('01/01/2021','DD/MM/YYYY') AND " + ///SB20210522 : ad discussed on 21stMay21 by Shiladitya to Include all Deals irrespective of Expairy on Report Date
			///SB20210522		"A.END_DT >= to_date('"+to_date+"','dd/mm/yyyy') AND " +
			 		"A.SN_REV_NO=(SELECT MAX(B.SN_REV_NO) FROM FMS7_SN_MST B where A.SN_NO=B.SN_NO AND A.FGSA_NO=B.FGSA_NO AND " +
			 		"A.CUSTOMER_CD=B.CUSTOMER_CD AND A.FGSA_REV_NO=B.FGSA_REV_NO) order by A.START_DT";
			//	System.out.println("DEAL-DTL: FMS7_SN_MST: "+queryString);
					rset = stmt.executeQuery(queryString);
					System.out.println("MRver2.0 SN : "+to_date+" "+from_date);
					while(rset.next())
					{									
						ContMthWise_tmp_from_dt = rset.getString(1)==null?"01/01/2000":rset.getString(1);
						ContMthWise_temp_dt = rset.getString(2)==null?"01/01/2020":rset.getString(2);
						VContMthWise_FRM_DT.add(rset.getString(1)==null?"01/01/2020":rset.getString(1));
						VContMthWise_TO_DT.add(rset.getString(2)==null?"01/01/2020":rset.getString(2));
												
						queryString ="select CUSTOMER_ABBR,CUSTOMER_NAME,CUSTOMER_CD from FMS7_CUSTOMER_MST where CUSTOMER_CD="+rset.getString(5)+" "
								+ "and eff_dt=(select max(eff_dt) from fms7_customer_mst where customer_cd='"+rset.getString(5)+"' and eff_dt<=to_date('"+rset.getString(1)+"','dd/mm/yyyy'))";
						rset2 = stmt2.executeQuery(queryString);
						////System.out.println(queryString);
						if(rset2.next())
						{
							CUSTOMER_NM.add(rset2.getString(2)==null?"":rset2.getString(2));
							CUSTOMER_ABBR.add(rset2.getString(1)==null?"":rset2.getString(1));	
							Deal_Cust_AbbrNm=rset2.getString(1)+"-"+rset2.getString(2); //SB20210129
						}
						else
						{
							CUSTOMER_NM.add("");
							CUSTOMER_ABBR.add("");
							Deal_Cust_AbbrNm=""; //SB20210129
						}		
						if(rset2.getString(1).equals("SEIPL"))
						{
							Buy_Sell="Buy";
						}
						Deal_Dt=rset.getString(10)==null?"":rset.getString(10);
						Cont_Rate=rset.getString(11)==null?"0":rset.getString(11);
						String TEMP_apr_by=rset.getString(12)==null?"0":rset.getString(12);
					  	sn_apr_dt.add(rset.getString(13)==null?"-":rset.getString(13));
					  	String temp_ent_by=rset.getString(14)==null?"0":rset.getString(14);
					  	sn_ent_dt.add(rset.getString(15)==null?"-":rset.getString(15));
					  	Deal_Start_Dt=rset.getString(1)==null?"":rset.getString(1); //SB20210129
					  	Deal_End_Dt=rset.getString(2)==null?"":rset.getString(2); //SB20210129
					  	Deal_Ref_No=rset.getString(9)==null?"":rset.getString(9); //SB20210129
					  	Deal_Entered_By=rset.getString(14)==null?"":rset.getString(14); //SB20210129
					  	Deal_Entered_Dt=rset.getString(15)==null?"":rset.getString(15); //SB20210129
					  	
					  	
					  	String queryString_nm ="SELECT EMP_NM FROM HR_EMP_MST WHERE EMP_CD='"+TEMP_apr_by+"'";
					  	rset1=stmt1.executeQuery(queryString_nm);
					  	if(rset1.next()){
					  		sn_apr_by.add(rset1.getString(1)==null?"-":rset1.getString(1));
					  	}else{
					  		sn_apr_by.add("-");
					  	}
					  	
					  	queryString_nm ="SELECT EMP_NM FROM HR_EMP_MST WHERE EMP_CD='"+temp_ent_by+"'";
					  	rset1=stmt1.executeQuery(queryString_nm);
					  	if(rset1.next()){
					  		sn_ent_by.add(rset1.getString(1)==null?"-":rset1.getString(1));
					  	}else{
					  		sn_ent_by.add("-");
					  	}
					}
				
			}
			else if(Cont_Type.equals("L")) ///For LoA
			{
			/////////////////////////////LOA LIst of Contracts/////////////////////////////////////////////////////////////////
			queryString =   "select TO_CHAR(A.START_DT,'DD/MM/YYYY'),TO_CHAR(A.END_DT,'DD/MM/YYYY'),A.LOA_NO,A.LOA_REV_NO,A.CUSTOMER_CD,A.TENDER_NO,A.TCQ, " +
			//SB20210528			"A.LOA_REF_NO,to_char(A.SIGNING_DT,'dd/mm/yyyy'),A.RATE,FCC_BY,TO_CHAR(FCC_DATE,'DD/mm/yyyy'),EMP_CD,TO_CHAR(ENT_DT,'dd/mm/yyyy'), DCQ, ADV_AMT, TRANSPORTATION_CHARGE  from FMS7_LOA_MST A where "+ 
			"A.LOA_REF_NO,to_char(A.SIGNING_DT,'dd/mm/yyyy'),A.RATE,FCC_BY,TO_CHAR(FCC_DATE,'DD/mm/yyyy'),EMP_CD,TO_CHAR(DEAL_ENT_DT,'dd/mm/yyyy'), DCQ, ADV_AMT, TRANSPORTATION_CHARGE  from FMS7_LOA_MST A where "+ //SB20210528
					"A.CUSTOMER_CD='"+DealCustCd+"' AND A.TENDER_NO='"+FgsaNo+"' AND A.LOA_NO='"+SnNo+"' AND A.LOA_REV_NO='"+SnRevNo+"' AND "+
					//SB20201013		"(A.START_DT <= to_date('"+to_date+"','dd/mm/yyyy')"+"AND A.END_DT >= to_date('"+from_date+"','dd/mm/yyyy')) AND " +
		///SB20210522	"(A.START_DT <= to_date('"+to_date+"','dd/mm/yyyy')"+"AND A.END_DT >= to_date('"+to_date+"','dd/mm/yyyy')) AND " + //SB20201013
					" A.END_DT >= TO_DATE('01/01/2021','DD/MM/YYYY') AND " + ///SB20210522 : ad discussed on 21stMay21 by Shiladitya to Include all Deals irrespective of Expairy on Report Date
					"A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM FMS7_LOA_MST B where A.LOA_NO=B.LOA_NO AND A.TENDER_NO=B.TENDER_NO AND " +
					"A.CUSTOMER_CD=B.CUSTOMER_CD) order by A.START_DT";
				rset = stmt.executeQuery(queryString);
			//		System.out.println("MRver2.0 LOA: "+queryString);
				while(rset.next())
				{	
					ContMthWise_tmp_from_dt = rset.getString(1)==null?"01/01/2000":rset.getString(1);
					ContMthWise_temp_dt = rset.getString(2)==null?"01/01/2020":rset.getString(2);
					VContMthWise_FRM_DT.add(rset.getString(1)==null?"01/01/2020":rset.getString(1));
					VContMthWise_TO_DT.add(rset.getString(2)==null?"01/01/2020":rset.getString(2));
											
					queryString ="select CUSTOMER_ABBR,CUSTOMER_NAME,CUSTOMER_CD from FMS7_CUSTOMER_MST where CUSTOMER_CD="+rset.getString(5)+" "
							+ "and eff_dt=(select max(eff_dt) from fms7_customer_mst where customer_cd='"+rset.getString(5)+"' and eff_dt<=to_date('"+rset.getString(1)+"','dd/mm/yyyy'))";
					rset2 = stmt2.executeQuery(queryString);
					////System.out.println(queryString);
					if(rset2.next())
					{
						CUSTOMER_NM.add(rset2.getString(2)==null?"":rset2.getString(2));
						CUSTOMER_ABBR.add(rset2.getString(1)==null?"":rset2.getString(1));
						Deal_Cust_AbbrNm=rset2.getString(1)+"-"+rset2.getString(2); //SB20210129
					}
					else
					{
						CUSTOMER_NM.add("");
						CUSTOMER_ABBR.add("");
						Deal_Cust_AbbrNm=""; //SB20210129
					}		
					if(rset2.getString(1).equals("SEIPL"))
					{
						Buy_Sell="Buy";
					}
					Deal_Dt=rset.getString(9)==null?"":rset.getString(9);
					Cont_Rate=rset.getString(10)==null?"0":rset.getString(10);
					String TEMP_apr_by=rset.getString(11)==null?"0":rset.getString(11);
				  	sn_apr_dt.add(rset.getString(12)==null?"-":rset.getString(12));
				  	String temp_ent_by=rset.getString(13)==null?"0":rset.getString(13);
				  	sn_ent_dt.add(rset.getString(14)==null?"-":rset.getString(14));
				  	Deal_Start_Dt=rset.getString(1)==null?"":rset.getString(1); //SB20210129
				  	Deal_End_Dt=rset.getString(2)==null?"":rset.getString(2); //SB20210129
				  	Deal_Entered_Dt=rset.getString(14)==null?"":rset.getString(14); //SB20210129
				  	Deal_Ref_No=rset.getString(8)==null?"":rset.getString(8); //SB20210129
				  	Deal_Entered_By=rset.getString(13)==null?"":rset.getString(13); //SB20210129
				  	Deal_Entered_Dt=rset.getString(14)==null?"":rset.getString(14); //SB20210129
				  	
				  	String queryString_nm ="SELECT EMP_NM FROM HR_EMP_MST WHERE EMP_CD='"+TEMP_apr_by+"'";
				  	rset1=stmt1.executeQuery(queryString_nm);
				  	if(rset1.next()){
				  		sn_apr_by.add(rset1.getString(1)==null?"-":rset1.getString(1));
				  	}else{
				  		sn_apr_by.add("-");
				  	}
				  	
				  	queryString_nm ="SELECT EMP_NM FROM HR_EMP_MST WHERE EMP_CD='"+temp_ent_by+"'";
				  	rset1=stmt1.executeQuery(queryString_nm);
				  	if(rset1.next()){
				  		sn_ent_by.add(rset1.getString(1)==null?"-":rset1.getString(1));
				  	}else{
				  		sn_ent_by.add("-");
				  	}	  	
				}
			}	
			else if(Cont_Type.equals("B"))
			{
				
				queryString = "SELECT TO_CHAR(A.DELV_FROM_DT,'DD/MM/YYYY'), TO_CHAR(A.DELV_TO_DT,'DD/MM/YYYY'),A.MAN_CONF_CD,A.CARGO_SEQ_NO,E.TRD_CD, A.MAN_CD, A.MAN_CD, A.CONFIRM_VOL,"
						+ "A.CARGO_REF_CD,to_char(C.MAN_CONF_DT,'dd/mm/yyyy'), A.PRICE,A.EMP_CD,TO_CHAR(A.ENT_DT,'dd/mm/yyyy'), A.EMP_CD,TO_CHAR(A.ENT_DT,'dd/mm/yyyy'), E.PRICE_TYPE, A.PRICE_TO, A.REMARK  "+
						" FROM FMS7_MAN_CONFIRM_CARGO_DTL A, FMS7_MAN_CONFIRM_MST C, FMS7_MAN_REQ_MST E WHERE "+
						"E.TRD_CD='"+DealCustCd+"' AND A.MAN_CONF_CD='"+SnNo+"' AND A.MAN_CD='"+FgsaNo+"' AND A.CARGO_SEQ_NO='"+SnRevNo+"' AND "+
			///SB20210522 			" A.DELV_TO_DT >= TO_DATE('"+to_date+"','DD/MM/YYYY') "
						" A.DELV_TO_DT >= TO_DATE('01/01/2021','DD/MM/YYYY') AND " + ///SB20210522 : ad discussed on 21stMay21 by Shiladitya to Include all Deals irrespective of Expairy on Report Date
						" E.MAN_CD=A.MAN_CD AND "+ 
				///		" A.UNIT_CD=C.UNIT_CD AND A.CARGO_STATUS='T' AND CARGO_REF_CD='"+VCAR_REF_NO_temp.elementAt(i)+"' "+ 
						" A.MAN_CONF_CD=C.MAN_CONF_CD AND A.CARGO_STATUS='T' AND CARGO_REF_CD LIKE '%' "+ 
						" ORDER BY A.MAN_CONF_CD, A.CARGO_REF_CD";
			///	queryString = "SELECT * FROM FMS7_CARGO_QQ_DTL WHERE CARGO_REF_NO=20032 ";
				//(START_DT <= to_date('"+to_date+"','dd/mm/yyyy')"+"AND END_DT >= to_date('"+from_date+"','dd/mm/yyyy'))
		//		System.out.println("MRver2.0 CARGO: "+queryString);
				rset = stmt.executeQuery(queryString);
				////System.out.println("FOr SN : "+to_date+" "+from_date);
				while(rset.next())
				{				
				//SB20201126	ContMthWise_tmp_from_dt = rset.getString(1)==null?"01/01/2000":rset.getString(1);
					ContMthWise_tmp_from_dt = rset.getString(1)==null?"01/01/2000":rset.getString(1);
					//SB20210524	ContMthWise_tmp_from_dt = rset.getString(2)==null?"01/01/2000":rset.getString(2); //SB20201126
					ContMthWise_temp_dt = rset.getString(2)==null?"01/01/2020":rset.getString(2);
					VContMthWise_FRM_DT.add(rset.getString(1)==null?"01/01/2020":rset.getString(1));
					VContMthWise_TO_DT.add(rset.getString(2)==null?"01/01/2020":rset.getString(2));
											
					queryString ="select TRADER_ABBR,TRADER_NAME,TRADER_CD from FMS7_TRADER_MST B where TRADER_CD="+rset.getString(5)+" "
							+ "and eff_dt=(select max(eff_dt) from FMS7_TRADER_MST where TRADER_CD='"+rset.getString(5)+"' and eff_dt<=to_date('"+ContMthWise_tmp_from_dt+"','dd/mm/yyyy'))";	
					rset2 = stmt2.executeQuery(queryString);
					////System.out.println(queryString);
					if(rset2.next())
					{
						CUSTOMER_NM.add(rset2.getString(2)==null?"":rset2.getString(2));
						CUSTOMER_ABBR.add(rset2.getString(1)==null?"":rset2.getString(1));	
						Deal_Cust_AbbrNm=rset2.getString(1)+"-"+rset2.getString(2); //SB20210129
					}
					else
					{
						CUSTOMER_NM.add("");
						CUSTOMER_ABBR.add("");
						Deal_Cust_AbbrNm=""; //SB20210129
					}		
				//	if(rset2.getString(1).equals("SEIPL"))
					{
						Buy_Sell="Buy";
					}
					
					CargoRefCd=rset.getString(9)==null?"0":rset.getString(9); //SB20201127
					Cont_Rate=rset.getString(11)==null?"0":rset.getString(11);
					String TEMP_apr_by=rset.getString(12)==null?"0":rset.getString(12);
				  	sn_apr_dt.add(rset.getString(13)==null?"-":rset.getString(13));
				  	String temp_ent_by=rset.getString(14)==null?"0":rset.getString(14);
				  	sn_ent_dt.add(rset.getString(15)==null?"-":rset.getString(15));
				  	Deal_Start_Dt=rset.getString(1)==null?"":rset.getString(1); //SB20210129
				  	Deal_End_Dt=rset.getString(2)==null?"":rset.getString(2); //SB20210129
				  	Deal_Entered_Dt=rset.getString(15)==null?"":rset.getString(15); //SB20210129
				  	Deal_Ref_No=rset.getString(9)==null?"":rset.getString(9); //SB20210129
				  	Deal_Entered_By=rset.getString(14)==null?"":rset.getString(14); //SB20210129
				  	Deal_Entered_Dt=rset.getString(15)==null?"":rset.getString(15); //SB20210129
				  
				  	Deal_Dt=rset.getString(10)==null?"":rset.getString(10);  //SB20201126:
				  	VContType.add("B"); //SB20201108
				  	
				  	
				  	String queryString_nm ="SELECT EMP_NM FROM HR_EMP_MST WHERE EMP_CD='"+TEMP_apr_by+"'";
				  	rset1=stmt1.executeQuery(queryString_nm);
				  	if(rset1.next()){
				  		sn_apr_by.add(rset1.getString(1)==null?"-":rset1.getString(1));
				  	}else{
				  		sn_apr_by.add("-");
				  	}
				  	VCont_Start_Dt.add(rset.getString(1)==null?"":rset.getString(1));
				  	VCont_End_Dt.add(rset.getString(2)==null?"":rset.getString(2));
				  	
				  	queryString_nm ="SELECT EMP_NM FROM HR_EMP_MST WHERE EMP_CD='"+temp_ent_by+"'";
				  	rset1=stmt1.executeQuery(queryString_nm);
				  	if(rset1.next()){
				  		sn_ent_by.add(rset1.getString(1)==null?"-":rset1.getString(1));
				  	}else{
				  		sn_ent_by.add("-");
				  	}
				 // 	MAPPING_ID.add("C"+rset.getString(6)+"-"+rset.getString(7)+"-"+rset.getString(3)+"-"+rset.getString(4));
				 //// 	MAPPING_ID.add("B"+rset.getString(3)+"-0-"+rset.getString(6)+"-"+rset.getString(7));
				}	
			}
			/////////////////////////////////////////////////////////////////
			else if(Cont_Type.equals("O"))
			{		
					ContMthWise_tmp_from_dt = StorageDate;
					ContMthWise_temp_dt = StorageDate;						
					CUSTOMER_NM.add("STORAGE");
					CUSTOMER_ABBR.add("SEIPL");
					Deal_Cust_AbbrNm="SEIPL-STORAGE"; //SB20210129	
					Buy_Sell="Buy";
					Cont_Rate="0";
				  	VContType.add("O"); //SB20201108
				  	sn_apr_by.add("-");
				  
				  	VCont_Start_Dt.add(StorageDate);
				  	VCont_End_Dt.add(StorageDate);
				  	sn_ent_by.add("-");
					Deal_Dt=StorageDate;
				  	Deal_Start_Dt=StorageDate; //SB20210129
				  	Deal_End_Dt=StorageDate; //SB20210129
				  	Deal_Ref_No=""; //SB20210129
				  	Deal_Entered_By="0"; //SB20210129
				  	Deal_Entered_Dt=StorageDate;
			}
	//SB20210528		String Mapp_Id=DealCustCd+"-"+FgsaNo+"-"+FgsaRevNo+"-"+SnNo+"-"+SnRevNo;
			String Mapp_Id=DealCustCd+"-"+FgsaNo+"-"+FgsaRevNo+"-"+SnNo+"-%"; //SB20210528
			if(Cont_Type.equals("B")) //SB20201127
				Mapp_Id=DealCustCd+"-"+SnNo+"-"+SnRevNo+"-"+CargoRefCd+"-0"; //SB20201127
			double count=0;
	
			String query="select to_date('"+ContMthWise_temp_dt+"','dd/mm/yyyy')- to_date('"+ContMthWise_tmp_from_dt+"','dd/mm/yyyy')+1 from dual";
		//	System.out.println("DEAL-DTL: FMS7_SN_MST: "+query);
			rset=stmt.executeQuery(query);
			if(rset.next())
			{
				count=rset.getDouble(1);
			}
			//////SB20201119////////This is to restrict number of delivery date////////////////
			int CountMaxDays=9125;  ///SB20210522: No limit i.e. 9125/365=25years
			if(count>CountMaxDays)
			{
			queryString = "SELECT TO_CHAR(TO_DATE('"+ContMthWise_temp_dt+"','DD/MM/YYYY')-"+CountMaxDays+",'DD/MM/YYYY') FROM DUAL";
			//	System.out.println("Get DAY Name: "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					ContMthWise_tmp_from_dt = rset.getString(1);
				}
			
			query="select to_date('"+ContMthWise_temp_dt+"','dd/mm/yyyy')- to_date('"+ContMthWise_tmp_from_dt+"','dd/mm/yyyy') from dual";
			//	System.out.println("DEAL-DTL: FMS7_SN_MST: "+query);
				rset=stmt.executeQuery(query);
				if(rset.next())
				{
					count=rset.getDouble(1);
				}
			}
			/////////////////^^^^^^SB20201119//////////////////////////////////////////////////
			System.out.println(ContMthWise_tmp_from_dt+" :ContMthWise_tmp_From_Dt: "+count);
			for(int i=0;i<count;i++)
			{
				String query1="select to_char(to_date('"+ContMthWise_tmp_from_dt+"','dd/mm/yyyy')+ "+i+",'dd/mm/yyyy') from dual";
				rset=stmt.executeQuery(query1);
				if(rset.next())
				{
					VContMthWise_gas_date.add(rset.getString(1));
				}	
			}	
			System.out.println(Mapp_Id+" :Mapp_Id & COUNT of +VContMthWise_gas_date: "+count);
			//System.out.println(VContMthWise_gas_date.size()+" :No. Of Delv Days & Check Point: OK------------------------>>>>");
	//////////TIME CALCULATION/////////////////////////////////////////////
				String CurrDtTm2="";
				queryString = "SELECT SYSDATE FROM DUAL";
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						CurrDtTm2 = rset.getString(1);System.out.println(VSettle_ICE_JKM_AVG.size()+" :<<<<<<INITIAL STEPS COMPLETED >>>>>>>>>>>>>>>>>>>>>>.....: "+CurrDtTm2);
					}
				//////////********TIME CALCULATION/////////////////////////////////////////////
				////Price Pulling from Table////////////////////////////
					String StartDate=""+VContMthWise_gas_date.elementAt(0);
					String tempStartDate[]=StartDate.split("/");
					String GenMth=tempStartDate[1]; 
					String GenYr=tempStartDate[2]; 
					String GenMthYr=GenMth+"/"+GenYr;
					StartDate=GenMthYr;
					String PrevMonth=GenMth; String ContinueReducePerc="Y"; //SB20201011
					
					String INDEX_TYPE="LNG_ICE_JKM"; String PriceGen_Dt=""; String NextDate="";
					
					int ContEndNoOfDays=-1; int ContinueReducePercDay=0;
					for(int i=0; i<VContMthWise_gas_date.size();i++) {	
						NextDate=""+VContMthWise_gas_date.elementAt(i);
						String tempNextDate[]=NextDate.split("/");
						GenMth=tempNextDate[1]; GenYr=tempNextDate[2]; GenMthYr=GenMth+"/"+GenYr;
						//System.out.println(PrevMonth+" :****"+i+"****: "+GenMth);
						if(PrevMonth.equals(GenMth)) //SB20201011
						{
							ContinueReducePerc="Y";
							ContinueReducePercDay++;
						}
						else
						{
						//SB20201020	ContinueReducePerc="N";
						//SB20201020: Due to 1st day of month not getting dropoff	ContinueReducePercDay=0;
							ContinueReducePerc="Y";
							ContinueReducePercDay++;
						}
					///	System.out.println(ContinueReducePercDay+") :ContinueReducePerc: "+ContinueReducePerc);
						PriceGen_Dt=StartDate;
////////////////////SB20201123: 1) To remove ERROR while Accessing FIXED PRICE deal of longer duration//This is optional and increases performance////////////////////////
						String PriceType="F";
						queryString ="SELECT DISTINCT PRICE_TYPE, CURVE_NM FROM FMS9_MRCR_CONT_PRICE_DTL "
						//SB20210528		+ "WHERE MAPPING_ID='"+Mapp_Id+"' ";//AND FROM_DT<=TO_DATE('"+VContMthWise_FRM_DT.elementAt(i)+"','DD/MM/YYYY')  AND FLAG='Y' ";//AND CURVE_NM='"+INDEX_TYPE+"' ";
								+ "WHERE MAPPING_ID LIKE '"+Mapp_Id+"' AND FROM_DT<=TO_DATE('"+VContMthWise_gas_date.elementAt(i)+"','DD/MM/YYYY') AND TO_DT>=TO_DATE('"+VContMthWise_gas_date.elementAt(i)+"','DD/MM/YYYY')  AND FLAG='Y' ";//AND CURVE_NM='"+INDEX_TYPE+"' ";//SB20210528
				//		System.out.println("FMS9_CURVE_PRICE_DTL: "+queryString);
						rset2 = stmt.executeQuery(queryString);			
						if(rset2.next())
						{
							//VPriceTypeDeal.add(rset2.getString(1)==null?"":rset2.getString(1));  //CR-DATED_BRENT
							PriceType=rset2.getString(1)==null?"":rset2.getString(1); //System.out.println(PriceType+" :*IN TABLE**PriceType*****: "+PriceType);
						}
						else
							PriceType="F";//System.out.println(PriceType+" :***PriceType*****: "+PriceType);
		
			//SB20210918			if(PriceType.equals("M")) //SB20201123
///////////^^^^SB20201123: To remove ERROR while Accessing FIXED PRICE deal of longer duration/////////////////////////////
			//SB20210918				MRCR_PriceOut2(to_date, INDEX_TYPE, ""+VContMthWise_gas_date.elementAt(i), Mapp_Id, Cont_Rate, ContinueReducePerc, ContinueReducePercDay);
////////////////////SB20201123: 2) To remove ERROR while Accessing FIXED PRICE deal of longer duration//////////////////////////
			//SB20210918			else //SB20201123
						{
							
							VMthYrWisePriceICE_JKM.add("0");
							PriceIndex_SettleDate="";
							VPriceIndex_SettleDate.add("");
							PriceIndex_StartDate="";
							VPriceIndex_StartDate.add("");
							PriceIndex_EndDate="";
							VPriceIndex_EndDate.add("");
							EffPriceSlopeConst="";
							VEff_Price.add("");
							VEff_CumulativePerc.add("0"); //SB20201006
							VEff_Perc.add("0"); 
							VWeekDayHoliDayFlag.add("1");
							VSlope_BRENT.add("1.0");
							VSlope_ICE_JKM.add("1.0");
							VSlope_JKM.add("1.0");
							VSlope_LNG_PHYS.add("1.0");
							VSlope_RLNG_PHYS.add("1.0");
						//SB20201102	VSlope.add("1.0");
							VSlope.add("1"); //SB20201102
							VSlope_FLAG.add("N");
							Slope_ICE_JKM="1";
							Slope_FLAG="N";
							
							VConst_ICE_JKM.add("0"); 
							VConst_RLNG_PHYS.add("0");
							VConst_ICE_JKM_FLAG.add("N");
							Const_ICE_JKM="0"; 
							Const_RLNG_PHYS="0";
							Const_ICE_JKM_FLAG="N";
							VMthYrWise.add(GenMthYr); //SB20210115
							VPriceTypeDeal.add("Fixed");
					//		VPriceTypeDealRate.add(Cont_Rate);  //Contract Rate SN_MST
							VPriceTypeDealSeQNo.add("0");
							VLNG_ICE_JKM_EXPODropOff.add("");
						}
///////////^^^^SB20201123: To remove ERROR while Accessing FIXED PRICE deal of longer duration/////////////////////////////
						StartDate=""+GenMthYr;
						PrevMonth=GenMth;
					}
			//SB20201110		VMthYrWisePriceICE_JKM.add(""+PrvCurvePrice);
			//SB20201110		VPriceIndex_SettleDate.add(""+PriceIndex_SettleDate);
			//SB20201128		VPriceIndex_StartDate.add(""+PriceIndex_StartDate);
			//SB20201128		VPriceIndex_EndDate.add(""+PriceIndex_EndDate);
					//SB20201119		VConst_ICE_JKM.add(Const_ICE_JKM);
					VConst_RLNG_PHYS.add(Const_ICE_JKM);
					VConst_ICE_JKM_FLAG.add("N");
			//SB20201110		VEff_Price.add(EffPriceSlopeConst);
					VEff_Perc.add(FinancialCumalativePerc);
					VEff_CumulativePerc.add(FinancialCumalativePerc);
					
					VSlope_BRENT.add(Slope_ICE_JKM);
					VSlope_ICE_JKM.add(Slope_ICE_JKM);
					VSlope_JKM.add(Slope_ICE_JKM);
					VSlope_LNG_PHYS.add(Slope_ICE_JKM);
					VSlope_RLNG_PHYS.add(Slope_ICE_JKM);
			//SB20201110		VSlope.add(Slope_ICE_JKM);
					VSlope_FLAG.add(Slope_FLAG);
				//	System.out.println(VEff_Perc+">>>>>>>VEff_CumulativePerc: "+VEff_CumulativePerc);
				//	System.out.println("^^^^^^^^^^ VLNG_ICE_JKM_EXPO "+VLNG_ICE_JKM_EXPO.size());	
				////^^^^^^^Price Pulling from Table////////////////////////////
		//	System.out.println(count+"  >>>>>>>VMthYrWisePriceICE_JKM: "+VMthYrWisePriceICE_JKM.size());
		//	System.out.println(count+"  >>>>>>>COMPLETED PRICING LIST(MRCR_PriceOut), Next Loop started");
//			System.out.println(VContMthWise_gas_date.size()+"DEAL-DTL: VContMthWise_gas_date: "+VContMthWise_gas_date);
			//////////TIME CALCULATION/////////////////////////////////////////////
			String CurrDtTm="";
			queryString = "SELECT SYSDATE FROM DUAL";
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					CurrDtTm = rset.getString(1);System.out.println(VContMthWise_gas_date.size()+" :<<<<<<GAS DATE: STEP-0 COMPLETED PRICING LIST(MRCR_PriceOut) >>>>>>>>>>>>>>>>>>>>>>.....: "+CurrDtTm);
				}
			//////////********TIME CALCULATION/////////////////////////////////////////////
			for(int i=0;i<count;i++)
			{
			if(Cont_Type.equals("S")) //For SN
			{
				queryString ="select TO_CHAR(A.START_DT,'DD/MM/YYYY'),TO_CHAR(A.END_DT,'DD/MM/YYYY'),A.SN_NO,A.SN_REV_NO,A.CUSTOMER_CD,A.FGSA_NO,A.FGSA_REV_NO,A.TCQ, " +
		 		"A.SN_REF_NO,to_char(A.SIGNING_DT,'dd/mm/yyyy'),A.RATE,FCC_BY,TO_CHAR(FCC_DATE,'DD/mm/yyyy'),EMP_CD,TO_CHAR(ENT_DT,'dd/mm/yyyy'), A.DCQ from FMS7_SN_MST A where "+ 
		 		"A.CUSTOMER_CD='"+DealCustCd+"' AND A.FGSA_NO='"+FgsaNo+"' AND A.FGSA_REV_NO='"+FgsaRevNo+"' AND A.SN_NO='"+SnNo+"' AND A.SN_REV_NO='"+SnRevNo+"' "+
		 		"AND (A.START_DT <= to_date('"+VContMthWise_gas_date.elementAt(i)+"','dd/mm/yyyy')"+"AND A.END_DT >= to_date('"+VContMthWise_gas_date.elementAt(i)+"','dd/mm/yyyy')) AND " +
		 		"A.SN_REV_NO=(SELECT MAX(B.SN_REV_NO) FROM FMS7_SN_MST B where A.SN_NO=B.SN_NO AND A.FGSA_NO=B.FGSA_NO AND " +
		 		"A.CUSTOMER_CD=B.CUSTOMER_CD AND A.FGSA_REV_NO=B.FGSA_REV_NO) order by A.START_DT";
			//		System.out.println("DEAL-DTL: FMS7_SN_MST: "+queryString);
				rset = stmt.executeQuery(queryString);
				////System.out.println("FOr SN : "+to_date+" "+from_date);
				while(rset.next())
				{				
					////System.out.println("FOr SN : "+rset.getString(1)+" "+rset.getString(2));
					SN_NO.add(rset.getString(3)==null?"0":rset.getString(3));
					CUSTOMER_CD.add(rset.getString(5)==null?"0":rset.getString(5));									
					tmp_from_dt = rset.getString(1)==null?"":rset.getString(1);
					tmp_to_dt = rset.getString(2)==null?"":rset.getString(2);
					FRM_DT.add(VContMthWise_gas_date.elementAt(i));
					TO_DT.add(VContMthWise_gas_date.elementAt(i));
					temp_dt = tmp_from_dt+"   -   "+tmp_to_dt;
					CARGO_ARRIVAL_DATE.add(temp_dt);				
					SN_REV_NO.add(rset.getString(4)==null?"0":rset.getString(4));			  	
				  	FGSA_NO.add("S"+rset.getString(6)==null?"0":rset.getString(6));	
				  	FGSA_REV_NO.add(rset.getString(7)==null?"0":rset.getString(7));	
				  	BOOKMMBTU.add(rset.getString(8)==null?"0.00":nf.format(Double.parseDouble(rset.getString(8))));
				  	BOOKMMSCM.add(rset.getString(8)==null?"0.00":nf.format(Double.parseDouble(rset.getString(8))/38900));//MD20110916
				  	tot_comit += Double.parseDouble(rset.getString(8)==null?"0":nf.format(Double.parseDouble(rset.getString(8))));
				  	tot_comit_mmscm=(tot_comit/38900);//MD20110916
				  	tot_commitment_mmscm=nf.format(tot_comit_mmscm);
				  	tot_commitment=nf.format(tot_comit);
				  	SN_REF_NO.add(rset.getString(9)==null?"":rset.getString(9));	
				  	SIGNING_DT.add(VContMthWise_gas_date.elementAt(i));
				  	RATE.add(nf2.format(Double.parseDouble(rset.getString(11)==null?"0":rset.getString(11))));
				  	///////////////////Get Variable DCQ//////////////////////////
				  	queryString = "SELECT NVL(dcq,'0') "
							+ " FROM FMS7_SN_DCQ_DTL A WHERE CUSTOMER_CD="+DealCustCd+" AND SN_NO="+SnNo+" AND SN_REV_NO="+SnRevNo+" AND FGSA_NO="+FgsaNo+" AND FGSA_REV_NO="+FgsaRevNo+" AND "
							+ " A.from_dt<=TO_DATE('"+VContMthWise_gas_date.elementAt(i)+"','DD/MM/YYYY') AND A.to_dt>=TO_DATE('"+VContMthWise_gas_date.elementAt(i)+"','DD/MM/YYYY') AND A.FLAG='Y' "
							+ " AND A.sn_rev_no=(SELECT MAX(B.sn_rev_no) FROM FMS7_SN_DCQ_DTL B WHERE A.fgsa_no=B.fgsa_no AND A.sn_no=B.sn_no AND "
							+ " A.customer_cd=B.customer_cd AND  B.from_dt<=TO_DATE('"+VContMthWise_gas_date.elementAt(i)+"','DD/MM/YYYY') AND B.to_dt>=TO_DATE('"+VContMthWise_gas_date.elementAt(i)+"','DD/MM/YYYY') AND B.FLAG='Y') ";
					//		System.out.println("FMS7_SN_DCQ_DTL: "+queryString);
							rset2 = stmt2.executeQuery(queryString);
							if(rset2.next())
							{
								VDealDCQ.add(rset2.getString(1));
								VDealDCQFlag.add("Y");
							}
							else
							{
								VDealDCQ.add(rset.getString(16)==null?"0":rset.getString(16));
								VDealDCQFlag.add("N");
							}
				  	///////////////////^^^^^Get Variable DCQ//////////////////////////
				}	
			}
			else if(Cont_Type.equals("L")) //For LoA
			{
/////////////////////////////LOA LIst of Contracts/////////////////////////////////////////////////////////////////
			queryString =   "select TO_CHAR(A.START_DT,'DD/MM/YYYY'),TO_CHAR(A.END_DT,'DD/MM/YYYY'),A.LOA_NO,A.LOA_REV_NO,A.CUSTOMER_CD,A.TENDER_NO,A.TCQ, " +
					"A.LOA_REF_NO,to_char(A.SIGNING_DT,'dd/mm/yyyy'),A.RATE,FCC_BY,TO_CHAR(FCC_DATE,'DD/mm/yyyy'),EMP_CD,TO_CHAR(ENT_DT,'dd/mm/yyyy'), DCQ, ADV_AMT, TRANSPORTATION_CHARGE  from FMS7_LOA_MST A where "+ 
					"A.CUSTOMER_CD='"+DealCustCd+"' AND A.TENDER_NO='"+FgsaNo+"' AND A.LOA_NO='"+SnNo+"' AND A.LOA_REV_NO='"+SnRevNo+"' AND "+ 
					//SB20201013		"(A.START_DT <= to_date('"+to_date+"','dd/mm/yyyy')"+"AND A.END_DT >= to_date('"+from_date+"','dd/mm/yyyy')) AND " +
	///SB20210522: Bug				"(A.START_DT <= to_date('"+to_date+"','dd/mm/yyyy')"+"AND A.END_DT >= to_date('"+to_date+"','dd/mm/yyyy')) AND " + //SB20201013
					"(A.START_DT <= to_date('"+VContMthWise_gas_date.elementAt(i)+"','dd/mm/yyyy')"+"AND A.END_DT >= to_date('"+VContMthWise_gas_date.elementAt(i)+"','dd/mm/yyyy')) AND " +///SB20210522:Corrected
					//SB20200919			"(A.SIGNING_DT <= to_date('"+to_date+"','dd/mm/yyyy')"+"AND A.END_DT >= to_date('"+from_date+"','dd/mm/yyyy')) AND " +
					"A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM FMS7_LOA_MST B where A.LOA_NO=B.LOA_NO AND A.TENDER_NO=B.TENDER_NO AND " +
					"A.CUSTOMER_CD=B.CUSTOMER_CD) order by A.START_DT";
				rset = stmt.executeQuery(queryString);
			///	System.out.println("LOA: "+queryString);
				while(rset.next())
				{				
				  	SN_NO.add(rset.getString(3)==null?"0":rset.getString(3));
					CUSTOMER_CD.add(rset.getString(5)==null?"0":rset.getString(5));									
					tmp_from_dt = rset.getString(1)==null?"":rset.getString(1);
					tmp_to_dt = rset.getString(2)==null?"":rset.getString(2);
					FRM_DT.add(VContMthWise_gas_date.elementAt(i));
					TO_DT.add(VContMthWise_gas_date.elementAt(i));
					temp_dt = tmp_from_dt+"   -   "+tmp_to_dt;
					CARGO_ARRIVAL_DATE.add(temp_dt);				
					SN_REV_NO.add(rset.getString(4)==null?"0":rset.getString(4));			  	
				  	FGSA_NO.add("L"+rset.getString(6)==null?"0":rset.getString(6));	
				  	FGSA_REV_NO.add("0");	 //No Rev no in LoA
				  	BOOKMMBTU.add(rset.getString(7)==null?"0.00":nf.format(Double.parseDouble(rset.getString(7))));
				  	BOOKMMSCM.add(rset.getString(7)==null?"0.00":nf.format(Double.parseDouble(rset.getString(7))/38900));//MD20110916
				  	tot_comit += Double.parseDouble(rset.getString(7)==null?"0":nf.format(Double.parseDouble(rset.getString(7))));
				  	tot_comit_mmscm=(tot_comit/38900);//MD20110916
				  	tot_commitment_mmscm=nf.format(tot_comit_mmscm);
				  	tot_commitment=nf.format(tot_comit);
				  	SN_REF_NO.add(rset.getString(8)==null?"":rset.getString(8));	
				  	SIGNING_DT.add(VContMthWise_gas_date.elementAt(i));
				  	RATE.add(nf2.format(Double.parseDouble(rset.getString(10)==null?"0":rset.getString(10))));
				  	///////////////////Get Variable DCQ//////////////////////////
					  	queryString = "SELECT NVL(dcq,'0') "
								+ " FROM FMS7_LOA_DCQ_DTL A WHERE CUSTOMER_CD="+DealCustCd+" AND LOA_NO="+SnNo+" AND LOA_REV_NO="+SnRevNo+" AND TENDER_NO="+FgsaNo+" AND "
								+ " A.from_dt<=TO_DATE('"+VContMthWise_gas_date.elementAt(i)+"','DD/MM/YYYY') AND A.to_dt>=TO_DATE('"+VContMthWise_gas_date.elementAt(i)+"','DD/MM/YYYY') AND A.FLAG='Y' "
								+ " AND A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM FMS7_LOA_DCQ_DTL B WHERE A.TENDER_NO=B.TENDER_NO AND A.LOA_NO=B.LOA_NO AND "
								+ " A.customer_cd=B.customer_cd AND  B.from_dt<=TO_DATE('"+VContMthWise_gas_date.elementAt(i)+"','DD/MM/YYYY') AND B.to_dt>=TO_DATE('"+VContMthWise_gas_date.elementAt(i)+"','DD/MM/YYYY') AND B.FLAG='Y') ";
							//	System.out.println("DCQ: FMS7_LOA_DCQ_DTL: "+queryString);
								rset2 = stmt2.executeQuery(queryString);
								if(rset2.next())
								{
									VDealDCQ.add(rset2.getString(1));
									VDealDCQFlag.add("Y");
								}
								else
								{
									VDealDCQ.add(rset.getString(15)==null?"0":rset.getString(15));
									VDealDCQFlag.add("N");
								}
					  	///////////////////^^^^^Get Variable DCQ//////////////////////////
				}
			}
			else if(Cont_Type.equals("B"))
			{
				queryString = "SELECT TO_CHAR(A.DELV_FROM_DT,'DD/MM/YYYY'), TO_CHAR(A.DELV_TO_DT,'DD/MM/YYYY'),A.MAN_CONF_CD,A.CARGO_SEQ_NO,E.TRD_CD, A.MAN_CD, A.MAN_CD, A.CONFIRM_VOL,"
						+ "A.CARGO_REF_CD,to_char(C.MAN_CONF_DT,'dd/mm/yyyy'), A.PRICE,A.EMP_CD,TO_CHAR(A.ENT_DT,'dd/mm/yyyy'), A.EMP_CD,TO_CHAR(A.ENT_DT,'dd/mm/yyyy'), E.PRICE_TYPE, A.PRICE_TO, A.REMARK  "+
						" FROM FMS7_MAN_CONFIRM_CARGO_DTL A, FMS7_MAN_CONFIRM_MST C, FMS7_MAN_REQ_MST E WHERE "+
						"E.TRD_CD='"+DealCustCd+"' AND A.MAN_CONF_CD='"+SnNo+"' AND A.MAN_CD='"+FgsaNo+"' AND A.CARGO_SEQ_NO='"+SnRevNo+"' AND "+
		///SB20210522				" A.DELV_TO_DT >= TO_DATE('"+to_date+"','DD/MM/YYYY') "
						" A.DELV_TO_DT >= TO_DATE('"+VContMthWise_gas_date.elementAt(i)+"','DD/MM/YYYY') " ///SB20210522:Corrected
						+ "AND E.MAN_CD=A.MAN_CD AND "+ 
				///		" A.UNIT_CD=C.UNIT_CD AND A.CARGO_STATUS='T' AND CARGO_REF_CD='"+VCAR_REF_NO_temp.elementAt(i)+"' "+ 
						" A.MAN_CONF_CD=C.MAN_CONF_CD AND A.CARGO_STATUS='T' AND CARGO_REF_CD LIKE '%' "+ 
						" ORDER BY A.MAN_CONF_CD, A.CARGO_REF_CD";
			//	System.out.println("2. CARGO: "+queryString);
				rset = stmt.executeQuery(queryString);
				////System.out.println("FOr SN : "+to_date+" "+from_date);
				while(rset.next())
				{				
					tmp_from_dt = rset.getString(1)==null?"":rset.getString(1);
					tmp_to_dt = rset.getString(2)==null?"":rset.getString(2);
					FRM_DT.add(VContMthWise_gas_date.elementAt(i));
					TO_DT.add(VContMthWise_gas_date.elementAt(i));
					temp_dt = tmp_from_dt+"   -   "+tmp_to_dt;
					CARGO_ARRIVAL_DATE.add(temp_dt);				
					SN_NO.add(rset.getString(3)==null?"0":rset.getString(3));
					SN_REV_NO.add(rset.getString(4)==null?"0":rset.getString(4));
					CUSTOMER_CD.add(rset.getString(5)==null?"0":rset.getString(5));																  	
				  	FGSA_NO.add("B"+rset.getString(6)==null?"0":rset.getString(6));	
				  	FGSA_REV_NO.add("0");	 //No Rev no in LoA
				  	BOOKMMBTU.add(rset.getString(8)==null?"0.00":nf.format(Double.parseDouble(rset.getString(8))));
				  	BOOKMMSCM.add(rset.getString(8)==null?"0.00":nf.format(Double.parseDouble(rset.getString(8))/38900));//MD20110916
				 // 	tot_comit += Double.parseDouble(rset.getString(7)==null?"0":nf.format(Double.parseDouble(rset.getString(7))));
				 // 	tot_comit_mmscm=(tot_comit/38900);//MD20110916
				  	tot_commitment_mmscm=nf.format(tot_comit_mmscm);
				  	tot_commitment=nf.format(tot_comit);
				  	SN_REF_NO.add(rset.getString(9)==null?"":rset.getString(9));	
				  	SIGNING_DT.add(VContMthWise_gas_date.elementAt(i));
				  	RATE.add(nf2.format(Double.parseDouble(rset.getString(11)==null?"0":rset.getString(11))));
				  	///////////////////Get Variable DCQ//////////////////////////
		//SB20210317		  	VDealDCQ.add(rset.getString(8)==null?"0":rset.getString(8));
					VDealDCQ.add(nf.format(rset.getDouble(8)/count)); //SB20210317: To get the Avg DCQ value, in case of BUY deal
					VDealDCQFlag.add("N");
					////////////////////SB20210517//////////////////////////
					/////////////////SB20210524: //////////////////////////////
					if(count<=3) //SB20210517: if it is CARGO Purchase( To differenciate between Cargo Buy and Domestic Buy Deal)
					{
				  	queryString = "SELECT QTY_MMBTU FROM FMS7_CARGO_QQ_DTL "
				  		//Sb20210525	+" WHERE CARGO_REF_NO='"+rset.getString(9)+"' AND QQ_DT<=TO_DATE('"+VContMthWise_gas_date.elementAt(i)+"','DD/MM/YYYY') AND FLAG='Y' ";	
				  	+" WHERE CARGO_REF_NO='"+rset.getString(9)+"' AND FLAG='Y' ";
				//  	System.out.println("ACTUAL-QTY/DCQ: FMS7_LOA_DCQ_DTL: "+queryString);
							rset2 = stmt2.executeQuery(queryString);
							if(rset2.next())
							{
							//SB20210524	VDealAllocQty.add(rset2.getString(1));
							///	VDealAllocQty.add(rset2.getDouble(1)/count);
								if(i==count-1)
									VDealAllocQty.add(rset2.getDouble(1));
								else
									VDealAllocQty.add("0");
							}
							else
							{
								VDealAllocQty.add("0");
							}
					}
					else //SB20210517: if it is BUY Deal Purchase(  From and To date is different)
					{
						String TransId="3"; String TransEntryPt="31"; String TransExitPt="341";
					//SB	String AllocMapp_Id=DealCustCd+"-"+SnNo+"-"+FgsaNo+"-"+TransId+"-"+TransEntryPt+"-"+TransExitPt; //5-21012-21001-3-31-341
						String AllocMapp_Id=DealCustCd+"-"+SnNo+"-"+FgsaNo+"-%"; //5-21012-21001-3-31-341
						String ContMapp_Id="B-"+DealCustCd+"-"+SnNo+"-0-"+FgsaNo+"-"+SnRevNo; //System.out.println("ContMapp_Id: "+ContMapp_Id);
					//	System.out.println("Mapp_Id: "+Mapp_Id);
						////////////////////^^^^SB20210517//////////////////////////
							queryString = "SELECT SUM(ENTRY_TOT_ENE) FROM FMS9_PO_ALLOC_MST "
									+" WHERE MAPPING_ID LIKE '"+AllocMapp_Id+"' AND CONT_MAPPING_ID = '"+ContMapp_Id+"' AND ALLOC_DT=TO_DATE('"+VContMthWise_gas_date.elementAt(i)+"','DD/MM/YYYY') ";
						 	//System.out.println("ACTUAL-QTY/DCQ: FMS9_PO_ALLOC_MST: "+queryString);
							rset2 = stmt2.executeQuery(queryString);
							if(rset2.next())
							{
								VDealAllocQty.add(rset2.getDouble(1));
							}
							else
							{
								VDealAllocQty.add("0");
							}
					}
				  	///////////////////^^^^^Get Variable DCQ//////////////////////////
				}	
			}
			/////////////////////////////////////////////////////////////////
			else if(Cont_Type.equals("O"))
			{
				
				{				
					tmp_from_dt =StorageDate;
					tmp_to_dt = StorageDate;
					FRM_DT.add(StorageDate);
					TO_DT.add(StorageDate);
					temp_dt = StorageDate+"   -   "+StorageDate;
					CARGO_ARRIVAL_DATE.add(temp_dt);				
					SN_NO.add("0");
					SN_REV_NO.add("0");
					CUSTOMER_CD.add("0");																  	
				  	FGSA_NO.add("0");	
				  	FGSA_REV_NO.add("0");	 //No Rev no in LoA
				  	BOOKMMBTU.add("0");
				  	BOOKMMSCM.add("0");//MD20110916
				  	tot_commitment_mmscm=nf.format(tot_comit_mmscm);
				  	tot_commitment=nf.format(tot_comit);
				  	SN_REF_NO.add("");	
					SIGNING_DT.add(StorageDate);
				  	RATE.add("0");
				  	///////////////////Get Variable DCQ//////////////////////////
				  	//////////////////SB20210201//////////////////////////////
					String Report_Dt=to_date; //SB20210201
					MarketExposureStorage(Report_Dt);  //SB20210201: Get Storage Qty available in VStoreSEIPL
					//////////////////^^^SB20210201//////////////////////////////
					if(VStoreSEIPL.size()>0)
						VDealDCQ.add(VStoreSEIPL.elementAt(0));
					else
						VDealDCQ.add("0");
					VDealDCQFlag.add("N");				  	
					VDealAllocQty.add("0");
				  	///////////////////^^^^^Get Variable DCQ//////////////////////////
				
				}	
			}
			}  
			System.out.println(VContMthWise_gas_date.size()+"  :Gas Date Size / CUSTOMER_NM: "+CUSTOMER_CD.size());
		
			for(int i=0;i<CUSTOMER_CD.size();i++)
			{	
				double AllPlantAllocQty=0; //SB20210617
				int AllPlantAllocQtyCount=0; //SB20210617
			//	System.out.println("CUSTOMER_NM: "+CUSTOMER_NM);
				int ReportDays=-1;
				queryString = "SELECT TO_DATE('"+to_date+"','DD/MM/YYYY')-TO_DATE('"+VContMthWise_gas_date.elementAt(i)+"','DD/MM/YYYY') FROM DUAL" ;
				//	System.out.println(" DUAL: LAST: "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next()) {
					ReportDays = rset.getInt(1);
					}	
				if(ReportDays>=0 && AllPlantAllocQty==0) ///Note: Incase Allocation is ZERO then it will pick up NOM data for the Report Date. Checking needed to verify
				{
				queryString ="select SUM(QTY_MMBTU), COUNT(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL where CUSTOMER_CD="+DealCustCd+" " +
		//SB20210524		 "AND SN_NO="+SnNo+" AND SN_REV_NO="+SnRevNo+" AND FGSA_NO="+FgsaNo+" " +
				 "AND SN_NO="+SnNo+" AND SN_REV_NO LIKE '%' AND FGSA_NO="+FgsaNo+" " + //SB20210524
		//SB20210524		 "AND FGSA_REV_NO="+FgsaRevNo+" AND GAS_DT between to_date('"+VContMthWise_gas_date.elementAt(i)+"','dd/mm/yyyy')" +
				 "AND FGSA_REV_NO LIKE '%' AND GAS_DT between to_date('"+VContMthWise_gas_date.elementAt(i)+"','dd/mm/yyyy')" + //SB20210524
				 "AND to_date('"+VContMthWise_gas_date.elementAt(i)+"','dd/mm/yyyy') AND CONTRACT_TYPE='"+Cont_Type+"' ";
			//	System.out.println("EXPO: >>>>>>>FMS7_DAILY_ALLOCATION_DTL "+queryString);
				rset = stmt.executeQuery(queryString);
				
				if(rset.next())
				{
					/*SB20210617 SUPPLIEDMBTU.add(rset.getString(1)==null?"0.00":nf.format(Double.parseDouble(rset.getString(1))));
					//MD20110916
					SUPPLIEDMMSCM.add(rset.getString(1)==null?"0.00":nf.format(Double.parseDouble(rset.getString(1))/38900));
					if(rset.getString(1)!=null && !(rset.getString(1).equals("")))
					{
						sup_tot_comit+=Double.parseDouble(rset.getString(1));
						sup_tot_comit_mmscm+=(Double.parseDouble(rset.getString(1))/38900);//MD20110916
					}
					else
					{
						sup_tot_comit+=0;
						sup_tot_comit_mmscm+=0;//MD20110916
					}*/
					AllPlantAllocQty=rset.getDouble(1); //SB20210617
					AllPlantAllocQtyCount=rset.getInt(2); //SB20210617
				}
				else 
				{
				//SB20210617	SUPPLIEDMBTU.add("0.00");
				//SB20210617	SUPPLIEDMMSCM.add("0.00");//MD20110916
					AllPlantAllocQty=0; //SB20210617
					AllPlantAllocQtyCount=0; //SB20210617
				}
				}
				//////////////////SB20210617: as discussed with Rohit & Shiladitya/////////////////////////////////////
				/*int ReportDays=-1;
				queryString = "SELECT TO_DATE('"+to_date+"','DD/MM/YYYY')-TO_DATE('"+VContMthWise_gas_date.elementAt(i)+"','DD/MM/YYYY') FROM DUAL" ;
					System.out.println(" DUAL: LAST: "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next()) {
					ReportDays = rset.getInt(1);
					}	*/
				if(ReportDays==0 && AllPlantAllocQtyCount==0) ///Note: Incase Allocation Count is ZERO then it will pick up NOM data for the Report Date. 
				{
					//////////////SB20210623: as discussed with Rohit & Shiladitya/////////////////////////////////////	
						int CountSellerDt=0;
						queryString="select COUNT(QTY_MMBTU) from FMS7_DAILY_SELLER_NOM_DTL "+
								" WHERE SN_NO="+SnNo+" AND SN_REV_NO LIKE '%' AND FGSA_NO="+FgsaNo+" AND FGSA_REV_NO LIKE '%' "
								+ "AND GAS_DT =to_date('"+to_date+"','dd/mm/yyyy') AND CONTRACT_TYPE='"+Cont_Type+"' ";
					//		System.out.println(map_id+" :STEP-3A: FMS7_DAILY_SELLER_NOM_DTL: "+queryString11);
						rset1 = stmt1.executeQuery(queryString);		
						if(rset1.next())
						{
							CountSellerDt = rset1.getInt(1);
						}
						if(CountSellerDt>0)
						{
							String TransCode=""; double SellerNomQty=0;
							queryString1 = "select DISTINCT(TRANSPORTER_CD) from FMS7_DAILY_SELLER_NOM_DTL "
										+" WHERE CUSTOMER_CD="+DealCustCd+" AND SN_NO="+SnNo+" AND SN_REV_NO LIKE '%' AND FGSA_NO="+FgsaNo+" AND FGSA_REV_NO LIKE '%' "
										+ "AND GAS_DT=to_date('"+to_date+"','dd/mm/yyyy') AND CONTRACT_TYPE='"+Cont_Type+"' ";
									System.out.println(DealCustCd+" :STEP-3B: FMS7_DAILY_SELLER_NOM_DTL: "+queryString1);
								rset1 = stmt1.executeQuery(queryString1);		
								while(rset1.next())
								{		
									TransCode=rset1.getString(1)==null?"0":rset1.getString(1);
									queryString="select SUM(QTY_MMBTU) from FMS7_DAILY_SELLER_NOM_DTL "
												+" WHERE CUSTOMER_CD="+DealCustCd+" AND SN_NO="+SnNo+" AND SN_REV_NO LIKE '%' AND FGSA_NO="+FgsaNo+" AND FGSA_REV_NO LIKE '%' "
												+ "AND GAS_DT=to_date('"+to_date+"','dd/mm/yyyy') AND CONTRACT_TYPE='"+Cont_Type+"' AND TRANSPORTER_CD='"+TransCode+"' "
												+ "AND NOM_REV_NO=(select MAX(NOM_REV_NO) from FMS7_DAILY_SELLER_NOM_DTL "
												+" WHERE CUSTOMER_CD="+DealCustCd+" AND SN_NO="+SnNo+" AND SN_REV_NO LIKE '%' AND FGSA_NO="+FgsaNo+" AND FGSA_REV_NO LIKE '%' "
												+ "AND GAS_DT=to_date('"+to_date+"','dd/mm/yyyy') AND CONTRACT_TYPE='"+Cont_Type+"' AND TRANSPORTER_CD='"+TransCode+"' ) ";
										System.out.println(DealCustCd+" :STEP-3B: FMS7_DAILY_SELLER_NOM_DTL: "+queryString);
										rset = stmt.executeQuery(queryString);		
										if(rset.next())
										{
											SellerNomQty+=rset.getDouble(1);
										}
								}
								SUPPLIEDMBTU.add(nf.format(SellerNomQty));
								SUPPLIEDMMSCM.add(nf.format(SellerNomQty/38900));//MD20110916
						}
						else if(CountSellerDt==0)
						{
						String TransCode=""; double NomQty=0;
						queryString1 = "select DISTINCT(TRANSPORTER_CD) from FMS7_DAILY_BUYER_NOM_DTL "
									+" WHERE CUSTOMER_CD="+DealCustCd+" AND SN_NO="+SnNo+" AND SN_REV_NO LIKE '%' AND FGSA_NO="+FgsaNo+" AND FGSA_REV_NO LIKE '%' "
									+ "AND GAS_DT=to_date('"+to_date+"','dd/mm/yyyy') AND CONTRACT_TYPE='"+Cont_Type+"' ";
							//	System.out.println(DealCustCd+" :STEP-3B: FMS7_DAILY_BUYER_NOM_DTL: "+queryString);
							rset1 = stmt1.executeQuery(queryString1);		
							while(rset1.next())
							{		
								TransCode=rset1.getString(1)==null?"0":rset1.getString(1);
								queryString="select SUM(QTY_MMBTU) from FMS7_DAILY_BUYER_NOM_DTL "
											+" WHERE CUSTOMER_CD="+DealCustCd+" AND SN_NO="+SnNo+" AND SN_REV_NO LIKE '%' AND FGSA_NO="+FgsaNo+" AND FGSA_REV_NO LIKE '%' "
											+ "AND GAS_DT=to_date('"+to_date+"','dd/mm/yyyy') AND CONTRACT_TYPE='"+Cont_Type+"' AND TRANSPORTER_CD='"+TransCode+"' "
											+ "AND NOM_REV_NO=(select MAX(NOM_REV_NO) from FMS7_DAILY_BUYER_NOM_DTL "
											+" WHERE CUSTOMER_CD="+DealCustCd+" AND SN_NO="+SnNo+" AND SN_REV_NO LIKE '%' AND FGSA_NO="+FgsaNo+" AND FGSA_REV_NO LIKE '%' "
											+ "AND GAS_DT=to_date('"+to_date+"','dd/mm/yyyy') AND CONTRACT_TYPE='"+Cont_Type+"' AND TRANSPORTER_CD='"+TransCode+"' ) ";
									System.out.println(DealCustCd+" :STEP-3B: FMS7_DAILY_BUYER_NOM_DTL: "+queryString);
									rset = stmt.executeQuery(queryString);		
									if(rset.next())
									{
										NomQty+=rset.getDouble(1);
									}
							}
							SUPPLIEDMBTU.add(nf.format(NomQty));
							SUPPLIEDMMSCM.add(nf.format(NomQty/38900));//MD20110916
						}
			     //////////////^^^^SB20210623: as discussed with Rohit & Shiladitya/////////////////////////////////////	
				}
				else
				{
					SUPPLIEDMBTU.add(AllPlantAllocQty);
					SUPPLIEDMMSCM.add(AllPlantAllocQty/38900);//MD20110916
				}
				//////////////^^^^^SB20210617: as discussed with Rohit & Shiladitya/////////////////////////////////////
				BALANCEMMBTU.add(nf.format(Double.parseDouble(""+BOOKMMBTU.elementAt(i))- Double.parseDouble(""+SUPPLIEDMBTU.elementAt(i))));
				//MD20110916
				BALANCEMMSCM.add(nf.format(Double.parseDouble(""+BOOKMMSCM.elementAt(i))- Double.parseDouble(""+SUPPLIEDMMSCM.elementAt(i))));
				bal_tot_comit+=Double.parseDouble(""+BOOKMMBTU.elementAt(i))- Double.parseDouble(""+SUPPLIEDMBTU.elementAt(i));
				bal_tot_comit_mmscm+=Double.parseDouble(""+BOOKMMSCM.elementAt(i))- Double.parseDouble(""+SUPPLIEDMMSCM.elementAt(i));
				//System.out.println("SUPLIEDMMSCM : "+SUPPLIEDMMSCM+" TOTAL : "+sup_tot_comit_mmscm);
				//System.out.println("BALANCEMMSCM : "+BALANCEMMSCM+" TOTAL : "+bal_tot_comit_mmscm);
					
				//////////////SB20200817////////////////////////
			}
		
			/*SB20210708 ///////////////////////////////HOLIDAY CALCULATION///////////////////////////////////
			String HolidayType="";
			if(INDEX_TYPE.equals("LNG_ICE_JKM") || INDEX_TYPE.equals("JKM_PLATTS_SETTLEMENT") || INDEX_TYPE.equals("ICE_JKM")) //SB20210402
				HolidayType="J";
			else if(INDEX_TYPE.equals("CR_DATED_BRENT") || INDEX_TYPE.equals("ICE_BRENT_FIRST_LINE")  || INDEX_TYPE.equals("ICE_BRENT")) //SB20210407 //SB20210402 //SB20201025
				HolidayType="B";
			else
				HolidayType="J";
///////////////////////////////////////////////////////////////////////////////////
*/
System.out.println(CUSTOMER_CD.size()+"  :CUSTOMER_CD: FINAL STEPS STARTED: >>>>>>>>>>>>>>>>>>>>");
//System.out.println(VPriceIndex_StartDate.size()+"  :VPriceIndex_StartDate: STEP-1: "+VPriceIndex_StartDate);
//System.out.println(VPriceIndex_EndDate.size()+"  :VPriceIndex_StartDate: STEP-1: "+VPriceIndex_EndDate);
	//////////TIME CALCULATION/////////////////////////////////////////////
	CurrDtTm="";
	queryString = "SELECT SYSDATE FROM DUAL";
		rset = stmt.executeQuery(queryString);
		if(rset.next())
		{
			CurrDtTm = rset.getString(1);System.out.println(VDealDCQ.size()+" :<<<<<<GAS DATE: STEP-1 COMPLETED >>>>>>>>>>>>>>>>>>>>>>.....: "+CurrDtTm);
		}
	//////////********TIME CALCULATION/////////////////////////////////////////////

			for(int i=0;i<CUSTOMER_CD.size();i++)
			{
				String TCQ=""; String Alloc_Qty=""; String Gen_Dt=to_date; String Cont_End_Dt=""; 
				TCQ=""+BOOKMMBTU.elementAt(i);
				String DCQ=""+VDealDCQ.elementAt(i); 
				TCQ=DCQ;
				Alloc_Qty=""+SUPPLIEDMBTU.elementAt(i); 
				Cont_End_Dt=""+TO_DT.elementAt(i);
				String Phy_CurFrd_Price="0"; String Fin_CurFrd_Price="0";
	
				if(Buy_Sell.equals("Buy"))
				{
					//SB20201201 VDealAllocQty.add(Alloc_Qty);
				}
				else
					VDealAllocQty.add("-"+Alloc_Qty);
		
			
				String PriorCurveGasPrice="";
			//	if(VMthYrWisePriceICE_JKM.elementAt(i).equals(""))
				//SB20210918		PriorCurveGasPrice=""+VMthYrWisePriceICE_JKM.elementAt(i);
				
				String DealPrice="";
				DealPrice=""+RATE.elementAt(i);
				String Slope="1.0";
				/*/SB20210918		if(Double.parseDouble(""+VMthYrWisePriceICE_JKM.elementAt(i))>0)  //SB: Set JKM Forward Price to ZERO, If Settle Price is Available
				{
					Fin_CurFrd_Price=""+Double.parseDouble(""+VMthYrWisePriceICE_JKM.elementAt(i));
					DealPrice=""+Double.parseDouble(""+VEff_Price.elementAt(i));   //SB20200918: If Price is Settled then Deal price is Settle Price
					//Slope=""+Double.parseDouble(""+VSlope.elementAt(i));   //SB20200918:
				}*/
			//	System.out.println(VEff_Price.elementAt(i)+" DealPrice: "+VMthYrWisePriceICE_JKM.elementAt(i));
			///SB	if(VMthYrWisePriceICE_JKM.elementAt(i).equals("0")) DealPrice="0";
				
				////////////////////////////SB20201020: Very Important for DropOff/////////////////////////////////
/*SB20210918				double LNG_ICE_JKM_EXPOBaseValue=0;
				int StratDayCount=0; int StratDayCount2=0; //SB20201017: to check Starting SAT/SUN/HOLIDAY
				String PriceBegMthDt=""+VPriceIndex_StartDate.elementAt(i);
				int ReportDays=0; int ReportDays2=0;
				if(!PriceBegMthDt.equals(""))  //System.out.println(i+"/"+VPriceIndex_StartDate.size()+" <--BLANK LOCATION: PriceBegMthDt-->: "+PriceBegMthDt);  //SB20201128
				{
					queryString = "SELECT TO_DATE('"+to_date+"','DD/MM/YYYY')-TO_DATE('"+PriceBegMthDt+"','DD/MM/YYYY')+1 FROM DUAL" ;
				//	System.out.println(" DUAL: LAST: "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next()) {
					ReportDays = rset.getInt(1);
					}	//System.out.println("ReportDays: "+ReportDays);
				}
				for(int j=0; j<ReportDays; j++)
				{
					String NextDate2="";
					queryString = "SELECT TO_CHAR(TO_DATE('"+PriceBegMthDt+"','DD/MM/YYYY')+"+j+",'DD/MM/YYYY') FROM DUAL";
				//		System.out.println("Get DAY Name: "+queryString);
						rset = stmt.executeQuery(queryString);
						if(rset.next())
						{
							NextDate2 = rset.getString(1)==null?"":rset.getString(1);
						}
				queryString ="SELECT to_char(HOLIDAY_DT,'dd/mm/yyyy') FROM FMS9_CURVE_HOLIDAY_DTL "
						//SB20210708		+ " WHERE HOLIDAY_DT =to_date('"+NextDate2+"','dd/mm/yyyy') AND FLAG='Y' AND HOLIDAY_TYPE='"+HolidayType+"' ORDER BY HOLIDAY_DT";
						+ " WHERE HOLIDAY_DT =to_date('"+NextDate2+"','dd/mm/yyyy') AND FLAG='Y' AND CURVE_NM='"+INDEX_TYPE+"' ORDER BY HOLIDAY_DT";//SB20210708
				rset = stmt.executeQuery(queryString);
			//	System.out.println(">>>>>>>PRICE PUULING: "+queryString);
				if(rset.next())
				{
					StratDayCount++; //System.out.println("Get HOLI DAY Name: ");		
				}
				else
				{
				String WeeklyOff="";
				queryString = "SELECT TO_CHAR(TO_DATE('"+NextDate2+"','DD/MM/YYYY'),'DAY') FROM DUAL";
				//	System.out.println("Get DAY Name: "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						WeeklyOff = rset.getString(1)==null?"":rset.getString(1);
					}
					if(WeeklyOff.trim().equals("SATURDAY")) 
					{
						StratDayCount++;
						//PriceBegMthDt="17/08/2020"; 
					}
					else if(WeeklyOff.trim().equals("SUNDAY")) 
					{
						StratDayCount++;
						//PriceBegMthDt="17/08/2020"; 
					}
				}
				}
			//	System.out.println(LNG_ICE_JKM_EXPOBaseValue+" :**SAIBAL*****StratDayCount: "+StratDayCount);
				int EndSettlePriceDateCount=ReportDays-1; //SB20201024
				ReportDays = ReportDays-StratDayCount;
				if(ReportDays>=0)
					LNG_ICE_JKM_EXPOBaseValue=ReportDays;
			//	System.out.println(LNG_ICE_JKM_EXPOBaseValue+" :******* VLNG_ICE_JKM_EXPO: "+ReportDays);
		///////////////////////////////^^^^SB20201020//////////////////////////////////////////
				
/////////////////////////////Avr Settlement Price //////////////////////////////////
				if(PriceBegMthDt.equals(""))
					PriceBegMthDt=""+to_date;
				int LastPriceDays=0; String SettlePriceDate="";
				if(!VPriceIndex_EndDate.elementAt(i).equals(""))
				{
					queryString = "SELECT TO_DATE('"+to_date+"','DD/MM/YYYY')-TO_DATE('"+VPriceIndex_EndDate.elementAt(i)+"','DD/MM/YYYY')+1 FROM DUAL" ;
				//	System.out.println(" DUAL: LAST: "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next()) {
					LastPriceDays = rset.getInt(1);
					}	
					if(LastPriceDays>0)
						SettlePriceDate=""+VPriceIndex_EndDate.elementAt(i);
					else
						SettlePriceDate=""+to_date; 
					//SB20201107 SettlePriceDate=""+VPriceIndex_EndDate.elementAt(i); //SB20201107: to get the end date for Avg Cal if Report date is current Date
				}
				else
					SettlePriceDate=""+to_date;
				
		////////Index type Pulling from Table////////////////////////////
				PricePHYSName=""; //SB20210312
		//SB20210310				queryString ="SELECT PRICE_TYPE, CURVE_NM, RATE, SEQ_NO FROM FMS9_MRCR_CONT_PRICE_DTL "
				queryString ="SELECT PRICE_TYPE, CURVE_NM, RATE, SEQ_NO, PHYS_CURVE_NM FROM FMS9_MRCR_CONT_PRICE_DTL " //SB20210310	
			//SB20210528			+ "WHERE MAPPING_ID='"+Mapp_Id+"' AND FROM_DT<=TO_DATE('"+VContMthWise_gas_date.elementAt(i)+"','DD/MM/YYYY') AND TO_DT>=TO_DATE('"+VContMthWise_gas_date.elementAt(i)+"','DD/MM/YYYY') AND FLAG='Y' ";//AND CURVE_NM='"+INDEX_TYPE+"' ";
				+ "WHERE MAPPING_ID LIKE '"+Mapp_Id+"' AND FROM_DT<=TO_DATE('"+VContMthWise_gas_date.elementAt(i)+"','DD/MM/YYYY') AND TO_DT>=TO_DATE('"+VContMthWise_gas_date.elementAt(i)+"','DD/MM/YYYY') AND FLAG='Y' ";//SB20210528
				//System.out.println("FMS9_CURVE_PRICE_DTL: "+queryString);
				rset2 = stmt.executeQuery(queryString);			
				if(rset2.next())
				{
					INDEX_TYPE=rset2.getString(2)==null?"":rset2.getString(2);
					PricePHYSName=rset2.getString(5)==null?"":rset2.getString(5);  //SB20210310	
				//	System.out.println("FMS9_MRCR_CONT_PRICE_DTL: PricePHYSName: "+PricePHYSName);
		*/
				/*SB20210708	if(INDEX_TYPE.equals("LNG_ICE_JKM") || INDEX_TYPE.equals("JKM_PLATTS_SETTLEMENT") || INDEX_TYPE.equals("ICE_JKM")) //SB20210402
						HolidayType="J";
					else if(INDEX_TYPE.equals("CR_DATED_BRENT") || INDEX_TYPE.equals("ICE_BRENT_FIRST_LINE")  || INDEX_TYPE.equals("ICE_BRENT")) //SB20210407 //SB20210402 //SB20201025
						HolidayType="B";
					else
						HolidayType="J";*/
/*SB20210918				}
				//SB20210407 if(INDEX_TYPE.equals("CR_DATED_BRENT") || INDEX_TYPE.equals("ICE_BRENT")) //SB20201117
			 if(INDEX_TYPE.equals("CR_DATED_BRENT") || INDEX_TYPE.equals("ICE_BRENT_FIRST_LINE")  || INDEX_TYPE.equals("ICE_BRENT")) //SB20210407 
				 PriceIndexName="ICE_BRENT";
			//SB20210407 else if(INDEX_TYPE.equals("LNG_ICE_JKM")) //SB20210406
			 else if(INDEX_TYPE.equals("LNG_ICE_JKM") || INDEX_TYPE.equals("JKM_PLATTS_SETTLEMENT") || INDEX_TYPE.equals("ICE_JKM")) //SB20210407
				 PriceIndexName="ICE_JKM";
			 else 
				 PriceIndexName=""+INDEX_TYPE;
					////////////////////////////////////////////////////////////////////////
		////////////////SB20210228: For Buy Deal It should be LNG_PHYS_INDIA else RLNG_PHYS_INDIA as per Shiladita 25th Feb 2021////////////////////////////////////////////////////////
		if(PricePHYSName.equals(""))  //SB20210310	
		{
			 if(Buy_Sell.equalsIgnoreCase("BUY"))
				 PricePHYSName="LNG_PHYS_INDIA";
			 else
				 PricePHYSName="RLNG_PHYS_INDIA";
		}
		
		//////////////^^^^SB20210504: PPAC Logic/////////////////////////
		
		String Phy_CurFrd_PriceNm=PricePHYSName; String Fin_CurFrd_PriceNm=PriceIndexName; //SB20210312
				////////////////^SB20210228////////////////////////////////////////////////////////
	double AvgPriceSettle=0; int PriceAvlCountSettle=1;
	

	String CurveName="";
	
	if(INDEX_TYPE.equals("LNG_ICE_JKM") || INDEX_TYPE.equals("JKM_PLATTS_SETTLEMENT") || INDEX_TYPE.equals("ICE_JKM")) //SB20210409
		 CurveName="('ICE_JKM')";
	 else 	 if(INDEX_TYPE.equals("CR_DATED_BRENT") || INDEX_TYPE.equals("ICE_BRENT_FIRST_LINE")  || INDEX_TYPE.equals("ICE_BRENT")) //SB20210409 //SB20210402 //SB20201025
		 CurveName="('ICE_BRENT')";
	 else //SB20210407
		 CurveName="('"+INDEX_TYPE+"')";
	queryString = "select AVG(SETTLE_PRICE), COUNT(*) from FMS9_CURVE2_PRICE_DTL "
			+ " where SETTLE_PRICE>0 AND CURVE_DD_MM_YR BETWEEN to_date('"+PriceBegMthDt+"','dd/mm/yyyy') AND to_date('"+SettlePriceDate+"','dd/mm/yyyy') "
			//SB20210409	+ "AND CURVE_TYPE='Spot' AND CURVE_NM='"+CurveName+"' ";
	+ "AND CURVE_TYPE='Spot' AND PHYS_FIN IN "+CurveName+" "; //SB20210409
//	System.out.println("AVG: FM S9_CURVE_PRICE_DTL: "+queryString);
	rset = stmt.executeQuery(queryString);
	if(rset.next())
	{
		AvgPriceSettle = rset.getDouble(1);
		PriceAvlCountSettle = rset.getInt(2);
		if(ReportDays>0)
			VSettle_ICE_JKM_AVG.add(nf2.format(AvgPriceSettle)); //Sb20201023
		else
			VSettle_ICE_JKM_AVG.add("");
		
	}
	else
	{
		AvgPriceSettle = 0;
		PriceAvlCountSettle = 1;
		VSettle_ICE_JKM_AVG.add("");
		
	}	
//	System.out.println(SettlePriceDate+" :AvgPriceSettle: " +AvgPriceSettle); System.out.println(VSettle_ICE_JKM_AVG+" :VSettle_ICE_JKM_AVG: " +VSettle_ICE_JKM_AVG.size());
//	System.out.println(VEff_Perc+" :VEff_Perc: " +VEff_Perc.size()+" : "+VContMthWise_gas_date.size());
////////////////////////////////////////////////////////////////////////////////////	
	///System.out.println(VSlope.size()+"VSlope "+VSlope);
				VLNG_ICE_JKM_EXPOBaseValue.add(LNG_ICE_JKM_EXPOBaseValue);
				String DropOff=""+VLNG_ICE_JKM_EXPODropOff.elementAt(i); //SB20201006
			
				Slope=""+Double.parseDouble(""+VSlope.elementAt(i)); 
				String Const=""+Double.parseDouble(""+VConst_ICE_JKM.elementAt(i));  //SB20210510
				String PriceType=""+VPriceTypeDeal.elementAt(i);
				String GasDate=""+VContMthWise_gas_date.elementAt(i); 
				String PercReduce=""+VEff_Perc.elementAt(i); //SB20201006
				String CumuPercReduce=""+VEff_CumulativePerc.elementAt(i); //SB20201006
				String FinPriceConMth=""+VPriceIndex_SettleDate.elementAt(i); //SB20210708
				//SB20210312	MRCR_ContDtl(HolidayType, Buy_Sell,PriceType,DealPrice, TCQ, Alloc_Qty,GasDate, Gen_Dt, Cont_End_Dt, Phy_CurFrd_Price, Fin_CurFrd_Price, Slope, PriorCurveGasPrice, PercReduce, CumuPercReduce, LNG_ICE_JKM_EXPOBaseValue, DropOff);
			//SB20210708			MRCR_ContDtlNew(HolidayType, Buy_Sell,PriceType,DealPrice, TCQ, Alloc_Qty,GasDate, Gen_Dt, Cont_End_Dt, Phy_CurFrd_Price, Phy_CurFrd_PriceNm, Fin_CurFrd_Price, Fin_CurFrd_PriceNm, Slope, PriorCurveGasPrice, PercReduce, CumuPercReduce, LNG_ICE_JKM_EXPOBaseValue, DropOff, Const, FinPriceConMth);
			//SB20210918			MRCR_ContDtlNew(PriceIndexName, Buy_Sell,PriceType,DealPrice, TCQ, Alloc_Qty,GasDate, Gen_Dt, Cont_End_Dt, Phy_CurFrd_Price, Phy_CurFrd_PriceNm, Fin_CurFrd_Price, Fin_CurFrd_PriceNm, Slope, PriorCurveGasPrice, PercReduce, CumuPercReduce, LNG_ICE_JKM_EXPOBaseValue, DropOff, Const, FinPriceConMth);
				////////////////////////////////////////////////
		   } */
				 VBuySell.add(Buy_Sell);}
		   sup_tot_comitment=nf.format(sup_tot_comit);
		   bal_tot_comitment=nf.format(bal_tot_comit);
		  
		   //////////TIME CALCULATION/////////////////////////////////////////////
			CurrDtTm="";
			queryString = "SELECT SYSDATE FROM DUAL";
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					CurrDtTm = rset.getString(1);System.out.println(VSettle_ICE_JKM_AVG.size()+" :<<<<<<FINAL LISTING COMPLETED >>>>>>>>>>>>>>>>>>>>>>.....: "+CurrDtTm);
				}
			//////////********TIME CALCULATION/////////////////////////////////////////////
		   
	/*SB20210429	   System.out.println(VSettle_ICE_JKM_AVG.size()+"VSettle_ICE_JKM_AVG ");
		   System.out.println(VRLNG_PHYS_INDIA_EXPOSURE_U.size()+"VRLNG_PHYS_INDIA_EXPOSURE_U ");
			System.out.println(VSettle_RLNG_PHYS.size()+"VSettle_RLNG_PHYS ");
			System.out.println(VLNG_ICE_JKM_EXPOSURE_U.size()+"VLNG_ICE_JKM_EXPOSURE_U ");
			System.out.println(VSettle_ICE_JKM.size()+"VSettle_ICE_JKM ");
			System.out.println(VConst_ICE_JKM.size()+"VConst_ICE_JKM ");
			System.out.println(VMTM.size()+"VMTM "+VMTM);
			System.out.println(VRealised.size()+"VRealised "+VRealised);
			System.out.println(VTotalMtmRealised.size()+"VTotalMtmRealised "+VTotalMtmRealised);
			
		   System.out.println(VLNG_ICE_JKM_EXPODropOff+"VLNG_ICE_JKM_EXPODropOff "+VLNG_ICE_JKM_EXPODropOff.size());		   
		 //  System.out.println(VLNG_ICE_JKM_EXPOBaseValue+"VLNG_ICE_JKM_EXPOBaseValue "+VLNG_ICE_JKM_EXPOBaseValue.size());
		   System.out.println("VLNG_ICE_JKM_EXPOBaseValue "+VLNG_ICE_JKM_EXPOBaseValue.size());
		   
		    System.out.println("VMthYrWisePriceICE_JKM "+VMthYrWisePriceICE_JKM.size());
			System.out.println("VPriceTypeDeal "+VPriceTypeDeal.size());
			System.out.println("VPriceType "+VPriceType.size());
			System.out.println("VDealPriceCurve "+VDealPriceCurve.size());
			System.out.println("VDealPhysCurve "+VDealPhysCurve.size());
			System.out.println("VCargoRefNo "+VCargoRefNo.size());
			System.out.println("VDealTCQ "+VDealTCQ.size());
			System.out.println("VRLNG_PHYS_IN_EXPO "+VRLNG_PHYS_IN_EXPO.size());
			System.out.println("VLNG_ICE_JKM_EXPO "+VLNG_ICE_JKM_EXPO.size());
			System.out.println("VRU_Physical_Leg "+VRU_Physical_Leg.size());
			System.out.println("VRLNG_PHYS_INDIA_EXPOSURE_U "+VRLNG_PHYS_INDIA_EXPOSURE_U.size());
			System.out.println("VRU_Financial_Leg "+VRU_Financial_Leg.size());
			System.out.println("VLNG_ICE_JKM_EXPOSURE_U "+VLNG_ICE_JKM_EXPOSURE_U.size());
			System.out.println("VU_Phy_Leg "+VU_Phy_Leg.size());
			System.out.println("VU_Fin_Leg "+VU_Fin_Leg.size());
			System.out.println("VR_Fin_Leg "+VR_Fin_Leg.size());
			System.out.println("VPNL_PHYS_REALISED "+VPNL_PHYS_REALISED.size());
			System.out.println("VPNL_ICE_JKM_REALISED "+VPNL_ICE_JKM_REALISED.size());
			System.out.println("VSettle_ICE_JKM_AVG "+VSettle_ICE_JKM_AVG.size());
			System.out.println("VSettle_ICE_JKM_MTM "+VMTM.size()); 
			System.out.println("VPriceIndex_SettleDate "+VPriceIndex_SettleDate.size());
			System.out.println("VSlope "+VSlope.size());
			System.out.println("VEff_Price: "+VEff_Price.size());*/
		}
		catch(Exception e)
		{
			//System.out.println("Exception in Databean_Contract_MasterReport--->fetchcustomer()-->"+e);
			  e.printStackTrace();	
		}
	}
	
	//*SB20201201//////////////For Price Type ////////////////////
	public void Fetch_ContExpire_LIST()
	{
		try
		{			
			String tmp_from_dt = " ";
			String temp_dt="";
			String tmp_to_dt = " ";			
			double tot_comit=0.0;
			double sup_tot_comit=0.0;
			double bal_tot_comit=0.0;
			//Introduce By Milan 20110916 FOR MMSCM
			double tot_comit_mmscm=0.0;
			double sup_tot_comit_mmscm=0.0;
			double bal_tot_comit_mmscm=0.0;
			Vector FRM_DT=new Vector();
			Vector TO_DT=new Vector();						
			Vector RE_GAS_REV_NO=new Vector();
			Vector LTCORA_REV_NO=new Vector();//ADDED FOR LTCORA AND CN
			Vector LTCORA_MAPPING_ID=new Vector();//ADDED FOR LTCORA AND CN
			Vector CN_REV_NO=new Vector();//ADDED FOR LTCORA AND CN
			Vector CN_MAPPING_ID=new Vector();//ADDED FOR LTCORA AND CN
			Vector MAPPING_ID=new Vector();//MAPP_ID for All
////////////////////SB20210209:STORAGE//////////////////////////////////
	String StorageDate="";
	queryString = "SELECT TO_CHAR(TO_DATE('"+to_date+"','DD/MM/YYYY')+1,'DD/MM/YYYY') FROM DUAL";
	//	System.out.println("Get DAY Name: "+queryString);
		rset = stmt.executeQuery(queryString);
		if(rset.next())
		{
			StorageDate = rset.getString(1)==null?"":rset.getString(1); //SB20201128;
		}

			queryString ="select TO_CHAR(A.START_DT,'DD/MM/YYYY'),TO_CHAR(A.END_DT,'DD/MM/YYYY'),A.SN_NO,A.SN_REV_NO,A.CUSTOMER_CD,A.FGSA_NO,A.FGSA_REV_NO,A.TCQ, " +
	 		"A.SN_REF_NO,to_char(A.SIGNING_DT,'dd/mm/yyyy'),A.RATE,FCC_BY,TO_CHAR(FCC_DATE,'DD/mm/yyyy'),EMP_CD,TO_CHAR(ENT_DT,'dd/mm/yyyy') from FMS7_SN_MST A where "+ 
	 		"(A.SIGNING_DT <= to_date('"+to_date+"','dd/mm/yyyy')"+"AND A.END_DT < to_date('"+to_date+"','dd/mm/yyyy')) AND " +
	 		" A.END_DT >= to_date('01/11/2020','dd/mm/yyyy') AND " +
	 		"A.SN_REV_NO=(SELECT MAX(B.SN_REV_NO) FROM FMS7_SN_MST B where A.SN_NO=B.SN_NO AND A.FGSA_NO=B.FGSA_NO AND " +
	 		"A.CUSTOMER_CD=B.CUSTOMER_CD AND A.FGSA_REV_NO=B.FGSA_REV_NO) order by A.START_DT";			
			//(START_DT <= to_date('"+to_date+"','dd/mm/yyyy')"+"AND END_DT >= to_date('"+from_date+"','dd/mm/yyyy'))
			System.out.println("******Expire List: "+queryString);
			rset = stmt.executeQuery(queryString);
			////System.out.println("FOr SN : "+to_date+" "+from_date);
			while(rset.next())
			{				
				////System.out.println("FOr SN : "+rset.getString(1)+" "+rset.getString(2));
				VSN_NO2.add(rset.getString(3)==null?"0":rset.getString(3));
				VCUSTOMER_CD2.add(rset.getString(5)==null?"0":rset.getString(5));									
				tmp_from_dt = rset.getString(1)==null?"":rset.getString(1);
				tmp_to_dt = rset.getString(2)==null?"":rset.getString(2);
			
				temp_dt = tmp_from_dt+"   -   "+tmp_to_dt;
				VCARGO_ARRIVAL_DATE2.add(temp_dt);				
				VSN_REV_NO2.add(rset.getString(4)==null?"0":rset.getString(4));			  	
			  	VFGSA_NO2.add("S"+rset.getString(6)==null?"0":rset.getString(6));	
			  	VFGSA_REV_NO2.add(rset.getString(7)==null?"0":rset.getString(7));	
			
			  	VSN_REF_NO2.add(rset.getString(9)==null?"":rset.getString(9));	
			  	VSIGNING_DT2.add(rset.getString(10)==null?"0":rset.getString(10));
			  	VRATE2.add(nf2.format(Double.parseDouble(rset.getString(11)==null?"0":rset.getString(11))));
			
			  	VContType2.add("S"); //SB20201108
			
			  	VCont_Start_Dt2.add(rset.getString(1)==null?"":rset.getString(1));
			  	VCont_End_Dt2.add(rset.getString(2)==null?"":rset.getString(2));
			 
			  	VMAPPING_ID2.add("S"+rset.getString(6)+"-"+rset.getString(7)+"-"+rset.getString(3)+"-"+rset.getString(4));
			}	
			System.out.println("VCont_Start_Dt2: "+VCont_Start_Dt2);
//*SB20201108////////////////////////////LOA LIst of Contracts/////////////////////////////////////////////////////////////////
		queryString =   "select TO_CHAR(A.START_DT,'DD/MM/YYYY'),TO_CHAR(A.END_DT,'DD/MM/YYYY'),A.LOA_NO,A.LOA_REV_NO,A.CUSTOMER_CD,A.TENDER_NO,A.TCQ, " +
				"A.LOA_REF_NO,to_char(A.SIGNING_DT,'dd/mm/yyyy'),A.RATE,FCC_BY,TO_CHAR(FCC_DATE,'DD/mm/yyyy'),EMP_CD,TO_CHAR(ENT_DT,'dd/mm/yyyy'), DCQ, ADV_AMT, TRANSPORTATION_CHARGE  from FMS7_LOA_MST A where "+ 
		//SB20201013		"(A.START_DT <= to_date('"+to_date+"','dd/mm/yyyy')"+"AND A.END_DT >= to_date('"+from_date+"','dd/mm/yyyy')) AND " +
				"(A.START_DT <= to_date('"+to_date+"','dd/mm/yyyy')"+"AND A.END_DT < to_date('"+to_date+"','dd/mm/yyyy')) AND " + //SB20201013
				" A.END_DT >= to_date('01/11/2020','dd/mm/yyyy') AND " +
				"A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM FMS7_LOA_MST B where A.LOA_NO=B.LOA_NO AND A.TENDER_NO=B.TENDER_NO AND " +
				"A.CUSTOMER_CD=B.CUSTOMER_CD) order by A.START_DT";
			rset = stmt.executeQuery(queryString);
	//		System.out.println("LOA: "+queryString);
			while(rset.next())
			{				

				VSN_NO2.add(rset.getString(3)==null?"0":rset.getString(3));
				VCUSTOMER_CD2.add(rset.getString(5)==null?"0":rset.getString(5));									
				tmp_from_dt = rset.getString(1)==null?"":rset.getString(1);
				tmp_to_dt = rset.getString(2)==null?"":rset.getString(2);
			
				temp_dt = tmp_from_dt+"   -   "+tmp_to_dt;
				VCARGO_ARRIVAL_DATE2.add(temp_dt);				
				VSN_REV_NO2.add(rset.getString(4)==null?"0":rset.getString(4));			  	
			  	VFGSA_NO2.add("L"+rset.getString(6)==null?"0":rset.getString(6));	
			  	VFGSA_REV_NO2.add("0");	 //No Rev no in LoA
			
			  	tot_comit += Double.parseDouble(rset.getString(7)==null?"0":nf.format(Double.parseDouble(rset.getString(7))));
			  	
			  	tot_comit_mmscm=(tot_comit/38900);//MD20110916
			  	tot_commitment_mmscm=nf.format(tot_comit_mmscm);
			  	
			  	tot_commitment=nf.format(tot_comit);
			  	VSN_REF_NO2.add(rset.getString(8)==null?"0":rset.getString(8));	
			  	VSIGNING_DT2.add(rset.getString(9)==null?"0":rset.getString(9));
			  	VRATE2.add(nf2.format(Double.parseDouble(rset.getString(10)==null?"0":rset.getString(10))));
			
			  	VContType2.add("L"); //SB20201108
			
				VCont_Start_Dt2.add(rset.getString(1)==null?"":rset.getString(1));
			  	VCont_End_Dt2.add(rset.getString(2)==null?"":rset.getString(2));
			  	VMAPPING_ID2.add(rset.getString(6)+"-0"+"-"+rset.getString(3)+"-"+rset.getString(4));
			  
			}
//SB/////This is for BUY Deal////////////////////////////////////////////////////////
			
			queryString = "SELECT TO_CHAR(A.DELV_FROM_DT,'DD/MM/YYYY'), TO_CHAR(A.DELV_TO_DT,'DD/MM/YYYY'),A.MAN_CONF_CD,A.CARGO_SEQ_NO,E.TRD_CD, A.MAN_CD, A.MAN_CD, A.CONFIRM_VOL,"
					+ "A.CARGO_REF_CD,to_char(C.MAN_CONF_DT,'dd/mm/yyyy'), A.PRICE,A.EMP_CD,TO_CHAR(A.ENT_DT,'dd/mm/yyyy'), A.EMP_CD,TO_CHAR(A.ENT_DT,'dd/mm/yyyy'), E.PRICE_TYPE, A.PRICE_TO, A.REMARK  "+
					" FROM FMS7_MAN_CONFIRM_CARGO_DTL A, FMS7_MAN_CONFIRM_MST C, FMS7_MAN_REQ_MST E WHERE "+
			///		" A.DELV_FROM_DT BETWEEN TO_DATE('"+SearchDtFrom+"','DD/MM/YYYY') AND TO_DATE('"+SearchDtTo+"','DD/MM/YYYY') "
					" A.DELV_TO_DT < TO_DATE('"+to_date+"','DD/MM/YYYY') "
					+" AND A.DELV_TO_DT >= to_date('01/11/2020','dd/mm/yyyy') " 
					+ "AND E.MAN_CD=A.MAN_CD AND "+ 
			///		" A.UNIT_CD=C.UNIT_CD AND A.CARGO_STATUS='T' AND CARGO_REF_CD='"+VCAR_REF_NO_temp.elementAt(i)+"' "+ 
					" A.MAN_CONF_CD=C.MAN_CONF_CD AND A.CARGO_STATUS='T' AND CARGO_REF_CD LIKE '%' "+ 
					" ORDER BY A.MAN_CONF_CD, A.CARGO_REF_CD";
			//(START_DT <= to_date('"+to_date+"','dd/mm/yyyy')"+"AND END_DT >= to_date('"+from_date+"','dd/mm/yyyy'))
			System.out.println("CARGO: "+queryString);
			rset = stmt.executeQuery(queryString);
			////System.out.println("FOr SN : "+to_date+" "+from_date);
			while(rset.next())
			{				
				tmp_from_dt = rset.getString(1)==null?"":rset.getString(1);
				tmp_to_dt = rset.getString(2)==null?"":rset.getString(2);
			//	FRM_DT.add(rset.getString(1)==null?"":rset.getString(1));
			//	TO_DT.add(rset.getString(2)==null?"":rset.getString(2));
				temp_dt = tmp_from_dt+"   -   "+tmp_to_dt;
				VCARGO_ARRIVAL_DATE2.add(temp_dt);				
				VSN_NO2.add(rset.getString(3)==null?"0":rset.getString(3));
				VSN_REV_NO2.add(rset.getString(4)==null?"0":rset.getString(4));		
				VCUSTOMER_CD2.add(rset.getString(5)==null?"0":rset.getString(5));																		  	
			  	VFGSA_NO2.add(rset.getString(6)==null?"0":rset.getString(6));	
			 /// 	FGSA_REV_NO.add(rset.getString(7)==null?"0":rset.getString(7));	
			  	VFGSA_REV_NO2.add("0");	 //No Rev no in LoA
			
			  	tot_comit += Double.parseDouble(rset.getString(8)==null?"0":nf.format(Double.parseDouble(rset.getString(8))));
			  	
			  	tot_comit_mmscm=(tot_comit/38900);//MD20110916
			  	tot_commitment_mmscm=nf.format(tot_comit_mmscm);
			  	////System.out.println("BOOKEDMMSCM : "+BOOKMMSCM+" TOTAL : "+tot_comit_mmscm);
			  	
			  	tot_commitment=nf.format(tot_comit);
			  	VSN_REF_NO2.add(rset.getString(9)==null?"":rset.getString(9));	
			  	VSIGNING_DT2.add(rset.getString(10)==null?"0":rset.getString(10));
			  	VRATE2.add(nf2.format(Double.parseDouble(rset.getString(11)==null?"0":rset.getString(11))));
		
			  	VContType2.add("B"); //SB20201108

			  	VCont_Start_Dt2.add(rset.getString(1)==null?"":rset.getString(1));
			  	VCont_End_Dt2.add(rset.getString(2)==null?"":rset.getString(2));
			
			  	VMAPPING_ID2.add("B"+rset.getString(3)+"-0-"+rset.getString(6)+"-"+rset.getString(7));
			 //as stored into FMS9_MRCR_CONT_PRICE_DTL  	String Mapp_Id=CUSTOMER_CD.elementAt(i)+"-"+SN_NO.elementAt(i)+"-"+SN_REV_NO.elementAt(i)+"-"+SN_REF_NO.elementAt(i)+"-0";
			}	

		//}
		///////////////////////////^^^^STORAGE//////////////////////////////////////////////
			System.out.println(VCUSTOMER_CD2.size()+" VCUSTOMER_CD2: "+VCUSTOMER_CD2);
			System.out.println(VContType2.size()+" CUSTOMER_CD2: "+VContType2);
			for(int i=0;i<VCUSTOMER_CD2.size();i++)
			{
				if(!VContType2.elementAt(i).equals("B"))
				{
				queryString ="select CUSTOMER_ABBR,CUSTOMER_NAME,CUSTOMER_CD from FMS7_CUSTOMER_MST where CUSTOMER_CD="+VCUSTOMER_CD2.elementAt(i)+" "
						+ "and eff_dt=(select max(eff_dt) from fms7_customer_mst where customer_cd='"+VCUSTOMER_CD2.elementAt(i)+"' and eff_dt<=to_date('"+VCont_Start_Dt2.elementAt(i)+"','dd/mm/yyyy'))";
				}
				else
				{
					queryString ="select TRADER_ABBR,TRADER_NAME,TRADER_CD from FMS7_TRADER_MST B where TRADER_CD="+VCUSTOMER_CD2.elementAt(i)+" "
							+ "and eff_dt=(select max(eff_dt) from FMS7_TRADER_MST where TRADER_CD='"+VCUSTOMER_CD2.elementAt(i)+"' and eff_dt<=to_date('"+VCont_Start_Dt2.elementAt(i)+"','dd/mm/yyyy'))";	
				}		
				rset = stmt.executeQuery(queryString);
				System.out.println(queryString);
				if(rset.next())
				{
					VCUSTOMER_NM2.add(rset.getString(2)==null?"":rset.getString(2));
					VCUSTOMER_ABBR2.add(rset.getString(1)==null?"":rset.getString(1));	
					System.out.println(VMAPPING_ID2.elementAt(i)+" :VCUSTOMER_NM2: "+rset.getString(2));
				}
				else
				{
					if(VCUSTOMER_CD2.elementAt(i).equals("0")) 
						VCUSTOMER_NM2.add("STORAGE");
					else
						VCUSTOMER_NM2.add("");
					VCUSTOMER_ABBR2.add("");
				}	
				

					
				//////////////SB20200817////////////////////////
				String TCQ="0"; String Alloc_Qty="0"; String Gen_Dt=to_date; String Cont_End_Dt="";
			///	TCQ=""+BOOKMMBTU.elementAt(i); Cont_End_Dt=""+TO_DT.elementAt(i);
			///	Alloc_Qty=""+SUPPLIEDMBTU.elementAt(i); Cont_End_Dt=""+TO_DT.elementAt(i);
				
				String Mapp_Id=VCUSTOMER_CD2.elementAt(i)+"-"+VFGSA_NO2.elementAt(i)+"-"+VFGSA_REV_NO2.elementAt(i)+"-"+VSN_NO2.elementAt(i)+"-"+VSN_REV_NO2.elementAt(i);
				if(VContType2.elementAt(i).equals("B"))
					Mapp_Id=VCUSTOMER_CD2.elementAt(i)+"-"+VSN_NO2.elementAt(i)+"-"+VSN_REV_NO2.elementAt(i)+"-"+VSN_REF_NO2.elementAt(i)+"-0";
				String Phy_CurFrd_Price="0"; String Fin_CurFrd_Price="0";
				String Buy_Sell="SELL";
				if(VCUSTOMER_ABBR2.elementAt(i).equals("SEIPL"))
					Buy_Sell="BUY";
				else if(VContType2.elementAt(i).equals("B"))
					Buy_Sell="BUY";
				else if(VContType2.elementAt(i).equals("O")) //SB20210209
					Buy_Sell="BUY";
				
				String PriceType="";
				queryString ="SELECT DISTINCT PRICE_TYPE FROM FMS9_MRCR_CONT_PRICE_DTL "+
						" where MAPPING_ID='"+Mapp_Id+"' AND CONTRACT_TYPE='"+VContType2.elementAt(i)+"' AND FLAG='Y' ";
				rset = stmt.executeQuery(queryString);
				System.out.println(">>>>>>> "+queryString);
				while(rset.next())
				{
					PriceType=PriceType+rset.getString(1);
				}
				
				System.out.println(PriceType+" NewSalePrice2>>>>>>> ");
				if(PriceType.length()>1)
					PriceType="M";
			
				if(PriceType.equals(""))
					PriceType="F";
		////////Index type Pulling from Table////////////////////////////
				String INDEX_TYPE=""; String HolidayType="";
				PricePHYSName=""; //SB20210311
				//SB20210311			queryString ="SELECT PRICE_TYPE, CURVE_NM, RATE, SEQ_NO FROM FMS9_MRCR_CONT_PRICE_DTL "
				queryString ="SELECT PRICE_TYPE, CURVE_NM, RATE, SEQ_NO, PHYS_CURVE_NM FROM FMS9_MRCR_CONT_PRICE_DTL " //SB20210311
						+ "WHERE MAPPING_ID='"+Mapp_Id+"' AND CONTRACT_TYPE='"+VContType2.elementAt(i)+"' AND FLAG='Y' ";
				System.out.println("FMS9_CURVE_PRICE_DTL: "+queryString);
				rset2 = stmt.executeQuery(queryString);			
				if(rset2.next())
				{
					INDEX_TYPE=rset2.getString(2)==null?"":rset2.getString(2);
					PricePHYSName=rset2.getString(5)==null?"":rset2.getString(5);  //SB20210310	 //SB20210311
								/*if(INDEX_TYPE.equals("LNG_ICE_JKM"))
									HolidayType="J";
								else if(INDEX_TYPE.equals("CR_DATED_BRENT")) //SB20201025
									HolidayType="B";
								else
									HolidayType="J";*/
				}
			////SB20210311: New Logic ////////////SB20210228: For Buy Deal It should be LNG_PHYS_INDIA else RLNG_PHYS_INDIA as per Shiladita 25th Feb 2021////////////////////////////////////////////////////////
				if(PricePHYSName.equals(""))  //SB20210310	
				{
					 if(Buy_Sell.equalsIgnoreCase("BUY"))
						 PricePHYSName="LNG_PHYS_INDIA";
					 else
						 PricePHYSName="RLNG_PHYS_INDIA";
				}
	////////////////^SB20210228////////////////////////////////////////////////////////
		/*///////////////////////////////////////////////////////////////////////
				String PriorCurveGasPrice="";
			//	if(Double.parseDouble(""+VMthYrWisePriceICE_JKM.elementAt(i))>0)
					PriorCurveGasPrice="0";
				String DealPrice="";
				DealPrice=""+VRATE2.elementAt(i);
				String Slope="1.000";
				String GasDate=""+VCont_Start_Dt2.elementAt(i);
				MRCR_ContDtl2(HolidayType, Buy_Sell,PriceType, DealPrice, TCQ, Alloc_Qty, GasDate, Gen_Dt, Cont_End_Dt, Phy_CurFrd_Price, Fin_CurFrd_Price, Slope, PriorCurveGasPrice);
				*//////////////////SB20210201//////////////////////////////
				String Mapp_IdCommon=VCUSTOMER_CD2.elementAt(i)+"-"+VFGSA_NO2.elementAt(i)+"-"+VFGSA_REV_NO2.elementAt(i)+"-"+VSN_NO2.elementAt(i)+"-"+VSN_REV_NO2.elementAt(i);
				String DealNo=""+VContType2.elementAt(i)+Mapp_IdCommon; String Report_Dt=to_date; String CustCode=""+VCUSTOMER_CD2.elementAt(i);//SB20210201
				Report_Dt=""+VCont_End_Dt2.elementAt(i);
				MarketExposureDealWiseReportView2(DealNo, Report_Dt, CustCode);  //SB20210201
				//////////////////^^^SB20210201//////////////////////////////
				////////////////////////////////Get Price Type from Existing system //////////////////////
			VBuySell2.add(Buy_Sell);
			if(PriceType.equals("M"))
			{
				VPriceType2.add("FLOAT");
				if(INDEX_TYPE.equals("CR_DATED_BRENT")) //SB20210407
					VDealPriceCurve2.add("ICE_BRENT"); //SB20210407
				else
					VDealPriceCurve2.add(INDEX_TYPE); //SB20210401
				/*SB20210401 if(HolidayType.equals("J"))
					VDealPriceCurve2.add("LNG_ICE_JKM");
				else
				//SB20201117	VDealPriceCurve.add("CR_DATED_BRENT");
					VDealPriceCurve2.add("ICE_BRENT"); //SB20201117
*/				/*if(Buy_Sell.equals("BUY"))
					VDealPhysCurve2.add("LNG_PHYS_INDIA"); //SB20210227
				else
					VDealPhysCurve2.add("RLNG_PHYS_INDIA"); //SB20210227
*/			}
			else if(PriceType.equals("F"))
			{
				VPriceType2.add("FIXED");
				//SB20210401	VDealPriceCurve2.add("RLNG_PHYS_INDIA");
				VDealPriceCurve2.add("-");
				/*if(Buy_Sell.equals("BUY"))
					VDealPhysCurve2.add("LNG_PHYS_INDIA"); //SB20210227
				else
					VDealPhysCurve2.add("RLNG_PHYS_INDIA"); //SB20210227
*/			}
			else
			{
			//SB20201124	VPriceType.add("FIXED");
				VPriceType2.add(PriceType);
				//SB20210401	VDealPriceCurve2.add("RLNG_PHYS_INDIA");
				VDealPriceCurve2.add("*");
				/*if(Buy_Sell.equals("BUY"))
					VDealPhysCurve2.add("LNG_PHYS_INDIA"); //SB20210227
				else
					VDealPhysCurve2.add("RLNG_PHYS_INDIA"); //SB20210227
*/			}
			VDealPhysCurve2.add(PricePHYSName); //SB20210311
			//////////////////////////////////////////////////////////////////////////////////////////
		   }
			System.out.println(VCUSTOMER_NM2.size()+" VCUSTOMER_NM2: "+VCUSTOMER_NM2);	
			System.out.println(VCUSTOMER_ABBR2.size()+" VCUSTOMER_ABBR2: "+VCUSTOMER_ABBR2);	
			//////////////////SB20210201//////////////////////////////
			String Report_Dt=to_date; //SB20210201
		///SB20210224	MarketExposureStorage(Report_Dt);  //SB20210201
			//////////////////^^^SB20210201//////////////////////////////
		  /* sup_tot_comitment=nf.format(sup_tot_comit);
		   bal_tot_comitment=nf.format(bal_tot_comit);
		   
		   //Introduce by Milan 20010916
		   sup_tot_comitment_mmscm=nf.format(sup_tot_comit_mmscm);
		   bal_tot_comitment_mmscm=nf.format(bal_tot_comit_mmscm);
			
		    //--------Logic for Tender and LOA based Summary Contract based Agreement	
			tot_comit=0.0;
			bal_tot_comit=0.0;
			sup_tot_comit=0.0;
			
			tot_comit_mmscm=0.0;
			sup_tot_comit_mmscm=0.0;
			bal_tot_comit_mmscm=0.0;
			FRM_DT.clear();
			TO_DT.clear();*/
			System.out.println(VContType2+" ******VContType2:******** "+VContType2.size());
		}		
		catch(Exception e)
		{
			  //System.out.println("Exception in Databean_Contract_MasterReport--->fetchEligibleCargoList()-->"+e);
			  e.printStackTrace();
		}
	}
////////////////////NEW Fn for Deal Exposure Dtl/////////
public void fetchDealExposureDtlExpired()
	{
	try
	{
	String tmp_from_dt = " ";
	String temp_dt="";
	String tmp_to_dt = " ";			
	double tot_comit=0.0;
	double sup_tot_comit=0.0;
	double bal_tot_comit=0.0;
	//Introduce By Milan 20110916 FOR MMSCM
	double tot_comit_mmscm=0.0;
	double sup_tot_comit_mmscm=0.0;
	double bal_tot_comit_mmscm=0.0;
	Vector FRM_DT=new Vector();
	Vector TO_DT=new Vector();	
	String Buy_Sell="Sell";
	Vector RE_GAS_REV_NO=new Vector();
	Vector LTCORA_REV_NO=new Vector();//ADDED FOR LTCORA AND CN
	Vector LTCORA_MAPPING_ID=new Vector();//ADDED FOR LTCORA AND CN
	Vector CN_REV_NO=new Vector();//ADDED FOR LTCORA AND CN
	Vector CN_MAPPING_ID=new Vector();//ADDED FOR LTCORA AND CN
	/////////////////////////////////Count No of Mth/Days of Contract/////////////////////////////////////
	String ContMthWise_tmp_from_dt = " ";
	String ContMthWise_temp_dt="";
	Vector VContMthWise_FRM_DT=new Vector();
	Vector VContMthWise_TO_DT=new Vector();	
	Vector VContMthWise_gas_date=new Vector();	
	String Cont_Rate="";
	String CargoRefCd=""; //SB20201127
	////////////////////SB20210209:STORAGE//////////////////////////////////
	String StorageDate="";
	queryString = "SELECT TO_CHAR(TO_DATE('"+to_date+"','DD/MM/YYYY')+1,'DD/MM/YYYY') FROM DUAL";
	//	System.out.println("Get DAY Name: "+queryString);
	rset = stmt.executeQuery(queryString);
	if(rset.next())
	{
		StorageDate = rset.getString(1)==null?"":rset.getString(1); //SB20201128;
	}
	////////////////////^^^SB20210209:STORAGE/////////////////////////////////////////////	
	System.out.println("Cont_Type: "+Cont_Type);
	if(Cont_Type.equals("S"))  //For SN
	{
	queryString ="select TO_CHAR(A.START_DT,'DD/MM/YYYY'),TO_CHAR(A.END_DT,'DD/MM/YYYY'),A.SN_NO,A.SN_REV_NO,A.CUSTOMER_CD,A.FGSA_NO,A.FGSA_REV_NO,A.TCQ, " +
			"A.SN_REF_NO,to_char(A.SIGNING_DT,'dd/mm/yyyy'),A.RATE,FCC_BY,TO_CHAR(FCC_DATE,'DD/mm/yyyy'),EMP_CD,TO_CHAR(ENT_DT,'dd/mm/yyyy') from FMS7_SN_MST A where "+ 
			"A.CUSTOMER_CD='"+DealCustCd+"' AND A.FGSA_NO='"+FgsaNo+"' AND A.FGSA_REV_NO='"+FgsaRevNo+"' AND A.SN_NO='"+SnNo+"' AND A.SN_REV_NO='"+SnRevNo+"' "+
	//SB20200910		"AND (A.START_DT <= to_date('"+to_date+"','dd/mm/yyyy')"+"AND A.END_DT >= to_date('"+from_date+"','dd/mm/yyyy')) AND " +
	//SB20200921 		"AND A.END_DT >= to_date('"+from_date+"','dd/mm/yyyy') AND " +
			"AND A.END_DT < to_date('"+to_date+"','dd/mm/yyyy') AND " +
			"A.SN_REV_NO=(SELECT MAX(B.SN_REV_NO) FROM FMS7_SN_MST B where A.SN_NO=B.SN_NO AND A.FGSA_NO=B.FGSA_NO AND " +
			"A.CUSTOMER_CD=B.CUSTOMER_CD AND A.FGSA_REV_NO=B.FGSA_REV_NO) order by A.START_DT";
	//System.out.println("DEAL-DTL: FMS7_SN_MST: "+queryString);
		rset = stmt.executeQuery(queryString);
		System.out.println("FOr SN : "+to_date+" "+from_date);
		while(rset.next())
		{									
			ContMthWise_tmp_from_dt = rset.getString(1)==null?"01/01/2000":rset.getString(1);
			ContMthWise_temp_dt = rset.getString(2)==null?"01/01/2020":rset.getString(2);
			VContMthWise_FRM_DT.add(rset.getString(1)==null?"01/01/2020":rset.getString(1));
			VContMthWise_TO_DT.add(rset.getString(2)==null?"01/01/2020":rset.getString(2));
									
			queryString ="select CUSTOMER_ABBR,CUSTOMER_NAME,CUSTOMER_CD from FMS7_CUSTOMER_MST where CUSTOMER_CD="+rset.getString(5)+" "
					+ "and eff_dt=(select max(eff_dt) from fms7_customer_mst where customer_cd='"+rset.getString(5)+"' and eff_dt<=to_date('"+rset.getString(1)+"','dd/mm/yyyy'))";
			rset2 = stmt2.executeQuery(queryString);
			////System.out.println(queryString);
			if(rset2.next())
			{
				CUSTOMER_NM.add(rset2.getString(2)==null?"":rset2.getString(2));
				CUSTOMER_ABBR.add(rset2.getString(1)==null?"":rset2.getString(1));	
				Deal_Cust_AbbrNm=rset2.getString(1)+"-"+rset2.getString(2); //SB20210129
			}
			else
			{
				CUSTOMER_NM.add("");
				CUSTOMER_ABBR.add("");
				Deal_Cust_AbbrNm=""; //SB20210129
			}		
			if(rset2.getString(1).equals("SEIPL"))
			{
				Buy_Sell="Buy";
			}
			Deal_Dt=rset.getString(10)==null?"":rset.getString(10);
			Cont_Rate=rset.getString(11)==null?"0":rset.getString(11);
			String TEMP_apr_by=rset.getString(12)==null?"0":rset.getString(12);
		  	sn_apr_dt.add(rset.getString(13)==null?"-":rset.getString(13));
		  	String temp_ent_by=rset.getString(14)==null?"0":rset.getString(14);
		  	sn_ent_dt.add(rset.getString(15)==null?"-":rset.getString(15));
		  	Deal_Start_Dt=rset.getString(1)==null?"":rset.getString(1); //SB20210129
		  	Deal_End_Dt=rset.getString(2)==null?"":rset.getString(2); //SB20210129
		  	Deal_Ref_No=rset.getString(9)==null?"":rset.getString(9); //SB20210129
		  	Deal_Entered_By=rset.getString(14)==null?"":rset.getString(14); //SB20210129
		  	Deal_Entered_Dt=rset.getString(15)==null?"":rset.getString(15); //SB20210129
		  	
		  	
		  	String queryString_nm ="SELECT EMP_NM FROM HR_EMP_MST WHERE EMP_CD='"+TEMP_apr_by+"'";
		  	rset1=stmt1.executeQuery(queryString_nm);
		  	if(rset1.next()){
		  		sn_apr_by.add(rset1.getString(1)==null?"-":rset1.getString(1));
		  	}else{
		  		sn_apr_by.add("-");
		  	}
		  	
		  	queryString_nm ="SELECT EMP_NM FROM HR_EMP_MST WHERE EMP_CD='"+temp_ent_by+"'";
		  	rset1=stmt1.executeQuery(queryString_nm);
		  	if(rset1.next()){
		  		sn_ent_by.add(rset1.getString(1)==null?"-":rset1.getString(1));
		  	}else{
		  		sn_ent_by.add("-");
		  	}
		}
	
	}
	else if(Cont_Type.equals("L")) ///For LoA
	{
	/////////////////////////////LOA LIst of Contracts/////////////////////////////////////////////////////////////////
	queryString =   "select TO_CHAR(A.START_DT,'DD/MM/YYYY'),TO_CHAR(A.END_DT,'DD/MM/YYYY'),A.LOA_NO,A.LOA_REV_NO,A.CUSTOMER_CD,A.TENDER_NO,A.TCQ, " +
		"A.LOA_REF_NO,to_char(A.SIGNING_DT,'dd/mm/yyyy'),A.RATE,FCC_BY,TO_CHAR(FCC_DATE,'DD/mm/yyyy'),EMP_CD,TO_CHAR(ENT_DT,'dd/mm/yyyy'), DCQ, ADV_AMT, TRANSPORTATION_CHARGE  from FMS7_LOA_MST A where "+ 
		"A.CUSTOMER_CD='"+DealCustCd+"' AND A.TENDER_NO='"+FgsaNo+"' AND A.LOA_NO='"+SnNo+"' AND A.LOA_REV_NO='"+SnRevNo+"' AND "+
		//SB20201013		"(A.START_DT <= to_date('"+to_date+"','dd/mm/yyyy')"+"AND A.END_DT >= to_date('"+from_date+"','dd/mm/yyyy')) AND " +
		"(A.START_DT <= to_date('"+to_date+"','dd/mm/yyyy')"+"AND A.END_DT < to_date('"+to_date+"','dd/mm/yyyy')) AND " + //SB20201013
		//SB20200919			"(A.SIGNING_DT <= to_date('"+to_date+"','dd/mm/yyyy')"+"AND A.END_DT >= to_date('"+from_date+"','dd/mm/yyyy')) AND " +
		"A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM FMS7_LOA_MST B where A.LOA_NO=B.LOA_NO AND A.TENDER_NO=B.TENDER_NO AND " +
		"A.CUSTOMER_CD=B.CUSTOMER_CD) order by A.START_DT";
	rset = stmt.executeQuery(queryString);
		System.out.println("LOA: "+queryString);
	while(rset.next())
	{	
		ContMthWise_tmp_from_dt = rset.getString(1)==null?"01/01/2000":rset.getString(1);
		ContMthWise_temp_dt = rset.getString(2)==null?"01/01/2020":rset.getString(2);
		VContMthWise_FRM_DT.add(rset.getString(1)==null?"01/01/2020":rset.getString(1));
		VContMthWise_TO_DT.add(rset.getString(2)==null?"01/01/2020":rset.getString(2));
								
		queryString ="select CUSTOMER_ABBR,CUSTOMER_NAME,CUSTOMER_CD from FMS7_CUSTOMER_MST where CUSTOMER_CD="+rset.getString(5)+" "
				+ "and eff_dt=(select max(eff_dt) from fms7_customer_mst where customer_cd='"+rset.getString(5)+"' and eff_dt<=to_date('"+rset.getString(1)+"','dd/mm/yyyy'))";
		rset2 = stmt2.executeQuery(queryString);
		////System.out.println(queryString);
		if(rset2.next())
		{
			CUSTOMER_NM.add(rset2.getString(2)==null?"":rset2.getString(2));
			CUSTOMER_ABBR.add(rset2.getString(1)==null?"":rset2.getString(1));
			Deal_Cust_AbbrNm=rset2.getString(1)+"-"+rset2.getString(2); //SB20210129
		}
		else
		{
			CUSTOMER_NM.add("");
			CUSTOMER_ABBR.add("");
			Deal_Cust_AbbrNm=""; //SB20210129
		}		
		if(rset2.getString(1).equals("SEIPL"))
		{
			Buy_Sell="Buy";
		}
		Deal_Dt=rset.getString(9)==null?"":rset.getString(9);
		Cont_Rate=rset.getString(10)==null?"0":rset.getString(10);
		String TEMP_apr_by=rset.getString(11)==null?"0":rset.getString(11);
	  	sn_apr_dt.add(rset.getString(12)==null?"-":rset.getString(12));
	  	String temp_ent_by=rset.getString(13)==null?"0":rset.getString(13);
	  	sn_ent_dt.add(rset.getString(14)==null?"-":rset.getString(14));
	  	Deal_Start_Dt=rset.getString(1)==null?"":rset.getString(1); //SB20210129
	  	Deal_End_Dt=rset.getString(2)==null?"":rset.getString(2); //SB20210129
	  	Deal_Entered_Dt=rset.getString(14)==null?"":rset.getString(14); //SB20210129
	  	Deal_Ref_No=rset.getString(8)==null?"":rset.getString(8); //SB20210129
	  	Deal_Entered_By=rset.getString(13)==null?"":rset.getString(13); //SB20210129
	  	Deal_Entered_Dt=rset.getString(14)==null?"":rset.getString(14); //SB20210129
	  	
	  	String queryString_nm ="SELECT EMP_NM FROM HR_EMP_MST WHERE EMP_CD='"+TEMP_apr_by+"'";
	  	rset1=stmt1.executeQuery(queryString_nm);
	  	if(rset1.next()){
	  		sn_apr_by.add(rset1.getString(1)==null?"-":rset1.getString(1));
	  	}else{
	  		sn_apr_by.add("-");
	  	}
	  	
	  	queryString_nm ="SELECT EMP_NM FROM HR_EMP_MST WHERE EMP_CD='"+temp_ent_by+"'";
	  	rset1=stmt1.executeQuery(queryString_nm);
	  	if(rset1.next()){
	  		sn_ent_by.add(rset1.getString(1)==null?"-":rset1.getString(1));
	  	}else{
	  		sn_ent_by.add("-");
	  	}	  	
	}
	}	
	else if(Cont_Type.equals("B"))
	{
	
	queryString = "SELECT TO_CHAR(A.DELV_FROM_DT,'DD/MM/YYYY'), TO_CHAR(A.DELV_TO_DT,'DD/MM/YYYY'),A.MAN_CONF_CD,A.CARGO_SEQ_NO,E.TRD_CD, A.MAN_CD, A.MAN_CD, A.CONFIRM_VOL,"
			+ "A.CARGO_REF_CD,to_char(C.MAN_CONF_DT,'dd/mm/yyyy'), A.PRICE,A.EMP_CD,TO_CHAR(A.ENT_DT,'dd/mm/yyyy'), A.EMP_CD,TO_CHAR(A.ENT_DT,'dd/mm/yyyy'), E.PRICE_TYPE, A.PRICE_TO, A.REMARK  "+
			" FROM FMS7_MAN_CONFIRM_CARGO_DTL A, FMS7_MAN_CONFIRM_MST C, FMS7_MAN_REQ_MST E WHERE "+
			"E.TRD_CD='"+DealCustCd+"' AND A.MAN_CONF_CD='"+SnNo+"' AND A.MAN_CD='"+FgsaNo+"' AND A.CARGO_SEQ_NO='"+SnRevNo+"' AND "+
			///		" A.DELV_FROM_DT BETWEEN TO_DATE('"+SearchDtFrom+"','DD/MM/YYYY') AND TO_DATE('"+SearchDtTo+"','DD/MM/YYYY') "
			" A.DELV_TO_DT < TO_DATE('"+to_date+"','DD/MM/YYYY') "
			+ "AND E.MAN_CD=A.MAN_CD AND "+ 
	///		" A.UNIT_CD=C.UNIT_CD AND A.CARGO_STATUS='T' AND CARGO_REF_CD='"+VCAR_REF_NO_temp.elementAt(i)+"' "+ 
			" A.MAN_CONF_CD=C.MAN_CONF_CD AND A.CARGO_STATUS='T' AND CARGO_REF_CD LIKE '%' "+ 
			" ORDER BY A.MAN_CONF_CD, A.CARGO_REF_CD";
	///	queryString = "SELECT * FROM FMS7_CARGO_QQ_DTL WHERE CARGO_REF_NO=20032 ";
	//(START_DT <= to_date('"+to_date+"','dd/mm/yyyy')"+"AND END_DT >= to_date('"+from_date+"','dd/mm/yyyy'))
	System.out.println("1. CARGO: "+queryString);
	rset = stmt.executeQuery(queryString);
	////System.out.println("FOr SN : "+to_date+" "+from_date);
	while(rset.next())
	{				
	//SB20201126	ContMthWise_tmp_from_dt = rset.getString(1)==null?"01/01/2000":rset.getString(1);
		ContMthWise_tmp_from_dt = rset.getString(2)==null?"01/01/2000":rset.getString(2); //SB20201126
		ContMthWise_temp_dt = rset.getString(2)==null?"01/01/2020":rset.getString(2);
		VContMthWise_FRM_DT.add(rset.getString(1)==null?"01/01/2020":rset.getString(1));
		VContMthWise_TO_DT.add(rset.getString(2)==null?"01/01/2020":rset.getString(2));
								
		queryString ="select TRADER_ABBR,TRADER_NAME,TRADER_CD from FMS7_TRADER_MST B where TRADER_CD="+rset.getString(5)+" "
				+ "and eff_dt=(select max(eff_dt) from FMS7_TRADER_MST where TRADER_CD='"+rset.getString(5)+"' and eff_dt<=to_date('"+ContMthWise_tmp_from_dt+"','dd/mm/yyyy'))";	
		rset2 = stmt2.executeQuery(queryString);
		////System.out.println(queryString);
		if(rset2.next())
		{
			CUSTOMER_NM.add(rset2.getString(2)==null?"":rset2.getString(2));
			CUSTOMER_ABBR.add(rset2.getString(1)==null?"":rset2.getString(1));	
			Deal_Cust_AbbrNm=rset2.getString(1)+"-"+rset2.getString(2); //SB20210129
		}
		else
		{
			CUSTOMER_NM.add("");
			CUSTOMER_ABBR.add("");
			Deal_Cust_AbbrNm=""; //SB20210129
		}		
	//	if(rset2.getString(1).equals("SEIPL"))
		{
			Buy_Sell="Buy";
		}
		CargoRefCd=rset.getString(9)==null?"0":rset.getString(9); //SB20201127
		Cont_Rate=rset.getString(11)==null?"0":rset.getString(11);
		String TEMP_apr_by=rset.getString(12)==null?"0":rset.getString(12);
	  	sn_apr_dt.add(rset.getString(13)==null?"-":rset.getString(13));
	  	String temp_ent_by=rset.getString(14)==null?"0":rset.getString(14);
	  	sn_ent_dt.add(rset.getString(15)==null?"-":rset.getString(15));
	  	Deal_Start_Dt=rset.getString(1)==null?"":rset.getString(1); //SB20210129
	  	Deal_End_Dt=rset.getString(2)==null?"":rset.getString(2); //SB20210129
	  	Deal_Entered_Dt=rset.getString(15)==null?"":rset.getString(15); //SB20210129
	  	Deal_Ref_No=rset.getString(9)==null?"":rset.getString(9); //SB20210129
	  	Deal_Entered_By=rset.getString(14)==null?"":rset.getString(14); //SB20210129
	  	Deal_Entered_Dt=rset.getString(15)==null?"":rset.getString(15); //SB20210129
	  	
	  /*SB	
	  	String query="SELECT TO_CHAR(MAN_CONF_DT,'DD/MM/YYYY') FROM FMS7_MAN_CONFIRM_MST WHERE MAN_CD='"+rset.getString(4)+"'";
	  	rset1=stmt1.executeQuery(query);
	  	if(rset1.next()){
	  		SIGNING_DT.add(rset1.getString(1)==null?"":rset1.getString(1));
	  		Deal_Dt=rset1.getString(1)==null?"":rset1.getString(1);  //SB20201126: NR
	  	}else{
	  		SIGNING_DT.add("");
	  	}
	  
	  	*/
	  	Deal_Dt=rset.getString(10)==null?"":rset.getString(10);  //SB20201126:
	  	VContType.add("B"); //SB20201108
	  	
	  	
	  	String queryString_nm ="SELECT EMP_NM FROM HR_EMP_MST WHERE EMP_CD='"+TEMP_apr_by+"'";
	  	rset1=stmt1.executeQuery(queryString_nm);
	  	if(rset1.next()){
	  		sn_apr_by.add(rset1.getString(1)==null?"-":rset1.getString(1));
	  	}else{
	  		sn_apr_by.add("-");
	  	}
	  	VCont_Start_Dt.add(rset.getString(1)==null?"":rset.getString(1));
	  	VCont_End_Dt.add(rset.getString(2)==null?"":rset.getString(2));
	  	
	  	queryString_nm ="SELECT EMP_NM FROM HR_EMP_MST WHERE EMP_CD='"+temp_ent_by+"'";
	  	rset1=stmt1.executeQuery(queryString_nm);
	  	if(rset1.next()){
	  		sn_ent_by.add(rset1.getString(1)==null?"-":rset1.getString(1));
	  	}else{
	  		sn_ent_by.add("-");
	  	}
	 // 	MAPPING_ID.add("C"+rset.getString(6)+"-"+rset.getString(7)+"-"+rset.getString(3)+"-"+rset.getString(4));
	 //// 	MAPPING_ID.add("B"+rset.getString(3)+"-0-"+rset.getString(6)+"-"+rset.getString(7));
	}	
	}
	/////////////////////////////////////////////////////////////////
	else if(Cont_Type.equals("O"))
	{		
		ContMthWise_tmp_from_dt = StorageDate;
		ContMthWise_temp_dt = StorageDate;						
		CUSTOMER_NM.add("STORAGE");
		CUSTOMER_ABBR.add("SEIPL");
		Deal_Cust_AbbrNm="SEIPL-STORAGE"; //SB20210129	
		Buy_Sell="Buy";
		Cont_Rate="0";
	  	VContType.add("O"); //SB20201108
	  	sn_apr_by.add("-");
	  
	  	VCont_Start_Dt.add(StorageDate);
	  	VCont_End_Dt.add(StorageDate);
	  	sn_ent_by.add("-");
		Deal_Dt=StorageDate;
	  	Deal_Start_Dt=StorageDate; //SB20210129
	  	Deal_End_Dt=StorageDate; //SB20210129
	  	Deal_Ref_No=""; //SB20210129
	  	Deal_Entered_By="0"; //SB20210129
	  	Deal_Entered_Dt=StorageDate;
	}
	String Mapp_Id=DealCustCd+"-"+FgsaNo+"-"+FgsaRevNo+"-"+SnNo+"-"+SnRevNo;
	if(Cont_Type.equals("B")) //SB20201127
	Mapp_Id=DealCustCd+"-"+SnNo+"-"+SnRevNo+"-"+CargoRefCd+"-0"; //SB20201127
	double count=0;
	//SB		if(ContMthWise_temp_dt.equals("")) {ContMthWise_temp_dt="01/02/2020"; ContMthWise_tmp_from_dt="01/02/2020";}
	
	String query="select to_date('"+ContMthWise_temp_dt+"','dd/mm/yyyy')- to_date('"+ContMthWise_tmp_from_dt+"','dd/mm/yyyy')+1 from dual";
	System.out.println("DEAL-DTL: FMS7_SN_MST: "+query);
	rset=stmt.executeQuery(query);
	if(rset.next())
	{
	count=rset.getDouble(1);
	}
	//////SB20201119////////This is to restrict number of delivery date////////////////
	int CountMaxDays=500;
	if(count>CountMaxDays)
	{
	queryString = "SELECT TO_CHAR(TO_DATE('"+ContMthWise_temp_dt+"','DD/MM/YYYY')-"+CountMaxDays+",'DD/MM/YYYY') FROM DUAL";
	//	System.out.println("Get DAY Name: "+queryString);
	rset = stmt.executeQuery(queryString);
	if(rset.next())
	{
		ContMthWise_tmp_from_dt = rset.getString(1);
	}
	
	query="select to_date('"+ContMthWise_temp_dt+"','dd/mm/yyyy')- to_date('"+ContMthWise_tmp_from_dt+"','dd/mm/yyyy') from dual";
	//	System.out.println("DEAL-DTL: FMS7_SN_MST: "+query);
	rset=stmt.executeQuery(query);
	if(rset.next())
	{
		count=rset.getDouble(1);
	}
	}
	/////////////////^^^^^^SB20201119//////////////////////////////////////////////////
	System.out.println(ContMthWise_tmp_from_dt+" :ContMthWise_tmp_From_Dt: "+count);
	for(int i=0;i<count;i++)
	{
	String query1="select to_char(to_date('"+ContMthWise_tmp_from_dt+"','dd/mm/yyyy')+ "+i+",'dd/mm/yyyy') from dual";
	rset=stmt.executeQuery(query1);
	if(rset.next())
	{
		VContMthWise_gas_date.add(rset.getString(1));
	}	
	}	
	System.out.println(Mapp_Id+" :Mapp_Id & COUNT of +VContMthWise_gas_date: "+count);
	//System.out.println(VContMthWise_gas_date.size()+" :No. Of Delv Days & Check Point: OK------------------------>>>>");
	/*/////////TIME CALCULATION/////////////////////////////////////////////
	String CurrDtTm2="";
	queryString = "SELECT SYSDATE FROM DUAL";
		rset = stmt.executeQuery(queryString);
		if(rset.next())
		{
			CurrDtTm2 = rset.getString(1);System.out.println(VSettle_ICE_JKM_AVG.size()+" :<<<<<<INITIAL STEPS COMPLETED >>>>>>>>>>>>>>>>>>>>>>.....: "+CurrDtTm2);
		}
	*//////////********TIME CALCULATION/////////////////////////////////////////////
	////Price Pulling from Table////////////////////////////
		String StartDate=""+VContMthWise_gas_date.elementAt(0);
		String tempStartDate[]=StartDate.split("/");
		String GenMth=tempStartDate[1]; 
		String GenYr=tempStartDate[2]; 
		String GenMthYr=GenMth+"/"+GenYr;
		StartDate=GenMthYr;
		String PrevMonth=GenMth; String ContinueReducePerc="Y"; //SB20201011
		
		String INDEX_TYPE="LNG_ICE_JKM"; String PriceGen_Dt=""; String NextDate="";
		
		int ContEndNoOfDays=-1; int ContinueReducePercDay=0;
		for(int i=0; i<VContMthWise_gas_date.size();i++) {	
			NextDate=""+VContMthWise_gas_date.elementAt(i);
			String tempNextDate[]=NextDate.split("/");
			GenMth=tempNextDate[1]; GenYr=tempNextDate[2]; GenMthYr=GenMth+"/"+GenYr;
		//	System.out.println(PrevMonth+" :****"+i+"****: "+GenMth);
			if(PrevMonth.equals(GenMth)) //SB20201011
			{
				ContinueReducePerc="Y";
				ContinueReducePercDay++;
			}
			else
			{
			//SB20201020	ContinueReducePerc="N";
			//SB20201020: Due to 1st day of month not getting dropoff	ContinueReducePercDay=0;
				ContinueReducePerc="Y";
				ContinueReducePercDay++;
			}
		///	System.out.println(ContinueReducePercDay+") :ContinueReducePerc: "+ContinueReducePerc);
			PriceGen_Dt=StartDate;
	////////////////////SB20201123: 1) To remove ERROR while Accessing FIXED PRICE deal of longer duration//This is optional and increases performance////////////////////////
			String PriceType="F";
			queryString ="SELECT DISTINCT PRICE_TYPE, CURVE_NM FROM FMS9_MRCR_CONT_PRICE_DTL "
					+ "WHERE MAPPING_ID='"+Mapp_Id+"' ";//AND FROM_DT<=TO_DATE('"+VContMthWise_FRM_DT.elementAt(i)+"','DD/MM/YYYY')  AND FLAG='Y' ";//AND CURVE_NM='"+INDEX_TYPE+"' ";
		//	System.out.println("FMS9_CURVE_PRICE_DTL: "+queryString);
			rset2 = stmt.executeQuery(queryString);			
			if(rset2.next())
			{
				//VPriceTypeDeal.add(rset2.getString(1)==null?"":rset2.getString(1));  //CR-DATED_BRENT
				PriceType=rset2.getString(1)==null?"":rset2.getString(1); //System.out.println(PriceType+" :*IN TABLE**PriceType*****: "+PriceType);
			}
			else
				PriceType="F";//System.out.println(PriceType+" :***PriceType*****: "+PriceType);
		//SB	if(Cont_Type.equals("O")) PriceType="M"; //SB20210203
			if(PriceType.equals("M")) //SB20201123
	///////////^^^^SB20201123: To remove ERROR while Accessing FIXED PRICE deal of longer duration/////////////////////////////
				MRCR_PriceOut2(to_date, INDEX_TYPE, ""+VContMthWise_gas_date.elementAt(i), Mapp_Id, Cont_Rate, ContinueReducePerc, ContinueReducePercDay);
	////////////////////SB20201123: 2) To remove ERROR while Accessing FIXED PRICE deal of longer duration//////////////////////////
			else //SB20201123
			{
				
				VMthYrWisePriceICE_JKM.add("0");
				PriceIndex_SettleDate="";
				VPriceIndex_SettleDate.add("");
				PriceIndex_StartDate="";
				VPriceIndex_StartDate.add("");
				PriceIndex_EndDate="";
				VPriceIndex_EndDate.add("");
				EffPriceSlopeConst="";
				VEff_Price.add("");
				VEff_CumulativePerc.add("0"); //SB20201006
				VEff_Perc.add("0"); 
				VWeekDayHoliDayFlag.add("1");
				VSlope_BRENT.add("1.0");
				VSlope_ICE_JKM.add("1.0");
				VSlope_JKM.add("1.0");
				VSlope_LNG_PHYS.add("1.0");
				VSlope_RLNG_PHYS.add("1.0");
			//SB20201102	VSlope.add("1.0");
				VSlope.add("1"); //SB20201102
				VSlope_FLAG.add("N");
				Slope_ICE_JKM="1";
				Slope_FLAG="N";
				
				VConst_ICE_JKM.add("0"); 
				VConst_RLNG_PHYS.add("0");
				VConst_ICE_JKM_FLAG.add("N");
				Const_ICE_JKM="0"; 
				Const_RLNG_PHYS="0";
				Const_ICE_JKM_FLAG="N";
				VMthYrWise.add(GenMthYr); //SB20210115
				VPriceTypeDeal.add("Fixed");
				VPriceTypeDealRate.add(Cont_Rate);  //Contract Rate SN_MST
				VPriceTypeDealSeQNo.add("0");
				VLNG_ICE_JKM_EXPODropOff.add("");
			//	System.out.println(VLNG_ICE_JKM_EXPODropOff+"  >>>>>>>VLNG_ICE_JKM_EXPODropOff: "+VLNG_ICE_JKM_EXPODropOff.size());
			}
	///////////^^^^SB20201123: To remove ERROR while Accessing FIXED PRICE deal of longer duration/////////////////////////////
			StartDate=""+GenMthYr;
			PrevMonth=GenMth;
		}
	//SB20201110		VMthYrWisePriceICE_JKM.add(""+PrvCurvePrice);
	//SB20201110		VPriceIndex_SettleDate.add(""+PriceIndex_SettleDate);
	//SB20201128		VPriceIndex_StartDate.add(""+PriceIndex_StartDate);
	//SB20201128		VPriceIndex_EndDate.add(""+PriceIndex_EndDate);
		//SB20201119		VConst_ICE_JKM.add(Const_ICE_JKM);
		VConst_RLNG_PHYS.add(Const_ICE_JKM);
		VConst_ICE_JKM_FLAG.add("N");
	//SB20201110		VEff_Price.add(EffPriceSlopeConst);
		VEff_Perc.add(FinancialCumalativePerc);
		VEff_CumulativePerc.add(FinancialCumalativePerc);
		
		VSlope_BRENT.add(Slope_ICE_JKM);
		VSlope_ICE_JKM.add(Slope_ICE_JKM);
		VSlope_JKM.add(Slope_ICE_JKM);
		VSlope_LNG_PHYS.add(Slope_ICE_JKM);
		VSlope_RLNG_PHYS.add(Slope_ICE_JKM);
	//SB20201110		VSlope.add(Slope_ICE_JKM);
		VSlope_FLAG.add(Slope_FLAG);
	//	System.out.println(VEff_Perc+">>>>>>>VEff_CumulativePerc: "+VEff_CumulativePerc);
	//	System.out.println("^^^^^^^^^^ VLNG_ICE_JKM_EXPO "+VLNG_ICE_JKM_EXPO.size());	
	////^^^^^^^Price Pulling from Table////////////////////////////
	System.out.println(count+"  >>>>>>>VMthYrWisePriceICE_JKM: "+VMthYrWisePriceICE_JKM.size());
	System.out.println(count+"  >>>>>>>COMPLETED PRICING LIST(MRCR_PriceOut), Next Loop started");
	//System.out.println(VContMthWise_gas_date.size()+"DEAL-DTL: VContMthWise_gas_date: "+VContMthWise_gas_date);
	//////////TIME CALCULATION/////////////////////////////////////////////
	String CurrDtTm="";
	queryString = "SELECT SYSDATE FROM DUAL";
	rset = stmt.executeQuery(queryString);
	if(rset.next())
	{
		CurrDtTm = rset.getString(1);System.out.println(VContMthWise_gas_date.size()+" :<<<<<<GAS DATE: STEP-0 COMPLETED >>>>>>>>>>>>>>>>>>>>>>.....: "+CurrDtTm);
	}
	//////////********TIME CALCULATION/////////////////////////////////////////////
	for(int i=0;i<count;i++)
	{
	if(Cont_Type.equals("S")) //For SN
	{
	queryString ="select TO_CHAR(A.START_DT,'DD/MM/YYYY'),TO_CHAR(A.END_DT,'DD/MM/YYYY'),A.SN_NO,A.SN_REV_NO,A.CUSTOMER_CD,A.FGSA_NO,A.FGSA_REV_NO,A.TCQ, " +
		"A.SN_REF_NO,to_char(A.SIGNING_DT,'dd/mm/yyyy'),A.RATE,FCC_BY,TO_CHAR(FCC_DATE,'DD/mm/yyyy'),EMP_CD,TO_CHAR(ENT_DT,'dd/mm/yyyy'), A.DCQ from FMS7_SN_MST A where "+ 
		"A.CUSTOMER_CD='"+DealCustCd+"' AND A.FGSA_NO='"+FgsaNo+"' AND A.FGSA_REV_NO='"+FgsaRevNo+"' AND A.SN_NO='"+SnNo+"' AND A.SN_REV_NO='"+SnRevNo+"' "+
		"AND (A.START_DT <= to_date('"+VContMthWise_gas_date.elementAt(i)+"','dd/mm/yyyy')"+"AND A.END_DT >= to_date('"+VContMthWise_gas_date.elementAt(i)+"','dd/mm/yyyy')) AND " +
		"A.SN_REV_NO=(SELECT MAX(B.SN_REV_NO) FROM FMS7_SN_MST B where A.SN_NO=B.SN_NO AND A.FGSA_NO=B.FGSA_NO AND " +
		"A.CUSTOMER_CD=B.CUSTOMER_CD AND A.FGSA_REV_NO=B.FGSA_REV_NO) order by A.START_DT";
	//		System.out.println("DEAL-DTL: FMS7_SN_MST: "+queryString);
	rset = stmt.executeQuery(queryString);
	////System.out.println("FOr SN : "+to_date+" "+from_date);
	while(rset.next())
	{				
		////System.out.println("FOr SN : "+rset.getString(1)+" "+rset.getString(2));
		SN_NO.add(rset.getString(3)==null?"0":rset.getString(3));
		CUSTOMER_CD.add(rset.getString(5)==null?"0":rset.getString(5));									
		tmp_from_dt = rset.getString(1)==null?"":rset.getString(1);
		tmp_to_dt = rset.getString(2)==null?"":rset.getString(2);
		FRM_DT.add(VContMthWise_gas_date.elementAt(i));
		TO_DT.add(VContMthWise_gas_date.elementAt(i));
		temp_dt = tmp_from_dt+"   -   "+tmp_to_dt;
		CARGO_ARRIVAL_DATE.add(temp_dt);				
		SN_REV_NO.add(rset.getString(4)==null?"0":rset.getString(4));			  	
	  	FGSA_NO.add("S"+rset.getString(6)==null?"0":rset.getString(6));	
	  	FGSA_REV_NO.add(rset.getString(7)==null?"0":rset.getString(7));	
	  	BOOKMMBTU.add(rset.getString(8)==null?"0.00":nf.format(Double.parseDouble(rset.getString(8))));
	  	BOOKMMSCM.add(rset.getString(8)==null?"0.00":nf.format(Double.parseDouble(rset.getString(8))/38900));//MD20110916
	  	tot_comit += Double.parseDouble(rset.getString(8)==null?"0":nf.format(Double.parseDouble(rset.getString(8))));
	  	tot_comit_mmscm=(tot_comit/38900);//MD20110916
	  	tot_commitment_mmscm=nf.format(tot_comit_mmscm);
	  	tot_commitment=nf.format(tot_comit);
	  	SN_REF_NO.add(rset.getString(9)==null?"":rset.getString(9));	
	  	SIGNING_DT.add(VContMthWise_gas_date.elementAt(i));
	  	RATE.add(nf2.format(Double.parseDouble(rset.getString(11)==null?"0":rset.getString(11))));
	  	///////////////////Get Variable DCQ//////////////////////////
	  	queryString = "SELECT NVL(dcq,'0') "
				+ " FROM FMS7_SN_DCQ_DTL A WHERE CUSTOMER_CD="+DealCustCd+" AND SN_NO="+SnNo+" AND SN_REV_NO="+SnRevNo+" AND FGSA_NO="+FgsaNo+" AND FGSA_REV_NO="+FgsaRevNo+" AND "
				+ " A.from_dt<=TO_DATE('"+VContMthWise_gas_date.elementAt(i)+"','DD/MM/YYYY') AND A.to_dt>=TO_DATE('"+VContMthWise_gas_date.elementAt(i)+"','DD/MM/YYYY') AND A.FLAG='Y' "
				+ " AND A.sn_rev_no=(SELECT MAX(B.sn_rev_no) FROM FMS7_SN_DCQ_DTL B WHERE A.fgsa_no=B.fgsa_no AND A.sn_no=B.sn_no AND "
				+ " A.customer_cd=B.customer_cd AND  B.from_dt<=TO_DATE('"+VContMthWise_gas_date.elementAt(i)+"','DD/MM/YYYY') AND B.to_dt>=TO_DATE('"+VContMthWise_gas_date.elementAt(i)+"','DD/MM/YYYY') AND B.FLAG='Y') ";
		//		System.out.println("FMS7_SN_DCQ_DTL: "+queryString);
				rset2 = stmt2.executeQuery(queryString);
				if(rset2.next())
				{
					VDealDCQ.add(rset2.getString(1));
					VDealDCQFlag.add("Y");
				}
				else
				{
					VDealDCQ.add(rset.getString(16)==null?"0":rset.getString(16));
					VDealDCQFlag.add("N");
				}
	  	///////////////////^^^^^Get Variable DCQ//////////////////////////
	}	
	}
	else if(Cont_Type.equals("L")) //For LoA
	{
	/////////////////////////////LOA LIst of Contracts/////////////////////////////////////////////////////////////////
	queryString =   "select TO_CHAR(A.START_DT,'DD/MM/YYYY'),TO_CHAR(A.END_DT,'DD/MM/YYYY'),A.LOA_NO,A.LOA_REV_NO,A.CUSTOMER_CD,A.TENDER_NO,A.TCQ, " +
		"A.LOA_REF_NO,to_char(A.SIGNING_DT,'dd/mm/yyyy'),A.RATE,FCC_BY,TO_CHAR(FCC_DATE,'DD/mm/yyyy'),EMP_CD,TO_CHAR(ENT_DT,'dd/mm/yyyy'), DCQ, ADV_AMT, TRANSPORTATION_CHARGE  from FMS7_LOA_MST A where "+ 
		"A.CUSTOMER_CD='"+DealCustCd+"' AND A.TENDER_NO='"+FgsaNo+"' AND A.LOA_NO='"+SnNo+"' AND A.LOA_REV_NO='"+SnRevNo+"' AND "+ 
		//SB20201013		"(A.START_DT <= to_date('"+to_date+"','dd/mm/yyyy')"+"AND A.END_DT >= to_date('"+from_date+"','dd/mm/yyyy')) AND " +
		"(A.START_DT <= to_date('"+VContMthWise_gas_date.elementAt(i)+"','dd/mm/yyyy')"+"AND A.END_DT >= to_date('"+VContMthWise_gas_date.elementAt(i)+"','dd/mm/yyyy')) AND " + //SB20201013
		//SB20200919			"(A.SIGNING_DT <= to_date('"+to_date+"','dd/mm/yyyy')"+"AND A.END_DT >= to_date('"+from_date+"','dd/mm/yyyy')) AND " +
		"A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM FMS7_LOA_MST B where A.LOA_NO=B.LOA_NO AND A.TENDER_NO=B.TENDER_NO AND " +
		"A.CUSTOMER_CD=B.CUSTOMER_CD) order by A.START_DT";
	rset = stmt.executeQuery(queryString);
	///	System.out.println("LOA: "+queryString);
	while(rset.next())
	{				
	  	SN_NO.add(rset.getString(3)==null?"0":rset.getString(3));
		CUSTOMER_CD.add(rset.getString(5)==null?"0":rset.getString(5));									
		tmp_from_dt = rset.getString(1)==null?"":rset.getString(1);
		tmp_to_dt = rset.getString(2)==null?"":rset.getString(2);
		FRM_DT.add(VContMthWise_gas_date.elementAt(i));
		TO_DT.add(VContMthWise_gas_date.elementAt(i));
		temp_dt = tmp_from_dt+"   -   "+tmp_to_dt;
		CARGO_ARRIVAL_DATE.add(temp_dt);				
		SN_REV_NO.add(rset.getString(4)==null?"0":rset.getString(4));			  	
	  	FGSA_NO.add("L"+rset.getString(6)==null?"0":rset.getString(6));	
	  	FGSA_REV_NO.add("0");	 //No Rev no in LoA
	  	BOOKMMBTU.add(rset.getString(7)==null?"0.00":nf.format(Double.parseDouble(rset.getString(7))));
	  	BOOKMMSCM.add(rset.getString(7)==null?"0.00":nf.format(Double.parseDouble(rset.getString(7))/38900));//MD20110916
	  	tot_comit += Double.parseDouble(rset.getString(7)==null?"0":nf.format(Double.parseDouble(rset.getString(7))));
	  	tot_comit_mmscm=(tot_comit/38900);//MD20110916
	  	tot_commitment_mmscm=nf.format(tot_comit_mmscm);
	  	tot_commitment=nf.format(tot_comit);
	  	SN_REF_NO.add(rset.getString(8)==null?"":rset.getString(8));	
	  	SIGNING_DT.add(VContMthWise_gas_date.elementAt(i));
	  	RATE.add(nf2.format(Double.parseDouble(rset.getString(10)==null?"0":rset.getString(10))));
	  	///////////////////Get Variable DCQ//////////////////////////
		 /* 	queryString = "SELECT NVL(dcq,'0') "
					+ " FROM FMS7_SN_DCQ_DTL A WHERE CUSTOMER_CD="+DealCustCd+" AND SN_NO="+SnNo+" AND SN_REV_NO="+SnRevNo+" AND FGSA_NO="+FgsaNo+" AND FGSA_REV_NO="+FgsaRevNo+" AND "
					+ " A.from_dt<=TO_DATE('"+VContMthWise_gas_date.elementAt(i)+"','DD/MM/YYYY') AND A.to_dt>=TO_DATE('"+VContMthWise_gas_date.elementAt(i)+"','DD/MM/YYYY') AND A.FLAG='Y' "
					+ " AND A.sn_rev_no=(SELECT MAX(B.sn_rev_no) FROM FMS7_SN_DCQ_DTL B WHERE A.fgsa_no=B.fgsa_no AND A.sn_no=B.sn_no AND "
					+ " A.customer_cd=B.customer_cd AND  B.from_dt<=TO_DATE('"+VContMthWise_gas_date.elementAt(i)+"','DD/MM/YYYY') AND B.to_dt>=TO_DATE('"+VContMthWise_gas_date.elementAt(i)+"','DD/MM/YYYY') AND B.FLAG='Y') ";
			//		System.out.println("FMS7_SN_DCQ_DTL: "+queryString);*/
		  	queryString = "SELECT NVL(dcq,'0') "
					+ " FROM FMS7_LOA_DCQ_DTL A WHERE CUSTOMER_CD="+DealCustCd+" AND LOA_NO="+SnNo+" AND LOA_REV_NO="+SnRevNo+" AND TENDER_NO="+FgsaNo+" AND "
					+ " A.from_dt<=TO_DATE('"+VContMthWise_gas_date.elementAt(i)+"','DD/MM/YYYY') AND A.to_dt>=TO_DATE('"+VContMthWise_gas_date.elementAt(i)+"','DD/MM/YYYY') AND A.FLAG='Y' "
					+ " AND A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM FMS7_LOA_DCQ_DTL B WHERE A.TENDER_NO=B.TENDER_NO AND A.LOA_NO=B.LOA_NO AND "
					+ " A.customer_cd=B.customer_cd AND  B.from_dt<=TO_DATE('"+VContMthWise_gas_date.elementAt(i)+"','DD/MM/YYYY') AND B.to_dt>=TO_DATE('"+VContMthWise_gas_date.elementAt(i)+"','DD/MM/YYYY') AND B.FLAG='Y') ";
				//	System.out.println("DCQ: FMS7_LOA_DCQ_DTL: "+queryString);
					rset2 = stmt2.executeQuery(queryString);
					if(rset2.next())
					{
						VDealDCQ.add(rset2.getString(1));
						VDealDCQFlag.add("Y");
					}
					else
					{
						VDealDCQ.add(rset.getString(15)==null?"0":rset.getString(15));
						VDealDCQFlag.add("N");
					}
		  	///////////////////^^^^^Get Variable DCQ//////////////////////////
	}
	}
	else if(Cont_Type.equals("B"))
	{
	queryString = "SELECT TO_CHAR(A.DELV_FROM_DT,'DD/MM/YYYY'), TO_CHAR(A.DELV_TO_DT,'DD/MM/YYYY'),A.MAN_CONF_CD,A.CARGO_SEQ_NO,E.TRD_CD, A.MAN_CD, A.MAN_CD, A.CONFIRM_VOL,"
			+ "A.CARGO_REF_CD,to_char(C.MAN_CONF_DT,'dd/mm/yyyy'), A.PRICE,A.EMP_CD,TO_CHAR(A.ENT_DT,'dd/mm/yyyy'), A.EMP_CD,TO_CHAR(A.ENT_DT,'dd/mm/yyyy'), E.PRICE_TYPE, A.PRICE_TO, A.REMARK  "+
			" FROM FMS7_MAN_CONFIRM_CARGO_DTL A, FMS7_MAN_CONFIRM_MST C, FMS7_MAN_REQ_MST E WHERE "+
			"E.TRD_CD='"+DealCustCd+"' AND A.MAN_CONF_CD='"+SnNo+"' AND A.MAN_CD='"+FgsaNo+"' AND A.CARGO_SEQ_NO='"+SnRevNo+"' AND "+
			///		" A.DELV_FROM_DT BETWEEN TO_DATE('"+SearchDtFrom+"','DD/MM/YYYY') AND TO_DATE('"+SearchDtTo+"','DD/MM/YYYY') "
			" A.DELV_TO_DT >= TO_DATE('"+VContMthWise_gas_date.elementAt(i)+"','DD/MM/YYYY') "
			+ "AND E.MAN_CD=A.MAN_CD AND "+ 
	///		" A.UNIT_CD=C.UNIT_CD AND A.CARGO_STATUS='T' AND CARGO_REF_CD='"+VCAR_REF_NO_temp.elementAt(i)+"' "+ 
			" A.MAN_CONF_CD=C.MAN_CONF_CD AND A.CARGO_STATUS='T' AND CARGO_REF_CD LIKE '%' "+ 
			" ORDER BY A.MAN_CONF_CD, A.CARGO_REF_CD";
	//(START_DT <= to_date('"+to_date+"','dd/mm/yyyy')"+"AND END_DT >= to_date('"+from_date+"','dd/mm/yyyy'))
	System.out.println("2. CARGO: "+queryString);
	rset = stmt.executeQuery(queryString);
	////System.out.println("FOr SN : "+to_date+" "+from_date);
	while(rset.next())
	{				
		tmp_from_dt = rset.getString(1)==null?"":rset.getString(1);
		tmp_to_dt = rset.getString(2)==null?"":rset.getString(2);
		FRM_DT.add(VContMthWise_gas_date.elementAt(i));
		TO_DT.add(VContMthWise_gas_date.elementAt(i));
		temp_dt = tmp_from_dt+"   -   "+tmp_to_dt;
		CARGO_ARRIVAL_DATE.add(temp_dt);				
		SN_NO.add(rset.getString(3)==null?"0":rset.getString(3));
		SN_REV_NO.add(rset.getString(4)==null?"0":rset.getString(4));
		CUSTOMER_CD.add(rset.getString(5)==null?"0":rset.getString(5));																  	
	  	FGSA_NO.add("B"+rset.getString(6)==null?"0":rset.getString(6));	
	  	FGSA_REV_NO.add("0");	 //No Rev no in LoA
	  	BOOKMMBTU.add(rset.getString(8)==null?"0.00":nf.format(Double.parseDouble(rset.getString(8))));
	  	BOOKMMSCM.add(rset.getString(8)==null?"0.00":nf.format(Double.parseDouble(rset.getString(8))/38900));//MD20110916
	 // 	tot_comit += Double.parseDouble(rset.getString(7)==null?"0":nf.format(Double.parseDouble(rset.getString(7))));
	 // 	tot_comit_mmscm=(tot_comit/38900);//MD20110916
	  	tot_commitment_mmscm=nf.format(tot_comit_mmscm);
	  	tot_commitment=nf.format(tot_comit);
	  	SN_REF_NO.add(rset.getString(9)==null?"":rset.getString(9));	
	  	SIGNING_DT.add(VContMthWise_gas_date.elementAt(i));
	  	RATE.add(nf2.format(Double.parseDouble(rset.getString(11)==null?"0":rset.getString(11))));
	  	///////////////////Get Variable DCQ//////////////////////////
	  	VDealDCQ.add(rset.getString(8)==null?"0":rset.getString(8));
		VDealDCQFlag.add("N");
	  	
	/*SB  	queryString = "SELECT NVL(dcq,'0') "
				+ " FROM FMS7_LOA_DCQ_DTL A WHERE CUSTOMER_CD="+DealCustCd+" AND LOA_NO="+SnNo+" AND LOA_REV_NO="+SnRevNo+" AND TENDER_NO="+FgsaNo+" AND "
				+ " A.from_dt<=TO_DATE('"+VContMthWise_gas_date.elementAt(i)+"','DD/MM/YYYY') AND A.to_dt>=TO_DATE('"+VContMthWise_gas_date.elementAt(i)+"','DD/MM/YYYY') AND A.FLAG='Y' "
				+ " AND A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM FMS7_LOA_DCQ_DTL B WHERE A.TENDER_NO=B.TENDER_NO AND A.LOA_NO=B.LOA_NO AND "
				+ " A.customer_cd=B.customer_cd AND  B.from_dt<=TO_DATE('"+VContMthWise_gas_date.elementAt(i)+"','DD/MM/YYYY') AND B.to_dt>=TO_DATE('"+VContMthWise_gas_date.elementAt(i)+"','DD/MM/YYYY') AND B.FLAG='Y') ";
	  	*/
	  	
		if(count==1) //SB20210517: if it is CARGO Purchase( because From and To date is same
		{
		queryString = "SELECT QTY_MMBTU FROM FMS7_CARGO_QQ_DTL "
	  			+" WHERE CARGO_REF_NO='"+rset.getString(9)+"' AND QQ_DT<=TO_DATE('"+VContMthWise_gas_date.elementAt(i)+"','DD/MM/YYYY') AND FLAG='Y' ";	
	  	System.out.println("ACTUAL-QTY/DCQ: FMS7_LOA_DCQ_DTL: "+queryString);
				rset2 = stmt2.executeQuery(queryString);
				if(rset2.next())
				{
				//	VDealDCQ.add(rset2.getString(1));
				//	VDealDCQFlag.add("Y");
					VDealAllocQty.add(rset2.getString(1));
				}
				else
				{
					//VDealDCQ.add(rset.getString(8)==null?"0":rset.getString(8));
					//VDealDCQFlag.add("N");
					VDealAllocQty.add("0");
				}
		}
		else //SB20210517: if it is BUY Deal Purchase( because From and To date is same
		{
			String TransId="3"; String TransEntryPt="31"; String TransExitPt="341";
		//SB	String AllocMapp_Id=DealCustCd+"-"+SnNo+"-"+FgsaNo+"-"+TransId+"-"+TransEntryPt+"-"+TransExitPt; //5-21012-21001-3-31-341
			String AllocMapp_Id=DealCustCd+"-"+SnNo+"-"+FgsaNo+"-%"; //5-21012-21001-3-31-341
			String ContMapp_Id="B-"+DealCustCd+"-"+SnNo+"-0-"+FgsaNo+"-"+SnRevNo; //System.out.println("ContMapp_Id: "+ContMapp_Id);
		//	System.out.println("Mapp_Id: "+Mapp_Id);
			////////////////////^^^^SB20210517//////////////////////////
				queryString = "SELECT SUM(ENTRY_TOT_ENE) FROM FMS9_PO_ALLOC_MST "
						+" WHERE MAPPING_ID LIKE '"+AllocMapp_Id+"' AND CONT_MAPPING_ID = '"+ContMapp_Id+"' AND ALLOC_DT=TO_DATE('"+VContMthWise_gas_date.elementAt(i)+"','DD/MM/YYYY') ";
		//	 	System.out.println("ACTUAL-QTY/DCQ: FMS9_PO_ALLOC_MST: "+queryString);
				rset2 = stmt2.executeQuery(queryString);
				if(rset2.next())
				{
					VDealAllocQty.add(rset2.getDouble(1));
				}
				else
				{
					VDealAllocQty.add("0");
				}
		}
	  	///////////////////^^^^^Get Variable DCQ//////////////////////////
	 // 	MAPPING_ID.add("C"+rset.getString(6)+"-"+rset.getString(7)+"-"+rset.getString(3)+"-"+rset.getString(4));
	 //// 	MAPPING_ID.add("B"+rset.getString(3)+"-0-"+rset.getString(6)+"-"+rset.getString(7));
	}	
	}
	/////////////////////////////////////////////////////////////////
	else if(Cont_Type.equals("O"))
	{
	
	{				
		tmp_from_dt =StorageDate;
		tmp_to_dt = StorageDate;
	//SB	FRM_DT.add(VContMthWise_gas_date.elementAt(i));
	//SB	TO_DT.add(VContMthWise_gas_date.elementAt(i));
		FRM_DT.add(StorageDate);
		TO_DT.add(StorageDate);
		temp_dt = StorageDate+"   -   "+StorageDate;
		CARGO_ARRIVAL_DATE.add(temp_dt);				
		SN_NO.add("0");
		SN_REV_NO.add("0");
		CUSTOMER_CD.add("0");																  	
	  	FGSA_NO.add("0");	
	  	FGSA_REV_NO.add("0");	 //No Rev no in LoA
	  	BOOKMMBTU.add("0");
	  	BOOKMMSCM.add("0");//MD20110916
	 // 	tot_comit += Double.parseDouble(rset.getString(7)==null?"0":nf.format(Double.parseDouble(rset.getString(7))));
	 // 	tot_comit_mmscm=(tot_comit/38900);//MD20110916
	  	tot_commitment_mmscm=nf.format(tot_comit_mmscm);
	  	tot_commitment=nf.format(tot_comit);
	  	SN_REF_NO.add("");	
	//SB	  	SIGNING_DT.add(VContMthWise_gas_date.elementAt(i));
		SIGNING_DT.add(StorageDate);
	  	RATE.add("0");
	  	///////////////////Get Variable DCQ//////////////////////////
	  	//////////////////SB20210201//////////////////////////////
		String Report_Dt=to_date; //SB20210201
		MarketExposureStorage(Report_Dt);  //SB20210201: Get Storage Qty available in VStoreSEIPL
		//////////////////^^^SB20210201//////////////////////////////
		if(VStoreSEIPL.size()>0)
			VDealDCQ.add(VStoreSEIPL.elementAt(0));
		else
			VDealDCQ.add("0");
		VDealDCQFlag.add("N");				  	
		VDealAllocQty.add("0");
	  	///////////////////^^^^^Get Variable DCQ//////////////////////////
	 // 	MAPPING_ID.add("C"+rset.getString(6)+"-"+rset.getString(7)+"-"+rset.getString(3)+"-"+rset.getString(4));
	 //// 	MAPPING_ID.add("B"+rset.getString(3)+"-0-"+rset.getString(6)+"-"+rset.getString(7));
	}	
	}
	}  System.out.println(VContMthWise_gas_date.size()+"  :CUSTOMER_NM: "+CUSTOMER_CD.size());
	//	System.out.println(VDealDCQ.size()+"  :VDealDCQ: "+VDealDCQ.size());
	for(int i=0;i<CUSTOMER_CD.size();i++)
	{							
	//	System.out.println("CUSTOMER_NM: "+CUSTOMER_NM);
	queryString ="select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL where CUSTOMER_CD="+DealCustCd+" " +
	 "AND SN_NO="+SnNo+" AND SN_REV_NO="+SnRevNo+" AND FGSA_NO="+FgsaNo+" " +
	 "AND FGSA_REV_NO="+FgsaRevNo+" AND GAS_DT between to_date('"+VContMthWise_gas_date.elementAt(i)+"','dd/mm/yyyy')" +
	//SB20201124		 "AND to_date('"+VContMthWise_gas_date.elementAt(i)+"','dd/mm/yyyy') AND CONTRACT_TYPE='S'";
	 "AND to_date('"+VContMthWise_gas_date.elementAt(i)+"','dd/mm/yyyy') AND CONTRACT_TYPE='"+Cont_Type+"' ";
	//	System.out.println("EXPO: >>>>>>>FMS7_DAILY_ALLOCATION_DTL "+queryString);
	rset = stmt.executeQuery(queryString);
	
	if(rset.next())
	{
		SUPPLIEDMBTU.add(rset.getString(1)==null?"0.00":nf.format(Double.parseDouble(rset.getString(1))));
		//MD20110916
		SUPPLIEDMMSCM.add(rset.getString(1)==null?"0.00":nf.format(Double.parseDouble(rset.getString(1))/38900));
		if(rset.getString(1)!=null && !(rset.getString(1).equals("")))
		{
			sup_tot_comit+=Double.parseDouble(rset.getString(1));
			sup_tot_comit_mmscm+=(Double.parseDouble(rset.getString(1))/38900);//MD20110916
		}
		else
		{
			sup_tot_comit+=0;
			sup_tot_comit_mmscm+=0;//MD20110916
		}
	}
	else
	{
		SUPPLIEDMBTU.add("0.00");
		SUPPLIEDMMSCM.add("0.00");//MD20110916
		sup_tot_comit+=0;
		sup_tot_comit_mmscm+=0;//MD20110916
	}
	
	BALANCEMMBTU.add(nf.format(Double.parseDouble(""+BOOKMMBTU.elementAt(i))- Double.parseDouble(""+SUPPLIEDMBTU.elementAt(i))));
	//MD20110916
	BALANCEMMSCM.add(nf.format(Double.parseDouble(""+BOOKMMSCM.elementAt(i))- Double.parseDouble(""+SUPPLIEDMMSCM.elementAt(i))));
	bal_tot_comit+=Double.parseDouble(""+BOOKMMBTU.elementAt(i))- Double.parseDouble(""+SUPPLIEDMBTU.elementAt(i));
	bal_tot_comit_mmscm+=Double.parseDouble(""+BOOKMMSCM.elementAt(i))- Double.parseDouble(""+SUPPLIEDMMSCM.elementAt(i));
	//System.out.println("SUPLIEDMMSCM : "+SUPPLIEDMMSCM+" TOTAL : "+sup_tot_comit_mmscm);
	//System.out.println("BALANCEMMSCM : "+BALANCEMMSCM+" TOTAL : "+bal_tot_comit_mmscm);
		
	//////////////SB20200817////////////////////////
	}
	/*SB20201127
	int BuySellFactor=0; String BuySell="Sell";
	if(BuySell.equalsIgnoreCase("Sell"))
	BuySellFactor=1;
	else
	BuySellFactor=-1;
	*/
	//SB LNG_ICE_JKM_EXPOBaseValue=Double.parseDouble(nf.format(LNG_ICE_JKM_EXPOBaseValue));
	///////////////////////////////HOLIDAY CALCULATION///////////////////////////////////
	String HolidayType="";
	if(INDEX_TYPE.equals("LNG_ICE_JKM"))
	HolidayType="J";
	else if(INDEX_TYPE.equals("CR_DATED_BRENT")) //SB20201025
	HolidayType="B";
	else
	HolidayType="J";
	///////////////////////////////////////////////////////////////////////////////////
	System.out.println(CUSTOMER_CD.size()+"  :CUSTOMER_CD: FINAL STEPS STARTED: >>>>>>>>>>>>>>>>>>>>");
	//System.out.println(VPriceIndex_StartDate.size()+"  :VPriceIndex_StartDate: STEP-1: "+VPriceIndex_StartDate);
	//System.out.println(VPriceIndex_EndDate.size()+"  :VPriceIndex_StartDate: STEP-1: "+VPriceIndex_EndDate);
	//////////TIME CALCULATION/////////////////////////////////////////////
	CurrDtTm="";
	queryString = "SELECT SYSDATE FROM DUAL";
	rset = stmt.executeQuery(queryString);
	if(rset.next())
	{
	CurrDtTm = rset.getString(1);System.out.println(VDealDCQ.size()+" :<<<<<<GAS DATE: STEP-1 COMPLETED >>>>>>>>>>>>>>>>>>>>>>.....: "+CurrDtTm);
	}
	//////////********TIME CALCULATION/////////////////////////////////////////////
	
	for(int i=0;i<CUSTOMER_CD.size();i++)
	{
	String TCQ=""; String Alloc_Qty=""; String Gen_Dt=to_date; String Cont_End_Dt=""; 
	TCQ=""+BOOKMMBTU.elementAt(i);
	String DCQ=""+VDealDCQ.elementAt(i); 
	TCQ=DCQ;
	Alloc_Qty=""+SUPPLIEDMBTU.elementAt(i); 
	Cont_End_Dt=""+TO_DT.elementAt(i);
	////	String Mapp_Id=CUSTOMER_CD.elementAt(i)+"-"+FGSA_NO.elementAt(i)+"-"+FGSA_REV_NO.elementAt(i)+"-"+SN_NO.elementAt(i)+"-"+SN_REV_NO.elementAt(i);
	String Phy_CurFrd_Price="0"; String Fin_CurFrd_Price="0";
	//String Buy_Sell="SELL";
	if(Buy_Sell.equals("Buy"))
	{
		//SB20201201 VDealAllocQty.add(Alloc_Qty);
	}
	else
		VDealAllocQty.add("-"+Alloc_Qty);
	
	
	String PriorCurveGasPrice="";
	//	if(VMthYrWisePriceICE_JKM.elementAt(i).equals(""))
		PriorCurveGasPrice=""+VMthYrWisePriceICE_JKM.elementAt(i);
	
	String DealPrice="";
	DealPrice=""+RATE.elementAt(i);
	String Slope="1.0";
	if(Double.parseDouble(""+VMthYrWisePriceICE_JKM.elementAt(i))>0)  //SB: Set JKM Forward Price to ZERO, If Settle Price is Available
	{
		Fin_CurFrd_Price=""+Double.parseDouble(""+VMthYrWisePriceICE_JKM.elementAt(i));
		DealPrice=""+Double.parseDouble(""+VEff_Price.elementAt(i));   //SB20200918: If Price is Settled then Deal price is Settle Price
		//Slope=""+Double.parseDouble(""+VSlope.elementAt(i));   //SB20200918:
	}
	//	System.out.println(VEff_Price.elementAt(i)+" DealPrice: "+DealPrice);
	////////////////////////////SB20201020: Very Important for DropOff/////////////////////////////////
	double LNG_ICE_JKM_EXPOBaseValue=0;
	int StratDayCount=0; int StratDayCount2=0; //SB20201017: to check Starting SAT/SUN/HOLIDAY
	String PriceBegMthDt=""+VPriceIndex_StartDate.elementAt(i);
	int ReportDays=0; int ReportDays2=0;
	if(!PriceBegMthDt.equals(""))  //System.out.println(i+"/"+VPriceIndex_StartDate.size()+" <--BLANK LOCATION: PriceBegMthDt-->: "+PriceBegMthDt);  //SB20201128
	{
		queryString = "SELECT TO_DATE('"+to_date+"','DD/MM/YYYY')-TO_DATE('"+PriceBegMthDt+"','DD/MM/YYYY')+1 FROM DUAL" ;
	//	System.out.println(" DUAL: LAST: "+queryString);
		rset = stmt.executeQuery(queryString);
		if(rset.next()) {
		ReportDays = rset.getInt(1);
		}//	System.out.println("ReportDays: "+ReportDays);
	}
	for(int j=0; j<ReportDays; j++)
	{
		String NextDate2="";
		queryString = "SELECT TO_CHAR(TO_DATE('"+PriceBegMthDt+"','DD/MM/YYYY')+"+j+",'DD/MM/YYYY') FROM DUAL";
	//		System.out.println("Get DAY Name: "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				NextDate2 = rset.getString(1)==null?"":rset.getString(1);
			}
	queryString ="SELECT to_char(HOLIDAY_DT,'dd/mm/yyyy') FROM FMS9_CURVE_HOLIDAY_DTL "
			+ " WHERE HOLIDAY_DT =to_date('"+NextDate2+"','dd/mm/yyyy') AND FLAG='Y' AND HOLIDAY_TYPE='"+HolidayType+"' ORDER BY HOLIDAY_DT";
	rset = stmt.executeQuery(queryString);
	//	System.out.println(">>>>>>>PRICE PUULING: "+queryString);
	if(rset.next())
	{
		StratDayCount++; //System.out.println("Get HOLI DAY Name: ");		
	}
	else
	{
	String WeeklyOff="";
	queryString = "SELECT TO_CHAR(TO_DATE('"+NextDate2+"','DD/MM/YYYY'),'DAY') FROM DUAL";
	//	System.out.println("Get DAY Name: "+queryString);
		rset = stmt.executeQuery(queryString);
		if(rset.next())
		{
			WeeklyOff = rset.getString(1)==null?"":rset.getString(1);
		}
		if(WeeklyOff.trim().equals("SATURDAY")) 
		{
			StratDayCount++;
			//PriceBegMthDt="17/08/2020"; 
		}
		else if(WeeklyOff.trim().equals("SUNDAY")) 
		{
			StratDayCount++;
			//PriceBegMthDt="17/08/2020"; 
		}
	}
	}
	//System.out.println(LNG_ICE_JKM_EXPOBaseValue+" :**SAIBAL*****StratDayCount: "+StratDayCount);
	int EndSettlePriceDateCount=ReportDays-1; //SB20201024
	ReportDays = ReportDays-StratDayCount;
	if(ReportDays>=0)
		LNG_ICE_JKM_EXPOBaseValue=ReportDays;
	//System.out.println(LNG_ICE_JKM_EXPOBaseValue+" :******* VLNG_ICE_JKM_EXPO: "+ReportDays);
	///////////////////////////////^^^^SB20201020//////////////////////////////////////////
	
	/////////////////////////////Avr Settlement Price //////////////////////////////////
	if(PriceBegMthDt.equals(""))
		PriceBegMthDt=""+to_date;
	int LastPriceDays=0; String SettlePriceDate="";
	if(!VPriceIndex_EndDate.elementAt(i).equals(""))
	{
		queryString = "SELECT TO_DATE('"+to_date+"','DD/MM/YYYY')-TO_DATE('"+VPriceIndex_EndDate.elementAt(i)+"','DD/MM/YYYY')+1 FROM DUAL" ;
	//	System.out.println(" DUAL: LAST: "+queryString);
		rset = stmt.executeQuery(queryString);
		if(rset.next()) {
		LastPriceDays = rset.getInt(1);
		}	
		if(LastPriceDays>0)
			SettlePriceDate=""+VPriceIndex_EndDate.elementAt(i);
		else
			SettlePriceDate=""+to_date; 
		//SB20201107 SettlePriceDate=""+VPriceIndex_EndDate.elementAt(i); //SB20201107: to get the end date for Avg Cal if Report date is current Date
	}
	else
		SettlePriceDate=""+to_date;
	
	////////Index type Pulling from Table////////////////////////////
	PricePHYSName=""; //SB20210311
	queryString ="SELECT PRICE_TYPE, CURVE_NM, RATE, SEQ_NO, PHYS_CURVE_NM FROM FMS9_MRCR_CONT_PRICE_DTL "
			+ "WHERE MAPPING_ID='"+Mapp_Id+"' AND FROM_DT<=TO_DATE('"+VContMthWise_gas_date.elementAt(i)+"','DD/MM/YYYY') AND TO_DT>=TO_DATE('"+VContMthWise_gas_date.elementAt(i)+"','DD/MM/YYYY') AND FLAG='Y' ";//AND CURVE_NM='"+INDEX_TYPE+"' ";
	//System.out.println("FMS9_CURVE_PRICE_DTL: "+queryString);
	rset2 = stmt.executeQuery(queryString);			
	if(rset2.next())
	{
		INDEX_TYPE=rset2.getString(2)==null?"":rset2.getString(2);
		PricePHYSName=rset2.getString(5)==null?"":rset2.getString(5); //SB20210311
		if(INDEX_TYPE.equals("LNG_ICE_JKM"))
			HolidayType="J";
		else if(INDEX_TYPE.equals("CR_DATED_BRENT")) //SB20201025
			HolidayType="B";
		else
			HolidayType="J";
	}
////SB20210311: New Logic ////////////SB20210228: For Buy Deal It should be LNG_PHYS_INDIA else RLNG_PHYS_INDIA as per Shiladita 25th Feb 2021////////////////////////////////////////////////////////
	if(PricePHYSName.equals(""))  //SB20210310	
	{
		 if(Buy_Sell.equalsIgnoreCase("BUY"))
			 PricePHYSName="LNG_PHYS_INDIA";
		 else
			 PricePHYSName="RLNG_PHYS_INDIA";
	}
////////////////^SB20210228////////////////////////////////////////////////////////
	if(INDEX_TYPE.equals("CR_DATED_BRENT") || INDEX_TYPE.equals("ICE_BRENT")) //SB20201117
	 PriceIndexName="ICE_BRENT";
	else
	 PriceIndexName=""+INDEX_TYPE;
		////////////////////////////////////////////////////////////////////////
	
	double AvgPriceSettle=0; int PriceAvlCountSettle=1;
	/*SB20201025
	queryString = "select AVG(LNG_ICE_JKM), COUNT(*) from FMS9_CURVE_PRICE_DTL "
	//		+ " where LNG_ICE_JKM>0 AND CURVE_DD_MM_YR BETWEEN to_date('"+PriceBegMthDt+"','dd/mm/yyyy') AND to_date('"+PriceBegMthDt+"','dd/mm/yyyy')+"+EndSettlePriceDateCount+" AND CURVE_TYPE='P' ";
	+ " where LNG_ICE_JKM>0 AND CURVE_DD_MM_YR BETWEEN to_date('"+PriceBegMthDt+"','dd/mm/yyyy') AND to_date('"+SettlePriceDate+"','dd/mm/yyyy') AND CURVE_TYPE='P' ";
	*/
	//////////////SB20201105: Introduced due to multiple record on Curve Price Tbl////////////////////////////
	int CountPlattEntDt=0; String PlattEntDt="";
	queryString = "select COUNT(ENT_DT) from FMS9_CURVE_PRICE_DTL "
	+ " where ENT_DT= to_date('"+Gen_Dt+"','dd/mm/yyyy') AND CURVE_TYPE='P' AND FLAG='Y' ";
	//System.out.println("FMS9_CURVE_PRICE_DTL: "+queryString);
	rset = stmt.executeQuery(queryString);
	if(rset.next())
	{
	CountPlattEntDt = rset.getInt(1);
	}
	if(CountPlattEntDt>0)
	{
	PlattEntDt=Gen_Dt; //System.out.println("PlattEntDt: "+PlattEntDt);
	queryString = "SELECT TO_CHAR(ENT_DT, 'DD-MON-YY')  from FMS9_CURVE_PRICE_DTL "
	+ " where ENT_DT= to_date('"+Gen_Dt+"','dd/mm/yyyy') AND CURVE_TYPE='P' AND FLAG='Y' ";
	//	System.out.println("FMS9_CURVE_PRICE_DTL: "+queryString);
	rset = stmt.executeQuery(queryString);
	if(rset.next())
	{
	PlattEntDt = rset.getString(1)==null?"":rset.getString(1);
	}
	}
	else
	{
	queryString = "SELECT TO_CHAR(MAX(ENT_DT), 'DD-MON-YY')  from FMS9_CURVE_PRICE_DTL "
	+ " where ENT_DT<= to_date('"+Gen_Dt+"','dd/mm/yyyy') AND CURVE_TYPE='P' AND FLAG='Y' ";
	//	System.out.println("FMS9_CURVE_PRICE_DTL: "+queryString);
	rset = stmt.executeQuery(queryString);
	if(rset.next())
	{
	PlattEntDt = rset.getString(1)==null?"":rset.getString(1);
	}
	if(PlattEntDt.equals(""))
	{
	queryString = "SELECT TO_CHAR(MIN(ENT_DT), 'DD-MON-YY')  from FMS9_CURVE_PRICE_DTL "
		+ " where ENT_DT>= to_date('"+Gen_Dt+"','dd/mm/yyyy') AND CURVE_TYPE='P' AND FLAG='Y' ";
	//	System.out.println("FMS9_CURVE_PRICE_DTL: "+queryString);
	rset = stmt.executeQuery(queryString);
	if(rset.next())
	{
	PlattEntDt = rset.getString(1)==null?"":rset.getString(1);
	}
	if(PlattEntDt.equals(""))
	{
	queryString = "SELECT TO_CHAR(MAX(ENT_DT), 'DD-MON-YY')  from FMS9_CURVE_PRICE_DTL "
			+ " where ENT_DT<= to_date('"+Gen_Dt+"','dd/mm/yyyy') AND CURVE_TYPE='P' AND FLAG='Y' ";
	//	System.out.println("FMS9_CURVE_PRICE_DTL: "+queryString);
	rset = stmt.executeQuery(queryString);
	if(rset.next())
	{
		PlattEntDt = rset.getString(1)==null?"":rset.getString(1);
	}
	}
	}
	}
	//System.out.println("PlattEntDt: "+PlattEntDt);
	//////////////////////////^^^^SB20201105////////////////////////////////////////////////////////////////////
	/*SB20201105	queryString = "select AVG("+INDEX_TYPE+"), COUNT(*) from FMS9_CURVE_PRICE_DTL "
	+ " where "+INDEX_TYPE+">0 AND CURVE_DD_MM_YR BETWEEN to_date('"+PriceBegMthDt+"','dd/mm/yyyy') AND to_date('"+SettlePriceDate+"','dd/mm/yyyy') AND CURVE_TYPE='P' ";
	*/
	queryString = "select AVG("+INDEX_TYPE+"), COUNT(*) from FMS9_CURVE_PRICE_DTL "
	+ " where "+INDEX_TYPE+">0 AND CURVE_DD_MM_YR BETWEEN to_date('"+PriceBegMthDt+"','dd/mm/yyyy') AND to_date('"+SettlePriceDate+"','dd/mm/yyyy') AND CURVE_TYPE='P' AND TO_DATE(ENT_DT, 'DD-MON-YY')=TO_DATE('"+PlattEntDt+"', 'DD-MON-YY')";
	//System.out.println("AVG: FMS9_CURVE_PRICE_DTL: "+queryString);
	rset = stmt.executeQuery(queryString);
	if(rset.next())
	{
	AvgPriceSettle = rset.getDouble(1);
	PriceAvlCountSettle = rset.getInt(2);
	if(ReportDays>0)
	VSettle_ICE_JKM_AVG.add(nf2.format(AvgPriceSettle)); //Sb20201023
	else
	VSettle_ICE_JKM_AVG.add("");
	
	}
	else
	{
	AvgPriceSettle = 0;
	PriceAvlCountSettle = 1;
	VSettle_ICE_JKM_AVG.add("");
	
	}	
	//System.out.println(SettlePriceDate+" :AvgPriceSettle: " +AvgPriceSettle);
	//System.out.println(VEff_Perc+" :VEff_Perc: " +VEff_Perc.size()+" : "+VContMthWise_gas_date.size());
	////////////////////////////////////////////////////////////////////////////////////
	///System.out.println(VSlope.size()+"VSlope "+VSlope);
	VLNG_ICE_JKM_EXPOBaseValue.add(LNG_ICE_JKM_EXPOBaseValue);
	String DropOff=""+VLNG_ICE_JKM_EXPODropOff.elementAt(i); //SB20201006
	
	Slope=""+Double.parseDouble(""+VSlope.elementAt(i)); 
	String PriceType=""+VPriceTypeDeal.elementAt(i);
	String GasDate=""+VContMthWise_gas_date.elementAt(i);
	String PercReduce=""+VEff_Perc.elementAt(i); //SB20201006
	String CumuPercReduce=""+VEff_CumulativePerc.elementAt(i); //SB20201006
	
	MRCR_ContDtl(HolidayType, Buy_Sell,PriceType,DealPrice, TCQ, Alloc_Qty,GasDate, Gen_Dt, Cont_End_Dt, Phy_CurFrd_Price, Fin_CurFrd_Price, Slope, PriorCurveGasPrice, PercReduce, CumuPercReduce, LNG_ICE_JKM_EXPOBaseValue, DropOff);
	////////////////////////////////////////////////
	}
	sup_tot_comitment=nf.format(sup_tot_comit);
	bal_tot_comitment=nf.format(bal_tot_comit);
	
	//Introduce by Milan 20010916
	//SB20201128	   sup_tot_comitment_mmscm=nf.format(sup_tot_comit_mmscm);
	//SB20201128	   bal_tot_comitment_mmscm=nf.format(bal_tot_comit_mmscm);
	//////////TIME CALCULATION/////////////////////////////////////////////
	CurrDtTm="";
	queryString = "SELECT SYSDATE FROM DUAL";
	rset = stmt.executeQuery(queryString);
	if(rset.next())
	{
		CurrDtTm = rset.getString(1);System.out.println(VSettle_ICE_JKM_AVG.size()+" :<<<<<<FINAL LISTING COMPLETED >>>>>>>>>>>>>>>>>>>>>>.....: "+CurrDtTm);
	}
	//////////********TIME CALCULATION/////////////////////////////////////////////
	/*
	System.out.println(VSettle_ICE_JKM_AVG.size()+"VSettle_ICE_JKM_AVG ");
	System.out.println(VRLNG_PHYS_INDIA_EXPOSURE_U.size()+"VRLNG_PHYS_INDIA_EXPOSURE_U ");
	System.out.println(VSettle_RLNG_PHYS.size()+"VSettle_RLNG_PHYS ");
	System.out.println(VLNG_ICE_JKM_EXPOSURE_U.size()+"VLNG_ICE_JKM_EXPOSURE_U ");
	System.out.println(VSettle_ICE_JKM.size()+"VSettle_ICE_JKM ");
	System.out.println(VConst_ICE_JKM.size()+"VConst_ICE_JKM ");
	System.out.println(VMTM.size()+"VMTM "+VMTM);
	System.out.println(VRealised.size()+"VRealised "+VRealised);
	System.out.println(VTotalMtmRealised.size()+"VTotalMtmRealised "+VTotalMtmRealised);
	
	System.out.println(VLNG_ICE_JKM_EXPODropOff+"VLNG_ICE_JKM_EXPODropOff "+VLNG_ICE_JKM_EXPODropOff.size());		   
	//  System.out.println(VLNG_ICE_JKM_EXPOBaseValue+"VLNG_ICE_JKM_EXPOBaseValue "+VLNG_ICE_JKM_EXPOBaseValue.size());
	System.out.println("VLNG_ICE_JKM_EXPOBaseValue "+VLNG_ICE_JKM_EXPOBaseValue.size());
	
	System.out.println("VMthYrWisePriceICE_JKM "+VMthYrWisePriceICE_JKM.size());
	System.out.println("VPriceTypeDeal "+VPriceTypeDeal.size());
	System.out.println("VPriceType "+VPriceType.size());
	System.out.println("VDealPriceCurve "+VDealPriceCurve.size());
	System.out.println("VDealPhysCurve "+VDealPhysCurve.size());
	System.out.println("VCargoRefNo "+VCargoRefNo.size());
	System.out.println("VDealTCQ "+VDealTCQ.size());
	System.out.println("VRLNG_PHYS_IN_EXPO "+VRLNG_PHYS_IN_EXPO.size());
	System.out.println("VLNG_ICE_JKM_EXPO "+VLNG_ICE_JKM_EXPO.size());
	System.out.println("VRU_Physical_Leg "+VRU_Physical_Leg.size());
	System.out.println("VRLNG_PHYS_INDIA_EXPOSURE_U "+VRLNG_PHYS_INDIA_EXPOSURE_U.size());
	System.out.println("VRU_Financial_Leg "+VRU_Financial_Leg.size());
	System.out.println("VLNG_ICE_JKM_EXPOSURE_U "+VLNG_ICE_JKM_EXPOSURE_U.size());
	System.out.println("VU_Phy_Leg "+VU_Phy_Leg.size());
	System.out.println("VU_Fin_Leg "+VU_Fin_Leg.size());
	System.out.println("VR_Fin_Leg "+VR_Fin_Leg.size());
	System.out.println("VPNL_PHYS_REALISED "+VPNL_PHYS_REALISED.size());
	System.out.println("VPNL_ICE_JKM_REALISED "+VPNL_ICE_JKM_REALISED.size());
	System.out.println("VSettle_ICE_JKM_AVG "+VSettle_ICE_JKM_AVG.size());
	System.out.println("VSettle_ICE_JKM_MTM "+VMTM.size()); 
	System.out.println("VPriceIndex_SettleDate "+VPriceIndex_SettleDate.size());
	System.out.println("VSlope "+VSlope.size());
	System.out.println("VEff_Price: "+VEff_Price.size());
	*/
	}
	catch(Exception e)
	{
	//System.out.println("Exception in Databean_Contract_MasterReport--->fetchcustomer()-->"+e);
	e.printStackTrace();	
	}
}
	//*/////////////////////NEW Fn: HP20201201////////////////////////////////////////////
	public void Fetch_SN_DCQ_LIST()
	{
		try
		{
			String mapp_id = Buyer_cd+"-"+FgsaNo+"-"+FgsaRevNo+"-"+SnNo+"-"+SnRevNo;		
			String contract_type=Cont_Type; String price_type="";
			queryString = "SELECT TO_CHAR(FROM_DT,'DD/MM/YYYY'), TO_CHAR(TO_DT,'DD/MM/YYYY'), DCQ, REMARKS, SLOPE, CONST, PRICE_TYPE, CURVE_NM, RATE, RATE_UNIT, PRICE_RANGE, TO_CHAR(PRICE_START_DT,'DD/MM/YYYY'), TO_CHAR(PRICE_END_DT,'DD/MM/YYYY'),SEQ_NO "
					+ ", PHYS_CURVE_NM,RATE,TO_CHAR(VERY_DT,'DD/MM/YYYY')  " +//SB20210310 //HARSH20210426 RATE AND VARIFY_DT ADDED
						  "FROM FMS9_MRCR_CONT_PRICE_DTL " +
						  "WHERE MAPPING_ID='"+mapp_id+"' AND CONTRACT_TYPE='"+contract_type+"' ORDER BY FROM_DT";
		//	System.out.println("Variable Pricing: FMS9_MRCR_CONT_PRICE_DTL: "+queryString);
			rset = stmt.executeQuery(queryString);			
			while(rset.next())
			{
				sn_Dcq_From_Dt.add(rset.getString(1)==null?"":rset.getString(1));
				sn_Dcq_To_Dt.add(rset.getString(2)==null?"":rset.getString(2));
				sn_Dcq_Value.add(rset.getString(3)==null?"":rset.getString(3));
				sn_Dcq_Remark.add(rset.getString(4)==null?"":rset.getString(4));
				VSlope.add(rset.getString(5)==null?"":rset.getString(5));
				VConst.add(rset.getString(6)==null?"":rset.getString(6));
				price_type=rset.getString(7)==null?"F":rset.getString(7);
				if(price_type.equals("F"))
					VPrice_Type.add("Fixed");
				else
					VPrice_Type.add("Float");
				VCurve_Nm.add(rset.getString(8)==null?"":rset.getString(8));
				VRate.add(rset.getString(9)==null?"":rset.getString(9));
				VRate_Unit.add(rset.getString(10)==null?"":rset.getString(10));
				VPrice_Range.add(rset.getString(11)==null?"":rset.getString(11));
				VPrice_Start_Dt.add(rset.getString(12)==null?"":rset.getString(12));
				VPrice_End_Dt.add(rset.getString(13)==null?"":rset.getString(13));
				//VPrice_Range.add("");
				//VPrice_Start_Dt.add("");
				//VPrice_End_Dt.add("");
				Vsn_DCQ_seq_no.add(rset.getString(14)==null?"":rset.getString(14));
				VPhys_Curve_Nm.add(rset.getString(15)==null?"":rset.getString(15));
				VPPAC_Price.add(rset.getString(16)==null?"":rset.getString(16)); //HARSH20210426
				VPPAC_Validity_Dt.add(rset.getString(17)==null?"":rset.getString(17)); //HARSH20210426
			}
			if(sn_Dcq_From_Dt.size()==0 && !contract_type.equals("B"))
			{
				if(contract_type.equals("S"))
				{
				queryString = "SELECT TO_CHAR(FROM_DT,'DD/MM/YYYY'), TO_CHAR(TO_DT,'DD/MM/YYYY'), DCQ, REMARK " +
					  "FROM FMS7_SN_DCQ_DTL " +
					  "WHERE CUSTOMER_CD="+Buyer_cd+" And FGSA_NO="+FgsaNo+" AND " +
					  "FGSA_REV_NO="+FgsaRevNo+" AND SN_NO="+SnNo+" AND SN_REV_NO="+SnRevNo+" ORDER BY FROM_DT";
		//		System.out.println("EXPO: >>>>>>>FMS7_SN_DCQ_DTL "+queryString);
				}
				else if(contract_type.equals("L"))
				{
					queryString = "SELECT TO_CHAR(FROM_DT,'DD/MM/YYYY'), TO_CHAR(TO_DT,'DD/MM/YYYY'), DCQ, REMARK " +
							  "FROM FMS7_LOA_DCQ_DTL " +
							  "WHERE CUSTOMER_CD="+Buyer_cd+" And TENDER_NO="+FgsaNo+" AND " +
							  "LOA_NO="+SnNo+" AND LOA_REV_NO="+SnRevNo+" ORDER BY FROM_DT";
					//	System.out.println("EXPO: >>>>>>>FMS7_LOA_DCQ_DTL "+queryString);
				}
					rset = stmt.executeQuery(queryString);			
					while(rset.next())
					{
						sn_Dcq_From_Dt.add(rset.getString(1)==null?"":rset.getString(1));
						sn_Dcq_To_Dt.add(rset.getString(2)==null?"":rset.getString(2));
						sn_Dcq_Value.add(rset.getString(3)==null?"":rset.getString(3));
						sn_Dcq_Remark.add(rset.getString(4)==null?"":rset.getString(4));	
						VSlope.add("0");
						VConst.add("0");
						price_type="F";
						if(price_type.equals("F"))
							VPrice_Type.add("Fixed");
						else
							VPrice_Type.add("Float");
						VCurve_Nm.add("");
						VRate.add("");
						VRate_Unit.add("");
						//VPrice_Range.add("");
						//VPrice_Start_Dt.add("");
						//VPrice_End_Dt.add("");
						Vsn_DCQ_seq_no.add("");
					}
			}
			//SBelse if(sn_Dcq_From_Dt.size()==0 && contract_type.equals("B"))
		/*if(sn_Dcq_From_Dt.size()==0)
			{
				sn_Dcq_From_Dt.add("");
				sn_Dcq_To_Dt.add("");
				sn_Dcq_Value.add("");
				sn_Dcq_Remark.add("");	
				VSlope.add("0");
				VConst.add("0");
				price_type="F";
				if(price_type.equals("F"))
					VPrice_Type.add("Fixed");
				else
					VPrice_Type.add("Float");
				VCurve_Nm.add("");
				VRate.add("");
				VRate_Unit.add("");
				VPrice_Range.add("");
				VPrice_Start_Dt.add("");
				VPrice_End_Dt.add("");
			}*/
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	////////////////////SB20210405/////////////////////////////////////////
	public void Var_Price_Phys_Fin_LIST()
	{
		try
		{
			String mapp_id = Buyer_cd+"-"+FgsaNo+"-"+FgsaRevNo+"-"+SnNo+"-"+SnRevNo;		
			String contract_type=Cont_Type; String price_type="";
			if(!contract_type.equals("B")) //SB20210528
				mapp_id = Buyer_cd+"-"+FgsaNo+"-"+FgsaRevNo+"-"+SnNo+"-%";	 //SB20210528
			queryString = "SELECT TO_CHAR(FROM_DT,'DD/MM/YYYY'), TO_CHAR(TO_DT,'DD/MM/YYYY'), DCQ, REMARKS, SLOPE, CONST, PRICE_TYPE, CURVE_NM, RATE, RATE_UNIT, PRICE_RANGE, TO_CHAR(PRICE_START_DT,'DD/MM/YYYY'), TO_CHAR(PRICE_END_DT,'DD/MM/YYYY'),SEQ_NO "
					+ ", PHYS_CURVE_NM,RATE,TO_CHAR(VERY_DT,'DD/MM/YYYY')  " +//SB20210310 //HARSH20210426 RATE AND VARIFY_DT ADDED
						  "FROM FMS9_MRCR_CONT_PRICE_DTL " +
						//SB20210528		  "WHERE MAPPING_ID='"+mapp_id+"' AND CONTRACT_TYPE='"+contract_type+"' ORDER BY FROM_DT";
			"WHERE MAPPING_ID LIKE '"+mapp_id+"' AND CONTRACT_TYPE='"+contract_type+"' ORDER BY FROM_DT"; //SB20210528
		//	System.out.println("Variable Pricing: FMS9_MRCR_CONT_PRICE_DTL: "+queryString);
			rset = stmt.executeQuery(queryString);			
			while(rset.next())
			{
				sn_Dcq_From_Dt.add(rset.getString(1)==null?"":rset.getString(1));
				sn_Dcq_To_Dt.add(rset.getString(2)==null?"":rset.getString(2));
				sn_Dcq_Value.add(rset.getString(3)==null?"":rset.getString(3));
				sn_Dcq_Remark.add(rset.getString(4)==null?"":rset.getString(4));
				VSlope.add(rset.getString(5)==null?"":rset.getString(5));
				VConst.add(rset.getString(6)==null?"":rset.getString(6));
				price_type=rset.getString(7)==null?"F":rset.getString(7);
				if(price_type.equals("F"))
					VPrice_Type.add("Fixed");
				else
					VPrice_Type.add("Float");
				VCurve_Nm.add(rset.getString(8)==null?"":rset.getString(8));
				VRate.add(rset.getString(9)==null?"":rset.getString(9));
				VRate_Unit.add(rset.getString(10)==null?"":rset.getString(10));
				VPrice_Range.add(rset.getString(11)==null?"A":rset.getString(11));
				VPrice_Start_Dt.add(rset.getString(12)==null?"":rset.getString(12));
				VPrice_End_Dt.add(rset.getString(13)==null?"":rset.getString(13));
				//VPrice_Range.add("");
				//VPrice_Start_Dt.add("");
				//VPrice_End_Dt.add("");
				Vsn_DCQ_seq_no.add(rset.getString(14)==null?"":rset.getString(14));
				VPhys_Curve_Nm.add(rset.getString(15)==null?"":rset.getString(15));
				VPPAC_Price.add(rset.getString(16)==null?"":rset.getString(16)); //HARSH20210426
				VPPAC_Validity_Dt.add(rset.getString(17)==null?"":rset.getString(17)); //HARSH20210426
			}
			/*if(sn_Dcq_From_Dt.size()==0 && !contract_type.equals("B"))
			{
				if(contract_type.equals("S"))
				{
				queryString = "SELECT TO_CHAR(FROM_DT,'DD/MM/YYYY'), TO_CHAR(TO_DT,'DD/MM/YYYY'), DCQ, REMARK " +
					  "FROM FMS7_SN_DCQ_DTL " +
					  "WHERE CUSTOMER_CD="+Buyer_cd+" And FGSA_NO="+FgsaNo+" AND " +
					  "FGSA_REV_NO="+FgsaRevNo+" AND SN_NO="+SnNo+" AND SN_REV_NO="+SnRevNo+" ORDER BY FROM_DT";
		//		System.out.println("EXPO: >>>>>>>FMS7_SN_DCQ_DTL "+queryString);
				}
				else if(contract_type.equals("L"))
				{
					queryString = "SELECT TO_CHAR(FROM_DT,'DD/MM/YYYY'), TO_CHAR(TO_DT,'DD/MM/YYYY'), DCQ, REMARK " +
							  "FROM FMS7_LOA_DCQ_DTL " +
							  "WHERE CUSTOMER_CD="+Buyer_cd+" And TENDER_NO="+FgsaNo+" AND " +
							  "LOA_NO="+SnNo+" AND LOA_REV_NO="+SnRevNo+" ORDER BY FROM_DT";
					//	System.out.println("EXPO: >>>>>>>FMS7_LOA_DCQ_DTL "+queryString);
				}
					rset = stmt.executeQuery(queryString);			
					while(rset.next())
					{
						sn_Dcq_From_Dt.add(rset.getString(1)==null?"":rset.getString(1));
						sn_Dcq_To_Dt.add(rset.getString(2)==null?"":rset.getString(2));
						sn_Dcq_Value.add(rset.getString(3)==null?"":rset.getString(3));
						sn_Dcq_Remark.add(rset.getString(4)==null?"":rset.getString(4));	
						VSlope.add("0");
						VConst.add("0");
						price_type="F";
						if(price_type.equals("F"))
							VPrice_Type.add("Fixed");
						else
							VPrice_Type.add("Float");
						VCurve_Nm.add("");
						VRate.add("");
						VRate_Unit.add("");
						//VPrice_Range.add("");
						//VPrice_Start_Dt.add("");
						//VPrice_End_Dt.add("");
						Vsn_DCQ_seq_no.add("");
					}
			}*/
			queryString = "select DISTINCT CURVE_NM from FMS9_FORWARD2_PRICE_DTL "
					+ " where CURVE_TYPE='Forward' AND PHYS_FIN='Physical' AND FLAG='Y' ORDER BY CURVE_NM ";
		//	System.out.println("Eff. AVG: FM FMS9_FORWARD2_PRICE_DTL: "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				VPhysCurveNm.add(rset.getString(1)==null?"":rset.getString(1));
			}
			queryString = "select DISTINCT CURVE_NM from FMS9_FORWARD2_PRICE_DTL "
					+ " where CURVE_TYPE='Forward' AND PHYS_FIN='Financial' AND FLAG='Y' ORDER BY CURVE_NM ";
		//	System.out.println("Eff. AVG: FM FMS9_FORWARD2_PRICE_DTL: "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				VFinCurveNm.add(rset.getString(1)==null?"":rset.getString(1));
			}
			queryString = "select DISTINCT CURVE_NM from FMS9_CURVE2_PRICE_DTL "
					+ " where CURVE_TYPE='Spot' AND PHYS_FIN='Financial' AND FLAG='Y' ORDER BY CURVE_NM ";
		//	System.out.println("Eff. AVG: FM FMS9_CURVE2_PRICE_DTL: "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				VFinCurveNm.add(rset.getString(1)==null?"":rset.getString(1));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		System.out.println(VPhysCurveNm.size()+" :VPhysCurveNm: "+VPhysCurveNm); System.out.println(VFinCurveNm.size()+" ::VFinCurveNm "+VFinCurveNm);
	}
//////////////////////////HP20210729/////////////////////////////
	public void Var_Price_Phys_Fin_LIST2()
	{
		try
		{
			String mapp_id = Buyer_cd+"-"+FgsaNo+"-"+FgsaRevNo+"-"+SnNo+"-"+SnRevNo;		
			String contract_type=Cont_Type; String price_type="";
			if(!contract_type.equals("B")) //SB20210528
				mapp_id = Buyer_cd+"-"+FgsaNo+"-"+FgsaRevNo+"-"+SnNo+"-%";	 //SB20210528
			queryString = "SELECT TO_CHAR(FROM_DT,'DD/MM/YYYY'), TO_CHAR(TO_DT,'DD/MM/YYYY'), DCQ, REMARKS, SLOPE, CONST, PRICE_TYPE, CURVE_NM, RATE, RATE_UNIT, PRICE_RANGE, TO_CHAR(PRICE_START_DT,'DD/MM/YYYY'), TO_CHAR(PRICE_END_DT,'DD/MM/YYYY'),SEQ_NO "
					+ ", PHYS_CURVE_NM,RATE,TO_CHAR(VERY_DT,'DD/MM/YYYY'),MAPPING_ID  " +//SB20210310 //HARSH20210426 RATE AND VARIFY_DT ADDED //HARSH20210728 MAPPING id ADDED
						  "FROM FMS9_MRCR_CONT_PRICE_DTL " +
						//SB20210528		  "WHERE MAPPING_ID='"+mapp_id+"' AND CONTRACT_TYPE='"+contract_type+"' ORDER BY FROM_DT";
			"WHERE MAPPING_ID LIKE '"+mapp_id+"' AND CONTRACT_TYPE='"+contract_type+"' ORDER BY FROM_DT"; //SB20210528
		//	System.out.println("Variable Pricing: FMS9_MRCR_CONT_PRICE_DTL: "+queryString);
			rset = stmt.executeQuery(queryString);			
			while(rset.next())
			{
				sn_Dcq_From_Dt.add(rset.getString(1)==null?"":rset.getString(1));
				sn_Dcq_To_Dt.add(rset.getString(2)==null?"":rset.getString(2));
				sn_Dcq_Value.add(rset.getString(3)==null?"":rset.getString(3));
				sn_Dcq_Remark.add(rset.getString(4)==null?"":rset.getString(4));
				//HARSH20210728 VSlope.add(rset.getString(5)==null?"":rset.getString(5));
				//HARSH20210728 VConst.add(rset.getString(6)==null?"":rset.getString(6));
				price_type=rset.getString(7)==null?"F":rset.getString(7);
				if(price_type.equals("F"))
					VPrice_Type.add("Fixed");
				else
					VPrice_Type.add("Float");
				//HARSH20210728 VCurve_Nm.add(rset.getString(8)==null?"":rset.getString(8));
				VRate.add(rset.getString(9)==null?"":rset.getString(9));
				VRate_Unit.add(rset.getString(10)==null?"":rset.getString(10));
				//HARSH20210820 VPrice_Range.add(rset.getString(11)==null?"A":rset.getString(11));
				VPrice_Start_Dt.add(rset.getString(12)==null?"":rset.getString(12));
				VPrice_End_Dt.add(rset.getString(13)==null?"":rset.getString(13));
				//VPrice_Range.add("");
				//VPrice_Start_Dt.add("");
				//VPrice_End_Dt.add("");
				Vsn_DCQ_seq_no.add(rset.getString(14)==null?"":rset.getString(14));
				VPhys_Curve_Nm.add(rset.getString(15)==null?"":rset.getString(15));
				VPPAC_Price.add(rset.getString(16)==null?"":rset.getString(16)); //HARSH20210426
				VPPAC_Validity_Dt.add(rset.getString(17)==null?"":rset.getString(17)); //HARSH20210426
				
				String seq_no =rset.getString(14)==null?"":rset.getString(14); //HARSH20210820
				//HARSH20210728
				String mm_id=rset.getString(18)==null?"":rset.getString(18);
				String rmk=rset.getString(4)==null?"":rset.getString(4);
				if(!rmk.equals(""))
				{
					String split[] = rmk.split("@");
					String slop_1="",cont_1="",cur_nm_1="",priceRange_1="",date_1="";
					if(split[0].equals("MIN"))
					{
						String slop="",cont="",cur_nm="",priceRange="",priceStDt="",priceEndDt="";
						queryString1="SELECT CURVE_NM,SLOPE,CONST,PRICE_RANGE,TO_CHAR(PRICE_START_DT,'DD/MM/YYYY'),TO_CHAR(PRICE_END_DT,'DD/MM/YYYY') FROM FMS9_MRCR_CONT_FIN_PRICE_DTL WHERE CONTRACT_TYPE='"+contract_type+"' AND MAPPING_ID='"+mm_id+"' AND SEQ_NO='"+seq_no+"' AND FLAG='Y' ";
						rset1=stmt1.executeQuery(queryString1);
						while(rset1.next())
						{
							cur_nm = rset1.getString(1)==null?"":rset1.getString(1);
							slop = rset1.getString(2)==null?"":rset1.getString(2);
							cont = rset1.getString(3)==null?"":rset1.getString(3);
							priceRange=rset1.getString(4)==null?"":rset1.getString(4);
							priceStDt=rset1.getString(5)==null?"":rset1.getString(5); //HARSH20210820
							priceEndDt=rset1.getString(6)==null?"":rset1.getString(6);//HARSH20210820
							
							if(slop_1.equals(""))
							{
								slop_1=slop;
							}
							else
							{
								slop_1+="<br>"+slop;
							}
							if(cont_1.equals(""))
							{
								cont_1=cont;
							}
							else
							{
								cont_1+="<br>"+cont;
							}
							if(cur_nm_1.equals(""))
							{
								cur_nm_1 = cur_nm;
							}
							else
							{
								cur_nm_1 += "<br>"+cur_nm;
							}
							if(priceRange_1.equals(""))//HARSH20210820
							{
								priceRange_1=priceRange;
							}
							else
							{
								priceRange_1+="<br>"+priceRange;
							}
							if(date_1.equals("")) //HARSH20210820
							{
								date_1=priceStDt+"-"+priceEndDt;
							}
							else
							{
								date_1+="<br>"+priceStDt+"-"+priceEndDt;
							}
						}
						VSlope.add(slop_1);
						VConst.add(cont_1);
						VCurve_Nm.add(cur_nm_1);
						VPrice_Range.add(priceRange_1);//HARSH20210820
						VMIN_Price_St_End_dt.add(date_1);//HARSH20210820
					}
					else
					{
						VSlope.add(rset.getString(5)==null?"":rset.getString(5));
						VConst.add(rset.getString(6)==null?"":rset.getString(6));
						VCurve_Nm.add(rset.getString(8)==null?"":rset.getString(8));
						VPrice_Range.add(rset.getString(11)==null?"A":rset.getString(11));//HARSH20210820
						VMIN_Price_St_End_dt.add("");//HARSH20210820
					}
				}
				else
				{
					VSlope.add(rset.getString(5)==null?"":rset.getString(5));
					VConst.add(rset.getString(6)==null?"":rset.getString(6));
					VCurve_Nm.add(rset.getString(8)==null?"":rset.getString(8));
					VPrice_Range.add(rset.getString(11)==null?"A":rset.getString(11));//HARSH20210820
					VMIN_Price_St_End_dt.add("");//HARSH20210820
				}
			}
			/*if(sn_Dcq_From_Dt.size()==0 && !contract_type.equals("B"))
			{
				if(contract_type.equals("S"))
				{
				queryString = "SELECT TO_CHAR(FROM_DT,'DD/MM/YYYY'), TO_CHAR(TO_DT,'DD/MM/YYYY'), DCQ, REMARK " +
					  "FROM FMS7_SN_DCQ_DTL " +
					  "WHERE CUSTOMER_CD="+Buyer_cd+" And FGSA_NO="+FgsaNo+" AND " +
					  "FGSA_REV_NO="+FgsaRevNo+" AND SN_NO="+SnNo+" AND SN_REV_NO="+SnRevNo+" ORDER BY FROM_DT";
		//		System.out.println("EXPO: >>>>>>>FMS7_SN_DCQ_DTL "+queryString);
				}
				else if(contract_type.equals("L"))
				{
					queryString = "SELECT TO_CHAR(FROM_DT,'DD/MM/YYYY'), TO_CHAR(TO_DT,'DD/MM/YYYY'), DCQ, REMARK " +
							  "FROM FMS7_LOA_DCQ_DTL " +
							  "WHERE CUSTOMER_CD="+Buyer_cd+" And TENDER_NO="+FgsaNo+" AND " +
							  "LOA_NO="+SnNo+" AND LOA_REV_NO="+SnRevNo+" ORDER BY FROM_DT";
					//	System.out.println("EXPO: >>>>>>>FMS7_LOA_DCQ_DTL "+queryString);
				}
					rset = stmt.executeQuery(queryString);			
					while(rset.next())
					{
						sn_Dcq_From_Dt.add(rset.getString(1)==null?"":rset.getString(1));
						sn_Dcq_To_Dt.add(rset.getString(2)==null?"":rset.getString(2));
						sn_Dcq_Value.add(rset.getString(3)==null?"":rset.getString(3));
						sn_Dcq_Remark.add(rset.getString(4)==null?"":rset.getString(4));	
						VSlope.add("0");
						VConst.add("0");
						price_type="F";
						if(price_type.equals("F"))
							VPrice_Type.add("Fixed");
						else
							VPrice_Type.add("Float");
						VCurve_Nm.add("");
						VRate.add("");
						VRate_Unit.add("");
						//VPrice_Range.add("");
						//VPrice_Start_Dt.add("");
						//VPrice_End_Dt.add("");
						Vsn_DCQ_seq_no.add("");
					}
			}*/
			queryString = "select DISTINCT CURVE_NM from FMS9_FORWARD2_PRICE_DTL "
					+ " where CURVE_TYPE='Forward' AND PHYS_FIN='Physical' AND FLAG='Y' ORDER BY CURVE_NM ";
		//	System.out.println("Eff. AVG: FM FMS9_FORWARD2_PRICE_DTL: "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				VPhysCurveNm.add(rset.getString(1)==null?"":rset.getString(1));
			}
			queryString = "select DISTINCT CURVE_NM from FMS9_FORWARD2_PRICE_DTL "
					+ " where CURVE_TYPE='Forward' AND PHYS_FIN='Financial' AND FLAG='Y' "
					+ " AND CURVE_NM IN (SELECT DISTINCT CURVE_NM FROM FMS9_SETTLE_CALND_DTL WHERE FLAG='Y') " //SB20210825
					+ " ORDER BY CURVE_NM ";
		//	System.out.println("Eff. AVG: FM FMS9_FORWARD2_PRICE_DTL: "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				VFinCurveNm.add(rset.getString(1)==null?"":rset.getString(1));
			}
			/*SB20210825 queryString = "select DISTINCT CURVE_NM from FMS9_CURVE2_PRICE_DTL "
					+ " where CURVE_TYPE='Spot' AND UPPER(PHYS_FIN)='FINANCIAL' AND FLAG='Y' "
					+ "ORDER BY CURVE_NM ";
			System.out.println("Eff. AVG: FM FMS9_CURVE2_PRICE_DTL: "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next()) 
			{
				VFinCurveNm.add(rset.getString(1)==null?"":rset.getString(1));
			}*/
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		System.out.println(VPhysCurveNm.size()+" :VPhysCurveNm: "+VPhysCurveNm); System.out.println(VFinCurveNm.size()+" ::VFinCurveNm "+VFinCurveNm);
}
	
	//NEW FUNCATION HARSH20210903
	public void Var_Price_Phys_Fin_LIST2_V2()
	{
		try
		{
			String mapp_id = Buyer_cd+"-"+FgsaNo+"-"+FgsaRevNo+"-"+SnNo+"-"+SnRevNo;		
			String contract_type=Cont_Type; String price_type="";
			if(!contract_type.equals("B")) //SB20210528
				mapp_id = Buyer_cd+"-"+FgsaNo+"-"+FgsaRevNo+"-"+SnNo+"-%";	 //SB20210528
			queryString = "SELECT TO_CHAR(FROM_DT,'DD/MM/YYYY'), TO_CHAR(TO_DT,'DD/MM/YYYY'), DCQ, REMARKS, SLOPE, CONST, PRICE_TYPE, CURVE_NM, RATE, RATE_UNIT, PRICE_RANGE, TO_CHAR(PRICE_START_DT,'DD/MM/YYYY'), TO_CHAR(PRICE_END_DT,'DD/MM/YYYY'),SEQ_NO "
					+ ", PHYS_CURVE_NM,RATE,TO_CHAR(VERY_DT,'DD/MM/YYYY'),MAPPING_ID  " +//SB20210310 //HARSH20210426 RATE AND VARIFY_DT ADDED //HARSH20210728 MAPPING id ADDED
						  "FROM FMS9_MRCR_CONT_PRICE_DTL " +
						//SB20210528		  "WHERE MAPPING_ID='"+mapp_id+"' AND CONTRACT_TYPE='"+contract_type+"' ORDER BY FROM_DT";
			"WHERE MAPPING_ID LIKE '"+mapp_id+"' AND CONTRACT_TYPE='"+contract_type+"' ORDER BY FROM_DT"; //SB20210528
		//	System.out.println("Variable Pricing: FMS9_MRCR_CONT_PRICE_DTL: "+queryString);
			rset = stmt.executeQuery(queryString);			
			while(rset.next())
			{
				sn_Dcq_From_Dt.add(rset.getString(1)==null?"":rset.getString(1));
				sn_Dcq_To_Dt.add(rset.getString(2)==null?"":rset.getString(2));
				sn_Dcq_Value.add(rset.getString(3)==null?"":rset.getString(3));
				sn_Dcq_Remark.add(rset.getString(4)==null?"":rset.getString(4));
				//HARSH20210728 VSlope.add(rset.getString(5)==null?"":rset.getString(5));
				//HARSH20210728 VConst.add(rset.getString(6)==null?"":rset.getString(6));
				price_type=rset.getString(7)==null?"F":rset.getString(7);
				if(price_type.equals("F"))
					VPrice_Type.add("Fixed");
				else
					VPrice_Type.add("Float");
				//HARSH20210728 VCurve_Nm.add(rset.getString(8)==null?"":rset.getString(8));
				VRate.add(rset.getString(9)==null?"":rset.getString(9));
				VRate_Unit.add(rset.getString(10)==null?"":rset.getString(10));
				//HARSH20210820 VPrice_Range.add(rset.getString(11)==null?"A":rset.getString(11));
				VPrice_Start_Dt.add(rset.getString(12)==null?"":rset.getString(12));
				VPrice_End_Dt.add(rset.getString(13)==null?"":rset.getString(13));
				//VPrice_Range.add("");
				//VPrice_Start_Dt.add("");
				//VPrice_End_Dt.add("");
				Vsn_DCQ_seq_no.add(rset.getString(14)==null?"":rset.getString(14));
				VPhys_Curve_Nm.add(rset.getString(15)==null?"":rset.getString(15));
				VPPAC_Price.add(rset.getString(16)==null?"":rset.getString(16)); //HARSH20210426
				VPPAC_Validity_Dt.add(rset.getString(17)==null?"":rset.getString(17)); //HARSH20210426
				
				String seq_no =rset.getString(14)==null?"":rset.getString(14); //HARSH20210820
				//HARSH20210728
				String mm_id=rset.getString(18)==null?"":rset.getString(18);
				String rmk=rset.getString(4)==null?"":rset.getString(4);
				if(!rmk.equals(""))
				{
					String split[] = rmk.split("@");
					String slop_1="",cont_1="",cur_nm_1="",priceRange_1="",date_1="";
					if(split[0].equals("MIN"))
					{
						String slop="",cont="",cur_nm="",priceRange="",priceStDt="",priceEndDt="";
						queryString1="SELECT CURVE_NM,SLOPE,CONST,PRICE_RANGE,TO_CHAR(PRICE_START_DT,'DD/MM/YYYY'),TO_CHAR(PRICE_END_DT,'DD/MM/YYYY') FROM FMS9_MRCR_CONT_FIN_PRICE_DTL WHERE CONTRACT_TYPE='"+contract_type+"' AND MAPPING_ID='"+mm_id+"' AND SEQ_NO='"+seq_no+"' AND FLAG='Y' ";
						rset1=stmt1.executeQuery(queryString1);
						while(rset1.next())
						{
							cur_nm = rset1.getString(1)==null?"":rset1.getString(1);
							slop = rset1.getString(2)==null?"":rset1.getString(2);
							cont = rset1.getString(3)==null?"":rset1.getString(3);
							priceRange=rset1.getString(4)==null?"":rset1.getString(4);
							priceStDt=rset1.getString(5)==null?"":rset1.getString(5); //HARSH20210820
							priceEndDt=rset1.getString(6)==null?"":rset1.getString(6);//HARSH20210820
							
							if(slop_1.equals(""))
							{
								slop_1=slop;
							}
							else
							{
								slop_1+="<br>"+slop;
							}
							if(cont_1.equals(""))
							{
								cont_1=cont;
							}
							else
							{
								cont_1+="<br>"+cont;
							}
							if(cur_nm_1.equals(""))
							{
								cur_nm_1 = cur_nm;
							}
							else
							{
								cur_nm_1 += "<br>"+cur_nm;
							}
							if(priceRange_1.equals(""))//HARSH20210820
							{
								priceRange_1=priceRange;
							}
							else
							{
								priceRange_1+="<br>"+priceRange;
							}
							if(date_1.equals("")) //HARSH20210820
							{
								date_1=priceStDt+"-"+priceEndDt;
							}
							else
							{
								date_1+="<br>"+priceStDt+"-"+priceEndDt;
							}
						}
						VSlope.add(slop_1);
						VConst.add(cont_1);
						VCurve_Nm.add(cur_nm_1);
						VPrice_Range.add(priceRange_1);//HARSH20210820
						VMIN_Price_St_End_dt.add(date_1);//HARSH20210820
					}
					else
					{
						VSlope.add(rset.getString(5)==null?"":rset.getString(5));
						VConst.add(rset.getString(6)==null?"":rset.getString(6));
						VCurve_Nm.add(rset.getString(8)==null?"":rset.getString(8));
						VPrice_Range.add(rset.getString(11)==null?"A":rset.getString(11));//HARSH20210820
						VMIN_Price_St_End_dt.add("");//HARSH20210820
					}
				}
				else
				{
					VSlope.add(rset.getString(5)==null?"":rset.getString(5));
					VConst.add(rset.getString(6)==null?"":rset.getString(6));
					VCurve_Nm.add(rset.getString(8)==null?"":rset.getString(8));
					VPrice_Range.add(rset.getString(11)==null?"A":rset.getString(11));//HARSH20210820
					VMIN_Price_St_End_dt.add("");//HARSH20210820
				}
				
				//HARSH20210902
				String phys_fin_curve_nm="",phys_slope="",phys_cont="",phys_priceRange="",phys_priceStDt="",phys_priceEndDt="";
				String phys_fin_curve_nm_1="",phys_slope_1="",phys_cont_1="",phys_priceRange_1="",phys_date_1="";
				queryString1="SELECT CURVE_NM,SLOPE,CONST,PRICE_RANGE,TO_CHAR(PRICE_START_DT,'DD/MM/YYYY'),TO_CHAR(PRICE_END_DT,'DD/MM/YYYY') FROM FMS9_MRCR_CONT_PHY_PRICE_DTL WHERE CONTRACT_TYPE='"+contract_type+"' AND MAPPING_ID='"+mm_id+"' AND SEQ_NO='"+seq_no+"' AND FLAG='Y'";
				rset1=stmt1.executeQuery(queryString1);
				while(rset1.next())
				{
					phys_fin_curve_nm = rset1.getString(1)==null?"":rset1.getString(1);
					phys_slope = rset1.getString(2)==null?"":rset1.getString(2);
					phys_cont = rset1.getString(3)==null?"":rset1.getString(3);
					phys_priceRange=rset1.getString(4)==null?"":rset1.getString(4);
					phys_priceStDt=rset1.getString(5)==null?"":rset1.getString(5); 
					phys_priceEndDt=rset1.getString(6)==null?"":rset1.getString(6);
					
					if(phys_fin_curve_nm_1.equals(""))
					{
						phys_fin_curve_nm_1=phys_fin_curve_nm;
					}
					else
					{
						phys_fin_curve_nm_1+="<br>"+phys_fin_curve_nm;
					}
					if(phys_cont_1.equals(""))
					{
						phys_cont_1=phys_cont;
					}
					else
					{
						phys_cont_1+="<br>"+phys_cont;
					}
					if(phys_slope_1.equals(""))
					{
						phys_slope_1=phys_slope;
					}
					else
					{
						phys_slope_1+="<br>"+phys_slope;
					}
					if(phys_priceRange_1.equals(""))
					{
						phys_priceRange_1=phys_priceRange;
					}
					else
					{
						phys_priceRange_1+="<br>"+phys_priceRange;
					}
					if(phys_date_1.equals("")) 
					{
						phys_date_1=phys_priceStDt+"-"+phys_priceEndDt;
					}
					else
					{
						phys_date_1+="<br>"+phys_priceStDt+"-"+phys_priceEndDt;
					}
				}
				
				VPhysFinCurveNm.add(phys_fin_curve_nm_1);
				VPhys_Slope.add(phys_slope_1);
				VPhys_Const.add(phys_cont_1);
				VPhys_Price_Range.add(phys_priceRange_1);
				VPhys_Price_St_End_dt.add(phys_date_1);
			}
			/*if(sn_Dcq_From_Dt.size()==0 && !contract_type.equals("B"))
			{
				if(contract_type.equals("S"))
				{
				queryString = "SELECT TO_CHAR(FROM_DT,'DD/MM/YYYY'), TO_CHAR(TO_DT,'DD/MM/YYYY'), DCQ, REMARK " +
					  "FROM FMS7_SN_DCQ_DTL " +
					  "WHERE CUSTOMER_CD="+Buyer_cd+" And FGSA_NO="+FgsaNo+" AND " +
					  "FGSA_REV_NO="+FgsaRevNo+" AND SN_NO="+SnNo+" AND SN_REV_NO="+SnRevNo+" ORDER BY FROM_DT";
		//		System.out.println("EXPO: >>>>>>>FMS7_SN_DCQ_DTL "+queryString);
				}
				else if(contract_type.equals("L"))
				{
					queryString = "SELECT TO_CHAR(FROM_DT,'DD/MM/YYYY'), TO_CHAR(TO_DT,'DD/MM/YYYY'), DCQ, REMARK " +
							  "FROM FMS7_LOA_DCQ_DTL " +
							  "WHERE CUSTOMER_CD="+Buyer_cd+" And TENDER_NO="+FgsaNo+" AND " +
							  "LOA_NO="+SnNo+" AND LOA_REV_NO="+SnRevNo+" ORDER BY FROM_DT";
					//	System.out.println("EXPO: >>>>>>>FMS7_LOA_DCQ_DTL "+queryString);
				}
					rset = stmt.executeQuery(queryString);			
					while(rset.next())
					{
						sn_Dcq_From_Dt.add(rset.getString(1)==null?"":rset.getString(1));
						sn_Dcq_To_Dt.add(rset.getString(2)==null?"":rset.getString(2));
						sn_Dcq_Value.add(rset.getString(3)==null?"":rset.getString(3));
						sn_Dcq_Remark.add(rset.getString(4)==null?"":rset.getString(4));	
						VSlope.add("0");
						VConst.add("0");
						price_type="F";
						if(price_type.equals("F"))
							VPrice_Type.add("Fixed");
						else
							VPrice_Type.add("Float");
						VCurve_Nm.add("");
						VRate.add("");
						VRate_Unit.add("");
						//VPrice_Range.add("");
						//VPrice_Start_Dt.add("");
						//VPrice_End_Dt.add("");
						Vsn_DCQ_seq_no.add("");
					}
			}*/
			queryString = "select DISTINCT CURVE_NM from FMS9_FORWARD2_PRICE_DTL "
					+ " where CURVE_TYPE='Forward' AND PHYS_FIN='Physical' AND FLAG='Y' ORDER BY CURVE_NM ";
		//	System.out.println("Eff. AVG: FM FMS9_FORWARD2_PRICE_DTL: "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				VPhysCurveNm.add(rset.getString(1)==null?"":rset.getString(1));
			}
			queryString = "select DISTINCT CURVE_NM from FMS9_FORWARD2_PRICE_DTL "
					+ " where CURVE_TYPE='Forward' AND PHYS_FIN='Financial' AND FLAG='Y' "
					+ " AND CURVE_NM IN (SELECT DISTINCT CURVE_NM FROM FMS9_SETTLE_CALND_DTL WHERE FLAG='Y') " //SB20210825
					+ " ORDER BY CURVE_NM ";
		//	System.out.println("Eff. AVG: FM FMS9_FORWARD2_PRICE_DTL: "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				VFinCurveNm.add(rset.getString(1)==null?"":rset.getString(1));
			}
			/*SB20210825 queryString = "select DISTINCT CURVE_NM from FMS9_CURVE2_PRICE_DTL "
					+ " where CURVE_TYPE='Spot' AND UPPER(PHYS_FIN)='FINANCIAL' AND FLAG='Y' "
					+ "ORDER BY CURVE_NM ";
			System.out.println("Eff. AVG: FM FMS9_CURVE2_PRICE_DTL: "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next()) 
			{
				VFinCurveNm.add(rset.getString(1)==null?"":rset.getString(1));
			}*/
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		System.out.println(VPhysCurveNm.size()+" :VPhysCurveNm: "+VPhysCurveNm); System.out.println(VFinCurveNm.size()+" ::VFinCurveNm "+VFinCurveNm);
}

	///////////////////////^^^HP20210729/////////////////////////////
	Vector VPPAC_Price = new Vector();  //HARSH20210426
    public Vector getVPPAC_Price() {return VPPAC_Price;} //HARSH20210426
    Vector VPPAC_Validity_Dt = new Vector();  //HARSH20210426
    public Vector getVPPAC_Validity_Dt() {return VPPAC_Validity_Dt;} //HARSH20210426

    Vector VMIN_Price_St_End_dt = new Vector();  //HARSH20210820
    public Vector getVMIN_Price_St_End_dt() {return VMIN_Price_St_End_dt;} //HARSH20210820
    
	Vector VPhysCurveNm = new Vector(); 
	public Vector getVPhysCurveNm() {return VPhysCurveNm;}
	Vector VFinCurveNm = new Vector(); 
	public Vector getVFinCurveNm() {return VFinCurveNm;}
	
	Vector VPhysFinCurveNm = new Vector();  //HARSH20210902
	public Vector getVPhysFinCurveNm() {return VPhysFinCurveNm;} //HARSH20210902
	Vector VPhys_Slope = new Vector(); //HARSH20210902
	Vector VPhys_Const = new Vector(); //HARSH20210902
	Vector VPhys_Price_Range = new Vector(); //HARSH20210902
	Vector VPhys_Price_St_End_dt = new Vector(); //HARSH20210902
	
	public Vector getVPhys_Slope() { //HARSH20210902
		return VPhys_Slope;
	}

	public Vector getVPhys_Const() { //HARSH20210902
		return VPhys_Const;
	}

	public Vector getVPhys_Price_Range() { //HARSH20210902
		return VPhys_Price_Range;
	}

	public Vector getVPhys_Price_St_End_dt() { //HARSH20210902
		return VPhys_Price_St_End_dt;
	}
	/////////////////////////^SB20210405//////////////////////////////////
	
	//HARSH20210602 NEW FUNCTION FOR CHECKING DATA IS AVAILABLE FOR THE SELECTED DATE IF YES THEN DELETE ALL
	public void deleteEoDProcessData(String ReportDt)
	{
		try
		{
			int count= 0;
			queryString = "SELECT COUNT(DEAL_ID) from FMS9_EOD_EXPOSURE_DTL "
					+ " WHERE TO_DATE(REPORT_DT, 'DD-MON-YY')=TO_DATE(to_date('"+ReportDt+"','dd/mm/yyyy'), 'DD-MON-YY') ";
			System.out.println("--> FMS9_CURVE_PRICE_DTL : "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				count = rset.getInt(1);
			}
			if(count > 0)
			{
				queryString="DELETE  from FMS9_EOD_EXPOSURE_DTL "
				+ " WHERE TO_DATE(REPORT_DT, 'DD-MON-YY')=TO_DATE(to_date('"+ReportDt+"','dd/mm/yyyy'), 'DD-MON-YY') ";
				stmt.executeUpdate(queryString);
				System.out.println("FMS9_EOD_EXPOSURE_DTL Data Delete Successfully for the Report Date :: "+ReportDt);
			}
			
			int count1= 0;
			queryString = "SELECT COUNT(DEAL_ID) from FMS9_EOD_EXPOSURE_MST "
					+ " WHERE TO_DATE(REPORT_DT, 'DD-MON-YY')=TO_DATE(to_date('"+ReportDt+"','dd/mm/yyyy'), 'DD-MON-YY') ";
			System.out.println("--> FMS9_CURVE_PRICE_MST : "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				count1 = rset.getInt(1);
			}
			if(count1 > 0)
			{
				queryString="DELETE  from FMS9_EOD_EXPOSURE_MST "
				+ " WHERE TO_DATE(REPORT_DT, 'DD-MON-YY')=TO_DATE(to_date('"+ReportDt+"','dd/mm/yyyy'), 'DD-MON-YY') ";
				stmt.executeUpdate(queryString);
				System.out.println("FMS9_EOD_EXPOSURE_MST Data Delete Successfully for the Report Date :: "+ReportDt);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void MarketExposureDealWiseFreezeDtl(String DealId, String ReportDt)
	{
		try
		{			
			String DealBuySell="";
			String ContType=""+DealId;	
			ContType=""+ContType.substring(0,1);	
			if(!ContType.equals("B")) DealBuySell="Sell";
			else DealBuySell="Buy";
		//SB20210310	if(PriceIndexName.equals("-")) PriceIndexName="RLNG_PHYS_INDIA";
			if(PriceIndexName.equals("-")) PriceIndexName=""; //SB20210310 
			if(ContType.equals("O")) DealBuySell="Buy"; //SB20210209
			
			String user_cd=Emp_cd;
			double TOT_DCQ=0;  double TOT_ALLOC_QTY=0; double DCQ=0;  
			double ORI_EXPO_PHY=0; double ORI_EXPO_FIN=0; double UNR_EXPO_PHY=0; double UNR_EXPO_FIN=0;	double R_EXPO_PHY=0; double R_EXPO_FIN=0; 
			double UNR_PHY_LEG=0; double UNR_FIN_LEG=0; double R_FIN_LEG=0; double MTM_TOTAL=0;
		/*SB20210604	///////////////////////////////////////////////////////
			String CurrentDealId=""+DealId;	
			String PrevDealId=""+DealId;	
			String tempDealId[]=DealId.split("-");	
			String temp_fgsa_no=tempDealId[1];
			String temp_fgsa_rev_no=tempDealId[2];
			String temp_sn_no=tempDealId[3];
			String temp_sn_rev_no=tempDealId[4];
			String temp_cust_cd=tempDealId[0];
			if(Integer.parseInt(temp_sn_rev_no)>0)
				temp_sn_rev_no=""+(Integer.parseInt(temp_sn_rev_no)-1);
			System.out.println("CURRENT DealId: "+CurrentDealId);
			if(!ContType.equals("B") && !ContType.equals("O")) 
				PrevDealId = temp_cust_cd+"-"+temp_fgsa_no+"-"+temp_fgsa_rev_no+"-"+temp_sn_no+"-"+temp_sn_rev_no;	 //SB20210528
			System.out.println("Prev DealId: "+PrevDealId);
			/////////////////^^^^^^^//////////////////////////////////////
*/			int Count=0;
			queryString = "SELECT COUNT(DEAL_ID) from FMS9_EOD_EXPOSURE_DTL "
					+ " where TO_DATE(REPORT_DT, 'DD-MON-YY')=TO_DATE(to_date('"+ReportDt+"','dd/mm/yyyy'), 'DD-MON-YY') AND CUSTOMER_CD='"+DealCustCd+"' AND DEAL_ID='"+DealId+"' AND FLAG='Y' ";
			System.out.println("PREV -->  FMS9_CURVE_PRICE_DTL: "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				Count = rset.getInt(1);
			//SB20210604	DealId=PrevDealId;
			}
/*HARSH20210602			if(Count==0)
			{
				queryString = "SELECT COUNT(DEAL_ID) from FMS9_EOD_EXPOSURE_DTL "
						+ " where TO_DATE(REPORT_DT, 'DD-MON-YY')=TO_DATE(to_date('"+ReportDt+"','dd/mm/yyyy'), 'DD-MON-YY') AND CUSTOMER_CD='"+DealCustCd+"' AND DEAL_ID='"+CurrentDealId+"' AND FLAG='Y' ";
				System.out.println("CURRENT-->  FMS9_CURVE_PRICE_DTL: "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					Count = rset.getInt(1);
				}
			}
			if(Count>0)
			{
				PrevDealId = temp_cust_cd+"-"+temp_fgsa_no+"-"+temp_fgsa_rev_no+"-"+temp_sn_no+"-%";	 //SB20210528
				String QueryDel="DELETE  from FMS9_EOD_EXPOSURE_DTL "
						//SB20210528					+ " where TO_DATE(REPORT_DT, 'DD-MON-YY')=TO_DATE(to_date('"+ReportDt+"','dd/mm/yyyy'), 'DD-MON-YY') AND CUSTOMER_CD='"+DealCustCd+"' AND DEAL_ID='"+DealId+"' AND FLAG='Y' ";
						+ " where TO_DATE(REPORT_DT, 'DD-MON-YY')=TO_DATE(to_date('"+ReportDt+"','dd/mm/yyyy'), 'DD-MON-YY') AND DEAL_ID LIKE '"+PrevDealId+"' AND FLAG='Y' ";
				System.out.println("DELETE -->  FMS9_CURVE_PRICE_DTL: "+queryString);
				stmt.execute(QueryDel);	
				Count=0;
			}
*/			
			if(Count==0)
			{
				for(int i=0; i<SIGNING_DT.size(); i++)
				{
					if(DealBuySell.equals("Sell")) 
						DCQ=(-1)*Double.parseDouble(""+VDealDCQ.elementAt(i));
					else
						DCQ=Double.parseDouble(""+VDealDCQ.elementAt(i));
					String ContMth="01/"+VMthYrWise.elementAt(i);
					if(VMthYrWise.elementAt(i).equals("")) ContMth="";
					String QueryEoD="insert into FMS9_EOD_EXPOSURE_DTL (CUSTOMER_CD,DEAL_ID, REPORT_DT, DELV_DT,DCQ,PRICE_TYPE,CURVE_NM,ALLOC_QTY, CONT_PRICE, "
							+ "INDEX_SETTLE_DT,INDEX_START_DT,INDEX_END_DT,SLOPE,CONST,EFF_DEAL_PRICE,CONT_MTH,ORI_EXPO_PHY,ORI_EXPO_FIN,RU_PHY_FLAG,RU_FIN_FLAG, "
							+ "UNR_EXPO_PHY,UNR_EXPO_FIN,R_EXPO_PHY,R_EXPO_FIN, FWD_PRICE_DT,FWD_PRICE_PHY,FWD_PRICE_FIN,SETTLE_PRICE,UNR_PHY_LEG,UNR_FIN_LEG,R_FIN_LEG, MTM_TOTAL, ENT_BY, ENT_DT,FLAG, PHYS_CURVE_NM)"
							+ " values ('"+CUSTOMER_CD.elementAt(i)+"','"+DealId+"',TO_DATE('"+ReportDt+"','DD/MM/YYYY'),TO_DATE('"+SIGNING_DT.elementAt(i)+"','DD/MM/YYYY'), '"+DCQ+"','"+VPriceTypeDeal.elementAt(i)+"','"+PriceIndexName+"','"+VDealAllocQty.elementAt(i)+"','"+VPriceTypeDealRate.elementAt(i)+"',"
							+ "TO_DATE('"+VPriceIndex_SettleDate.elementAt(i)+"','DD/MM/YYYY'), TO_DATE('"+VPriceIndex_StartDate.elementAt(i)+"','DD/MM/YYYY'),TO_DATE('"+VPriceIndex_EndDate.elementAt(i)+"','DD/MM/YYYY'),'"+VSlope_ICE_JKM.elementAt(i)+"','"+VConst_ICE_JKM.elementAt(i)+"','"+VEff_Price.elementAt(i)+"',"
							+ " TO_DATE('"+ContMth+"','DD/MM/YYYY'),'"+VRLNG_PHYS_IN_EXPO.elementAt(i)+"','"+VLNG_ICE_JKM_EXPO.elementAt(i)+"','"+VRU_Physical_Leg.elementAt(i)+"','"+VRU_Financial_Leg.elementAt(i)+"',"
							+ " '"+VRLNG_PHYS_INDIA_EXPOSURE_U.elementAt(i)+"','"+VLNG_ICE_JKM_EXPOSURE_U.elementAt(i)+"','"+VPNL_PHYS_REALISED.elementAt(i)+"','"+VPNL_ICE_JKM_REALISED.elementAt(i)+"',TO_DATE('"+FrdPriceEntDt+"','DD/MM/YYYY'), "
							+ "'"+VSettle_RLNG_PHYS.elementAt(i)+"','"+VSettle_ICE_JKM.elementAt(i)+"','"+VSettle_ICE_JKM_AVG.elementAt(i)+"','"+VU_Phy_Leg.elementAt(i)+"','"+VU_Fin_Leg.elementAt(i)+"','"+VRealised.elementAt(i)+"','"+VTotalMtmRealised.elementAt(i)+"',"
							//SB20210310	+ "'"+user_cd+"',sysdate,'Y')";	
							+ "'"+user_cd+"',sysdate,'Y','"+PricePHYSName+"')";	//SB20210310
				//	System.out.println("FMS9_EOD_EXPOSURE_DTL: "+QueryEoD);
					stmt.execute(QueryEoD);
					TOT_DCQ+=DCQ; 
					TOT_ALLOC_QTY+=Double.parseDouble(""+VDealAllocQty.elementAt(i)); 
					if(!VRLNG_PHYS_IN_EXPO.elementAt(i).equals("")) {
						ORI_EXPO_PHY+=Double.parseDouble(""+VRLNG_PHYS_IN_EXPO.elementAt(i));  ORI_EXPO_PHY=Double.parseDouble(nf.format(ORI_EXPO_PHY));
					}
					if(!VLNG_ICE_JKM_EXPO.elementAt(i).equals("")) {
						ORI_EXPO_FIN+=Double.parseDouble(""+VLNG_ICE_JKM_EXPO.elementAt(i));  ORI_EXPO_FIN=Double.parseDouble(nf.format(ORI_EXPO_FIN)); }
					if(!VRLNG_PHYS_INDIA_EXPOSURE_U.elementAt(i).equals("")) {
						UNR_EXPO_PHY+=Double.parseDouble(""+VRLNG_PHYS_INDIA_EXPOSURE_U.elementAt(i));  UNR_EXPO_PHY=Double.parseDouble(nf.format(UNR_EXPO_PHY));
					}
					if(!VLNG_ICE_JKM_EXPOSURE_U.elementAt(i).equals("")) {
						UNR_EXPO_FIN+=Double.parseDouble(""+VLNG_ICE_JKM_EXPOSURE_U.elementAt(i)); UNR_EXPO_FIN=Double.parseDouble(nf.format(UNR_EXPO_FIN));
					}
					if(!VPNL_PHYS_REALISED.elementAt(i).equals("")) {
						R_EXPO_PHY+=Double.parseDouble(""+VPNL_PHYS_REALISED.elementAt(i)); R_EXPO_PHY=Double.parseDouble(nf.format(R_EXPO_PHY));
					}
					if(!VPNL_ICE_JKM_REALISED.elementAt(i).equals("")) {
						R_EXPO_FIN+=Double.parseDouble(""+VPNL_ICE_JKM_REALISED.elementAt(i)); R_EXPO_FIN=Double.parseDouble(nf.format(R_EXPO_FIN));
					}
					if(!VU_Phy_Leg.elementAt(i).equals("")) {
						UNR_PHY_LEG+=Double.parseDouble(""+VU_Phy_Leg.elementAt(i)); UNR_PHY_LEG=Double.parseDouble(nf.format(UNR_PHY_LEG));
					}
					if(!VU_Fin_Leg.elementAt(i).equals("")) {
						UNR_FIN_LEG+=Double.parseDouble(""+VU_Fin_Leg.elementAt(i)); UNR_FIN_LEG=Double.parseDouble(nf.format(UNR_FIN_LEG));
					}
					if(!VRealised.elementAt(i).equals("")) {
						R_FIN_LEG+=Double.parseDouble(""+VRealised.elementAt(i)); R_FIN_LEG=Double.parseDouble(nf.format(R_FIN_LEG));
					}
					if(!VTotalMtmRealised.elementAt(i).equals("")) {
						MTM_TOTAL+=Double.parseDouble(""+VTotalMtmRealised.elementAt(i));MTM_TOTAL=Double.parseDouble(nf.format(MTM_TOTAL));
					}
				}	
				String CustomerName=""+Deal_Cust_AbbrNm; String DealRefNo=""+Deal_Ref_No; String DealStartDate=""+Deal_Start_Dt; String DealEndDate=""+Deal_End_Dt; String DealPriceType="";
		//	System.out.println("Updated into FMS9_EOD_EXPOSURE_DTL: No. of Records: "+SIGNING_DT.size());
				Count =0;
				queryString = "SELECT COUNT(DEAL_ID) from FMS9_EOD_EXPOSURE_MST "
						+ " where TO_DATE(REPORT_DT, 'DD-MON-YY')=TO_DATE(to_date('"+ReportDt+"','dd/mm/yyyy'), 'DD-MON-YY') AND DEAL_ID='"+DealId+"' AND FLAG='Y' ";
			//	System.out.println("FMS9_CURVE_PRICE_DTL: "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					Count = rset.getInt(1);
					//DealId=PrevDealId;
				}
			/*HARSH20210602	if(Count==0)
				{
					queryString = "SELECT COUNT(DEAL_ID) from FMS9_EOD_EXPOSURE_MST "
							+ " where TO_DATE(REPORT_DT, 'DD-MON-YY')=TO_DATE(to_date('"+ReportDt+"','dd/mm/yyyy'), 'DD-MON-YY') AND CUSTOMER_CD='"+DealCustCd+"' AND DEAL_ID='"+CurrentDealId+"' AND FLAG='Y' ";
					System.out.println("CURRENT-->  FMS9_CURVE_PRICE_DTL: "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						Count = rset.getInt(1);
					}
				}
				if(Count>0)
				{
					PrevDealId = temp_cust_cd+"-"+temp_fgsa_no+"-"+temp_fgsa_rev_no+"-"+temp_sn_no+"-%";	 //SB20210528
					String QueryDel="DELETE  from FMS9_EOD_EXPOSURE_MST "
							//SB20210528					+ " where TO_DATE(REPORT_DT, 'DD-MON-YY')=TO_DATE(to_date('"+ReportDt+"','dd/mm/yyyy'), 'DD-MON-YY') AND CUSTOMER_CD='"+DealCustCd+"' AND DEAL_ID='"+DealId+"' AND FLAG='Y' ";
							+ " where TO_DATE(REPORT_DT, 'DD-MON-YY')=TO_DATE(to_date('"+ReportDt+"','dd/mm/yyyy'), 'DD-MON-YY') AND DEAL_ID LIKE '"+PrevDealId+"' AND FLAG='Y' ";
					System.out.println("DELETE -->  FMS9_CURVE_PRICE_DTL: "+QueryDel);
					stmt.execute(QueryDel);	
					Count=0;
				}  */
				
				if(Count==0)
				{
				String QueryEoD="insert into FMS9_EOD_EXPOSURE_MST (CUSTOMER_CD, DEAL_ID, REPORT_DT, CUSTOMER_NM, DEAL_REF_NO, BUY_SELL, DEAL_SIGN_DT, DEAL_START_DT, DEAL_END_DT, TOT_DCQ, TOT_ALLOC_QTY, PRICE_TYPE, CURVE_NM,"
			//SB20210310			+ "TOT_ORI_EXPO_PHY,TOT_ORI_EXPO_FIN,TOT_UNR_EXPO_PHY,TOT_UNR_EXPO_FIN,TOT_R_EXPO_PHY,TOT_R_EXPO_FIN,TOT_UNR_PHY_LEG,TOT_UNR_FIN_LEG,TOT_R_FIN_LEG, TOT_MTM_TOTAL, FWD_PRICE_DT, ENT_BY, ENT_DT,APRV_BY, APRV_DT,FLAG)"
					+ "TOT_ORI_EXPO_PHY,TOT_ORI_EXPO_FIN,TOT_UNR_EXPO_PHY,TOT_UNR_EXPO_FIN,TOT_R_EXPO_PHY,TOT_R_EXPO_FIN,TOT_UNR_PHY_LEG,TOT_UNR_FIN_LEG,TOT_R_FIN_LEG, TOT_MTM_TOTAL, FWD_PRICE_DT, ENT_BY, ENT_DT,APRV_BY, APRV_DT,FLAG, PHYS_CURVE_NM)" //SB20210310
					+ " values ('"+DealCustCd+"','"+DealId+"',TO_DATE('"+ReportDt+"','DD/MM/YYYY'),'"+CustomerName+"','"+DealRefNo+"','"+DealBuySell+"',TO_DATE('"+Deal_Dt+"','DD/MM/YYYY'), "
					+ "TO_DATE('"+DealStartDate+"','DD/MM/YYYY'), TO_DATE('"+DealEndDate+"','DD/MM/YYYY'),'"+TOT_DCQ+"','"+TOT_ALLOC_QTY+"','"+DealPriceType+"','"+PriceIndexName+"',"
					+ "'"+ORI_EXPO_PHY+"','"+ORI_EXPO_FIN+"','"+UNR_EXPO_PHY+"','"+UNR_EXPO_FIN+"',"
					+ " '"+R_EXPO_PHY+"','"+R_EXPO_FIN+"','"+UNR_PHY_LEG+"','"+UNR_FIN_LEG+"'," 
				//SB20210310	+ "'"+R_FIN_LEG+"','"+MTM_TOTAL+"',TO_DATE('"+FrdPriceEntDt+"','DD/MM/YYYY'), '"+Deal_Entered_By+"',TO_DATE('"+Deal_Entered_Dt+"','DD/MM/YYYY'),'"+user_cd+"',sysdate,'Y')";		
					+ "'"+R_FIN_LEG+"','"+MTM_TOTAL+"',TO_DATE('"+FrdPriceEntDt+"','DD/MM/YYYY'), '"+Deal_Entered_By+"',TO_DATE('"+Deal_Entered_Dt+"','DD/MM/YYYY'),'"+user_cd+"',sysdate,'Y','"+PricePHYSName+"')";	
				//	System.out.println("FMS9_EOD_EXPOSURE_MST: "+QueryEoD);
			stmt.execute(QueryEoD);	
				}
			
			}
			else
				System.out.println(DealId+" Already Exists in FMS9_EOD_EXPOSURE_DTL for Report Date: "+ReportDt);
			
			/*///////////////////Master entry////////////////////////////
			ORI_EXPO_PHY=Double.parseDouble(nf.format(ORI_EXPO_PHY));
			ORI_EXPO_FIN=Double.parseDouble(nf.format(ORI_EXPO_FIN));
			UNR_EXPO_PHY=Double.parseDouble(nf.format(UNR_EXPO_PHY));
			UNR_EXPO_FIN=Double.parseDouble(nf.format(UNR_EXPO_FIN));
			R_EXPO_PHY=Double.parseDouble(nf.format(R_EXPO_PHY));
			R_EXPO_FIN=Double.parseDouble(nf.format(R_EXPO_FIN));
			UNR_PHY_LEG=Double.parseDouble(nf.format(UNR_PHY_LEG));
			UNR_FIN_LEG=Double.parseDouble(nf.format(UNR_FIN_LEG));
			R_FIN_LEG=Double.parseDouble(nf.format(R_FIN_LEG));
			MTM_TOTAL=Double.parseDouble(nf.format(MTM_TOTAL));
			*/
			
		}
		catch(Exception e)
		{
			//System.out.println("Exception in Databean_Contract_MasterReport--->fetchcustomer()-->"+e);
			  e.printStackTrace();	
		}
	}
	
	//NEW FUNCATION FOR FREEZE EXPOSURE EOD DATA HARSH20210901
	public void MarketExposureDealWiseFreezeDtl_V2(String DealId, String ReportDt)
	{
		try
		{
			String DealBuySell="";
			String ContType=""+DealId;	
			ContType=""+ContType.substring(0,1);	
			if(!ContType.equals("B")) {
				DealBuySell="Sell";
			}
			else {
				DealBuySell="Buy";
			}
			//SB20210404	if(PriceIndexName.equals("-")) PriceIndexName="RLNG_PHYS_INDIA";
			if(PriceIndexName.equals("-")) {
				PriceIndexName=""; //SB20210404
			}
			if(ContType.equals("O")) {
				DealBuySell="Buy"; //SB20210209
			}
			
			String user_cd=Emp_cd;
			double TOT_DCQ=0;  double TOT_ALLOC_QTY=0; double DCQ=0;  
			double ORI_EXPO_PHY=0; double ORI_EXPO_FIN=0; double UNR_EXPO_PHY=0; double UNR_EXPO_FIN=0;	double R_EXPO_PHY=0; double R_EXPO_FIN=0; 
			double UNR_PHY_LEG=0; double UNR_FIN_LEG=0; double R_FIN_LEG=0; double MTM_TOTAL=0;
			int Count=0;
			queryString = "SELECT COUNT(DEAL_ID) from FMS9_EOD_EXPOSURE_DTL "
					+ " where TO_DATE(REPORT_DT, 'DD-MON-YY')=TO_DATE(to_date('"+ReportDt+"','dd/mm/yyyy'), 'DD-MON-YY') AND CUSTOMER_CD='"+DealCustCd+"' AND DEAL_ID='"+DealId+"' AND FLAG='Y' ";
			//	System.out.println("FMS9_CURVE_PRICE_DTL: "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				Count = rset.getInt(1);
			}
			
			if(Count==0)
			{
				for(int i=0; i<VDlv_Dt_HP.size(); i++)
				{
					if(DealBuySell.equals("Sell"))
					{
						DCQ=Double.parseDouble(""+VDcq_HP.elementAt(i));
					}
					else
					{
						DCQ=Double.parseDouble(""+VDcq_HP.elementAt(i));					
					}
					String ContMth="01/"+VPhy_EXPO_ContMth.elementAt(i);
					if(VPhy_EXPO_ContMth.elementAt(i).equals("")) {
						ContMth="";
					}
					String QueryEoD="insert into FMS9_EOD_EXPOSURE_DTL (CUSTOMER_CD,DEAL_ID,REPORT_DT,DELV_DT,DCQ,"
							+ "PRICE_TYPE,CURVE_NM,ALLOC_QTY,CONT_PRICE,INDEX_SETTLE_DT,INDEX_START_DT,INDEX_END_DT,"
							+ "SLOPE,CONST,EFF_DEAL_PRICE,CONT_MTH,ORI_EXPO_PHY,ORI_EXPO_FIN,RU_PHY_FLAG,RU_FIN_FLAG,UNR_EXPO_PHY,"
							+ "UNR_EXPO_FIN,R_EXPO_PHY,R_EXPO_FIN,FWD_PRICE_DT,FWD_PRICE_PHY,FWD_PRICE_FIN,SETTLE_PRICE,UNR_PHY_LEG,"
							+ "UNR_FIN_LEG,R_FIN_LEG,MTM_TOTAL,ENT_BY,ENT_DT,FLAG,PHYS_CURVE_NM,SEQ_NO) "
							+ "values("
							+ "'"+Vcust_Cd.elementAt(i)+"','"+DealId+"',TO_DATE('"+ReportDt+"','DD/MM/YYYY'),TO_DATE('"+VDlv_Dt_HP.elementAt(i)+"','DD/MM/YYYY'),'"+DCQ+"',"
							+ "'"+VFixedFloat_Type.elementAt(i)+"','"+VMutiCurveNm.elementAt(i)+"','"+VActualQty.elementAt(i)+"','"+VContFixedPrice.elementAt(i)+"',TO_DATE('"+VFin_EXPO_ContMth.elementAt(i)+"','DD/MM/YYYY'),TO_DATE('"+VFin_SettleStDt.elementAt(i)+"','DD/MM/YYYY'),TO_DATE('"+VFin_SettleEndDt.elementAt(i)+"','DD/MM/YYYY'),"
							+ "'"+VFin_Slope.elementAt(i)+"','"+VFin_Constant.elementAt(i)+"','"+VFin_EffSettlePrice.elementAt(i)+"',TO_DATE('"+ContMth+"','DD/MM/YYYY'),'"+VPhy_ORI_EXPO_BrkUp.elementAt(i)+"','"+VFin_ORI_EXPO_BrkUp.elementAt(i)+"','"+VPhy_RU_flag.elementAt(i)+"','"+VFin_RU_flag.elementAt(i)+"','"+VPhy_Expo_U.elementAt(i)+"',"
							+ "'"+VFin_Expo_U.elementAt(i)+"','"+VPhy_Expo_R.elementAt(i)+"','"+VFin_Expo_R.elementAt(i)+"',TO_DATE('"+FrdPriceEntDt+"','DD/MM/YYYY'),'"+VPhy_FwdPrice.elementAt(i)+"','"+VFin_FwdPrice.elementAt(i)+"','"+VFin_SettlePrice.elementAt(i)+"','"+VPhy_U_Leg.elementAt(i)+"',"
							+ "'"+VFin_U_Leg.elementAt(i)+"','"+VFin_R_Leg.elementAt(i)+"','"+VTotalPnL.elementAt(i)+"','"+user_cd+"',sysdate,'Y','"+PricePHYSName+"','"+SEQ_NO.elementAt(i)+"')";	
					//	System.out.println("FMS9_EOD_EXPOSURE_DTL: "+QueryEoD);
					stmt.execute(QueryEoD);
					TOT_DCQ+=DCQ; 
					TOT_ALLOC_QTY+=Double.parseDouble(""+VActualQty.elementAt(i)); 
					if(!VPhy_ORI_EXPO_BrkUp.elementAt(i).equals("")) {
						ORI_EXPO_PHY+=Double.parseDouble(""+VPhy_ORI_EXPO_BrkUp.elementAt(i));  ORI_EXPO_PHY=Double.parseDouble(nf.format(ORI_EXPO_PHY));
					}
					if(!VFin_ORI_EXPO_BrkUp.elementAt(i).equals("")) {
						ORI_EXPO_FIN+=Double.parseDouble(""+VFin_ORI_EXPO_BrkUp.elementAt(i));  ORI_EXPO_FIN=Double.parseDouble(nf.format(ORI_EXPO_FIN)); }
					if(!VPhy_Expo_U.elementAt(i).equals("")) {
						UNR_EXPO_PHY+=Double.parseDouble(""+VPhy_Expo_U.elementAt(i));  UNR_EXPO_PHY=Double.parseDouble(nf.format(UNR_EXPO_PHY));
					}
					if(!VFin_Expo_U.elementAt(i).equals("")) {
						UNR_EXPO_FIN+=Double.parseDouble(""+VFin_Expo_U.elementAt(i)); UNR_EXPO_FIN=Double.parseDouble(nf.format(UNR_EXPO_FIN));
					}
					if(!VPhy_Expo_R.elementAt(i).equals("")) {
						R_EXPO_PHY+=Double.parseDouble(""+VPhy_Expo_R.elementAt(i)); R_EXPO_PHY=Double.parseDouble(nf.format(R_EXPO_PHY));
					}
					if(!VFin_Expo_R.elementAt(i).equals("")) {
						R_EXPO_FIN+=Double.parseDouble(""+VFin_Expo_R.elementAt(i)); R_EXPO_FIN=Double.parseDouble(nf.format(R_EXPO_FIN));
					}
					if(!VPhy_U_Leg.elementAt(i).equals("")) {
						UNR_PHY_LEG+=Double.parseDouble(""+VPhy_U_Leg.elementAt(i)); UNR_PHY_LEG=Double.parseDouble(nf.format(UNR_PHY_LEG));
					}
					if(!VFin_U_Leg.elementAt(i).equals("")) {
						UNR_FIN_LEG+=Double.parseDouble(""+VFin_U_Leg.elementAt(i)); UNR_FIN_LEG=Double.parseDouble(nf.format(UNR_FIN_LEG));
					}
					if(!VFin_R_Leg.elementAt(i).equals("")) {
						R_FIN_LEG+=Double.parseDouble(""+VFin_R_Leg.elementAt(i)); R_FIN_LEG=Double.parseDouble(nf.format(R_FIN_LEG));
					}
					if(!VTotalPnL.elementAt(i).equals("")) {
						MTM_TOTAL+=Double.parseDouble(""+VTotalPnL.elementAt(i));MTM_TOTAL=Double.parseDouble(nf.format(MTM_TOTAL));
					}
				}	
				String CustomerName=""+Deal_Cust_AbbrNm; String DealRefNo=""+Deal_Ref_No; String DealStartDate=""+Deal_Start_Dt; String DealEndDate=""+Deal_End_Dt; String DealPriceType="";
				//	System.out.println("Updated into FMS9_EOD_EXPOSURE_DTL: No. of Records: "+SIGNING_DT.size());
				Count =0;
				queryString = "SELECT COUNT(DEAL_ID) from FMS9_EOD_EXPOSURE_MST "
						+ " where TO_DATE(REPORT_DT, 'DD-MON-YY')=TO_DATE(to_date('"+ReportDt+"','dd/mm/yyyy'), 'DD-MON-YY') AND DEAL_ID='"+DealId+"' AND FLAG='Y' ";
				//	System.out.println("FMS9_CURVE_PRICE_DTL: "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					Count = rset.getInt(1);
				}
				if(Count==0)
				{
					String QueryEoD="insert into FMS9_EOD_EXPOSURE_MST (CUSTOMER_CD, DEAL_ID, REPORT_DT, CUSTOMER_NM, DEAL_REF_NO, BUY_SELL, DEAL_SIGN_DT, DEAL_START_DT, DEAL_END_DT, TOT_DCQ, TOT_ALLOC_QTY, PRICE_TYPE, CURVE_NM,"
						+ "TOT_ORI_EXPO_PHY,TOT_ORI_EXPO_FIN,TOT_UNR_EXPO_PHY,TOT_UNR_EXPO_FIN,TOT_R_EXPO_PHY,TOT_R_EXPO_FIN,TOT_UNR_PHY_LEG,TOT_UNR_FIN_LEG,TOT_R_FIN_LEG, TOT_MTM_TOTAL, FWD_PRICE_DT, ENT_BY, ENT_DT,APRV_BY, APRV_DT,FLAG, PHYS_CURVE_NM)"
						+ " values ('"+DealCustCd+"','"+DealId+"',TO_DATE('"+ReportDt+"','DD/MM/YYYY'),'"+CustomerName+"','"+DealRefNo+"','"+DealBuySell+"',TO_DATE('"+Deal_Dt+"','DD/MM/YYYY'), "
						+ "TO_DATE('"+DealStartDate+"','DD/MM/YYYY'), TO_DATE('"+DealEndDate+"','DD/MM/YYYY'),'"+TOT_DCQ+"','"+TOT_ALLOC_QTY+"','"+DealPriceType+"','"+PriceIndexName+"',"
						+ "'"+ORI_EXPO_PHY+"','"+ORI_EXPO_FIN+"','"+UNR_EXPO_PHY+"','"+UNR_EXPO_FIN+"',"
						+ " '"+R_EXPO_PHY+"','"+R_EXPO_FIN+"','"+UNR_PHY_LEG+"','"+UNR_FIN_LEG+"',"
						+ "'"+R_FIN_LEG+"','"+MTM_TOTAL+"',TO_DATE('"+FrdPriceEntDt+"','DD/MM/YYYY'), '"+Deal_Entered_By+"',TO_DATE('"+Deal_Entered_Dt+"','DD/MM/YYYY'),'"+user_cd+"',sysdate,'Y','"+PricePHYSName+"')";		
					//	System.out.println("FMS9_EOD_EXPOSURE_DTL: "+QueryEoD);
					stmt.execute(QueryEoD);	
					//System.out.println(DealId+" INSERT: FMS9_EOD_EXPOSURE_MST for Report Date: "+ReportDt);
					System.out.println(DealId+" INSERT: FMS9_EOD_EXPOSURE_MST for Report Date: "+ReportDt);
				}
			
			}
			else
				System.out.println(DealId+" Already Exists in FMS9_EOD_EXPOSURE_DTL for Report Date: "+ReportDt);
			
		}
		catch(Exception e)
		{
			//System.out.println("Exception in Databean_Contract_MasterReport--->fetchcustomer()-->"+e);
			  e.printStackTrace();	
		}
	}
	
	
	////////HARSH20210119 FOR CLEARING VECTOR DATA////////////////
	public void clear()
	{
		CUSTOMER_CD.clear();FGSA_NO.clear();FGSA_REV_NO.clear();SN_NO.clear();SN_REV_NO.clear();SN_REF_NO.clear();CARGO_ARRIVAL_DATE.clear();
		sn_ent_by.clear();sn_apr_by.clear();SIGNING_DT.clear();VDealDCQFlag.clear();VBuySell.clear();VDealDCQ.clear();VPriceTypeDeal.clear();VDealAllocQty.clear();VPriceTypeDealRate.clear();
		VPriceIndex_SettleDate.clear();VPriceIndex_StartDate.clear();VPriceIndex_EndDate.clear();VSlope_ICE_JKM.clear();VConst_ICE_JKM.clear();VEff_Price.clear();VMthYrWise.clear();VRLNG_PHYS_IN_EXPO.clear();
		VLNG_ICE_JKM_EXPO.clear();VRU_Physical_Leg.clear();VRU_Financial_Leg.clear();VRLNG_PHYS_INDIA_EXPOSURE_U.clear();VLNG_ICE_JKM_EXPOSURE_U.clear();VPNL_PHYS_REALISED.clear();VPNL_ICE_JKM_REALISED.clear();
		VSettle_RLNG_PHYS.clear();VSettle_ICE_JKM.clear();VSettle_ICE_JKM_AVG.clear();VU_Phy_Leg.clear();VU_Fin_Leg.clear(); VRealised.clear();VTotalMtmRealised.clear(); CUSTOMER_NM.clear(); CUSTOMER_ABBR.clear();
		VRLNG_PHYS_IN_EXPO.clear();VTotal.clear();VPriceTypeDealSeQNo.clear();VSettle_ICE_JKM_AVG.clear();VRLNG_PHYS_INDIA_EXPOSURE_U.clear();VSettle_RLNG_PHYS.clear();VLNG_ICE_JKM_EXPOSURE_U.clear();VSettle_ICE_JKM.clear();
		VConst_ICE_JKM.clear();VMTM.clear();VRealised.clear();VTotalMtmRealised.clear();VLNG_ICE_JKM_EXPODropOff.clear();VLNG_ICE_JKM_EXPOBaseValue.clear();VMthYrWisePriceICE_JKM.clear();VPriceTypeDeal.clear();
		VPriceType.clear();VDealPriceCurve.clear();VDealPhysCurve.clear();VCargoRefNo.clear();VDealTCQ.clear();VRLNG_PHYS_IN_EXPO.clear();VLNG_ICE_JKM_EXPO.clear();VRU_Physical_Leg.clear();VRLNG_PHYS_INDIA_EXPOSURE_U.clear();
		VRU_Financial_Leg.clear();VLNG_ICE_JKM_EXPOSURE_U.clear();VU_Phy_Leg.clear();VU_Fin_Leg.clear();VR_Fin_Leg.clear();VPNL_PHYS_REALISED.clear();VPNL_ICE_JKM_REALISED.clear();VSettle_ICE_JKM_AVG.clear();VMTM.clear(); 
		VPriceIndex_SettleDate.clear();VSlope.clear();VEff_Price.clear(); SUPPLIEDMBTU.clear(); BOOKMMBTU.clear();VRLNG_PHYS_IN_EXPO_SubTotal.clear();VLNG_ICE_JKM_EXPO_SubTotal.clear();VTotal_SubTotal.clear();
		VRLNG_PHYS_INDIA_EXPOSURE_U_SubTotal.clear();VLNG_ICE_JKM_EXPOSURE_U_SubTotal.clear();VU_Phy_Leg_SubTotal.clear();VU_Fin_Leg_SubTotal.clear(); RATE.clear();
		VEff_CumulativePerc.clear();VEff_Perc.clear(); //SB20210406
		
		//HARSH20210901
		VDlv_Dt.clear();
		VDcq.clear();
		VDlv_Dt_HP.clear();
		VDcq_HP.clear();
		VFixedFloat_Type.clear();
		VContFixedPrice.clear();
		VActualQty.clear();
		VMutiCurveNm.clear();
		VFin_ORI_EXPO_BrkUp.clear();
		VPhy_ORI_EXPO_BrkUp.clear();
		VFin_EXPO_ContMth.clear();
		VPhy_EXPO_ContMth.clear();
		VFin_SettleStDt.clear();
		VFin_SettleEndDt.clear();
		VFin_EffSettlePrice.clear();
		VFin_SettlePrice.clear();
		VFin_Slope.clear();
		VFin_Constant.clear();
		VPhy_RU_flag.clear();
		VFin_RU_flag.clear();
		VPhy_Expo_U.clear();
		VFin_Expo_U.clear();
		VPhy_Expo_R.clear();
		VFin_Expo_R.clear();
		VFin_FwdPrice.clear();
		VPhy_FwdPrice.clear();
		VPhy_U_Leg.clear();
		VFin_U_Leg.clear();
		VFin_R_Leg.clear();
		VTotalPnL.clear();
		VRowCol_Colour.clear();
		Vcust_Cd.clear();
		SEQ_NO.clear();
	}
	/////////////////////////////////////////////////////////////
	////////////////////////SB20210201: Freezed Data/////////////////////////////////////////////////
	public void MarketExposureDealWiseReportView(String DealId, String ReportDt, String CustCode)
	{
		try
		{
		//	DealId=Cont_Type+FgsaNo+"-"+FgsaRevNo+"-"+SnNo+"-"+SnRevNo;
			String user_cd=Emp_cd;
			//for(int i=0; i<CUSTOMER_CD.size(); i++)
			{
				int Count=0;
				queryString = "SELECT COUNT(DEAL_ID)  from FMS9_EOD_EXPOSURE_MST "
						+ " where TO_DATE(REPORT_DT, 'DD-MON-YY')=TO_DATE(to_date('"+ReportDt+"','dd/mm/yyyy'), 'DD-MON-YY') AND CUSTOMER_CD='"+CustCode+"' AND DEAL_ID LIKE '"+DealId+"' AND FLAG='Y' ";
			//	System.out.println("FMS9_EOD_EXPOSURE_DTL: "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					Count = rset.getInt(1);
				}
				if(Count>0)
				{
					VExpoFreezeeStatus.add(""+Count);	
				System.out.println("Extracted from FMS9_EOD_EXPOSURE_DTL: No. of Records: "+Count);
				}
				else
				{
					VExpoFreezeeStatus.add(""+Count);	
					System.out.println("NOT AVAILABLE in FMS9_EOD_EXPOSURE_DTL for Report Date: "+ReportDt);
				}
			}
		}
		catch(Exception e)
		{
			//System.out.println("Exception in Databean_Contract_MasterReport--->fetchcustomer()-->"+e);
			  e.printStackTrace();	
		}
	}
////////////////////////^^^^^^SB20210201: Freezed Data/////////////////////////////////////////////////
////////////////////////SB20210201: Freezed Data/////////////////////////////////////////////////
public void MarketExposureDealWiseReportView2(String DealId, String ReportDt, String CustCode)
{
	try
	{
		//	DealId=Cont_Type+FgsaNo+"-"+FgsaRevNo+"-"+SnNo+"-"+SnRevNo;
		String user_cd=Emp_cd;
		//for(int i=0; i<CUSTOMER_CD.size(); i++)
		{
		int Count=0; String LastEoD_Dt="";
		queryString = "SELECT TO_CHAR(REPORT_DT,'dd/mm/yyyy') from FMS9_EOD_EXPOSURE_MST "
			+ " where TO_DATE(REPORT_DT, 'DD-MON-YY')=(SELECT MAX(REPORT_DT) FROM FMS9_EOD_EXPOSURE_MST "
			+ " WHERE REPORT_DT <=TO_DATE(to_date('"+ReportDt+"','dd/mm/yyyy'), 'DD-MON-YY') AND CUSTOMER_CD='"+CustCode+"' AND DEAL_ID='"+DealId+"' AND FLAG='Y') "
			+ " AND CUSTOMER_CD='"+CustCode+"' AND DEAL_ID='"+DealId+"' AND FLAG='Y'";
	//	System.out.println(" EOD2 :FMS9_EOD_EXPOSURE_DTL: "+queryString);
		rset = stmt.executeQuery(queryString);
		if(rset.next())
		{
		Count++;
		LastEoD_Dt= rset.getString(1);
		}
		if(Count>0)
		{
			VExpoFreezeeStatus2.add(""+LastEoD_Dt);	
			System.out.println("Extracted from FMS9_EOD_EXPOSURE_DTL: No. of Records: "+Count);
		}
		else
		{
			VExpoFreezeeStatus2.add(""+Count);	
			System.out.println("NOT AVAILABLE in FMS9_EOD_EXPOSURE_DTL for Report Date: "+ReportDt);
		}
		}
	}
	catch(Exception e)
	{
	//System.out.println("Exception in Databean_Contract_MasterReport--->fetchcustomer()-->"+e);
	e.printStackTrace();	
	}
}
////////////////////////^^^^^^SB20210201: Freezed Data/////////////////////////////////////////////////
////////////////////////SB20210201: Freezed Data/////////////////////////////////////////////////
public void MarketExposureStorage(String ReportDt)
{
	try
	{
		double tot_tank1=60; double tot_tank2=90; double tankTotal=0;
		double tot_comit=0; double tot_comit_mmscm=0;  double supplied_mmscm=0;
		double balance = 0; double supplied = 0; double total_supplied=0; 
		double Nominated = 0; double SellerNominated = 0; //SB20210328 
		double TPBalanceMmbtu=0; //SB20210630
		queryString="select TO_CHAR(START_DT,'DD/MM/YYYY'),TO_CHAR(END_DT,'DD/MM/YYYY'), " +
				"A.mapping_id,A.mapping_id,A.CARGO_REF_NO,A.CARGO_SEQ_NO,A.QTY_TO_BE_SUPPLY,A.REGAS_TARIF, " +
				"C.REV_NO,C.PRE_APPROVAL_BY,TO_CHAR(C.PRE_APPROVAL_DaTE,'DD/MM/YYYY'),C.ENT_BY,TO_CHAR(C.ENT_DT,'DD/MM/YYYY'), DCQ_QTY, A.TARIFF_CUR_FLAG, NO_OF_CARGO "
				+ ", A.ADQ_QTY,TO_CHAR(ACTUAL_RECPT_DT,'DD/MM/YYYY'),TO_CHAR(EDQ_FROM_DT,'DD/MM/YYYY'),TO_CHAR(EDQ_TO_DT,'DD/MM/YYYY') " //SB20210614
				+ "from FMS8_LNG_REGAS_CARGO_DTL A, FMS8_LNG_REGAS_MST C where " +
				"(A.CONTRACT_START_DT <= to_date('"+ReportDt+"','dd/mm/yyyy') AND " +
				"A.CONTRACT_END_DT >= to_date('"+ReportDt+"','dd/mm/yyyy')) AND A.MAPPING_ID=C.MAPPING_ID " +
				"AND C.CN_NO!='0' AND CN_NO >'9999' AND C.CN_REV_NO=(SELECT MAX(B.CN_REV_NO) FROM FMS8_LNG_REGAS_MST B " +
				"where C.agreement_no=B.agreement_no AND C.customer_cd=B.customer_cd AND B.CN_NO!=0 AND CN_NO >'9999' AND " +
				"B.CN_NO=C.CN_NO AND c.rev_no=B.rev_no AND B.REV_NO=(SELECT MAX(D.REV_NO) FROM FMS8_LNG_REGAS_MST D WHERE " +
				"D.agreement_no=B.agreement_no AND B.customer_cd=D.customer_cd AND D.CN_NO=0 " +
				//"AND D.agreement_no=C.agreement_no AND D.customer_cd=C.customer_cd )) " +  //RG20191108
				"AND D.agreement_no=C.agreement_no AND D.customer_cd=C.customer_cd and B.rev_no=D.rev_no and D.rev_no=C.rev_no )) " +  
				"order by A.CONTRACT_START_DT";
				rset = stmt.executeQuery(queryString);
				System.out.println("LTCORA: -"+queryString);
				while(rset.next())
				{									
					String map_id=rset.getString(3)==null?"":rset.getString(3);
					String tempmap_id[]=map_id.split("-");
					
					String temp_regas_no=tempmap_id[3];
					String temp_regas_rev_no=tempmap_id[4];
					String temp_cust_cd=tempmap_id[0];
					
					String temp_cn_seq_no=rset.getString(6)==null?"0":rset.getString(6);	 //SB20210324	
				//SB	BOOKMMBTU.add(rset.getString(7)==null?"0.00":nf.format(Double.parseDouble(rset.getString(7))));
					VStoreTPCustMmscm.add(rset.getString(7)==null?"0.00":nf.format(Double.parseDouble(rset.getString(7))/38900));//MD20110916
					//SB20210614	  	tot_comit += Double.parseDouble(rset.getString(7)==null?"0":nf.format(Double.parseDouble(rset.getString(7))));
				//HARSH20210809 tot_comit += Double.parseDouble(rset.getString(17)==null?"0":nf.format(Double.parseDouble(rset.getString(17)))); //SB20210614
				  	VStoreTPCustCd.add(temp_cust_cd);	
				  	/////////////////////////SB20210614//////////////////////////////////////////////////////////
				  	System.out.println(map_id+" :Commited Qty: "+Double.parseDouble(rset.getString(7)));
					//SB20210916////////HARSH20210809 DAY WISE ADQ QTY
				 	queryString1="SELECT COUNT(*) FROM FMS9_CARGO_UNLOADED_QTY_DTL WHERE MAPPING_ID='"+map_id+"'";
				  	//System.out.println("Hello  : "+queryString1);
				  	rset1=stmt1.executeQuery(queryString1);
				  	if(rset1.next())
				  	{
				  		if(rset1.getInt(1)>0)
				  		{
				  			queryString2="SELECT SUM(DAILY_ADQ_QTY) FROM FMS9_CARGO_UNLOADED_QTY_DTL WHERE MAPPING_ID='"+map_id+"' "
				  					+ "AND ADQ_DT <= TO_DATE('"+ReportDt+"','DD/MM/YYYY') ";
				  			rset2=stmt2.executeQuery(queryString2);
				  			if(rset2.next())
				  			{
				  				VStoreTPCustMmbtuADQ.add(Double.parseDouble(rset2.getString(1)==null?"0":nf.format(Double.parseDouble(rset2.getString(1)))));
				  				tot_comit += Double.parseDouble(rset2.getString(1)==null?"0":nf.format(Double.parseDouble(rset2.getString(1))));
				  			}
				  			else
				  			{
				  				VStoreTPCustMmbtuADQ.add(Double.parseDouble(rset.getString(17)==null?"0":nf.format(Double.parseDouble(rset.getString(17)))));
				  				tot_comit += Double.parseDouble(rset.getString(17)==null?"0":nf.format(Double.parseDouble(rset.getString(17))));
				  			}
				  		}
				  		else
				  		{
				  			VStoreTPCustMmbtuADQ.add(Double.parseDouble(rset.getString(17)==null?"0":nf.format(Double.parseDouble(rset.getString(17)))));
				  			tot_comit += Double.parseDouble(rset.getString(17)==null?"0":nf.format(Double.parseDouble(rset.getString(17))));
				  		}
				  	}
				  	else
				  	{
				  		VStoreTPCustMmbtuADQ.add(Double.parseDouble(rset.getString(17)==null?"0":nf.format(Double.parseDouble(rset.getString(17)))));
				  		tot_comit += Double.parseDouble(rset.getString(17)==null?"0":nf.format(Double.parseDouble(rset.getString(17))));
				  	}
				  	///////^^^^SB20210916///////////////////////
				  	
				  	VStoreTPCustMmbtuEDQ.add(Double.parseDouble(rset.getString(7)==null?"0":nf.format(Double.parseDouble(rset.getString(7)))));
					VStoreTPCustMmbtuADQ.add(Double.parseDouble(rset.getString(17)==null?"0":nf.format(Double.parseDouble(rset.getString(17)))));
					VStoreTPCustMmbtuADQ_DT.add(rset.getString(18)==null?"":rset.getString(18));
					VStoreTPCustMmbtuCONT_ST_DT.add(rset.getString(1)==null?"":rset.getString(1));
					VStoreTPCustMmbtuCONT_END_DT.add(rset.getString(2)==null?"":rset.getString(2));
					VStoreTPCustMmbtuEDQ_ST_DT.add(rset.getString(19)==null?"":rset.getString(19));
					VStoreTPCustMmbtuEDQ_END_DT.add(rset.getString(20)==null?"":rset.getString(20));
					///////////////////////^^^^SB20210614//////////////////////////////////////////////////////////
					String queryString11="select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL "+
							" WHERE "//CUSTOMER_CD="+CUSTOMER_CD.elementAt(i)+" AND "
							+ " SN_NO="+temp_cn_seq_no+" AND FGSA_NO="+temp_regas_no+" AND " //SB20210324
							+ "MAPPING_ID='"+map_id+"' AND GAS_DT between to_date('"+rset.getString(1)+"','dd/mm/yyyy') and to_date('"+ReportDt+"','dd/mm/yyyy')";
					//SB+ " AND GAS_DT <= to_date('"+LastBilledQtyEndDt+"','dd/mm/yyyy')";
							//+ "  AND CONTRACT_TYPE='"+VContType.elementAt(i)+"' ";
						System.out.println(map_id+" :STEP-3A: FMS7_DAILY_ALLOCATION_DTL: "+queryString11);
					rset1 = stmt1.executeQuery(queryString11);
					//	Introduce By Milan Dalsaniya 20111012					
					if(rset1.next())
					{
							supplied = rset1.getDouble(1);
							total_supplied += supplied;
							
					}
				/////SB20210327:Daily Nom/SellerNom Qty////////////////////
					int CountAllocDt=0;
					queryString11="select COUNT(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL "+
							" WHERE SN_NO="+temp_cn_seq_no+" AND FGSA_NO="+temp_regas_no+" AND MAPPING_ID='"+map_id+"' AND GAS_DT =to_date('"+ReportDt+"','dd/mm/yyyy')";
						System.out.println(map_id+" :STEP-3A: FMS7_DAILY_ALLOCATION_DTL: "+queryString11);
					rset1 = stmt1.executeQuery(queryString11);
					//	Introduce By Milan Dalsaniya 20111012					
					if(rset1.next())
					{
						CountAllocDt = rset1.getInt(1);
					}
					System.out.println(map_id+" :CountAllocDt: "+CountAllocDt);
					if(CountAllocDt==0)
					{		
						int CountSellerDt=0;
						queryString11="select COUNT(QTY_MMBTU) from FMS7_DAILY_SELLER_NOM_DTL "+
								" WHERE SN_NO="+temp_cn_seq_no+" AND FGSA_NO="+temp_regas_no+" AND MAPPING_ID='"+map_id+"' AND GAS_DT =to_date('"+ReportDt+"','dd/mm/yyyy')";
					//		System.out.println(map_id+" :STEP-3A: FMS7_DAILY_SELLER_NOM_DTL: "+queryString11);
						rset1 = stmt1.executeQuery(queryString11);
						//	Introduce By Milan Dalsaniya 20111012					
						if(rset1.next())
						{
							CountSellerDt = rset1.getInt(1);
						}
						if(CountSellerDt>0)
						{
							queryString11="select QTY_MMBTU, QTY_SCM from FMS7_DAILY_SELLER_NOM_DTL "
									+" WHERE  SN_NO="+temp_cn_seq_no+" AND FGSA_NO="+temp_regas_no+" AND MAPPING_ID='"+map_id+"' AND GAS_DT=to_date('"+ReportDt+"','dd/mm/yyyy') "
									+ "AND NOM_REV_NO=(select MAX(NOM_REV_NO) from FMS7_DAILY_SELLER_NOM_DTL "
									+" WHERE  SN_NO="+temp_cn_seq_no+" AND FGSA_NO="+temp_regas_no+" AND MAPPING_ID='"+map_id+"' AND GAS_DT=to_date('"+ReportDt+"','dd/mm/yyyy') ) ";
						//	System.out.println(map_id+" :STEP-3B: FMS7_DAILY_SELLER_NOM_DTL: "+queryString11);
							rset1 = stmt1.executeQuery(queryString11);
							//	Introduce By Milan Dalsaniya 20111012					
							if(rset1.next())
							{
								SellerNominated = rset1.getDouble(1);//System.out.println(" :SellerNominated: "+SellerNominated);
								total_supplied += SellerNominated;
								VStoreTPCustMmbtuSuppliedRptDt.add("SCH: "+SellerNominated); //SB20210614
							}
						}
						else if(CountSellerDt==0)
						{
							queryString11="select QTY_MMBTU, QTY_SCM from FMS7_DAILY_BUYER_NOM_DTL "
									+" WHERE  SN_NO="+temp_cn_seq_no+" AND FGSA_NO="+temp_regas_no+" AND MAPPING_ID='"+map_id+"' AND GAS_DT=to_date('"+ReportDt+"','dd/mm/yyyy') "
									+ "AND NOM_REV_NO=(select MAX(NOM_REV_NO) from FMS7_DAILY_BUYER_NOM_DTL "
									+" WHERE  SN_NO="+temp_cn_seq_no+" AND FGSA_NO="+temp_regas_no+" AND MAPPING_ID='"+map_id+"' AND GAS_DT=to_date('"+ReportDt+"','dd/mm/yyyy') ) ";
							//System.out.println(map_id+" :STEP-3B: FMS7_DAILY_BUYER_NOM_DTL: "+queryString11);
							rset1 = stmt1.executeQuery(queryString11);
							//	Introduce By Milan Dalsaniya 20111012					
							if(rset1.next())
							{
								Nominated = rset1.getDouble(1);//System.out.println(" :Nominated: "+Nominated);
								total_supplied += Nominated;
								VStoreTPCustMmbtuSuppliedRptDt.add("NOM: "+Nominated); //SB20210614
							}
						}
					}
					else
						VStoreTPCustMmbtuSuppliedRptDt.add("ACT: "+supplied); //SB20210614
					/////////////////////////////////////////////////
					queryString ="select CUSTOMER_ABBR,CUSTOMER_NAME,CUSTOMER_CD from FMS7_CUSTOMER_MST where CUSTOMER_CD="+temp_cust_cd+" "
							+ "AND EFF_DT=(SELECT MAX(EFF_DT) FROM FMS7_CUSTOMER_MST WHERE CUSTOMER_CD='"+temp_cust_cd+"' AND EFF_DT<=TO_DATE('"+ReportDt+"','DD/MM/YYYY'))";
					rset1 = stmt1.executeQuery(queryString);
					////System.out.println(queryString);
					if(rset1.next())
					{
						VStoreTPCustNm.add(rset1.getString(1)==null?"":rset1.getString(1));
					}
				}
				queryString="select TO_CHAR(START_DT,'DD/MM/YYYY'),TO_CHAR(END_DT,'DD/MM/YYYY'), " +
						"A.mapping_id,A.mapping_id,A.CARGO_REF_NO,A.CARGO_SEQ_NO,A.QTY_TO_BE_SUPPLY,A.REGAS_TARIF, " +
						"C.REV_NO,PRE_APPROVAL_BY,TO_CHAR(PRE_APPROVAL_DATE,'DD/MM/YYYY'),C.ENT_BY,TO_CHAR(C.ENT_DT,'DD/MM/YYYY'), DCQ_QTY, A.TARIFF_CUR_FLAG, NO_OF_CARGO  "
						+ ", A.ADQ_QTY,TO_CHAR(ACTUAL_RECPT_DT,'DD/MM/YYYY'),TO_CHAR(EDQ_FROM_DT,'DD/MM/YYYY'),TO_CHAR(EDQ_TO_DT,'DD/MM/YYYY') " //SB20210614
						+ "from FMS8_LNG_REGAS_CARGO_DTL A, FMS8_LNG_REGAS_MST C where " +
						"(A.CONTRACT_START_DT <= to_date('"+ReportDt+"','dd/mm/yyyy') AND " +
						"A.CONTRACT_END_DT >= to_date('"+ReportDt+"','dd/mm/yyyy')) AND A.MAPPING_ID=C.MAPPING_ID " +
						"AND C.CN_NO!='0' AND CN_NO <'9999' AND C.CN_REV_NO=(SELECT MAX(B.CN_REV_NO) FROM FMS8_LNG_REGAS_MST B " +
						"where C.agreement_no=B.agreement_no AND C.customer_cd=B.customer_cd AND B.CN_NO!=0 AND CN_NO <'9999' AND " +
						"B.CN_NO=C.CN_NO AND c.rev_no=B.rev_no AND B.REV_NO=(SELECT MAX(D.REV_NO) FROM FMS8_LNG_REGAS_MST D WHERE " +
						"D.agreement_no=B.agreement_no AND B.customer_cd=D.customer_cd AND D.CN_NO=0 " +
						"AND D.agreement_no=C.agreement_no AND D.customer_cd=C.customer_cd )) " +
						"order by A.CONTRACT_START_DT";
				rset = stmt.executeQuery(queryString);
				System.out.println("CN-LTCORA: -"+queryString);
				while(rset.next())
				{				
				
				String map_id=rset.getString(3)==null?"":rset.getString(3);
				String tempmap_id[]=map_id.split("-");
				
				String temp_regas_no=tempmap_id[3];
				String temp_regas_rev_no=tempmap_id[4];
				String temp_cust_cd=tempmap_id[0];
				
				String temp_cn_seq_no=rset.getString(6)==null?"0":rset.getString(6);	 //SB20210324
			//SB	BOOKMMBTU.add(rset.getString(7)==null?"0.00":nf.format(Double.parseDouble(rset.getString(7))));
				VStoreTPCustMmscm.add(rset.getString(7)==null?"0.00":nf.format(Double.parseDouble(rset.getString(7))/38900));//MD20110916
				//SB20210614		tot_comit += Double.parseDouble(rset.getString(7)==null?"0":nf.format(Double.parseDouble(rset.getString(7))));
				//SB20210916////////HARSH20210809 tot_comit += Double.parseDouble(rset.getString(17)==null?"0":nf.format(Double.parseDouble(rset.getString(17))));
				VStoreTPCustCd.add(temp_cust_cd);	
		//SB20210614	  	VStoreTPCustNm.add("");
				/////////////////////////SB20210614//////////////////////////////////////////////////////////
			  	System.out.println(map_id+" :Commited Qty: "+Double.parseDouble(rset.getString(7)));
			  	
			  	//HARSH20210809 DAY WISE ADQ QTY
			  	queryString1="SELECT COUNT(*) FROM FMS9_CARGO_UNLOADED_QTY_DTL WHERE MAPPING_ID='"+map_id+"'";
			  	//System.out.println("Hello  : "+queryString1);
			  	rset1=stmt1.executeQuery(queryString1);
			  	if(rset1.next())
			  	{
			  		if(rset1.getInt(1)>0)
			  		{
			  			queryString2="SELECT SUM(DAILY_ADQ_QTY) FROM FMS9_CARGO_UNLOADED_QTY_DTL WHERE MAPPING_ID='"+map_id+"' "
			  					+ "AND ADQ_DT <= TO_DATE('"+ReportDt+"','DD/MM/YYYY') ";
			  			rset2=stmt2.executeQuery(queryString2);
			  			if(rset2.next())
			  			{
			  				VStoreTPCustMmbtuADQ.add(Double.parseDouble(rset2.getString(1)==null?"0":nf.format(Double.parseDouble(rset2.getString(1)))));
			  				tot_comit += Double.parseDouble(rset2.getString(1)==null?"0":nf.format(Double.parseDouble(rset2.getString(1))));
			  			}
			  			else
			  			{
			  				VStoreTPCustMmbtuADQ.add(Double.parseDouble(rset.getString(17)==null?"0":nf.format(Double.parseDouble(rset.getString(17)))));
			  				tot_comit += Double.parseDouble(rset.getString(17)==null?"0":nf.format(Double.parseDouble(rset.getString(17))));
			  			}
			  		}
			  		else
			  		{
			  			VStoreTPCustMmbtuADQ.add(Double.parseDouble(rset.getString(17)==null?"0":nf.format(Double.parseDouble(rset.getString(17)))));
			  			tot_comit += Double.parseDouble(rset.getString(17)==null?"0":nf.format(Double.parseDouble(rset.getString(17))));
			  		}
			  	}
			  	else
			  	{
			  		VStoreTPCustMmbtuADQ.add(Double.parseDouble(rset.getString(17)==null?"0":nf.format(Double.parseDouble(rset.getString(17)))));
			  		tot_comit += Double.parseDouble(rset.getString(17)==null?"0":nf.format(Double.parseDouble(rset.getString(17))));
			  	}
				///////^^^^SB20210916///////////////////////
			  	
			  	VStoreTPCustMmbtuEDQ.add(Double.parseDouble(rset.getString(7)==null?"0":nf.format(Double.parseDouble(rset.getString(7)))));
				VStoreTPCustMmbtuADQ.add(Double.parseDouble(rset.getString(17)==null?"0":nf.format(Double.parseDouble(rset.getString(17)))));
				VStoreTPCustMmbtuADQ_DT.add(rset.getString(18)==null?"":rset.getString(18));
				VStoreTPCustMmbtuCONT_ST_DT.add(rset.getString(1)==null?"":rset.getString(1));
				VStoreTPCustMmbtuCONT_END_DT.add(rset.getString(2)==null?"":rset.getString(2));
				VStoreTPCustMmbtuEDQ_ST_DT.add(rset.getString(19)==null?"":rset.getString(19));
				VStoreTPCustMmbtuEDQ_END_DT.add(rset.getString(20)==null?"":rset.getString(20));
				///////////////////////^^^^SB20210614//////////////////////////////////////////////////////////
				
					String queryString11="select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL "+
							" WHERE "//CUSTOMER_CD="+CUSTOMER_CD.elementAt(i)+" AND "
							+ " SN_NO="+temp_cn_seq_no+" AND FGSA_NO="+temp_regas_no+" AND " //SB20210324
							+ "MAPPING_ID='"+map_id+"' AND GAS_DT between to_date('"+rset.getString(1)+"','dd/mm/yyyy') and to_date('"+ReportDt+"','dd/mm/yyyy')";
					//SB+ " AND GAS_DT <= to_date('"+LastBilledQtyEndDt+"','dd/mm/yyyy')";
							//+ "  AND CONTRACT_TYPE='"+VContType.elementAt(i)+"' ";
						System.out.println(map_id+" :STEP-3A: FMS7_DAILY_ALLOCATION_DTL: "+queryString11);
					rset1 = stmt1.executeQuery(queryString11);
					//	Introduce By Milan Dalsaniya 20111012					
					if(rset1.next())
					{
							supplied = rset1.getDouble(1);
							total_supplied += supplied;
							
					}
					/////SB20210327:Daily Nom/SellerNom Qty////////////////////
					int CountAllocDt=0;
					queryString11="select COUNT(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL "+
							" WHERE SN_NO="+temp_cn_seq_no+" AND FGSA_NO="+temp_regas_no+" AND MAPPING_ID='"+map_id+"' AND GAS_DT =to_date('"+ReportDt+"','dd/mm/yyyy')";
					//	System.out.println(map_id+" :STEP-3A: FMS7_DAILY_ALLOCATION_DTL: "+queryString11);
					rset1 = stmt1.executeQuery(queryString11);
					//	Introduce By Milan Dalsaniya 20111012					
					if(rset1.next())
					{
						CountAllocDt = rset1.getInt(1);
					}
					
					if(CountAllocDt==0)
					{		
						int CountSellerDt=0;
						queryString11="select COUNT(QTY_MMBTU) from FMS7_DAILY_SELLER_NOM_DTL "+
								" WHERE SN_NO="+temp_cn_seq_no+" AND FGSA_NO="+temp_regas_no+" AND MAPPING_ID='"+map_id+"' AND GAS_DT =to_date('"+ReportDt+"','dd/mm/yyyy')";
						//	System.out.println(map_id+" :STEP-3A: FMS7_DAILY_ALLOCATION_DTL: "+queryString11);
						rset1 = stmt1.executeQuery(queryString11);
						//	Introduce By Milan Dalsaniya 20111012					
						if(rset1.next())
						{
							CountSellerDt = rset1.getInt(1);
						}
						if(CountSellerDt>0)
						{
							queryString11="select QTY_MMBTU, QTY_SCM from FMS7_DAILY_SELLER_NOM_DTL "
									+" WHERE  SN_NO="+temp_cn_seq_no+" AND FGSA_NO="+temp_regas_no+" AND MAPPING_ID='"+map_id+"' AND GAS_DT=to_date('"+ReportDt+"','dd/mm/yyyy') "
									+ "AND NOM_REV_NO=(select MAX(NOM_REV_NO) from FMS7_DAILY_SELLER_NOM_DTL "
									+" WHERE  SN_NO="+temp_cn_seq_no+" AND FGSA_NO="+temp_regas_no+" AND MAPPING_ID='"+map_id+"' AND GAS_DT=to_date('"+ReportDt+"','dd/mm/yyyy') ) ";
						//	System.out.println(map_id+" :STEP-3B: FMS7_DAILY_BUYER_NOM_DTL: "+queryString11);
							rset1 = stmt1.executeQuery(queryString11);
							//	Introduce By Milan Dalsaniya 20111012					
							if(rset1.next())
							{
								SellerNominated = rset1.getDouble(1);//System.out.println(" :SellerNominated: "+SellerNominated);
								total_supplied += SellerNominated;
								VStoreTPCustMmbtuSuppliedRptDt.add("SCH: "+SellerNominated); //SB20210614
							}
						}
						else if(CountSellerDt==0)
						{
							queryString11="select QTY_MMBTU, QTY_SCM from FMS7_DAILY_BUYER_NOM_DTL "
									+" WHERE  SN_NO="+temp_cn_seq_no+" AND FGSA_NO="+temp_regas_no+" AND MAPPING_ID='"+map_id+"' AND GAS_DT=to_date('"+ReportDt+"','dd/mm/yyyy') "
									+ "AND NOM_REV_NO=(select MAX(NOM_REV_NO) from FMS7_DAILY_BUYER_NOM_DTL "
									+" WHERE  SN_NO="+temp_cn_seq_no+" AND FGSA_NO="+temp_regas_no+" AND MAPPING_ID='"+map_id+"' AND GAS_DT=to_date('"+ReportDt+"','dd/mm/yyyy') ) ";
						//	System.out.println(map_id+" :STEP-3B: FMS7_DAILY_BUYER_NOM_DTL: "+queryString11);
							rset1 = stmt1.executeQuery(queryString11);
							//	Introduce By Milan Dalsaniya 20111012					
							if(rset1.next())
							{
								Nominated = rset1.getDouble(1);//System.out.println(" :Nominated: "+Nominated);
								total_supplied += Nominated;
								VStoreTPCustMmbtuSuppliedRptDt.add("NOM: "+Nominated); //SB20210614
							}
						}
					}
					else
						VStoreTPCustMmbtuSuppliedRptDt.add("ACT: "+supplied); //SB20210614
					/////////////////////////////////////////////////
					queryString ="select CUSTOMER_ABBR,CUSTOMER_NAME,CUSTOMER_CD from FMS7_CUSTOMER_MST where CUSTOMER_CD="+temp_cust_cd+" "
							+ "AND EFF_DT=(SELECT MAX(EFF_DT) FROM FMS7_CUSTOMER_MST WHERE CUSTOMER_CD='"+temp_cust_cd+"' AND EFF_DT<=TO_DATE('"+ReportDt+"','DD/MM/YYYY'))";
					rset1 = stmt1.executeQuery(queryString);
					////System.out.println(queryString);
					if(rset1.next())
					{
						VStoreTPCustNm.add(rset1.getString(1)==null?"":rset1.getString(1));
					}
				}
				
				
				String inv_level_dt_month=""; double Total_Inventory_mmscm=0; double Total_Inventory_mmbtu=0; 
				double Total_Inventory_mmscmT2=0; double Total_Inventory_mmbtuT2=0; //SB20210517
			/*SB20210210:For Graphical Table	queryString1 = "SELECT TO_CHAR(MAX(INV_DT),'DD/MM/YYYY') "+
						" FROM FMS8_MONTHLY_TANK_DETAILS WHERE TO_DATE(INV_DT, 'DD-MON-YY')<=TO_DATE(to_date('"+ReportDt+"','dd/mm/yyyy'), 'DD-MON-YY')";
						*/
				////////////////////SB20210715:STORAGE: As discussed with rohot & Shiladitya//////////////////////////////////
				String StorageDate="";
				queryString = "SELECT TO_CHAR(TO_DATE('"+ReportDt+"','DD/MM/YYYY')+1,'DD/MM/YYYY') FROM DUAL";
				//	System.out.println("Get DAY Name: "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						StorageDate = rset.getString(1)==null?"":rset.getString(1); //SB20201128;
					}
				////////////////////^^^SB20210715:STORAGE/////////////////////////////////////////////	
				queryString1 = "SELECT TO_CHAR(MAX(INV_LEVEL_DT),'DD/MM/YYYY') "+
				//SB20210715		" FROM FMS7_INVENTORY_LEVEL_DTL WHERE TO_DATE(INV_LEVEL_DT, 'DD-MON-YY')<=TO_DATE(to_date('"+ReportDt+"','dd/mm/yyyy'), 'DD-MON-YY')";
				" FROM FMS7_INVENTORY_LEVEL_DTL WHERE TO_DATE(INV_LEVEL_DT, 'DD-MON-YY')<=TO_DATE(to_date('"+StorageDate+"','dd/mm/yyyy'), 'DD-MON-YY')";//SB20210715
						System.out.println("Max. Inv Dt: "+queryString1);
						rset1 = stmt1.executeQuery(queryString1);
						if(rset1.next())
						{
							inv_level_dt_month = rset1.getString(1)==null?"":rset1.getString(1);
						}
						else
							inv_level_dt_month ="";
						if(!inv_level_dt_month.equals(""))
						{	
							/*SB20210210:For Graphical Table		queryString1 = "SELECT SUM(T1_MMSCM+T2_MMSCM), SUM(T1_MMBTU+T2_MMBTU) "+
							" FROM FMS8_MONTHLY_TANK_DETAILS WHERE INV_DT=TO_DATE('"+inv_level_dt_month+"','DD/MM/YYYY')";*/
							/*queryString1 = "SELECT SUM(T1_MMSCM+T2_MMSCM), SUM(T1_MMBTU+T2_MMBTU) "+
									" FROM FMS7_INVENTORY_LEVEL_DTL WHERE INV_LEVEL_DT=TO_DATE('"+inv_level_dt_month+"','DD/MM/YYYY')";
							System.out.println("Max. Inv Dt: "+queryString1);
							rset1 = stmt1.executeQuery(queryString1);
							if(rset1.next())
							{
								Total_Inventory_mmscm = rset1.getDouble(1);
								Total_Inventory_mmbtu = rset1.getDouble(2);
							}*/
							queryString1 = "SELECT SUM(T1_MMSCM), SUM(T2_MMSCM), SUM(T1_MMBTU),SUM(T2_MMBTU) "+
									" FROM FMS7_INVENTORY_LEVEL_DTL WHERE INV_LEVEL_DT=TO_DATE('"+inv_level_dt_month+"','DD/MM/YYYY')";
							System.out.println("Max. Inv Dt: "+queryString1);
							rset1 = stmt1.executeQuery(queryString1);
							if(rset1.next())
							{
								Total_Inventory_mmscm = rset1.getDouble(1);
								Total_Inventory_mmbtu = rset1.getDouble(3);
								Total_Inventory_mmscmT2 = rset1.getDouble(2);
								Total_Inventory_mmbtuT2 = rset1.getDouble(4);
							}
							Total_Inventory_mmscm = Total_Inventory_mmscm+Total_Inventory_mmscmT2;
							Total_Inventory_mmbtu = Total_Inventory_mmbtu+Total_Inventory_mmbtuT2;
						}
						
						tot_commitment=nf.format(tot_comit);
					  	tot_comit_mmscm=(tot_comit/38900);//MD20110916
					  	tot_commitment_mmscm=nf.format(tot_comit_mmscm);
					  	supplied_mmscm=total_supplied/38900;
				tankTotal=tot_tank1+tot_tank2;
				balance=Double.parseDouble(tot_commitment_mmscm)-Double.parseDouble(nf.format(supplied_mmscm));
				TPBalanceMmbtu=tot_comit-total_supplied; //SB20210630
				InventoryDt=inv_level_dt_month;
				VDealType.add("Storage"); //SB20201021
				VStoreTankTotal.add(Total_Inventory_mmscm);
				
				VStoreTPTotalmmscm.add(tot_commitment_mmscm);
				VStoreTPTotal.add(tot_commitment);
				VStoreTPSuppliedmmscm.add(nf.format(supplied_mmscm));
				VStoreTPBalancemmscm.add(nf.format(balance));
				VStoreSEIPLmmscm.add(nf.format(Total_Inventory_mmscm-balance));
				/*//SB20210715 if((Total_Inventory_mmscm-balance)>0)
				//SB20210630	VStoreSEIPL.add(nf.format((Total_Inventory_mmscm-balance)*38900));
					VStoreSEIPL.add((Total_Inventory_mmbtu-TPBalanceMmbtu));
				else
					VStoreSEIPL.add("0");*/
				VStoreSEIPL.add(nf.format(Total_Inventory_mmbtu-TPBalanceMmbtu)); //SB20210715
				
				System.out.println(VStoreTPCustNm.size()+" :VStoreTPCustNm: "+VStoreTPCustNm);
				System.out.println(VStoreTPCustMmscm.size()+" :VStoreTPCustMmscm: "+VStoreTPCustMmscm);
				System.out.println(VStoreTPCustMmbtuEDQ.size()+" :VStoreTPCustMmbtuEDQ: "+VStoreTPCustMmbtuEDQ);
				System.out.println(VStoreTPCustMmbtuADQ.size()+" :VStoreTPCustMmbtuADQ: "+VStoreTPCustMmbtuADQ);
				System.out.println(VStoreTPCustMmbtuADQ_DT.size()+" :VStoreTPCustMmbtuADQ_DT: "+VStoreTPCustMmbtuADQ_DT);
				System.out.println(VStoreTPCustMmbtuEDQ_ST_DT.size()+" :VStoreTPCustMmbtuEDQ_ST_DT: "+VStoreTPCustMmbtuEDQ_ST_DT);
				System.out.println(VStoreTPCustMmbtuEDQ_END_DT.size()+" :VStoreTPCustMmbtuEDQ_END_DT: "+VStoreTPCustMmbtuEDQ_END_DT);
				System.out.println(VStoreTPCustMmbtuSuppliedRptDt.size()+" :VStoreTPCustMmbtuSuppliedRptDt: "+VStoreTPCustMmbtuSuppliedRptDt);
				
				System.out.println(VDealType.size()+" :VDealType: "+VDealType);
				System.out.println(VStoreTPTotal.size()+" :Total Commited-MMBTU: "+VStoreTPTotal);
				System.out.println(VStoreTPTotalmmscm.size()+" :Total Commited: "+VStoreTPTotalmmscm);
				System.out.println(VStoreTPSuppliedmmscm.size()+" :Total Supplied: "+VStoreTPSuppliedmmscm);
				System.out.println(" :balance_mmscm: "+balance);
				System.out.println(" :Total_Inventory_mmscm: "+Total_Inventory_mmscm);
				System.out.println(VStoreSEIPLmmscm.size()+" :VStorageSEIPLmmscm: "+VStoreSEIPLmmscm);
				System.out.println(VStoreSEIPL.size()+" :VStorageSEIPL-MMBTU: "+VStoreSEIPL);
	}
	catch(Exception e)
		{
		//System.out.println("Exception in Databean_Contract_MasterReport--->fetchcustomer()-->"+e);
		e.printStackTrace();	
		}
}
////////////////////////^^^^^^SB20210201: Freezed Data/////////////////////////////////////////////////
private void addColumnsFMS9_MRCR_CONT_PRICE_DTL() throws SQLException  {
	try {
		int count=0;
/*SB20210721		String s="SELECT COUNT(COLUMN_NAME) FROM COLS WHERE UPPER(TABLE_NAME) LIKE 'FMS9_MRCR_CONT_PRICE_DTL' "+
				" AND UPPER(COLUMN_NAME) LIKE 'PHYS_CURVE_NM'";
		System.out.println("Add Column : FMS9_MRCR_CONT_PRICE_DTL: "+s);
		rset=stmt.executeQuery(s);
		if(rset.next())
		{
			count=rset.getInt(1);
		}
		if(count==0)
		{
			s="ALTER TABLE FMS9_MRCR_CONT_PRICE_DTL ADD PHYS_CURVE_NM VARCHAR2(30 BYTE)";
			stmt.executeUpdate(s);
			conn.commit();
		}	
		count=0;
		s="SELECT COUNT(COLUMN_NAME) FROM COLS WHERE UPPER(TABLE_NAME) LIKE 'LOG_FMS9_MRCR_CONT_PRICE_DTL' "+
				" AND UPPER(COLUMN_NAME) LIKE 'PHYS_CURVE_NM'";
		System.out.println("Add Column : LOG_FMS9_MRCR_CONT_PRICE_DTL: "+s);
		rset=stmt.executeQuery(s);
		if(rset.next())
		{
			count=rset.getInt(1);
		}
		if(count==0)
		{
			s="ALTER TABLE LOG_FMS9_MRCR_CONT_PRICE_DTL ADD PHYS_CURVE_NM VARCHAR2(30 BYTE)";
			stmt.executeUpdate(s);
			conn.commit();
		}		
		count=0;
		s="SELECT COUNT(COLUMN_NAME) FROM COLS WHERE UPPER(TABLE_NAME) LIKE 'FMS9_EOD_EXPOSURE_MST' "+
				" AND UPPER(COLUMN_NAME) LIKE 'PHYS_CURVE_NM'";
		System.out.println("Add Column : FMS9_EOD_EXPOSURE_MST: "+s);
		rset=stmt.executeQuery(s);
		if(rset.next())
		{
			count=rset.getInt(1);
		}
		if(count==0)
		{
			s="ALTER TABLE FMS9_EOD_EXPOSURE_MST ADD PHYS_CURVE_NM VARCHAR2(30 BYTE)";
			stmt.executeUpdate(s);
			conn.commit();
		}	
		count=0;
		s="SELECT COUNT(COLUMN_NAME) FROM COLS WHERE UPPER(TABLE_NAME) LIKE 'FMS9_EOD_EXPOSURE_DTL' "+
				" AND UPPER(COLUMN_NAME) LIKE 'PHYS_CURVE_NM'";
		System.out.println("Add Column : FMS9_EOD_EXPOSURE_DTL: "+s);
		rset=stmt.executeQuery(s);
		if(rset.next())
		{
			count=rset.getInt(1);
		}
		if(count==0)
		{
			s="ALTER TABLE FMS9_EOD_EXPOSURE_DTL ADD PHYS_CURVE_NM VARCHAR2(30 BYTE)";
			stmt.executeUpdate(s);
			conn.commit();
		}	
		/////////////////////For Forward Pricing///////////////////////////////////////
		count=0;
		s="SELECT COUNT(COLUMN_NAME) FROM COLS WHERE UPPER(TABLE_NAME) LIKE 'FMS9_FORWARD_PRICE_DTL' "+
				" AND UPPER(COLUMN_NAME) LIKE 'GAS_KGD6'";
		System.out.println("Add Column : FMS9_EOD_EXPOSURE_DTL: "+s);
		rset=stmt.executeQuery(s);
		if(rset.next())
		{
			count=rset.getInt(1);
		}
		if(count==0)
		{
			s="ALTER TABLE FMS9_FORWARD_PRICE_DTL ADD GAS_KGD6 NUMBER(12,8)";
			stmt.executeUpdate(s);
			conn.commit();
		}	*/
		String s="SELECT COUNT(COLUMN_NAME) FROM COLS WHERE UPPER(TABLE_NAME) LIKE 'FMS9_MRCR_CONT_PRICE_DTL' "+
				" AND UPPER(COLUMN_NAME) LIKE 'DEAL_FORMULA'";
		System.out.println("Add Column : FMS9_MRCR_CONT_PRICE_DTL: "+s);
		rset=stmt.executeQuery(s);
		if(rset.next())
		{
			count=rset.getInt(1);
		}
		if(count==0)
		{
			s="ALTER TABLE FMS9_MRCR_CONT_PRICE_DTL ADD DEAL_FORMULA VARCHAR2(50 BYTE)";
			stmt.executeUpdate(s);
			conn.commit();
		}	
		//////////////////////////////////////////////////////////////////////////////////////////////////////////
	}
	catch(Exception e) {
		conn.rollback();
		e.printStackTrace();
	}
}
private void UpdateFMS9_CURVE2_PRICE_DTL() throws SQLException  {
	try {
		int count=0;
		String s="SELECT COUNT(PHYS_FIN) FROM FMS9_CURVE2_PRICE_DTL WHERE CURVE_NM='PLATTS_JKM' AND PHYS_FIN ='Financial' ";
	//	System.out.println("Check  : FMS9_CURVE2_PRICE_DTL: "+s);
		rset=stmt.executeQuery(s);
		if(rset.next())
		{
			count=rset.getInt(1);
		}
		if(count>0)
		{
			s="UPDATE FMS9_CURVE2_PRICE_DTL SET PHYS_FIN ='ICE_JKM' WHERE CURVE_NM='PLATTS_JKM' AND PHYS_FIN ='Financial'";
			System.out.println("UPDATE PLATTS_JKM : FMS9_CURVE2_PRICE_DTL: "+s);
			stmt.executeUpdate(s);
			conn.commit();
		}
		count=0;
		s="SELECT COUNT(PHYS_FIN) FROM FMS9_CURVE2_PRICE_DTL WHERE CURVE_NM='JKM_PLATTS_SETTLEMENT' AND PHYS_FIN ='Financial' ";
		//System.out.println("Check  : FMS9_CURVE2_PRICE_DTL: "+s);
		rset=stmt.executeQuery(s);
		if(rset.next())
		{
			count=rset.getInt(1);
		}
		if(count>0)
		{
			s="UPDATE FMS9_CURVE2_PRICE_DTL SET PHYS_FIN ='ICE_JKM' WHERE CURVE_NM='JKM_PLATTS_SETTLEMENT' AND PHYS_FIN ='Financial' ";
			System.out.println("UPDATE JKM_PLATTS_SETTLEMENT : FMS9_CURVE2_PRICE_DTL: "+s);
			stmt.executeUpdate(s);
			conn.commit();
		}
		
		
		s="SELECT COUNT(PHYS_FIN) FROM FMS9_CURVE2_PRICE_DTL WHERE CURVE_NM='ICE_BRENT' AND PHYS_FIN ='Financial' ";
		//System.out.println("Check  : FMS9_CURVE2_PRICE_DTL: "+s);
		rset=stmt.executeQuery(s);
		if(rset.next())
		{
			count=rset.getInt(1);
		}
		if(count>0)
		{
			s="UPDATE FMS9_CURVE2_PRICE_DTL SET PHYS_FIN ='ICE_BRENT' WHERE CURVE_NM='ICE_BRENT' AND PHYS_FIN ='Financial'";
			System.out.println("UPDATE ICE_BRENT : FMS9_CURVE2_PRICE_DTL: "+s);
			stmt.executeUpdate(s);
			conn.commit();
		}
		count=0;
		s="SELECT COUNT(PHYS_FIN) FROM FMS9_CURVE2_PRICE_DTL WHERE CURVE_NM='ICE_BRENT_FIRST_LINE' AND PHYS_FIN ='Financial' ";
		//System.out.println("Check  : FMS9_CURVE2_PRICE_DTL: "+s);
		rset=stmt.executeQuery(s);
		if(rset.next())
		{
			count=rset.getInt(1);
		}
		if(count>0)
		{
			s="UPDATE FMS9_CURVE2_PRICE_DTL SET PHYS_FIN ='ICE_BRENT' WHERE CURVE_NM='ICE_BRENT_FIRST_LINE' AND PHYS_FIN ='Financial' ";
			System.out.println("UPDATE ICE_BRENT_FIRST_LINE : FMS9_CURVE2_PRICE_DTL: "+s);
			stmt.executeUpdate(s);
			conn.commit();
		}
		/////////////////////SB20210824:DATEDBRENT_PLATTS_SETTLEMENT/////////////////////////////////////////////
		int count22=999; //Already done in LIVE
		if(count22==0)
		{
		s="ALTER TABLE FMS9_CURVE2_PRICE_DTL MODIFY PHYS_FIN VARCHAR2(40)";
		stmt.executeUpdate(s);
		conn.commit();
		}	
		count=0;
		s="SELECT COUNT(PHYS_FIN) FROM FMS9_CURVE2_PRICE_DTL WHERE CURVE_NM='DATEDBRENT_PLATTS_SETTLEMENT' AND PHYS_FIN ='Financial' ";
		//System.out.println("Check  : FMS9_CURVE2_PRICE_DTL: "+s);
		rset=stmt.executeQuery(s);
		if(rset.next())
		{
			count=rset.getInt(1);
		}
		if(count>0)
		{
			s="UPDATE FMS9_CURVE2_PRICE_DTL SET PHYS_FIN ='ICE_DATED_BRENT' WHERE CURVE_NM='DATEDBRENT_PLATTS_SETTLEMENT' AND PHYS_FIN ='Financial' ";
			System.out.println("UPDATE DATEDBRENT_PLATTS_SETTLEMENT : FMS9_CURVE2_PRICE_DTL: "+s);
			stmt.executeUpdate(s);
			conn.commit();
		}
		/////////////////////////////SB20210913: New Pricing NYM_HH_SETTLEMENT ////////////////////////////////////////////
		count=0;
		s="SELECT COUNT(PHYS_FIN) FROM FMS9_CURVE2_PRICE_DTL WHERE CURVE_NM='NYM_HH_SETTLEMENT' AND PHYS_FIN ='Financial' ";
		//System.out.println("Check  : FMS9_CURVE2_PRICE_DTL: "+s);
		rset=stmt.executeQuery(s);
		if(rset.next())
		{
		count=rset.getInt(1);
		}
		if(count>0)
		{
			s="UPDATE FMS9_CURVE2_PRICE_DTL SET PHYS_FIN ='NYM_HH' WHERE CURVE_NM='NYM_HH_SETTLEMENT' AND PHYS_FIN ='Financial' ";
			System.out.println("UPDATE ICE_BRENT_FIRST_LINE : FMS9_CURVE2_PRICE_DTL: "+s);
			stmt.executeUpdate(s);
			conn.commit();
		}
		//////////////////////////////////////////////////////////////////////////////////////////////////////////NYM_HH_SETTLEMENT
	}
	catch(Exception e) {
		conn.rollback();
		e.printStackTrace();
	}
}

////////////////////////^^^^^^SB20210201: Freezed Data/////////////////////////////////////////////////
private void addColumnsFMS7_MAN_CONFIRM_CARGO_DTL() throws SQLException  {
	try {
		int count=0;
		String s="SELECT COUNT(COLUMN_NAME) FROM COLS WHERE UPPER(TABLE_NAME) LIKE 'FMS7_MAN_CONFIRM_CARGO_DTL' "+
		" AND UPPER(COLUMN_NAME) LIKE 'DOM_BUY_FLAG'";
		System.out.println("Add Column : FMS7_MAN_CONFIRM_CARGO_DTL: "+s);
		rset=stmt.executeQuery(s);
		if(rset.next())
		{
		count=rset.getInt(1);
		}
		if(count==0)
		{
		s="ALTER TABLE FMS7_MAN_CONFIRM_CARGO_DTL ADD DOM_BUY_FLAG CHAR(1)";
		stmt.executeUpdate(s);
		conn.commit();
		}	
	}
	catch(Exception e) {
		conn.rollback();
		e.printStackTrace();
	}
}
///////////////////////SB20210705: Set DOM/INT Buy Deal ///////////////////////////////////////////
private void UpdateFMS7_MAN_CONFIRM_CARGO_DTL() throws SQLException  {
	try {
		queryString = "SELECT TO_CHAR(A.DELV_FROM_DT,'DD/MM/YYYY'), TO_CHAR(A.DELV_TO_DT,'DD/MM/YYYY'),A.MAN_CONF_CD,A.CARGO_SEQ_NO,E.TRD_CD, A.MAN_CD, A.MAN_CD, A.CONFIRM_VOL,"
			+ "A.CARGO_REF_CD,to_char(C.MAN_CONF_DT,'dd/mm/yyyy'), A.PRICE,A.EMP_CD,TO_CHAR(A.ENT_DT,'dd/mm/yyyy'), A.EMP_CD,TO_CHAR(A.ENT_DT,'dd/mm/yyyy'), E.PRICE_TYPE, A.PRICE_TO, A.REMARK  "
			+ ",DOM_BUY_FLAG "+ //SB20210618
			" FROM FMS7_MAN_CONFIRM_CARGO_DTL A, FMS7_MAN_CONFIRM_MST C, FMS7_MAN_REQ_MST E WHERE "+
			///SB20210522		" A.DELV_TO_DT >= TO_DATE('"+to_date+"','DD/MM/YYYY') "
			" A.DELV_TO_DT >= TO_DATE('01/01/2021','DD/MM/YYYY') AND " + ///SB20210522 : ad discussed on 21stMay21 by Shiladitya to Include all Deals irrespective of Expairy on Report Date
			" E.MAN_CD=A.MAN_CD AND "+ 
		///		" A.UNIT_CD=C.UNIT_CD AND A.CARGO_STATUS='T' AND CARGO_REF_CD='"+VCAR_REF_NO_temp.elementAt(i)+"' "+ 
			" A.MAN_CONF_CD=C.MAN_CONF_CD AND A.CARGO_STATUS='T' AND CARGO_REF_CD LIKE '%' "+ 
			" ORDER BY E.TRD_CD, A.MAN_CONF_CD, A.CARGO_REF_CD";
		//	System.out.println("MRver2.0 CARGO: "+queryString);
		rset = stmt.executeQuery(queryString);
		////System.out.println("FOr SN : "+to_date+" "+from_date);
		while(rset.next())
		{				
			String win_from_dt = rset.getString(1)==null?"":rset.getString(1);
			String win_to_dt = rset.getString(2)==null?"":rset.getString(2);
			String DomBuyFlag=rset.getString(19)==null?"":rset.getString(19); //SB20210618

			if(DomBuyFlag.equals("")) 
			{
			int CountWindowDays=0; String DomFlag="";
				String QueryWin="select to_date('"+win_to_dt+"','dd/mm/yyyy')- to_date('"+win_from_dt+"','dd/mm/yyyy') from dual";
			//	System.out.println("DEAL-DTL: DUAL: "+QueryWin);
				rset1=stmt1.executeQuery(QueryWin);
				if(rset1.next())
				{
					CountWindowDays=rset1.getInt(1);
				}
			if(CountWindowDays<=3) 
				DomFlag="N";
			else
				DomFlag="Y";
			
				QueryWin="update FMS7_MAN_CONFIRM_CARGO_DTL set DOM_BUY_FLAG='"+DomFlag+"' "
			             +"WHERE MAN_CONF_CD='"+rset.getString(3)+"' and MAN_CD='"+rset.getString(6)+"' "
			             + "AND CARGO_REF_CD='"+rset.getString(9)+"' AND CARGO_SEQ_NO='"+rset.getString(4)+"' and FLAG='T'";
				System.out.println("UPDATE DOM_BUY_FLAG : FMS7_MAN_CONFIRM_CARGO_DTL: "+QueryWin);
				stmt2.executeUpdate(QueryWin);
				conn.commit();
				System.out.println("B"+rset.getString(3)+"-"+rset.getString(6)+"-"+rset.getString(9)+"-"+rset.getString(4)+" DOM-DEAL-DTL Updated as: DomBuyFlag: "+DomBuyFlag);
			}
		}
		}
		catch(Exception e) {
			conn.rollback();
			e.printStackTrace();
		}
}
///////////////////////////Cargo Buy Deal Cancellation////////////////////////////////////////////////////////////////
public void MRCR_Cargo_Cancelled_Deal()
{
	try
	{
		queryString = "SELECT TO_CHAR(A.DELV_FROM_DT,'DD/MM/YYYY'), TO_CHAR(A.DELV_TO_DT,'DD/MM/YYYY'),A.MAN_CONF_CD,A.CARGO_SEQ_NO,E.TRD_CD, A.MAN_CD, A.MAN_CD, A.CONFIRM_VOL,"
				+ "A.CARGO_REF_CD,to_char(C.MAN_CONF_DT,'dd/mm/yyyy'), A.PRICE,A.EMP_CD,TO_CHAR(A.ENT_DT,'dd/mm/yyyy'), A.EMP_CD,TO_CHAR(A.ENT_DT,'dd/mm/yyyy'), E.PRICE_TYPE, A.PRICE_TO, A.REMARK  "
				+ ",DOM_BUY_FLAG "+ //SB20210618
				" FROM FMS7_MAN_CONFIRM_CARGO_DTL A, FMS7_MAN_CONFIRM_MST C, FMS7_MAN_REQ_MST E WHERE "+
				///SB20210522		" A.DELV_TO_DT >= TO_DATE('"+to_date+"','DD/MM/YYYY') "
				" A.DELV_TO_DT >= TO_DATE('01/01/2021','DD/MM/YYYY') AND " + ///SB20210522 : ad discussed on 21stMay21 by Shiladitya to Include all Deals irrespective of Expairy on Report Date
				" E.MAN_CD=A.MAN_CD AND "+ 
		///		" A.UNIT_CD=C.UNIT_CD AND A.CARGO_STATUS='T' AND CARGO_REF_CD='"+VCAR_REF_NO_temp.elementAt(i)+"' "+ 
				" A.MAN_CONF_CD=C.MAN_CONF_CD AND A.CARGO_STATUS='X' AND CARGO_REF_CD LIKE '%' "+ 
				" ORDER BY E.TRD_CD, A.MAN_CONF_CD, A.CARGO_REF_CD";
		//	System.out.println("MRver2.0 CARGO: "+queryString);
		rset = stmt.executeQuery(queryString);
		////System.out.println("FOr SN : "+to_date+" "+from_date);
		while(rset.next())
		{				
			String tmp_from_dt = rset.getString(1)==null?"":rset.getString(1);
			String tmp_to_dt = rset.getString(2)==null?"":rset.getString(2);
			//FRM_DT.add(rset.getString(1)==null?"":rset.getString(1));
			//TO_DT.add(rset.getString(2)==null?"":rset.getString(2));
			String temp_dt = tmp_from_dt+"   -   "+tmp_to_dt;
			VCargo_Arvl_Dt_X.add(temp_dt);				
			VSN_No_X.add(rset.getString(3)==null?"0":rset.getString(3));
			VSN_Rev_No_X.add(rset.getString(4)==null?"0":rset.getString(4));
			VCust_Cd_X.add(rset.getString(5)==null?"0":rset.getString(5));
		  	VFGSA_No_X.add(rset.getString(6)==null?"0":rset.getString(6));
		  	VFGSA_Rev_No_X.add("0");	 //No Rev no in LoA
		  	VBOOKMMBTU_X.add(rset.getString(8)==null?"0.00":nf.format(Double.parseDouble(rset.getString(8))));
		  	double tot_comit = Double.parseDouble(rset.getString(8)==null?"0":nf.format(Double.parseDouble(rset.getString(8))));
		  	VCost_X.add(nf.format(tot_comit*Double.parseDouble(rset.getString(11))));
		  	tot_commitment=nf.format(tot_comit);
		  	VSN_Ref_No_X.add(rset.getString(9)==null?"":rset.getString(9));	
		  	VSIGNING_Dt_X.add(rset.getString(10)==null?"0":rset.getString(10));
		  	VRATE_X.add(nf2.format(Double.parseDouble(rset.getString(11)==null?"0":rset.getString(11))));
		  	String TEMP_apr_by=rset.getString(12)==null?"0":rset.getString(12);
		  	sn_apr_dt.add(rset.getString(13)==null?"-":rset.getString(13));
		  	String temp_ent_by=rset.getString(14)==null?"0":rset.getString(14);
		  	sn_ent_dt.add(rset.getString(15)==null?"-":rset.getString(15));
		  	VContType.add("B"); //SB20201108
		  	String DomBuyFlag=rset.getString(19)==null?"":rset.getString(19); //SB20210618
		  	if(DomBuyFlag.equals("Y")) 
		  		DomBuyFlag=" (DOM)";
		  	else
		  		DomBuyFlag=""; //SB20210630
		  
		  	queryString ="select TRADER_ABBR,TRADER_NAME,TRADER_CD from FMS7_TRADER_MST B where TRADER_CD="+rset.getString(5)+" "
					+ "and eff_dt=(select max(eff_dt) from FMS7_TRADER_MST where TRADER_CD='"+rset.getString(5)+"' and eff_dt<=to_date('"+rset.getString(1)+"','dd/mm/yyyy'))";	
		  	rset1 = stmt1.executeQuery(queryString);
			//	System.out.println(queryString);
				if(rset1.next())
				{
					DomBuyFlag=rset1.getString(2)+DomBuyFlag;
			//SB20210618	CUSTOMER_NM.add(rset1.getString(2)==null?"":rset1.getString(2));
					VCUSTOMER_Nm_X.add(DomBuyFlag);
				//	CUSTOMER_ABBR.add(rset1.getString(1)==null?"":rset1.getString(1));	
				}
		
		  	/*String queryString_nm ="SELECT EMP_NM FROM HR_EMP_MST WHERE EMP_CD='"+TEMP_apr_by+"'";
		  	rset1=stmt1.executeQuery(queryString_nm);
		  	if(rset1.next()){
		  		sn_apr_by.add(rset1.getString(1)==null?"-":rset1.getString(1));
		  	}else{
		  		sn_apr_by.add("-");
		  	}
		  	VCont_Start_Dt.add(rset.getString(1)==null?"":rset.getString(1));
		  	VCont_End_Dt.add(rset.getString(2)==null?"":rset.getString(2));
		  	
		  	queryString_nm ="SELECT EMP_NM FROM HR_EMP_MST WHERE EMP_CD='"+temp_ent_by+"'";
		  	rset1=stmt1.executeQuery(queryString_nm);
		  	if(rset1.next()){
		  		sn_ent_by.add(rset1.getString(1)==null?"-":rset1.getString(1));
		  	}else{
		  		sn_ent_by.add("-");
		  	}*/
		 // 	MAPPING_ID.add("C"+rset.getString(6)+"-"+rset.getString(7)+"-"+rset.getString(3)+"-"+rset.getString(4));
		//  	VMAPPING_ID_X.add("B"+rset.getString(3)+"-0-"+rset.getString(6)+"-"+rset.getString(7));
		 //as stored into FMS9_MRCR_CONT_PRICE_DTL  	String Mapp_Id=CUSTOMER_CD.elementAt(i)+"-"+SN_NO.elementAt(i)+"-"+SN_REV_NO.elementAt(i)+"-"+SN_REF_NO.elementAt(i)+"-0";
		}
	}
	catch(Exception e) {
		//conn.rollback();
		e.printStackTrace();
	}
}	
Vector VCargo_Arvl_Dt_X = new Vector();
public Vector getVCargo_Arvl_Dt_X() {return VCargo_Arvl_Dt_X;}
Vector VSN_No_X = new Vector();
public Vector getVSN_No_X() {return VSN_No_X;}
Vector VSN_Rev_No_X = new Vector();
public Vector getVSN_Rev_No_X() {return VSN_Rev_No_X;}
Vector VCust_Cd_X = new Vector();
public Vector getVCust_Cd_X() {return VCust_Cd_X;}
Vector VFGSA_No_X = new Vector();
public Vector getVFGSA_No_X() {return VFGSA_No_X;}
Vector VFGSA_Rev_No_X = new Vector();
public Vector getVFGSA_Rev_No_X() {return VFGSA_Rev_No_X;}
Vector VBOOKMMBTU_X = new Vector();
public Vector getVBOOKMMBTU_X() {return VBOOKMMBTU_X;}
Vector VSN_Ref_No_X = new Vector();
public Vector getVSN_Ref_No_X() {return VSN_Ref_No_X;}
Vector VRATE_X = new Vector();
public Vector getVRATE_X() {return VRATE_X;}
Vector VSIGNING_Dt_X = new Vector();
public Vector getVSIGNING_Dt_X() {return VSIGNING_Dt_X;}
Vector VCUSTOMER_Nm_X = new Vector();
public Vector getVCUSTOMER_Nm_X() {return VCUSTOMER_Nm_X;}
Vector VCost_X = new Vector();
public Vector getVCost_X() {return VCost_X;}
///////////////////////^^^^Cargo Buy Deal Cancellation//////////////////////////////////////////////////////////////////////
//////////////////////////////SB20210710:BreakUp//////////////////////////////////////////////////////////////////////	
public void MRCR_ContDtlFinBreakUp(String MappId, String ContType)
{
	try
	{
		double SumTotalRLNG_PHYS=0; double SumTotalLNG_JKM=0; double SumTotalRLNG_PHYS_INDIA_EXPOSURE_U=0; 
		double SumTotalLNG_ICE_JKM_EXPOSURE_U=0; double SumTotalU_Phy_Leg=0; double SumTotalU_Fin_Leg=0; double SumTotalTotal=0;
		System.out.println(VLNG_ICE_JKM_EXPO.size()+" :BREAKUP ---->>>>>>>> Started---------------->>>>>>>>): ");
	//SB20210911	System.out.println(VLNG_ICE_JKM_EXPO.size()+" :BREAKUP ---->>>>>>>> VLNG_ICE_JKM_EXPO): "+VLNG_ICE_JKM_EXPO);
	//SB20210911	System.out.println(VPriceTypeDealSeQNo.size()+" :BREAKUP ---->>>>>>>> VPriceTypeDealSeQNo): "+VLNG_ICE_JKM_EXPO);
		int NoMth=3; int StartingPrevMth=1; int NoMth_FinCurve=3;
		double Phy_MthBrkUp=0; double Fin_MthBrkUp=0; 
		String PrevContMthYr="01/01/2021";//+VMthYrWise.elementAt(0);
		Vector VContFixedPrice_Temp = new Vector();
		Vector VFin_CurrContMthYr = new Vector();
		Vector VFin_SettleStDt_Temp = new Vector();
		Vector VFin_SettleEndDt_Temp = new Vector();
		Vector VFin_SettlePrice_Temp = new Vector();
		Vector VFin_EffSettlePrice_Temp = new Vector();
		Vector VFin_AvgEffSettlePrice_Temp = new Vector();
		Vector VFin_Slope_Temp = new Vector();
		Vector VFin_Constant_Temp = new Vector();
		Vector VFin_FwdPrice_Temp = new Vector();
		Vector VPhy_FwdPrice_Temp = new Vector();
		Vector VMutiCurveNm_Temp = new Vector();
		
		String CurveName=""; String PhyCurFrdPriceNm="";  String IndexSettlePrice="A"; String PriceFormula=""; String DealName=""; String PriceType="F";
		/*queryString ="SELECT PRICE_TYPE, CURVE_NM, RATE, SEQ_NO, PHYS_CURVE_NM, REMARKS FROM FMS9_MRCR_CONT_PRICE_DTL " //SB20210311
				//SB20210528		+ "WHERE MAPPING_ID='"+Mapp_Id+"' AND CONTRACT_TYPE='"+VContType.elementAt(i)+"' AND FLAG='Y' ";
		+ "WHERE MAPPING_ID LIKE '"+MappId+"' AND CONTRACT_TYPE='"+ContType+"' AND FLAG='Y' "; //SB20210528
		System.out.println("FMS9_CURVE_PRICE_DTL: "+queryString);
		rset2 = stmt.executeQuery(queryString);			
		if(rset2.next())
		{
			CurveName=rset2.getString(2)==null?"":rset2.getString(2);
			PhyCurFrdPriceNm=rset2.getString(5)==null?"":rset2.getString(5);
			PriceFormula=rset2.getString(6)==null?"":rset2.getString(6);
		}
		System.out.println("PriceFormula: "+PriceFormula);
		if(!PriceFormula.equals(""))
		{
			String tempPriceFormula[]=PriceFormula.split("@"); //OPAL@0@3  : TypeOfDeal@PreviousMonth(Zero)@UptoPreviousMonth(3) 
			if(tempPriceFormula.length>2)
			{				
				NoMth=Integer.parseInt(tempPriceFormula[2]);
				StartingPrevMth=Integer.parseInt(tempPriceFormula[1]);
				DealName=tempPriceFormula[0];
			}
		}
		*/
		String BuySell="Sell"; 
		String PriceTypeCargo="M";
		
		double U_Phy_Leg=0;
		double U_Fin_Leg=0;
		double R_Fin_Leg=0;
		 
		for(int i=0; i<VDealDCQ.size(); i++)
		{		
			BuySell=""+VBuySell.elementAt(i); 
			String DCQ=""+VDealDCQ.elementAt(i);
			if(BuySell.equalsIgnoreCase("Sell"))
				DCQ="-"+DCQ;
			String CurrContMthYr="01/"+VMthYrWise.elementAt(i);
			String DealPrice=""+RATE.elementAt(i);
			int ContMthDiff=0;
			queryString = "SELECT TO_DATE('"+CurrContMthYr+"','DD/MM/YYYY')-TO_DATE('"+PrevContMthYr+"','DD/MM/YYYY') FROM DUAL" ;
			//	System.out.println(" DUAL: LAST: "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next()) {
					ContMthDiff = rset.getInt(1);
				}	
			PrevContMthYr="01/"+VMthYrWise.elementAt(i);	//System.out.println(i+"  :ContMthDiff: "+ContMthDiff);
			if(i==0) ContMthDiff =-1; //SB20210831
			/////////////////////////Get the Config Parameters////////////////////////////////////////
			String Mapp_Id=""; //SB20210528
			if(BuySell.equalsIgnoreCase("Buy"))
			{
				queryString = "SELECT TO_CHAR(A.DELV_FROM_DT,'DD/MM/YYYY'), TO_CHAR(A.DELV_TO_DT,'DD/MM/YYYY'),A.MAN_CONF_CD,A.CARGO_SEQ_NO,E.TRD_CD, A.MAN_CD, A.MAN_CD, A.CONFIRM_VOL,"
						+ "A.CARGO_REF_CD,to_char(C.MAN_CONF_DT,'dd/mm/yyyy'), A.PRICE,A.EMP_CD,TO_CHAR(A.ENT_DT,'dd/mm/yyyy'), A.EMP_CD,TO_CHAR(A.ENT_DT,'dd/mm/yyyy'), E.PRICE_TYPE, A.PRICE_TO, A.REMARK  "+
						" FROM FMS7_MAN_CONFIRM_CARGO_DTL A, FMS7_MAN_CONFIRM_MST C, FMS7_MAN_REQ_MST E WHERE "+
						"E.TRD_CD='"+DealCustCd+"' AND A.MAN_CONF_CD='"+SnNo+"' AND A.MAN_CD='"+FgsaNo+"' AND A.CARGO_SEQ_NO='"+SnRevNo+"' AND "+
			///SB20210522 			" A.DELV_TO_DT >= TO_DATE('"+to_date+"','DD/MM/YYYY') "
						" A.DELV_TO_DT >= TO_DATE('01/01/2021','DD/MM/YYYY') AND " + ///SB20210522 : ad discussed on 21stMay21 by Shiladitya to Include all Deals irrespective of Expairy on Report Date
						" E.MAN_CD=A.MAN_CD AND "+ 
				///		" A.UNIT_CD=C.UNIT_CD AND A.CARGO_STATUS='T' AND CARGO_REF_CD='"+VCAR_REF_NO_temp.elementAt(i)+"' "+ 
						" A.MAN_CONF_CD=C.MAN_CONF_CD AND A.CARGO_STATUS='T' AND CARGO_REF_CD LIKE '%' "+ 
						" ORDER BY A.MAN_CONF_CD, A.CARGO_REF_CD";
			///	queryString = "SELECT * FROM FMS7_CARGO_QQ_DTL WHERE CARGO_REF_NO=20032 ";
				//(START_DT <= to_date('"+to_date+"','dd/mm/yyyy')"+"AND END_DT >= to_date('"+from_date+"','dd/mm/yyyy'))
			//	System.out.println("MRver2.0 CARGO: "+queryString);
				rset = stmt.executeQuery(queryString);
				////System.out.println("FOr SN : "+to_date+" "+from_date);
				while(rset.next())
				{				
					String CargoRefCd=rset.getString(9)==null?"0":rset.getString(9); //SB20201127
					Mapp_Id=DealCustCd+"-"+SnNo+"-"+SnRevNo+"-"+CargoRefCd+"-0"; //SB20201127
					MappId=Mapp_Id;
				}						
			}
			PPACPriceFlag="0";
			String GenDate=""+SIGNING_DT.elementAt(i); String UserAvgPriceStartDt=""; String UserAvgPriceEndDt="";
			queryString ="SELECT PRICE_TYPE, CURVE_NM, RATE, SEQ_NO, PRICE_RANGE, TO_CHAR(PRICE_START_DT,'DD/MM/YYYY'), PHYS_CURVE_NM, TO_CHAR(PRICE_END_DT,'DD/MM/YYYY'), REMARKS "
					+ "FROM FMS9_MRCR_CONT_PRICE_DTL "  //SB20210504
					+ "WHERE MAPPING_ID LIKE '"+MappId+"' AND FROM_DT<=TO_DATE('"+GenDate+"','DD/MM/YYYY') AND TO_DT>=TO_DATE('"+GenDate+"','DD/MM/YYYY') AND FLAG='Y' ";//SB20210528
		//	System.out.println("FMS9_CURVE_PRICE_DTL: "+queryString);
			rset2 = stmt.executeQuery(queryString);			
			if(rset2.next())
			{
				PriceType=rset2.getString(1)==null?"":rset2.getString(1);
				if(PriceType.equals("F"))
				{
					NoMth=1;
					StartingPrevMth=0;
					NoMth_FinCurve=NoMth;
					CurveName=rset2.getString(2)==null?"":rset2.getString(2);
					PhyCurFrdPriceNm=rset2.getString(7)==null?"":rset2.getString(7);
			//SB20210928		PriceFormula=rset2.getString(6)==null?"":rset2.getString(6);
					PriceFormula=rset2.getString(9)==null?"":rset2.getString(9); //SB20210928
					priceSettle="";
				}
				else if(PriceType.equals("M"))
				{
				///	VPriceTypeDeal.add("Float");
					CurveName=rset2.getString(2)==null?"":rset2.getString(2);					
					PPACPriceFlag=rset2.getString(3)==null?"0":rset2.getString(3);//SB20210506 System.out.println(INDEX_TYPE+":PPACPriceFlag: "+PPACPriceFlag
					priceSettle=rset2.getString(5)==null?"A":rset2.getString(5);  //SB20201128: Dynamically picked up for Avg. Calculation (priceSettle=Global Variable from JSP)
					UserAvgPriceStartDt=rset2.getString(6)==null?"":rset2.getString(6);  //SB20201128: Dynamically picked up the start date for Avg. Calculation (UserAvgPriceStartDt=PRICE_START_DT from Table)
					UserAvgPriceEndDt=rset2.getString(8)==null?"":rset2.getString(8);  //SB20201128: Dynamically picked up the End date for Avg. Calculation (UserAvgPriceEndDt=PRICE_END_DT from Table)
					PhyCurFrdPriceNm=rset2.getString(7)==null?"":rset2.getString(7);
					PriceFormula=rset2.getString(9)==null?"":rset2.getString(9);
				}
				else
				{
					if(BuySell.equalsIgnoreCase("BUY"))
						PhyCurFrdPriceNm="LNG_PHYS_INDIA";
					 else
						 PhyCurFrdPriceNm="RLNG_PHYS_INDIA";
				}
			///	INDEX_TYPE=rset2.getString(2)==null?"":rset2.getString(2);
			///	VPriceTypeDealSeQNo.add(rset2.getString(4));
			}
			else
			{
				PriceType="F"; 	//System.out.println("PriceType: "+PriceType);
				NoMth=1;
				StartingPrevMth=0;
				NoMth_FinCurve=NoMth;
				if(PhyCurFrdPriceNm.equals(""))  //SB20210310	
				{
					 if(BuySell.equalsIgnoreCase("BUY"))
						 PhyCurFrdPriceNm="LNG_PHYS_INDIA";
					 else
						 PhyCurFrdPriceNm="RLNG_PHYS_INDIA";
				}
			}
			PricePHYSName=PhyCurFrdPriceNm; //SB20210918
	//////////SB20210905: ////////////////////////////////////
			if(CurveName.equals("LNG_ICE_JKM")) //SB20210409
				 CurveName="ICE_JKM";
	//////////^^^^^^SB20210905: ////////////////////////////////////	
	if(PriceType.equals("F"))
	{
		PriceTypeCargo="F";
	}
			IndexSettlePrice=priceSettle; //System.out.println(PriceType+" :IndexSettlePrice: "+IndexSettlePrice);
			FinPriceFormula=PriceFormula;
			String tempPriceFormula[]=PriceFormula.split("@");//Now it MULTI_LEG //OPAL@0@3  : TypeOfDeal@PreviousMonth(Zero)@UptoPreviousMonth(3) 
			DealName=tempPriceFormula[0];
			
			if(!PriceFormula.equals(""))
			{  //System.out.println("PriceFormula: "+PriceFormula);
				
				if(DealName.equals("MULTI_LEG"))
				{
					if(tempPriceFormula.length>2)
					{				
						NoMth=Integer.parseInt(tempPriceFormula[2]);
						StartingPrevMth=Integer.parseInt(tempPriceFormula[1]);
						NoMth_FinCurve=NoMth;
					}
					FinPriceFormula=FinPriceFormula.replace("@", " Leg -M");	
					FinPriceFormula="MULTI_LEG Settlement "+FinPriceFormula.substring(5);
				}
				else if(DealName.equals("MIN"))
				{
					queryString ="SELECT COUNT(FROM_DT) FROM FMS9_MRCR_CONT_FIN_PRICE_DTL "
							+ "WHERE  MAPPING_ID LIKE '"+MappId+"' AND TO_DATE(TO_DATE('"+GenDate+"','DD/MM/YYYY'),'DD-MON-YY') >=TO_DATE(FROM_DT,'DD-MON-YY') "
							+ "AND TO_DATE(TO_DATE('"+GenDate+"','DD/MM/YYYY'),'DD-MON-YY') <=TO_DATE(TO_DT,'DD-MON-YY') AND FLAG='Y' "; //SB20210528
						//	System.out.println("FMS9_MRCR_CONT_PRICE_DTL: "+queryString);
							rset2 = stmt.executeQuery(queryString);			
							if(rset2.next())
							{
								NoMth=rset2.getInt(1);  //LNG_ICE_JKM
							}
							
					//NoMth=2;
					StartingPrevMth=0;
					NoMth_FinCurve=1; //NotForAvg, 
					//CurveName2=tempPriceFormula[2];
					//CurveName1=tempPriceFormula[1];
					/*for(i=1; i<tempPriceFormula.length; i++)
						FinPriceFormula+=", "+tempPriceFormula[i];*/
					FinPriceFormula=FinPriceFormula.replace("@", ", ");	
					FinPriceFormula="Min. of "+FinPriceFormula.substring(4);
				}
				else //SB20210906
				{
					NoMth=1;
					StartingPrevMth=0;
					NoMth_FinCurve=NoMth;
					FinPriceFormula=""+PriceFormula; //System.out.println("*********OLD Naming: FinPriceFormula: "+FinPriceFormula);
					if(PPACPriceFlag.equals("1")) //SB20210912
						FinPriceFormula="PPAC: "+FinPriceFormula; //SB20210912
				}
			}
			else
			{
				NoMth=1;
				StartingPrevMth=0;
				NoMth_FinCurve=NoMth;
				FinPriceFormula=""+PriceFormula;
				if(PPACPriceFlag.equals("1")) //SB20210912
					FinPriceFormula="PPAC: "+FinPriceFormula; //SB20210912
			}
			
			double AvgEffSettlePrice=0; //Used in Allocated Qty 
		//	System.out.println(GenDate+">>>>>>>INDEX_TYPE: "+ContMthDiff);	
			///////////////////////////////
			double ppacPriceApplicable=0; //SB20210912
				if(ContMthDiff!=0)
				{
					///Min_Eff_avgCommodityValue=100; 
					VFin_CurrContMthYr.clear();
					VContFixedPrice_Temp.clear();
					VMutiCurveNm_Temp.clear();
					VFin_SettleStDt_Temp.clear();
					VFin_SettleEndDt_Temp.clear();
					VFin_SettlePrice_Temp.clear();
					VFin_EffSettlePrice_Temp.clear();
					VFin_AvgEffSettlePrice_Temp.clear();
					VFin_Slope_Temp.clear();
					VFin_Constant_Temp.clear();
					VFin_FwdPrice_Temp.clear();
					VPhy_FwdPrice_Temp.clear();
					
					String PrevContMthYrTemp="";String ContMthYr2=CurrContMthYr; 
					for(int j=0; j<NoMth; j++)
					{	
						int BackMth=StartingPrevMth+j;	
						if(DealName.equals("MIN"))
						{
							CurveName=tempPriceFormula[j+1];
							BackMth=StartingPrevMth;
							VMutiCurveNm_Temp.add(CurveName);
				///////////////SB20210825: To get the Avg Calculation type//////////////////////////////////////////
							queryString ="SELECT PRICE_RANGE FROM FMS9_MRCR_CONT_FIN_PRICE_DTL "
									+ "WHERE  MAPPING_ID LIKE '"+MappId+"' AND TO_DATE(TO_DATE('"+GenDate+"','DD/MM/YYYY'),'DD-MON-YY') >=TO_DATE(FROM_DT,'DD-MON-YY') "
									+ "AND TO_DATE(TO_DATE('"+GenDate+"','DD/MM/YYYY'),'DD-MON-YY') <=TO_DATE(TO_DT,'DD-MON-YY') AND FLAG='Y' AND CURVE_NM='"+CurveName+"'"; //SB20210528
								//	System.out.println("FMS9_MRCR_CONT_PRICE_DTL: "+queryString);
									rset2 = stmt.executeQuery(queryString);			
									if(rset2.next())
									{
										//NoMth=rset2.getInt(1);  //LNG_ICE_JKM
										IndexSettlePrice=rset2.getString(1)==null?"A":rset2.getString(1); 
									}
									else
										IndexSettlePrice="";
							///////////////^^^To get the Avg Calculation type//////////////////////////////////////////
						}
						else
							VMutiCurveNm_Temp.add(CurveName);
						queryString = "SELECT TO_CHAR(TO_DATE('"+ContMthYr2+"','DD/MM/YYYY')-"+BackMth+",'DD/MM/YYYY') FROM DUAL";
					//		System.out.println(j+"  :Get DAY Difference: "+queryString);
							rset = stmt.executeQuery(queryString);
							if(rset.next())
							{
								PrevContMthYrTemp = rset.getString(1)==null?"":rset.getString(1); //SB20201128;
								String tempPrevContMthYr[]=PrevContMthYrTemp.split("/");
								PrevContMthYrTemp="01/"+tempPrevContMthYr[1]+"/"+tempPrevContMthYr[2];
							///	VFin_CurrContMthYr.add(PrevContMthYrTemp);
							}				
							ContMthYr2=PrevContMthYrTemp;
							
							String PriceIndex_SettleMth="";		
					String PrevMthSettleEndDtPlusOne="";
					String ContMthSettleDt="";
					if(UserAvgPriceStartDt.equals("")) 
					{
						/////////////////////SB20201116: Prev Settlement Date: to find the start date for Contract Month////////////////////////////////////////
						///////////////////////////SB20201006: New Logic To Calculate Pricinging Start-End Date and No. of Days in between ///////////////////////////////		
						int NoOfDays=1; String IndexSettlePriceAvgStr="";
						if(IndexSettlePrice.length()>1)
						{
							NoOfDays=Integer.parseInt(IndexSettlePrice.substring(1,IndexSettlePrice.length())); //System.out.println("NoOfDays Avg. Days: "+NoOfDays);
						///	IndexSettlePriceAvgStr="A"+NoOfDays;
						}
					//	System.out.println(GenDate+">>>>>>>INDEX_TYPE: "+INDEX_TYPE);	
						///////////////////////////////////			
			/*/SB20210820		queryString = "SELECT TO_CHAR(SETTLE_DT,'DD/MM/YYYY'), TO_CHAR(SETTLE_END_DT,'DD/MM/YYYY') FROM FMS9_SETTLE_CALND_DTL "
					//SB20210708		+ "WHERE CONT_MM_YYYY=TO_DATE('"+ContMthYr2+"','DD/MM/YYYY') AND SETTLE_TYPE='"+HolidayType+"' ";
					+ "WHERE CONT_MM_YYYY=TO_DATE('"+ContMthYr2+"','DD/MM/YYYY') AND CURVE_NM='"+CurveName+"' ";
					*/
					queryString = "SELECT TO_CHAR(TO_DATE(SETTLE_DT,'DD-MON-YY'),'DD/MM/YYYY') FROM FMS9_SETTLE_CALND_DTL "
							//SB20210708		+ "WHERE CONT_MM_YYYY=TO_DATE('"+ContMthYr2+"','DD/MM/YYYY') AND SETTLE_TYPE='"+HolidayType+"' ";
							+ "WHERE CONT_MM_YYYY=TO_DATE(TO_DATE('"+ContMthYr2+"','DD/MM/YYYY'),'DD-MON-YY') AND CURVE_NM='"+CurveName+"' ";
					//	System.out.println(" SETTLE: FMS9_SETTLE_CALND_DTL: "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next()) {
					ContMthSettleDt = rset.getString(1)==null?"":rset.getString(1);	
					VFin_SettleEndDt_Temp.add(rset.getString(1)==null?"":rset.getString(1));
					}
					else //SB20210425
					{
						ContMthSettleDt = ContMthYr2; //SB20210425
						VFin_SettleEndDt_Temp.add(""); ///sb
					}
					String PrevContMthYrTemp2="";
					queryString = "SELECT TO_CHAR(TO_DATE('"+PrevContMthYrTemp+"','DD/MM/YYYY')-1,'DD/MM/YYYY') FROM DUAL";
					//		System.out.println(j+"  :Get DAY Difference: "+queryString);
							rset = stmt.executeQuery(queryString);
							if(rset.next())
							{
								PrevContMthYrTemp2 = rset.getString(1)==null?"":rset.getString(1); //SB20201128;
							}
							String tempPrevContMthYr2[]=PrevContMthYrTemp2.split("/");
							PrevContMthYrTemp2="01/"+tempPrevContMthYr2[1]+"/"+tempPrevContMthYr2[2];
					
					Vector Veff_dt = new Vector();Vector Veff_dtWeeklyOff = new Vector();
					
					if(IndexSettlePrice.equals("A"))
					{
						queryString = "SELECT TO_CHAR(TO_DATE(SETTLE_END_DT,'DD-MON-YY')+1,'DD/MM/YYYY') FROM FMS9_SETTLE_CALND_DTL "
								//SB20210708		+ "WHERE CONT_MM_YYYY=TO_DATE('"+PrevContMthYr+"','DD/MM/YYYY') AND SETTLE_TYPE='"+HolidayType+"' ";
						+ "WHERE CONT_MM_YYYY=TO_DATE('"+PrevContMthYrTemp2+"','DD/MM/YYYY') AND CURVE_NM='"+CurveName+"' ";
						//	System.out.println(" SETTLE: FMS9_SETTLE_CALND_DTL: "+queryString);
						rset = stmt.executeQuery(queryString);
						if(rset.next()) {
							PrevMthSettleEndDtPlusOne = rset.getString(1)==null?ContMthYr2:rset.getString(1); //SB20201128;	
						///	VFin_SettleStDt_Temp.add(rset.getString(1)==null?"":rset.getString(1));
							}
							else //SB20210425
							{
								PrevMthSettleEndDtPlusOne = ContMthYr2; //SB20210425
							///	VFin_SettleStDt_Temp.add(ContMthYr2);	
							}
					}
					else if(IndexSettlePrice.equals("A"+NoOfDays))
					{
						int NoOfDays2=1;
						NoOfDays2=NoOfDays-1;
						queryString = "SELECT TO_CHAR(TO_DATE('"+ContMthSettleDt+"','DD/MM/YYYY')-"+NoOfDays2+",'DD/MM/YYYY') FROM DUAL";
						//	System.out.println("Get DAY Name: "+queryString);
						rset = stmt.executeQuery(queryString);
						if(rset.next())
						{
							PrevMthSettleEndDtPlusOne = rset.getString(1)==null?ContMthYr2:rset.getString(1); //SB20201128;
						///	VFin_SettleStDt_Temp.add(rset.getString(1)==null?"":rset.getString(1));
						}
						else //SB20210425
						{
							PrevMthSettleEndDtPlusOne = ContMthYr2; //SB20210425
						///	VFin_SettleStDt_Temp.add(ContMthYr2);	
						}
					}
					else if(IndexSettlePrice.equals("F")) //SB20201027: For BRENT Settlement  Date
					{
						queryString = "SELECT TO_CHAR(SETTLE_DT,'DD/MM/YYYY'), TO_CHAR(SETTLE_END_DT,'DD/MM/YYYY') FROM FMS9_SETTLE_CALND_DTL "
						//SB20210708		+ "WHERE CONT_MM_YYYY=TO_DATE('"+ContMthYr+"','DD/MM/YYYY') AND SETTLE_TYPE='"+HolidayType+"' ";
						+ "WHERE CONT_MM_YYYY=TO_DATE('"+ContMthYr2+"','DD/MM/YYYY') AND CURVE_NM='"+CurveName+"' ";
						//	System.out.println(" SETTLE: FMS9_SETTLE_CALND_DTL: "+queryString);
							rset = stmt.executeQuery(queryString);
							if(rset.next()) {
								PrevMthSettleEndDtPlusOne = rset.getString(1)==null?ContMthYr2:rset.getString(1);	
							///	VFin_SettleStDt_Temp.add(rset.getString(1)==null?"":rset.getString(1));
							}
							else
							{
								PrevMthSettleEndDtPlusOne=ContMthYr2;
							///	VFin_SettleStDt_Temp.add(ContMthYr2);	
							}
						///	Veff_dt.add(PrevMthSettleEndDtPlusOne);
					}
					else
					{
						PrevMthSettleEndDtPlusOne=ContMthYr2;
					///	VFin_SettleStDt_Temp.add("");System.out.println("2  PriceType: "+PriceType);
					} //System.out.println(ContMthYr2+" ContMthYr2: PrevMthSettleEndDtPlusOne: "+PrevMthSettleEndDtPlusOne);
					if(CurveName.equals("PPAC"))
					{
						queryString = "SELECT TO_CHAR(TO_DATE(SETTLE_START_DT,'DD-MON-YY'),'DD/MM/YYYY') FROM FMS9_SETTLE_CALND_DTL "
								//SB20210708		+ "WHERE CONT_MM_YYYY=TO_DATE('"+PrevContMthYr+"','DD/MM/YYYY') AND SETTLE_TYPE='"+HolidayType+"' ";
						+ "WHERE CONT_MM_YYYY=TO_DATE('"+CurrContMthYr+"','DD/MM/YYYY') AND CURVE_NM='"+CurveName+"' ";
						//	System.out.println(" SETTLE: FMS9_SETTLE_CALND_DTL: "+queryString);
						rset = stmt.executeQuery(queryString);
						if(rset.next()) {
							PrevMthSettleEndDtPlusOne = rset.getString(1)==null?"":rset.getString(1); //SB20201128;	
						///	VFin_SettleStDt_Temp.add(rset.getString(1)==null?"":rset.getString(1));
							}
							else //SB20210425
							{
								PrevMthSettleEndDtPlusOne = ContMthYr2; //SB20210425
							///	VFin_SettleStDt_Temp.add(ContMthYr2);	
							}
					}
					//////////////////////To Find Out SAT/SUN/Holiday////////////////////
					double count=0;		
					String query="select to_date('"+ContMthSettleDt+"','DD/MM/YY')- to_date('"+PrevMthSettleEndDtPlusOne+"','DD/MM/YY')+1 from dual";
				//	System.out.println("Date Difference: dual: "+query);
					rset=stmt.executeQuery(query);
					if(rset.next())
					{
						count=rset.getInt(1);
					}
					//PriceBegMthDt =ContMthYr;
					int StratDayCount=0; int StratDayCount2=0; //SB20201017: to check Starting SAT/SUN/HOLIDAY
					String SettleStartDate="";
				String	PriceBegMthDt =PrevMthSettleEndDtPlusOne;
				if(!CurveName.equals("PPAC"))
				{	
				for(int m=0;m<count;m++)
							{
								String query1="select to_char(to_date('"+PrevMthSettleEndDtPlusOne+"','dd/mm/yy')+ "+m+",'dd/mm/yyyy') from dual";
							///	System.out.println(">>>>>>>dual: "+query1);
								rset=stmt.executeQuery(query1);
								if(rset.next())
								{
									SettleStartDate=rset.getString(1)==null?"":rset.getString(1); //SB20201128
								}		
							///////////////////////SB20201017: to check Starting SAT/SUN/HOLIDAY//////////////////////////////					
							if(StratDayCount==StratDayCount2)
							{
								queryString ="SELECT to_char(HOLIDAY_DT,'dd/mm/yyyy') FROM FMS9_CURVE_HOLIDAY_DTL "
							//SB20210708			+ " WHERE HOLIDAY_DT =to_date('"+date_arr+"','dd/mm/yyyy') AND FLAG='Y' AND HOLIDAY_TYPE='"+HolidayType+"' ORDER BY HOLIDAY_DT";
								+ " WHERE HOLIDAY_DT =to_date('"+SettleStartDate+"','dd/mm/yyyy') AND FLAG='Y' AND CURVE_NM='"+CurveName+"' ORDER BY HOLIDAY_DT";
								rset = stmt.executeQuery(queryString);
							//	System.out.println(">>>>>>>PRICE PUULING: "+queryString);
								if(rset.next())
								{
									StratDayCount++; //System.out.println("Get HOLI DAY Name: ");		
								}
								else
								{
								String WeeklyOff="";
								queryString = "SELECT TO_CHAR(TO_DATE('"+SettleStartDate+"','DD/MM/YYYY'),'DAY') FROM DUAL";
								//	System.out.println("Get DAY Name: "+queryString);
									rset = stmt.executeQuery(queryString);
									if(rset.next())
									{
										WeeklyOff = rset.getString(1)==null?"":rset.getString(1); //SB20201128;
									}
									if(WeeklyOff.trim().equals("SATURDAY") || WeeklyOff.trim().equals("SUNDAY")) 
									{
										StratDayCount++;
									}
									else
									{							
										Veff_dt.add(SettleStartDate);
										PriceBegMthDt=SettleStartDate; //System.out.println("Get DAY Name: "+WeeklyOff);							
									}
								}
							}
							else
								Veff_dt.add(SettleStartDate); //Ori Line
							StratDayCount2++; //System.out.println(StratDayCount+" : "+StratDayCount2);
							//////////////////////////////////////////////////////////////////////////////////////////
						//SB20201017	Veff_dt.add(date_arr);
						}
				} //EoF Logic 
							VFin_SettleStDt_Temp.add(PriceBegMthDt);	
					/////////////////^^^^^To Find Out SAT/SUN/Holiday/////////////////////////////////////////////////////
							
					queryString = "SELECT TO_CHAR(MAX(TO_DATE(CONT_MM_YYYY,'DD-MON-YY')),'MM/YYYY') FROM FMS9_SETTLE_CALND_DTL "
							+ "WHERE SETTLE_DT BETWEEN TO_DATE(TO_DATE('"+PrevMthSettleEndDtPlusOne+"','DD/MM/YYYY'),'DD-MON-YY') AND TO_DATE(TO_DATE('"+ContMthSettleDt+"','DD/MM/YYYY'),'DD-MON-YY') "
						//Remove this checking from every where	+ "AND SETTLE_TYPE='"+HolidayType+"' ";
							+ "AND CURVE_NM='"+CurveName+"' ";
					//	System.out.println(" SETTLE: FMS9_SETTLE_CALND_DTL: "+queryString);
						rset = stmt.executeQuery(queryString);
						if(rset.next()) {
							PriceIndex_SettleMth = rset.getString(1)==null?"":rset.getString(1);//System.out.println(" INSIDE: PriceIndex_SettleMth: "+PriceIndex_SettleMth); //SB20201128;
						}
						else
							PriceIndex_SettleMth=ContMthSettleDt;  ////This line to be corrected SB20210911
							/////////////SB20210702/////////////////	
						PriceIndex_SettleMth="01/"+PriceIndex_SettleMth;  //"01" added for Insert into FMS9_EOD_EXPOSURE_DTL table
						//SB20210910		PriceIndex_SettleMth=ContMthSettleDt; //SB20210910
						//SB20210814	if(PPACPriceFlag.equals("0"))
						if(PPACPriceFlag.equals("0") && !CurveName.equals("PPAC"))
							VFin_CurrContMthYr.add(PriceIndex_SettleMth);
					//SB20210814	else if(PPACPriceFlag.equals("1"))
						else if(PPACPriceFlag.equals("1"))  //SB20210910
							VFin_CurrContMthYr.add(CurrContMthYr); //SB20210910
						else if(CurveName.equals("PPAC"))  //SB20210814
							VFin_CurrContMthYr.add(CurrContMthYr);
						else 
							VFin_CurrContMthYr.add(PriceIndex_SettleMth);
				///	VFin_CurrContMthYr.add(PrevContMthYrTemp);
					}//EoF Condition Checking
					else  //Incase of Specific Date Range
					{
						if(!UserAvgPriceStartDt.equals(""))  //SB20201128
							PrevMthSettleEndDtPlusOne=UserAvgPriceStartDt; //SB20201128
						if(!UserAvgPriceEndDt.equals(""))  //SB20201128
							ContMthSettleDt=UserAvgPriceEndDt; //SB20210511
						VFin_SettleStDt_Temp.add(PrevMthSettleEndDtPlusOne);
						VFin_SettleEndDt_Temp.add(ContMthSettleDt);
						
						queryString = "SELECT TO_CHAR(MAX(TO_DATE(CONT_MM_YYYY,'DD-MON-YY')),'MM/YYYY') FROM FMS9_SETTLE_CALND_DTL "
							+ "WHERE SETTLE_DT BETWEEN TO_DATE(TO_DATE('"+PrevMthSettleEndDtPlusOne+"','DD/MM/YYYY'),'DD-MON-YY') AND TO_DATE(TO_DATE('"+ContMthSettleDt+"','DD/MM/YYYY'),'DD-MON-YY') "
						//Remove this checking from every wher	+ "AND SETTLE_TYPE='"+HolidayType+"' ";
							+ "AND CURVE_NM='"+CurveName+"' ";
				//		System.out.println(" SETTLE: FMS9_SETTLE_CALND_DTL: "+queryString);
						rset = stmt.executeQuery(queryString);
						if(rset.next()) {
							PriceIndex_SettleMth = rset.getString(1)==null?"":rset.getString(1); //SB20201128;
						}
						else
							PriceIndex_SettleMth=ContMthSettleDt; ////This line to be corrected SB20210911
							/////////////SB20210702/////////////////	
						PriceIndex_SettleMth="01/"+PriceIndex_SettleMth;  //"01" added for Insert into FMS9_EOD_EXPOSURE_DTL table
						VFin_CurrContMthYr.add(PriceIndex_SettleMth);
					} //System.out.println(ContMthSettleDt+" ContMthSettleDt: PriceIndex_SettleMth: "+PriceIndex_SettleMth);
					double avgCommodityValue=0; double Eff_avgCommodityValue=0; 
					//System.out.println(" FINAL: PriceIndex_SettleMth: "+PriceIndex_SettleMth); //SB20201128;
		//////////////SB20201105: Introduced due to multiple record on Curve Price Tbl////////////////////////////
								String Gen_Dt=to_date; 
								int CountPlattEntDt=0; String PlattEntDt="";
								/*SB20210324 queryString = "select COUNT(ENT_DT) from FMS9_FORWARD_PRICE_DTL "
										+ " where ENT_DT= to_date('"+Gen_Dt+"','dd/mm/yyyy') AND CURVE_TYPE='F' AND FLAG='Y' ";*/
								queryString = "select COUNT(ENT_DT) from FMS9_FORWARD2_PRICE_DTL "
										+ " where ENT_DT= to_date('"+Gen_Dt+"','dd/mm/yyyy') AND CURVE_TYPE='Forward' AND FLAG='Y' ";
							//	System.out.println("FMS9_CURVE_PRICE_DTL: "+queryString);
								rset = stmt.executeQuery(queryString);
								if(rset.next())
								{
									CountPlattEntDt = rset.getInt(1);
								}
								if(CountPlattEntDt>0)
								{
								//	PlattEntDt=Gen_Dt; 
								//	System.out.println("1. PlattEntDt: "+PlattEntDt);
									/*SB20210324	queryString = "SELECT DISTINCT TO_CHAR(ENT_DT, 'dd/mm/yyyy')  from FMS9_FORWARD_PRICE_DTL "
											+ " where ENT_DT= to_date('"+Gen_Dt+"','dd/mm/yyyy') AND CURVE_TYPE='F' AND FLAG='Y' "; */
									queryString = "SELECT DISTINCT TO_CHAR(ENT_DT, 'dd/mm/yyyy')  from FMS9_FORWARD2_PRICE_DTL "
											+ " where ENT_DT= to_date('"+Gen_Dt+"','dd/mm/yyyy') AND CURVE_TYPE='Forward' AND FLAG='Y' ";
								//	System.out.println("A. FMS9_CURVE_PRICE_DTL: "+queryString);
									rset = stmt.executeQuery(queryString);
									if(rset.next())
									{
										PlattEntDt = rset.getString(1)==null?"":rset.getString(1);
									}
								}
								else
								{
									/*SB20210324	queryString = "SELECT TO_CHAR(MAX(ENT_DT), 'dd/mm/yyyy')  from FMS9_FORWARD_PRICE_DTL "
											+ " where ENT_DT<= to_date('"+Gen_Dt+"','dd/mm/yyyy') AND CURVE_TYPE='F' AND FLAG='Y' "; */
									queryString = "SELECT TO_CHAR(MAX(ENT_DT), 'dd/mm/yyyy')  from FMS9_FORWARD2_PRICE_DTL "
											+ " where ENT_DT<= to_date('"+Gen_Dt+"','dd/mm/yyyy') AND CURVE_TYPE='Forward' AND FLAG='Y' ";
								//	System.out.println("B. FMS9_CURVE_PRICE_DTL: "+queryString);
									rset = stmt.executeQuery(queryString);
									if(rset.next())
									{
										PlattEntDt = rset.getString(1)==null?"":rset.getString(1);
									}
									if(PlattEntDt.equals(""))
									{
										/*SB20210324	queryString = "SELECT TO_CHAR(MIN(ENT_DT), 'dd/mm/yyyy')  from FMS9_FORWARD_PRICE_DTL "
												+ " where ENT_DT>= to_date('"+Gen_Dt+"','dd/mm/yyyy') AND CURVE_TYPE='F' AND FLAG='Y' "; */
										queryString = "SELECT TO_CHAR(MIN(ENT_DT), 'dd/mm/yyyy')  from FMS9_FORWARD2_PRICE_DTL "
												+ " where ENT_DT>= to_date('"+Gen_Dt+"','dd/mm/yyyy') AND CURVE_TYPE='Forward' AND FLAG='Y' ";
									//	System.out.println("C. FMS9_CURVE_PRICE_DTL: "+queryString);
										rset = stmt.executeQuery(queryString);
										if(rset.next())
										{
											PlattEntDt = rset.getString(1)==null?"":rset.getString(1);
										}
										if(PlattEntDt.equals(""))
										{
											/*SB20210324	queryString = "SELECT TO_CHAR(MAX(ENT_DT), 'dd/mm/yyyy')  from FMS9_FORWARD_PRICE_DTL "
													+ " where ENT_DT<= to_date('"+Gen_Dt+"','dd/mm/yyyy') AND CURVE_TYPE='F' AND FLAG='Y' "; */
											queryString = "SELECT TO_CHAR(MAX(ENT_DT), 'dd/mm/yyyy')  from FMS9_FORWARD2_PRICE_DTL "
													+ " where ENT_DT<= to_date('"+Gen_Dt+"','dd/mm/yyyy') AND CURVE_TYPE='Forward' AND FLAG='Y' ";
									//		System.out.println("D. FMS9_CURVE_PRICE_DTL: "+queryString);
											rset = stmt.executeQuery(queryString);
											if(rset.next())
											{
												PlattEntDt = rset.getString(1)==null?"":rset.getString(1);
											}
										}
									}
								}
							//	System.out.println("V2. FMS9_FORWARD2_PRICE_DTL: PlattEntDt: "+PlattEntDt);
								//////////////////////////^^^^SB20201105////////////////////////////////////////////////////////////////////
					if(!PriceType.equals("F"))
					{
						queryString = "select AVG(SETTLE_PRICE) from FMS9_CURVE2_PRICE_DTL "
								+ " where SETTLE_PRICE>0 AND CURVE_DD_MM_YR BETWEEN TO_DATE(to_date('"+PrevMthSettleEndDtPlusOne+"','dd/mm/yyyy'),'DD-MON-YY') AND TO_DATE(to_date('"+ContMthSettleDt+"','dd/mm/yyyy'),'DD-MON-YY') "
								//SB20210409		+ "AND CURVE_TYPE='Spot' AND CURVE_NM='"+CurveName+"' ";
						+ "AND CURVE_TYPE='Spot' AND PHYS_FIN='"+CurveName+"' "; //SB20210409
					//	System.out.println("Eff. AVG: FM S9_CURVE_PRICE_DTL: "+queryString);
						rset = stmt.executeQuery(queryString);
						if(rset.next())
						{
							avgCommodityValue = rset.getDouble(1);
						///sb	VFin_SettlePrice_Temp.add(nf2.format(avgCommodityValue));
						}
						else
						{
							avgCommodityValue = 0;
						///sb	VFin_SettlePrice_Temp.add("");
						}	
						String Slope="1"; String Constant="0.5";
						if(!DealName.equals("MIN"))
						{
						queryString ="SELECT SLOPE, CONST FROM FMS9_MRCR_CONT_PRICE_DTL "
									+ "WHERE  MAPPING_ID LIKE '"+MappId+"' AND TO_DATE(TO_DATE('"+GenDate+"','DD/MM/YYYY'),'DD-MON-YY') >=TO_DATE(FROM_DT,'DD-MON-YY') "
									+ "AND TO_DATE(TO_DATE('"+GenDate+"','DD/MM/YYYY'),'DD-MON-YY') <=TO_DATE(TO_DT,'DD-MON-YY') AND PRICE_TYPE='M' AND CURVE_NM='"+CurveName+"' "; //SB20210528
							//		System.out.println("FMS9_MRCR_CONT_PRICE_DTL: "+queryString);
									rset2 = stmt.executeQuery(queryString);			
									if(rset2.next())
									{
										VFin_Slope_Temp.add(rset2.getString(1)==null?"1":rset2.getString(1));  //LNG_ICE_JKM
										VFin_Constant_Temp.add(rset2.getString(2)==null?"0":rset2.getString(2));  //LNG_ICE_JKM
										Slope=rset2.getString(1)==null?"1":rset2.getString(1);
										Constant=rset2.getString(2)==null?"0":rset2.getString(2);
									}
									else
									{
										///////////////////////SB20210918: Due to LNG_ICE_JKM instead of ICE_JKM not able to get Slope & Const.///////////////////
										if(CurveName.equals("ICE_JKM")) //SB20210409
											 CurveName="LNG_ICE_JKM";
										queryString ="SELECT SLOPE, CONST FROM FMS9_MRCR_CONT_PRICE_DTL "
												+ "WHERE  MAPPING_ID LIKE '"+MappId+"' AND TO_DATE(TO_DATE('"+GenDate+"','DD/MM/YYYY'),'DD-MON-YY') >=TO_DATE(FROM_DT,'DD-MON-YY') "
												+ "AND TO_DATE(TO_DATE('"+GenDate+"','DD/MM/YYYY'),'DD-MON-YY') <=TO_DATE(TO_DT,'DD-MON-YY') AND PRICE_TYPE='M' AND CURVE_NM='"+CurveName+"' "; //SB20210528
											//	System.out.println("FMS9_MRCR_CONT_PRICE_DTL: "+queryString);
												rset2 = stmt.executeQuery(queryString);			
												if(rset2.next())
												{
													VFin_Slope_Temp.add(rset2.getString(1)==null?"1":rset2.getString(1));  //LNG_ICE_JKM
													VFin_Constant_Temp.add(rset2.getString(2)==null?"0":rset2.getString(2));  //LNG_ICE_JKM
													Slope=rset2.getString(1)==null?"1":rset2.getString(1);
													Constant=rset2.getString(2)==null?"0":rset2.getString(2);
												}
												else///////////////////////^^SB20210918: Due to LNG_ICE_JKM instead of ICE_JKM not able to get Slope & Const.///////////////////
												{
													VFin_Slope_Temp.add("1");
													VFin_Constant_Temp.add("0.1");
												}
												if(CurveName.equals("LNG_ICE_JKM")) //SB20210409
													 CurveName="ICE_JKM";
									}
						}
						else if(DealName.equals("MIN"))
						{
										queryString ="SELECT SLOPE, CONST FROM FMS9_MRCR_CONT_FIN_PRICE_DTL "
												+ "WHERE  MAPPING_ID LIKE '"+MappId+"' AND TO_DATE(TO_DATE('"+GenDate+"','DD/MM/YYYY'),'DD-MON-YY') >=TO_DATE(FROM_DT,'DD-MON-YY') "
												+ "AND TO_DATE(TO_DATE('"+GenDate+"','DD/MM/YYYY'),'DD-MON-YY') <=TO_DATE(TO_DT,'DD-MON-YY')  AND CURVE_NM='"+CurveName+"' "; //SB20210528
											//	System.out.println("FMS9_MRCR_CONT_PRICE_DTL: "+queryString);
												rset2 = stmt.executeQuery(queryString);			
												if(rset2.next())
												{
													VFin_Slope_Temp.add(rset2.getString(1)==null?"2":rset2.getString(1));  //LNG_ICE_JKM
													VFin_Constant_Temp.add(rset2.getString(2)==null?"0":rset2.getString(2));  //LNG_ICE_JKM
													Slope=rset2.getString(1)==null?"1":rset2.getString(1);
													Constant=rset2.getString(2)==null?"0":rset2.getString(2);
												}
												else
												{
													VFin_Slope_Temp.add("3");
													VFin_Constant_Temp.add("0");
												}
						}
						
										/////////////SB20210506: Get PPAC Price from FORWARD2///////////////////////
												//sB20210912	double ppacPriceApplicable=0;
													//SB20210814		if(PriceTypeCargo.equals("M") && PPACPriceFlag.equals("1"))
												//SB20210910	if(PriceTypeCargo.equals("M") &&  CurveName.equals("PPAC"))  //SB20210814
													if(PriceTypeCargo.equals("M") && (PPACPriceFlag.equals("1") || CurveName.equals("PPAC")))  //SB20210814
													{
													queryString ="SELECT SETTLE_PRICE FROM FMS9_FORWARD2_PRICE_DTL "+ 
															" WHERE CURVE_DD_MM_YR=TO_DATE('"+PriceIndex_SettleMth+"','DD/MM/YYYY') AND CURVE_TYPE='Forward' AND FLAG='Y' AND ENT_DT=(SELECT TO_DATE(MAX(ENT_DT),'DD-MON-YY') FROM FMS9_FORWARD2_PRICE_DTL "+ 
															" WHERE CURVE_DD_MM_YR=TO_DATE('"+PriceIndex_SettleMth+"','DD/MM/YYYY') AND CURVE_TYPE='Forward' AND FLAG='Y' AND ENT_DT<=TO_DATE('"+to_date+"','DD/MM/YYYY') "
															+ "AND CURVE_NM='PPAC') "
															+ "AND CURVE_NM='PPAC' ";
											//		System.out.println(" :PPAC PRICE: FMS9_FORWARD2_PRICE_DTL: "+queryString);
														rset2 = stmt.executeQuery(queryString);			
														if(rset2.next())
														{
															VContFixedPrice_Temp.add(rset2.getString(1)==null?"1":rset2.getString(1));  //RLNG_PHYS_FORWARD //SB20210227
															ppacPriceApplicable=rset2.getDouble(1);
														}
														else
														{
														///	VPriceTypeDealRate.add("0");
															VContFixedPrice_Temp.add("0");
															ppacPriceApplicable=0;
														}
												
														if(CurveName.equals("PPAC")) //SB20210910		
															avgCommodityValue = ppacPriceApplicable;
													}
													//SB20210814	else if(PriceTypeCargo.equals("M") && PPACPriceFlag.equals("0")) //SB20210530
													//SB20210910	else if(PriceTypeCargo.equals("M") && !CurveName.equals("PPAC"))  //SB20210814
													else if(PriceTypeCargo.equals("M") && (PPACPriceFlag.equals("0") || !CurveName.equals("PPAC"))) //SB20210910 //SB20210814
														VContFixedPrice_Temp.add("");//SB20210530
													else if(PriceTypeCargo.equals("F")) //SB20210530
														VContFixedPrice_Temp.add(DealPrice);  ////SB20210530 Contract Rate SN_MST
													else 
														VContFixedPrice_Temp.add("0");
													VFin_SettlePrice_Temp.add(nf2.format(avgCommodityValue));
											//////////////^^SB20210506: Get PPAC Price from FORWARD2////////////////////
													
						Eff_avgCommodityValue=avgCommodityValue*Double.parseDouble(""+Slope)+Double.parseDouble(""+Constant); //SB20200918: As per Shiladitya						
						VFin_EffSettlePrice_Temp.add(nf2.format(Eff_avgCommodityValue));
						AvgEffSettlePrice+=Eff_avgCommodityValue;
						VFin_AvgEffSettlePrice_Temp.add(nf2.format(AvgEffSettlePrice/NoMth_FinCurve));
						
			
	/////////////////////SB20210703: Incase of Forward Price of Curve Nm not in latest date///////////////////////
							/*SB20210909	queryString ="SELECT SETTLE_PRICE, TO_CHAR(TO_DATE(ENT_DT,'DD-MON-YY'),'DD/MM/YYYY') FROM FMS9_FORWARD2_PRICE_DTL "+ 
							//SB20210708:Old Ver. Same Phy&Fin					" WHERE CURVE_DD_MM_YR=TO_DATE('"+FirstDateGasMthYr+"','DD/MM/YYYY') AND CURVE_TYPE='Forward' AND FLAG='Y' AND CURVE_NM='"+FinCurFrdPriceNm+"' "
										" WHERE CURVE_DD_MM_YR=TO_DATE('"+PriceIndex_SettleMth+"','DD/MM/YYYY') AND CURVE_TYPE='Forward' AND FLAG='Y' AND CURVE_NM='"+CurveName+"' "
										+ "AND ENT_DT=(SELECT TO_DATE(MAX(ENT_DT),'DD-MON-YY') FROM FMS9_FORWARD2_PRICE_DTL  WHERE CURVE_DD_MM_YR=TO_DATE('"+PriceIndex_SettleMth+"','DD/MM/YYYY') AND " 
										+ "CURVE_TYPE='Forward' AND FLAG='Y' AND ENT_DT<=TO_DATE('"+to_date+"','DD/MM/YYYY') AND CURVE_NM='"+CurveName+"')";*/
								queryString ="SELECT SETTLE_PRICE FROM FMS9_FORWARD2_PRICE_DTL "+ 
										" WHERE CURVE_DD_MM_YR=TO_DATE('"+PriceIndex_SettleMth+"','DD/MM/YYYY') AND CURVE_TYPE='Forward' AND FLAG='Y' AND ENT_DT=TO_DATE('"+PlattEntDt+"','DD/MM/YYYY') "
										+ "AND CURVE_NM='"+CurveName+"' ";
							//		System.out.println(ContMthYr2+" :Incase of Forward Price of Curve Nm not in latest date "+to_date+":FORWARD2 PRICE: FMS9_FORWARD2_PRICE_DTL: "+queryString);
								rset2 = stmt.executeQuery(queryString);			
								if(rset2.next())
								{
									VFin_FwdPrice_Temp.add(rset2.getString(1)==null?"0":rset2.getString(1));  //ICE_JKM_FORWARD
								}
								else /////////////////////^^^^^SB20210703: Incase of Forward Price of Curve Nm not in latest date///////////////////////
								{
									VFin_FwdPrice_Temp.add("0.1");
								}
					}
					else
					{
						VFin_SettlePrice_Temp.add("0");
						VFin_Slope_Temp.add("1");
						VFin_Constant_Temp.add("0");
						VFin_EffSettlePrice_Temp.add("");
						VFin_AvgEffSettlePrice_Temp.add("");
						VFin_FwdPrice_Temp.add("0");
					}
			/////////////////////SB20210703: Incase of Forward Price of Curve Nm not in latest date///////////////////////
								//String FirstDateGasMthYr="01/"+VMthYrWise.elementAt(i);
								/*SB20210909 queryString ="SELECT SETTLE_PRICE, TO_CHAR(TO_DATE(ENT_DT,'DD-MON-YY'),'DD/MM/YYYY') FROM FMS9_FORWARD2_PRICE_DTL "+ 
										" WHERE CURVE_DD_MM_YR=TO_DATE('"+CurrContMthYr+"','DD/MM/YYYY') AND CURVE_TYPE='Forward' AND FLAG='Y' AND CURVE_NM='"+PhyCurFrdPriceNm+"' "
										+ "AND ENT_DT=(SELECT TO_DATE(MAX(ENT_DT),'DD-MON-YY') FROM FMS9_FORWARD2_PRICE_DTL  WHERE CURVE_DD_MM_YR=TO_DATE('"+CurrContMthYr+"','DD/MM/YYYY') AND " 
										+ "CURVE_TYPE='Forward' AND FLAG='Y' AND ENT_DT<=TO_DATE('"+to_date+"','DD/MM/YYYY') AND CURVE_NM='"+PhyCurFrdPriceNm+"')";*/
								queryString ="SELECT SETTLE_PRICE FROM FMS9_FORWARD2_PRICE_DTL "+ 
										" WHERE CURVE_DD_MM_YR=TO_DATE('"+CurrContMthYr+"','DD/MM/YYYY') AND CURVE_TYPE='Forward' AND FLAG='Y' AND ENT_DT=TO_DATE('"+PlattEntDt+"','DD/MM/YYYY') "
										+ "AND CURVE_NM='"+PhyCurFrdPriceNm+"' ";	
								//	System.out.println(BuySell+" :Incase of Forward Price of Curve Nm not in latest date "+to_date+":FORWARD2 PRICE: FMS9_FORWARD2_PRICE_DTL: "+queryString);
										rset2 = stmt.executeQuery(queryString);			
										if(rset2.next())
										{
											VPhy_FwdPrice_Temp.add(rset2.getString(1)==null?"0":rset2.getString(1));  //RLNG_PHYS_FORWARD //SB20210227
										}
										else /////////////////////^^^^^SB20210703: Incase of Forward Price of Curve Nm not in latest date///////////////////////
										{
											VPhy_FwdPrice_Temp.add("0");
										}
							/*////////////SB20210506: Get PPAC Price from FORWARD2///////////////////////
										double ppacPriceApplicable=0;
										//SB20210814		if(PriceTypeCargo.equals("M") && PPACPriceFlag.equals("1"))
										if(PriceTypeCargo.equals("M") && (PPACPriceFlag.equals("1") || CurveName.equals("PPAC")))  //SB20210814
										{
										queryString ="SELECT SETTLE_PRICE FROM FMS9_FORWARD2_PRICE_DTL "+ 
												" WHERE CURVE_DD_MM_YR=TO_DATE('"+PriceIndex_SettleMth+"','DD/MM/YYYY') AND CURVE_TYPE='Forward' AND FLAG='Y' AND ENT_DT=(SELECT TO_DATE(MAX(ENT_DT),'DD-MON-YY') FROM FMS9_FORWARD2_PRICE_DTL "+ 
												" WHERE CURVE_DD_MM_YR=TO_DATE('"+PriceIndex_SettleMth+"','DD/MM/YYYY') AND CURVE_TYPE='Forward' AND FLAG='Y' AND ENT_DT<=TO_DATE('"+to_date+"','DD/MM/YYYY') "
												+ "AND CURVE_NM='PPAC') "
												+ "AND CURVE_NM='PPAC' ";
										System.out.println(" :PPAC PRICE: FMS9_FORWARD2_PRICE_DTL: "+queryString);
											rset2 = stmt.executeQuery(queryString);			
											if(rset2.next())
											{
												VContFixedPrice_Temp.add(rset2.getString(1)==null?"1.11":rset2.getString(1));  //RLNG_PHYS_FORWARD //SB20210227
												ppacPriceApplicable=rset2.getDouble(1);
											}
											else
											{
											///	VPriceTypeDealRate.add("0");
												VContFixedPrice_Temp.add("0");
												ppacPriceApplicable=0;
											}
										}
										//SB20210814	else if(PriceTypeCargo.equals("M") && PPACPriceFlag.equals("0")) //SB20210530
										else if(PriceTypeCargo.equals("M") && (PPACPriceFlag.equals("0") || !CurveName.equals("PPAC")))  //SB20210814
											VContFixedPrice_Temp.add("");//SB20210530
										else if(PriceTypeCargo.equals("F")) //SB20210530
											VContFixedPrice_Temp.add(DealPrice);  ////SB20210530 Contract Rate SN_MST
										else 
											VContFixedPrice_Temp.add("");
								*//////////////^^SB20210506: Get PPAC Price from FORWARD2////////////////////
					}
					
			}
				///////////////////Find out the Min FinCurve Pricing(Avg Pricing)////////////////////
				double Min_Eff_avgCommodityValue=500; 
				if(!PriceTypeCargo.equals("F"))
				{
					for(int m=0; m<VFin_EffSettlePrice_Temp.size(); m++)
					{
						if(Double.parseDouble(""+VFin_EffSettlePrice_Temp.elementAt(m))>0)
						{
						if(Double.parseDouble(""+VFin_EffSettlePrice_Temp.elementAt(m))<Min_Eff_avgCommodityValue) 
							Min_Eff_avgCommodityValue=Double.parseDouble(""+VFin_EffSettlePrice_Temp.elementAt(m)); //SB20200929
						}
					}
				}
				/////////////////////////////////////////////////////////////////////////
				
				///////////////////Find out the Min Foward Pricing////////////////////
				double Min_FwdPrice=500;  String FinPrice="0"; 
				if(!PriceTypeCargo.equals("F"))
					{
					for(int m=0; m<VFin_FwdPrice_Temp.size(); m++)
						{
							FinPrice=nf6.format((Double.parseDouble(""+VFin_FwdPrice_Temp.elementAt(m))*Double.parseDouble(""+VFin_Slope_Temp.elementAt(m)))+Double.parseDouble(""+VFin_Constant_Temp.elementAt(m))); //SB20210514
							if(Double.parseDouble(""+FinPrice)>0)
							{
								if(Double.parseDouble(""+FinPrice)<Min_FwdPrice) 
									Min_FwdPrice=Double.parseDouble(""+FinPrice); //SB20200929
							}
						}
					}
						/////////////////////////////////////////////////////////////////////////
				/////SB20210924//////////////Find out the Min between Foward Pricing & Settled Pricing////////////////////
				double Min_FwdSettlePrice=500;  
				if(!PriceTypeCargo.equals("F"))
					{
						if(Min_Eff_avgCommodityValue<Min_FwdPrice) 
							Min_FwdSettlePrice=Min_Eff_avgCommodityValue;
						else
							Min_FwdSettlePrice=Min_FwdPrice;
					}
				//System.out.println(Min_FwdPrice+":"+Min_Eff_avgCommodityValue+"   Final Min_FwdSettlePrice: "+Min_FwdSettlePrice);	
				/////////////////////////////////////////////////////////////////////////
			//	System.out.println("**********  Min_Eff_avgCommodityValue: "+Min_Eff_avgCommodityValue);	
			/*if(!VRLNG_PHYS_IN_EXPO.elementAt(i).equals(""))
				//SB20210726 Phy_MthBrkUp=Double.parseDouble(""+VRLNG_PHYS_IN_EXPO.elementAt(i))/NoMth;
				Phy_MthBrkUp=Double.parseDouble(""+VRLNG_PHYS_IN_EXPO.elementAt(i))/NoMth_FinCurve;*/
			/*if(!VLNG_ICE_JKM_EXPO.elementAt(i).equals(""))
				//SB20210726 Fin_MthBrkUp=Double.parseDouble(""+VLNG_ICE_JKM_EXPO.elementAt(i))/NoMth;
				Fin_MthBrkUp=Phy_MthBrkUp*Double.parseDouble(""+VFin_Slope_Temp.elementAt(i))/NoMth_FinCurve;*/
		//	if(BuySell.equalsIgnoreCase("Sell"))
			///	System.out.println(PriceType+"**********  VFin_SettleStDt_Temp: "+NoMth);
			Phy_MthBrkUp=Double.parseDouble(""+DCQ)/NoMth_FinCurve;
////////////////////For Calculation of MIN, OPAL/MULTI_LEG, Normal//////////////////////////////////////
			double SumDcq=Double.parseDouble(""+DCQ); double sumQty=0; double balance=0;
			for(int j=0; j<NoMth; j++)
			{	
				if(j==0)
				{
					VDlv_Dt.add(SIGNING_DT.elementAt(i));
					VDcq.add(DCQ);
					
					VDlv_Dt_HP.add(SIGNING_DT.elementAt(i));//HARSH2021901 THIS IS USE ONLY FOR EOD PROCESS
					VDcq_HP.add(DCQ);//HARSH2021901 THIS IS USE ONLY FOR EOD PROCESS
					Vcust_Cd.add(CUSTOMER_CD.elementAt(i)); //HARSH2021901 THIS IS USE ONLY FOR EOD PROCESS
					SEQ_NO.add(j);//HARSH2021901 THIS IS USE ONLY FOR EOD PROCESS
					
				///	VFixedFloat_Type.add("Float");
				//	VActualQty.add(VDealAllocQty.elementAt(i));
				//	VContFixedPrice.add(VContFixedPrice_Temp.elementAt(i));					
				}
				else
				{
					VDlv_Dt.add("");
					VDcq.add("");
					
					VDlv_Dt_HP.add(SIGNING_DT.elementAt(i));//HARSH2021901 THIS IS USE ONLY FOR EOD PROCESS
					VDcq_HP.add(DCQ);//HARSH2021901 THIS IS USE ONLY FOR EOD PROCESS
					Vcust_Cd.add(CUSTOMER_CD.elementAt(i)); //HARSH2021901 THIS IS USE ONLY FOR EOD PROCESS
					SEQ_NO.add(j);//HARSH2021901 THIS IS USE ONLY FOR EOD PROCESS
					
				///	VFixedFloat_Type.add("Float");
				//	VActualQty.add("-0");
				//	VContFixedPrice.add("");
				}
				if(!PriceType.equals("F"))
				{
					VFixedFloat_Type.add("Float");
					VMutiCurveNm.add(VMutiCurveNm_Temp.elementAt(j));	
					VContFixedPrice.add(VContFixedPrice_Temp.elementAt(j));	
					VFin_SettleStDt.add(VFin_SettleStDt_Temp.elementAt(j));
					VFin_SettleEndDt.add(VFin_SettleEndDt_Temp.elementAt(j));
					VFin_Slope.add(VFin_Slope_Temp.elementAt(j));
					VFin_Constant.add(VFin_Constant_Temp.elementAt(j));
					VFin_EXPO_ContMth.add(VFin_CurrContMthYr.elementAt(j));
				}
				else
				{
					VFixedFloat_Type.add("Fixed");
					VMutiCurveNm.add("");	
					VContFixedPrice.add(""+RATE.elementAt(i));	
					VFin_SettleStDt.add("");
					VFin_SettleEndDt.add("");
				///	VFin_SettlePrice.add(VFin_SettlePrice_Temp.elementAt(j));
				//	VFin_EffSettlePrice.add(VFin_EffSettlePrice_Temp.elementAt(j));
					VFin_Slope.add("1");
					VFin_Constant.add("0");
					VFin_EXPO_ContMth.add("");
				//	VFin_FwdPrice.add(VFin_FwdPrice_Temp.elementAt(j));
					
				}
				VPhy_FwdPrice.add(VPhy_FwdPrice_Temp.elementAt(j));
				VPhy_EXPO_ContMth.add(VMthYrWise.elementAt(i));
			///	VFin_EXPO_ContMth.add(VFin_CurrContMthYr.elementAt(j));
				/// Phy_MthBrkUp //for Buy +ve; for Sell -ve
			//SB20210812	VPhy_ORI_EXPO_BrkUp.add(nf.format(Phy_MthBrkUp));
				if(DealName.equals("MULTI_LEG"))  //It was OPAL
				{
					sumQty+=Double.parseDouble(""+nf2.format(Phy_MthBrkUp));
					balance=Double.parseDouble(""+nf2.format(SumDcq-sumQty)); //System.out.println(j+"**********  sumQty: "+sumQty);
					//System.out.println(j+"**********  balance: "+balance);
					if(j==NoMth-1)
						Phy_MthBrkUp=Phy_MthBrkUp+balance;
				}
				
				VPhy_ORI_EXPO_BrkUp.add(nf2.format(Phy_MthBrkUp));
				Fin_MthBrkUp=Phy_MthBrkUp*Double.parseDouble(""+VFin_Slope_Temp.elementAt(j));
				Fin_MthBrkUp=(-1)*Fin_MthBrkUp;   //for Sell +ve; for Buy -ve
				Fin_MthBrkUp=Double.parseDouble(""+nf2.format(Fin_MthBrkUp));
				/*if(!PriceType.equals("F"))
				{
					if(!VMutiCurveNm_Temp.elementAt(j).equals("PPAC"))
						VFin_ORI_EXPO_BrkUp.add(nf2.format(Fin_MthBrkUp));
					//SB20210812 VFin_ORI_EXPO_BrkUp.add(nf10.format(Fin_MthBrkUp));
					else
						VFin_ORI_EXPO_BrkUp.add("0"); //Incase of PPAC
				}
				else if(PriceType.equals("F"))
				{
					Fin_MthBrkUp=0;
					VFin_ORI_EXPO_BrkUp.add("");
				}*/

////////////////////////////////////////////////////////////////////////////////////

////////////////////////////SB20201020: Very Important for DropOff/////////////////////////////////
				int Fin_EXPOBaseValue=0;
				int StratDayCount=0; int StratDayCount2=0; //SB20201017: to check Starting SAT/SUN/HOLIDAY
				String PriceBegMthDt=""+VFin_SettleStDt_Temp.elementAt(j);
				String PriceEndMthDt=""+VFin_SettleEndDt_Temp.elementAt(j);
				int ReportDays=0; int ReportDays2=0;
				
				String Slope=""+Double.parseDouble(""+VFin_Slope_Temp.elementAt(j)); 
				String Const=""+Double.parseDouble(""+VFin_Constant_Temp.elementAt(j));  //SB20210510
				///PriceType=""+VPriceTypeDeal.elementAt(i);
				String GasDate=""+SIGNING_DT.elementAt(i); 
				////////////////////////////////////////////////
				if(PPACPriceFlag.equals("1"))  //SB20210912
					ppacPriceApplicable=Double.parseDouble(""+VContFixedPrice_Temp.elementAt(j)); //SB20210912
				
				int NoOfDays=0;	double LNG_ICE_JKM_EXPO=0; //SB20201019
				queryString = "SELECT TO_DATE('"+to_date+"','DD/MM/YYYY')-TO_DATE('"+GasDate+"','DD/MM/YYYY')+1 FROM DUAL" ;
			//	System.out.println(" DUAL: LAST: "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next()) {
					NoOfDays = rset.getInt(1);
				}
				if(NoOfDays>0)
				{
					////////////////ORI Exposure////////////
					if(!PriceType.equals("F"))
					{
						if(PPACPriceFlag.equals("1")) //SB20210910
						{
							if(ppacPriceApplicable<Min_Eff_avgCommodityValue) //SB20210912
								VFin_ORI_EXPO_BrkUp.add("0"); //SB20210912 //Incase of PPAC is LOWER
							else
								VFin_ORI_EXPO_BrkUp.add(nf2.format(Fin_MthBrkUp));
						}
						else ///if(PPACPriceFlag.equals("0"))  //^^^SB20210910
						{
						if(!VMutiCurveNm_Temp.elementAt(j).equals("PPAC"))
							VFin_ORI_EXPO_BrkUp.add(nf2.format(Fin_MthBrkUp));
						//SB20210812 VFin_ORI_EXPO_BrkUp.add(nf10.format(Fin_MthBrkUp));
						else
							VFin_ORI_EXPO_BrkUp.add("0"); //Incase of PPAC
						}
					}
					else if(PriceType.equals("F"))
					{
						Fin_MthBrkUp=0;
						VFin_ORI_EXPO_BrkUp.add("");
					}
					////////////////////////
					VPhy_RU_flag.add("R");
					if(!PriceType.equals("F"))
					{
						VFin_RU_flag.add("R");
						VFin_Expo_U.add("0");
					}
					else
					{
						VFin_RU_flag.add("");
						VFin_Expo_U.add("");
					}
					VPhy_Expo_U.add("0");
				//	VFin_Expo_U.add("0");
				//SB20210924	if(DealName.equals("MIN") && Min_Eff_avgCommodityValue==Double.parseDouble(""+VFin_EffSettlePrice_Temp.elementAt(j)))
					if(DealName.equals("MIN") && Min_FwdSettlePrice==Double.parseDouble(""+VFin_EffSettlePrice_Temp.elementAt(j))) //SB20210924
					{
						VActualQty.add(VDealAllocQty.elementAt(i));
						VPhy_Expo_R.add(nf2.format(Phy_MthBrkUp));
						if(!VMutiCurveNm_Temp.elementAt(j).equals("PPAC")) //SB20210912
							VFin_Expo_R.add(nf2.format(Fin_MthBrkUp)); //SB20210912
						else //SB20210912
							VFin_Expo_R.add("0"); //SB20210912 //Incase of PPAC
					//SB20210912	VFin_Expo_R.add(nf2.format(Fin_MthBrkUp));
					}
					else if(DealName.equals("MIN"))
					{
						VActualQty.add("-0");
						VPhy_Expo_R.add("0");
						VFin_Expo_R.add("0");
					}
					else //if(!DealName.equals("MIN") && !DealName.equals("OPAL"))
					{
						if(j==0)
						{
							VActualQty.add(VDealAllocQty.elementAt(i));			
						}
						else
						{
							VActualQty.add("-0");
						}
						VPhy_Expo_R.add(nf2.format(Phy_MthBrkUp));
						if(!PriceType.equals("F"))
						{
							if(PPACPriceFlag.equals("1")) //SB20210910
							{
								if(ppacPriceApplicable<Min_Eff_avgCommodityValue) //SB20210912
									VFin_Expo_R.add("0"); //SB20210912 //Incase of PPAC is LOWER
								else
								VFin_Expo_R.add(nf2.format(Fin_MthBrkUp)); //SB20210910 //Incase of PPAC
							}
							else if(PPACPriceFlag.equals("0"))  //^^^SB20210910
							{
							VFin_Expo_R.add(nf2.format(Fin_MthBrkUp));
							}
						}
						else
							VFin_Expo_R.add("");
					}
					
					if(j==0 && DealName.equals("MULTI_LEG")) //It was OPAL
						VFin_EffSettlePrice.add(VFin_AvgEffSettlePrice_Temp.elementAt(NoMth-1));
					else if(j>0 && DealName.equals("MULTI_LEG")) //It was OPAL 20210304
						VFin_EffSettlePrice.add(""); //For Realised Do not require every month Pricing. ONLY Average(as above line condition)
					else if(DealName.equals("MIN"))
						VFin_EffSettlePrice.add(VFin_EffSettlePrice_Temp.elementAt(j)); //SB20210726
					else
						VFin_EffSettlePrice.add(VFin_EffSettlePrice_Temp.elementAt(j)); //For General Pricing(No Pricing Formula)
					if(!PriceType.equals("F"))
					{
						VFin_FwdPrice.add("0");
						VFin_SettlePrice.add(VFin_SettlePrice_Temp.elementAt(j));
					}
					else
					{
						VFin_FwdPrice.add("");
						VFin_SettlePrice.add("");
					}
				}
				else  ///When Delivery Date > To_date(Report Date)
				{
					////////////////ORI Exposure////////////
						if(!PriceType.equals("F"))
						{
							if(PPACPriceFlag.equals("1")) //SB20210910
							{
								if(ppacPriceApplicable<Min_FwdPrice) //SB20210912
									VFin_ORI_EXPO_BrkUp.add("0"); //SB20210912 //Incase of PPAC is LOWER
								else //SB20210912
									VFin_ORI_EXPO_BrkUp.add((nf2.format(Fin_MthBrkUp))); //SB20210910 //Incase of PPAC
							}
							else if(PPACPriceFlag.equals("0"))  //^^^SB20210910
							{
								VFin_ORI_EXPO_BrkUp.add(nf2.format(Fin_MthBrkUp));
							//SB20210812 VFin_ORI_EXPO_BrkUp.add(nf10.format(Fin_MthBrkUp));
							}
						}
						else if(PriceType.equals("F"))
						{
							Fin_MthBrkUp=0;
							VFin_ORI_EXPO_BrkUp.add("");
						}
						////////////////////////
					VActualQty.add("0");
					VPhy_RU_flag.add("U");
					
					VPhy_Expo_R.add("0");
						
					queryString = "SELECT TO_DATE('"+to_date+"','DD/MM/YYYY')-TO_DATE('"+VFin_SettleEndDt_Temp.elementAt(j)+"','DD/MM/YYYY')+1 FROM DUAL" ;
					//	System.out.println(" DUAL: LAST: "+queryString);
						rset = stmt.executeQuery(queryString);
						if(rset.next()) {
							NoOfDays = rset.getInt(1);
						}
						if(NoOfDays>0)
							{
							if(!PriceType.equals("F")) 
								VFin_RU_flag.add("R");
							else
								VFin_RU_flag.add("");
			/*				if(DealName.equals("MIN") && Min_Eff_avgCommodityValue==Double.parseDouble(""+VFin_EffSettlePrice_Temp.elementAt(j)))
							{
								VPhy_Expo_U.add(nf2.format(Phy_MthBrkUp));
							}
							else if(DealName.equals("MIN"))
							{
								VPhy_Expo_U.add("0");
							}
							else
							{
								VPhy_Expo_U.add(nf2.format(Phy_MthBrkUp));
							}*/
							VFin_Expo_U.add("0");
						//	if(DealName.equals("MIN") && Min_Eff_avgCommodityValue==Double.parseDouble(""+VFin_EffSettlePrice_Temp.elementAt(j)))
							FinPrice=nf6.format((Double.parseDouble(""+VFin_FwdPrice_Temp.elementAt(j))*Double.parseDouble(""+VFin_Slope_Temp.elementAt(j)))+Double.parseDouble(""+VFin_Constant_Temp.elementAt(j))); //SB20210514
						///	if(DealName.equals("MIN") && Min_FwdPrice==Double.parseDouble(""+VFin_FwdPrice_Temp.elementAt(j)))
						//Sb20210923	if(DealName.equals("MIN") && Min_FwdPrice==Double.parseDouble(""+FinPrice))
						//SB20210924	if(DealName.equals("MIN") && Min_Eff_avgCommodityValue==Double.parseDouble(""+VFin_EffSettlePrice_Temp.elementAt(j)))
							if(DealName.equals("MIN") && Min_FwdSettlePrice==Double.parseDouble(""+VFin_EffSettlePrice_Temp.elementAt(j))) //SB20210924
							{
								VPhy_Expo_U.add(nf2.format(Phy_MthBrkUp));
								VFin_Expo_R.add(nf2.format(Fin_MthBrkUp));
							}
							else if(DealName.equals("MIN"))
							{
								VPhy_Expo_U.add("0"); //10
								VFin_Expo_R.add("0");
							}
							else
							{
								VPhy_Expo_U.add(nf2.format(Phy_MthBrkUp));
								if(!PriceType.equals("F")) //sb
								{
									if(PPACPriceFlag.equals("1"))
									{
									if(ppacPriceApplicable<Min_Eff_avgCommodityValue) //SB20210912
										VFin_Expo_R.add("0"); //SB20210912 //Incase of PPAC is LOWER
									else
										VFin_Expo_R.add(nf2.format(Fin_MthBrkUp));
									}
									else
										VFin_Expo_R.add(nf2.format(Fin_MthBrkUp));
								}
								else
									VFin_Expo_R.add(""); //sb
							}
							
							if(!PriceType.equals("F")) //sb
							{
								VFin_EffSettlePrice.add(VFin_EffSettlePrice_Temp.elementAt(j));
								VFin_FwdPrice.add("0");		
								VFin_SettlePrice.add(VFin_SettlePrice_Temp.elementAt(j));
							}
							else
							{
								VFin_EffSettlePrice.add("");
								VFin_FwdPrice.add("");		
								VFin_SettlePrice.add("");
								
							}
							
							}
						else  //Unrealised Fin Leg
						{
							double DaysCount=0;		
							String query="select to_date('"+PriceEndMthDt+"','DD/MM/YY')- to_date('"+PriceBegMthDt+"','DD/MM/YY')+1 from dual";
							//System.out.println("Date Difference: dual: "+query);
							rset=stmt.executeQuery(query);
							if(rset.next())
							{
								DaysCount=rset.getInt(1);
							}
							String Days2="";
							queryString = "SELECT TO_DATE('"+to_date+"','DD/MM/YYYY')-TO_DATE('"+PriceBegMthDt+"','DD/MM/YYYY')+1 FROM DUAL" ;
					//	if(i==61)	System.out.println(" DUAL: LAST: "+queryString);
							rset = stmt.executeQuery(queryString);
							if(rset.next()) {
							//ReportDays = rset.getInt(1);
							Days2 = rset.getString(1)==null?"1":rset.getString(1);
							}	
							ReportDays=Integer.parseInt(Days2);	
						//	System.out.println(i+" :ReportDays: "+ReportDays);
					///		if(PriceTypeCargo.equals("M") && !CurveName.equals("PPAC"))
							if(PriceTypeCargo.equals("M") && !VMutiCurveNm_Temp.elementAt(j).equals("PPAC"))
							{
							for(int k=0; k<ReportDays; k++)
							{
								String NextDate2=""; int Days=k;
								queryString = "SELECT TO_CHAR(TO_DATE('"+PriceBegMthDt+"','DD/MM/YYYY')+"+Days+",'DD/MM/YYYY') FROM DUAL";
								//	System.out.println("Get DAY Name: "+queryString);
									rset = stmt.executeQuery(queryString);
									if(rset.next())
									{
										NextDate2 = rset.getString(1)==null?"":rset.getString(1);
									}
								queryString ="SELECT to_char(HOLIDAY_DT,'dd/mm/yyyy') FROM FMS9_CURVE_HOLIDAY_DTL "
										//SB20210708		+ " WHERE HOLIDAY_DT =to_date('"+NextDate2+"','dd/mm/yyyy') AND FLAG='Y' AND HOLIDAY_TYPE='"+HolidayType+"' ORDER BY HOLIDAY_DT";
										+ " WHERE HOLIDAY_DT =to_date('"+NextDate2+"','dd/mm/yyyy') AND FLAG='Y' AND CURVE_NM='"+CurveName+"' ORDER BY HOLIDAY_DT";//SB20210708
								rset = stmt.executeQuery(queryString);
							//	System.out.println(">>>>>>>PRICE PUULING: "+queryString);
								if(rset.next())
								{
									StratDayCount++; //System.out.println("Get HOLI DAY Name: ");		
								}
								else
								{
								String WeeklyOff="";
								queryString = "SELECT TO_CHAR(TO_DATE('"+NextDate2+"','DD/MM/YYYY'),'DAY') FROM DUAL";
								//	System.out.println("Get DAY Name: "+queryString);
									rset = stmt.executeQuery(queryString);
									if(rset.next())
									{
										WeeklyOff = rset.getString(1)==null?"":rset.getString(1);
									}
									if(WeeklyOff.trim().equals("SATURDAY")) 
									{
										StratDayCount++;
									}
									else if(WeeklyOff.trim().equals("SUNDAY")) 
									{
										StratDayCount++;
									}
								}
							}
							} //Eof SAT/SUN/HOLI Checking
							ReportDays = ReportDays-StratDayCount;
							if(ReportDays>=0)
								Fin_EXPOBaseValue=ReportDays;
							////////////////////////////////Percen Reduction///////////////////////////////
							StratDayCount=0;
						///	if(PriceTypeCargo.equals("M") && !CurveName.equals("PPAC"))
							if(PriceTypeCargo.equals("M") && !VMutiCurveNm_Temp.elementAt(j).equals("PPAC"))
							{
							for(int k=0; k<DaysCount; k++)
							{
								String NextDate2=""; int Days=k;
								queryString = "SELECT TO_CHAR(TO_DATE('"+PriceBegMthDt+"','DD/MM/YYYY')+"+Days+",'DD/MM/YYYY') FROM DUAL";
								//	System.out.println("Get DAY Name: "+queryString);
									rset = stmt.executeQuery(queryString);
									if(rset.next())
									{
										NextDate2 = rset.getString(1)==null?"":rset.getString(1);
									}
								queryString ="SELECT to_char(HOLIDAY_DT,'dd/mm/yyyy') FROM FMS9_CURVE_HOLIDAY_DTL "
										//SB20210708		+ " WHERE HOLIDAY_DT =to_date('"+NextDate2+"','dd/mm/yyyy') AND FLAG='Y' AND HOLIDAY_TYPE='"+HolidayType+"' ORDER BY HOLIDAY_DT";
										+ " WHERE HOLIDAY_DT =to_date('"+NextDate2+"','dd/mm/yyyy') AND FLAG='Y' AND CURVE_NM='"+CurveName+"' ORDER BY HOLIDAY_DT";//SB20210708
								rset = stmt.executeQuery(queryString);
							//	System.out.println(">>>>>>>PRICE PUULING: "+queryString);
								if(rset.next())
								{
									StratDayCount++; //System.out.println("Get HOLI DAY Name: ");		
								}
								else
								{
								String WeeklyOff="";
								queryString = "SELECT TO_CHAR(TO_DATE('"+NextDate2+"','DD/MM/YYYY'),'DAY') FROM DUAL";
								//	System.out.println("Get DAY Name: "+queryString);
									rset = stmt.executeQuery(queryString);
									if(rset.next())
									{
										WeeklyOff = rset.getString(1)==null?"":rset.getString(1);
									}
									if(WeeklyOff.trim().equals("SATURDAY")) 
									{
										StratDayCount++;
									}
									else if(WeeklyOff.trim().equals("SUNDAY")) 
									{
										StratDayCount++;
									}
								}
							}
							} //Eof finding Start date after SAT/SUN/HOLI days
								DaysCount = DaysCount-StratDayCount;
							double PercReduce2=0;
							int BaseValue=Fin_EXPOBaseValue; 
								if(DaysCount>0)
									PercReduce2=100/DaysCount;
						
							
							if(BuySell.equalsIgnoreCase("Sell"))
								LNG_ICE_JKM_EXPO=(-1)*Double.parseDouble(DCQ)*Double.parseDouble(Slope);
							else
								LNG_ICE_JKM_EXPO=Double.parseDouble(DCQ)*Double.parseDouble(Slope);

							double FinancialReduction=Double.parseDouble(""+PercReduce2)/100; //SB20201006

							/////////////////////BASE VALUE Episode//////////////////
					//		if(PriceTypeCargo.equals("M") && CurveName.equals("PPAC"))  System.out.println(CurveName+GasDate+" :GAS-DATE: "+DaysCount+" :Days-count: "+BaseValue+" :BaseValue: "+FinancialReduction);
							///FinancialReduction=Double.parseDouble(CumuPercReduce)/100; 
						///	if(BaseValue>0 && DropOff.equals("Y"))  //SB: Dropoff Logic: if Y multiply by BaseValue i.e. days(excluding SAT/SUN & HOLI)
						////		LNG_ICE_JKM_EXPO=LNG_ICE_JKM_EXPO-(LNG_ICE_JKM_EXPO*FinancialReduction)*BaseValue; //SB20201006
								LNG_ICE_JKM_EXPO=Fin_MthBrkUp-(Fin_MthBrkUp*FinancialReduction)*BaseValue; //SB20201006
						//		if(PriceTypeCargo.equals("M") && CurveName.equals("PPAC"))  System.out.println(LNG_ICE_JKM_EXPO+GasDate+" :GAS-DATE: "+Fin_MthBrkUp+" :Fin_MthBrkUp: "+BaseValue+" :BaseValue: "+FinancialReduction);
							///////////////////SB20210504////////////////////////////////
							
							if(!PriceType.equals("F"))	
								VFin_RU_flag.add("U");
							else
								VFin_RU_flag.add("");
							
							
							
							////////PPAC Price Checking///////////////
							String FinFwdPrice="0";
							if(Double.parseDouble(""+VFin_FwdPrice_Temp.elementAt(j))>0) 
								FinFwdPrice=nf6.format((Double.parseDouble(""+VFin_FwdPrice_Temp.elementAt(j))*Double.parseDouble(Slope))+Double.parseDouble(Const)); //SB20210514
						///	else
						///		FinFwdPrice=DealPrice;
					//Sb20210815		if(PPACPriceFlag.equals("1"))
							if(PriceTypeCargo.equals("M") && VMutiCurveNm_Temp.elementAt(j).equals("PPAC"))
							{//System.out.println(Min_FwdPrice+" :111111 >>>>>>>> VFin_FwdPrice_Temp.elementAt(j)): "+FinFwdPrice);
								///	if(Double.parseDouble(""+FinFwdPrice)<Double.parseDouble(""+VFin_FwdPrice_Temp.elementAt(j)))
								///	if(DealName.equals("MIN") && Min_FwdPrice==Double.parseDouble(""+VFin_FwdPrice_Temp.elementAt(j)))
								//SB20210924	if(DealName.equals("MIN") && Min_FwdPrice==Double.parseDouble(""+FinFwdPrice))
									if(DealName.equals("MIN") && Min_FwdSettlePrice==Double.parseDouble(""+FinFwdPrice)) //SB20210924
									{
										VPhy_Expo_U.add(nf2.format(Phy_MthBrkUp));
										VFin_Expo_U.add(nf2.format(LNG_ICE_JKM_EXPO));
										if(ReportDays<0)
											VFin_Expo_R.add(nf2.format(Fin_MthBrkUp - Fin_MthBrkUp));
										else
											VFin_Expo_R.add(nf2.format(Fin_MthBrkUp - LNG_ICE_JKM_EXPO));
									}
									else
									{
										VPhy_Expo_U.add("0");//20
										VFin_Expo_U.add("0");
										VFin_Expo_R.add("0");
									}
							}
							else  ///////////////////////////
							{//System.out.println(Phy_MthBrkUp+" :222222 ---->>>>>>>> PPACPriceFlag): "+LNG_ICE_JKM_EXPO);
							//System.out.println(Min_FwdPrice+" :222222 >>>>>>>> FinFwdPrice): "+FinFwdPrice);
							
							///	if(DealName.equals("MIN") && Min_FwdPrice==Double.parseDouble(""+VFin_FwdPrice_Temp.elementAt(j)))
						//SB20210924			if(DealName.equals("MIN") && Min_FwdPrice==Double.parseDouble(""+FinFwdPrice))
								if(DealName.equals("MIN") && Min_FwdSettlePrice==Double.parseDouble(""+FinFwdPrice)) //SB20210924
								{
									VPhy_Expo_U.add(nf2.format(Phy_MthBrkUp));
									VFin_Expo_U.add(nf2.format(LNG_ICE_JKM_EXPO));
									if(ReportDays<0)
										VFin_Expo_R.add(nf.format(0));
									else
										VFin_Expo_R.add(nf2.format(Fin_MthBrkUp - LNG_ICE_JKM_EXPO));
									
								}
								else if(DealName.equals("MIN"))
								{
									VPhy_Expo_U.add("0");//30
									VFin_Expo_U.add("0");
									VFin_Expo_R.add("0");
									
								}
								else
								{
									if(!PriceType.equals("F"))
									{
										VPhy_Expo_U.add(nf2.format(Phy_MthBrkUp));
										///////////////////SB20210910////////////////////////////////
										if(PPACPriceFlag.equals("1"))
										{
											if(ppacPriceApplicable<Min_FwdPrice) //SB20210912
										 //SB20210912 if(Double.parseDouble(""+FinFwdPrice)>Double.parseDouble(""+VContFixedPrice_Temp.elementAt(j)))
											LNG_ICE_JKM_EXPO=0; //SB20210910
										}
										///////////////////^^^SB20210910////////////////////////////////
										VFin_Expo_U.add(nf2.format(LNG_ICE_JKM_EXPO));
										if(ReportDays<0)
											VFin_Expo_R.add(0);
										else
										{
											if(PPACPriceFlag.equals("1"))
											{
											if(ppacPriceApplicable<Min_FwdPrice) //SB20210912
												VFin_Expo_R.add("0"); //SB20210912 //Incase of PPAC is LOWER
											else
											VFin_Expo_R.add(nf2.format(Fin_MthBrkUp - LNG_ICE_JKM_EXPO));
											}
											else
												VFin_Expo_R.add(nf2.format(Fin_MthBrkUp - LNG_ICE_JKM_EXPO));
										}
									}
									else
									{
										VPhy_Expo_U.add(nf2.format(Phy_MthBrkUp));
										VFin_Expo_U.add("");
										VFin_Expo_R.add("");
									}
									/*if(ReportDays<0)
										VFin_Expo_R.add(nf.format(Fin_MthBrkUp - Fin_MthBrkUp));
									else
										VFin_Expo_R.add(nf.format(Fin_MthBrkUp - LNG_ICE_JKM_EXPO));*/
								}
								
							/*if(ReportDays<0)
								VFin_Expo_R.add(nf.format(Fin_MthBrkUp - Fin_MthBrkUp));
							else
								VFin_Expo_R.add(nf.format(Fin_MthBrkUp - LNG_ICE_JKM_EXPO));*/
							}//System.out.println(PPACPriceFlag+" :111111 >>>>>>>> VFin_SettlePrice_Temp.elementAt(j)): "+VFin_SettlePrice_Temp.elementAt(j));
							VFin_EffSettlePrice.add("");
							if(!PriceType.equals("F"))
							{
								VFin_FwdPrice.add(VFin_FwdPrice_Temp.elementAt(j));	
								if(Double.parseDouble(""+VFin_SettlePrice_Temp.elementAt(j))==0)
									VFin_SettlePrice.add("0"); //SB20210910
								else
									VFin_SettlePrice.add(VFin_SettlePrice_Temp.elementAt(j));
							}
							else
							{
								VFin_FwdPrice.add("");
								VFin_SettlePrice.add("");
							}
							
						}
				}	
			}	
			////////////////////^^^^^For Calculation of MIN, OPAL/MULTI_LEG, Normal//////////////////////////////////////
		}

		
		for(int ii=0; ii<VPhy_Expo_U.size(); ii++)
		{			
			double mtm=0; double mtm2=0; double PriceConst=0; 
			double realised=0; double realised2=0; double PriceConst2=0;  double Total=0;
			mtm=Double.parseDouble(""+VPhy_Expo_U.elementAt(ii))*Double.parseDouble(""+VPhy_FwdPrice.elementAt(ii));//
		
			if(!VFin_FwdPrice.elementAt(ii).equals("") && !VFin_Constant.elementAt(ii).equals("")) //SB20201119
				PriceConst=Double.parseDouble(""+VFin_FwdPrice.elementAt(ii))+(Double.parseDouble(""+VFin_Constant.elementAt(ii))/Double.parseDouble(""+VFin_Slope.elementAt(ii)));  
			if(!VFin_Expo_U.elementAt(ii).equals(""))
				mtm2=Double.parseDouble(""+VFin_Expo_U.elementAt(ii))*PriceConst;
				VMTM.add(nf.format(mtm+mtm2)); //Sb20201023
				VU_Phy_Leg.add(nf.format(mtm));
				VU_Fin_Leg.add(nf.format(mtm2));
				
			if(!VFin_SettlePrice.elementAt(ii).equals(""))
				PriceConst2=Double.parseDouble(""+VFin_SettlePrice.elementAt(ii))+(Double.parseDouble(""+VFin_Constant.elementAt(ii))/Double.parseDouble(""+VFin_Slope.elementAt(ii))); 
			////////////////SB20210505///////////////////
			String EffPrice=""+VFin_EffSettlePrice.elementAt(ii);
			double SettleAvgPriceSlopCost=0;
			if(!VFin_FwdPrice.elementAt(ii).equals("") && !VFin_Constant.elementAt(ii).equals("")) //SB20201119
				SettleAvgPriceSlopCost=(Double.parseDouble(""+VFin_FwdPrice.elementAt(ii))*Double.parseDouble(""+VFin_Slope.elementAt(ii)))+Double.parseDouble(""+VFin_Constant.elementAt(ii));  
			
			if(VFixedFloat_Type.elementAt(ii).equals("Float") && VPhy_RU_flag.elementAt(ii).equals("U"))
			{
				//if(ii>215 && ii<225)
					//System.out.println(ii+" :UUUUUU  BREAKUP ---->>>>>>>> : "+VPhy_RU_flag.elementAt(ii)+" : "+VFin_RU_flag.elementAt(ii));
				if(!VFin_Expo_R.elementAt(ii).equals(""))
				 realised=Double.parseDouble(""+VFin_Expo_R.elementAt(ii));
				/////SB20210513//////////Added for PPAC Logic: It may change in future////////////////////////
					if(PPACPriceFlag.equals("1")) //Sb20210923
			//SB20210910		if(!VContFixedPrice.elementAt(ii).equals("") && VMutiCurveNm.elementAt(ii).equals("PPAC"))	
			//Sb20210923		if(!VContFixedPrice.elementAt(ii).equals("") && (PPACPriceFlag.equals("1") || VMutiCurveNm.elementAt(ii).equals("PPAC")))	 //SB20210910
					{
						if(!VFin_EffSettlePrice.elementAt(ii).equals("")) //Sb20210923
							SettleAvgPriceSlopCost=Double.parseDouble(""+VFin_EffSettlePrice.elementAt(ii)); //Sb20210923
						
							if(Double.parseDouble(""+SettleAvgPriceSlopCost)<=Double.parseDouble(""+VContFixedPrice.elementAt(ii)))
							{
								realised=Double.parseDouble(""+VFin_Expo_R.elementAt(ii));
							//	EffPrice=""+VEff_Price.elementAt(i);
							}
							else
							{
								realised=(-1)*Double.parseDouble(""+VPhy_Expo_U.elementAt(ii));
						//		EffPrice=""+VPriceTypeDealRate.elementAt(i); //SB20210504
								PriceConst2=Double.parseDouble(""+VContFixedPrice.elementAt(ii));   //SB20210504
							}								
					}
				////^^^^^SB20210513//////////Added for PPAC Logic: It may change in future////////////////////////
						realised2=realised*PriceConst2;
			}
			else if(VFixedFloat_Type.elementAt(ii).equals("Float") && VPhy_RU_flag.elementAt(ii).equals("R"))
			{//if(ii>215 && ii<225)
			//	System.out.println(ii+" :RRRRR  BREAKUP ---->>>>>>>> : "+VPhy_RU_flag.elementAt(ii)+" : "+VFin_RU_flag.elementAt(ii));
			//	System.out.println(ii+" :RRRRR  BREAKUP ---->>>>>>>> : "+VFin_EffSettlePrice.elementAt(ii)+" : "+VContFixedPrice.elementAt(ii));
				realised=(-1)*Double.parseDouble(""+VActualQty.elementAt(ii)); //SB20201102: As discussed on 2nd Nov 2020 "ActualQty" to be reversed for Realised Financial Leg(Last Column)
			////SB20210513//////////Added for PPAC Logic: It may change in future////////////////////////
			///	if(!VFin_EffSettlePrice.elementAt(ii).equals("") && PPACPriceFlag.equals("1"))
			//SB20210910	if(!VFin_EffSettlePrice.elementAt(ii).equals("") && VMutiCurveNm.elementAt(ii).equals("PPAC"))	
				if(!VContFixedPrice.elementAt(ii).equals("") && (PPACPriceFlag.equals("1") || VMutiCurveNm.elementAt(ii).equals("PPAC")))	 //SB20210910
				{
					if(Double.parseDouble(""+VFin_EffSettlePrice.elementAt(ii))<Double.parseDouble(""+VContFixedPrice.elementAt(ii)))
					{
					//	EffPrice=""+VEff_Price.elementAt(ii);
						EffPrice=""+VFin_EffSettlePrice.elementAt(ii);
					}
					else
					{
						EffPrice=""+VContFixedPrice.elementAt(ii); //SB20210504
					}
				}
			////^^^^^SB20210513//////////Added for PPAC Logic: It may change in future////////////////////////
						if(!EffPrice.equals(""))
					realised2=realised*Double.parseDouble(""+EffPrice);
			}
		
			if(VFixedFloat_Type.elementAt(ii).equals("Fixed") && VPhy_RU_flag.elementAt(ii).equals("U"))
			{
				realised=(-1)*Double.parseDouble(""+VPhy_Expo_U.elementAt(ii)); 
				realised2=realised*Double.parseDouble(""+VContFixedPrice.elementAt(ii)); 
			}
			else if(VFixedFloat_Type.elementAt(ii).equals("Fixed") && VPhy_RU_flag.elementAt(ii).equals("R"))
			{
				realised=(-1)*Double.parseDouble(""+VActualQty.elementAt(ii)); 
				realised2=realised*Double.parseDouble(""+VContFixedPrice.elementAt(ii)); 
			}
			//////////////^^^^SB20201028////////////////		
		
			VPhy_U_Leg.add(nf.format(mtm));
			VFin_U_Leg.add(nf.format(mtm2));
			VFin_R_Leg.add(nf.format(realised2));
			VTotalPnL.add(nf.format(mtm+mtm2+realised2));

		}
	//	VRLNG_PHYS_IN_EXPO_SubTotal.add(nf.format(SumTotalRLNG_PHYS));
		VLNG_ICE_JKM_EXPO_SubTotal.add(nf.format(SumTotalLNG_JKM));
		double SumTotal=0; 
		double SumTotalRealised=0; double SumTotalFinUnRealised=0; double SumTotalPhyUnRealised=0; //SB20201228
		double TotalDCQTCQ=0;
		double TotalAllocQty=0;
		double TotalRealizedQty=0;
		double TotalUnRealizedQty=0;
		double TotalExposureQty=0;
		double TotalExposureJKMQty=0;
		
		for(int i=0; i<VPhy_Expo_U.size(); i++)
		{
			if(!VDcq.elementAt(i).equals(""))
				TotalDCQTCQ=TotalDCQTCQ+Double.parseDouble(""+VDcq.elementAt(i));
			if(!VActualQty.elementAt(i).equals(""))
				TotalAllocQty=TotalAllocQty+Double.parseDouble(""+VActualQty.elementAt(i));
			if(!VPhy_Expo_U.elementAt(i).equals(""))
				TotalRealizedQty=TotalRealizedQty+Double.parseDouble(""+VPhy_Expo_U.elementAt(i));
			if(!VFin_Expo_U.elementAt(i).equals(""))
				TotalUnRealizedQty=TotalUnRealizedQty+Double.parseDouble(""+VFin_Expo_U.elementAt(i));
		//SB20201026	SumTotal=SumTotal+Double.parseDouble(""+VTotal.elementAt(i));
			SumTotal=SumTotal+Double.parseDouble(""+VTotalPnL.elementAt(i));
			SumTotalRealised=SumTotalRealised+Double.parseDouble(""+VFin_R_Leg.elementAt(i)); //SB20201228
			SumTotalFinUnRealised=SumTotalFinUnRealised+Double.parseDouble(""+VFin_U_Leg.elementAt(i)); //SB20201228
			SumTotalPhyUnRealised=SumTotalPhyUnRealised+Double.parseDouble(""+VPhy_U_Leg.elementAt(i)); //SB20201228
			
	
		}
		FinalTotal=nf.format(SumTotal);
		FinalTotalRealised=nf.format(SumTotalRealised); //Sb20201229
		FinalTotalFinUnRealised=nf.format(SumTotalFinUnRealised); //Sb20201229
		FinalTotalPhyUnRealised=nf.format(SumTotalPhyUnRealised); //Sb20201229
		Total_DCQ_TCQ = nf.format(TotalDCQTCQ);
		Total_AllocQty = nf.format(TotalAllocQty);
		Total_RealizedQty = nf.format(TotalRealizedQty);
		Total_UnRealizedQty = nf.format(TotalUnRealizedQty);
		Total_ExposureJKMQty = nf.format(TotalExposureJKMQty);
	/*	System.out.println(VLNG_ICE_JKM_EXPO_SubTotal.size()+" :BREAKUP ---->>>>>>>> VLNG_ICE_JKM_EXPO_SubTotal): "+VLNG_ICE_JKM_EXPO_SubTotal);
		System.out.println(VFin_ORI_EXPO_BrkUp.size()+" :BREAKUP ---->>>>>>>> VFin_ORI_EXPO_BrkUp): "+VFin_ORI_EXPO_BrkUp);
	//	System.out.println(VFin_EXPO_BrkUp.size()+" :BREAKUP ---->>>>>>>> VFin_EXPO_BrkUp): "+VFin_EXPO_BrkUp);
		System.out.println(VPhy_EXPO_ContMth.size()+" :BREAKUP ---->>>>>>>> VPhy_EXPO_ContMth): "+VPhy_EXPO_ContMth);
		System.out.println(VFin_EXPO_ContMth.size()+" :BREAKUP ---->>>>>>>> VFin_EXPO_ContMth): "+VFin_EXPO_ContMth);
		System.out.println(VFin_SettleStDt.size()+" :BREAKUP ---->>>>>>>> VFin_SettleStDt): "+VFin_SettleStDt);
		System.out.println(VFin_SettleEndDt.size()+" :BREAKUP ---->>>>>>>> VFin_SettleEndDt): "+VFin_SettleEndDt);
		System.out.println(VFin_EffSettlePrice.size()+" :BREAKUP ---->>>>>>>> VFin_EffSettlePrice): "+VFin_EffSettlePrice);
		System.out.println(VFin_SettlePrice.size()+" :BREAKUP ---->>>>>>>> VFin_SettlePrice): "+VFin_SettlePrice);
		System.out.println(VFin_Slope.size()+" :BREAKUP ---->>>>>>>> VFin_Slope): "+VFin_Slope);
		System.out.println(VFin_Constant.size()+" :BREAKUP ---->>>>>>>> VFin_Constant): "+VFin_Constant);
		System.out.println(VFin_FwdPrice.size()+" :BREAKUP ---->>>>>>>> VFin_FwdPrice): "+VFin_FwdPrice);
		System.out.println(VPhy_FwdPrice.size()+" :BREAKUP ---->>>>>>>> VPhy_FwdPrice): "+VPhy_FwdPrice);
		System.out.println(VPhy_RU_flag.size()+" :BREAKUP ---->>>>>>>> VPhy_RU_flag): "+VPhy_RU_flag);
		System.out.println(VFin_RU_flag.size()+" :BREAKUP ---->>>>>>>> VFin_RU_flag): "+VFin_RU_flag);
		System.out.println(VPhy_Expo_U.size()+" :BREAKUP ---->>>>>>>> VPhy_Expo_U): "+VPhy_Expo_U);
		System.out.println(VFin_Expo_U.size()+" :BREAKUP ---->>>>>>>> VFin_Expo_U): "+VFin_Expo_U);
		System.out.println(VPhy_U_Leg.size()+" :BREAKUP ---->>>>>>>> VPhy_U_Leg): "+VPhy_U_Leg);
		System.out.println(VFin_U_Leg.size()+" :BREAKUP ---->>>>>>>> VFin_U_Leg): "+VFin_U_Leg);
		System.out.println(VFin_R_Leg.size()+" :BREAKUP ---->>>>>>>> VFin_R_Leg): "+VFin_R_Leg);
		System.out.println(VTotalPnL.size()+" :BREAKUP ---->>>>>>>> VTotalPnL): "+VTotalPnL);
		System.out.println(VFin_SettlePrice.size()+" :BREAKUP ---->>>>>>>> VFin_SettlePrice): "+VFin_SettlePrice);
		System.out.println(VFin_Constant.size()+" :BREAKUP ---->>>>>>>> VFin_Constant): "+VFin_Constant);
		System.out.println(VFin_Slope.size()+" :BREAKUP ---->>>>>>>> VFin_Slope): "+VFin_Slope);
		System.out.println(VFin_Expo_R.size()+" :BREAKUP ---->>>>>>>> VFin_Expo_R): "+VFin_Expo_R);
		System.out.println(VFin_Slope.size()+" :BREAKUP ---->>>>>>>> VFin_Slope): "+VFin_Slope);
		System.out.println(VFin_EffSettlePrice.size()+" :BREAKUP ---->>>>>>>> VFin_EffSettlePrice): "+VFin_EffSettlePrice);
		System.out.println(VMutiCurveNm.size()+" :BREAKUP ---->>>>>>>> VMutiCurveNm): "+VMutiCurveNm);*/
//////////TIME CALCULATION/////////////////////////////////////////////
				String CurrDtTm2="";
				queryString = "SELECT SYSDATE FROM DUAL";
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						CurrDtTm2 = rset.getString(1);//System.out.println(VSettle_ICE_JKM_AVG.size()+" :<<<<<<INITIAL STEPS COMPLETED >>>>>>>>>>>>>>>>>>>>>>.....: "+CurrDtTm2);
					}
				//////////********TIME CALCULATION/////////////////////////////////////////////
		System.out.println(VMutiCurveNm.size()+" :MULTI_LEG(OPAL)/MIN BREAKUP --COMPLETED-->>>>>>>> Total PnL(INR): "+FinalTotal+" : "+CurrDtTm2);
	}
	catch(Exception e)
	{
		//System.out.println("Exception in Databean_Contract_MasterReport--->fetchcustomer()-->"+e);
		  e.printStackTrace();	
	}
}
////////
////////////////////////////^^^SB20210312: New logic for Phys & Fin/////////////////////////////////////////
////////SB20210909: For P files function brought from Market_Risk.java///////////////////
public void ExposureDealWiseFreezeDtlViewForQP_V2MIN_OPAL(String ReportDt)
{
	try
	{
		ReportDt=to_date; 
		String tempReportDt[]=ReportDt.split("/");
		String GenMth=tempReportDt[1]; String GenYr=tempReportDt[2]; 
		String GenMthYr=GenMth+"/"+GenYr;
		String CashMthFirstDt="01/"+GenMthYr; 
	
		Vector VQPCont_Mth_DDMONYYTemp = new Vector();
		Vector VQPCont_Mth_Temp = new Vector();	
		Vector VQPDeal_NoTemp= new Vector();
		Vector VQPBuy_SellTemp = new Vector();
		Vector VQPCurve_NmTemp = new Vector();
		Vector VQPCounter_PartyTemp = new Vector();
		Vector VQPDEAL_END_DTTemp = new Vector();
		Vector VQPDEAL_START_DTTemp = new Vector();
		Vector VQPPhys_Curve_NmTemp = new Vector(); //SB20210403
		
		String user_cd=Emp_cd;
		int Count=0;
		queryString = "SELECT COUNT(DEAL_ID)  from FMS9_EOD_EXPOSURE_DTL "
		+ " where TO_DATE(REPORT_DT, 'DD-MON-YY')=TO_DATE(to_date('"+ReportDt+"','dd/mm/yyyy'), 'DD-MON-YY') AND FLAG='Y' ";
		//	System.out.println("FMS9_CURVE_PRICE_DTL: "+queryString);
		rset = stmt.executeQuery(queryString);
		if(rset.next())
		{
			Count = rset.getInt(1);
		}
		queryString = "SELECT Distinct TO_CHAR(TO_DATE(CONT_MTH, 'DD-MON-YY'),'DD/MM/YYYY') "//,TO_CHAR(DEAL_START_DT,'dd/mm/yyyy'), PRICE_TYPE, CURVE_NM "
		+ "from FMS9_EOD_EXPOSURE_DTL "
		+ " where TO_DATE(REPORT_DT, 'DD-MON-YY')=TO_DATE(to_date('"+ReportDt+"','dd/mm/yyyy'), 'DD-MON-YY') AND FLAG='Y' "
		//SB20210201:As per Shiladitya		
		+ " AND TO_DATE(CONT_MTH, 'DD-MON-YY')>=TO_DATE(to_date('"+CashMthFirstDt+"','dd/mm/yyyy'), 'DD-MON-YY') "
		//SB20210201:+ 				+ "ORDER BY TO_CHAR(to_date(CONT_MTH,'dd/mm/yyyy'), 'DD-MON-YY') DESC";
		+"ORDER BY TO_CHAR(TO_DATE(CONT_MTH, 'DD-MON-YY'),'DD/MM/YYYY') ASC";
		//System.out.println("FMS9_CURVE_PRICE_DTL: "+queryString);
		rset = stmt.executeQuery(queryString);
		while(rset.next())
		{
			String TempDate=rset.getString(1);//System.out.println("TempDate: "+TempDate);
			//HARSH20210421 String tempmap_id[]=TempDate.split("-");
			String tempmap_id[]=TempDate.split("/"); //HARSH20210421
			//HARSH20210421 TempDate=tempmap_id[0]+"-"+tempmap_id[1];
			TempDate=tempmap_id[2]+"-"+tempmap_id[1]; //HARSH20210421 
			//System.out.println(tempmap_id[2]+"DUAL: "+TempDate);
			//System.out.println(" MM/YYYY : "+tempmap_id[1]+"/"+tempmap_id[2]);
			VQPCont_Mth_DDMONYYTemp.add(tempmap_id[1]+"/"+tempmap_id[2]); //HARSH20210421
		
			//SB20210306		queryString = "SELECT TO_CHAR(TO_DATE('"+TempDate+"','YYYY/MM'),'MON-YY'), TO_CHAR(TO_DATE('"+TempDate+"','YYYY/MM'),'DD-MON-YY') FROM DUAL ";
			//SB20210412		queryString = "SELECT TO_CHAR(TO_DATE('"+TempDate+"','YYYY/MM'),'MON-YY'), TO_CHAR(TO_DATE('"+TempDate+"','YY/MM'),'MM/YYYY') FROM DUAL ";
			//HARSH20210421 queryString = "SELECT TO_CHAR(TO_DATE('"+TempDate+"','YY-MM'),'MON-YY'), TO_CHAR(TO_DATE('"+TempDate+"','YY-MM'),'MM/YYYY') FROM DUAL ";
			queryString = "SELECT TO_CHAR(TO_DATE('"+TempDate+"','YYYY-MM'),'MON-YY'), TO_CHAR(TO_DATE('"+TempDate+"','YYYY-MM'),'MM/YYYY') FROM DUAL ";
		//	System.out.println("DUAL: "+queryString);
			rset2 = stmt2.executeQuery(queryString);
			if(rset2.next()) {
				VQPCont_Mth_Temp.add(rset2.getString(1)==null?"":rset2.getString(1));
				//HARSH20210421 VQPCont_Mth_DDMONYYTemp.add(rset2.getString(2)==null?"":rset2.getString(2));
			}
		}
		String QueryEoD="SELECT DEAL_ID, CUSTOMER_NM, BUY_SELL, CURVE_NM, TO_CHAR(DEAL_START_DT,'DD/MM/YYYY'), TO_CHAR(DEAL_END_DT,'DD/MM/YYYY'), PHYS_CURVE_NM "
		+ " FROM FMS9_EOD_EXPOSURE_MST WHERE REPORT_DT=TO_DATE('"+ReportDt+"','DD/MM/YYYY')";		
		//	System.out.println("FMS9_EOD_EXPOSURE_DTL: "+QueryEoD);
		rset = stmt.executeQuery(QueryEoD);
		while(rset.next())		
		{			
			VQPDeal_NoTemp.add(rset.getString(1)==null?"":rset.getString(1));			
			VQPCounter_PartyTemp.add(rset.getString(2)==null?"":rset.getString(2));
			VQPBuy_SellTemp.add(rset.getString(3)==null?"":rset.getString(3));		
			VQPCurve_NmTemp.add(rset.getString(4)==null?"":rset.getString(4));
			VQPDEAL_START_DTTemp.add(rset.getString(5)==null?"":rset.getString(5));
			VQPDEAL_END_DTTemp.add(rset.getString(6)==null?"":rset.getString(6));
			VQPPhys_Curve_NmTemp.add(rset.getString(7)==null?"":rset.getString(7));
		}
	//	System.out.println(VQPCurve_NmTemp.size()+" :VQPCurve_NmTemp: No. of Records: "+VQPCurve_NmTemp);
		if(VQPCounter_Party.size()==0)
			System.out.println("NOT AVAILABLE in FMS9_EOD_EXPOSURE_MST for Report Date: "+ReportDt);
		if(Count>0)
		{
		for(int i=0; i<VQPDeal_NoTemp.size(); i++)
		{
			for(int j=0; j<VQPCont_Mth_Temp.size(); j++)
			{	
				String ContMthFirstDt="01-"+VQPCont_Mth_Temp.elementAt(j);
				//////////////////////////////To filter the beyond Contract Month ///////////////	
				int CountBreakUp=0; String CurveNameBrkup=""; String PriceTypeBrkup="";
				queryString = "SELECT DISTINCT PHYS_CURVE_NM, (SEq_NO), (PRICE_TYPE)"+
						" from FMS9_EOD_EXPOSURE_DTL "+
						" where TO_DATE(REPORT_DT, 'DD-MON-YY')=TO_DATE(to_date('"+ReportDt+"','dd/mm/yyyy'), 'DD-MON-YY') AND FLAG='Y' AND DEAL_ID='"+VQPDeal_NoTemp.elementAt(i)+"' "
						+ "GROUP BY PHYS_CURVE_NM, SEq_NO, PRICE_TYPE" ;
				//	System.out.println(" DUAL: LAST: "+queryString);
				rset1 = stmt1.executeQuery(queryString);
				while(rset1.next()) {
					CurveNameBrkup = rset1.getString(1)==null?"":rset1.getString(1);
					CountBreakUp = rset1.getInt(2);
					PriceTypeBrkup = rset1.getString(3)==null?"":rset1.getString(3);
					//}
			//////////////////////////////////^^//////////////////////////////////////////////	
					
		///Sb		for(int m=0; m<CountBreakUp; m++)
		///Sb		{
					queryString = "SELECT SUM(UNR_EXPO_PHY),SUM(UNR_EXPO_FIN), AVG(FWD_PRICE_PHY), AVG(FWD_PRICE_FIN) "
							+ "from FMS9_EOD_EXPOSURE_DTL "
							+ " where TO_DATE(REPORT_DT, 'DD-MON-YY')=TO_DATE(to_date('"+ReportDt+"','dd/mm/yyyy'), 'DD-MON-YY') "
							+ " AND RU_PHY_FLAG='U' "
							+ "AND TO_DATE(CONT_MTH, 'DD-MON-YY')=TO_DATE('"+ContMthFirstDt+"', 'DD-MON-YY') AND DEAL_ID='"+VQPDeal_NoTemp.elementAt(i)+"' AND FLAG='Y' "
							+ "AND PHYS_CURVE_NM='"+CurveNameBrkup+"' AND SEq_NO="+CountBreakUp+" ";
					//	if(VQPDeal_NoTemp.elementAt(i).equals("S34-1-0-2-1"))		System.out.println("PHYS: FMS9_EOD_EXPOSURE_DTL: "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						VCOB_Dt.add(ReportDt);
						VQPLegal_Entity.add("SEI");
						VQPDeal_No.add(VQPDeal_NoTemp.elementAt(i));	
						VQPCounter_Party.add(VQPCounter_PartyTemp.elementAt(i));
						VQPBuy_Sell.add(VQPBuy_SellTemp.elementAt(i));
					//	if(VQPCurve_NmTemp.elementAt(i).equals("RLNG_PHYS_INDIA") || VQPCurve_NmTemp.elementAt(i).equals("LNG_PHYS_INDIA") || !VQPPhys_Curve_NmTemp.elementAt(i).equals(""))
						///SB	VQPPrice_Type.add("Fixed");
							VQPPrice_Type.add(PriceTypeBrkup);
						VQPUnit.add("MMBTU");
						VQPCont_Mth.add(""+VQPCont_Mth_DDMONYYTemp.elementAt(j));
						/*Sb if(VQPPhys_Curve_NmTemp.elementAt(i).equals(""))
						{
						if(VQPBuy_SellTemp.elementAt(i).equals("Buy"))
							VQPCurve_Nm.add("LNG_PHYS_INDIA");
						else
							VQPCurve_Nm.add("RLNG_PHYS_INDIA");
						}
						else
							VQPCurve_Nm.add(VQPPhys_Curve_NmTemp.elementAt(i));*/
						VQPCurve_Nm.add(CurveNameBrkup);
						VQP_Exposure.add(rset.getString(1)==null?"0":rset.getString(1));
						VQP_PhyFin.add("Physical");
					//	VQP_Exposure.add(rset.getString(2)==null?"0":rset.getString(2));
						VQP_RelUnRel.add("Unrealised");
						VQP_FwdPrice.add(rset.getString(3)==null?"0":rset.getString(3));
					//	VQP_FwdPrice.add(rset.getString(4)==null?"0":rset.getString(4));
					}	
				}
				//////////////////////////////To filter the beyond Contract Month ///////////////	
					CountBreakUp=0; CurveNameBrkup=""; PriceTypeBrkup="";
					queryString = "SELECT DISTINCT CURVE_NM, (SEq_NO), (PRICE_TYPE) "+
							" from FMS9_EOD_EXPOSURE_DTL "+
							" where TO_DATE(REPORT_DT, 'DD-MON-YY')=TO_DATE(to_date('"+ReportDt+"','dd/mm/yyyy'), 'DD-MON-YY') AND FLAG='Y' AND DEAL_ID='"+VQPDeal_NoTemp.elementAt(i)+"' "
							+ "GROUP BY CURVE_NM, SEq_NO, PRICE_TYPE" ;					
					//	System.out.println(" DUAL: LAST: "+queryString);
					rset1 = stmt1.executeQuery(queryString);
					while(rset1.next()) {
						CurveNameBrkup = rset1.getString(1)==null?"":rset1.getString(1);
						CountBreakUp = rset1.getInt(2);
						PriceTypeBrkup = rset1.getString(3)==null?"":rset1.getString(3);
						//}
				//////////////////////////////////^^//////////////////////////////////////////////	
			//	for(int m=0; m<=CountBreakUp; m++)
			//	{
				//SB20210918	if(!VQPCurve_NmTemp.elementAt(i).equals("") )
					{
					///	if(!VQPCurve_NmTemp.elementAt(i).equals("RLNG_PHYS_INDIA") || !VQPCurve_NmTemp.elementAt(i).equals("LNG_PHYS_INDIA"))
				//Sb		{
						queryString = "SELECT SUM(UNR_EXPO_PHY),SUM(UNR_EXPO_FIN), AVG(FWD_PRICE_PHY), AVG(FWD_PRICE_FIN) "
								+ "from FMS9_EOD_EXPOSURE_DTL "
								+ " where TO_DATE(REPORT_DT, 'DD-MON-YY')=TO_DATE(to_date('"+ReportDt+"','dd/mm/yyyy'), 'DD-MON-YY') "
								+ " AND RU_FIN_FLAG='U' "
								+ "AND TO_DATE(CONT_MTH, 'DD-MON-YY')=TO_DATE('"+ContMthFirstDt+"', 'DD-MON-YY') AND DEAL_ID='"+VQPDeal_NoTemp.elementAt(i)+"' AND FLAG='Y' "
								+ "AND CURVE_NM='"+CurveNameBrkup+"' AND SEq_NO="+CountBreakUp+" ";
						//	System.out.println("FIN: FMS9_EOD_EXPOSURE_DTL: "+queryString);
						rset = stmt.executeQuery(queryString);
						if(rset.next())
						{
							VCOB_Dt.add(ReportDt);
							VQPLegal_Entity.add("SEI");
							VQPDeal_No.add(VQPDeal_NoTemp.elementAt(i));	
							VQPCounter_Party.add(VQPCounter_PartyTemp.elementAt(i));
							VQPBuy_Sell.add(VQPBuy_SellTemp.elementAt(i));
							VQPPrice_Type.add(PriceTypeBrkup);
							///SB	VQPPrice_Type.add("Float");
							VQPUnit.add("MMBTU");
							////	VQPCont_Mth.add(""+VQPCont_Mth_DDMONYYTemp.elementAt(j));
							///Sb   VQPCurve_Nm.add(VQPCurve_NmTemp.elementAt(i));
							VQPCurve_Nm.add(CurveNameBrkup);
							VQP_Exposure.add(rset.getString(2)==null?"0":rset.getString(2));
							VQP_PhyFin.add("Financial");
							VQP_RelUnRel.add("Unrealised");
							VQP_FwdPrice.add(rset.getString(4)==null?"0":rset.getString(4));		
					///Sb	}
						String queryString2 = "SELECT TO_CHAR(TO_DATE(INDEX_SETTLE_DT, 'DD-MON-YY'),'DD/MM/YYYY') "
								+ "from FMS9_EOD_EXPOSURE_DTL "
								+ " where TO_DATE(REPORT_DT, 'DD-MON-YY')=TO_DATE(to_date('"+ReportDt+"','dd/mm/yyyy'), 'DD-MON-YY') "
						//SB20210918		+ " AND RU_FIN_FLAG IN ('U','R') "
								+ " AND RU_FIN_FLAG IN ('U') "
								+ "AND TO_DATE(CONT_MTH, 'DD-MON-YY')=TO_DATE('"+ContMthFirstDt+"', 'DD-MON-YY') AND DEAL_ID='"+VQPDeal_NoTemp.elementAt(i)+"' AND FLAG='Y' "
								+ "AND CURVE_NM='"+CurveNameBrkup+"' AND SEq_NO="+CountBreakUp+" ";
						//		System.out.println("FIN: FMS9_EOD_EXPOSURE_DTL: "+queryString2);
						rset2 = stmt2.executeQuery(queryString2);
						if(rset2.next())
						{
							String TempDate=rset2.getString(1)==null?"":rset2.getString(1);
							String tempmap_id[]=TempDate.split("/"); //HARSH20210421
							TempDate=""+tempmap_id[1]+"/"+tempmap_id[2]; //HARSH20210421 
							VQPCont_Mth.add(TempDate);
						}
						else
							VQPCont_Mth.add(""+VQPCont_Mth_DDMONYYTemp.elementAt(j));
					}
					}
				}
			}
		}
		System.out.println(VQPCont_Mth.size()+" :Extracted from FMS9_EOD_EXPOSURE_DTL: No. of Records: ");
		/*	System.out.println(VQPCont_Mth.size()+" :Extracted from FMS9_EOD_EXPOSURE_DTL: No. of Records: "+VQPCont_Mth);
		    System.out.println(VCOB_Dt.size()+" :Extracted from FMS9_EOD_EXPOSURE_DTL: No. of Records: "+VCOB_Dt);
			System.out.println(VQPDeal_No.size()+" :Extracted from FMS9_EOD_EXPOSURE_DTL: No. of Records: "+VQPDeal_No);
			System.out.println(VQPBuy_Sell.size()+" :Extracted from FMS9_EOD_EXPOSURE_DTL: No. of Records: "+VQPBuy_Sell);
			System.out.println(VQPCurve_Nm.size()+" :Extracted from FMS9_EOD_EXPOSURE_DTL: No. of Records: "+VQPCurve_Nm);
			System.out.println(VQP_Exposure.size()+" :Extracted from FMS9_EOD_EXPOSURE_DTL: No. of Records: "+VQP_Exposure);
			
			System.out.println(VQPLegal_Entity.size()+" :VQPLegal_Entity: No. of Records: "+VQPLegal_Entity);
			System.out.println(VQPPrice_Type.size()+" :VQPPrice_Type: No. of Records: "+VQPPrice_Type);
			System.out.println(VQP_PhyFin.size()+" :VQP_PhyFin: No. of Records: "+VQP_PhyFin);
			System.out.println(VQP_RelUnRel.size()+" :VQP_RelUnRel: No. of Records: "+VQP_RelUnRel);
			System.out.println(VQP_FwdPrice.size()+" :VQP_FwdPrice: No. of Records: "+VQP_FwdPrice);
			*/
		}
		else
		System.out.println("NOT AVAILABLE in FMS9_EOD_EXPOSURE_DTL for Report Date: "+ReportDt);
		
		
		//HARSH20210701 STORAGE VALUE GETTING 0 FOR REPORT DT 30/06/2021
		queryString="SELECT TO_CHAR(DELV_DT,'DD/MM/YYYY') FROM FMS9_EOD_EXPOSURE_DTL "
				+ "WHERE DEAL_ID='O0-0-0-0-0' AND REPORT_DT=TO_DATE('"+to_date+"','DD/MM/YYYY') AND CUSTOMER_CD='0'";
		rset=stmt.executeQuery(queryString);
		if(rset.next())
		{
			storage_delv_dt=rset.getString(1)==null?to_date:rset.getString(1);
		}
		else
		{
			storage_delv_dt=to_date;
		}
		System.out.println("storage_delv_dt :: "+storage_delv_dt);
		//////////////////////////////////////////////////////////////////////
	}
	catch(Exception e)
	{
	//System.out.println("Exception in Databean_Contract_MasterReport--->fetchcustomer()-->"+e);
	e.printStackTrace();	
	}
}
public void ExposureDealWiseFreezeDtlViewForQP_PriceV2AllSettlePrice(String ReportDt)
{
	try
	{
		ReportDt=to_date; 
		String tempReportDt[]=ReportDt.split("/");
		String GenMth=tempReportDt[1]; String GenYr=tempReportDt[2]; 
		String GenMthYr=GenMth+"/"+GenYr;
		String CashMthFirstDt="01/"+GenMthYr; 
		String ContSettleType="J";
		String ContMthCurveNm="";
		String ContMthCurveId="";
		
		String user_cd=Emp_cd;
		int Count=0;
		Vector VReportDtTemp = new Vector(); //SB20210429
		/////// //SB20210429//////////////Find Last 80 days date////////////////////////////////////////////
		int PrevCountDays = 0;		int TotalCountDays = 0;		
		String WeeklyOff="";String PastDate="";
		////////////////////////////////Start Date of Pricing/////////////////
		int NoOfDays=0;
		String query="select to_date('"+ReportDt+"','DD/MM/YYYY')- to_date('01/01/2021','DD/MM/YYYY') from dual";
		//	System.out.println("Date Difference: dual: "+query);
			rset=stmt.executeQuery(query);
			if(rset.next())
			{
				NoOfDays=rset.getInt(1);
			}
		////////////////////////////////Start Date of Pricing/////////////////
		for(int i=NoOfDays;i>=0;i--)
		{			
					 String query1="select to_char(to_date('"+ReportDt+"','dd/mm/yyyy')-"+i+",'dd/mm/yyyy') from dual";
					// System.out.println("FMS9_FORWARD_PRICE_DTL: "+query1);
						rset=stmt.executeQuery(query1);
						if(rset.next())
						{								
							PrevCountDays++;
							PastDate=rset.getString(1)==null?"":rset.getString(1);
						}
						queryString = "SELECT TO_CHAR(TO_DATE('"+PastDate+"','DD/MM/YYYY'),'DAY') FROM DUAL";
						//	System.out.println("Get DAY Name: "+queryString);
							rset = stmt.executeQuery(queryString);
							if(rset.next())
							{
								WeeklyOff = rset.getString(1)==null?"":rset.getString(1);	
								WeeklyOff =WeeklyOff.trim();
							 if(!WeeklyOff.trim().toUpperCase().equalsIgnoreCase("SATURDAY") && !WeeklyOff.trim().toUpperCase().equalsIgnoreCase("SUNDAY"))
							 {
								 VReportDtTemp.add(PastDate);
								 TotalCountDays++;
							 }
						 }	
		//	if(TotalCountDays>=80) break;
		}
		//	System.out.println(VReportDtTemp.size()+" :FIN: FMS9_EOD_EXPOSURE_DTL: "+VReportDtTemp);
		///////////////////////////////////////////////////////////////////////////////////////
		queryString = "SELECT DISTINCT CURVE_NM FROM FMS9_SETTLE_CALND_DTL WHERE FLAG='Y' ORDER BY CURVE_NM "; //HARSH20210717 ONE MORE CONDITION ADDED SETTLE_TYPE IN ('J','B')
		//	System.out.println("FMS9_SETTLE_CALND_DTL: "+queryString);
		rset2 = stmt2.executeQuery(queryString);
		while(rset2.next())
		{
		ContMthCurveNm = rset2.getString(1);
		ContMthCurveNm=ContMthCurveNm.trim();
		
		/*SB20210901 ContSettleType = rset2.getString(1);
		ContSettleType=ContSettleType.trim();
		System.out.println(ContSettleType+ ": in FMS9_SETTLE_CALND_DTL for Report Date: "+ReportDt);
		if(ContSettleType.trim().equals("J"))
		{
		ContMthCurveNm="ICE_JKM";
		ContMthCurveId="J01";
		}
		else if(ContSettleType.trim().equals("B"))
		{
		ContMthCurveNm="ICE_BRENT";
		ContMthCurveId="B01";
		}*/
		
		for(int j=0; j<VReportDtTemp.size();j++)  //SB20210429
		{	
			int count = 0; //HARSH20210419	
			PastDate=""+VReportDtTemp.elementAt(j);  //SB20210429
			String queryString="SELECT TO_CHAR(TO_DATE(A.ENT_DT, 'DD-MON-YY'),'DD/MM/YYYY'), TO_CHAR(B.SETTLE_DT,'DD/MM/YYYY'), TO_CHAR(A.CURVE_DD_MM_YR,'DD/MM/YYYY'), SETTLE_PRICE, UPPER(A.CURVE_UNIT) "+ //HARSH20210423 CURVE_UNIT ADDED
			" FROM FMS9_FORWARD2_PRICE_DTL A, FMS9_SETTLE_CALND_DTL B "+
			//SB20210901	" WHERE A.CURVE_DD_MM_YR=B.CONT_MM_YYYY AND TO_DATE(A.ENT_DT, 'DD-MON-YY')=TO_DATE(to_date('"+PastDate+"','dd/mm/yyyy'), 'DD-MON-YY') AND SETTLE_TYPE='"+ContSettleType+"' AND A.CURVE_NM='"+ContMthCurveNm+"' AND A.CURVE_NM=B.CURVE_NM AND A.FLAG='Y'"+ //SB20210430 //SB20210429 //HARSH20210717 ONE MORE CONDITION ADDED A.CURVE_NM=B.CURVE_NM
			" WHERE A.CURVE_DD_MM_YR=B.CONT_MM_YYYY AND TO_DATE(A.ENT_DT, 'DD-MON-YY')=TO_DATE(to_date('"+PastDate+"','dd/mm/yyyy'), 'DD-MON-YY') AND A.CURVE_NM='"+ContMthCurveNm+"' AND A.CURVE_NM=B.CURVE_NM AND A.FLAG='Y'"+ //SB20210430 //SB20210429 //HARSH20210717 ONE MORE CONDITION ADDED A.CURVE_NM=B.CURVE_NM
			" ORDER BY A.CURVE_DD_MM_YR ";
			//	System.out.println("FMS9_FORWARD_PRICE_DTL: "+queryString);
			//	System.out.println("FIN: FMS9_EOD_EXPOSURE_DTL: "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				/*count=count+1; //HARSH20210419
				if(count == 81) //HARSH20210419
				{
				break;
				}
				else*/
				{
				VQPPrice_ReportDt.add(rset.getString(1)==null?"":rset.getString(1));
					VQPPrice_CurveNm.add(ContMthCurveNm);
					VQPPrice_CurveId.add(ContMthCurveId);								
				VQPPrice_SettleDt.add(rset.getString(2)==null?"":rset.getString(2));
				VQPPrice_ContMth.add(rset.getString(3)==null?"":rset.getString(3));
				VQPPrice_FwdPrice.add(rset.getString(4)==null?"0":rset.getString(4));
				VQPPrice_Currency.add("USD");
				String tempUnit[]=rset.getString(5).split("/");  //SB20210429
				if(tempUnit.length>1)  //SB20210429
					VQPPrice_Curve_Unit.add(tempUnit[1]);  //SB20210429 //HARSH20210423 FETCH FROM TABLE and filter
				else  //SB20210429
					VQPPrice_Curve_Unit.add(rset.getString(5)==null?"":rset.getString(5)); //HARSH20210423 FETCH FROM TABLE
				}
			}	
		}//EoFor
		}		
		
		Vector ContMthCurveNmTemp = new Vector();
		String FirstDateContMthYr=""; int code=0;
		//SB20210901	queryString = "SELECT DISTINCT CURVE_NM FROM FMS9_FORWARD2_PRICE_DTL WHERE PHYS_FIN='Physical' AND FLAG='Y' ORDER BY CURVE_NM";
		queryString = "SELECT DISTINCT CURVE_NM FROM FMS9_FORWARD2_PRICE_DTL WHERE UPPER(PHYS_FIN)='PHYSICAL' AND FLAG='Y' ORDER BY CURVE_NM";
		//	System.out.println("FMS9_SETTLE_CALND_DTL: "+queryString);
		rset2 = stmt2.executeQuery(queryString);
		while(rset2.next())
		{
		ContMthCurveNmTemp.add(rset2.getString(1)==null?"":rset2.getString(1));
		}
		for(int i=0; i<ContMthCurveNmTemp.size(); i++)
		{
		code++; //SB20210429
		//int PrevCountDays = 0; String PastDate=""; //SB20210429
		for(int j=0; j<VReportDtTemp.size();j++) //SB20210429
		{					
		int count=0; //HARSH20210419
		PastDate=""+VReportDtTemp.elementAt(j); //SB20210429
		ContMthCurveNm = ""+ContMthCurveNmTemp.elementAt(i);
		
		ContMthCurveId="F0"+code;
		String queryString="SELECT TO_CHAR(TO_DATE(ENT_DT, 'DD-MON-YY'),'DD/MM/YYYY'), TO_CHAR(CURVE_DD_MM_YR,'DD/MM/YYYY'), TO_CHAR(CURVE_DD_MM_YR,'DD/MM/YYYY'), SETTLE_PRICE,UPPER(CURVE_UNIT) "+ //HARSH20210423 CURVE_UNIT ADDED
		" FROM FMS9_FORWARD2_PRICE_DTL "+
		//SB20210429		" WHERE ENT_DT=TO_DATE('"+ReportDt+"','DD/MM/YYYY') AND CURVE_TYPE='Forward' AND CURVE_NM='"+ContMthCurveNm+"' AND FLAG='Y'"+ 
		//SB20210430		" WHERE ENT_DT=TO_DATE('"+PastDate+"','DD/MM/YYYY') AND CURVE_TYPE='Forward' AND CURVE_NM='"+ContMthCurveNm+"' AND FLAG='Y'"+ //SB20210429
			" WHERE TO_DATE(ENT_DT, 'DD-MON-YY')=TO_DATE(to_date('"+PastDate+"','dd/mm/yyyy'), 'DD-MON-YY') AND CURVE_NM='"+ContMthCurveNm+"' AND FLAG='Y'"+ //SB20210430 //SB20210429
			" ORDER BY CURVE_DD_MM_YR ";
		//	System.out.println("FMS9_FORWARD_PRICE_DTL: "+queryString);
		//	System.out.println("FIN: FMS9_EOD_EXPOSURE_DTL: "+queryString);
		rset = stmt.executeQuery(queryString);
		while(rset.next())
		{
		/*count = count+1; //HARSH20210419
		if(count == 81) //HARSH20210419
		{
		break;
		}
		else*/
		{
		VQPPrice_ReportDt.add(rset.getString(1)==null?"":rset.getString(1));
		VQPPrice_CurveNm.add(ContMthCurveNm);
		VQPPrice_CurveId.add(ContMthCurveId);		
		FirstDateContMthYr=rset.getString(2)==null?"":rset.getString(2);
		String queryString2 = "SELECT TO_CHAR(LAST_DAY(TO_DATE('"+FirstDateContMthYr+"','DD/MM/YYYY')),'DD/MM/YYYY') FROM DUAL";
		//	System.out.println("Query for generating Last Date of the selected Month = "+queryString);
			rset2 = stmt2.executeQuery(queryString2);
			if(rset2.next())
			{
				VQPPrice_SettleDt.add(rset2.getString(1)==null?"":rset2.getString(1));
			}
		//VQPPrice_SettleDt.add(rset.getString(2)==null?"":rset.getString(2));
		VQPPrice_ContMth.add(rset.getString(3)==null?"":rset.getString(3));
		VQPPrice_FwdPrice.add(rset.getString(4)==null?"0":rset.getString(4));
		VQPPrice_Currency.add("USD");
		String tempUnit[]=rset.getString(5).split("/");  //SB20210429
		if(tempUnit.length>1)  //SB20210429
			VQPPrice_Curve_Unit.add(tempUnit[1]);  //SB20210429//HARSH20210423 FETCH FROM TABLE and filter
		else  //SB20210429
			VQPPrice_Curve_Unit.add(rset.getString(5)==null?"":rset.getString(5)); //HARSH20210423 FETCH FROM TABLE
		}
		}	
		}//EoFor				
		}
		/*System.out.println(VQPPrice_ReportDt.size()+" :Extracted from FMS9_FORWARD2_PRICE_DTL: No. of Records: "+VQPPrice_ReportDt);
		System.out.println(VQPPrice_CurveNm.size()+" :Extracted from FMS9_FORWARD2_PRICE_DTL: No. of Records: "+VQPPrice_CurveNm);
		System.out.println(VQPPrice_CurveId.size()+" :Extracted from FMS9_FORWARD2_PRICE_DTL: No. of Records: "+VQPPrice_CurveId);
		System.out.println(VQPPrice_SettleDt.size()+" :Extracted from FMS9_FORWARD2_PRICE_DTL: No. of Records: "+VQPPrice_SettleDt);
		System.out.println(VQPPrice_ContMth.size()+" :Extracted from FMS9_FORWARD2_PRICE_DTL: No. of Records: "+VQPPrice_ContMth);
		
		System.out.println(VQPPrice_FwdPrice.size()+" :VQPPrice_FwdPrice: No. of Records: "+VQPPrice_FwdPrice);
		System.out.println(VQPPrice_Currency.size()+" :VQPPrice_Currency: No. of Records: "+VQPPrice_Currency);
		System.out.println(VQPPrice_Curve_Unit.size()+" :VQPPrice_Curve_Unit: No. of Records: "+VQPPrice_Curve_Unit);*/
		System.out.println(">>>>>>>>> Not in Use:End Of QP Pricing(100/80 Days): No. of Records: "+VQPPrice_FwdPrice.size());	
	}
	catch(Exception e)
	{
	//System.out.println("Exception in Databean_Contract_MasterReport--->fetchcustomer()-->"+e);
	e.printStackTrace();	
	}
}
Vector VQPPrice_ReportDt = new Vector(); //SB20201209
public Vector getVQPPrice_ReportDt() {return VQPPrice_ReportDt;} //SB20210304
Vector VQPPrice_CurveNm = new Vector(); //SB20201209
public Vector getVQPPrice_CurveNm() {return VQPPrice_CurveNm;} //SB20210304
Vector VQPPrice_CurveId = new Vector(); //SB20201209
public Vector getVQPPrice_CurveId() {return VQPPrice_CurveId;} //SB20210304
Vector VQPPrice_SettleDt = new Vector(); //SB20201209
public Vector getVQPPrice_SettleDt() {return VQPPrice_SettleDt;} //SB20210304
Vector VQPPrice_ContMth = new Vector(); //SB20201209
public Vector getVQPPrice_ContMth() {return VQPPrice_ContMth;} //SB20210304
Vector VQPPrice_FwdPrice = new Vector(); //SB20201209
public Vector getVQPPrice_FwdPrice() {return VQPPrice_FwdPrice;} //SB20210304
Vector VQPPrice_Currency = new Vector(); //SB20201209
public Vector getVQPPrice_Currency() {return VQPPrice_Currency;} //SB20210304
Vector VQPPrice_Curve_Unit = new Vector(); //SB20201209
public Vector getVQPPrice_Curve_Unit() {return VQPPrice_Curve_Unit;} //SB20210304
///////////////////////^^^SB20210329////////////////////////////////////////
String storage_delv_dt=""; //HARSH20210701
public String getstorage_delv_dt() {return storage_delv_dt;}//HARSH20210701
Vector VCOB_Dt = new Vector(); //SB20201209
public Vector getVCOB_Dt() {return VCOB_Dt;} //SB20210304
Vector VQPLegal_Entity = new Vector(); //SB20201209
public Vector getVQPLegal_Entity() {return VQPLegal_Entity;} //SB20210304
Vector VQPDeal_No = new Vector(); //SB20201209
public Vector getVQPDeal_No() {return VQPDeal_No;} //SB20210304
Vector VQPCounter_Party = new Vector(); //SB20201209
public Vector getVQPCounter_Party() {return VQPCounter_Party;} //SB20210304
Vector VQPBuy_Sell = new Vector(); //SB20201209
public Vector getVQPBuy_Sell() {return VQPBuy_Sell;} //SB20210304
Vector VQPPrice_Type = new Vector(); //SB20201209
public Vector getVQPPrice_Type() {return VQPPrice_Type;} //SB20210304
Vector VQPUnit = new Vector(); //SB20201209
public Vector getVQPUnit() {return VQPUnit;} //SB20210304
Vector VQPCont_Mth = new Vector(); //SB20201209
public Vector getVQPCont_Mth() {return VQPCont_Mth;} //SB20210304
Vector VQPCurve_Nm = new Vector(); //SB20201209
public Vector getVQPCurve_Nm() {return VQPCurve_Nm;} //SB20210304
Vector VQP_Exposure = new Vector(); //SB20201209
public Vector getVQP_Exposure() {return VQP_Exposure;} //SB20210304
Vector VQP_PhyFin = new Vector(); //SB20201209
public Vector getVQP_PhyFin() {return VQP_PhyFin;} //SB20210304
Vector VQP_RelUnRel = new Vector(); //SB20201209
public Vector getVQP_RelUnRel() {return VQP_RelUnRel;} //SB20210304
Vector VQP_FwdPrice = new Vector(); //SB20201209
public Vector getVQP_FwdPrice() {return VQP_FwdPrice;} //SB20210304
//////////////////////^^^^^^^SB2021020////////////////////////////////////////////////////////
///////^^^^^^^^^^66////////////////////////////////////////////////
String FinPriceFormula="";
public String getFinPriceFormula() {return FinPriceFormula;}
Vector VDlv_Dt = new Vector();
public Vector getVDlv_Dt() {return VDlv_Dt;}
Vector VDcq = new Vector();
public Vector getVDcq() {return VDcq;}
Vector VFixedFloat_Type = new Vector();
public Vector getVFixedFloat_Type() {return VFixedFloat_Type;}
Vector VContFixedPrice = new Vector();
public Vector getVContFixedPrice() {return VContFixedPrice;}
Vector VActualQty = new Vector();
public Vector getVActualQty() {return VActualQty;}
Vector VMutiCurveNm = new Vector();
public Vector getVMutiCurveNm() {return VMutiCurveNm;}


Vector VFin_ORI_EXPO_BrkUp = new Vector();
public Vector getVFin_ORI_EXPO_BrkUp() {return VFin_ORI_EXPO_BrkUp;}
Vector VPhy_ORI_EXPO_BrkUp = new Vector();
public Vector getVPhy_ORI_EXPO_BrkUp() {return VPhy_ORI_EXPO_BrkUp;}
Vector VFin_EXPO_ContMth = new Vector();
public Vector getVFin_EXPO_ContMth() {return VFin_EXPO_ContMth;}
Vector VPhy_EXPO_ContMth = new Vector();
public Vector getVPhy_EXPO_ContMth() {return VPhy_EXPO_ContMth;}
Vector VFin_SettleStDt = new Vector();
public Vector getVFin_SettleStDt() {return VFin_SettleStDt;}
Vector VFin_SettleEndDt = new Vector();
public Vector getVFin_SettleEndDt() {return VFin_SettleEndDt;}
Vector VFin_EffSettlePrice = new Vector();
public Vector getVFin_EffSettlePrice() {return VFin_EffSettlePrice;}
Vector VFin_SettlePrice = new Vector();
public Vector getVFin_SettlePrice() {return VFin_SettlePrice;}
Vector VFin_Slope = new Vector();
public Vector getVFin_Slope() {return VFin_Slope;}
Vector VFin_Constant = new Vector();
public Vector getVFin_Constant() {return VFin_Constant;}
Vector VPhy_RU_flag = new Vector();
public Vector getVPhy_RU_flag() {return VPhy_RU_flag;}
Vector VFin_RU_flag = new Vector();
public Vector getVFin_RU_flag() {return VFin_RU_flag;}
Vector VPhy_Expo_U = new Vector();
public Vector getVPhy_Expo_U() {return VPhy_Expo_U;}
Vector VFin_Expo_U = new Vector();
public Vector getVFin_Expo_U() {return VFin_Expo_U;}
Vector VPhy_Expo_R = new Vector();
public Vector getVPhy_Expo_R() {return VPhy_Expo_R;}
Vector VFin_Expo_R = new Vector();
public Vector getVFin_Expo_R() {return VFin_Expo_R;}
Vector VFin_FwdPrice = new Vector();
public Vector getVFin_FwdPrice() {return VFin_FwdPrice;}
Vector VPhy_FwdPrice = new Vector();
public Vector getVPhy_FwdPrice() {return VPhy_FwdPrice;} 
Vector VPhy_U_Leg = new Vector();
public Vector getVPhy_U_Leg() {return VPhy_U_Leg;}
Vector VFin_U_Leg = new Vector();
public Vector getVFin_U_Leg() {return VFin_U_Leg;}
Vector VFin_R_Leg = new Vector();
public Vector getVFin_R_Leg() {return VFin_R_Leg;}
Vector VTotalPnL = new Vector();
public Vector getVTotalPnL() {return VTotalPnL;}
Vector VRowCol_Colour = new Vector();
public Vector getVRowCol_Colour() {return VRowCol_Colour;}

Vector VDlv_Dt_HP = new Vector(); //HARSH2021901
public Vector getVDlv_Dt_HP() {return VDlv_Dt_HP;}//HARSH2021901
Vector VDcq_HP = new Vector();//HARSH2021901
public Vector getVDcq_HP() {return VDcq_HP;}//HARSH2021901
Vector Vcust_Cd = new Vector();//HARSH2021901
public Vector getVcust_Cd() {return Vcust_Cd;}//HARSH2021901
Vector SEQ_NO = new Vector();//HARSH2021901
public Vector getSEQ_NO() {return SEQ_NO;}//HARSH2021901
/////////////////////////^^^SB20210710: BreakUp/////////////////////////////////////////////
	public Vector VExpoFreezeeStatus = new Vector();
	public Vector getVExpoFreezeeStatus() {return VExpoFreezeeStatus;} //SB20210201
	
	public Vector VDealType = new Vector();
	public Vector getVDealType() {return VDealType;} //SB20210201
	public Vector VStoreTank1 = new Vector();
	public Vector getVStoreTank1() {return VStoreTank1;} //SB20210201
	public Vector VStoreTankTotal = new Vector();
	public Vector getVStoreTankTotal() {return VStoreTankTotal;} //SB20210201
	public Vector VStoreTPTotal = new Vector();
	public Vector getVStoreTPTotal() {return VStoreTPTotal;} //SB20210201
	public Vector VStoreTPCustCd = new Vector();
	public Vector getVStoreTPCustCd() {return VStoreTPCustCd;} //SB20210201
	public Vector VStoreTPCustNm = new Vector();
	public Vector getVStoreTPCustNm() {return VStoreTPCustNm;} //SB20210201
	public Vector VStoreTPCustMmscm = new Vector();
	public Vector getVStoreTPCustMmscm() {return VStoreTPCustMmscm;} //SB20210201
	public Vector VStoreTPTotalmmscm = new Vector();
	public Vector getVStoreTPTotalmmscm() {return VStoreTPTotalmmscm;} //SB20210201
	public Vector VStoreTPSuppliedmmscm = new Vector();
	public Vector getVStoreTPSuppliedmmscm() {return VStoreTPSuppliedmmscm;} //SB20210201
	public Vector VStoreTPBalancemmscm = new Vector();
	public Vector getVStoreTPBalancemmscm() {return VStoreTPBalancemmscm;} //SB20210201
	public Vector VStoreTank2 = new Vector();
	public Vector getVStoreTank2() {return VStoreTank2;} //SB20210201
	public Vector VStoreSEIPL = new Vector();
	public Vector getVStoreSEIPL() {return VStoreSEIPL;} //SB20210201
	public Vector VStoreSEIPLmmscm = new Vector();
	public Vector getVStoreSEIPLmmscm() {return VStoreSEIPLmmscm;} //SB20210201
	
	String InventoryDt="";
	public String getInventoryDt() {return InventoryDt;}
	
	public Vector VStoreTPCustMmbtuEDQ = new Vector();
	public Vector getVStoreTPCustMmbtuEDQ() {return VStoreTPCustMmbtuEDQ;} //SB20210614
	public Vector VStoreTPCustMmbtuADQ = new Vector();
	public Vector getVStoreTPCustMmbtuADQ() {return VStoreTPCustMmbtuADQ;} //SB20210614
	public Vector VStoreTPCustMmbtuADQ_DT = new Vector();
	public Vector getVStoreTPCustMmbtuADQ_DT() {return VStoreTPCustMmbtuADQ_DT;} //SB20210614
	public Vector VStoreTPCustMmbtuEDQ_ST_DT = new Vector();
	public Vector getVStoreTPCustMmbtuEDQ_ST_DT() {return VStoreTPCustMmbtuEDQ_ST_DT;} //SB20210614
	public Vector VStoreTPCustMmbtuEDQ_END_DT = new Vector();
	public Vector getVStoreTPCustMmbtuEDQ_END_DT() {return VStoreTPCustMmbtuEDQ_END_DT;} //SB20210614
	public Vector VStoreTPCustMmbtuSuppliedRptDt = new Vector();
	public Vector getVStoreTPCustMmbtuSuppliedRptDt() {return VStoreTPCustMmbtuSuppliedRptDt;} //SB20210614
	public Vector VStoreTPCustMmbtuCONT_ST_DT = new Vector();
	public Vector getVStoreTPCustMmbtuCONT_ST_DT() {return VStoreTPCustMmbtuCONT_ST_DT;} //SB20210614
	public Vector VStoreTPCustMmbtuCONT_END_DT = new Vector();
	public Vector getVStoreTPCustMmbtuCONT_END_DT() {return VStoreTPCustMmbtuCONT_END_DT;} //SB20210614
	
	////////////////////////^^^^^SB20210201/////////////////////////////////////////////////
	public Vector Vsn_DCQ_seq_no = new Vector();
	public Vector getVsn_DCQ_seq_no() {return Vsn_DCQ_seq_no;} //HARSH20201201
	//////////////////////^^///////////////////////////////////////////////////////////
	public Vector sn_Dcq_From_Dt = new Vector();
	public Vector sn_Dcq_To_Dt = new Vector();
	public Vector sn_Dcq_Value = new Vector();
	public Vector sn_Dcq_Remark = new Vector();
	public Vector getSn_Dcq_From_Dt() {return sn_Dcq_From_Dt;}
	public Vector getSn_Dcq_To_Dt() {return sn_Dcq_To_Dt;}
	public Vector getSn_Dcq_Value() {return sn_Dcq_Value;}
	public Vector getSn_Dcq_Remark() {return sn_Dcq_Remark;}
	
	public Vector VConst = new Vector();
	public Vector VPrice_Type = new Vector();
	public Vector VCurve_Nm = new Vector();
	public Vector VRate = new Vector();
	public Vector VRate_Unit = new Vector();
	public Vector getVConst() {return VConst;}
	public Vector getVPrice_Type() {return VPrice_Type;}
	public Vector getVCurve_Nm() {return VCurve_Nm;}
	public Vector getVRate() {return VRate;}
	public Vector getVRate_Unit() {return VRate_Unit;}
	public Vector VPrice_Range = new Vector();
	public Vector getVPrice_Range() {return VPrice_Range;}
	public Vector VPrice_Start_Dt = new Vector();
	public Vector getVPrice_Start_Dt() {return VPrice_Start_Dt;}
	public Vector VPrice_End_Dt = new Vector();
	public Vector getVPrice_End_Dt() {return VPrice_End_Dt;}
	public Vector VPhys_Curve_Nm = new Vector(); //SB20210310
	public Vector getVPhys_Curve_Nm() {return VPhys_Curve_Nm;} //SB20210310
	
	//////////////////////////////////////////////////
	Vector VContType = new Vector(); //SB20201108
	public Vector getVContType() {return VContType;} //SB20201108
	String Cont_Type=""; //SB20201123
	public void setContType(String Cont_Type) {this.Cont_Type = Cont_Type;} //SB20201123
	String DealCustCd="";
	public void setDealCustCd(String DealCustCd) {this.DealCustCd = DealCustCd;}
	String FgsaNo="";
	public void setFgsaNo(String FgsaNo) {this.FgsaNo = FgsaNo;}
	String FgsaRevNo="";
	public void setFgsaRevNo(String FgsaRevNo) {this.FgsaRevNo = FgsaRevNo;}
	String SnNo="";
	public void setSnNo(String SnNo) {this.SnNo = SnNo;}
	String SnRevNo="";
	public void setSnRevNo(String SnRevNo) {this.SnRevNo = SnRevNo;}
	String GenDt="";
	public void setGenDt(String GenDt) {this.GenDt = GenDt;}
	String priceSettle="";
	public void setpriceSettle(String priceSettle) {this.priceSettle = priceSettle;}
	/////////////////////////////////////////////////////////
	String PrvCurvePrice="0"; //Used for JKM
	String PriceIndex_SettleDate=""; //Used for JKM
	String PriceIndex_StartDate=""; //Used for JKM
	String PriceIndex_EndDate=""; //Used for JKM 
	String EffPriceSlopeConst=""; //Used for JKM
	String PriceIndexName="";//SB20201025: to be used for Report Heading
	public String getPriceIndexName() {return PriceIndexName;} //SB20201025: to be used for Report Heading
	String FrdPriceEntDt="";//SB20201025: to be used for Report Heading
	public String getFrdPriceEntDt() {return FrdPriceEntDt;} //SB20201025: to be used for Report Heading
	String PricePHYSName="";//SB20201025: to be used for Report Heading
	public String getPricePHYSName() {return PricePHYSName;} //SB20210228: to be used for Report Heading
	String PPACPriceFlag="";//SB20201025: to be used for Report Heading
	public String getPPACPriceFlag() {return PPACPriceFlag;} //SB20210228: to be used for RIL Deal(Gas_KGD6)
/////PFinancial Reduction///////////SB20201006	
	String FinancialCumalativePerc="100"; //Used for JKM
	String CumalativePerc="0"; //Used for JKM
	Vector VEff_Perc = new Vector();
	public Vector getVEff_Perc() {return VEff_Perc;}
	Vector VEff_CumulativePerc = new Vector();
	public Vector getVEff_CumulativePerc() {return VEff_CumulativePerc;}
	Vector VWeekDayHoliDay = new Vector();
	public Vector getVWeekDayHoliDay() {return VWeekDayHoliDay;}
	Vector VWeekDayHoliDayFlag = new Vector();
	public Vector getVWeekDayHoliDayFlag() {return VWeekDayHoliDayFlag;}
////////////////////////////////////////////////	
	Vector VCont_Start_Dt = new Vector(); //SB20200901 Not in Use
	public Vector getVCont_Start_Dt() {return VCont_Start_Dt;}
	Vector VCont_End_Dt = new Vector(); //SB20200901 Not in Use
	public Vector getVCont_End_Dt() {return VCont_End_Dt;}
	Vector VMthYrWise = new Vector();
	public Vector getVMthYrWise() {return VMthYrWise;}
	Vector VPriceIndex_SettleDate = new Vector();
	public Vector getVPriceIndex_SettleDate() {return VPriceIndex_SettleDate;}
	Vector VPriceIndex_StartDate = new Vector();
	public Vector getVPriceIndex_StartDate() {return VPriceIndex_StartDate;}
	Vector VPriceIndex_EndDate = new Vector();
	public Vector getVPriceIndex_EndDate() {return VPriceIndex_EndDate;}
	Vector VEff_Price = new Vector();
	public Vector getVEff_Price() {return VEff_Price;}
	Vector VMthYrWisePriceICE_JKM = new Vector();
	public Vector getVMthYrWisePriceICE_JKM() {return VMthYrWisePriceICE_JKM;}
	Vector VBuySell = new Vector();
	public Vector getVBuySell() {return VBuySell;}
	Vector VPriceType = new Vector();
	public Vector getVPriceType() {return VPriceType;}
	Vector VPriceTypeDealRate = new Vector();
	public Vector getVPriceTypeDealRate() {return VPriceTypeDealRate;}
	Vector VPriceTypeDeal = new Vector();
	public Vector getVPriceTypeDeal() {return VPriceTypeDeal;}
	Vector VPriceTypeDealSeQNo = new Vector();
	public Vector getVPriceTypeDealSeQNo() {return VPriceTypeDealSeQNo;}
	Vector VDealPriceCurve = new Vector();
	public Vector getVDealPriceCurve() {return VDealPriceCurve;}
	Vector VDealPhysCurve = new Vector();
	public Vector getVDealPhysCurve() {return VDealPhysCurve;}
	Vector VDealTCQ = new Vector();
	public Vector getVDealTCQ() {return VDealTCQ;}
	Vector VDealDCQ = new Vector();
	public Vector getVDealDCQ() {return VDealDCQ;}
	Vector VDealDCQFlag = new Vector();
	public Vector getVDealDCQFlag() {return VDealDCQFlag;}
	Vector VDealAllocQty = new Vector();
	public Vector getVDealAllocQty() {return VDealAllocQty;}
	Vector VRLNG_PHYS_IN_EXPO = new Vector();
	public Vector getVRLNG_PHYS_IN_EXPO() {return VRLNG_PHYS_IN_EXPO;}
	Vector VLNG_ICE_JKM_EXPO = new Vector();
	public Vector getVLNG_ICE_JKM_EXPO() {return VLNG_ICE_JKM_EXPO;}
	Vector VLNG_ICE_JKM_EXPOBaseValue = new Vector();
	public Vector getVLNG_ICE_JKM_EXPOBaseValue() {return VLNG_ICE_JKM_EXPOBaseValue;}
	Vector VLNG_ICE_JKM_EXPODropOff = new Vector();
	public Vector getVLNG_ICE_JKM_EXPODropOff() {return VLNG_ICE_JKM_EXPODropOff;}
	Vector VCargoRefNo = new Vector();
	public Vector getVCargoRefNo() {return VCargoRefNo;}
	////////////////////Cumulative Sum//////////////////
	String RLNG_PHYS_IN_EXPO_Sum="0"; 
	Vector VRLNG_PHYS_IN_EXPO_Sum = new Vector();
	public Vector getVRLNG_PHYS_IN_EXPO_Sum() {return VRLNG_PHYS_IN_EXPO_Sum;}
	Vector VRLNG_PHYS_IN_EXPO_SubTotal = new Vector();
	public Vector getVRLNG_PHYS_IN_EXPO_SubTotal() {return VRLNG_PHYS_IN_EXPO_SubTotal;}
	Vector VLNG_ICE_JKM_EXPO_SubTotal = new Vector();
	public Vector getVLNG_ICE_JKM_EXPO_SubTotal() {return VLNG_ICE_JKM_EXPO_SubTotal;}
	Vector VRLNG_PHYS_INDIA_EXPOSURE_U_SubTotal = new Vector();
	public Vector getVRLNG_PHYS_INDIA_EXPOSURE_U_SubTotal() {return VRLNG_PHYS_INDIA_EXPOSURE_U_SubTotal;}
	Vector VLNG_ICE_JKM_EXPOSURE_U_SubTotal = new Vector();
	public Vector getVLNG_ICE_JKM_EXPOSURE_U_SubTotal() {return VLNG_ICE_JKM_EXPOSURE_U_SubTotal;}
	Vector VU_Phy_Leg_SubTotal = new Vector();
	public Vector getVU_Phy_Leg_SubTotal() {return VU_Phy_Leg_SubTotal;}
	Vector VU_Fin_Leg_SubTotal = new Vector();
	public Vector getVU_Fin_Leg_SubTotal() {return VU_Fin_Leg_SubTotal;}
	Vector VTotal_SubTotal = new Vector();
	public Vector getVTotal_SubTotal() {return VTotal_SubTotal;}
	
	///////////////////////////////////////////////////
	String Deal_Dt = ""; //SB20200915
	public String getDeal_Dt() {return Deal_Dt;}
	
	Vector VAll_Dt = new Vector(); //SB20200901 Not in Use
	public Vector getVAll_Dt() {return VAll_Dt;}
	
	String Slope_ICE_JKM="0"; //Used for JKM
	String Slope_FLAG="N"; //Used for JKM
	Vector VSlope = new Vector();
	public Vector getVSlope() {return VSlope;}
	Vector VSlope_BRENT = new Vector();
	public Vector getVSlope_BRENT() {return VSlope_BRENT;}
	Vector VSlope_ICE_JKM = new Vector();
	public Vector getVSlope_ICE_JKM() {return VSlope_ICE_JKM;}
	Vector VSlope_JKM = new Vector();
	public Vector getVSlope_JKM() {return VSlope_JKM;}
	Vector VSlope_LNG_PHYS = new Vector();
	public Vector getVSlope_LNG_PHYS() {return VSlope_LNG_PHYS;}
	Vector VSlope_RLNG_PHYS = new Vector();
	public Vector getVSlope_RLNG_PHYS() {return VSlope_RLNG_PHYS;}
	Vector VSlope_FLAG = new Vector();
	public Vector getVSlope_FLAG() {return VSlope_FLAG;}
	
	Vector VSettle_ICE_JKM = new Vector();
	public Vector getVSettle_ICE_JKM() {return VSettle_ICE_JKM;}
	Vector VSettle_RLNG_PHYS = new Vector();
	public Vector getVSettle_RLNG_PHYS() {return VSettle_RLNG_PHYS;}
	Vector VSettle_ICE_JKM_FLAG = new Vector();
	public Vector getVSettle_ICE_JKM_FLAG() {return VSettle_ICE_JKM_FLAG;}
	Vector VSettle_RLNG_PHYS_FLAG = new Vector();
	public Vector getVSettle_RLNG_PHYS_FLAG() {return VSettle_RLNG_PHYS_FLAG;}

	String Const_ICE_JKM="0"; //Used for JKM
	String Const_RLNG_PHYS="0"; 
	String Const_ICE_JKM_FLAG=""; 
	Vector VConst_ICE_JKM = new Vector();
	public Vector getVConst_ICE_JKM() {return VConst_ICE_JKM;}
	Vector VConst_RLNG_PHYS = new Vector();
	public Vector getVConst_RLNG_PHYS() {return VConst_RLNG_PHYS;}
	Vector VConst_ICE_JKM_FLAG = new Vector();
	public Vector getVConst_ICE_JKM_FLAG() {return VConst_ICE_JKM_FLAG;}
	
	Vector VRU_Physical_Leg = new Vector();
	public Vector getVRU_Physical_Leg() {return VRU_Physical_Leg;}
	Vector VRU_Financial_Leg = new Vector();
	public Vector getVRU_Financial_Leg() {return VRU_Financial_Leg;}
	Vector VRLNG_PHYS_INDIA_EXPOSURE_U = new Vector();
	public Vector getVRLNG_PHYS_INDIA_EXPOSURE_U() {return VRLNG_PHYS_INDIA_EXPOSURE_U;}
	Vector VLNG_ICE_JKM_EXPOSURE_U = new Vector();
	public Vector getVLNG_ICE_JKM_EXPOSURE_U() {return VLNG_ICE_JKM_EXPOSURE_U;}
	Vector VU_Phy_Leg = new Vector();
	public Vector getVU_Phy_Leg() {return VU_Phy_Leg;}
	Vector VU_Fin_Leg = new Vector();
	public Vector getVU_Fin_Leg() {return VU_Fin_Leg;}
	Vector VR_Fin_Leg = new Vector();
	public Vector getVR_Fin_Leg() {return VR_Fin_Leg;}
	Vector VTotal = new Vector();
	public Vector getVTotal() {return VTotal;}
	
	///////////////SB20201022/////////////////////////////////////////
	Vector VPNL_PHYS_REALISED = new Vector(); 
	public Vector getVPNL_PHYS_REALISED() {return VPNL_PHYS_REALISED;}
	Vector VPNL_ICE_JKM_REALISED = new Vector();
	public Vector getVPNL_ICE_JKM_REALISED() {return VPNL_ICE_JKM_REALISED;}
	
	Vector VSettle_ICE_JKM_AVG = new Vector();
	public Vector getVSettle_ICE_JKM_AVG() {return VSettle_ICE_JKM_AVG;}
	Vector VMTM = new Vector();
	public Vector getVMTM() {return VMTM;}
	Vector VRealised = new Vector();
	public Vector getVRealised() {return VRealised;}
	Vector VTotalMtmRealised = new Vector();
	public Vector getVTotalMtmRealised() {return VTotalMtmRealised;}
	//////////////TOTAL FIGURE: CALCULATION//////////////////////////////////////////////
	
	String FinalTotal = "";
	public String getFinalTotal() {return FinalTotal;}
	String FinalTotalRealised = ""; //SB20201229
	public String getFinalTotalRealised() {return FinalTotalRealised;} //SB20201229
	String FinalTotalFinUnRealised = ""; //SB20201229
	public String getFinalTotalFinUnRealised() {return FinalTotalFinUnRealised;} //SB20201229
	String FinalTotalPhyUnRealised = ""; //SB20201229
	public String getFinalTotalPhyUnRealised() {return FinalTotalPhyUnRealised;} //SB20201229
	String Total_DCQ_TCQ = "";
	public String getTotal_DCQ_TCQ() {return Total_DCQ_TCQ;}
	String Total_AllocQty = "";
	public String getTotal_AllocQty() {return Total_AllocQty;}
	String Total_RealizedQty = "";
	public String getTotal_RealizedQty() {return Total_RealizedQty;}
	String Total_UnRealizedQty = "";
	public String getTotal_UnRealizedQty() {return Total_UnRealizedQty;}
	String Total_ExposureQty = "";
	public String getTotal_ExposureQty() {return Total_ExposureQty;}
	String Total_ExposureJKMQty = "";
	public String getTotal_ExposureJKMQty() {return Total_ExposureJKMQty;}
	//////////////////////////////////////////////////////////////////////////////////////
	public void setCallFlag(String callFlag) {this.callFlag = callFlag;}	
	public void setCargo_ref_cd(String cargo_ref_cd) {this.cargo_ref_cd = cargo_ref_cd;}
	public void setMandate_conf_no(String mandate_conf_no) {Mandate_conf_no = mandate_conf_no;}	
	public void setShip_cd(String ship_cd) {this.ship_cd = ship_cd;}	
	public void setFrom_date(String from_date) {this.from_date = from_date;}
	public void setTo_date(String to_date) {this.to_date = to_date;}	
	public void setCargo_ref_no(String cargo_ref_no) {this.cargo_ref_no = cargo_ref_no;}
	public void setYear(String year) {this.year = year;}		
	public void setSELLER_NAME(String seller_name) {SELLER_NAME = seller_name;}		
		
	public Vector getTRADER_ABBR() {return TRADER_ABBR;}
	public Vector getTRADER_CD() {return TRADER_CD;}
	public Vector getTRADER_NAME() {return TRADER_NAME;}
	public Vector getMAN_CD() {return MAN_CD;}		
	public Vector getMAN_CONF_CD() {return MAN_CONF_CD;}			
	public Vector getCARGO_REF_CD() {return CARGO_REF_CD;}	
	public Vector getSHIP_CD() {return SHIP_CD;}	
	public Vector getSHIP_NAME() {return SHIP_NAME;}	
	public Vector getCUSTOMER_CD() {return CUSTOMER_CD;}
	public Vector getCUSTOMER_NM() {return CUSTOMER_NM;}				
	public Vector getMAN_CONF_DT() {return MAN_CONF_DT;}
	public Vector getCARGO_ARRIVAL_DATE() {return CARGO_ARRIVAL_DATE;}	
	
	public Vector getFGSA_NO() {return FGSA_NO;}
	public Vector getFGSA_REV_NO() {return FGSA_REV_NO;}
	public Vector getSN_REV_NO() {return SN_REV_NO;}
	public Vector getSN_NO() {return SN_NO;}
	public Vector getBOOKMMBTU() {return BOOKMMBTU;}
	public Vector getSUPPLIEDMBTU() {return SUPPLIEDMBTU;}
	public Vector getBALANCEMMBTU() {return BALANCEMMBTU;}	
			
	public String getMandate_conf_no() {return Mandate_conf_no;}		
	public String getSELLER_NAME() {return SELLER_NAME;}
	public String getSHIPNAME() {return SHIPNAME;}		
	public String getTot_commitment() {return tot_commitment;}


	public Vector getLOA_BALANCEMMBTU() {return LOA_BALANCEMMBTU;}
	public Vector getLOA_BOOKMMBTU() {return LOA_BOOKMMBTU;}
	public Vector getLOA_CARGO_ARRIVAL_DATE() {return LOA_CARGO_ARRIVAL_DATE;}
	public Vector getLOA_CUSTOMER_CD() {return LOA_CUSTOMER_CD;}
	public Vector getLOA_CUSTOMER_NM() {return LOA_CUSTOMER_NM;}
	public Vector getLOA_NO() {return LOA_NO;}
	public Vector getLOA_REV_NO() {return LOA_REV_NO;}
	public Vector getLOA_SUPPLIEDMBTU() {return LOA_SUPPLIEDMBTU;}
	public String getLoa_tot_commitment() {return loa_tot_commitment;}
	public Vector getTENDER_NO() {return TENDER_NO;}
	public String getRe_gas_tot_commit() {return re_gas_tot_commit;}
	public Vector getRE_GAS_BALANCEMMBTU() {return RE_GAS_BALANCEMMBTU;}
	public Vector getRE_GAS_BOOKMMBTU() {return RE_GAS_BOOKMMBTU;}
	public Vector getRE_GAS_CARGO_ARRIVAL_DATE() {return RE_GAS_CARGO_ARRIVAL_DATE;}
	public Vector getRE_GAS_CUSTOMER_CD() {return RE_GAS_CUSTOMER_CD;}
	public Vector getRE_GAS_CUSTOMER_NM() {return RE_GAS_CUSTOMER_NM;}
	public Vector getRE_GAS_NO() {return RE_GAS_NO;}
	public Vector getRE_GAS_SEQ_NO() {return RE_GAS_SEQ_NO;}
	public Vector getRE_GAS_SUPPLIEDMBTU() {return RE_GAS_SUPPLIEDMBTU;}


	public String getBal_tot_comitment() {
		return bal_tot_comitment;
	}


	public String getSup_tot_comitment() {
		return sup_tot_comitment;
	}


	public String getLoa_bal_tot_comitment() {
		return loa_bal_tot_comitment;
	}


	public String getLoa_sup_tot_comitment() {
		return loa_sup_tot_comitment;
	}


	public String getRe_gas_bal_tot_comitment() {
		return re_gas_bal_tot_comitment;
	}


	public String getRe_gas_sup_tot_comitment() {
		return re_gas_sup_tot_comitment;
	}


	public Vector getLOA_REF_NO() {
		return LOA_REF_NO;
	}


	public Vector getSN_REF_NO() {
		return SN_REF_NO;
	}


	public Vector getSN_CLOSURE_CLOSE() {
		return SN_CLOSURE_CLOSE;
	}


	public Vector getSN_CLOSURE_DT() {
		return SN_CLOSURE_DT;
	}


	public Vector getTCQ_REQUEST_QTY() {
		return TCQ_REQUEST_QTY;
	}


	public Vector getLOA_TCQ_REQUEST_QTY() {
		return LOA_TCQ_REQUEST_QTY;
	}


	public Vector getACTUAL_TCQ() {
		return ACTUAL_TCQ;
	}


	public Vector getLOA_ACTUAL_TCQ() {
		return LOA_ACTUAL_TCQ;
	}


	public Vector getSN_CLOSURE_QTY() {
		return SN_CLOSURE_QTY;
	}


	public Vector getTCQ_REQUEST_SIGN() {
		return TCQ_REQUEST_SIGN;
	}


	public Vector getLOA_TCQ_REQUEST_SIGN() {
		return LOA_TCQ_REQUEST_SIGN;
	}

	public Vector getRATE() {
		return RATE;
	}

	public Vector getSIGNING_DT() {
		return SIGNING_DT;
	}

	public Vector getLOA_SIGNING_DT() {
		return LOA_SIGNING_DT;
	}

	public Vector getLOA_RATE() {
		return LOA_RATE;
	}

	public Vector getREGAS_RATE() {
		return REGAS_RATE;
	}

	public Vector getREGAS_SIGNING_DT() {
		return REGAS_SIGNING_DT;
	}


	public Vector getBOOKMMSCM() {
		return BOOKMMSCM;
	}


	public Vector getSUPPLIEDMMSCM() {
		return SUPPLIEDMMSCM;
	}


	public Vector getBALANCEMMSCM() {
		return BALANCEMMSCM;
	}


	public String getBal_tot_comitment_mmscm() {
		return bal_tot_comitment_mmscm;
	}


	public String getSup_tot_comitment_mmscm() {
		return sup_tot_comitment_mmscm;
	}


	public String getTot_commitment_mmscm() {
		return tot_commitment_mmscm;
	}


	public String getLoa_bal_tot_comitment_mmscm() {
		return loa_bal_tot_comitment_mmscm;
	}


	public Vector getLOA_BALANCEMMSCM() {
		return LOA_BALANCEMMSCM;
	}


	public Vector getLOA_BOOKMMSCM() {
		return LOA_BOOKMMSCM;
	}


	public String getLoa_sup_tot_comitment_mmscm() {
		return loa_sup_tot_comitment_mmscm;
	}


	public Vector getLOA_SUPPLIEDMMSCM() {
		return LOA_SUPPLIEDMMSCM;
	}


	public String getLoa_tot_commitment_mmscm() {
		return loa_tot_commitment_mmscm;
	}


	public Vector getRE_GAS_BALANCEMMSCM() {
		return RE_GAS_BALANCEMMSCM;
	}


	public Vector getRE_GAS_BOOKMMSCM() {
		return RE_GAS_BOOKMMSCM;
	}


	public Vector getRE_GAS_SUPPLIEDMMSCM() {
		return RE_GAS_SUPPLIEDMMSCM;
	}


	public String getRe_gas_tot_commit_mmscm() {
		return re_gas_tot_commit_mmscm;
	}


	public String getRe_gas_sup_tot_comitment_mmscm() {
		return re_gas_sup_tot_comitment_mmscm;
	}


	public String getRe_gas_bal_tot_comitment_mmscm() {
		return re_gas_bal_tot_comitment_mmscm;
	}
	public Vector getCUSTOMER_ABR_FGSA() {
		return CUSTOMER_ABR_FGSA;
	}
	public Vector getCUSTOMER_CD_FGSA() {
		return CUSTOMER_CD_FGSA;
	}
	public Vector getEMP_ABR_FGSA() {
		return EMP_ABR_FGSA;
	}
	public Vector getEMP_CD_FGSA() {
		return EMP_CD_FGSA;
	}
	public Vector getEND_DT_FGSA() {
		return END_DT_FGSA;
	}
	public Vector getRENEWAL_DT_FGSA() {
		return RENEWAL_DT_FGSA;
	}
	public Vector getSIGNING_DT_FGSA() {
		return SIGNING_DT_FGSA;
	}
	public Vector getSTART_DT_FGSA() {
		return START_DT_FGSA;
	}
	public Vector getFGSA_NO_NEW() {
		return FGSA_NO_NEW;
	}
	public Vector getREV_NO_FGSA() {
		return REV_NO_FGSA;
	}
	public void setREV_NO_FGSA(Vector rev_no_fgsa) {
		REV_NO_FGSA = rev_no_fgsa;
	}
	public Vector getREV_DT_FGSA() {
		return REV_DT_FGSA;
	}

	public Vector getCUST_CD() {
		return CUST_CD;
	}

	public Vector getCUST_NM() {
		return CUST_NM;
	}

	public String getCust_nm() {
		return cust_nm;
	}

	public void setCust_nm(String cust_nm) {
		this.cust_nm = cust_nm;
	}

	public Vector getDELV_POINT() {
		return DELV_POINT;
	}


	public Vector getCUSTOMER_ABBR() {
		return CUSTOMER_ABBR;
	}


	public Vector getLOA_CUSTOMER_ABBR() {
		return LOA_CUSTOMER_ABBR;
	}


	public Vector getRE_GAS_CUSTOMER_ABBR() {
		return RE_GAS_CUSTOMER_ABBR;
	}


	public Vector getCUSTOMER_CD_SN_P() {
		return CUSTOMER_CD_SN_P;
	}


	public Vector getCUSTOMER_ABR_SN_P() {
		return CUSTOMER_ABR_SN_P;
	}


	public Vector getSN_NO_P() {
		return SN_NO_P;
	}


	public Vector getSN_REV_NO_P() {
		return SN_REV_NO_P;
	}


	public Vector getFGSA_NO_P() {
		return FGSA_NO_P;
	}


	public Vector getFGSA_REV_NO_P() {
		return FGSA_REV_NO_P;
	}


	public Vector getSTART_DT_SN_P() {
		return START_DT_SN_P;
	}


	public Vector getEND_DT_SN_P() {
		return END_DT_SN_P;
	}


	public Vector getRATE_SN_P() {
		return RATE_SN_P;
	}

	public Vector getCARGO_MARGIN_SN_P() {
		return CARGO_MARGIN_SN_P;
	}


	public Vector getCARGO_REF_NO_SN_P() {
		return CARGO_REF_NO_SN_P;
	}


	public Vector getCARGO_CONF_PRICE_DT_SN_P() {
		return CARGO_CONF_PRICE_DT_SN_P;
	}


	public Vector getCARGO_CUSTOM_DUTY_SN_P() {
		return CARGO_CUSTOM_DUTY_SN_P;
	}


	public ResultSet getRset_tmpl() {
		return rset_tmpl;
	}


	public Vector getCOLOR_FLAG_CONF_PRICE_SN_P() {
		return COLOR_FLAG_CONF_PRICE_SN_P;
	}


	public Vector getCOLOR_FLAG_CUSTOM_SN_P() {
		return COLOR_FLAG_CUSTOM_SN_P;
	}


	public Vector getCOLOR_FLAG_MARGIN_SN_P() {
		return COLOR_FLAG_MARGIN_SN_P;
	}


	public Vector getCOLOR_FLAG_RATE_SN_P() {
		return COLOR_FLAG_RATE_SN_P;
	}
	public Vector getVgas_date() {
		return Vgas_date;
	}
	public Vector[] getSUPPLIEDMBTU1() {
		return SUPPLIEDMBTU1;
	}
	public Vector[] getSUPPLIEDMMSCM1() {
		return SUPPLIEDMMSCM1;
	}
	public Vector[] getRE_GAS_SUPPLIEDMBTU1() {
		return RE_GAS_SUPPLIEDMBTU1;
	}
	public Vector[] getRE_GAS_SUPPLIEDMMSCM1() {
		return RE_GAS_SUPPLIEDMMSCM1;
	}
	public Vector[] getLOA_SUPPLIEDMBTU1() {
		return LOA_SUPPLIEDMBTU1;
	}
	public Vector[] getLOA_SUPPLIEDMMSCM1() {
		return LOA_SUPPLIEDMMSCM1;
	}
	public Vector getRegas_cargo_ref_no() {
		return Regas_cargo_ref_no;
	}
	public void setBuyer_cd(String buyer_cd) {
		Buyer_cd = buyer_cd;
	}
	public void setRe_gas_no(String re_gas_no) {
		this.re_gas_no = re_gas_no;
	}
	public void setNo_of_cargo(String no_of_cargo) {
		this.no_of_cargo = no_of_cargo;
	}
	public void setFrm_dt(String frm_dt) {
		this.frm_dt = frm_dt;
	}
	public void setTo_dt(String to_dt) {
		this.to_dt = to_dt;
	}
	public void setRe_gas_rev_no(String re_gas_rev_no) {
		this.re_gas_rev_no = re_gas_rev_no;
	}
	public Vector getEDQ_FROM_DT() {
		return EDQ_FROM_DT;
	}
	public Vector getEDQ_TO_DT() {
		return EDQ_TO_DT;
	}
	public Vector getACTUAL_RECPT_DT() {
		return ACTUAL_RECPT_DT;
	}
	public Vector getCONTRACT_START_DT() {
		return CONTRACT_START_DT;
	}
	public Vector getCONTRACT_END_DT() {
		return CONTRACT_END_DT;
	}
	public Vector getADQ_QTY() {
		return ADQ_QTY;
	}
	public Vector getSYS_USE_GAS() {
		return SYS_USE_GAS;
	}
	public Vector getQTY_TO_BE_SUPPLY() {
		return QTY_TO_BE_SUPPLY;
	}
	public Vector getDCQ_QTY() {
		return DCQ_QTY;
	}
	public Vector getQTY_UNIT_CD() {
		return QTY_UNIT_CD;
	}
	public Vector getQTY_UNIT_ABR() {
		return QTY_UNIT_ABR;
	}
	public Vector getBOE_QTY() {
		return BOE_QTY;
	}
	public Vector getSUPP_CD() {
		return SUPP_CD;
	}
	public Vector getSUPP_NM() {
		return SUPP_NM;
	}
	public Vector getBOE_NO() {
		return BOE_NO;
	}
	public Vector getBOE_DT() {
		return BOE_DT;
	}
	public Vector getCARGO_REF_NO() {
		return CARGO_REF_NO;
	}
	public Vector getSUPP_SEQ_NO() {
		return SUPP_SEQ_NO;
	}
	public Vector getSUPP_CONTACT_PERSON() {
		return SUPP_CONTACT_PERSON;
	}
	public Vector getSUPP_CONTACT_PERSON_DESG() {
		return SUPP_CONTACT_PERSON_DESG;
	}
	public String getSetcargo_ref_no() {
		return setcargo_ref_no;
	}
	public void setSetcargo_ref_no(String setcargo_ref_no) {
		this.setcargo_ref_no = setcargo_ref_no;
	}
	public Vector getCust_plant_cd() {
		return cust_plant_cd;
	}
	public Vector getCust_plant_nm() {
		return cust_plant_nm;
	}
	public Vector getVdatewise_plant_tot() {
		return Vdatewise_plant_tot;
	}
	public String getContact_Customer_Person_Address() {
		return contact_Customer_Person_Address;
	}
	public String getContact_Customer_Person_City() {
		return contact_Customer_Person_City;
	}
	public String getContact_Customer_Person_Country() {
		return contact_Customer_Person_Country;
	}
	public String getContact_Customer_Person_Fax() {
		return contact_Customer_Person_Fax;
	}
	public String getContact_Customer_Person_Phone() {
		return contact_Customer_Person_Phone;
	}
	public String getContact_Customer_Person_Pin() {
		return contact_Customer_Person_Pin;
	}
	public String getContact_Customer_Person_State() {
		return contact_Customer_Person_State;
	}
	public void setSign_dt(String sign_dt) {
		Sign_dt = sign_dt;
	}
	public String getSign_dt() {
		return Sign_dt;
	}
	public Vector getQQ_DT() {
		return QQ_DT;
	}
	public Vector getQQ_NO() {
		return QQ_NO;
	}
	public String getSuppl_abbr() {
		return Suppl_abbr;
	}
	public String getSuppl_Name() {
		return Suppl_Name;
	}
	public Vector getVplant_inv_no() {
		return Vplant_inv_no;
	}

	public String getBoe_no_dated() {
		return boe_no_dated;
	}

	public void setBoe_no_dated(String boe_no_dated) {
		this.boe_no_dated = boe_no_dated;
	}

	public String getDear_sir() {
		return dear_sir;
	}

	public void setDear_sir(String dear_sir) {
		this.dear_sir = dear_sir;
	}

	public String getFaithful() {
		return faithful;
	}

	public void setFaithful(String faithful) {
		this.faithful = faithful;
	}

	public String getHlpl() {
		return hlpl;
	}

	public void setHlpl(String hlpl) {
		this.hlpl = hlpl;
	}

	public String getKind_attn() {
		return kind_attn;
	}

	public void setKind_attn(String kind_attn) {
		this.kind_attn = kind_attn;
	}

	public String getLess() {
		return less;
	}

	public void setLess(String less) {
		this.less = less;
	}

	public String getLng_unloaded() {
		return lng_unloaded;
	}

	public void setLng_unloaded(String lng_unloaded) {
		this.lng_unloaded = lng_unloaded;
	}

	public String[] getPara() {
		return para;
	}

	public void setPara(String[] para) {
		this.para = para;
	}

	public String getQuantity_quality_ref_no() {
		return quantity_quality_ref_no;
	}

	public void setQuantity_quality_ref_no(String quantity_quality_ref_no) {
		this.quantity_quality_ref_no = quantity_quality_ref_no;
	}

	public String getStorage_end_dt() {
		return storage_end_dt;
	}

	public void setStorage_end_dt(String storage_end_dt) {
		this.storage_end_dt = storage_end_dt;
	}

	public String getStorage_st_dt() {
		return storage_st_dt;
	}

	public void setStorage_st_dt(String storage_st_dt) {
		this.storage_st_dt = storage_st_dt;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getSug_unloaded() {
		return sug_unloaded;
	}

	public void setSug_unloaded(String sug_unloaded) {
		this.sug_unloaded = sug_unloaded;
	}

	public String getTbl_statmnt() {
		return tbl_statmnt;
	}

	public void setTbl_statmnt(String tbl_statmnt) {
		this.tbl_statmnt = tbl_statmnt;
	}

	public String getThanks() {
		return thanks;
	}

	public void setThanks(String thanks) {
		this.thanks = thanks;
	}

	public String[] getTotal_lng() {
		return total_lng;
	}

	public void setTotal_lng(String[] total_lng) {
		this.total_lng = total_lng;
	}

	public String getVessel_arrival_dt() {
		return vessel_arrival_dt;
	}

	public void setVessel_arrival_dt(String vessel_arrival_dt) {
		this.vessel_arrival_dt = vessel_arrival_dt;
	}

	public String getVessel_name() {
		return vessel_name;
	}

	public void setVessel_name(String vessel_name) {
		this.vessel_name = vessel_name;
	}

	public Vector getBOE_DT_1() {
		return BOE_DT_1;
	}

	public void setBOE_DT_1(Vector boe_dt_1) {
		BOE_DT_1 = boe_dt_1;
	}

	public Vector getLTCORA_BALANCEMMBTU() {
		return LTCORA_BALANCEMMBTU;
	}

	public void setLTCORA_BALANCEMMBTU(Vector ltcora_balancemmbtu) {
		LTCORA_BALANCEMMBTU = ltcora_balancemmbtu;
	}

	public Vector getLTCORA_BALANCEMMSCM() {
		return LTCORA_BALANCEMMSCM;
	}

	public void setLTCORA_BALANCEMMSCM(Vector ltcora_balancemmscm) {
		LTCORA_BALANCEMMSCM = ltcora_balancemmscm;
	}

	public Vector getLTCORA_BOOKMMBTU() {
		return LTCORA_BOOKMMBTU;
	}

	public void setLTCORA_BOOKMMBTU(Vector ltcora_bookmmbtu) {
		LTCORA_BOOKMMBTU = ltcora_bookmmbtu;
	}

	public Vector getLTCORA_BOOKMMSCM() {
		return LTCORA_BOOKMMSCM;
	}

	public void setLTCORA_BOOKMMSCM(Vector ltcora_bookmmscm) {
		LTCORA_BOOKMMSCM = ltcora_bookmmscm;
	}

	public Vector getLTCORA_CARGO_ARRIVAL_DATE() {
		return LTCORA_CARGO_ARRIVAL_DATE;
	}

	public void setLTCORA_CARGO_ARRIVAL_DATE(Vector ltcora_cargo_arrival_date) {
		LTCORA_CARGO_ARRIVAL_DATE = ltcora_cargo_arrival_date;
	}

	public Vector getLTCORA_cargo_ref_no() {
		return LTCORA_cargo_ref_no;
	}

	public void setLTCORA_cargo_ref_no(Vector ltcora_cargo_ref_no) {
		LTCORA_cargo_ref_no = ltcora_cargo_ref_no;
	}

	public Vector getLTCORA_CUSTOMER_ABBR() {
		return LTCORA_CUSTOMER_ABBR;
	}

	public void setLTCORA_CUSTOMER_ABBR(Vector ltcora_customer_abbr) {
		LTCORA_CUSTOMER_ABBR = ltcora_customer_abbr;
	}

	public Vector getLTCORA_CUSTOMER_CD() {
		return LTCORA_CUSTOMER_CD;
	}

	public void setLTCORA_CUSTOMER_CD(Vector ltcora_customer_cd) {
		LTCORA_CUSTOMER_CD = ltcora_customer_cd;
	}

	public Vector getLTCORA_CUSTOMER_NM() {
		return LTCORA_CUSTOMER_NM;
	}

	public void setLTCORA_CUSTOMER_NM(Vector ltcora_customer_nm) {
		LTCORA_CUSTOMER_NM = ltcora_customer_nm;
	}

	public Vector getLTCORA_NO() {
		return LTCORA_NO;
	}

	public void setLTCORA_NO(Vector ltcora_no) {
		LTCORA_NO = ltcora_no;
	}

	public Vector getLTCORA_RATE() {
		return LTCORA_RATE;
	}

	public void setLTCORA_RATE(Vector ltcora_rate) {
		LTCORA_RATE = ltcora_rate;
	}

	public Vector getLTCORA_SEQ_NO() {
		return LTCORA_SEQ_NO;
	}

	public void setLTCORA_SEQ_NO(Vector ltcora_seq_no) {
		LTCORA_SEQ_NO = ltcora_seq_no;
	}

	public Vector getLTCORA_SIGNING_DT() {
		return LTCORA_SIGNING_DT;
	}

	public void setLTCORA_SIGNING_DT(Vector ltcora_signing_dt) {
		LTCORA_SIGNING_DT = ltcora_signing_dt;
	}

	public Vector getLTCORA_SUPPLIEDMBTU() {
		return LTCORA_SUPPLIEDMBTU;
	}

	public void setLTCORA_SUPPLIEDMBTU(Vector ltcora_suppliedmbtu) {
		LTCORA_SUPPLIEDMBTU = ltcora_suppliedmbtu;
	}

	public Vector[] getLTCORA_SUPPLIEDMBTU1() {
		return LTCORA_SUPPLIEDMBTU1;
	}

	public void setLTCORA_SUPPLIEDMBTU1(Vector[] ltcora_suppliedmbtu1) {
		LTCORA_SUPPLIEDMBTU1 = ltcora_suppliedmbtu1;
	}

	public Vector getLTCORA_SUPPLIEDMMSCM() {
		return LTCORA_SUPPLIEDMMSCM;
	}

	public void setLTCORA_SUPPLIEDMMSCM(Vector ltcora_suppliedmmscm) {
		LTCORA_SUPPLIEDMMSCM = ltcora_suppliedmmscm;
	}

	public Vector[] getLTCORA_SUPPLIEDMMSCM1() {
		return LTCORA_SUPPLIEDMMSCM1;
	}

	public void setLTCORA_SUPPLIEDMMSCM1(Vector[] ltcora_suppliedmmscm1) {
		LTCORA_SUPPLIEDMMSCM1 = ltcora_suppliedmmscm1;
	}

	public String getLTCORA_bal_tot_comitment() {
		return LTCORA_bal_tot_comitment;
	}

	public void setLTCORA_bal_tot_comitment(String ltcora_bal_tot_comitment) {
		LTCORA_bal_tot_comitment = ltcora_bal_tot_comitment;
	}

	public String getLTCORA_bal_tot_comitment_mmscm() {
		return LTCORA_bal_tot_comitment_mmscm;
	}

	public void setLTCORA_bal_tot_comitment_mmscm(
			String ltcora_bal_tot_comitment_mmscm) {
		LTCORA_bal_tot_comitment_mmscm = ltcora_bal_tot_comitment_mmscm;
	}

	public String getLTCORA_sup_tot_comitment() {
		return LTCORA_sup_tot_comitment;
	}

	public void setLTCORA_sup_tot_comitment(String ltcora_sup_tot_comitment) {
		LTCORA_sup_tot_comitment = ltcora_sup_tot_comitment;
	}

	public String getLTCORA_sup_tot_comitment_mmscm() {
		return LTCORA_sup_tot_comitment_mmscm;
	}

	public void setLTCORA_sup_tot_comitment_mmscm(
			String ltcora_sup_tot_comitment_mmscm) {
		LTCORA_sup_tot_comitment_mmscm = ltcora_sup_tot_comitment_mmscm;
	}

	public String getLTCORA_tot_commit() {
		return LTCORA_tot_commit;
	}

	public void setLTCORA_tot_commit(String ltcora_tot_commit) {
		LTCORA_tot_commit = ltcora_tot_commit;
	}

	public String getLTCORA_tot_commit_mmscm() {
		return LTCORA_tot_commit_mmscm;
	}

	public void setLTCORA_tot_commit_mmscm(String ltcora_tot_commit_mmscm) {
		LTCORA_tot_commit_mmscm = ltcora_tot_commit_mmscm;
	}

	public Vector getCN_BALANCEMMBTU() {
		return CN_BALANCEMMBTU;
	}

	public void setCN_BALANCEMMBTU(Vector cn_balancemmbtu) {
		CN_BALANCEMMBTU = cn_balancemmbtu;
	}

	public Vector getCN_BALANCEMMSCM() {
		return CN_BALANCEMMSCM;
	}

	public void setCN_BALANCEMMSCM(Vector cn_balancemmscm) {
		CN_BALANCEMMSCM = cn_balancemmscm;
	}

	public Vector getCN_BOOKMMBTU() {
		return CN_BOOKMMBTU;
	}

	public void setCN_BOOKMMBTU(Vector cn_bookmmbtu) {
		CN_BOOKMMBTU = cn_bookmmbtu;
	}

	public Vector getCN_BOOKMMSCM() {
		return CN_BOOKMMSCM;
	}

	public void setCN_BOOKMMSCM(Vector cn_bookmmscm) {
		CN_BOOKMMSCM = cn_bookmmscm;
	}

	public Vector getCN_CARGO_ARRIVAL_DATE() {
		return CN_CARGO_ARRIVAL_DATE;
	}

	public void setCN_CARGO_ARRIVAL_DATE(Vector cn_cargo_arrival_date) {
		CN_CARGO_ARRIVAL_DATE = cn_cargo_arrival_date;
	}

	public Vector getCN_cargo_ref_no() {
		return CN_cargo_ref_no;
	}

	public void setCN_cargo_ref_no(Vector cn_cargo_ref_no) {
		CN_cargo_ref_no = cn_cargo_ref_no;
	}

	public Vector getCN_CUSTOMER_ABBR() {
		return CN_CUSTOMER_ABBR;
	}

	public void setCN_CUSTOMER_ABBR(Vector cn_customer_abbr) {
		CN_CUSTOMER_ABBR = cn_customer_abbr;
	}

	public Vector getCN_CUSTOMER_CD() {
		return CN_CUSTOMER_CD;
	}

	public void setCN_CUSTOMER_CD(Vector cn_customer_cd) {
		CN_CUSTOMER_CD = cn_customer_cd;
	}

	public Vector getCN_CUSTOMER_NM() {
		return CN_CUSTOMER_NM;
	}

	public void setCN_CUSTOMER_NM(Vector cn_customer_nm) {
		CN_CUSTOMER_NM = cn_customer_nm;
	}

	public Vector getCN_NO() {
		return CN_NO;
	}

	public void setCN_NO(Vector cn_no) {
		CN_NO = cn_no;
	}

	public Vector getCN_RATE() {
		return CN_RATE;
	}

	public void setCN_RATE(Vector cn_rate) {
		CN_RATE = cn_rate;
	}

	public Vector getCN_SEQ_NO() {
		return CN_SEQ_NO;
	}

	public void setCN_SEQ_NO(Vector cn_seq_no) {
		CN_SEQ_NO = cn_seq_no;
	}

	public Vector getCN_SIGNING_DT() {
		return CN_SIGNING_DT;
	}

	public void setCN_SIGNING_DT(Vector cn_signing_dt) {
		CN_SIGNING_DT = cn_signing_dt;
	}

	public Vector getCN_SUPPLIEDMBTU() {
		return CN_SUPPLIEDMBTU;
	}

	public void setCN_SUPPLIEDMBTU(Vector cn_suppliedmbtu) {
		CN_SUPPLIEDMBTU = cn_suppliedmbtu;
	}

	public Vector[] getCN_SUPPLIEDMBTU1() {
		return CN_SUPPLIEDMBTU1;
	}

	public void setCN_SUPPLIEDMBTU1(Vector[] cn_suppliedmbtu1) {
		CN_SUPPLIEDMBTU1 = cn_suppliedmbtu1;
	}

	public Vector getCN_SUPPLIEDMMSCM() {
		return CN_SUPPLIEDMMSCM;
	}

	public void setCN_SUPPLIEDMMSCM(Vector cn_suppliedmmscm) {
		CN_SUPPLIEDMMSCM = cn_suppliedmmscm;
	}

	public Vector[] getCN_SUPPLIEDMMSCM1() {
		return CN_SUPPLIEDMMSCM1;
	}

	public void setCN_SUPPLIEDMMSCM1(Vector[] cn_suppliedmmscm1) {
		CN_SUPPLIEDMMSCM1 = cn_suppliedmmscm1;
	}

	public String getCN_bal_tot_comitment() {
		return CN_bal_tot_comitment;
	}

	public void setCN_bal_tot_comitment(String cn_bal_tot_comitment) {
		CN_bal_tot_comitment = cn_bal_tot_comitment;
	}

	public String getCN_bal_tot_comitment_mmscm() {
		return CN_bal_tot_comitment_mmscm;
	}

	public void setCN_bal_tot_comitment_mmscm(String cn_bal_tot_comitment_mmscm) {
		CN_bal_tot_comitment_mmscm = cn_bal_tot_comitment_mmscm;
	}

	public String getCN_sup_tot_comitment() {
		return CN_sup_tot_comitment;
	}

	public void setCN_sup_tot_comitment(String cn_sup_tot_comitment) {
		CN_sup_tot_comitment = cn_sup_tot_comitment;
	}

	public String getCN_sup_tot_comitment_mmscm() {
		return CN_sup_tot_comitment_mmscm;
	}

	public void setCN_sup_tot_comitment_mmscm(String cn_sup_tot_comitment_mmscm) {
		CN_sup_tot_comitment_mmscm = cn_sup_tot_comitment_mmscm;
	}

	public String getCN_tot_commit() {
		return CN_tot_commit;
	}

	public void setCN_tot_commit(String cn_tot_commit) {
		CN_tot_commit = cn_tot_commit;
	}

	public String getCN_tot_commit_mmscm() {
		return CN_tot_commit_mmscm;
	}

	public void setCN_tot_commit_mmscm(String cn_tot_commit_mmscm) {
		CN_tot_commit_mmscm = cn_tot_commit_mmscm;
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

	public Vector getDeal_NO() {
		return Deal_NO;
	}

	public void setDeal_NO(Vector deal_NO) {
		Deal_NO = deal_NO;
	}

	public Vector getDeal_Signing_dt() {
		return Deal_Signing_dt;
	}

	public void setDeal_Signing_dt(Vector deal_Signing_dt) {
		Deal_Signing_dt = deal_Signing_dt;
	}

	public Vector getDeal_cust_nm() {
		return Deal_cust_nm;
	}

	public void setDeal_cust_nm(Vector deal_cust_nm) {
		Deal_cust_nm = deal_cust_nm;
	}

	public Vector getDeal_cust_cd() {
		return Deal_cust_cd;
	}

	public void setDeal_cust_cd(Vector deal_cust_cd) {
		Deal_cust_cd = deal_cust_cd;
	}

	public Vector getDeal_cust_abbr() {
		return Deal_cust_abbr;
	}

	public void setDeal_cust_abbr(Vector deal_cust_abbr) {
		Deal_cust_abbr = deal_cust_abbr;
	}

	public Vector getDeal_typ() {
		return Deal_typ;
	}

	public void setDeal_typ(Vector deal_typ) {
		Deal_typ = deal_typ;
	}

	public Vector getDeal_cargo_seq_no() {
		return Deal_cargo_seq_no;
	}

	public void setDeal_cargo_seq_no(Vector deal_cargo_seq_no) {
		Deal_cargo_seq_no = deal_cargo_seq_no;
	}

	public Vector getDeal_ENT_DT() {
		return Deal_ENT_DT;
	}

	public void setDeal_ENT_DT(Vector deal_ENT_DT) {
		Deal_ENT_DT = deal_ENT_DT;
	}

	public Vector getDeal_ENT_BY() {
		return Deal_ENT_BY;
	}

	public void setDeal_ENT_BY(Vector deal_ENT_BY) {
		Deal_ENT_BY = deal_ENT_BY;
	}

	public Vector getDeal_Price() {
		return Deal_Price;
	}

	public void setDeal_Price(Vector deal_Price) {
		Deal_Price = deal_Price;
	}

	public Vector getDeal_ST_DT() {
		return Deal_ST_DT;
	}

	public void setDeal_ST_DT(Vector deal_ST_DT) {
		Deal_ST_DT = deal_ST_DT;
	}

	public Vector getDeal_END_DT() {
		return Deal_END_DT;
	}

	public void setDeal_END_DT(Vector deal_END_DT) {
		Deal_END_DT = deal_END_DT;
	}

	public Vector getDeal_qty_booked() {
		return Deal_qty_booked;
	}

	public void setDeal_qty_booked(Vector deal_qty_booked) {
		Deal_qty_booked = deal_qty_booked;
	}

	public Vector getDeal_DCQ() {
		return Deal_DCQ;
	}

	public void setDeal_DCQ(Vector deal_DCQ) {
		Deal_DCQ = deal_DCQ;
	}

	public Vector getDeal_qty_Supplied() {
		return Deal_qty_Supplied;
	}

	public void setDeal_qty_Supplied(Vector deal_qty_Supplied) {
		Deal_qty_Supplied = deal_qty_Supplied;
	}

	public Vector getDeal_sts() {
		return Deal_sts;
	}

	public void setDeal_sts(Vector deal_sts) {
		Deal_sts = deal_sts;
	}

	public Vector getDeal_duration() {
		return Deal_duration;
	}

	public void setDeal_duration(Vector deal_duration) {
		Deal_duration = deal_duration;
	}

	public Vector getDeal_amt_wout_tax() {
		return Deal_amt_wout_tax;
	}

	public void setDeal_amt_wout_tax(Vector deal_amt_wout_tax) {
		Deal_amt_wout_tax = deal_amt_wout_tax;
	}

	public Vector getDeal_amt_PAY_NOT_RECV() {
		return Deal_amt_PAY_NOT_RECV;
	}

	public void setDeal_amt_PAY_NOT_RECV(Vector deal_amt_PAY_NOT_RECV) {
		Deal_amt_PAY_NOT_RECV = deal_amt_PAY_NOT_RECV;
	}

	public Vector getDeal_adv_amt() {
		return Deal_adv_amt;
	}

	public void setDeal_adv_amt(Vector deal_adv_amt) {
		Deal_adv_amt = deal_adv_amt;
	}

	public Vector getDeal_cont_no() {
		return Deal_cont_no;
	}

	public void setDeal_cont_no(Vector deal_cont_no) {
		Deal_cont_no = deal_cont_no;
	}

	public Vector getDeal_typ_nm() {
		return Deal_typ_nm;
	}

	public void setDeal_typ_nm(Vector deal_typ_nm) {
		Deal_typ_nm = deal_typ_nm;
	}

	public Vector getDeal_curr() {
		return Deal_curr;
	}

	public void setDeal_curr(Vector deal_curr) {
		Deal_curr = deal_curr;
	}

	public Vector getLtcora_agr_no() {
		return ltcora_agr_no;
	}

	public void setLtcora_agr_no(Vector ltcora_agr_no) {
		this.ltcora_agr_no = ltcora_agr_no;
	}

	public Vector getCn_agr_no() {
		return cn_agr_no;
	}

	public void setCn_agr_no(Vector cn_agr_no) {
		this.cn_agr_no = cn_agr_no;
	}

	public Vector getSn_ent_by() {
		return sn_ent_by;
	}

	public void setSn_ent_by(Vector sn_ent_by) {
		this.sn_ent_by = sn_ent_by;
	}

	public Vector getSn_ent_dt() {
		return sn_ent_dt;
	}

	public void setSn_ent_dt(Vector sn_ent_dt) {
		this.sn_ent_dt = sn_ent_dt;
	}

	public Vector getLoa_ent_by() {
		return loa_ent_by;
	}

	public void setLoa_ent_by(Vector loa_ent_by) {
		this.loa_ent_by = loa_ent_by;
	}

	public Vector getLoa_ent_dt() {
		return loa_ent_dt;
	}

	public void setLoa_ent_dt(Vector loa_ent_dt) {
		this.loa_ent_dt = loa_ent_dt;
	}

	public Vector getLtcora_ent_by() {
		return ltcora_ent_by;
	}

	public void setLtcora_ent_by(Vector ltcora_ent_by) {
		this.ltcora_ent_by = ltcora_ent_by;
	}

	public Vector getLtcora_ent_dt() {
		return ltcora_ent_dt;
	}

	public void setLtcora_ent_dt(Vector ltcora_ent_dt) {
		this.ltcora_ent_dt = ltcora_ent_dt;
	}

	public Vector getCn_ent_by() {
		return cn_ent_by;
	}

	public void setCn_ent_by(Vector cn_ent_by) {
		this.cn_ent_by = cn_ent_by;
	}

	public Vector getCn_ent_dt() {
		return cn_ent_dt;
	}

	public void setCn_ent_dt(Vector cn_ent_dt) {
		this.cn_ent_dt = cn_ent_dt;
	}

	public Vector getSn_apr_by() {
		return sn_apr_by;
	}

	public void setSn_apr_by(Vector sn_apr_by) {
		this.sn_apr_by = sn_apr_by;
	}

	public Vector getSn_apr_dt() {
		return sn_apr_dt;
	}

	public void setSn_apr_dt(Vector sn_apr_dt) {
		this.sn_apr_dt = sn_apr_dt;
	}

	public Vector getLoa_apr_by() {
		return loa_apr_by;
	}

	public void setLoa_apr_by(Vector loa_apr_by) {
		this.loa_apr_by = loa_apr_by;
	}

	public Vector getLoa_apr_dt() {
		return loa_apr_dt;
	}

	public void setLoa_apr_dt(Vector loa_apr_dt) {
		this.loa_apr_dt = loa_apr_dt;
	}

	public Vector getLtcora_apr_by() {
		return ltcora_apr_by;
	}

	public void setLtcora_apr_by(Vector ltcora_apr_by) {
		this.ltcora_apr_by = ltcora_apr_by;
	}

	public Vector getLtcora_apr_dt() {
		return ltcora_apr_dt;
	}

	public void setLtcora_apr_dt(Vector ltcora_apr_dt) {
		this.ltcora_apr_dt = ltcora_apr_dt;
	}

	public Vector getCn_apr_by() {
		return cn_apr_by;
	}

	public void setCn_apr_by(Vector cn_apr_by) {
		this.cn_apr_by = cn_apr_by;
	}

	public Vector getCn_apr_dt() {
		return cn_apr_dt;
	}

	public void setCn_apr_dt(Vector cn_apr_dt) {
		this.cn_apr_dt = cn_apr_dt;
	}
//////////////////////////////////////////SB20210129//////////////////////////////////////////
	String Deal_Ref_No = ""; //SB20210129
	public String getDeal_Ref_No() {return Deal_Ref_No;}
	String Deal_Cust_AbbrNm = ""; //SB20210129
	public String getDeal_Cust_AbbrNm() {return Deal_Cust_AbbrNm;}
	String Deal_Start_Dt = ""; //SB20210129
	public String getDeal_Start_Dt() {return Deal_Start_Dt;}
	String Deal_End_Dt = ""; //SB20210129
	public String getDeal_End_Dt() {return Deal_End_Dt;}
	String Deal_Entered_By = ""; //SB20210129
	public String getDeal_Entered_By() {return Deal_Entered_By;}
	String Deal_Entered_Dt = ""; //SB20210129
	public String getDeal_Entered_Dt() {return Deal_Entered_Dt;}
	
/////////////////////////////////////////^^^^^/SB20210129//////////////////////////////////////////
	
	Vector VCont_Start_Dt2 = new Vector(); //SB20200901 Not in Use
	public Vector getVCont_Start_Dt2() {return VCont_Start_Dt2;}
	Vector VCont_End_Dt2 = new Vector(); //SB20200901 Not in Use
	public Vector getVCont_End_Dt2() {return VCont_End_Dt2;}
	Vector VSN_NO2 = new Vector(); //SB20200901 Not in Use
	public Vector getVSN_NO2() {return VSN_NO2;}
	Vector VSN_REV_NO2 = new Vector(); //SB20200901 Not in Use
	public Vector getVSN_REV_NO2() {return VSN_REV_NO2;}
	Vector VFGSA_NO2 = new Vector(); //SB20200901 Not in Use
	public Vector getVFGSA_NO2() {return VFGSA_NO2;}
	Vector VFGSA_REV_NO2 = new Vector(); //SB20200901 Not in Use
	public Vector getVFGSA_REV_NO2() {return VFGSA_REV_NO2;}
	Vector VSIGNING_DT2 = new Vector(); //SB20200901 Not in Use
	public Vector getVSIGNING_DT2() {return VSIGNING_DT2;}
	Vector VSN_REF_NO2 = new Vector(); //SB20200901 Not in Use
	public Vector getVSN_REF_NO2() {return VSN_REF_NO2;}
	Vector VCUSTOMER_CD2 = new Vector(); //SB20200901 Not in Use
	public Vector getVCUSTOMER_CD2() {return VCUSTOMER_CD2;}
	Vector VCUSTOMER_NM2 = new Vector(); //SB20200901 Not in Use
	public Vector getVCUSTOMER_NM2() {return VCUSTOMER_NM2;}
	Vector VCUSTOMER_ABBR2 = new Vector(); //SB20200901 Not in Use
	public Vector getVCUSTOMER_ABBR2() {return VCUSTOMER_ABBR2;}
	Vector VRATE2 = new Vector(); //SB20200901 Not in Use
	public Vector getVRATE2() {return VRATE2;}
	Vector VContType2 = new Vector(); //SB20200901 Not in Use
	public Vector getVContType2() {return VContType2;}
	Vector VMAPPING_ID2 = new Vector(); //SB20200901 Not in Use
	public Vector getVMAPPING_ID2() {return VMAPPING_ID2;}
	Vector VCARGO_ARRIVAL_DATE2 = new Vector(); //SB20200901 Not in Use
	public Vector getVCARGO_ARRIVAL_DATE2() {return VCARGO_ARRIVAL_DATE2;}
	Vector VDealPriceCurve2 = new Vector(); //SB20200901 Not in Use
	public Vector getVDealPriceCurve2() {return VDealPriceCurve2;}
	Vector VDealPhysCurve2 = new Vector(); //SB20200901 Not in Use
	public Vector getVDealPhysCurve2() {return VDealPhysCurve2;}
	Vector VPriceType2 = new Vector(); //SB20200901 Not in Use
	public Vector getVPriceType2() {return VPriceType2;}
	Vector VBuySell2 = new Vector(); //SB20200901 Not in Use
	public Vector getVBuySell2() {return VBuySell2;}
	public Vector VExpoFreezeeStatus2 = new Vector();
	public Vector getVExpoFreezeeStatus2() {return VExpoFreezeeStatus2;} //SB20210201
	public Vector VExpoFreezeeStatus2LastDt = new Vector();
	public Vector getVExpoFreezeeStatus2LastDt() {return VExpoFreezeeStatus2LastDt;} //SB20210201
	
	String EoD_Start_Dt = ""; //SB20210528
	public String getEoD_Start_Dt() {return EoD_Start_Dt;} //SB20210528
	String EoD_End_Dt = ""; //SB20210528
	public String getEoD_End_Dt() {return EoD_End_Dt;} //SB20210528
	
	Vector DEAL_SIGN_DT_HP = new Vector(); //HARSH20210728
	public Vector getDEAL_SIGN_DT_HP() {return DEAL_SIGN_DT_HP;}
	
	Vector CUSTOMER_CD_HP = new Vector(); //HARSH20210728
	public Vector getCUSTOMER_CD_HP() {return CUSTOMER_CD_HP;}
	
	Vector CUSTOMER_ABBR_HP = new Vector(); //HARSH20210728
	public Vector getCUSTOMER_ABBR_HP() {return CUSTOMER_ABBR_HP;}
	
	Vector SN_NO_HP = new Vector(); //HARSH20210728
	public Vector getSN_NO_HP() {return SN_NO_HP;}
	
	Vector SN_REV_NO_HP = new Vector();	//HARSH20210728 
	public Vector getSN_REV_NO_HP() {return SN_REV_NO_HP;}
	
	Vector FGSA_NO_HP = new Vector();	//HARSH20210728 
	public Vector getFGSA_NO_HP() {return FGSA_NO_HP;}
	
	Vector FGSA_REV_NO_HP = new Vector();	//HARSH20210728
	public Vector getFGSA_REV_NO_HP() {return FGSA_REV_NO_HP;}
	
	Vector VBuySell_HP = new Vector();	//HARSH20210728
	public Vector getVBuySell_HP() {return VBuySell_HP;}
	
	Vector VContType_HP = new Vector();	//HARSH20210728
	public Vector getVContType_HP() {return VContType_HP;}
	
	Vector VPriceType_HP = new Vector();	//HARSH20210728
	public Vector getVPriceType_HP() {return VPriceType_HP;}

	Vector VPriceFormulaRemark = new Vector();
	public Vector getVPriceFormulaRemark() {return VPriceFormulaRemark;}
}//End Of Class DataBean_Cargo ...