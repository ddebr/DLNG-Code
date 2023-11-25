package com.seipl.hazira.dlng.contract_mgmt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.seipl.hazira.dlng.util.RuntimeConf;
import com.seipl.hazira.dlng.util.UtilBean;

public class DataBean_TLU_DataCapture {
	Connection conn = null;
	Statement stmt;
	Statement stmt1;
	Statement stmt2;
	Statement stmt3;
	Statement stmt4;
	ResultSet rset;
	ResultSet rset1;
	ResultSet rset2;
	ResultSet rset3;
	ResultSet rset4;
	String queryString = "";
	String queryString1 = "";
	String queryString2 = "";
	String queryString3 = "";
	String queryString4 = "";
	
	String tomorrow_date = "";
	
	String gas_date = "";
	String gen_date = "";
	double gcv = 0;
	double conversion_factor_from_m3_to_mmbtu = 23.9;
	NumberFormat nf = new DecimalFormat("###########0.00");
	
	int total_truck_avail = 0;
	double single_truck_capacity = 0;
	
	public Vector customerName = new Vector();
	public Vector nominated_qty = new Vector();
	public Vector Seller_Nom_Fgsa_No = new Vector();
	public Vector Seller_Nom_Fgsa_Rev_No = new Vector();
	public Vector Seller_Nom_Sn_Rev_No = new Vector();
	public Vector Seller_Nom_Sn_No = new Vector();
	public Vector Seller_Nom_Cd = new Vector();
	public Vector Seller_Nom_Dcq = new Vector();
	public Vector Seller_Gen_Day_With_Rev_No = new Vector();
	public Vector Seller_Nom_Plant_Seq_No = new Vector();
	public Vector Seller_Nom_Qty_Mmbtu = new Vector();
	public Vector Seller_Nom_Type = new Vector();
	public Vector Buyer_Gen_Day_With_Rev_No = new Vector();
	public Vector Buyer_Nom_Plant_Seq_No = new Vector();
	public Vector Buyer_Nom_Plant_Seq_Abbr = new Vector();
	public Vector Buyer_Nom_Qty_Mmbtu = new Vector();
	public Vector Seller_Nom_Mapping_Id = new Vector();
	public Vector Sch_Mapping_Id = new Vector();
	
	public Vector reg_no = new Vector();
	public Vector capacity_m3 = new Vector();
	public Vector load_dt = new Vector();
	public Vector end_dt = new Vector();	
	
	public Vector loaded_mmbtu = new Vector();
	public Vector loaded_mt = new Vector();
	public Vector loaded_scm = new Vector();
	public Vector unloaded_scm = new Vector();
	public Vector status = new Vector();
	public Vector trans_cd = new Vector();
	public Vector truck_id = new Vector();
	
	public Vector truckCnt = new Vector();
	public Vector allocated_qty_mmbtu = new Vector();
	
	public Vector jsonNomDates = new Vector();
	public Vector jsonNomTimes = new Vector();
	public Vector jsonNomQtyValue = new Vector();
	
	UtilBean utilBean = new UtilBean();
	HttpServletRequest req;

	public void setReq(HttpServletRequest req) {
		this.req = req;
	}


