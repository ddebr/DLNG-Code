package com.seipl.hazira.dlng.contract_master;

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

public class Databean_Advance_Payment2 {
	Connection conn; 
	Statement stmt;
	Statement stmt1;
	Statement stmt2;
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
	String error_msg = "";
	
	String fgsa_revno = "";
	String sn_cd = "";
	String sn_revno = "";
	String fgsa_cd = "";
	String bscode = "";
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
	    			
	    			addColumns();
	    			if(callFlag.equalsIgnoreCase("fetchAdvPayDtl")) {
	    				
	    				fetchAvailAdvPayDtl();
	    				
	    			}else if(callFlag.equalsIgnoreCase("fetchAdvPayDtlMulti")) {
	    				
	    				fetchCustDtl();
	    				if(!cust_cd.equalsIgnoreCase("")) {
	    					
	    					fetchAdvPayDtlMulti();
	    				}
	    				
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
	Vector Vcust_cd = new Vector();
	Vector Vcust_nm = new Vector();
	Vector Vcust_abbr = new Vector();
	String cust_cd = "";
	private void fetchCustDtl()throws SQLException,IOException {
		
		try {
			if(cont_typ.equalsIgnoreCase("L")) {
				
				queryString = "SELECT CUSTOMER_CD,CUSTOMER_NAME,CUSTOMER_ABBR FROM FMS7_CUSTOMER_MST A "
						+ "where (DORMANT_FLAG!='Y' OR DORMANT_FLAG IS NULL) "
						+ " AND CUSTOMER_CD IN (SELECT CUSTOMER_CD FROM DLNG_TENDER_MST WHERE END_DT >=SYSDATE AND FLAG='Y') "
						+ " AND EFF_DT=(SELECT MAX(EFF_DT) FROM FMS7_CUSTOMER_MST C WHERE A.CUSTOMER_CD=C.CUSTOMER_CD AND C.EFF_DT<=SYSDATE) "
						+ " ORDER BY A.CUSTOMER_CD";
			}else {
				
				queryString = "SELECT A.CUSTOMER_CD,CUSTOMER_NAME,CUSTOMER_ABBR "
						+ "FROM FMS7_CUSTOMER_MST A"
						+ " where (A.DORMANT_FLAG!='Y' OR A.DORMANT_FLAG IS NULL) "
						+ " AND CUSTOMER_CD IN (SELECT CUSTOMER_CD FROM DLNG_FLSA_MST WHERE END_DT >=SYSDATE AND FLAG='Y')"
						+ " AND EFF_DT=(SELECT MAX(EFF_DT) FROM FMS7_CUSTOMER_MST C WHERE A.CUSTOMER_CD=C.CUSTOMER_CD AND C.EFF_DT<=SYSDATE) "
						+ "ORDER BY A.CUSTOMER_NAME";
				
			}
//			System.out.println("cont type****"+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next()) {
				Vcust_cd.add(rset.getString(1)==null?"":rset.getString(1));	
			    Vcust_nm.add(rset.getString(2)==null?"":rset.getString(2));
				Vcust_abbr.add(rset.getString(3)==null?"":rset.getString(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public Vector vsn_no= new Vector();
	public Vector vsn_rev_No= new Vector();
	public Vector vsn_tcq= new Vector();
	public Vector vsn_dcq= new Vector();
	public Vector vsn_stDt= new Vector();
	public Vector vsn_endDt= new Vector();
	public Vector vsn_name= new Vector();
	public Vector vsn_fcc_flag= new Vector();
	public Vector Vflsa_no=new Vector();
	public Vector Vflsa_rev_no=new Vector();
	public Vector vadv_cnt=new Vector();
	public Vector vadv_collection_flag=new Vector();
	public Vector vadv_collection_currency=new Vector();
	
	int advCnt = 0;
	private void fetchAdvPayDtlMulti()throws SQLException,IOException {
		try {
			
			if(cont_typ.equalsIgnoreCase("S")) {
				
				queryString = "SELECT A.SN_NO, A.SN_REV_NO,A.FLSA_NO, A.FLSA_REV_NO,A.TCQ,"
						+ " A.SN_NAME,nvl(A.FCC_FLAG,'N'),TO_CHAR(A.START_DT,'DD/MM/YYYY'),"
						+ " TO_CHAR(A.END_DT,'DD/MM/YYYY'),A.ADVANCE_COLLECTION_FLAG,A.CURRENCY_CD"
						+ "  FROM DLNG_SN_MST A, FMS7_CUSTOMER_MST C, " +
						"FMS7_UNIT_MST D WHERE A.FLAG='T' AND A.SN_REV_NO=(SELECT MAX(B.SN_REV_NO) FROM " +
						"DLNG_SN_MST B WHERE A.SN_NO=B.SN_NO AND A.FLSA_NO=B.FLSA_NO AND A.FLSA_REV_NO=B.FLSA_REV_NO " +
						"AND B.FLAG='T' AND A.CUSTOMER_CD=B.CUSTOMER_CD) AND A.CUSTOMER_CD=C.CUSTOMER_CD AND A.CUSTOMER_CD='"+cust_cd+"' AND " +
						"C.EFF_DT=(SELECT MAX(E.EFF_DT) FROM FMS7_CUSTOMER_MST E WHERE E.CUSTOMER_CD=A.CUSTOMER_CD) " +
						"AND A.STATUS='Y' AND A.QUANTITY_UNIT=D.UNIT_CD " +
						"ORDER BY A.customer_cd,A.flsa_no,A.flsa_rev_no,A.sn_no";
				
//				System.out.println("SN List---"+queryString);
			}else if(cont_typ.equalsIgnoreCase("L")) {
				
				queryString = "SELECT A.LOA_NO,A.LOA_REV_NO,A.TENDER_NO,0,A.TCQ,"
						+ " A.LOA_NAME,nvl(A.FCC_FLAG,'N'),TO_CHAR(START_DT,'DD/MM/YYYY'), TO_CHAR(END_DT,'DD/MM/YYYY'),"
						+ " A.ADVANCE_COLLECTION_FLAG,CURRENCY_CD "+
						"from DLNG_LOA_MST A,FMS7_CUSTOMER_MST C,FMS7_UNIT_MST D WHERE A.FLAG='T' AND " +
						"A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM DLNG_LOA_MST B WHERE A.LOA_NO=B.LOA_NO AND "+
			            "A.TENDER_NO=B.TENDER_NO AND B.FLAG='T' AND A.CUSTOMER_CD=B.CUSTOMER_CD) AND A.CUSTOMER_CD=C.CUSTOMER_CD AND " +
			            "A.CUSTOMER_CD='"+cust_cd+"' AND C.EFF_DT=(SELECT MAX(E.EFF_DT) FROM FMS7_CUSTOMER_MST E WHERE " +
			            "E.CUSTOMER_CD=A.CUSTOMER_CD) AND A.STATUS='Y' AND A.QUANTITY_UNIT=D.UNIT_CD " +
						" order by C.customer_NAME,A.tender_no,A.loa_no";
//				System.out.println("LoA list----"+queryString);
			}
			rset1 = stmt1.executeQuery(queryString);
			while (rset1.next()) {
				
				advCnt = 0;
				vsn_no.add(rset1.getString(1)==null?"":rset1.getString(1));
				vsn_rev_No.add(rset1.getString(2)==null?"":rset1.getString(2));
				Vflsa_no.add(rset1.getString(3)==null?"":rset1.getString(3));
				Vflsa_rev_no.add(rset1.getString(4)==null?"":rset1.getString(4));
				vsn_tcq.add(rset1.getString(5)==null?"":rset1.getString(5));
				vsn_name.add(rset1.getString(6)==null?"":rset1.getString(6));
				vsn_fcc_flag.add(rset1.getString(7)==null?"":rset1.getString(7));
				vsn_stDt.add(rset1.getString(8)==null?"":rset1.getString(8));
				vsn_endDt.add(rset1.getString(9)==null?"":rset1.getString(9));
				vadv_collection_flag.add(rset1.getString(10)==null?"":rset1.getString(10));
				vadv_collection_currency.add(rset1.getString(11)==null?"":rset1.getString(11));
				
				bscode = cust_cd;
				fgsa_cd = rset1.getString(3);
				sn_cd = rset1.getString(1);
				
				fetchAvailAdvPayDtl();
//				System.out.println("total_amt-------"+total_amt);
				vadv_cnt.add(advCnt);
				approved_amt.add(nf3.format(total_amt));
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private void addColumns() throws SQLException  {
		try {
			int count=0;
			String s="SELECT COUNT(COLUMN_NAME) FROM COLS WHERE UPPER(TABLE_NAME) LIKE 'DLNG_CONT_PRICE_DTL' "+
					" AND UPPER(COLUMN_NAME) LIKE 'PAY_TYPE'";
//			//System.out.println("s****"+s);
			rset=stmt.executeQuery(s);
			if(rset.next())
			{
				count=rset.getInt(1);
			}
			if(count==0)
			{
				s="ALTER TABLE DLNG_CONT_PRICE_DTL ADD PAY_TYPE VARCHAR2(2 BYTE)";
				stmt.executeUpdate(s);
				conn.commit();
			}
			
			int count1=0;
			String s1="SELECT COUNT(COLUMN_NAME) FROM COLS WHERE UPPER(TABLE_NAME) LIKE 'DLNG_ADVC_PAY_MST' "+
					" AND UPPER(COLUMN_NAME) LIKE 'CONTRACT_TYPE'";
//			//System.out.println("s****"+s);
			rset=stmt.executeQuery(s1);
			if(rset.next())
			{
				count1=rset.getInt(1);
			}
			if(count1==0)
			{
				s1="ALTER TABLE DLNG_ADVC_PAY_MST ADD CONTRACT_TYPE CHAR(1 BYTE)";
				stmt.executeUpdate(s1);
				conn.commit();
			}
			
			count1=0;
			s1="SELECT COUNT(COLUMN_NAME) FROM COLS WHERE UPPER(TABLE_NAME) LIKE 'DLNG_ADVC_PAY_MST' "+
					" AND UPPER(COLUMN_NAME) LIKE 'DR_CR_FLAG'";
//			//System.out.println("s****"+s);
			rset=stmt.executeQuery(s1);
			if(rset.next())
			{
				count1=rset.getInt(1);
			}
			if(count1==0)
			{
				s1="ALTER TABLE DLNG_ADVC_PAY_MST ADD DR_CR_FLAG CHAR(1 BYTE)";
				stmt.executeUpdate(s1);
				conn.commit();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}	
	 Vector payment_dt = new Vector();
	 Vector  adv_amt = new Vector();
	 Vector  exg_rt =  new Vector();
	 Vector  currency =  new Vector();
	 Vector  exg_dt =  new Vector();
	 Vector  seq_no =  new Vector();
	 Vector  ent_by_cd =  new Vector();
	 Vector  ent_by_nm =  new Vector();
	 Vector  ent_dt =  new Vector();
	 Vector  remark =  new Vector();
	 Vector  pay_type_nm =  new Vector();
	 Vector  pay_type_cd =  new Vector();
	 Vector approved_by =  new Vector();
	 Vector approved_dt  =  new Vector(); 
	 Vector approved_flag =  new Vector();
	 Vector approved_amt =  new Vector();
	 Vector dr_cr_flag =  new Vector();
	 
	 double total_amt= 0;
	 String cont_typ = "";
	 NumberFormat nf = new DecimalFormat("###########0.00"); 
	 NumberFormat nf3 = new DecimalFormat("###,###,###,##0.00");
	 
	private void fetchAvailAdvPayDtl()throws SQLException,IOException {
		
		try {
			total_amt= 0;
			String fetchAdvDtlSql = "select SEQ_NO,CURRENCY,EMP_CD,to_char(ENT_DT,'dd/mm/yyyy'),to_char(EXG_DATE,'dd/mm/yyyy'),"
					+ " EXG_RATE,nvl(PAY_AMT,0),to_char(PAY_DT,'dd/mm/yyyy'),REMARK,PAY_TYPE,APPROVED_BY,"
					+ "to_char(APPROVED_DT,'dd/mm/yyyy'),nvl(APPROVED_FLAG,'N'),nvl(DR_CR_FLAG,'')"
					+ " from DLNG_ADVC_PAY_MST where CUSTOMER_CD='"+bscode+"'and FLSA_NO = '"+fgsa_cd+"' "
					+ "  and SN_NO = '"+sn_cd+"'  and CONTRACT_TYPE='"+cont_typ+"'  ORDER BY seq_no,pay_type";
//			System.out.println("fetchAdvDtlSql------------"+fetchAdvDtlSql);
			rset = stmt.executeQuery(fetchAdvDtlSql);
			while (rset.next()) {
				
				advCnt++;
				seq_no.add(rset.getString(1)==null?"":rset.getString(1));
				currency.add(rset.getString(2)==null?"":rset.getString(2));
				ent_by_cd.add(rset.getString(3)==null?"":rset.getString(3));
				ent_dt.add(rset.getString(4)==null?"":rset.getString(4));
				exg_dt.add(rset.getString(5)==null?"":rset.getString(5));
				exg_rt.add(rset.getString(6)==null?"":rset.getString(6));
				adv_amt.add(rset.getString(7)==null?"":rset.getString(7));
				payment_dt.add(rset.getString(8)==null?"":rset.getString(8));
				remark.add(rset.getString(9)==null?"":rset.getString(9));
				String pay_typ = rset.getString(10)==null?"":rset.getString(10);
				approved_dt.add(rset.getString(12)==null?"":rset.getString(12));
				approved_flag.add(rset.getString(13)==null?"":rset.getString(13));
				dr_cr_flag.add(rset.getString(14)==null?"":rset.getString(14));
				String drcrFlg = rset.getString(14)==null?"":rset.getString(14);
				
				if(pay_typ.equals("AP")) {
					pay_type_nm.add("Advance");
				}else if(pay_typ.equals("SP")) {
					pay_type_nm.add("Security");
				}else if(pay_typ.equals("LP")) {
					pay_type_nm.add("Lump-sum");
				}else {
					pay_type_nm.add("");
				}
				
				pay_type_cd.add(pay_typ);
				
				if(!rset.getString(3).equals("")) {
					String empNmSql = "select emp_nm from HR_EMP_MST where emp_cd='"+rset.getString(3)+"' ";
					rset2 = stmt2.executeQuery(empNmSql);
					if(rset2.next()) {
						ent_by_nm.add(rset2.getString(1)==null?"":rset2.getString(1));
					}
				}else {
					ent_by_nm.add("");
				}
				if(!rset.getString(7).equals("") &&  !rset.getString(7).equals(" ") && rset.getString(13).toString().equalsIgnoreCase("Y")) {
//					System.out.println("rset.getString(14)----"+rset.getString(14));
					if(drcrFlg.equalsIgnoreCase("D")) {
						total_amt-= rset.getDouble(7);
					}else {
						total_amt+= rset.getDouble(7);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getCallFlag() {
		return callFlag;
	}

	public String getFgsa_revno() {
		return fgsa_revno;
	}

	public String getSn_cd() {
		return sn_cd;
	}

	public String getSn_revno() {
		return sn_revno;
	}

	public String getFgsa_cd() {
		return fgsa_cd;
	}

	public String getBscode() {
		return bscode;
	}

	public void setCallFlag(String callFlag) {
		this.callFlag = callFlag;
	}

	public void setFgsa_revno(String fgsa_revno) {
		this.fgsa_revno = fgsa_revno;
	}

	public void setSn_cd(String sn_cd) {
		this.sn_cd = sn_cd;
	}

	public void setSn_revno(String sn_revno) {
		this.sn_revno = sn_revno;
	}

	public void setFgsa_cd(String fgsa_cd) {
		this.fgsa_cd = fgsa_cd;
	}

	public void setBscode(String bscode) {
		this.bscode = bscode;
	}

	public Vector getPayment_dt() {
		return payment_dt;
	}

	public Vector getAdv_amt() {
		return adv_amt;
	}

	public Vector getExg_rt() {
		return exg_rt;
	}

	public Vector getCurrency() {
		return currency;
	}

	public Vector getExg_dt() {
		return exg_dt;
	}

	public Vector getSeq_no() {
		return seq_no;
	}

	public Vector getEnt_by_cd() {
		return ent_by_cd;
	}

	public Vector getEnt_dt() {
		return ent_dt;
	}

	public Vector getRemark() {
		return remark;
	}

	public void setPayment_dt(Vector payment_dt) {
		this.payment_dt = payment_dt;
	}

	public void setAdv_amt(Vector adv_amt) {
		this.adv_amt = adv_amt;
	}

	public void setExg_rt(Vector exg_rt) {
		this.exg_rt = exg_rt;
	}

	public void setCurrency(Vector currency) {
		this.currency = currency;
	}

	public void setExg_dt(Vector exg_dt) {
		this.exg_dt = exg_dt;
	}

	public void setSeq_no(Vector seq_no) {
		this.seq_no = seq_no;
	}

	public void setEnt_by_cd(Vector ent_by_cd) {
		this.ent_by_cd = ent_by_cd;
	}

	public void setEnt_dt(Vector ent_dt) {
		this.ent_dt = ent_dt;
	}

	public void setRemark(Vector remark) {
		this.remark = remark;
	}

	public Vector getEnt_by_nm() {
		return ent_by_nm;
	}

	public void setEnt_by_nm(Vector ent_by_nm) {
		this.ent_by_nm = ent_by_nm;
	}

	public double getTotal_amt() {
		return total_amt;
	}

	public void setTotal_amt(double total_amt) {
		this.total_amt = total_amt;
	}

	public String getCont_typ() {
		return cont_typ;
	}

	public void setCont_typ(String cont_typ) {
		this.cont_typ = cont_typ;
	}

	public Vector getPay_type_nm() {
		return pay_type_nm;
	}

	public void setPay_type_nm(Vector pay_type_nm) {
		this.pay_type_nm = pay_type_nm;
	}

	public Vector getPay_type_cd() {
		return pay_type_cd;
	}

	public void setPay_type_cd(Vector pay_type_cd) {
		this.pay_type_cd = pay_type_cd;
	}

	public Vector getApproved_dt() {
		return approved_dt;
	}

	public Vector getApproved_flag() {
		return approved_flag;
	}

	public Vector getVcust_cd() {
		return Vcust_cd;
	}

	public Vector getVcust_nm() {
		return Vcust_nm;
	}

	public Vector getVcust_abbr() {
		return Vcust_abbr;
	}

	public String getCust_cd() {
		return cust_cd;
	}

	public void setCust_cd(String cust_cd) {
		this.cust_cd = cust_cd;
	}

	public Vector getVsn_no() {
		return vsn_no;
	}

	public Vector getVsn_rev_No() {
		return vsn_rev_No;
	}

	public Vector getVsn_tcq() {
		return vsn_tcq;
	}

	public Vector getVsn_dcq() {
		return vsn_dcq;
	}

	public Vector getVsn_stDt() {
		return vsn_stDt;
	}

	public Vector getVsn_endDt() {
		return vsn_endDt;
	}

	public Vector getVsn_name() {
		return vsn_name;
	}

	public Vector getVflsa_no() {
		return Vflsa_no;
	}

	public Vector getVflsa_rev_no() {
		return Vflsa_rev_no;
	}

	public Vector getVsn_fcc_flag() {
		return vsn_fcc_flag;
	}

	public Vector getVadv_cnt() {
		return vadv_cnt;
	}

	public Vector getVadv_collection_flag() {
		return vadv_collection_flag;
	}

	public Vector getVadv_collection_currency() {
		return vadv_collection_currency;
	}

	public Vector getApproved_amt() {
		return approved_amt;
	}

	public Vector getDr_cr_flag() {
		return dr_cr_flag;
	}
}
