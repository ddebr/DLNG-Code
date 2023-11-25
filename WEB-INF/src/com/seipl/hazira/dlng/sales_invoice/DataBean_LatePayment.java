package com.seipl.hazira.dlng.sales_invoice;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfAnnotation;
import com.lowagie.text.pdf.PdfFormField;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.events.FieldPositioningEvents;
import com.seipl.hazira.dlng.util.RuntimeConf;

public class DataBean_LatePayment {

	private String callFlag = "";
	private String late_pay_month = "", late_pay_year = "";
	public String tempcompname="";
	
	Connection conn; 
	Statement stmt;
	Statement stmt1;
	Statement stmt2;
	ResultSet rset;
	ResultSet rset1;
	ResultSet rset2;
	ResultSet rset3;
	
	NumberFormat nf = new DecimalFormat("###########0.00");
	NumberFormat nf_ = new DecimalFormat("############.##");
	NumberFormat nf_dr = new DecimalFormat("###.####");
	NumberFormat nf1 = new DecimalFormat("##.########");
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
	    			
	    			addColumns();
	    			
	    			if(callFlag.equalsIgnoreCase("LatePayInvoicePrepare")) {
	    				fetchLatePayInvoiceDetails();
	    			} else if(callFlag.equalsIgnoreCase("Prepare_Late_Payment_Invoice")) {
	    				Prepare_Late_Payment_Invoice();
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
	
	private void addColumns() throws SQLException  {
		try {
			int count=0;
			String s="SELECT COUNT(COLUMN_NAME) FROM COLS WHERE UPPER(TABLE_NAME) LIKE 'DLNG_INVOICE_MST' "+
					" AND UPPER(COLUMN_NAME) LIKE 'TDS_TAX_AMT'";
//			System.out.println("s****"+s);
			rset=stmt.executeQuery(s);
			if(rset.next())
			{
				count=rset.getInt(1);
			}
			if(count==0)
			{
				s="ALTER TABLE DLNG_INVOICE_MST ADD TDS_TAX_AMT NUMBER(18,2)";
				stmt.executeUpdate(s);
				conn.commit();
			}
			
			count=0;
			s="SELECT COUNT(COLUMN_NAME) FROM COLS WHERE UPPER(TABLE_NAME) LIKE 'DLNG_INVOICE_MST' "+
					" AND UPPER(COLUMN_NAME) LIKE 'TDS_TAX_PERCENT'";
//			System.out.println("s****"+s);
			rset=stmt.executeQuery(s);
			if(rset.next())
			{
				count=rset.getInt(1);
			}
			if(count==0)
			{
				s="ALTER TABLE DLNG_INVOICE_MST ADD TDS_TAX_PERCENT NUMBER(18,2)";
				stmt.executeUpdate(s);
				conn.commit();
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	String queryString = "",queryString1 ="",queryString3 ="";
	Vector months=new Vector();
	Vector months_id=new Vector();

	Vector late_pay_new_inv_seq_no = new Vector();
	Vector late_pay_hlpl_inv_seq_no = new Vector();
	Vector late_pay_inv_dt = new Vector();
	Vector late_pay_due_dt = new Vector();
	Vector late_pay_recv_dt = new Vector();
	Vector late_pay_recv_amt = new Vector();
	Vector late_pay_net_amt = new Vector();
	Vector late_pay_payable_amt = new Vector();
	Vector late_pay_int_cal_percentage = new Vector();
	Vector late_pay_customer_cd = new Vector();
	Vector late_pay_contract_type = new Vector();
	Vector late_pay_customer_abbr = new Vector();	
	Vector late_pay_customer_nm = new Vector();	
	Vector late_pay_plant_seq_no = new Vector();	
	Vector late_pay_financial_year = new Vector();
	Vector late_pay_no_days = new Vector();
	
	Vector Mhlpl_inv_seq_no = new Vector();
	Vector Mnew_inv_seq_no = new Vector();
	Vector Minvoice_dt = new Vector();
	Vector Mcontract_type = new Vector();
	Vector Mchecked_flag = new Vector();
	Vector Mapproved_flag = new Vector();
	Vector Mpdf_inv_dtl = new Vector();
	Vector Mpdf_lock_flag = new Vector();
	Vector Mpdf_view = new Vector();
	Vector Mpdf_file_nm = new Vector();
	Vector Mpdf_view_signpdf = new Vector();
	Vector Mpdf_view_mail_sent = new Vector();
	Vector Mcontact_person_cd= new Vector();
	Vector irn_flag=new Vector();
	Vector irn_flag_cd=new Vector();
	String StartDt=""; 
	String EndDt="";
	Vector  Vact_cont_type = new Vector();
	String act_cont_type = "";
	private void fetchLatePayInvoiceDetails() throws Exception{

		try {

			String query="select  to_char(to_date(level,'mm'),'fmMonth') month "
					+ ",to_char(to_date(level,'mm'),'mm') month_no from  dual connect by level <= 12";
//			System.out.println("query----"+query);
			rset=stmt.executeQuery(query);
			
			while(rset.next()){
				months.add(rset.getString(1));
				months_id.add(rset.getString(2));
			}
			EndDt="";StartDt="";
			StartDt="01/"+late_pay_month+"/"+late_pay_year; 
			query="SELECT TO_CHAR(LAST_DAY(TO_DATE('"+StartDt+"','dd/mm/yyyy')),'DD/MM/YYYY') from DUAL ";
			rset=stmt.executeQuery(query);
			if(rset.next()){
				EndDt=rset.getString(1);
			}
			
			queryString = " SELECT PAY_RECV_AMT,TO_CHAR(PAY_RECV_DT,'DD/MM/YYYY'),TO_CHAR(DUE_DT,'DD/MM/YYYY'),NEW_INV_SEQ_NO,HLPL_INV_SEQ_NO,"
					+ "NET_AMT_INR,TO_CHAR(INVOICE_DT,'DD/MM/YYYY'),FINANCIAL_YEAR,CONTRACT_TYPE,FLAG,CUSTOMER_CD,"
					+ "FGSA_NO,SN_NO,SN_REV_NO,FGSA_REV_NO,MAPPING_ID "
					+ ",(TO_DATE(PAY_RECV_DT,'DD/MM/YYYY')-TO_DATE(DUE_DT,'DD/MM/YYYY')),PLANT_SEQ_NO "
					+ "FROM DLNG_INVOICE_MST WHERE FLAG NOT IN ('A','F') AND CONTRACT_TYPE "
					+ "IN ('V','S','L') AND PAY_RECV_DT IS NOT NULL "
					+ "AND PAY_RECV_DT > DUE_DT ";
			if(!late_pay_month.equals(""))
			{
//				queryString += "AND PAY_RECV_DT >= TO_DATE('"+StartDt+"','DD/MM/YYYY') AND PAY_RECV_DT <= TO_DATE('"+EndDt+"','DD/MM/YYYY') ";
				queryString += "AND PAY_RECV_DT >= TO_DATE('"+StartDt+"','DD/MM/YYYY')  ";
			}
			queryString += "ORDER BY CUSTOMER_CD,CONTRACT_TYPE,NEW_INV_SEQ_NO";
//			System.out.println("Fetching Data..."+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next()) {
				String net_amt = rset.getString(6)==null?"0":rset.getString(6);
				String pay_amt = rset.getString(1)==null?"0":rset.getString(1);
				
				String contract_type = rset.getString(9)==null?"":rset.getString(9);
				String query_per = "";
				double cal_percentage = 1;
				String cal_sign = "+";
				String no_days = rset.getString(17)==null?"0":rset.getString(17);
				
				//HIren for Service Invoices
				String mapping_id = rset.getString(16)==null?"0":rset.getString(16);
				if(contract_type.equalsIgnoreCase("V")) {
					String map_arr [] = mapping_id.split("-");
					act_cont_type = map_arr [0];
				}else {
					act_cont_type = contract_type;
				}
				
				if(contract_type.equals("S") || contract_type.equals("L") || contract_type.equals("V")) {
					query_per = "SELECT NVL(INT_CAL_PERCENTAGE,1),INT_CAL_SIGN FROM "
							+ "DLNG_SN_BILLING_DTL WHERE FLSA_NO='"+rset.getString(12)+"' AND "
							+ "FLSA_REV_NO='"+rset.getString(15)+"' AND SN_NO='"+rset.getString(13)+"' "
							+ "AND SN_REV_NO='"+rset.getString(14)+"' AND CUSTOMER_CD='"+rset.getString(11)+"' "
							+ "AND CONT_TYPE = '"+act_cont_type+"' ";
				
				} 
//				System.out.println("Fetching Billing Data..."+query_per);
				rset1 = stmt1.executeQuery(query_per);
				if(rset1.next()) {
					cal_percentage = rset1.getDouble(1)==0?1:rset1.getDouble(1);
					cal_sign = rset1.getString(2)==null?"+":rset1.getString(2);
				} else {
					if(act_cont_type.equals("S")) {
						query_per = "SELECT NVL(INT_CAL_PERCENTAGE,1),INT_CAL_SIGN FROM "
							+ "DLNG_FLSA_BILLING_DTL WHERE FLSA_NO='"+rset.getString(12)+"' AND "
							+ "CUSTOMER_CD='"+rset.getString(11)+"' AND FLSA_REV_NO='"+rset.getString(15)+"' AND "
							+ "CONT_TYPE='F'"; 
					} else if(act_cont_type.equals("L")) {
						query_per = "SELECT NVL(INT_CAL_PERCENTAGE,1),INT_CAL_SIGN FROM "
								+ "DLNG_FLSA_BILLING_DTL WHERE FLSA_NO='"+rset.getString(12)+"' AND "
								+ "CUSTOMER_CD='"+rset.getString(11)+"' AND FLSA_REV_NO='"+rset.getString(15)+"' AND "
								+ "CONT_TYPE='T'";
					}
					rset1 = stmt1.executeQuery(query_per);
				}
				
				double diff_amt = Double.parseDouble(net_amt);
				//diff_amt = Math.abs(Double.parseDouble(net_amt) - Double.parseDouble(pay_amt));
				if(diff_amt==0) {
					diff_amt = Double.parseDouble(net_amt);
				}
					
				double total_payable_amt = (diff_amt*cal_percentage*Double.parseDouble(no_days))/100;
				
				late_pay_hlpl_inv_seq_no.add(rset.getString(5)==null?"":rset.getString(5));
				late_pay_inv_dt.add(rset.getString(7)==null?"":rset.getString(7));
				late_pay_due_dt.add(rset.getString(3)==null?"":rset.getString(3));
				late_pay_recv_dt.add(rset.getString(2)==null?"":rset.getString(2));
				late_pay_recv_amt.add(nf3.format(Double.parseDouble(pay_amt)));
				late_pay_net_amt.add(nf3.format(Double.parseDouble(net_amt)));
				late_pay_payable_amt.add(nf3.format(total_payable_amt));
				late_pay_int_cal_percentage.add(cal_percentage);
				late_pay_customer_cd.add(rset.getString(11)==null?"":rset.getString(11));
				late_pay_contract_type.add(contract_type);
				Vact_cont_type.add(act_cont_type);
				late_pay_financial_year.add(rset.getString(8));
				late_pay_no_days.add(no_days);
				late_pay_plant_seq_no.add(rset.getString(18)==null?"":rset.getString(18));
				
				String nm = "";
				query_per = "SELECT CUSTOMER_ABBR,CUSTOMER_NAME FROM FMS7_CUSTOMER_MST WHERE CUSTOMER_CD='"+rset.getString(11)+"' AND "
						+ "EFF_DT <= (SELECT MAX(EFF_DT) FROM FMS7_CUSTOMER_MST WHERE CUSTOMER_CD='"+rset.getString(11)+"' )";
				rset1 = stmt1.executeQuery(query_per);
				if(rset1.next()) {
					late_pay_customer_abbr.add(rset1.getString(1)==null?rset.getString(11):rset1.getString(1));
					nm = rset1.getString(1)==null?"":rset1.getString(1);
					late_pay_customer_nm.add(rset1.getString(2)==null?"":rset1.getString(2));
				} else {
					late_pay_customer_abbr.add(rset.getString(11));
					late_pay_customer_nm.add("");
				}
				
				String hlplno = rset.getString(5);
				if(hlplno.length()==3) {
					hlplno = "0"+hlplno;
				} else if(hlplno.length()==2) {
					hlplno = "00"+hlplno;
				} else if(hlplno.length()==1) {
					hlplno = "000"+hlplno;
				} 
				hlplno = hlplno +"/"+ rset.getString(8).substring(2,5)+ rset.getString(8).substring(7,9);
				String newno = rset.getString(4)==null?"":rset.getString(4);
				if(newno.equals("")) {
					late_pay_new_inv_seq_no.add(hlplno);
					newno = hlplno;
				} else {
					late_pay_new_inv_seq_no.add(newno);
				}
				
				//CONTRACT TYPE AND FLAG = M
				//FETCHING DETAILS OF LATE PAYMENT INVOICES.....AGAINST EACH INVOICE...
				query = "SELECT HLPL_INV_SEQ_NO,NEW_INV_SEQ_NO,TO_CHAR(INVOICE_DT,'DD/MM/YYYY'), "
						+ "CONTRACT_TYPE,CHECKED_FLAG,APPROVED_FLAG,PDF_INV_DTL,PLANT_SEQ_NO,CONTACT_PERSON_CD FROM "
						+ "DLNG_INVOICE_MST WHERE REMARK_SPECIFICATION='"+newno+"' AND CUSTOMER_CD='"+rset.getString(11)+"' "
						+ "AND FLAG in ('M','I') AND CONTRACT_TYPE in ('M','I') ";
//				System.out.println("Fetching Data.1..."+query);
				rset1 = stmt1.executeQuery(query);
				if(rset1.next()) {
					Mhlpl_inv_seq_no.add(rset1.getString(1)==null?"":rset1.getString(1));
					Mnew_inv_seq_no.add(rset1.getString(2)==null?"":rset1.getString(2));
					Minvoice_dt.add(rset1.getString(3)==null?"":rset1.getString(3));
					Mcontract_type.add(rset1.getString(4)==null?"":rset1.getString(4));
					Mchecked_flag.add(rset1.getString(5)==null?"":rset1.getString(5));
					Mapproved_flag.add(rset1.getString(6)==null?"":rset1.getString(6));
					Mpdf_inv_dtl.add(rset1.getString(7)==null?"":rset1.getString(7));
					Mcontact_person_cd.add(rset1.getString(9)==null?"":rset1.getString(9));
					String col = rset1.getString(7)==null?"":rset1.getString(7);
					if(col.length()>=2){
						Mpdf_lock_flag.add("Y");
					}else{
						Mpdf_lock_flag.add("N");
					}
					String temp_cont=rset1.getString(4)==null?"":rset1.getString(4);
					String plant_nm = "";
					queryString1 = "SELECT NVL(A.plant_name,' ') FROM FMS7_CUSTOMER_PLANT_DTL A " +
							   "WHERE A.customer_cd="+rset.getString(11)+" AND A.seq_no='"+rset1.getString(8)+"' " +
							   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
							   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
							   "AND B.eff_dt<=TO_DATE('"+rset1.getString(3)+"','DD/MM/YYYY'))";
					rset2 = stmt2.executeQuery(queryString1);
					if(rset2.next())
					{
						plant_nm = rset2.getString(1);
					}
					else
					{
						plant_nm = " ";
					}
					
					
					String temp_pdf_plant_abbr = plant_nm;
					if(temp_pdf_plant_abbr.length()>40)
					{
						temp_pdf_plant_abbr = temp_pdf_plant_abbr.substring(0,40);
					}
					temp_pdf_plant_abbr = temp_pdf_plant_abbr.replaceAll(" ", "_");
					
					String file1="",file2="";
					//file2 = "INVOICE-"+rset1.getString(3).trim().substring(0,2)+rset1.getString(3).trim().substring(3,5)+rset1.getString(3).trim().substring(6)+"-"+nm+"-"+temp_pdf_plant_abbr+"-M-"+rset1.getString(1);
					file2 = "INVOICE-"+rset1.getString(3).trim().substring(0,2)+rset1.getString(3).trim().substring(3,5)+rset1.getString(3).trim().substring(6)+"-"+nm+"-"+temp_pdf_plant_abbr+"-"+temp_cont+"-"+rset1.getString(1);
					Mpdf_file_nm.add(file2);
				
					Vector invoice_path=new Vector();
					Vector invoice_path_signpdf=new Vector();
					Vector invoice_path_mail=new Vector();
					String qq1="select pdf_inv_nm,inv_type,pdf_signed_flag,mail_sent_flag from dlng_inv_pdf_dtl"
								+ " where pdf_inv_nm like '"+file2+"%' "
								+ "and created_dt is not null";
//					System.out.println("Fetching data..."+qq1);
					rset2=stmt2.executeQuery(qq1);
					while(rset2.next()){
						file1=rset2.getString(1)+"-"+rset2.getString(2);
						invoice_path.add(file1);
						invoice_path_signpdf.add(rset2.getString(3)==null?"":rset2.getString(3));//RG20191227 changed for dgs mail part
						invoice_path_mail.add(rset2.getString(4)==null?"":rset2.getString(4));
					}
					Mpdf_view.add(invoice_path);
					Mpdf_view_signpdf.add(invoice_path_signpdf);
					Mpdf_view_mail_sent.add(invoice_path_mail);
					//for irn & qrcode
					String no=rset1.getString(2)==null?"":rset1.getString(2);
					queryString="SELECT IRN_NO ,SIGN_QR_CODE FROM FMS7_INVOICE_IRN_DTL WHERE CONTRACT_TYPE='M' AND NEW_INV_SEQ_NO='"+no+"' ";
					rset2=stmt2.executeQuery(queryString);
//					System.out.println("queryString...."+queryString);
					if(rset2.next()){
						String irn_no=rset2.getString(1)==null?"":rset2.getString(1);
						String qrcode=rset2.getString(2)==null?"":rset2.getString(2);
						if(!irn_no.equals("") && (!qrcode.equals(""))){
							irn_flag.add("Y");
						}else{
							irn_flag.add("");
						}
					}else{
						irn_flag.add("");
					}
					//
					
				} else {
					Mhlpl_inv_seq_no.add("");
					Mnew_inv_seq_no.add("");
					Minvoice_dt.add("");
					Mcontract_type.add("");
					Mchecked_flag.add("");
					Mapproved_flag.add("");
					Mpdf_inv_dtl.add("");
					Mpdf_lock_flag.add("N");
					Mpdf_file_nm.add("-");
					Mpdf_view.add(new Vector());
					Mpdf_view_signpdf.add(new Vector());
					Mpdf_view_mail_sent.add(new Vector());
					Mcontact_person_cd.add("");
					irn_flag.add("");
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	String pCustomer_cd = "", pNew_inv_seq_no = "", pHlpl_inv_seq_no = "", pInvoice_date = "",
			pContract_type = "", pFinancial_year = "", pCustomer_abbr = "", pPlant_nm = "", 
			pCal_percentage = "1", pNo_days = "", pContract_start_dt = "", pContract_end_dt = "", 
			pDue_date = "", pPay_recv_date = "", pPay_recv_amt = "", pNet_amt_inr = "0", pPlant_seq_no= "",pContract_no = "";
	String temp_cont_typ = "";
	String nNew_inv_seq_no = "", nNet_amt = "", nCust_inv_seq_no = "", nInvoice_seq_no_actual = "",
			nInvoice_date = "", nTax_Structure_dtl = "", nTax_struct_cd = "", nTotal_amount = "",ndiff_days="";
	String activity="";
	String refresh_flag="N";
	String mNew_inv_seq_no = "", mHlpl_inv_seq_no = "", mInvoice_dt = "";
	String diff_days="0";
	Vector pContact_person_nm = new Vector();
	Vector pContact_person_cd = new Vector();
	Vector nTax_cd = new Vector();
	Vector nTax_abbr = new Vector();
	Vector nTax_factor = new Vector();
	String nTotal_tax_amount = "0";
	Map nTax_component_amt = new HashMap();
	String invFinancialYear = "";
	String year_interest="";
	String rmk="";
	String nRemark_1 = "", nContact_person_cd = "";
	String pFgsa_no = "", pSn_no = "", pSn_rev_no = "", pSn_ref_no = "", pFgsa_rev_no = "";
	public String customer_Invoice_DT = "";
	public String customer_Invoice_DT1 = "";
	public String customer_Invoice_Due_DT = "";
	public String customer_Invoice_Start_DT = "";
	public String customer_Invoice_End_DT = "";
	public String contact_Person_Name_And_Designation = "";
	public String contact_Person_Seq_No = "";
	public String contact_Customer_Name = "";
	public String contact_Customer_Person_Address = "";
	public String contact_Customer_Person_City = "";
	public String contact_Customer_Person_Pin = "";	
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
	String contact_Suppl_State = "";
	String contact_Suppl_State_Code = "";
	String contact_Customer_Plant_State = "";
	String contact_Customer_State_Code = "";
	public String contact_addr_flag = "";
	public Vector vSTAT_CD = new Vector();
	public Vector vSTAT_NM = new Vector();
	public Vector vSTAT_NO = new Vector();
	public Vector vSTAT_EFF_DT = new Vector();
	String sac_code = "", sac_name = "", rule_remark = "Issued under Rule-4A of the Service Tax Rules, 1994", service_desc = "";
	public String customer_Invoice_Tax_Flag = "";
	public String customer_Invoice_SN_Dt = "";
	public String customer_Invoice_FGSA_Dt = "";
	public String agreement_base="";
	public Vector invoice_agr_base = new Vector();
	public String boe_no = "";
	public String boe_dt = "";
	public String remark_3 = "";
	public String modifyadjremark="";
	Vector pSr_No = new Vector();
	Vector pItem = new Vector();
	Vector pCurrency = new Vector();
	Vector pQuantity = new Vector();
	Vector pRate = new Vector();
	Vector pAmount = new Vector();
	Vector pData = new Vector();
	String irn_no="";
	String qr_code="";
	String pdf_flag = "N";
	public String contact_Customer_GVAT_NO = "";
	public String contact_Customer_GVAT_DT = "";
	public HttpServletRequest request = null;
	String pdf_path = "", nInvoice_title = "", fileName = "", f_nm = "", inv_type_pdf = "", url_start = "";
	String tax_struct_cd = "0";
	String tax_flag = "";
	public void Prepare_Late_Payment_Invoice() throws Exception {
		try {
			String query_per = "";
			double cal_percentage = 1;
			String cal_perc = "";
			
			String cal_sign = "+";
			String int_cal_cd="";
			String no_days = "", net_amt = "", pay_amt = "", plant_seq_no = "", invoice_dt1 = "", 
					mapping_id = "", fgsa_no = "", fgsa_rev_no = "", sn_no = "", sn_rev_no = "", 
					sn_ref_no = "", sale_price = "0", total_qty = "0", invoice_dt_formated = "";
			double total_payable_amt = 0;
			int daysin_year=0;
			
			//For getting no of days in a year//
			queryString="SELECT ADD_MONTHS(TRUNC(SYSDATE,'Y'),12) - TRUNC(SYSDATE,'Y') FROM DUAL";
			rset=stmt.executeQuery(queryString);
			if(rset.next()){
				daysin_year=rset.getInt(1);
			}
			//System.out.println("query---"+queryString);
			//
			
			String query = "SELECT FGSA_NO,FGSA_REV_NO,SN_NO,SN_REV_NO,TO_CHAR(DUE_DT,'DD/MM/YYYY'),"
					+ "NET_AMT_INR,TAX_STRUCT_CD,PAY_RECV_AMT,TO_CHAR(PAY_RECV_DT,'DD/MM/YYYY'), "
					+ "MAPPING_ID,(TO_DATE(PAY_RECV_DT,'DD/MM/YYYY')-TO_DATE(DUE_DT,'DD/MM/YYYY')),"
					+ "PLANT_SEQ_NO,SALE_PRICE,TOTAL_QTY,TO_CHAR(INVOICE_DT,'DD-MON-YY'),TAX_FLAG "
					+ "FROM DLNG_INVOICE_MST WHERE CONTRACT_TYPE='"+pContract_type+"' AND CUSTOMER_CD='"+pCustomer_cd+"' "
					+ "AND FINANCIAL_YEAR='"+pFinancial_year+"' AND HLPL_INV_SEQ_NO='"+pHlpl_inv_seq_no+"' "
					+ "AND INVOICE_DT = TO_DATE('"+pInvoice_date+"','DD/MM/YYYY') ";
			System.out.println("Fetching All Data..."+query);
			rset = stmt.executeQuery(query);
			if(rset.next()) {
				
				net_amt = rset.getString(6)==null?"0":rset.getString(6);
				tax_struct_cd = rset.getString(7)==null?"0":rset.getString(7);
				tax_flag = rset.getString(16)==null?"":rset.getString(16);
				pay_amt = rset.getString(8)==null?"0":rset.getString(8); 
				pDue_date = rset.getString(5)==null?"":rset.getString(5);
				pPay_recv_date = rset.getString(9)==null?"":rset.getString(9);
				pPay_recv_amt = nf3.format(Double.parseDouble(pay_amt));
				pNet_amt_inr = nf3.format(Double.parseDouble(net_amt));
				pPlant_seq_no = plant_seq_no = rset.getString(12)==null?"":rset.getString(12);
				no_days = rset.getString(11)==null?"0":rset.getString(11);
				mapping_id = rset.getString(10)==null?"":rset.getString(10);
				invoice_dt1 = pInvoice_date;
				fgsa_no = rset.getString(1);
				fgsa_rev_no = rset.getString(2);
				sn_no = rset.getString(3);
				sn_rev_no = rset.getString(4);
				pFgsa_no = fgsa_no; pSn_no = sn_no; pSn_rev_no = sn_rev_no;
				pFgsa_rev_no = fgsa_rev_no;
				sale_price = rset.getString(13)==null?"0.00":nf2.format(Double.parseDouble(rset.getString(13)));
				total_qty = rset.getString(14)==null?"0.00":nf3.format(Double.parseDouble(rset.getString(14)));
				invoice_dt_formated = rset.getString(15)==null?invoice_dt1:rset.getString(15);
				
//				System.out.println("pContract_type---"+pContract_type+"***act_cont_type***"+act_cont_type);
				temp_cont_typ = pContract_type;
				if(pContract_type.equals("V")) {
					pContract_type = act_cont_type;
				}
				
				if(pContract_type.equals("S") || pContract_type.equals("L")) {
					pContract_no = rset.getString(3);
					query_per = "SELECT NVL(INT_CAL_PERCENTAGE,0),INT_CAL_SIGN,INT_CAL_RATE_CD FROM "
							+ "DLNG_SN_BILLING_DTL WHERE FLSA_NO='"+rset.getString(1)+"' AND "
							+ "FLSA_REV_NO='"+rset.getString(2)+"' AND SN_NO='"+rset.getString(3)+"' "
							+ "AND SN_REV_NO='"+rset.getString(4)+"' AND CUSTOMER_CD='"+pCustomer_cd+"' "
							+ "AND CONT_TYPE = '"+pContract_type+"' ";
				} else if(pContract_type.equals("C") || pContract_type.equals("B")) {
					pContract_no = rset.getString(1);
					query_per = "SELECT INT_CAL_PERCENTAGE,INT_CAL_SIGN,INT_CAL_RATE_CD FROM "
							+ "FMS8_LNG_REGAS_BILLING_DTL WHERE MAPPING_ID='"+rset.getString(10)+"' ";
				} else if(pContract_type.equals("R")) {
					pContract_no = rset.getString(1);
					query_per = "SELECT INT_CAL_PERCENTAGE,INT_CAL_SIGN,INT_CAL_RATE_CD FROM "
							+ "FMS7_RE_GAS_BILLING_DTL WHERE RE_GAS_NO='"+rset.getString(1)+"' AND "
							+ "RE_GAS_REV_NO='"+rset.getString(2)+"' AND CUSTOMER_CD='"+pCustomer_cd+"' "
							+ "AND CONTRACT_TYPE = '"+pContract_type+"' ";
				}
//				System.out.println("Fetching Billing Data..."+query_per);
				rset1 = stmt1.executeQuery(query_per);
				if(rset1.next()) {
					cal_percentage = rset1.getDouble(1)==0?0:rset1.getDouble(1);
					//cal_sign = rset1.getString(2)==null?"+":rset1.getString(2);
					cal_sign = rset1.getString(2)==null?"":rset1.getString(2);
					int_cal_cd= rset1.getString(3)==null?"0":rset1.getString(3);
				} else {
					if(pContract_type.equals("S")) {
						query_per = "SELECT NVL(INT_CAL_PERCENTAGE,0),INT_CAL_SIGN,INT_CAL_RATE_CD FROM "
							+ "DLNG_FLSA_BILLING_DTL WHERE FLSA_NO='"+rset.getString(1)+"' AND "
							+ "CUSTOMER_CD='"+pCustomer_cd+"' AND FLSA_REV_NO='"+rset.getString(2)+"' AND "
							+ "CONT_TYPE='F'"; 
					} else if(pContract_type.equals("L")) {
						query_per = "SELECT NVL(INT_CAL_PERCENTAGE,0),INT_CAL_SIGN,INT_CAL_RATE_CD FROM "
								+ "DLNG_FLSA_BILLING_DTL WHERE FLSA_NO='"+rset.getString(1)+"' AND "
								+ "CUSTOMER_CD='"+pCustomer_cd+"' AND FLSA_REV_NO='"+rset.getString(2)+"' AND "
								+ "CONT_TYPE='T'";
					} else if(pContract_type.equals("C")  || pContract_type.equals("B")) {
						String map_id[] = rset.getString(10).split("-");
						String map = map_id[0]+"-"+map_id[1]+"-0-0-0";
						query_per = "SELECT NVL(INT_CAL_PERCENTAGE,0),INT_CAL_SIGN,INT_CAL_RATE_CD FROM "
								+ "FMS8_LNG_REGAS_BILLING_DTL WHERE "
								+ "MAPPING_ID='"+map+"' "; 
					}
//					System.out.println("query_per--"+query_per);
					rset1 = stmt1.executeQuery(query_per);
					cal_percentage = rset1.getDouble(1)==0?0:rset1.getDouble(1);
				//	cal_sign = rset1.getString(2)==null?"+":rset1.getString(2);
					cal_sign = rset1.getString(2)==null?"":rset1.getString(2);
					int_cal_cd= rset1.getString(3)==null?"0":rset1.getString(3);
				}
				
				//For adding rate
				String int_cal="0";
				
				String int_rate_nm="";
				queryString="SELECT int_val FROM FMS7_INT_PAY_RATE_ENTRY A WHERE A.INT_RATE_CD='"+int_cal_cd+"' and "
						+ " A.eff_dt=(select max(eff_dt) from FMS7_INT_PAY_RATE_ENTRY B where B.INT_RATE_CD='"+int_cal_cd+"' "
						+ "and A.INT_RATE_CD=B.INT_RATE_CD)";
				rset=stmt.executeQuery(queryString);
//				System.out.println("--queryString--"+queryString);
				if(rset.next()){
					int_cal=rset.getString(1)==null?"0":rset.getString(1);
					
				}else{
					int_cal="0";
				}
				String queryString1="SELECT INT_RATE_NM FROM FMS7_CONT_INT_RATE_MST WHERE INT_RATE_CD ='"+int_cal_cd+"'";
				rset1=stmt1.executeQuery(queryString1);
				if(rset1.next()){
					int_rate_nm=rset1.getString(1)==null?"":rset1.getString(1);
				}
//				System.out.println("cal_sign---"+cal_sign);
				if(cal_sign.equals("-")){
					if(!int_cal.equals("0")){
						cal_perc=nf1.format((Double.parseDouble(int_cal)-cal_percentage)/daysin_year);
						year_interest=""+(Double.parseDouble(int_cal)-cal_percentage);
						rmk="Annualized Interest rate is "+int_rate_nm+"("+int_cal+") - "+cal_percentage+" = "+year_interest+" %";
					}else{
						cal_perc=nf1.format((cal_percentage)/daysin_year);
						year_interest=""+cal_percentage;
						//rmk="Annualized Interest rate is "+cal_percentage+"%";
					}
				}else if(cal_sign.equals("+")){

					//System.out.println("----"+int_cal+"--cal_percentage-"+cal_percentage);
					if(!int_cal.equals("0")){
						cal_perc=nf1.format((Double.parseDouble(int_cal)+cal_percentage)/daysin_year);
						year_interest=""+(Double.parseDouble(int_cal)+cal_percentage);
						rmk="Annualized Interest rate is "+int_rate_nm+"("+int_cal+") + "+cal_percentage+" = "+year_interest+" %";
					}else{
						cal_perc=nf1.format((cal_percentage)/daysin_year);
						year_interest=""+cal_percentage;
						//rmk="Annualized Interest rate is "+cal_percentage+"%";
					}
				
				}else {
					//System.out.println("----"+int_cal+"--cal_percentage-"+cal_percentage);
					if(!int_cal.equals("0")){
						cal_perc=nf1.format((cal_percentage)/daysin_year);
						year_interest=""+(cal_percentage);
						//rmk="Annualized Interest rate is "+int_rate_nm+"("+int_cal+") + "+cal_percentage+" = "+year_interest+" %";
					}else{
						cal_perc=nf1.format((cal_percentage)/daysin_year);
						year_interest=""+cal_percentage;
						//rmk="Annualized Interest rate is "+cal_percentage+"%";
					}
				}
				//System.out.println("cal_perc---"+cal_perc+"----");
				//
				
				if(refresh_flag.equals("Y")) {
					if(!pCal_percentage.equals(""))
						cal_percentage = Double.parseDouble(pCal_percentage);
					invoice_dt1 = nInvoice_date;
				}
				
					double diff_amt = Double.parseDouble(net_amt);
					//diff_amt = Math.abs(Double.parseDouble(net_amt) - Double.parseDouble(pay_amt));
					if(diff_amt==0) {
						diff_amt = Double.parseDouble(net_amt);
					}
						
					//total_payable_amt = (diff_amt*cal_percentage*Double.parseDouble(no_days))/100;
					if(!diff_days.equals("")){
						total_payable_amt = (diff_amt*Double.parseDouble(year_interest)*Double.parseDouble(diff_days))/(100 * daysin_year);//changed logic for discount dayss
					}else{
						total_payable_amt = (diff_amt*Double.parseDouble(year_interest)*Double.parseDouble(no_days))/(100 * daysin_year);//changed logic for discount dayss
					}
					//System.out.println("--total_payable_amt--"+total_payable_amt);
					if(pContract_type.equals("C")) {
						total_payable_amt = Math.round(total_payable_amt);
					}
					pCal_percentage = ""+year_interest;
					pNo_days = ""+no_days;
					
					//tax details
					if(pContract_type.equals("C") ||  pContract_type.equals("B")) {
						String querystring = "SELECT A.TAX_STRUCT_DTL,A.TAX_STRUCT_CD FROM FMS7_CUSTOMER_SERVICE_TAX_DTL A WHERE " +
								  "A.customer_cd='"+pCustomer_cd+"' AND " +
								  "A.plant_seq_no='"+plant_seq_no+"' AND " +
						 		  "A.tax_struct_dt=(SELECT MAX(B.tax_struct_dt) FROM FMS7_CUSTOMER_SERVICE_TAX_DTL B WHERE " +
						 		  "A.customer_cd=B.customer_cd AND A.plant_seq_no=B.plant_seq_no AND " +
						 		  "B.tax_struct_dt<=TO_DATE('"+invoice_dt1+"','DD/MM/YYYY'))";
						rset1 = stmt1.executeQuery(querystring);
						if(rset1.next())
						{
							nTax_Structure_dtl = rset1.getString(1)==null?"":rset1.getString(1);
							nTax_struct_cd = rset1.getString(2)==null?"0":rset1.getString(2);
						}
						else
						{
							nTax_Structure_dtl = "";
							nTax_struct_cd = "0";
						}
					}
					 else if(pContract_type.equals("S") || pContract_type.equals("L")) {
						 queryString3 = "SELECT A.TAX_STRUCT_DTL,A.TAX_STRUCT_CD FROM FMS7_CUSTOMER_TAX_STRUCT_DTL A WHERE " +
								   "customer_cd='"+pCustomer_cd+"' AND plant_seq_no='"+plant_seq_no+"' AND " +
								   "A.tax_struct_dt=(SELECT MAX(B.tax_struct_dt) FROM FMS7_CUSTOMER_TAX_STRUCT_DTL B WHERE " +
						 		  "A.customer_cd=B.customer_cd AND A.plant_seq_no=B.plant_seq_no AND " +
						 		  "B.tax_struct_dt<=TO_DATE('"+invoice_dt1+"','DD/MM/YYYY'))";
						rset1 = stmt1.executeQuery(queryString3);
						if(rset1.next())
						{
							nTax_Structure_dtl = rset1.getString(1)==null?"":rset1.getString(1);
							nTax_struct_cd = rset1.getString(2)==null?"0":rset1.getString(2);
						}
					}
			}
			
			String contact_person_cd = "0";
			if(activity.equals("M") || activity.equals("V")) {
				query_per = "SELECT NVL(OFFSPEC_RATE,0),TAX_STRUCT_CD,NVL(REMARK_1,''),GROSS_AMT_INR,"
						+ "NET_AMT_INR,TAX_AMT_INR,CONTACT_PERSON_CD,CUST_INV_SEQ_NO,TO_CHAR(INVOICE_DT,'DD-Mon-YY'),OFFSPEC_QTY "
						+ "FROM DLNG_INVOICE_MST WHERE "
						//+ "CONTRACT_TYPE='M' AND FLAG='M' AND NEW_INV_SEQ_NO='"+mNew_inv_seq_no+"' "
						+ "CONTRACT_TYPE in ('M','I') AND FLAG in ('M','I') AND NEW_INV_SEQ_NO='"+mNew_inv_seq_no+"' "
					//	+ "AND HLPL_INV_SEQ_NO='"+mHlpl_inv_seq_no+"' AND FINANCIAL_YEAR='"+pFinancial_year+"'"
						+ " AND CUSTOMER_CD='"+pCustomer_cd+"' AND REMARK_SPECIFICATION='"+pNew_inv_seq_no+"' ";
//				System.out.println("Fetchnig Data For Modification.."+query_per);
				rset1 = stmt1.executeQuery(query_per);
				if(rset1.next()) {
					nTax_struct_cd = rset1.getString(2)==null?"":rset1.getString(2);
					nRemark_1 = rset1.getString(3)==null?"":rset1.getString(3);
					nInvoice_seq_no_actual = mHlpl_inv_seq_no;
					nCust_inv_seq_no = rset1.getString(8)==null?"":rset1.getString(8);
					ndiff_days=rset1.getString(10)==null?"":rset1.getString(10);
					nNew_inv_seq_no = mNew_inv_seq_no;
					//GST_INVOICE_SEQ_NO=mNew_inv_seq_no;
					customer_Invoice_DT = rset1.getString(9);
					
					if(refresh_flag.equals("N")) {
						cal_percentage = rset1.getDouble(1)==0?Double.parseDouble(pCal_percentage):rset1.getDouble(1);
						pCal_percentage = ""+cal_percentage;
						contact_person_cd = rset1.getString(7)==null?"0":rset1.getString(7);
						total_payable_amt = rset1.getString(4)==null?0:Double.parseDouble(rset1.getString(4));
						nContact_person_cd = contact_person_cd;
						invoice_dt1 = mInvoice_dt;
					}
				}
				
				//tax details
				if(pContract_type.equals("C")  || pContract_type.equals("B")) {
					String querystring = "SELECT A.TAX_STRUCT_DTL,A.TAX_STRUCT_CD FROM FMS7_CUSTOMER_SERVICE_TAX_DTL A WHERE " +
							  "A.customer_cd='"+pCustomer_cd+"' AND " +
							  "A.plant_seq_no='"+plant_seq_no+"' AND " +
					 		  "A.tax_struct_dt=(SELECT MAX(B.tax_struct_dt) FROM FMS7_CUSTOMER_SERVICE_TAX_DTL B WHERE " +
					 		  "A.customer_cd=B.customer_cd AND A.plant_seq_no=B.plant_seq_no AND " +
					 		  "B.tax_struct_dt<=TO_DATE('"+invoice_dt1+"','DD/MM/YYYY'))";
					rset1 = stmt1.executeQuery(querystring);
					if(rset1.next())
					{
						nTax_Structure_dtl = rset1.getString(1)==null?"":rset1.getString(1);
						nTax_struct_cd = rset1.getString(2)==null?"0":rset1.getString(2);
					}
					else
					{
						nTax_Structure_dtl = "";
						nTax_struct_cd = "0";
					}
				}
				else if(pContract_type.equals("S") || pContract_type.equals("L")) {
					 queryString3 = "SELECT A.TAX_STRUCT_DTL,A.TAX_STRUCT_CD FROM FMS7_CUSTOMER_TAX_STRUCT_DTL A WHERE " +
							   "customer_cd='"+pCustomer_cd+"' AND plant_seq_no='"+plant_seq_no+"' AND " +
							   "A.tax_struct_dt=(SELECT MAX(B.tax_struct_dt) FROM FMS7_CUSTOMER_TAX_STRUCT_DTL B WHERE " +
					 		  "A.customer_cd=B.customer_cd AND A.plant_seq_no=B.plant_seq_no AND " +
					 		  "B.tax_struct_dt<=TO_DATE('"+invoice_dt1+"','DD/MM/YYYY'))";
					rset1 = stmt1.executeQuery(queryString3);
					if(rset1.next())
					{
						nTax_Structure_dtl = rset1.getString(1)==null?"":rset1.getString(1);
						nTax_struct_cd = rset1.getString(2)==null?"0":rset1.getString(2);
					}
				}
			}
			
			//fetching contract person name
			query="SELECT NVL(A.seq_no,'0'),NVL(contact_person,' '),NVL(contact_desig,' '),eff_dt FROM FMS7_CUSTOMER_CONTACT_MST A"
					+ " WHERE A.customer_cd='"+pCustomer_cd+"' "
					+ " AND A.def_inv_flag='Y' "
					+ " AND A.active_flag='Y' "
					+ " AND def_inv_to_flag = 'Y' "
					+ " AND (A.addr_flag='B'"
					+ " OR A.addr_flag='R' OR A.addr_flag='C' OR A.addr_flag='"+plant_seq_no+"' or a.addr_flag='P"+plant_seq_no+"') AND "
					+ "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_CONTACT_MST B "
					+ "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no "
					+ "AND B.eff_dt<=TO_DATE('"+invoice_dt1+"','DD/MM/YYYY'))";
//			System.out.println("contact query==="+query);
			rset1=stmt1.executeQuery(query);
			while(rset1.next()){
				pContact_person_cd.add(rset1.getString(1)==null?"":rset1.getString(1));
				pContact_person_nm.add(rset1.getString(2)==null?"":rset1.getString(2));
			}
			
			queryString1 = "SELECT NVL(A.plant_name,' ') FROM FMS7_CUSTOMER_PLANT_DTL A " +
					   "WHERE A.customer_cd='"+pCustomer_cd+"' AND A.seq_no='"+plant_seq_no+"' " +
					   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
					   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
					   "AND B.eff_dt<=TO_DATE('"+invoice_dt1+"','DD/MM/YYYY'))";
			rset1 = stmt1.executeQuery(queryString1);
			if(rset1.next())
			{
				pPlant_nm = rset1.getString(1);
			}
			else
			{
				pPlant_nm = " ";
			}
			
			//contract strat dt & end date
			String ContractStartDate = "", ContractEndDate = "", querystring = "";
			if(pContract_type.equals("C")  || pContract_type.equals("B")) {
				querystring="select to_char(start_dt,'dd/mm/yyyy'),to_char(end_dt,'dd/mm/yyyy') " +
					" from FMS8_LNG_REGAS_MST WHERE "
					+ "MAPPING_ID='"+mapping_id+"'";
				rset1=stmt1.executeQuery(querystring);	
			} else if(pContract_type.equals("S")) {
				querystring = "select to_char(start_dt,'dd/mm/yyyy'),to_char(end_dt,'dd/mm/yyyy') "
						+ "from dlng_sn_mst where sn_no='"+sn_no+"' and sn_rev_no='"+sn_rev_no+"' and fLsa_no='"+fgsa_no+"' "
						+ "and FLSA_REV_NO='"+fgsa_rev_no+"' and customer_cd='"+pCustomer_cd+"' ";
				rset1=stmt1.executeQuery(querystring);	
			} else if(pContract_type.equals("L")) {
				querystring = "select to_char(start_dt,'dd/mm/yyyy'),to_char(end_dt,'dd/mm/yyyy') "
						+ "from dlng_loa_mst where loa_no='"+sn_no+"' and loa_rev_no='"+sn_rev_no+"' and tender_no='"+fgsa_no+"' "
						+ "and customer_cd='"+pCustomer_cd+"' ";
				rset1=stmt1.executeQuery(querystring);	
			} else if(pContract_type.equals("R")) {
				querystring="select to_char(contract_start_dt,'dd/mm/yyyy'),to_char(contract_end_dt,'dd/mm/yyyy') " +
						" from FMS7_RE_GAS_CARGO_DTL WHERE RE_GAS_NO='"+fgsa_no+"' AND REV_NO='"+fgsa_rev_no+"' "
						+ "AND CARGO_SEQ_NO = '"+sn_no+"' and customer_cd='"+pCustomer_cd+"'";
				rset1=stmt1.executeQuery(querystring);	
			}

			if(rset1.next()){
				ContractStartDate=rset1.getString(1);
				ContractEndDate=rset1.getString(2);
			}
			pContract_start_dt = ContractStartDate;
			pContract_end_dt = ContractEndDate;
			
			queryString = "SELECT customer_name,gst_tin_no,cst_tin_no,TO_CHAR(gst_tin_dt,'DD-MM-YYYY'),TO_CHAR(cst_tin_dt,'DD-MM-YYYY') " +
					  "FROM FMS7_CUSTOMER_MST A " +
					  "WHERE A.customer_cd='"+pCustomer_cd+"' AND " +
					  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_MST B " +
					  "WHERE A.customer_cd=B.customer_cd AND " +
					  "B.eff_dt<=TO_DATE('"+invoice_dt1+"','DD/MM/YYYY'))";
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				contact_Customer_Name = rset.getString(1)==null?"":rset.getString(1);
				contact_Customer_GST_NO = rset.getString(2)==null?"":rset.getString(2);
				contact_Customer_CST_NO = rset.getString(3)==null?"":rset.getString(3);
				contact_Customer_GST_DT = rset.getString(4)==null?"":rset.getString(4);
				contact_Customer_CST_DT = rset.getString(5)==null?"":rset.getString(5);
			}
			
			queryString = "SELECT supplier_name,gst_tin_no,cst_tin_no,TO_CHAR(gst_tin_dt,'DD-MM-YYYY')," +
					  "TO_CHAR(cst_tin_dt,'DD-MM-YYYY'),addl_no,TO_CHAR(addl_issue_dt,'DD-MM-YYYY'),"
					  + "PAN_NO,TO_CHAR(PAN_ISSUE_DT,'DD-MM-YYYY'),GSTIN_NO,TO_CHAR(GSTIN_DT,'DD/MM/YYYY') " +
					  "FROM FMS7_SUPPLIER_MST A  WHERE A.supplier_cd=1 AND  A.eff_dt=(SELECT MAX(B.eff_dt) " +
					  "FROM FMS7_SUPPLIER_MST B  WHERE A.supplier_cd=B.supplier_cd AND " +
					  "B.eff_dt<=TO_DATE('"+invoice_dt1+"','DD/MM/YYYY'))";
			rset1 = stmt1.executeQuery(queryString);
			if(rset1.next()) {
				contact_Suppl_Name = rset1.getString(1)==null?"":rset1.getString(1);
				contact_Suppl_GST_NO = rset1.getString(2)==null?"":rset1.getString(2);
				contact_Suppl_CST_NO = rset1.getString(3)==null?"":rset1.getString(3);
				contact_Suppl_GST_DT = rset1.getString(4)==null?"":rset1.getString(4);
				contact_Suppl_CST_DT = rset1.getString(5)==null?"":rset1.getString(5);
				contact_Suppl_Service_Tax_NO = rset1.getString(6)==null?"":rset1.getString(6);
				contact_Suppl_Service_Tax_DT = rset1.getString(7)==null?"":rset1.getString(7);
				contact_Suppl_PAN_NO = rset1.getString(8)==null?"":rset1.getString(8);	
				contact_Suppl_PAN_DT = rset1.getString(9)==null?"":rset1.getString(9);	
				contact_Suppl_GSTIN_NO = rset1.getString(10)==null?"":rset1.getString(10);	//RS01062017
				contact_Suppl_GSTIN_DT = rset1.getString(11)==null?"":rset1.getString(11);	//RS01062017
			}
			
			queryString = "SELECT addr,city,pin,NVL(STATE,'N/A')  FROM FMS7_SUPPLIER_ADDRESS_MST A  WHERE A.supplier_cd=1"
					+ " AND A.address_type='R' AND  A.eff_dt=(SELECT MAX(B.eff_dt) FROM "
					+ "FMS7_SUPPLIER_ADDRESS_MST B  WHERE A.supplier_cd=B.supplier_cd AND B.address_type='R'"
					+ " AND  B.eff_dt<=TO_DATE('"+invoice_dt1+"','DD/MM/YYYY'))";
			rset1 = stmt1.executeQuery(queryString);
			if(rset1.next()) {
				contact_Suppl_Person_Address = rset1.getString(1)==null?"":rset1.getString(1);
				contact_Suppl_Person_City = rset1.getString(2)==null?"":rset1.getString(2);
				contact_Suppl_Person_Pin = rset1.getString(3)==null?"":rset1.getString(3);
				contact_Suppl_State = rset1.getString(4)==null?"":rset1.getString(4);
				
				if(!contact_Suppl_State.equals("")) {
					queryString = "SELECT NVL(STATE_CODE,'N/A') FROM STATE_MST WHERE UPPER(STATE_NM) = '"+contact_Suppl_State.toUpperCase()+"' ";
					rset1 = stmt1.executeQuery(queryString);
					if(rset1.next()) {
						contact_Suppl_State_Code = rset1.getString(1);
					}
				}
			}
			
			queryString = "SELECT NVL(A.seq_no,'0'),NVL(contact_person,' '),NVL(contact_desig,' '),addr_flag " +
					  "FROM FMS7_CUSTOMER_CONTACT_MST A  WHERE"
					  + " A.customer_cd='"+pCustomer_cd+"' "
					  + " AND A.seq_no='"+contact_person_cd+"'"
					  + " AND A.active_flag='Y'"
					  + " AND A.def_inv_flag='Y' "
					  + " AND def_inv_to_flag = 'Y' "
					  + " AND A.eff_dt=(SELECT MAX(B.eff_dt)"
					  + " FROM FMS7_CUSTOMER_CONTACT_MST B WHERE A.customer_cd=B.customer_cd AND "
					  + "A.seq_no=B.seq_no AND B.seq_no='"+contact_person_cd+"' AND "
					  + "B.eff_dt<=TO_DATE('"+invoice_dt1+"','DD/MM/YYYY'))";
//			System.out.println("==querystin"+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next()) {
				contact_Person_Seq_No = rset.getString(1)==null?"":rset.getString(3);
				if(rset.getString(3).equals(" ")) {
					contact_Person_Name_And_Designation = rset.getString(2).trim();
				}
				else {
					contact_Person_Name_And_Designation = rset.getString(2).trim()+" ("+rset.getString(3).trim()+")";
				}
				contact_addr_flag = rset.getString(4)==null?"":rset.getString(4);
			}
			
			if(contact_addr_flag.trim().equalsIgnoreCase("R") || contact_addr_flag.trim().equalsIgnoreCase("C") || contact_addr_flag.trim().equalsIgnoreCase("B"))
			{

				queryString = "SELECT addr,city,pin " +
							  "FROM FMS7_CUSTOMER_ADDRESS_MST A " +
							  "WHERE A.customer_cd="+pCustomer_cd+" AND A.address_type='"+contact_addr_flag+"' AND " +
							  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_ADDRESS_MST B " +
							  "WHERE A.customer_cd=B.customer_cd AND B.address_type='"+contact_addr_flag+"' AND " +
							  "B.eff_dt<=TO_DATE('"+invoice_dt1+"','DD/MM/YYYY'))";
				rset = stmt.executeQuery(queryString);
				if(rset.next()) {
					contact_Customer_Person_Address = rset.getString(1)==null?"":rset.getString(1);
					contact_Customer_Person_City = rset.getString(2)==null?"":rset.getString(2);
					contact_Customer_Person_Pin = rset.getString(3)==null?"":rset.getString(3);
				}
				
				queryString = "SELECT PLANT_STATE FROM FMS7_CUSTOMER_PLANT_DTL WHERE CUSTOMER_CD='"+pCustomer_cd+"' "
						+ "AND SEQ_NO = '"+plant_seq_no.replace("P", "")+"' AND EFF_DT = (SELECT MAX(EFF_DT) FROM FMS7_CUSTOMER_PLANT_DTL B "
						+ "WHERE B.SEQ_NO='"+plant_seq_no.replace("P", "")+"' AND B.CUSTOMER_CD='"+pCustomer_cd+"' AND B.EFF_DT <= TO_DATE('"+pContract_start_dt+"','DD/MM/YYYY'))";
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
				queryString1 = "";
				if(!contact_addr_flag.equalsIgnoreCase(""))
				new_plant_no = contact_addr_flag.trim().substring(1);
				
				if(new_plant_no.length()>=1) {
					queryString = "SELECT plant_addr,plant_city,plant_pin " +
								  "FROM FMS7_CUSTOMER_PLANT_DTL A " +
								  "WHERE A.customer_cd='"+pCustomer_cd+"' AND A.seq_no='"+new_plant_no+"' AND " +
								  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
								  "WHERE A.customer_cd=B.customer_cd AND B.seq_no='"+new_plant_no+"' AND " +
								  "B.eff_dt<=TO_DATE('"+pContract_start_dt+"','DD/MM/YYYY'))";
					
					queryString1 = "SELECT PLANT_STATE FROM FMS7_CUSTOMER_PLANT_DTL WHERE CUSTOMER_CD='"+pCustomer_cd+"' "
							+ "AND SEQ_NO = '"+new_plant_no+"' AND EFF_DT = (SELECT MAX(EFF_DT) FROM FMS7_CUSTOMER_PLANT_DTL B "
							+ "WHERE B.SEQ_NO='"+new_plant_no+"' AND B.CUSTOMER_CD='"+pCustomer_cd+"' AND B.EFF_DT <= TO_DATE('"+pContract_start_dt+"','DD/MM/YYYY'))";
				} else {
					queryString = "SELECT plant_addr,plant_city,plant_pin " +
								  "FROM FMS7_CUSTOMER_PLANT_DTL A " +
								  "WHERE A.customer_cd='"+pCustomer_cd+"' AND A.seq_no='"+plant_seq_no.replace("P", "")+"' AND " +
								  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
								  "WHERE A.customer_cd=B.customer_cd AND B.seq_no='"+plant_seq_no.replace("P", "")+"' AND " +
								  "B.eff_dt<=TO_DATE('"+pContract_start_dt+"','DD/MM/YYYY'))";
					
					queryString1 = "SELECT PLANT_STATE FROM FMS7_CUSTOMER_PLANT_DTL WHERE CUSTOMER_CD='"+pCustomer_cd+"' "
							+ "AND SEQ_NO = '"+plant_seq_no.replace("P", "")+"' AND EFF_DT = (SELECT MAX(EFF_DT) FROM FMS7_CUSTOMER_PLANT_DTL B "
							+ "WHERE B.SEQ_NO='"+plant_seq_no.replace("P", "")+"' AND B.CUSTOMER_CD='"+pCustomer_cd+"' AND B.EFF_DT <= TO_DATE('"+pContract_start_dt+"','DD/MM/YYYY'))";
				}
//				System.out.println("queryString---"+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next()) {
					contact_Customer_Person_Address = rset.getString(1)==null?"":rset.getString(1);
					contact_Customer_Person_City = rset.getString(2)==null?"":rset.getString(2);
					contact_Customer_Person_Pin = rset.getString(3)==null?"":rset.getString(3);
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
			
			double total_tax_amt = 0;
			double tax_amt = 0;
			String tax_cd = "0";
			String tax_factor = "0.00";
			String invWiseTaxFlg = "N";
			
			queryString = "SELECT A.TAX_STRUCT_DTL,A.TAX_STRUCT_CD FROM FMS7_TAX_DTL_INVOICEWISE A WHERE " +
					  "A.customer_cd='"+pCustomer_cd+"' AND " +
					  "A.plant_seq_no='"+plant_seq_no+"' AND A.contract_type='M' and A.invflag='M' AND " +
			 		  "A.tax_struct_dt=(SELECT MAX(B.tax_struct_dt) FROM FMS7_TAX_DTL_INVOICEWISE B WHERE " +
			 		  "A.customer_cd=B.customer_cd AND A.plant_seq_no=B.plant_seq_no AND " +
			 		  "B.tax_struct_dt<=TO_DATE('"+invoice_dt1+"','DD/MM/YYYY') AND B.contract_type='M' and B.invflag ='M')";
//			System.out.println("queryString---"+queryString);
			rset1 = stmt1.executeQuery(queryString);
			if(rset1.next())
			{
				nTax_Structure_dtl = rset1.getString(1)==null?"":rset1.getString(1);
				nTax_struct_cd = rset1.getString(2)==null?"0":rset1.getString(2);
				
				invWiseTaxFlg = "Y";
			}
//			System.out.println("invWiseTaxFlg----"+invWiseTaxFlg);
			if(temp_cont_typ.equalsIgnoreCase("V") && invWiseTaxFlg.equalsIgnoreCase("N")) {
				
				nTax_struct_cd = tax_struct_cd;
				
				if(tax_flag.equalsIgnoreCase("I")) {
					
					tax_amt = (total_payable_amt*Double.parseDouble(tax_struct_cd))/100;
					total_tax_amt+= tax_amt;
					nTax_component_amt.put(tax_struct_cd,nf3.format(tax_amt));
					nTax_cd.add(tax_struct_cd);
					nTax_abbr.add("IGST");
					nTax_factor.add(nf.format(Double.parseDouble(tax_struct_cd)));
					nTax_Structure_dtl = "IGST "+tax_struct_cd+"%";
					
				}else if(tax_flag.equalsIgnoreCase("C")) {
					
					/* for cgst */
					tax_amt = (total_payable_amt*Double.parseDouble(tax_struct_cd))/100;
					total_tax_amt+= tax_amt;
					nTax_component_amt.put(tax_struct_cd,nf3.format(tax_amt));
					nTax_cd.add(tax_struct_cd);
					nTax_abbr.add("CGST");
					nTax_factor.add(nf.format(Double.parseDouble(tax_struct_cd)));
					nTax_Structure_dtl = "CGST "+tax_struct_cd+"%";
					
					/* for sgst */
					tax_amt = (total_payable_amt*Double.parseDouble(tax_struct_cd))/100;
					total_tax_amt+= tax_amt;
					nTax_component_amt.put(tax_struct_cd,nf3.format(tax_amt));
					nTax_cd.add(tax_struct_cd);
					nTax_abbr.add("SGST");
					nTax_factor.add(nf.format(Double.parseDouble(tax_struct_cd)));
					nTax_Structure_dtl += ", SGST "+tax_struct_cd+"%";
				}
				
			}else {
				
				queryString = "SELECT NVL(A.tax_code,'0'), NVL(A.factor,'0.00'), NVL(A.tax_on,'1'), NVL(A.tax_on_cd,'0'), " +
							  "TO_CHAR(A.app_date,'DD/MM/YYYY') FROM FMS7_TAX_STRUCTURE_DTL A WHERE A.tax_str_cd='"+nTax_struct_cd+"' AND " +
							  "A.app_date=(SELECT MAX(B.app_date) FROM FMS7_TAX_STRUCTURE_DTL B WHERE B.tax_str_cd='"+nTax_struct_cd+"' AND " +
							  "B.app_date<=TO_DATE('"+invoice_dt1+"','DD/MM/YYYY')) ORDER BY A.tax_code";
//				System.out.println("Query For Finding Out Correct Tax Structure Details = "+queryString);
				rset1=stmt1.executeQuery(queryString);
				while(rset1.next())
				{
					tax_cd = rset1.getString(1);
					tax_factor = rset1.getString(2);
					
					if(rset1.getString(3).equals("1"))
					{
						tax_amt = (total_payable_amt*Double.parseDouble(rset1.getString(2)))/100;
					}
					else if(rset1.getString(3).equals("2"))
					{
						queryString1 = "SELECT NVL(A.tax_code,'0'), NVL(A.factor,'0'), NVL(A.tax_on,'1'), NVL(A.tax_on_cd,'0'), " +
									  "TO_CHAR(A.app_date,'DD/MM/YYYY') FROM FMS7_TAX_STRUCTURE_DTL A WHERE A.tax_str_cd='"+nTax_struct_cd+"' AND " +
									  "A.app_date=(SELECT MAX(B.app_date) FROM FMS7_TAX_STRUCTURE_DTL B WHERE B.tax_str_cd='"+nTax_struct_cd+"' AND " +
									  "B.app_date<=TO_DATE('"+invoice_dt1+"','DD/MM/YYYY')) AND A.tax_code='"+rset1.getString(4)+"'";
//						System.out.println("Query For Finding Out Tax Which Is Dependent On Other Tax Value = "+queryString1);
						rset2=stmt2.executeQuery(queryString1);
						if(rset2.next())
						{
					 			if(rset2.getString(3).equals("1"))
								{
									tax_amt = (total_payable_amt*Double.parseDouble(rset2.getString(2)))/100;
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
					
					String tax_abbr = "";
					queryString = "SELECT sht_nm,tax_name FROM FMS7_TAX_MST WHERE " +
							  "tax_code='"+tax_cd+"'";
					rset2 = stmt2.executeQuery(queryString);
					if(rset2.next())
					{
						tax_abbr = rset2.getString(1).trim()==null?"":rset2.getString(1).trim();
					}
					total_tax_amt += tax_amt;
					nTax_component_amt.put(tax_cd,nf3.format(tax_amt));
					nTax_cd.add(tax_cd);
					nTax_abbr.add(tax_abbr);
					nTax_factor.add(nf.format(Double.parseDouble(tax_factor)));
				}
			}
			nTotal_tax_amount = nf3.format(total_tax_amt);
			nNet_amt = nf3.format(total_payable_amt);
			nTotal_amount = nf3.format(total_tax_amt + total_payable_amt);
			
			//hlpl_inv_seq_no for advance invoice
			if(activity.equals("P"))
			{
				int fin_yr = Integer.parseInt(nInvoice_date.substring(6));
				int fin_mon = Integer.parseInt(nInvoice_date.substring(3,5));
				String financial_year = "";
				int inv_no = 0;
				String invoice_no = "";
				
				if(fin_mon>3) {
					financial_year = ""+fin_yr+"-"+(fin_yr+1);
				} else {
					financial_year = ""+(fin_yr-1)+"-"+fin_yr;
				}
				invFinancialYear=financial_year;
					String supplier_cd1="2";
					String inv_seq_no="";
					String new_inv_no="";
					String inv_flag="Y";
//					System.out.println("pContract_type--"+pContract_type);
					if(pContract_type.equals("S") || pContract_type.equals("L")){
						String seq_format = "I";
						if(temp_cont_typ.equalsIgnoreCase("V")) {
							seq_format = "M";
						}
						queryString = "SELECT NVL(MAX(cust_inv_seq_no),'0') FROM DLNG_INVOICE_MST " +
							  	  "WHERE financial_year='"+financial_year+"' " +
							  	  "AND contract_type ='"+seq_format+"' and supplier_cd!='"+supplier_cd1+"'  and flag!='A' ";
//						System.out.println("------"+queryString);
						rset=stmt.executeQuery(queryString);
						if(rset.next())
						{
							if(rset.getString(1)!=null && !(rset.getString(1).trim().equals(""))){
								nCust_inv_seq_no = ""+(Integer.parseInt(rset.getString(1))+1);
								}else{
									nCust_inv_seq_no = "1";
								}
						}else{
							nCust_inv_seq_no = "1";
						}
						
						/*queryString = "SELECT NVL(MAX(hlpl_inv_seq_no),'0') FROM DLNG_INVOICE_MST " +
							  	  "WHERE financial_year='"+financial_year+"' " +
							  	  "AND contract_type = '"+seq_format+"' and supplier_cd!='"+supplier_cd1+"' and flag!='A' ";
//						System.out.println("------"+queryString);
						rset=stmt.executeQuery(queryString);
						if(rset.next())
						{
							if(rset.getString(1)!=null && !(rset.getString(1).trim().equals(""))){
								inv_seq_no =rset.getString(1);
								}else{
									inv_seq_no = "0";
								}
						}else{
							inv_seq_no = "0";
						}
						String temp_inv_no =inv_seq_no;
						String q_new_invoice_no = "SELECT NVL(NEW_INV_SEQ_NO,'') FROM DLNG_INVOICE_MST WHERE "
								+ "HLPL_INV_SEQ_NO='"+temp_inv_no+"' AND FINANCIAL_YEAR = '"+financial_year+"' AND "+
						" contract_type   = '"+seq_format+"' and supplier_cd!='"+supplier_cd1+"' and flag!='A' ";
//						System.out.println("------"+q_new_invoice_no);
						rset1 = stmt1.executeQuery(q_new_invoice_no);
						if(rset1.next()) {
							new_inv_no = rset1.getString(1)==null?"":rset1.getString(1);
							if(!new_inv_no.equals("")) {
								
								if(new_inv_no.contains("IT")){
									new_inv_no = new_inv_no.substring(2,6);
								}else{
									new_inv_no = new_inv_no.substring(2,6);
								}
							} else{
								queryString = "SELECT NVL(MAX(hlpl_inv_seq_no),'0') FROM DLNG_INVOICE_MST " +
									  	  "WHERE financial_year='"+financial_year+"' " +
									  	  "AND contract_type = '"+seq_format+"' and supplier_cd!='"+supplier_cd1+"' AND (NEW_INV_SEQ_NO IS NOT NULL OR NEW_INV_SEQ_NO!='') and flag!='A' ";
								//System.out.println("------"+queryString);
								rset2=stmt2.executeQuery(queryString);
								if(rset2.next()) {
									String temp_no= rset2.getString(1)==null?"":rset2.getString(1);
									if(!temp_no.equalsIgnoreCase("")) {
										String q_new_invoice_no1 = "SELECT NVL(NEW_INV_SEQ_NO,'') FROM DLNG_INVOICE_MST WHERE "
												+ "HLPL_INV_SEQ_NO='"+temp_no+"' AND FINANCIAL_YEAR = '"+financial_year+"' AND "+
										" contract_type  = '"+seq_format+"' and  supplier_cd!='"+supplier_cd1+"' and flag!='A' ";
//										System.out.println("------"+q_new_invoice_no1);
										rset1 = stmt1.executeQuery(q_new_invoice_no1);
										if(rset1.next()) {
											new_inv_no = rset1.getString(1)==null?"":rset1.getString(1);
											if(!new_inv_no.equals("")) {
												if(new_inv_no.contains("IT")){
													new_inv_no = new_inv_no.substring(2,6);
												}else{
													new_inv_no = new_inv_no.substring(2,6);
												}
											}
										}
										
									}
								}
							}
						} */
						
						queryString = "SELECT NVL(MAX(hlpl_inv_seq_no),'0') FROM DLNG_INVOICE_MST " +
							  	  "WHERE financial_year='"+financial_year+"' " +
							  	  "AND contract_type = '"+seq_format+"' and supplier_cd!='"+supplier_cd1+"'"
							  	 + " AND (NEW_INV_SEQ_NO IS NOT NULL OR NEW_INV_SEQ_NO!='') and flag!='A' ";
						System.out.println("queryString---"+queryString);
						rset = stmt.executeQuery(queryString);
						if(rset.next()) {
							new_inv_no = rset.getString(1)==null?"":rset.getString(1);
						}
						if(new_inv_no.equals("")) {
								new_inv_no = "0000";
						}
						System.out.println("--new_inv_no...."+new_inv_no);
						new_inv_no = ""+(Integer.parseInt(new_inv_no)+1);
						//if(contract_type.equals("C")) {
							if(new_inv_no.length()==1) {
								new_inv_no = "000"+new_inv_no;
							} else if(new_inv_no.length()==2) {
								new_inv_no = "00"+new_inv_no;
							} else if(new_inv_no.length()==3) {
								new_inv_no = "0"+new_inv_no;
							} else if(new_inv_no.length()==4) {
								//new_inv_no = ""+new_inv_no;
								new_inv_no = new_inv_no;
							} 
							/*else if(new_inv_no.length()==5) {
								new_inv_no = new_inv_no;
							}*/
							String seq_format_1 = "IT";
							if(temp_cont_typ.equalsIgnoreCase("V")) {
								seq_format_1 = "LT";
							}
							//GST_INVOICE_SEQ_NO = new_inv_no + "/" + fin_yr.substring(0,5)+fin_yr.substring(7,9);
							nNew_inv_seq_no =  seq_format_1+""+new_inv_no + "/" + financial_year.substring(2,5)+""+financial_year.substring(7,9);
//							System.out.println("GST_INVOICE_SEQ_NO.."+nNew_inv_seq_no);
							nInvoice_seq_no_actual = ""+(Integer.parseInt(rset.getString(1))+1);
							//System.out.println("nInvoice_seq_no_actual.."+nNew_inv_seq_no);
					}	
				
			} //if for P
			
			query_per = "SELECT CUSTOMER_ABBR FROM FMS7_CUSTOMER_MST WHERE CUSTOMER_CD='"+pCustomer_cd+"' AND "
					+ "EFF_DT <= (SELECT MAX(EFF_DT) FROM FMS7_CUSTOMER_MST WHERE CUSTOMER_CD='"+pCustomer_cd+"' "
					+ "AND EFF_DT<=TO_DATE('"+invoice_dt1+"','DD/MM/YYYY'))";
			rset1 = stmt1.executeQuery(query_per);
			if(rset1.next()) {
				pCustomer_abbr = rset1.getString(1)==null?"":rset1.getString(1);
			} 
			
			Vector stat_no_temp= new Vector();
			Vector stat_nm_temp= new Vector();
			if(pContract_type.equalsIgnoreCase("S") || pContract_type.equalsIgnoreCase("L"))
			{
				queryString =  "select stat_cd,stat_nm from fms7_govt_stat_no where (stat_type='S' OR STAT_TYPE='G') order by stat_cd";
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					stat_no_temp.add(rset.getString(1));
					stat_nm_temp.add(rset.getString(2));
				}
				for(int i=0;i<stat_no_temp.size();i++)
				{
					int count_no=0;
					queryString = "SELECT A.stat_no, TO_CHAR(A.eff_dt,'DD-MM-YYYY'), B.stat_nm, B.stat_cd " +
							  "FROM FMS7_CUSTOMER_PLANT_TAX_CDS A, FMS7_GOVT_STAT_NO B " +
							  "WHERE A.stat_cd=B.stat_cd AND A.customer_cd="+pCustomer_cd+" AND " +
							  "A.plant_seq_no="+plant_seq_no+" AND (B.stat_type='S' OR B.STAT_TYPE='G') "
							  + " and b.stat_cd= '"+stat_no_temp.elementAt(i)+"'" +
							  "ORDER BY A.stat_cd";
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
							vSTAT_EFF_DT.add(stat_eff_dt.trim());
						}
						else
						{
							vSTAT_NO.add("");
							vSTAT_NM.add(""+stat_nm_temp.elementAt(i));
							vSTAT_CD.add(""+stat_no_temp.elementAt(i));
							vSTAT_EFF_DT.add("");
						}
					}
					if(count_no==0)
					{
						vSTAT_NO.add("");
						vSTAT_NM.add(""+stat_nm_temp.elementAt(i));
						vSTAT_CD.add(""+stat_no_temp.elementAt(i));
						vSTAT_EFF_DT.add("");
					}
				}
			}
			else if(pContract_type.equalsIgnoreCase("R") || pContract_type.equalsIgnoreCase("T") || pContract_type.equalsIgnoreCase("C")  || pContract_type.equals("B")) //ADDED FOR LTCORA AND CN
			{}
			queryString = "SELECT SERVICE_NM,SERVICE_CD,RULE_REMARK,SERVICE_DESC FROM FMS7_LNG_SALES_MAPPING WHERE "
					+ "CONTRACT_TYPE='"+pContract_type+"' ";
			rset = stmt.executeQuery(queryString);
			if(rset.next()) {
				sac_code = rset.getString(2)==null?"":rset.getString(2);
				sac_name = rset.getString(1)==null?"":rset.getString(1);
				rule_remark = rset.getString(3)==null?"":rset.getString(3);
				service_desc = rset.getString(4)==null?"":rset.getString(4);
			}
			
			if(pContract_type.equalsIgnoreCase("S"))
			{
				queryString = "SELECT SN_REF_NO " +
							  "FROM dlng_sn_mst WHERE " +
				  			  "flsa_no="+fgsa_no+" AND " +
				  			  "sn_no="+sn_no+" AND sn_ref_no IS NOT NULL AND " +
				  			  "customer_cd="+pCustomer_cd+"";
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					pSn_ref_no = rset.getString(1)==null?"":rset.getString(1);
				}
	
				queryString = "SELECT TO_CHAR(signing_dt,'fmddth') " +
							  "|| ' ' || TO_CHAR(signing_dt,'fmMonth') " +
							  "|| ' ' || TO_CHAR(signing_dt,'yyyy') " +
							  "FROM DLNG_FLSA_MST WHERE " +
							  "flsa_no="+fgsa_no+" AND " +
							  "rev_no="+fgsa_rev_no+" AND " +
							  "customer_cd="+pCustomer_cd+" " +
							  "ORDER BY rev_no DESC";
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					customer_Invoice_FGSA_Dt = rset.getString(1)==null?"":rset.getString(1);
				}
				
				queryString = "SELECT TO_CHAR(signing_dt,'fmddth') " +
							  "|| ' ' || TO_CHAR(signing_dt,'fmMonth') " +
							  "|| ' ' || TO_CHAR(signing_dt,'yyyy') " +
				  			  "FROM dlng_sn_mst WHERE " +
				  			  "flsa_no="+fgsa_no+" AND FLSA_REV_NO="+fgsa_rev_no+" AND " +
				  			  "sn_no="+sn_no+" AND sn_rev_no="+sn_rev_no+" AND " +
				  			  "customer_cd="+pCustomer_cd+"";
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					customer_Invoice_SN_Dt = rset.getString(1)==null?"":rset.getString(1);
				}
			}
			else if(pContract_type.equalsIgnoreCase("L"))
			{
				queryString = "SELECT LOA_REF_NO " +
							  "FROM dlng_loa_mst WHERE " +
				  			  "tender_no="+fgsa_no+" AND " +
				  			  "loa_no="+sn_no+" AND LOA_REF_NO IS NOT NULL AND " +
				  			  "customer_cd="+pCustomer_cd+"";
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					pSn_ref_no = rset.getString(1)==null?"":rset.getString(1);
				}
	
				queryString = "SELECT TO_CHAR(signing_dt,'fmddth') " +
							  "|| ' ' || TO_CHAR(signing_dt,'fmMonth') " +
							  "|| ' ' || TO_CHAR(signing_dt,'yyyy') " +
							  "FROM DLNG_TENDER_MST WHERE " +
							  "tender_no="+fgsa_no+" AND " +
							  "customer_cd="+pCustomer_cd+"";
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					customer_Invoice_FGSA_Dt = rset.getString(1)==null?"":rset.getString(1);
				}
				
				queryString = "SELECT TO_CHAR(signing_dt,'fmddth') " +
							  "|| ' ' || TO_CHAR(signing_dt,'fmMonth') " +
							  "|| ' ' || TO_CHAR(signing_dt,'yyyy') " +
				  			  "FROM dlng_loa_mst WHERE " +
				  			  "tender_no="+fgsa_no+" AND " +
				  			  "loa_no="+sn_no+" AND loa_rev_no="+sn_rev_no+" AND " +
				  			  "customer_cd="+pCustomer_cd+"";
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					customer_Invoice_SN_Dt = rset.getString(1)==null?"":rset.getString(1);
				}
			}
			else if(pContract_type.equalsIgnoreCase("R"))
			{}
			else if(pContract_type.equalsIgnoreCase("T")) //ADDED FOR LTCORA AND CN
			{}
			else if(pContract_type.equalsIgnoreCase("C")  || pContract_type.equals("B")) //ADDED FOR LTCORA AND CN
			{}
			
			int sr_no = 1;
			Map no = new HashMap();
			
			pSr_No.add(sr_no++);
			pItem.add("Delayed Payment Invoice Generated Against Invoice No. "+pNew_inv_seq_no+" dated "+invoice_dt_formated);
			pData.add(" ");
			pCurrency.add("");
			pQuantity.add(total_qty);
			//pRate.add(sale_price);
			pRate.add("");
			pAmount.add("");
			
			pSr_No.add(sr_no++);
		//	pItem.add("Net Amount of Invoice No. "+pNew_inv_seq_no);
			pItem.add("Net Amount");
			pData.add(" ");
			pCurrency.add("Rupees");
			pQuantity.add(" ");
			pRate.add(" ");
			pAmount.add(pNet_amt_inr);
			
			pSr_No.add(sr_no++);
			//pItem.add("Due Date of Invoice No. "+pNew_inv_seq_no);
			pItem.add("Due Date ");
			pData.add(pDue_date);
			pCurrency.add(" ");
			pQuantity.add(" ");
			pRate.add(" ");
			pAmount.add(" ");
			no.put("DUEDATE",(sr_no-1));
			
			pSr_No.add(sr_no++);
			//pItem.add("Payment Received Date And Amount For Invoice No. "+pNew_inv_seq_no);//RG20200401
			pItem.add("Payment Received Date");
			pData.add(pPay_recv_date);
			pCurrency.add(" ");
			pQuantity.add(" ");
			pRate.add(" ");
			pAmount.add(pPay_recv_amt);
			no.put("PAYDATE",(sr_no-1));
			
			String _1 = ""+no.get("DUEDATE");
			String _2 = ""+no.get("PAYDATE");
			pSr_No.add(sr_no++);
			//pItem.add("No Of Days For Late Payment Invoice ("+_2+" - "+_1+")");
			pItem.add("No Of Days For Late Payment Invoice");
			pData.add(ndiff_days+" Days");
			pCurrency.add(" ");
			pQuantity.add(" ");
			pRate.add(" ");
			pAmount.add(" ");
			
			pSr_No.add(sr_no++);
			pItem.add("Interest Rate");
			pData.add(" ");
			pCurrency.add(" ");
			pQuantity.add(" ");
			pRate.add(pCal_percentage+" %");
			pAmount.add(" ");
			
			pSr_No.add(sr_no++);
			pItem.add("Payable Amount");
			pData.add(" ");
			pCurrency.add("Rupees");
			pQuantity.add(" ");
			pRate.add(" ");
			pAmount.add(nf3.format(total_payable_amt));
			String finalT = " ("+(sr_no-1);
//			System.out.println("pContract_type---"+pContract_type+"***temp_cont_typ***"+temp_cont_typ);
			if((pContract_type.equalsIgnoreCase("S") || pContract_type.equalsIgnoreCase("L")) && !act_cont_type.equalsIgnoreCase("V") && !temp_cont_typ.equalsIgnoreCase("V")){
				
			}else{
			
				String total = "Total Tax Payable";
				String total_tax = " (";
				for(int i=0;i<nTax_cd.size();i++) {
					String amt_ = nTax_component_amt.get(nTax_cd.elementAt(i))+"";
					pSr_No.add(sr_no++);
					pItem.add(nTax_abbr.elementAt(i));
					pData.add(" ");
					pCurrency.add("Rupees");
					pQuantity.add(" ");
					pRate.add(nTax_factor.elementAt(i)+" %");
					pAmount.add(amt_);
					
					if(nTax_abbr.contains("IGST")) {
						total = "Total IGST Payable";
					} else if(nTax_abbr.contains("GST")) {
						total = "Total GST Payable";
					}else if(nTax_abbr.contains("CGST")) {
						total = "Total GST Payable";
					}
					if(nTax_cd.size()>1) {
						if(i==0)
							total_tax += (sr_no-1);
						else
							total_tax += " + "+(sr_no-1);
					} else {
						total_tax += (sr_no-1);
					}
					if(i==0) {
						finalT += " + ";
					}
				}
				total_tax += ") ";
				
				pSr_No.add(sr_no++);
				pItem.add(total + total_tax);
				pData.add(" ");
				pCurrency.add("Rupees");
				pQuantity.add(" ");
				pRate.add(" ");
				pAmount.add(nf3.format(total_tax_amt));
				finalT += (sr_no-1)+")";
			}
			if((pContract_type.equalsIgnoreCase("S") || pContract_type.equalsIgnoreCase("L")) && !act_cont_type.equalsIgnoreCase("V") && !temp_cont_typ.equalsIgnoreCase("V")){
				pSr_No.add(sr_no++);
				pItem.add("Net Amount Payable");
				pData.add(" ");
				pCurrency.add("Rupees");
				pQuantity.add(" ");
				pRate.add(" ");
				pAmount.add(nf3.format(total_payable_amt));
			}else{
				pSr_No.add(sr_no++);
				pItem.add("Invoice Amount Payable"+finalT);
				pData.add(" ");
				pCurrency.add("Rupees");
				pQuantity.add(" ");
				pRate.add(" ");
				pAmount.add(nTotal_amount);
				
				pSr_No.add(sr_no++);
				pItem.add("Net Amount Payable");
				pData.add(" ");
				pCurrency.add("Rupees");
				pQuantity.add(" ");
				pRate.add(" ");
				pAmount.add(nTotal_amount);
			}
			
			if(pdf_flag.equals("Y")) {
				if(temp_cont_typ.equals("V")){
					queryString="SELECT IRN_NO,sign_qr_code FROM FMS7_INVOICE_IRN_DTL WHERE NEW_INV_SEQ_NO='"+mNew_inv_seq_no+"' AND CONTRACT_TYPE='M'";
					rset=stmt.executeQuery(queryString);
//					System.out.println("queryString--"+queryString);
					if(rset.next()){
						irn_no=rset.getString(1)==null?"":rset.getString(1);
						qr_code=rset.getString(2)==null?"":rset.getString(2);
					}
				}
//				System.out.println("p fin---"+pFinancial_year);
				printPDFLateInvoice_SignPDF();
				printPDFLateInvoice();
				updateEntriesInTable();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	boolean flag_DCB = false;
	public static final String SIGNAME = "Signature1";
	
	public void updateEntriesInTable() throws Exception {
		try {
			 PreparedStatement pstmt=null;
 			String pdf_inv_dtl="",curr_date="",inv_title="";
 			//System.out.println(nInvoice_title);
 			
 			if(nInvoice_title.equalsIgnoreCase("ORIGINAL")) {
 				inv_title="O";
 			} else if(nInvoice_title.equalsIgnoreCase("DUPLICATE")) {
 				inv_title="D";
 			} else if(nInvoice_title.equalsIgnoreCase("TRIPLICATE")) {
 				inv_title="T";
 			}
 			String temp_cont="";
// 			System.out.println("temp_cont_typ---"+temp_cont_typ);
 			if((pContract_type.equalsIgnoreCase("S") || pContract_type.equalsIgnoreCase("L")) && !temp_cont_typ.equalsIgnoreCase("V")){
 				temp_cont="I";
 			}else{
 				temp_cont="M";
 			}
 			//System.out.println("inv title"+inv_title);
 			String query="select pdf_inv_dtl from dlng_invoice_mst where contract_type='"+temp_cont+"' " +
 					"and financial_year='"+pFinancial_year+"' and hlpl_inv_seq_no='"+mHlpl_inv_seq_no+"' AND FLAG='"+temp_cont+"' ";
// 			System.out.println("inv title query*** "+query);
 			rset=stmt.executeQuery(query);
 			if(rset.next()) {
 				pdf_inv_dtl=rset.getString(1)==null?"":rset.getString(1);
 			}
 				
 			//System.out.println("pdf_inv_dtl"+pdf_inv_dtl);
 			HttpSession session = request.getSession();
 			String user_cd = (String)session.getAttribute("user_cd")==null?"0":(String)session.getAttribute("user_cd");
 			
 			String q1="";
 			
 			if(pdf_inv_dtl.equalsIgnoreCase("") || !pdf_inv_dtl.contains(inv_title)) {
 				pdf_inv_dtl+=inv_title;
 				if(inv_title.equalsIgnoreCase("O")) {
 					q1="update dlng_invoice_mst set pdf_inv_dtl='"+pdf_inv_dtl+"',print_by_ori='"+user_cd+"',print_dt_ori=to_date(sysdate,'dd/mm/yy HH24:MI') where contract_type='"+temp_cont+"' " +
 							"and financial_year='"+pFinancial_year+"' and hlpl_inv_seq_no='"+mHlpl_inv_seq_no+"' AND FLAG='"+temp_cont+"'  ";
 				} else if(inv_title.equalsIgnoreCase("D")) {
 					q1="update dlng_invoice_mst set pdf_inv_dtl='"+pdf_inv_dtl+"',print_by_dup='"+user_cd+"',print_dt_dup=to_date(sysdate,'dd/mm/yy HH24:MI') where contract_type='"+temp_cont+"' " +
 							"and financial_year='"+pFinancial_year+"' and hlpl_inv_seq_no='"+mHlpl_inv_seq_no+"'  AND FLAG='"+temp_cont+"' ";
 				} else if(inv_title.equalsIgnoreCase("T")) {
 					q1="update dlng_invoice_mst set pdf_inv_dtl='"+pdf_inv_dtl+"',print_by_tri='"+user_cd+"',print_dt_tri=to_date(sysdate,'dd/mm/yy HH24:MI') where contract_type='"+temp_cont+"' " +
 							"and financial_year='"+pFinancial_year+"' and hlpl_inv_seq_no='"+mHlpl_inv_seq_no+"' AND FLAG='"+temp_cont+"'  ";
 				} 
// 				System.out.println("Update..."+q1);
 				stmt.executeUpdate(q1);
 			} 
 			
 			int count = 0;
 			String query11="select count(*) from LOG_DLNG_INVOICE_MST where "
 					+ "contract_type='"+temp_cont+"' and financial_year='"+pFinancial_year+"' and hlpl_inv_seq_no='"+mHlpl_inv_seq_no+"' AND FLAG='"+temp_cont+"' ";
 			rset=stmt.executeQuery(query11);
 			if(rset.next()){
 				count = rset.getInt(1);
 			}
 			if(count==0) {
 				String queryString1 ="INSERT INTO LOG_DLNG_INVOICE_MST SELECT SN_NO,SN_REV_NO,FGSA_NO,FGSA_REV_NO," +
 				"CUSTOMER_CD,PLANT_SEQ_NO,CONTRACT_TYPE,to_date(to_char(PERIOD_END_DT,'dd/mm/yyyy'),'dd/mm/yyyy'),to_date(to_char(PERIOD_START_DT,'dd/mm/yyyy'),'dd/mm/yyyy'),HLPL_INV_SEQ_NO," +
 				"CUST_INV_SEQ_NO,FINANCIAL_YEAR,CONTACT_PERSON_CD,to_date(to_char(INVOICE_DT,'dd/mm/yyyy'),'dd/mm/yyyy'),to_date(to_char(DUE_DT,'dd/mm/yyyy'),'dd/mm/yyyy'),TOTAL_QTY,SALE_PRICE," +
 				"GROSS_AMT_INR,GROSS_AMT_USD,NET_AMT_INR,TAX_AMT_INR,TAX_STRUCT_CD,TAX_FLAG,EXCHG_RATE_CD," +
 				"to_date(to_char(EXCHG_RATE_DT,'dd/mm/yyyy'),'dd/mm/yyyy'),EXCHG_RATE_VALUE,EXCHG_RATE_TYPE,EMP_CD,to_date(to_char(ENT_DT,'dd/mm/yyyy'),'dd/mm/yyyy'),FLAG,REMARK_1,REMARK_2," +
 				"CHECKED_BY,to_date(to_char(CHECKED_DT,'dd/mm/yyyy'),'dd/mm/yyyy'),CHECKED_FLAG,AUTHORIZED_BY,to_date(to_char(AUTHORIZED_DT,'dd/mm/yyyy'),'dd/mm/yyyy'),AUTHORIZED_FLAG,APPROVED_BY," +
 				"to_date(to_char(APPROVED_DT,'dd/mm/yyyy'),'dd/mm/yyyy'),APPROVED_FLAG,EXCHG_RATE_INDEX,OFFSPEC_QTY,OFFSPEC_FLAG,OFFSPEC_RATE,SUN_APPROVAL," +
 				"to_date(to_char(SUN_APPROVAL_DT,'dd/mm/yyyy'),'dd/mm/yyyy'),SUN_APPROVAL_BY,REMARK_3,INV_AMT_USD,INV_AMT_INR,INV_CUR_FLAG," +
 				"MAPPING_ID,ADV_INV_NO,to_date(to_char(ADV_INV_DT,'dd/mm/yyyy'),'dd/mm/yyyy'),PDF_INV_DTL,to_date(to_char(USER_DEFINED_DAY,'dd/mm/yyyy'),'dd/mm/yyyy') " +
 				",'',to_date('','dd/mm/yyyy'),to_date('','dd/mm/yyyy'),'"+contact_Customer_Name+"'," +
 				"'"+contact_Suppl_Name+"','"+contact_Suppl_Person_Address+"','"+contact_Suppl_Person_Pin+"'," +
 				"'"+contact_Person_Name_And_Designation+"','"+contact_Customer_Person_Address+"','"+contact_Customer_Person_City+"'," +
 				"'"+contact_Customer_Person_Pin+"','"+contact_Suppl_Person_City+"','','"+contact_Suppl_GST_NO+"',to_date('"+contact_Suppl_GST_DT+"','dd/mm/yyyy')," +
 				"'"+contact_Suppl_CST_NO+"',to_date('"+contact_Suppl_CST_DT+"','dd/mm/yyyy'),'"+contact_Suppl_Service_Tax_NO+"',to_date('"+contact_Suppl_Service_Tax_DT+"','dd/mm/yyyy') " +
 				",'','','','' ,'','"+pPlant_nm+"','','','','','', '' ,'' , '','',to_date('','dd/mm/yyyy'),"+
 				"'','','', '','',  '','','', '' , PAY_TYPE,ADV_ADJ_FLG,NEW_INV_SEQ_NO,SUPPLIER_CD,REMARK_SPECIFICATION,PRICE_UNIT,DUMMY_CARGO_NO,"
 				+ "'"+contact_Suppl_GSTIN_NO+"',to_date('"+contact_Suppl_GSTIN_DT+"','dd/mm/yyyy'),'"+sac_code+"',"
 				+ "'"+contact_Suppl_State_Code+"','"+contact_Customer_Plant_State+"','"+contact_Customer_State_Code+"',"
 				+ " '','','','', " 
 				+ " '','','','', " 
 				+ " '','','','', '','','','',GROSS_AMT_INR_NEW,GROSS_AMT_USD_NEW,ADVANCE_ADJ_AMT_NEW,ADVANCE_ADJ_GROSS_AMT_USD,ADVANCE_ADJ_GROSS_AMT_INR,"
 				+ "ADVANCE_ADJ_REM_NEW,REF_FLAG,SUP_STATE_CODE,TDS_TAX_PERCENT,TDS_TAX_AMT "   
 				+ " FROM DLNG_INVOICE_MST " +
 				"where contract_type='"+temp_cont+"' and financial_year='"+pFinancial_year+"' and hlpl_inv_seq_no='"+mHlpl_inv_seq_no+"'  AND FLAG='"+temp_cont+"' ";
// 				System.out.println("INserting Log Data...."+queryString1);
 				stmt.executeUpdate(queryString1);
 				
 				String mapp_id=pCustomer_cd+":"+pFgsa_no+":"+pFgsa_rev_no+":"+pSn_no+":"+pSn_rev_no+":"+pPlant_seq_no;
 				String Mapping_seq_no=temp_cont+":"+pFinancial_year+":"+mHlpl_inv_seq_no+":"+mInvoice_dt;
 				//System.out.println("mappinf--"+Mapping_seq_no);
 				for(int i=0;i<vSTAT_CD.size();i++)
 				{
 					String queryString2="INSERT INTO LOG_DLNG_INV_ADD_DTL (MAPPING_ID,INV_SEQ_NO,TYPE_NO,SEQ_NO,INV_DT,INV_NO,INV_AMT," +
 					"INV_CURR,INV_AMT_BAL,EXC_RATE_NM) VALUES ('"+mapp_id+"','"+Mapping_seq_no+"','3','"+i+"'," +
 					"to_date('"+vSTAT_EFF_DT.elementAt(i)+"','dd/mm/yy'),'"+vSTAT_CD.elementAt(i)+"'," +
 					"'"+vSTAT_NO.elementAt(i)+"'," +
 					"'','0','"+vSTAT_NM.elementAt(i)+"')";
// 					System.out.println("queryString2--"+queryString2);
// 					stmt2.executeUpdate(queryString2);
 				}
 			}
 			
 			File f=new File(pdf_path);
 			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
 			String file_date=sdf.format(f.lastModified());
 			SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
 			String file_time=sdf1.format(f.lastModified());
 			
 			if(!f_nm.equalsIgnoreCase("")){
 				String quer="select count(*) from dlng_inv_pdf_dtl where PDF_INV_NM='"+f_nm.trim()+"' "
 						+ "and INV_TYPE='"+inv_type_pdf.trim()+"'";
// 				System.out.println("Select data..."+quer);
 				rset=stmt.executeQuery(quer);
 				if(rset.next()){
 					if(rset.getInt(1)>0){
 						 quer = "update dlng_inv_pdf_dtl   "
 								+ " set  created_dt=sysdate,flag='Y' where PDF_INV_NM='"+f_nm.trim()+"' and  INV_TYPE='"+inv_type_pdf.trim()+"'";
// 						System.out.println("update data..."+quer);
 						 stmt.executeUpdate(quer);
 					}else{
 						quer = "insert into dlng_inv_pdf_dtl  "
 								+ "  (PDF_INV_NM,created_dt,INV_TYPE,flag) values ('"+f_nm.trim()+"',sysdate,'"+inv_type_pdf.trim()+"','Y') ";
// 						System.out.println("insert data..."+quer);
 						stmt.executeUpdate(quer);
 					}
 				}
 			}
 			conn.commit();	
		} catch(Exception e) {
			e.printStackTrace();
			conn.rollback();
		}
	}
	
	public String createPdfFileForLatePayment()
	{
		try
		{
			//System.out.println("In Printing File..");
			HttpSession sess = request.getSession();
			pdf_path = ""+sess.getAttribute("late_payment_invoice_pdf_path");
			String inv_title="";
			if(nInvoice_title.equalsIgnoreCase("ORIGINAL"))
			{
				inv_title="O";
			}
			else if(nInvoice_title.equalsIgnoreCase("DUPLICATE"))
			{
				inv_title="D";
			}
			else if(nInvoice_title.equalsIgnoreCase("TRIPLICATE"))
			{
				inv_title="T";
			}
			
			String nm = pPlant_nm;
			if(pPlant_nm.length()>40) {
				nm = pPlant_nm.substring(0,40);
			}
			nm = nm.replaceAll(" ", "_");
			String temp_cont="";
//			System.out.println("temp_cont_typ---"+temp_cont_typ);
			if((pContract_type.equalsIgnoreCase("S") || pContract_type.equalsIgnoreCase("L")) && !temp_cont_typ.equalsIgnoreCase("V")){
				temp_cont="I";
			}else{
				temp_cont="M";
			}
			fileName = "INVOICE-"+mInvoice_dt.trim().substring(0,2)+mInvoice_dt.trim().substring(3,5)+mInvoice_dt.trim().substring(6)+"-"+pCustomer_abbr+"-"+nm+"-"+temp_cont+"-"+mHlpl_inv_seq_no+"-"+inv_title+".pdf";
			f_nm="INVOICE-"+mInvoice_dt.trim().substring(0,2)+mInvoice_dt.trim().substring(3,5)+mInvoice_dt.trim().substring(6)+"-"+pCustomer_abbr+"-"+nm+"-"+temp_cont+"-"+mHlpl_inv_seq_no;
			inv_type_pdf=inv_title;
			pdf_path = pdf_path+"//"+fileName;
			
//			System.out.println("fileName--"+fileName);
//			System.out.println("f_nm--"+f_nm);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return pdf_path;
	}
	
	public void printPDFLateInvoice() throws Exception {
	
		Rectangle pageSize = new Rectangle(595, 842);
		Rectangle pageSize1 = new Rectangle(842,595);
	//	pageSize.setBackgroundColor(new java.awt.Color(0xffffff));
	//	pageSize1.setBackgroundColor(new java.awt.Color(0xffffff));
		String url_DCB = request.getContextPath(); //request.getRequestURL().toString();
	    if(url_DCB.contains("TEST"))
	    {
	    	flag_DCB=true;
	    }
	    else
	    {
	    	flag_DCB=false;
	    }
	    if(flag_DCB==true)
		{
			pageSize.setBackgroundColor(new java.awt.Color(0xe3f8c0)); 
			pageSize1.setBackgroundColor(new java.awt.Color(0xe3f8c0)); 
		}
		else
		{
			pageSize.setBackgroundColor(new java.awt.Color(0xffffff)); 
			pageSize1.setBackgroundColor(new java.awt.Color(0xffffff)); 
		}
		Document document = new Document(pageSize);
		try {
			PdfWriter writer = PdfWriter.getInstance(document,new FileOutputStream(createPdfFileForLatePayment()));
			document.addTitle("Late Payment Invoice Details");
			document.addSubject("Late Payment Invoice Details For Customer");
	        document.addKeywords("iText, Invoice Details, Step 2, metadata");
	        document.addCreator("Late Payment Invoice Details Generation using iText");
	        document.addAuthor("FMS8@BIPL");
	        document.open();
			document.setPageSize(pageSize);
	        document.newPage();
	
	        Font very_small_black_normal = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.NORMAL, new Color(0x00, 0x00, 0x00));
	        Font small_black_normal = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL, new Color(0x00, 0x00, 0x00));
	        Font small_black_normal2 = FontFactory.getFont(FontFactory.HELVETICA, 6, Font.NORMAL, new Color(0x00, 0x00, 0x00));
	        Font small_black_bold = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD, new Color(0x00, 0x00, 0x00));
	        Font black_bold = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD, new Color(0x00, 0x00, 0x00));
	        Font big_black_bold_2 = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, new Color(0x00, 0x00, 0x00));
	        Font big_black_bold = FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD, new Color(0x00, 0x00, 0x00));
	        