	@SuppressWarnings("unused")
	public void init()
	{
	    try
	    {
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
//	    		System.out.println("conn************"+conn);
	    		if(conn != null)  
	    		{
	    			stmt = conn.createStatement();
	    			stmt1 = conn.createStatement();
	    			stmt2 = conn.createStatement();
	    			stmt3 = conn.createStatement();
	    			stmt4 = conn.createStatement();
	    			
	    			tludatacapture();
	    			conn.close();
	    			conn = null;
	    		}
	    	}
	    }
	    catch(Exception e)
	    {
	    	System.out.println("Exception In : (DataBean_truckMaster) - (init()): "+e.getMessage());
	    	e.printStackTrace();
	    }
	    finally
	    {
	    	if(rset != null)
	    	{
				try
				{
					rset.close();
				}
				catch(SQLException e)
				{
					System.out.println("rset is not close "+e);
				}
			}
	    	if(rset1 != null)
	    	{
				try
				{
					rset1.close();
				}
				catch(SQLException e)
				{
					System.out.println("rset1 is not close "+e);
				}
			}
	    	if(rset2 != null)
	    	{
				try
				{
					rset2.close();
				}
				catch(SQLException e)
				{
					System.out.println("rset2 is not close "+e);
				}
			}
	    	if(rset3 != null)
	    	{
				try
				{
					rset3.close();
				}
				catch(SQLException e)
				{
					System.out.println("rset3 is not close "+e);
				}
			}
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
			if(stmt1 != null)
			{
				try
				{
					stmt1.close();
				}
				catch(SQLException e)
				{
					System.out.println("stmt1 is not close "+e);
				}
			}
			if(stmt2 != null)
			{
				try
				{
					stmt2.close();
				}
				catch(SQLException e)
				{
					System.out.println("stmt2 is not close "+e);
				}
			}
			if(stmt3 != null)
			{
				try
				{
					stmt3.close();
				}
				catch(SQLException e)
				{
					System.out.println("stmt3 is not close "+e);
				}
			}
			if(stmt4 != null)
			{
				try
				{
					stmt4.close();
				}
				catch(SQLException e)
				{
					System.out.println("stmt4 is not close "+e);
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
	}


	private static void parseItemListObj(JSONObject nomQtyObj) {
		JSONObject ItemListObj = (JSONObject)nomQtyObj.get("Items");
		
		
		String valueNomQty = (String)ItemListObj.get("Value");
		String timeStamps = (String)ItemListObj.get("Timestamp");
		
		 System.out.println("valueNomQty : "+valueNomQty);
		 System.out.println("timeStamps : "+timeStamps);
	}
	
	@SuppressWarnings("unchecked")
	private void tludatacapture() throws SAXException, IOException, SQLException, ParseException {
		try {
			System.out.println("*****in***************");
				SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
				SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");
				SimpleDateFormat format3 = new SimpleDateFormat("yyyy-MM-dd");
				Date date = format1.parse(tomorrow_date);
				
				//String path = req.getContextPath();
				String realPath = req.getRealPath("tlu_xml");
				System.out.println("realPath***"+realPath);
				//File file = new File("C://Users//Hiren//eclipse-workspace//TLU_TOM7//tlu_xml//");
				String filePath = realPath;
				//System.out.println("filePath***"+filePath);
				File file = new File(filePath);
				File[] files = file.listFiles();	
				
				String filenm = "tlr_"+format2.format(date)+".xml";
				System.out.println("XML File should be Exist Like : "+filenm);
				
				/*----------------For XML File-------------*/
				
				for(File f: files)
				{
					if(f.getName().equals(filenm))
					{	
						System.out.println("\nXML File Exist At Server : "+f.getName());
						File fXmlFile = new File(file+"\\"+f.getName());
						
						DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
						DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
						Document doc = dBuilder.parse(fXmlFile);
						doc.getDocumentElement().normalize();
						Element documentElement = doc.getDocumentElement();
						
						NodeList otherstuffNodeList = doc.getElementsByTagName("otherstuff");
						
						for(int i = 0; i < otherstuffNodeList.getLength(); i++)
						{
							NodeList otherstuffChildNodeList = otherstuffNodeList.item(i).getChildNodes();
							for(int j=0;j<otherstuffChildNodeList.getLength();j++) {
								Node otherNode = otherstuffChildNodeList.item(j);
								
								if("gas_day".equals(otherNode.getNodeName())) {
									gas_date = doc.getElementsByTagName("gas_day").item(0).getTextContent();
								}
								if("loading_date".equals(otherNode.getNodeName())) {
									gen_date = doc.getElementsByTagName("loading_date").item(0).getTextContent();
								}
								if("energy".equals(otherNode.getNodeName())) {
									gcv = Double.parseDouble(doc.getElementsByTagName("energy").item(0).getTextContent());
								}
							}
						}
						
						NodeList customerNodeList = documentElement.getElementsByTagName("customer");
						
						for(int i=0;i<customerNodeList.getLength();i++) 
						{
							Node customerNode = customerNodeList.item(i);
							if (customerNode.getNodeType() == Node.ELEMENT_NODE)
							 {
								Element custNodeElement = (Element) customerNode;
						
								customerName.add(custNodeElement.getElementsByTagName("name").item(0).getTextContent());
								nominated_qty.add(custNodeElement.getElementsByTagName("nominated_qty").item(0).getTextContent());
								Seller_Nom_Fgsa_No.add(custNodeElement.getElementsByTagName("Seller_Nom_Fgsa_No").item(0).getTextContent());
								Seller_Nom_Fgsa_Rev_No.add(custNodeElement.getElementsByTagName("Seller_Nom_Fgsa_Rev_No").item(0).getTextContent());
								Seller_Nom_Sn_No.add(custNodeElement.getElementsByTagName("Seller_Nom_Sn_No").item(0).getTextContent());
								Seller_Nom_Sn_Rev_No.add(custNodeElement.getElementsByTagName("Seller_Nom_Sn_Rev_No").item(0).getTextContent());
								Seller_Nom_Cd.add(custNodeElement.getElementsByTagName("Seller_Nom_Cd").item(0).getTextContent());
								Seller_Nom_Dcq.add(custNodeElement.getElementsByTagName("Seller_Nom_Dcq").item(0).getTextContent());
								Seller_Gen_Day_With_Rev_No.add(custNodeElement.getElementsByTagName("Seller_Gen_Day_With_Rev_No").item(0).getTextContent());
								Seller_Nom_Plant_Seq_No.add(custNodeElement.getElementsByTagName("Seller_Nom_Plant_Seq_No").item(0).getTextContent());
								Seller_Nom_Qty_Mmbtu.add(custNodeElement.getElementsByTagName("Seller_Nom_Qty_Mmbtu").item(0).getTextContent());
								Seller_Nom_Type.add(custNodeElement.getElementsByTagName("Seller_Nom_Type").item(0).getTextContent());
								Buyer_Gen_Day_With_Rev_No.add(custNodeElement.getElementsByTagName("Buyer_Gen_Day_With_Rev_No").item(0).getTextContent());
								Buyer_Nom_Plant_Seq_No.add(custNodeElement.getElementsByTagName("Buyer_Nom_Plant_Seq_No").item(0).getTextContent());
								Buyer_Nom_Plant_Seq_Abbr.add(custNodeElement.getElementsByTagName("Buyer_Nom_Plant_Seq_Abbr").item(0).getTextContent());
								Buyer_Nom_Qty_Mmbtu.add(custNodeElement.getElementsByTagName("Buyer_Nom_Qty_Mmbtu").item(0).getTextContent());
								Seller_Nom_Mapping_Id.add(custNodeElement.getElementsByTagName("Seller_Nom_Mapping_Id").item(0).getTextContent());
								Sch_Mapping_Id.add(custNodeElement.getElementsByTagName("daily_Sch_Mapping_Id").item(0).getTextContent());
									
						        NodeList truckNodeList = custNodeElement.getElementsByTagName("truck");
						        
						        allocated_qty_mmbtu.add("0");
						        truckCnt.add(truckNodeList.getLength());
						        
						        for(int j=0;j<truckNodeList.getLength();j++) 
						        {
						        	Element truckNodeElement = (Element) truckNodeList.item(j);
						        	NodeList trans_cdList = truckNodeElement.getElementsByTagName("trans_cd");
						        	NodeList reg_noList = truckNodeElement.getElementsByTagName("reg_no");
						        	NodeList capacity_m3List = truckNodeElement.getElementsByTagName("capacity_m3");
						        	NodeList load_dtList = truckNodeElement.getElementsByTagName("load_dt");
						        	NodeList end_dtList = truckNodeElement.getElementsByTagName("unload_dt");
						        	NodeList loaded_mmbtuList = truckNodeElement.getElementsByTagName("loaded_mmbtu");
						        	NodeList loaded_scmList = truckNodeElement.getElementsByTagName("loaded_scm");
						        	NodeList unloaded_scmList = truckNodeElement.getElementsByTagName("unloaded_scm");
						        	NodeList statusList = truckNodeElement.getElementsByTagName("status");
						        	
						        	for(int n=0;n<reg_noList.getLength();n++) 
						        	{
						        		truck_id.add(truckNodeElement.getAttribute("id"));
						        		
						        		Element trans_cdElement = (Element)trans_cdList.item(n);
						        		trans_cd.add(trans_cdElement.getFirstChild().getNodeValue());
						        		
						        		Element reg_noElement = (Element)reg_noList.item(n);
						        		reg_no.add(reg_noElement.getFirstChild().getNodeValue());
						        		
						        		Element capacity_m3Element = (Element)capacity_m3List.item(n);
						        		capacity_m3.add(capacity_m3Element.getFirstChild().getNodeValue());
		
						        		Element load_dtElement = (Element)load_dtList.item(n);
						        		load_dt.add(load_dtElement.getFirstChild().getNodeValue());								        		
						        		
						        		Element end_dtElement = (Element)end_dtList.item(n);
						        		end_dt.add(end_dtElement.getFirstChild().getNodeValue());
						        		
						        		Element loaded_mmbtuElement = (Element)loaded_mmbtuList.item(n);
						        		loaded_mmbtu.add(loaded_mmbtuElement.getFirstChild().getNodeValue());
						        		
						        		Element loaded_scmElement = (Element)loaded_scmList.item(n);
						        		loaded_scm.add(loaded_scmElement.getFirstChild().getNodeValue());
						        		
						        		Element unloaded_scmElement = (Element)unloaded_scmList.item(n);
						        		unloaded_scm.add(unloaded_scmElement.getFirstChild().getNodeValue());
						        		
						        		Element statusElement = (Element)statusList.item(n);
						        		status.add(statusElement.getFirstChild().getNodeValue());
						        	}
						        }
							 }
						}
					}
				}
				
				/*----------------For JSON File-------------*/ 
				
				/*String filenm2 = "tlu_"+format2.format(date)+".json";
				System.out.println("JSON File should be Exist Like : "+filenm2+"\n");
				String filenm3 = "tlu_new"+format2.format(date)+".json";
                FileWriter Writefile = new FileWriter(filePath+"\\"+filenm3);
				
				for(File f: files)
				{
					if(f.getName().equals(filenm2))
					{
						System.out.println("JSON File Exist At Server : "+f.getName()+"\n");
						File fJsonFile = new File(file+"\\"+f.getName());
						
						JSONParser jsonParser = new JSONParser();
						JSONObject nominatedItemList = new JSONObject();
						try(FileReader reader = new FileReader(fJsonFile)) 
						{
							Object obj = jsonParser.parse(reader);
							
							JSONObject nomQty = (JSONObject)obj;
							JSONArray itemList = (JSONArray)nomQty.get("Items");
							System.out.println("\n");
							System.out.println("itemList : "+itemList.size()+"..."+itemList+"\n");
							
							@SuppressWarnings("rawtypes")
							Iterator i = itemList.iterator();
				            while(i.hasNext())
				            {
				            	JSONObject jsonObj = (JSONObject) i.next();
				            	Date date2 = format3.parse(jsonObj.get("Timestamp").toString().substring(0,10));
				            	jsonNomDates.add(format1.format(date2));
				            	jsonNomQtyValue.add(jsonObj.get("Value"));
				            	jsonNomTimes.add(jsonObj.get("Timestamp").toString().substring(11,19));
				            	
				            	String dt = format1.format(date2);//System.out.println("dt : "+dt);
				            	nominatedItemList.put("Date",dt);//.replace("\\\\", "")
				            	nominatedItemList.put("Value", jsonObj.get("Value"));
				            	nominatedItemList.put("Time", jsonObj.get("Timestamp").toString().substring(11,19));
				            	
				            	Writefile.write(nominatedItemList.toJSONString());
				            }
				            Writefile.close();
				            for(int j=0;j<jsonNomDates.size();j++) {
				            	System.out.println(jsonNomDates.elementAt(j)+"..."+jsonNomQtyValue.elementAt(j)+"..."+jsonNomTimes.elementAt(j));
				            }
				            
						 } catch (FileNotFoundException e) {
					            e.printStackTrace();
				         } catch (IOException e) {
				            e.printStackTrace();
				         } catch (org.json.simple.parser.ParseException e) {
							e.printStackTrace();
						}
					}
				}*/
				
				
				
			} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}  
	}
	
	
	public String getGas_date() {
		return gas_date;
	}
	public void setGas_date(String gas_date) {
		this.gas_date = gas_date;
	}
	public String getGen_date() {
		return gen_date;
	}
	public void setGen_date(String gen_date) {
		this.gen_date = gen_date;
	}
	public double getGcv() {
		return gcv;
	}
	public void setGcv(double gcv) {
		this.gcv = gcv;
	}
	public Vector getCustomerName() {
		return customerName;
	}
	public void setCustomerName(Vector customerName) {
		this.customerName = customerName;
	}
	public Vector getNominated_qty() {
		return nominated_qty;
	}
	public void setNominated_qty(Vector nominated_qty) {
		this.nominated_qty = nominated_qty;
	}
	public Vector getSeller_Nom_Fgsa_No() {
		return Seller_Nom_Fgsa_No;
	}
	public void setSeller_Nom_Fgsa_No(Vector seller_Nom_Fgsa_No) {
		Seller_Nom_Fgsa_No = seller_Nom_Fgsa_No;
	}
	public Vector getSeller_Nom_Fgsa_Rev_No() {
		return Seller_Nom_Fgsa_Rev_No;
	}
	public void setSeller_Nom_Fgsa_Rev_No(Vector seller_Nom_Fgsa_Rev_No) {
		Seller_Nom_Fgsa_Rev_No = seller_Nom_Fgsa_Rev_No;
	}
	public Vector getSeller_Nom_Sn_Rev_No() {
		return Seller_Nom_Sn_Rev_No;
	}
	public void setSeller_Nom_Sn_Rev_No(Vector seller_Nom_Sn_Rev_No) {
		Seller_Nom_Sn_Rev_No = seller_Nom_Sn_Rev_No;
	}
	public Vector getSeller_Nom_Cd() {
		return Seller_Nom_Cd;
	}
	public void setSeller_Nom_Cd(Vector seller_Nom_Cd) {
		Seller_Nom_Cd = seller_Nom_Cd;
	}
	public Vector getSeller_Nom_Dcq() {
		return Seller_Nom_Dcq;
	}
	public void setSeller_Nom_Dcq(Vector seller_Nom_Dcq) {
		Seller_Nom_Dcq = seller_Nom_Dcq;
	}
	public Vector getSeller_Gen_Day_With_Rev_No() {
		return Seller_Gen_Day_With_Rev_No;
	}
	public void setSeller_Gen_Day_With_Rev_No(Vector seller_Gen_Day_With_Rev_No) {
		Seller_Gen_Day_With_Rev_No = seller_Gen_Day_With_Rev_No;
	}
	public Vector getSeller_Nom_Plant_Seq_No() {
		return Seller_Nom_Plant_Seq_No;
	}
	public void setSeller_Nom_Plant_Seq_No(Vector seller_Nom_Plant_Seq_No) {
		Seller_Nom_Plant_Seq_No = seller_Nom_Plant_Seq_No;
	}
	public Vector getSeller_Nom_Qty_Mmbtu() {
		return Seller_Nom_Qty_Mmbtu;
	}
	public void setSeller_Nom_Qty_Mmbtu(Vector seller_Nom_Qty_Mmbtu) {
		Seller_Nom_Qty_Mmbtu = seller_Nom_Qty_Mmbtu;
	}
	public Vector getSeller_Nom_Type() {
		return Seller_Nom_Type;
	}
	public void setSeller_Nom_Type(Vector seller_Nom_Type) {
		Seller_Nom_Type = seller_Nom_Type;
	}
	public Vector getBuyer_Gen_Day_With_Rev_No() {
		return Buyer_Gen_Day_With_Rev_No;
	}
	public void setBuyer_Gen_Day_With_Rev_No(Vector buyer_Gen_Day_With_Rev_No) {
		Buyer_Gen_Day_With_Rev_No = buyer_Gen_Day_With_Rev_No;
	}
	public Vector getBuyer_Nom_Plant_Seq_No() {
		return Buyer_Nom_Plant_Seq_No;
	}
	public void setBuyer_Nom_Plant_Seq_No(Vector buyer_Nom_Plant_Seq_No) {
		Buyer_Nom_Plant_Seq_No = buyer_Nom_Plant_Seq_No;
	}
	public Vector getBuyer_Nom_Plant_Seq_Abbr() {
		return Buyer_Nom_Plant_Seq_Abbr;
	}
	public void setBuyer_Nom_Plant_Seq_Abbr(Vector buyer_Nom_Plant_Seq_Abbr) {
		Buyer_Nom_Plant_Seq_Abbr = buyer_Nom_Plant_Seq_Abbr;
	}
	public Vector getBuyer_Nom_Qty_Mmbtu() {
		return Buyer_Nom_Qty_Mmbtu;
	}
	public void setBuyer_Nom_Qty_Mmbtu(Vector buyer_Nom_Qty_Mmbtu) {
		Buyer_Nom_Qty_Mmbtu = buyer_Nom_Qty_Mmbtu;
	}
	public Vector getSeller_Nom_Mapping_Id() {
		return Seller_Nom_Mapping_Id;
	}
	public void setSeller_Nom_Mapping_Id(Vector seller_Nom_Mapping_Id) {
		Seller_Nom_Mapping_Id = seller_Nom_Mapping_Id;
	}
	public Vector getSch_Mapping_Id() {
		return Sch_Mapping_Id;
	}
	public void setSch_Mapping_Id(Vector sch_Mapping_Id) {
		Sch_Mapping_Id = sch_Mapping_Id;
	}
	public Vector getSeller_Nom_Sn_No() {
		return Seller_Nom_Sn_No;
	}
	public void setSeller_Nom_Sn_No(Vector seller_Nom_Sn_No) {
		Seller_Nom_Sn_No = seller_Nom_Sn_No;
	}
	public Vector getReg_no() {
		return reg_no;
	}
	public void setReg_no(Vector reg_no) {
		this.reg_no = reg_no;
	}
	public Vector getCapacity_m3() {
		return capacity_m3;
	}
	public void setCapacity_m3(Vector capacity_m3) {
		this.capacity_m3 = capacity_m3;
	}
	public Vector getLoad_dt() {
		return load_dt;
	}
	public void setLoad_dt(Vector load_dt) {
		this.load_dt = load_dt;
	}
	public Vector getEnd_dt() {
		return end_dt;
	}
	public void setEnd_dt(Vector end_dt) {
		this.end_dt = end_dt;
	}
	public Vector getLoaded_mmbtu() {
		return loaded_mmbtu;
	}
	public void setLoaded_mmbtu(Vector loaded_mmbtu) {
		this.loaded_mmbtu = loaded_mmbtu;
	}
	public Vector getStatus() {
		return status;
	}
	public void setStatus(Vector status) {
		this.status = status;
	}
	public Vector getLoaded_scm() {
		return loaded_scm;
	}
	public void setLoaded_scm(Vector loaded_scm) {
		this.loaded_scm = loaded_scm;
	}
	public Vector getUnloaded_scm() {
		return unloaded_scm;
	}
	public void setUnloaded_scm(Vector unloaded_scm) {
		this.unloaded_scm = unloaded_scm;
	}
	public Vector getTrans_cd() {
		return trans_cd;
	}
	public void setTrans_cd(Vector trans_cd) {
		this.trans_cd = trans_cd;
	}
	public Vector getTruck_id() {
		return truck_id;
	}
	public void setTruck_id(Vector truck_id) {
		this.truck_id = truck_id;
	}
	public Vector getTruckCnt() {
		return truckCnt;
	}
	public void setTruckCnt(Vector truckCnt) {
		this.truckCnt = truckCnt;
	}
	public Vector getAllocated_qty_mmbtu() {
		return allocated_qty_mmbtu;
	}
	public void setAllocated_qty_mmbtu(Vector allocated_qty_mmbtu) {
		this.allocated_qty_mmbtu = allocated_qty_mmbtu;
	}
	public String getTomorrow_date() {
		return tomorrow_date;
	}
	public void setTomorrow_date(String tomorrow_date) {
		this.tomorrow_date = tomorrow_date;
	}
}  