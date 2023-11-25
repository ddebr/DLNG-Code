package com.seipl.hazira.dlng.contract_mgmt;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DB_TankInventory_Automation {

	public static void main(String[] args) {
		
		TankInventory_Automation ta = new TankInventory_Automation();
		ta.getmail_list();
		ta.init();
	}
}

class TankInventory_Automation
{
	Connection conn;
    Statement stmt,stmt1,stmt2,stmt3,stmt_tmp1;
    ResultSet rset,rset1,rset2,rset3,rset_tmpl;
	String queryString="",queryString1="",queryString2="",queryString3="";
	String from_mail="";
    String to_mail="";
    String from_pwd="";
    String dbline="";
    String username="";
    String password="";
    String encrypted="";
    String user="";
    String pass="";
    String to_date="";
    String file_path="";
	
	NumberFormat nf = new DecimalFormat("###########0.00");
	NumberFormat nf2 = new DecimalFormat("###########0.0000");
	
	public void init()
	{
		try
        {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println(dbline);
			conn=DriverManager.getConnection(dbline,username,password);
                    
			if(conn != null)
			{
				stmt = conn.createStatement();
				stmt1 = conn.createStatement();
				stmt2 = conn.createStatement();
				stmt3 = conn.createStatement();
				stmt_tmp1 = conn.createStatement();
				
				getTankInventoryData();
			}
        }
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
        {
        	try
        	{
        		if(conn!=null)
            	{
            		conn.close();
            	}
            	if(stmt!=null)
            	{
            		stmt.close();
            	}
            	if(stmt1!=null)
            	{
            		stmt1.close();
            	}
            	if(stmt2!=null)
            	{
            		stmt2.close();
            	}
            	if(stmt3!=null)
            	{
            		stmt3.close();
            	}
            	if(stmt_tmp1!=null)
            	{
            		stmt_tmp1.close();
            	}
        	}
        	catch(Exception e)
        	{
        		e.printStackTrace();
        	}
        } 
	}	
	public void getTankInventoryData()
	{
		try
		{
			System.out.println("############ Inside Method #############");
			String[] commands = {"curl", "-k", "-L",  "--negotiate", "-u", "s_inf087" + ":" + "rV6tR2jT", "-X", "GET", "https://pejjbt-s-8082.asia-pac.shell.com/piwebapi/streamsets/end?webid=F1DPjFOakldGMUu0YR_7zapK7wKlAJAARFNBUFBJQ09MTFxIQVo6RFBSLkxORy5DTE9TSU5HU1RPQ0s"};
			Process process = Runtime.getRuntime().exec(commands);
			BufferedReader reader2 = new BufferedReader(new InputStreamReader(process.getInputStream()));
			StringBuilder builder = new StringBuilder();
			String line2 = null;
			String response = null;
			while ((line2 = reader2.readLine()) != null) {
			    builder.append(line2);
			    builder.append(System.getProperty("line2.separator"));
			    //System.out.println(line2);
			}
			response = builder.toString();
			System.out.print("Response : "+response);
			
			//response="{null\"Links\":{},\"Items\":[{\"WebId\":\"F1DPjFOakldGMUu0YR_7zapK7wKlAJAARFNBUFBJQ09MTFxIQVo6RFBSLkxORy5DTE9TSU5HU1RPQ0s\",\"Name\":\"HAZ:DPR.LNG.Closingstock\",\"Path\":\"\\\\DSAPPICOLL\\HAZ:DPR.LNG.Closingstock\",\"Links\":{\"Source\":\"https://pejjbt-s-8082.asia-pac.shell.com/piwebapi/points/F1DPjFOakldGMUu0YR_7zapK7wKlAJAARFNBUFBJQ09MTFxIQVo6RFBSLkxORy5DTE9TSU5HU1RPQ0s\"},\"Value\":{\"Timestamp\":\"2021-06-21T00:40:00Z\",\"Value\":111624.141,\"UnitsAbbreviation\":\"\",\"Good\":true,\"Questionable\":false,\"Substituted\":false,\"Annotated\":false}}]}null";
			//System.out.print("Response : "+response);
			
			response=response.replace("null", "");
			
			JSONParser parse = new JSONParser(); 
			JSONObject jobj = (JSONObject)parse.parse(response);
			System.out.println("\nJSON Parse :"+ jobj);
			JSONArray jsonarr_1 = (JSONArray) jobj.get("Items");
			JSONObject nominatedItemList = new JSONObject();
			Iterator ii = jsonarr_1.iterator();
			String jsonvalue="";
			while(ii.hasNext())
			{
				JSONObject jsonObj = (JSONObject) ii.next();
				jsonvalue = nominatedItemList.toJSONString((Map) jsonObj.get("Value")).toString();
				System.out.println(jsonvalue);
				
			}
			String Value="0";
			String Timestamp="";
			if(!jsonvalue.equals(""))
			{
				String split[] = jsonvalue.split(",");
				//System.out.println(split.length);
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
					if(split2[0].trim().equals("Timestamp"))
					{
						Timestamp=split2[1].trim();
					}
				}
			}
			System.out.println("Value : "+Value);
			System.out.println("Timestamp : "+Timestamp);
			
			String date="";
			if(!Timestamp.equals(""))
			{
				String str_date[] = Timestamp.split("T");
				date = str_date[0].substring(8,str_date[0].length())+"/"+str_date[0].substring(5,7)+"/"+str_date[0].substring(0,4);
			}
			System.out.println("Date : "+date);
			
			double inv_value = Double.parseDouble(Value);
			double con_factor_mmscm = 600;
			double con_factor_mmbtu = 38900;
			
			double mmscm= (inv_value * con_factor_mmscm) / 1000000;
			double mmbtu= mmscm * con_factor_mmbtu;
			
			String str_mmscm = nf.format(mmscm);
			String str_mmbtu = nf.format(mmbtu);
			System.out.println("MMSCM : "+nf.format(mmscm));
			System.out.println("MMBTU : "+nf.format(mmbtu));
			
			
			
			if(!Value.equals("") && !date.equals(""))
			{
				/*HARSH20210629 String gas_date="";
				queryString = "SELECT TO_CHAR(TO_DATE('"+date+"','DD/MM/YYYY')-1,'DD/MM/YYYY') FROM DUAL";
				rset=stmt.executeQuery(queryString);
				if(rset.next())
				{
					gas_date=rset.getString(1)==null?"":rset.getString(1);
				}
				System.out.println("\n GAS Date : "+gas_date);*/
				
				//if(!gas_date.equals(""))
				//{
					System.out.println("Inserting....");
					queryString="SELECT COUNT(*) FROM FMS7_INVENTORY_LEVEL_DTL WHERE INV_LEVEL_DT=TO_DATE('"+date+"','DD/MM/YYYY')";
					rset=stmt.executeQuery(queryString);
					if(rset.next())
					{
						if(rset.getInt(1)>=1)
						{
							System.out.println("Data already exist for the date "+date);
						}
						else
						{
							queryString = "INSERT INTO FMS7_INVENTORY_LEVEL_DTL(INV_LEVEL_DT,"
									+ "T2_VOLUME,T2_CONV_FACTOR_1,T2_MMSCM,T2_CONV_FACTOR_2,T2_MMBTU,"
									+ "EMP_CD,ENT_DT,FLAG) "
									+ "VALUES(TO_DATE('"+date+"','DD/MM/YYYY'),'"+Value+"','"+con_factor_mmscm+"','"+str_mmscm+"',"
									+ "'"+con_factor_mmbtu+"','"+str_mmbtu+"','0',sysdate,'Y')";
							System.out.println(queryString);
							stmt1.executeUpdate(queryString);
							conn.commit();
							System.out.println("Inserted....");
						}
					}
				/*}
				else
				{
					System.out.println("GAS Date is not found!!");
				}*/
			}
			else
			{
				System.out.println("\n Tank Value or Date Not Found !!");
			}
			
			
			
			/*for(int i=0;i<jsonarr_1.size();i++)
			{
				JSONObject jsonobj_1 = (JSONObject)jsonarr_1.get(i);
				jsonobj_1.toString();
			
				System.out.println("Value "+jsonobj_1.get("Value"));
				JSONParser jp = (JSONParser) jsonobj_1.get("");
				//JSONArray jsonarr_2 = (JSONArray) jsonobj_1.get("Value");
				List value = (List) jsonobj_1.get("Value");
				System.out.println(value);
				
				//String[] value = (String[])jsonobj_1.get("Value");
				//System.out.println("Value123"+value.length);
				/*JSONObject v = (JSONObject)parse.parse(value);
				JSONArray varray = (JSONArray) v.get("Value");*/
				/*for(int j=0; j<jsonarr_2.size();j++)
				{
					JSONObject jsonobj_2 = (JSONObject)jsonarr_2.get(j);
					System.out.println("Timestamp: " +jsonobj_2.get("Timestamp"));
					System.out.println("Value: " +jsonobj_2.get("Value"));
					System.out.println("UnitsAbbreviation: " +jsonobj_2.get("UnitsAbbreviation"));
					System.out.println("Good: " +jsonobj_2.get("Good"));
					System.out.println("Questionable: " +jsonobj_2.get("Questionable"));
					System.out.println("Substituted: " +jsonobj_2.get("Substituted"));
					System.out.println("Annotated: " +jsonobj_2.get("Annotated"));
				}
			}*/
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void getmail_list() {
		try
		{
			String strline = "";
			
			File fsetup=new File("mrcr/Setup.txt");
			String mail_list_path=fsetup.getAbsolutePath();
			//mail_list_path = request.getRealPath("/amc/SETUP.txt");
			FileInputStream f1 = new FileInputStream(mail_list_path);
			DataInputStream in = new DataInputStream(f1);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			while((strline = br.readLine())!=null)
			{
				if(strline.startsWith("pwd"))
				{
					String  tmp[]=strline.split("pwd:");
					encrypted = tmp[1].toString();
					from_pwd = encrypted;
				}
				if(strline.startsWith("dbline"))
				{
					String  tmp[]=strline.split("dbline:");
					dbline = tmp[1].toString();
				}
				if(strline.startsWith("username"))
				{
					String  tmp[]=strline.split("username:");
					username = tmp[1].toString();
				}
				if(strline.startsWith("password"))
				{
					String  tmp[]=strline.split("password:");
					 encrypted = tmp[1].toString();
					 password = encrypted;
				}
			}
		}
		catch (Exception e) 
		{
			System.out.println("E.getmassage..."+e.getMessage());
			e.printStackTrace();
		}
	}
}