	        String context_nm = request.getContextPath();
			String server_nm = request.getServerName();
			String server_port = ""+request.getServerPort();
			  
			url_start = "http://"+server_nm+":"+server_port+context_nm;
			
	//        Image hlpl_logo = Image.getInstance(url_start+"//images//LOGO//HLPL-Symbol.jpg");
		//  Image hlpl_logo = Image.getInstance(url_start+"//images//LOGO//HLPL-Symbol.jpg");//RG20200320
				Image hlpl_logo = Image.getInstance(url_start+"//images//Shell-Logo.jpg"); //RG20191130
				 /*RG20191128 added for adding logo for sign pdf*/
			 	hlpl_logo.setBorder(Rectangle.NO_BORDER);
	         hlpl_logo.scaleAbsolute(48,45);
	         PdfPCell hlpl_logo_cell1 = new PdfPCell(hlpl_logo,false);
	         hlpl_logo_cell1.setBorder(Rectangle.NO_BORDER);
	         float[] hlpl_logo_Widths = {0.45f};
	         PdfPTable hlpl_logo_table = new PdfPTable(hlpl_logo_Widths);
	         hlpl_logo_table.setWidthPercentage(100);
	         hlpl_logo_table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	         hlpl_logo_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
	         hlpl_logo_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
	         hlpl_logo_table.addCell(hlpl_logo_cell1);
			  /* ended adding logo*/   
			//String inv_nm = "TAX INVOICE";
	        String inv_nm = ""; 
	        if((pContract_type.equals("S") || pContract_type.equals("L")) && !temp_cont_typ.equalsIgnoreCase("V")){
	       	 inv_nm = "DEBIT NOTE";
	        }else{
	       	 inv_nm = "TAX INVOICE";
	        }
			String inv_desc = contact_Suppl_Name;
			if(pContract_type.equals("C") || pContract_type.equals("B")) {
				inv_nm += "\n"+rule_remark;
			}
			String sn = "";
			
