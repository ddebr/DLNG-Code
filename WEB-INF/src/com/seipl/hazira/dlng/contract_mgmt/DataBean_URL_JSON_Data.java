package com.seipl.hazira.dlng.contract_mgmt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.servlet.http.HttpServletResponse;

import java.net.URL;
import java.net.URLConnection;
 
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.seipl.hazira.dlng.util.RuntimeConf;
import com.seipl.hazira.dlng.util.UtilBean;

@WebServlet("/servlet/DataBean_URL_JSON_Data")
public class DataBean_URL_JSON_Data extends HttpServlet{

	private static final long serialVersionUID = 1L;
	Connection conn = null;
	Statement stmt;
	
	UtilBean utilBean = new UtilBean();
	public Vector CM_ROOM_FY = new Vector();
	public Vector SUB_CAT_NM = new Vector();
	public Vector SUB_CAT_ID = new Vector();
	public Vector SUB_CAT_AGENT_ID = new Vector();
	
	public Vector nomQtyDate = new Vector(); 
	public Vector nomQtyTime = new Vector(); 
	public Vector nomQtyValue = new Vector(); 
	String urLRequest = "";
	
	
	@SuppressWarnings("unused")
	
	public void init(HttpServletRequest request,HttpServletResponse response) {
		
		try {
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
	    			
	    			
	    			getJSONDataFromURL(request,response);
	    			
	    		}
	    	}
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getJSONDataFromURL(HttpServletRequest request, HttpServletResponse response) throws UnknownHostException 
	{
		try
	    {
				try
			    {
			    			SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
							SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");

							//String api_url = "https://barodainformatics.com/RMS20/CheckLogin.php?option=getListData&Param={%22usercd%22:%22300001%22,%22menuId%22:%220%22,%22selectedDropId%22:%221%22,%22selectedStatus%22:%220%22,%22extraSpinnerId%22:%228%22,%22selectedCompId%22:%220%22}";
							
							//String api_url = "https://pejjbt-s-8082.asia-pac.shell.com/piwebapi/streamsets/end?webid=F1DPjFOakldGMUu0YR_7zapK7wf1cIAARFNBUFBJQ09MTFxIQVo6SEFaTE5HLlBWSS4xMDBMSTEwMi5QVg&webid=F1DPjFOakldGMUu0YR_7zapK7wg1cIAARFNBUFBJQ09MTFxIQVo6SEFaTE5HLlBWSS4xMDBMSTIwMi5QVg";
							
							
							String api_url = "https://barodainformatics.com/RMS20/CheckLogin.php?option=getListData&Param=%7b%22usercd%22:%22301419%22,%22menuId%22:%227%22,%22selectedDropId%22:%221%22,%22selectedStatus%22:%222%22,%22extraSpinnerId%22:%220%22,%22selectedCompId%22:%227%22%7d";
							
							
							System.out.println("Databean api_urls : "+api_url);	
							
							String inline = "";

							PrintWriter outs = response.getWriter();
							
				            TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
					                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					                    return null;
					                }
					                public void checkClientTrusted(X509Certificate[] certs, String authType) {
					                }
					                public void checkServerTrusted(X509Certificate[] certs, String authType) {
					                }
				                }
				            };

				            SSLContext sc = SSLContext.getInstance("SSL");
				            sc.init(null, trustAllCerts, new java.security.SecureRandom());
				            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
				           // sc.startHandshake();
				            
				           // Create all-trusting host name verifier
				            
				           HostnameVerifier allHostsValid = new HostnameVerifier() {
				                public boolean verify(String hostname, SSLSession session) {
				                    return true;
				                }
				           };
				            
				            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
				            URL url = new URL(api_url);
				            URLConnection con = url.openConnection();
				            
				            try(BufferedReader br = new BufferedReader(
									  new InputStreamReader(con.getInputStream(), "utf-8"))) {
									    StringBuilder res = new StringBuilder();
									    String responseLine = null;
									    while ((responseLine = br.readLine()) != null) {
									    	res.append(responseLine.trim());
									    }
									    inline = res.toString();
									    System.out.println("inline : "+inline);
							 }catch (Exception e) {
								 System.out.println("Exception : "+e.getMessage());
							}
				            
