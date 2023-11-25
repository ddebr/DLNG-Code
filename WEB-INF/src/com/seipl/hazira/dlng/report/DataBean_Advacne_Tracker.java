package com.seipl.hazira.dlng.report;

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

import com.seipl.hazira.dlng.util.RuntimeConf;
import com.seipl.hazira.dlng.util.UtilBean;

public class DataBean_Advacne_Tracker {

	
	Connection conn; 
	Statement stmt;
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
	String callFlag = "";
	String gas_date = "";
	String gen_date = ""; 
	String from_dt = "";
	String to_dt   ="";
	String week = "";
	String customer_cd = "0";
	String customer_contact_cd = "0";
	String methodName = "";
	String databeanName = "DataBean_Contract_Mgmt";
	String cust_id="";
	
	NumberFormat nf = new DecimalFormat("###########0.00");
	NumberFormat nf2 = new DecimalFormat("###########0.0000");
	NumberFormat nf3 = new DecimalFormat("###########0.000000000000");
	NumberFormat nf4 = new DecimalFormat("############.##");
	NumberFormat nf5 = new DecimalFormat("############");
	NumberFormat nf6 = new DecimalFormat("###,###,###,##0.00");
	UtilBean util = new UtilBean();
	
	
	
	@SuppressWarnings("unused")
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
	    			