			if(pSn_ref_no.trim().equals(""))
			{
				sn = pSn_no;
			}
			else
			{
				sn = pSn_ref_no;
			}
			
			String inv_note = "";
			
			if(pContract_type.equalsIgnoreCase("S"))
			{	
				inv_note = "In respect of Supply Notice (SN-"+sn+") executed on "+customer_Invoice_SN_Dt+" pursuant to Framework Gas Sales Agreement executed on "+customer_Invoice_FGSA_Dt+"\nbetween "+contact_Suppl_Name+" and "+contact_Customer_Name;
			}
			else if(pContract_type.equalsIgnoreCase("L"))
			{
				inv_note = "In respect of Letter of Agreement (LOA-"+sn+") executed on "+customer_Invoice_SN_Dt+" pursuant to Tender executed on "+customer_Invoice_FGSA_Dt+"\nbetween "+contact_Suppl_Name+" and "+contact_Customer_Name;
			}
			else if(pContract_type.equalsIgnoreCase("R"))
			{
				inv_note = "In respect of Regassification Agreement executed on "+customer_Invoice_FGSA_Dt+" and subsequent side letters\nbetween "+contact_Customer_Name+" and "+contact_Suppl_Name;
			}            
			else if(pContract_type.equalsIgnoreCase("T"))//ADDED FOR LTCORA AND CN
			{
				inv_note = "In respect of LTCORA executed on "+customer_Invoice_FGSA_Dt+" \nbetween "+contact_Customer_Name+" and "+contact_Suppl_Name;
			}  
			else if(pContract_type.equalsIgnoreCase("C")  || pContract_type.equals("B"))//ADDED FOR LTCORA AND CN
			{
				inv_note = "In respect of LTCORA  executed on "+customer_Invoice_FGSA_Dt+" ";
				if(Double.parseDouble(pFgsa_no)<9999) 
				{
					inv_note = inv_note+"& CN-"+pFgsa_no+" executed on "+customer_Invoice_SN_Dt+"";
				}
				inv_note = inv_note+"\nbetween "+contact_Customer_Name+" and "+contact_Suppl_Name;
			}  
			
			
			String addr_supl = "Registered Office:";
			/*Following changes is done by RG20190425 for late payment invoice */
			Paragraph pp=new Paragraph();
			pp.add(new Phrase(new Chunk(addr_supl,small_black_normal)));
	        