				            System.out.println("out of try inline : "+inline);
				     
						    JSONParser jsonParse = new JSONParser();
							JSONObject jsonobj = (JSONObject) jsonParse.parse(inline);
							
							JSONArray jArray = (JSONArray) jsonobj.get("subcategoryListReport");
							Iterator i = jArray.iterator();
				            while(i.hasNext())
				            {
				            	JSONObject jsonObj = (JSONObject) i.next();
				            	SUB_CAT_NM.add(jsonObj.get("sub_cat_nm"));
				            	SUB_CAT_ID.add(jsonObj.get("sub_cat_id"));
				            	SUB_CAT_AGENT_ID.add(jsonObj.get("sub_cat_agent_id"));
				            }
							System.out.println("Databean.....SUB_CAT_NM : "+SUB_CAT_NM.size()+"....."+SUB_CAT_NM);
							System.out.println("Databean.....SUB_CAT_ID : "+SUB_CAT_ID.size()+"....."+SUB_CAT_ID);
							System.out.println("Databean.....SUB_CAT_AGENT_ID : "+SUB_CAT_AGENT_ID.size()+"....."+SUB_CAT_AGENT_ID);
							
							/*String nomiQList = "";
				            JSONObject nominatedItemList = new JSONObject();
				            JSONArray ItemList = (JSONArray) jsonobj.get("Items");
				            
				            Iterator it = ItemList.iterator();
				            while(it.hasNext())
				            {
				            	JSONObject itemjsonObj = (JSONObject) it.next();
				            	JSONArray valueArray = (JSONArray) itemjsonObj.get("Value");
				            	
				            	Iterator vit = valueArray.iterator();
				            	while(vit.hasNext())
				            	{
				            		JSONObject valueObj = (JSONObject) vit.next();
				            		
				            		Date date2 = format2.parse(valueObj.get("Timestamp").toString().substring(0,10));
					            	String dt = format1.format(date2);//System.out.println("dt : "+dt);
					            	nominatedItemList.put("Date",dt);//.replace("\\\\", "")
					            	nominatedItemList.put("Time", valueObj.get("Timestamp").toString().substring(11,16));
					            	nominatedItemList.put("Value", valueObj.get("Value"));

					            	nomQtyDate.add(dt);
					            	nomQtyTime.add(valueObj.get("Timestamp").toString().substring(11,16));
					            	nomQtyValue.add(valueObj.get("Value"));
				            	}
				            }
						
				            System.out.println("nomQtyDate : "+nomQtyDate.size()+"....."+nomQtyDate);
				            System.out.println("nomQtyTime : "+nomQtyTime.size()+"....."+nomQtyTime);
				            System.out.println("nomQtyValue : "+nomQtyValue.size()+"....."+nomQtyValue);*/
						    conn.close();
			    			conn = null;
			    	
		 	    }catch(Exception e) {
		 	    	System.out.println("Exception In : (DataBean_URL_JSON_Data) - (init()): "+e.getMessage());
		 	    	e.printStackTrace();
		 	    }
				
				finally
			    {	
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
				
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	@SuppressWarnings("rawtypes")
	public Vector getCM_ROOM_FY() {
		return CM_ROOM_FY;
	}
	public Vector getSUB_CAT_NM() {
		return SUB_CAT_NM;
	}
	public Vector getSUB_CAT_ID() {
		return SUB_CAT_ID;
	}
	public Vector getSUB_CAT_AGENT_ID() {
		return SUB_CAT_AGENT_ID;
	}
	
	public Vector getNomQtyDate() {
		return nomQtyDate;
	}
	public Vector getNomQtyTime() {
		return nomQtyTime;
	}
	public Vector getNomQtyValue() {
		return nomQtyValue;
	}
	public void setURLRequest(String urLRequest) {
		this.urLRequest = urLRequest;
	}
}