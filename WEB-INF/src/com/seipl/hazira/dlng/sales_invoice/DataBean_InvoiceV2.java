package com.seipl.hazira.dlng.sales_invoice;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.seipl.hazira.dlng.util.RuntimeConf;
//import com.sun.xml.internal.bind.v2.runtime.output.StAXExStreamWriterOutput;

public class DataBean_InvoiceV2 {
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
	String billCycle = "0";
	String callFlag = "";
	public String tcqflag="";
	String methodName = "";
	String databeanName = "DataBean_Invoice";
	String period_start_dt = "";
	String period_end_dt = "";
	String mappid="";	//BK20160331
	String flag="N"; 
	String remark = "";
	Vector fy_yr  = new Vector();
	int gst_eff_dt = 20170701;
	boolean date_flag = false;
	String maxYear="";
	
	Vector flagqty=new Vector();
	Vector VRemark=new Vector();
	Vector VcrNewInvSeqNo=new Vector();
	
	public Vector invoice_Customer_Cd = new Vector();
	public Vector invoice_Customer_Abbr = new Vector();
	public Vector invoice_Customer_Plant_Seq_No = new Vector();
	public Vector invoice_Customer_Plant_Nm = new Vector();
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
	public Vector invoice_total_qty_mmbtu = new Vector(); //SB20111207
	public Vector invoice_temp_Tcq_mmbtu  = new Vector(); //SB20111207
	public Vector invoice_Due_Dt = new Vector();
	public Vector invoice_Exchg_Rate_Calculation_Method = new Vector();
	public Vector invoice_Exchg_Rate_Cd = new Vector();
	public Vector invoice_adjust_flag = new Vector();
	public Vector invoice_HLPL_Seq_No = new Vector();
	public Vector cust_loaded_truck = new Vector();//Hiren_20200313
	public Vector cust_loaded_truck_cd = new Vector();//Hiren_20200316
	public Vector cust_loaded_truck_cnt = new Vector();//Hiren_20200313
	Vector VPDF_Contact_person_cd=new Vector();
	public Vector hlpl_Invoice_Seq_No_Arr = new Vector();
	public Vector hlpl_Invoice_Financial_Year_Arr = new Vector();
	public Vector hlpl_Invoice_Actual_Seq_No = new Vector();
	public Vector customer_Invoice_Actual_Seq_No = new Vector();
	public Vector inv_Checked_Flag = new Vector();
	public Vector inv_Checked_By = new Vector();
	public Vector inv_Authorized_Flag = new Vector();
	public Vector inv_Authorized_By = new Vector();
	public Vector inv_Approved_Flag = new Vector();
	public Vector inv_Approved_By = new Vector();
	public Vector inv_Gross_Amt_USD = new Vector();
	public Vector inv_Gross_Amt_INR = new Vector();
	public Vector inv_Exchg_Rate_CD = new Vector();
	public Vector inv_Offspec_Rate = new Vector();
	Vector invoice_Mismatch_flag = new Vector();
	Vector invoice_Mismatch_dates = new Vector();
	Vector VPDF_File_Nm=new Vector(); 
	Vector VPDF_Inv_Dt=new Vector(); 
	Vector VPDF_due_dt=new Vector();
	Vector VCustContPlantStDt=new Vector(); 
	Vector VCustContPlantStDt_Flag=new Vector(); 
	Vector VCustContPlantCrDrAprv_Flag=new Vector(); //SB20160623
	
	public Vector getVCustContPlantStDt() {return VCustContPlantStDt;}
	public Vector getVCustContPlantStDt_Flag() {return VCustContPlantStDt_Flag;}//SB20160623
	public Vector getVCustContPlantCrDrAprv_Flag() {return VCustContPlantCrDrAprv_Flag;}//SB20160623
	
	Vector new_Invoice_Seq_No = new Vector(); 
	public Vector invoice_Mapping_Id = new Vector(); //NB20141031
	Vector invoice_pre_aprv=new Vector();
	public Vector invoice_agr_base = new Vector();
	Vector invoice_tax_adj=new Vector();
	public Vector Invoice_Pending_approval=new Vector();
	Vector tax_adj_flag_pdf=new Vector();
	Vector DelFlag=new Vector();
	Vector invoice_inv_date=new Vector();
	Vector invoice_inv_period_dt=new Vector(); //HS20160620
	Vector Vdrcrflag=new Vector();
	Vector pdf_color= new Vector();
	Vector customer_invoice_pdf_path=new Vector();
	Vector customer_invoice_pdf_lock_flag=new Vector();
	Vector customer_invoice_pdf_path_flag=new Vector();
	String invoice_bench_date="10/03/2015";
	String invoice_bench_date1="04/07/2015";
	
	Vector VCrInvSeqNo=new Vector();		//SB20160601
	Vector VCrContType=new Vector();		//SB20160601
	Vector VCrFY=new Vector();	
	Vector Vinvseqno=new Vector();
	Vector Vctype=new Vector();
	Vector VDrcrCriteria=new Vector();
	String drcrcriteria="";
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
	
	Map credit_map = new HashMap();
	Map invoice_view_signed_pdf=new HashMap();
	Map invoice_mail_sent=new HashMap();
	public HttpServletRequest request = null;
	public Vector TCQ=new Vector();
	public Vector AllocatedQty=new Vector();
	public Vector RemainingQty=new Vector();
	public String cutoffDate="";
	public String attachment1flag="";
	Vector cuttDate=new Vector();
	Vector VBalanceQty_mmbtu=new Vector();		//SB20160603 
	public Vector getVBalanceQty_mmbtu() {return VBalanceQty_mmbtu;}//SB20160603
	Vector VInv_AllocatedQty_mmbtu=new Vector();		//SB20160604 
	public Vector getVInv_AllocatedQty_mmbtu() {return VInv_AllocatedQty_mmbtu;}//SB20160604
	Vector VInv_Mapping_Id=new Vector();		//SB20160604 
	public Vector getVInv_Mapping_Id() {return VInv_Mapping_Id;}
	public String total_Invoice_Qty = "";
	Vector Vdue_dt_flag = new Vector();		
	
	NumberFormat nf = new DecimalFormat("###########0.00");
	NumberFormat nf2 = new DecimalFormat("##0.0000"); //For Currency Purpose ... Defined By Samik Shah On 1st June, 2010 ...
	NumberFormat nf3 = new DecimalFormat("###,###,###,##0.00");
	NumberFormat nf4 = new DecimalFormat("##0.000");
	NumberFormat nf5 = new DecimalFormat("##########0");
	
