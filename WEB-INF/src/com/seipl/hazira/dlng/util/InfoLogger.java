//Samik Shah 20100704:
package com.seipl.hazira.dlng.util;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;

import javax.naming.*;
import javax.sql.*;
import java.util.*;
import java.sql.*;
import com.seipl.hazira.dlng.util.escapeSingleQuotes;

import com.seipl.hazira.dlng.util.RuntimeConf;

public class InfoLogger
{
    Connection conn; 
	Statement stmt;
	ResultSet rset;
	String queryString="";
	String queryString1="";
	
 	private PrintWriter writer = null;
 	
 	public static escapeSingleQuotes escObj = new escapeSingleQuotes();
    
	public void writelog(String msg)
	{
		//System.out.println("write log method in Infologger called");
		Timestamp ts = new Timestamp(System.currentTimeMillis());
        String tsString = ts.toString().substring(0, 19);
        String tsDate = tsString.substring(0, 10);
        try
        {
			Context initContext = new InitialContext();
		    if(initContext == null) 
		          throw new Exception("Boom - No Context");
			
		    Context envContext  = (Context)initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource)envContext.lookup(RuntimeConf.security_database);
		    if(ds != null)
		    {
		    	conn = ds.getConnection();       
		        if(conn != null)
		        {
		           stmt = conn.createStatement();
		        }
		    }
            
		    synchronized(this)
		    {
				open(tsDate);
				String time = "";
				String uid = "";
				String mid="";
				String rem="";
				
				time = tsString.substring(11, 19);
				int p1 = msg.indexOf('/');
				uid = msg.substring(1,p1);
				
				int p2 = msg.indexOf(']');
				mid = msg.substring(p1+1, p2);
				
				//Following (6) Lines of Coding Has Been Introduced By Samik Shah On 29th January, 2011 ...
				String form_cd = "0";
				String form_name = "";
				int p3 = msg.indexOf('@');
				int p4 = msg.indexOf('~');
				form_cd = msg.substring(p2+3, p3);
				form_name = msg.substring(p3+1, p4);
				//End of (6) Coding Lines ...
				
//				System.out.println("form_cd = "+form_cd+",  form_name = "+form_name);
				
				rem = msg.substring(p4+3);
				String tuid=uid.trim();
				
				rem = escObj.replaceSingleQuotes(rem);
				tuid = escObj.replaceSingleQuotes(tuid);
				
				String emp_cd = "0";
				
				queryString = "SELECT EMP_CD FROM HR_EMP_MST WHERE EMP_NM='"+tuid+"'";
//				System.out.println("INFO-QRY0001 :SELECT :HR_EMP_MST : InfoLogger.java : "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					emp_cd = rset.getString(1);
				}
				
				queryString = "insert into SEC_LOG_DETAILS(LOG_DT, LOG_TIME, LOG_UID, LOG_MACH_ID, REMARKS, EMP_CD, FORM_CD, FORM_NAME) " +
							  "values(to_date('"+tsDate+"','yyyy-mm-dd'), '"+time+"', '"+uid+"', '"+mid+"', '"+rem+"', '"+emp_cd+"', '"+form_cd+"', '"+form_name+"')";
//				System.out.println("INFO-QRY0002 : SELECT : SEC_LOG_DETAILS : InfoLogger.java : "+queryString);
				stmt.executeUpdate(queryString);
				
//                writer.println(tsString+" "+msg);
				close();
			}
        }
        catch(Exception e)
        {
//        	System.out.println("Exception In InfoLogger --> writelog() Method --> While Writing Into SEC_LOG_DETAILS Table :\n"+e.getMessage());
        	e.printStackTrace();
        }
        finally
        {
	    	 try
	    	 {
	    		 if(conn != null)
	    		 {
	    			 conn.close();
	    		 }
	    		 if(stmt != null)
	    		 {
	    			 stmt.close();
	    		 }
	    		 if(rset != null)
	    		 {
	    			 rset.close();
	    		 }
	    	 }
	    	 catch(Exception e1)
	    	 {
//				System.out.println("Exception While Closing Connection Under InfoLogger Servlet :\n"+e1.getMessage());
				e1.printStackTrace();
			 }
        }
    }
    
	private void close()
	{
        if(writer == null)
			return;
        
        writer.flush();
        writer.close();
        writer = null;
    }
    
	private void open(String date)
	{
        //Create the directory if necessary
        File dir = new File("InfoLogs");
        dir.mkdirs();
        //Open the  log file
        try
        {
            String pathname = dir.getAbsolutePath() + File.separator + "DLNG." + date + ".log";
			//System.out.println(pathname);
            writer = new PrintWriter(new FileWriter(pathname,true), true);
        }
        catch(IOException e)
        {
//			System.out.println("EXCEPTION:InfoLogger: "+e);
            writer = null;
        }
    }

	
}// End Of Class InfoLogger ...