	    			if(callFlag.equalsIgnoreCase("fetchAdvanceTrackerDtl")) {
	    				
	    				fetchInvCustDtl();
	    			}
	    			
	    		}
	    	}
	    } catch(Exception e) {
	    	e.printStackTrace();
	    }finally {

	    	if(rset != null)
	    	{
				try
				{
					rset.close();
				}
				catch(SQLException e)
				{
					System.out.println("rset is not close "+e);
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
					System.out.println("rset1 is not close "+e);
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
					System.out.println("rset2 is not close "+e);
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
					System.out.println("rset3 is not close "+e);
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
					System.out.println("stmt is not close "+e);
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
					System.out.println("stmt1 is not close "+e);
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
					System.out.println("stmt2 is not close "+e);
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
					System.out.println("stmt3 is not close "+e);
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
					System.out.println("conn is not close "+e);
				}
			}
	    
		}
	}
	
	Vector Vcust_cd = new Vector();
	Vector Vcust_nm = new Vector();
	Vector mapping_id = new Vector();
	Vector Vcust_abbr = new Vector();
	Vector transDate = new Vector();
	Vector remark = new Vector();
	Vector Vcredit = new Vector();
	Vector Vdebit = new Vector();
	Vector Vclosing_bal = new Vector();
	Vector Vadvance_bal = new Vector();
	Vector Vpay_type = new Vector();
	Vector bgColor = new Vector();
	
	Vector VholdAmt = new Vector();
	Vector VholdRmk = new Vector();
	Vector VholdTransDate = new Vector();
	Vector VholdBgColor = new Vector();
	
	String selMapId = "";
	String sysdate = "";
	/*
	 * NOTE : fetchInvCustDtl() method also used from DataBean_Prepare_TLU_InvoiceV2.java
	 */
	public void fetchInvCustDtl()throws SQLException,IOException {
		try {
			String custCdSql="SELECT DISTINCT(CUSTOMER_CD) FROM DLNG_INVOICE_MST WHERE CONTRACT_TYPE in ('S','L')  AND TRUCK_ID IS NOT NULL ORDER BY CUSTOMER_CD";
//			System.out.println("custCdSql***"+custCdSql);
			rset = stmt.executeQuery(custCdSql);
			while (rset.next()) {
				
				String custAbrSql="SELECT CUSTOMER_ABBR FROM  FMS7_CUSTOMER_MST WHERE CUSTOMER_CD='"+rset.getString(1)+"'";
				rset1 = stmt1.executeQuery(custAbrSql);
				if(rset1.next()) {
				
					if(!from_dt.equals("") && !to_dt.equals("")) {
						
						/*queryString1 = "SELECT FLSA_NO,FLSA_REV_NO,SN_NO,SN_REV_NO,NVL(A.dcq,'0'),NVL(A.mdcq_percentage,'100'),NVL(A.tcq,'0'),NVL(A.variation_qty,'0'),NVL(A.DCQ_MT,'0') "
								+ " ,A.FCC_FLAG, SN_BASE,TO_CHAR(START_DT,'dd/mm/yyyy'),TO_CHAR(END_DT,'dd/mm/yyyy') "
								+ " FROM DLNG_SN_MST A WHERE "
								+ " CUSTOMER_CD = '"+rset.getString(1)+"'"
								+ " AND A.start_dt<=TO_DATE('"+to_dt+"','DD/MM/YYYY')"
								+ " AND A.end_dt>=TO_DATE('"+to_dt+"','DD/MM/YYYY') AND A.status='Y' "
								+ " AND A.sn_rev_no=(SELECT MAX(B.sn_rev_no) FROM DLNG_SN_MST B WHERE A.flsa_no=B.flsa_no AND A.sn_no=B.sn_no AND"
								+ " A.customer_cd=B.customer_cd AND  B.start_dt<=TO_DATE('"+to_dt+"','DD/MM/YYYY') AND B.end_dt>=TO_DATE('"+to_dt+"','DD/MM/YYYY') AND B.status='Y') ORDER BY CUSTOMER_CD";*/
						queryString1 = "SELECT FLSA_NO,FLSA_REV_NO,SN_NO,SN_REV_NO,NVL(A.dcq,'0'),NVL(A.mdcq_percentage,'100'),NVL(A.tcq,'0'),NVL(A.variation_qty,'0'),NVL(A.DCQ_MT,'0') "
								+ " ,A.FCC_FLAG, SN_BASE,TO_CHAR(START_DT,'dd/mm/yyyy'),TO_CHAR(END_DT,'dd/mm/yyyy') "
								+ " FROM DLNG_SN_MST A WHERE "
								+ " CUSTOMER_CD = '"+rset.getString(1)+"'"
								+ " AND A.start_dt<=TO_DATE('"+to_dt+"','DD/MM/YYYY')"
								+ " AND A.status='Y' "
								+ " AND A.sn_rev_no=(SELECT MAX(B.sn_rev_no) FROM DLNG_SN_MST B WHERE A.flsa_no=B.flsa_no AND A.sn_no=B.sn_no AND"
								+ " A.customer_cd=B.customer_cd AND  B.start_dt<=TO_DATE('"+to_dt+"','DD/MM/YYYY') "
								+ " AND B.status='Y') ORDER BY CUSTOMER_CD";
//						System.out.println("Fetching SN COntracts.."+queryString1);
						rset2 = stmt2.executeQuery(queryString1);
						while(rset2.next())
						{
							mapping_id.add("S-"+rset.getString(1)+"-"+rset2.getString(1)+"-"+rset2.getString(2)+"-"+rset2.getString(3)+"-"+rset2.getString(4)+"-"+rset2.getString(12)+"-"+rset2.getString(13));
							Vcust_abbr.add(rset1.getString(1)+" "+"SN-"+rset2.getString(3));
						}
						
						/*queryString2 = "SELECT TENDER_NO,LOA_NO,LOA_REV_NO,NVL(A.dcq,'0'),NVL(A.mdcq_percentage,'100'),NVL(A.tcq,'0'),NVL(A.variation_qty,'0'),NVL(A.DCQ_MT,'0') "
								+ " ,A.FCC_FLAG, LOA_BASE,TO_CHAR(START_DT,'dd/mm/yyyy'),TO_CHAR(END_DT,'dd/mm/yyyy') "
								+ " FROM DLNG_LOA_MST A WHERE"
								+ " CUSTOMER_CD = '"+rset.getString(1)+"'"
								+ " AND A.start_dt<=TO_DATE('"+to_dt+"','DD/MM/YYYY')"
								+ " AND A.end_dt>=TO_DATE('"+to_dt+"','DD/MM/YYYY') AND A.status='Y' "
								+ " AND A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM DLNG_LOA_MST B WHERE A.TENDER_NO=B.TENDER_NO AND A.LOA_NO=B.LOA_NO AND"
								+ " A.customer_cd=B.customer_cd AND  B.start_dt<=TO_DATE('"+to_dt+"','DD/MM/YYYY') AND B.end_dt>=TO_DATE('"+to_dt+"','DD/MM/YYYY') AND B.status='Y') ORDER BY CUSTOMER_CD";*/
						queryString2 = "SELECT TENDER_NO,LOA_NO,LOA_REV_NO,NVL(A.dcq,'0'),NVL(A.mdcq_percentage,'100'),NVL(A.tcq,'0'),NVL(A.variation_qty,'0'),NVL(A.DCQ_MT,'0') "
								+ " ,A.FCC_FLAG, LOA_BASE,TO_CHAR(START_DT,'dd/mm/yyyy'),TO_CHAR(END_DT,'dd/mm/yyyy') "
								+ " FROM DLNG_LOA_MST A WHERE"
								+ " CUSTOMER_CD = '"+rset.getString(1)+"'"
								+ " AND A.start_dt<=TO_DATE('"+to_dt+"','DD/MM/YYYY')"
								+ " AND A.status='Y' "
								+ " AND A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM DLNG_LOA_MST B WHERE A.TENDER_NO=B.TENDER_NO AND A.LOA_NO=B.LOA_NO AND"
								+ " A.customer_cd=B.customer_cd AND  B.start_dt<=TO_DATE('"+to_dt+"','DD/MM/YYYY') "
								+ " AND B.status='Y') ORDER BY CUSTOMER_CD";
						
//							System.out.println("Fetching LoA COntracts.."+queryString2);
						rset2 = stmt2.executeQuery(queryString2);
						while(rset2.next())
						{
							mapping_id.add("L-"+rset.getString(1)+"-"+rset2.getString(1)+"-0-"+rset2.getString(2)+"-"+rset2.getString(3)+"-"+rset2.getString(11)+"-"+rset2.getString(12));
							Vcust_abbr.add(rset1.getString(1)+" "+"LoA-"+rset2.getString(2));
						}
					}
				}
			}
//			System.out.println("selMapId----"+selMapId);
			if(!selMapId.equals("") && selMapId.contains("-")) {
				
				String temp_arr[] = selMapId.split("-");
				
				String contTyp = temp_arr[0];
				String custCd =  temp_arr[1];
				String flsaCd =  temp_arr[2];
				String snCd = temp_arr[4];
				String start_dt = temp_arr[6];
				String end_dt = temp_arr[7];
//				System.out.println("end_dt------"+end_dt);
				if(!end_dt.equals("") && !start_dt.equals("")) {
					
					String compEndDt = "select * from dual where TO_DATE('"+end_dt+"','DD/MM/YYYY') > sysdate ";
//					System.out.println("compEndDt----"+compEndDt);
					rset = stmt.executeQuery(compEndDt);
					if(rset.next()) {
						end_dt = sysdate ; 	
					}
					
					/*checking for Advance before SN Start Date*/
					String checkBefAdvSql = "select nvl(PAY_AMT,0),PAY_TYPE,to_char(PAY_DT,'DD/MM/YYYY'),nvl(DR_CR_FLAG,'') "
							+ " from DLNG_ADVC_PAY_MST"
							+ " where PAY_DT < to_date('"+start_dt+"','dd/mm/yyyy')"
							+ " AND CUSTOMER_CD = '"+custCd+"'"
							+ " AND FLSA_NO = '"+flsaCd+"'"
							+ " AND SN_NO = '"+snCd+"'"
							+ " AND CONTRACT_TYPE = '"+contTyp+"' "
							+ " AND APPROVED_FLAG = 'Y' ";
//					System.out.println("checkBefAdvSql*********"+checkBefAdvSql);
					rset1 = stmt1.executeQuery(checkBefAdvSql);
					while (rset1.next()) {
						
						String pay_type = "";
						if(rset1.getString(2).equalsIgnoreCase("AP")) {
							pay_type = "Advance";
						}else if(rset1.getString(2).equalsIgnoreCase("SP")) {
							pay_type = "Security Deposit";
						}else if(rset1.getString(2).equalsIgnoreCase("LP")) {
							pay_type = "Lump-Sum";
						}
						/*
						 * Vcredit.add(rset1.getString(1)==null?"0":rset1.getString(1));
						 * Vdebit.add("0.00");
						 */
						String drcr = rset1.getString(4)==null?"":rset1.getString(4);
						if(drcr.equalsIgnoreCase("D")) {
							Vdebit.add(rset1.getString(1)==null?"0":rset1.getString(1));
							Vcredit.add("0.00");
						}else {
							Vcredit.add(rset1.getString(1)==null?"0":rset1.getString(1));
							Vdebit.add("0.00");
						}
						remark.add(pay_type);
						transDate.add(rset1.getString(3)==null?"0":rset1.getString(3));
						bgColor.add("#a6e2eb");
						Vpay_type.add(rset1.getString(2));
					}
					
//					System.out.println("end_dt------"+end_dt);
					String dateList = "SELECT to_char(to_date('"+start_dt+"', 'dd/mm/yyyy') + LEVEL - 1,'dd/mm/yyyy')"
							+ " FROM DUAL"
							+ " CONNECT BY LEVEL <= to_date('"+end_dt+"', 'dd/mm/yyyy') + 1 - to_date('"+start_dt+"', 'dd/mm/yyyy')";
//					System.out.println("dateList******"+dateList);
					rset = stmt.executeQuery(dateList);
					while (rset.next()) {
						
						String selDate = rset.getString(1)==null?"":rset.getString(1);
						if(!selDate.equals("")) {
							
							/*checking for Advance*/
							String checkAdvSql = "select nvl(PAY_AMT,0),PAY_TYPE,DR_CR_FLAG from DLNG_ADVC_PAY_MST"
									+ " where PAY_DT = to_date('"+selDate+"','dd/mm/yyyy')"
									+ " AND CUSTOMER_CD = '"+custCd+"'"
									+ " AND FLSA_NO = '"+flsaCd+"'"
									+ " AND SN_NO = '"+snCd+"'"
									+ " AND CONTRACT_TYPE = '"+contTyp+"' "
									+ " AND APPROVED_FLAG = 'Y' ";
							
//							System.out.println("checkAdvSql*********"+checkAdvSql);
							rset1 = stmt1.executeQuery(checkAdvSql);
							while (rset1.next()) {
								
								String pay_type = "";
								if(rset1.getString(2).equalsIgnoreCase("AP")) {
									pay_type = "Advance";
								}else if(rset1.getString(2).equalsIgnoreCase("SP")) {
									pay_type = "Security Deposit";
								}else if(rset1.getString(2).equalsIgnoreCase("LP")) {
									pay_type = "Lump-Sum";
								}
								String drcr = rset1.getString(3)==null?"":rset1.getString(3);
								if(drcr.equalsIgnoreCase("D")) {
									Vdebit.add(rset1.getString(1)==null?"0":rset1.getString(1));
									Vcredit.add("0.00");
								}else {
									Vcredit.add(rset1.getString(1)==null?"0":rset1.getString(1));
									Vdebit.add("0.00");
								}
								
								remark.add(pay_type);
								transDate.add(selDate);
								bgColor.add("#a6e2eb");
								Vpay_type.add(rset1.getString(2));
							}
							
							/*checking for invoice*/
							String checkInvSql = "select NET_AMT_INR,NEW_INV_SEQ_NO,MAPPING_ID from DLNG_INVOICE_MST"
									+ " where  INVOICE_DT = to_date('"+selDate+"','dd/mm/yyyy')"
									+ " AND CUSTOMER_CD = '"+custCd+"'"
									+ " AND FGSA_NO = '"+flsaCd+"'"
									+ " AND SN_NO = '"+snCd+"'"
									+ " AND CONTRACT_TYPE = '"+contTyp+"' ";
//							System.out.println("checkInvSql-------"+checkInvSql);
							rset1 = stmt1.executeQuery(checkInvSql);
							while (rset1.next()) {
								
								Vcredit.add("0.00");
								Vdebit.add(rset1.getString(1)==null?"0.00":rset1.getString(1));
								remark.add("Invoice No. :"+rset1.getString(2));
								transDate.add(selDate);
								bgColor.add("#e5edde");
								Vpay_type.add("");
								
								/*checking for Invoice HOLD Amount*/
								String map_id = rset1.getString(3)==null?"":rset1.getString(3);
								String temp_arr1[];
								String temp_mapId="";
								if(map_id.contains("-")) {
									
									temp_arr1 = map_id.split("-");
									temp_mapId = temp_arr1[0]+"-"+temp_arr1[1]+"-"+temp_arr1[2]+"-"+temp_arr1[3]+"-"+temp_arr1[4]+"-"+contTyp;
								}else {
									temp_mapId = "%-%-%-%-%-"+contTyp;
								}
//								System.out.println("temp_mapId-------"+temp_mapId);
								String holdAmtSql = "select nvl(HOLD_AMOUNT,0),to_char(PAY_RECV_DT,'dd/mm/yyyy') from FMS8_PAY_RECV_DTL where NEW_INV_SEQ_NO = '"+rset1.getString(2)+"'"
										+ " and MAPPING_ID like '"+temp_mapId+"' "
										+ " and COMMODITY_TYPE='DLNG' "
										+ " and REV_NO = (select max(REV_NO) from FMS8_PAY_RECV_DTL where"
											+ " NEW_INV_SEQ_NO = '"+rset1.getString(2)+"' "
											+ " and MAPPING_ID like '"+temp_mapId+"'  and COMMODITY_TYPE='DLNG' )";
//								System.out.println("holdAmtSql---Invoice---"+holdAmtSql);
								rset2 = stmt2.executeQuery(holdAmtSql);
								if(rset2.next()) {
									if(Double.parseDouble(rset2.getString(1)+"") > 0) {
										
										Vcredit.add("0.00");
										Vdebit.add(rset2.getString(1)==null?"0.00":rset2.getString(1));
										remark.add("Invoice Hold Amount :"+rset1.getString(2));
										transDate.add(rset2.getString(2));
										bgColor.add("");
										Vpay_type.add("");
										
										/*checking for C-Form*/
										String CFormSql = "select A.CFORM_NO,to_char(B.CFORM_DT,'dd/mm/yyyy') "
												+ " from FMS7_CFORM_DTL A,FMS7_CFORM_MST B WHERE INVOICE_NO = '"+rset1.getString(2)+"' "
												+ " and A.CFORM_NO = B.CFORM_NO";
//										System.out.println("CFormSql----"+CFormSql);
										rset3 = stmt3.executeQuery(CFormSql);
										if(rset3.next()) {
											
											Vcredit.add(rset2.getString(1)==null?"0.00":rset2.getString(1));
											Vdebit.add("0.00");
											remark.add("Hold amount release againts C-Form No. :"+rset3.getString(1));
											transDate.add(rset3.getString(2)==null?"":rset3.getString(2));
											bgColor.add("#fee5e2");	
											Vpay_type.add("");
										}
									}
								}
							}
							/*checking for debit/credit note*/
							String checkDrCrSql = "select DR_CR_NET_AMT_INR,DR_CR_DOC_NO,DR_CR_FLAG from "
									+ " DLNG_DR_CR_NOTE where"
									+ " DR_CR_DT = to_date('"+selDate+"','dd/mm/yyyy')"
									+ " AND CUSTOMER_CD = '"+custCd+"'"
									+ " AND FGSA_NO = '"+flsaCd+"'"
									+ " AND SN_NO = '"+snCd+"'"
									+ " AND CONTRACT_TYPE = '"+contTyp+"'"
									+ " AND APRV_BY > 0  ";
//							System.out.println("checkDrCrSql----"+checkDrCrSql);
							rset1 = stmt1.executeQuery(checkDrCrSql);
							while (rset1.next()) {
								String drcr = "";
								if(rset1.getString(3).equalsIgnoreCase("dr")) {
									Vcredit.add("0.00");
									Vdebit.add(rset1.getString(1)==null?"0.00":rset1.getString(1));
									drcr = "Debit Note :";
								}else {
									Vdebit.add("0.00");
									Vcredit.add(rset1.getString(1)==null?"0.00":rset1.getString(1));
									drcr = "Credit Note :";
								}
								remark.add(drcr+" "+rset1.getString(2));
								transDate.add(selDate);
								bgColor.add("#d7eaff");
								Vpay_type.add("");
								
								/*checking for Dr/Cr HOLD Amount*/
								
								String holdAmtSql = "select nvl(HOLD_AMOUNT,0),to_char(PAY_RECV_DT,'dd/mm/yyyy') from FMS8_PAY_RECV_DTL where "
										+ " NEW_INV_SEQ_NO = '"+rset1.getString(2)+"'  and COMMODITY_TYPE='DLNG' "
										+ " and REV_NO = (select max(REV_NO) from FMS8_PAY_RECV_DTL where"
											+ " NEW_INV_SEQ_NO = '"+rset1.getString(2)+"'  and COMMODITY_TYPE='DLNG' )";
//								System.out.println("holdAmtSql--DR/CR----"+holdAmtSql);
								rset2 = stmt2.executeQuery(holdAmtSql);
								if(rset2.next()) {
									if(Double.parseDouble(rset2.getString(1)+"") > 0) {
										Vcredit.add("0.00");
										Vdebit.add(rset2.getString(1)==null?"0.00":rset2.getString(1));
										remark.add(drcr+" Hold Amount: "+rset1.getString(2));
										transDate.add(rset2.getString(2));
										bgColor.add("");
										Vpay_type.add("");
									}
								}
							}
						}
					}
//					System.out.println(VholdTransDate.size()+"------"+transDate.size());
					double closing_bal = 0;double advance_bal = 0;
					for(int i = 0 ; i < transDate.size() ; i++ ) {
//						System.out.println("VholdAmt.elementAt(i)-----"+VholdAmt.elementAt(i));
						closing_bal+= Double.parseDouble(Vcredit.elementAt(i)+"");
						closing_bal-= (Double.parseDouble(Vdebit.elementAt(i)+""));
						Vclosing_bal.add(nf6.format(closing_bal));
						
						if(!Vpay_type.elementAt(i).equals("SP")) {// As per incident no.268
							advance_bal+= Double.parseDouble(Vcredit.elementAt(i)+"");
							advance_bal-= (Double.parseDouble(Vdebit.elementAt(i)+""));
						}
						Vadvance_bal.add(nf6.format(advance_bal));
					}
//					System.out.println("Vadvance_bal-----"+Vadvance_bal);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Vector getVholdTransDate() {
		return VholdTransDate;
	}

	public Vector getVholdBgColor() {
		return VholdBgColor;
	}

	public Vector getVholdRmk() {
		return VholdRmk;
	}

	public void setCallFlag(String callFlag) {
		this.callFlag = callFlag;
	}

	public Vector getVcust_cd() {
		return Vcust_cd;
	}

	public Vector getVcust_nm() {
		return Vcust_nm;
	}

	public String getFrom_dt() {
		return from_dt;
	}

	public void setFrom_dt(String from_dt) {
		this.from_dt = from_dt;
	}

	public String getTo_dt() {
		return to_dt;
	}

	public void setTo_dt(String to_dt) {
		this.to_dt = to_dt;
	}

	public Vector getVcust_abbr() {
		return Vcust_abbr;
	}

	public Vector getMapping_id() {
		return mapping_id;
	}

	public String getSelMapId() {
		return selMapId;
	}

	public void setSelMapId(String selMapId) {
		this.selMapId = selMapId;
	}

	public void setSysdate(String sysdate) {
		this.sysdate = sysdate;
	}

	public Vector getTransDate() {
		return transDate;
	}

	public Vector getRemark() {
		return remark;
	}

	public Vector getVcredit() {
		return Vcredit;
	}

	public Vector getVdebit() {
		return Vdebit;
	}

	public Vector getVclosing_bal() {
		return Vclosing_bal;
	}

	public Vector getBgColor() {
		return bgColor;
	}
	public Vector getVholdAmt() {
		return VholdAmt;
	}

	public Vector getVadvance_bal() {
		return Vadvance_bal;
	}

	
}
