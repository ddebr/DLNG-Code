package com.seipl.hazira.dlng.util;
import javax.naming.*;
import javax.sql.*;

import java.sql.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import com.seipl.hazira.dlng.util.RuntimeConf;
/*
This DataBean retrives data for frm_penom.jsp
*/
public class DB_UserProfile 
{
    Connection conn;
	Statement stmt;
	ResultSet rset;

	public String query;	
	public boolean status ; 
	public String method_name;
	
	public String userId;
	public String Uan_No;
	public String Lic_No ; 
	public String Pf_No;
	public String Lic_Policy;
	public String SUPERANNUATION_LIC="";
	

	public String TAX_DEDUCTION="";
	public String TAX_RATE="";
	public String EXIT_LOAD="";
	public String NET_VAL="";
	public String BANK_ACCOUNT_NO="";
	public String BANK_NAME="";
	public String Beneficiary_nm="";
	public String emp_cd="";
	public int count;
	

 public void setCount(int count) {this.count = count;}
 
  public void init() {
    try
    {

	  Context initContext = new InitialContext();
      if(initContext == null ) 
          throw new Exception("Boom - No Context");
	  Context envContext  = (Context)initContext.lookup("java:/comp/env");
	  DataSource ds = (DataSource)envContext.lookup(RuntimeConf.security_database);
      if (ds != null) 
      {
         conn = ds.getConnection();       
         if(conn != null)  
         {
           stmt = conn.createStatement();
		   
				   getModuleDetail();
		   }
		   conn.close();
        }
    }catch(Exception e) {
      //e.printStackTrace();
	  System.out.println(" Exception in LogicBean_Alerter "+e+" "+method_name);
    }
 }

 public void getModuleDetail()
 {
	 try
	{
	     query = " SELECT emp_cd, Email_id, UAN_NO, LIC_NO, PF_NO, LIC_POLICY,emp_alias_cd FROM HR_EMP_MST " +
	     		"WHERE emp_abr='"+userId+"' and emp_status!='N'";
//	    System.out.println("PROFILE: QRY0001 :SELECT :HR_EMP_MST: DB_UserProfile.java : "+query);
	     rset = stmt.executeQuery(query);
	     if(rset.next())
	     {
	     	Uan_No = rset.getString(3);
	     	Lic_No = rset.getString(4);
	     	Pf_No = rset.getString(5);
	     	Lic_Policy = rset.getString(6);
	     	emp_cd = rset.getString(7)==null?"":rset.getString(7);
	     	//System.out.println("MILLLLLLLLLLLLLLLLL  "+ rset.getString(2)+" "+userId+" "+rset.getString(1));
	     }
	     
	     query="select SUPERANNUATION_LIC,TAX_DEDUCTION,TAX_RATE,EXIT_LOAD,NET_VAL,BANK_ACCOUNT_NO,BANK_NAME,BENIFICIARY_NM from emp_profile_dtl where emp_alias_cd='"+emp_cd+"'  ";
	      rset=stmt.executeQuery(query);
	      if(rset.next()){
	    	  SUPERANNUATION_LIC=rset.getString(1)==null?"":rset.getString(1);
	    	  TAX_DEDUCTION=rset.getString(2)==null?"":rset.getString(2);
	    	  TAX_RATE=rset.getString(3)==null?"":rset.getString(3);
	    	  EXIT_LOAD=rset.getString(4)==null?"":rset.getString(4);
	    	  NET_VAL=rset.getString(5)==null?"":rset.getString(5);
	    	  BANK_ACCOUNT_NO=rset.getString(6)==null?"":rset.getString(6);
	    	  BANK_NAME=rset.getString(7)==null?"":rset.getString(7);
	    	  Beneficiary_nm=rset.getString(8)==null?"":rset.getString(8);
	      }
	     
	}catch(Exception e){
		System.out.println("EXCEPTION:LogicBean_Alerter-->getModuleDetail()-->SEC_MODULE_MST: "+e);
	}
 }
 public String getUan_No() { return Uan_No; }
 public String getLic_No() { return Lic_No; }
 public String getPf_No() { return Pf_No; }
 public String getLic_Policy() { return Lic_Policy; }
 public String getSUPERANNUATION_LIC() {
		return SUPERANNUATION_LIC;
	}

	public void setSUPERANNUATION_LIC(String sUPERANNUATION_LIC) {
		SUPERANNUATION_LIC = sUPERANNUATION_LIC;
	}

	public String getTAX_DEDUCTION() {
		return TAX_DEDUCTION;
	}

	public void setTAX_DEDUCTION(String tAX_DEDUCTION) {
		TAX_DEDUCTION = tAX_DEDUCTION;
	}

	public String getTAX_RATE() {
		return TAX_RATE;
	}

	public void setTAX_RATE(String tAX_RATE) {
		TAX_RATE = tAX_RATE;
	}

	public String getEXIT_LOAD() {
		return EXIT_LOAD;
	}

	public void setEXIT_LOAD(String eXIT_LOAD) {
		EXIT_LOAD = eXIT_LOAD;
	}

	public String getNET_VAL() {
		return NET_VAL;
	}

	public void setNET_VAL(String nET_VAL) {
		NET_VAL = nET_VAL;
	}

	public String getBANK_ACCOUNT_NO() {
		return BANK_ACCOUNT_NO;
	}

	public void setBANK_ACCOUNT_NO(String bANK_ACCOUNT_NO) {
		BANK_ACCOUNT_NO = bANK_ACCOUNT_NO;
	}

	public String getBANK_NAME() {
		return BANK_NAME;
	}

	public void setBANK_NAME(String bANK_NAME) {
		BANK_NAME = bANK_NAME;
	}

	public String getBeneficiary_nm() {
		return Beneficiary_nm;
	}

	public void setBeneficiary_nm(String beneficiary_nm) {
		Beneficiary_nm = beneficiary_nm;
	}

	public String getUserId() {
		return userId;
	}

	public void setUan_No(String uan_No) {
		Uan_No = uan_No;
	}

 public int getCount() { return count; }
 public boolean getStatus(){ return status; }

 public void setUserId(String s) { userId = s;}
}