	public void init(){
		try{
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
	    			
	    			if(callFlag.equalsIgnoreCase("FetchMaxYear"))
	    			{
	    				fetchMaxYear();
	    			}
	    			
	    			if(callFlag.equalsIgnoreCase("SALES_INVOICE_PREPERATION"))
					{
	    				fetchInvoicePreperationDetails();
	    				if(tcqflag.equalsIgnoreCase("Calculate"))
	    				{
//	    					fetchTotalQuantity(); Hiren_20201120
	    				}
					}
	    			
	    			conn.close();
	    			conn = null;
	    		}
	    	}	
	    }catch(Exception e)
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
					////System.out.println("rset is not close "+e);
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
					////System.out.println("rset1 is not close "+e);
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
					////System.out.println("rset2 is not close "+e);
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
					////System.out.println("rset3 is not close "+e);
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
					////System.out.println("rset4 is not close "+e);
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
					////System.out.println("rset5 is not close "+e);
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
					////System.out.println("rset6 is not close "+e);
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
					////System.out.println("stmt is not close "+e);
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
					////System.out.println("stmt1 is not close "+e);
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
					////System.out.println("stmt2 is not close "+e);
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
					////System.out.println("stmt3 is not close "+e);
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
					////System.out.println("stmt4 is not close "+e);
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
					////System.out.println("stmt5 is not close "+e);
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
					////System.out.println("stmt6 is not close "+e);
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
					////System.out.println("conn is not close "+e);
				}
			}
	    }
	}
	
	Map invoice_signded_pdf=new HashMap();//HS20160604
	Map invoice_view_pdf=new HashMap();
	
	private void PDFFileNmForInvoice() {

		try
		{}
		catch(Exception e)
		{
			////System.out.println("Exception in createPdfFileForInvoice() Method :\n"+e.getMessage());
			e.printStackTrace();
		}
		//SB return invoice_pdf_path;
	
		
	}
	private void fetchTotalQuantity() {
		
		System.out.println(invoice_Contract_Type.size()+"**********"+invoice_Customer_Cd.size());
		try
		{
			for(int i=0; i<invoice_Customer_Cd.size(); i++)
	  		{
				String inv_map_id = invoice_Customer_Cd.elementAt(i)+"-"+invoice_FGSA_No.elementAt(i)+"-"+invoice_FGSA_Rev_No.elementAt(i)+"-"+invoice_SN_No.elementAt(i)+"-"+invoice_SN_Rev_No.elementAt(i);
				
//				System.out.println("invoice_Contract_Type****"+invoice_Contract_Type);
				if(invoice_Contract_Type.elementAt(i).toString().equalsIgnoreCase("S"))
				{
					String query="SELECT TO_CHAR(START_DT,'DD/MM/YYYY'), TO_CHAR(END_DT,'DD/MM/YYYY'), TCQ , RATE, " +
							" nvl(TCQ_REQUEST_FLAG,'N'), nvl(TCQ_REQUEST_SIGN,'+'), nvl(TCQ_REQUEST_QTY,'0') FROM DLNG_SN_MST" +
							" WHERE CUSTOMER_CD='"+invoice_Customer_Cd.elementAt(i)+"'  AND SN_REV_NO='"+invoice_SN_Rev_No.elementAt(i)+"' " +
							" AND SN_NO='"+invoice_SN_No.elementAt(i)+"' " +
							" AND FLSA_REV_NO='"+invoice_FGSA_Rev_No.elementAt(i)+"' AND FLSA_NO='"+invoice_FGSA_No.elementAt(i)+"'";
//					System.out.println("FETCHING TCQ FOR SN-"+query);
					rset=stmt.executeQuery(query);
					String tmptcq="0";
					String flag="";
					String sign="";
					String qty="";
					if(rset.next())
					{
						tmptcq=rset.getString(3)==null?"0":rset.getString(3);
						flag=rset.getString(5);
						sign=rset.getString(6);
						qty=rset.getString(7);	
						
						if(tmptcq.equals("0")) {
							tmptcq="0";
						}
						TCQ.add(tmptcq==""?"0.00":tmptcq); //HS20160615
						
					}else {
						
						TCQ.add("0.00"); //HS20160615
					}					
				}
				else if(invoice_Contract_Type.elementAt(i).toString().equalsIgnoreCase("L"))
				{
					String tmptcq="0";
					
					String query="SELECT TO_CHAR(START_DT,'DD/MM/YYYY'), TO_CHAR(END_DT,'DD/MM/YYYY'), TCQ, " +
							" RATE FROM DLNG_LOA_MST "+ 
							" WHERE CUSTOMER_CD='"+invoice_Customer_Cd.elementAt(i)+"' AND LOA_REV_NO='"+invoice_SN_Rev_No.elementAt(i)+"' " +
							" AND LOA_NO='"+invoice_SN_No.elementAt(i)+"' AND TENDER_NO='"+invoice_FGSA_No.elementAt(i)+"' ";
//					System.out.println("FETCHING TCQ FOR LOA-"+query);
					rset=stmt.executeQuery(query);
					if(rset.next())
					{
						tmptcq=rset.getString(3)==null?"0":rset.getString(3);
						if(tmptcq.equals("0")) {
							tmptcq="0";
						}
						TCQ.add(tmptcq==""?"0.00":tmptcq);
					}else{
						TCQ.add("0.00"); //HS20160615
					}	
				}
				
				String query1="SELECT NVL(SUM(ENTRY_TOT_ENE),'0') FROM DLNG_ALLOC_MST " +
				" WHERE MAPPING_ID = '"+inv_map_id+"' "+
				" AND GAS_DT>=TO_DATE('"+invoice_SN_Start_Dt.elementAt(i)+"','DD/MM/YYYY') " +
				" AND GAS_DT<=TO_DATE('"+invoice_SN_End_Dt.elementAt(i)+"','DD/MM/YYYY')";
			//	////System.out.println("ALOCATED QTY--"+query1);
				rset1=stmt1.executeQuery(query1);		
				if(rset1.next())
				{
					String tmpqty=rset1.getString(1)==null?"0":rset1.getString(1);//-Double.parseDouble(rset1.getString(2))+"";
					if(tmpqty.equals("")) {
						tmpqty="0";
					}
					AllocatedQty.add(tmpqty);
				}else {
					AllocatedQty.add("0");
				}
				
//				System.out.println("TCQ***"+TCQ.size()+"**AllocatedQty***"+AllocatedQty.size());
//				System.out.println("TCQ.elementAt(i)****"+TCQ.elementAt(i)+"AllocatedQty.elementAt(i)*****"+AllocatedQty.elementAt(i));
				String tmpremaininngqty=Double.parseDouble(TCQ.elementAt(i).toString())-Double.parseDouble(AllocatedQty.elementAt(i).toString())+"";
				RemainingQty.add(tmpremaininngqty);
				//////System.out.println("TOTAL---"+TCQ.elementAt(i)+"--"+AllocatedQty.elementAt(i)+"REMAINING---"+tmpremaininngqty);
					
				if(RemainingQty.elementAt(i).equals("0.0"))
				{
					//systemout.println("INSIDE--");
					String query2="SELECT to_char(max(gas_dt),'dd/mm/yyyy') FROM DLNG_ALLOC_MST " +
					" WHERE MAPPING_ID = '"+inv_map_id+"' "+
					" AND GAS_DT>=TO_DATE('"+invoice_SN_Start_Dt.elementAt(i)+"','DD/MM/YYYY') " +
					" AND GAS_DT<=TO_DATE('"+invoice_SN_End_Dt.elementAt(i)+"','DD/MM/YYYY')";
			//		////System.out.println("MAX-DT-of-ALOCATED QTY--"+query2);
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
			//	////System.out.println("INV-LIST:FMS7_CUSTOMER_PLANT_DTL: "+query);
				rset=stmt.executeQuery(query);
				if(rset.next())
				{
					plant_no=(rset.getString(1));
				}
				
				String Inv_MappingId =invoice_Customer_Cd.elementAt(i)+"-"+invoice_Contract_Type.elementAt(i)+"-"+plant_no; //SB20160606
				String inv_alloc_id = invoice_Customer_Cd.elementAt(i)+"-"+invoice_FGSA_No.elementAt(i)+"-"+invoice_FGSA_Rev_No.elementAt(i)+"-"+invoice_SN_No.elementAt(i)+"-"+invoice_SN_Rev_No.elementAt(i)+"-"+invoice_SN_No.elementAt(i)+"-"+plant_no;
				
				queryString1 = "SELECT NVL(SUM(ENTRY_TOT_ENE),'0') " +
						" FROM DLNG_ALLOC_MST " +
						" WHERE ALLOC_ID = '"+inv_alloc_id+"' "+
						" AND gas_dt>=TO_DATE('"+invoice_Bill_Period_Start_Dt.elementAt(i)+"','DD/MM/YYYY')"
						+ " AND gas_dt<=TO_DATE('"+invoice_Bill_Period_End_Dt.elementAt(i)+"','DD/MM/YYYY')"
						+ " AND ENTRY_TOT_ENE>=0.99 ORDER BY gas_dt DESC";	
			//	////System.out.println("Bill-ALOCATED QTY: "+queryString1);
				rset1=stmt1.executeQuery(queryString1);	
				while(rset1.next())
				{
					String tmp_Bill_qty=rset1.getString(1);//-Double.parseDouble(rset1.getString(2))+"";
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
	String from_dt = "";
	String to_dt ="";
	private void fetchInvoicePreperationDetails()throws SQLException,IOException {
	
		HttpSession sess = request.getSession();
		methodName = "fetchInvoicePreperationDetails()";
		try 
		{
			String period_end_dt_FIXED = ""; //SB20110920
			
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
			Vector temp_agr_base = new Vector();
			Vector temp_apprv=new Vector();
			Vector temp_Exchg_Rate_Cd = new Vector();
			Vector temp_Tcq = new Vector();
			Vector temp_due_days = new Vector();
			
			queryString = "SELECT A.sn_no,A.sn_rev_no,A.flsa_no,A.flsa_rev_no,A.customer_cd," +
						  "TO_CHAR(A.start_dt,'DD/MM/YYYY'),TO_CHAR(A.end_dt,'DD/MM/YYYY'),NVL(tcq,'0')+NVL(VARIATION_QTY,'0') AS T_tcq " +
						  "FROM DLNG_SN_MST A WHERE A.start_dt IS NOT NULL AND A.end_dt IS NOT NULL AND " +
						  "((A.start_dt>=TO_DATE('"+period_start_dt+"','DD/MM/YYYY') AND " +
						  "A.start_dt<=TO_DATE('"+period_end_dt+"','DD/MM/YYYY')) OR " +
						  "(A.end_dt>=TO_DATE('"+period_start_dt+"','DD/MM/YYYY') AND " +
						  "A.end_dt<=TO_DATE('"+period_end_dt+"','DD/MM/YYYY')) OR " +
						  "(A.start_dt<=TO_DATE('"+period_start_dt+"','DD/MM/YYYY') AND " +
						  "A.end_dt>=TO_DATE('"+period_end_dt+"','DD/MM/YYYY'))) AND " +
						  "A.sn_rev_no=(SELECT MAX(B.sn_rev_no) FROM DLNG_SN_MST B WHERE " +
						  "A.sn_no=B.sn_no AND A.flsa_no=B.flsa_no AND A.flsa_rev_no=B.flsa_rev_no AND " +
						  "A.customer_cd=B.customer_cd AND " +
						  "((B.start_dt>=TO_DATE('"+period_start_dt+"','DD/MM/YYYY') AND " +
						  "B.start_dt<=TO_DATE('"+period_end_dt+"','DD/MM/YYYY')) OR " +
						  "(B.end_dt>=TO_DATE('"+period_start_dt+"','DD/MM/YYYY') AND " +
						  "B.end_dt<=TO_DATE('"+period_end_dt+"','DD/MM/YYYY')) OR " +
						  "(B.start_dt<=TO_DATE('"+period_start_dt+"','DD/MM/YYYY') AND " +
						  "B.end_dt>=TO_DATE('"+period_end_dt+"','DD/MM/YYYY')))) " +
						  "ORDER BY A.customer_cd,A.flsa_no,A.flsa_rev_no,A.sn_no";
//			System.out.println("STEP-1:DLNG_SN_MST: "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				String customer_abbr = "";
				String bill_period_start_dt = "";
				String bill_period_end_dt = "";
				String bill_period_end_dt_WFM = "";//SB20200729 : Weekly/Fortnightly/Monthly
				String bill_period_end_dt_FLAG = "";//SB20200729 : Y: before mth end
				String due_dt = "";
				int due_days = 0;
				queryString3 = "SELECT customer_abbr FROM FMS7_CUSTOMER_MST WHERE customer_cd="+rset.getString(5)+"";
				////systemout.println("Query To FindOut Customer Abbriviation = "+queryString3);
				rset3 = stmt3.executeQuery(queryString3);
				if(rset3.next())
				{
					customer_abbr = rset3.getString(1)==null?"":rset3.getString(1);
				}
				from_dt=bill_period_start_dt;	//BK20160331
				to_dt=bill_period_end_dt;
//				System.out.println("from_dt******"+from_dt);
				queryString3 = "SELECT TO_DATE('"+rset.getString(6)+"','DD/MM/YYYY') - TO_DATE('"+period_start_dt+"','DD/MM/YYYY') FROM DUAL";
				//System.out.println("Query To FindOut Days Difference Between Two Start Dates = "+queryString3);
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
				String LastDay=""; String ContLastDay="";
				queryString3 = "SELECT TO_CHAR(LAST_DAY(TO_DATE('"+period_end_dt+"','DD/MM/YYYY')),'DD/MM/YYYY') FROM DUAL";
				//System.out.println("Query To FindOut Days Difference Between Two End Dates = "+queryString3);
				rset3 = stmt3.executeQuery(queryString3);
				if(rset3.next())
					{
						LastDay=rset3.getString(1);
					}
				ContLastDay=rset.getString(7);
				
				queryString3 = "SELECT TO_DATE('"+rset.getString(7)+"','DD/MM/YYYY') - TO_DATE('"+period_end_dt+"','DD/MM/YYYY') FROM DUAL";
				////System.out.println("Query To FindOut Days Difference Between Two End Dates = "+queryString3);
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
						bill_period_end_dt_WFM=bill_period_end_dt; //SB20200729
						
					}
				}
//				//System.out.println("period_start_dt*****"+period_start_dt);
				////////////SB20200729: r Billing end Date///////////////////
				String Temp_Bill_dt[] = period_start_dt.split("/");
				String Bill_day = Temp_Bill_dt[0]; String Bill_mth = Temp_Bill_dt[1]; String Bill_yr = Temp_Bill_dt[2];
//				 //System.out.println("bill_period_end_dt_FLAG"+bill_period_end_dt_FLAG);
//				//System.out.println("bill_period_end_dt"+bill_period_end_dt);
				////////////////////////////////////////////////////////////
//				if(billCycle.equals("1") || billCycle.equals("2"))
//				{
					queryString1 = "SELECT NVL(billing_freq,'F'),NVL(due_date,'0'),NVL(exchng_rate_cd,'0'),NVL(exchng_rate_cal,'D') " +
							" FROM DLNG_SN_BILLING_DTL " +
								   "WHERE cont_type='S' AND customer_cd="+rset.getString(5)+" AND " +
								   "flsa_no="+rset.getString(3)+" "
//								   + " AND flsa_rev_no="+rset.getString(4)+" "
								   + " AND sn_no="+rset.getString(1)+" ";
//								   + " AND sn_rev_no="+rset.getString(2)+"";					
//					System.out.println("STEP-2: DLNG_SN_BILLING_DTL: "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);					
					if(rset1.next())
					{
						///////////////////////////////////
						if(rset1.getString(1).equalsIgnoreCase("F"))
						{
							
							if(Integer.parseInt(Bill_day)<=15) 
								bill_period_end_dt_WFM="15/"+Bill_mth+"/"+Bill_yr;//Bill_day ="15";
							
							if(Integer.parseInt(Bill_day)>15) {
								
								queryString3 = "SELECT TO_CHAR(LAST_DAY(TO_DATE('"+period_start_dt+"','DD/MM/YYYY')),'DD/MM/YYYY') FROM DUAL";
//									System.out.println("Query To FindOut Days Difference Between Two End Dates = "+queryString3);
									rset3 = stmt3.executeQuery(queryString3);
									if(rset3.next())
										{
											bill_period_end_dt_WFM=rset3.getString(1);
										}
								}
						
//							System.out.println("ContLastDay***"+ContLastDay);
							queryString3 = "SELECT TO_DATE('"+LastDay+"','DD/MM/YYYY') - TO_DATE('"+ContLastDay+"','DD/MM/YYYY') FROM DUAL";
//							System.out.println("FIND DATE GAP: = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								if(Integer.parseInt(rset3.getString(1))>0 && (Integer.parseInt(Bill_day)>15))
								{						
									bill_period_end_dt_WFM=ContLastDay;
								}
							}
						}
						
//						System.out.println("bill_period_end_dt_WFM*****"+bill_period_end_dt_WFM);
						/////////////////////////////////////////
						
							due_days = Integer.parseInt(rset1.getString(2));
							
							/*queryString3 = "SELECT TO_CHAR(TO_DATE('"+bill_period_end_dt_WFM+"','DD/MM/YYYY') + "+due_days+",'DD/MM/YYYY') FROM DUAL";
							System.out.println("STEP-2A: DUAL: "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								temp_Due_Dt.add(rset3.getString(1));
							}
							else
							{
								temp_Due_Dt.add(bill_period_end_dt);
							}*/
							
							
							temp_Due_Dt.add(bill_period_end_dt_WFM);
							
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
							temp_due_days.add(due_days);
//							System.out.println("temp_Due_Dt****"+temp_Due_Dt);
						
					}
					else
					{}
//				}
			}//while end
			Vector temp_file_nm=new Vector();
//			System.out.println("temp_Customer_Cd****"+temp_Customer_Cd);
			
			for(int i=0;i<temp_Customer_Cd.size();i++)
			{
				/*queryString = "SELECT DISTINCT(plant_seq_no) FROM DLNG_DAILY_ALLOCATION_DTL " +
							  "WHERE customer_cd="+temp_Customer_Cd.elementAt(i)+" AND " +
							  "fgsa_no="+temp_FGSA_No.elementAt(i)+" AND " +
							  "fgsa_rev_no="+temp_FGSA_Rev_No.elementAt(i)+" AND " +
							  "sn_no="+temp_SN_No.elementAt(i)+" AND " +
							  "sn_rev_no="+temp_SN_Rev_No.elementAt(i)+" AND " +
							  "contract_type='S' AND " +
							  "gen_dt>=TO_DATE('"+temp_Bill_Period_Start_Dt.elementAt(i)+"','DD/MM/YYYY') AND " +
							  "gen_dt<=TO_DATE('"+temp_Bill_Period_End_Dt.elementAt(i)+"','DD/MM/YYYY') " +
							  "ORDER BY plant_seq_no";*/
//				String temp_map_id=temp_Customer_Cd.elementAt(i)+"-"+temp_FGSA_No.elementAt(i)+"-"+temp_FGSA_Rev_No.elementAt(i)+"-"+temp_SN_No.elementAt(i)+"-"+temp_SN_Rev_No.elementAt(i);
				String temp_map_id=temp_Customer_Cd.elementAt(i)+"-"+temp_FGSA_No.elementAt(i)+"-%-"+temp_SN_No.elementAt(i)+"-%";
				
//				System.out.println("temp_map_id****"+temp_map_id);
				queryString = "SELECT DISTINCT(ALLOC_ID) FROM DLNG_ALLOC_MST " +
						  " WHERE MAPPING_ID like '"+temp_map_id+"'"
						 + " AND  alloc_dt>=TO_DATE('"+temp_Bill_Period_Start_Dt.elementAt(i)+"','DD/MM/YYYY') "
						 + " AND alloc_dt<=TO_DATE('"+temp_Bill_Period_End_Dt.elementAt(i)+"','DD/MM/YYYY') "
						 + " AND CONTRACT_TYPE='S' ORDER BY ALLOC_ID";
				
//				System.out.println("STEP2: Plant Seq NO For SN LIST = "+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next()) {
					
					String plant_seq_no = "";
					if(rset.getString(1).contains("-")) {
						String temp_plant[] = rset.getString(1).split("-");
						plant_seq_no = temp_plant[6];
					}
					int due_days =0;
					
					invoice_Customer_Cd.add(""+temp_Customer_Cd.elementAt(i));
					invoice_Customer_Abbr.add(""+temp_Customer_Abbr.elementAt(i));
					invoice_Customer_Plant_Seq_No.add(plant_seq_no);
					invoice_FGSA_No.add(""+temp_FGSA_No.elementAt(i));
					invoice_FGSA_Rev_No.add(""+temp_FGSA_Rev_No.elementAt(i));
					invoice_SN_No.add(""+temp_SN_No.elementAt(i));
					invoice_SN_Rev_No.add(""+temp_SN_Rev_No.elementAt(i));
					invoice_Contract_Type.add(""+temp_Contract_Type.elementAt(i));
					invoice_SN_Start_Dt.add(""+temp_SN_Start_Dt.elementAt(i));
					invoice_SN_End_Dt.add(""+temp_SN_End_Dt.elementAt(i));
					invoice_Bill_Period_Start_Dt.add(""+temp_Bill_Period_Start_Dt.elementAt(i));
					
					String bill_start_dt1 = "";
					String bill_end_dt1 = "";
					String bill_start_dt2 = "";
					String bill_end_dt2 = "";
					String bill_end_dt3 = "";
					String bill_end_dt4 = "";
					String plant_no = plant_seq_no;
					
					String tmp_bill_period_end_dt = ""+temp_Bill_Period_End_Dt.elementAt(i);
					period_end_dt_FIXED = tmp_bill_period_end_dt;  //SB20111206
					
					queryString3 = "SELECT TO_CHAR(tax_struct_dt,'DD/MM/YYYY') " +
							" FROM FMS7_CUSTOMER_TAX_STRUCT_DTL WHERE " +
								   "customer_cd="+temp_Customer_Cd.elementAt(i)+" AND plant_seq_no='"+plant_no+"' AND " +
								   "tax_struct_dt>TO_DATE('"+temp_Bill_Period_Start_Dt.elementAt(i)+"','DD/MM/YYYY') AND " +
								   "tax_struct_dt<=TO_DATE('"+temp_Bill_Period_End_Dt.elementAt(i)+"','DD/MM/YYYY')";
//					//System.out.println("===Query To FindOut TAX Structure Change Dates In-Between Invoice Billing Period = "+queryString3);
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
						//Following Whole Logic Is For Finding Last Allocation Date While Whole TCQ Has Been Occupied ...
						double total_qty_mmbtu = 0;
						bill_end_dt3 = "";
						queryString1 = "SELECT SUM(ENTRY_TOT_ENE) FROM DLNG_ALLOC_MST " +
									   " WHERE MAPPING_ID LIKE '"+temp_map_id+"' "
									 + " AND alloc_dt<=TO_DATE('"+temp_Bill_Period_End_Dt.elementAt(i)+"','DD/MM/YYYY')"
									 + " AND CONTRACT_TYPE='S'";						
						rset1 = stmt1.executeQuery(queryString1);
						if(rset1.next())
						{
							total_qty_mmbtu = rset1.getDouble(1);
						}						
						if(total_qty_mmbtu>=(Double.parseDouble(""+temp_Tcq.elementAt(i))-0.99))
						{
							queryString1 = "SELECT TO_CHAR(gas_dt,'dd/mm/yyyy') FROM DLNG_ALLOC_MST  " +
										   "WHERE ALLOC_ID like '"+temp_Customer_Cd.elementAt(i)+"-"+temp_FGSA_No.elementAt(i)+"-%-"+temp_SN_No.elementAt(i)+"-%-%-"+plant_no+"' "
										+ " AND ALLOC_DT>=TO_DATE('"+temp_Bill_Period_Start_Dt.elementAt(i)+"','DD/MM/YYYY') "
										+ " AND ALLOC_DT<=TO_DATE('"+temp_Bill_Period_End_Dt.elementAt(i)+"','DD/MM/YYYY') "
										+ " AND ENTRY_TOT_ENE>=0.99"
										 + " AND CONTRACT_TYPE='S'  ORDER BY gas_dt DESC";							
							rset1 = stmt1.executeQuery(queryString1);
							if(rset1.next())
							{
								bill_end_dt3 = rset1.getString(1)==null?"":rset1.getString(1);
								
								if(!bill_end_dt3.trim().equals(""))
								{
									tmp_bill_period_end_dt = bill_end_dt3;
									
									queryString2 = "SELECT NVL(billing_freq,'F'),NVL(due_date,'0'),NVL(exchng_rate_cd,'0'),NVL(exchng_rate_cal,'D') " +
											" FROM DLNG_SN_BILLING_DTL " +
												   "WHERE cont_type='S' AND " +
												   "customer_cd="+temp_Customer_Cd.elementAt(i)+" AND " +
												   "flsa_no="+temp_FGSA_No.elementAt(i)+" AND " +
												   "sn_no="+temp_SN_No.elementAt(i)+" " +
												   "ORDER BY sn_rev_no DESC";
//									System.out.println("queryString2***"+queryString2);
									rset2 = stmt2.executeQuery(queryString2);
									if(rset2.next())
									{
										due_days = Integer.parseInt(rset2.getString(2));
											
										queryString3 = "SELECT TO_CHAR(TO_DATE('"+bill_end_dt3+"','DD/MM/YYYY') + "+due_days+",'DD/MM/YYYY') FROM DUAL";
//										System.out.println("queryString3*****"+queryString3);
										rset3 = stmt3.executeQuery(queryString3);
										if(rset3.next())
										{
//											temp_Due_Dt.setElementAt(rset3.getString(1),i);   //Hiren_20201023 As discussed with mahesh sir.
										}
									}
								}
							}
						}
						
						queryString1 = "SELECT NVL(A.sn_ref_no,'') FROM DLNG_SN_MST A " +
									   "WHERE A.customer_cd="+temp_Customer_Cd.elementAt(i)+" " +
									   "AND A.sn_no="+temp_SN_No.elementAt(i)+" " +
									   "AND A.flsa_no='"+temp_FGSA_No.elementAt(i)+"' " +
									   "AND A.sn_ref_no IS NOT NULL " +
									   "ORDER BY A.sn_rev_no DESC";
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
								//SB20200702	   "WHERE A.customer_cd="+temp_Customer_Cd.elementAt(i)+" AND A.seq_no="+rset.getString(1)+" " +
									   "WHERE A.customer_cd="+temp_Customer_Cd.elementAt(i)+" AND A.seq_no="+plant_no+" "+
									   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
									   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
									   "AND B.eff_dt<=TO_DATE('"+temp_Bill_Period_Start_Dt.elementAt(i)+"','DD/MM/YYYY'))";
						////System.out.println("Customer Plant Name: SN: = "+queryString1);
						rset1 = stmt1.executeQuery(queryString1);
						if(rset1.next())
						{
							invoice_Customer_Plant_Nm.add(rset1.getString(1));
						}
						else
						{
							invoice_Customer_Plant_Nm.add(" ");
						}
						
						queryString1 = "SELECT TO_CHAR(gas_dt,'dd/mm/yyyy') FROM DLNG_ALLOC_MST  " +
								   "WHERE MAPPING_ID like '"+temp_Customer_Cd.elementAt(i)+"-"+temp_FGSA_No.elementAt(i)+"-%-"+temp_SN_No.elementAt(i)+"-%' "
								+ " AND ALLOC_DT>=TO_DATE('"+temp_Bill_Period_Start_Dt.elementAt(i)+"','DD/MM/YYYY') "
								+ " AND ALLOC_DT<=TO_DATE('"+temp_Bill_Period_End_Dt.elementAt(i)+"','DD/MM/YYYY') "
								+ " AND ENTRY_TOT_ENE>=0.99 ORDER BY gas_dt DESC";
						
						/*queryString1 = "SELECT TO_CHAR(gas_dt,'dd/mm/yyyy') FROM DLNG_DAILY_ALLOCATION_DTL " +
						   "WHERE customer_cd="+temp_Customer_Cd.elementAt(i)+" AND " +
						   "fgsa_no="+temp_FGSA_No.elementAt(i)+" AND " +
						   "sn_no="+temp_SN_No.elementAt(i)+" AND " +
						   //"plant_seq_no="+plant_no+" AND " +
						   "contract_type='S' AND " +
						   "gen_dt>=TO_DATE('"+temp_Bill_Period_Start_Dt.elementAt(i)+"','DD/MM/YYYY') AND " +
						   "gen_dt<=TO_DATE('"+temp_Bill_Period_End_Dt.elementAt(i)+"','DD/MM/YYYY') AND " +
						   "qty_mmbtu>=0.99 ORDER BY gas_dt DESC";*/
							
						rset1 = stmt1.executeQuery(queryString1);
							if(rset1.next())
							{
								tmp_bill_period_end_dt = rset1.getString(1)==null?"":rset1.getString(1);
							}
							String clauseFlag="";
							queryString1 = "SELECT NVL(FLAG,'Y') FROM DLNG_SN_BILLING_DTL " +
							   "WHERE cont_type='S' AND " +
							   "customer_cd="+temp_Customer_Cd.elementAt(i)+" AND " +
							   "flsa_no="+temp_FGSA_No.elementAt(i)+" AND sn_no="+temp_SN_No.elementAt(i)+" " +
							   "ORDER BY sn_rev_no ASC";			
							////System.out.println("SN-CLAUSE: "+queryString1);
							rset1 = stmt1.executeQuery(queryString1);						
							while(rset1.next())
							{
								clauseFlag = rset1.getString(1);
							}
						
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
					/* ***Hiren_20201102 Filtering SUN from invoice due date calculation******* */	
						
						String WeeklyOff = "";
						String NextDate2 = temp_Due_Dt.elementAt(i)+"";
						int StratDayCount = 0;
//						System.out.println("NextDate2******"+NextDate2);
						String DueDate="";
//						System.out.println("temp_due_days***"+temp_due_days);
//						for(int k = 0 ; k < temp_due_days.size(); k++) {
							
							StratDayCount = 0;
							int foundHoliday=0;
							DueDate="";
							int nxt_cnt =0;
							String due_dt_flag = "";
							for (int j = 0 ; j < Integer.parseInt(temp_due_days.elementAt(i)+""); j++) {
								
								 queryString = "SELECT TO_CHAR(TO_DATE('"+NextDate2+"','DD/MM/YYYY') + "+(j+1)+",'DAY') FROM DUAL";
//								 System.out.println("Get DAY Name: "+queryString);
	                             rset5 = stmt5.executeQuery(queryString);
	                             if(rset5.next())
	                             {
	                                    WeeklyOff = rset5.getString(1);
	                             }
	                           
	                           String query1="select to_char(to_date('"+NextDate2+"','dd/mm/yyyy')+ "+(j+1)+",'dd/mm/yyyy') from dual";
		   	                   System.out.println("query1*****"+query1);
	   	                       rset5=stmt5.executeQuery(query1);
	   	                       if(rset5.next())
	   	                       {
	   	                    	   DueDate =rset5.getString(1);
	   	                       }
	   	                     //Hiren_20230123 checking DUE_DT_CALC_FLAG
	   	                     due_dt_flag = "";
	   	                     String dueDtFlagSql  = "select nvl(DUE_DT_FLAG,'B') from DLNG_SN_BILLING_DTL where"
	   	                     		+ " customer_cd = '"+temp_Customer_Cd.elementAt(i)+"' "
	   	                     		+ " AND flsa_no = '"+temp_FGSA_No.elementAt(i)+"' "
	   	                     		+ " AND flsa_rev_no = '"+temp_FGSA_Rev_No.elementAt(i)+"'"
	   	                     		+ " AND sn_no = '"+temp_SN_No.elementAt(i)+"'"
	   	                     		+ " AND SN_REV_NO = '"+temp_SN_Rev_No.elementAt(i)+"'"
	   	                     		+ " AND CONT_TYPE = '"+temp_Contract_Type.elementAt(i)+"'";
                            System.out.println("dueDtFlagSql****"+dueDtFlagSql);
                            rset1 = stmt1.executeQuery(dueDtFlagSql);
	                        if(rset1.next()) {
	                        	Vdue_dt_flag.add(rset1.getString(1)==null?"":rset1.getString(1));
	                        	due_dt_flag = rset1.getString(1)==null?"":rset1.getString(1);
	                        }else {
	                        	Vdue_dt_flag.add("");
	                        }
                            if(due_dt_flag.equalsIgnoreCase("B")) {
                            
		   	                    if(WeeklyOff.trim().equals("SUNDAY")) 
	                             {
	                                    StratDayCount++;
	                             }      
	//                             }else {
	                            	 
	                            	 
	//                            	 System.out.println("StratDayCount****"+StratDayCount);
	                                 String state_code="";
	        	                       String stateCd_sql = "select STATE_CODE from STATE_MST where "
	        	                       		+ " STATE_NM like (select plant_state from FMS7_CUSTOMER_PLANT_DTL where CUSTOMER_CD='"+temp_Customer_Cd.elementAt(i)+"' and SEQ_NO ='"+plant_seq_no+"'"
	        	                       				+ " and eff_dt= (select max(eff_dt) from FMS7_CUSTOMER_PLANT_DTL where CUSTOMER_CD='"+temp_Customer_Cd.elementAt(i)+"' and SEQ_NO ='"+plant_no+"' ))";
	        	                       rset1 = stmt1.executeQuery(stateCd_sql);
	//  	      	                       System.out.println("stateCd_sql***"+stateCd_sql);
	        	                       if(rset1.next()) {
	        	                    	   state_code = rset1.getString(1)==null?"":rset1.getString(1);
	        	                       }
	  	      	                       
	//  	      	                       System.out.println("state_code*****"+state_code);
	  	      	                    if(!state_code.equals("")) {
	  	   	                    	  
	  	      	                    String query2="select to_char(to_date('"+ DueDate+"','dd/mm/yyyy')+ "+StratDayCount+",'dd/mm/yyyy') from dual";
	//		   	                      System.out.println("query2*****"+query2);
			   	                       rset5=stmt5.executeQuery(query2);
			   	                       if(rset5.next())
			   	                       {
			   	                    	   DueDate =rset5.getString(1);
			   	                       }
			   	                       
	  	   	                    	  //first checking with National holiday
	  	   		                      String NH_sql = "select count(*) from DLNG_HOLIDAY_DTL where TO_CHAR(HOLIDAY_DT,'DD/MM/YYYY') = TO_CHAR(TO_DATE('"+DueDate+"','DD/MM/YYYY'),'dd/mm/yyyy') and STATE_CODE='0' and FLAG='Y' ";
	//  	   		                      System.out.println("NH_sql***"+NH_sql);
	  	   		                       rset5 = stmt5.executeQuery(NH_sql);
	  	   		                      if(rset5.next()) {
	  	   		                    	  foundHoliday = rset5.getInt(1); 
	  	   		                    	  nxt_cnt+=foundHoliday;
	  	   		                      }
	  	   		                      
	  	   		                      if(foundHoliday == 0) {
	  	   		                    	  String SH_sql = "select count(*) from DLNG_HOLIDAY_DTL where TO_CHAR(HOLIDAY_DT,'DD/MM/YYYY') = TO_CHAR(TO_DATE('"+DueDate+"','DD/MM/YYYY'),'dd/mm/yyyy') and STATE_CODE='"+state_code+"' and FLAG='Y' ";
	//  	   			                      System.out.println("SH_sql***"+SH_sql);
	  	   			                       rset5 = stmt5.executeQuery(SH_sql);
	  	   			                      if(rset5.next()) {
	  	   			                    	  foundHoliday = rset5.getInt(1);
	  	   			                    	  nxt_cnt+=foundHoliday;
	  	   			                      }
	  	   		                      }
	//  	   		                      System.out.println("foundHoliday*******"+foundHoliday);
	  	      	                    }
	                             } 
							}// 1st for loop
							if(due_dt_flag.equalsIgnoreCase("B")) {	
								 int finalDueDt=nxt_cnt;
	//							 System.out.println("sunday Count***"+StratDayCount);
	//							 System.out.println("foundHoliday Count***"+nxt_cnt);
								 if(!DueDate.equals("")) {
									String actDueDt="select TO_CHAR(TO_DATE('"+DueDate+"','DD/MM/YYYY') + '"+finalDueDt+"','DD/MM/YYYY') from dual";
	//			                      System.out.println("actDueDt****"+actDueDt);
			                    	  rset5=stmt5.executeQuery(actDueDt);
				                       if(rset5.next())
				                       {
				                              DueDate =rset5.getString(1);
				                       }
				                      
				                       String state_code="";
	        	                       String stateCd_sql = "select STATE_CODE from STATE_MST where "
	        	                       		+ " STATE_NM like (select plant_state from FMS7_CUSTOMER_PLANT_DTL where CUSTOMER_CD='"+temp_Customer_Cd.elementAt(i)+"' and SEQ_NO ='"+plant_seq_no+"'"
	        	                       		+ " and eff_dt = (select max(eff_dt) from FMS7_CUSTOMER_PLANT_DTL where CUSTOMER_CD='"+temp_Customer_Cd.elementAt(i)+"' and SEQ_NO ='"+plant_no+"' ))";
	        	                       rset1 = stmt1.executeQuery(stateCd_sql);
	//  	      	                       System.out.println("stateCd_sql***"+stateCd_sql);
	        	                       if(rset1.next()) {
	        	                    	   state_code = rset1.getString(1)==null?"":rset1.getString(1);
	        	                       }
	//        	                       System.out.println("DueDate****"+DueDate);
				                       int l=0;
								 			}
	//							System.out.println("DueDate Final****"+DueDate);
								String tempDueDt="";
								 if(DueDate.equals("")) {
										tempDueDt =NextDate2; 
										
									}else {
										tempDueDt = DueDate;
									}
								 
								 /* **************re-checking final date for sunday ***************** */
									WeeklyOff="";
									queryString = "SELECT TO_CHAR(TO_DATE('"+tempDueDt+"','DD/MM/YYYY'),'DAY') FROM DUAL";
	//								 System.out.println("Re checking Due date Get DAY Name: "+queryString);
		                             rset5 = stmt5.executeQuery(queryString);
		                             if(rset5.next())
		                             {
		                                    WeeklyOff = rset5.getString(1);
		                             }
		                             if(WeeklyOff.trim().equals("SUNDAY")) 
		                             {
		                            	 String query1="select to_char(to_date('"+tempDueDt+"','dd/mm/yyyy')+ "+1+",'dd/mm/yyyy') from dual";
	//			   	                      System.out.println("query1*****"+query1);
			   	                       rset5=stmt5.executeQuery(query1);
			   	                       if(rset5.next())
			   	                       {
			   	                    	   DueDate =rset5.getString(1);
			   	                       }
		                             }
		                             String state_code="";
	      	                       String stateCd_sql = "select STATE_CODE from STATE_MST where "
	      	                       		+ " STATE_NM like (select plant_state from FMS7_CUSTOMER_PLANT_DTL where CUSTOMER_CD='"+temp_Customer_Cd.elementAt(i)+"' and SEQ_NO ='"+plant_seq_no+"'"
	      	                       		+ " and eff_dt= (select max(eff_dt) from FMS7_CUSTOMER_PLANT_DTL where CUSTOMER_CD='"+temp_Customer_Cd.elementAt(i)+"' and SEQ_NO ='"+plant_no+"' ))";
	      	                       rset1 = stmt1.executeQuery(stateCd_sql);
	//	      	                       System.out.println("stateCd_sql***"+stateCd_sql);
	      	                       if(rset1.next()) {
	      	                    	   state_code = rset1.getString(1)==null?"":rset1.getString(1);
	      	                       }
	      	                       
	      	                  /* ****Re - Checking with holiday master***** */
				               int l=0;
		                   	   for(int m = 0 ; m <= 7 ; m++) {
		                   		   int nxt_cnt1=0;
	//				                   		   System.out.println("DueDate********"+DueDate);
		                   		   String NH_sql_nxt = "select count(*) from DLNG_HOLIDAY_DTL where TO_CHAR(HOLIDAY_DT,'DD/MM/YYYY')=TO_CHAR(TO_DATE('"+DueDate+"','DD/MM/YYYY') + '"+l+"','DD/MM/YYYY')   and STATE_CODE='0' and FLAG='Y' ";
	//				   		                      System.out.println("SH_sql_nxt***"+NH_sql_nxt);
	   		                       rset5 = stmt5.executeQuery(NH_sql_nxt);
	   		                      if(rset5.next()) {
	   		                    	  nxt_cnt1 = rset5.getInt(1);  
	   		                      }
	   		                      
	   		                      if(nxt_cnt1 == 0) {
	   		                    	  String SH_sql_nxt = "select count(*) from DLNG_HOLIDAY_DTL where TO_CHAR(HOLIDAY_DT,'DD/MM/YYYY')=TO_CHAR(TO_DATE('"+DueDate+"','DD/MM/YYYY') + '"+l+"','DD/MM/YYYY') and STATE_CODE='"+state_code+"' and FLAG='Y' ";
	//				   			                      System.out.println("SH_sql_nxt***"+SH_sql_nxt);
	   			                       rset5 = stmt5.executeQuery(SH_sql_nxt);
	   			                      if(rset5.next()) {
	   			                    	  nxt_cnt1 = rset5.getInt(1);  
	   			                      }
	   		                      }
	//				   		                      System.out.println("recheckig nxt_cnt1************************"+nxt_cnt1);
	   		                      if(nxt_cnt1 == 0) {
	   		                    	  
	   		                    	  String actDueDt1="select TO_CHAR(TO_DATE('"+DueDate+"','DD/MM/YYYY') + '"+l+"','DD/MM/YYYY') from dual";
	//				   			                      System.out.println("actDueDt1****"+actDueDt1);
	   		                    	  rset5=stmt5.executeQuery(actDueDt1);
	   			                       if(rset5.next())
	   			                       {
	   			                              DueDate =rset5.getString(1);
	   			                       }
	   			                       break;  
	   		                      }
	   		                   l++;
		                   	}
						}
//						}// 2nd for loop
						System.out.println("DueDate****"+DueDate);
						invoice_Due_Dt.add(DueDate);	
//						System.out.println("invoice_Due_Dt**SN**"+invoice_Due_Dt);
						invoice_Exchg_Rate_Cd.add(""+temp_Exchg_Rate_Cd.elementAt(i));
						invoice_Exchg_Rate_Calculation_Method.add(""+temp_Exchg_Rate_Calculation_Method.elementAt(i));	
						invoice_Mapping_Id.add(temp_Customer_Cd.elementAt(i)+"-"+temp_FGSA_No.elementAt(i)+"-"+temp_FGSA_Rev_No.elementAt(i)+"-"+temp_SN_No.elementAt(i)+"-"+temp_SN_Rev_No.elementAt(i)+"-"+temp_SN_No.elementAt(i)+"-"+plant_no); //Hiren_20200305
						invoice_pre_aprv.add("Y");
					}
				}
			}
			
//			System.out.println("invoice_Customer_Cd****"+invoice_Customer_Cd);
//			System.out.println("invoice_Mapping_Id****"+invoice_Mapping_Id);
			
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
			temp_agr_base.clear();//RG20200123
			temp_Tcq.clear();
			String BuyerAbbr = ""; String BuyerContType = ""; String BuyerPlant = "";  String BuyerStartDt = ""; String BuyerEndDt = "";
			queryString = "SELECT A.loa_no,A.loa_rev_no,A.tender_no,A.customer_cd," +
			//SB20111206			  "TO_CHAR(A.start_dt,'DD/MM/YYYY'),TO_CHAR(A.end_dt,'DD/MM/YYYY'),NVL(tcq,'0') " +
						  "TO_CHAR(A.start_dt,'DD/MM/YYYY'),TO_CHAR(A.end_dt,'DD/MM/YYYY'),NVL(A.tcq,'0')+NVL(A.VARIATION_QTY,'0') AS T_tcq,C.TENDER_BASE " +//RG20200123
						  "FROM DLNG_LOA_MST A,DLNG_TENDER_MST C WHERE A.start_dt IS NOT NULL AND A.end_dt IS NOT NULL AND " +
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
						  "B.end_dt>=TO_DATE('"+period_end_dt+"','DD/MM/YYYY')))) AND A.tender_no=C.tender_no AND " +
						  "A.customer_cd=C.customer_cd  " +
						  "ORDER BY A.customer_cd,A.tender_no,A.loa_no";
//				System.out.println("STEP-1: LOA-Invoice: DLNG_LOA_MST A,DLNG_TENDER_MST C: "+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					String customer_abbr = "";
					String bill_period_start_dt = "";
					String bill_period_end_dt = "";
					String due_dt = "";
					int due_days = 0;
					String bill_period_end_dt_WFM = "";				
					
					temp_agr_base.add(rset.getString(8)==null?"":rset.getString(8));
					queryString3 = "SELECT TO_DATE('"+rset.getString(5)+"','DD/MM/YYYY') - TO_DATE('"+period_start_dt+"','DD/MM/YYYY') FROM DUAL";
					////System.out.println("STEP-2: LOA-Invoice: "+queryString3);
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
					
					String LastDay=""; String ContLastDay="";
					queryString3 = "SELECT TO_CHAR(LAST_DAY(TO_DATE('"+period_end_dt+"','DD/MM/YYYY')),'DD/MM/YYYY') FROM DUAL";
					//System.out.println("Query To FindOut Days Difference Between Two End Dates = "+queryString3);
					rset3 = stmt3.executeQuery(queryString3);
					if(rset3.next())
						{
							LastDay=rset3.getString(1);
						}
					ContLastDay=rset.getString(6);
					
					queryString3 = "SELECT TO_DATE('"+rset.getString(6)+"','DD/MM/YYYY') - TO_DATE('"+period_end_dt+"','DD/MM/YYYY') FROM DUAL";
					////System.out.println("STEP-3: LOA-Invoice: "+queryString3);
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
							bill_period_end_dt_WFM=bill_period_end_dt;
						}
					}
					queryString3 = "SELECT customer_abbr FROM FMS7_CUSTOMER_MST WHERE customer_cd="+rset.getString(4)+"";
					////System.out.println("STEP-4: LOA-Invoice: "+queryString3);
					rset3 = stmt3.executeQuery(queryString3);
					if(rset3.next())
					{
						customer_abbr = rset3.getString(1)==null?"":rset3.getString(1);
					}
					
					String Temp_Bill_dt[] = period_start_dt.split("/");
					String Bill_day = Temp_Bill_dt[0]; String Bill_mth = Temp_Bill_dt[1]; String Bill_yr = Temp_Bill_dt[2];
					
					queryString1 = "SELECT NVL(billing_freq,'F'),NVL(due_date,'0'),NVL(exchng_rate_cd,'0'),NVL(exchng_rate_cal,'D') FROM DLNG_SN_BILLING_DTL " +
							   "WHERE cont_type='L' AND customer_cd="+rset.getString(4)+" AND " +
							   "flsa_no="+rset.getString(3)+""