	        if(!contact_Suppl_Name.trim().equals(""))
	        {
	        	//addr_supl += "\n"+contact_Suppl_Name;
	        	pp.add(new Phrase(new Chunk("\n"+contact_Suppl_Name,small_black_normal)));
	        }
	        if(!tempcompname.trim().equals(""))
	        {
	        	//addr_supl += "\n"+contact_Suppl_Name;    
	        	pp.add(new Phrase(new Chunk("\n"+tempcompname,small_black_normal2)));
	        }
	        if(!contact_Suppl_Person_Address.trim().equals(""))
	        {
	        	//addr_supl += "\n"+contact_Suppl_Person_Address;//RG20190425
	        	addr_supl = "\n"+contact_Suppl_Person_Address;
	        }
	        if(!contact_Suppl_Person_City.trim().equals(""))
	        {
	        	addr_supl += "\n"+contact_Suppl_Person_City;     	
	        }
	        if(!contact_Suppl_Person_Pin.trim().equals(""))
	        {
	        	addr_supl += " "+"-"+" "+contact_Suppl_Person_Pin;     	
	        }
	        pp.add(new Phrase(new Chunk(addr_supl,small_black_normal)));//RG20190425
			String addr_customer = "";
	        
			if(contact_Person_Name_And_Designation.trim().equalsIgnoreCase(contact_Customer_Name.trim()))
			{
				if(!contact_Customer_Name.trim().equals(""))
	            {
	            	addr_customer += contact_Customer_Name;    	
	            }
			}
			else
			{
	            if(!contact_Person_Name_And_Designation.trim().equals(""))
	            {
	            	addr_customer += contact_Person_Name_And_Designation;     	
	            }
	            if(!contact_Customer_Name.trim().equals(""))
	            {
	            	addr_customer += "\n"+contact_Customer_Name;     	
	            }
			}
	        if(!contact_Customer_Person_Address.trim().equals(""))
	        {
	        	addr_customer += "\n"+contact_Customer_Person_Address;     	
	        }
	        if(!contact_Customer_Person_City.trim().equals(""))
	        {
	        	addr_customer += "\n"+contact_Customer_Person_City;     	
	        }
	        if(!contact_Customer_Person_Pin.trim().equals(""))
	        {
	        	addr_customer += " "+"-"+" "+contact_Customer_Person_Pin;     	
	        }
	        
