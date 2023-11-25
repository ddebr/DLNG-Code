package com.seipl.hazira.dlng.contract_mgmt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;

@WebServlet("/Frm_Fetch_MT_GCV")
public class Frm_Fetch_MT_GCV extends HttpServlet {
	private String flag = "";
	private String msg = "";
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try {
			
				String option = request.getParameter("option")==null?"":request.getParameter("option");
				flag = request.getParameter("flag")==null?"":request.getParameter("flag");
//				System.out.println("option----"+option+"***flag***"+flag);
				
				if(option.equalsIgnoreCase("fetchPIValues")) {
					JSONObject PIvalJson =  fetchPIValues(request);
					String PIVal = new Gson().toJson(PIvalJson);
					response.getWriter().write(PIVal);
				}		
			}catch (Exception e) {
				e.printStackTrace();
			}
		}

	private JSONObject fetchPIValues(HttpServletRequest request) {
		
		String Value="0";
		JSONObject json = new JSONObject();
		msg = "";
		try {
			if(flag.equalsIgnoreCase("fetchGCV")) {
				
				String[] commands = {"curl", "-k", "-L",  "--negotiate", "-u", "s_inf087" + ":" + "rV6tR2jT", "-X", "GET", "https://pejjbt-s-8082.asia-pac.shell.com/piwebapi/streamsets/end?webid=F1DPjFOakldGMUu0YR_7zapK7wonwJAARFNBUFBJQ09MTFxIQVo6QkFZMUdDVjIuUFY"};
				
				Process process; 
				process = Runtime.getRuntime().exec(commands);
				BufferedReader reader2 = new BufferedReader(new InputStreamReader(process.getInputStream()));
//				System.out.print("DLNG Response : "+reader2.toString()); 
				StringBuilder builder = new StringBuilder();
				String line2 = null;
				String response = null;
		
				  while ((line2 = reader2.readLine()) != null) { 
					  builder.append(line2);
					  builder.append(System.getProperty("line2.separator"));
					 }
				 
				response = builder.toString();
//				response="{\"Links\":{},\"Items\":[{\"WebId\":\"F1DPjFOakldGMUu0YR_7zapK7wKlAJAARFNBUFBJQ09MTFxIQVo6RFBSLkxORy5DTE9TSU5HU1RPQ0s\",\"Name\":\"HAZ:DPR.LNG.Closingstock\",\"Path\":\"\\\\DSAPPICOLL\\HAZ:DPR.LNG.Closingstock\",\"Links\":{\"Source\":\"https://pejjbt-s-8082.asia-pac.shell.com/piwebapi/points/F1DPjFOakldGMUu0YR_7zapK7wKlAJAARFNBUFBJQ09MTFxIQVo6RFBSLkxORy5DTE9TSU5HU1RPQ0s\"},\"Value\":{\"Timestamp\":\"2021-06-21T00:40:00Z\",\"Value\":111624.141,\"UnitsAbbreviation\":\"\",\"Good\":true,\"Questionable\":false,\"Substituted\":false,\"Annotated\":false}}]}";
				response=response.replace("null", "");
				System.out.print("DLNG Response : "+response);
				JSONParser parse = new JSONParser(); 
				JSONObject jobj = (JSONObject)parse.parse(response);
				System.out.println("\nDLNG JSON Parse :"+ jobj);
				JSONArray jsonarr_1 = (JSONArray) jobj.get("Items");
				JSONObject gcvItemList = new JSONObject();
				Iterator ii = jsonarr_1.iterator();
				String jsonvalue="";
				while(ii.hasNext())
				{
					JSONObject jsonObj = (JSONObject) ii.next();
					jsonvalue = gcvItemList.toJSONString((Map) jsonObj.get("Value")).toString();
					System.out.println("DLNG--"+jsonvalue);
				}
				
				if(!jsonvalue.equals(""))
				{
					String split[] = jsonvalue.split(",");
					System.out.println("DLNG--"+split.length);
					int i=0;
					for(i=0; i < split.length; i++)
					{
						String v = split[i];
						String v1=v.replaceAll("\"", "");
						//System.out.println(v1);
						String split2[] = v1.split(":");
						if(split2[0].trim().equals("Value"))
						{
							Value=split2[1].trim();
						}
					}
				}
				System.out.println("Value : "+Value);
				
				
			}else if(flag.equalsIgnoreCase("fetchMT")) {
				
				String[] commands = {"curl", "-k", "-L",  "--negotiate", "-u", "s_inf087" + ":" + "rV6tR2jT", "-X", "GET", "https://pejjbt-s-8082.asia-pac.shell.com/piwebapi/streamsets/end?webid=F1DPjFOakldGMUu0YR_7zapK7wl3wJAARFNBUFBJQ09MTFxIQVo6QkFZMVFMT0FERUQuUFY"};
				
				Process process;
				process = Runtime.getRuntime().exec(commands);
				BufferedReader reader2 = new BufferedReader(new InputStreamReader(process.getInputStream()));
				System.out.println("reader2---"+reader2.toString().length());
				StringBuilder builder = new StringBuilder();
				String line2 = null;
				String response = null;
				while ((line2 = reader2.readLine()) != null) {
				    builder.append(line2);
				    builder.append(System.getProperty("line2.separator"));
				    System.out.println("line2---"+line2);
				}
				response = builder.toString();
//				response="{\"Links\":{},\"Items\":[{\"WebId\":\"F1DPjFOakldGMUu0YR_7zapK7wKlAJAARFNBUFBJQ09MTFxIQVo6RFBSLkxORy5DTE9TSU5HU1RPQ0s\",\"Name\":\"HAZ:DPR.LNG.Closingstock\",\"Path\":\"\\\\DSAPPICOLL\\HAZ:DPR.LNG.Closingstock\",\"Links\":{\"Source\":\"https://pejjbt-s-8082.asia-pac.shell.com/piwebapi/points/F1DPjFOakldGMUu0YR_7zapK7wKlAJAARFNBUFBJQ09MTFxIQVo6RFBSLkxORy5DTE9TSU5HU1RPQ0s\"},\"Value\":{\"Timestamp\":\"2021-06-21T00:40:00Z\",\"Value\":111624.141,\"UnitsAbbreviation\":\"\",\"Good\":true,\"Questionable\":false,\"Substituted\":false,\"Annotated\":false}}]}";
//				response="{\"Links\":{},\"Items\":[{\"WebId\":\"F1DPjFOakldGMUu0YR_7zapK7wl3wJAARFNBUFBJQ09MTFxIQVo6QkFZMVFMT0FERUQuUFY\",\"Name\":\"HAZ:Bay1QLoaded.PV\",\"Path\":\"\\\\DSAPPICOLL\\HAZ:Bay1QLoaded.PV\",\"Links\":{\"Source\":\"https://pejjbt-s-8082.asia-pac.shell.com/piwebapi/points/F1DPjFOakldGMUu0YR_7zapK7wl3wJAARFNBUFBJQ09MTFxIQVo6QkFZMVFMT0FERUQuUFY\"},\"Value\":{\"Timestamp\":\"2022-03-19T08:34:00.9700012Z\",\"Value\":16.14,\"UnitsAbbreviation\":\"\",\"Good\":true,\"Questionable\":false,\"Substituted\":false,\"Annotated\":false}}]}";
				System.out.print("DLNG fetchMT Response : "+response);
				response=response.replace("null", "");
				JSONParser parse = new JSONParser(); 
				JSONObject jobj = (JSONObject)parse.parse(response);
				System.out.println("\nDLNG fetchMT JSON Parse :"+ jobj);
				JSONArray jsonarr_1 = (JSONArray) jobj.get("Items");
				JSONObject mtItemList = new JSONObject();
				Iterator ii = jsonarr_1.iterator();
				String jsonvalue="";
				while(ii.hasNext())
				{
					JSONObject jsonObj = (JSONObject) ii.next();
					jsonvalue = mtItemList.toJSONString((Map) jsonObj.get("Value")).toString();
					System.out.println("DLNG fetchMT--"+jsonvalue);
				}
				
				String Timestamp="";
				if(!jsonvalue.equals(""))
				{
					String split[] = jsonvalue.split(",");
					System.out.println("DLNG fetchMT--"+split.length);
					int i=0;
					for(i=0; i < split.length; i++)
					{
						String v = split[i];
						String v1=v.replaceAll("\"", "");
						//System.out.println(v1);
						String split2[] = v1.split(":");
						if(split2[0].trim().equals("Value"))
						{
							Value=split2[1].trim();
						}
					}
				}
				System.out.println("Value fetchMT: "+Value);
			}
		}catch (Exception e) {
			e.printStackTrace();
			msg = "Error: Failed to fetch MT/GCV value from PI";
		}
		json.put("flag", flag);
		json.put("value", Value);
		json.put("msg", msg);
		
		return json;
	}

}
