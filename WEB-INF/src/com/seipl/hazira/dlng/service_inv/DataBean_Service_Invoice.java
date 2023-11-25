package com.seipl.hazira.dlng.service_inv;

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
import javax.sql.DataSource;

import com.seipl.hazira.dlng.util.RuntimeConf;

public class DataBean_Service_Invoice {

	 Connection conn; 
		Statement stmt;
		public String getDrcr_inr_km() {
			return drcr_inr_km;
		}

		public String getDrcr_inr_mmbtu() {
			return drcr_inr_mmbtu;
		}

		public String getDiff_inr_km() {
			return diff_inr_km;
		}

		public String getDiff_inr_mmbtu() {
			return diff_inr_mmbtu;
		}

		Statement stmt1;
		Statement stmt2;
		Statement stmt3;
		ResultSet rset;
		ResultSet rset1;
		ResultSet rset2;
		ResultSet rset3;
		String queryString = "";
		String queryString1 = "";
		String queryString2 = "";
		String tempcompnm="";
		String callFlag = "";
		String month = "";
		String year = "";
		String from_dt = "";
		String to_dt ="";
		String activity="";
		String operation ="";
		String pay_cd = "";
		
		String bill_cycle = "0";
		String methodName = "";
		String databeanName = "DataBean_Service_Invoice";
		public String url_start = "";
		public HttpServletRequest request = null;
		public String pdf_path = ""; //By Achal Pathak ...
		String fileName = "";
		boolean flag_DCB = false;
		NumberFormat nf7 = new DecimalFormat("###,###,###,##0.00"); 
		NumberFormat nf5 = new DecimalFormat("##########0");
		NumberFormat nf = new DecimalFormat("###########0.00");
		String inr_km_str="";
		String km_str="";
		String total_km_inr="0";
		String invDt="";
		String dueDt="";
		String calcBase="";
		String invGrossAmt="";
		String invNetAmt="";
		String invTaxAmt="";
		String remark1="";
		String remark2="";
		String tax_structure="";
		String taxnm_str = "";
		double total_qty = 0; 
		double total_amt = 0; 
		Vector yearList = new Vector();
		Vector VinvCustomer_cd = new Vector();
		Vector VinvCustomer_abbr = new Vector();
		Vector VinvMapping_id = new Vector();
		Vector VinvContNo = new Vector();
		Vector VinvContType = new Vector();
		Vector VinvPlant_seq_no = new Vector();
		Vector VinvPlant_name = new Vector();
		Vector VinvNew_inv_seq_no = new Vector();
		Vector VinvInv_seq_no = new Vector();
		Vector VinvInvoice_dt = new Vector();
		Vector VinvInvoice_due_dt = new Vector();
		Vector VinvInvoice_fin_yr = new Vector();
		Vector VinvInvoice_print = new Vector();
		Vector VinvInvoice_cont_cd = new Vector();
		Vector VinvInvoice_supp_cd = new Vector();
		Vector VinvInvoice_bgColor = new Vector();
		Vector VinvChecked_by = new Vector();
		Vector VinvChecked_flag = new Vector();
		Vector VinvApproved_by = new Vector();
		Vector VinvApproved_flag = new Vector();
		
		Vector irn_flag=new Vector();
		String irn_no = "";
		String qr_code = "";
		
		Vector serv_inv_no  = new Vector();
		Vector serv_inv_bill_cycle = new Vector();
		
		Vector View_amount  = new Vector();
		Vector View_invoice_qty = new Vector();
		Vector View_km =  new Vector();
		Vector View_rate =  new Vector();
		Vector View_service_inv_dt =  new Vector();
		Vector View_truck_inv_dt =  new Vector();
		Vector View_truck_inv_no =  new Vector();
		Vector View_truck_nm = new Vector();
		Vector View_calc_bs =  new Vector();
		Vector View_diff_qty =  new Vector();
		Vector View_dr_cr_qty =  new Vector();
		String ls_str = "";
		String chkBoxFlgStr = "";
		String inr_mmbtu_str = "";
		String qty_mmbtu_str = "";
		Vector VPDF_File_Nm = new Vector();
		Map invoice_signded_pdf=new HashMap();
		Map invoice_view_pdf=new HashMap();
		Map invoice_view_signed_pdf=new HashMap();
		Map invoice_mail_sent=new HashMap();
		String inv_flag = "";
		
		Vector dr_cr_doc_no = new Vector();
		Vector dr_cr_flag = new Vector();
		Vector dr_cr_dt = new Vector();
		Vector dr_cr_aprv_by = new Vector();
		Vector dr_cr_aprv_dt = new Vector();
		Vector dr_cr_criteria = new Vector();
		Vector dr_cr_remark = new Vector();
		Vector dr_cr_inv_flag = new Vector();
		Vector dr_cr_qty = new Vector();
		Vector diff_qty = new Vector();
		String drcr_doc_no = "";
		String qty_unit = "",rate_unit = "";
		double tot_dr_cr_qty = 0 ;
		double tot_inv_qty = 0 ;
		double inv_rate = 0;
		double dr_cr_rate = 0;
		double diff_rate = 0;
		double tot_diff_qty = 0 ;
		Vector dr_cr_irn_cnt = new Vector();
		
		Vector inv_tax_perc = new Vector();
		Vector drcr_tax_perc = new Vector();
		Vector diff_tax_perc = new Vector();
		
		Vector inv_tax_amt = new Vector();
		Vector drcr_tax_amt = new Vector();
		Vector diff_tax_amt = new Vector();
		
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
		    			
		    			addColumns();
		    			if(callFlag.equalsIgnoreCase("FETCH_GEN_INVOICE_DTL")){
		    				Supl_Inv_MST();
		    				
		    				if(!year.equals("") && !month.equals("") && !bill_cycle.equals("")) {
		    					createPeriodDate();
		    					fetchGeneratedInvDtl();
		    				}
		    			}
		    			
		    			else if(callFlag.equalsIgnoreCase("FETCH_INVOICE_DTL")){
		    				fetchSupplierDtl();
		    				fetchCustDtl();
//		    				System.out.println("plant_no-----"+plant_no);
		    				if(!plant_no.equals("")) {
		    					createPeriodDate();
		    					fetchContracts();
		    					
		    				}
		    				if(!Smapping_id.equalsIgnoreCase("")) {
		    					fetchInvoiceDtl();
		    				}
						}
		    			else if(callFlag.equalsIgnoreCase("FETCH_INVOICE_PREVIEW_DTL")){
		    				fetchSupMstDtl();
		    				fetchSacDtl();
		    				fetchInvoicePreviewDtl();
		    				fetchTransContDtl();
		    				
		    			}else if(callFlag.equalsIgnoreCase("fetchModifyInvDtl")) {
		    				
		    				fetchSupplierDtl();
		    				fetchCustDtl();
		    				fetchModifyInvoiceDtl();
		    				
		    			}else if(callFlag.equalsIgnoreCase("PREVIEW_DR_CR_NOTE")) {
		    				fetchSupMstDtl();
		    				fetchTransContDtl();
		    				
		    				if(!drcr_doc_no.equalsIgnoreCase("")) {
		    					fetch_dr_cr_dtl();
		    					fetchSacDtl();
		    				}
		    			}
		    			
