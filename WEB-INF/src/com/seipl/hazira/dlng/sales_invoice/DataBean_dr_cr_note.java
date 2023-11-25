package com.seipl.hazira.dlng.sales_invoice;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.seipl.hazira.dlng.report.DataBean_Advacne_Tracker;
import com.seipl.hazira.dlng.util.RuntimeConf;

public class DataBean_dr_cr_note {

	Connection conn; 
	public String getTaxnm() {
		return taxnm;
	}

	public void setTaxnm(String taxnm) {
		this.taxnm = taxnm;
	}

	Statement stmt;
	Statement stmt1;
	Statement stmt2;
	Statement stmt3;
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
	String inv_typ = "";
	
	NumberFormat nf = new DecimalFormat("###########0.00");
	NumberFormat nf_ = new DecimalFormat("############.##");
	NumberFormat nf_dr = new DecimalFormat("###.####");
	NumberFormat nf1 = new DecimalFormat("##.########");
	NumberFormat nf2 = new DecimalFormat("##0.0000"); //For Currency Purpose ... Defined By Samik Shah On 1st June, 2010 ...
	NumberFormat nf3 = new DecimalFormat("###,###,###,##0.00");
	NumberFormat nf4 = new DecimalFormat("##0.000");
	NumberFormat nf5 = new DecimalFormat("##########0");
	
	
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
	    			
	    			createColumn();
	    			
