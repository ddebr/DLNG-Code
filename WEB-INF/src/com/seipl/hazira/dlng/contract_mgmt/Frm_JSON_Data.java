package com.seipl.hazira.dlng.contract_mgmt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.net.ssl.HttpsURLConnection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


import com.google.gson.Gson;
import com.seipl.hazira.dlng.util.RuntimeConf;

@WebServlet("/servlet/Frm_JSON_Data")
public class Frm_JSON_Data extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	Connection conn = null;
	String url = "";
	
	public Vector CM_ROOM_FY = new Vector(); 
	
	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.print("I'm in do Post......"+"\n");
		
		try {
			Context intialCont = new InitialContext();
			if(intialCont == null) {
				throw new Exception("Boom - No Context");
			}
			
			Context envCont = (Context) intialCont.lookup("java:/comp/env");
			DataSource ds = (DataSource) envCont.lookup(RuntimeConf.security_database);
			if(ds != null)
			{
				conn = ds.getConnection();
				if(conn != null) 
				{
					SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
					SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
					
					String requestUrl = request.getRequestURI();
					//String name = requestUrl.substring("/people/".length());
					
					System.out.println("requestUrl : "+requestUrl);

					StringBuffer urls = request.getRequestURL();
					String uri = request.getRequestURI();
					String ctx = request.getContextPath();
					String base = urls.substring(0, urls.length() - uri.length() + ctx.length()) + "/";
					
					System.out.println("urls : "+urls);
					System.out.println("uri : "+uri);
					System.out.println("ctx : "+ctx);
					System.out.println("base : "+base);
					
					String base_url = request.getScheme() + "://" + request.getServerName() +request.getContextPath();
					System.out.println("base_url : "+base_url);
					
					//String api_url = "https://barodainformatics.com/RMS20/CheckLogin.php?option=getListData&Param={%22usercd%22:%22300001%22,%22menuId%22:%220%22,%22selectedDropId%22:%221%22,%22selectedStatus%22:%220%22,%22extraSpinnerId%22:%228%22,%22selectedCompId%22:%220%22}";
					String api_url = "http://billpower.in/BP10/CheckLogin.php?option=getListData&Param={%22usercd%22:%22300001%22,%22menuId%22:%220%22,%22selectedDropId%22:%221%22,%22selectedStatus%22:%220%22,%22extraSpinnerId%22:%228%22,%22selectedCompId%22:%220%22}";
					
					System.out.println("api_urls : "+api_url);			
					
					String inline = "";

					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");
					PrintWriter out = response.getWriter();
		            
		            URL parseUrls = new URL(api_url);
					
					Scanner sc = new Scanner(parseUrls.openStream());
					while(sc.hasNext())
					{
						inline+=sc.nextLine();
					}
					System.out.println("inline : "+inline);
		            
					sc.close();
					JSONParser jsonParse =  new JSONParser();
					JSONObject jsonobj = (JSONObject) jsonParse.parse(inline);
					
																																		JSONObject categoryData = new JSONObject();
					JSONArray jArray = (JSONArray) jsonobj.get("categoryReportData");
					Iterator i = jArray.iterator();
		            while(i.hasNext())
		            {
		            	JSONObject jsonObj = (JSONObject) i.next();
		            	categoryData.put("CM_ROOM_FY", jsonObj.get("CM_ROOM_FY"));
		            	
		            	CM_ROOM_FY.add(jsonObj.get("CM_ROOM_FY"));
		            	
		            }
		            
		            String j = new Gson().toJson(categoryData);
					out.write(j);
					
					System.out.println("CM_ROOM_FY : "+CM_ROOM_FY.size()+"....."+CM_ROOM_FY);
		            System.out.println("jArrr : "+jArray);
		            System.out.println("categoryData : "+categoryData);
					
					
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

			            	System.out.println("nominatedItemList : "+nominatedItemList);
			            	
			            	nomiQList = "Nomination {" + "Date=" + dt + ", Time="
			            								+ valueObj.get("Timestamp").toString().substring(11,19) + ", Value=" + valueObj.get("Value") + '}';
		            	}
		            }
		            
		            String j = new Gson().toJson(nominatedItemList);
					out.write(j);
		            System.out.println("nomiQList : "+nomiQList);*/
					
		            
		            /*--------All For Open HTTP Connection ------ */  
					
					/*URL parseUrl = new URL(api_url); 
					HttpsURLConnection httpconn = (HttpsURLConnection)parseUrl.openConnection();
					httpconn.setRequestMethod("GET");
					int responsecode = httpconn.getResponseCode();*/
					
					/*try 
					{
						URL parseUrl = new URL(api_url);
			            HttpURLConnection httpcon = (HttpURLConnection) parseUrl.openConnection();
			            httpcon.addRequestProperty("User-Agent", "Mozilla/4.76");
			            InputStreamReader isreader = new InputStreamReader(httpcon.getInputStream());
			            System.out.println("isreader : "+isreader);
					} catch (IOException ex) {
			            ex.printStackTrace();
			        }*/
	
		            /*try (InputStream is = new URL(api_url).openStream();
		                    Reader readers = new InputStreamReader(is, StandardCharsets .UTF_8)) {
		                Gson gsons = new Gson();
		                System.out.println("readers : "+readers);
		            }*/
		            
					
		            	

					url = "../master/frm_mst_truckLoading.jsp";
					System.out.println("url : "+url);
					conn.close();
					conn = null;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
	    {    	
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
			/*RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			try {
				dispatcher.forward(request, response);
			} catch (ServletException e) {
				System.out.println("ServletException..."+e);
			}catch (IOException e) {
				System.out.println("IOException..."+e);
			}*/
		
	}
}