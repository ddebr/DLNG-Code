package com.seipl.hazira.dlng.truck_master;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.seipl.hazira.dlng.util.RuntimeConf;
import com.seipl.hazira.dlng.util.UtilBean;

public class DataBean_transporterMaster {

	Connection conn = null;
	Statement stmt;
	Statement stmt1;
	Statement stmt2;
	ResultSet rset;
	ResultSet rset1;
	ResultSet rset2;
	String quryString = "";
	String quryString1 = "";
	String quryString2 = "";
	String methodName = "";
	
	private Vector transporter_id = new Vector();
	public Vector transporter_name = new Vector();
	public Vector effective_date = new Vector();
	public Vector pan_no = new Vector();
	public Vector pan_issue_date = new Vector();
	public Vector address = new Vector();
	public Vector state = new Vector();
	public Vector city = new Vector();
	public Vector pincode = new Vector();
	public Vector cst_tin_no = new Vector();
	public Vector cst_tin_dt = new Vector();
	public Vector gst_tin_no= new Vector();
	public Vector gst_tin_dt= new Vector();
	public Vector tan_no = new Vector();
	public Vector tan_dt = new Vector();
	public Vector gstin_no = new Vector();
	public Vector gstin_dt= new Vector();
	public Vector status = new Vector();
	public Vector abbr = new Vector();
	
	
	public Vector statecode = new Vector();//11MAY2020
	public Vector statename = new Vector();//11MAY2020
	
	
	UtilBean utilBean = new UtilBean();
	String transporterFlg = "";
	String transporter_nm ="";
	String transporterAvail="false";
	String transporterid = "";
	