//							   	+ " AND flsa_rev_no=0 "
							   	+ " AND sn_no="+rset.getString(1)+" ";
//								AND sn_rev_no="+rset.getString(2)+"";					
//					System.out.println("STEP-5:FMS7_SN_BILLING_DTL: "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);					
					if(rset1.next())
					{
						if(rset1.getString(1).equalsIgnoreCase("F"))
						{
							
							if(Integer.parseInt(Bill_day)<=15) {
								bill_period_end_dt_WFM="15/"+Bill_mth+"/"+Bill_yr;//Bill_day ="15";
							}
							
							if(Integer.parseInt(Bill_day)>15) {
								
								queryString3 = "SELECT TO_CHAR(LAST_DAY(TO_DATE('"+period_start_dt+"','DD/MM/YYYY')),'DD/MM/YYYY') FROM DUAL";
//									System.out.println("Query To FindOut Days Difference Between Two End Dates = "+queryString3);
									rset3 = stmt3.executeQuery(queryString3);
									if(rset3.next())
										{
											bill_period_end_dt_WFM=rset3.getString(1);
										}
								}
								
							queryString3 = "SELECT TO_DATE('"+LastDay+"','DD/MM/YYYY') - TO_DATE('"+ContLastDay+"','DD/MM/YYYY') FROM DUAL";
//							System.out.println("FIND DATE GAP: = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								if(Integer.parseInt(rset3.getString(1))>0 && (Integer.parseInt(Bill_day)>15))
								{						
									bill_period_end_dt_WFM=ContLastDay;
								}
							}
							
							due_days = Integer.parseInt(rset1.getString(2));
							
							/*queryString3 = "SELECT TO_CHAR(TO_DATE('"+bill_period_end_dt_WFM+"','DD/MM/YYYY') + "+due_days+",'DD/MM/YYYY') FROM DUAL";
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								temp_Due_Dt.add(rset3.getString(1));
							}
							else
							{
								temp_Due_Dt.add(bill_period_end_dt);
							}*/
							temp_Due_Dt.add(bill_period_end_dt_WFM);
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
					{}
					BuyerAbbr = customer_abbr;
					BuyerContType = "LoA-"+rset.getString(3);
					BuyerPlant = "";  
					BuyerStartDt = rset.getString(5); 
					BuyerEndDt =rset.getString(6);
				}
				////System.out.println("temp_Customer_Cd.size(): "+temp_Customer_Cd.size());
				if(temp_Customer_Cd.size()==0)
				{
					invoice_Customer_Cd.add("");
					invoice_Customer_Abbr.add(""+BuyerAbbr);
					invoice_Customer_Plant_Seq_No.add("");
					invoice_FGSA_No.add(""+BuyerContType);
					invoice_FGSA_Rev_No.add("");
					invoice_SN_No.add(""+BuyerContType);
					invoice_SN_Rev_No.add("");
					invoice_Contract_Type.add("");
					invoice_SN_Start_Dt.add(""+BuyerStartDt);
					invoice_SN_End_Dt.add(""+BuyerEndDt);
					invoice_Bill_Period_Start_Dt.add("");
					invoice_SN_Ref_No.add("");
					invoice_Customer_Plant_Nm.add("Billing Details Not Defined");
					invoice_Bill_Period_End_Dt.add("");
					invoice_Due_Dt.add("");
					invoice_Exchg_Rate_Cd.add("");
					invoice_Exchg_Rate_Calculation_Method.add("");				
					invoice_Mapping_Id.add(""); //Hiren_20200305
					invoice_pre_aprv.add("Y");
				}
				for(int i=0;i<temp_Customer_Cd.size();i++)
				{
//					String temp_map_id=temp_Customer_Cd.elementAt(i)+"-"+temp_FGSA_No.elementAt(i)+"-"+temp_FGSA_Rev_No.elementAt(i)+"-"+temp_SN_No.elementAt(i)+"-"+temp_SN_Rev_No.elementAt(i);
					String temp_map_id=temp_Customer_Cd.elementAt(i)+"-"+temp_FGSA_No.elementAt(i)+"-%-"+temp_SN_No.elementAt(i)+"-%";
					////System.out.println("MAPPING_ID: "+temp_map_id);
					queryString = "SELECT DISTINCT(ALLOC_ID) FROM DLNG_ALLOC_MST " +
						  " WHERE MAPPING_ID like '"+temp_map_id+"' AND contract_type='L' "
						 + " AND  alloc_dt>=TO_DATE('"+temp_Bill_Period_Start_Dt.elementAt(i)+"','DD/MM/YYYY') "
						 + " AND alloc_dt<=TO_DATE('"+temp_Bill_Period_End_Dt.elementAt(i)+"','DD/MM/YYYY')"
						 + " AND CONTRACT_TYPE='L' ORDER BY ALLOC_ID";
				////System.out.println("STEP-7: DLNG_ALLOC_MST: "+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					String bill_start_dt1 = "";
					String bill_end_dt1 = "";
					String bill_start_dt2 = "";
					String bill_end_dt2 = "";
					String bill_end_dt3 = "";
					String bill_end_dt4 = "";
					String plant_no = "";
					
					if(rset.getString(1).contains("-")) {
						String temp_plant[] = rset.getString(1).split("-");
						plant_no = temp_plant[6];
					}
					
					invoice_Customer_Cd.add(""+temp_Customer_Cd.elementAt(i));
					invoice_Customer_Abbr.add(""+temp_Customer_Abbr.elementAt(i));
					invoice_Customer_Plant_Seq_No.add(plant_no);
					invoice_FGSA_No.add(""+temp_FGSA_No.elementAt(i));
					invoice_FGSA_Rev_No.add(""+temp_FGSA_Rev_No.elementAt(i));
					invoice_SN_No.add(""+temp_SN_No.elementAt(i));
					invoice_SN_Rev_No.add(""+temp_SN_Rev_No.elementAt(i));
					invoice_Contract_Type.add(""+temp_Contract_Type.elementAt(i));
					invoice_SN_Start_Dt.add(""+temp_SN_Start_Dt.elementAt(i));
					invoice_SN_End_Dt.add(""+temp_SN_End_Dt.elementAt(i));
					invoice_Bill_Period_Start_Dt.add(""+temp_Bill_Period_Start_Dt.elementAt(i));
				
					String tmp_bill_period_end_dt = ""+temp_Bill_Period_End_Dt.elementAt(i);
					period_end_dt_FIXED = tmp_bill_period_end_dt;  //SB20110930
	//				//////System.out.println("FMS7-INV-LIST:*2: DLNG_LOA_MST ******: "+period_end_dt_FIXED);
					
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
//					String cont_mapp_id="L-"+temp_Customer_Cd.elementAt(i)+"-"+temp_FGSA_No.elementAt(i)+"-0-"+temp_SN_No.elementAt(i)+"-"+temp_SN_Rev_No.elementAt(i)+"-"+rset.getString(1);
					if(bill_start_dt1.equals("") || bill_end_dt1.equals("") || bill_start_dt2.equals("") || bill_end_dt2.equals(""))
					{
						double total_qty_mmbtu = 0;
						bill_end_dt3 = "";
							queryString1 = "SELECT SUM(ENTRY_TOT_ENE) FROM DLNG_ALLOC_MST " +
									   " WHERE CONTRACT_TYPE='L' AND "
									 + " MAPPING_ID LIKE '"+temp_Customer_Cd.elementAt(i)+"-"+temp_FGSA_No.elementAt(i)+"-%-"+temp_SN_No.elementAt(i)+"-%' "
									 + " AND alloc_dt<=TO_DATE('"+temp_Bill_Period_End_Dt.elementAt(i)+"','DD/MM/YYYY')"
									 + " AND CONTRACT_TYPE='L'";		
							////System.out.println("Query For Fetching Last Allocation Date = "+queryString1);
							rset1 = stmt1.executeQuery(queryString1);
//						}
						if(rset1.next())
						{
							total_qty_mmbtu = rset1.getDouble(1);
						}
						
						if(total_qty_mmbtu>=(Double.parseDouble(""+temp_Tcq.elementAt(i))-0.99))
						{
							queryString1 = "SELECT TO_CHAR(gas_dt,'dd/mm/yyyy') FROM DLNG_ALLOC_MST  " +
									   "WHERE CONTRACT_TYPE='L' AND ALLOC_ID like '"+temp_Customer_Cd.elementAt(i)+"-"+temp_FGSA_No.elementAt(i)+"-%-"+temp_SN_No.elementAt(i)+"-%-%-"+plant_no+"' "
									+ " AND ALLOC_DT>=TO_DATE('"+temp_Bill_Period_Start_Dt.elementAt(i)+"','DD/MM/YYYY') "
									+ " AND ALLOC_DT<=TO_DATE('"+temp_Bill_Period_End_Dt.elementAt(i)+"','DD/MM/YYYY') "
									+ " AND ENTRY_TOT_ENE>=0.99"
									 + " AND CONTRACT_TYPE='L' ORDER BY gas_dt DESC";	
							
							////System.out.println("Query For Fetching Last Allocation Date = "+queryString1);
							rset1 = stmt1.executeQuery(queryString1);
//							}
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
												   "flsa_no="+temp_FGSA_No.elementAt(i)+" AND " +
												   "sn_no="+temp_SN_No.elementAt(i)+" " +
												   "ORDER BY sn_rev_no DESC";
									
									////System.out.println("Query To FindOut Due Days from LOA Bill Details = "+queryString2);
									rset2 = stmt2.executeQuery(queryString2);
									
									if(rset2.next())
									{
										int due_days = Integer.parseInt(rset2.getString(2));
											
										queryString3 = "SELECT TO_CHAR(TO_DATE('"+bill_end_dt3+"','DD/MM/YYYY') + "+due_days+",'DD/MM/YYYY') FROM DUAL";
										//////////System.out.println("Query To FindOut Due Dates = "+queryString3);
										rset3 = stmt3.executeQuery(queryString3);
										if(rset3.next())
										{
//											temp_Due_Dt.setElementAt(rset3.getString(1),i);
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
									//SB20200702   "WHERE A.customer_cd="+temp_Customer_Cd.elementAt(i)+" AND A.seq_no="+rset.getString(1)+" " +
									   "WHERE A.customer_cd="+temp_Customer_Cd.elementAt(i)+" AND A.seq_no="+plant_no+" " +
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
						queryString1 = "SELECT TO_CHAR(gas_dt,'dd/mm/yyyy') FROM DLNG_ALLOC_MST  " +
								   "WHERE CONTRACT_TYPE='L' AND MAPPING_ID like '"+temp_Customer_Cd.elementAt(i)+"-"+temp_FGSA_No.elementAt(i)+"-%-"+temp_SN_No.elementAt(i)+"-%' "
								+ " AND ALLOC_DT>=TO_DATE('"+temp_Bill_Period_Start_Dt.elementAt(i)+"','DD/MM/YYYY') "
								+ " AND ALLOC_DT<=TO_DATE('"+temp_Bill_Period_End_Dt.elementAt(i)+"','DD/MM/YYYY') "
								+ " AND ENTRY_TOT_ENE>=0.99"
								+ " AND CONTRACT_TYPE='L' ORDER BY gas_dt DESC";
						////System.out.println("@@@@@FMS7-LOA-INV-LIST:LAST-DATE W/O Plant:QRY-IL1001AA:FMS7_DAILY_ALLOCATION_DTL:SELECT: "+queryString1);
						rset1 = stmt1.executeQuery(queryString1);
						if(rset1.next())
						{
							tmp_bill_period_end_dt = rset1.getString(1)==null?"":rset1.getString(1);
						}
////////////			This is for Billing Clause: dt07Dec2011:REL#20111201: ////////////////
						String clauseFlag="";
						queryString1 = "SELECT NVL(FLAG,'Y') FROM DLNG_FLSA_BILLING_DTL " +
						   "WHERE cont_type='L' AND " +
						   "customer_cd="+temp_Customer_Cd.elementAt(i)+" AND " +
						   "flsa_no="+temp_FGSA_No.elementAt(i)+"  " +
						   "ORDER BY flsa_rev_no ASC";			
						////System.out.println("SN-CLAUSE: "+queryString1);
						rset1 = stmt1.executeQuery(queryString1);						
						while(rset1.next())
						{
							clauseFlag = rset1.getString(1);
						}
						
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
						
						/* ***Hiren_20201102 Filtering SUN from invoice due date calculation******* */	
						
						String WeeklyOff = "";
						String NextDate2 = temp_Due_Dt.elementAt(i)+"";
						int StratDayCount = 0;
//						System.out.println("NextDate2******"+NextDate2);
						
//						System.out.println("temp_due_days.size()***"+temp_due_days.size());
						String DueDate="";
//						for(int k = 0 ; k < temp_due_days.size(); k++) {
							
							StratDayCount = 0;
							int foundHoliday=0;
							DueDate="";
							int nxt_cnt =0;
							String due_dt_flag = "";
							
							for (int j = 0 ; j < Integer.parseInt(temp_due_days.elementAt(i)+""); j++) {
								
								 queryString = "SELECT TO_CHAR(TO_DATE('"+NextDate2+"','DD/MM/YYYY') + "+(j+1)+",'DAY') FROM DUAL";
//								 System.out.println("Get DAY Name: "+queryString);
	                             rset5 = stmt5.executeQuery(queryString);
	                             if(rset5.next())
	                             {
	                                    WeeklyOff = rset5.getString(1);
	                             }
	                           
	                           String query1="select to_char(to_date('"+ NextDate2+"','dd/mm/yyyy')+ "+(j+1)+",'dd/mm/yyyy') from dual";
//		   	                      System.out.println("query1*****"+query1);
	   	                       rset5=stmt5.executeQuery(query1);
	   	                       if(rset5.next())
	   	                       {
	   	                    	   DueDate =rset5.getString(1);
	   	                       }
	   	                    //Hiren_20230123 checking DUE_DT_CALC_FLAG
		   	                     due_dt_flag = "";
		   	                     String dueDtFlagSql  = "select nvl(DUE_DT_FLAG,'B') from DLNG_SN_BILLING_DTL where"
		   	                     		+ " customer_cd = '"+temp_Customer_Cd.elementAt(i)+"' "
		   	                     		+ " AND flsa_no = '"+temp_FGSA_No.elementAt(i)+"' "
		   	                     		+ " AND flsa_rev_no = '"+temp_FGSA_Rev_No.elementAt(i)+"'"
		   	                     		+ " AND sn_no = '"+temp_SN_No.elementAt(i)+"'"
		   	                     		+ " AND SN_REV_NO = '"+temp_SN_Rev_No.elementAt(i)+"'"
		   	                     		+ " AND CONT_TYPE = '"+temp_Contract_Type.elementAt(i)+"'";
//	                            System.out.println("dueDtFlagSql****"+dueDtFlagSql);
	                            rset1 = stmt1.executeQuery(dueDtFlagSql);
		                        if(rset1.next()) {
		                        	Vdue_dt_flag.add(rset1.getString(1)==null?"":rset1.getString(1));
		                        	due_dt_flag = rset1.getString(1)==null?"":rset1.getString(1);
		                        }else {
		                        	Vdue_dt_flag.add("");
		                        }
	                            if(due_dt_flag.equalsIgnoreCase("B")) {
			   	                     if(WeeklyOff.trim().equals("SUNDAY")) 
		                             {
		                                    StratDayCount++;
		                                    
		                             }
		//	   	                     else {
		                            	 
		//                            	 System.out.println("StratDayCount****"+StratDayCount);
		                                 String state_code="";
		                                 String stateCd_sql = "select STATE_CODE from STATE_MST where "
		            	                       		+ " STATE_NM like (select plant_state from FMS7_CUSTOMER_PLANT_DTL where CUSTOMER_CD='"+temp_Customer_Cd.elementAt(i)+"' and SEQ_NO ='"+plant_no+"'"
		            	                       		+ " and eff_dt= (select max(eff_dt) from FMS7_CUSTOMER_PLANT_DTL where CUSTOMER_CD='"+temp_Customer_Cd.elementAt(i)+"' and SEQ_NO ='"+plant_no+"' ))";
		            	                       rset1 = stmt1.executeQuery(stateCd_sql);
		//            	                       System.out.println("stateCd_sql***"+stateCd_sql);
		            	                       if(rset1.next()) {
		            	                    	   state_code = rset1.getString(1)==null?"":rset1.getString(1);
		            	                       }
		  	      	                       
		  	      	                       
		  	      	                    if(!state_code.equals("")) {
		  	   	                    	  
		  	      	                    String query2="select to_char(to_date('"+ DueDate+"','dd/mm/yyyy')+ "+StratDayCount+",'dd/mm/yyyy') from dual";
		//		   	                      System.out.println("query2*****"+query2);
				   	                       rset5=stmt5.executeQuery(query2);
				   	                       if(rset5.next())
				   	                       {
				   	                    	   DueDate =rset5.getString(1);
				   	                       }
				   	                       
		  	   	                    	  //first checking with National holiday
		  	   		                      String NH_sql = "select count(*) from DLNG_HOLIDAY_DTL where TO_CHAR(HOLIDAY_DT,'DD/MM/YYYY')=TO_CHAR(TO_DATE('"+DueDate+"','DD/MM/YYYY'),'dd/mm/yyyy') and STATE_CODE='0' and FLAG='Y' ";
		//  	   		                      System.out.println("NH_sql***"+NH_sql);
		  	   		                       rset5 = stmt5.executeQuery(NH_sql);
		  	   		                      if(rset5.next()) {
		  	   		                    	  foundHoliday = rset5.getInt(1); 
		  	   		                    	  nxt_cnt+=foundHoliday;
		  	   		                      }
		  	   		                      
		  	   		                      if(foundHoliday == 0) {
		  	   		                    	  String SH_sql = "select count(*) from DLNG_HOLIDAY_DTL where TO_CHAR(HOLIDAY_DT,'DD/MM/YYYY')=TO_CHAR(TO_DATE('"+DueDate+"','DD/MM/YYYY'),'dd/mm/yyyy') and STATE_CODE='"+state_code+"' and FLAG='Y' ";
		//  	   			                      System.out.println("SH_sql***"+SH_sql);
		  	   			                       rset5 = stmt5.executeQuery(SH_sql);
		  	   			                      if(rset5.next()) {
		  	   			                    	  foundHoliday = rset5.getInt(1);
		  	   			                    	  nxt_cnt+=foundHoliday;
		  	   			                      }
		  	   		                      }
		//  	   		                      System.out.println("foundHoliday*******"+foundHoliday);
		  	      	                    }
		//                             }
									}
								}// 1st for loop
								 if(due_dt_flag.equalsIgnoreCase("B")) {
									 int finalDueDt=nxt_cnt;
		//							 System.out.println("sunday Count***"+StratDayCount);
		//							 System.out.println("foundHoliday Count***"+nxt_cnt);
									 if(!DueDate.equals("")) {
										String actDueDt="select TO_CHAR(TO_DATE('"+DueDate+"','DD/MM/YYYY') + '"+finalDueDt+"','DD/MM/YYYY') from dual";
		//			                      System.out.println("actDueDt****"+actDueDt);
				                    	  rset5=stmt5.executeQuery(actDueDt);
					                       if(rset5.next())
					                       {
					                              DueDate =rset5.getString(1);
					                       }
					                      
					                       String state_code="";
		        	                       String stateCd_sql = "select STATE_CODE from STATE_MST where "
		        	                       		+ " STATE_NM like (select plant_state from FMS7_CUSTOMER_PLANT_DTL where CUSTOMER_CD='"+temp_Customer_Cd.elementAt(i)+"' and SEQ_NO ='"+plant_no+"' "
		        	                       		+ " and eff_dt= (select max(eff_dt) from FMS7_CUSTOMER_PLANT_DTL where CUSTOMER_CD='"+temp_Customer_Cd.elementAt(i)+"' and SEQ_NO ='"+plant_no+"' ))";
		        	                       rset1 = stmt1.executeQuery(stateCd_sql);
		//  	      	                       System.out.println("stateCd_sql***"+stateCd_sql);
		        	                       if(rset1.next()) {
		        	                    	   state_code = rset1.getString(1)==null?"":rset1.getString(1);
		        	                       }
		        	                       
					                       int l=0;
									 }
		//							 System.out.println("DueDate Final****"+DueDate);
										String tempDueDt="";
										 if(DueDate.equals("")) {
												tempDueDt =NextDate2; 
												
											}else {
												tempDueDt = DueDate;
											}
										 
										 /* **************re-checking final date for sunday ***************** */
											WeeklyOff="";
											queryString = "SELECT TO_CHAR(TO_DATE('"+tempDueDt+"','DD/MM/YYYY'),'DAY') FROM DUAL";
		//									 System.out.println("Re checking Due date Get DAY Name: "+queryString);
				                             rset5 = stmt5.executeQuery(queryString);
				                             if(rset5.next())
				                             {
				                                    WeeklyOff = rset5.getString(1);
				                             }
				                             if(WeeklyOff.trim().equals("SUNDAY")) 
				                             {
				                            	 String query1="select to_char(to_date('"+tempDueDt+"','dd/mm/yyyy')+ "+1+",'dd/mm/yyyy') from dual";
		//				   	                      System.out.println("query1*****"+query1);
					   	                       rset5=stmt5.executeQuery(query1);
					   	                       if(rset5.next())
					   	                       {
					   	                    	   DueDate =rset5.getString(1);
					   	                       }
				                             }
				                             String state_code="";
			      	                       String stateCd_sql = "select STATE_CODE from STATE_MST where "
			      	                       		+ " STATE_NM like (select plant_state from FMS7_CUSTOMER_PLANT_DTL where CUSTOMER_CD='"+temp_Customer_Cd.elementAt(i)+"' and SEQ_NO ='"+plant_no+"'"
			      	                       	+ " and eff_dt= (select max(eff_dt) from FMS7_CUSTOMER_PLANT_DTL where CUSTOMER_CD='"+temp_Customer_Cd.elementAt(i)+"' and SEQ_NO ='"+plant_no+"' ))";
		
			      	                       rset1 = stmt1.executeQuery(stateCd_sql);
		//		      	                       System.out.println("stateCd_sql***"+stateCd_sql);
			      	                       if(rset1.next()) {
			      	                    	   state_code = rset1.getString(1)==null?"":rset1.getString(1);
			      	                       }
			      	                       /* ****Re - Checking with hollyday master***** */
					                       int l=0;
					                   	   for(int m = 0 ; m <= 7 ; m++) {
	//					                   		   System.out.println("************ in for ************");
					                   		   int nxt_cnt1=0;
	//					                   		   System.out.println("DueDate********"+DueDate);
					                   		   String NH_sql_nxt = "select count(*) from DLNG_HOLIDAY_DTL where TO_CHAR(HOLIDAY_DT,'DD/MM/YYYY')=TO_CHAR(TO_DATE('"+DueDate+"','DD/MM/YYYY') + '"+l+"','DD/MM/YYYY')   and STATE_CODE='0' and FLAG='Y' ";
	//					   		                      System.out.println("SH_sql_nxt***"+NH_sql_nxt);
					   		                       rset5 = stmt5.executeQuery(NH_sql_nxt);
					   		                      if(rset5.next()) {
					   		                    	  nxt_cnt1 = rset5.getInt(1);  
					   		                      }
					   		                      
					   		                      if(nxt_cnt1 == 0) {
					   		                    	  String SH_sql_nxt = "select count(*) from DLNG_HOLIDAY_DTL where TO_CHAR(HOLIDAY_DT,'DD/MM/YYYY')=TO_CHAR(TO_DATE('"+DueDate+"','DD/MM/YYYY') + '"+l+"','DD/MM/YYYY') and STATE_CODE='"+state_code+"' and FLAG='Y' ";
	//					   			                      System.out.println("SH_sql_nxt***"+SH_sql_nxt);
					   			                       rset5 = stmt5.executeQuery(SH_sql_nxt);
					   			                      if(rset5.next()) {
					   			                    	  nxt_cnt1 = rset5.getInt(1);  
					   			                      }
					   		                      }
	//					   		                      System.out.println("recheckig nxt_cnt1************************"+nxt_cnt1);
					   		                      if(nxt_cnt1 == 0) {
					   		                    	  
					   		                    	  String actDueDt1="select TO_CHAR(TO_DATE('"+DueDate+"','DD/MM/YYYY') + '"+l+"','DD/MM/YYYY') from dual";
	//					   			                      System.out.println("actDueDt1****"+actDueDt1);
					   		                    	  rset5=stmt5.executeQuery(actDueDt1);
					   			                       if(rset5.next())
					   			                       {
					   			                              DueDate =rset5.getString(1);
					   			                       }
					   			                       break;  
					   		                      }
					   		                   l++;
					                   	   		}
								 }
//						}// 2nd for loop
						invoice_Due_Dt.add(DueDate);
//						System.out.println("invoice_Due_Dt**LoA**"+invoice_Due_Dt);
						/*String WeeklyOff = "";
						String NextDate2 = temp_Due_Dt.elementAt(i)+"";
						int StratDayCount = 0;
						String DueDate="";
						
                       queryString = "SELECT TO_CHAR(TO_DATE('"+NextDate2+"','DD/MM/YYYY'),'DAY') FROM DUAL";
//                            System.out.println("Get DAY Name: "+queryString);
                              rset6 = stmt1.executeQuery(queryString);
                              if(rset6.next())
                              {
                                     WeeklyOff = rset6.getString(1);
                              }
                              if(WeeklyOff.trim().equals("SATURDAY")) 
                              {
                                     StratDayCount= StratDayCount+2;
                              }
                              else if(WeeklyOff.trim().equals("SUNDAY")) 
                              {
                                     StratDayCount++;
                              }

                              if(WeeklyOff.trim().equals("SUNDAY")) 
                              {
                                     StratDayCount++;
                              }
//                             System.out.println("WeeklyOff*****"+WeeklyOff);
                           String query1="select to_char(to_date('"+ NextDate2+"','dd/mm/yyyy')+ "+StratDayCount+",'dd/mm/yyyy') from dual";
//                           System.out.println("query1****"+query1);
   	                       rset6=stmt1.executeQuery(query1);
   	                       if(rset6.next())
   	                       {
   	                              DueDate =rset6.getString(1);
   	                       }
   	                       System.out.println("DueDate****"+DueDate);
                               ******** Hiren_20201102 filter Holiday*********** 
   	                       String state_code="";
//   	                       System.out.println("DueDate*******"+DueDate);
   	                       
   	                       String stateCd_sql = "select STATE_CODE from STATE_MST where "
   	                       		+ " STATE_NM like (select plant_state from FMS7_CUSTOMER_PLANT_DTL where CUSTOMER_CD='"+temp_Customer_Cd.elementAt(i)+"' and SEQ_NO ='"+plant_no+"'"
   	                       		+ " and eff_dt= (select max(eff_dt) from FMS7_CUSTOMER_PLANT_DTL where CUSTOMER_CD='"+temp_Customer_Cd.elementAt(i)+"' and SEQ_NO ='"+plant_no+"' ))";
   	                       rset1 = stmt1.executeQuery(stateCd_sql);
//   	                       System.out.println("stateCd_sql***"+stateCd_sql);
   	                       if(rset1.next()) {
   	                    	   state_code = rset1.getString(1)==null?"":rset1.getString(1);
   	                       }
   	                     
   	                      int foundHoliday=0;
   	                      
   	                      if(!state_code.equals("")) {
   	                    	  
   	                    	  first checking with National holiday
   		                      String NH_sql = "select count(*) from DLNG_HOLIDAY_DTL where TO_DATE(HOLIDAY_DT,'DD/MM/YYYY')=to_date('"+DueDate+"','dd/mm/yyyy') and STATE_CODE='0' and FLAG='Y' ";
//   		                      System.out.println("NH_sql***"+NH_sql);
   		                       rset6 = stmt1.executeQuery(NH_sql);
   		                      if(rset6.next()) {
   		                    	  foundHoliday = rset6.getInt(1);  
   		                      }
   		                      
   		                      if(foundHoliday == 0) {
   		                    	  String SH_sql = "select count(*) from DLNG_HOLIDAY_DTL where TO_DATE(HOLIDAY_DT,'DD/MM/YYYY')=to_date('"+DueDate+"','dd/mm/yyyy') and STATE_CODE='"+state_code+"' and FLAG='Y' ";
//   			                      System.out.println("SH_sql***"+SH_sql);
   			                       rset6 = stmt1.executeQuery(SH_sql);
   			                      if(rset6.next()) {
   			                    	  foundHoliday = rset6.getInt(1);  
   			                      }
   		                      }
   		                      int k=0;
   		                      if(foundHoliday > 0) {
   		                   	   for(int j = 0 ; j <= 7 ; j++) {
//   		                   		   System.out.println("************ in for ************");
   		                   		   int nxt_cnt=0;
   		                   		   
   		                   		   k++;
   		                   		   
   		                   		   String NH_sql_nxt = "select count(*) from DLNG_HOLIDAY_DTL where TO_CHAR(HOLIDAY_DT,'DD/MM/YYYY')=TO_CHAR(TO_DATE('"+DueDate+"','DD/MM/YYYY') + '"+k+"','DD/MM/YYYY')   and STATE_CODE='0' and FLAG='Y' ";
//   		   		                      System.out.println("SH_sql_nxt***"+NH_sql_nxt);
   		   		                       rset5 = stmt1.executeQuery(NH_sql_nxt);
   		   		                      if(rset5.next()) {
   		   		                    	  nxt_cnt = rset5.getInt(1);  
   		   		                      }
   		   		                      
   		   		                      if(nxt_cnt == 0) {
   		   		                    	  String SH_sql_nxt = "select count(*) from DLNG_HOLIDAY_DTL where TO_CHAR(HOLIDAY_DT,'dd/mm/yyyy')=TO_CHAR(TO_DATE('"+DueDate+"','DD/MM/YYYY') + '"+k+"','DD/MM/YYYY') and STATE_CODE='"+state_code+"' and FLAG='Y' ";
//   		   			                      System.out.println("SH_sql_nxt***"+SH_sql_nxt);
   		   			                       rset5 = stmt1.executeQuery(SH_sql_nxt);
   		   			                      if(rset5.next()) {
   		   			                    	  nxt_cnt = rset5.getInt(1);  
   		   			                      }
   		   		                      }
   		                       	   
//   		   		                      System.out.println("nxt_cnt************************"+nxt_cnt);
   		   		                      if(nxt_cnt == 0) {
   		   		                    	  
   		   		                    	  String actDueDt="select TO_CHAR(TO_DATE('"+DueDate+"','DD/MM/YYYY') + '"+k+"','DD/MM/YYYY') from dual";
//   		   			                      System.out.println("actDueDt****"+actDueDt);
   		   		                    	  rset5=stmt.executeQuery(actDueDt);
   		   			                       if(rset5.next())
   		   			                       {
   		   			                              DueDate =rset5.getString(1);
   		   			                       }
//   		   			                       System.out.println("DueDate******"+DueDate);
   		   			                       break;  
   		   		                      }
   		                   	   		}
   	                      		}else {
//   	                      			DueDate =""+temp_Due_Dt.elementAt(i);
   	                      		}
   	                      	} 
   	                   System.out.println("DueDate End ******"+DueDate);*/
	                       
//						invoice_Due_Dt.add(""+temp_Due_Dt.elementAt(i));
						invoice_Exchg_Rate_Cd.add(""+temp_Exchg_Rate_Cd.elementAt(i));
						invoice_Exchg_Rate_Calculation_Method.add(""+temp_Exchg_Rate_Calculation_Method.elementAt(i));
						
						invoice_Mapping_Id.add(temp_Customer_Cd.elementAt(i)+"-"+temp_FGSA_No.elementAt(i)+"-"+temp_FGSA_Rev_No.elementAt(i)+"-"+temp_SN_No.elementAt(i)+"-"+temp_SN_Rev_No.elementAt(i)+"-"+temp_SN_No.elementAt(i)+"-"+plant_no); //Hiren_20200305
						invoice_pre_aprv.add("Y");
					}
				}
			}
			
			
			for(int i = 0 ; i < invoice_Customer_Cd.size() ; i ++){
					int drcrcnt=0;
//					String map_id=invoice_Customer_Cd.elementAt(i)+"-"+invoice_FGSA_No.elementAt(i)+"-"+invoice_FGSA_Rev_No.elementAt(i)+"-"+invoice_SN_No.elementAt(i)+"-"+invoice_SN_Rev_No.elementAt(i);
					String map_id=invoice_Customer_Cd.elementAt(i)+"-"+invoice_FGSA_No.elementAt(i)+"-%-"+invoice_SN_No.elementAt(i)+"-%";
//					String alloc_id=invoice_Customer_Cd.elementAt(i)+"-"+invoice_FGSA_No.elementAt(i)+"-"+invoice_FGSA_Rev_No.elementAt(i)+"-"+invoice_SN_No.elementAt(i)+"-"+invoice_SN_Rev_No.elementAt(i)+"-"+invoice_SN_No.elementAt(i)+"-"+invoice_Customer_Plant_Seq_No.elementAt(i);
					String alloc_id=invoice_Customer_Cd.elementAt(i)+"-"+invoice_FGSA_No.elementAt(i)+"-%-"+invoice_SN_No.elementAt(i)+"-%-"+invoice_SN_No.elementAt(i)+"-"+invoice_Customer_Plant_Seq_No.elementAt(i);
					
					int loadedTruckCnt=0;
					String inv_apr="",inv_seq_arr="",inv_seq_yr="",inv_per_dt="";
					String loadedTruckSQL = "SELECT A.SUP_TRN_CD,B.TRUCK_NM FROM DLNG_ALLOC_MST A,DLNG_TANK_TRUCK_MST B WHERE "
							+ " A.ALLOC_DT=TO_DATE('"+invoice_Bill_Period_Start_Dt.elementAt(i)+"','DD/MM/YYYY') "
							+ " AND A.MAPPING_ID like '"+map_id+"'"
							+ " AND A.ALLOC_ID like '"+alloc_id+"'"
							+ " AND A.SUP_TRN_CD = B.TRUCK_ID";
//					System.out.println("STEP-8: DLNG_ALLOC_MST A,DLNG_TANK_TRUCK_MST B : "+loadedTruckSQL);
					rset4 = stmt4.executeQuery(loadedTruckSQL);
					while (rset4.next()) {
						loadedTruckCnt++;
						cust_loaded_truck_cd.add(rset4.getString(1)==null?"":rset4.getString(1));
						cust_loaded_truck.add(rset4.getString(2)==null?"":rset4.getString(2));
						
						queryString2 = "SELECT hlpl_inv_seq_no,financial_year,cust_inv_seq_no," +
								   "checked_flag,checked_by,authorized_flag,authorized_by," +
								   "approved_flag,approved_by,exchg_rate_cd,gross_amt_usd,gross_amt_inr,offspec_rate " +
								   ", TO_CHAR(INVOICE_DT,'DD/MM/YYYY'),NEW_INV_SEQ_NO,contact_person_cd,to_char(due_dt,'dd/mm/yyyy') " +  
								   "FROM DLNG_INVOICE_MST " +
					  			   "WHERE customer_cd="+invoice_Customer_Cd.elementAt(i)+" AND " +
					  			   "fgsa_no="+invoice_FGSA_No.elementAt(i)+" AND " +
//					  			   "fgsa_rev_no="+invoice_FGSA_Rev_No.elementAt(i)+" AND " +
					  			   "sn_no="+invoice_SN_No.elementAt(i)+" AND " +
//					  			   "sn_rev_no="+invoice_SN_Rev_No.elementAt(i)+" AND " +
					  			   "contract_type='"+invoice_Contract_Type.elementAt(i)+"' AND " +
					  			   "period_start_dt=TO_DATE('"+invoice_Bill_Period_Start_Dt.elementAt(i)+"','DD/MM/YYYY') AND " +
					  			   "plant_seq_no="+invoice_Customer_Plant_Seq_No.elementAt(i)+"" +
					  			   " AND FLAG ='Y'"
					  			   + " AND TRUCK_ID='"+rset4.getString(1)+"' "
					  			   + " AND MAPPING_ID like '"+alloc_id+"'";
							System.out.println("---queryString2-------"+queryString2);
							rset2 = stmt2.executeQuery(queryString2);
							if(rset2.next())
							{
								hlpl_Invoice_Seq_No_Arr.add(rset2.getString(1)==null?"":rset2.getString(1));
								inv_seq_arr = rset2.getString(1)==null?"":rset2.getString(1);
								hlpl_Invoice_Financial_Year_Arr.add(rset2.getString(2)==null?"":rset2.getString(2));
								inv_seq_yr = rset2.getString(2)==null?"":rset2.getString(2);
								inv_Checked_Flag.add(rset2.getString(4)==null?"":rset2.getString(4));
								inv_Checked_By.add(rset2.getString(5)==null?"0":rset2.getString(5));
								inv_Authorized_Flag.add(rset2.getString(6)==null?"":rset2.getString(6));
								inv_Authorized_By.add(rset2.getString(7)==null?"0":rset2.getString(7));
								inv_Approved_Flag.add(rset2.getString(8)==null?"":rset2.getString(8));
								inv_apr=rset2.getString(8)==null?"":rset2.getString(8);
								inv_Approved_By.add(rset2.getString(9)==null?"0":rset2.getString(9));
								
								inv_Exchg_Rate_CD.add(rset2.getString(10)==null?"0":rset2.getString(10));
								inv_Gross_Amt_USD.add(rset2.getString(11)==null?"":rset2.getString(11));
								inv_Gross_Amt_INR.add(rset2.getString(12)==null?"":rset2.getString(12));
								inv_Offspec_Rate.add(rset2.getString(13)==null?"":rset2.getString(13));
								VPDF_Inv_Dt.add(rset2.getString(14)==null?"":rset2.getString(14));
								VPDF_Contact_person_cd.add(rset2.getString(16)==null?"":rset2.getString(16));
								VPDF_due_dt.add(rset2.getString(17)==null?"":rset2.getString(17));
								
								invoice_HLPL_Seq_No.add("");
								
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
								
							}else {
								/*for CR/DR*/
								queryString2 = "SELECT hlpl_inv_seq_no,financial_year,cust_inv_seq_no," +
										   "checked_flag,checked_by,authorized_flag,authorized_by," +
										   "approved_flag,approved_by,exchg_rate_cd,gross_amt_usd,gross_amt_inr,offspec_rate " +
										   ", TO_CHAR(INVOICE_DT,'DD/MM/YYYY'),NEW_INV_SEQ_NO,contact_person_cd,to_char(due_dt,'dd/mm/yyyy') " +  
										   "FROM DLNG_INVOICE_MST " +
							  			   "WHERE customer_cd="+invoice_Customer_Cd.elementAt(i)+" AND " +
							  			   "fgsa_no="+invoice_FGSA_No.elementAt(i)+" AND " +
//							  			   "fgsa_rev_no="+invoice_FGSA_Rev_No.elementAt(i)+" AND " +
							  			   "sn_no="+invoice_SN_No.elementAt(i)+" AND " +
//							  			   "sn_rev_no="+invoice_SN_Rev_No.elementAt(i)+" AND " +
							  			   "contract_type='"+invoice_Contract_Type.elementAt(i)+"' AND " +
							  			   "period_start_dt=TO_DATE('"+invoice_Bill_Period_Start_Dt.elementAt(i)+"','DD/MM/YYYY') AND " +
							  			   "plant_seq_no="+invoice_Customer_Plant_Seq_No.elementAt(i)+"" +
							  			   " AND FLAG ='X'"
							  			   + " AND TRUCK_ID='"+rset4.getString(1)+"' "
							  			   + " AND MAPPING_ID like '"+alloc_id+"'";
											
									System.out.println("---queryString21-------"+queryString2);
									rset2 = stmt2.executeQuery(queryString2);
									if(rset2.next())
									{
										drcrcnt++;
										hlpl_Invoice_Seq_No_Arr.add(rset2.getString(1)==null?"":rset2.getString(1));
										inv_seq_arr = rset2.getString(1)==null?"":rset2.getString(1);
										hlpl_Invoice_Financial_Year_Arr.add(rset2.getString(2)==null?"":rset2.getString(2));
										inv_seq_yr = rset2.getString(2)==null?"":rset2.getString(2);
										inv_Checked_Flag.add(rset2.getString(4)==null?"":rset2.getString(4));
										inv_Checked_By.add(rset2.getString(5)==null?"0":rset2.getString(5));
										inv_Authorized_Flag.add(rset2.getString(6)==null?"":rset2.getString(6));
										inv_Authorized_By.add(rset2.getString(7)==null?"0":rset2.getString(7));
										inv_Approved_Flag.add(rset2.getString(8)==null?"":rset2.getString(8));
										inv_apr=rset2.getString(8)==null?"":rset2.getString(8);
										inv_Approved_By.add(rset2.getString(9)==null?"0":rset2.getString(9));
										
										inv_Exchg_Rate_CD.add(rset2.getString(10)==null?"0":rset2.getString(10));
										inv_Gross_Amt_USD.add(rset2.getString(11)==null?"":rset2.getString(11));
										inv_Gross_Amt_INR.add(rset2.getString(12)==null?"":rset2.getString(12));
										inv_Offspec_Rate.add(rset2.getString(13)==null?"":rset2.getString(13));
										VPDF_Inv_Dt.add(rset2.getString(14)==null?"":rset2.getString(14));
										VPDF_Contact_person_cd.add(rset2.getString(16)==null?"":rset2.getString(16));
										VPDF_due_dt.add(rset2.getString(17)==null?"":rset2.getString(17));
										
										invoice_HLPL_Seq_No.add("");
										
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
									}else {	
										drcrcnt = 0;
										inv_seq_arr="";
										inv_seq_yr="";
										inv_apr="";
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
										VPDF_Inv_Dt.add(""); 
										new_Invoice_Seq_No.add("");	
										invoice_HLPL_Seq_No.add("");
										VPDF_Contact_person_cd.add("");
										VPDF_due_dt.add("");
								
									}
								}
							
							int count=0;
							
							String query="SELECT COUNT(HLPL_INV_SEQ_NO) FROM DLNG_INVOICE_MST WHERE CUSTOMER_CD='"+invoice_Customer_Cd.elementAt(i)+"' "
									+ "AND PERIOD_END_DT > TO_DATE('"+period_end_dt+"','DD/MM/YYYY') AND TRUCK_ID='"+rset4.getString(1)+"'";
							rset6=stmt6.executeQuery(query);
							if(rset6.next()){
								count=rset6.getInt(1);
							}
							if(count==0){
								DelFlag.add("Y");
							}else{
								DelFlag.add("N");
							}
							Invoice_Pending_approval.add("");
							invoice_tax_adj.add("");
							tax_adj_flag_pdf.add("");
							
							
							if(inv_apr.equalsIgnoreCase("Y"))
							{
								String flag="Y";
								if(drcrcnt > 0) {
//									flag = "X";
								}
								String invoice_pdf_path = sess.getAttribute("invoice_pdf_path").toString();
								
								String invoice_date1="";
								String invoice_date2="";
								String pdf_inv_dtl="";
								/*String query1="SELECT to_char(PERIOD_END_DT,'dd/mm/yyyy'),to_char(INVOICE_DT,'dd/mm/yyyy'),PDF_INV_DTL FROM DLNG_INVOICE_MST WHERE CUSTOMER_CD='"+invoice_Customer_Cd.elementAt(i)+"' " +
										"AND HLPL_INV_SEQ_NO='"+inv_seq_arr+"' AND CONTRACT_TYPE='"+invoice_Contract_Type.elementAt(i)+"' " +
										" AND FINANCIAL_YEAR='"+inv_seq_yr+"'"
										+ " AND FLAG='"+flag+"'"
										+ "  and TRUCK_ID='"+rset4.getString(1)+"'";*/
								
								String query1="SELECT to_char(PERIOD_END_DT,'dd/mm/yyyy'),to_char(INVOICE_DT,'dd/mm/yyyy'),PDF_INV_DTL FROM DLNG_INVOICE_MST WHERE CUSTOMER_CD='"+invoice_Customer_Cd.elementAt(i)+"' " +
										"AND HLPL_INV_SEQ_NO='"+inv_seq_arr+"' AND CONTRACT_TYPE='"+invoice_Contract_Type.elementAt(i)+"' " +
										" AND FINANCIAL_YEAR='"+inv_seq_yr+"'"
										+ "  and TRUCK_ID='"+rset4.getString(1)+"'";
								
//								System.out.println("Fetchin....."+query1);
								rset1=stmt1.executeQuery(query1);
								if(rset1.next())
								{
									 invoice_date1=rset1.getString(1)==null?"":rset1.getString(1);
									 invoice_date2=rset1.getString(2)==null?"":rset1.getString(2);
									 pdf_inv_dtl=rset1.getString(3)==null?"":rset1.getString(3);
								}
								invoice_inv_period_dt.add(invoice_date1); //HS20160620
								invoice_inv_date.add(invoice_date2);
								inv_per_dt=invoice_date1;
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
								else
								{
									pdf_color.add("N");
								}
								
							//SB20160402 if(pdf_inv_dtl.length()==3)
								boolean temp_flag = false;
								String q_debit = "SELECT COUNT(SN_NO) FROM DLNG_DR_CR_NOTE WHERE FINANCIAL_YEAR='"+inv_seq_yr+"' "
										+ "AND CONTRACT_TYPE='"+invoice_Contract_Type.elementAt(i)+"' "
										+ "AND HLPL_INV_SEQ_NO='"+inv_seq_arr+"' "
										+ "AND CUSTOMER_CD='"+invoice_Customer_Cd.elementAt(i)+"'";
								rset1 = stmt1.executeQuery(q_debit);
//								//System.out.println("==="+q_debit);
								if(rset1.next()) {
									int count_debit = rset1.getInt(1);
									if(count_debit>0) {
										temp_flag = true;
									}
								}
//								System.out.println("pdf_inv_dtl.length()**********"+pdf_inv_dtl.length());	
								/*if(temp_flag) {
									if(pdf_inv_dtl.length()>=5)  //SB20160405
									{
										customer_invoice_pdf_lock_flag.add("Y");
									}
									else
									{
										customer_invoice_pdf_lock_flag.add("N");
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
								}*/
								customer_invoice_pdf_lock_flag.add("N");
//								//System.out.println("customer invoice pdf lock flag=="+customer_invoice_pdf_lock_flag+"=="+pdf_inv_dtl+"=="+date_flag+"=="+temp_flag);
								//ADDED FOR INVOICEPDF CHANGE AFTER BENCH DATE1
								
								String tempD10[]=invoice_bench_date1.split("/");
								String d10=tempD10[2]+tempD10[1]+tempD10[0];
//								System.out.println("----------->"+d20+"-------"+d10);
								String tempD20[]=invoice_date2.split("/");
//								System.out.println("invoice_date2****"+invoice_date2);
								String d20=tempD20[2]+tempD20[1]+tempD20[0];
								if(Integer.parseInt(d20)>Integer.parseInt(d10))
								{
									int count_inv=0;
									customer_invoice_pdf_path_flag.add("Y"); //SB20181024
									customer_invoice_pdf_path.add("");
								}
								else
								{
//									////System.out.println("IN ELSE");
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
											String f1="";
											String tempD1[]=invoice_bench_date.split("/"); //10/03/2015
											String d1=tempD1[2]+tempD1[1]+tempD1[0];
											
											String tempD2[]=invoice_date2.split("/");
											String d2=tempD2[2]+tempD2[1]+tempD2[0];
											
												if(Integer.parseInt(d2)>Integer.parseInt(d1))
												{
													f1="INVOICE-"+invoice_date1.trim().substring(0,2)+invoice_date1.trim().substring(3,5)+invoice_date1.trim().substring(6)+"-"+invoice_Customer_Abbr.elementAt(i)+"-"+invoice_Customer_Plant_Nm.elementAt(i)+"-"+invoice_Contract_Type.elementAt(i)+"-"+inv_seq_arr+"-"+title_vect.elementAt(h);
												}
												else
												{
													f1="INVOICE-"+invoice_date1.trim().substring(0,2)+invoice_date1.trim().substring(3,5)+invoice_date1.trim().substring(6)+"-"+invoice_Customer_Abbr.elementAt(i)+"-"+invoice_Customer_Plant_Nm.elementAt(i);
												}
												f1=invoice_pdf_path1+"/"+f1;
												File inv_file=new File(f1+".pdf");
												if(inv_file.exists())
												{
													count1++;
													count2++;
													invoice_pdf_path1=invoice_pdf_path1+"//"+inv_file;
										            String context_nm = request.getContextPath();
													String server_nm = request.getServerName();
													String server_port = ""+request.getServerPort();
													  ////System.out.println("invoice_pdf_path--"+invoice_pdf_path1);
													String url_start = "http://"+server_nm+":"+server_port+context_nm;
													
													String pdfpath = invoice_pdf_path1;
													pdfpath = pdfpath.substring(pdfpath.indexOf("//"));
													
													pdfpath = url_start+"/pdf_reports/pdf_files"+pdfpath;
//														//System.out.println("pdfpath---> "+pdfpath);
													customer_invoice_pdf_path1.add(pdfpath);
													customer_invoice_pdf_path_flag1.add("Y");
												}
										if(count2==0)
										{
											customer_invoice_pdf_path1.add("");
											customer_invoice_pdf_path_flag1.add("N");
										}
									}
									if(count1==0)
									{
										temp_file_nm.add("");
										customer_invoice_pdf_path_flag.add("N");
									}
									else
									{
										customer_invoice_pdf_path_flag.add("Y");
									}
									customer_invoice_pdf_path.add(customer_invoice_pdf_path1);
								}	
								
							}else {

								//////System.out.println("IN 44444 IFFFFF");
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
										"AND HLPL_INV_SEQ_NO='"+inv_seq_arr+"' AND CONTRACT_TYPE='"+invoice_Contract_Type.elementAt(i)+"' " +
										"AND FINANCIAL_YEAR='"+inv_seq_yr+"' AND FLAG='Y' AND (NEW_INV_SEQ_NO NOT LIKE 'D%' OR NEW_INV_SEQ_NO IS NULL) "
										+ " AND TRUCK_ID='"+rset4.getString(1)+"'";
						
								rset1=stmt1.executeQuery(query1);
								if(rset1.next()){
									 invoice_date1=rset1.getString(1)==null?"":rset1.getString(1);
									 invoice_date2=rset1.getString(2)==null?"":rset1.getString(2);
									 //pdf_inv_dtl=rset1.getString(3)==null?"":rset1.getString(3);
								}
								invoice_inv_period_dt.add(invoice_date1); //HS20160620
								inv_per_dt=invoice_date1;
								invoice_inv_date.add(invoice_date2);
							}
							
							/* fetching CR/DR details*/
							queryString2="select HLPL_INV_SEQ_NO, CONTRACT_TYPE, FINANCIAL_YEAR,mapping_id,new_inv_seq_no,"
									+ "customer_cd,fgsa_no,sn_no,plant_seq_no "
									+ " from dlng_invoice_mst " +
								" where PERIOD_START_DT >= TO_DATE('"+period_start_dt+"','DD/MM/YYYY') "+
								" AND PERIOD_END_DT <= TO_DATE('"+period_end_dt+"','DD/MM/YYYY') AND FLAG='X' ORDER BY INVOICE_DT DESC";
//							System.out.println("queryString2----"+queryString2);
							rset3=stmt3.executeQuery(queryString2);
							while(rset3.next())
							{
								VCrInvSeqNo.add(rset3.getString(1));
								VCrContType.add(rset3.getString(2));
								VCrFY.add(rset3.getString(3));
								invoice_Mapping_Id.add(rset3.getString(4)==null?"":rset3.getString(4));
								VcrNewInvSeqNo.add(rset3.getString(5)==null?rset3.getString(1):rset3.getString(4));
								credit_map.put(rset3.getString(6)+":"+rset3.getString(7)+":"+rset3.getString(8)+":"+rset3.getString(9)+":"+rset3.getString(2),"Y");
							}
							
							queryString2="select TO_CHAR(INVOICE_DT, 'DD/MM/YYYY') from dlng_invoice_mst " +
								" where PERIOD_START_DT >= TO_DATE('"+period_start_dt+"','DD/MM/YYYY') "+
								" AND PERIOD_END_DT <= TO_DATE('"+period_end_dt+"','DD/MM/YYYY') AND FLAG='X' ORDER BY INVOICE_DT DESC";
								rset3=stmt3.executeQuery(queryString2);
							while(rset3.next()){
								VPDF_Inv_Dt.add(rset3.getString(1)); 
							}
							
							queryString="SELECT A.FLAG,A.HLPL_INV_SEQ_NO,A.CONTRACT_TYPE " +
									", DR_CR_DOC_NO, DR_CR_FLAG, TO_CHAR(DR_CR_DT, 'DD-Mon-YY') " + //HS20160615 && SB20160526
									" , TO_CHAR(A.INVOICE_DT, 'DD/MM/YYYY'), NVL(B.APRV_BY,'0'), TO_CHAR(B.APRV_DT, 'DD/MM/YYYY'),B.CRITERIA,B.REMARK " + //SB20160526
									" FROM DLNG_INVOICE_MST A, DLNG_DR_CR_NOTE B WHERE " +
									"A.INVOICE_DT >= TO_DATE('"+invoice_Bill_Period_Start_Dt.elementAt(i)+"','DD/MM/YYYY')"+ // MCL20160531 AND A.INVOICE_DT <= TO_DATE('"+invoice_Bill_Period_End_Dt.elementAt(k)+"','DD/MM/YYYY') " + 
									"AND B.FLAG='Y' AND A.FLAG!='A' AND A.HLPL_INV_SEQ_NO='"+inv_seq_arr+"' AND A.HLPL_INV_SEQ_NO=B.HLPL_INV_SEQ_NO  AND  " + // AND B.CRITERIA='DIFF-EXG' A.INVOICE_DT=TO_DATE('"+invoice_inv_date.elementAt(i)+"','DD/MM/YYYY') AND A.INVOICE_DT=B.INVOICE_DT
									" A.CUSTOMER_CD='"+invoice_Customer_Cd.elementAt(i)+"' AND A.CUSTOMER_CD=B.CUSTOMER_CD AND A.CONTRACT_TYPE='"+invoice_Contract_Type.elementAt(i)+"' AND A.CONTRACT_TYPE=B.CONTRACT_TYPE "
									+ "AND A.FINANCIAL_YEAR = '"+inv_seq_yr+"' AND A.FINANCIAL_YEAR = B.FINANCIAL_YEAR "
									+ "AND A.HLPL_INV_SEQ_NO||A.FINANCIAL_YEAR||A.CONTRACT_TYPE IN (SELECT B.HLPL_INV_SEQ_NO||B.FINANCIAL_YEAR||B.CONTRACT_TYPE FROM DLNG_DR_CR_NOTE B " +
									"WHERE (B.CRITERIA like '%REV_INV%' OR B.CRITERIA like '%DIFF-EXG%'  OR B.CRITERIA like '%DIFF-PRICE%'  OR B.CRITERIA like '%DIFF-QTY%' OR B.CRITERIA like '%DIFF-TAX%' ) )";
//								System.out.println(" :INVOICE-DR-CR: "+queryString);
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

									int cnt1=0;
									Vector qty_dt=new Vector(); 
									String nom_rev_no="";
									flag="";
									queryString="SELECT SN_NO,FGSA_NO,CUSTOMER_CD,PLANT_SEQ_NO,CONTRACT_TYPE,HLPL_INV_SEQ_NO," +
									"FINANCIAL_YEAR,CRITERIA,REMARK FROM DLNG_DR_CR_NOTE Where customer_cd='"+invoice_Customer_Cd.elementAt(i)+"' AND " +
									" FINANCIAL_YEAR='"+inv_seq_yr+"' AND CRITERIA='DIFF-QTY--' and (dr_cr_flag='cr' OR dr_cr_flag='dr')" +
									" and hlpl_inv_seq_no='"+inv_seq_arr+"' ";
//									System.out.println("queryString----"+queryString);
									rset1=stmt1.executeQuery(queryString);
									if(rset1.next()){
										
										remark=rset1.getString(9)==null?"":rset1.getString(9);
										if(!remark.equals("")) {
											String dt=remark.substring(48,remark.length()-1); //SB20170127
											
											String date[]=dt.split(",");
											for(int j=0;j<date.length;j++){  
											//String dates=date[cnt];
											qty_dt.add(date[j]);
											}
											for(int j=0;j<qty_dt.size();j++){  
												
												queryString = "SELECT NVL(MAX(nom_rev_no),'-1') FROM DLNG_ALLOC_MST " +
															  " WHERE ALLOC_ID LIKE '"+rset1.getString(3)+"-"+rset1.getString(2)+"-%"+rset1.getString(1)+"-%-"+rset1.getString(1)+"-"+rset1.getString(4)+"' "+
															  " AND gas_dt=TO_DATE('"+qty_dt.elementAt(j)+"','DD/MM/YYYY')";
	//											System.out.println("----query-->"+queryString);
												rset=stmt.executeQuery(queryString);
												if(rset.next()){
													nom_rev_no=rset.getString(1);
												}
	//											System.out.println("rset********"+rset4.getString(1));
												/*Hiren_20210215 not required since Flow change for Change Quantity criteria   
												 * query="select * from DLNG_ALLOC_MST " +
											 			"WHERE"
											 			+ " alloc_id like '"+rset1.getString(3)+"-"+rset1.getString(2)+"-%-"+rset1.getString(1)+"-%"+rset1.getString(1)+"-"+rset1.getString(4)+"'"
														+ " AND contract_type='"+rset1.getString(5)+"' "
														+ " AND nom_rev_no="+nom_rev_no+""
														+ " AND gas_dt=TO_DATE('"+qty_dt.elementAt(j)+"','DD/MM/YYYY')"
														+ " AND SUP_TRN_CD='"+rset4.getString(1)+"'";
												System.out.println("---query---"+query);
												rset=stmt.executeQuery(query);
												if(rset.next()){
													cnt1++;
												}*/
											}
										}
//										System.out.println("cnt1*****"+cnt1+"-----"+qty_dt.size());
										if(cnt1==qty_dt.size() && cnt1>0){
											flag="Y";
										}
										
										if(qty_dt.size()>0 && cnt1==0){
											flag="N";
										}
									}
									flagqty.add(flag); 
							
				/* ***************** PDFFileNmForInvoice********************** */	

								String invoice_date=inv_per_dt; //HS20160620
								if(!invoice_date.equals(""))
									VPDF_File_Nm.add("INVOICE-"+invoice_date.trim().substring(0,2)+invoice_date.trim().substring(3,5)+invoice_date.trim().substring(6)+"-"+invoice_Customer_Abbr.elementAt(i)+"-"+invoice_Customer_Plant_Nm.elementAt(i)+"-"+invoice_Contract_Type.elementAt(i)+"-"+inv_seq_arr);
								else
									VPDF_File_Nm.add("-");
								VCustContPlantStDt.add(invoice_Customer_Abbr.elementAt(i)+"-"+invoice_Customer_Plant_Nm.elementAt(i)+"-"+invoice_Contract_Type.elementAt(i)+"-"+invoice_SN_No.elementAt(i)+"-"+invoice_FGSA_No.elementAt(i));
							}
							String file1="",file2="";

							for(int k = 0; k < VPDF_File_Nm.size(); k++){
								
								Vector invoice_path=new Vector();
								Vector invoice_path_sign=new Vector();
								Vector invoice_mail_sent_flag=new Vector();
								
								String qq1="select pdf_inv_nm,inv_type,pdf_signed_flag,mail_sent_flag from  dlng_inv_pdf_dtl"
										+ " where pdf_inv_nm='"+VPDF_File_Nm.elementAt(k).toString()+"' ";
//								System.out.println("qq1****"+qq1);
								rset5=stmt5.executeQuery(qq1);
								
								while(rset5.next()){
									
									file1=rset5.getString(1)+"-"+rset5.getString(2);
									invoice_path.add(file1);
									invoice_path_sign.add(rset5.getString(3)==null?"":rset5.getString(3));
									invoice_mail_sent_flag.add(rset5.getString(4)==null?"":rset5.getString(4));
								}
								invoice_view_pdf.put(k, invoice_path);
								invoice_view_signed_pdf.put(k,invoice_path_sign);
								invoice_mail_sent.put(k,invoice_mail_sent_flag);
							}	
							int start=0;
							for(int k=start; k<VCustContPlantStDt.size(); k++)
							{
								int count=0;
								for(int j=start+1; j<VCustContPlantStDt.size(); j++)
								{
									if(VCustContPlantStDt.elementAt(k).equals(VCustContPlantStDt.elementAt(j)))
									{
										VCustContPlantStDt_Flag.add("Y");
										VCustContPlantCrDrAprv_Flag.add(VDrCrAprvBy.elementAt(j)); //SB20160623
										start++;
										count++;
										break;
									}
								}
								if(count==0)
								{
									if(!VDrCrAprvBy.elementAt(k).equals("0"))
										VCustContPlantStDt_Flag.add("Y");
									else
										VCustContPlantStDt_Flag.add("N");
										VCustContPlantCrDrAprv_Flag.add("X"); //SB20160623
										start++;
									}
											
												
				/*******************************************************************/					
						}// inner while end
						cust_loaded_truck_cnt.add(loadedTruckCnt);
						loadedTruckCnt=0;
					//END of Truck details
				}//main while end
			
//			print_pdf_cr_dr_details();
		}
		catch(Exception e)
		{
			////System.out.println("EXCEPTION IN Method : "+methodName+" Of "+databeanName+" DataBean = "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	private void print_pdf_cr_dr_details()throws SQLException,IOException {

		methodName = "print_pdf_cr_dr_details()";
		try 
		{
			String inv_seq_number="";
			String cst_inv_seq_number="";
			
			//////System.out.println("tax_adj_flag_pdf----"+tax_adj_flag_pdf+"--invoice_tax_adj----"+invoice_tax_adj);
			queryString2="select HLPL_INV_SEQ_NO, CONTRACT_TYPE, FINANCIAL_YEAR,mapping_id,new_inv_seq_no,"
					+ "customer_cd,fgsa_no,sn_no,plant_seq_no "
					+ " from dlng_invoice_mst " +
			" where PERIOD_START_DT >= TO_DATE('"+period_start_dt+"','DD/MM/YYYY') "+
			" AND PERIOD_END_DT <= TO_DATE('"+period_end_dt+"','DD/MM/YYYY') AND FLAG='X' ORDER BY INVOICE_DT DESC";
//			System.out.println("queryString2----"+queryString2);
			rset3=stmt3.executeQuery(queryString2);
			while(rset3.next())
			{
//				System.out.println("*****innnnnnnnnnnnnn*******");
				VCrInvSeqNo.add(rset3.getString(1));
				VCrContType.add(rset3.getString(2));
				VCrFY.add(rset3.getString(3));
				invoice_Mapping_Id.add(rset3.getString(4)==null?"":rset3.getString(4));
				VcrNewInvSeqNo.add(rset3.getString(5)==null?rset3.getString(1):rset3.getString(4));
				
				//customer_cd+":"+fgsa_no+":"+sn_no+":"+plant_no+":"+contract_type
				credit_map.put(rset3.getString(6)+":"+rset3.getString(7)+":"+rset3.getString(8)+":"+rset3.getString(9)+":"+rset3.getString(2),"Y");
			}
//			System.out.println("VCrInvSeqNo********"+VCrInvSeqNo);
				int x=invoice_Customer_Plant_Seq_No.size();
				for(int k=0;k<VCrInvSeqNo.size();k++){
					
						queryString2="SELECT CUSTOMER_CD,PLANT_SEQ_NO,HLPL_INV_SEQ_NO,FGSA_NO,"
								+ " SN_NO,SN_REV_NO,CONTRACT_TYPE,PERIOD_START_DT,PERIOD_END_DT,DUE_DT, "
								+ "EXCHG_RATE_TYPE,EXCHG_RATE_CD,CHECKED_FLAG,CUST_INV_SEQ_NO,FINANCIAL_YEAR,"
								+ " CHECKED_BY,AUTHORIZED_FLAG,AUTHORIZED_BY,APPROVED_FLAG,APPROVED_BY,"
								+ "GROSS_AMT_USD,GROSS_AMT_INR,OFFSPEC_RATE,PDF_INV_DTL,FGSA_REV_NO,to_char(PERIOD_END_DT,'dd/mm/yyyy'), " //HS20160620
								+ "NEW_INV_SEQ_NO,contact_person_cd,to_char(due_dt,'dd/mm/yyyy')  from dlng_invoice_mst " +
								" WHERE FINANCIAL_YEAR='"+VCrFY.elementAt(k)+"' AND HLPL_INV_SEQ_NO='"+VCrInvSeqNo.elementAt(k)+"' " +
								" AND CONTRACT_TYPE='"+VCrContType.elementAt(k)+"' AND FLAG='X' " +
								" ORDER BY HLPL_INV_SEQ_NO DESC";
						System.out.println("queryString2---3---"+queryString2);
					rset3=stmt3.executeQuery(queryString2);
					while(rset3.next())
					{
						String cstcd=rset3.getString(1)==null?"":rset3.getString(1);
						invoice_Customer_Cd.add(cstcd);
						VPDF_Contact_person_cd.add(rset3.getString(28)==null?"":rset3.getString(28));//RG20191227
						VPDF_due_dt.add(rset3.getString(29)==null?"":rset3.getString(29));//RG20200305
						
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
								   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
								   "AND B.eff_dt<=TO_DATE('"+from_dt+"','DD/MM/YYYY'))";
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
					//	////System.out.println("INV:START-DT :"+psdt); 
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
						//////System.out.println("Deep inv_seq_number:"+inv_seq_number);
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
						
						invoice_Mismatch_dates.add("");
						invoice_Mismatch_flag.add("N");
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
//						//System.out.println("pdf_inv_dtl"+pdf_inv_dtl);
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
						invoice_agr_base.add("");
						//////System.out.println("invoice_pre_aprv--5--"+invoice_pre_aprv);
						customer_invoice_pdf_path.add("-");
						
						 invoice_inv_date.add("15/03/2016"); //BK: to fill vector
						String temp_inv_dt="";
					//	////System.out.println("--invoice_Mapping_Id---"+invoice_Mapping_Id);
						String query1="SELECT to_char(PERIOD_END_DT,'dd/mm/yyyy'),to_char(INVOICE_DT,'dd/mm/yyyy') FROM DLNG_INVOICE_MST WHERE CUSTOMER_CD='"+invoice_Customer_Cd.elementAt(x+k)+"' " +
								"AND HLPL_INV_SEQ_NO='"+hlpl_Invoice_Seq_No_Arr.elementAt(x+k)+"' AND CONTRACT_TYPE='"+invoice_Contract_Type.elementAt(x+k)+"' " +
										"AND FINANCIAL_YEAR='"+hlpl_Invoice_Financial_Year_Arr.elementAt(x+k)+"' AND FLAG='X'";
							
							if((""+invoice_Contract_Type.elementAt(x+k)).equalsIgnoreCase("T") || (""+invoice_Contract_Type.elementAt(x+k)).equalsIgnoreCase("C"))
								{
									query1+=" and mapping_id='"+invoice_Mapping_Id.elementAt(x+k)+"'";
								}
					//		////System.out.println("--query1-- " +query1);	
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
						//////System.out.println("queryString---credit-- "+queryString);
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
			/*SB20160615	queryString2="select TO_CHAR(INVOICE_DT, 'DD/MM/YYYY') from fms7_invoice_mst " +
						" where INVOICE_DT BETWEEN TO_DATE('"+from_dt+"','DD/MM/YYYY') "+
				"AND TO_DATE('"+to_dt+"','DD/MM/YYYY') AND FLAG='X' ORDER BY INVOICE_DT DESC";*/	
				queryString2="select TO_CHAR(INVOICE_DT, 'DD/MM/YYYY') from dlng_invoice_mst " +
				" where PERIOD_START_DT >= TO_DATE('"+period_start_dt+"','DD/MM/YYYY') "+
				" AND PERIOD_END_DT <= TO_DATE('"+period_end_dt+"','DD/MM/YYYY') AND FLAG='X' ORDER BY INVOICE_DT DESC";
				rset3=stmt3.executeQuery(queryString2);
				while(rset3.next()){
					VPDF_Inv_Dt.add(rset3.getString(1)); //SB20160530
				}
		
			
			//////System.out.println(invoice_Customer_Cd.size()+"-----invoice_Customer_Cd--- "+invoice_Customer_Cd);
			for(int k=0;k<invoice_Customer_Cd.size();k++){
				queryString="SELECT A.FLAG,A.HLPL_INV_SEQ_NO,A.CONTRACT_TYPE " +
				", DR_CR_DOC_NO, DR_CR_FLAG, TO_CHAR(DR_CR_DT, 'DD-Mon-YY') " + //HS20160615 && SB20160526
				" , TO_CHAR(A.INVOICE_DT, 'DD/MM/YYYY'), NVL(B.APRV_BY,'0'), TO_CHAR(B.APRV_DT, 'DD/MM/YYYY'),B.CRITERIA,B.REMARK " + //SB20160526
				" FROM DLNG_INVOICE_MST A, DLNG_DR_CR_NOTE B WHERE " +
			"A.INVOICE_DT >= TO_DATE('"+invoice_Bill_Period_Start_Dt.elementAt(k)+"','DD/MM/YYYY')"+ // MCL20160531 AND A.INVOICE_DT <= TO_DATE('"+invoice_Bill_Period_End_Dt.elementAt(k)+"','DD/MM/YYYY') " + 
			"AND B.FLAG='Y' AND A.FLAG!='A' AND A.HLPL_INV_SEQ_NO='"+hlpl_Invoice_Seq_No_Arr.elementAt(k)+"' AND A.HLPL_INV_SEQ_NO=B.HLPL_INV_SEQ_NO  AND  " + // AND B.CRITERIA='DIFF-EXG' A.INVOICE_DT=TO_DATE('"+invoice_inv_date.elementAt(k)+"','DD/MM/YYYY') AND A.INVOICE_DT=B.INVOICE_DT
			" A.CUSTOMER_CD='"+invoice_Customer_Cd.elementAt(k)+"' AND A.CUSTOMER_CD=B.CUSTOMER_CD AND A.CONTRACT_TYPE='"+invoice_Contract_Type.elementAt(k)+"' AND A.CONTRACT_TYPE=B.CONTRACT_TYPE "
		+ "AND A.FINANCIAL_YEAR = '"+hlpl_Invoice_Financial_Year_Arr.elementAt(k)+"' AND A.FINANCIAL_YEAR = B.FINANCIAL_YEAR "
		+ "AND A.HLPL_INV_SEQ_NO||A.FINANCIAL_YEAR||A.CONTRACT_TYPE IN (SELECT B.HLPL_INV_SEQ_NO||B.FINANCIAL_YEAR||B.CONTRACT_TYPE FROM DLNG_DR_CR_NOTE B " +
		"WHERE (B.CRITERIA='REV_INV' OR B.CRITERIA='DIFF-EXG'  OR B.CRITERIA='DIFF-PRICE'  OR B.CRITERIA='DIFF-QTY' OR B.CRITERIA='DIFF-TAX' OR B.CRITERIA='Imbalance' ) )";	// B.CRITERIA='DIFF-EXG'
//		//System.out.println(k+" :INVOICE-DR-CR: "+queryString); //RG20161125 For fetching debit credit note for all criteria.....
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
				"FINANCIAL_YEAR,CRITERIA,REMARK FROM DLNG_DR_CR_NOTE Where customer_cd='"+invoice_Customer_Cd.elementAt(i)+"' AND " +
				" FINANCIAL_YEAR='"+hlpl_Invoice_Financial_Year_Arr.elementAt(i)+"' AND CRITERIA='DIFF-QTY' and (dr_cr_flag='cr' OR dr_cr_flag='dr')" +
				" and hlpl_inv_seq_no='"+hlpl_Invoice_Seq_No_Arr.elementAt(i)+"' ";
				//////System.out.println("queryString----"+queryString);
				rset1=stmt1.executeQuery(queryString);
				while(rset1.next()){
					//////System.out.println("inside query");
					remark=rset1.getString(9);
			//SB20170127		String dt=remark.substring(48,remark.length()-1);
					String dt=remark.substring(48,remark.length()-1); //SB20170127
					
					String date[]=dt.split(",");
					for(int j=0;j<date.length;j++){  
					//String dates=date[cnt];
					qty_dt.add(date[j]);
					}
					for(int j=0;j<qty_dt.size();j++){  
						
					queryString = "SELECT NVL(MAX(nom_rev_no),'-1') FROM DLNG_ALLOC_MST " +
								  "WHERE"
								  + " alloc_id like '"+rset1.getString(3)+"-"+rset1.getString(2)+"-%-"+rset1.getString(1)+"-%"+rset1.getString(1)+"-"+rset1.getString(4)+"'"
								  + " AND contract_type='"+rset1.getString(5)+"' "
								  + " AND gas_dt=TO_DATE('"+qty_dt.elementAt(j)+"','DD/MM/YYYY')";
					System.out.println("----query-->"+queryString);
					rset=stmt.executeQuery(queryString);
					while(rset.next()){
						nom_rev_no=rset.getString(1);
					}
					
					String query="select * from DLNG_ALLOC_MST " +
				 			"WHERE"
				 			+ " alloc_id like '"+rset1.getString(3)+"-"+rset1.getString(2)+"-%-"+rset1.getString(1)+"-%"+rset1.getString(1)+"-"+rset1.getString(4)+"'"
							+ " AND contract_type='"+rset1.getString(5)+"' "
							+ " AND nom_rev_no="+nom_rev_no+""
							+ " AND gas_dt=TO_DATE('"+qty_dt.elementAt(j)+"','DD/MM/YYYY')";
				 			/*+ " sn_no='"+rset1.getString(1)+"' " +
						   "AND fgsa_no='"+rset1.getString(2)+"' " +
						   "AND customer_cd='"+rset1.getString(3)+"' " +
						   "AND plant_seq_no='"+rset1.getString(4)+"' " +
						   "AND contract_type='"+rset1.getString(5)+"' " +
						   "AND nom_rev_no="+nom_rev_no+" " +
						   "AND gas_dt=TO_DATE('"+qty_dt.elementAt(j)+"','DD/MM/YYYY')";*/
//					System.out.println("---query---"+query);
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
				//////System.out.println("--flag---"+flagqty.size()+"---"+flagqty);
			}
			
		}catch(Exception e)
		{
			////System.out.println("EXCEPTION IN Method : "+methodName+" Of "+databeanName+" DataBean = "+e.getMessage());
			e.printStackTrace();
		}
	}
	private void fetchMaxYear() {

		try
		{
			String query="SELECT MAX(A.DT) FROM "
					+ "( SELECT TO_CHAR(MAX(END_DT),'YYYY') AS DT FROM DLNG_SN_MST UNION ALL "
					+ "SELECT TO_CHAR(MAX(contract_end_dt),'YYYY') FROM fms7_re_gas_cargo_dtl UNION ALL "
					+ "SELECT TO_CHAR(MAX(contract_end_dt),'YYYY') FROM FMS8_LNG_REGAS_CARGO_DTL UNION ALL "
					+ "SELECT TO_CHAR(MAX(END_DT),'YYYY') FROM DLNG_LOA_MST ) A "
					+ "ORDER BY A.DT DESC";
			rset=stmt.executeQuery(query);
			//////System.out.println("JAVA fetchMaxYear query: "+query);
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
	public String getCallFlag() {
		return callFlag;
	}
	public void setCallFlag(String callFlag) {
		this.callFlag = callFlag;
	}
	public String getMaxYear() {
		return maxYear;
	}
	public void setBillCycle(String billCycle) {
		this.billCycle = billCycle;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public Vector getInvoice_Customer_Cd() {
		return invoice_Customer_Cd;
	}
	public Vector getInvoice_Customer_Abbr() {
		return invoice_Customer_Abbr;
	}
	public Vector getInvoice_Customer_Plant_Seq_No() {
		return invoice_Customer_Plant_Seq_No;
	}
	public Vector getInvoice_Customer_Plant_Nm() {
		return invoice_Customer_Plant_Nm;
	}
	public boolean isDate_flag() {
		return date_flag;
	}
	public Vector getInvoice_Contract_Type() {
		return invoice_Contract_Type;
	}
	public Vector getInvoice_SN_Start_Dt() {
		return invoice_SN_Start_Dt;
	}
	public Vector getInvoice_SN_End_Dt() {
		return invoice_SN_End_Dt;
	}
	public Vector getInvoice_SN_Ref_No() {
		return invoice_SN_Ref_No;
	}
	public Vector getInvoice_SN_No() {
		return invoice_SN_No;
	}
	public Vector getCust_loaded_truck_cd() {
		return cust_loaded_truck_cd;
	}
	public Vector getCust_loaded_truck() {
		return cust_loaded_truck;
	}
	public Vector getCust_loaded_truck_cnt() {
		return cust_loaded_truck_cnt;
	}
	public Vector getHlpl_Invoice_Seq_No_Arr() {
		return hlpl_Invoice_Seq_No_Arr;
	}
	public Vector getHlpl_Invoice_Financial_Year_Arr() {
		return hlpl_Invoice_Financial_Year_Arr;
	}
	public Vector getHlpl_Invoice_Actual_Seq_No() {
		return hlpl_Invoice_Actual_Seq_No;
	}
	public Vector getInvoice_Bill_Period_Start_Dt() {
		return invoice_Bill_Period_Start_Dt;
	}
	public Vector getInvoice_Bill_Period_End_Dt() {
		return invoice_Bill_Period_End_Dt;
	}
	public Vector getInv_Checked_Flag() {
		return inv_Checked_Flag;
	}
	public Vector getInv_Checked_By() {
		return inv_Checked_By;
	}
	public Vector getInv_Authorized_Flag() {
		return inv_Authorized_Flag;
	}
	public Vector getInv_Authorized_By() {
		return inv_Authorized_By;
	}
	public Vector getInv_Approved_Flag() {
		return inv_Approved_Flag;
	}
	public Vector getInv_Approved_By() {
		return inv_Approved_By;
	}
	public Vector getInv_Gross_Amt_USD() {
		return inv_Gross_Amt_USD;
	}
	public Vector getInv_Gross_Amt_INR() {
		return inv_Gross_Amt_INR;
	}
	public Vector getInv_Exchg_Rate_CD() {
		return inv_Exchg_Rate_CD;
	}
	public Vector getInv_Offspec_Rate() {
		return inv_Offspec_Rate;
	}
	public Vector getVPDF_File_Nm() {
		return VPDF_File_Nm;
	}
	public Vector getVPDF_Inv_Dt() {
		return VPDF_Inv_Dt;
	}
	public Vector getCustomer_Invoice_Actual_Seq_No() {
		return customer_Invoice_Actual_Seq_No;
	}
	public Vector getInvoice_FGSA_No() {
		return invoice_FGSA_No;
	}
	public Vector getInvoice_FGSA_Rev_No() {
		return invoice_FGSA_Rev_No;
	}
	public Vector getInvoice_SN_Rev_No() {
		return invoice_SN_Rev_No;
	}
	public Vector getInvoice_Due_Dt() {
		return invoice_Due_Dt;
	}
	public Vector getInvoice_Exchg_Rate_Calculation_Method() {
		return invoice_Exchg_Rate_Calculation_Method;
	}
	public Vector getInvoice_Exchg_Rate_Cd() {
		return invoice_Exchg_Rate_Cd;
	}
	public Vector getInvoice_HLPL_Seq_No() {
		return invoice_HLPL_Seq_No;
	}
	public Vector getInvoice_Mapping_Id() {
		return invoice_Mapping_Id;
	}
	public Vector getInvoice_pre_aprv() {
		return invoice_pre_aprv;
	}
	public Vector getInvoice_tax_adj() {
		return invoice_tax_adj;
	}
	public Vector getNew_Invoice_Seq_No() {
		return new_Invoice_Seq_No;
	}
	public Vector getDelFlag() {
		return DelFlag;
	}
	public Vector getInvoice_Pending_approval() {
		return Invoice_Pending_approval;
	}
	public Vector getTax_adj_flag_pdf() {
		return tax_adj_flag_pdf;
	}
	public Vector getInvoice_inv_date() {
		return invoice_inv_date;
	}
	public Vector getInvoice_inv_period_dt() {
		return invoice_inv_period_dt;
	}
	public Vector getVdrcrflag() {
		return Vdrcrflag;
	}
	public Vector getPdf_color() {
		return pdf_color;
	}
	public Vector getCustomer_invoice_pdf_path() {
		return customer_invoice_pdf_path;
	}
	public Vector getCustomer_invoice_pdf_lock_flag() {
		return customer_invoice_pdf_lock_flag;
	}
	public Vector getCustomer_invoice_pdf_path_flag() {
		return customer_invoice_pdf_path_flag;
	}
	public void setTcqflag(String tcqflag) {
		this.tcqflag = tcqflag;
	}
	public String getTotal_Invoice_Qty() {
		return total_Invoice_Qty;
	}
	public void setTotal_Invoice_Qty(String total_Invoice_Qty) {
		this.total_Invoice_Qty = total_Invoice_Qty;
	}
	public Vector getTCQ() {
		return TCQ;
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
	public Vector getCuttDate() {
		return cuttDate;
	}
	public Vector getVctype() {
		return Vctype;
	}
	public Vector getVDrcrCriteria() {
		return VDrcrCriteria;
	}
	public Map getInvoice_signded_pdf() {
		return invoice_signded_pdf;
	}
	public Map getInvoice_view_pdf() {
		return invoice_view_pdf;
	}
	public Map getCredit_map() {
		return credit_map;
	}
	public Vector getFlagqty() {
		return flagqty;
	}
	public Map getInvoice_view_signed_pdf() {
		return invoice_view_signed_pdf;
	}
	public Map getInvoice_mail_sent() {
		return invoice_mail_sent;
	}
	public Vector getVPDF_Contact_person_cd() {
		return VPDF_Contact_person_cd;
	}
	public Vector getVPDF_due_dt() {
		return VPDF_due_dt;
	}
	public Vector getVdue_dt_flag() {
		return Vdue_dt_flag;
	}
}
