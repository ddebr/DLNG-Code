package com.seipl.hazira.dlng.util;
import javax.naming.*;
import javax.sql.*;

import com.seipl.hazira.dlng.util.RuntimeConf;

import java.sql.*;
/*
This DataBean retrives data for frm_penom.jsp
*/
public class LogicBean_Alerter 
{
    Connection conn;
	Statement stmt;
	ResultSet rst;

	public String query;
	public String day;
	public String mdq;
	public String pm_bal="0";
	public String sup_id;
	public String party_id;
	public String contract_year;
	public String pm_st_dt;
	public String pm_end_dt;
	public String data_for;
	public String nom_dt;
	public String method_name;
	public String estlval;
	public String date_start;
	public String date_end;
	public boolean status ; 
	public boolean status_contract ; 
	public boolean tktstat;
	public boolean status_contract_end;
    public String set_form_cd="";
    public String module_name="";
	public int count;
	
	public void clear_variables()
	{
		query = "";
	    data_for = "";
		nom_dt = "";
		status = false;
		status_contract = false;
		status_contract_end=false;
		tktstat = false;
		count = 0;
		method_name="";
	}
 
   
  private  void check_partynom() throws SQLException
  {
			method_name = "check_partynom()";
			rst = stmt.executeQuery("select count(*) from party_nom where nom_dt=to_date('"+nom_dt+"','dd/mm/yy') and party_id='GSPL'");
			if(rst.next()){
			count = Integer.parseInt(rst.getString(1));
			if(count > 0){
			status = true;
			}
			else status = false;
			}
			//
			rst = stmt.executeQuery("select count(*) from trans_dtl where st_dt<=to_date('"+nom_dt+"','dd/mm/yy') and party_id='GSPL'");
			if(rst.next()){
			count = Integer.parseInt(rst.getString(1));
			if(count > 0){
			status_contract = true;
			}
			else status_contract = false;
			}
			//
			// check for tieckt entry
			tktstat = false;
			query="select party_id from ticket_rec where ticket_dt=to_date('"+nom_dt+"','dd/mm/yy')";
			rst = stmt.executeQuery(query);
			if(rst.next()){
				tktstat = true;
			}			
			//modified on 01-march-2006
			//check of contract year (if year> contract year limit) return false}
			rst = stmt.executeQuery("select count(*) from trans_dtl where end_dt>=to_date('"+nom_dt+"','dd/mm/yy') and party_id='GSPL'");
			if(rst.next()){
				count = Integer.parseInt(rst.getString(1));
			if(count > 0){
				status_contract_end = true;
			}
			else status_contract_end = false;
			}
			
			
			//			
			


  }
  
   private  void check_penom() throws SQLException{
			method_name = "check_penom()";
			
			rst = stmt.executeQuery("select count(*) from pe_nom where nom_dt=to_date('"+nom_dt+"','dd/mm/yy')");
			if(rst.next()){
			count = Integer.parseInt(rst.getString(1));
			if(count > 0){
			status = true;
			}
			else status = false;
			}
}

     private  void date_cmp() throws SQLException
     {
		//	method_name = "check_penom()";
			query="select (to_date('"+date_end+"','dd/mm/yy:hh24:mi') - to_date('"+date_start+"','dd/mm/yy:hh24:mi'))  from dual";
			//System.out.println("pe"+query);
			rst = stmt.executeQuery(query);
			
			if(rst.next()){
			day = rst.getString(1);
			}

    }