	Vector  CSEQ_NO= new Vector();
    Vector  CEFF_DT= new Vector();
    Vector  CONTACT_PERSON= new Vector();
    Vector  CPHONE= new Vector();
    Vector  CMOBILE= new Vector();
    Vector  CFAX_1= new Vector();
    Vector  CFAX_2 = new Vector();
    Vector  CEMAIL= new Vector();
    Vector  ADDR_FLAG= new Vector();
    Vector   ADDL_ADDR_LINE= new Vector();
    Vector   NOM_FLAG = new Vector();
    Vector  INV_FLAG = new Vector();
    Vector  FM_FLAG= new Vector();
    Vector  PM_FLAG = new Vector();
    Vector  JT_FLAG = new Vector();
    Vector  OTHER_FLAG= new Vector();
    Vector   DNOM_FLAG = new Vector();
    Vector  DINV_FLAG = new Vector();
    Vector  DFM_FLAG= new Vector();
    Vector  DPM_FLAG = new Vector();
    Vector  DJT_FLAG = new Vector();
    Vector  DOTHER_FLAG= new Vector();
    Vector  CONTACT_DESIG = new Vector();
    Vector  ACTIVE_FLAG = new Vector();
    Vector PLANT_SEQ_NO = new Vector();
    Vector PLANT_TYP = new Vector();
    Vector PLANT_NM = new Vector();
    Vector PLANT_ABR = new Vector();
    private String trans_cd = "";
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
	    			fetchExistTransporter();	
	    			conn.close();
	    			conn = null;
	    		}
	    	}
	    }
	    catch(Exception e)
	    {
	    	System.out.println("Exception In : (DataBean_transporterMaster) - (init()): "+e.getMessage());
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
					System.out.println("rset is not close "+e);
				}
			}if(rset1 != null)
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
	private void fetchExistTransporter() {
		 
		Vector CODE = new Vector();
		Vector DT  = new Vector();
		  
		methodName = "fetchExistTransporter()";
		try {
			quryString = "SELECT TRANS_NAME,TO_CHAR(EFF_DT,'DD/MM/YYYY'),PAN_NO,TO_CHAR(PAN_ISSUE_DT,'DD/MM/YYYY'),WEB_ADDR,"
					   + "CST_TIN_NO,TO_CHAR(CST_TIN_DT,'DD/MM/YYYY'),GST_TIN_NO,TO_CHAR(GST_TIN_DT,'DD/MM/YYYY'),"
					   + "TAN_NO,TO_CHAR(TAN_ISSUE_DT,'DD/MM/YYYY'),GSTIN_NO,TO_CHAR(GSTIN_DT,'DD/MM/YYYY'),TRANS_CD,FLAG,TRANS_ABBR "
					   + "From DLNG_TRANS_MST WHERE "
//					   + " DEL_FLAG='N'"
					   + " FLAG='Y' ";
//			System.out.println("quryString***"+quryString);
			rset = stmt.executeQuery(quryString);
			while(rset.next()) {
				transporter_name.add(rset.getString(1)==null?"":rset.getString(1));
				effective_date.add(rset.getString(2)==null?"":rset.getString(2));
				pan_no.add(rset.getString(3)==null?"":rset.getString(3));
				pan_issue_date.add(rset.getString(4)==null?"":rset.getString(4));
				address.add(rset.getString(5)==null?"":rset.getString(5));
				cst_tin_no.add(rset.getString(6)==null?"":rset.getString(6));
				cst_tin_dt.add(rset.getString(7)==null?"":rset.getString(7));
				gst_tin_no.add(rset.getString(8)==null?"":rset.getString(8));
				gst_tin_dt.add(rset.getString(9)==null?"":rset.getString(9));
				tan_no.add(rset.getString(10)==null?"":rset.getString(10));
				tan_dt.add(rset.getString(11)==null?"":rset.getString(11));
				gstin_no.add(rset.getString(12)==null?"":rset.getString(12));
				gstin_dt.add(rset.getString(13)==null?"":rset.getString(13));
				transporter_id.add(rset.getString(14)==null?"":rset.getString(14));
				status.add(rset.getString(15)==null?"":rset.getString(15));
				abbr.add(rset.getString(16)==null?"":rset.getString(16));
				//state.add(rset.getString(16)==null?"":rset.getString(16));
				//city.add(rset.getString(17)==null?"":rset.getString(17));
				//pincode.add(rset.getString(18)==null?"":rset.getString(18));
			}
			
			quryString1 = "SELECT STATE_CODE,STATE_NM From STATE_MST ORDER BY STATE_NM";
			rset1 = stmt1.executeQuery(quryString1);
			while(rset1.next()) {
				statecode.add(rset1.getString(1)==null?"":rset1.getString(1));
				statename.add(rset1.getString(2)==null?"":rset1.getString(2));
			}
//			System.out.println("trans_cd----"+trans_cd);
			if(!trans_cd.equals("")) {
				  String queryString = "select seq_no, to_char(max(eff_dt),'dd/mm/yyyy') from dlng_transporter_contact_mst where " +
			  				"trans_cd='"+trans_cd+"' group by seq_no";
//			  System.out.println("queryString---"+queryString);
			  rset = stmt.executeQuery(queryString);
			  while(rset.next())
			  {
				  CODE.add(rset.getString(1)==null?"":rset.getString(1));
				  DT.add(rset.getString(2)==null?"":rset.getString(2));
			  }
			  for(int j=0;j<CODE.size();j++)
			  { 		
				  queryString = "SELECT SEQ_NO, to_char(EFF_DT,'dd/mm/yyyy'), " +
						  		" CONTACT_PERSON, PHONE, MOBILE," +
						  		" FAX_1, FAX_2, EMAIL, ADDR_FLAG," +
						  		" ADDL_ADDR_LINE, NOM_FLAG, INV_FLAG," +
						  		" FM_FLAG, PM_FLAG, OTHER_FLAG, ACTIVE_FLAG,CONTACT_DESIG ,JT_FLAG,DEF_NOM_FLAG, " +
						  		"DEF_INV_FLAG, DEF_FM_FLAG, DEF_PM_FLAG,  DEF_OTHER_FLAG, DEF_JT_FLAG " +
						  		" FROM DLNG_TRANSPORTER_CONTACT_MST WHERE trans_cd = '"+trans_cd+"' " +
						  		" AND EFF_DT = to_date('"+DT.elementAt(j)+"','dd/mm/yyyy')  AND SEQ_NO = '"+CODE.elementAt(j)+"' " ;
//				  System.out.println("queryString---"+queryString);
				  rset = stmt.executeQuery(queryString);
				  if(rset.next())
				  {
			   	       CSEQ_NO.add(rset.getString(1)==null?"":rset.getString(1)); 
			   	       CEFF_DT.add(rset.getString(2)==null?"":rset.getString(2)); 
			   	       CONTACT_PERSON.add(rset.getString(3)==null?"":rset.getString(3)); 
			   	       CPHONE.add(rset.getString(4)==null?"":rset.getString(4)); 
			   	       CMOBILE.add(rset.getString(5)==null?"":rset.getString(5)); 
			   	       CFAX_1.add(rset.getString(6)==null?"":rset.getString(6)); 
			   	       CFAX_2.add(rset.getString(7)==null?"":rset.getString(7)); 
			   	       CEMAIL.add(rset.getString(8)==null?"":rset.getString(8)); 
			   	       ADDR_FLAG.add(rset.getString(9)==null?"":rset.getString(9)); 
			   	       ADDL_ADDR_LINE.add(rset.getString(10)==null?"":rset.getString(10)); 
			   	       NOM_FLAG.add(rset.getString(11)==null?"":rset.getString(11)); 
			   	       INV_FLAG.add(rset.getString(12)==null?"":rset.getString(12)); 
			   	       FM_FLAG.add(rset.getString(13)==null?"":rset.getString(13)); 
			   	       PM_FLAG.add(rset.getString(14)==null?"":rset.getString(14)); 
			   	       OTHER_FLAG.add(rset.getString(15)==null?"":rset.getString(15)); 
			   	       ACTIVE_FLAG.add(rset.getString(16)==null?"":rset.getString(16)); 
			   	       CONTACT_DESIG.add(rset.getString(17)==null?"":rset.getString(17)); 
			   	       JT_FLAG.add(rset.getString(18)==null?"":rset.getString(18));
			   	       DNOM_FLAG.add(rset.getString(19)==null?"":rset.getString(19)); 
				       DINV_FLAG.add(rset.getString(20)==null?"":rset.getString(20)); 
				       DFM_FLAG.add(rset.getString(21)==null?"":rset.getString(21)); 
				       DPM_FLAG.add(rset.getString(22)==null?"":rset.getString(22)); 
				       DOTHER_FLAG.add(rset.getString(23)==null?"":rset.getString(23)); 
				       DJT_FLAG.add(rset.getString(24)==null?"":rset.getString(24));
		          } 
	          }
		  }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private void fetchTransporterNameOnCustId(String cust_id2, String transporter_nm2) {

	}
	public Vector getTransporter_id() {
		return transporter_id;
	}
	public void setTransporter_id(Vector transporter_id) {
		this.transporter_id = transporter_id;
	}
	public Vector getTransporter_name() {
		return transporter_name;
	}
	public void setTransporter_name(Vector transporter_name) {
		this.transporter_name = transporter_name;
	}
	public Vector getEffective_date() {
		return effective_date;
	}
	public void setEffective_date(Vector effective_date) {
		this.effective_date = effective_date;
	}
	public Vector getPan_no() {
		return pan_no;
	}
	public void setPan_no(Vector pan_no) {
		this.pan_no = pan_no;
	}
	public Vector getPan_issue_date() {
		return pan_issue_date;
	}
	public void setPan_issue_date(Vector pan_issue_date) {
		this.pan_issue_date = pan_issue_date;
	}
	public Vector getAddress() {
		return address;
	}
	public void setAddress(Vector address) {
		this.address = address;
	}
	public Vector getState() {
		return state;
	}
	public void setState(Vector state) {
		this.state = state;
	}
	public Vector getCity() {
		return city;
	}
	public void setCity(Vector city) {
		this.city = city;
	}
	public Vector getPincode() {
		return pincode;
	}
	public void setPincode(Vector pincode) {
		this.pincode = pincode;
	}
	public String getTransporterFlg() {
		return transporterFlg;
	}
	public void setTransporterFlg(String transporterFlg) {
		this.transporterFlg = transporterFlg;
	}
	public String getTransporter_nm() {
		return transporter_nm;
	}
	public void setTransporter_nm(String transporter_nm) {
		this.transporter_nm = transporter_nm;
	}
	public String getTransporterAvail() {
		return transporterAvail;
	}
	public void setTransporterAvail(String transporterAvail) {
		this.transporterAvail = transporterAvail;
	}
	public String getTransporterid() {
		return transporterid;
	}
	public void setTransporterid(String transporterid) {
		this.transporterid = transporterid;
	}
	public Vector getStatus() {
		return status;
	}
	public void setStatus(Vector status) {
		this.status = status;
	}
	public Vector getCst_tin_no() {
		return cst_tin_no;
	}
	public void setCst_tin_no(Vector cst_tin_no) {
		this.cst_tin_no = cst_tin_no;
	}
	public Vector getCst_tin_dt() {
		return cst_tin_dt;
	}
	public void setCst_tin_dt(Vector cst_tin_dt) {
		this.cst_tin_dt = cst_tin_dt;
	}
	public Vector getGst_tin_no() {
		return gst_tin_no;
	}
	public void setGst_tin_no(Vector gst_tin_no) {
		this.gst_tin_no = gst_tin_no;
	}
	public Vector getGst_tin_dt() {
		return gst_tin_dt;
	}
	public void setGst_tin_dt(Vector gst_tin_dt) {
		this.gst_tin_dt = gst_tin_dt;
	}
	public Vector getTan_no() {
		return tan_no;
	}
	public void setTan_no(Vector tan_no) {
		this.tan_no = tan_no;
	}
	public Vector getTan_dt() {
		return tan_dt;
	}
	public void setTan_dt(Vector tan_dt) {
		this.tan_dt = tan_dt;
	}
	public Vector getGstin_no() {
		return gstin_no;
	}
	public void setGstin_no(Vector gstin_no) {
		this.gstin_no = gstin_no;
	}
	public Vector getGstin_dt() {
		return gstin_dt;
	}
	public void setGstin_dt(Vector gstin_dt) {
		this.gstin_dt = gstin_dt;
	}
	
//----------11MAY2020---------------------------------------
	public Vector getStatecode() {
		return statecode;
	}
	public void setStatecode(Vector statecode) {
		this.statecode = statecode;
	}
	public Vector getStatename() {
		return statename;
	}
	public void setStatename(Vector statename) {
		this.statename = statename;
	}
	public Vector getAbbr() {
		return abbr;
	}
	public Vector getCSEQ_NO() {
		return CSEQ_NO;
	}
	public Vector getCEFF_DT() {
		return CEFF_DT;
	}
	public Vector getCONTACT_PERSON() {
		return CONTACT_PERSON;
	}
	public Vector getCPHONE() {
		return CPHONE;
	}
	public Vector getCMOBILE() {
		return CMOBILE;
	}
	public Vector getCFAX_1() {
		return CFAX_1;
	}
	public Vector getCFAX_2() {
		return CFAX_2;
	}
	public Vector getCEMAIL() {
		return CEMAIL;
	}
	public Vector getADDR_FLAG() {
		return ADDR_FLAG;
	}
	public Vector getADDL_ADDR_LINE() {
		return ADDL_ADDR_LINE;
	}
	public Vector getNOM_FLAG() {
		return NOM_FLAG;
	}
	public Vector getINV_FLAG() {
		return INV_FLAG;
	}
	public Vector getFM_FLAG() {
		return FM_FLAG;
	}
	public Vector getPM_FLAG() {
		return PM_FLAG;
	}
	public Vector getJT_FLAG() {
		return JT_FLAG;
	}
	public Vector getOTHER_FLAG() {
		return OTHER_FLAG;
	}
	public Vector getDNOM_FLAG() {
		return DNOM_FLAG;
	}
	public Vector getDINV_FLAG() {
		return DINV_FLAG;
	}
	public Vector getDFM_FLAG() {
		return DFM_FLAG;
	}
	public Vector getDPM_FLAG() {
		return DPM_FLAG;
	}
	public Vector getDJT_FLAG() {
		return DJT_FLAG;
	}
	public Vector getDOTHER_FLAG() {
		return DOTHER_FLAG;
	}
	public Vector getCONTACT_DESIG() {
		return CONTACT_DESIG;
	}
	public Vector getACTIVE_FLAG() {
		return ACTIVE_FLAG;
	}
	public Vector getPLANT_SEQ_NO() {
		return PLANT_SEQ_NO;
	}
	public Vector getPLANT_TYP() {
		return PLANT_TYP;
	}
	public Vector getPLANT_NM() {
		return PLANT_NM;
	}
	public Vector getPLANT_ABR() {
		return PLANT_ABR;
	}
	public String getTrans_cd() {
		return trans_cd;
	}
	public void setTrans_cd(String trans_cd) {
		this.trans_cd = trans_cd;
	}
}