	        PdfPTable InvoiceTitleTable = new PdfPTable(1);
	        InvoiceTitleTable.setWidthPercentage(100);
	        InvoiceTitleTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	        InvoiceTitleTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	    	String inv_tit = nInvoice_title;
	    	if(pContract_type.equals("C")  || pContract_type.equals("B")) {
	        	if(nInvoice_title.equalsIgnoreCase("ORIGINAL")) {
	        		inv_tit += " FOR RECIPIENT"; 
	            } else if(nInvoice_title.equalsIgnoreCase("DUPLICATE")) {
	            	inv_tit += " FOR SUPPLIER";
	            } 
	    	}
	    	InvoiceTitleTable.addCell(new Phrase(new Chunk(inv_tit,black_bold)));
	        
	        PdfPTable InvoiceDescTable = new PdfPTable(1);
	        InvoiceDescTable.setWidthPercentage(100);
	        InvoiceDescTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	        InvoiceDescTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	        InvoiceDescTable.addCell(new Phrase(new Chunk(inv_desc,big_black_bold)));
	        
	        PdfPTable InvoiceDescTable2 = new PdfPTable(1);
	        InvoiceDescTable2.setWidthPercentage(100);
	        InvoiceDescTable2.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	        InvoiceDescTable2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	    	InvoiceDescTable2.addCell(new Phrase(new Chunk(inv_nm,big_black_bold_2)));
	        
	        PdfPTable InvoiceNoteTable = new PdfPTable(1);
	        InvoiceNoteTable.setWidthPercentage(100);
	        InvoiceNoteTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	        InvoiceNoteTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	        InvoiceNoteTable.addCell(new Phrase(new Chunk(inv_note,small_black_normal)));
	        
	        float[] ContactAddrWidths = {0.50f, 0.10f, 0.40f};
	        PdfPTable contact_addr_table = new PdfPTable(ContactAddrWidths);
	        contact_addr_table.setWidthPercentage(100);
	        contact_addr_table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	        contact_addr_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
	        contact_addr_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
	        //contact_addr_table.addCell(new Phrase(new Chunk(addr_supl,small_black_normal)));//RG20190425
	        contact_addr_table.addCell(pp); //RG20190425
	        contact_addr_table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	        contact_addr_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
	        contact_addr_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
	        contact_addr_table.addCell(new Phrase(new Chunk("To:",small_black_bold)));
	        contact_addr_table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	        contact_addr_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
	        contact_addr_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
	        contact_addr_table.addCell(new Phrase(new Chunk(addr_customer,small_black_normal)));
	        
	        String supl_gst_cst_info = "";
	        String customer_gst_cst_info = "";
	        
	        if(pContract_type.equalsIgnoreCase("S") || pContract_type.equalsIgnoreCase("L"))
			{
	        	if(!contact_Suppl_GST_NO.trim().equals(""))
	        	{
	        		supl_gst_cst_info = " GST TIN No. : "+contact_Suppl_GST_NO+" DT. "+contact_Suppl_GST_DT;
	        	}
	        	else
	        	{
	        		supl_gst_cst_info = "";
	        	}
	        	
	        	if(!contact_Suppl_CST_NO.trim().equals(""))
	        	{
	        		supl_gst_cst_info += " \nCST TIN No. : "+contact_Suppl_CST_NO+" DT. "+contact_Suppl_CST_DT;
	        	}
	        	else
	        	{
	        		supl_gst_cst_info += "\n";
	        	}
	        	
	        	if(!contact_Suppl_PAN_NO.trim().equals(""))
	        	{
	        		supl_gst_cst_info += " \nPAN : "+contact_Suppl_PAN_NO+" ";
	        	}
	        	else
	        	{
	        		supl_gst_cst_info += "\n";
	        	}
			}
	        
	        if(vSTAT_CD.size()>0)
			{	
				if(pContract_type.equalsIgnoreCase("S") || pContract_type.equalsIgnoreCase("L"))
				{
					for(int i=0; i<vSTAT_CD.size(); i++)
					{
						if(i==0)
						{
							if(vSTAT_NM.elementAt(i).toString().equalsIgnoreCase("PAN No.")) 
							{
								customer_gst_cst_info = "PAN : "+vSTAT_NO.elementAt(i)+" ";
							}
							else
							{
								if(!vSTAT_NO.elementAt(i).equals("")){
									customer_gst_cst_info = ""+vSTAT_NM.elementAt(i)+" : "+vSTAT_NO.elementAt(i)+" ";
									if(!vSTAT_EFF_DT.elementAt(i).equals("")){
										customer_gst_cst_info+= "DT. "+vSTAT_EFF_DT.elementAt(i);
									}
								}
							}
						}
						else if(i>0)
						{
							if(vSTAT_NM.elementAt(i).toString().equalsIgnoreCase("PAN No.")) 
							{
								customer_gst_cst_info += "\nPAN : "+vSTAT_NO.elementAt(i)+" ";
							}
							else
							{
								if(!vSTAT_NO.elementAt(i).equals("")){
									customer_gst_cst_info += "\n"+vSTAT_NM.elementAt(i)+" : "+vSTAT_NO.elementAt(i)+" ";
									if(!vSTAT_EFF_DT.elementAt(i).equals("")){
										customer_gst_cst_info += " DT. "+vSTAT_EFF_DT.elementAt(i);
									}
								}
							}
						}
					}
				}
				else
				{
					customer_gst_cst_info = "State : "+contact_Customer_Plant_State+"\n";
					customer_gst_cst_info += "State Code : "+contact_Customer_State_Code+"\n";
					for(int i=0; i<vSTAT_CD.size(); i++)
					{
						if(vSTAT_NM.elementAt(i).toString().contains("GSTIN")) { 
							customer_gst_cst_info += vSTAT_NM.elementAt(i).toString()+" : "+vSTAT_NO.elementAt(i)+"\n";
						} else if(vSTAT_NM.elementAt(i).toString().equalsIgnoreCase("PAN No.")) 
						{
							customer_gst_cst_info += "PAN : "+vSTAT_NO.elementAt(i)+"\n";	
						}
					}
				}
			}
	        else
	        {
	        	if(pContract_type.equalsIgnoreCase("S") || pContract_type.equalsIgnoreCase("L"))
	    		{
	        		if(!contact_Customer_GST_NO.trim().equals(""))
	            	{
	            		customer_gst_cst_info = "GST TIN No. : "+contact_Customer_GST_NO+" DT. "+contact_Customer_GST_DT;
	            	}
	            	else
	            	{
	            		customer_gst_cst_info = "";
	            	}
	            	
	            	if(!contact_Customer_CST_NO.trim().equals(""))
	            	{
	            		customer_gst_cst_info += "\nCST TIN No. : "+contact_Customer_CST_NO+" DT. "+contact_Customer_CST_DT;
	            	}
	            	else
	            	{
	            		customer_gst_cst_info += "\n";
	            	}
	            	
	            	if(!contact_Customer_GVAT_NO.trim().equals(""))
	            	{
	            		customer_gst_cst_info += "\nGVAT TIN No. : "+contact_Customer_GVAT_NO+" DT. "+contact_Customer_GVAT_DT;
	            	}
	            	else
	            	{
	            		customer_gst_cst_info += "\n";
	            	}
	    		}
	        }
	        
	        
	        float[] GstCstInfoWidths = {0.50f, 0.10f, 0.40f};
	        PdfPTable GstCstInfoTable = new PdfPTable(GstCstInfoWidths);
	        GstCstInfoTable.setWidthPercentage(100);
	        GstCstInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	        GstCstInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
	        GstCstInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
	        GstCstInfoTable.addCell(new Phrase(new Chunk("\n"+supl_gst_cst_info,small_black_normal)));
	        GstCstInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	        GstCstInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
	        GstCstInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
	        GstCstInfoTable.addCell(new Phrase(new Chunk("\n ",small_black_normal)));
	        GstCstInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	        GstCstInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
	        GstCstInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
	        GstCstInfoTable.addCell(new Phrase(new Chunk("\n"+customer_gst_cst_info,small_black_normal)));
			
	        String invno = "";
	        
	        if(!mNew_inv_seq_no.equals("")) {
	        	invno = mNew_inv_seq_no;
	        } else {
	        	if(pContract_type.equalsIgnoreCase("T") || pContract_type.equalsIgnoreCase("C")  || pContract_type.equals("B"))
	        	{
	        		if(mHlpl_inv_seq_no.length()>13)
	        		{
	        			invno = mHlpl_inv_seq_no.substring(0,10)+""+mHlpl_inv_seq_no.substring(12);
	        		}      		
	        	}
	        	else
	        	{
	        		if(mHlpl_inv_seq_no.length()>13)
	        		{
	        			invno = mHlpl_inv_seq_no.substring(0,5)+""+mHlpl_inv_seq_no.substring(7,10)+""+mHlpl_inv_seq_no.substring(12);
	        		}       		
	        	}
	        }
	        
			String inv_no_info = "";
			String invoiceType = "";
			String inv_dt_Header = "Invoice Date:";
			String Inv_Ref_Dtl = ""; 
			 
			invoiceType = "Invoice Seq No:";
		
