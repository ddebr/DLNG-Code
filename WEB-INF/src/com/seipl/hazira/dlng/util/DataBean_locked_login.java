package com.seipl.hazira.dlng.util;
import javax.servlet.http.*;
import java.io.*;
import javax.servlet.*;
import java.sql.*;
import java.util.*;
import java.util.Date.*;
import java.text.*;
import java.awt.event.*;
import javax.naming.*;
import javax.sql.*;
 
/*	Any Controller using this bean must set following fields as initialization parameter.
		-- invoice_type
		-- mmyy
*/

public class DataBean_locked_login{

    Connection conn;
	Statement stmt;
	ResultSet rset;


	public String queryString;
	public String queryString1;
	public String queryString2;
	public String queryString3;
	public String method_name;
	public String loginname=null;
	public int i=0;
	public int cnt=0;
	public String msg = "";

  public void clear_variables(){

         queryString="";  
         queryString1="";  
         queryString2="";  
         queryString3="";  
         method_name="";  
}

  	public void setLogin(String login){this.loginname = login;}
  	public void setMsg(String msg){this.msg = msg;}

  	public void loadDefaultValues() throws SQLException
  	{
        try
        {
            if(!msg.equalsIgnoreCase("In-Active User Not Permitted to Log-In into the System !!!"))
            {
            	queryString = "select count(*)  from sec_emp_passwords where emp_cd=" +
            		" ( select emp_cd from hr_emp_mst where emp_cd = '"+loginname+"')  ";
            	System.out.println("QRY0026 :SELECT :SEC_EMP_PASSWORDS:DataBean_locked_login.java : " +queryString);
            	rset = stmt.executeQuery(queryString);
                if(rset.next())
                {
                	i=Integer.parseInt(rset.getString(1));
                }
				if(i>0)
				{
					queryString = "select emp_cd from hr_emp_mst where emp_cd = '"+loginname+"'";
					System.out.println("QRY0027 :SELECT :HR_EMP_MST:DataBean_locked_login.java : " +queryString);
					rset = stmt.executeQuery(queryString);
					int empcd = 0;
					if(rset.next())
					{
						empcd = rset.getInt(1);
						
					}
					queryString = "update sec_emp_passwords set locked_flag='Y' where emp_cd='"+empcd+"' ";
					System.out.println("QRY0028 :UPDATE :SEC_EMP_PASSWORDS:DataBean_locked_login.java : " +queryString);
					stmt.executeUpdate(queryString);
                }
            }
        }
        catch(Exception e)
        {
        	System.out.println("Exception in the loadDefaultvalues() Method of DataBean_locked_login..."+e);
        }
	}//end of method
  	

 public void init() {
    try{

	Context initContext = new InitialContext();
      if(initContext == null ) 
          throw new Exception("Boom - No Context");
	  Context envContext  = (Context)initContext.lookup("java:/comp/env");
	  DataSource ds = (DataSource)envContext.lookup(RuntimeConf.security_database);
      if (ds != null) {
        conn = ds.getConnection();       

		//conn = new DBMSConnection().getConnection(Globals.USER,Globals.PASSWD);
		 if(conn != null)  {
			stmt = conn.createStatement();
				loadDefaultValues();
				conn.close();
			}
	 }
    }catch(Exception e) {
     	  System.out.println(" DataBean_locked_login in "+method_name+" \n Exception occured "+e);
    }
}
}//END OF BEAN