	    			if(callFlag.equals("InvAvailableFY")) {
	    				fetchInvAvailableFY();
	    			}else if(callFlag.equals("dr_cr_note")) {
	    				fetchDr_CrDetails();
	    				Customer_DTL();
	    			}else if(callFlag.equalsIgnoreCase("fetchINV_Details")){
	    				fetchINV_Details(); 
	    				Customer_DTL();
	    				Customer_NM();
					}else if(callFlag.equalsIgnoreCase("Inv_dates"))
	    			{
						createColumn_inv_DATES();
	    				Inv_dates();
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
			}if(stmt1 != null)
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
	    	
	    }
	}
	
	public void createColumn()throws SQLException
	{
		try
		{
			int count=0;
			String query="SELECT COUNT(COLUMN_NAME) FROM COLS WHERE UPPER(TABLE_NAME) LIKE 'DLNG_DR_CR_NOTE' "
					+ "AND UPPER(COLUMN_NAME) LIKE 'DR_CR_TCS_FLAG' ";
			rset=stmt.executeQuery(query);
			if(rset.next())
			{
				count=rset.getInt(1);
			}
			if(count==0)
			{
				query="alter table DLNG_DR_CR_NOTE add DR_CR_TCS_FLAG CHAR(1 BYTE)";
				stmt.executeUpdate(query);
				conn.commit();
			}
			
			int count1=0;
			String query1="SELECT COUNT(COLUMN_NAME) FROM COLS WHERE UPPER(TABLE_NAME) LIKE 'DLNG_DR_CR_NOTE' "
					+ "AND UPPER(COLUMN_NAME) LIKE 'DR_CR_QTY' ";
			rset=stmt.executeQuery(query1);
			if(rset.next())
			{
				count1=rset.getInt(1);
			}
			if(count1==0)
			{
				query1="alter table DLNG_DR_CR_NOTE add DR_CR_QTY NUMBER(11,2)";
				stmt.executeUpdate(query1);
				conn.commit();
			}
			
			int count2=0;
			String query2="SELECT COUNT(COLUMN_NAME) FROM COLS WHERE UPPER(TABLE_NAME) LIKE 'DLNG_DR_CR_NOTE' "
					+ "AND UPPER(COLUMN_NAME) LIKE 'DIFF_TAX' ";
			rset=stmt.executeQuery(query2);
			if(rset.next())
			{
				count2=rset.getInt(1);
			}
			if(count2==0)
			{
				query1="alter table DLNG_DR_CR_NOTE add DIFF_TAX NUMBER(3,2)";
				stmt.executeUpdate(query1);
				conn.commit();
			}
			
			count=0;
			query="SELECT COUNT(COLUMN_NAME) FROM COLS WHERE UPPER(TABLE_NAME) LIKE 'DLNG_DR_CR_NOTE' "
					+ "AND UPPER(COLUMN_NAME) in ('DR_CR_INR_MMBTU','DIFF_INR_MMBTU','DR_CR_INR_KM','DIFF_INR_KM') ";
			rset=stmt.executeQuery(query);
			if(rset.next())
			{
				count=rset.getInt(1);
			}
			if(count==0)
			{
				query="alter table DLNG_DR_CR_NOTE add"
						+ " (DR_CR_INR_MMBTU NUMBER(10,2),"
						+ " DIFF_INR_MMBTU NUMBER(10,2),"
						+ " DR_CR_INR_KM NUMBER(10,2),"
						+ " DIFF_INR_KM NUMBER(10,2))";
				stmt.executeUpdate(query);
				conn.commit();
			}
			
			count2=0;
			query2="SELECT COUNT(COLUMN_NAME) FROM COLS WHERE UPPER(TABLE_NAME) LIKE 'DLNG_DR_CR_NOTE' "
					+ "AND UPPER(COLUMN_NAME) IN ('DR_CR_TAX_RATE','DIFF_TAX_STR') ";
			rset=stmt.executeQuery(query2);
			if(rset.next())
			{
				count2=rset.getInt(1);
			}
			if(count2==0)
			{
				query="alter table DLNG_DR_CR_NOTE add"
						+ " (DR_CR_TAX_RATE NUMBER(5,2),"
						+ " DIFF_TAX_STR VARCHAR2 (20 BYTE))";
				stmt.executeUpdate(query);
				conn.commit();
			}
			
			count2=0;
			query2="SELECT COUNT(COLUMN_NAME) FROM COLS WHERE UPPER(TABLE_NAME) LIKE 'DLNG_DR_CR_DTL' "
					+ "AND UPPER(COLUMN_NAME) LIKE 'SEQ_NO' ";
			rset=stmt.executeQuery(query2);
			if(rset.next())
			{
				count2=rset.getInt(1);
			}
			if(count2==0)
			{
				query="ALTER TABLE DLNG_DR_CR_DTL ADD SEQ_NO NUMBER(5) DEFAULT '1' NOT NULL";
				stmt.executeUpdate(query);
				
				query="ALTER TABLE DLNG_DR_CR_DTL DROP CONSTRAINT DLNG_DR_CR_DTL_PK";
				stmt.executeUpdate(query);
				
				query="ALTER TABLE DLNG_DR_CR_DTL ADD CONSTRAINT DLNG_DR_CR_DTL_PK PRIMARY KEY (CUSTOMER_CD,HLPL_INV_SEQ_NO,CON_TYPE,FINANCIAL_YEAR,SEQ_NO)";
				stmt.executeUpdate(query);

				conn.commit();
			}

			count2=0;
			query2="SELECT COUNT(COLUMN_NAME) FROM COLS WHERE UPPER(TABLE_NAME) LIKE 'DLNG_DR_CR_NOTE' "
					+ "AND UPPER(COLUMN_NAME) LIKE 'SEQ_NO' ";
			rset=stmt.executeQuery(query2);
			if(rset.next())
			{
				count2=rset.getInt(1);
			}
			if(count2==0)
			{
				query="ALTER TABLE DLNG_DR_CR_NOTE ADD SEQ_NO NUMBER(5) DEFAULT '1' NOT NULL";
				stmt.executeUpdate(query);
				
				query="ALTER TABLE DLNG_DR_CR_NOTE DROP CONSTRAINT SYS_C0015236";
				stmt.executeUpdate(query);
				
				query="ALTER TABLE DLNG_DR_CR_NOTE ADD CONSTRAINT DLNG_DR_CR_NOTE_PK PRIMARY KEY (SN_NO,FGSA_NO,CUSTOMER_CD,PLANT_SEQ_NO,CONTRACT_TYPE,HLPL_INV_SEQ_NO,FINANCIAL_YEAR,CRITERIA,SUP_STATE_CODE,SEQ_NO)";
				stmt.executeUpdate(query);

				conn.commit();
			}
			
			count2=0;
			query2="SELECT COUNT(COLUMN_NAME) FROM COLS WHERE UPPER(TABLE_NAME) LIKE 'DLNG_DR_CR_DTL' "
					+ "AND UPPER(COLUMN_NAME) LIKE 'NEW_INV_SEQ_NO' ";
			rset=stmt.executeQuery(query2);
			if(rset.next())
			{
				count2=rset.getInt(1);
			}
			if(count2==0)
			{
				query="ALTER TABLE DLNG_DR_CR_DTL ADD NEW_INV_SEQ_NO VARCHAR2(15 BYTE) ";
				stmt.executeUpdate(query);
				
				conn.commit();
			}
			
			count2=0;
			query2="SELECT COUNT(COLUMN_NAME) FROM COLS WHERE UPPER(TABLE_NAME) LIKE 'DLNG_DR_CR_DTL' "
					+ "AND UPPER(COLUMN_NAME) LIKE 'DR_CR_DOC_NO' ";
			rset=stmt.executeQuery(query2);
			if(rset.next())
			{
				count2=rset.getInt(1);
			}
			if(count2==0)
			{
				query="ALTER TABLE DLNG_DR_CR_DTL ADD DR_CR_DOC_NO VARCHAR2(20 BYTE) ";
				stmt.executeUpdate(query);
				
				conn.commit();
			}
			
			count2=0;
			query2="SELECT COUNT(COLUMN_NAME) FROM COLS WHERE UPPER(TABLE_NAME) LIKE 'DLNG_DR_CR_NOTE' "
					+ "AND UPPER(COLUMN_NAME) LIKE 'NEW_INV_SEQ_NO' ";
			rset=stmt.executeQuery(query2);
			if(rset.next())
			{
				count2=rset.getInt(1);
			}
			if(count2==0)
			{
				query="ALTER TABLE DLNG_DR_CR_NOTE ADD NEW_INV_SEQ_NO VARCHAR2(15 BYTE) ";
				stmt.executeUpdate(query);
				
				conn.commit();
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	private void Inv_dates()throws SQLException,IOException {

		try
		{
//			System.out.println("view_flag****"+view_flag);
			total_diff_qty = 0 ;
			if(view_flag.equals("Y")) {
				
				queryString="SELECT SN_NO,FGSA_NO,CUSTOMER_CD,PLANT_SEQ_NO,CONTRACT_TYPE,HLPL_INV_SEQ_NO," +
						"FINANCIAL_YEAR,SN_REV_NO,FGSA_REV_NO,TO_CHAR(INVOICE_DT,'DD/MM/YYYY'),TOTAL_QTY,SALE_PRICE," +
						"GROSS_AMT_INR,NET_AMT_INR,CRITERIA,TO_CHAR(DUE_DT,'DD/MM/YYYY'),EXCHG_RATE_VALUE,DR_CR_FLAG," +
						"DR_CR_NO,TO_CHAR(DR_CR_DT,'DD/MM/YYYY'),DR_CR_EXG_RATE,DIFF_QTY,DIFF_AMT,DAY_DIFF," +
						"INT_RATE_TYPE,INT_RATE,INT_AMT,REMARK,DR_CR_DOC_NO,DR_CR_GROSS_AMT_INR,DR_CR_NET_AMT_INR," +
						"DR_CR_GROSS_AMT_USD,GROSS_AMT_USD,TAX_AMT_INR,TAX_STRUCT_CD,DR_CR_FIN_YEAR,DR_CR_SALE_RATE,item_description,tax_remark "
						+ " "
						+ "FROM DLNG_DR_CR_NOTE Where CUSTOMER_CD='"+customer_cd+"' " +
						"AND SN_NO='"+sn_no+"' AND FGSA_NO='"+fgsa_no+"' AND PLANT_SEQ_NO='"+plant_no+"' AND " +
						"CONTRACT_TYPE='"+contract_type+"' AND HLPL_INV_SEQ_NO='"+hlplInvoiceNo +"' AND " +
						"FINANCIAL_YEAR='"+fin_year+"' AND CRITERIA='"+criteria+"' and DR_CR_FLAG='"+cr_dr1+"' and SEQ_NO = '"+dr_cr_seq_no+"' ";
					System.out.println("Query For Fetching DR CR  = "+queryString);
					rset = stmt.executeQuery(queryString);				
					if(rset.next())
					{
						invoice_dt = rset.getString(10)==null?"":rset.getString(10); 
						tot_qty = rset.getString(11)==null?"0":rset.getString(11);
						sale_rate = rset.getString(12)==null?"0":nf2.format(Double.parseDouble(rset.getString(12)));
						gross_amt_inr = rset.getString(13)==null?"0":nf3.format(Double.parseDouble(rset.getString(13)));
						net_amt_inr = rset.getString(14)==null?"0":nf3.format(Double.parseDouble(rset.getString(14)));
						exch_rate = rset.getString(17)==null?"0":nf2.format(Double.parseDouble(rset.getString(17)));
						tax_amt_inr = rset.getString(34)==null?"":nf3.format(Double.parseDouble(rset.getString(34)));
//						new_inv_seq_no = rset.getString(38)==null?"":rset.getString(38);
						
						dr_cr_sales_rate = rset.getString(37)==null?"":nf2.format(Double.parseDouble(rset.getString(37)));
						dr_cr_no 	  = rset.getString(19)==null?"":rset.getString(19);
						dr_cr_dt 	  = rset.getString(20)==null?"":rset.getString(20);
						dr_cr_exg_rt  = rset.getString(21)==null?"":nf2.format(Double.parseDouble(rset.getString(21)));						
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
							grossamttax=Double.parseDouble(rset.getString(30)==null?"0.00":rset.getString(30));
						}
						if(dr_cr_net_amt.trim().equals(""))
						{
							dr_cr_net_amt  = rset.getString(31)==null?"":nf3.format(Double.parseDouble(rset.getString(31)));
						}
						dr_cr_gross_usd = rset.getString(32)==null?"":rset.getString(32);
						gross_amt_usd = rset.getString(33)==null?"":rset.getString(33);
						dr_cr_no_disp  = rset.getString(19)==null?"":rset.getString(19)+"/"+rset.getString(36).substring(2,4)+"-"+rset.getString(36).substring(7);
						dr_cr_DUEDT = rset.getString(16)==null?"":rset.getString(16);
						item_desc = rset.getString(38)==null?"":rset.getString(38);
						tax_rmk = rset.getString(39)==null?"":rset.getString(39);
						tax_struc_cd=rset.getString(35)==null?"":rset.getString(35);
						
						if(!exch_rate.equals("") && !dr_cr_exg_rt.equals("")) {
							if(cr_dr1.equals("cr")) {
								diff_exg = nf2.format(Double.parseDouble(exch_rate+"") - Double.parseDouble(dr_cr_exg_rt+"")); 
							}else {
								//System.out.println("dr_cr_exg_rt----"+dr_cr_exg_rt+"***exch_rate-----"+exch_rate);
								diff_exg =  nf2.format(Double.parseDouble(dr_cr_exg_rt+"") -Double.parseDouble(exch_rate+"")); 
							}
						}
					}
					if(!tax_rmk.equals("")){
						String taxstr[]=tax_rmk.split(",");
						for(int k=0;k<taxstr.length;k++){
							String taxrt[]=taxstr[k].split("@");
							Mtaxrt.add(taxrt[1]);
							queryString1 = "SELECT tax_name,sht_nm FROM FMS7_TAX_MST WHERE " +
									  "tax_code='"+taxrt[0]+"'";
							//System.out.println("Query For Fetching Tax Name = "+queryString);
							rset2 = stmt2.executeQuery(queryString1);
							if(rset2.next())
							{
								String taxnm=rset2.getString(1)==null?"":rset2.getString(1);
								Mtaxnm+=taxnm+" @ "+taxrt[1]+"%"+",";
								//customer_Invoice_Tax_Name.add(rset2.getString(1)==null?"":rset2.getString(1));
							}else{
								///customer_Invoice_Tax_Name.add("");
							}
						}
					}
					double tax_amt = 0;
					String tax_cd = "0";
					String tax_factor = "0.00";
					double tot_tax_amt= 0;
						queryString = "SELECT NVL(A.tax_code,'0'), NVL(A.factor,'0.00'), NVL(A.tax_on,'1'), NVL(A.tax_on_cd,'0'), " +
									  "TO_CHAR(A.app_date,'DD/MM/YYYY') FROM FMS7_TAX_STRUCTURE_DTL A WHERE A.tax_str_cd='"+tax_struc_cd+"' AND " +
									  "A.app_date=(SELECT MAX(B.app_date) FROM FMS7_TAX_STRUCTURE_DTL B WHERE B.tax_str_cd='"+tax_struc_cd+"' AND " +
									  "B.app_date<=TO_DATE('"+invoice_dt+"','DD/MM/YYYY')) ORDER BY A.tax_code";
						//System.out.println("queryString*******"+queryString);
						rset1=stmt1.executeQuery(queryString);
						while(rset1.next())
						{
							tax_cd = rset1.getString(1);
							tax_factor = rset1.getString(2);
							
//							customer_Invoice_Tax_Code.add(tax_cd);
//							customer_Invoice_Tax_Rate.add(nf.format(Double.parseDouble(tax_factor)));
							if(rset1.getString(3).equals("1"))
							{
								if(grossamttax>0)
								{
									tax_amt = (grossamttax* Double.parseDouble(rset1.getString(2)))/100;
								}
							}
							
							String tax_nm="";
							queryString1 = "SELECT tax_name,sht_nm FROM FMS7_TAX_MST WHERE " +
									  "tax_code='"+tax_cd+"'";
							//System.out.println("Query For Fetching Tax Name = "+queryString);
							rset2 = stmt2.executeQuery(queryString1);
							if(rset2.next())
							{
								tax_nm=rset2.getString(1)==null?"":rset2.getString(1);
								//customer_Invoice_Tax_Name.add(rset2.getString(1)==null?"":rset2.getString(1));
							}else{
								///customer_Invoice_Tax_Name.add("");
							}
							
							tax_str_nm+=tax_nm+" : "+tax_factor+" %,";
							tax_fact+=tax_factor+",";
							if(contract_type.equals("S") || contract_type.equals("L")){
								tot_tax_amt+=Double.parseDouble(nf_.format(tax_amt));
							}else{
							tot_tax_amt+=Math.round(tax_amt);
							}
						}
						if(contract_type.equals("S") || contract_type.equals("L")){
							tax_amt_inr_MODIFY = nf_.format(tot_tax_amt);
							}else{
								tax_amt_inr_MODIFY = nf.format(tot_tax_amt);
							}

					if(cr_dr1.equalsIgnoreCase("dr")){
						queryString="SELECT HLPL_INV_SEQ_NO,TCS_AMT FROM FMS7_INVOICE_TCS_DTL WHERE CUSTOMER_CD='"+customer_cd+"' "
							+ "AND FINANCIAL_YEAR='"+fin_year+"' and hlpl_inv_seq_no='"+dr_cr_no+"' and contract_type='"+contract_type+"'"
							+ " AND (INVOICE_TYPE='DEBIT') AND FLAG='Y' and commodity_type='DLNG' ";
						rset=stmt.executeQuery(queryString);
					}else {
						queryString="SELECT HLPL_INV_SEQ_NO,TCS_AMT FROM FMS7_INVOICE_TCS_DTL WHERE CUSTOMER_CD='"+customer_cd+"' "
								+ "AND FINANCIAL_YEAR='"+fin_year+"' and hlpl_inv_seq_no='"+dr_cr_no+"' and contract_type='"+contract_type+"'"
								+ " AND (INVOICE_TYPE='CREDIT') AND FLAG='Y' and commodity_type='DLNG' ";
						rset=stmt.executeQuery(queryString);
					}
					//System.out.println("queryString*********tcs--"+queryString);
					if(rset.next()){
						tcs_app_flag="Y";
						tcs_amt = rset.getString(2)==null?"":rset.getString(2);
					}
					if(tcs_app_flag.equals("Y")){
						queryString="SELECT TAX_CODE,FACTOR FROM FMS7_TAX_STRUCTURE_DTL A WHERE TAX_STR_CD='22' AND APP_DATE=(SELECT MAX(B.APP_DATE) "
								+ "FROM FMS7_TAX_STRUCTURE_DTL B WHERE A.TAX_STR_CD=B.TAX_STR_CD AND B.TAX_STR_CD='22' AND " +
								  "B.APP_DATE<=TO_DATE('"+dr_cr_dt+"','DD/MM/YYYY'))";
								// "B.APP_DATE<=TO_DATE('01/08/2020','DD/MM/YYYY'))";
						rset=stmt.executeQuery(queryString);
						//System.out.println("queryString---"+queryString);
						if(rset.next()){
							fact=nf4.format(Double.parseDouble(rset.getString(2)==null?"0":rset.getString(2)));
							
							queryString1 = "SELECT sht_nm,tax_name FROM FMS7_TAX_MST WHERE " +
									  "tax_code="+rset.getString(1)+"";
							//System.out.println("Query For Fetching Tax Name = "+queryString);
							rset1 = stmt1.executeQuery(queryString1);
							if(rset1.next())
							{
								tcs_nm=rset1.getString(2)==null?"":rset1.getString(2);
								tcs_sht_nm=rset1.getString(1)==null?"":rset1.getString(1);
								tcs_nm=tcs_nm+" ("+tcs_sht_nm+")";
							}
						}
					}
				
			} else {
				//System.out.println("in thisss"+inv_no);
				
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
					////System.out.println("FMS7_CUSTOMER_MST Fetch Query = "+queryString);
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
				
				
				 if(contract_type.equals("V")) {
				  
					 String fetchTaxDtl = "select TAX_DETAILS,TAX_CD,nvl(RATE_IGST,0),nvl(RATE_CGST,0),nvl(RATE_SGST,0)"
					 		+ " from DLNG_SERVICE_INVOICE_DTL where "
					 		+ " INV_SEQ_NO = '"+hlplInvoiceNo+"' "
					 		+ " and EFF_DT = to_date('"+inv_dt+"','dd/mm/yyyy')"
					 		+ " and contract_type = 'V'";
//					 System.out.println("fetchTaxDtl****"+fetchTaxDtl);
					 rset1 = stmt1.executeQuery(fetchTaxDtl);
					 if(rset1.next()) {
						 tax_str_nm = rset1.getString(1)==null?"":rset1.getString(1);
						 customer_Invoice_Tax_Code.add(rset1.getString(2)==null?"":rset1.getString(2));
						 tax_td = rset1.getString(2)==null?"":rset1.getString(2);
						 double igst_tax_perc = rset1.getDouble(3);
						 double cgst_tax_perc = rset1.getDouble(4);
						 double sgst_tax_perc = rset1.getDouble(5);

						 String appSql = "";
						
						 if(tax_td.equalsIgnoreCase("I")) {
							 serv_total_tax_perc = igst_tax_perc;
							
							 if(cr_dr1.equalsIgnoreCase("dr")) {
								 appSql = " AND RATE > "+igst_tax_perc+" ";
							 }else if(cr_dr1.equalsIgnoreCase("cr")) {
								 appSql = " AND RATE < "+igst_tax_perc+" ";
							 }
//							 for diff-tax criteria
							 String rateSql = "SELECT nvl(RATE,0) FROM GST_CODE_MST "
									+ " WHERE ABBR LIKE '%IGST' "
									+ " and GST_CODE!='IGSTEX' "
									+ " "+appSql+" "
									+ " order by rate";
//							 System.out.println("rateSql**igst**"+rateSql);
							 rset2 = stmt2.executeQuery(rateSql);
							 while (rset2.next()) {
								double rate_gst = rset2.getDouble(1);
								serv_tax_perc.add(nf_.format(rate_gst));
								String tax_struct="IGST-"+rate_gst+"%";
								serv_tax_desc.add(tax_struct);
							 }
						 }else {
							 serv_total_tax_perc = cgst_tax_perc+sgst_tax_perc;
							 
							 if(cr_dr1.equalsIgnoreCase("dr")) {
								 appSql = " AND RATE > "+serv_total_tax_perc+" ";
							 }else if(cr_dr1.equalsIgnoreCase("cr")) {
								 appSql = " AND RATE < "+serv_total_tax_perc+" ";
							 }
							 
							 //for diff-tax criteria
							 String rateSql = "SELECT nvl(RATE,0) FROM GST_CODE_MST "
									+ " WHERE ABBR LIKE '%SGST' "
									+ " and GST_CODE!='SGSTEX' "
									+ " "+appSql+" "
									+ " order by rate";
//							 System.out.println("rateSql**sgst**"+rateSql);
							 rset2 = stmt2.executeQuery(rateSql);
							 while (rset2.next()) {
								double rate_gst = (rset2.getDouble(1)/2);
								serv_tax_perc.add(nf_.format(rset2.getDouble(1)));
								String tax_struct="CGST,SGST-"+rate_gst+"%";
								serv_tax_desc.add(tax_struct);
							 }
						 }
						 
						 customer_Invoice_Tax_Rate.add("");
						 customer_Invoice_Tax_Name.add("");
						 customer_Invoice_Tax_Amt.add("");
						 
						 /*fetching attachement details*/
//						 System.out.println("criteria***"+criteria);
						 if(calc_base.equalsIgnoreCase("3") || criteria.contains("DIFF-KM") || criteria.contains("DIFF-QTY") ) {
							
							 String attachSql = "SELECT nvl(AMOUNT,'0'),nvl(INVOICE_QTY,'0'),KM,RATE,to_char(SERVICE_INV_DT,'dd/mm/yyyy'),"
									+ " to_char(TRUCK_INV_DT,'dd/mm/yyyy'),TRUCK_INV_NO,TRUCK_NM "
									+ " FROM DLNG_SERVICE_INVOICE_ATTACH "
									+ " WHERE SERVICE_INVOICE_NO = '"+inv_no+"' "
									+ " AND SERVICE_INV_DT = to_date('"+inv_dt+"','dd/mm/yyyy') ";
//							System.out.println("attachSql---"+attachSql);
							rset1 = stmt1.executeQuery(attachSql);
							while (rset1.next()) {
								
								View_amount.add(rset1.getString(1) == null?"":rset1.getString(1));
								View_invoice_qty.add(rset1.getString(2) == null?"":rset1.getString(2));
								View_km.add(rset1.getString(3) == null?"":rset1.getString(3));
								View_rate.add(rset1.getString(4) == null?"":rset1.getString(4));
								View_service_inv_dt.add(rset1.getString(5) == null?"":rset1.getString(5));
								View_truck_inv_dt.add(rset1.getString(6) == null?"":rset1.getString(6));
								View_truck_inv_no.add(rset1.getString(7) == null?"":rset1.getString(7));
								View_truck_nm.add(rset1.getString(8) == null?"":rset1.getString(8));
								
								total_qty+=rset1.getDouble(2);
								String tempAmt = "0"; 
								if(rset1.getString(1).contains(",")) {
									tempAmt = rset1.getString(1).replace(",","");
								}else {
									tempAmt = rset1.getString(1);
								}
								total_amt+=Double.parseDouble(tempAmt+"");
								int truckInvDrCrCnt = 0 ;
								double dr_cr_qty = 0 , diff_qty = 0 ;
								if(criteria.contains("DIFF-QTY")) { //checking for truck invoice's DR/CR note
									
									String invSeqSql = "select nvl(DR_CR_QTY,0),nvl(DIFF_QTY,0),A.CRITERIA "
											+ " from DLNG_DR_CR_NOTE A,DLNG_INVOICE_MST B"
											+ " where B.new_inv_seq_no = '"+rset1.getString(7)+"' "
											+ " and B.invoice_dt = to_date('"+rset1.getString(6)+"','dd/mm/yyyy') "
											+ " and B.HLPL_INV_SEQ_NO = A.HLPL_INV_SEQ_NO  and B.SN_NO = A.SN_NO and B.FGSA_NO = A.FGSA_NO"
											+ " and B.CUSTOMER_CD = A.CUSTOMER_CD and B.FINANCIAL_YEAR = A.FINANCIAL_YEAR "
											+ " and B.PLANT_SEQ_NO = A.PLANT_SEQ_NO"
											+ " and B.CONTRACT_TYPE = A.CONTRACT_TYPE ";
//											+ " and B.SUP_STATE_CODE = A.SUP_STATE_CODE";
//									System.out.println("check Invoice DR CR---"+invSeqSql);
									rset2 = stmt2.executeQuery(invSeqSql);
									if(rset2.next()) {
										String inv_dr_cr_criteria = rset2.getString(3)==null?"":rset2.getString(3);
										if(inv_dr_cr_criteria.contains("DIFF-QTY")) {
											truckInvDrCrCnt++;
											dr_cr_qty = rset2.getDouble(1);
											diff_qty = rset2.getDouble(2);
											total_diff_qty+=diff_qty;
											total_dr_cr_qty+=dr_cr_qty;
										}
									}
								}
								view_truck_inv_cnt.add(truckInvDrCrCnt);
								view_truck_inv_dr_cr_qty.add(dr_cr_qty);
								view_truck_inv_dr_cr_diff_qty.add(diff_qty);
							}
//							tot_qty = nf.format(total_amt);
						 }
					 }
				 }else {
				
						queryString = "SELECT NVL(A.tax_code,'0'), NVL(A.factor,'0.00'), NVL(A.tax_on,'1'), NVL(A.tax_on_cd,'0'), " +
									  "TO_CHAR(A.app_date,'DD/MM/YYYY') FROM FMS7_TAX_STRUCTURE_DTL A WHERE A.tax_str_cd='"+tax_struc_cd+"' AND " +
									  "A.app_date=(SELECT MAX(B.app_date) FROM FMS7_TAX_STRUCTURE_DTL B WHERE B.tax_str_cd='"+tax_struc_cd+"' AND " +
									  "B.app_date<=TO_DATE('"+inv_dt+"','DD/MM/YYYY')) ORDER BY A.tax_code";
//						System.out.println("tax perc****"+queryString);
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
	//						System.out.println("tax_amt*******"+tax_amt);
							customer_Invoice_Tax_Code.add(tax_cd);
							customer_Invoice_Tax_Rate.add(nf.format(Double.parseDouble(tax_factor)));
							String tax_nm="";
							queryString1 = "SELECT tax_name,sht_nm FROM FMS7_TAX_MST WHERE " +
									  "tax_code='"+tax_cd+"'";
							//System.out.println("Query For Fetching Tax Name = "+queryString);
							rset2 = stmt2.executeQuery(queryString1);
							if(rset2.next())
							{
								tax_nm=rset2.getString(2)==null?"":rset2.getString(2);
								if(tax_nm.contains("VAT") || tax_nm.contains("CST")){
									tax_nm=tax_nm.substring(0,3);
								}
								customer_Invoice_Tax_Name.add(tax_nm);
							}else{
								customer_Invoice_Tax_Name.add("");
							}
							
	//						tax_str_nm+=tax_factor+"% "+tax_nm+",";
							tax_str_nm+=tax_nm+" : "+tax_factor+" %,";
							tax_fact+=tax_factor+",";
							if(date_flag) {
								customer_Invoice_Tax_Amt.add(nf.format(Math.round(tax_amt)));
								if(contract_type.equals("S") || contract_type.equals("L")){
									tot_tax_amt+=Double.parseDouble(nf_.format(tax_amt));
								}else{
								tot_tax_amt+=Math.round(tax_amt);
								}
							} else {
								customer_Invoice_Tax_Amt.add(nf.format(tax_amt));
								tot_tax_amt+=tax_amt;
							}
						}
					}
					if(contract_type.equals("S") || contract_type.equals("L")){
						tax_amt_inr = nf_.format(tot_tax_amt);
					}else{
						tax_amt_inr = nf.format(tot_tax_amt);
					}
//				System.out.println("tax_amt_inr****hiren*"+tax_amt_inr);
				if(!(drcrgrossamt.trim().equals("")))
				{
					if(date_flag) {
						//System.out.println("in thisss dr_cr_net_amt in date flag--"+dr_cr_net_amt);
						if(contract_type.equals("S") || contract_type.equals("L")){
							dr_cr_net_amt = nf.format(Double.parseDouble(drcrgrossamt) + Double.parseDouble(tax_amt_inr));
						}else{
							dr_cr_net_amt = nf.format(Math.round(Double.parseDouble(drcrgrossamt) + Double.parseDouble(tax_amt_inr)));
						}
					} else {
						dr_cr_net_amt = nf.format(Double.parseDouble(drcrgrossamt) + Double.parseDouble(tax_amt_inr));
					}
					
				}
//				System.out.println("in thisss dr_cr_net_amt--"+drcrgrossamt+"**********"+tax_amt_inr);
				
				if(!drcrgrossamt.equalsIgnoreCase("")  || !drcrgrossamt.equalsIgnoreCase("null")){	
					
				queryString="SELECT SN_NO,FGSA_NO,CUSTOMER_CD,PLANT_SEQ_NO,CONTRACT_TYPE,HLPL_INV_SEQ_NO," +
					"FINANCIAL_YEAR,SN_REV_NO,FGSA_REV_NO,TO_CHAR(INVOICE_DT,'DD/MM/YYYY'),TOTAL_QTY,SALE_PRICE," +
					"GROSS_AMT_INR,NET_AMT_INR,CRITERIA,TO_CHAR(DUE_DT,'DD/MM/YYYY'),EXCHG_RATE_VALUE,DR_CR_FLAG," +
					"DR_CR_NO,TO_CHAR(DR_CR_DT,'DD/MM/YYYY'),DR_CR_EXG_RATE,DIFF_QTY,DIFF_AMT,DAY_DIFF," +
					"INT_RATE_TYPE,INT_RATE,INT_AMT,REMARK,DR_CR_DOC_NO,DR_CR_GROSS_AMT_INR,DR_CR_NET_AMT_INR," +
					"DR_CR_GROSS_AMT_USD,GROSS_AMT_USD,TAX_AMT_INR,TAX_STRUCT_CD,DR_CR_FIN_YEAR,DR_CR_SALE_RATE,"
					+ " NVL(APRV_BY,'0'),item_description,nvl(tax_remark,'0'),DR_CR_TCS_FLAG,DR_CR_QTY,DIFF_TAX,"
					+ "DIFF_INR_KM,DIFF_INR_MMBTU,DR_CR_INR_KM,DR_CR_INR_MMBTU,DR_CR_TAX_RATE,DIFF_TAX_STR "
					+ " FROM DLNG_DR_CR_NOTE Where CUSTOMER_CD='"+customer_cd+"' " +
					"AND SN_NO='"+sn_no+"' AND FGSA_NO='"+fgsa_no+"' AND PLANT_SEQ_NO='"+plant_no+"' AND " +
					"CONTRACT_TYPE='"+contract_type+"' AND HLPL_INV_SEQ_NO='"+hlplInvoiceNo +"' AND " +
					"FINANCIAL_YEAR='"+fin_year+"' AND CRITERIA='"+criteria+"' and DR_CR_FLAG='"+cr_dr1+"' and SEQ_NO = '"+dr_cr_seq_no+"' ";
//				System.out.println("queryString-hiren-"+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{	
					diff_inr_km  = rset.getString(44)==null?"0":rset.getString(44); 
					diff_inr_mmbtu = rset.getString(45)==null?"0":rset.getString(45);
					dr_cr_inr_km = rset.getString(46)==null?"0":rset.getString(46);
					dr_cr_inr_mmbtu = rset.getString(47)==null?"0":rset.getString(47);
					dr_cr_tax_rate = rset.getString(48)==null?"":rset.getString(48);
					diff_tax_str = rset.getString(49)==null?"":rset.getString(49);
					dr_cr_aprv_by = rset.getString(28)==null?"0":rset.getString(28);
					dr_cr_sales_rate = rset.getString(37)==null?"":nf2.format(Double.parseDouble(rset.getString(37)));
					dr_cr_aprv = rset.getInt(38);
					dr_cr_no 	  = rset.getString(19)==null?"":rset.getString(19);
					dr_cr_dt 	  = rset.getString(20)==null?"":rset.getString(20);
					dr_cr_exg_rt  = rset.getString(21)==null?"":nf2.format(Double.parseDouble(rset.getString(21)));						
					
					if(diff_qty.equals("") || diff_qty.equals("0")) {
						diff_qty = rset.getString(22)==null?"":rset.getString(22);
					}
					
					
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
					dr_cr_DUEDT = rset.getString(16)==null?"":rset.getString(16);
					item_desc= rset.getString(39)==null?"":rset.getString(39);
					tax_rmk= rset.getString(40)==null?"0":rset.getString(40);
					if(Double.parseDouble(tax_amt_inr+"") <= 0  || tax_amt_inr.equals("")) {
						tax_amt_inr= rset.getString(40)==null?"":rset.getString(40);//Hiren_20210119	
					}
					
					dr_cr_tcs_flag = rset.getString(41)==null?"":rset.getString(41);//Hiren_20210209
					
					if(dr_cr_qty.equals("")) {
						dr_cr_qty = rset.getString(42)==null?"":rset.getString(42);//Hiren_20210212
					}
					
					if(tax_str.equals("")) {
						dr_cr_tax_rt = rset.getString(43)==null?"":rset.getString(43);//Hiren_20210212
					}
					
					if(rmk_desc.equals("")) {
						rmk_desc = item_desc;	
					}
					
					if(!exg_rt.equals("") && !dr_cr_exg_rt.equals("")) {
						if(diff_exg.equals("")) {
							if(cr_dr1.equals("cr")) {
								diff_exg =  nf2.format(Double.parseDouble(exg_rt+"") -Double.parseDouble(dr_cr_exg_rt+""));
							}else {
								diff_exg =  nf2.format(Double.parseDouble(dr_cr_exg_rt+"") -Double.parseDouble(exg_rt+""));
							}
						}
					}
					//System.out.println("sale_rate****"+sale_rate+"----------"+dr_cr_sales_rate);
					if(!sale_rate.equals("") && !dr_cr_sales_rate.equals("")) {
						if(diff_rate.equals("")) {
							if(cr_dr1.equals("cr")) {
									diff_rate =  nf2.format(Double.parseDouble(sale_rate+"") -Double.parseDouble(dr_cr_sales_rate+""));
							}else {
								 	diff_rate =  nf2.format(Double.parseDouble(dr_cr_sales_rate+"") -Double.parseDouble(sale_rate+""));
							}
						}
					}
				}
				}
				if(!dr_cr_net_amt.equals("")) {
					if(date_flag) {
						if(contract_type.equals("S") || contract_type.equals("L")){
							dr_cr_net_amt = nf.format(Double.parseDouble(dr_cr_net_amt));
						}else{
						dr_cr_net_amt = nf.format(Math.round(Double.parseDouble(""+NumberFormat.getInstance().parse(dr_cr_net_amt))));
						}
					} else {
						dr_cr_net_amt = nf.format(Double.parseDouble(""+NumberFormat.getInstance().parse(dr_cr_net_amt)));	
					}
				}
				if(!dr_cr_gross_amt.equals("")) {
					//System.out.println("in thisss dr_cr_net_amt here-for before modify-");
					if(date_flag) {
						if(contract_type.equals("S") || contract_type.equals("L")){
							dr_cr_gross_amt = nf.format(Double.parseDouble(dr_cr_gross_amt));
						}else{
						dr_cr_gross_amt = nf.format(Math.round(Double.parseDouble(""+NumberFormat.getInstance().parse(dr_cr_gross_amt))));
						}
					} else {
						dr_cr_gross_amt = nf.format(Double.parseDouble(""+NumberFormat.getInstance().parse(dr_cr_gross_amt)));
					}
					//for getting tax_amt
						
					
				}
				if(!drcrgrossamt.equals("")) {
					Modify_flag="Y";
					//System.out.println("in thisss dr_cr_net_amt here-for after modify-");
					if(date_flag) {
						if(contract_type.equals("S") || contract_type.equals("L")){
							drcrgrossamt = nf.format(Double.parseDouble(drcrgrossamt));
						}else{	
						drcrgrossamt = nf.format(Math.round(Double.parseDouble(""+NumberFormat.getInstance().parse(drcrgrossamt))));
						}
					} else {
						drcrgrossamt = nf.format(Double.parseDouble(""+NumberFormat.getInstance().parse(drcrgrossamt)));
					}
				}
				if(criteria.contains("Imbalance")){
					if(!drcrgrossamt.equals("")) {
						rmk_desc=" Imbalance / Overrun Charges / Ship or Pay";
					}
					}
				if(criteria.contains("DIFF-TAX")){
					if(!drcrgrossamt.equals("")) {
						String inv_dt_format="";
						if(!inv_dt.equals("")){
							queryString="SELECT TO_CHAR(TO_DATE('"+inv_dt+"','DD/MM/YYYY'),'DD-Mon-yyyy') from dual";
							rset=stmt.executeQuery(queryString);
							if(rset.next()){
								inv_dt_format=rset.getString(1)==null?"":rset.getString(1);
							}
						}
						
						String rmkfortax="";
//						System.out.println("in thisss dr_cr_net_amt here-for after modify-"+tax_str+"--------"+taxnm);
						String tax_str_rate[]=tax_str.split(",");
						String tax_name[]=taxnm.split(",");
						double tot_taxamt=0;String taxfact="";
						for(int k=0;k<tax_str_rate.length;k++){
							if(!tax_str_rate[k].equals("")){
								tot_taxamt+=(Double.parseDouble(drcrgrossamt)* Double.parseDouble(tax_str_rate[k]))/100;
								rmk_desc+=tax_factor+"% "+tax_name[k]+",";
								//int taxfact=Integer.parseInt(tax_factor)+Integer.parseInt(tax_str_rate[k]);
								
									 taxfact=""+(Double.parseDouble(tax_factor)+Double.parseDouble(tax_str_rate[k]));
//									 System.out.println("taxfact----"+taxfact);
									 if(taxfact.contains(".")){
//										 System.out.println("taxfact----"+taxfact);
										 taxfact=taxfact.replace(".","/");
										 String tmp[]=taxfact.split("/");
//										 System.out.println("tmp[1]----"+tmp[1]);
										 if(!tmp[1].equals("")){
											 if(Double.parseDouble(tmp[1])==0){
												 taxfact=tmp[0];
											 }else{
												 taxfact=tmp[0]+"."+tmp[1];
											 }
										 }
									 	}
								rmkfortax=taxfact+"% "+tax_name[k]+"";
							}
						}
						if(contract_type.equals("S") || contract_type.equals("L")){
							tax_amt_inr = nf.format(tot_taxamt);
						}else{	
							tax_amt_inr = nf.format(Math.round(tot_taxamt));
						}
						dr_cr_net_amt = nf.format(tot_taxamt);
						
						if(!rmk_desc.equals("")){
//							System.out.println("Inv_no*******"+Inv_no);
							String rmk=rmk_desc.substring(0,rmk_desc.length()-1);
							rmk_desc=rmkfortax+" required less "+rmk+" already collected through invoice no. "+inv_no+" dated "+inv_dt_format;
						}
//						System.out.println("in thisss rmk_desc here--"+rmk_desc);
					}
//					System.out.println("tax_rmk*************"+tax_rmk);
					/*if(!tax_rmk.equals("")){
						String taxstr[]=tax_rmk.split(",");
						for(int k=0;k<taxstr.length;k++){
							String taxrt[]=taxstr[k].split("@");
							Mtaxrt.add(taxrt[1]);
						}
					}*/
				}
//				System.out.println("in thisss dr_cr_net_amt here--"+dr_cr_net_amt);
				//For TCS part
				Mnet_amt_inr=dr_cr_net_amt;
				String total_net_amt_finan="";
				String dlng_total_net_amt_finan="";
				String tot_diff_amt="";
				String apply_flag="Y";
				//System.out.println("resfreshflag-"+Modify_flag);
				String tcs_flag="";
				if(Mdr_cr_dt.equals("")){
					//System.out.println("queryString-abc--"+Mdr_cr_dt);
					Mdr_cr_dt=dr_cr_dt;
					//System.out.println("queryString-abc-after-"+Mdr_cr_dt);
				}
//				System.out.println("Mdr_cr_dt---------"+Mdr_cr_dt);
			if(!Mdr_cr_dt.equals("")){
				if((contract_type.equalsIgnoreCase("S") || contract_type.equalsIgnoreCase("L")) && (cr_dr1.equalsIgnoreCase("dr") || cr_dr1.equalsIgnoreCase("cr"))){
//					if(!criteria.contains("REV_INV") && !criteria.contains("DIFF-QTY")){
					if(!criteria.contains("REV_INV") ){
						String prev_fin_Yr = "";
						if(!Mdr_cr_dt.equals("") && cr_dr1.equalsIgnoreCase("dr")) {
							String prev_finYr = "select  "
									+ " (TO_NUMBER(TO_CHAR(ADD_MONTHS (to_date('"+Mdr_cr_dt+"','dd/mm/yyyy'), -3),'YYYY'))-1) "
									+ " || '-' || "
									+ " (TO_NUMBER(TO_CHAR(ADD_MONTHS (to_date('"+Mdr_cr_dt+"','dd/mm/yyyy'), 9),'YYYY'))-1) prev_fin_yr FROM DUAL";
//							System.out.println("prev_finYr----"+prev_finYr);
							rset = stmt.executeQuery(prev_finYr);
							if(rset.next()) {
								prev_fin_Yr = rset.getString(1)==null?"":rset.getString(1);
							}
							tdsCnt = 0; 
							tdsEntryCnt = 0; 
							String TDSFlagSql = "select count(*) from FMS7_CUSTOMER_TURNOVER_DTL"
									+ " where CUSTOMER_CD= '"+customer_cd+"' "
									+ " and FINANCIAL_YEAR = '"+prev_fin_Yr+"' "
									+ " and TURNOVER_CD = '1'"
									+ " and TURNOVER_FLAG = 'Y' "; 
//							System.out.println("TDSFlagSql-----"+TDSFlagSql);
							rset = stmt.executeQuery(TDSFlagSql);
							if(rset.next()) {
								tdsCnt = rset.getInt(1);
								tdsEntryCnt = rset.getInt(1);
							}
							if(tdsEntryCnt  == 0) {
								String checkCnt = "select count(*) from FMS7_CUSTOMER_TURNOVER_DTL"
										+ " where CUSTOMER_CD= '"+customer_cd+"' "
										+ " and FINANCIAL_YEAR = '"+prev_fin_Yr+"' "
										+ " and TURNOVER_CD = '1'";
//								System.out.println("checkCnt-----"+checkCnt);
								rset = stmt.executeQuery(checkCnt);
								if(rset.next()) {
									tdsEntryCnt = rset.getInt(1);
								}
							}
						}
						if(tdsEntryCnt > 0 && tdsCnt == 0) {
								
							queryString="SELECT TAX_CODE,FACTOR FROM FMS7_TAX_STRUCTURE_DTL A WHERE TAX_STR_CD='22' AND APP_DATE=(SELECT MAX(B.APP_DATE) "
									+ "FROM FMS7_TAX_STRUCTURE_DTL B WHERE A.TAX_STR_CD=B.TAX_STR_CD AND B.TAX_STR_CD='22' AND " +
									  "B.APP_DATE<=TO_DATE('"+Mdr_cr_dt+"','DD/MM/YYYY'))";
									// "B.APP_DATE<=TO_DATE('01/08/2020','DD/MM/YYYY'))";
							rset=stmt.executeQuery(queryString);
//							System.out.println("queryString-abc--"+queryString);
							if(rset.next()){
								tcs_flag="Y";
							}
							if(tcs_flag.equals("Y")){
							if(!dr_cr_no.equals("")){
								if(cr_dr1.equalsIgnoreCase("dr")){
									queryString="SELECT HLPL_INV_SEQ_NO,TCS_AMT FROM FMS7_INVOICE_TCS_DTL WHERE CUSTOMER_CD='"+customer_cd+"' "
										+ "AND FINANCIAL_YEAR='"+fin_year+"' and hlpl_inv_seq_no='"+dr_cr_no+"' and contract_type='"+contract_type+"'"
										+ " AND (INVOICE_TYPE='DEBIT') AND FLAG='Y' and commodity_type='DLNG' ";
									rset=stmt.executeQuery(queryString);
								}else {
									queryString="SELECT HLPL_INV_SEQ_NO,TCS_AMT FROM FMS7_INVOICE_TCS_DTL WHERE CUSTOMER_CD='"+customer_cd+"' "
											+ "AND FINANCIAL_YEAR='"+fin_year+"' and hlpl_inv_seq_no='"+dr_cr_no+"' and contract_type='"+contract_type+"'"
											+ " AND (INVOICE_TYPE='CREDIT') AND FLAG='Y' and commodity_type='DLNG' ";
									rset=stmt.executeQuery(queryString);
								}
//								System.out.println("checking for TCS----"+queryString);
								if(rset.next()){
									tcs_app_flag="Y";
									apply_flag="N";
									tcs_amt = rset.getString(2)==null?"":rset.getString(2);
									dr_cr_net_amt= nf.format(Double.parseDouble(dr_cr_net_amt.replace(",", "")));
									if(Modify_flag.equals("Y")){
										queryString="SELECT HLPL_INV_SEQ_NO FROM FMS7_INVOICE_TCS_DTL WHERE CUSTOMER_CD='"+customer_cd+"' "
												+ "AND FINANCIAL_YEAR='"+fin_year+"' AND FLAG='Y' ";
										rset=stmt.executeQuery(queryString);
										if(rset.next()){
											tcs_app_flag="Y";
											tot_diff_amt=dr_cr_net_amt.replace(",","");
										}else{
											/*queryString="SELECT SUM(NET_AMT_INR) FROM FMS7_INVOICE_MST WHERE CONTRACT_TYPE IN ('S','L') AND CUSTOMER_CD='"+customer_cd+"' "
													+ "AND FINANCIAL_YEAR='"+fin_year+"' AND FLAG='Y' ";*/
											queryString="SELECT nvl(SUM(NET_AMT_INR),'0') FROM DLNG_INVOICE_MST WHERE CONTRACT_TYPE IN ('S','L','K') AND CUSTOMER_CD='"+customer_cd+"' "
													+ "AND FINANCIAL_YEAR='"+fin_year+"' AND HLPL_INV_SEQ_NO NOT IN (SELECT B.HLPL_INV_SEQ_NO FROM FMS7_DR_CR_NOTE B WHERE B.CONTRACT_TYPE IN ('S','L','K') "
													+ "	AND B.CUSTOMER_CD='"+customer_cd+"' AND B.FINANCIAL_YEAR='"+fin_year+"'  AND "
													+ "B.DR_CR_FLAG='cr' AND B.APRV_BY IS NOT NULL) ";
	//										System.out.println("queryString-DLNG-"+queryString);
											rset=stmt.executeQuery(queryString);
											if(rset.next()){
												dlng_total_net_amt_finan = rset.getString(1)==null?"0":rset.getString(1);
												//tot_diff_amt=customer_Invoice_Net_Amt_INR.replace(",","");
												
												//FOR GETTING DEBIT NOTE FROM MANUAL TABLE 
												queryString1="SELECT nvl(SUM(DR_CR_NET_AMT_INR),'0') FROM DLNG_DR_CR_NOTE WHERE CONTRACT_TYPE IN ('S','L','K') AND CUSTOMER_CD='"+customer_cd+"' "
														+ "AND FINANCIAL_YEAR='"+fin_year+"' AND FLAG='Y' AND DR_CR_FLAG='dr' AND APRV_BY IS NOT NULL ";
												//System.out.println("queryString--"+queryString1);
												rset1=stmt1.executeQuery(queryString1);
												if(rset1.next()){
													dlng_total_net_amt_finan=nf.format(Double.parseDouble(dlng_total_net_amt_finan)+Double.parseDouble(rset1.getString(1)==null?"":rset1.getString(1)));
												}
												//
												//FOR GETTING CREDIT NOTE FROM TABLE 
												queryString1="SELECT nvl(SUM(DR_CR_NET_AMT_INR),'0') FROM DLNG_DR_CR_NOTE WHERE CONTRACT_TYPE IN ('S','L','K') AND CUSTOMER_CD='"+customer_cd+"' "
														+ "AND FINANCIAL_YEAR='"+fin_year+"' AND FLAG='Y' AND DR_CR_FLAG='cr' and criteria not like 'REV_INV%' AND APRV_BY IS NOT NULL ";
												//System.out.println("queryString--"+queryString1);
												rset1=stmt1.executeQuery(queryString1);
												if(rset1.next()){
													dlng_total_net_amt_finan=nf.format(Double.parseDouble(dlng_total_net_amt_finan)-Double.parseDouble(rset1.getString(1)==null?"":rset1.getString(1)));
												}
											}
											queryString="SELECT nvl(SUM(NET_AMT_INR),'0') + "+dlng_total_net_amt_finan+" FROM FMS7_INVOICE_MST WHERE CONTRACT_TYPE IN ('S','L','K') AND CUSTOMER_CD='"+customer_cd+"' "
													+ "AND FINANCIAL_YEAR='"+fin_year+"' AND HLPL_INV_SEQ_NO NOT IN (SELECT B.HLPL_INV_SEQ_NO FROM FMS7_DR_CR_NOTE B WHERE B.CONTRACT_TYPE IN ('S','L','K') "
													+ "	AND B.CUSTOMER_CD='"+customer_cd+"' AND B.FINANCIAL_YEAR='"+fin_year+"' AND B.CRITERIA ='REV_INV' AND "
													+ "B.DR_CR_FLAG='cr' AND B.APRV_BY IS NOT NULL) AND HLPL_INV_SEQ_NO NOT IN (SELECT C.HLPL_INV_SEQ_NO FROM FMS7_MANUAL_DR_CR_NOTE C WHERE "
													+ "C.CONTRACT_TYPE IN ('S','L','K') AND C.CUSTOMER_CD='"+customer_cd+"' "
													+ "AND C.FINANCIAL_YEAR='"+fin_year+"' AND C.CRITERIA ='REV_INV' AND C.DR_CR_FLAG='cr' AND C.APRV_BY IS NOT NULL) ";
											//System.out.println("queryString--"+queryString);
											//System.out.println("queryString--"+queryString);
											rset=stmt.executeQuery(queryString);
											if(rset.next()){
												//total_net_amt_finan=nf.format(Double.parseDouble(rset.getString(1)==null?"0":rset.getString(1)));
												queryString1="SELECT nvl(SUM(DR_CR_NET_AMT_INR),'0') FROM FMS7_DR_CR_NOTE WHERE CONTRACT_TYPE IN ('S','L','K') AND CUSTOMER_CD='"+customer_cd+"' "
														+ "AND FINANCIAL_YEAR='"+fin_year+"' AND FLAG='Y' AND DR_CR_FLAG='dr' AND APRV_BY IS NOT NULL ";
												//System.out.println("queryString--"+queryString1);
												rset1=stmt1.executeQuery(queryString1);
												if(rset1.next()){
													total_net_amt_finan=nf.format(Double.parseDouble(rset.getString(1)==null?"":rset.getString(1))+Double.parseDouble(rset1.getString(1)==null?"":rset1.getString(1)));
												}else{
													total_net_amt_finan=nf.format(Double.parseDouble(rset.getString(1)==null?"":rset.getString(1)));
												}
												//for getting manual invoice
												queryString1="SELECT nvl(SUM(NET_AMT_INR),'0') FROM FMS7_MANUAL_INVOICE_MST WHERE CONTRACT_TYPE IN ('S','L','K') AND CUSTOMER_CD='"+customer_cd+"' "
														+ "AND FINANCIAL_YEAR='"+fin_year+"' AND HLPL_INV_SEQ_NO NOT IN (SELECT B.HLPL_INV_SEQ_NO FROM FMS7_DR_CR_NOTE B WHERE B.CONTRACT_TYPE IN ('S','L','K') "
														+ "	AND B.CUSTOMER_CD='"+customer_cd+"' AND B.FINANCIAL_YEAR='"+fin_year+"' AND B.CRITERIA ='REV_INV' AND "
														+ "B.DR_CR_FLAG='cr' AND B.APRV_BY IS NOT NULL) AND HLPL_INV_SEQ_NO NOT IN (SELECT C.HLPL_INV_SEQ_NO FROM FMS7_MANUAL_DR_CR_NOTE C WHERE "
														+ "C.CONTRACT_TYPE IN ('S','L','K') AND C.CUSTOMER_CD='"+customer_cd+"' "
														+ "AND C.FINANCIAL_YEAR='"+fin_year+"' AND C.CRITERIA ='REV_INV' AND C.DR_CR_FLAG='cr' AND C.APRV_BY IS NOT NULL) ";
												//System.out.println("queryString1--"+queryString1);
												rset1=stmt1.executeQuery(queryString1);
												if(rset1.next()){
													total_net_amt_finan=nf.format(Double.parseDouble(total_net_amt_finan)+Double.parseDouble(rset1.getString(1)==null?"":rset1.getString(1)));
												}
												//
												//FOR GETTING DEBIT NOTE FROM MANUAL TABLE 
												queryString1="SELECT nvl(SUM(DR_CR_NET_AMT_INR),'0') FROM FMS7_MANUAL_DR_CR_NOTE WHERE CONTRACT_TYPE IN ('S','L','K') AND CUSTOMER_CD='"+customer_cd+"' "
														+ "AND FINANCIAL_YEAR='"+fin_year+"' AND FLAG='Y' AND DR_CR_FLAG='dr' AND APRV_BY IS NOT NULL ";
												//System.out.println("queryString--"+queryString1);
												rset1=stmt1.executeQuery(queryString1);
												if(rset1.next()){
													total_net_amt_finan=nf.format(Double.parseDouble(total_net_amt_finan)+Double.parseDouble(rset1.getString(1)==null?"":rset1.getString(1)));
												}
												//
												//FOR GETTING CREDIT NOTE FROM TABLE 
												queryString1="SELECT nvl(SUM(DR_CR_NET_AMT_INR),'0') FROM FMS7_DR_CR_NOTE WHERE CONTRACT_TYPE IN ('S','L','K') AND CUSTOMER_CD='"+customer_cd+"' "
														+ "AND FINANCIAL_YEAR='"+fin_year+"' AND FLAG='Y' AND DR_CR_FLAG='cr' and criteria!='REV_INV' AND APRV_BY IS NOT NULL ";
												//System.out.println("queryString--"+queryString1);
												rset1=stmt1.executeQuery(queryString1);
												if(rset1.next()){
													total_net_amt_finan=nf.format(Double.parseDouble(total_net_amt_finan)-Double.parseDouble(rset1.getString(1)==null?"":rset1.getString(1)));
												}
												//
												//FOR GETTING CREDIT NOTE FROM TABLE 
												queryString1="SELECT nvl(SUM(DR_CR_NET_AMT_INR),'0') FROM FMS7_MANUAL_DR_CR_NOTE WHERE CONTRACT_TYPE IN ('S','L','K') AND CUSTOMER_CD='"+customer_cd+"' "
														+ "AND FINANCIAL_YEAR='"+fin_year+"' AND FLAG='Y' AND DR_CR_FLAG='cr' and criteria!='REV_INV' AND APRV_BY IS NOT NULL  ";
												//System.out.println("queryString--"+queryString1);
												rset1=stmt1.executeQuery(queryString1);
												if(rset1.next()){
													total_net_amt_finan=nf.format(Double.parseDouble(total_net_amt_finan)-Double.parseDouble(rset1.getString(1)==null?"":rset1.getString(1)));
												}
												
												//
												//System.out.println("total_net_amt_finan--"+total_net_amt_finan+"----"+dr_cr_net_amt);
												if(Double.parseDouble(total_net_amt_finan)>tcs_limit_amt){
													//System.out.println("total_net_amt_finan-in if-"+total_net_amt_finan);
													tcs_app_flag="Y";
													tot_diff_amt=dr_cr_net_amt.replace(",","");
												}else{
													if(!dr_cr_net_amt.equals("")){
														double tot_amt=0;
														if(cr_dr1.equalsIgnoreCase("cr")){
															tot_amt=Double.parseDouble(total_net_amt_finan)-Double.parseDouble(dr_cr_net_amt.replace(",",""));
														}else{
															tot_amt=Double.parseDouble(dr_cr_net_amt.replace(",",""))+Double.parseDouble(total_net_amt_finan);
														}
														//System.out.println("tot_amt--"+tot_amt);
														if(tot_amt>tcs_limit_amt){
															tcs_app_flag="Y";
															tot_diff_amt=nf.format(tot_amt-tcs_limit_amt);
														}
													}
													//invoice_total_tax=nf3.format(total_tax_amt);
													//customer_Invoice_Net_Amt_INR = nf3.format(total_tax_amt+Double.parseDouble(temp_gross_amt_inr));
												}
											}else{
												if(Double.parseDouble(dlng_total_net_amt_finan)>tcs_limit_amt){
	//												System.out.println("total_net_amt_finan-in if-"+dr_cr_net_amt);
													tcs_app_flag="Y";
													tot_diff_amt=dr_cr_net_amt.replace(",","");
												}else{
													if(!dr_cr_net_amt.equals("")){
														double tot_amt=0;
														if(cr_dr1.equalsIgnoreCase("cr")){
															tot_amt=Double.parseDouble(dlng_total_net_amt_finan)-Double.parseDouble(dr_cr_net_amt.replace(",",""));
														}else{
															tot_amt=Double.parseDouble(dr_cr_net_amt.replace(",",""))+Double.parseDouble(dlng_total_net_amt_finan);
														}
														//System.out.println("tot_amt--"+tot_amt);
														if(tot_amt>tcs_limit_amt){
															tcs_app_flag="Y";
															tot_diff_amt=nf.format(tot_amt-tcs_limit_amt);
														}
													}
													//invoice_total_tax=nf3.format(total_tax_amt);
													//customer_Invoice_Net_Amt_INR = nf3.format(total_tax_amt+Double.parseDouble(temp_gross_amt_inr));
												}
											}
										}
									}
								}else{
									queryString="SELECT HLPL_INV_SEQ_NO FROM FMS7_INVOICE_TCS_DTL WHERE CUSTOMER_CD='"+customer_cd+"' "
											+ "AND FINANCIAL_YEAR='"+fin_year+"' AND FLAG='Y' ";
									rset=stmt.executeQuery(queryString);
									if(rset.next()){
										tcs_app_flag="Y";
										tot_diff_amt=dr_cr_net_amt.replace(",","");
									}else{
										/*queryString="SELECT SUM(NET_AMT_INR) FROM FMS7_INVOICE_MST WHERE CONTRACT_TYPE IN ('S','L') AND CUSTOMER_CD='"+customer_cd+"' "
												+ "AND FINANCIAL_YEAR='"+fin_year+"' AND FLAG='Y' ";*/
										
										queryString="SELECT nvl(SUM(NET_AMT_INR),'0') FROM DLNG_INVOICE_MST WHERE CONTRACT_TYPE IN ('S','L','K') AND CUSTOMER_CD='"+customer_cd+"' "
												+ "AND FINANCIAL_YEAR='"+fin_year+"' AND HLPL_INV_SEQ_NO NOT IN (SELECT B.HLPL_INV_SEQ_NO FROM FMS7_DR_CR_NOTE B WHERE B.CONTRACT_TYPE IN ('S','L','K') "
												+ "	AND B.CUSTOMER_CD='"+customer_cd+"' AND B.FINANCIAL_YEAR='"+fin_year+"'  AND "
												+ "B.DR_CR_FLAG='cr' AND B.APRV_BY IS NOT NULL) ";
	//									System.out.println("queryString-DLNG-"+queryString);
										rset=stmt.executeQuery(queryString);
										if(rset.next()){
											dlng_total_net_amt_finan = rset.getString(1)==null?"0":rset.getString(1);
										
											//FOR GETTING DEBIT NOTE FROM MANUAL TABLE 
											queryString1="SELECT nvl(SUM(DR_CR_NET_AMT_INR),'0') FROM DLNG_DR_CR_NOTE WHERE CONTRACT_TYPE IN ('S','L','K') AND CUSTOMER_CD='"+customer_cd+"' "
													+ "AND FINANCIAL_YEAR='"+fin_year+"' AND FLAG='Y' AND DR_CR_FLAG='dr' AND APRV_BY IS NOT NULL ";
											//System.out.println("queryString--"+queryString1);
											rset1=stmt1.executeQuery(queryString1);
											if(rset1.next()){
												dlng_total_net_amt_finan=nf.format(Double.parseDouble(dlng_total_net_amt_finan)+Double.parseDouble(rset1.getString(1)==null?"":rset1.getString(1)));
											}
											//
											//FOR GETTING CREDIT NOTE FROM TABLE 
											queryString1="SELECT nvl(SUM(DR_CR_NET_AMT_INR),'0') FROM DLNG_DR_CR_NOTE WHERE CONTRACT_TYPE IN ('S','L','K') AND CUSTOMER_CD='"+customer_cd+"' "
													+ "AND FINANCIAL_YEAR='"+fin_year+"' AND FLAG='Y' AND DR_CR_FLAG='cr' and criteria not like 'REV_INV%' AND APRV_BY IS NOT NULL ";
											//System.out.println("queryString--"+queryString1);
											rset1=stmt1.executeQuery(queryString1);
											if(rset1.next()){
												dlng_total_net_amt_finan=nf.format(Double.parseDouble(dlng_total_net_amt_finan)-Double.parseDouble(rset1.getString(1)==null?"":rset1.getString(1)));
											}
										}
										queryString="SELECT nvl(SUM(NET_AMT_INR),'0') + "+dlng_total_net_amt_finan+" FROM FMS7_INVOICE_MST WHERE CONTRACT_TYPE IN ('S','L','K') AND CUSTOMER_CD='"+customer_cd+"' "
												+ "AND FINANCIAL_YEAR='"+fin_year+"' AND HLPL_INV_SEQ_NO NOT IN (SELECT B.HLPL_INV_SEQ_NO FROM FMS7_DR_CR_NOTE B WHERE B.CONTRACT_TYPE IN ('S','L','K') "
												+ "	AND B.CUSTOMER_CD='"+customer_cd+"' AND B.FINANCIAL_YEAR='"+fin_year+"' AND B.CRITERIA ='REV_INV' AND "
												+ "B.DR_CR_FLAG='cr' AND B.APRV_BY IS NOT NULL) AND HLPL_INV_SEQ_NO NOT IN (SELECT C.HLPL_INV_SEQ_NO FROM FMS7_MANUAL_DR_CR_NOTE C WHERE "
												+ "C.CONTRACT_TYPE IN ('S','L','K') AND C.CUSTOMER_CD='"+customer_cd+"' "
												+ "AND C.FINANCIAL_YEAR='"+fin_year+"' AND C.CRITERIA ='REV_INV' AND C.DR_CR_FLAG='cr' AND C.APRV_BY IS NOT NULL) ";
										//System.out.println("queryString--"+queryString);
										//System.out.println("queryString--"+queryString);
										rset=stmt.executeQuery(queryString);
										if(rset.next()){
											//total_net_amt_finan=nf.format(Double.parseDouble(rset.getString(1)==null?"0":rset.getString(1)));
											queryString1="SELECT nvl(SUM(DR_CR_NET_AMT_INR),'0') FROM FMS7_DR_CR_NOTE WHERE CONTRACT_TYPE IN ('S','L','K') AND CUSTOMER_CD='"+customer_cd+"' "
													+ "AND FINANCIAL_YEAR='"+fin_year+"' AND FLAG='Y' AND DR_CR_FLAG='dr' AND APRV_BY IS NOT NULL ";
											//System.out.println("queryString--"+queryString1);
											rset1=stmt1.executeQuery(queryString1);
											if(rset1.next()){
												total_net_amt_finan=nf.format(Double.parseDouble(rset.getString(1)==null?"":rset.getString(1))+Double.parseDouble(rset1.getString(1)==null?"":rset1.getString(1)));
											}else{
												total_net_amt_finan=nf.format(Double.parseDouble(rset.getString(1)==null?"":rset.getString(1)));
											}
											//for getting manual invoice
											queryString1="SELECT nvl(SUM(NET_AMT_INR),'0') FROM FMS7_MANUAL_INVOICE_MST WHERE CONTRACT_TYPE IN ('S','L','K') AND CUSTOMER_CD='"+customer_cd+"' "
													+ "AND FINANCIAL_YEAR='"+fin_year+"' AND HLPL_INV_SEQ_NO NOT IN (SELECT B.HLPL_INV_SEQ_NO FROM FMS7_DR_CR_NOTE B WHERE B.CONTRACT_TYPE IN ('S','L','K') "
													+ "	AND B.CUSTOMER_CD='"+customer_cd+"' AND B.FINANCIAL_YEAR='"+fin_year+"' AND B.CRITERIA ='REV_INV' AND "
													+ "B.DR_CR_FLAG='cr' AND B.APRV_BY IS NOT NULL) AND HLPL_INV_SEQ_NO NOT IN (SELECT C.HLPL_INV_SEQ_NO FROM FMS7_MANUAL_DR_CR_NOTE C WHERE "
													+ "C.CONTRACT_TYPE IN ('S','L','K') AND C.CUSTOMER_CD='"+customer_cd+"' "
													+ "AND C.FINANCIAL_YEAR='"+fin_year+"' AND C.CRITERIA ='REV_INV' AND C.DR_CR_FLAG='cr' AND C.APRV_BY IS NOT NULL) ";
											//System.out.println("queryString1--"+queryString1);
											rset1=stmt1.executeQuery(queryString1);
											if(rset1.next()){
												total_net_amt_finan=nf.format(Double.parseDouble(total_net_amt_finan)+Double.parseDouble(rset1.getString(1)==null?"":rset1.getString(1)));
											}
											//
											//FOR GETTING DEBIT NOTE FROM MANUAL TABLE 
											queryString1="SELECT nvl(SUM(DR_CR_NET_AMT_INR),'0') FROM FMS7_MANUAL_DR_CR_NOTE WHERE CONTRACT_TYPE IN ('S','L','K') AND CUSTOMER_CD='"+customer_cd+"' "
													+ "AND FINANCIAL_YEAR='"+fin_year+"' AND FLAG='Y' AND DR_CR_FLAG='dr' AND APRV_BY IS NOT NULL ";
											//System.out.println("queryString--"+queryString1);
											rset1=stmt1.executeQuery(queryString1);
											if(rset1.next()){
												total_net_amt_finan=nf.format(Double.parseDouble(total_net_amt_finan)+Double.parseDouble(rset1.getString(1)==null?"":rset1.getString(1)));
											}
											//
											//FOR GETTING CREDIT NOTE FROM TABLE 
											queryString1="SELECT nvl(SUM(DR_CR_NET_AMT_INR),'0') FROM FMS7_DR_CR_NOTE WHERE CONTRACT_TYPE IN ('S','L','K') AND CUSTOMER_CD='"+customer_cd+"' "
													+ "AND FINANCIAL_YEAR='"+fin_year+"' AND FLAG='Y' AND DR_CR_FLAG='cr' and criteria!='REV_INV' AND APRV_BY IS NOT NULL ";
											//System.out.println("queryString--"+queryString1);
											rset1=stmt1.executeQuery(queryString1);
											if(rset1.next()){
												total_net_amt_finan=nf.format(Double.parseDouble(total_net_amt_finan)-Double.parseDouble(rset1.getString(1)==null?"":rset1.getString(1)));
											}
											//
											//FOR GETTING CREDIT NOTE FROM TABLE 
											queryString1="SELECT nvl(SUM(DR_CR_NET_AMT_INR),'0') FROM FMS7_MANUAL_DR_CR_NOTE WHERE CONTRACT_TYPE IN ('S','L','K') AND CUSTOMER_CD='"+customer_cd+"' "
													+ "AND FINANCIAL_YEAR='"+fin_year+"' AND FLAG='Y' AND DR_CR_FLAG='cr' and criteria!='REV_INV' AND APRV_BY IS NOT NULL  ";
											//System.out.println("queryString--"+queryString1);
											rset1=stmt1.executeQuery(queryString1);
											if(rset1.next()){
												total_net_amt_finan=nf.format(Double.parseDouble(total_net_amt_finan)-Double.parseDouble(rset1.getString(1)==null?"":rset1.getString(1)));
											}
											
											//
											//System.out.println("total_net_amt_finan--"+total_net_amt_finan+"----"+dr_cr_net_amt);
											if(Double.parseDouble(total_net_amt_finan)>tcs_limit_amt){
												//System.out.println("total_net_amt_finan-in if-"+total_net_amt_finan);
												tcs_app_flag="Y";
												tot_diff_amt=dr_cr_net_amt.replace(",","");
											}else{
												if(!dr_cr_net_amt.equals("")){
													double tot_amt=0;
													if(cr_dr1.equalsIgnoreCase("cr")){
														tot_amt=Double.parseDouble(total_net_amt_finan)-Double.parseDouble(dr_cr_net_amt.replace(",",""));
													}else{
														tot_amt=Double.parseDouble(dr_cr_net_amt.replace(",",""))+Double.parseDouble(total_net_amt_finan);
													}
													//System.out.println("tot_amt--"+tot_amt);
													if(tot_amt>tcs_limit_amt){
														tcs_app_flag="Y";
														tot_diff_amt=nf.format(tot_amt-tcs_limit_amt);
													}
												}
												//invoice_total_tax=nf3.format(total_tax_amt);
												//customer_Invoice_Net_Amt_INR = nf3.format(total_tax_amt+Double.parseDouble(temp_gross_amt_inr));
											}
											
										}else{
											if(Double.parseDouble(dlng_total_net_amt_finan)>tcs_limit_amt){
												//System.out.println("total_net_amt_finan-in if-"+total_net_amt_finan);
												tcs_app_flag="Y";
												tot_diff_amt=dr_cr_net_amt.replace(",","");
											}else{
												if(!dr_cr_net_amt.equals("")){
													double tot_amt=0;
													if(cr_dr1.equalsIgnoreCase("cr")){
														tot_amt=Double.parseDouble(dlng_total_net_amt_finan)-Double.parseDouble(dr_cr_net_amt.replace(",",""));
													}else{
														tot_amt=Double.parseDouble(dr_cr_net_amt.replace(",",""))+Double.parseDouble(dlng_total_net_amt_finan);
													}
													//System.out.println("tot_amt--"+tot_amt);
													if(tot_amt>tcs_limit_amt){
														tcs_app_flag="Y";
														tot_diff_amt=nf.format(tot_amt-tcs_limit_amt);
													}
												}
												//invoice_total_tax=nf3.format(total_tax_amt);
												//customer_Invoice_Net_Amt_INR = nf3.format(total_tax_amt+Double.parseDouble(temp_gross_amt_inr));
											}
										}
									}
								}
							}else{
								queryString="SELECT HLPL_INV_SEQ_NO FROM FMS7_INVOICE_TCS_DTL WHERE CUSTOMER_CD='"+customer_cd+"' "
										+ "AND FINANCIAL_YEAR='"+fin_year+"' AND FLAG='Y'  ";
								rset=stmt.executeQuery(queryString);
	//							System.out.println("-in if-"+queryString);
								if(rset.next()){
									tcs_app_flag="Y";
									tot_diff_amt=dr_cr_net_amt.replace(",","");
								}else{
	//								queryString="SELECT nvl(SUM(NET_AMT_INR),'0') FROM FMS7_INVOICE_MST WHERE CONTRACT_TYPE IN ('S','L') AND CUSTOMER_CD='"+customer_cd+"' "
	//										+ "AND FINANCIAL_YEAR='"+fin_year+"' AND FLAG='Y' ";
									queryString="SELECT nvl(SUM(NET_AMT_INR),'0') FROM DLNG_INVOICE_MST WHERE CONTRACT_TYPE IN ('S','L','K') AND CUSTOMER_CD='"+customer_cd+"' "
											+ "AND FINANCIAL_YEAR='"+fin_year+"' AND HLPL_INV_SEQ_NO NOT IN (SELECT B.HLPL_INV_SEQ_NO FROM FMS7_DR_CR_NOTE B WHERE B.CONTRACT_TYPE IN ('S','L','K') "
											+ "	AND B.CUSTOMER_CD='"+customer_cd+"' AND B.FINANCIAL_YEAR='"+fin_year+"'  AND "
											+ "B.DR_CR_FLAG='cr' AND B.APRV_BY IS NOT NULL) ";
	//								System.out.println("queryString-DLNG-"+queryString);
									rset=stmt.executeQuery(queryString);
									if(rset.next()){
										dlng_total_net_amt_finan = rset.getString(1)==null?"0":rset.getString(1);
										
										//FOR GETTING DEBIT NOTE FROM MANUAL TABLE 
										queryString1="SELECT nvl(SUM(DR_CR_NET_AMT_INR),'0') FROM DLNG_DR_CR_NOTE WHERE CONTRACT_TYPE IN ('S','L','K') AND CUSTOMER_CD='"+customer_cd+"' "
												+ "AND FINANCIAL_YEAR='"+fin_year+"' AND FLAG='Y' AND DR_CR_FLAG='dr' AND APRV_BY IS NOT NULL ";
	//									System.out.println("queryString--"+queryString1);
										rset1=stmt1.executeQuery(queryString1);
										if(rset1.next()){
											dlng_total_net_amt_finan=nf.format(Double.parseDouble(dlng_total_net_amt_finan)+Double.parseDouble(rset1.getString(1)==null?"":rset1.getString(1)));
										}
										//
										//FOR GETTING CREDIT NOTE FROM TABLE 
										queryString1="SELECT nvl(SUM(DR_CR_NET_AMT_INR),'0') FROM DLNG_DR_CR_NOTE WHERE CONTRACT_TYPE IN ('S','L','K') AND CUSTOMER_CD='"+customer_cd+"' "
												+ "AND FINANCIAL_YEAR='"+fin_year+"' AND FLAG='Y' AND DR_CR_FLAG='cr' and criteria not like 'REV_INV%' AND APRV_BY IS NOT NULL ";
	//									System.out.println("queryString--"+queryString1);
										rset1=stmt1.executeQuery(queryString1);
										if(rset1.next()){
											dlng_total_net_amt_finan=nf.format(Double.parseDouble(dlng_total_net_amt_finan)-Double.parseDouble(rset1.getString(1)==null?"":rset1.getString(1)));
										}
									}
									queryString="SELECT nvl(SUM(NET_AMT_INR),'0') + "+dlng_total_net_amt_finan+" FROM FMS7_INVOICE_MST WHERE CONTRACT_TYPE IN ('S','L','K') AND CUSTOMER_CD='"+customer_cd+"' "
											+ "AND FINANCIAL_YEAR='"+fin_year+"' AND HLPL_INV_SEQ_NO NOT IN (SELECT B.HLPL_INV_SEQ_NO FROM FMS7_DR_CR_NOTE B WHERE B.CONTRACT_TYPE IN ('S','L','K') "
											+ "	AND B.CUSTOMER_CD='"+customer_cd+"' AND B.FINANCIAL_YEAR='"+fin_year+"' AND B.CRITERIA ='REV_INV' AND "
											+ "B.DR_CR_FLAG='cr' AND B.APRV_BY IS NOT NULL) AND HLPL_INV_SEQ_NO NOT IN (SELECT C.HLPL_INV_SEQ_NO FROM FMS7_MANUAL_DR_CR_NOTE C WHERE "
											+ "C.CONTRACT_TYPE IN ('S','L','K') AND C.CUSTOMER_CD='"+customer_cd+"' "
											+ "AND C.FINANCIAL_YEAR='"+fin_year+"' AND C.CRITERIA ='REV_INV' AND C.DR_CR_FLAG='cr' AND C.APRV_BY IS NOT NULL) ";
	//								System.out.println("queryString--"+queryString);
									rset=stmt.executeQuery(queryString);
									if(rset.next()){
										queryString1="SELECT nvl(SUM(DR_CR_NET_AMT_INR),'0') FROM FMS7_DR_CR_NOTE WHERE CONTRACT_TYPE IN ('S','L','K') AND CUSTOMER_CD='"+customer_cd+"' "
												+ "AND FINANCIAL_YEAR='"+fin_year+"' AND FLAG='Y' AND DR_CR_FLAG='dr' AND APRV_BY IS NOT NULL ";
	//									System.out.println("queryString--"+queryString1);
										rset1=stmt1.executeQuery(queryString1);
										if(rset1.next()){
											total_net_amt_finan=nf.format(Double.parseDouble(rset.getString(1)==null?"":rset.getString(1))+Double.parseDouble(rset1.getString(1)==null?"":rset1.getString(1)));
										}else{
											total_net_amt_finan=nf.format(Double.parseDouble(rset.getString(1)==null?"":rset.getString(1)));
										}
										//for getting manual invoice
										queryString1="SELECT nvl(SUM(NET_AMT_INR),'0') FROM FMS7_MANUAL_INVOICE_MST WHERE CONTRACT_TYPE IN ('S','L','K') AND CUSTOMER_CD='"+customer_cd+"' "
												+ "AND FINANCIAL_YEAR='"+fin_year+"' AND HLPL_INV_SEQ_NO NOT IN (SELECT B.HLPL_INV_SEQ_NO FROM FMS7_DR_CR_NOTE B WHERE B.CONTRACT_TYPE IN ('S','L','K') "
												+ "	AND B.CUSTOMER_CD='"+customer_cd+"' AND B.FINANCIAL_YEAR='"+fin_year+"' AND B.CRITERIA ='REV_INV' AND "
												+ "B.DR_CR_FLAG='cr' AND B.APRV_BY IS NOT NULL) AND HLPL_INV_SEQ_NO NOT IN (SELECT C.HLPL_INV_SEQ_NO FROM FMS7_MANUAL_DR_CR_NOTE C WHERE "
												+ "C.CONTRACT_TYPE IN ('S','L','K') AND C.CUSTOMER_CD='"+customer_cd+"' "
												+ "AND C.FINANCIAL_YEAR='"+fin_year+"' AND C.CRITERIA ='REV_INV' AND C.DR_CR_FLAG='cr' AND C.APRV_BY IS NOT NULL) ";
	//									System.out.println("queryString1--"+queryString1);
										rset1=stmt1.executeQuery(queryString1);
										if(rset1.next()){
											total_net_amt_finan=nf.format(Double.parseDouble(total_net_amt_finan)+Double.parseDouble(rset1.getString(1)==null?"":rset1.getString(1)));
										}
										//
										//FOR GETTING DEBIT NOTE FROM MANUAL TABLE 
										queryString1="SELECT nvl(SUM(DR_CR_NET_AMT_INR),'0') FROM FMS7_MANUAL_DR_CR_NOTE WHERE CONTRACT_TYPE IN ('S','L','K') AND CUSTOMER_CD='"+customer_cd+"' "
												+ "AND FINANCIAL_YEAR='"+fin_year+"' AND FLAG='Y' AND DR_CR_FLAG='dr' AND APRV_BY IS NOT NULL ";
	//									System.out.println("queryString--"+queryString1);
										rset1=stmt1.executeQuery(queryString1);
										if(rset1.next()){
											total_net_amt_finan=nf.format(Double.parseDouble(total_net_amt_finan)+Double.parseDouble(rset1.getString(1)==null?"":rset1.getString(1)));
										}
										//
										//FOR GETTING CREDIT NOTE FROM TABLE 
										queryString1="SELECT nvl(SUM(DR_CR_NET_AMT_INR),'0') FROM FMS7_DR_CR_NOTE WHERE CONTRACT_TYPE IN ('S','L','K') AND CUSTOMER_CD='"+customer_cd+"' "
												+ "AND FINANCIAL_YEAR='"+fin_year+"' AND FLAG='Y' AND DR_CR_FLAG='cr' and criteria!='REV_INV' AND APRV_BY IS NOT NULL ";
	//									System.out.println("queryString--"+queryString1);
										rset1=stmt1.executeQuery(queryString1);
										if(rset1.next()){
											total_net_amt_finan=nf.format(Double.parseDouble(total_net_amt_finan)-Double.parseDouble(rset1.getString(1)==null?"":rset1.getString(1)));
										}
										//
										//FOR GETTING CREDIT NOTE FROM TABLE 
										queryString1="SELECT nvl(SUM(DR_CR_NET_AMT_INR),'0') FROM FMS7_MANUAL_DR_CR_NOTE WHERE CONTRACT_TYPE IN ('S','L','K') AND CUSTOMER_CD='"+customer_cd+"' "
												+ "AND FINANCIAL_YEAR='"+fin_year+"' AND FLAG='Y' AND DR_CR_FLAG='cr' and criteria!='REV_INV' AND APRV_BY IS NOT NULL  ";
	//									System.out.println("queryString--"+queryString1);
										rset1=stmt1.executeQuery(queryString1);
										if(rset1.next()){
											total_net_amt_finan=nf.format(Double.parseDouble(total_net_amt_finan)-Double.parseDouble(rset1.getString(1)==null?"":rset1.getString(1)));
										}
										
										//
	//									System.out.println("total_net_amt_finan--"+total_net_amt_finan+"----"+dr_cr_net_amt);
										if(Double.parseDouble(total_net_amt_finan)>tcs_limit_amt){
	//										System.out.println("total_net_amt_finan-in if-"+total_net_amt_finan);
											tcs_app_flag="Y";
											tot_diff_amt=dr_cr_net_amt.replace(",","");
										}else{
											if(!dr_cr_net_amt.equals("")){
												double tot_amt=0;
												if(cr_dr1.equalsIgnoreCase("cr")){
												
													tot_amt=Double.parseDouble(total_net_amt_finan)-Double.parseDouble(dr_cr_net_amt.replace(",",""));
												}else{
													tot_amt=Double.parseDouble(dr_cr_net_amt.replace(",",""))+Double.parseDouble(total_net_amt_finan);
												}
												//System.out.println("tot_amt--"+tot_amt);
												if(tot_amt>tcs_limit_amt){
													//System.out.println("tot_amt--"+tot_amt);
													tcs_app_flag="Y";
													tot_diff_amt=nf.format(tot_amt-tcs_limit_amt);
												}
											}
											//invoice_total_tax=nf3.format(total_tax_amt);
											//customer_Invoice_Net_Amt_INR = nf3.format(total_tax_amt+Double.parseDouble(temp_gross_amt_inr));
										}
									}else{
										if(Double.parseDouble(dlng_total_net_amt_finan)>tcs_limit_amt){
	//										System.out.println("dlng_total_net_amt_finan-in if-"+dlng_total_net_amt_finan);
											tcs_app_flag="Y";
											tot_diff_amt=dr_cr_net_amt.replace(",","");
										}else{
											if(!dr_cr_net_amt.equals("")){
												double tot_amt=0;
												if(cr_dr1.equalsIgnoreCase("cr")){
													tot_amt=Double.parseDouble(dlng_total_net_amt_finan)-Double.parseDouble(dr_cr_net_amt.replace(",",""));
												}else{
													tot_amt=Double.parseDouble(dr_cr_net_amt.replace(",",""))+Double.parseDouble(dlng_total_net_amt_finan);
												}
												//System.out.println("tot_amt--"+tot_amt);
												if(tot_amt>tcs_limit_amt){
													//System.out.println("tot_amt--"+tot_amt);
													tcs_app_flag="Y";
													tot_diff_amt=nf.format(tot_amt-tcs_limit_amt);
												}
											}
											//invoice_total_tax=nf3.format(total_tax_amt);
											//customer_Invoice_Net_Amt_INR = nf3.format(total_tax_amt+Double.parseDouble(temp_gross_amt_inr));
										}
									}
								}
							}
							}
						}
						double tcs_taxamt=0;
						if(tdsCnt == 0){
							queryString="SELECT TAX_CODE,FACTOR FROM FMS7_TAX_STRUCTURE_DTL A WHERE TAX_STR_CD='22' AND APP_DATE=(SELECT MAX(B.APP_DATE) "
									+ "FROM FMS7_TAX_STRUCTURE_DTL B WHERE A.TAX_STR_CD=B.TAX_STR_CD AND B.TAX_STR_CD='22' AND " +
									  "B.APP_DATE<=TO_DATE('"+Mdr_cr_dt+"','DD/MM/YYYY'))";
									// "B.APP_DATE<=TO_DATE('01/08/2020','DD/MM/YYYY'))";
							rset=stmt.executeQuery(queryString);
							//System.out.println("queryString---"+queryString);
							if(rset.next()){
								fact=nf4.format(Double.parseDouble(rset.getString(2)==null?"0":rset.getString(2)));
								if(apply_flag.equalsIgnoreCase("Y") || Modify_flag.equalsIgnoreCase("Y")){
									//System.out.println("apply_flag---"+apply_flag);
									if(!tot_diff_amt.equals("")){
										//System.out.println("tot_diff_amt****"+tot_diff_amt);
										tcs_amt = nf.format((Double.parseDouble(tot_diff_amt)*Double.parseDouble(fact))/100);
										tcs_taxamt=(Double.parseDouble(tot_diff_amt)*Double.parseDouble(fact))/100;
//										dr_cr_net_amt= nf.format(tcs_taxamt+Double.parseDouble(Mnet_amt_inr.replace(",", ""))); //Hiren_20210209 As per customer req. TCS inclusion optional
										//System.out.println("cr_dr1***"+cr_dr1);
										if(cr_dr1.equalsIgnoreCase("cr")){
											dr_cr_net_amt= nf.format(Double.parseDouble(Mnet_amt_inr.replace(",", "")));
										}else {
											dr_cr_net_amt= nf.format(tcs_taxamt+Double.parseDouble(Mnet_amt_inr.replace(",", "")));
										}
									}
									
								}
								
								queryString1 = "SELECT sht_nm,tax_name FROM FMS7_TAX_MST WHERE " +
										  "tax_code="+rset.getString(1)+"";
								//System.out.println("Query For Fetching Tax Name = "+queryString);
								rset1 = stmt1.executeQuery(queryString1);
								if(rset1.next())
								{
									tcs_nm=rset1.getString(2)==null?"":rset1.getString(2);
									tcs_sht_nm=rset1.getString(1)==null?"":rset1.getString(1);
									tcs_nm=tcs_nm+" ("+tcs_sht_nm+")";
								}
							}
						}else if(tdsCnt > 0) { //Hiren_20210622
							
							int entryTDS = 0;
							String tdsCntSql = "select count(*) from FMS7_INVOICE_TDS_DTL WHERE "
									+ " CUSTOMER_CD='"+customer_cd+"'"
									+ " AND FINANCIAL_YEAR='"+fin_year+"'"
									+ " AND FLAG='Y'";
							rset = stmt1.executeQuery(tdsCntSql);
							if(rset.next()) {
								entryTDS = rset.getInt(1);
							}
							if(entryTDS > 0) {
								tds_amt = drcrgrossamt;
							}else {

								String dlng_total_gross_amt_finan= "";
								String total_gross_amt_finan = "";
								queryString="SELECT nvl(SUM(GROSS_AMT_INR),'0') FROM DLNG_INVOICE_MST WHERE CONTRACT_TYPE IN ('S','L','K') AND CUSTOMER_CD='"+customer_cd+"' "
										+ "AND FINANCIAL_YEAR='"+fin_year+"' AND HLPL_INV_SEQ_NO NOT IN (SELECT B.HLPL_INV_SEQ_NO FROM DLNG_DR_CR_NOTE B WHERE B.CONTRACT_TYPE IN ('S','L','K') "
										+ "	AND B.CUSTOMER_CD='"+customer_cd+"' AND B.FINANCIAL_YEAR='"+fin_year+"'  AND "
										+ "B.DR_CR_FLAG='cr' AND B.APRV_BY IS NOT NULL) ";
//								System.out.println("queryString-DLNG-"+queryString);
								rset=stmt.executeQuery(queryString);
								if(rset.next()){
									dlng_total_gross_amt_finan = rset.getString(1)==null?"0":rset.getString(1);
									
									//FOR GETTING DEBIT NOTE FROM MANUAL TABLE 
									queryString1="SELECT nvl(SUM(DR_CR_GROSS_AMT_INR),'0') FROM DLNG_DR_CR_NOTE WHERE CONTRACT_TYPE IN ('S','L','K') AND CUSTOMER_CD='"+customer_cd+"' "
											+ "AND FINANCIAL_YEAR='"+fin_year+"' AND FLAG='Y' AND DR_CR_FLAG='dr' AND APRV_BY IS NOT NULL ";
//									System.out.println("queryString--"+queryString1);
									rset3=stmt3.executeQuery(queryString1);
									if(rset3.next()){
										dlng_total_gross_amt_finan=nf.format(Double.parseDouble(dlng_total_gross_amt_finan)+Double.parseDouble(rset3.getString(1)==null?"":rset3.getString(1)));
									}
									//
									//FOR GETTING CREDIT NOTE FROM TABLE 
									queryString1="SELECT nvl(SUM(DR_CR_GROSS_AMT_INR),'0') FROM DLNG_DR_CR_NOTE WHERE CONTRACT_TYPE IN ('S','L','K') AND CUSTOMER_CD='"+customer_cd+"' "
											+ "AND FINANCIAL_YEAR='"+fin_year+"' AND FLAG='Y' AND DR_CR_FLAG='cr' and criteria not like 'REV_INV%' AND APRV_BY IS NOT NULL ";
//									System.out.println("queryString--"+queryString1);
									rset3=stmt3.executeQuery(queryString1);
									if(rset3.next()){
										dlng_total_gross_amt_finan=nf.format(Double.parseDouble(dlng_total_gross_amt_finan)-Double.parseDouble(rset3.getString(1)==null?"":rset3.getString(1)));
									}
									//tot_diff_amt=customer_Invoice_Net_Amt_INR.replace(",","");
								}
								queryString="SELECT nvl(SUM(GROSS_AMT_INR),'0') + "+dlng_total_gross_amt_finan+" FROM FMS7_INVOICE_MST WHERE CONTRACT_TYPE IN ('S','L','K') AND CUSTOMER_CD='"+customer_cd+"' "
										+ "AND FINANCIAL_YEAR='"+fin_year+"' AND HLPL_INV_SEQ_NO NOT IN (SELECT B.HLPL_INV_SEQ_NO FROM FMS7_DR_CR_NOTE B WHERE B.CONTRACT_TYPE IN ('S','L','K') "
										+ "	AND B.CUSTOMER_CD='"+customer_cd+"' AND B.FINANCIAL_YEAR='"+fin_year+"' AND B.CRITERIA ='REV_INV' AND "
										+ "B.DR_CR_FLAG='cr' AND B.APRV_BY IS NOT NULL) AND HLPL_INV_SEQ_NO NOT IN (SELECT C.HLPL_INV_SEQ_NO FROM FMS7_MANUAL_DR_CR_NOTE C WHERE "
										+ "C.CONTRACT_TYPE IN ('S','L','K') AND C.CUSTOMER_CD='"+customer_cd+"' "
										+ "AND C.FINANCIAL_YEAR='"+fin_year+"' AND C.CRITERIA ='REV_INV' AND C.DR_CR_FLAG='cr' AND C.APRV_BY IS NOT NULL) ";
								//System.out.println("queryString--"+queryString);
								//System.out.println("queryString--"+queryString);
								rset=stmt.executeQuery(queryString);
								if(rset.next()){
									//total_gross_amt_finan=nf.format(Double.parseDouble(rset.getString(1)==null?"0":rset.getString(1)));
									queryString1="SELECT nvl(SUM(DR_CR_GROSS_AMT_INR),'0') FROM FMS7_DR_CR_NOTE WHERE CONTRACT_TYPE IN ('S','L','K') AND CUSTOMER_CD='"+customer_cd+"' "
											+ "AND FINANCIAL_YEAR='"+fin_year+"' AND FLAG='Y' AND DR_CR_FLAG='dr' AND APRV_BY IS NOT NULL ";
									//System.out.println("queryString--"+queryString1);
									rset3=stmt3.executeQuery(queryString1);
									if(rset3.next()){
										total_gross_amt_finan=nf.format(Double.parseDouble(rset.getString(1)==null?"":rset.getString(1))+Double.parseDouble(rset3.getString(1)==null?"":rset3.getString(1)));
									}else{
										total_gross_amt_finan=nf.format(Double.parseDouble(rset.getString(1)==null?"":rset.getString(1)));
									}
									//for getting manual invoice
									queryString1="SELECT nvl(SUM(GROSS_AMT_INR),'0') FROM FMS7_MANUAL_INVOICE_MST WHERE CONTRACT_TYPE IN ('S','L','K') AND CUSTOMER_CD='"+customer_cd+"' "
											+ "AND FINANCIAL_YEAR='"+fin_year+"' AND HLPL_INV_SEQ_NO NOT IN (SELECT B.HLPL_INV_SEQ_NO FROM FMS7_DR_CR_NOTE B WHERE B.CONTRACT_TYPE IN ('S','L','K') "
											+ "	AND B.CUSTOMER_CD='"+customer_cd+"' AND B.FINANCIAL_YEAR='"+fin_year+"' AND B.CRITERIA ='REV_INV' AND "
											+ "B.DR_CR_FLAG='cr' AND B.APRV_BY IS NOT NULL) AND HLPL_INV_SEQ_NO NOT IN (SELECT C.HLPL_INV_SEQ_NO FROM FMS7_MANUAL_DR_CR_NOTE C WHERE "
											+ "C.CONTRACT_TYPE IN ('S','L','K') AND C.CUSTOMER_CD='"+customer_cd+"' "
											+ "AND C.FINANCIAL_YEAR='"+fin_year+"' AND C.CRITERIA ='REV_INV' AND C.DR_CR_FLAG='cr' AND C.APRV_BY IS NOT NULL) ";
									//System.out.println("queryString1--"+queryString1);
									rset3=stmt3.executeQuery(queryString1);
									if(rset3.next()){
										total_gross_amt_finan=nf.format(Double.parseDouble(total_gross_amt_finan)+Double.parseDouble(rset3.getString(1)==null?"":rset3.getString(1)));
									}
									//
									//FOR GETTING DEBIT NOTE FROM MANUAL TABLE 
									queryString1="SELECT nvl(SUM(DR_CR_GROSS_AMT_INR),'0') FROM FMS7_MANUAL_DR_CR_NOTE WHERE CONTRACT_TYPE IN ('S','L','K') AND CUSTOMER_CD='"+customer_cd+"' "
											+ "AND FINANCIAL_YEAR='"+fin_year+"' AND FLAG='Y' AND DR_CR_FLAG='dr' AND APRV_BY IS NOT NULL ";
									//System.out.println("queryString--"+queryString1);
									rset3=stmt3.executeQuery(queryString1);
									if(rset3.next()){
										total_gross_amt_finan=nf.format(Double.parseDouble(total_gross_amt_finan)+Double.parseDouble(rset3.getString(1)==null?"":rset3.getString(1)));
									}
									//
									//FOR GETTING CREDIT NOTE FROM TABLE 
									queryString1="SELECT nvl(SUM(DR_CR_GROSS_AMT_INR),'0') FROM FMS7_DR_CR_NOTE WHERE CONTRACT_TYPE IN ('S','L','K') AND CUSTOMER_CD='"+customer_cd+"' "
											+ "AND FINANCIAL_YEAR='"+fin_year+"' AND FLAG='Y' AND DR_CR_FLAG='cr' and criteria!='REV_INV' AND APRV_BY IS NOT NULL ";
									//System.out.println("queryString--"+queryString1);
									rset3=stmt3.executeQuery(queryString1);
									if(rset3.next()){
										total_gross_amt_finan=nf.format(Double.parseDouble(total_gross_amt_finan)-Double.parseDouble(rset3.getString(1)==null?"":rset3.getString(1)));
									}
									//
									//FOR GETTING CREDIT NOTE FROM TABLE 
									queryString1="SELECT nvl(SUM(DR_CR_GROSS_AMT_INR),'0') FROM FMS7_MANUAL_DR_CR_NOTE WHERE CONTRACT_TYPE IN ('S','L','K') AND CUSTOMER_CD='"+customer_cd+"' "
											+ "AND FINANCIAL_YEAR='"+fin_year+"' AND FLAG='Y' AND DR_CR_FLAG='cr' and criteria!='REV_INV' AND APRV_BY IS NOT NULL  ";
									//System.out.println("queryString--"+queryString1);
									rset3=stmt3.executeQuery(queryString1);
									if(rset3.next()){
										total_gross_amt_finan=nf.format(Double.parseDouble(total_gross_amt_finan)-Double.parseDouble(rset3.getString(1)==null?"":rset3.getString(1)));
									}
									
//									System.out.println("total_gross_amt_finan--"+total_gross_amt_finan+"---customer_Invoice_Gross_Amt_INR--"+drcrgrossamt);
									/*
									 * if(Double.parseDouble(total_gross_amt_finan)>tcs_limit_amt){
									 * tds_amt=drcrgrossamt.replace(",",""); }else{ if(!drcrgrossamt.equals("")){
									 * double
									 * tot_amt=Double.parseDouble(drcrgrossamt.replace(",",""))+Double.parseDouble(
									 * total_gross_amt_finan); // System.out.println("tot_amt--"+tot_amt);
									 * if(tot_amt>tcs_limit_amt){ tds_amt=nf.format(tot_amt-tcs_limit_amt); } } }
									 */
									if(Double.parseDouble(total_gross_amt_finan)>tcs_limit_amt){
										tds_amt=customer_Invoice_Gross_Amt_INR.replace(",","");
									}else{
										if(!customer_Invoice_Gross_Amt_INR.equals("")){
											tds_amt=customer_Invoice_Gross_Amt_INR.replace(",","");
										}
									}
								}else{
									/*
									 * if(Double.parseDouble(dlng_total_gross_amt_finan)>tcs_limit_amt){
									 * tds_amt=drcrgrossamt.replace(",",""); }else{ if(!drcrgrossamt.equals("")){
									 * double
									 * tot_amt=Double.parseDouble(drcrgrossamt.replace(",",""))+Double.parseDouble(
									 * dlng_total_gross_amt_finan); // System.out.println("tot_amt--"+tot_amt);
									 * if(tot_amt>tcs_limit_amt){ // tds_amt=nf.format(tot_amt-tcs_limit_amt);
									 * tds_amt=nf.format(tot_amt); } } }
									 */
									if(Double.parseDouble(total_gross_amt_finan)>tcs_limit_amt){
										tds_amt=customer_Invoice_Gross_Amt_INR.replace(",","");
									}else{
										if(!customer_Invoice_Gross_Amt_INR.equals("")){
											tds_amt=customer_Invoice_Gross_Amt_INR.replace(",","");
										}
									}
								}
							}
//							System.out.println("tds_amt----"+tds_amt);
							tds_app_flag = "Y";
							tcs_app_flag = "N";
					 } //if of reversal
					}//if of reversal
				}
			}
			if(!dr_cr_no.equalsIgnoreCase("")) { //fetching rates for the service invoices dr/cr note
				for(int i = 0 ; i < View_truck_inv_no.size() ; i ++) {
					
					String rateSql = "select dr_cr_qty,diff_qty from DLNG_DR_CR_SERVICE_DTL a"
							+ " where a.dr_cr_doc_no = '"+dr_cr_doc_no+"'"
							+ " and a.truck_no = '"+View_truck_nm.elementAt(i)+"'"
							+ " and a.supply_dt = to_date('"+View_truck_inv_dt.elementAt(i)+"','dd/mm/yyyy')"
							+ " and a.rev_no = (select max(b.rev_no) from DLNG_DR_CR_SERVICE_DTL b"
								+ " where b.dr_cr_doc_no = a.dr_cr_doc_no and b.truck_no = a.truck_no and b.supply_dt = a.supply_dt)";
//					System.out.println("rateSql----"+rateSql);
					rset = stmt.executeQuery(rateSql);
					if(rset.next()) {
						serv_dr_cr_qty.add(rset.getString(1)==null?"":rset.getString(1));
						serv_diff_qty.add(rset.getString(2)==null?"":rset.getString(2));
					}else {
						serv_dr_cr_qty.add("");
						serv_diff_qty.add("");
					}
				}
			}
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
}
	public double getTotal_dr_cr_qty() {
		return total_dr_cr_qty;
	}

	public String getRmk_desc() {
		return rmk_desc;
	}

	public void setRmk_desc(String rmk_desc) {
		this.rmk_desc = rmk_desc;
	}

	public Integer getDr_cr_aprv() {
		return dr_cr_aprv;
	}

	public void setDr_cr_aprv(Integer dr_cr_aprv) {
		this.dr_cr_aprv = dr_cr_aprv;
	}

	public void setDr_cr_sales_rate(String dr_cr_sales_rate) {
		this.dr_cr_sales_rate = dr_cr_sales_rate;
	}
	public void createColumn_inv_DATES()
	{
		try
		{
			int count=0;
			String query="SELECT COUNT(COLUMN_NAME) FROM COLS WHERE UPPER(TABLE_NAME) LIKE 'DLNG_DR_CR_NOTE' "
					+ "AND UPPER(COLUMN_NAME) LIKE 'ITEM_DESCRIPTION' ";
			rset=stmt.executeQuery(query);
			if(rset.next())
			{
				count=rset.getInt(1);
			}
			if(count==0)
			{
				query="alter table DLNG_DR_CR_NOTE add ITEM_DESCRIPTION VARCHAR2(500)";
				stmt.executeUpdate(query);
				
				query="alter table DLNG_DR_CR_NOTE add TAX_REMARK VARCHAR2(100)";
				stmt.executeUpdate(query);
				conn.commit();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	String cust_name = "";
	String view_flag = "N";
	String invoice_dt = "",tot_qty="0",sale_rate="0",gross_amt_inr="0",net_amt_inr="0",exch_rate="0",dr_cr_qty="0",dr_cr_tax_rt="";

	String dr_cr_aprv_by = "0";
	String fgsa_no = "";
	String fgsa_rev_no = "";
	String sn_no = "";
	String sn_rev_no = "";
	String contract_type = "";
	String year = "0";
	String dr_cr_fin_year= "";
	String customer_plant_seq_no = "";
	String plant_no ="0";
	String hlplInvoiceNo = "0";
	String fin_year= "",cr_dr1="",tax_amt_inr ="";String dr_cr_sales_rate="";Integer dr_cr_aprv=0;String drcrgrossamt="";
	String dr_cr_exg_rt = "";
	String dr_cr_tcs_flag="";
	String calc_base = "";
	String rate_service = "";
	String diff_inr_km = "",diff_inr_mmbtu = "",dr_cr_inr_km = "",dr_cr_inr_mmbtu = "";
	String dr_cr_tax_rate = "",diff_tax_str = "";
	String dr_cr_seq_no = "";
	String SDr_cr_ref_inv_no = "";
	
	public String getDr_cr_tcs_flag() {
		return dr_cr_tcs_flag;
	}
	public void setDr_cr_tcs_flag(String dr_cr_tcs_flag) {
		this.dr_cr_tcs_flag = dr_cr_tcs_flag;
	}
	String dr_cr_no = "";
	String dr_cr_no_disp = "";
	String dr_cr_dt = "";
	String diff_qty = "";
	String diff_amt = "";
	String day_diff = "";
	String exg_rt = "";	
	String gross_amt = "";
	String net_amt = "";
	String qty = "";		
	String inv_dt = "";		
	String inv_no = "";
	String rmk_desc="";
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
	String Mdr_cr_dt = "";
	double grossamttax=0;
	String dr_cr_gross_amt = "";
	String dr_cr_net_amt  = "";
	String dr_cr_gross_usd = "";
	String gross_amt_usd = "";
	String tax_struc_cd = "";
	String dr_cr_DUEDT="";
	String item_desc="";
	String tax_rmk="";
	String tax_amt_inr_MODIFY="";
	String dr_cr_inv_dt="";
	String taxrt="";
	Vector Mtaxrt=new Vector();
	String Mtaxnm="";
	String diff_exg_rt1="";
	String applicable_qty1="";
	String dr_cr_net_amt_inr1="";
	String criteria1="";							
	String due_dt1 = "";				
	String exg_rt1 = "";
	String cr_dr2= "";
	String dr_cr_no1 ="";
	String split_qty="";
	String dr_diff_grossusd="";
	String dr_cr_dt1="";
	String dr_cr_exg_rt1="";
	String dr_cr_gross_amt1="";
	String dr_cr_tax_amt="";
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
	String accepted_Offspec_Qty1 = "";
	String accepted_FM_Qty1 = "";
	String total_Invoice_Qty1="";
	String diff_qty1="";
	String flag_drcr="";String st_dt="";String bill_dt="";
	String end_dt="";
	String tax_str_nm="";
	String tax_td = "";
	String tax_fact="";
	double tcs_limit_amt=5000000;
	String tcs_app_flag="";
	String tcs_amt="";
	String tds_amt = "";
	String tcs_nm="";
	String fact="";
	String tcs_sht_nm="";
	String Mnet_amt_inr="";
	Vector inv_seq_no=new Vector();
	Vector tcs_invdt=new Vector();
	Vector tcs_net_amt=new Vector();
	Vector tcs_fgsano=new Vector();
	Vector tcs_fgsarevno=new Vector();
	Vector tcs_snno=new Vector();
	Vector tcs_snrevno=new Vector();
	String total_tcs_amt="";
	String irn_no="";
	String qr_code="";
	Vector flag_invoice=new Vector();
	Vector irn_flag=new Vector();
	Vector irn_flag_cd=new Vector();
	Vector Invoice_period_date1=new Vector();
	boolean flag_DCB = false;
	String tax_str="";
	String taxnm="";
	String Inv_no="";
	int tdsCnt = 0; 
	int tdsEntryCnt = 0; 
	
	String Modify_flag="";int gst_eff_dt = 20170701;boolean date_flag = false;
	public String customer_Invoice_Gross_Amt_USD = "";
	public String customer_Invoice_Gross_Amt_INR = "";
	public String customer_Invoice_Net_Amt_INR = "";
	public String customer_Invoice_Exchg_Rate = "";
	public String customer_Invoice_Exchg_Rate2 = "";
	public String customer_Invoice_Tax_Amt_INR = "";
	public Vector customer_Invoice_Tax_Code = new Vector();
	public Vector customer_Invoice_Tax_Abbr = new Vector();
	public Vector customer_Invoice_Tax_Name = new Vector();
	public Vector customer_Invoice_Tax_Amt = new Vector();
	public Vector customer_Invoice_Tax_Rate = new Vector();
	double customer_Invoice_Tax_Amt_INR1=0;
	
	
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
	Vector NEW_INV_SEQ_NO = new Vector();
	Vector CALC_BASE_SERVICE = new Vector();
	Vector RATE_SERVICE = new Vector();
	public Vector DUE_DT = new Vector();
	public Vector EXCHG_RATE_VALUE = new Vector();
	public Vector SN_NO = new Vector();
	public Vector SN_REV_NO = new Vector();
	public Vector FGSA_NO = new Vector();
	public Vector FGSA_REV_NO = new Vector();
	
	public Vector GROSS_AMT_USD = new Vector();
	public Vector TAX_STRUCT_CD = new Vector();
	public Vector DR_CR_MAPPING_ID=new Vector(); 
	public Vector TAX_AMT_INR=new Vector();  
	public Vector FLAG=new Vector(); 
	public String DR_CR_MAPPING_ID_STR="";
	String cust_nm="";
	
	public Vector DR_CR_NO= new Vector();
	public Vector DR_CR_FIN_YEAR= new Vector();
	public Vector DR_CR_NO_DISP= new Vector();
	public Vector DR_CR_DOC_NO= new Vector(); 
	public Vector DR_CR_APRV_BY=new Vector();  
	public Vector DR_CR_APRV_DT=new Vector();  
	public Vector DR_CR_DTL_REM=new Vector();
	public Vector PAY_CD= new Vector();
	public Vector PAY_DT= new Vector();		
	public Vector PAY_AMT= new Vector();
	public Vector PAY_TYPE= new Vector();
	public Vector ADV_TYPE= new Vector();
	public Vector SETTLE_FLAG= new Vector();
	
	public Vector serv_dr_cr_qty = new Vector();
	public Vector serv_diff_qty = new Vector();
	
	String flagCust = "N";
	Vector View_amount  = new Vector();
	Vector View_invoice_qty = new Vector();
	Vector View_km =  new Vector();;
	Vector View_rate =  new Vector();
	Vector View_service_inv_dt =  new Vector();
	Vector View_truck_inv_dt =  new Vector();
	Vector View_truck_inv_no =  new Vector();
	Vector View_truck_nm = new Vector();
	Vector view_truck_inv_cnt = new Vector();
	Vector view_truck_inv_dr_cr_qty = new Vector();
	Vector view_truck_inv_dr_cr_diff_qty = new Vector();
	
	double total_qty = 0; 
	double total_amt = 0; 
	double total_diff_qty = 0; 
	double total_dr_cr_qty = 0 ; 
	
	public String getFlagCust() {
		return flagCust;
	}

	public void setFlagCust(String flagCust) {
		this.flagCust = flagCust;
	}
	
String master_flag = "N";
String tds_app_flag =  "";
double serv_total_tax_perc = 0;
Vector serv_tax_desc = new Vector();
Vector serv_tax_perc = new Vector();

Vector Vdr_cr_no = new Vector();
Vector Vdr_cr_cnt = new Vector();

public void Customer_NM()
{
	try
	{
		queryString = "SELECT CUSTOMER_CD,CUSTOMER_NAME,CUSTOMER_ABBR FROM FMS7_CUSTOMER_MST WHERE CUSTOMER_CD='"+customer_cd+"' ";
		if(flagCust.equals("Y")) {
			queryString += " AND EFF_DT = (SELECT MAX(EFF_DT) FROM FMS7_CUSTOMER_MST WHERE CUSTOMER_CD='"+customer_cd+"' AND EFF_DT<=TO_DATE('"+from_dt+"','dd/mm/yyyy')) ";
		}
//		//System.out.println("FMS7_CUSTOMER_MST Fetch Query = "+queryString);
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
	public void Customer_DTL()throws SQLException,IOException
	
	{
		try
		{
			queryString = "SELECT CUSTOMER_CD,CUSTOMER_NAME,CUSTOMER_ABBR FROM FMS7_CUSTOMER_MST A ";
			queryString += " WHERE EFF_DT=(SELECT MAX(EFF_DT) FROM FMS7_CUSTOMER_MST B WHERE A.CUSTOMER_CD=B.CUSTOMER_CD)"
					+ " AND CUSTOMER_CD IN (SELECT CUSTOMER_CD FROM DLNG_FLSA_MST WHERE FLAG='Y') or CUSTOMER_CD IN (SELECT CUSTOMER_CD FROM DLNG_TENDER_MST WHERE FLAG='Y') ";
			queryString += " ORDER BY CUSTOMER_ABBR";
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
	
	public void fetchDr_CrDetails()throws SQLException,IOException
	{
		String inv_no="";
		try
		{
			String contSql = "";
			if(inv_typ.equalsIgnoreCase("S")) {
				contSql = " AND contract_type = 'V' ";
			}else if(inv_typ.equalsIgnoreCase("C")) {
				contSql = " AND contract_type != 'V' ";
			}
			Vector multiDrCrNo = new Vector();
			Vector SEQ_NO = new Vector();
			if(activity.equalsIgnoreCase("insert"))
			{
				queryString="SELECT SALE_PRICE,GROSS_AMT_INR,NET_AMT_INR,TOTAL_QTY,TO_CHAR(INVOICE_DT,'DD/MM/YYYY')," +
				"TO_CHAR(PERIOD_START_DT,'DD/MM/YYYY'),TO_CHAR(PERIOD_END_DT,'DD/MM/YYYY'),PLANT_SEQ_NO,A.HLPL_INV_SEQ_NO," +
				"CONTRACT_TYPE,A.FINANCIAL_YEAR,TO_CHAR(DUE_DT,'DD/MM/YYYY'),EXCHG_RATE_VALUE,SN_NO,SN_REV_NO,FGSA_NO,FGSA_REV_NO," +
				"GROSS_AMT_USD,TAX_STRUCT_CD " +
				",MAPPING_ID " +
				",TAX_AMT_INR, FLAG,NEW_INV_SEQ_NO,GROSS_AMT_INR_NEW,ADVANCE_ADJ_AMT_NEW,ADVANCE_ADJ_GROSS_AMT_INR,"
				+ "ADV_ADJ_FLG,INV_AMT_INR,GROSS_AMT_USD_NEW " + 
				"FROM DLNG_INVOICE_MST A Where CUSTOMER_CD='"+customer_cd+"' AND A.FINANCIAL_YEAR='"+cr_dr_yr+"' AND " +
				"INVOICE_DT >= TO_DATE('"+from_dt+"','DD/MM/YYYY') AND INVOICE_DT <= TO_DATE('"+to_dt+"','DD/MM/YYYY') AND " +
				"A.HLPL_INV_SEQ_NO||A.FINANCIAL_YEAR||A.CONTRACT_TYPE NOT IN (SELECT B.HLPL_INV_SEQ_NO||B.FINANCIAL_YEAR||B.CONTRACT_TYPE FROM DLNG_DR_CR_NOTE B WHERE " +
				"B.CRITERIA='"+criteria+"')" +
				" AND APPROVED_FLAG='Y' AND (FLAG='Y' or FLAG='X')"
				+" "+contSql+" ORDER BY CONTRACT_TYPE "; 	
			}
			else if(activity.equalsIgnoreCase("update"))
			{
					queryString="SELECT SALE_PRICE,GROSS_AMT_INR,NET_AMT_INR,TOTAL_QTY,TO_CHAR(INVOICE_DT,'DD/MM/YYYY')," +
					"TO_CHAR(PERIOD_START_DT,'DD/MM/YYYY'),TO_CHAR(PERIOD_END_DT,'DD/MM/YYYY'),PLANT_SEQ_NO,A.HLPL_INV_SEQ_NO," +
					"CONTRACT_TYPE,A.FINANCIAL_YEAR,TO_CHAR(DUE_DT,'DD/MM/YYYY'),EXCHG_RATE_VALUE,SN_NO,SN_REV_NO,FGSA_NO,FGSA_REV_NO," +
					"GROSS_AMT_USD,TAX_STRUCT_CD " +
					",MAPPING_ID " + 
					",TAX_AMT_INR, FLAG,NEW_INV_SEQ_NO,GROSS_AMT_INR_NEW,ADVANCE_ADJ_AMT_NEW,ADVANCE_ADJ_GROSS_AMT_INR,"
					+ "ADV_ADJ_FLG,INV_AMT_INR,GROSS_AMT_USD_NEW " + 
					"FROM DLNG_INVOICE_MST A Where CUSTOMER_CD='"+customer_cd+"' AND " +
					"INVOICE_DT >= TO_DATE('"+from_dt+"','DD/MM/YYYY') AND " +
					"INVOICE_DT <= TO_DATE('"+to_dt+"','DD/MM/YYYY') AND A.HLPL_INV_SEQ_NO||A.FINANCIAL_YEAR||A.CONTRACT_TYPE IN " +
					"(SELECT B.HLPL_INV_SEQ_NO||B.FINANCIAL_YEAR||B.CONTRACT_TYPE FROM DLNG_DR_CR_NOTE B WHERE B.CRITERIA='"+criteria+"' AND B.DR_CR_FLAG='"+cr_dr+"')" +
					" AND APPROVED_FLAG='Y' (FLAG='Y' or FLAG='X') "
					+ " "+contSql+" ORDER BY CONTRACT_TYPE ";
			}
//			System.out.println("queryString**********"+queryString);
			if(!criteria.equalsIgnoreCase("C-FORM"))
			{
				rset = stmt.executeQuery(queryString);				
				while(rset.next())
				{		
					String AdvAdjFlag = rset.getString(27)==null?"NA":rset.getString(27);
					boolean vol_dis = false;
					String map_id = rset.getString(10)+":"+rset.getString(11)+":"+rset.getString(9)+":%";
					String q_vol = "select nvl(amount,'') from fms7_inv_compo_dtl where price_cd='14' and "
							+ "inv_seq_no like '"+map_id+"' and (flag_temp not like 'T' or flag_temp is null) ";
					rset1 = stmt1.executeQuery(q_vol);
					if(rset1.next()) {
						String vol_adj_amt = rset1.getString(1)==null?"":rset1.getString(1);
						if(!vol_adj_amt.equals("")) {
							vol_dis = true;
						}
					}

					boolean prev_adj_flag = false;
					String adj_amt_prev = rset.getString(25)==null?"":rset.getString(25);
					if(!adj_amt_prev.equals("")) {
						prev_adj_flag = true;
					}
					
					boolean tax_adj_flag = false, adj_flag = false; String tax_adj_amt = "0";
					
					
					q_vol = "select amount from fms7_inv_compo_dtl where price_cd = '1' and "
							+ "inv_seq_no like '"+map_id+"' and (flag_temp not like 'T' or flag_temp is null) ";
					rset2 = stmt2.executeQuery(q_vol);
					if(rset2.next()) {
						tax_adj_amt = rset2.getString(1)==null?"0":rset2.getString(1);
						adj_flag = true;
						
						q_vol = "select count(price_cd) from fms7_inv_compo_dtl where price_cd in ('6','7','8','9','10','11','12','13') and "
								+ "inv_seq_no like '"+map_id+"' and (flag_temp not like 'T' or flag_temp is null) ";
						rset1 = stmt1.executeQuery(q_vol);
						if(rset1.next()) {
							int count_adj = rset1.getInt(1);
							if(count_adj>0) {
								tax_adj_flag = true;
							}
						}
					}
					
					//System.out.println("Flags..."+rset.getString(23)+"=="+prev_adj_flag+"=="+tax_adj_flag+"=="+adj_flag);
					
					String gross_amt_inr = rset.getString(2)==null?"0":rset.getString(2);
					if(prev_adj_flag) {
						if(vol_dis || adj_flag) 
							gross_amt_inr = rset.getString(2)==null?"0":rset.getString(2);
						else {
							gross_amt_inr = rset.getString(26)==null?"0":rset.getString(26);
							if(rset.getString(26)==null) {
								gross_amt_inr = rset.getString(2)==null?"0":rset.getString(2);
							}
						}
					}
					
					if(tax_adj_flag) {
						if(!gross_amt_inr.equals(""))
							gross_amt_inr = ""+(Double.parseDouble(gross_amt_inr)-Double.parseDouble(tax_adj_amt));
					} else {
						if(adj_flag && prev_adj_flag) {
							gross_amt_inr = rset.getString(2)==null?"0":rset.getString(2);
						} else if(adj_flag) {
							if(AdvAdjFlag.equals("AA")) {
								gross_amt_inr = rset.getString(26)==null?"0":rset.getString(26);
							} else if(AdvAdjFlag.equals("BA")) {
								gross_amt_inr = rset.getString(28)==null?"0":rset.getString(28);
								gross_amt_inr = ""+(Double.parseDouble(gross_amt_inr)-Double.parseDouble(tax_adj_amt));
							}
						}
					}
					gross_amt_inr = nf3.format(Double.parseDouble(gross_amt_inr));
					
					String gross_amt_usd = rset.getString(18)==null?"0":rset.getString(18);
					if(prev_adj_flag || vol_dis) {
						gross_amt_usd = rset.getString(29)==null?"0":rset.getString(29);
						if(rset.getString(29)==null) {
							gross_amt_usd = rset.getString(18)==null?"0":rset.getString(18);
						}
					}
					
					SALE_PRICE.add(rset.getString(1)==null?"":nf2.format(Double.parseDouble(rset.getString(1))));	
					GROSS_AMT_INR.add(gross_amt_inr);
					NET_AMT_INR.add(rset.getString(3)==null?"":nf3.format(Double.parseDouble(rset.getString(3))));
					TOTAL_QTY.add(rset.getString(4)==null?"":nf.format(Double.parseDouble(rset.getString(4))));
					INVOICE_DT.add(rset.getString(5)==null?"":rset.getString(5));
					PERIOD_START_DT.add(rset.getString(6)==null?"":rset.getString(6));
					PERIOD_END_DT.add(rset.getString(7)==null?"":rset.getString(7));
					PLANT_SEQ_NO.add(rset.getString(8)==null?"":rset.getString(8));
					HLPL_INV_SEQ_NO.add(rset.getString(9)==null?"":rset.getString(9));
					CONTRACT_TYPE.add(rset.getString(10)==null?"":rset.getString(10));
					String cont_type = rset.getString(10)==null?"":rset.getString(10);
					FINANCIAL_YEAR.add(rset.getString(11)==null?"":rset.getString(11));
					DUE_DT.add(rset.getString(12)==null?"":rset.getString(12));
					EXCHG_RATE_VALUE.add(rset.getString(13)==null?"":rset.getString(13));
					SN_NO.add(rset.getString(14)==null?"":rset.getString(14));
					SN_REV_NO.add(rset.getString(15)==null?"":rset.getString(15));
					FGSA_NO.add(rset.getString(16)==null?"":rset.getString(16));
					FGSA_REV_NO.add(rset.getString(17)==null?"":rset.getString(17));
					GROSS_AMT_USD.add(gross_amt_usd);
					TAX_STRUCT_CD.add(rset.getString(19)==null?"":rset.getString(19));
					DR_CR_MAPPING_ID.add(rset.getString(20)==null?"":rset.getString(20));
					TAX_AMT_INR.add(rset.getString(21)==null?"":rset.getString(21));
					FLAG.add(rset.getString(22)==null?"":rset.getString(22));
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
					String calc_base = "";
					if(cont_type.equalsIgnoreCase("V")) { // for Service Invoice
						
						String servDtlSql = "select CALC_BASE from DLNG_SERVICE_INVOICE_ATTACH where "
								+ " SERVICE_INVOICE_NO = '"+rset.getString(23)+"' ";
						rset1 = stmt1.executeQuery(servDtlSql);
						if(rset1.next()) {
							calc_base = rset1.getString(1)==null?"":rset1.getString(1);
						}
					}
					CALC_BASE_SERVICE.add(calc_base);
					
					/* fetching credit/debit note details */
					
				}
			}
			int dr_cr_cnt = 0 ;int inv_drcr_cnt = 0 ;
			for(int i=0;i<FINANCIAL_YEAR.size();i++)
			{
				dr_cr_cnt = 0 ;
				inv_drcr_cnt = 0 ;
				/*queryString ="SELECT B.DR_CR_NO,B.DR_CR_FIN_YEAR, DR_CR_DOC_NO " +
						", APRV_BY, TO_CHAR(APRV_DT,'DD/MM/YYYY')" + 
						" FROM DLNG_DR_CR_NOTE B " +
						"WHERE B.CRITERIA='"+criteria+"' AND " +
						"B.HLPL_INV_SEQ_NO='"+HLPL_INV_SEQ_NO.elementAt(i)+"' AND B.FINANCIAL_YEAR='"+FINANCIAL_YEAR.elementAt(i)+"' " +
						"AND CONTRACT_TYPE='"+CONTRACT_TYPE.elementAt(i)+"' "; 
//				}
//				System.out.println("+fetching Data.."+queryString);
				rset = stmt.executeQuery(queryString);				
				if(rset.next())
				{
					DR_CR_NO.add(rset.getString(1)==null?"":rset.getString(1));
					DR_CR_NO_DISP.add(rset.getString(1)==null?"":rset.getString(1)+("/"+rset.getString(2).substring(2,4)+"-"+rset.getString(2).substring(7)));
					DR_CR_FIN_YEAR.add(rset.getString(2)==null?"":rset.getString(2));
					DR_CR_DOC_NO.add(rset.getString(3)==null?"":rset.getString(3)); 
					DR_CR_APRV_BY.add(rset.getString(4)==null?"":rset.getString(4)); 
					DR_CR_APRV_DT.add(rset.getString(5)==null?"":rset.getString(5)); 
				}
				else
				{
					DR_CR_NO.add("");
					DR_CR_FIN_YEAR.add("");
					DR_CR_NO_DISP.add("");
					DR_CR_DOC_NO.add(""); 
					DR_CR_APRV_BY.add("");
					DR_CR_APRV_DT.add(""); 
				}*/
				queryString ="select X1_VAL,X2_VAL,X3_VAL,X4_VAL,X5_VAL,X6_VAL,X7_VAL,X8_VAL,X9_VAL,X10_VAL,X11_VAL,X12_VAL,X13_VAL,INV_TYPE from ("
						+ " SELECT B.DR_CR_NO AS X1_VAL,B.DR_CR_FIN_YEAR AS X2_VAL, DR_CR_DOC_NO AS X3_VAL,APRV_BY AS X4_VAL, "
						+ " TO_CHAR(APRV_DT,'DD/MM/YYYY') AS X5_VAL,"
						+ " B.SEQ_NO AS X6_VAL,to_char(B.DR_CR_DT,'dd/mm/yyyy') AS X7_VAL,"
						+ " (CASE WHEN nvl(DR_CR_QTY,0) != 0 THEN DR_CR_QTY ELSE nvl(TOTAL_QTY,0) END) AS X8_VAL,"
						+ " (CASE WHEN nvl(DR_CR_SALE_RATE,0) != 0 THEN DR_CR_SALE_RATE ELSE nvl(SALE_PRICE,0) END) AS X9_VAL,"
						+ " nvl(DR_CR_GROSS_AMT_INR,0) AS X10_VAL,"
						+ " nvl(DR_CR_NET_AMT_INR,0) AS X11_VAL,CRITERIA AS X12_VAL,SEQ_NO AS X13_VAL,'INVOICE' as INV_TYPE "
						+ " FROM DLNG_DR_CR_NOTE B " +
						" WHERE B.HLPL_INV_SEQ_NO='"+HLPL_INV_SEQ_NO.elementAt(i)+"'"
						+ " AND B.SN_NO = '"+SN_NO.elementAt(i)+"'"
						+ " AND B.FGSA_NO = '"+FGSA_NO.elementAt(i)+"'"
						+ " AND B.PLANT_SEQ_NO = '"+PLANT_SEQ_NO.elementAt(i)+"'"
						+ " AND B.FINANCIAL_YEAR='"+FINANCIAL_YEAR.elementAt(i)+"'"
						+ " AND B.CONTRACT_TYPE='"+CONTRACT_TYPE.elementAt(i)+"'"
						+ " AND B.CUSTOMER_CD = '"+customer_cd+"'"
						+ " AND B.INVOICE_DT = to_date('"+INVOICE_DT.elementAt(i)+"','dd/mm/yyyy') "
						+ " AND B.APRV_BY > 0 AND B.NEW_INV_SEQ_NO IS NULL "
						+ " UNION ALL "
						+ " SELECT B.DR_CR_NO AS X1_VAL,B.DR_CR_FIN_YEAR AS X2_VAL, DR_CR_DOC_NO AS X3_VAL,APRV_BY AS X4_VAL, "
						+ " TO_CHAR(APRV_DT,'DD/MM/YYYY') AS X5_VAL,"
						+ " B.SEQ_NO AS X6_VAL,to_char(B.DR_CR_DT,'dd/mm/yyyy') AS X7_VAL,"
						+ " (CASE WHEN nvl(DR_CR_QTY,0) != 0 THEN DR_CR_QTY ELSE nvl(TOTAL_QTY,0) END) AS X8_VAL,"
						+ " (CASE WHEN nvl(DR_CR_SALE_RATE,0) != 0 THEN DR_CR_SALE_RATE ELSE nvl(SALE_PRICE,0) END) AS X9_VAL,"
						+ " nvl(DR_CR_GROSS_AMT_INR,0) AS X10_VAL,"
						+ " nvl(DR_CR_NET_AMT_INR,0) AS X11_VAL,CRITERIA AS X12_VAL,SEQ_NO AS X13_VAL,'DRCR' as INV_TYPE"
						+ " FROM DLNG_DR_CR_NOTE B " +
						" 	WHERE B.NEW_INV_SEQ_NO='"+NEW_INV_SEQ_NO.elementAt(i)+"'"
						+ " AND B.SN_NO = '"+SN_NO.elementAt(i)+"'"
						+ " AND B.FGSA_NO = '"+FGSA_NO.elementAt(i)+"'"
						+ " AND B.PLANT_SEQ_NO = '"+PLANT_SEQ_NO.elementAt(i)+"'"
						+ " AND B.FINANCIAL_YEAR='"+FINANCIAL_YEAR.elementAt(i)+"'"
						+ " AND B.CONTRACT_TYPE='"+CONTRACT_TYPE.elementAt(i)+"'"
						+ " AND B.CUSTOMER_CD = '"+customer_cd+"'"
//						+ " AND B.DR_CR_DT between to_date('"+from_dt+"','dd/mm/yyyy') and to_date('"+to_dt+"','dd/mm/yyyy')"
						+ " AND B.APRV_BY > 0 AND B.NEW_INV_SEQ_NO IS NOT NULL ) ORDER BY X13_VAL,to_date(X7_VAL,'dd/mm/yyyy') ";
//				}
				System.out.println("+fetching Data.."+queryString);
				rset = stmt.executeQuery(queryString);				
				while(rset.next())
				{
					DR_CR_NO.add(rset.getString(1)==null?"":rset.getString(1));
					DR_CR_NO_DISP.add(rset.getString(1)==null?"":rset.getString(1)+("/"+rset.getString(2).substring(2,4)+"-"+rset.getString(2).substring(7)));
					DR_CR_FIN_YEAR.add(rset.getString(2)==null?"":rset.getString(2));
					DR_CR_DOC_NO.add(rset.getString(3)==null?"":rset.getString(3)); 
					DR_CR_APRV_BY.add(rset.getString(4)==null?"":rset.getString(4)); 
					DR_CR_APRV_DT.add(rset.getString(5)==null?"":rset.getString(5)); 
					DR_CR_SEQ_NO.add(rset.getString(6)==null?"":rset.getString(6)); 
					DR_CR_DT.add(rset.getString(7)==null?"":rset.getString(7)); 
					DR_CR_QTY.add(nf.format(rset.getDouble(8))); 
					DR_CR_SALE_RATE.add(nf2.format(rset.getDouble(9)));
					DR_CR_GROSS_AMT_INR.add(nf3.format(rset.getDouble(10)));
					DR_CR_NET_AMT_INR.add(nf3.format(rset.getDouble(11)));
					DR_CR_CRITERIA.add(rset.getString(12)==null?"":rset.getString(12));
				
					String inv_type = rset.getString("INV_TYPE")==null?"":rset.getString("INV_TYPE");
					if(inv_type.equalsIgnoreCase("INVOICE")){
						
						
					}else{
						
					}
					
					dr_cr_cnt++;
					inv_drcr_cnt++;
				}
				
				Vdr_cr_cnt.add(dr_cr_cnt);
				
				String temp_criteria = "";
				queryString="select A.remark from DLNG_DR_CR_DTL A where "
						+ " CUSTOMER_CD='"+customer_cd+"' "
						+ " and HLPL_INV_SEQ_NO='"+HLPL_INV_SEQ_NO.elementAt(i)+"' "
						+ " and con_type='"+CONTRACT_TYPE.elementAt(i)+"' "
						+ " and FINANCIAL_YEAR='"+FINANCIAL_YEAR.elementAt(i)+"'"
						+ " and INVOICE_DT = to_date('"+INVOICE_DT.elementAt(i)+"','dd/mm/yyyy')"
						+ " and NEW_INV_SEQ_NO IS NULL ";
//				System.out.println("queryString****"+queryString);
				rset = stmt.executeQuery(queryString);				
				while(rset.next()) {
					temp_criteria+=rset.getString(1)==null?"":rset.getString(1);
					
				} 
//				System.out.println("temp_criteria****"+temp_criteria);
				DR_CR_DTL_REM.add(temp_criteria);
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	Vector VInv_FY=new Vector();
	Vector VInv_Dt_FY=new Vector();
	
	String fy ="",from_dt="",to_dt="",criteria="",cr_dr_yr="",customer_cd="",cr_dr="",activity="";
	
	public void fetchInvAvailableFY()throws SQLException,IOException
	{
		try
		{
			String query="SELECT DISTINCT(FINANCIAL_YEAR) FROM DLNG_INVOICE_MST " +
					" ORDER BY FINANCIAL_YEAR DESC";
			rset=stmt.executeQuery(query);
		//	////System.out.println("INV-FY: "+query);
			while(rset.next())
			{
				VInv_FY.add(rset.getString(1));				
			}
			if(fy.equals("0"))
			{
				for(int i=0; i<VInv_FY.size(); i++) 
				{
					/*
					 * query="SELECT DISTINCT(INVOICE_DT), TO_CHAR(INVOICE_DT, 'DD/MM/YYYY') " +
					 * " FROM DLNG_INVOICE_MST WHERE FINANCIAL_YEAR='"+VInv_FY.elementAt(i)+"' " +
					 * " ORDER BY INVOICE_DT DESC";
					 */
					query=  " SELECT DISTINCT(X_VAL) FROM ("
							+ " SELECT distinct(TO_CHAR(INVOICE_DT, 'DD/MM/YYYY')) as X_VAL  FROM DLNG_INVOICE_MST"
							+ " WHERE FINANCIAL_YEAR='"+VInv_FY.elementAt(i)+"'  "
							+ " union all"
							+ " SELECT DISTINCT(TO_CHAR(DR_CR_DT, 'DD/MM/YYYY'))  as X_VAL  FROM DLNG_DR_CR_NOTE "
							+ " WHERE DR_CR_FIN_YEAR='"+VInv_FY.elementAt(i)+"' and APRV_BY > 0  "
							+ " )ORDER BY to_date(X_VAL, 'dd/mm/yyyy') desc";
					rset=stmt.executeQuery(query);
//					System.out.println("1. INV-DT: "+query);
					while(rset.next())
					{
						VInv_Dt_FY.add(rset.getString(1));	
					}
				}
			}
			else
			{
				query=  " SELECT DISTINCT(X_VAL) FROM ("
						+ " SELECT distinct(TO_CHAR(INVOICE_DT, 'DD/MM/YYYY')) as X_VAL  FROM DLNG_INVOICE_MST"
						+ " WHERE FINANCIAL_YEAR='"+fy+"'  "
						+ " union all"
						+ " SELECT DISTINCT(TO_CHAR(DR_CR_DT, 'DD/MM/YYYY'))  as X_VAL  FROM DLNG_DR_CR_NOTE "
						+ " WHERE DR_CR_FIN_YEAR='"+fy+"' and APRV_BY > 0 "
						+ " )ORDER BY to_date(X_VAL, 'dd/mm/yyyy') desc";
				
				/*
				 * query= " SELECT DISTINCT(INVOICE_DT), TO_CHAR(INVOICE_DT, 'DD/MM/YYYY') " +
				 * " FROM DLNG_INVOICE_MST " + " WHERE FINANCIAL_YEAR='"+fy+"' " +
				 * " ORDER BY INVOICE_DT DESC";
				 */
				rset=stmt.executeQuery(query);
//				System.out.println("2. INV-DT: "+query);
				while(rset.next())
				{
					VInv_Dt_FY.add(rset.getString(1));	
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	String dr_cr_year="";
	String pan_no="";
	String gst_tin_no="";
	String cst_tin_no="";
	String diff_exg = "";
	String diff_rate = "";
	
	Vector Dr_cr_hlpl_inv_no = new Vector();
	Vector Dr_cr_cont_typ = new Vector();
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
	Vector DR_CR_NEW_INV_SEQ_NO = new Vector();
	Vector DR_CR_SEQ_NO = new Vector();
	Vector DR_CR_REF_INV_NO = new Vector();
	Vector DR_CR_DT = new Vector();
	Vector DR_CR_QTY = new Vector();
	Vector DR_CR_SALE_RATE = new Vector();
	Vector DR_CR_GROSS_AMT_INR = new Vector();
	Vector DR_CR_NET_AMT_INR = new Vector();
	Vector DR_CR_CRITERIA = new Vector();
	
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
	
	public void fetchINV_Details()throws SQLException,IOException
	{
		String inv_no="";
		
		try
		{
			/*String query="select hlpl_inv_seq_no,con_type from dlng_dr_cr_dtl where customer_cd='"+customer_cd+"' and " +
					"INVOICE_DT >= TO_DATE('"+from_dt+"','DD/MM/YYYY') AND " +
					"INVOICE_DT <= TO_DATE('"+to_dt+"','DD/MM/YYYY') ";	
//			//System.out.println("---query--"+query);
			rset = stmt.executeQuery(query);				
			while(rset.next())
			{
				Dr_cr_hlpl_inv_no.add(rset.getString(1)==null?"":rset.getString(1)); 
				Dr_cr_cont_typ.add(rset.getString(2)==null?"":rset.getString(2)); 
			}*/
			//////System.out.println("Dr_cr_hlpl_inv_no--"+Dr_cr_hlpl_inv_no.size());
			/*
			 * for(int i=0;i<Dr_cr_hlpl_inv_no.size();i++) {
			 */
				/*queryString="SELECT A.SALE_PRICE,A.GROSS_AMT_INR,A.NET_AMT_INR,A.TOTAL_QTY,TO_CHAR(A.INVOICE_DT,'DD/MM/YYYY')," +
						"TO_CHAR(A.PERIOD_START_DT,'DD/MM/YYYY'),TO_CHAR(A.PERIOD_END_DT,'DD/MM/YYYY')," +
						"A.PLANT_SEQ_NO,A.HLPL_INV_SEQ_NO,A.CONTRACT_TYPE,A.FINANCIAL_YEAR,TO_CHAR(A.DUE_DT,'DD/MM/YYYY')," +
						"A.EXCHG_RATE_VALUE,A.SN_NO,A.SN_REV_NO,A.FGSA_NO,A.FGSA_REV_NO,A.GROSS_AMT_USD,A.TAX_STRUCT_CD ," +
						"A.MAPPING_ID ,A.TAX_AMT_INR,A.FLAG,b.remark,a.remark_1,a.remark_2,a.remark_3,a.contact_person_cd"
						+ ",a.new_inv_seq_no,GROSS_AMT_INR_NEW,ADVANCE_ADJ_AMT_NEW,ADVANCE_ADJ_GROSS_AMT_INR,"
						+ "ADV_ADJ_FLG,INV_AMT_INR,GROSS_AMT_USD_NEW,b.SEQ_NO as seq_no  "
						+ "FROM DLNG_INVOICE_MST A,DLNG_DR_CR_DTL b  Where a.CUSTOMER_CD='"+customer_cd+"'"
						+ " AND a.financial_year='"+dr_cr_year+"' and " +
						"A.INVOICE_DT >= TO_DATE('"+from_dt+"','DD/MM/YYYY') AND A.INVOICE_DT <= TO_DATE('"+to_dt+"','DD/MM/YYYY') " +
						"and a.hlpl_inv_seq_no=b.hlpl_inv_seq_no"
						+ " and b.hlpl_inv_seq_no = a.hlpl_inv_seq_no"
						+ " AND A.APPROVED_FLAG='Y'"
						+ " AND A.FLAG!='A' and A.contract_type=b.con_type and A.financial_year=b.financial_year"
//						+ " AND NEW_INV_SEQ_NO IS NULL"
						+ " ORDER BY A.CONTRACT_TYPE,HLPL_INV_SEQ_NO "; //RG20200403 For fetching invoice data */
			queryString="SELECT A.SALE_PRICE,A.GROSS_AMT_INR,A.NET_AMT_INR,A.TOTAL_QTY,TO_CHAR(A.INVOICE_DT,'DD/MM/YYYY'),"
					+ " TO_CHAR(A.PERIOD_START_DT,'DD/MM/YYYY'),TO_CHAR(A.PERIOD_END_DT,'DD/MM/YYYY'),A.PLANT_SEQ_NO,A.HLPL_INV_SEQ_NO,A.CONTRACT_TYPE,"
					+ " A.FINANCIAL_YEAR,TO_CHAR(A.DUE_DT,'DD/MM/YYYY'),A.EXCHG_RATE_VALUE,A.SN_NO,A.SN_REV_NO,A.FGSA_NO,A.FGSA_REV_NO,A.GROSS_AMT_USD,"
					+ " A.TAX_STRUCT_CD ,A.MAPPING_ID ,A.TAX_AMT_INR,A.FLAG,b.remark,a.remark_1,a.remark_2,a.remark_3,"
					+ " a.contact_person_cd,a.new_inv_seq_no,"
					+ " GROSS_AMT_INR_NEW,ADVANCE_ADJ_AMT_NEW,ADVANCE_ADJ_GROSS_AMT_INR,ADV_ADJ_FLG,INV_AMT_INR,"
					+ " GROSS_AMT_USD_NEW,b.SEQ_NO as seq_no,'' as dr_cr_tax_amt,'invoice' as inv_type,'' as ref_inv_no "
					+ " FROM DLNG_INVOICE_MST A,DLNG_DR_CR_DTL b "
					+ " Where a.CUSTOMER_CD='"+customer_cd+"' "
					+ " AND a.financial_year='"+dr_cr_year+"' "
					+ "	AND A.INVOICE_DT >= TO_DATE('"+from_dt+"','DD/MM/YYYY')"
					+ " AND A.INVOICE_DT <= TO_DATE('"+to_dt+"','DD/MM/YYYY')"
					+ " and a.hlpl_inv_seq_no=b.hlpl_inv_seq_no"
					+ " and b.hlpl_inv_seq_no = a.hlpl_inv_seq_no "
					+ " AND A.APPROVED_FLAG='Y' "
					+ " AND A.FLAG!='A' "
					+ " and A.contract_type=b.con_type"
					+ " and A.financial_year=b.financial_year "
					+ " and b.NEW_INV_SEQ_NO IS NULL "
					+ " union all"
					+ " SELECT (CASE WHEN nvl(A.DR_CR_SALE_RATE,0) != 0 THEN A.DR_CR_SALE_RATE ELSE nvl(SALE_PRICE,0) END) AS dr_cr_sale_rate,"
					+ " A.DR_CR_GROSS_AMT_INR,A.DR_CR_NET_AMT_INR,"
					+ " (CASE WHEN nvl(DR_CR_QTY,0) != 0 THEN DR_CR_QTY ELSE nvl(TOTAL_QTY,0) END) AS DR_CR_QTY,"
					+ " TO_CHAR(A.DR_CR_DT,'DD/MM/YYYY'),"
					+ " null as PERIOD_START_DT,null as PERIOD_END_DT,A.PLANT_SEQ_NO,A.DR_CR_NO as HLPL_INV_SEQ_NO,A.CONTRACT_TYPE,"
					+ " A.DR_CR_FIN_YEAR,TO_CHAR(A.DUE_DT,'DD/MM/YYYY'),"
					+ " (CASE WHEN nvl(A.DR_CR_EXG_RATE,0) != 0 THEN A.DR_CR_EXG_RATE ELSE nvl(EXCHG_RATE_VALUE,0) END) AS dr_cr_exg_rate,"
					+ " A.SN_NO,A.SN_REV_NO,A.FGSA_NO,A.FGSA_REV_NO,A.GROSS_AMT_USD,"
					+ " A.TAX_STRUCT_CD ,'' as MAPPING_ID ,0 as TAX_AMT_INR,A.FLAG,b.remark,'' as remark_1,'' as remark_2,'' as remark_3,"
					+ " 0 as contact_person_cd,A.DR_CR_DOC_NO as new_inv_seq_no,"
					+ " DR_CR_GROSS_AMT_INR as GROSS_AMT_INR_NEW,0 as ADVANCE_ADJ_AMT_NEW,0 as ADVANCE_ADJ_GROSS_AMT_INR,"
					+ " '' as ADV_ADJ_FLG,0 as INV_AMT_INR,0 as GROSS_AMT_USD_NEW,b.SEQ_NO as seq_no,TAX_REMARK as dr_cr_tax_amt,"
					+ " 'drcr' as inv_type,nvl(b.NEW_INV_SEQ_NO,'') as ref_inv_no"
					+ " FROM DLNG_DR_CR_NOTE A,DLNG_DR_CR_DTL b "
					+ " Where a.CUSTOMER_CD='"+customer_cd+"' "
					+ " AND a.DR_CR_FIN_YEAR='"+dr_cr_year+"'"
					+ " and A.DR_CR_DT >= TO_DATE('"+from_dt+"','DD/MM/YYYY')"
					+ " AND A.DR_CR_DT <= TO_DATE('"+to_dt+"','DD/MM/YYYY') "
					+ " and a.DR_CR_DOC_NO=b.DR_CR_DOC_NO "
					+ " AND A.APRV_BY > 0 and A.contract_type=b.con_type "
					+ " and A.financial_year=b.financial_year and b.NEW_INV_SEQ_NO IS NOT NULL";
					System.out.println("---query--"+queryString);
					rset = stmt.executeQuery(queryString);				
					while(rset.next())
					{
						String AdvAdjFlag = rset.getString(27)==null?"NA":rset.getString(27);
						boolean vol_dis = false;
						String map_id = rset.getString(10)+":"+rset.getString(11)+":"+rset.getString(9)+":%";
						String q_vol = "select nvl(amount,'') from fms7_inv_compo_dtl where price_cd='14' and "
								+ "inv_seq_no like '"+map_id+"' and (flag_temp not like 'T' or flag_temp is null) ";
						rset1 = stmt1.executeQuery(q_vol);
						if(rset1.next()) {
							String vol_adj_amt = rset1.getString(1)==null?"":rset1.getString(1);
							if(!vol_adj_amt.equals("")) {
								vol_dis = true;
							}
						}

						boolean prev_adj_flag = false;
						String adj_amt_prev = rset.getString(25)==null?"":rset.getString(25);
						if(!adj_amt_prev.equals("")) {
							prev_adj_flag = true;
						}
						
						boolean tax_adj_flag = false, adj_flag = false; String tax_adj_amt = "0";
						
						
						q_vol = "select amount from fms7_inv_compo_dtl where price_cd = '1' and "
								+ "inv_seq_no like '"+map_id+"' and (flag_temp not like 'T' or flag_temp is null) ";
						rset2 = stmt2.executeQuery(q_vol);
						if(rset2.next()) {
							tax_adj_amt = rset2.getString(1)==null?"0":rset2.getString(1);
							adj_flag = true;
							
							q_vol = "select count(price_cd) from fms7_inv_compo_dtl where price_cd in ('6','7','8','9','10','11','12','13') and "
									+ "inv_seq_no like '"+map_id+"' and (flag_temp not like 'T' or flag_temp is null) ";
							rset1 = stmt1.executeQuery(q_vol);
							if(rset1.next()) {
								int count_adj = rset1.getInt(1);
								if(count_adj>0) {
									tax_adj_flag = true;
								}
							}
						}
						
						String gross_amt_inr = rset.getString(2)==null?"0":rset.getString(2);
						if(prev_adj_flag) {
							if(vol_dis || adj_flag) 
								gross_amt_inr = rset.getString(2)==null?"0":rset.getString(2);
							else {
								gross_amt_inr = rset.getString(29)==null?"0":rset.getString(29);
								if(rset.getString(29)==null) {
									gross_amt_inr = rset.getString(2)==null?"0":rset.getString(2);
								}
							}
						}
						
						if(tax_adj_flag) {
							if(!gross_amt_inr.equals(""))
								gross_amt_inr = ""+(Double.parseDouble(gross_amt_inr)-Double.parseDouble(tax_adj_amt));
						} else {
							if(adj_flag && prev_adj_flag) {
								gross_amt_inr = rset.getString(2)==null?"0":rset.getString(2);
							} else if(adj_flag) {
								if(AdvAdjFlag.equals("AA")) {
									gross_amt_inr = rset.getString(31)==null?"0":rset.getString(31);
								} else if(AdvAdjFlag.equals("BA")) {
									gross_amt_inr = rset.getString(33)==null?"0":rset.getString(33);
									gross_amt_inr = ""+(Double.parseDouble(gross_amt_inr)-Double.parseDouble(tax_adj_amt));
								}
							}
						}
						gross_amt_inr = nf.format(Double.parseDouble(gross_amt_inr));
						
						String gross_amt_usd = rset.getString(18)==null?"0":rset.getString(18);
						if(prev_adj_flag || vol_dis) {
							gross_amt_usd = rset.getString(34)==null?"0":rset.getString(34);
							if(rset.getString(34)==null) {
								gross_amt_usd = rset.getString(18)==null?"0":rset.getString(18);
							}
						}
						
						SALE_PRICE_DR_CR.add(rset.getString(1)==null?"":nf2.format(Double.parseDouble(rset.getString(1))));	
						GROSS_AMT_INR_DR_CR.add(gross_amt_inr);
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
						GROSS_AMT_USD_DR_CR.add(gross_amt_usd);
						TAX_STRUCT_CD_DR_CR.add(rset.getString(19)==null?"":rset.getString(19));
						DR_CR_MAPPING_ID_DR_CR.add(rset.getString(20)==null?"":rset.getString(20));
						String inv_type = rset.getString("inv_type")==null?"":rset.getString("inv_type");
						if(inv_type.equalsIgnoreCase("drcr")) {
							TAX_AMT_INR_DR_CR.add(rset.getString("dr_cr_tax_amt")==null?"":rset.getString("dr_cr_tax_amt"));
						}else {
							TAX_AMT_INR_DR_CR.add(rset.getString(21)==null?"":rset.getString(21));
						}
						FLAG_DR_CR.add(rset.getString(22)==null?"":rset.getString(22));	
						REMARK_DR_CR.add(rset.getString(23)==null?"":rset.getString(23));
						REMARK_1_DR_CR.add(rset.getString(24)==null?"":rset.getString(24));
						REMARK_2_DR_CR.add(rset.getString(25)==null?"":rset.getString(25));
						REMARK_3_DR_CR.add(rset.getString(26)==null?"":rset.getString(26));
						Contact_person_cd_DR_CR.add(rset.getString(27)==null?"":rset.getString(27));
						
						if(rset.getString(9)!=null && !(rset.getString(9).trim().equals("")) )
						{}	
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
						
						DR_CR_NEW_INV_SEQ_NO.add(rset.getString(28)==null?inv_no:rset.getString(28));
						DR_CR_SEQ_NO.add(rset.getString("seq_no"));
						DR_CR_REF_INV_NO.add(rset.getString("ref_inv_no")==null?"":rset.getString("ref_inv_no"));
//						String dr_cr_seq_no = rset.getString(29)==null?inv_no:rset.getString(29);
						String calc_base = "";String rate = "";
//						System.out.println("rset.getString(10)***"+rset.getString(23));
						if(rset.getString(10).equalsIgnoreCase("V")) { // for Service Invoice
							
							String servDtlSql = "select CALC_BASE,nvl(RATE,0) from DLNG_SERVICE_INVOICE_ATTACH where "
									+ " SERVICE_INVOICE_NO = '"+rset.getString(28)+"' ";
//							System.out.println("servDtlSql***"+servDtlSql);
							rset1 = stmt1.executeQuery(servDtlSql);
							if(rset1.next()) {
								calc_base = rset1.getString(1)==null?"":rset1.getString(1);
								rate = rset1.getString(2)==null?"":rset1.getString(2);
							}
						}
						CALC_BASE_SERVICE.add(calc_base);
						RATE_SERVICE.add(rate);
					}
//			}
			
			String query2="select pan_no, gst_tin_no, cst_tin_no from fms7_customer_mst where customer_cd='"+customer_cd+"'";
			rset = stmt.executeQuery(query2);	
			if(rset.next())
			{
				pan_no=rset.getString(1)==null?"-":rset.getString(1);
				gst_tin_no=rset.getString(2);
				cst_tin_no=rset.getString(3);
			}
			for(int i=0;i<FINANCIAL_YEAR_DR_CR.size();i++){
				
				queryString1="select flag,dr_cr_doc_no from dlng_dr_cr_note where "
						+ " CUSTOMER_CD='"+customer_cd+"' and HLPL_INV_SEQ_NO='"+HLPL_INV_SEQ_NO_DR_CR.elementAt(i)+"' "
						+ " and contract_type='"+CONTRACT_TYPE_DR_CR.elementAt(i)+"' "
						+ " and FINANCIAL_YEAR='"+FINANCIAL_YEAR_DR_CR.elementAt(i)+"' and seq_no = '"+DR_CR_SEQ_NO.elementAt(i)+"' ";
				System.out.println("queryString1***"+queryString1);
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
					queryString4 = "SELECT plant_addr,plant_city,plant_pin " +
								  "FROM FMS7_CUSTOMER_PLANT_DTL A " +
								  "WHERE A.customer_cd='"+customer_cd+"' AND A.seq_no='"+new_plant_no+"' AND " +
								  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
								  "WHERE A.customer_cd=B.customer_cd AND B.seq_no='"+new_plant_no+"' AND " +
								  "B.eff_dt<=TO_DATE('"+PERIOD_START_DT_DR_CR.elementAt(i)+"','DD/MM/YYYY'))";
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
	public void setCallFlag(String callFlag) {
		this.callFlag = callFlag;
	}


	public Vector getVInv_FY() {
		return VInv_FY;
	}


	public Vector getVInv_Dt_FY() {
		return VInv_Dt_FY;
	}


	public String getFy() {
		return fy;
	}


	public void setFy(String fy) {
		this.fy = fy;
	}
	public void setFrom_dt(String from_dt) {
		this.from_dt = from_dt;
	}

	public void setTo_dt(String to_dt) {
		this.to_dt = to_dt;
	}
	public void setCriteria(String criteria) {
		this.criteria = criteria;
	}
	public void setCr_dr(String cr_dr) {
		this.cr_dr = cr_dr;
	}
	public void setCustomer_cd(String customer_cd) {
		this.customer_cd = customer_cd;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	public void setCr_dr_yr(String cr_dr_yr) {
		this.cr_dr_yr = cr_dr_yr;
	}

	public Vector getCUST_CD() {
		return CUST_CD;
	}

	public Vector getCUST_NM() {
		return CUST_NM;
	}

	public void setCUST_CD(Vector cUST_CD) {
		CUST_CD = cUST_CD;
	}

	public void setCUST_NM(Vector cUST_NM) {
		CUST_NM = cUST_NM;
	}

	public Vector getNET_AMT_INR() {
		return NET_AMT_INR;
	}

	public Vector getTOTAL_QTY() {
		return TOTAL_QTY;
	}

	public Vector getINVOICE_DT() {
		return INVOICE_DT;
	}

	public Vector getPERIOD_START_DT() {
		return PERIOD_START_DT;
	}

	public Vector getPERIOD_END_DT() {
		return PERIOD_END_DT;
	}

	public Vector getNEW_INV_SEQ_NO() {
		return NEW_INV_SEQ_NO;
	}

	public Vector getSALE_PRICE() {
		return SALE_PRICE;
	}

	public Vector getGROSS_AMT_INR() {
		return GROSS_AMT_INR;
	}

	public Vector getFLAG() {
		return FLAG;
	}

	public Vector getDR_CR_DTL_REM() {
		return DR_CR_DTL_REM;
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
	public Vector getGROSS_AMT_USD() {
		return GROSS_AMT_USD;
	}
	public Vector getTAX_STRUCT_CD() {
		return TAX_STRUCT_CD;
	}

	public Vector getDR_CR_MAPPING_ID() {
		return DR_CR_MAPPING_ID;
	}
	public Vector getDR_CR_FIN_YEAR() {
		return DR_CR_FIN_YEAR;
	}
	public Vector getTAX_AMT_INR() {return TAX_AMT_INR;}
	public Vector getDR_CR_NO() {
		return DR_CR_NO;
	}

	public String getDr_cr_year() {
		return dr_cr_year;
	}

	public void setDr_cr_year(String dr_cr_year) {
		this.dr_cr_year = dr_cr_year;
	}

	public Vector getDr_cr_hlpl_inv_no() {
		return Dr_cr_hlpl_inv_no;
	}
	public void setFY(String FY) {this.fy = FY;}

	public Vector getSALE_PRICE_DR_CR() {
		return SALE_PRICE_DR_CR;
	}

	public Vector getGROSS_AMT_INR_DR_CR() {
		return GROSS_AMT_INR_DR_CR;
	}

	public Vector getNET_AMT_INR_DR_CR() {
		return NET_AMT_INR_DR_CR;
	}

	public Vector getTOTAL_QTY_DR_CR() {
		return TOTAL_QTY_DR_CR;
	}

	public Vector getINVOICE_DT_DR_CR() {
		return INVOICE_DT_DR_CR;
	}

	public Vector getPERIOD_START_DT_DR_CR() {
		return PERIOD_START_DT_DR_CR;
	}

	public Vector getPERIOD_END_DT_DR_CR() {
		return PERIOD_END_DT_DR_CR;
	}

	public Vector getPLANT_SEQ_NO_DR_CR() {
		return PLANT_SEQ_NO_DR_CR;
	}

	public Vector getHLPL_INV_SEQ_NO_DR_CR() {
		return HLPL_INV_SEQ_NO_DR_CR;
	}

	public Vector getFINANCIAL_YEAR_DR_CR() {
		return FINANCIAL_YEAR_DR_CR;
	}

	public Vector getINVOICE_NO_DR_CR() {
		return INVOICE_NO_DR_CR;
	}

	public Vector getDUE_DT_DR_CR() {
		return DUE_DT_DR_CR;
	}

	public Vector getEXCHG_RATE_VALUE_DR_CR() {
		return EXCHG_RATE_VALUE_DR_CR;
	}

	public Vector getSN_NO_DR_CR() {
		return SN_NO_DR_CR;
	}

	public Vector getSN_REV_NO_DR_CR() {
		return SN_REV_NO_DR_CR;
	}

	public Vector getFGSA_REV_NO_DR_CR() {
		return FGSA_REV_NO_DR_CR;
	}

	public Vector getGROSS_AMT_USD_DR_CR() {
		return GROSS_AMT_USD_DR_CR;
	}

	public Vector getTAX_STRUCT_CD_DR_CR() {
		return TAX_STRUCT_CD_DR_CR;
	}

	public Vector getDR_CR_MAPPING_ID_DR_CR() {
		return DR_CR_MAPPING_ID_DR_CR;
	}

	public Vector getTAX_AMT_INR_DR_CR() {
		return TAX_AMT_INR_DR_CR;
	}

	public Vector getFLAG_DR_CR() {
		return FLAG_DR_CR;
	}

	public Vector getREMARK_DR_CR() {
		return REMARK_DR_CR;
	}

	public Vector getREMARK_1_DR_CR() {
		return REMARK_1_DR_CR;
	}

	public Vector getREMARK_2_DR_CR() {
		return REMARK_2_DR_CR;
	}

	public Vector getREMARK_3_DR_CR() {
		return REMARK_3_DR_CR;
	}

	public Vector getFlag_dr_cr() {
		return flag_dr_cr;
	}

	public void setSALE_PRICE_DR_CR(Vector sALE_PRICE_DR_CR) {
		SALE_PRICE_DR_CR = sALE_PRICE_DR_CR;
	}

	public void setGROSS_AMT_INR_DR_CR(Vector gROSS_AMT_INR_DR_CR) {
		GROSS_AMT_INR_DR_CR = gROSS_AMT_INR_DR_CR;
	}

	public void setNET_AMT_INR_DR_CR(Vector nET_AMT_INR_DR_CR) {
		NET_AMT_INR_DR_CR = nET_AMT_INR_DR_CR;
	}

	public void setTOTAL_QTY_DR_CR(Vector tOTAL_QTY_DR_CR) {
		TOTAL_QTY_DR_CR = tOTAL_QTY_DR_CR;
	}

	public void setINVOICE_DT_DR_CR(Vector iNVOICE_DT_DR_CR) {
		INVOICE_DT_DR_CR = iNVOICE_DT_DR_CR;
	}

	public void setPERIOD_START_DT_DR_CR(Vector pERIOD_START_DT_DR_CR) {
		PERIOD_START_DT_DR_CR = pERIOD_START_DT_DR_CR;
	}

	public void setPERIOD_END_DT_DR_CR(Vector pERIOD_END_DT_DR_CR) {
		PERIOD_END_DT_DR_CR = pERIOD_END_DT_DR_CR;
	}

	public void setPLANT_SEQ_NO_DR_CR(Vector pLANT_SEQ_NO_DR_CR) {
		PLANT_SEQ_NO_DR_CR = pLANT_SEQ_NO_DR_CR;
	}

	public void setHLPL_INV_SEQ_NO_DR_CR(Vector hLPL_INV_SEQ_NO_DR_CR) {
		HLPL_INV_SEQ_NO_DR_CR = hLPL_INV_SEQ_NO_DR_CR;
	}

	public void setFINANCIAL_YEAR_DR_CR(Vector fINANCIAL_YEAR_DR_CR) {
		FINANCIAL_YEAR_DR_CR = fINANCIAL_YEAR_DR_CR;
	}

	public void setINVOICE_NO_DR_CR(Vector iNVOICE_NO_DR_CR) {
		INVOICE_NO_DR_CR = iNVOICE_NO_DR_CR;
	}

	public void setDUE_DT_DR_CR(Vector dUE_DT_DR_CR) {
		DUE_DT_DR_CR = dUE_DT_DR_CR;
	}

	public void setEXCHG_RATE_VALUE_DR_CR(Vector eXCHG_RATE_VALUE_DR_CR) {
		EXCHG_RATE_VALUE_DR_CR = eXCHG_RATE_VALUE_DR_CR;
	}

	public void setSN_NO_DR_CR(Vector sN_NO_DR_CR) {
		SN_NO_DR_CR = sN_NO_DR_CR;
	}

	public void setSN_REV_NO_DR_CR(Vector sN_REV_NO_DR_CR) {
		SN_REV_NO_DR_CR = sN_REV_NO_DR_CR;
	}

	public void setFGSA_REV_NO_DR_CR(Vector fGSA_REV_NO_DR_CR) {
		FGSA_REV_NO_DR_CR = fGSA_REV_NO_DR_CR;
	}

	public void setGROSS_AMT_USD_DR_CR(Vector gROSS_AMT_USD_DR_CR) {
		GROSS_AMT_USD_DR_CR = gROSS_AMT_USD_DR_CR;
	}

	public void setTAX_STRUCT_CD_DR_CR(Vector tAX_STRUCT_CD_DR_CR) {
		TAX_STRUCT_CD_DR_CR = tAX_STRUCT_CD_DR_CR;
	}

	public void setDR_CR_MAPPING_ID_DR_CR(Vector dR_CR_MAPPING_ID_DR_CR) {
		DR_CR_MAPPING_ID_DR_CR = dR_CR_MAPPING_ID_DR_CR;
	}

	public void setTAX_AMT_INR_DR_CR(Vector tAX_AMT_INR_DR_CR) {
		TAX_AMT_INR_DR_CR = tAX_AMT_INR_DR_CR;
	}

	public void setFLAG_DR_CR(Vector fLAG_DR_CR) {
		FLAG_DR_CR = fLAG_DR_CR;
	}

	public void setREMARK_DR_CR(Vector rEMARK_DR_CR) {
		REMARK_DR_CR = rEMARK_DR_CR;
	}

	public void setREMARK_1_DR_CR(Vector rEMARK_1_DR_CR) {
		REMARK_1_DR_CR = rEMARK_1_DR_CR;
	}

	public void setREMARK_2_DR_CR(Vector rEMARK_2_DR_CR) {
		REMARK_2_DR_CR = rEMARK_2_DR_CR;
	}

	public void setREMARK_3_DR_CR(Vector rEMARK_3_DR_CR) {
		REMARK_3_DR_CR = rEMARK_3_DR_CR;
	}

	public void setFlag_dr_cr(Vector flag_dr_cr) {
		this.flag_dr_cr = flag_dr_cr;
	}

	public Vector getFGSA_NO_DR_CR() {
		return FGSA_NO_DR_CR;
	}

	public void setFGSA_NO_DR_CR(Vector fGSA_NO_DR_CR) {
		FGSA_NO_DR_CR = fGSA_NO_DR_CR;
	}

	public String getPan_no() {
		return pan_no;
	}

	public String getGst_tin_no() {
		return gst_tin_no;
	}

	public String getCst_tin_no() {
		return cst_tin_no;
	}

	public void setPan_no(String pan_no) {
		this.pan_no = pan_no;
	}

	public void setGst_tin_no(String gst_tin_no) {
		this.gst_tin_no = gst_tin_no;
	}

	public void setCst_tin_no(String cst_tin_no) {
		this.cst_tin_no = cst_tin_no;
	}

	public Vector getContact_person_cd_DR_CR() {
		return Contact_person_cd_DR_CR;
	}

	public Vector getContact_Person_Name_And_Designation1() {
		return contact_Person_Name_And_Designation1;
	}

	public Vector getContact_addr_flag1() {
		return contact_addr_flag1;
	}

	public Vector getContact_Customer_Person_Address1() {
		return contact_Customer_Person_Address1;
	}

	public Vector getContact_Customer_Person_City1() {
		return contact_Customer_Person_City1;
	}

	public Vector getContact_Customer_Person_Pin1() {
		return contact_Customer_Person_Pin1;
	}

	public void setContact_person_cd_DR_CR(Vector contact_person_cd_DR_CR) {
		Contact_person_cd_DR_CR = contact_person_cd_DR_CR;
	}

	public void setContact_addr_flag1(Vector contact_addr_flag1) {
		this.contact_addr_flag1 = contact_addr_flag1;
	}

	public void setContact_Customer_Person_Address1(Vector contact_Customer_Person_Address1) {
		this.contact_Customer_Person_Address1 = contact_Customer_Person_Address1;
	}

	public void setContact_Customer_Person_City1(Vector contact_Customer_Person_City1) {
		this.contact_Customer_Person_City1 = contact_Customer_Person_City1;
	}

	public void setContact_Customer_Person_Pin1(Vector contact_Customer_Person_Pin1) {
		this.contact_Customer_Person_Pin1 = contact_Customer_Person_Pin1;
	}

	public Vector getDr_cr_no2() {
		return dr_cr_no2;
	}

	public Vector getDR_CR_NEW_INV_SEQ_NO() {
		return DR_CR_NEW_INV_SEQ_NO;
	}

	public Vector getCONTRACT_TYPE_DR_CR() {
		return CONTRACT_TYPE_DR_CR;
	}
	public void setEnd_dt(String end_dt) {
		this.end_dt = end_dt;
	}

	public void setSt_dt(String st_dt) {
		this.st_dt = st_dt;
	}
	public void setPlant_no(String plant_no) {
		this.plant_no = plant_no;
	}
	public void setFin_year(String fin_year) {
		this.fin_year = fin_year;
	}
	public void setHlplInvoiceNo(String hlplInvoiceNo) {this.hlplInvoiceNo = hlplInvoiceNo;}
	public void setFgsa_no(String fgsa_no) {
		this.fgsa_no = fgsa_no;
	}
	public void setSn_no(String sn_no) {
		this.sn_no = sn_no;
	}
	public void setContract_type(String contract_type) {
		this.contract_type = contract_type;
	}
	public void setYear(String year) {this.year = year;}
	public void setDr_cr_fin_year(String dr_cr_fin_year) {
		this.dr_cr_fin_year = dr_cr_fin_year;
	}
	public void setTax_struc_cd(String tax_struc_cd) {
		this.tax_struc_cd = tax_struc_cd;
	}
	public void setCr_dr1(String cr_dr1) {
		this.cr_dr1 = cr_dr1;
	}
	public void setDrcrgrossamt(String drcrgrossamt) {
		this.drcrgrossamt = drcrgrossamt;
	}
	public void setInv_dt(String inv_dt) {
		this.inv_dt = inv_dt;
	}
	public void setView_flag(String view_flag) {
		this.view_flag = view_flag;
	}
	public String getDr_cr_doc_no() {
		return dr_cr_doc_no;
	}
	public Vector getInvoice_period_date1() {
		return Invoice_period_date1;
	}
	public String getDr_cr_net_amt() {
		return dr_cr_net_amt;
	}
	public String getDr_cr_gross_amt() {
		return dr_cr_gross_amt;
	}
	public String getDr_cr_dt() {
		return dr_cr_dt;
	}
	public String getDr_cr_exg_rt() {
		return dr_cr_exg_rt;
	}
	public String getRemark() {
		return remark;
	}
	public String getDr_cr_sales_rate() {
		return dr_cr_sales_rate;
	}
	public boolean isDate_flag() {
		return date_flag;
	}
	public String getSale_rate() {
		return sale_rate;
	}
	public String getTot_qty() {
		return tot_qty;
	}
	public String getNet_amt_inr() {
		return net_amt_inr;
	}
	public String getTax_amt_inr() {
		return tax_amt_inr;
	}
	public String getExch_rate() {
		return exch_rate;
	}
	public String getGross_amt_inr() {
		return gross_amt_inr;
	}
	public String getInvoice_dt() {
		return invoice_dt;
	}
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	public String getBill_dt() {
		return bill_dt;
	}
	public void setBill_dt(String bill_dt) {
		this.bill_dt = bill_dt;
	}
	public double getTcs_limit_amt() {
		return tcs_limit_amt;
	}
	public String getTcs_app_flag() {
		return tcs_app_flag;
	}
	public String getTcs_amt() {
		return tcs_amt;
	}
	public String getTcs_nm() {
		return tcs_nm;
	}
	public String getTcs_sht_nm() {
		return tcs_sht_nm;
	}
	public Vector getTcs_invdt() {
		return tcs_invdt;
	}
	public Vector getTcs_net_amt() {
		return tcs_net_amt;
	}
	public Vector getTcs_fgsano() {
		return tcs_fgsano;
	}
	public Vector getTcs_fgsarevno() {
		return tcs_fgsarevno;
	}
	public Vector getTcs_snno() {
		return tcs_snno;
	}
	public Vector getTcs_snrevno() {
		return tcs_snrevno;
	}
	public void setTcs_limit_amt(double tcs_limit_amt) {
		this.tcs_limit_amt = tcs_limit_amt;
	}
	public void setTcs_app_flag(String tcs_app_flag) {
		this.tcs_app_flag = tcs_app_flag;
	}
	public void setTcs_amt(String tcs_amt) {
		this.tcs_amt = tcs_amt;
	}
	public void setTcs_nm(String tcs_nm) {
		this.tcs_nm = tcs_nm;
	}
	public void setTcs_sht_nm(String tcs_sht_nm) {
		this.tcs_sht_nm = tcs_sht_nm;
	}
	public void setTcs_invdt(Vector tcs_invdt) {
		this.tcs_invdt = tcs_invdt;
	}
	public void setTcs_net_amt(Vector tcs_net_amt) {
		this.tcs_net_amt = tcs_net_amt;
	}
	public void setTcs_fgsano(Vector tcs_fgsano) {
		this.tcs_fgsano = tcs_fgsano;
	}
	public void setTcs_fgsarevno(Vector tcs_fgsarevno) {
		this.tcs_fgsarevno = tcs_fgsarevno;
	}
	public void setTcs_snno(Vector tcs_snno) {
		this.tcs_snno = tcs_snno;
	}
	public void setTcs_snrevno(Vector tcs_snrevno) {
		this.tcs_snrevno = tcs_snrevno;
	}
	public String getFact() {
		return fact;
	}
	public void setFact(String fact) {
		this.fact = fact;
	}
	public String getMdr_cr_dt() {
		return Mdr_cr_dt;
	}

	public void setMdr_cr_dt(String mdr_cr_dt) {
		Mdr_cr_dt = mdr_cr_dt;
	}
	public String getDiff_exg() {
		return diff_exg;
	}
	public void setDiff_exg(String diff_exg) {
		this.diff_exg = diff_exg;
	}
	public String getExg_rt() {
		return exg_rt;
	}
	public void setExg_rt(String exg_rt) {
		this.exg_rt = exg_rt;
	}
	public String getDiff_rate() {
		return diff_rate;
	}
	public void setDiff_rate(String diff_rate) {
		this.diff_rate = diff_rate;
	}
	public String getDr_cr_DUEDT() {
		return dr_cr_DUEDT;
	}
	public void setDr_cr_DUEDT(String dr_cr_DUEDT) {
		this.dr_cr_DUEDT = dr_cr_DUEDT;
	}
	public void setSale_rate(String sale_rate) {
		this.sale_rate = sale_rate;
	}
	public Vector getCustomer_Invoice_Tax_Rate() {
		return customer_Invoice_Tax_Rate;
	}
	public void setCustomer_Invoice_Tax_Rate(Vector customer_Invoice_Tax_Rate) {
		this.customer_Invoice_Tax_Rate = customer_Invoice_Tax_Rate;
	}
	public String getTax_fact() {
		return tax_fact;
	}
	public void setTax_fact(String tax_fact) {
		this.tax_fact = tax_fact;
	}
	public String getTax_str_nm() {
		return tax_str_nm;
	}
	public void setTax_str_nm(String tax_str_nm) {
		this.tax_str_nm = tax_str_nm;
	}
	public String getDr_cr_qty() {
		return dr_cr_qty;
	}

	public void setDr_cr_qty(String dr_cr_qty) {
		this.dr_cr_qty = dr_cr_qty;
	}

	public String getDiff_qty() {
		return diff_qty;
	}

	public void setDiff_qty(String diff_qty) {
		this.diff_qty = diff_qty;
	}

	public Vector getCustomer_Invoice_Tax_Code() {
		return customer_Invoice_Tax_Code;
	}

	public Vector getCustomer_Invoice_Tax_Name() {
		return customer_Invoice_Tax_Name;
	}

	public Vector getCustomer_Invoice_Tax_Amt() {
		return customer_Invoice_Tax_Amt;
	}

	public void setCustomer_Invoice_Tax_Code(Vector customer_Invoice_Tax_Code) {
		this.customer_Invoice_Tax_Code = customer_Invoice_Tax_Code;
	}

	public void setCustomer_Invoice_Tax_Name(Vector customer_Invoice_Tax_Name) {
		this.customer_Invoice_Tax_Name = customer_Invoice_Tax_Name;
	}

	public void setCustomer_Invoice_Tax_Amt(Vector customer_Invoice_Tax_Amt) {
		this.customer_Invoice_Tax_Amt = customer_Invoice_Tax_Amt;
	}

	public Vector getMtaxrt() {
		return Mtaxrt;
	}

	public void setMtaxrt(Vector mtaxrt) {
		Mtaxrt = mtaxrt;
	}

	public String getTax_str() {
		return tax_str;
	}

	public void setTax_str(String tax_str) {
		this.tax_str = tax_str;
	}

	public String getDr_cr_tax_rt() {
		return dr_cr_tax_rt;
	}

	public void setDr_cr_tax_rt(String dr_cr_tax_rt) {
		this.dr_cr_tax_rt = dr_cr_tax_rt;
	}

	public String getInv_no() {
		return inv_no;
	}

	public void setInv_no(String inv_no) {
		this.inv_no = inv_no;
	}

	public String getTds_app_flag() {
		return tds_app_flag;
	}

	public String getTds_amt() {
		return tds_amt;
	}

	public String getFgsa_rev_no() {
		return fgsa_rev_no;
	}

	public void setFgsa_rev_no(String fgsa_rev_no) {
		this.fgsa_rev_no = fgsa_rev_no;
	}

	public String getSn_rev_no() {
		return sn_rev_no;
	}

	public void setSn_rev_no(String sn_rev_no) {
		this.sn_rev_no = sn_rev_no;
	}

	public int getTdsEntryCnt() {
		return tdsEntryCnt;
	}

	public Vector getCALC_BASE_SERVICE() {
		return CALC_BASE_SERVICE;
	}

	public Vector getRATE_SERVICE() {
		return RATE_SERVICE;
	}

	public String getCalc_base() {
		return calc_base;
	}

	public void setCalc_base(String calc_base) {
		this.calc_base = calc_base;
	}

	public String getRate_service() {
		return rate_service;
	}

	public void setRate_service(String rate_service) {
		this.rate_service = rate_service;
	}

	public double getServ_total_tax_perc() {
		return serv_total_tax_perc;
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

	public String getInv_typ() {
		return inv_typ;
	}

	public void setInv_typ(String inv_typ) {
		this.inv_typ = inv_typ;
	}

	public String getDiff_inr_km() {
		return diff_inr_km;
	}

	public String getDiff_inr_mmbtu() {
		return diff_inr_mmbtu;
	}

	public String getDr_cr_inr_km() {
		return dr_cr_inr_km;
	}

	public String getDr_cr_inr_mmbtu() {
		return dr_cr_inr_mmbtu;
	}

	public Vector getServ_dr_cr_qty() {
		return serv_dr_cr_qty;
	}

	public Vector getServ_diff_qty() {
		return serv_diff_qty;
	}

	public Vector getServ_tax_desc() {
		return serv_tax_desc;
	}

	public Vector getServ_tax_perc() {
		return serv_tax_perc;
	}

	public String getTax_td() {
		return tax_td;
	}

	public String getDr_cr_tax_rate() {
		return dr_cr_tax_rate;
	}

	public String getDiff_tax_str() {
		return diff_tax_str;
	}

	public Vector getView_truck_inv_cnt() {
		return view_truck_inv_cnt;
	}

	public Vector getDR_CR_SEQ_NO() {
		return DR_CR_SEQ_NO;
	}

	public String getDr_cr_seq_no() {
		return dr_cr_seq_no;
	}

	public void setDr_cr_seq_no(String dr_cr_seq_no) {
		this.dr_cr_seq_no = dr_cr_seq_no;
	}

	public Vector getVdr_cr_no() {
		return Vdr_cr_no;
	}

	public Vector getVdr_cr_cnt() {
		return Vdr_cr_cnt;
	}

	public Vector getDR_CR_DOC_NO() {
		return DR_CR_DOC_NO;
	}

	public Vector getDR_CR_APRV_BY() {
		return DR_CR_APRV_BY;
	}

	public Vector getDR_CR_APRV_DT() {
		return DR_CR_APRV_DT;
	}

	public Vector getDR_CR_DT() {
		return DR_CR_DT;
	}

	public Vector getDR_CR_QTY() {
		return DR_CR_QTY;
	}

	public Vector getDR_CR_SALE_RATE() {
		return DR_CR_SALE_RATE;
	}

	public Vector getDR_CR_GROSS_AMT_INR() {
		return DR_CR_GROSS_AMT_INR;
	}

	public Vector getDR_CR_NET_AMT_INR() {
		return DR_CR_NET_AMT_INR;
	}

	public Vector getDR_CR_CRITERIA() {
		return DR_CR_CRITERIA;
	}

	public Vector getDR_CR_REF_INV_NO() {
		return DR_CR_REF_INV_NO;
	}

	public String getSDr_cr_ref_inv_no() {
		return SDr_cr_ref_inv_no;
	}

	public void setSDr_cr_ref_inv_no(String sDr_cr_ref_inv_no) {
		SDr_cr_ref_inv_no = sDr_cr_ref_inv_no;
	}

	public Vector getView_truck_inv_dr_cr_qty() {
		return view_truck_inv_dr_cr_qty;
	}

	public Vector getView_truck_inv_dr_cr_diff_qty() {
		return view_truck_inv_dr_cr_diff_qty;
	}

	public double getTotal_diff_qty() {
		return total_diff_qty;
	}

}