			if(pContract_type.equalsIgnoreCase("R"))
			{
				inv_no_info = "SEIPL R-gas Invoice Seq No:";
			}
			else if(pContract_type.equalsIgnoreCase("T") || pContract_type.equalsIgnoreCase("C") || pContract_type.equals("B"))
			{
				inv_no_info = "SEIPL Tax Invoice Seq No:";
			}
			else
			{
				inv_no_info = "SEIPL Invoice Seq No:";
			}
//	        System.out.println("pContract_type---"+temp_cont_typ+"--irn_no--"+irn_no+"---qr_code==="+qr_code);
			PdfPTable InvoiceDateInfoTable;
			PdfPTable InvoiceNOInfoTable;
			float[] InvoiceDateInfoWidths_qr = {0.40f, 0.20f, 0.60f};
			 PdfPTable InvoiceDateInfoTable_qr = new PdfPTable(InvoiceDateInfoWidths_qr);
			if(!irn_no.equals("") && (!qr_code.equals("")) && (temp_cont_typ.equalsIgnoreCase("V"))){
	
				//float[] InvoiceDateInfoWidths = {0.60f, 0.25f, 0.15f};
				float[] InvoiceDateInfoWidths = {0.20f, 0.45f, 0.25f};
	            InvoiceDateInfoTable = new PdfPTable(InvoiceDateInfoWidths);
	            InvoiceDateInfoTable.setWidthPercentage(100);
	            InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	            InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
	            InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
	        	InvoiceDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
	            InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	            InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
	            InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
	        	InvoiceDateInfoTable.addCell(new Phrase(new Chunk("Invoice Date:",small_black_bold)));
	        	InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
	            InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
	            InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
	            InvoiceDateInfoTable.addCell(new Phrase(new Chunk(customer_Invoice_DT,small_black_bold)));
	        
	            float[] InvoiceNOInfoWidths = {0.60f, 0.25f, 0.15f};
	            InvoiceNOInfoTable = new PdfPTable(InvoiceNOInfoWidths);
	            InvoiceNOInfoTable.setWidthPercentage(100);
	            
	            InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	            InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
	            InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
	            InvoiceDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
	            InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	            InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
	            InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
	            InvoiceDateInfoTable.addCell(new Phrase(new Chunk(inv_no_info,small_black_bold)));
	            InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
	            InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
	            InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
	            InvoiceDateInfoTable.addCell(new Phrase(new Chunk(invno,small_black_bold)));
	            
	          //Extra cell added for no border
	            InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	            InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
	            InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
	            InvoiceDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
	            InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	            InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
	            InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
	            InvoiceDateInfoTable.addCell(new Phrase(new Chunk("",small_black_bold)));
	            InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	            InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
	            InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
	            InvoiceDateInfoTable.addCell(new Phrase(new Chunk("",small_black_bold)));
	            
	          //code for Irn & qrcode starts now
	            url_start = "http://"+server_nm+":"+server_port+context_nm;
	                String qrCodeText = qr_code;
	                String filePath =  request.getRealPath("/pdf_reports/xls_reports/qrcode");
	      		  filePath = filePath+"//"+invno.replace("/","_")+".png";
	      		  int size = 45;//125 original
	      		  String fileType = "png";
	      		  File qrFile = new File(filePath);
	      		  com.seipl.hazira.dlng.util.DataBean_QRCode_Generator.createQRImage(qrFile, qrCodeText, size, fileType);
//	      		  System.out.println("filePath---"+filePath);
	      		  
	                
	              Image qr_codeimg = Image.getInstance(url_start+"//pdf_reports//xls_reports//qrcode//"+invno.replace("/","_")+".png");
	              
	              qr_codeimg.setBorder(Rectangle.NO_BORDER);
	              qr_codeimg.setAlignment(Element.ALIGN_LEFT);
	             // qr_codeimg.scaleAbsolute(75,75);
	            //  qr_codeimg.scaleAbsolute(50,50);
	              PdfPCell qrcode_cell = new PdfPCell(qr_codeimg,false);
	              qrcode_cell.setBorder(Rectangle.NO_BORDER);
	              qrcode_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	                  
	//                String irn_no=irn_no;
	                String char_16= irn_no.substring(0,16);
	                String char_32= irn_no.substring(16,32);
	                String char_48= irn_no.substring(32,48);
	                String char_64= irn_no.substring(48,irn_no.length());
	                String final_irn_no=char_16+"\n"+char_32+"\n"+char_48+"\n"+char_64;
	                
	                PdfPTable InvoiceDateInfoTable_qr1 = new PdfPTable(1);
	    	        InvoiceDateInfoTable_qr1.setWidthPercentage(100);
	//    	        InvoiceDateInfoTable_qr1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	//    	        InvoiceDateInfoTable_qr1.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	//    	        InvoiceDateInfoTable_qr1.addCell(new Phrase(new Chunk("QR Code",small_black_bold)));
	    	        InvoiceDateInfoTable_qr1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
	    	        InvoiceDateInfoTable_qr1.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	    	        InvoiceDateInfoTable_qr1.addCell(qrcode_cell);
	    	        
	    	        PdfPTable InvoiceDateInfoTable_qr11 = new PdfPTable(1);
	    	        InvoiceDateInfoTable_qr11.setWidthPercentage(100);
	    	        InvoiceDateInfoTable_qr11.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	    	        InvoiceDateInfoTable_qr11.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	    	        InvoiceDateInfoTable_qr11.addCell(new Phrase(new Chunk("IRN no.",small_black_bold)));
	    	        InvoiceDateInfoTable_qr11.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	    	        InvoiceDateInfoTable_qr11.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	    	        InvoiceDateInfoTable_qr11.addCell(new Phrase(new Chunk(final_irn_no,small_black_normal)));
	                
	    			
	    	        InvoiceDateInfoTable_qr.setWidthPercentage(100);
	    	               
	    	        InvoiceDateInfoTable_qr.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	    	        InvoiceDateInfoTable_qr.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
	    	        InvoiceDateInfoTable_qr.addCell(InvoiceDateInfoTable_qr1);
	    	        
	    	        InvoiceDateInfoTable_qr.getDefaultCell().setBorder(Rectangle.BOX);
	    	        InvoiceDateInfoTable_qr.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	    	        InvoiceDateInfoTable_qr.addCell(InvoiceDateInfoTable_qr11);
	    	        
	    	        InvoiceDateInfoTable_qr.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	    	        InvoiceDateInfoTable_qr.addCell(InvoiceDateInfoTable);
			
			}else{
				float[] InvoiceDateInfoWidths = {0.60f, 0.25f, 0.15f};
	             InvoiceDateInfoTable = new PdfPTable(InvoiceDateInfoWidths);
	            InvoiceDateInfoTable.setWidthPercentage(100);
	            InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	            InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
	            InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
	        	InvoiceDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
	            InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	            InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
	            InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
	        	InvoiceDateInfoTable.addCell(new Phrase(new Chunk("Invoice Date:",small_black_bold)));
	        	InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
	            InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
	            InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
	            InvoiceDateInfoTable.addCell(new Phrase(new Chunk(customer_Invoice_DT,small_black_bold)));
	        
	            float[] InvoiceNOInfoWidths = {0.60f, 0.25f, 0.15f};
	            InvoiceNOInfoTable = new PdfPTable(InvoiceNOInfoWidths);
	            InvoiceNOInfoTable.setWidthPercentage(100);
	            InvoiceNOInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	            InvoiceNOInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
	            InvoiceNOInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
	            InvoiceNOInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
	            InvoiceNOInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	            InvoiceNOInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
	            InvoiceNOInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
	            InvoiceNOInfoTable.addCell(new Phrase(new Chunk(inv_no_info,small_black_bold)));
	            InvoiceNOInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
	            InvoiceNOInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
	            InvoiceNOInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
	            InvoiceNOInfoTable.addCell(new Phrase(new Chunk(invno,small_black_bold)));
			}
	            
	        float[] BillingFieldsInfoWidths = {0.06f, 0.34f, 0.10f, 0.10f, 0.10f, 0.15f};
	        PdfPTable BillingFieldsInfoTable = new PdfPTable(BillingFieldsInfoWidths);
	        BillingFieldsInfoTable.setWidthPercentage(100);
	        BillingFieldsInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
	        BillingFieldsInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	        BillingFieldsInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
	        BillingFieldsInfoTable.addCell(new Phrase(new Chunk("Sr. No.",small_black_bold)));
	        BillingFieldsInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
	        BillingFieldsInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	        BillingFieldsInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
	        BillingFieldsInfoTable.addCell(new Phrase(new Chunk("Item",small_black_bold)));
	        BillingFieldsInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
	        BillingFieldsInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	        BillingFieldsInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
	        BillingFieldsInfoTable.addCell(new Phrase(new Chunk("Data",small_black_bold)));
	        BillingFieldsInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
	        BillingFieldsInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	        BillingFieldsInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
	        BillingFieldsInfoTable.addCell(new Phrase(new Chunk("Currency",small_black_bold)));
	        BillingFieldsInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
	        BillingFieldsInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	        BillingFieldsInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
	        BillingFieldsInfoTable.addCell(new Phrase(new Chunk("Rate",small_black_bold)));
	        BillingFieldsInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
	        BillingFieldsInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	        BillingFieldsInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
	        BillingFieldsInfoTable.addCell(new Phrase(new Chunk("Amount",small_black_bold)));
	        
	        String field_1 = "";
	        String field_2 = "";
	        String field_3 = "";
	        String field_4 = "";
	        String field_5 = "";
	        String field_6 = "";
	        
