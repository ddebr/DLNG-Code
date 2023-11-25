//SB20091209:SCR10001
package com.seipl.hazira.dlng.util;
import javax.naming.*;
import javax.sql.*;
import java.sql.*;

import com.seipl.hazira.dlng.util.RuntimeConf;


public class DataBean_modulelock{
	Connection conn;
	Statement stmt;
	ResultSet rset;

	public String queryString;
	public String method_name;
	public String uname="";
	public String empId;
	public String formId;
	public String login_date_time;
	public String call_from;	
	public String exist_empid,sessionId,exist_sessionId;
    public String set_username="";
	public int count_exist;
	public String emp_cd="";
	public boolean modLock=false;
	
	public void clear(){
		queryString="";    
		method_name="";    
		empId="";          
		formId="";         
		login_date_time="";
		count_exist=0; call_from=""; exist_empid="";sessionId="";
		exist_sessionId="";
	}
    //SB20091209: Function-1
	public void getEmpCd() throws SQLException
    {
    	try
    	{
    	  //set_username=set_username.trim();	
   	      queryString="SELECT EMP_CD FROM HR_EMP_MST WHERE EMP_NM='"+set_username+"'";
//   	   System.out.println("QRY0001 :SELECT :HR_EMP_MST:DataBean_modulelock.java : " +queryString);
    	  rset=stmt.executeQuery(queryString);
    	  if(rset.next())
    	  {
    		  emp_cd=rset.getString(1);
    	  }
    	}
    	catch(Exception e)
    	{
    		System.out.println("EXCEPTION:DataBean_modulelock-->getEmpCd-->HR_EMP_MST: "+e);
    	}
    }
    
	public void checkData() throws SQLException{
		String dttime="";
		try
		{
			//queryString="delete from sec_module_lock where emp_cd='"+emp_cd+"' and form_cd <> '"+formId+"'";
			queryString="delete from sec_module_lock where emp_cd='"+emp_cd+"'";
			//System.out.println("Query String : " + queryString );
			stmt.executeUpdate(queryString);
		}catch(Exception e){
			System.out.println("EXCEPTION: DataBean_modulelock-->checkData()-->sec_module_lock: "+e);
		}
		
		
		try
		{
			queryString ="select count(*) from sec_module_lock where form_cd='"+formId+"' and emp_cd <> '"+emp_cd+"'";
			//System.out.println("QueryString : " + queryString );
			rset = stmt.executeQuery(queryString);
			if(rset.next())
				count_exist = rset.getInt(1);
			
			queryString="select to_char(sysdate,'dd/mm/yy hh24:mi:ss') from dual";
			rset=stmt.executeQuery(queryString);
			if(rset.next())
			{
				dttime=rset.getString(1);
			}
		}catch(Exception e){
			System.out.println("EXCEPTION: DataBean_modulelock-->checkData()-->sec_module_lock-dual: "+e);
		}
		
		try
		{
			if(count_exist == 0){
				queryString = "insert into sec_module_lock(emp_cd,form_cd,lock_in,sess_id) values "+
								"('"+emp_cd+"','"+formId+"',to_date('"+dttime+"','dd/mm/yy hh24:mi:ss'),'"+sessionId+"')";
				//System.out.println("Query Lock : " + queryString);
				stmt.executeUpdate(queryString);
			}
		}catch(Exception e){
			System.out.println(" "+e);
		}
		if(count_exist > 0){
			try
			{
			queryString = "select a.emp_cd ,sess_id,b.emp_nm from sec_module_lock a,hr_emp_mst b where" +
					" form_cd='"+formId+"' and a.emp_cd=b.emp_cd" ;

			//System.out.println("QueryString : " +queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next()){
				exist_empid = rset.getString(1);
				exist_sessionId = rset.getString(2);
				uname=rset.getString(3);
		}
		modLock=true;
			}catch(Exception e){
				System.out.println("EXCEPTION:DataBean_modulelock-->checkData()-->SEC_MODULE_LOCK: "+e);;
		}
	 }
   }
	
	public void deleteData() throws SQLException{
		try
		{
			queryString ="Select count(*) from module_lock where form_id='"+formId+"' ";
			rset = stmt.executeQuery(queryString);
			if(rset.next())
				count_exist = rset.getInt(1);
			if(count_exist > 0){
				queryString="delete from module_lock where form_id='"+formId+"' and empid='"+empId+"'";
				stmt.executeUpdate(queryString);
			}
		}catch(Exception e){
			System.out.println("Exception: "+e);
		}
	}