  private  void pm_balance() throws SQLException{
			String query="select pm_bal, "+
						 " to_char(pm_st_dt,'dd/mm/yy'),to_char(pm_end_dt,'dd/mm/yy'),mdq "+
						 " from sup_dtl  where party_id='"+party_id+"' and "+
						 " contract_year='"+contract_year+"'";
			
			rst = stmt.executeQuery(query);
           
			if(rst.next()){
				pm_bal=rst.getString(1);
				pm_st_dt=rst.getString(2);
				pm_end_dt=rst.getString(3);
				mdq=rst.getString(4);
			}
			
		   query="select pm_bal,to_char(pm_st_dt,'dd/mm/yy') ,to_char(pm_end_dt,'dd/mm/yy'),mdq "+
				" from trans_dtl  where "+
				" party_id='"+party_id+"' and "+
				" supp_id='"+sup_id+"' and  contract_year='"+contract_year+"'";
			rst = stmt.executeQuery(query);
			
			if(rst.next()){
			pm_bal=rst.getString(1);
			pm_st_dt=rst.getString(2);
			pm_end_dt=rst.getString(3);
			mdq=rst.getString(4);
			}
			if(!sup_id.equals("xxxx")){
			query="select pm_bal,to_char(pm_st_dt,'dd/mm/yy'),to_char(pm_end_dt,'dd/mm/yy'),mdq from estl_plan_mnt_dtl  where party_id='"+estlval+"' and "+
						 " supp_id='"+sup_id+"'and  contract_year='"+contract_year+"'"; }
			 else  {
			 query="select pm_bal,to_char(pm_st_dt,'dd/mm/yy') ,to_char(pm_end_dt,'dd/mm/yy'),mdq from estl_plan_mnt_dtl  where party_id='"+estlval+"' and "+
						 "   contract_year='"+contract_year+"'"; }


		     
			rst = stmt.executeQuery(query);
           
			if(rst.next()){
			pm_bal=rst.getString(1);
			pm_st_dt=rst.getString(2);
			pm_end_dt=rst.getString(3);
			mdq=rst.getString(4);
			}
 }

 public void setSup_id(String sup_id) {this.sup_id = sup_id;}
 public void setParty_id(String party_id) {this.party_id = party_id;}
 public void setContract_year(String contract_year) {this.contract_year = contract_year;}
 public void setNom_dt(String nom_dt) {this.nom_dt = nom_dt; }
 public void setData_for(String data_for) {this.data_for = data_for; }
 public void setestlval(String estlval) {this.estlval = estlval; }
 public void setDatestart(String date_start) {this.date_start = date_start; }
 public void setDateend(String date_end) {this.date_end = date_end; }

 
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
		   if( data_for != null)
		   {
				if(data_for.equals("penom"))
				{
				   check_penom();
				}
				if(data_for.equals("partynom"))
				{
				   check_partynom();
				}
				if(data_for.equals("planmnt"))
				{
				   pm_balance();
				}
				if(data_for.equals("datecmp"))
				{
				   date_cmp();
				}
				if(data_for.equalsIgnoreCase("findmod"))
				{
				   getModuleDetail();
				}
		   }
		   conn.close();
        }
      }
    }catch(Exception e) {
      //e.printStackTrace();
	  System.out.println(" Exception in LogicBean_Alerter "+e+" "+method_name);
    }
 }

 public void getModuleDetail()
 {
	 query="select module_name from "+
	 " sec_form_mst a,sec_module_mst b "+
	 " where a.module_cd=b.module_cd and a.form_cd='"+set_form_cd+"'";
	//System.out.println("here ruchi---"+query);
	 try
	{
	   rst=stmt.executeQuery(query);
	   if(rst.next())
	   {
		  module_name=rst.getString(1);
	   }
	}catch(Exception e){
		System.out.println("EXCEPTION:LogicBean_Alerter-->getModuleDetail()-->SEC_MODULE_MST: "+e);
	}
 }
 public String getNom_dt() { return nom_dt; }
 public String getPm_bal() { return pm_bal; }
 public String getPm_st_dt() { return pm_st_dt; }
 public String getPm_end_dt() { return pm_end_dt; }
 public String getData_for() { return data_for; }
 public int getCount() { return count; }
 public boolean getStatus(){ return status; }
 public boolean getStatus_contract(){ return status_contract; }
 public boolean getTktstat(){ return tktstat; }
 public String getDay(){ return day; }
 public String getMdq(){ return mdq; }
 public boolean getStatus_contract_end(){ return status_contract_end; }


public void setSet_form_cd(String set_form_cd) {
	this.set_form_cd = set_form_cd;
}


public String getModule_name() {
	return module_name;
}



}