	        PdfPTable BillingFieldsDetailsTable = new PdfPTable(BillingFieldsInfoWidths);
	        for(int k=0;k<pSr_No.size()-1;k++) {
	        	field_1 = pSr_No.elementAt(k).toString();
	        	field_2 = " "+pItem.elementAt(k).toString();
	        	field_3 = pData.elementAt(k).toString();
	        	field_4 = pCurrency.elementAt(k).toString();
	        	field_5 = pRate.elementAt(k).toString();
	        	field_6 = pAmount.elementAt(k).toString();
	        	
	            BillingFieldsDetailsTable.setWidthPercentage(100);
	            if(k==0)
	            	BillingFieldsDetailsTable.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.TOP | Rectangle.RIGHT);
	            else
	            	BillingFieldsDetailsTable.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.RIGHT);
	            BillingFieldsDetailsTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	            BillingFieldsDetailsTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
	            BillingFieldsDetailsTable.addCell(new Phrase(new Chunk(field_1,small_black_normal)));
	            if(k==0)
	            	BillingFieldsDetailsTable.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.TOP | Rectangle.RIGHT);
	            else
	            	BillingFieldsDetailsTable.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.RIGHT);
	            BillingFieldsDetailsTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
	            BillingFieldsDetailsTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
	            BillingFieldsDetailsTable.addCell(new Phrase(new Chunk(field_2,small_black_normal)));
	            if(k==0)
	            	BillingFieldsDetailsTable.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.TOP | Rectangle.RIGHT);
	            else
	            	BillingFieldsDetailsTable.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.RIGHT);
	            BillingFieldsDetailsTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	            BillingFieldsDetailsTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
	          	BillingFieldsDetailsTable.addCell(new Phrase(new Chunk(field_3,small_black_normal)));
	          	if(k==0)
	            	BillingFieldsDetailsTable.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.TOP | Rectangle.RIGHT);
	            else
	            	BillingFieldsDetailsTable.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.RIGHT);
	            BillingFieldsDetailsTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	            BillingFieldsDetailsTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
	            BillingFieldsDetailsTable.addCell(new Phrase(new Chunk(field_4,small_black_normal)));
	            if(k==0)
	            	BillingFieldsDetailsTable.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.TOP | Rectangle.RIGHT);
	            else
	            	BillingFieldsDetailsTable.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.RIGHT);
	            BillingFieldsDetailsTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
	            BillingFieldsDetailsTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
	            BillingFieldsDetailsTable.addCell(new Phrase(new Chunk(field_5,small_black_normal)));
	            if(k==0)
	            	BillingFieldsDetailsTable.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.TOP | Rectangle.RIGHT);
	            else
	            	BillingFieldsDetailsTable.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.RIGHT);
	            BillingFieldsDetailsTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
	            BillingFieldsDetailsTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
	            BillingFieldsDetailsTable.addCell(new Phrase(new Chunk(field_6,small_black_normal)));
	        }
	        
	    	field_1 = pSr_No.elementAt(pSr_No.size()-1).toString()+"\n\n"; 
	    	field_2 = " "+pItem.elementAt(pSr_No.size()-1).toString()+"\n\n"; 
	    	field_3 = pData.elementAt(pSr_No.size()-1).toString()+"\n\n"; 
	    	field_4 = pCurrency.elementAt(pSr_No.size()-1).toString()+"\n\n"; 
	    	field_5 = pRate.elementAt(pSr_No.size()-1).toString()+" \n\n"; 
	    	field_6 = pAmount.elementAt(pSr_No.size()-1).toString()+" \n\n"; 
	        
	    	PdfPTable BillingFieldsDetailsTable1 = new PdfPTable(BillingFieldsInfoWidths);
	        BillingFieldsDetailsTable1.setWidthPercentage(100);
	        BillingFieldsDetailsTable1.getDefaultCell().setBorder(Rectangle.BOX);
	        BillingFieldsDetailsTable1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	        BillingFieldsDetailsTable1.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
	        BillingFieldsDetailsTable1.addCell(new Phrase(new Chunk(field_1,small_black_bold)));
	        BillingFieldsDetailsTable1.getDefaultCell().setBorder(Rectangle.BOX);
	        BillingFieldsDetailsTable1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
	        BillingFieldsDetailsTable1.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
	        BillingFieldsDetailsTable1.addCell(new Phrase(new Chunk(field_2,small_black_bold)));
	        BillingFieldsDetailsTable1.getDefaultCell().setBorder(Rectangle.BOX);
	        BillingFieldsDetailsTable1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	        BillingFieldsDetailsTable1.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
	      	BillingFieldsDetailsTable1.addCell(new Phrase(new Chunk(field_3,small_black_bold)));
	        BillingFieldsDetailsTable1.getDefaultCell().setBorder(Rectangle.BOX);
	        BillingFieldsDetailsTable1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	        BillingFieldsDetailsTable1.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
	        BillingFieldsDetailsTable1.addCell(new Phrase(new Chunk(field_4,small_black_bold)));
	        BillingFieldsDetailsTable1.getDefaultCell().setBorder(Rectangle.BOX);
	        BillingFieldsDetailsTable1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
	        BillingFieldsDetailsTable1.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
	        BillingFieldsDetailsTable1.addCell(new Phrase(new Chunk(field_5,small_black_bold)));
	        BillingFieldsDetailsTable1.getDefaultCell().setBorder(Rectangle.BOX);
	        BillingFieldsDetailsTable1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
	        BillingFieldsDetailsTable1.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
	        BillingFieldsDetailsTable1.addCell(new Phrase(new Chunk(field_6,small_black_bold)));
	        
	        if(!nRemark_1.equals("")){
	        	nRemark_1=nRemark_1.replace("%$%$%$%$","\n");
	        }
	        
	        PdfPTable RemarkTable = new PdfPTable(1);
	        RemarkTable.setWidthPercentage(100);
	        RemarkTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	        RemarkTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
	        RemarkTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
	        RemarkTable.addCell(new Phrase(new Chunk(nRemark_1,small_black_normal)));
	        
	        PdfPTable SignatureInfoTable = new PdfPTable(1);
			SignatureInfoTable.setWidthPercentage(100);
			SignatureInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			SignatureInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
			SignatureInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			SignatureInfoTable.addCell(new Phrase(new Chunk(contact_Suppl_Name+"\n\n\n\n\nAuthorised Signatory",black_bold)));
	        
	        /*document.add(new Paragraph(" "));
	        document.add(new Paragraph(" "));
	        document.add(new Paragraph(" "));
	        document.add(new Paragraph(" "));*/
			document.add(hlpl_logo_table);
	        if(!irn_no.equals("") && (!qr_code.equals("")) && (temp_cont_typ.equalsIgnoreCase("V"))){
	        	
	        }else{
	        	document.add(InvoiceTitleTable);
	        }
	        document.add(InvoiceDescTable);
	        document.add(InvoiceDescTable2);
	        document.add(InvoiceNoteTable);
	        document.add(new Paragraph(" "));
	        document.add(contact_addr_table);
	        document.add(GstCstInfoTable);
	        document.add(new Paragraph(" "));
	        if(!irn_no.equals("") && (!qr_code.equals("")) && (temp_cont_typ.equalsIgnoreCase("V"))){
	        	document.add(InvoiceDateInfoTable_qr);
	        }else{
	            document.add(InvoiceDateInfoTable);
	            document.add(InvoiceNOInfoTable);
	        }
	        document.add(new Paragraph(" "));
	        document.add(BillingFieldsInfoTable);
	        document.add(BillingFieldsDetailsTable);
	        document.add(BillingFieldsDetailsTable1);
	        document.add(new Paragraph(" "));
	        document.add(RemarkTable);
	        document.add(new Paragraph(" "));
	        document.add(SignatureInfoTable);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			document.close();
		}
}
	public String createPdfFileForLatePayment_SignPDF()
	{
		try
		{
			//System.out.println("In Printing File..");
			HttpSession sess = request.getSession();
			//pdf_path = ""+sess.getAttribute("late_payment_invoice_pdf_path");
			pdf_path = request.getRealPath("/unsigned_pdf/latepay_invoice"); //RG20200320 changed for filtering of invoice
			String inv_title="";
			if(nInvoice_title.equalsIgnoreCase("ORIGINAL"))
			{
				inv_title="O";
			}
			else if(nInvoice_title.equalsIgnoreCase("DUPLICATE"))
			{
				inv_title="D";
			}
			else if(nInvoice_title.equalsIgnoreCase("TRIPLICATE"))
			{
				inv_title="T";
			}
			
			String nm = pPlant_nm;
			if(pPlant_nm.length()>40) {
				nm = pPlant_nm.substring(0,40);
			}
			nm = nm.replaceAll(" ", "_");
			String temp_cont="";
			if((pContract_type.equalsIgnoreCase("S") || pContract_type.equalsIgnoreCase("L")) && !temp_cont_typ.equalsIgnoreCase("V") ){
				temp_cont="I";
			}else{
				temp_cont="M";
			}
			fileName = "LATEPAY_INVOICE-"+mInvoice_dt.trim().substring(0,2)+mInvoice_dt.trim().substring(3,5)+mInvoice_dt.trim().substring(6)+"-"+pCustomer_abbr+"-"+nm+"-"+temp_cont+"-"+mHlpl_inv_seq_no+"-"+inv_title+".pdf";
			f_nm="INVOICE-"+mInvoice_dt.trim().substring(0,2)+mInvoice_dt.trim().substring(3,5)+mInvoice_dt.trim().substring(6)+"-"+pCustomer_abbr+"-"+nm+"-"+temp_cont+"-"+mHlpl_inv_seq_no;
			inv_type_pdf=inv_title;
			pdf_path = pdf_path+"//"+fileName;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return pdf_path;
	}
	public void printPDFLateInvoice_SignPDF() throws Exception {
		Rectangle pageSize = new Rectangle(595, 842);
		Rectangle pageSize1 = new Rectangle(842,595);
		//pageSize.setBackgroundColor(new java.awt.Color(0xffffff));
		//pageSize1.setBackgroundColor(new java.awt.Color(0xffffff));
		String url_DCB = request.getContextPath(); //request.getRequestURL().toString();
	    if(url_DCB.contains("TEST"))
	    {
	    	flag_DCB=true;
	    }
	    else
	    {
	    	flag_DCB=false;
	    }
	    if(flag_DCB==true)
		{
			pageSize.setBackgroundColor(new java.awt.Color(0xe3f8c0)); 
			pageSize1.setBackgroundColor(new java.awt.Color(0xe3f8c0)); 
		}
		else
		{
			pageSize.setBackgroundColor(new java.awt.Color(0xffffff)); 
			pageSize1.setBackgroundColor(new java.awt.Color(0xffffff)); 
		}
		Document document = new Document(pageSize);
		
		try {
			PdfWriter writer = PdfWriter.getInstance(document,new FileOutputStream(createPdfFileForLatePayment_SignPDF()));
			document.addTitle("Late Payment Invoice Details");
			document.addSubject("Late Payment Invoice Details For Customer");
	        document.addKeywords("iText, Invoice Details, Step 2, metadata");
	        document.addCreator("Late Payment Invoice Details Generation using iText");
	        document.addAuthor("DLNG@BIPL");
	        document.open();
			document.setPageSize(pageSize);
	        document.newPage();
	
	        Font very_small_black_normal = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.NORMAL, new Color(0x00, 0x00, 0x00));
	        Font small_black_normal = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL, new Color(0x00, 0x00, 0x00));
	        Font small_black_normal2 = FontFactory.getFont(FontFactory.HELVETICA, 6, Font.NORMAL, new Color(0x00, 0x00, 0x00));
	        Font small_black_bold = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD, new Color(0x00, 0x00, 0x00));
	        Font black_bold = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD, new Color(0x00, 0x00, 0x00));
	        Font big_black_bold_2 = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, new Color(0x00, 0x00, 0x00));
	        Font big_black_bold = FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD, new Color(0x00, 0x00, 0x00));
	        
	        String context_nm = request.getContextPath();
			String server_nm = request.getServerName();
			String server_port = ""+request.getServerPort();
			  
			url_start = "http://"+server_nm+":"+server_port+context_nm;
			
			//  Image hlpl_logo = Image.getInstance(url_start+"//images//LOGO//HLPL-Symbol.jpg");//RG20200320
				Image hlpl_logo = Image.getInstance(url_start+"//images//Shell-Logo.jpg"); //RG20191130
				 /*RG20191128 added for adding logo for sign pdf*/
			 	hlpl_logo.setBorder(Rectangle.NO_BORDER);
	         hlpl_logo.scaleAbsolute(48,45);
	         PdfPCell hlpl_logo_cell1 = new PdfPCell(hlpl_logo,false);
	         hlpl_logo_cell1.setBorder(Rectangle.NO_BORDER);
	         float[] hlpl_logo_Widths = {0.45f};
	         PdfPTable hlpl_logo_table = new PdfPTable(hlpl_logo_Widths);
	         hlpl_logo_table.setWidthPercentage(100);
	         hlpl_logo_table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	         hlpl_logo_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
	         hlpl_logo_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
	         hlpl_logo_table.addCell(hlpl_logo_cell1);
			  /* ended adding logo*/
			           
			//String inv_nm = "TAX INVOICE";
	         String inv_nm = ""; 
	         if((pContract_type.equals("S") || pContract_type.equals("L")) && !temp_cont_typ.equalsIgnoreCase("V")){
	        	 inv_nm = "DEBIT NOTE";
	         }else{
	        	 inv_nm = "TAX INVOICE";
	         }
			String inv_desc = contact_Suppl_Name;
			if(pContract_type.equals("C")  || pContract_type.equals("B")) {
				inv_nm += "\n"+rule_remark;
			}
			String sn = "";
			
			if(pSn_ref_no.trim().equals(""))
			{
				sn = pSn_no;
			}
			else
			{
				sn = pSn_ref_no;
			}
			
			String inv_note = "";
			
			if(pContract_type.equalsIgnoreCase("S"))
			{	
				inv_note = "In respect of Supply Notice (SN-"+sn+") executed on "+customer_Invoice_SN_Dt+" pursuant to Framework Gas Sales Agreement executed on "+customer_Invoice_FGSA_Dt+"\nbetween "+contact_Suppl_Name+" and "+contact_Customer_Name;
			}
			else if(pContract_type.equalsIgnoreCase("L"))
			{
				inv_note = "In respect of Letter of Agreement (LOA-"+sn+") executed on "+customer_Invoice_SN_Dt+" pursuant to Tender executed on "+customer_Invoice_FGSA_Dt+"\nbetween "+contact_Suppl_Name+" and "+contact_Customer_Name;
			}
			else if(pContract_type.equalsIgnoreCase("R"))
			{
				inv_note = "In respect of Regassification Agreement executed on "+customer_Invoice_FGSA_Dt+" and subsequent side letters\nbetween "+contact_Customer_Name+" and "+contact_Suppl_Name;
			}            
			else if(pContract_type.equalsIgnoreCase("T"))//ADDED FOR LTCORA AND CN
			{
				inv_note = "In respect of LTCORA executed on "+customer_Invoice_FGSA_Dt+" \nbetween "+contact_Customer_Name+" and "+contact_Suppl_Name;
			}  
			else if(pContract_type.equalsIgnoreCase("C")  || pContract_type.equals("B"))//ADDED FOR LTCORA AND CN
			{
				inv_note = "In respect of LTCORA  executed on "+customer_Invoice_FGSA_Dt+" ";
				if(Double.parseDouble(pFgsa_no)<9999) 
				{
					inv_note = inv_note+"& CN-"+pFgsa_no+" executed on "+customer_Invoice_SN_Dt+"";
				}
				inv_note = inv_note+"\nbetween "+contact_Customer_Name+" and "+contact_Suppl_Name;
			}  
			
			
			String addr_supl = "Registered Office:";
			/*Following changes is done by RG20190425 for late payment invoice */
			Paragraph pp=new Paragraph();
			pp.add(new Phrase(new Chunk(addr_supl,small_black_normal)));
	        
	        if(!contact_Suppl_Name.trim().equals(""))
	        {
	        	//addr_supl += "\n"+contact_Suppl_Name;
	        	pp.add(new Phrase(new Chunk("\n"+contact_Suppl_Name,small_black_normal)));
	        }
	        if(!tempcompname.trim().equals(""))
	        {
	        	//addr_supl += "\n"+contact_Suppl_Name;    
	        	pp.add(new Phrase(new Chunk("\n"+tempcompname,small_black_normal2)));
	        }
	        if(!contact_Suppl_Person_Address.trim().equals(""))
	        {
	        	//addr_supl += "\n"+contact_Suppl_Person_Address;//RG20190425
	        	addr_supl = "\n"+contact_Suppl_Person_Address;
	        }
	        if(!contact_Suppl_Person_City.trim().equals(""))
	        {
	        	addr_supl += "\n"+contact_Suppl_Person_City;     	
	        }
	        if(!contact_Suppl_Person_Pin.trim().equals(""))
	        {
	        	addr_supl += " "+"-"+" "+contact_Suppl_Person_Pin;     	
	        }
	        pp.add(new Phrase(new Chunk(addr_supl,small_black_normal)));//RG20190425
			String addr_customer = "";
	        
			if(contact_Person_Name_And_Designation.trim().equalsIgnoreCase(contact_Customer_Name.trim()))
			{
				if(!contact_Customer_Name.trim().equals(""))
	            {
	            	addr_customer += contact_Customer_Name;    	
	            }
			}
			else
			{
	            if(!contact_Person_Name_And_Designation.trim().equals(""))
	            {
	            	addr_customer += contact_Person_Name_And_Designation;     	
	            }
	            if(!contact_Customer_Name.trim().equals(""))
	            {
	            	addr_customer += "\n"+contact_Customer_Name;     	
	            }
			}
	        if(!contact_Customer_Person_Address.trim().equals(""))
	        {
	        	addr_customer += "\n"+contact_Customer_Person_Address;     	
	        }
	        if(!contact_Customer_Person_City.trim().equals(""))
	        {
	        	addr_customer += "\n"+contact_Customer_Person_City;     	
	        }
	        if(!contact_Customer_Person_Pin.trim().equals(""))
	        {
	        	addr_customer += " "+"-"+" "+contact_Customer_Person_Pin;     	
	        }
	        
	        PdfPTable InvoiceTitleTable = new PdfPTable(1);
	        InvoiceTitleTable.setWidthPercentage(100);
	        InvoiceTitleTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	        InvoiceTitleTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	    	String inv_tit = nInvoice_title;
	    	if(pContract_type.equals("C")  || pContract_type.equals("B")) {
	        	if(nInvoice_title.equalsIgnoreCase("ORIGINAL")) {
	        		inv_tit += " FOR RECIPIENT"; 
	            } else if(nInvoice_title.equalsIgnoreCase("DUPLICATE")) {
	            	inv_tit += " FOR SUPPLIER";
	            } 
	    	}
	    	InvoiceTitleTable.addCell(new Phrase(new Chunk(inv_tit,black_bold)));
	        
	        PdfPTable InvoiceDescTable = new PdfPTable(1);
	        InvoiceDescTable.setWidthPercentage(100);
	        InvoiceDescTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	        InvoiceDescTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	        InvoiceDescTable.addCell(new Phrase(new Chunk(inv_desc,big_black_bold)));
	        
	        PdfPTable InvoiceDescTable2 = new PdfPTable(1);
	        InvoiceDescTable2.setWidthPercentage(100);
	        InvoiceDescTable2.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	        InvoiceDescTable2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	    	InvoiceDescTable2.addCell(new Phrase(new Chunk(inv_nm,big_black_bold_2)));
	        
	        PdfPTable InvoiceNoteTable = new PdfPTable(1);
	        InvoiceNoteTable.setWidthPercentage(100);
	        InvoiceNoteTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	        InvoiceNoteTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	        InvoiceNoteTable.addCell(new Phrase(new Chunk(inv_note,small_black_normal)));
	        
	        float[] ContactAddrWidths = {0.50f, 0.10f, 0.40f};
	        PdfPTable contact_addr_table = new PdfPTable(ContactAddrWidths);
	        contact_addr_table.setWidthPercentage(100);
	        contact_addr_table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	        contact_addr_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
	        contact_addr_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
	        //contact_addr_table.addCell(new Phrase(new Chunk(addr_supl,small_black_normal)));//RG20190425
	        contact_addr_table.addCell(pp); //RG20190425
	        contact_addr_table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	        contact_addr_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
	        contact_addr_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
	        contact_addr_table.addCell(new Phrase(new Chunk("To:",small_black_bold)));
	        contact_addr_table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	        contact_addr_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
	        contact_addr_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
	        contact_addr_table.addCell(new Phrase(new Chunk(addr_customer,small_black_normal)));
	        
	        String supl_gst_cst_info = "";
	        String customer_gst_cst_info = "";
	        
	        if(pContract_type.equalsIgnoreCase("S") || pContract_type.equalsIgnoreCase("L"))
			{
	        	if(!contact_Suppl_GST_NO.trim().equals(""))
	        	{
	        		supl_gst_cst_info = "GST TIN No. : "+contact_Suppl_GST_NO+" DT. "+contact_Suppl_GST_DT;
	        	}
	        	else
	        	{
	        		supl_gst_cst_info = "";
	        	}
	        	
	        	if(!contact_Suppl_CST_NO.trim().equals(""))
	        	{
	        		supl_gst_cst_info += " \nCST TIN No. : "+contact_Suppl_CST_NO+" DT. "+contact_Suppl_CST_DT;
	        	}
	        	else
	        	{
	        		supl_gst_cst_info += "\n";
	        	}
	        	
	        	if(!contact_Suppl_PAN_NO.trim().equals(""))
	        	{
	        		supl_gst_cst_info += " \nPAN : "+contact_Suppl_PAN_NO+" ";
	        	}
	        	else
	        	{
	        		supl_gst_cst_info += "\n";
	        	}
			}
	        
	        if(vSTAT_CD.size()>0)
			{	
				if(pContract_type.equalsIgnoreCase("S") || pContract_type.equalsIgnoreCase("L"))
				{
					for(int i=0; i<vSTAT_CD.size(); i++)
					{
						if(i==0)
						{
							if(vSTAT_NM.elementAt(i).toString().equalsIgnoreCase("PAN No.")) 
							{
								customer_gst_cst_info = "PAN : "+vSTAT_NO.elementAt(i)+" ";
							}
							else
							{
								if(!vSTAT_NO.elementAt(i).equals("")){
									customer_gst_cst_info = ""+vSTAT_NM.elementAt(i)+" : "+vSTAT_NO.elementAt(i)+" ";
									if(!vSTAT_EFF_DT.elementAt(i).equals("")){
										customer_gst_cst_info+= "DT. "+vSTAT_EFF_DT.elementAt(i);
									}
								}
							}
						}
						else if(i>0)
						{
							if(vSTAT_NM.elementAt(i).toString().equalsIgnoreCase("PAN No.")) 
							{
								customer_gst_cst_info += "\nPAN : "+vSTAT_NO.elementAt(i)+" ";
							}
							else
							{
								if(!vSTAT_NO.elementAt(i).equals("")){
									customer_gst_cst_info += "\n"+vSTAT_NM.elementAt(i)+" : "+vSTAT_NO.elementAt(i)+" ";
									if(!vSTAT_EFF_DT.elementAt(i).equals("")){
										customer_gst_cst_info += " DT. "+vSTAT_EFF_DT.elementAt(i);
									}
								}
							}
						}
					}
				}
				else
				{
					customer_gst_cst_info = "State : "+contact_Customer_Plant_State+"\n";
					customer_gst_cst_info += "State Code : "+contact_Customer_State_Code+"\n";
					for(int i=0; i<vSTAT_CD.size(); i++)
					{
						if(vSTAT_NM.elementAt(i).toString().contains("GSTIN")) { 
							customer_gst_cst_info += vSTAT_NM.elementAt(i).toString()+" : "+vSTAT_NO.elementAt(i)+"\n";
						} else if(vSTAT_NM.elementAt(i).toString().equalsIgnoreCase("PAN No.")) 
						{
							customer_gst_cst_info += "PAN : "+vSTAT_NO.elementAt(i)+"\n";	
						}
					}
				}
			}
	        else
	        {
	        	if(pContract_type.equalsIgnoreCase("S") || pContract_type.equalsIgnoreCase("L"))
	    		{
	        		if(!contact_Customer_GST_NO.trim().equals(""))
	            	{
	            		customer_gst_cst_info = "GST TIN No. : "+contact_Customer_GST_NO+" DT. "+contact_Customer_GST_DT;
	            	}
	            	else
	            	{
	            		customer_gst_cst_info = "";
	            	}
	            	
	            	if(!contact_Customer_CST_NO.trim().equals(""))
	            	{
	            		customer_gst_cst_info += "\nCST TIN No. : "+contact_Customer_CST_NO+" DT. "+contact_Customer_CST_DT;
	            	}
	            	else
	            	{
	            		customer_gst_cst_info += "\n";
	            	}
	            	
	            	if(!contact_Customer_GVAT_NO.trim().equals(""))
	            	{
	            		customer_gst_cst_info += "\nGVAT TIN No. : "+contact_Customer_GVAT_NO+" DT. "+contact_Customer_GVAT_DT;
	            	}
	            	else
	            	{
	            		customer_gst_cst_info += "\n";
	            	}
	    		}
	        }
	        
	        float[] GstCstInfoWidths = {0.50f, 0.10f, 0.40f};
	        PdfPTable GstCstInfoTable = new PdfPTable(GstCstInfoWidths);
	        GstCstInfoTable.setWidthPercentage(100);
	        GstCstInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	        GstCstInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
	        GstCstInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
	        GstCstInfoTable.addCell(new Phrase(new Chunk("\n"+supl_gst_cst_info,small_black_normal)));
	        GstCstInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	        GstCstInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
	        GstCstInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
	        GstCstInfoTable.addCell(new Phrase(new Chunk("\n ",small_black_normal)));
	        GstCstInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	        GstCstInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
	        GstCstInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
	        GstCstInfoTable.addCell(new Phrase(new Chunk("\n"+customer_gst_cst_info,small_black_normal)));
			
	        String invno = "";
	        
	        if(!mNew_inv_seq_no.equals("")) {
	        	invno = mNew_inv_seq_no;
	        } else {
	        	if(pContract_type.equalsIgnoreCase("T") || pContract_type.equalsIgnoreCase("C")  || pContract_type.equals("B"))
	        	{
	        		if(mHlpl_inv_seq_no.length()>13)
	        		{
	        			invno = mHlpl_inv_seq_no.substring(0,10)+""+mHlpl_inv_seq_no.substring(12);
	        		}      		
	        	}
	        	else
	        	{
	        		if(mHlpl_inv_seq_no.length()>13)
	        		{
	        			invno = mHlpl_inv_seq_no.substring(0,5)+""+mHlpl_inv_seq_no.substring(7,10)+""+mHlpl_inv_seq_no.substring(12);
	        		}       		
	        	}
	        }
	        
			String inv_no_info = "";
			String invoiceType = "";
			String inv_dt_Header = "Invoice Date:";
			String Inv_Ref_Dtl = ""; 
			 
			invoiceType = "Invoice Seq No:";
		
			if(pContract_type.equalsIgnoreCase("R"))
			{
				inv_no_info = "SEIPL R-gas Invoice Seq No:";
			}
			else if(pContract_type.equalsIgnoreCase("T") || pContract_type.equalsIgnoreCase("C")  || pContract_type.equals("B"))
			{
				inv_no_info = "SEIPL Tax Invoice Seq No:";
			}
			else
			{
				inv_no_info = "SEIPL Invoice Seq No:";
			}
	        
			PdfPTable InvoiceDateInfoTable;
			PdfPTable InvoiceNOInfoTable;
			float[] InvoiceDateInfoWidths_qr = {0.40f, 0.20f, 0.60f};
			 PdfPTable InvoiceDateInfoTable_qr = new PdfPTable(InvoiceDateInfoWidths_qr);
			if(!irn_no.equals("") && (!qr_code.equals("")) && (temp_cont_typ.equalsIgnoreCase("V"))){
	
				//float[] InvoiceDateInfoWidths = {0.60f, 0.25f, 0.15f};
				float[] InvoiceDateInfoWidths = {0.20f, 0.45f, 0.25f};
	            InvoiceDateInfoTable = new PdfPTable(InvoiceDateInfoWidths);
	            InvoiceDateInfoTable.setWidthPercentage(100);
	            InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	            InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
	            InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
	        	InvoiceDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
	            InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	            InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
	            InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
	        	InvoiceDateInfoTable.addCell(new Phrase(new Chunk("Invoice Date:",small_black_bold)));
	        	InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
	            InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
	            InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
	            InvoiceDateInfoTable.addCell(new Phrase(new Chunk(customer_Invoice_DT,small_black_bold)));
	        
	            float[] InvoiceNOInfoWidths = {0.60f, 0.25f, 0.15f};
	            InvoiceNOInfoTable = new PdfPTable(InvoiceNOInfoWidths);
	            InvoiceNOInfoTable.setWidthPercentage(100);
	            
	            InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	            InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
	            InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
	            InvoiceDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
	            InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	            InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
	            InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
	            InvoiceDateInfoTable.addCell(new Phrase(new Chunk(inv_no_info,small_black_bold)));
	            InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
	            InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
	            InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
	            InvoiceDateInfoTable.addCell(new Phrase(new Chunk(invno,small_black_bold)));
	            
	            //Extra cell added for no border
	            InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	            InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
	            InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
	            InvoiceDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
	            InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	            InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
	            InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
	            InvoiceDateInfoTable.addCell(new Phrase(new Chunk("",small_black_bold)));
	            InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	            InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
	            InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
	            InvoiceDateInfoTable.addCell(new Phrase(new Chunk("",small_black_bold)));
	            
	          //code for Irn & qrcode starts now
	            url_start = "http://"+server_nm+":"+server_port+context_nm;
	                String qrCodeText = qr_code;
	                String filePath =  request.getRealPath("/pdf_reports/xls_reports/qrcode");
	      		  filePath = filePath+"//"+invno.replace("/","_")+".png";
//	      		  System.out.println("filePath----"+filePath);
	      		  int size = 45;//125 original
	      		  String fileType = "png";
	      		  File qrFile = new File(filePath);
	      		  com.seipl.hazira.dlng.util.DataBean_QRCode_Generator.createQRImage(qrFile, qrCodeText, size, fileType);
	      		  System.out.println("DONE");
	      		  
	                
	              Image qr_codeimg = Image.getInstance(url_start+"//pdf_reports//xls_reports//qrcode//"+invno.replace("/","_")+".png");			  
	              qr_codeimg.setBorder(Rectangle.NO_BORDER);
	              qr_codeimg.setAlignment(Element.ALIGN_LEFT);
	             // qr_codeimg.scaleAbsolute(75,75);
	            //  qr_codeimg.scaleAbsolute(50,50);
	              PdfPCell qrcode_cell = new PdfPCell(qr_codeimg,false);
	              qrcode_cell.setBorder(Rectangle.NO_BORDER);
	              qrcode_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	                  
	//                String irn_no=irn_no;
	                String char_16= irn_no.substring(0,16);
	                String char_32= irn_no.substring(16,32);
	                String char_48= irn_no.substring(32,48);
	                String char_64= irn_no.substring(48,irn_no.length());
	                String final_irn_no=char_16+"\n"+char_32+"\n"+char_48+"\n"+char_64;
	                
	                PdfPTable InvoiceDateInfoTable_qr1 = new PdfPTable(1);
	    	        InvoiceDateInfoTable_qr1.setWidthPercentage(100);
	//    	        InvoiceDateInfoTable_qr1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	//    	        InvoiceDateInfoTable_qr1.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	//    	        InvoiceDateInfoTable_qr1.addCell(new Phrase(new Chunk("QR Code",small_black_bold)));
	    	        InvoiceDateInfoTable_qr1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
	    	        InvoiceDateInfoTable_qr1.getDefaultCell().setBorder(Rectangle.BOX);
	    	        InvoiceDateInfoTable_qr1.addCell(qrcode_cell);
	    	        
	    	        PdfPTable InvoiceDateInfoTable_qr11 = new PdfPTable(1);
	    	        InvoiceDateInfoTable_qr11.setWidthPercentage(100);
	    	        InvoiceDateInfoTable_qr11.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	    	        InvoiceDateInfoTable_qr11.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	    	        InvoiceDateInfoTable_qr11.addCell(new Phrase(new Chunk("IRN no.",small_black_bold)));
	    	        InvoiceDateInfoTable_qr11.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	    	        InvoiceDateInfoTable_qr11.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	    	        InvoiceDateInfoTable_qr11.addCell(new Phrase(new Chunk(final_irn_no,small_black_normal)));
	                
	    			
	    	        InvoiceDateInfoTable_qr.setWidthPercentage(100);
	    	               
	    	        InvoiceDateInfoTable_qr.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	    	        InvoiceDateInfoTable_qr.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
	    	        InvoiceDateInfoTable_qr.addCell(InvoiceDateInfoTable_qr1);
	    	        
	    	        InvoiceDateInfoTable_qr.getDefaultCell().setBorder(Rectangle.BOX);
	    	        InvoiceDateInfoTable_qr.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	    	        InvoiceDateInfoTable_qr.addCell(InvoiceDateInfoTable_qr11);
	    	        
	    	        InvoiceDateInfoTable_qr.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	    	        InvoiceDateInfoTable_qr.addCell(InvoiceDateInfoTable);
			
			
			}else{
				float[] InvoiceDateInfoWidths = {0.60f, 0.25f, 0.15f};
	             InvoiceDateInfoTable = new PdfPTable(InvoiceDateInfoWidths);
	            InvoiceDateInfoTable.setWidthPercentage(100);
	            InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	            InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
	            InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
	        	InvoiceDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
	            InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	            InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
	            InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
	        	InvoiceDateInfoTable.addCell(new Phrase(new Chunk("Invoice Date:",small_black_bold)));
	        	InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
	            InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
	            InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
	            InvoiceDateInfoTable.addCell(new Phrase(new Chunk(customer_Invoice_DT,small_black_bold)));
	        
	            float[] InvoiceNOInfoWidths = {0.60f, 0.25f, 0.15f};
	            InvoiceNOInfoTable = new PdfPTable(InvoiceNOInfoWidths);
	            InvoiceNOInfoTable.setWidthPercentage(100);
	            InvoiceNOInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	            InvoiceNOInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
	            InvoiceNOInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
	            InvoiceNOInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
	            InvoiceNOInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	            InvoiceNOInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
	            InvoiceNOInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
	            InvoiceNOInfoTable.addCell(new Phrase(new Chunk(inv_no_info,small_black_bold)));
	            InvoiceNOInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
	            InvoiceNOInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
	            InvoiceNOInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
	            InvoiceNOInfoTable.addCell(new Phrase(new Chunk(invno,small_black_bold)));
			}
	            
	        float[] BillingFieldsInfoWidths = {0.06f, 0.34f, 0.10f, 0.10f, 0.10f, 0.15f};
	        PdfPTable BillingFieldsInfoTable = new PdfPTable(BillingFieldsInfoWidths);
	        BillingFieldsInfoTable.setWidthPercentage(100);
	        BillingFieldsInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
	        BillingFieldsInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	        BillingFieldsInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
	        BillingFieldsInfoTable.addCell(new Phrase(new Chunk("Sr. No.",small_black_bold)));
	        BillingFieldsInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
	        BillingFieldsInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	        BillingFieldsInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
	        BillingFieldsInfoTable.addCell(new Phrase(new Chunk("Item",small_black_bold)));
	        BillingFieldsInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
	        BillingFieldsInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	        BillingFieldsInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
	        BillingFieldsInfoTable.addCell(new Phrase(new Chunk("Data",small_black_bold)));
	        BillingFieldsInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
	        BillingFieldsInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	        BillingFieldsInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
	        BillingFieldsInfoTable.addCell(new Phrase(new Chunk("Currency",small_black_bold)));
	        BillingFieldsInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
	        BillingFieldsInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	        BillingFieldsInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
	        BillingFieldsInfoTable.addCell(new Phrase(new Chunk("Rate",small_black_bold)));
	        BillingFieldsInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
	        BillingFieldsInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	        BillingFieldsInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
	        BillingFieldsInfoTable.addCell(new Phrase(new Chunk("Amount",small_black_bold)));
	        
	        String field_1 = "";
	        String field_2 = "";
	        String field_3 = "";
	        String field_4 = "";
	        String field_5 = "";
	        String field_6 = "";
	        
	        PdfPTable BillingFieldsDetailsTable = new PdfPTable(BillingFieldsInfoWidths);
	        for(int k=0;k<pSr_No.size()-1;k++) {
	        	field_1 = pSr_No.elementAt(k).toString();
	        	field_2 = " "+pItem.elementAt(k).toString();
	        	field_3 = pData.elementAt(k).toString();
	        	field_4 = pCurrency.elementAt(k).toString();
	        	field_5 = pRate.elementAt(k).toString();
	        	field_6 = pAmount.elementAt(k).toString();
	        	
	            BillingFieldsDetailsTable.setWidthPercentage(100);
	            if(k==0)
	            	BillingFieldsDetailsTable.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.TOP | Rectangle.RIGHT);
	            else
	            	BillingFieldsDetailsTable.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.RIGHT);
	            BillingFieldsDetailsTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	            BillingFieldsDetailsTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
	            BillingFieldsDetailsTable.addCell(new Phrase(new Chunk(field_1,small_black_normal)));
	            if(k==0)
	            	BillingFieldsDetailsTable.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.TOP | Rectangle.RIGHT);
	            else
	            	BillingFieldsDetailsTable.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.RIGHT);
	            BillingFieldsDetailsTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
	            BillingFieldsDetailsTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
	            BillingFieldsDetailsTable.addCell(new Phrase(new Chunk(field_2,small_black_normal)));
	            if(k==0)
	            	BillingFieldsDetailsTable.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.TOP | Rectangle.RIGHT);
	            else
	            	BillingFieldsDetailsTable.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.RIGHT);
	            BillingFieldsDetailsTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	            BillingFieldsDetailsTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
	          	BillingFieldsDetailsTable.addCell(new Phrase(new Chunk(field_3,small_black_normal)));
	          	if(k==0)
	            	BillingFieldsDetailsTable.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.TOP | Rectangle.RIGHT);
	            else
	            	BillingFieldsDetailsTable.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.RIGHT);
	            BillingFieldsDetailsTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	            BillingFieldsDetailsTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
	            BillingFieldsDetailsTable.addCell(new Phrase(new Chunk(field_4,small_black_normal)));
	            if(k==0)
	            	BillingFieldsDetailsTable.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.TOP | Rectangle.RIGHT);
	            else
	            	BillingFieldsDetailsTable.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.RIGHT);
	            BillingFieldsDetailsTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
	            BillingFieldsDetailsTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
	            BillingFieldsDetailsTable.addCell(new Phrase(new Chunk(field_5,small_black_normal)));
	            if(k==0)
	            	BillingFieldsDetailsTable.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.TOP | Rectangle.RIGHT);
	            else
	            	BillingFieldsDetailsTable.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.RIGHT);
	            BillingFieldsDetailsTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
	            BillingFieldsDetailsTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
	            BillingFieldsDetailsTable.addCell(new Phrase(new Chunk(field_6,small_black_normal)));
	        }
	        
	    	field_1 = pSr_No.elementAt(pSr_No.size()-1).toString()+"\n\n"; 
	    	field_2 = " "+pItem.elementAt(pSr_No.size()-1).toString()+"\n\n"; 
	    	field_3 = pData.elementAt(pSr_No.size()-1).toString()+"\n\n"; 
	    	field_4 = pCurrency.elementAt(pSr_No.size()-1).toString()+"\n\n"; 
	    	field_5 = pRate.elementAt(pSr_No.size()-1).toString()+" \n\n"; 
	    	field_6 = pAmount.elementAt(pSr_No.size()-1).toString()+" \n\n"; 
	        
	    	PdfPTable BillingFieldsDetailsTable1 = new PdfPTable(BillingFieldsInfoWidths);
	        BillingFieldsDetailsTable1.setWidthPercentage(100);
	        BillingFieldsDetailsTable1.getDefaultCell().setBorder(Rectangle.BOX);
	        BillingFieldsDetailsTable1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	        BillingFieldsDetailsTable1.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
	        BillingFieldsDetailsTable1.addCell(new Phrase(new Chunk(field_1,small_black_bold)));
	        BillingFieldsDetailsTable1.getDefaultCell().setBorder(Rectangle.BOX);
	        BillingFieldsDetailsTable1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
	        BillingFieldsDetailsTable1.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
	        BillingFieldsDetailsTable1.addCell(new Phrase(new Chunk(field_2,small_black_bold)));
	        BillingFieldsDetailsTable1.getDefaultCell().setBorder(Rectangle.BOX);
	        BillingFieldsDetailsTable1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	        BillingFieldsDetailsTable1.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
	      	BillingFieldsDetailsTable1.addCell(new Phrase(new Chunk(field_3,small_black_bold)));
	        BillingFieldsDetailsTable1.getDefaultCell().setBorder(Rectangle.BOX);
	        BillingFieldsDetailsTable1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	        BillingFieldsDetailsTable1.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
	        BillingFieldsDetailsTable1.addCell(new Phrase(new Chunk(field_4,small_black_bold)));
	        BillingFieldsDetailsTable1.getDefaultCell().setBorder(Rectangle.BOX);
	        BillingFieldsDetailsTable1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
	        BillingFieldsDetailsTable1.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
	        BillingFieldsDetailsTable1.addCell(new Phrase(new Chunk(field_5,small_black_bold)));
	        BillingFieldsDetailsTable1.getDefaultCell().setBorder(Rectangle.BOX);
	        BillingFieldsDetailsTable1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
	        BillingFieldsDetailsTable1.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
	        BillingFieldsDetailsTable1.addCell(new Phrase(new Chunk(field_6,small_black_bold)));
	        
	        if(!nRemark_1.equals("")){
	        	nRemark_1=nRemark_1.replace("%$%$%$%$","\n");
	        }
	        
	        PdfPTable RemarkTable = new PdfPTable(1);
	        RemarkTable.setWidthPercentage(100);
	        RemarkTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	        RemarkTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
	        RemarkTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
	        RemarkTable.addCell(new Phrase(new Chunk(nRemark_1,small_black_normal)));
	        
	        PdfPTable SignatureInfoTable = new PdfPTable(1);
			SignatureInfoTable.setWidthPercentage(100);
			SignatureInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			SignatureInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
			SignatureInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			//SignatureInfoTable.addCell(new Phrase(new Chunk(contact_Suppl_Name+"\n\n\n\n\nAuthorised Signatory",black_bold)));
			SignatureInfoTable.addCell(new Phrase(new Chunk(contact_Suppl_Name,black_bold)));
			
			  // create a signature form field //RG20200320
	        PdfPTable BillingFieldsInfoTable81 = new PdfPTable(1);
	        BillingFieldsInfoTable81.setWidthPercentage(20);
	        BillingFieldsInfoTable81.setHorizontalAlignment(Element.ALIGN_LEFT);
	        BillingFieldsInfoTable81.getDefaultCell().setBorder(Rectangle.BOX);
	        BillingFieldsInfoTable81.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
	        BillingFieldsInfoTable81.getDefaultCell().setVerticalAlignment(Element.ALIGN_LEFT);
	       
	        
	        PdfFormField field = PdfFormField.createSignature(writer);
	        field.setFieldName(SIGNAME);
	        //field.setFieldFlags(PdfFormField.FF_READ_ONLY); //to make invisible 
	        
	        // set the widget properties
	        field.setPage();
	        field.setWidget(new Rectangle(72, 732, 144, 780), PdfAnnotation.HIGHLIGHT_NONE);
	//        field.setButton(0);
	        field.setFlags(PdfAnnotation.FLAGS_PRINT);
	//        field.setColor(Color.WHITE);
	        writer.addAnnotation(field);
	        
	        PdfPCell sigCell = new PdfPCell();
	        FieldPositioningEvents events = new FieldPositioningEvents(writer, field);
	        sigCell.setCellEvent(events);
	        sigCell.setFixedHeight(50f);
	        BillingFieldsInfoTable81.addCell(sigCell);
	        // add it as an annotation
	        
	        PdfPTable SignatureInfoTable1 = new PdfPTable(1);
			SignatureInfoTable1.setWidthPercentage(100);
			SignatureInfoTable1.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			SignatureInfoTable1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
			SignatureInfoTable1.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			SignatureInfoTable1.addCell(new Phrase(new Chunk("Authorised Signatory",black_bold)));
			
			
	        
	       /* document.add(new Paragraph(" "));
	        document.add(new Paragraph(" "));
	        document.add(new Paragraph(" "));
	        document.add(new Paragraph(" "));*/
			document.add(hlpl_logo_table); //RG20200320 added for adding logo for sign pdf
			if(!irn_no.equals("") && (!qr_code.equals("")) && (temp_cont_typ.equalsIgnoreCase("V"))){}else{
				document.add(InvoiceTitleTable);
			}
	        document.add(InvoiceDescTable);
	        document.add(InvoiceDescTable2);
	        document.add(InvoiceNoteTable);
	        document.add(new Paragraph(" "));
	        document.add(contact_addr_table);
	        document.add(GstCstInfoTable);
	        document.add(new Paragraph(" "));
	        if(!irn_no.equals("") && (!qr_code.equals("")) && (temp_cont_typ.equalsIgnoreCase("V"))){
	        	document.add(InvoiceDateInfoTable_qr);
	        }else{
	        document.add(InvoiceDateInfoTable);
	        document.add(InvoiceNOInfoTable);
	        }
	        document.add(new Paragraph(" "));
	        document.add(BillingFieldsInfoTable);
	        document.add(BillingFieldsDetailsTable);
	        document.add(BillingFieldsDetailsTable1);
	        document.add(new Paragraph(" "));
	        document.add(RemarkTable);
	        document.add(new Paragraph(" "));
	        document.add(SignatureInfoTable);
	        document.add(BillingFieldsInfoTable81);
	        document.add(SignatureInfoTable1);
	        
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			document.close();
		}
	}
	
	public void setCallFlag(String callFlag) {
		this.callFlag = callFlag;
	}
	public String getLate_pay_month() {
		return late_pay_month;
	}
	public String getLate_pay_year() {
		return late_pay_year;
	}
	public String getTempcompname() {
		return tempcompname;
	}
	public void setTempcompname(String tempcompname) {
		this.tempcompname = tempcompname;
	}
	public void setLate_pay_month(String late_pay_month) {
		this.late_pay_month = late_pay_month;
	}
	public void setLate_pay_year(String late_pay_year) {
		this.late_pay_year = late_pay_year;
	}
	public Vector getMonths() {
		return months;
	}
	public Vector getMonths_id() {
		return months_id;
	}
	public String getStartDt() {
		return StartDt;
	}
	public String getEndDt() {
		return EndDt;
	}
	public Vector getLate_pay_plant_seq_no() {
		return late_pay_plant_seq_no;
	}
	public void setLate_pay_plant_seq_no(Vector late_pay_plant_seq_no) {
		this.late_pay_plant_seq_no = late_pay_plant_seq_no;
	}

	public Vector getLate_pay_new_inv_seq_no() {
		return late_pay_new_inv_seq_no;
	}

	public Vector getLate_pay_hlpl_inv_seq_no() {
		return late_pay_hlpl_inv_seq_no;
	}

	public Vector getLate_pay_inv_dt() {
		return late_pay_inv_dt;
	}

	public Vector getLate_pay_due_dt() {
		return late_pay_due_dt;
	}

	public Vector getLate_pay_recv_dt() {
		return late_pay_recv_dt;
	}

	public Vector getLate_pay_recv_amt() {
		return late_pay_recv_amt;
	}

	public Vector getLate_pay_net_amt() {
		return late_pay_net_amt;
	}

	public Vector getLate_pay_payable_amt() {
		return late_pay_payable_amt;
	}

	public Vector getLate_pay_int_cal_percentage() {
		return late_pay_int_cal_percentage;
	}

	public Vector getLate_pay_customer_cd() {
		return late_pay_customer_cd;
	}

	public Vector getLate_pay_contract_type() {
		return late_pay_contract_type;
	}

	public Vector getLate_pay_customer_abbr() {
		return late_pay_customer_abbr;
	}

	public Vector getLate_pay_customer_nm() {
		return late_pay_customer_nm;
	}

	public Vector getLate_pay_financial_year() {
		return late_pay_financial_year;
	}

	public Vector getLate_pay_no_days() {
		return late_pay_no_days;
	}

	public Vector getMhlpl_inv_seq_no() {
		return Mhlpl_inv_seq_no;
	}

	public Vector getMnew_inv_seq_no() {
		return Mnew_inv_seq_no;
	}

	public Vector getMinvoice_dt() {
		return Minvoice_dt;
	}

	public Vector getMcontract_type() {
		return Mcontract_type;
	}

	public Vector getMchecked_flag() {
		return Mchecked_flag;
	}

	public Vector getMapproved_flag() {
		return Mapproved_flag;
	}

	public Vector getMpdf_inv_dtl() {
		return Mpdf_inv_dtl;
	}

	public Vector getMpdf_lock_flag() {
		return Mpdf_lock_flag;
	}
	public Vector getMpdf_view() {
		return Mpdf_view;
	}

	public Vector getMpdf_file_nm() {
		return Mpdf_file_nm;
	}
	public Vector getMpdf_view_signpdf() {
		return Mpdf_view_signpdf;
	}

	public Vector getMpdf_view_mail_sent() {
		return Mpdf_view_mail_sent;
	}

	public Vector getMcontact_person_cd() {
		return Mcontact_person_cd;
	}

	public Vector getIrn_flag() {
		return irn_flag;
	}
	public String getpCustomer_cd() {
		return pCustomer_cd;
	}
	public void setpCustomer_cd(String pCustomer_cd) {
		this.pCustomer_cd = pCustomer_cd;
	}
	public String getpNew_inv_seq_no() {
		return pNew_inv_seq_no;
	}
	public void setpNew_inv_seq_no(String pNew_inv_seq_no) {
		this.pNew_inv_seq_no = pNew_inv_seq_no;
	}
	public String getpHlpl_inv_seq_no() {
		return pHlpl_inv_seq_no;
	}
	public void setpHlpl_inv_seq_no(String pHlpl_inv_seq_no) {
		this.pHlpl_inv_seq_no = pHlpl_inv_seq_no;
	}
	public String getpInvoice_date() {
		return pInvoice_date;
	}
	public void setpInvoice_date(String pInvoice_date) {
		this.pInvoice_date = pInvoice_date;
	}
	public String getpContract_type() {
		return pContract_type;
	}
	public void setpContract_type(String pContract_type) {
		this.pContract_type = pContract_type;
	}
	public String getpFinancial_year() {
		return pFinancial_year;
	}
	public void setpFinancial_year(String pFinancial_year) {
		this.pFinancial_year = pFinancial_year;
	}
	public String getpCustomer_abbr() {
		return pCustomer_abbr;
	}
	public void setpCustomer_abbr(String pCustomer_abbr) {
		this.pCustomer_abbr = pCustomer_abbr;
	}
	public String getpPlant_nm() {
		return pPlant_nm;
	}
	public void setpPlant_nm(String pPlant_nm) {
		this.pPlant_nm = pPlant_nm;
	}
	public String getpCal_percentage() {
		return pCal_percentage;
	}
	public void setpCal_percentage(String pCal_percentage) {
		this.pCal_percentage = pCal_percentage;
	}
	public String getpNo_days() {
		return pNo_days;
	}
	public void setpNo_days(String pNo_days) {
		this.pNo_days = pNo_days;
	}
	public String getpContract_start_dt() {
		return pContract_start_dt;
	}
	public void setpContract_start_dt(String pContract_start_dt) {
		this.pContract_start_dt = pContract_start_dt;
	}
	public String getpContract_end_dt() {
		return pContract_end_dt;
	}
	public void setpContract_end_dt(String pContract_end_dt) {
		this.pContract_end_dt = pContract_end_dt;
	}
	public String getpDue_date() {
		return pDue_date;
	}
	public void setpDue_date(String pDue_date) {
		this.pDue_date = pDue_date;
	}
	public String getpPay_recv_date() {
		return pPay_recv_date;
	}
	public void setpPay_recv_date(String pPay_recv_date) {
		this.pPay_recv_date = pPay_recv_date;
	}
	public String getpPay_recv_amt() {
		return pPay_recv_amt;
	}
	public void setpPay_recv_amt(String pPay_recv_amt) {
		this.pPay_recv_amt = pPay_recv_amt;
	}
	public String getpNet_amt_inr() {
		return pNet_amt_inr;
	}
	public void setpNet_amt_inr(String pNet_amt_inr) {
		this.pNet_amt_inr = pNet_amt_inr;
	}
	public String getpPlant_seq_no() {
		return pPlant_seq_no;
	}
	public void setpPlant_seq_no(String pPlant_seq_no) {
		this.pPlant_seq_no = pPlant_seq_no;
	}
	public String getnNew_inv_seq_no() {
		return nNew_inv_seq_no;
	}
	public void setnNew_inv_seq_no(String nNew_inv_seq_no) {
		this.nNew_inv_seq_no = nNew_inv_seq_no;
	}
	public String getnNet_amt() {
		return nNet_amt;
	}
	public void setnNet_amt(String nNet_amt) {
		this.nNet_amt = nNet_amt;
	}
	public String getnCust_inv_seq_no() {
		return nCust_inv_seq_no;
	}
	public void setnCust_inv_seq_no(String nCust_inv_seq_no) {
		this.nCust_inv_seq_no = nCust_inv_seq_no;
	}
	public String getnInvoice_seq_no_actual() {
		return nInvoice_seq_no_actual;
	}
	public void setnInvoice_seq_no_actual(String nInvoice_seq_no_actual) {
		this.nInvoice_seq_no_actual = nInvoice_seq_no_actual;
	}
	public String getnInvoice_date() {
		return nInvoice_date;
	}
	public void setnInvoice_date(String nInvoice_date) {
		this.nInvoice_date = nInvoice_date;
	}
	public String getnTax_Structure_dtl() {
		return nTax_Structure_dtl;
	}
	public void setnTax_Structure_dtl(String nTax_Structure_dtl) {
		this.nTax_Structure_dtl = nTax_Structure_dtl;
	}
	public String getnTax_struct_cd() {
		return nTax_struct_cd;
	}
	public void setnTax_struct_cd(String nTax_struct_cd) {
		this.nTax_struct_cd = nTax_struct_cd;
	}
	public String getnTotal_amount() {
		return nTotal_amount;
	}
	public void setnTotal_amount(String nTotal_amount) {
		this.nTotal_amount = nTotal_amount;
	}
	public String getNdiff_days() {
		return ndiff_days;
	}
	public void setNdiff_days(String ndiff_days) {
		this.ndiff_days = ndiff_days;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	public String getRefresh_flag() {
		return refresh_flag;
	}
	public void setRefresh_flag(String refresh_flag) {
		this.refresh_flag = refresh_flag;
	}
	public String getmNew_inv_seq_no() {
		return mNew_inv_seq_no;
	}
	public void setmNew_inv_seq_no(String mNew_inv_seq_no) {
		this.mNew_inv_seq_no = mNew_inv_seq_no;
	}
	public String getmHlpl_inv_seq_no() {
		return mHlpl_inv_seq_no;
	}
	public void setmHlpl_inv_seq_no(String mHlpl_inv_seq_no) {
		this.mHlpl_inv_seq_no = mHlpl_inv_seq_no;
	}
	public String getmInvoice_dt() {
		return mInvoice_dt;
	}
	public void setmInvoice_dt(String mInvoice_dt) {
		this.mInvoice_dt = mInvoice_dt;
	}
	public void setMhlpl_inv_seq_no(Vector mhlpl_inv_seq_no) {
		Mhlpl_inv_seq_no = mhlpl_inv_seq_no;
	}
	public void setMinvoice_dt(Vector minvoice_dt) {
		Minvoice_dt = minvoice_dt;
	}
	public String getDiff_days() {
		return diff_days;
	}
	public void setDiff_days(String diff_days) {
		this.diff_days = diff_days;
	}
	public String getpContract_no() {
		return pContract_no;
	}
	public void setpContract_no(String pContract_no) {
		this.pContract_no = pContract_no;
	}
	public Vector getpContact_person_nm() {
		return pContact_person_nm;
	}
	public Vector getpContact_person_cd() {
		return pContact_person_cd;
	}
	public Vector getnTax_cd() {
		return nTax_cd;
	}
	public Vector getnTax_factor() {
		return nTax_factor;
	}
	public String getnTotal_tax_amount() {
		return nTotal_tax_amount;
	}
	public Vector getnTax_abbr() {
		return nTax_abbr;
	}
	public Map getnTax_component_amt() {
		return nTax_component_amt;
	}
	public String getInvFinancialYear() {
		return invFinancialYear;
	}
	public String getYear_interest() {
		return year_interest;
	}
	public String getRmk() {
		return rmk;
	}
	public String getnRemark_1() {
		return nRemark_1;
	}
	public String getnContact_person_cd() {
		return nContact_person_cd;
	}


	public String getCustomer_Invoice_DT() {
		return customer_Invoice_DT;
	}


	public String getCustomer_Invoice_DT1() {
		return customer_Invoice_DT1;
	}


	public String getCustomer_Invoice_Due_DT() {
		return customer_Invoice_Due_DT;
	}


	public String getCustomer_Invoice_Start_DT() {
		return customer_Invoice_Start_DT;
	}


	public String getCustomer_Invoice_End_DT() {
		return customer_Invoice_End_DT;
	}


	public String getContact_Person_Name_And_Designation() {
		return contact_Person_Name_And_Designation;
	}


	public String getContact_Customer_Name() {
		return contact_Customer_Name;
	}


	public String getContact_Customer_Person_Address() {
		return contact_Customer_Person_Address;
	}


	public String getContact_Customer_Person_City() {
		return contact_Customer_Person_City;
	}


	public String getContact_Customer_Person_Pin() {
		return contact_Customer_Person_Pin;
	}


	public String getContact_Customer_GST_NO() {
		return contact_Customer_GST_NO;
	}


	public String getContact_Customer_CST_NO() {
		return contact_Customer_CST_NO;
	}


	public String getContact_Customer_GST_DT() {
		return contact_Customer_GST_DT;
	}


	public String getContact_Customer_CST_DT() {
		return contact_Customer_CST_DT;
	}


	public String getContact_Customer_Service_Tax_NO() {
		return contact_Customer_Service_Tax_NO;
	}


	public String getContact_Customer_Service_Tax_DT() {
		return contact_Customer_Service_Tax_DT;
	}


	public String getContact_Suppl_Name() {
		return contact_Suppl_Name;
	}


	public String getContact_Suppl_Person_Address() {
		return contact_Suppl_Person_Address;
	}


	public String getContact_Suppl_Person_City() {
		return contact_Suppl_Person_City;
	}


	public String getContact_Suppl_Person_Pin() {
		return contact_Suppl_Person_Pin;
	}


	public String getContact_Suppl_GST_NO() {
		return contact_Suppl_GST_NO;
	}


	public String getContact_Suppl_CST_NO() {
		return contact_Suppl_CST_NO;
	}


	public String getContact_Suppl_GST_DT() {
		return contact_Suppl_GST_DT;
	}


	public String getContact_Suppl_CST_DT() {
		return contact_Suppl_CST_DT;
	}


	public String getContact_Suppl_Service_Tax_NO() {
		return contact_Suppl_Service_Tax_NO;
	}


	public String getContact_Suppl_Service_Tax_DT() {
		return contact_Suppl_Service_Tax_DT;
	}


	public String getContact_Suppl_PAN_NO() {
		return contact_Suppl_PAN_NO;
	}


	public String getContact_Suppl_PAN_DT() {
		return contact_Suppl_PAN_DT;
	}


	public String getContact_Suppl_GSTIN_NO() {
		return contact_Suppl_GSTIN_NO;
	}


	public String getContact_Suppl_GSTIN_DT() {
		return contact_Suppl_GSTIN_DT;
	}


	public String getContact_Suppl_State() {
		return contact_Suppl_State;
	}


	public String getContact_Suppl_State_Code() {
		return contact_Suppl_State_Code;
	}


	public String getContact_Customer_Plant_State() {
		return contact_Customer_Plant_State;
	}


	public String getContact_Customer_State_Code() {
		return contact_Customer_State_Code;
	}


	public String getContact_addr_flag() {
		return contact_addr_flag;
	}


	public String getCustomer_Invoice_Tax_Flag() {
		return customer_Invoice_Tax_Flag;
	}


	public String getCustomer_Invoice_SN_Dt() {
		return customer_Invoice_SN_Dt;
	}


	public String getCustomer_Invoice_FGSA_Dt() {
		return customer_Invoice_FGSA_Dt;
	}


	public Vector getvSTAT_CD() {
		return vSTAT_CD;
	}


	public Vector getvSTAT_NM() {
		return vSTAT_NM;
	}


	public Vector getvSTAT_NO() {
		return vSTAT_NO;
	}


	public Vector getvSTAT_EFF_DT() {
		return vSTAT_EFF_DT;
	}


	public String getSac_code() {
		return sac_code;
	}


	public String getSac_name() {
		return sac_name;
	}


	public String getRule_remark() {
		return rule_remark;
	}


	public String getService_desc() {
		return service_desc;
	}


	public String getpSn_no() {
		return pSn_no;
	}


	public String getpSn_rev_no() {
		return pSn_rev_no;
	}


	public String getpSn_ref_no() {
		return pSn_ref_no;
	}


	public Vector getpSr_No() {
		return pSr_No;
	}


	public Vector getpQuantity() {
		return pQuantity;
	}


	public Vector getpRate() {
		return pRate;
	}


	public String getpFgsa_no() {
		return pFgsa_no;
	}


	public String getpFgsa_rev_no() {
		return pFgsa_rev_no;
	}


	public Vector getpItem() {
		return pItem;
	}


	public void setpFgsa_rev_no(String pFgsa_rev_no) {
		this.pFgsa_rev_no = pFgsa_rev_no;
	}


	public Vector getpCurrency() {
		return pCurrency;
	}


	public Vector getpAmount() {
		return pAmount;
	}


	public Vector getpData() {
		return pData;
	}


	public String getContact_Customer_GVAT_NO() {
		return contact_Customer_GVAT_NO;
	}


	public void setContact_Customer_GVAT_NO(String contact_Customer_GVAT_NO) {
		this.contact_Customer_GVAT_NO = contact_Customer_GVAT_NO;
	}


	public String getContact_Customer_GVAT_DT() {
		return contact_Customer_GVAT_DT;
	}


	public void setContact_Customer_GVAT_DT(String contact_Customer_GVAT_DT) {
		this.contact_Customer_GVAT_DT = contact_Customer_GVAT_DT;
	}


	public String getPdf_flag() {
		return pdf_flag;
	}


	public void setPdf_flag(String pdf_flag) {
		this.pdf_flag = pdf_flag;
	}


	public HttpServletRequest getRequest() {
		return request;
	}


	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}


	public String getnInvoice_title() {
		return nInvoice_title;
	}


	public void setnInvoice_title(String nInvoice_title) {
		this.nInvoice_title = nInvoice_title;
	}


	public String getPdf_path() {
		return pdf_path;
	}


	public void setPdf_path(String pdf_path) {
		this.pdf_path = pdf_path;
	}


	public String getUrl_start() {
		return url_start;
	}


	public void setUrl_start(String url_start) {
		this.url_start = url_start;
	}

	public Vector getVact_cont_type() {
		return Vact_cont_type;
	}

	public String getAct_cont_type() {
		return act_cont_type;
	}

	public void setAct_cont_type(String act_cont_type) {
		this.act_cont_type = act_cont_type;
	}

	public String getContact_Person_Seq_No() {
		return contact_Person_Seq_No;
	}

	public void setContact_Person_Seq_No(String contact_Person_Seq_No) {
		this.contact_Person_Seq_No = contact_Person_Seq_No;
	}

	public String getTax_flag() {
		return tax_flag;
	}
	
}