		    			conn.close();
		    			conn = null;
		    		}
		    	}
		    }
		    catch(Exception e)
		    {
		    	//System.out.println("Exception In : ("+databeanName+") - (init()): "+e.getMessage());
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
						//System.out.println("rset2 is not close "+e);
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
						//System.out.println("stmt2 is not close "+e);
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
						//System.out.println("conn is not close "+e);
					}
				}
		    }
		}
		
		String drcr_dt = "",drcr_due_dt = "";
		String drcr_flag = "";
		String drcr_aprv_by = "";
		String drcr_aprv_dt = "";
		String drcr_criteria = "";
		String drcr_remark = "", drcr_inr_km = "0",drcr_inr_mmbtu = "0",diff_inr_km = "",diff_inr_mmbtu = "",drcr_gross_amt_inr= "0";
		double drcr_tax_rate = 0,drcr_tax_samt = 0,drcr_net_amt_inr = 0;
		String drcr_remark1 = "",drcr_remark2 = "";
		String formated_inv_dt = "";double gross_amt_inr = 0 ;
		private void fetch_dr_cr_dtl() throws SQLException,IOException {
			
			try {
			
				String drcrSql = "select to_char(a.dr_cr_dt,'dd-Mon-yy'),to_char(a.due_dt,'dd-Mon-yy'), "
						+ " a.dr_cr_flag,nvl(a.aprv_by,'0'),to_char(a.aprv_dt, 'dd/mm/yyyy'),"
						+ " a.criteria,a.remark,"
						+ " nvl(dr_cr_inr_km,0),nvl(dr_cr_inr_mmbtu,0),nvl(diff_inr_km,0),nvl(diff_inr_mmbtu,0),"
						+ " nvl(dr_cr_gross_amt_inr,0),nvl(dr_cr_tax_rate,'0'),nvl(tax_remark,'0'),"
						+ " nvl(dr_cr_net_amt_inr,'0'),b.remark_1,b.remark_2,to_char(B.invoice_dt,'dd-Mon-yy'),nvl(B.gross_amt_inr,0)  "
						+ " from DLNG_DR_CR_NOTE A,DLNG_INVOICE_MST B"
						+ " where "
						+ " a.dr_cr_doc_no = '"+drcr_doc_no+"'"
						+ " and B.new_inv_seq_no = '"+new_inv_seq_no+"' "
						+ " and B.invoice_dt = to_date('"+invDt+"','dd/mm/yyyy') "
						+ " and B.HLPL_INV_SEQ_NO = A.HLPL_INV_SEQ_NO  and B.SN_NO = A.SN_NO and B.FGSA_NO = A.FGSA_NO"
						+ " and B.CUSTOMER_CD = A.CUSTOMER_CD and B.FINANCIAL_YEAR = A.FINANCIAL_YEAR "
						+ " and B.PLANT_SEQ_NO = A.PLANT_SEQ_NO"
						+ " and B.CONTRACT_TYPE = A.CONTRACT_TYPE ";
//				System.out.println("drcrSql***"+drcrSql);
				rset = stmt.executeQuery(drcrSql);
				if(rset.next()) {
					
					drcr_dt = rset.getString(1)==null?"":rset.getString(1);
					drcr_due_dt = rset.getString(2)==null?"":rset.getString(2);
					drcr_flag = rset.getString(3)==null?"":rset.getString(3);
					drcr_aprv_by = rset.getString(4)==null?"0":rset.getString(4);
					drcr_aprv_dt = rset.getString(5)==null?"":rset.getString(5);
					drcr_criteria = rset.getString(6)==null?"":rset.getString(6);
					drcr_remark = rset.getString(7)==null?"":rset.getString(7);
					drcr_inr_km = rset.getString(8)==null?"0":rset.getString(8);;
					drcr_inr_mmbtu = rset.getString(9)==null?"0":rset.getString(9);;
					diff_inr_km = rset.getString(10)==null?"":rset.getString(10);
					diff_inr_mmbtu = rset.getString(11)==null?"":rset.getString(11);
					drcr_gross_amt_inr = rset.getString(12)==null?"":rset.getString(12);
					drcr_tax_rate = rset.getDouble(13);
					drcr_tax_samt = rset.getDouble(14);
					drcr_net_amt_inr = rset.getDouble(15);
					drcr_remark1 = rset.getString(16)==null?"":rset.getString(16);
					drcr_remark2 = rset.getString(17)==null?"":rset.getString(17);
					formated_inv_dt  = rset.getString(18)==null?"":rset.getString(18);
					gross_amt_inr = rset.getDouble(19);
				}
				
				
					if(drcr_criteria.contains("DIFF-KM") || drcr_criteria.contains("DIFF-INRKM") ) {
						
						qty_unit = "KM";
						dr_cr_rate = Double.parseDouble(drcr_inr_km+"");
						diff_rate =  Double.parseDouble(diff_inr_km+"");
						
					}else if(drcr_criteria.contains("DIFF-INRMMBTU")) {
						
						dr_cr_rate = Double.parseDouble(drcr_inr_mmbtu+"");
						diff_rate =  Double.parseDouble(diff_inr_mmbtu+"");
					}
					
					//for DIFF-KM & DIFF-LUMPSUM &  DIFF-QTY criteria details
					String attachSql = "SELECT c.TRUCK_NM,to_char(c.TRUCK_INV_DT,'dd/mm/yyyy'),nvl(c.KM,0),nvl(c.RATE,0)"
							+ ",c.truck_inv_no,nvl(c.amount,0),nvl(c.INVOICE_QTY,0),to_char(c.SERVICE_INV_DT,'dd/mm/yyyy'),c.CALC_BASE,"
							+ " nvl(a.diff_qty,0),nvl(a.dr_cr_qty,0)"
							+ " FROM DLNG_SERVICE_INVOICE_ATTACH c,DLNG_DR_CR_SERVICE_DTL a "
							+ " WHERE SERVICE_INVOICE_NO = '"+new_inv_seq_no+"' "
							+ " AND SERVICE_INV_DT = to_date('"+invDt+"','dd/mm/yyyy') "
							+ " and a.dr_cr_doc_no= '"+drcr_doc_no+"'"
							+ " and a.truck_no = c.TRUCK_NM"
							+ " and a.rev_no = (select max(b.rev_no) from DLNG_DR_CR_SERVICE_DTL b where "
							+ "  b.dr_cr_doc_no= a.dr_cr_doc_no"
							+ " and b.TRUCK_NO = a.TRUCK_NO)";
//					System.out.println("attachSql---"+attachSql);
					rset = stmt.executeQuery(attachSql);
					while (rset.next()) {
						inv_rate = rset.getDouble(4);
						View_truck_nm.add(rset.getString(1)==null?"":rset.getString(1));
						View_truck_inv_dt.add(rset.getString(2)==null?"":rset.getString(2));
						tot_inv_qty+=rset.getDouble(3);
						View_truck_inv_no.add(rset.getString(5)==null?"":rset.getString(5));
						View_amount.add(rset.getString(6) == null?"":rset.getString(6));
						View_rate.add(rset.getString(4) == null?"":rset.getString(4));
						View_km.add(rset.getString(3) == null?"":rset.getString(3));
						View_invoice_qty.add(rset.getString(7) == null?"":rset.getString(7));
						View_service_inv_dt.add(rset.getString(8) == null?"":rset.getString(8));
						View_calc_bs.add(rset.getString(9) == null?"":rset.getString(9));
						View_diff_qty.add(rset.getString(10) == null?"":rset.getString(10));
						View_dr_cr_qty.add(rset.getString(11) == null?"":rset.getString(11));
						
						total_qty+=rset.getDouble(10);
						String tempAmt = "0"; 
						if(rset.getString(10).contains(",")) {
							tempAmt = rset.getString(10).replace(",","");
						}else {
							tempAmt = rset.getString(10);
						}
						total_amt+=Double.parseDouble(tempAmt+"");	
					}
					for(int i = 0 ; i < View_truck_nm.size(); i++) {
						
						String rateSql = "select dr_cr_qty,diff_qty from DLNG_DR_CR_SERVICE_DTL a"
								+ " where a.dr_cr_doc_no = '"+drcr_doc_no+"'"
								+ " and a.truck_no = '"+View_truck_nm.elementAt(i)+"'"
								+ " and a.supply_dt = to_date('"+View_truck_inv_dt.elementAt(i)+"','dd/mm/yyyy')"
								+ " and a.rev_no = (select max(b.rev_no) from DLNG_DR_CR_SERVICE_DTL b"
									+ " where b.dr_cr_doc_no = a.dr_cr_doc_no and b.truck_no = a.truck_no and b.supply_dt = a.supply_dt)";
//						System.out.println("rateSql----"+rateSql);
						rset = stmt.executeQuery(rateSql);
						if(rset.next()) {
							
							tot_dr_cr_qty+=rset.getDouble(1);
							tot_diff_qty+=rset.getDouble(2);
							dr_cr_qty.add(rset.getString(1)==null?"":rset.getString(1));
							diff_qty.add(rset.getString(2)==null?"":rset.getString(2));
							
						}else {
							
							dr_cr_qty.add("");
							diff_qty.add("");
						}
					}
				double inr_mmbtu = 0; 
				String invDtlSql = "select tax_cd,nvl(rate_igst,0),nvl(rate_sgst,0),nvl(rate_cgst,0),"
						+ " nvl(igst_amt,0),nvl(cgst_amt,0),nvl(sgst_amt,0),nvl(quantity,0),nvl(rate,0) "
						+ " from dlng_service_invoice_dtl "
						+ " where inv_seq_no = '"+inv_seq_no+"' "
						+ " and eff_dt = to_date('"+invDt+"','dd/mm/yyyy') "
						+ " and MAPPING_ID = '"+mapping_id+"' ";
//				System.out.println("invDtlSql---"+invDtlSql);
				rset = stmt.executeQuery(invDtlSql);
				if (rset.next()) {
					
					String tax_cd = rset.getString(1)==null?"":rset.getString(1);
					double rate_igst = rset.getDouble(2);
					double rate_sgst =  rset.getDouble(3);
					double rate_cgst = rset.getDouble(4);
					double igst_amt = rset.getDouble(5);
					double cgst_amt = rset.getDouble(6);
					double sgst_amt = rset.getDouble(7);
					double inv_qty = rset.getDouble(8);
					inr_mmbtu = rset.getDouble(9);
					tot_inv_qty=inv_qty;
					
					double diff_tax = 0 ;
					
					if(tax_cd.equals("I")) {
						Tax_nm.add("IGST");
						inv_tax_perc.add(nf.format(rate_igst));
						inv_tax_amt.add(nf.format(igst_amt));
						drcr_tax_amt.add(nf.format(drcr_tax_samt));
						
						if(drcr_tax_rate > 0 ) {
							drcr_tax_perc.add(nf.format(drcr_tax_rate));
							
							if(drcr_flag.equalsIgnoreCase("cr")) {
								diff_tax_perc.add(nf.format(rate_igst - drcr_tax_rate));
							}else {
								diff_tax_perc.add(nf.format(drcr_tax_rate - rate_igst));
							}
						}else {
							drcr_tax_perc.add("");
							diff_tax_perc.add("");
						}
						
					}else if(tax_cd.equals("C")) {
						
						Tax_nm.add("SGST");
						Tax_nm.add("CGST");
						
						inv_tax_perc.add(nf.format(rate_sgst));
						inv_tax_perc.add(nf.format(rate_cgst));
						inv_tax_amt.add(nf.format(sgst_amt));
						inv_tax_amt.add(nf.format(cgst_amt));
						
						drcr_tax_amt.add(nf.format(drcr_tax_samt/2));
						drcr_tax_amt.add(nf.format(drcr_tax_samt/2));
						
						if(drcr_tax_rate > 0 ) {
							
							drcr_tax_perc.add(nf.format(drcr_tax_rate/2));
							drcr_tax_perc.add(nf.format(drcr_tax_rate/2));
							
							if(drcr_flag.equalsIgnoreCase("cr")) {
								diff_tax_perc.add(nf.format(rate_sgst - (drcr_tax_rate/2)));
								diff_tax_perc.add(nf.format(rate_cgst - (drcr_tax_rate/2)));
							}else {
								diff_tax_perc.add(nf.format((drcr_tax_rate/2) - rate_sgst));
								diff_tax_perc.add(nf.format((drcr_tax_rate/2) - rate_cgst));
							}
						}else {
							drcr_tax_perc.add("");
							drcr_tax_perc.add("");
							diff_tax_perc.add("");
							diff_tax_perc.add("");
							drcr_tax_amt.add("");
							drcr_tax_amt.add("");
							
						}
					}
				}
				
				if (drcr_criteria.contains("DIFF-INRKM")) { /* for change in INR/KM Criteria */
					
					qty_unit = "(KM)";
					rate_unit = "(INR/KM)";
				}
				if (drcr_criteria.contains("DIFF-LUMPSUM")) { /* for change in INR/KM Criteria */
					
					qty_unit = "(No. of Trucks)";
					rate_unit = "(INR)";
				}
				if (drcr_criteria.contains("DIFF-INRMMBTU")) { /* for change in INR/MMBTU Criteria */
					
					qty_unit = "(MMBtu)";
					rate_unit = "(INR/MMBtu)";
					inv_rate = inr_mmbtu;
				}
				
				irn_no = "";
				qr_code = "";
				//for irn & qrcode
				queryString="SELECT IRN_NO ,SIGN_QR_CODE FROM FMS7_INVOICE_IRN_DTL WHERE "
						+ " CONTRACT_TYPE='V' "
						+ " AND NEW_INV_SEQ_NO='"+drcr_doc_no+"' ";
				rset2=stmt2.executeQuery(queryString);
//				System.out.println("queryString..print.."+queryString);
				if(rset2.next()){
					irn_no=rset2.getString(1)==null?"":rset2.getString(1);
					qr_code=rset2.getString(2)==null?"":rset2.getString(2);
					if(!irn_no.equals("") && (!qr_code.equals(""))){
						irn_flag.add("Y");
					}else{
						irn_flag.add("");
					}
				}else{
					irn_flag.add("");
				}
				
			}catch (Exception e) {
				e.printStackTrace(); 
			}
		}
		public String getQty_unit() {
			return qty_unit;
		}

		public double getTot_dr_cr_qty() {
			return tot_dr_cr_qty;
		}

		private void fetchSupMstDtl() throws SQLException,IOException {
				
				try {
					queryString = "SELECT supplier_name,gst_tin_no,cst_tin_no,TO_CHAR(gst_tin_dt,'DD-MM-YYYY')," +
							  "TO_CHAR(cst_tin_dt,'DD-MM-YYYY'),addl_no,TO_CHAR(addl_issue_dt,'DD-MM-YYYY'),"
							  + "PAN_NO,TO_CHAR(PAN_ISSUE_DT,'DD-MM-YYYY'),"
							  + "GSTIN_NO,TO_CHAR(GSTIN_DT,'DD/MM/YYYY') " +
							  "FROM FMS7_SUPPLIER_MST A " +
							  "WHERE A.supplier_cd=1 AND " +
							  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_SUPPLIER_MST B " +
							  "WHERE A.supplier_cd=B.supplier_cd AND " +
							  "B.eff_dt<=TO_DATE('"+period_start_dt+"','DD/MM/YYYY'))";
//				System.out.println("Supplier Details Fetch Query = "+queryString);
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
					contact_Suppl_PAN_NO = rset.getString(8)==null?"":rset.getString(8);	
					contact_Suppl_PAN_DT = rset.getString(9)==null?"":rset.getString(9);	
					contact_Suppl_GSTIN_NO = rset.getString(10)==null?"":rset.getString(10);	
					contact_Suppl_GSTIN_DT = rset.getString(11)==null?"":rset.getString(11);	
				}
				
				queryString = "SELECT addr,city,pin,NVL(STATE,'N/A') " +
						  "FROM FMS7_SUPPLIER_ADDRESS_MST A " +
						  "WHERE A.supplier_cd=1 AND A.address_type='R' AND " +
						  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_SUPPLIER_ADDRESS_MST B " +
						  "WHERE A.supplier_cd=B.supplier_cd AND B.address_type='R' AND " +
						  "B.eff_dt<=TO_DATE('"+period_start_dt+"','DD/MM/YYYY'))";
//			System.out.println("Supplier Address Fetch Query = "+queryString);
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
			
				if(mapping_id.contains("-")) {
					
					String tempMap [] = mapping_id.split("-");
					selCont_type = tempMap[0];
					agr_no = tempMap[1];
					agr_rev_no = tempMap[2];
					cont_no = tempMap[3];
					cont_rev_no = tempMap[4];
				}
				//System.out.println("selCont_type---"+selCont_type);
				if(selCont_type.equalsIgnoreCase("S")) {
					
					queryString = "SELECT TO_CHAR(signing_dt,'fmddth') " +
							  "|| ' ' || TO_CHAR(signing_dt,'fmMonth') " +
							  "|| ' ' || TO_CHAR(signing_dt,'yyyy') " +
							  "FROM DLNG_FLSA_MST WHERE " +
							  "flsa_no="+agr_no+" AND " +
							  "rev_no="+agr_rev_no+" AND " +
							  "customer_cd="+cust_cd+" " +
							  "ORDER BY rev_no DESC";
						
				}else {
					queryString = "SELECT TO_CHAR(signing_dt,'fmddth') " +
							  "|| ' ' || TO_CHAR(signing_dt,'fmMonth') " +
							  "|| ' ' || TO_CHAR(signing_dt,'yyyy') " +
							  "FROM DLNG_TENDER_MST WHERE " +
							  "tender_no="+agr_no+" AND " +
							  "customer_cd="+cust_cd+"";
				}
				//System.out.println("queryString-----"+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					customer_Invoice_FGSA_Dt = rset.getString(1)==null?"":rset.getString(1);
				}
				
				queryString = "SELECT customer_name,gst_tin_no,cst_tin_no,TO_CHAR(gst_tin_dt,'DD-MM-YYYY')," +
						  "TO_CHAR(cst_tin_dt,'DD-MM-YYYY'),addl_no,TO_CHAR(addl_issue_dt,'DD-MM-YYYY') " +
						  "FROM FMS7_CUSTOMER_MST A " +
						  "WHERE A.customer_cd="+cust_cd+" AND " +
						  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_MST B " +
						  "WHERE A.customer_cd=B.customer_cd AND " +
						  "B.eff_dt<=TO_DATE('"+period_start_dt+"','DD/MM/YYYY'))";
						////System.out.println("Customer Details Fetch Query = "+queryString);
						rset = stmt.executeQuery(queryString);
						if(rset.next())
						{
							contact_Customer_Name = rset.getString(1)==null?"":rset.getString(1);
						}
						
						if(!invFlag.equalsIgnoreCase("VIEW")) {
							fetchInvContactPerson();
						}
						if(contact_addr_flag.trim().equalsIgnoreCase("R") || contact_addr_flag.trim().equalsIgnoreCase("C") || contact_addr_flag.trim().equalsIgnoreCase("B"))
							{
								queryString = "SELECT addr,city,pin,state " +
										  "FROM FMS7_CUSTOMER_ADDRESS_MST A " +
										  "WHERE A.customer_cd="+cust_cd+" AND A.address_type='"+contact_addr_flag+"' AND " +
										  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_ADDRESS_MST B " +
										  "WHERE A.customer_cd=B.customer_cd AND B.address_type='"+contact_addr_flag+"' AND " +
										  "B.eff_dt<=TO_DATE('"+period_start_dt+"','DD/MM/YYYY'))";
		//					System.out.println("Hiren***Customer Address Fetch Query = "+queryString);
							rset = stmt.executeQuery(queryString);
							if(rset.next())
							{
								contact_Customer_Person_Address = rset.getString(1)==null?"":rset.getString(1);
								contact_Customer_Person_City = rset.getString(2)==null?"":rset.getString(2);
								contact_Customer_Person_Pin = rset.getString(3)==null?"":rset.getString(3);
								contact_Customer_Person_State = rset.getString(4)==null?"":rset.getString(4);
							}
							
							queryString = "SELECT PLANT_STATE FROM FMS7_CUSTOMER_PLANT_DTL WHERE CUSTOMER_CD='"+cust_cd+"' "
									+ "AND SEQ_NO = '"+plant_no+"' AND EFF_DT = (SELECT MAX(EFF_DT) FROM FMS7_CUSTOMER_PLANT_DTL B "
									+ "WHERE B.SEQ_NO='"+plant_no+"' AND B.CUSTOMER_CD='"+cust_cd+"' AND B.EFF_DT <= TO_DATE('"+period_start_dt+"','DD/MM/YYYY'))";
							//System.out.println("Fetchinng State-8888-"+queryString);
							rset = stmt.executeQuery(queryString);
							if(rset.next()) {
								contact_Customer_Plant_State = rset.getString(1)==null?"":rset.getString(1);
							} else {
								contact_Customer_Plant_State = "";
							}
						}else{
							
							String new_plant_no="";
							if(!contact_addr_flag.equalsIgnoreCase(""))
							new_plant_no = contact_addr_flag.trim().substring(1);
							
							if(new_plant_no.length()>=1)
							{
								queryString = "SELECT plant_addr,plant_city,plant_pin,PLANT_STATE " +
											  "FROM FMS7_CUSTOMER_PLANT_DTL A " +
											  "WHERE A.customer_cd="+cust_cd+" AND A.seq_no='"+new_plant_no+"' AND " +
											  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
											  "WHERE A.customer_cd=B.customer_cd AND B.seq_no='"+new_plant_no+"' AND " +
											  "B.eff_dt<=TO_DATE('"+period_start_dt+"','DD/MM/YYYY'))";
								
								queryString1 = "SELECT PLANT_STATE FROM FMS7_CUSTOMER_PLANT_DTL WHERE CUSTOMER_CD='"+cust_cd+"' "
										+ "AND SEQ_NO = '"+new_plant_no+"' AND EFF_DT = (SELECT MAX(EFF_DT) FROM FMS7_CUSTOMER_PLANT_DTL B "
										+ "WHERE B.SEQ_NO='"+new_plant_no+"' AND B.CUSTOMER_CD='"+cust_cd+"' AND B.EFF_DT <= TO_DATE('"+period_start_dt+"','DD/MM/YYYY'))";
							}
							else
							{
								queryString = "SELECT plant_addr,plant_city,plant_pin,PLANT_STATE " +
											  "FROM FMS7_CUSTOMER_PLANT_DTL A " +
											  "WHERE A.customer_cd='"+cust_cd+"' AND A.seq_no='"+plant_no+"' AND " +
											  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
											  "WHERE A.customer_cd=B.customer_cd AND B.seq_no='"+plant_no+"' AND " +
											  "B.eff_dt<=TO_DATE('"+period_start_dt+"','DD/MM/YYYY'))";
								
								queryString1 = "SELECT PLANT_STATE FROM FMS7_CUSTOMER_PLANT_DTL WHERE CUSTOMER_CD='"+cust_cd+"' "
										+ "AND SEQ_NO = '"+plant_no+"' AND EFF_DT = (SELECT MAX(EFF_DT) FROM FMS7_CUSTOMER_PLANT_DTL B "
										+ "WHERE B.SEQ_NO='"+plant_no+"' AND B.CUSTOMER_CD='"+cust_cd+"' AND B.EFF_DT <= TO_DATE('"+period_start_dt+"','DD/MM/YYYY'))";
							}
							
							//System.out.println("STEP-1A.6:FMS7_CUSTOMER_PLANT_DTL: "+queryString);
							rset = stmt.executeQuery(queryString);
							if(rset.next())
							{
								contact_Customer_Person_Address = rset.getString(1)==null?"":rset.getString(1);
								contact_Customer_Person_City = rset.getString(2)==null?"":rset.getString(2);
								contact_Customer_Person_Pin = rset.getString(3)==null?"":rset.getString(3);
								contact_Customer_Person_State = rset.getString(4)==null?"":rset.getString(4);
							}
							queryString1 = "SELECT PLANT_STATE FROM FMS7_CUSTOMER_PLANT_DTL WHERE CUSTOMER_CD='"+cust_cd+"' "
									+ "AND SEQ_NO = '"+plant_no+"' AND EFF_DT = (SELECT MAX(EFF_DT) FROM FMS7_CUSTOMER_PLANT_DTL B "
									+ "WHERE B.SEQ_NO='"+plant_no+"' AND B.CUSTOMER_CD='"+cust_cd+"' AND B.EFF_DT <= TO_DATE('"+period_start_dt+"','DD/MM/YYYY'))";
							//System.out.println("Fetchinng State-8888-"+queryString);
//							rset = stmt.executeQuery(queryString);
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
						queryString = "SELECT A.stat_no, TO_CHAR(A.eff_dt,'DD-MM-YYYY'), B.stat_nm, B.stat_cd " +
								  "FROM FMS7_CUSTOMER_PLANT_TAX_CDS A, FMS7_GOVT_STAT_NO B " +
								  "WHERE A.stat_cd=B.stat_cd AND A.customer_cd="+cust_cd+" AND " +
								  "A.plant_seq_no="+plant_no+" AND (B.stat_type='S' OR B.stat_type='G') " +
								  "ORDER BY A.stat_cd";
//					System.out.println("Customer Plant's Tax Names Details Fetch Query = "+queryString);
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
				}catch (Exception e) {
					e.printStackTrace();
				}
		}

		private String signing_dt = "";
		private String trans_cont_no = "";
		private void fetchTransContDtl()throws SQLException,IOException {
			try {
				
				if(mapping_id.contains("-")) {
					
					String tempMap [] = mapping_id.split("-");
					String cont_typ = tempMap[0];
					String agr_no = tempMap[1];
					String agr_rev_no = tempMap[2];
					String cont_no = tempMap[3];
					
					String transDtlSql = " SELECT TO_CHAR(SIGNING_DT,'fmddth') " +
							  "|| ' ' || TO_CHAR(signing_dt,'fmMonth') " +
							  "|| ' ' || TO_CHAR(signing_dt,'yyyy') " 
							+ ",TRANS_CONT_NO FROM  DLNG_SALES_TRANSPORTER_MST"
							+ " WHERE CUSTOMER_CD = '"+cust_cd+"' AND CONTRACT_TYPE = '"+cont_typ+"'"
							+ " AND AGREEMENT_NO = '"+agr_no+"' AND AGREEMENT_REV_NO = '"+agr_rev_no+"'"
							+ " AND  CONTRACT_NO = '"+cont_no+"' "
							+ " AND CONTRACT_REV_NO = (SELECT MAX(CONTRACT_REV_NO) FROM DLNG_SALES_TRANSPORTER_MST"
								+ " WHERE CUSTOMER_CD = '"+cust_cd+"' AND CONTRACT_TYPE = '"+cont_typ+"'"
								+ " AND AGREEMENT_NO = '"+agr_no+"' AND AGREEMENT_REV_NO = '"+agr_rev_no+"'"
								+ " AND  CONTRACT_NO = '"+cont_no+"' )";
					
//					System.out.println("transDtlSql---"+transDtlSql);
					rset = stmt.executeQuery(transDtlSql);
					if(rset.next()) {
						
						signing_dt = rset.getString(1)== null ?"":rset.getString(1);
						trans_cont_no = rset.getString(2)== null ?"":rset.getString(2);
					}
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}

		private void fetchModifyInvoiceDtl()throws SQLException,IOException {

			try {
			/* for modify invoice */
			String mappnig_id[]=null;
			if(Smapping_id.contains("-")) {
				
				mappnig_id = Smapping_id.split("-");
				
				if(!period_start_dt.equals("") && !period_end_dt.equals("")) {
//					for SN
					if(mappnig_id[0].equals("S")) {
						
						String snSql = "SELECT to_char(start_dt,'dd/mm/yyyy'),to_char(end_dt,'dd/mm/yyyy') "
							+ " FROM DLNG_SN_MST A WHERE "
							+ " CUSTOMER_CD='"+cust_cd+"'"
							+ " AND A.FLSA_NO='"+mappnig_id[1]+"'"
							+ " AND A.FLSA_REV_NO='"+mappnig_id[2]+"'"
							+ " AND A.SN_NO = '"+mappnig_id[3]+"'"
							+ " AND A.SN_REV_NO = (SELECT MAX(B.SN_REV_NO) FROM DLNG_SN_MST B "
							+ " 	WHERE  B.CUSTOMER_CD='"+cust_cd+"'"
								+ " AND B.FLSA_NO='"+mappnig_id[1]+"'"
								+ " AND B.FLSA_REV_NO='"+mappnig_id[2]+"'"
								+ " AND B.SN_NO = '"+mappnig_id[3]+"'"
								+ " AND B.status='Y' "
								+ " AND B.SN_BASE = 'D') "
						+ " AND A.SN_BASE = 'D' "
						+ " AND A.STATUS='Y' ";
						
//						System.out.println("Fetching SN COntracts.."+snSql);
						rset = stmt.executeQuery(snSql);
					
					} else if (mappnig_id[0].equals("L")) {
						
						String LoASql = "SELECT to_char(start_dt,'dd/mm/yyyy'),to_char(end_dt,'dd/mm/yyyy') "
								+ " FROM DLNG_LOA_MST A WHERE "
								+ " CUSTOMER_CD='"+cust_cd+"'"
								+ " AND A.TENDER_NO='"+mappnig_id[1]+"'"
								+ " AND A.LOA_NO = '"+mappnig_id[3]+"'"
								+ " AND A.LOA_REV_NO = (SELECT MAX(B.LOA_REV_NO) FROM DLNG_LOA_MST B "
								+ " 	WHERE  B.CUSTOMER_CD='"+cust_cd+"'"
									+ " AND B.TENDER_NO='"+mappnig_id[1]+"'"
									+ " AND B.LOA_NO = '"+mappnig_id[3]+"'"
									+ " AND B.status='Y' "
									+ " AND B.LOA_BASE = 'D' AND B.FCC_FLAG='Y') "
							+ " AND A.LOA_BASE = 'D' "
							+ " AND A.STATUS='Y' "
							+ " AND A.FCC_FLAG='Y' ";
//						System.out.println("LoA Contract****"+LoASql);
						rset = stmt.executeQuery(LoASql);
					} 
					while (rset.next()) {
						
						 cont_start_dt = rset.getString(1) == null?"":rset.getString(1);
						 cont_end_dt = rset.getString(2) == null?"":rset.getString(2);
					}
				}
				String transChargeSql = "select nvl(INR_KM,0),nvl(INR_MMBTU,0),nvl(LUMPSUM_FLAG,'N') from DLNG_SN_PLANT_MST"
				 		+ " where CUSTOMER_CD = '"+cust_cd+"' "
				 		+ " and FLSA_NO='"+mappnig_id[1]+"'"
				 		+ " and FLSA_REV_NO='"+mappnig_id[2]+"'"
				 		+ " and SN_NO ='"+mappnig_id[3]+"' "
				 		+ " and SN_REV_NO = '"+mappnig_id[4]+"' "
				 		+ " and PLANT_SEQ_NO = '"+plant_no+"' ";
//				 System.out.println("transChargeSql--SN--"+transChargeSql);
				 rset1 = stmt1.executeQuery(transChargeSql);
				 if (rset1.next()) {
					 inr_km = rset1.getString(1)==null?"0":rset1.getString(1);
					 inr_mmbtu = rset1.getString(2)==null?"0":rset1.getString(2);
					 lumpsumFlg = rset1.getString(3)==null?"N":rset1.getString(3);
				}
				
				if(refreshFlg.equalsIgnoreCase("N")) {
					 /* for calcBase */
						String attachSql = "SELECT CALC_BASE FROM DLNG_SERVICE_INVOICE_ATTACH WHERE "
								+ " SERVICE_INVOICE_NO = '"+new_inv_seq_no+"' ";
//						System.out.println("attachSql----"+attachSql);
						rset1 = stmt1.executeQuery(attachSql);
						if(rset1.next()) {
							calcBase = rset1.getString(1)==null?"0":rset1.getString(1);
						}
						
						/* for tax structure code */
						String taxCdSql = "SELECT TAX_STRUCT_CD,REMARK_1,REMARK_2 FROM DLNG_INVOICE_MST WHERE NEW_INV_SEQ_NO = '"+new_inv_seq_no+"'";
//						System.out.println("taxCdSql--"+taxCdSql);
						rset1 = stmt1.executeQuery(taxCdSql);
						if(rset1.next()) {
							tax_structure = rset1.getString(1)==null?"0":rset1.getString(1);
						}
						
						/* for service invoice line items */
						int rowNo=0;
						String lineSql = "SELECT SAC_CODE,ITEM_DESCRIPTION,QUANTITY,RATE,CARGO_AMOUNT FROM DLNG_SERVICE_INVOICE_DTL"
								+ " WHERE SUPPLIER_CD = '"+supp_cd+"' AND FINANCIAL_YEAR = '"+fin_yr+"'"
								+ " AND INV_SEQ_NO = '"+inv_seq_no+"' AND CONTRACT_TYPE = '"+contract_type+"'";
//						System.out.println("lineSql----"+lineSql);
						rset1 = stmt1.executeQuery(lineSql);
						while(rset1.next()) {
							rowNo++;
							sacStr+=rset1.getString(1)==null?"":rset1.getString(1)+"@@";
							itemStr+=rset1.getString(2)==null?"":rset1.getString(2)+"@@";
							qtyStr+=rset1.getString(3)==null?"":rset1.getString(3)+"@@";
							rateStr+=rset1.getString(4)==null?"":rset1.getString(4)+"@@";
							amtStr+=rset1.getString(5)==null?"":rset1.getString(5)+"@@";
						}
						rowno = rowNo+"";
					}
				
					String taxCdSql = "SELECT REMARK_1,REMARK_2 FROM DLNG_INVOICE_MST WHERE NEW_INV_SEQ_NO = '"+new_inv_seq_no+"'";
//					System.out.println("taxCdSql--"+taxCdSql);
					rset1 = stmt1.executeQuery(taxCdSql);
					if(rset1.next()) {
						remark1 = rset1.getString(1)==null?"":rset1.getString(1);
						remark2 = rset1.getString(2)==null?"":rset1.getString(2);
					}
					/*fetching tax structure*/
					queryString="SELECT TYPE FROM STATE_MST WHERE STATE_CODE='"+plant_state_cd+"'";
	//				System.out.println("queryString--"+queryString);
					rset=stmt.executeQuery(queryString);
					if(rset.next()){
						app_tax_flag=rset.getString(1)==null?"":rset.getString(1);
					}
//					System.out.println("supp_state_cd--"+supp_state_cd+"**plant_state_cd--"+plant_state_cd);
					if(app_tax_flag.trim().equalsIgnoreCase("S")){
						if(!supp_state_cd.equals(plant_state_cd))
						{
							Tax_nm.add("IGST");
	//						taxstr.add("18.0");
							double rate_gst=0;
							queryString="SELECT RATE FROM GST_CODE_MST WHERE ABBR LIKE '%IGST' and GST_CODE!='IGSTEX' order by rate ";
							rset=stmt.executeQuery(queryString);
							while(rset.next()){
								GST_RT.add(rset.getString(1)==null?"":rset.getString(1));
								rate_gst=Double.parseDouble(rset.getString(1));
								SGST_CGST_RT.add(rate_gst);
								tax_struct="IGST-"+rate_gst+"%";
								tax_desc.add(tax_struct);
							}
							
						}else if(supp_state_cd.equals(plant_state_cd))
						{
							
							Tax_nm.add("CGST");
							Tax_nm.add("SGST");
							taxstr.add("9.0");
							taxstr.add("9.0");
							double rate_gst=0;
							queryString="SELECT RATE FROM GST_CODE_MST WHERE ABBR LIKE '%SGST'  and GST_CODE!='SGSTEX'  order by rate ";
							rset=stmt.executeQuery(queryString);
							while(rset.next()){
								GST_RT.add(rset.getString(1)==null?"":rset.getString(1));
								rate_gst=Double.parseDouble(rset.getString(1))/2;
								SGST_CGST_RT.add(rate_gst);
								tax_struct="CGST,SGST-"+rate_gst+"%";
								tax_desc.add(tax_struct);
							}
						}
					}else if(app_tax_flag.trim().equalsIgnoreCase("U")){
						Tax_nm.add("UTGST");
						taxstr.add("18.0");
						double rate_gst=0;
						queryString="SELECT RATE FROM GST_CODE_MST WHERE ABBR LIKE '%UTGST' and GST_CODE!='UTGSTEX' order by rate ";
						rset=stmt.executeQuery(queryString);
						while(rset.next()){
							GST_RT.add(rset.getString(1)==null?"":rset.getString(1));
							rate_gst=Double.parseDouble(rset.getString(1));
							SGST_CGST_RT.add(rate_gst);
							tax_struct="UTGST-"+rate_gst+"%";
							tax_desc.add(tax_struct);
						}
					}else{
						Tax_nm.add("IGST");
						taxstr.add("18.0");
						double rate_gst=0;
						queryString="SELECT RATE FROM GST_CODE_MST WHERE ABBR LIKE '%IGST' and GST_CODE!='IGSTEX' order by rate ";
						rset=stmt.executeQuery(queryString);
						while(rset.next()){
							GST_RT.add(rset.getString(1)==null?"":rset.getString(1));
							rate_gst=Double.parseDouble(rset.getString(1));
							SGST_CGST_RT.add(rate_gst);
							tax_struct="IGST-"+rate_gst+"%";
							tax_desc.add(tax_struct);
						}
					}
					
					//Fetching sales/loa invoice details
					String amt_inr_mmbtu = "0";
					double total_ls = 0,total_km = 0;
					String invDtl = "SELECT NEW_INV_SEQ_NO,to_char(INVOICE_DT,'DD/MM/YYYY'),nvl(TOTAL_QTY,0),TRUCK_ID "
							+ " FROM DLNG_INVOICE_MST WHERE"
							+ " CUSTOMER_CD = '"+cust_cd+"' "
							+ " AND INVOICE_DT between to_date('"+period_start_dt+"','dd/mm/yyyy') and to_date('"+period_end_dt+"','dd/mm/yyyy') "
							+ " AND CONTRACT_TYPE= '"+mappnig_id[0]+"' "
							+ " AND APPROVED_FLAG = 'Y' "
							+ " and FGSA_NO = '"+mappnig_id[1]+"'"
							+ " and FGSA_REV_NO = '"+mappnig_id[2]+"'"
							+ " and SN_NO = '"+mappnig_id[3]+"'"
//							+ " and SN_REV_NO = '"+mappnig_id[4]+"'"
							+ " AND PLANT_SEQ_NO='"+plant_no+"' order by  INVOICE_DT";
//					System.out.println("invDtl===="+invDtl);
					rset = stmt.executeQuery(invDtl);
					while (rset.next()) {
						
						VInv_new_inv_seq_no.add(rset.getString(1)==null?"":rset.getString(1));
						VInv_invoice_dt.add(rset.getString(2)==null?"":rset.getString(2));
						VInv_total_qty.add(rset.getString(3)==null?"":rset.getString(3));
//						total_qty_mmbtu+=Double.parseDouble(rset.getString(3)+"");
						
						amt_inr_mmbtu = "0";
						if(!inr_mmbtu.equals("0")) {
							amt_inr_mmbtu = nf5.format(Double.parseDouble(rset.getString(3)+"") * Double.parseDouble(inr_mmbtu+""));
//							total_qty_inr+=Double.parseDouble(rset.getString(3)+"") * Double.parseDouble(inr_mmbtu+"");
						}
						
						Vinv_qty_inr.add(amt_inr_mmbtu);
						
						String truckNmSql = "SELECT TRUCK_NM from DLNG_TANK_TRUCK_MST where TRUCK_ID = '"+rset.getString(4)+"'";
						rset1 = stmt1.executeQuery(truckNmSql);
						if(rset1.next()) {
							Vinv_truck_nm.add(rset1.getString(1)==null?"":rset1.getString(1));
						}else {
							Vinv_truck_nm.add("");
						}
						
						if(refreshFlg.equalsIgnoreCase("N")) {
							/*fetching invoice attachement data*/
							String attachSqlDtl = "SELECT KM,nvl(AMOUNT,0),nvl(INVOICE_QTY,0) FROM DLNG_SERVICE_INVOICE_ATTACH WHERE "
									+ " SERVICE_INVOICE_NO = '"+new_inv_seq_no+"' and TRUCK_INV_NO = '"+rset.getString(1)+"' ";
//							System.out.println("attachSqlDtl----"+attachSqlDtl);
							rset1 = stmt1.executeQuery(attachSqlDtl);
							if (rset1.next()) {
								if(calcBase.equals("2") ) {
									
									km_str+= rset1.getString(1)==null?"":rset1.getString(1)+"@@";
									inr_km_str+= rset1.getString(2)==null?"":rset1.getString(2)+"@@";
									total_km+=rset1.getDouble(2);
									total_km_inr=total_km+"";
									
								}else if(calcBase.equals("3") ) {
									
									ls_str+=rset1.getString(2)==null?"":rset1.getString(2)+"@@";
									total_ls+=rset1.getDouble(2);
									total_ls_inr = total_ls+"";
									
								}else if(calcBase.equals("1")) {
									
									inr_mmbtu_str+= rset1.getString(2)==null?"0":rset1.getString(2)+"@@";
									qty_mmbtu_str+= rset1.getString(3)==null?"0":rset1.getString(3)+"@@";
								}
								chkBoxFlgStr+="Y@@";
							}else {
								if(calcBase.equals("2") ) {
									km_str+= "0@@";
									inr_km_str+= "0@@";
								}else if(calcBase.equals("3") ) {
									ls_str+="0@@";
								}
								chkBoxFlgStr+="N@@";
							}
						}
					}
//					System.out.println("km_str----"+km_str);
					if(km_str.contains("@@")) {
						
						String temp1[] = km_str.split("@@");
						String temp2[] = inr_km_str.split("@@");
						for(int i = 0 ; i < temp1.length ; i++) {
							Vinv_km.add(temp1[i]);
							Vinv_km_inr.add(temp2[i]);
							total_qty_km+=Double.parseDouble(temp1[i]+"");
						}
					}else {
						for(int i = 0 ; i < VInv_new_inv_seq_no.size(); i++ ) {
							Vinv_km.add("");
							Vinv_km_inr.add("");
						}
					}
//					System.out.println("ls_str----"+ls_str);
					if(ls_str.contains("@@")) {
						String temp1[] = ls_str.split("@@");
						for(int i = 0 ; i < temp1.length ; i++) {
							Vinv_lumpSum.add(temp1[i]);
						}
					}else {
						for(int i = 0 ; i < VInv_new_inv_seq_no.size(); i++ ) {
							Vinv_lumpSum.add("");
						}
					}
					
					if(inr_mmbtu_str.contains("@@")) {
						
						String temp1[] = qty_mmbtu_str.split("@@");
						String temp2[] = inr_mmbtu_str.split("@@");
						for(int i = 0 ; i < temp1.length ; i++) {
							total_qty_mmbtu+=Double.parseDouble(temp1[i]+"");
							
							String temp_qty_inr = temp2[i].toString().replace(",","");
							
							total_qty_inr+=Double.parseDouble(temp_qty_inr+"");
						}
					}

					if(chkBoxFlgStr.contains("@@")) {
						String tempChk []=chkBoxFlgStr.split("@@");
						for(int i = 0 ; i < tempChk.length ; i++) {
							Vinv_chkBox_Flg.add(tempChk[i]);
						}
					}else {
						for(int i = 0 ; i < VInv_new_inv_seq_no.size(); i++ ) {
							Vinv_chkBox_Flg.add("");
						}
					}
					//for line items 
//					System.out.println("sacStr----"+sacStr);
					if(sacStr.contains("@@")) {
						
						String tempSac [] = sacStr.split("@@");
						String tempItem [] = itemStr.split("@@");
						String tempQty [] = qtyStr.split("@@");
						String tempRate [] = rateStr.split("@@");
						String tempAmt [] = amtStr.split("@@");
						
						for(int i = 0; i < Integer.parseInt(rowno+"") ; i++) {
//							System.out.println("tempSac[i]----"+tempSac[i]);
							String sac_cd = tempSac[i];
							if(sac_cd.equals("-")) {
								sac_cd = "";
							}
							String item_desc = tempItem[i];
							if(item_desc.equals("-")) {
								item_desc = "";
							}
							String qty = tempQty[i];
							if(qty.equals("-")) {
								qty = "0";
							}
//							System.out.println("qty----"+qty);
							String rate = tempRate[i];
							if(rate.equals("-")) {
								rate = "0";
							}
							String amt = tempAmt[i];
							if(amt.equals("-")) {
								amt = "0";
							}
							if( i == 0) { // for invoice line item row
								if(calcBase.equals("1")) {
									
//									Vsac_cd.add("998599");
									Vqty.add(nf5.format(total_qty_mmbtu));
									Vrate.add(inr_mmbtu);
									Vamt.add(nf.format(total_qty_inr));
									inv_gross_amt+=total_qty_inr;
									total_qty = total_qty_mmbtu;
								}else if(calcBase.equals("2")) {
									
//									Vsac_cd.add("998599");
									Vqty.add(nf.format(total_qty_km));
									Vrate.add(inr_km);
									Vamt.add(nf.format(Double.parseDouble(total_km_inr+"")));
									inv_gross_amt+=Double.parseDouble(total_km_inr+"");
									total_qty = total_qty_km;
									
								}else if(calcBase.equals("3")) {
//									Vsac_cd.add("998599");
									Vqty.add("1");
									Vrate.add(total_ls_inr);
									Vamt.add(nf.format(Double.parseDouble(total_ls_inr+"")));
									inv_gross_amt+=Double.parseDouble(total_ls_inr+"");
//									total_qty = Double.parseDouble(+"";
								}else {
//									Vsac_cd.add("998599");
									Vqty.add(qty);
									Vrate.add(rate);
									Vamt.add(amt);
								}
								fetchSacDtl();
							}else {
//								Vsac_cd.add(sac_cd);
								Vqty.add(qty);
								Vrate.add(rate);
								Vamt.add(amt);
								inv_gross_amt+=Double.parseDouble(amt+"");
								total_qty+=Double.parseDouble(qty+"");
							}
							Vitem_desc.add(item_desc);
							Vsac_cd.add(sac_code);
						}
					}else {
						Vsac_cd.add("");
						Vitem_desc.add("");
						Vqty.add("");
						Vrate.add("");
						Vamt.add("");
//						System.out.println("total_qty_mmbtu===="+total_qty_mmbtu);
						if(calcBase.equals("1")) {
							
							total_qty=Double.parseDouble(total_qty_mmbtu+"");
						}else if(calcBase.equals("2")) {
							total_qty=Double.parseDouble(total_qty_km+"");
						}
					}
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}

		private void addColumns()throws SQLException,IOException {
			
			try
			{
				int count=0;
				String query="SELECT COUNT(COLUMN_NAME) FROM COLS WHERE UPPER(TABLE_NAME) LIKE 'DLNG_INVOICE_MST' "
						+ "AND UPPER(COLUMN_NAME) LIKE 'BILLING_CYCLE' ";
				rset=stmt.executeQuery(query);
				if(rset.next())
				{
					count=rset.getInt(1);
				}
				if(count==0)
				{
					query="alter table DLNG_INVOICE_MST add BILLING_CYCLE NUMBER(1,0)";
					stmt.executeUpdate(query);
					conn.commit();
				}
				
				count=0;
				query="SELECT COUNT(COLUMN_NAME) FROM COLS WHERE UPPER(TABLE_NAME) LIKE 'DLNG_INVOICE_MST' "
						+ "AND UPPER(COLUMN_NAME) LIKE 'INR_BASE' ";
				rset=stmt.executeQuery(query);
				if(rset.next())
				{
					count=rset.getInt(1);
				}
				if(count==0)
				{
					query="alter table DLNG_INVOICE_MST add INR_BASE CHAR(1 BYTE)";
					stmt.executeUpdate(query);
					conn.commit();
				}		
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}

		private void createPeriodDate()throws SQLException,IOException {
			try {
//			System.out.println("bill_cycle----"+bill_cycle);
			if(bill_cycle.equals("2") || bill_cycle.equals("1")) //1st-Fortnightly or 2nd-Fortnightly Invoice ...
			{
				if(bill_cycle.equals("2")) //2nd-Fortnightly Invoice ...
				{
					period_start_dt = "16"+"/"+month+"/"+year;
				}
				else  //1st-Fortnightly Invoice ...
				{
					period_start_dt = "01"+"/"+month+"/"+year;
				}
				
				if(bill_cycle.equals("1"))  //1st-Fortnightly Invoice ...
				{
					period_end_dt = "15"+"/"+month+"/"+year;
				}
				else  //2nd-Fortnightly Invoice ...
				{
					queryString = "SELECT TO_CHAR(LAST_DAY(TO_DATE('"+month+"/"+year+"','MM/YYYY')),'DD/MM/YYYY') FROM DUAL";
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						period_end_dt = rset.getString(1);
					}
				}
			}
			else if(bill_cycle.equals("3")) //1st-Weekly Invoice ...
			{
				period_start_dt = "01"+"/"+month+"/"+year;
				period_end_dt = "07"+"/"+month+"/"+year;
			}
			else if(bill_cycle.equals("4")) //2nd-Weekly Invoice ...
			{
				period_start_dt = "08"+"/"+month+"/"+year;
				period_end_dt = "14"+"/"+month+"/"+year;
			}
			else if(bill_cycle.equals("5")) //3rd-Weekly Invoice ...
			{
				period_start_dt = "15"+"/"+month+"/"+year;
				period_end_dt = "21"+"/"+month+"/"+year;
			}
			else if(bill_cycle.equals("6")) //4th-Weekly Invoice ...
			{
				period_start_dt = "22"+"/"+month+"/"+year;
				period_end_dt = "28"+"/"+month+"/"+year;
			}
			else if(bill_cycle.equals("9")) //5th-Weekly Invoice ...
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
			else if(bill_cycle.equals("7"))  //Monthly Invoice ...
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
			
			if(!period_start_dt.equals("") && !period_end_dt.equals("")) {
				
				String checkPeriodSql = "select * from DUAL "
						+ " where "
						+ " SYSDATE BETWEEN to_date('"+period_start_dt+"','dd/mm/yyyy') and to_date('"+period_end_dt+"','dd/mm/yyyy')"
						+ " OR SYSDATE <= to_date('"+period_start_dt+"','dd/mm/yyyy')";
				rset = stmt.executeQuery(checkPeriodSql);
//				System.out.println("checkPeriodSql----"+checkPeriodSql);
				if(rset.next()) {
					alwInvGen = "N";
				}else {
					alwInvGen = "Y";
				}
			}
			
			}catch (Exception e) {
				e.printStackTrace();
			}
		}

		private void fetchGeneratedInvDtl()throws SQLException,IOException {
			try {
				
				String invSql= "SELECT CUSTOMER_CD,MAPPING_ID,PLANT_SEQ_NO,NEW_INV_SEQ_NO,HLPL_INV_SEQ_NO,"
						+ " to_char(INVOICE_DT,'dd/mm/yyyy'),CONTRACT_TYPE,to_char(DUE_DT,'dd/mm/yyyy'),"
						+ " FINANCIAL_YEAR,PDF_INV_DTL,to_char(PERIOD_START_DT,'dd/mm/yyyy'),to_char(PERIOD_END_DT,'dd/mm/yyyy'),"
						+ " CONTACT_PERSON_CD,SUPPLIER_CD,CHECKED_BY,CHECKED_FLAG,APPROVED_BY,APPROVED_FLAG"
						+ " From DLNG_INVOICE_MST where"
//						+ " INVOICE_DT between to_date('"+period_start_dt+"','dd/mm/yyyy') and to_date('"+period_end_dt+"','dd/mm/yyyy') "
						+ " PERIOD_START_DT = to_date('"+period_start_dt+"','dd/mm/yyyy')"
						+ " AND PERIOD_END_DT = to_date('"+period_end_dt+"','dd/mm/yyyy') "
						+ " AND CONTRACT_TYPE = 'V' ORDER BY HLPL_INV_SEQ_NO desc ";
//				System.out.println("invSql----"+invSql);
				rset = stmt.executeQuery(invSql);
				while (rset.next()) {
					
					VinvCustomer_cd.add(rset.getString(1) == null ?"":rset.getString(1));
					VinvMapping_id.add(rset.getString(2) == null ?"":rset.getString(2));
					VinvPlant_seq_no.add(rset.getString(3) == null ?"":rset.getString(3));
					VinvNew_inv_seq_no.add(rset.getString(4) == null ?"":rset.getString(4));
					VinvInv_seq_no.add(rset.getString(5) == null ?"":rset.getString(5));
					String inv_seq_no = rset.getString(5) == null ?"":rset.getString(5);
					VinvInvoice_dt.add(rset.getString(6) == null ?"":rset.getString(6));
					String invoice_dt = rset.getString(6) == null ?"":rset.getString(6);
					VinvContType.add(rset.getString(7) == null ?"":rset.getString(7));
					String cont_type = rset.getString(7) == null ?"":rset.getString(7);
					VinvInvoice_due_dt.add(rset.getString(8) == null ?"":rset.getString(8));
					VinvInvoice_fin_yr.add(rset.getString(9) == null ?"":rset.getString(9));
					VinvInvoice_print.add(rset.getString(10) == null ?"":rset.getString(10));
					String print_copy = rset.getString(10) == null ?"":rset.getString(10);
					
					String period_st_dt = rset.getString(11) == null ?"":rset.getString(11);
					String period_end_dt = rset.getString(12) == null ?"":rset.getString(12);
					VinvInvoice_cont_cd.add(rset.getString(13) == null ?"":rset.getString(13));
					VinvInvoice_supp_cd.add(rset.getString(14) == null ?"":rset.getString(14));
					VinvChecked_by.add(rset.getString(15) == null ?"":rset.getString(15));
					VinvChecked_flag.add(rset.getString(16) == null ?"":rset.getString(16));
					VinvApproved_by.add(rset.getString(17) == null ?"":rset.getString(17));
					VinvApproved_flag.add(rset.getString(18) == null ?"":rset.getString(18));
					
					String customer_abbr = "",cust_plant_nm = "";
					
					String queryString3 = "SELECT customer_abbr FROM FMS7_CUSTOMER_MST WHERE customer_cd="+rset.getString(1)+"";
					rset1 = stmt1.executeQuery(queryString3);
					if(rset1.next())
					{
						customer_abbr = rset1.getString(1)==null?"":rset1.getString(1);
					}
					
					queryString1 = "SELECT NVL(A.plant_name,' ') FROM FMS7_CUSTOMER_PLANT_DTL A " +
							   "WHERE A.customer_cd="+rset.getString(1)+" AND A.seq_no="+rset.getString(3)+" " +
							   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
							   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
							   "AND B.eff_dt<=TO_DATE('"+period_st_dt+"','DD/MM/YYYY'))";
					////System.out.println("Customer Plant Name Fetch Query For LOA = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						cust_plant_nm = rset1.getString(1)==null?"":rset1.getString(1);
					}
					
					String bgColor = "";
					if(print_copy.contains("O")) {
						bgColor = "#FFFFE0";
					}if(print_copy.contains("D")) {
						bgColor = "#F0E68C";
					}if(print_copy.contains("T")) {
						bgColor = "#FFA500";
					}
					VinvInvoice_bgColor.add(bgColor);
					
					String custAbbrSql = "SELECT CUSTOMER_ABBR FROM FMS7_CUSTOMER_MST WHERE CUSTOMER_CD = '"+rset.getString(1)+"'";
					rset1 = stmt1.executeQuery(custAbbrSql);
					if(rset1.next()) {
						VinvCustomer_abbr.add(rset1.getString(1) == null ?"":rset1.getString(1));
					}else {
						VinvCustomer_abbr.add("");
					}
					
					String plantAbbrSql = "SELECT PLANT_NAME FROM FMS7_CUSTOMER_PLANT_DTL WHERE CUSTOMER_CD = '"+rset.getString(1)+"'"
							+ " AND SEQ_NO = '"+rset.getString(3)+"'";
					rset1 = stmt1.executeQuery(plantAbbrSql);
					if(rset1.next()) {
						VinvPlant_name.add(rset1.getString(1) == null ?"":rset1.getString(1));
					}else {
						VinvPlant_name.add("");
					}
					 String map_cont_type = "";
					 String cont_no = "";
					 String cont_rev_no = "";
					 String agr_no = "";
					 String agr_rev__no = "";
					 String temp_cont = "";
					if(rset.getString(2).toString().contains("-")) {
						String tempMap [] = rset.getString(2).split("-");
							if(tempMap[0].equals("S")) {
								map_cont_type = "SN";
								temp_cont = "S";
							}else {
								map_cont_type = "LoA";
								temp_cont = "L";
							}
							agr_no = tempMap[1];
							agr_rev__no = tempMap[2];
							cont_no = tempMap[3];
							cont_rev_no = tempMap[4];
							
					}
//					Hiren_20230116
					String trans_cont_no = "";
					String trans_cont_sql = "select TRANS_CONT_NO from DLNG_SALES_TRANSPORTER_MST where "
							+ " CONTRACT_NO = '"+cont_no+"'"
							+ " AND CONTRACT_REV_NO='"+cont_rev_no+"'"
							+ " AND AGREEMENT_NO = '"+agr_no+"'"
							+ " AND AGREEMENT_REV_NO='"+agr_rev__no+"'"
							+ " AND CUSTOMER_CD='"+rset.getString(1)+"'"
							+ " AND CONTRACT_TYPE = '"+temp_cont+"'";
					rset2=stmt2.executeQuery(trans_cont_sql);
//					System.out.println("queryString...."+queryString);
					if(rset2.next()){
						trans_cont_no = rset2.getString(1)==null?"":rset2.getString(1);
					}
					
					VinvContNo.add(map_cont_type+ "-" +cont_no+" ("+trans_cont_no+")");
					irn_no = "";
					qr_code = "";
					//for irn & qrcode
					String no=rset.getString(4)==null?"":rset.getString(4);
					queryString="SELECT IRN_NO ,SIGN_QR_CODE FROM FMS7_INVOICE_IRN_DTL WHERE "
							+ " CONTRACT_TYPE='V' "
							+ " AND NEW_INV_SEQ_NO='"+no+"'  ";
					rset2=stmt2.executeQuery(queryString);
//					System.out.println("queryString...."+queryString);
					if(rset2.next()){
						irn_no=rset2.getString(1)==null?"":rset2.getString(1);
						qr_code=rset2.getString(2)==null?"":rset2.getString(2);
						
						if(!irn_no.equals("") && (!qr_code.equals(""))){
							irn_flag.add("Y");
						}else{
							irn_flag.add("");
						}
					}else{
						irn_flag.add("");
					}
					//
					
					/* ***************** PDFFileNmForInvoice********************** */	

					String invoice_date=invoice_dt;
					if(!invoice_date.equals(""))
						VPDF_File_Nm.add("INVOICE-"+invoice_date.trim().substring(0,2)+invoice_date.trim().substring(3,5)+invoice_date.trim().substring(6)+"-"+customer_abbr+"-"+cust_plant_nm+"-"+cont_type+"-"+inv_seq_no);
					else
						VPDF_File_Nm.add("-");
					
//					for DR/CR Note
					String invSeqSql = "select A.DR_CR_DOC_NO, A.DR_CR_FLAG, TO_CHAR(A.DR_CR_DT, 'DD-Mon-YY'),NVL(A.APRV_BY,'0'),"
							+ " TO_CHAR(A.APRV_DT, 'DD/MM/YYYY'),A.CRITERIA,A.REMARK,B.FLAG  "
							+ " from DLNG_DR_CR_NOTE A,DLNG_INVOICE_MST B"
							+ " where B.new_inv_seq_no = '"+no+"' "
							+ " and B.invoice_dt = to_date('"+invoice_dt+"','dd/mm/yyyy') "
							+ " and B.HLPL_INV_SEQ_NO = A.HLPL_INV_SEQ_NO  and B.SN_NO = A.SN_NO and B.FGSA_NO = A.FGSA_NO"
							+ " and B.CUSTOMER_CD = A.CUSTOMER_CD and B.FINANCIAL_YEAR = A.FINANCIAL_YEAR "
							+ " and B.PLANT_SEQ_NO = A.PLANT_SEQ_NO"
							+ " and B.CONTRACT_TYPE = A.CONTRACT_TYPE ";
//							+ " and B.SUP_STATE_CODE = A.SUP_STATE_CODE";
//					System.out.println("check Invoice DR CR---"+invSeqSql);
					rset2 = stmt2.executeQuery(invSeqSql);
					if(rset2.next()) {
						
						dr_cr_doc_no.add(rset2.getString(1)==null?"":rset2.getString(1));
						dr_cr_flag.add(rset2.getString(2)==null?"":rset2.getString(2));
						dr_cr_dt.add(rset2.getString(3)==null?"":rset2.getString(3));
						dr_cr_aprv_by.add(rset2.getString(4)==null?"0":rset2.getString(4));
						dr_cr_aprv_dt.add(rset2.getString(5)==null?"":rset2.getString(5));
						dr_cr_criteria.add(rset2.getString(6)==null?"":rset2.getString(6));
						dr_cr_remark.add(rset2.getString(7)==null?"":rset2.getString(7));
						dr_cr_inv_flag.add(rset2.getString(8)==null?"":rset2.getString(8));
						
						queryString="SELECT count(*) FROM FMS7_INVOICE_IRN_DTL WHERE "
								+ " CONTRACT_TYPE='V' "
								+ " AND NEW_INV_SEQ_NO='"+rset2.getString(1)+"'  ";
//						System.out.println("queryString...."+queryString);
						rset3=stmt3.executeQuery(queryString);
						if(rset3.next()){
							dr_cr_irn_cnt.add(rset3.getInt(1));
						}else{
							dr_cr_irn_cnt.add(0);
						}
					}else {
						dr_cr_irn_cnt.add(0);
						dr_cr_doc_no.add("");
						dr_cr_flag.add("");
						dr_cr_dt.add("");
						dr_cr_aprv_by.add("0");
						dr_cr_aprv_dt.add("");
						dr_cr_criteria.add("");
						dr_cr_remark.add("");
						dr_cr_inv_flag.add("");
					}
				}
				String file1="",file2="";

				for(int k = 0; k < VPDF_File_Nm.size(); k++){
					
					Vector invoice_path=new Vector();
					Vector invoice_path_sign=new Vector();
					Vector invoice_mail_sent_flag=new Vector();
					
					String qq1="select pdf_inv_nm,inv_type,pdf_signed_flag,mail_sent_flag from  dlng_inv_pdf_dtl"
							+ " where pdf_inv_nm='"+VPDF_File_Nm.elementAt(k).toString()+"' ";
//					System.out.println("qq1****"+qq1);
					rset1=stmt1.executeQuery(qq1);
					
					while(rset1.next()){
						
						file1=rset1.getString(1)+"-"+rset1.getString(2);
						invoice_path.add(file1);
						invoice_path_sign.add(rset1.getString(3)==null?"":rset1.getString(3));
						invoice_mail_sent_flag.add(rset1.getString(4)==null?"":rset1.getString(4));
					}
					invoice_view_pdf.put(k, invoice_path);
					invoice_view_signed_pdf.put(k,invoice_path_sign);
					invoice_mail_sent.put(k,invoice_mail_sent_flag);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
		}

		public String sac_code = "", sac_name = "", rule_remark = "Issued under Rule-4A of the Service Tax Rules, 1994", service_desc = "";
		public String contact_Suppl_Name = "";
		public String contact_Suppl_Person_Address = "";
		public String contact_Suppl_Person_City = "";
		public String contact_Suppl_Person_Pin = "";
		String contact_Suppl_State = "";
		String contact_Suppl_State_Code = "";
		public String contact_Suppl_GST_NO = "";
		public String contact_Suppl_CST_NO = "";
		public String contact_Suppl_GST_DT = "";
		public String contact_Suppl_CST_DT = "";
		public String contact_Suppl_Service_Tax_NO = "";
		public String contact_Suppl_Service_Tax_DT = "";
		
		public String contact_Suppl_PAN_NO = "";		
		public String contact_Suppl_PAN_DT = "";		
		
		String contact_Suppl_GSTIN_NO = "";	
		String contact_Suppl_GSTIN_DT = "";
		String customer_Invoice_FGSA_Dt = "";
		String contact_Customer_Name = "";
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
		String contact_Customer_Plant_State = "";
		String contact_Customer_State_Code = "";
		
		String mapping_id = "";
		String cont_no="";
		String cont_rev_no="";
		String agr_no="";
		String agr_rev_no="";
		String selCont_type="";
		public String invoice_Customer_Contact_Cd = "";
		public String invoice_Customer_Contact_Nm = "";
		public String contact_addr_flag = "";
		
		public Vector vSTAT_CD = new Vector();
		public Vector vSTAT_NM = new Vector();
		public Vector vSTAT_NO = new Vector();
		public Vector vSTAT_EFF_DT = new Vector();
		String invFlag="";
		private void fetchSacDtl()throws SQLException,IOException {
			try {
				
				queryString = "SELECT SERVICE_NM,SERVICE_CD,RULE_REMARK,SERVICE_DESC FROM FMS7_LNG_SALES_MAPPING WHERE "
						+ "CONTRACT_TYPE='"+contract_type+"' ";
//				System.out.println("queryString---"+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next()) {
					sac_code = rset.getString(2)==null?"":rset.getString(2);
					sac_name = rset.getString(1)==null?"":rset.getString(1);
					rule_remark = rset.getString(3)==null?"":rset.getString(3);
					service_desc = rset.getString(4)==null?"":rset.getString(4);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		private void fetchInvoicePreviewDtl()throws SQLException,IOException {
			
			try {
				
				//for line items 
				if(sacStr.contains("@@")) {
					
					String tempSac [] = sacStr.split("@@");
					String tempItem [] = itemStr.split("@@");
					String tempQty [] = qtyStr.split("@@");
					String tempRate [] = rateStr.split("@@");
					String tempAmt [] = amtStr.split("@@");
					
					for(int i = 0; i < Integer.parseInt(rowno+"") ; i++) {
//						System.out.println("tempSac[i]----"+tempSac[i]);
						String sac_cd = tempSac[i];
						if(sac_cd.equals("-")) {
							sac_cd = "";
						}
						String item_desc = tempItem[i];
						if(item_desc.equals("-")) {
							item_desc = "";
						}
						String qty = tempQty[i];
						if(qty.equals("-")) {
							qty = "0";
						}
						String rate = tempRate[i];
						if(rate.equals("-")) {
							rate = "0";
						}
						String amt = tempAmt[i];
						if(amt.equals("-")) {
							amt = "0";
						}
						
						Vsac_cd.add(sac_cd);
						Vqty.add(qty);
						Vrate.add(rate);
						Vamt.add(amt);
						Vitem_desc.add(item_desc);
					}
				}
//				System.out.println("invFlag----"+invFlag);
				/* ************************************* */
				if(invFlag.equalsIgnoreCase("VIEW")) {
					invoice_Customer_Contact_Cd = "" ;plant_no = "";
					String fetchInvMst = "select INR_BASE,nvl(GROSS_AMT_INR,0),nvl(NET_AMT_INR,0),nvl(TAX_AMT_INR,0),REMARK_1,"
							+ " REMARK_2,NEW_INV_SEQ_NO,CONTACT_PERSON_CD,PLANT_SEQ_NO "
							+ " from DLNG_INVOICE_MST "
							+ " where CUSTOMER_CD = '"+cust_cd+"' "
							+ " and HLPL_INV_SEQ_NO = '"+inv_seq_no+"'"
							+ " and FINANCIAL_YEAR = '"+fin_yr+"'"
							+ " and CONTRACT_TYPE = 'V' "
							+ " and SUPPLIER_CD = '1'"
							+ " and mapping_id= '"+mapping_id+"'"
							+ " and INVOICE_DT = to_date('"+invDt+"','dd/mm/yyyy')";
					System.out.println("fetchInvMst----"+fetchInvMst);
					rset = stmt.executeQuery(fetchInvMst);
					if(rset.next()) {
						calcBase = rset.getString(1)==null?"":rset.getString(1);
						invGrossAmt = rset.getString(2)==null?"0":rset.getString(2);
						invNetAmt = rset.getString(3)==null?"0":rset.getString(3);
						invTaxAmt = rset.getString(4)==null?"0":rset.getString(4);
						remark1 = rset.getString(5)==null?"":rset.getString(5);
						remark2 = rset.getString(6)==null?"":rset.getString(6);
						new_inv_seq_no = rset.getString(7)==null?"":rset.getString(7);
						invoice_Customer_Contact_Cd = rset.getString(8)==null?"":rset.getString(8);
						plant_no = rset.getString(9)==null?"":rset.getString(9);
						String temp_plant_no = "P"+plant_no.trim();
						
						queryString = "SELECT NVL(contact_person,' '),NVL(contact_desig,' '),addr_flag " +
								  " FROM FMS7_CUSTOMER_CONTACT_MST A " +
								  " WHERE A.customer_cd="+cust_cd+" "
								+ " AND SEQ_NO = '"+invoice_Customer_Contact_Cd+"' "
								+ " AND (A.addr_flag='B' OR A.addr_flag='R' OR A.addr_flag='C' OR A.addr_flag='"+temp_plant_no+"') " +
								  " AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_CONTACT_MST B"
								  	+ " WHERE A.customer_cd=B.customer_cd"
								  	+ " AND B.seq_no = '"+invoice_Customer_Contact_Cd+"' "
								  	+ " AND  B.eff_dt<=TO_DATE('"+period_start_dt+"','DD/MM/YYYY'))";
//						System.out.println("Customer Contact Person Fetch Query Invoice = "+queryString);
						rset1 = stmt1.executeQuery(queryString);
						if(rset1.next())
						{
							invoice_Customer_Contact_Nm = rset1.getString(1)==null?"":rset1.getString(1)+" ("+rset1.getString(2).trim()+")";
							contact_addr_flag = rset1.getString(3)==null?"":rset1.getString(3);
						}
					}
					
					int i = 0;
					String fetchInvDtl = "select ITEM_DESCRIPTION,RATE,TAX_DETAILS,QUANTITY,CARGO_AMOUNT,TAX_CD,"
							+ " IGST_AMT,CGST_AMT,SGST_AMT,SAC_CODE,RATE_CGST,RATE_IGST,RATE_SGST"
							+ " from DLNG_SERVICE_INVOICE_DTL"
							+ " where "
							+ "  INV_SEQ_NO = '"+inv_seq_no+"'"
							+ " and FINANCIAL_YEAR = '"+fin_yr+"'"
							+ " and CONTRACT_TYPE = 'V' "
							+ " and SUPPLIER_CD = '1'"
							+ " and mapping_id= '"+mapping_id+"'"
							+ " and EFF_DT = to_date('"+invDt+"','dd/mm/yyyy')";
					rset = stmt.executeQuery(fetchInvDtl);
					while (rset.next()) {
						
						Vitem_desc.add(rset.getString(1)==null?"":rset.getString(1));
						Vrate.add(rset.getString(2)==null?"":rset.getString(2));
						Vqty.add(rset.getString(4)==null?"":rset.getString(4));
						Vamt.add(rset.getString(5)==null?"":rset.getString(5));
						Vsac_cd.add(rset.getString(10)==null?"":rset.getString(10));
						
						String tax_cd = rset.getString(6)==null?"":rset.getString(6);
						String igst_amt = rset.getString(7)==null?"":rset.getString(7);
						String cgst_amt = rset.getString(8)==null?"":rset.getString(8);
						String sgst_amt = rset.getString(9)==null?"":rset.getString(9);
						String rate_cgst = rset.getString(11)==null?"":rset.getString(11);
						String rate_igst = rset.getString(12)==null?"":rset.getString(12);
						String rate_sgst = rset.getString(13)==null?"":rset.getString(13);
						
						if(i == 0) {
							if(tax_cd.equalsIgnoreCase("I")) {
								
								taxnm_str = "IGST@@";
								taxAmt_str = igst_amt+"@@"; 
								tax_structure = rate_igst;
							}else if(tax_cd.equalsIgnoreCase("C")) {
								
								taxnm_str = "CGST@@SGST@@";
								taxAmt_str = cgst_amt+"@@"+sgst_amt+"@@"; 
								tax_structure = rate_cgst;
							}
						}
						i++;
					}
					
					/*fetching attachement details*/
					String attachSql = "SELECT nvl(AMOUNT,'0'),nvl(INVOICE_QTY,'0'),KM,RATE,to_char(SERVICE_INV_DT,'dd/mm/yyyy'),"
							+ " to_char(TRUCK_INV_DT,'dd/mm/yyyy'),TRUCK_INV_NO,TRUCK_NM "
							+ " FROM DLNG_SERVICE_INVOICE_ATTACH "
							+ " WHERE SERVICE_INVOICE_NO = '"+new_inv_seq_no+"' "
							+ " AND SERVICE_INV_DT = to_date('"+invDt+"','dd/mm/yyyy') ";
//					System.out.println("attachSql---"+attachSql);
					rset = stmt.executeQuery(attachSql);
					while (rset.next()) {
						
						View_amount.add(rset.getString(1) == null?"":rset.getString(1));
						View_invoice_qty.add(rset.getString(2) == null?"":rset.getString(2));
						View_km.add(rset.getString(3) == null?"":rset.getString(3));
						View_rate.add(rset.getString(4) == null?"":rset.getString(4));
						View_service_inv_dt.add(rset.getString(5) == null?"":rset.getString(5));
						View_truck_inv_dt.add(rset.getString(6) == null?"":rset.getString(6));
						View_truck_inv_no.add(rset.getString(7) == null?"":rset.getString(7));
						View_truck_nm.add(rset.getString(8) == null?"":rset.getString(8));
						
						total_qty+=rset.getDouble(2);
						String tempAmt = "0"; 
						if(rset.getString(1).contains(",")) {
							tempAmt = rset.getString(1).replace(",","");
						}else {
							tempAmt = rset.getString(1);
						}
						total_amt+=Double.parseDouble(tempAmt+"");	
					}
				}
				
				irn_no = "";
				qr_code = "";
				//for irn & qrcode
				queryString="SELECT IRN_NO ,SIGN_QR_CODE FROM FMS7_INVOICE_IRN_DTL WHERE "
						+ " CONTRACT_TYPE='V' "
						+ " AND NEW_INV_SEQ_NO='"+new_inv_seq_no+"' ";
				rset2=stmt2.executeQuery(queryString);
//				System.out.println("queryString..print.."+queryString);
				if(rset2.next()){
					irn_no=rset2.getString(1)==null?"":rset2.getString(1);
					qr_code=rset2.getString(2)==null?"":rset2.getString(2);
					if(!irn_no.equals("") && (!qr_code.equals(""))){
						irn_flag.add("Y");
					}else{
						irn_flag.add("");
					}
				}else{
					irn_flag.add("");
				}
				//
				
			}catch (Exception e) {
				e.printStackTrace();
			}
		}

		private void fetchInvContactPerson()throws SQLException,IOException {
			try {
				if(!period_start_dt.equals("")) {
					
					String temp_plant_no = "P"+plant_no.trim();
					queryString = "SELECT NVL(A.seq_no,'0'),NVL(contact_person,' '),NVL(contact_desig,' '),addr_flag " +
								  "FROM FMS7_CUSTOMER_CONTACT_MST A " +
								  "WHERE A.customer_cd="+cust_cd+" AND A.def_inv_flag='Y' AND " +
								  "A.active_flag='Y' AND (A.addr_flag='B' OR A.addr_flag='R' OR " +
								  "A.addr_flag='C' OR A.addr_flag='"+temp_plant_no+"') AND " +
								  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_CONTACT_MST B " +
								  "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no AND " +
								  "B.eff_dt<=TO_DATE('"+period_start_dt+"','DD/MM/YYYY'))"
								  + " and def_inv_to_flag = 'Y' ";
//					System.out.println("Customer Contact Person Fetch Query = "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						invoice_Customer_Contact_Cd = rset.getString(1)==null?"":rset.getString(1);
						invoice_Customer_Contact_Nm = rset.getString(2)==null?"":rset.getString(2)+" ("+rset.getString(3).trim()+")";
						
						contact_addr_flag = rset.getString(4)==null?"":rset.getString(4);
					}
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}

		public String contract_type = "";
		String Min_FY = ""; 
		public String getMin_FY() {return Min_FY;}
		
		public void Supl_Inv_MST()throws SQLException,IOException 
		{
			try
			{
				
				String invYears =  "Select distinct(TO_CHAR(INVOICE_DT,'YYYY'))  from DLNG_INVOICE_MST"
						+ " where TO_CHAR(INVOICE_DT,'YYYY') not in (SELECT TO_CHAR(SYSDATE,'YYYY') FROM DUAL)"
						+ " ORDER BY TO_CHAR(INVOICE_DT,'YYYY')";
//				System.out.println("invYears---"+invYears);
				rset = stmt.executeQuery(invYears);
				while (rset.next()) {
					yearList.add(rset.getString(1) == null ?"":rset.getString(1));
				}
				
				String queryString1="SELECT TO_CHAR(SYSDATE,'YYYY') FROM DUAL";
				//System.out.println("GST-INV:FirstInvYrinSystem: "+queryString1);
				rset=stmt.executeQuery(queryString1);
				if(rset.next())
				{
					yearList.add(rset.getString(1));
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		
		String fin_yr = "";
		String inv_seq_no = "";
		String new_inv_seq_no = "";
		Vector Tax_nm=new Vector();
		Vector GST_RT=new Vector();
		Vector SGST_CGST_RT=new Vector();
		String tax_struct="";
		Vector tax_desc=new Vector();
		Vector taxstr=new Vector();
		String app_tax_flag="";
		
		Vector VInv_new_inv_seq_no = new Vector();
		Vector VInv_invoice_dt = new Vector();
		Vector VInv_total_qty= new Vector();
		Vector Vinv_truck_nm= new Vector();
		Vector Vinv_qty_inr = new Vector();
		Vector Vinv_km_inr = new Vector();
		Vector Vinv_km = new Vector();
		Vector Vinv_lumpSum = new Vector();
		Vector Vinv_chkBox_Flg = new Vector();
		
		double total_qty_inr = 0;
		double total_qty_mmbtu = 0;
		double total_qty_km = 0;
		double total_inv_km = 0 ;
		
		String sacStr = "";
		String itemStr = "";
		String qtyStr = "";
		String rateStr = "";
		String amtStr = "";
		String rowno = "";
		
		Vector Vsac_cd = new Vector();
		Vector Vitem_desc = new Vector();
		Vector Vqty = new Vector();
		Vector Vrate = new Vector();
		Vector Vamt = new Vector();
		double inv_gross_amt = 0;
		String totalTax = "";
		String netAmt = "";
		String taxAmt_str = "";
		String grossAmt =  "";
		String truckInvSz = "";
		String total_ls_inr =  "";
		@SuppressWarnings("unchecked")
		private void fetchInvoiceDtl()throws SQLException,IOException {
			try {
//				System.out.println("month---"+month+"--year--"+year);
				
					if(Integer.parseInt(month)<4){
						fin_yr=(Integer.parseInt(year)-1)+"-"+year;
					}else{
						fin_yr=year+"-"+(Integer.parseInt(year)+1);
					}
					
	//				System.out.println("fin_yr---"+fin_yr);
					String maxInvSql = "SELECT NVL(MAX(HLPL_INV_SEQ_NO+1),1) FROM DLNG_INVOICE_MST WHERE"
							+ " SUPPLIER_CD = '"+supp_cd+"' AND FINANCIAL_YEAR = '"+fin_yr+"' AND CONTRACT_TYPE = 'V' ";
//					System.out.println("maxInvSql===="+maxInvSql);
					rset = stmt.executeQuery(maxInvSql);
					if(rset.next()) {
						inv_seq_no = rset.getString(1)==null?"":rset.getString(1);
					}
					if(inv_seq_no.length()==1) {
						inv_seq_no = "0000"+inv_seq_no;
					} else if(inv_seq_no.length()==2) {
						inv_seq_no = "000"+inv_seq_no;
					} else if(inv_seq_no.length()==3) {
						inv_seq_no = "00"+inv_seq_no;
					} else if(inv_seq_no.length()==4) {
						inv_seq_no = "0"+inv_seq_no;
					}else if(inv_seq_no.length()==5) {
						inv_seq_no = inv_seq_no;
					} 
					
					new_inv_seq_no = "TMS"+inv_seq_no + "/" + fin_yr.substring(2,5)+fin_yr.substring(7,9);
					
				
				/*fetching tax structure*/
				queryString="SELECT TYPE FROM STATE_MST WHERE STATE_CODE='"+plant_state_cd+"'";
//				System.out.println("queryString--"+queryString);
				rset=stmt.executeQuery(queryString);
				if(rset.next()){
					app_tax_flag=rset.getString(1)==null?"":rset.getString(1);
				}
//				System.out.println("supp_state_cd--"+supp_state_cd+"**plant_state_cd--"+plant_state_cd);
				if(app_tax_flag.trim().equalsIgnoreCase("S")){
					if(!supp_state_cd.equals(plant_state_cd))
					{
						Tax_nm.add("IGST");
//						taxstr.add("18.0");
						double rate_gst=0;
						queryString="SELECT RATE FROM GST_CODE_MST WHERE ABBR LIKE '%IGST' and GST_CODE!='IGSTEX' order by rate ";
						rset=stmt.executeQuery(queryString);
						while(rset.next()){
							GST_RT.add(rset.getString(1)==null?"":rset.getString(1));
							rate_gst=Double.parseDouble(rset.getString(1));
							SGST_CGST_RT.add(rate_gst);
							tax_struct="IGST-"+rate_gst+"%";
							tax_desc.add(tax_struct);
						}
						
					}else if(supp_state_cd.equals(plant_state_cd))
					{
						
						Tax_nm.add("CGST");
						Tax_nm.add("SGST");
						taxstr.add("9.0");
						taxstr.add("9.0");
						double rate_gst=0;
						queryString="SELECT RATE FROM GST_CODE_MST WHERE ABBR LIKE '%SGST'  and GST_CODE!='SGSTEX'  order by rate ";
						rset=stmt.executeQuery(queryString);
						while(rset.next()){
							GST_RT.add(rset.getString(1)==null?"":rset.getString(1));
							rate_gst=Double.parseDouble(rset.getString(1))/2;
							SGST_CGST_RT.add(rate_gst);
							tax_struct="CGST,SGST-"+rate_gst+"%";
							tax_desc.add(tax_struct);
						}
					}
				}else if(app_tax_flag.trim().equalsIgnoreCase("U")){
					Tax_nm.add("UTGST");
					taxstr.add("18.0");
					double rate_gst=0;
					queryString="SELECT RATE FROM GST_CODE_MST WHERE ABBR LIKE '%UTGST' and GST_CODE!='UTGSTEX' order by rate ";
					rset=stmt.executeQuery(queryString);
					while(rset.next()){
						GST_RT.add(rset.getString(1)==null?"":rset.getString(1));
						rate_gst=Double.parseDouble(rset.getString(1));
						SGST_CGST_RT.add(rate_gst);
						tax_struct="UTGST-"+rate_gst+"%";
						tax_desc.add(tax_struct);
					}
				}else{
					Tax_nm.add("IGST");
					taxstr.add("18.0");
					double rate_gst=0;
					queryString="SELECT RATE FROM GST_CODE_MST WHERE ABBR LIKE '%IGST' and GST_CODE!='IGSTEX' order by rate ";
					rset=stmt.executeQuery(queryString);
					while(rset.next()){
						GST_RT.add(rset.getString(1)==null?"":rset.getString(1));
						rate_gst=Double.parseDouble(rset.getString(1));
						SGST_CGST_RT.add(rate_gst);
						tax_struct="IGST-"+rate_gst+"%";
						tax_desc.add(tax_struct);
					}
				}
				//////
				
				//Fetching sales/loa invoice details
				if(Smapping_id.contains("-")) {
					
					String mappnig_id[] = Smapping_id.split("-");
					String amt_inr_mmbtu = "0";
					String invDtl = "SELECT NEW_INV_SEQ_NO,to_char(INVOICE_DT,'DD/MM/YYYY'),nvl(TOTAL_QTY,0),TRUCK_ID "
							+ " FROM DLNG_INVOICE_MST WHERE"
							+ " CUSTOMER_CD = '"+cust_cd+"' "
							+ " AND INVOICE_DT between to_date('"+period_start_dt+"','dd/mm/yyyy') and to_date('"+period_end_dt+"','dd/mm/yyyy') "
							+ " AND CONTRACT_TYPE= '"+mappnig_id[0]+"' "
							+ " AND APPROVED_FLAG = 'Y' "
							+ " and FGSA_NO = '"+mappnig_id[1]+"'"
							+ " and FGSA_REV_NO = '"+mappnig_id[2]+"'"
							+ " and SN_NO = '"+mappnig_id[3]+"'"
//							+ " and SN_REV_NO = '"+mappnig_id[4]+"'"
							+ " AND PLANT_SEQ_NO='"+plant_no+"' order by  INVOICE_DT";
//					System.out.println("invDtl===="+invDtl);
					rset = stmt.executeQuery(invDtl);
					while (rset.next()) {
						
						VInv_new_inv_seq_no.add(rset.getString(1)==null?"":rset.getString(1));
						VInv_invoice_dt.add(rset.getString(2)==null?"":rset.getString(2));
						VInv_total_qty.add(rset.getString(3)==null?"":rset.getString(3));
//						total_qty_mmbtu+=Double.parseDouble(rset.getString(3)+"");
						
						amt_inr_mmbtu = "0";
						if(!inr_mmbtu.equals("0")) {
							amt_inr_mmbtu = nf5.format(Double.parseDouble(rset.getString(3)+"") * Double.parseDouble(inr_mmbtu+""));
//							total_qty_inr+=Double.parseDouble(rset.getString(3)+"") * Double.parseDouble(inr_mmbtu+"");
						}
						
						Vinv_qty_inr.add(amt_inr_mmbtu);
						
						String truckNmSql = "SELECT TRUCK_NM from DLNG_TANK_TRUCK_MST where TRUCK_ID = '"+rset.getString(4)+"'";
						rset1 = stmt1.executeQuery(truckNmSql);
						if(rset1.next()) {
							Vinv_truck_nm.add(rset1.getString(1)==null?"":rset1.getString(1));
						}else {
							Vinv_truck_nm.add("");
						}
					}
					if(km_str.contains("@@")) {
						
						String temp1[] = km_str.split("@@");
						String temp2[] = inr_km_str.split("@@");
						for(int i = 0 ; i < temp1.length ; i++) {
							Vinv_km.add(temp1[i]);
							Vinv_km_inr.add(temp2[i]);
							total_qty_km+=Double.parseDouble(temp1[i]+"");
						}
					}else {
						for(int i = 0 ; i < VInv_new_inv_seq_no.size(); i++ ) {
							Vinv_km.add("");
							Vinv_km_inr.add("");
						}
					}
					
					if(inr_mmbtu_str.contains("@@")) {
						
						String temp1[] = qty_mmbtu_str.split("@@");
						String temp2[] = inr_mmbtu_str.split("@@");
						for(int i = 0 ; i < temp1.length ; i++) {
							total_qty_mmbtu+=Double.parseDouble(temp1[i]+"");
							String temp_qty_inr = temp2[i].toString().replace(",","");
							total_qty_inr+=Double.parseDouble(temp_qty_inr+"");
						}
					}
					
					if(ls_str.contains("@@")) {
						String temp1[] = ls_str.split("@@");
						for(int i = 0 ; i < temp1.length ; i++) {
							Vinv_lumpSum.add(temp1[i]);
						}
					}else {
						for(int i = 0 ; i < VInv_new_inv_seq_no.size(); i++ ) {
							Vinv_lumpSum.add("");
						}
					}
					if(chkBoxFlgStr.contains("@@")) {
						String tempChk []=chkBoxFlgStr.split("@@");
						for(int i = 0 ; i < tempChk.length ; i++) {
							Vinv_chkBox_Flg.add(tempChk[i]);
						}
					}else {
						for(int i = 0 ; i < VInv_new_inv_seq_no.size(); i++ ) {
							Vinv_chkBox_Flg.add("");
						}
					}
					
					
					//for line items 
//					System.out.println("sacStr----"+sacStr);
					if(sacStr.contains("@@")) {
						
						String tempSac [] = sacStr.split("@@");
						String tempItem [] = itemStr.split("@@");
						String tempQty [] = qtyStr.split("@@");
						String tempRate [] = rateStr.split("@@");
						String tempAmt [] = amtStr.split("@@");
						
						for(int i = 0; i < Integer.parseInt(rowno+"") ; i++) {
//							System.out.println("tempSac[i]----"+tempSac[i]);
							String sac_cd = tempSac[i];
							if(sac_cd.equals("-")) {
								sac_cd = "";
							}
							String item_desc = tempItem[i];
							if(item_desc.equals("-")) {
								item_desc = "";
							}
							String qty = tempQty[i];
							if(qty.equals("-")) {
								qty = "0";
							}
//							System.out.println("qty----"+qty);
							String rate = tempRate[i];
							if(rate.equals("-")) {
								rate = "0";
							}
							String amt = tempAmt[i];
							if(amt.equals("-")) {
								amt = "0";
							}
							if( i == 0) { // for invoice line item row
								if(calcBase.equals("1")) {
									
//									Vsac_cd.add("998599");
									Vqty.add(nf5.format(total_qty_mmbtu));
									Vrate.add(inr_mmbtu);
									Vamt.add(nf.format(total_qty_inr));
									inv_gross_amt+=total_qty_inr;
									total_qty = total_qty_mmbtu;
								}else if(calcBase.equals("2")) {
									
//									Vsac_cd.add("998599");
									Vqty.add(nf.format(total_qty_km));
									Vrate.add(inr_km);
									Vamt.add(nf.format(Double.parseDouble(total_km_inr+"")));
									inv_gross_amt+=Double.parseDouble(total_km_inr+"");
									total_qty = total_qty_km;
									
								}else if(calcBase.equals("3")) {
//									Vsac_cd.add("998599");
									Vqty.add("1");
									Vrate.add(total_ls_inr);
									Vamt.add(nf.format(Double.parseDouble(total_ls_inr+"")));
									inv_gross_amt+=Double.parseDouble(total_ls_inr+"");
//									total_qty = Double.parseDouble(+"";
								}else {
//									Vsac_cd.add("998599");
									Vqty.add(qty);
									Vrate.add(rate);
									Vamt.add(amt);
								}
								fetchSacDtl();
							}else {
//								Vsac_cd.add(sac_cd);
								Vqty.add(qty);
								Vrate.add(rate);
								Vamt.add(amt);
								inv_gross_amt+=Double.parseDouble(amt+"");
								total_qty+=Double.parseDouble(qty+"");
							}
							Vitem_desc.add(item_desc);
							Vsac_cd.add(sac_code);
						}
					}else {
						Vsac_cd.add("");
						Vitem_desc.add("");
						Vqty.add("");
						Vrate.add("");
						Vamt.add("");
//						System.out.println("total_qty_mmbtu===="+total_qty_mmbtu);
						if(calcBase.equals("1")) {
							
							total_qty=Double.parseDouble(total_qty_mmbtu+"");
						}else if(calcBase.equals("2")) {
							total_qty=Double.parseDouble(total_qty_km+"");
						}
					}
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		Vector Vcont_no = new Vector();
		Vector Vcont_rev_no = new Vector();
		Vector Vagr_no = new Vector();
		Vector Vagr_rev_no = new Vector();
		Vector Vcont_type = new Vector();
		Vector Vinr_km = new Vector();
		Vector Vinr_mmbtu = new Vector();
		Vector Vlumpsum_flg = new Vector();
		Vector Vcont_start_dt = new Vector();
		Vector Vcont_end_dt = new Vector();
		String period_start_dt = "";
		String period_end_dt = "";
		String chkFlg = "";
		String Smapping_id="";
		String Srate = "";
		String alwInvGen = "N";
		String lumpsumFlg = ""; 
		String refreshFlg= "N";
		String cont_start_dt = "";
		String cont_end_dt = "";
		
		private void fetchContracts()throws SQLException,IOException {
			try {
				
				//System.out.println("period_start_dt---"+period_start_dt+"****period_end_dt---"+period_end_dt);
				
				if(!period_start_dt.equals("") && !period_end_dt.equals("")) {
//					for SN
					
					String snSql = "SELECT FLSA_NO,FLSA_REV_NO,SN_NO,SN_REV_NO,to_char(start_dt,'dd/mm/yyyy'),to_char(end_dt,'dd/mm/yyyy') "
							+ " FROM DLNG_SN_MST A WHERE "
							+ " A.CUSTOMER_CD='"+cust_cd+"' and SN_BASE = 'D' "
//							+ " AND A.start_dt<=TO_DATE('"+period_start_dt+"','DD/MM/YYYY')"
							+ " AND A.end_dt>=TO_DATE('"+period_start_dt+"','DD/MM/YYYY')"
								+ " AND A.status='Y' "
							+ " AND A.sn_rev_no=(SELECT MAX(B.sn_rev_no) FROM DLNG_SN_MST B "
							+ "	WHERE B.CUSTOMER_CD='"+cust_cd+"' AND A.flsa_no=B.flsa_no AND A.sn_no=B.sn_no AND"
							+ " A.customer_cd=B.customer_cd  "
//							+ " B.start_dt<=TO_DATE('"+period_start_dt+"','DD/MM/YYYY')"
								+ " AND B.end_dt>=TO_DATE('"+period_start_dt+"','DD/MM/YYYY')"
							   + "  AND B.status='Y') Order BY SN_NO";
//						System.out.println("Fetching SN COntracts.."+snSql);
						rset = stmt.executeQuery(snSql);
						while (rset.next()) {
							
							 Vagr_no.add(rset.getString(1) == null?"":rset.getString(1));
							 Vagr_rev_no.add(rset.getString(2) == null?"":rset.getString(2));
							 Vcont_no.add(rset.getString(3) == null?"":rset.getString(3));
							 Vcont_rev_no.add(rset.getString(4) == null?"":rset.getString(4));
							 Vcont_type.add("S");
							 Vcont_start_dt.add(rset.getString(5) == null?"":rset.getString(5));
							 Vcont_end_dt.add(rset.getString(6) == null?"":rset.getString(6));
							 
							 String transChargeSql = "select nvl(INR_KM,0),nvl(INR_MMBTU,0),nvl(LUMPSUM_FLAG,'N') from DLNG_SN_PLANT_MST"
							 		+ " where CUSTOMER_CD = '"+cust_cd+"' and FLSA_NO='"+rset.getString(1)+"' and FLSA_REV_NO='"+rset.getString(2)+"'"
							 		+ " and SN_NO ='"+rset.getString(3)+"' and SN_REV_NO = '"+rset.getString(4)+"' and PLANT_SEQ_NO = '"+plant_no+"' ";
							 
							 //System.out.println("transChargeSql--SN--"+transChargeSql);
							 rset1 = stmt1.executeQuery(transChargeSql);
							 if (rset1.next()) {
								 Vinr_km.add(rset1.getString(1)==null?"0":rset1.getString(1));
								 Vinr_mmbtu.add(rset1.getString(2)==null?"0":rset1.getString(2));
								 Vlumpsum_flg.add(rset1.getString(3)==null?"N":rset1.getString(3));
							}else {
								 Vinr_km.add("0");
								 Vinr_mmbtu.add("0");
								 Vlumpsum_flg.add("N");
							}
						}
						
						String LoASql = "SELECT TENDER_NO,LOA_NO,LOA_REV_NO,to_char(start_dt,'dd/mm/yyyy'),to_char(end_dt,'dd/mm/yyyy') "
								+ " FROM DLNG_LOA_MST A WHERE"
								+ " A.CUSTOMER_CD='"+cust_cd+"'  and LOA_BASE = 'D'  AND A.start_dt<=TO_DATE('"+period_start_dt+"','DD/MM/YYYY')"
								+ " AND A.end_dt>=TO_DATE('"+period_start_dt+"','DD/MM/YYYY') "
								+ " AND A.status='Y' AND A.FCC_FLAG='Y' "
								+ " AND A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM DLNG_LOA_MST B "
								+ "	WHERE A.CUSTOMER_CD='"+cust_cd+"' AND A.TENDER_NO=B.TENDER_NO AND A.LOA_NO=B.LOA_NO AND"
								+ " A.customer_cd=B.customer_cd AND  "
//								+ " B.start_dt<=TO_DATE('"+period_start_dt+"','DD/MM/YYYY') "
								+ " B.end_dt>=TO_DATE('"+period_start_dt+"','DD/MM/YYYY')"
								+ "  AND B.status='Y') ORDER BY LOA_NO";
						//System.out.println("LoA Contract****"+LoASql);
						rset = stmt.executeQuery(LoASql);
						while (rset.next()) {
							
							 Vagr_no.add(rset.getString(1) == null?"":rset.getString(1));
							 Vagr_rev_no.add("0");
							 Vcont_no.add(rset.getString(2) == null?"":rset.getString(2));
							 Vcont_rev_no.add(rset.getString(3) == null?"":rset.getString(3));
							 Vcont_type.add("L");
							 Vcont_start_dt.add(rset.getString(4) == null?"":rset.getString(4));
							 Vcont_end_dt.add(rset.getString(5) == null?"":rset.getString(5));
							 
							 String transChargeSql = "select  nvl(INR_KM,0),nvl(INR_MMBTU,0),nvl(LUMPSUM_FLAG,'N') from DLNG_LOA_PLANT_MST"
								 		+ " where CUSTOMER_CD = '"+cust_cd+"' and TENDER_NO='"+rset.getString(1)+"' "
								 		+ " and LOA_NO ='"+rset.getString(2)+"' and LOA_REV_NO = '"+rset.getString(3)+"' and PLANT_SEQ_NO = '"+plant_no+"' ";
								 rset1 = stmt1.executeQuery(transChargeSql);
								 if (rset1.next()) {
									 Vinr_km.add(rset1.getString(1)==null?"0":rset1.getString(1));
									 Vinr_mmbtu.add(rset1.getString(2)==null?"0":rset1.getString(2));
									 Vlumpsum_flg.add(rset1.getString(3)==null?"N":rset1.getString(3));
								}else {
									 Vinr_km.add("0");
									 Vinr_mmbtu.add("0");
									 Vlumpsum_flg.add("N");
								}
						}
						fetchInvContactPerson();
						
						/*checking for Service invoice generated in selected period*/
						for(int i = 0 ; i < Vagr_no.size() ; i++) {
							
							String mapId= Vcont_type.elementAt(i)+"-"+Vagr_no.elementAt(i)+"-"+Vagr_rev_no.elementAt(i)+"-"+Vcont_no.elementAt(i)+"-%";
							
							String serInvMst = "select NEW_INV_SEQ_NO,BILLING_CYCLE from DLNG_INVOICE_MST where "
									+ " CUSTOMER_CD = '"+cust_cd+"'"
									+ " AND MAPPING_ID like '"+mapId+"'"
									+ " AND PLANT_SEQ_NO = '"+plant_no+"' "
									+ " AND SUPPLIER_CD = '"+supp_cd+"'"
									+ " AND PERIOD_START_DT = to_date('"+period_start_dt+"','dd/mm/yyyy') "
									+ " AND PERIOD_END_DT = to_date('"+period_end_dt+"','dd/mm/yyyy') "
									+ " AND CONTRACT_TYPE = 'V' ";
//							System.out.println("serInvMst----"+serInvMst);
							rset = stmt.executeQuery(serInvMst);
							if(rset.next()) {
								serv_inv_no.add(rset.getString(1)==null?"":rset.getString(1));
								serv_inv_bill_cycle.add(rset.getString(2)==null?"":rset.getString(2));
							}else {
								serv_inv_no.add("");
								serv_inv_bill_cycle.add("");
							}
						}	
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		Vector Vsupp_cd = new Vector();
		Vector Vsupp_nm = new Vector();
		String supp_cd = "";
		String supp_nm = "";
		String supp_addr = "";
		String supp_city =  "";
		String supp_pin =  "";
		String supp_state =  "";
		String supp_state_cd = "";
		String supp_pan_no = "";
		String supp_gstin_no = "";
		
		private void fetchSupplierDtl()throws SQLException,IOException {
			
			try {
				
				queryString1="SELECT SUPPLIER_CD,SUPPLIER_NAME FROM FMS7_SUPPLIER_MST order by SUPPLIER_NAME ";
//				System.out.println("queryString1-----"+queryString1);
				rset=stmt.executeQuery(queryString1);
				while(rset.next())
				{
					Vsupp_cd.add(rset.getString(1)==null?"":rset.getString(1));
					Vsupp_nm.add(rset.getString(2)==null?"":rset.getString(2));
				}
				if(!supp_cd.equals("")) {
					
					queryString = "SELECT PAN_NO, gstin_no,SUPPLIER_NAME  " +
							" FROM FMS7_SUPPLIER_MST A "+ 
							" WHERE A.supplier_cd='"+supp_cd+"'  "
							+ " AND A.eff_dt=(SELECT MAX(B.eff_dt) " +
							" FROM FMS7_SUPPLIER_MST B WHERE A.supplier_cd=B.supplier_cd and  supplier_cd='"+supp_cd+"')";
//					System.out.println("GST-INV:Type=Z:QRY-004: "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						supp_pan_no=rset.getString(1)==null?"":rset.getString(1);
						supp_gstin_no=rset.getString(2)==null?"":rset.getString(2);
						supp_nm=rset.getString(3)==null?"":rset.getString(3);
					}
					
					queryString="SELECT ADDR, CITY, PIN, STATE, STATE_CODE " +
							" FROM FMS7_SUPPLIER_ADDRESS_MST A, STATE_MST B " +
							" WHERE SUPPLIER_CD='"+supp_cd+"' AND A.STATE=B.STATE_NM AND (A.FLAG='Y' OR  A.FLAG='T')" +
							" AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_SUPPLIER_ADDRESS_MST B "+ 
							" WHERE A.supplier_cd=B.supplier_cd)";
//					System.out.println("GST-INV:Type=Z:QRY-004A: "+queryString);
					rset=stmt.executeQuery(queryString);
					if(rset.next())
					{
						supp_addr=rset.getString(1)==null?"":rset.getString(1);
						supp_city=rset.getString(2)==null?"":rset.getString(2);
						supp_pin=rset.getString(3)==null?"":rset.getString(3);
						supp_state=rset.getString(4)==null?"":rset.getString(4);
						supp_state_cd=rset.getString(5)==null?"":rset.getString(5);
					}
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		Vector Vcust_cd=new Vector();
		Vector Vcust_abbr=new Vector();
		Vector Vcust_nm=new Vector();
		
		String cust_pan_no= "";
		String cust_gstin_no="";
		String cust_cd = "";
		String cust_addr="";
		String cust_city="";
		String cust_pin="";
		String cust_country="";
		String cust_state="";
		String state_cd="";
		String cust_nm = "";
		String plant_nm = "";
		Vector plant_seq_no = new Vector();
		Vector plant_name = new Vector();
		
		private void fetchCustDtl()throws SQLException,IOException{
			try {
				
				/*queryString="SELECT distinct A.CUSTOMER_CD,A.CUSTOMER_ABBR,A.CUSTOMER_NAME,A.GST_TIN_NO,A.PAN_NO"
						+ " FROM FMS7_CUSTOMER_MST A,DLNG_SN_MST B,DLNG_LOA_MST C"
						+ " WHERE  (B.SN_BASE = 'D' or  C.LOA_BASE= 'D')"
						+ " AND (A.CUSTOMER_CD= B.CUSTOMER_CD or A.CUSTOMER_CD= C.CUSTOMER_CD) ";*/
				queryString="SELECT DISTINCT(A_VAL),B_VAL,C_VAL,D_VAL,E_VAL FROM ("
						+ " SELECT distinct A.CUSTOMER_CD AS A_VAL,A.CUSTOMER_ABBR AS B_VAL,A.CUSTOMER_NAME AS C_VAL,A.GST_TIN_NO AS D_VAL,A.PAN_NO AS E_VAL"
						+ " FROM FMS7_CUSTOMER_MST A,DLNG_SN_MST B"
						+ " WHERE (B.SN_BASE = 'D')"
						+ " AND (A.CUSTOMER_CD= B.CUSTOMER_CD)"
						+ " union all "
						+ " SELECT distinct C.CUSTOMER_CD AS A_VAL,C.CUSTOMER_ABBR AS B_VAL,C.CUSTOMER_NAME AS C_VAL,C.GST_TIN_NO AS D_VAL,C.PAN_NO AS E_VAL"
						+ " FROM FMS7_CUSTOMER_MST C,DLNG_LOA_MST D"
						+ " WHERE (D.LOA_BASE = 'D')"
						+ " AND (C.CUSTOMER_CD= D.CUSTOMER_CD)) ORDER BY C_VAL";
//					System.out.println("fetchcustomer list----"+queryString);
					rset=stmt.executeQuery(queryString);
					while(rset.next())
					{
						Vcust_cd.add(rset.getString(1)==null?"":rset.getString(1));
						Vcust_abbr.add(rset.getString(2)==null?"":rset.getString(2));
						Vcust_nm.add(rset.getString(3)==null?"":rset.getString(3));
					}
					
					if(!cust_cd.equals("")) {
						
						fetchingCustPlantDtl(cust_cd);
						
						 queryString="SELECT GST_TIN_NO,PAN_NO,CUSTOMER_NAME FROM FMS7_CUSTOMER_MST A"
										+ " WHERE  CUSTOMER_CD = '"+cust_cd+"' "
										+ " and A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_MST B " +
										  " WHERE A.CUSTOMER_CD=B.CUSTOMER_CD and B.CUSTOMER_CD = '"+cust_cd+"')"
										+ "  order by CUSTOMER_ABBR";
						 rset=stmt.executeQuery(queryString);
						 if(rset.next()){
							cust_gstin_no = rset.getString(1)==null?"":rset.getString(1);
							cust_pan_no = rset.getString(2)==null?"":rset.getString(2);
							cust_nm = rset.getString(3)==null?"":rset.getString(3);
						}
						 
						 
						String queryString31="SELECT distinct addr,city,PIN,state,country"
								+ " FROM FMS7_CUSTOMER_ADDRESS_MST A"
								+ " WHERE A.CUSTOMER_CD='"+cust_cd+"'  AND A.address_type='B' AND " +
						  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_ADDRESS_MST B " +
						  "WHERE A.CUSTOMER_CD=B.CUSTOMER_CD AND B.address_type='B')   ";
		//								System.out.println("dhbfdjf0dfbjbdjf-----"+queryString31);
							rset=stmt.executeQuery(queryString31);
							if(rset.next())
							{
								cust_addr = rset.getString(1)==null?"":rset.getString(1);
								cust_city = rset.getString(2)==null?"":rset.getString(2);
								cust_pin = rset.getString(3)==null?"":rset.getString(3);
								cust_state = rset.getString(4)==null?"":rset.getString(4);
								cust_country = rset.getString(5)==null?"":rset.getString(5);
								
							}else {
								
								String queryString32="SELECT distinct addr,city,PIN,state,country"
										+ " FROM FMS7_CUSTOMER_ADDRESS_MST A"
										+ " WHERE A.CUSTOMER_CD='"+cust_cd+"'  AND"
										+ "  A.address_type='C' AND " +
								  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_ADDRESS_MST B " +
								  "WHERE A.CUSTOMER_CD=B.CUSTOMER_CD AND B.address_type='C' )   ";
		//										System.out.println("dhbfdjf0dfbjbdjf-----"+queryString31);
									rset=stmt.executeQuery(queryString32);
									if(rset.next())
									{
										cust_addr = rset.getString(1)==null?"":rset.getString(1);
										cust_city = rset.getString(2)==null?"":rset.getString(2);
										cust_pin = rset.getString(3)==null?"":rset.getString(3);
										cust_state = rset.getString(4)==null?"":rset.getString(4);
										cust_country = rset.getString(5)==null?"":rset.getString(5);
										
									}else {
										String queryString33="SELECT distinct addr,city,PIN,state,country"
												+ " FROM FMS7_CUSTOMER_ADDRESS_MST A"
												+ " WHERE A.CUSTOMER_CD='"+cust_cd+"'"
												+ "  AND A.address_type='R' AND " +
										  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_ADDRESS_MST B " +
										  "WHERE A.CUSTOMER_CD=B.CUSTOMER_CD AND B.address_type='R' )   ";
		//												System.out.println("dhbfdjf0dfbjbdjf-----"+queryString31);
											rset=stmt.executeQuery(queryString33);
											if(rset.next())
											{
												cust_addr = rset.getString(1)==null?"":rset.getString(1);
												cust_city = rset.getString(2)==null?"":rset.getString(2);
												cust_pin = rset.getString(3)==null?"":rset.getString(3);
												cust_state = rset.getString(4)==null?"":rset.getString(4);
												cust_country = rset.getString(5)==null?"":rset.getString(5);
												
											}
									}
							}
						}
					queryString1="SELECT STATE_code FROM STATE_MST WHERE  lower(state_nm) LIKE '%"+cust_state+"%'  " ;
//					System.out.println("query--"+queryString1);
					rset1=stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						state_cd = rset1.getString(1)==null?"":rset1.getString(1);
					}
					
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}

		String plant_no = "";
		String plant_addr = "";
		String plant_state = "";
		String plant_state_cd = "";
		String plant_city = "";
		String plant_pin = "";
		String plant_pan_no = "";
		String plant_gstin_no = "";
		String inr_km = "0";
		String inr_mmbtu = "0"; 
		private void fetchingCustPlantDtl(String cust_cd)throws Exception {
			try {
				
				queryString1 = "Select DISTINCT(A.SEQ_NO), to_char(A.EFF_DT,'dd/mm/yyyy'), A.PLANT_TYPE, A.PLANT_NAME, A.PLANT_ABBR, A.PLANT_ADDR,"+  
						" A.PLANT_STATE, A.PLANT_ZONE, A.PLANT_CITY,  A.PLANT_PIN, A.PLANT_SECTOR, A.PLANT_SHORT_ABBR from FMS7_CUSTOMER_PLANT_DTL A"+ 
						" WHERE A.CUSTOMER_CD='"+cust_cd+"' and"+ 
						" A.EFF_DT=(select max(B.eff_dt) from FMS7_CUSTOMER_PLANT_DTL B where A.seq_no=B.seq_no and B.CUSTOMER_CD='"+cust_cd+"')" +
						" order by A.SEQ_NO";
//				System.out.println("customer plant details****"+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				while(rset1.next()) {
					
					 plant_seq_no.add(rset1.getString(1)==null?"":rset1.getString(1));
					 plant_name.add(rset1.getString(4)==null?"":rset1.getString(4));
				}
				
				if(!plant_no.equals("")) {
					
					queryString1 = "Select  A.PLANT_ADDR,A.PLANT_STATE,A.PLANT_CITY,A.PLANT_PIN,A.PLANT_NAME"
							+ " from FMS7_CUSTOMER_PLANT_DTL A"+ 
								" WHERE A.CUSTOMER_CD='"+cust_cd+"' "
							+ " AND A.SEQ_NO = '"+plant_no+"' "
							+ " AND A.EFF_DT=(select max(B.eff_dt) from FMS7_CUSTOMER_PLANT_DTL B where A.seq_no=B.seq_no and B.CUSTOMER_CD='"+cust_cd+"' and A.SEQ_NO = '"+plant_no+"')";
//					System.out.println("selected customer plant details****"+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next()) {
						
						plant_addr = rset1.getString(1)==null?"":rset1.getString(1);
						plant_state = rset1.getString(2)==null?"":rset1.getString(2);
						plant_city = rset1.getString(3)==null?"":rset1.getString(3);
						plant_pin = rset1.getString(4)==null?"":rset1.getString(4);
						plant_nm = rset1.getString(5)==null?"":rset1.getString(5);
						
						String stateCode = "select STATE_CODE from STATE_MST where STATE_NM like '%"+plant_state+"%'";
//						 System.out.println("stateCode----"+stateCode);
						 rset2 = stmt2.executeQuery(stateCode);
						 if(rset2.next()) {
							 plant_state_cd = rset2.getString(1)==null?"":rset2.getString(1);
						 }else {
							 plant_state_cd = "";
						 }
//						 System.out.println("plant_state_cd----"+plant_state_cd);
						 queryString1 = "SELECT STAT_NO, TO_CHAR(EFF_DT,'dd/mm/yyyy'), REMARK " +
								   "FROM FMS7_CUSTOMER_PLANT_TAX_CDS " +
								   "WHERE customer_cd="+cust_cd+" AND " +
								   "plant_seq_no="+plant_no+" AND stat_cd = '1006'";
//						 System.out.println("PAN *****"+queryString1);
						 rset2 = stmt2.executeQuery(queryString1);
						 if(rset2.next()) {
							 plant_pan_no = rset2.getString(1)==null?"":rset2.getString(1);
						 }else {
							 plant_pan_no = "";
						 }
						 
						 queryString1 = "SELECT STAT_NO, TO_CHAR(EFF_DT,'dd/mm/yyyy'), REMARK " +
								   "FROM FMS7_CUSTOMER_PLANT_TAX_CDS " +
								   "WHERE customer_cd="+cust_cd+" AND " +
								   "plant_seq_no="+plant_no+" AND stat_cd = '1008'";
//						 System.out.println("GSTIN No *****"+queryString1);
						 rset2 = stmt2.executeQuery(queryString1);
						 if(rset2.next()) {
							 plant_gstin_no = rset2.getString(1)==null?"":rset2.getString(1);
						 }else {
							 plant_gstin_no = "";
						 }
					}
					fetchInvContactPerson();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		public void setCallFlag(String callFlag) {
			this.callFlag = callFlag;
		}


		public String getTempcompnm() {
			return tempcompnm;
		}


		public Vector getVsupp_cd() {
			return Vsupp_cd;
		}


		public Vector getVsupp_nm() {
			return Vsupp_nm;
		}


		public String getSupp_cd() {
			return supp_cd;
		}


		public void setSupp_cd(String supp_cd) {
			this.supp_cd = supp_cd;
		}


		public String getSupp_addr() {
			return supp_addr;
		}


		public String getSupp_city() {
			return supp_city;
		}


		public String getSupp_pin() {
			return supp_pin;
		}


		public String getSupp_state() {
			return supp_state;
		}


		public String getSupp_state_cd() {
			return supp_state_cd;
		}


		public String getSupp_pan_no() {
			return supp_pan_no;
		}


		public void setSupp_gstin_no(String supp_gstin_no) {
			this.supp_gstin_no = supp_gstin_no;
		}


		public String getSupp_gstin_no() {
			return supp_gstin_no;
		}

		public Vector getVcust_cd() {
			return Vcust_cd;
		}

		public Vector getVcust_abbr() {
			return Vcust_abbr;
		}

		public Vector getVcust_nm() {
			return Vcust_nm;
		}
		public String getCust_cd() {
			return cust_cd;
		}

		public void setCust_cd(String cust_cd) {
			this.cust_cd = cust_cd;
		}

		public String getCust_addr() {
			return cust_addr;
		}

		public String getCust_city() {
			return cust_city;
		}

		public String getCust_pin() {
			return cust_pin;
		}

		public String getCust_country() {
			return cust_country;
		}

		public String getCust_state() {
			return cust_state;
		}

		public String getState_cd() {
			return state_cd;
		}

		public String getCust_pan_no() {
			return cust_pan_no;
		}

		public String getCust_gstin_no() {
			return cust_gstin_no;
		}

		public Vector getPlant_seq_no() {
			return plant_seq_no;
		}

		public Vector getPlant_name() {
			return plant_name;
		}

		public String getPlant_no() {
			return plant_no;
		}

		public void setPlant_no(String plant_no) {
			this.plant_no = plant_no;
		}

		public String getPlant_addr() {
			return plant_addr;
		}

		public String getPlant_state() {
			return plant_state;
		}

		public String getPlant_state_cd() {
			return plant_state_cd;
		}

		public String getPlant_city() {
			return plant_city;
		}

		public String getPlant_pin() {
			return plant_pin;
		}

		public String getPlant_pan_no() {
			return plant_pan_no;
		}

		public String getPlant_gstin_no() {
			return plant_gstin_no;
		}

		public String getBill_cycle() {
			return bill_cycle;
		}

		public void setBill_cycle(String bill_cycle) {
			this.bill_cycle = bill_cycle;
		}

		public String getMonth() {
			return month;
		}

		public void setMonth(String month) {
			this.month = month;
		}

		public String getYear() {
			return year;
		}

		public void setYear(String year) {
			this.year = year;
		}

		public Vector getVcont_no() {
			return Vcont_no;
		}

		public Vector getVcont_rev_no() {
			return Vcont_rev_no;
		}

		public Vector getVagr_no() {
			return Vagr_no;
		}

		public Vector getVagr_rev_no() {
			return Vagr_rev_no;
		}

		public Vector getVcont_type() {
			return Vcont_type;
		}

		public Vector getVinr_km() {
			return Vinr_km;
		}

		public Vector getVinr_mmbtu() {
			return Vinr_mmbtu;
		}

		public String getPeriod_start_dt() {
			return period_start_dt;
		}

		public String getPeriod_end_dt() {
			return period_end_dt;
		}
		public String getSmapping_id() {
			return Smapping_id;
		}

		public String getSrate() {
			return Srate;
		}
		public void setSmapping_id(String smapping_id) {
			Smapping_id = smapping_id;
		}

		public void setSrate(String srate) {
			Srate = srate;
		}
		public String getChkFlg() {
			return chkFlg;
		}
		public void setChkFlg(String chkFlg) {
			this.chkFlg = chkFlg;
		}

		public String getInv_seq_no() {
			return inv_seq_no;
		}

		public String getNew_inv_seq_no() {
			return new_inv_seq_no;
		}

		public Vector getSGST_CGST_RT() {
			return SGST_CGST_RT;
		}

		public Vector getTax_desc() {
			return tax_desc;
		}

		public Vector getVInv_new_inv_seq_no() {
			return VInv_new_inv_seq_no;
		}

		public Vector getVInv_invoice_dt() {
			return VInv_invoice_dt;
		}

		public Vector getVInv_total_qty() {
			return VInv_total_qty;
		}

		public Vector getVinv_truck_nm() {
			return Vinv_truck_nm;
		}

		public Vector getVcont_start_dt() {
			return Vcont_start_dt;
		}

		public Vector getVcont_end_dt() {
			return Vcont_end_dt;
		}

		public String getInr_km() {
			return inr_km;
		}

		public String getInr_mmbtu() {
			return inr_mmbtu;
		}

		public void setInr_km(String inr_km) {
			this.inr_km = inr_km;
		}

		public void setInr_mmbtu(String inr_mmbtu) {
			this.inr_mmbtu = inr_mmbtu;
		}

		public Vector getVinv_qty_inr() {
			return Vinv_qty_inr;
		}

		public double getTotal_qty_inr() {
			return total_qty_inr;
		}

		public String getInr_km_str() {
			return inr_km_str;
		}

		public String getKm_str() {
			return km_str;
		}

		public void setInr_km_str(String inr_km_str) {
			this.inr_km_str = inr_km_str;
		}

		public String getTotal_km_inr() {
			return total_km_inr;
		}

		public void setTotal_km_inr(String total_km_inr) {
			this.total_km_inr = total_km_inr;
		}

		public void setKm_str(String km_str) {
			this.km_str = km_str;
		}

		public Vector getVinv_km_inr() {
			return Vinv_km_inr;
		}

		public Vector getVinv_km() {
			return Vinv_km;
		}

		public double getTotal_qty_mmbtu() {
			return total_qty_mmbtu;
		}

		public String getInvDt() {
			return invDt;
		}

		public String getDueDt() {
			return dueDt;
		}

		public String getCalcBase() {
			return calcBase;
		}

		public String getTax_structure() {
			return tax_structure;
		}

		public void setInvDt(String invDt) {
			this.invDt = invDt;
		}

		public void setDueDt(String dueDt) {
			this.dueDt = dueDt;
		}

		public void setCalcBase(String calcBase) {
			this.calcBase = calcBase;
		}

		public void setTax_structure(String tax_structure) {
			this.tax_structure = tax_structure;
		}

		public Vector getTax_nm() {
			return Tax_nm;
		}

		public Vector getTaxstr() {
			return taxstr;
		}

		public double getTotal_qty_km() {
			return total_qty_km;
		}
		public String getSacStr() {
			return sacStr;
		}
		public String getItemStr() {
			return itemStr;
		}
		public String getQtyStr() {
			return qtyStr;
		}
		public String getRateStr() {
			return rateStr;
		}
		public String getAmtStr() {
			return amtStr;
		}
		public String getRowno() {
			return rowno;
		}
		public void setSacStr(String sacStr) {
			this.sacStr = sacStr;
		}
		public void setItemStr(String itemStr) {
			this.itemStr = itemStr;
		}
		public void setQtyStr(String qtyStr) {
			this.qtyStr = qtyStr;
		}
		public void setRateStr(String rateStr) {
			this.rateStr = rateStr;
		}
		public void setAmtStr(String amtStr) {
			this.amtStr = amtStr;
		}
		public void setRowno(String rowno) {
			this.rowno = rowno;
		}
		public Vector getVsac_cd() {
			return Vsac_cd;
		}
		public Vector getVitem_desc() {
			return Vitem_desc;
		}
		public Vector getVqty() {
			return Vqty;
		}
		public Vector getVrate() {
			return Vrate;
		}
		public Vector getVamt() {
			return Vamt;
		}
		public double getInv_gross_amt() {
			return inv_gross_amt;
		}
		public String getContract_type() {
			return contract_type;
		}
		public void setContract_type(String contract_type) {
			this.contract_type = contract_type;
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

		public String getContact_Suppl_Name() {
			return contact_Suppl_Name;
		}

		public String getMapping_id() {
			return mapping_id;
		}

		public void setMapping_id(String mapping_id) {
			this.mapping_id = mapping_id;
		}

		public String getCustomer_Invoice_FGSA_Dt() {
			return customer_Invoice_FGSA_Dt;
		}

		public void setCustomer_Invoice_FGSA_Dt(String customer_Invoice_FGSA_Dt) {
			this.customer_Invoice_FGSA_Dt = customer_Invoice_FGSA_Dt;
		}

		public String getContact_Customer_Name() {
			return contact_Customer_Name;
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

		public String getInvoice_Customer_Contact_Cd() {
			return invoice_Customer_Contact_Cd;
		}

		public String getInvoice_Customer_Contact_Nm() {
			return invoice_Customer_Contact_Nm;
		}

		public void setPeriod_start_dt(String period_start_dt) {
			this.period_start_dt = period_start_dt;
		}

		public void setPeriod_end_dt(String period_end_dt) {
			this.period_end_dt = period_end_dt;
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

		public String getContact_Customer_Person_State() {
			return contact_Customer_Person_State;
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

		public String getContact_Customer_Plant_State() {
			return contact_Customer_Plant_State;
		}

		public String getContact_Customer_State_Code() {
			return contact_Customer_State_Code;
		}

		public String getContact_addr_flag() {
			return contact_addr_flag;
		}

		public void setContact_Suppl_CST_DT(String contact_Suppl_CST_DT) {
			this.contact_Suppl_CST_DT = contact_Suppl_CST_DT;
		}

		public String getContact_Suppl_State() {
			return contact_Suppl_State;
		}

		public String getContact_Suppl_State_Code() {
			return contact_Suppl_State_Code;
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

		public void setNew_inv_seq_no(String new_inv_seq_no) {
			this.new_inv_seq_no = new_inv_seq_no;
		}

		public String getTaxnm_str() {
			return taxnm_str;
		}

		public void setTaxnm_str(String taxnm_str) {
			this.taxnm_str = taxnm_str;
		}

		public String getTotalTax() {
			return totalTax;
		}

		public String getNetAmt() {
			return netAmt;
		}

		public void setTotalTax(String totalTax) {
			this.totalTax = totalTax;
		}

		public void setNetAmt(String netAmt) {
			this.netAmt = netAmt;
		}

		public String getTaxAmt_str() {
			return taxAmt_str;
		}

		public void setTaxAmt_str(String taxAmt_str) {
			this.taxAmt_str = taxAmt_str;
		}

		public String getGrossAmt() {
			return grossAmt;
		}

		public void setGrossAmt(String grossAmt) {
			this.grossAmt = grossAmt;
		}

		public String getFin_yr() {
			return fin_yr;
		}

		public void setFin_yr(String fin_yr) {
			this.fin_yr = fin_yr;
		}

		public double getTotal_qty() {
			return total_qty;
		}

		public void setTotal_qty(double total_qty) {
			this.total_qty = total_qty;
		}

		public Vector getYearList() {
			return yearList;
		}

		public Vector getVinvCustomer_cd() {
			return VinvCustomer_cd;
		}

		public Vector getVinvMapping_id() {
			return VinvMapping_id;
		}

		public Vector getVinvPlant_seq_no() {
			return VinvPlant_seq_no;
		}

		public Vector getVinvNew_inv_seq_no() {
			return VinvNew_inv_seq_no;
		}

		public Vector getVinvInv_seq_no() {
			return VinvInv_seq_no;
		}

		public Vector getVinvInvoice_dt() {
			return VinvInvoice_dt;
		}

		public Vector getVinvCustomer_abbr() {
			return VinvCustomer_abbr;
		}

		public Vector getVinvPlant_name() {
			return VinvPlant_name;
		}

		public Vector getVinvContNo() {
			return VinvContNo;
		}

		public Vector getVinvContType() {
			return VinvContType;
		}

		public Vector getVinvInvoice_due_dt() {
			return VinvInvoice_due_dt;
		}

		public String getInvFlag() {
			return invFlag;
		}

		public void setInvFlag(String invFlag) {
			this.invFlag = invFlag;
		}

		public Vector getVinvInvoice_fin_yr() {
			return VinvInvoice_fin_yr;
		}

		public void setInv_seq_no(String inv_seq_no) {
			this.inv_seq_no = inv_seq_no;
		}

		public String getInvGrossAmt() {
			return invGrossAmt;
		}

		public void setInvGrossAmt(String invGrossAmt) {
			this.invGrossAmt = invGrossAmt;
		}

		public String getInvNetAmt() {
			return invNetAmt;
		}

		public void setInvNetAmt(String invNetAmt) {
			this.invNetAmt = invNetAmt;
		}

		public String getInvTaxAmt() {
			return invTaxAmt;
		}

		public void setInvTaxAmt(String invTaxAmt) {
			this.invTaxAmt = invTaxAmt;
		}

		public String getRemark1() {
			return remark1;
		}

		public String getRemark2() {
			return remark2;
		}

		public void setRemark1(String remark1) {
			this.remark1 = remark1;
		}

		public void setRemark2(String remark2) {
			this.remark2 = remark2;
		}

		public String getAlwInvGen() {
			return alwInvGen;
		}

		public void setAlwInvGen(String alwInvGen) {
			this.alwInvGen = alwInvGen;
		}

		public Vector getServ_inv_no() {
			return serv_inv_no;
		}

		public Vector getServ_inv_bill_cycle() {
			return serv_inv_bill_cycle;
		}

		public void setServ_inv_no(Vector serv_inv_no) {
			this.serv_inv_no = serv_inv_no;
		}

		public void setServ_inv_bill_cycle(Vector serv_inv_bill_cycle) {
			this.serv_inv_bill_cycle = serv_inv_bill_cycle;
		}

		public String getTruckInvSz() {
			return truckInvSz;
		}

		public void setTruckInvSz(String truckInvSz) {
			this.truckInvSz = truckInvSz;
		}

		public Vector getView_amount() {
			return View_amount;
		}

		public Vector getView_invoice_qty() {
			return View_invoice_qty;
		}

		public Vector getView_km() {
			return View_km;
		}

		public Vector getView_rate() {
			return View_rate;
		}

		public Vector getView_service_inv_dt() {
			return View_service_inv_dt;
		}

		public Vector getView_truck_inv_dt() {
			return View_truck_inv_dt;
		}

		public Vector getView_truck_inv_no() {
			return View_truck_inv_no;
		}

		public Vector getView_truck_nm() {
			return View_truck_nm;
		}

		public double getTotal_amt() {
			return total_amt;
		}

		public void setTotal_amt(double total_amt) {
			this.total_amt = total_amt;
		}

		public Vector getVinvInvoice_print() {
			return VinvInvoice_print;
		}

		public Vector getVinvInvoice_bgColor() {
			return VinvInvoice_bgColor;
		}

		public Vector getIrn_flag() {
			return irn_flag;
		}

		public void setIrn_flag(Vector irn_flag) {
			this.irn_flag = irn_flag;
		}
		public String getIrn_no() {
			return irn_no;
		}
		public void setIrn_no(String irn_no) {
			this.irn_no = irn_no;
		}

		public String getQr_code() {
			return qr_code;
		}

		public void setQr_code(String qr_code) {
			this.qr_code = qr_code;
		}

		public String getLs_str() {
			return ls_str;
		}

		public void setLs_str(String ls_str) {
			this.ls_str = ls_str;
		}

		public Vector getVinv_lumpSum() {
			return Vinv_lumpSum;
		}

		public String getTotal_ls_inr() {
			return total_ls_inr;
		}

		public void setTotal_ls_inr(String total_ls_inr) {
			this.total_ls_inr = total_ls_inr;
		}

		public Vector getVPDF_File_Nm() {
			return VPDF_File_Nm;
		}

		public Map getInvoice_signded_pdf() {
			return invoice_signded_pdf;
		}

		public Map getInvoice_view_pdf() {
			return invoice_view_pdf;
		}

		public Map getInvoice_view_signed_pdf() {
			return invoice_view_signed_pdf;
		}

		public Map getInvoice_mail_sent() {
			return invoice_mail_sent;
		}

		public Vector getVinvInvoice_cont_cd() {
			return VinvInvoice_cont_cd;
		}
		public Vector getVinvInvoice_supp_cd() {
			return VinvInvoice_supp_cd;
		}
		public Vector getVlumpsum_flg() {
			return Vlumpsum_flg;
		}

		public String getLumpsumFlg() {
			return lumpsumFlg;
		}

		public void setLumpsumFlg(String lumpsumFlg) {
			this.lumpsumFlg = lumpsumFlg;
		}

		public String getSupp_nm() {
			return supp_nm;
		}

		public String getCust_nm() {
			return cust_nm;
		}

		public String getPlant_nm() {
			return plant_nm;
		}

		public String getRefreshFlg() {
			return refreshFlg;
		}

		public void setRefreshFlg(String refreshFlg) {
			this.refreshFlg = refreshFlg;
		}

		public String getCont_start_dt() {
			return cont_start_dt;
		}

		public String getCont_end_dt() {
			return cont_end_dt;
		}

		public void setCont_start_dt(String cont_start_dt) {
			this.cont_start_dt = cont_start_dt;
		}

		public void setCont_end_dt(String cont_end_dt) {
			this.cont_end_dt = cont_end_dt;
		}

		public String getSigning_dt() {
			return signing_dt;
		}

		public String getTrans_cont_no() {
			return trans_cont_no;
		}

		public String getChkBoxFlgStr() {
			return chkBoxFlgStr;
		}

		public void setChkBoxFlgStr(String chkBoxFlgStr) {
			this.chkBoxFlgStr = chkBoxFlgStr;
		}

		public Vector getVinv_chkBox_Flg() {
			return Vinv_chkBox_Flg;
		}

		public String getInr_mmbtu_str() {
			return inr_mmbtu_str;
		}

		public String getQty_mmbtu_str() {
			return qty_mmbtu_str;
		}

		public void setInr_mmbtu_str(String inr_mmbtu_str) {
			this.inr_mmbtu_str = inr_mmbtu_str;
		}

		public void setQty_mmbtu_str(String qty_mmbtu_str) {
			this.qty_mmbtu_str = qty_mmbtu_str;
		}

		public Vector getVinvChecked_by() {
			return VinvChecked_by;
		}

		public Vector getVinvChecked_flag() {
			return VinvChecked_flag;
		}

		public Vector getVinvApproved_by() {
			return VinvApproved_by;
		}

		public Vector getVinvApproved_flag() {
			return VinvApproved_flag;
		}

		public String getInv_flag() {
			return inv_flag;
		}

		public void setInv_flag(String inv_flag) {
			this.inv_flag = inv_flag;
		}

		public Vector getDr_cr_doc_no() {
			return dr_cr_doc_no;
		}

		public Vector getDr_cr_flag() {
			return dr_cr_flag;
		}

		public Vector getDr_cr_dt() {
			return dr_cr_dt;
		}

		public Vector getDr_cr_aprv_by() {
			return dr_cr_aprv_by;
		}

		public Vector getDr_cr_aprv_dt() {
			return dr_cr_aprv_dt;
		}

		public Vector getDr_cr_criteria() {
			return dr_cr_criteria;
		}

		public Vector getDr_cr_remark() {
			return dr_cr_remark;
		}

		public Vector getDr_cr_inv_flag() {
			return dr_cr_inv_flag;
		}

		public String getDrcr_doc_no() {
			return drcr_doc_no;
		}

		public void setDrcr_doc_no(String drcr_doc_no) {
			this.drcr_doc_no = drcr_doc_no;
		}

		public String getDrcr_dt() {
			return drcr_dt;
		}

		public String getDrcr_due_dt() {
			return drcr_due_dt;
		}

		public String getDrcr_flag() {
			return drcr_flag;
		}

		public String getDrcr_aprv_by() {
			return drcr_aprv_by;
		}

		public String getDrcr_aprv_dt() {
			return drcr_aprv_dt;
		}

		public String getDrcr_criteria() {
			return drcr_criteria;
		}

		public String getDrcr_remark() {
			return drcr_remark;
		}
		public double getTot_inv_qty() {
			return tot_inv_qty;
		}

		public double getTot_diff_qty() {
			return tot_diff_qty;
		}

		public double getDr_cr_rate() {
			return dr_cr_rate;
		}

		public double getDiff_rate() {
			return diff_rate;
		}

		public double getInv_rate() {
			return inv_rate;
		}

		public String getDrcr_gross_amt_inr() {
			return drcr_gross_amt_inr;
		}

		public Vector getInv_tax_perc() {
			return inv_tax_perc;
		}

		public Vector getDrcr_tax_perc() {
			return drcr_tax_perc;
		}

		public Vector getDiff_tax_perc() {
			return diff_tax_perc;
		}

		public Vector getInv_tax_amt() {
			return inv_tax_amt;
		}

		public Vector getDrcr_tax_amt() {
			return drcr_tax_amt;
		}

		public Vector getDiff_tax_amt() {
			return diff_tax_amt;
		}

		public double getDrcr_tax_samt() {
			return drcr_tax_samt;
		}

		public double getDrcr_net_amt_inr() {
			return drcr_net_amt_inr;
		}

		public String getDrcr_remark1() {
			return drcr_remark1;
		}

		public String getDrcr_remark2() {
			return drcr_remark2;
		}

		public double getTotal_inv_km() {
			return total_inv_km;
		}

		public String getRate_unit() {
			return rate_unit;
		}

		public Vector getView_calc_bs() {
			return View_calc_bs;
		}

		public Vector getView_diff_qty() {
			return View_diff_qty;
		}

		public Vector getView_dr_cr_qty() {
			return View_dr_cr_qty;
		}

		public String getFormated_inv_dt() {
			return formated_inv_dt;
		}

		public double getGross_amt_inr() {
			return gross_amt_inr;
		}

		public Vector getDr_cr_irn_cnt() {
			return dr_cr_irn_cnt;
		}

}
