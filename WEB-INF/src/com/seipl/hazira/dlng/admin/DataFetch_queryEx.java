package com.seipl.hazira.dlng.admin;
import java.lang.*;
import java.util.*;
import java.text.*;

//import com.basic.random;
//import com.basic.util.*;
import javax.naming.*;
import javax.sql.*;
import java.sql.*;

import java.io.*;
import com.seipl.hazira.dlng.util.RuntimeConf;
import com.seipl.hazira.dlng.util.escapeSingleQuotes;

public class DataFetch_queryEx
{
	Connection conn=null;
	Statement stmt=null,stmt2=null;
	ResultSet rset=null;
	ResultSetMetaData rsmd=null;
	
	NumberFormat nf=NumberFormat.getCurrencyInstance(Locale.US);
	escapeSingleQuotes obj = new  escapeSingleQuotes();
	
	NumberFormat nf_perc=new DecimalFormat("##0.00");
	String queryStatement="";
	String op_cd="",error=""; String ResultStatus="";
	int totalRows=0;
	HashMap HashTable=new HashMap();
	HashMap HeaderHashTable=new HashMap();
	
	
	public void Query()
	{
		try
		{
//			System.out.println("Qry-Statement Execution Started......>>>>>>> ");
		
			String shareStrTemp[] =queryStatement.split(" ");     
			String msg="";
			ResultStatus="";
        	if(shareStrTemp[0].toUpperCase().equals("SELECT"))
        	{			
				rset=stmt.executeQuery(queryStatement);
				rsmd = rset.getMetaData();
				int columns=rsmd.getColumnCount();
				
				int row=1;
				for(int i=1;i<=columns;i++) {
						HeaderHashTable.put(i,rsmd.getColumnName(i).toUpperCase());
				}
				rsmd=null;
				while(rset.next()) {
					HashMap SubHashTable=new HashMap();
					for(int i=1;i<=columns;i++) {
						SubHashTable.put(i,rset.getString(i)==null?"NULL":rset.getString(i));
						HashTable.put(row,SubHashTable);
					}
					row++;
				}
				totalRows=row-1;
				msg="	Query: "+queryStatement+"<BR>	Number of Rows/Results: "+totalRows;
//				System.out.println(msg);
        	}
        	else  ///UPDATE & INSERT Query
        	{
        		stmt.executeUpdate(queryStatement);	
        			totalRows++;	
        		if(shareStrTemp[0].toUpperCase().equals("UPDATE"))
        			msg="	Query: "+queryStatement+"<BR>	Number of Rows/Results: "+totalRows+ "  UPDATED";
        		else if(shareStrTemp[0].toUpperCase().equals("INSERT"))
        			msg="	Query: "+queryStatement+"<BR>	Number of Rows/Results: "+totalRows+ "  INSERTED";
        		else if(shareStrTemp[0].toUpperCase().equals("DELETE"))
        			msg="	Query: "+queryStatement+"<BR><FONT COLOR=RED>Number of Rows/Results: "+totalRows+ "  DELETED</FONT>";
//        			System.out.println(msg); 	
        	}
        	ResultStatus=""+msg;
//        	System.out.println("Qry-Statement Execution Completed......<<<<<<< ");
		}
		catch(Exception e)
		{
			error=e.getLocalizedMessage();
			e.printStackTrace();
		}
	}
	
	public void init()
	{
		try
		{
			Context initContext = new InitialContext();
			if(initContext == null ) throw new Exception("Boom - No Context");
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			//Context envContext  = (Context)initContext.lookup("");
			DataSource ds = (DataSource)envContext.lookup(RuntimeConf.security_database);
			
			if(ds != null) 
			{
				conn = ds.getConnection();  
					
				if(conn != null) 
				{
						stmt = conn.createStatement();
						stmt2 = conn.createStatement();
						if(op_cd.equals("1")) {
							if(!queryStatement.trim().equals(""))
								Query();
						}
						if(rset != null)
						{ 
							try 
							{
								rset.close(); 
							}
							catch (SQLException e)
							{
								System.out.println("Exception in DataFetch_Bean  "+e);
							}
							rset = null;
						}
						if(stmt != null)
						{ 
							try
							{
								stmt.close();
							} 
							catch (SQLException e) 
							{
								System.out.println("Exception in DataFetch_Bean  "+e);
							}
							stmt = null;
						}
						if(stmt2 != null)
						{ 
							try
							{
								stmt2.close();
							} 
							catch (SQLException e) 
							{
								System.out.println("Exception in DataFetch_Bean  "+e);
							}
							stmt2 = null;
						}
						conn.close();
						conn =null;
					}
			  }
		}
		catch(Exception e) 
		{
			//conn.close();
			System.out.println("DataFetch_Bean  \n Exception occured "+e);
		}
		finally
		{
			if(rset != null) 
			{ 
				try 
				{
					rset.close(); 
				}
				catch (SQLException e) 
				{ 
					System.out.println("Exception in DataFetch_Bean  "+e);
				}
				rset = null;
			}
			if(stmt != null) 
			{ 
				try
				{
					stmt.close(); 
				} 
				catch (SQLException e) 
				{
					System.out.println("Exception in DataFetch_Bean  "+e);
				}
				stmt = null;
			}
			if(stmt2 != null) 
			{ 
				try
				{
					stmt2.close(); 
				} 
				catch (SQLException e) 
				{
					System.out.println("Exception in DataFetch_Bean  "+e);
				}
				stmt2 = null;
			}
			if(conn != null) 
			{ 
				try 
				{
					conn.close(); 
				}
				catch (SQLException e) 
				{
					System.out.println("Exception in DataFetch_Bean  "+e);
				}
				conn = null;
			}
		}
	}



	public String getQueryStatement() {
		return queryStatement;
	}



	public void setQueryStatement(String queryStatement) {
		this.queryStatement = queryStatement;
	}



	public String getOp_cd() {
		return op_cd;
	}



	public void setOp_cd(String op_cd) {
		this.op_cd = op_cd;
	}



	public HashMap getHashTable() {
		return HashTable;
	}



	public int getTotalRows() {
		return totalRows;
	}



	public HashMap getHeaderHashTable() {
		return HeaderHashTable;
	}


	
	public String getError() {
		return error;
	}
	public String getResultStatus() {return ResultStatus;}
}//End Of Class DataBean_Fetch.java