//	SB20091209: Function-2
	public void deleteModuleLock() throws SQLException{
		try
		{
			queryString ="Select count(*) from sec_module_lock where emp_cd='"+emp_cd+"'";
			//System.out.println("QRY0002 :SELECT :SEC_MODULE_lOCK:DataBean_modulelock.java : ");
			rset = stmt.executeQuery(queryString);
			if(rset.next())
				count_exist = rset.getInt(1);
			
			if(count_exist > 0){
					queryString="delete from sec_module_lock where emp_cd='"+emp_cd+"'";
					//System.out.println("QRY0003 :DELETE :SEC_MODULE_lOCK:DataBean_modulelock.java : ");
					stmt.executeUpdate(queryString);
			}
		}catch(Exception e){
//			System.out.println("Exception: "+e);
			e.printStackTrace();
		}
	}

	public void deleteSession() throws SQLException
	{
		queryString ="delete from module_lock  where empid='"+empId+"' and sess_id='"+sessionId+"'";
	    stmt.executeUpdate(queryString);
	}

	 public void init() 
	 {
	    try{
			try{
			Context initContext = new InitialContext();
		    if(initContext == null ) 
				  throw new Exception("Boom - No Context");
		    Context envContext  = (Context)initContext.lookup("java:/comp/env");
//SB20091221			DataSource ds = (DataSource)envContext.lookup(RuntimeConf.invoice_database);
			DataSource ds = (DataSource)envContext.lookup(RuntimeConf.security_database);
		    if (ds != null) {
				 conn = ds.getConnection();       
			}else {
			  System.out.println("Data Source Not Found - Invoice Module Error");
			}
		}catch(Exception e){
				System.out.println("Exception ..."+e);
		}
		 if(conn != null)  {
			stmt = conn.createStatement();

		 	 getEmpCd();
		 	 if(call_from.equalsIgnoreCase("start") )
		 	 {
		 		deleteModuleLock();
		 	 }
		 	 if(call_from.equalsIgnoreCase("finish"))
		 	 {
		 		deleteModuleLock();
		 	 }
		 	 if(call_from.equals("other")){
				checkData();
		     }
			 conn.close();
		 }

	        }catch(Exception e) {
 		      System.out.println(" DataBean_modulelock in "+method_name+" \n Exception occured "+e);
		  	 if(conn != null){
			 	 try{
					stmt = conn.createStatement();
					conn.close();
				  }catch(Exception e1){
					System.out.println("Exception whilst closing the connection in the DataBean_modulelock..."+e1);
				 }
			   }
	     }finally{
	    	 try
	    	 {
	    	 if(conn != null){
	    		 conn.close();
	    	 }
	    	 }catch(Exception e1){
					//System.out.println("Closing Connection: "+e1);
			 }
	     }
   	  }//END OF INIT()

public void setEmpId(String e){this.empId = e;}
public void setFormId(String f){this.formId = f;}
public void setLockIn(String l){this.login_date_time = l;}
public void setCallFrom(String str){this.call_from = str;}
public void setSessionId(String sid){this.sessionId = sid;}

public int getCounterExist(){return count_exist;}
public String getEmpId(){return empId;}
public String getFormId(){return formId;}
public String getLockIn(){return login_date_time;}
public String getExistEmpid(){return exist_empid;}
public String getExistSessionId(){return exist_sessionId;}

//public void setUsername(String username) {
	//this.username = username;
//}

public void setSet_username(String set_username) {
	this.set_username = set_username;
}
public String getUname() {
	return uname;
}
public boolean isModLock() {
	return modLock;
}
}