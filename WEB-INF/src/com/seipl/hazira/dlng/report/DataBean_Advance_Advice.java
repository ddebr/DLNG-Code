package com.seipl.hazira.dlng.report;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.seipl.hazira.dlng.util.RuntimeConf;
import com.seipl.hazira.dlng.util.UtilBean;

import oracle.net.aso.r;

public class DataBean_Advance_Advice {
	Connection conn; 
	Statement stmt;
	Statement stmt1;
	Statement stmt2;
	Statement stmt3;
	ResultSet rset;
	ResultSet rset1;
	ResultSet rset2;
	ResultSet rset3;
	String queryString = "";
	String queryString1 = "";
	String queryString2 = "";
	String callFlag = "";
	String gas_date = "";
	String gen_date = ""; 
	String from_dt = "";
	String to_dt   ="";
	String week = "";
	String customer_cd = "0";
	String customer_contact_cd = "";
	String customer_contact_nm = "";
	String methodName = "";
	String databeanName = "DataBean_Contract_Mgmt";
	String cust_id="";
	String selMapId = "";
	String sysdate = "";
	String time_gen = "";
	double closing_bal = 0,minimum_req_adv = 0,bal_perc = 0,total_req_adv = 0;
	String contTyp="",custCd="",flsaCd ="",flsaRev =  "",snCd = "",start_dt = "",end_dt="",snRev="",contNo = "";
	public String sales_rate = "0";
	public String contact_Suppl_Name = "";
	public String contact_Suppl_Person_Address = "";
	public String contact_Suppl_Person_City = "";
	public String contact_Suppl_Person_Pin = "";
	String contact_Suppl_State = "";
	String contact_Suppl_State_Code = "";
	public String contact_Suppl_GST_NO = "";
	public String contact_Suppl_CST_NO = "";
	public String contact_Suppl_GST_DT = "";
	public String contact_Suppl_CST_DT = "";
	public String contact_Suppl_Service_Tax_NO = "";
	public String contact_Suppl_Service_Tax_DT = "";
	
	public String contact_Suppl_PAN_NO = "";		
	public String contact_Suppl_PAN_DT = "";		
	
	String contact_Suppl_GSTIN_NO = "";	
	String contact_Suppl_GSTIN_DT = "";
	
	NumberFormat nf = new DecimalFormat("###########0.00");
	NumberFormat nf2 = new DecimalFormat("###########0.0000");
	NumberFormat nf3 = new DecimalFormat("###########0.000000000000");
	NumberFormat nf4 = new DecimalFormat("############.##");
	NumberFormat nf5 = new DecimalFormat("############");
	NumberFormat nf6 = new DecimalFormat("###,###,###,##0.00");
	UtilBean util = new UtilBean();
	String exchg_rate_cd="";
	String Previous_Available_Exchg_Date = "",Previous_Exchg_Rate_Value = "0";
	String print_pdf_flg = "N";
	String pdf_path = "";
	String fileName = "";
	String f_nm="";
	public HttpServletRequest request = null;
	String cust_abbr = "";
	String from_mail="";
	String advance_advice_pdf__path="/pdf_reports/advance_advice/pdf_files";
	String xls_file = "",bcc_mail="";
	String pdfpath="",pdfsignedpath="";
	double last_inv_qty = 0;
	
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
	    		if(conn != null)  
	    		{
	    			stmt = conn.createStatement();
	    			stmt1 = conn.createStatement();
	    			stmt2 = conn.createStatement();
	    			stmt3 = conn.createStatement();
	    			
	    			if(callFlag.equalsIgnoreCase("Advance_Advice_Rpt")) {
	    				
	    				fetchCustDtl(); 
	    				if(print_pdf_flg.equals("Y")) {
	    					
	    					pdf_path = createPdfFileForAdvanceAdvice();
	    					printPdfFileForAdvanceAdvice();
	    				}
	    			}else if(callFlag.equalsIgnoreCase("Send_Mail_Advance_Advice")) {
	    				sendAll1(); 
	    				send_Mail_Advance_Advice();
	    			}
	    			
	    		}
	    	}
	    } catch(Exception e) {
	    	e.printStackTrace();
	    }finally {

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
	
	private void send_Mail_Advance_Advice() {
		try {
			if(selMapId.contains("-")) {
			
				String temp_arr[] = selMapId.toString().split("-");
				contTyp = temp_arr[0];
				custCd =  temp_arr[1];
				flsaCd =  temp_arr[2];
				flsaRev =  temp_arr[3];
				snCd = temp_arr[4];
				snRev = temp_arr[5];
				start_dt = temp_arr[6];
				end_dt = temp_arr[7];
	//			System.out.println("end_dt------"+end_dt);
				if(contTyp.equalsIgnoreCase("S")) {
					contNo = "SN-"+snCd;
				}else if(contTyp.equalsIgnoreCase("L")) {
					contNo = "LoA-"+snCd;
				}
				
				String custAbrSql="SELECT CUSTOMER_ABBR FROM  FMS7_CUSTOMER_MST WHERE CUSTOMER_CD='"+custCd+"'";
				rset = stmt.executeQuery(custAbrSql);
				if(rset.next()) {
					cust_abbr = rset.getString(1) == null?"":rset.getString(1);
				}
				queryString1 = "Select  A.PLANT_ADDR,A.PLANT_STATE,A.PLANT_CITY,A.PLANT_PIN,A.PLANT_NAME"
						+ " from FMS7_CUSTOMER_PLANT_DTL A"+ 
							" WHERE A.CUSTOMER_CD='"+custCd+"' "
						+ " AND A.SEQ_NO = '"+plant_no+"' "
						+ " AND A.EFF_DT=(select max(B.eff_dt) from FMS7_CUSTOMER_PLANT_DTL B where A.seq_no=B.seq_no and B.CUSTOMER_CD='"+custCd+"' and A.SEQ_NO = '"+plant_no+"')";
//				System.out.println("selected customer plant details****"+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				if(rset1.next()) {
					
					plant_addr = rset1.getString(1)==null?"":rset1.getString(1);
					plant_state = rset1.getString(2)==null?"":rset1.getString(2);
					plant_city = rset1.getString(3)==null?"":rset1.getString(3);
					plant_pin = rset1.getString(4)==null?"":rset1.getString(4);
					plant_nm = rset1.getString(5)==null?"":rset1.getString(5);
				}	
				
				fileName = "ADVANCE_ADVICE-"+sysdate.trim().substring(0,2)+sysdate.trim().substring(3,5)+sysdate.trim().substring(6)+"-"+cust_abbr+"-"+plant_nm+"-"+contNo+".pdf";
//				System.out.println("fileName----"+fileName);

				String f1="";
				String f2="";
				File pdf_file;
				String path=request.getRealPath(advance_advice_pdf__path);
				f1=fileName;
				f1=path+"/"+f1;
				f2=advance_advice_pdf__path+"/"+fileName;
				pdf_file=new File(f1);
//				System.out.println("pdf_file.exists()****"+pdf_file.exists());
				
				if(pdf_file.exists())
				{
					xls_file = fileName;
					path=path+"//"+pdf_file;
		            String context_nm = request.getContextPath();
					String server_nm = request.getServerName();
					String server_port = ""+request.getServerPort();
					String url_start = "http://"+server_nm+":"+server_port+context_nm;
					pdfpath = path;
					pdfpath = pdfpath.substring(pdfpath.indexOf("//"));
					pdfpath = url_start+"/"+f2;
//					System.out.println("pdfpath****"+pdfpath);
					pdfsignedpath=pdfpath;
				}
				else{
					pdfsignedpath="";
					xls_file ="Attachment Not Available!!";
				}
				
				String temp_plant_no = "P"+plant_no.trim();
				queryString = "SELECT email FROM FMS7_CUSTOMER_CONTACT_MST A " +
							  "WHERE A.customer_cd='"+custCd+"' AND " +
							  "A.active_flag='Y' AND (A.addr_flag='B' OR A.addr_flag='R' OR " +
							  "A.addr_flag='C' OR A.addr_flag='"+temp_plant_no+"') AND " +
							  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_CONTACT_MST B " +
							  "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no AND " +
							  "B.eff_dt<=TO_DATE('"+sysdate+"','DD/MM/YYYY'))";
//				System.out.println("Customer Contact Person Fetch Query = "+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					email_to+= rset.getString(1)==null?"":rset.getString(1)+",";
				}
				if(email_to.contains(",") && email_to.length() > 0) {
					email_to = email_to.substring(0,email_to.length()-1);
				}
				
				email_cc="GX-HAZIRA-DLNGOPS@shell.com"; //Hiren_20200202 Fix as discussed with Mahesh Sir.
				
				queryString="SELECT SUPPLIER_NAME,GST_TIN_NO,TO_CHAR(GST_TIN_DT,'DD/MM/YYYY'),CST_TIN_NO,TO_CHAR(CST_TIN_DT,'DD/MM/YYYY'),PAN_NO,SUPPLIER_ABBR FROM FMS7_SUPPLIER_MST WHERE SUPPLIER_CD='"+supplier_cd+"' ";
				rset=stmt.executeQuery(queryString);
				if(rset.next()){
					supplier_nm=rset.getString(1)==null?"":rset.getString(1);
					supplier_gst_no=rset.getString(2)==null?"":rset.getString(2);
					supplier_gst_dt=rset.getString(3)==null?"":rset.getString(3);
					supplier_cst_no=rset.getString(4)==null?"":rset.getString(4);
					supplier_cst_dt=rset.getString(5)==null?"":rset.getString(5);
					supplier_pan_no=rset.getString(6)==null?"":rset.getString(6);
					supplier_abbr=rset.getString(7)==null?"":rset.getString(7);
				}
				queryString="SELECT ADDR,CITY,PIN,PHONE FROM FMS7_SUPPLIER_ADDRESS_MST  A WHERE SUPPLIER_CD='"+supplier_cd+"'"
						+ " AND ADDRESS_TYPE='B' AND EFF_DT = (SELECT MAX(EFF_DT) FROM FMS7_SUPPLIER_ADDRESS_MST  B "
						+ " WHERE SUPPLIER_CD='"+supplier_cd+"'  AND ADDRESS_TYPE='B') ";
				
				rset=stmt.executeQuery(queryString);
				if(rset.next()){
					supplier_addr=rset.getString(1)==null?"":rset.getString(1);
					supplier_city=rset.getString(2)==null?"":rset.getString(2);
					supplier_pin_code=rset.getString(3)==null?"":rset.getString(3);
					supplier_phone_no=rset.getString(4)==null?"":rset.getString(4);
				}
				
				queryString="SELECT CUSTOMER_NAME FROM FMS7_CUSTOMER_MST A WHERE CUSTOMER_CD='"+customer_cd+"' AND A.EFF_DT=(SELECT MAX(B.EFF_DT) FROM "
						+ "FMS7_CUSTOMER_MST B WHERE A.CUSTOMER_CD=B.CUSTOMER_CD AND " +
						  "B.EFF_DT<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
				rset=stmt.executeQuery(queryString);
				//System.out.println("queryString--"+queryString);
				if(rset.next()){
					cust_nm=rset.getString(1)==null?"":rset.getString(1);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	private synchronized void sendAll1()throws Exception 
	{
		try
			{
				String strline = "";
			    
			    File fsetup=new File(request.getRealPath("/sales_invoice/Setup.txt"));
				String mail_list_path=fsetup.getAbsolutePath();
				FileInputStream f1 = new FileInputStream(mail_list_path);
				DataInputStream in = new DataInputStream(f1);
				BufferedReader br = new BufferedReader(new InputStreamReader(in));
				
				while((strline = br.readLine())!=null)
				{
					if(strline.startsWith("from"))
					{
						String  tmp[]=strline.split("from:");
						from_mail = tmp[1].toString();
					}
				}
			}	
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	
	public String getFrom_mail() {
		return from_mail;
	}


	boolean flag_DCB = false;
	String url_start = "";
	public String getUrl_start() {
		return url_start;
	}

	private void printPdfFileForAdvanceAdvice() {
		
		Rectangle pageSize = new Rectangle(595, 842);
		if(flag_DCB==true)
		{
			pageSize.setBackgroundColor(new java.awt.Color(0xe3f8c0)); 
		}
		else
		{
			pageSize.setBackgroundColor(new java.awt.Color(0xffffff)); 
		}
		 Document document = new Document(pageSize);
        try
		{
			// Step 2:
			// We create a writer that listens to the document, and directs a PDF-stream to a file ...
			PdfWriter writer = PdfWriter.getInstance(document,new FileOutputStream(createPdfFileForAdvanceAdvice()));
			
			//writer.setEncryption(PdfWriter.STRENGTH128BITS, "", "Daily Seller Nomination To Customer", PdfWriter.AllowCopy | PdfWriter.AllowPrinting);
			document.addTitle("Advance Advice for Next Truck Loading");
            document.addSubject("Advance Advice for Next Truck Loading");
            document.addKeywords("iText, Advance Advice for Next Truck Loading, Step 2, metadata");
            document.addCreator("Advance Advice for Next Truck Loading Sheet Generation using iText");
            document.addAuthor("DLNG");
			
            //Step 3: We open the document for PDF writing ...
			document.open();
		
			//Step 4: We create Page Size, Font Size , and Font Type ... Then add some paragraphs to the document ...
			document.setPageSize(pageSize);
            document.newPage();
            
                                                         
            //iText-5.0.4 ...
            /*Font small_black_normal = FontFactory.getFont(FontFactory.HELVETICA, 8.00f, Font.NORMAL, new BaseColor(new Color(0x00, 0x00, 0x00)));
            Font small_black_bold = FontFactory.getFont(FontFactory.HELVETICA, 8.00f, Font.BOLD, new BaseColor(new Color(0x00, 0x00, 0x00)));
            Font black_bold = FontFactory.getFont(FontFactory.HELVETICA, 9.00f, Font.BOLD, new BaseColor(new Color(0x00, 0x00, 0x00)));*/
            //itext-1.4 ...
            Font small_black_normal = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL, new Color(0x00, 0x00, 0x00));
            Font small_black_bold = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD, new Color(0x00, 0x00, 0x00));
            Font black_bold = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD, new Color(0x00, 0x00, 0x00));  
            Font black_bold1 = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD, new Color(0x00, 0x00, 0x00));  
            
            String context_nm = request.getContextPath();
			String server_nm = request.getServerName();
			String server_port = ""+request.getServerPort();
			  
			url_start = "http://"+server_nm+":"+server_port+context_nm;
			////System.out.println("Jaimin:"+url_start);
			////System.out.println("this is the pic path  "+url_start+"\\images\\LOGO\\HLPL_Logo.jpg");
            Image hlpl_logo = Image.getInstance(url_start+"/images/logo/company_Logo.png");
             
            hlpl_logo.setBorder(Rectangle.NO_BORDER);
            //hlpl_logo.scaleAbsolute(200,90);
            hlpl_logo.scaleAbsolute(48,45);
            PdfPCell hlpl_logo_cell = new PdfPCell(hlpl_logo,false);
            hlpl_logo_cell.setBorder(Rectangle.NO_BORDER);
            
            Paragraph pp=new Paragraph();
            pp.add(new Phrase(new Chunk("Shell Energy India Private Limited",black_bold1)));
//            pp.add(new Phrase(new Chunk("\nAdvance Advice for Next Truck Loading",small_black_bold)));
            
             
            float[] ContactAddrWidths1 = {0.32f,0.68f};
   	     	PdfPTable HlplLogoTable = new PdfPTable(ContactAddrWidths1);
            HlplLogoTable.setWidthPercentage(100);
            HlplLogoTable.setHorizontalAlignment(Element.ALIGN_LEFT);
            HlplLogoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            HlplLogoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            HlplLogoTable.addCell(hlpl_logo_cell);  
            
            HlplLogoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            HlplLogoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            HlplLogoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
            //HlplLogoTable.addCell(new Phrase(new Chunk("Shell Energy India Private Limited",black_bold)));
            HlplLogoTable.addCell(pp);
            
            
            //------------Logic for Gas Day of Daily Customer------------  
            PdfPTable BillingFields = new PdfPTable(1);
            BillingFields.setWidthPercentage(100);
            BillingFields.setHorizontalAlignment(Element.ALIGN_CENTER);
            BillingFields.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            BillingFields.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            BillingFields.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
            BillingFields.addCell(new Phrase(new Chunk("Advance Advice for Next Truck Loading",black_bold)));
            
            String addr_customer = "To,\n\n ";
            
            if(!customer_contact_nm.trim().equals(""))
            {
            	addr_customer += customer_contact_nm;     	
            }
            if(!contact_Customer_Name.trim().equals(""))
            {
            	addr_customer += "\n "+contact_Customer_Name;     	
            }
            if(!contact_Customer_Person_Address.trim().equals(""))
            {
            	addr_customer += "\n "+contact_Customer_Person_Address;     	
            }
            if(!contact_Customer_Person_City.trim().equals(""))
            {
            	addr_customer += "\n "+contact_Customer_Person_City;     	
            }
            if(!contact_Customer_Person_Pin.trim().equals(""))
            {
            	addr_customer += " "+"-"+" "+contact_Customer_Person_Pin;     	
            }
            
            float[] ContactAddrWidths = {0.80f};
            PdfPTable contact_addr_table = new PdfPTable(ContactAddrWidths);
            contact_addr_table.setWidthPercentage(80);
            
            contact_addr_table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            contact_addr_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            contact_addr_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
            contact_addr_table.addCell(new Phrase(new Chunk(addr_customer,small_black_normal)));
            
            float[] dateTimeWidths = {0.20f};
            PdfPTable date_time_table = new PdfPTable(dateTimeWidths);
            date_time_table.setWidthPercentage(20);
            
            date_time_table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            date_time_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            date_time_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
            date_time_table.addCell(new Phrase(new Chunk("Sent Date : "+sysdate,small_black_normal)));
            
            date_time_table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            date_time_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT );
            date_time_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
            date_time_table.addCell(new Phrase(new Chunk("Sent Time : "+time_gen,small_black_normal)));
			  
		    float[] ContactAddrHeaderWidths = {0.80f,0.20f};
            PdfPTable contact_addr_header_table = new PdfPTable(ContactAddrHeaderWidths);
            contact_addr_header_table.setWidthPercentage(100);
            contact_addr_header_table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            contact_addr_header_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            contact_addr_header_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
            contact_addr_header_table.addCell(contact_addr_table);
            contact_addr_header_table.addCell(date_time_table);
            
            float[] subjectWidths = {0.10f,0.90f};
            PdfPTable subject_table = new PdfPTable(subjectWidths);
            subject_table.setWidthPercentage(100);
            
            subject_table.getDefaultCell().setBorder(Rectangle.BOTTOM);
            subject_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            subject_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
            subject_table.addCell(new Phrase(new Chunk("Subject: ",small_black_bold)));
            subject_table.getDefaultCell().setBorder(Rectangle.BOTTOM);
            subject_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            subject_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
            subject_table.addCell(new Phrase(new Chunk("Advance Advice",small_black_normal)));
            
            PdfPTable InfoTable = new PdfPTable(1);
			InfoTable.setWidthPercentage(100);
			InfoTable.setHorizontalAlignment(Element.ALIGN_LEFT);
			InfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			InfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
			InfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
			InfoTable.addCell(new Phrase(new Chunk("\nDear Madam / Sir, ",small_black_normal)));
			
			String contract ="As per the requirements of advance payment clause mentioned in "+contNo+" Dt."+start_dt+" , kindly deposit the advance amount before the nomination of next truck.";
			
			PdfPTable InfoTable1 = new PdfPTable(1);
			InfoTable1.setWidthPercentage(100);
			InfoTable1.setHorizontalAlignment(Element.ALIGN_LEFT);
			InfoTable1.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			InfoTable1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
			InfoTable1.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
			InfoTable1.addCell(new Phrase(new Chunk(contract+"",small_black_normal)));
            
			float[] detaiHeaderWidth = null;
			detaiHeaderWidth = new float[plant_seq_no.size()+1];
			detaiHeaderWidth[0] = 0.20f;
			
			if(plant_seq_no.size() == 1) {
				
				detaiHeaderWidth[1] = 0.10f;
            	
			}else if(plant_seq_no.size() == 2) {
				detaiHeaderWidth[1] = 0.10f;
				detaiHeaderWidth[2] = 0.10f;
				
			}else if(plant_seq_no.size() == 3) {
				
				detaiHeaderWidth[1] = 0.10f;
				detaiHeaderWidth[2] = 0.10f;
				detaiHeaderWidth[3] = 0.10f;
			}else if(plant_seq_no.size() == 4) {
				detaiHeaderWidth[1] = 0.10f;
				detaiHeaderWidth[2] = 0.10f;
				detaiHeaderWidth[3] = 0.10f;
				detaiHeaderWidth[4] = 0.10f;
			}else if(plant_seq_no.size() == 5) {
				detaiHeaderWidth[1] = 0.10f;
				detaiHeaderWidth[2] = 0.10f;
				detaiHeaderWidth[3] = 0.10f;
				detaiHeaderWidth[4] = 0.10f;
				detaiHeaderWidth[5] = 0.10f;
			}else if(plant_seq_no.size() == 6) {
				
				detaiHeaderWidth[1] = 0.10f;
				detaiHeaderWidth[2] = 0.10f;
				detaiHeaderWidth[3] = 0.10f;
				detaiHeaderWidth[4] = 0.10f;
				detaiHeaderWidth[5] = 0.10f;
				detaiHeaderWidth[6] = 0.10f;
				
			}else if(plant_seq_no.size() == 7) {
				
				detaiHeaderWidth[1] = 0.10f;
				detaiHeaderWidth[2] = 0.10f;
				detaiHeaderWidth[3] = 0.10f;
				detaiHeaderWidth[4] = 0.10f;
				detaiHeaderWidth[5] = 0.10f;
				detaiHeaderWidth[6] = 0.10f;
				detaiHeaderWidth[7] = 0.10f;
				
			}else if(plant_seq_no.size() == 8) {
				
				detaiHeaderWidth[1] = 0.10f;
				detaiHeaderWidth[2] = 0.10f;
				detaiHeaderWidth[3] = 0.10f;
				detaiHeaderWidth[4] = 0.10f;
				detaiHeaderWidth[5] = 0.10f;
				detaiHeaderWidth[6] = 0.10f;
				detaiHeaderWidth[7] = 0.10f;
				detaiHeaderWidth[8] = 0.10f;
			}else if(plant_seq_no.size() == 9) {
				detaiHeaderWidth[1] = 0.10f;
				detaiHeaderWidth[2] = 0.10f;
				detaiHeaderWidth[3] = 0.10f;
				detaiHeaderWidth[4] = 0.10f;
				detaiHeaderWidth[5] = 0.10f;
				detaiHeaderWidth[6] = 0.10f;
				detaiHeaderWidth[7] = 0.10f;
				detaiHeaderWidth[8] = 0.10f;
				detaiHeaderWidth[9] = 0.10f;
				
			}/*else if(plant_seq_no.size() == 10) {
				
				detaiHeaderWidth[1] = 0.10f;
				detaiHeaderWidth[2] = 0.10f;
				detaiHeaderWidth[3] = 0.10f;
				detaiHeaderWidth[4] = 0.10f;
				detaiHeaderWidth[5] = 0.10f;
				detaiHeaderWidth[6] = 0.10f;
				detaiHeaderWidth[7] = 0.10f;
				detaiHeaderWidth[8] = 0.10f;
				detaiHeaderWidth[9] = 0.10f;
				detaiHeaderWidth[10] = 0.10f;
			}*/
            
            PdfPTable detail_header_table = new PdfPTable(detaiHeaderWidth);
            detail_header_table.setWidthPercentage(100);
            detail_header_table.setHorizontalAlignment(Element.ALIGN_LEFT);
            
            detail_header_table.getDefaultCell().setBorder(Rectangle.BOX );
            detail_header_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            detail_header_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
            detail_header_table.addCell(new Phrase(new Chunk("Items",small_black_bold)));
            
            for(int i = 0 ; i < plant_seq_no.size(); i++) {
            	
            	detail_header_table.getDefaultCell().setBorder(Rectangle.BOX );
            	detail_header_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            	detail_header_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
            	detail_header_table.addCell(new Phrase(new Chunk(plant_name.elementAt(i)+"",small_black_bold)));
            }
            detail_header_table.getDefaultCell().setBorder(Rectangle.BOX );
            detail_header_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            detail_header_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
            detail_header_table.addCell(new Phrase(new Chunk("Price (USD/MMBtu)",small_black_normal)));
            
            for(int i = 0 ; i < plant_seq_no.size(); i++) {
            	
            	detail_header_table.getDefaultCell().setBorder(Rectangle.BOX );
            	detail_header_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
            	detail_header_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
            	detail_header_table.addCell(new Phrase(new Chunk(sales_rate+"",small_black_normal)));
            }
            
            detail_header_table.getDefaultCell().setBorder(Rectangle.BOX );
            detail_header_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            detail_header_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
            detail_header_table.addCell(new Phrase(new Chunk("Fx (USD/INR)",small_black_normal)));
            
            for(int i = 0 ; i < plant_seq_no.size(); i++) {
            	
            	detail_header_table.getDefaultCell().setBorder(Rectangle.BOX );
            	detail_header_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
            	detail_header_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
            	detail_header_table.addCell(new Phrase(new Chunk(Previous_Exchg_Rate_Value+"",small_black_normal)));
            }
            
            detail_header_table.getDefaultCell().setBorder(Rectangle.BOX );
            detail_header_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            detail_header_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
            detail_header_table.addCell(new Phrase(new Chunk("Volume (MMBtu)",small_black_normal)));
            
            for(int i = 0 ; i < plant_seq_no.size(); i++) {
            	
            	detail_header_table.getDefaultCell().setBorder(Rectangle.BOX );
            	detail_header_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
            	detail_header_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
            	detail_header_table.addCell(new Phrase(new Chunk(nf.format(last_inv_qty),small_black_normal)));
            }
	            
            detail_header_table.getDefaultCell().setBorder(Rectangle.BOX );
            detail_header_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            detail_header_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
            detail_header_table.addCell(new Phrase(new Chunk("Tax Rate",small_black_normal)));
            
            for(int i = 0 ; i < plant_seq_no.size(); i++) {
            	
            	detail_header_table.getDefaultCell().setBorder(Rectangle.BOX );
            	detail_header_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
            	detail_header_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
            	detail_header_table.addCell(new Phrase(new Chunk(tax_factor.elementAt(i)+"",small_black_normal)));
            }
	            
            detail_header_table.getDefaultCell().setBorder(Rectangle.BOX );
            detail_header_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            detail_header_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
            detail_header_table.addCell(new Phrase(new Chunk("Tax Liability",small_black_normal)));
            
            for(int i = 0 ; i < plant_seq_no.size(); i++) {
            	String c_form_app= "";
            	if(cform_flag.elementAt(i).toString().equalsIgnoreCase("N")){ 
            		c_form_app = "N/A";
				}else if(cform_flag.elementAt(i).equals("")){ 
					c_form_app = "C-form Not Configured";
				}else{ 
					c_form_app = taxLiability.elementAt(i)+"";
				} 
            	detail_header_table.getDefaultCell().setBorder(Rectangle.BOX );
            	detail_header_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
            	detail_header_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
            	detail_header_table.addCell(new Phrase(new Chunk(c_form_app+"",small_black_normal)));
            }

            detail_header_table.getDefaultCell().setBorder(Rectangle.BOX );
            detail_header_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            detail_header_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
            detail_header_table.addCell(new Phrase(new Chunk("Advance/Truck (INR)",small_black_bold)));
	            
            for(int i = 0 ; i < plant_seq_no.size(); i++) {
            	
            	detail_header_table.getDefaultCell().setBorder(Rectangle.BOX );
            	detail_header_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
            	detail_header_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
            	detail_header_table.addCell(new Phrase(new Chunk(adv_required.elementAt(i)+"",small_black_bold)));
            }

            detail_header_table.getDefaultCell().setBorder(Rectangle.BOX );
            detail_header_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            detail_header_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
            detail_header_table.addCell(new Phrase(new Chunk("Top-up (%)",small_black_normal)));
	          
            for(int i = 0 ; i < plant_seq_no.size(); i++) {
            	
            	detail_header_table.getDefaultCell().setBorder(Rectangle.BOX );
            	detail_header_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
            	detail_header_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
            	detail_header_table.addCell(new Phrase(new Chunk(top_up_per.elementAt(i) +"",small_black_normal)));
            }

            detail_header_table.getDefaultCell().setBorder(Rectangle.BOX );
            detail_header_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            detail_header_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
            detail_header_table.addCell(new Phrase(new Chunk("Top-up Amount (INR)",small_black_normal)));
	           
            for(int i = 0 ; i < plant_seq_no.size(); i++) {
            	
            	detail_header_table.getDefaultCell().setBorder(Rectangle.BOX );
            	detail_header_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
            	detail_header_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
            	detail_header_table.addCell(new Phrase(new Chunk(top_up_amt.elementAt(i)+"",small_black_normal)));
            }

            detail_header_table.getDefaultCell().setBorder(Rectangle.BOX );
            detail_header_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            detail_header_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
            detail_header_table.addCell(new Phrase(new Chunk("Total incl. top-up (INR) (A)",small_black_bold)));
	           
            for(int i = 0 ; i < plant_seq_no.size(); i++) {
            	
            	detail_header_table.getDefaultCell().setBorder(Rectangle.BOX );
            	detail_header_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
            	detail_header_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
            	detail_header_table.addCell(new Phrase(new Chunk(total_incl_top_up.elementAt(i)+"",small_black_bold)));
            }
            
            detail_header_table.getDefaultCell().setBorder(Rectangle.BOX );
            detail_header_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            detail_header_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
            detail_header_table.addCell(new Phrase(new Chunk("Balance (B)",small_black_normal)));
	           
            for(int i = 0 ; i < plant_seq_no.size(); i++) {
            	
            	detail_header_table.getDefaultCell().setBorder(Rectangle.BOX );
            	detail_header_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
            	detail_header_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
            	detail_header_table.addCell(new Phrase(new Chunk(nf6.format(closing_bal)+"",small_black_normal)));
            }
            
            detail_header_table.getDefaultCell().setBorder(Rectangle.BOX );
            detail_header_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            detail_header_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
            detail_header_table.addCell(new Phrase(new Chunk("Min. Advance Required (INR) (A-B)",small_black_bold)));
	            
            for(int i = 0 ; i < plant_seq_no.size(); i++) {
            	
            	detail_header_table.getDefaultCell().setBorder(Rectangle.BOX );
            	detail_header_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
            	detail_header_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
            	detail_header_table.addCell(new Phrase(new Chunk(total_req_advance.elementAt(i)+"",small_black_bold)));
            }

            PdfPTable NoteInfoTable1 = new PdfPTable(1);
			NoteInfoTable1.setWidthPercentage(80);
			NoteInfoTable1.setHorizontalAlignment(Element.ALIGN_LEFT);
			NoteInfoTable1.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			NoteInfoTable1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
			NoteInfoTable1.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
			//RG20190325 For Company change*/ NoteInfoTable1.addCell(new Phrase(new Chunk("Thanking You,\n\nFor Hazira LNG Private Limited,\n\n\n\n"+supp_con_desig+"\n\n",small_black_normal)));
			NoteInfoTable1.addCell(new Phrase(new Chunk("Thanking You,\n\nFor Shell Energy India Private Limited,\n\n\n\n\n\n",small_black_normal)));
			
			PdfPTable NoteInfoTable3 = new PdfPTable(1);
  			NoteInfoTable3.setWidthPercentage(100);
  			NoteInfoTable3.setHorizontalAlignment(Element.ALIGN_LEFT);
  			NoteInfoTable3.getDefaultCell().setBorder(Rectangle.NO_BORDER);
  			NoteInfoTable3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
  			NoteInfoTable3.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
  			NoteInfoTable3.addCell(new Phrase(new Chunk(note,small_black_normal)));
  			
  			PdfPTable InfoTable2 = new PdfPTable(1);
			InfoTable2.setWidthPercentage(100);
			InfoTable2.setHorizontalAlignment(Element.ALIGN_LEFT);
			InfoTable2.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			InfoTable2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			InfoTable2.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
			InfoTable2.addCell(new Phrase(new Chunk("This is a computer generated Report.",small_black_normal)));
			
          //Adding All Cells To PDF Document - One By One ... 
			document.add(HlplLogoTable);
			document.add(new Paragraph("              "));
			document.add(BillingFields);
			document.add(new Paragraph("              "));
            document.add(contact_addr_header_table);
            document.add(new Paragraph("              "));
            document.add(subject_table);
            document.add(InfoTable);
            document.add(new Paragraph("              "));
            document.add(InfoTable1);
            document.add(new Paragraph("              "));  
            document.add(detail_header_table);
            document.add(new Paragraph("              "));
            document.add(new Paragraph("              "));
            document.add(NoteInfoTable1);
            document.add(new Paragraph("              "));
            document.add(NoteInfoTable3);
            document.add(new Paragraph("              "));
            document.add(new Paragraph("              "));
            document.add(InfoTable2);
		}catch(DocumentException de)
		{
			System.err.println("DocumentException in printPdfFileForDailyCustomer() Method :\n"+de.getMessage());
			de.printStackTrace();
		}
		catch(IOException ioe)
		{
			System.err.println("IOException in printPdfFileForDailyCustomer() Method :\n"+ioe.getMessage());
			ioe.printStackTrace();
		}
		finally
		{
			document.close();
		}
	}

	private String createPdfFileForAdvanceAdvice() {
		
		HttpSession sess = request.getSession();
		pdf_path = sess.getAttribute("advance_pdf_path").toString();
//		System.out.println("SYSDATE*****"+sysdate);
		
		fileName = "ADVANCE_ADVICE-"+sysdate.trim().substring(0,2)+sysdate.trim().substring(3,5)+sysdate.trim().substring(6)+"-"+cust_abbr+"-"+plant_nm+"-"+contNo+".pdf";
		pdf_path = pdf_path+"//"+fileName;
//		System.out.println("--file Advance_Advice_pdf_path---"+pdf_path);
		
		return pdf_path;
	}

	private void fetchCustDtl()throws Exception{
		try {
			
			String custCdSql="SELECT DISTINCT(CUSTOMER_CD) FROM DLNG_INVOICE_MST WHERE CONTRACT_TYPE in ('S','L')  AND TRUCK_ID IS NOT NULL ORDER BY CUSTOMER_CD";
//			System.out.println("custCdSql***"+custCdSql);
			rset = stmt.executeQuery(custCdSql);
			while (rset.next()) {
				
				String custAbrSql="SELECT CUSTOMER_ABBR FROM  FMS7_CUSTOMER_MST WHERE CUSTOMER_CD='"+rset.getString(1)+"'";
				rset1 = stmt1.executeQuery(custAbrSql);
				if(rset1.next()) {
				
//					if(!from_dt.equals("") && !to_dt.equals("")) {
						
						queryString1 = "SELECT FLSA_NO,FLSA_REV_NO,SN_NO,SN_REV_NO,NVL(A.dcq,'0'),NVL(A.mdcq_percentage,'100'),NVL(A.tcq,'0'),NVL(A.variation_qty,'0'),"
								+ " NVL(A.DCQ_MT,'0')  ,A.FCC_FLAG, SN_BASE,TO_CHAR(START_DT,'dd/mm/yyyy'),TO_CHAR(END_DT,'dd/mm/yyyy')"
								+ " FROM DLNG_SN_MST A WHERE"
								+ " A.SN_REV_NO=(SELECT MAX(B.SN_REV_NO) FROM DLNG_SN_MST B WHERE A.SN_NO=B.SN_NO"
								+ " AND A.FLSA_NO=B.FLSA_NO AND A.FLSA_REV_NO=B.FLSA_REV_NO AND B.FLAG='T' AND A.CUSTOMER_CD=B.CUSTOMER_CD )"
								+ "	AND A.CUSTOMER_CD='"+rset.getString(1)+"'"
								+ " AND A.STATUS='Y'"
								+ " AND A.END_DT >= to_date('"+from_dt+"','dd/mm/yyyy')"
								+ " ORDER BY A.customer_cd,A.flsa_no,A.flsa_rev_no,A.sn_no";
//						System.out.println("Fetching SN COntracts.."+queryString1);
						rset2 = stmt2.executeQuery(queryString1);
						while(rset2.next())
						{
							mapping_id.add("S-"+rset.getString(1)+"-"+rset2.getString(1)+"-"+rset2.getString(2)+"-"+rset2.getString(3)+"-"+rset2.getString(4)+"-"+rset2.getString(12)+"-"+rset2.getString(13));
							Vcust_abbr.add(rset1.getString(1)+" "+"SN-"+rset2.getString(3));
						}
						
						queryString2 = "SELECT TENDER_NO,LOA_NO,LOA_REV_NO,NVL(A.dcq,'0'),NVL(A.mdcq_percentage,'100'),NVL(A.tcq,'0'),NVL(A.variation_qty,'0'),"
								+ " NVL(A.DCQ_MT,'0')  ,A.FCC_FLAG, LOA_BASE,TO_CHAR(START_DT,'dd/mm/yyyy'),TO_CHAR(END_DT,'dd/mm/yyyy')"
								+ " FROM DLNG_LOA_MST A WHERE"
								+ " A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM DLNG_LOA_MST B WHERE A.TENDER_NO=B.TENDER_NO AND A.LOA_NO=B.LOA_NO AND A.customer_cd=B.customer_cd)"
								+ " AND A.CUSTOMER_CD='"+rset.getString(1)+"'"
								+ " AND A.STATUS='Y'"
//								+ " AND A.END_DT >= sysdate"
								+ " AND A.END_DT >= to_date('"+from_dt+"','dd/mm/yyyy')"
								+ " ORDER BY CUSTOMER_CD";
//							System.out.println("Fetching LoA COntracts.."+queryString2);
						rset2 = stmt2.executeQuery(queryString2);
						while(rset2.next())
						{
							mapping_id.add("L-"+rset.getString(1)+"-"+rset2.getString(1)+"-0-"+rset2.getString(2)+"-"+rset2.getString(3)+"-"+rset2.getString(11)+"-"+rset2.getString(12));
							Vcust_abbr.add(rset1.getString(1)+" "+"LoA-"+rset2.getString(2));
						}
//					}
				}
			}
			
			if(!selMapId.equals("")) {
				
				queryString = "SELECT supplier_name,gst_tin_no,cst_tin_no,TO_CHAR(gst_tin_dt,'DD-MM-YYYY')," +
						  "TO_CHAR(cst_tin_dt,'DD-MM-YYYY'),addl_no,TO_CHAR(addl_issue_dt,'DD-MM-YYYY'),"
						  + "PAN_NO,TO_CHAR(PAN_ISSUE_DT,'DD-MM-YYYY'),"
						  + "GSTIN_NO,TO_CHAR(GSTIN_DT,'DD/MM/YYYY') " +
						  "FROM FMS7_SUPPLIER_MST A " +
						  "WHERE A.supplier_cd=1 AND " +
						  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_SUPPLIER_MST B " +
						  "WHERE A.supplier_cd=B.supplier_cd)";
//				System.out.println("Supplier Details Fetch Query = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					contact_Suppl_Name = rset.getString(1)==null?"":rset.getString(1);
					contact_Suppl_GST_NO = rset.getString(2)==null?"":rset.getString(2);
					contact_Suppl_CST_NO = rset.getString(3)==null?"":rset.getString(3);
					contact_Suppl_GST_DT = rset.getString(4)==null?"":rset.getString(4);
					contact_Suppl_CST_DT = rset.getString(5)==null?"":rset.getString(5);
					contact_Suppl_Service_Tax_NO = rset.getString(6)==null?"":rset.getString(6);
					contact_Suppl_Service_Tax_DT = rset.getString(7)==null?"":rset.getString(7);
					contact_Suppl_PAN_NO = rset.getString(8)==null?"":rset.getString(8);	
					contact_Suppl_PAN_DT = rset.getString(9)==null?"":rset.getString(9);	
					contact_Suppl_GSTIN_NO = rset.getString(10)==null?"":rset.getString(10);	
					contact_Suppl_GSTIN_DT = rset.getString(11)==null?"":rset.getString(11);	
				}
//				System.out.println("selMapId----"+selMapId);
				String temp_arr[] = selMapId.toString().split("-");
				
				contTyp = temp_arr[0];
				custCd =  temp_arr[1];
				flsaCd =  temp_arr[2];
				flsaRev =  temp_arr[3];
				snCd = temp_arr[4];
				snRev = temp_arr[5];
				start_dt = temp_arr[6];
				end_dt = temp_arr[7];
//				System.out.println("end_dt------"+end_dt);
				if(contTyp.equalsIgnoreCase("S")) {
					contNo = "SN-"+snCd;
					
				}else if(contTyp.equalsIgnoreCase("L")) {
					contNo = "LoA-"+snCd;
				}
				String custAbrSql="SELECT CUSTOMER_ABBR FROM  FMS7_CUSTOMER_MST WHERE CUSTOMER_CD='"+custCd+"'";
				rset = stmt.executeQuery(custAbrSql);
				if(rset.next()) {
					cust_abbr = rset.getString(1) == null?"":rset.getString(1);
				}
				
				/*for sales Rate*/
				String rateSql = "";
				double var_sales_rate = 0; double ori_sale_price = 0;  
				
				queryString = "SELECT DISTINCT NEW_SALE_PRICE, ORI_SALE_PRICE FROM DLNG_CARGO_ALLOC_REVISED_DTL WHERE FLSA_NO="+flsaCd+" AND SN_NO="+snCd+" "
						  + " AND  CUSTOMER_CD="+custCd+" AND FLAG='A' AND MODIFICATION_SEQ_NO = (SELECT MAX(MODIFICATION_SEQ_NO) FROM DLNG_CARGO_ALLOC_REVISED_DTL WHERE FLSA_NO="+flsaCd+" AND SN_NO="+snCd+" "
						  + " AND  CUSTOMER_CD="+custCd+" AND FLAG='A' AND NEW_PRICE_EFF_DT <=TO_DATE('"+from_dt+"','DD/MM/YYYY'))"
						  + " AND NEW_PRICE_EFF_DT <=TO_DATE('"+from_dt+"','DD/MM/YYYY')";
//				System.out.println("QRY-01: Variable Sales Rate: "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					var_sales_rate = rset.getDouble(1);
					ori_sale_price =  rset.getDouble(2);
					sales_rate =""+var_sales_rate; 
				}
				if(var_sales_rate==0){
					
					queryString = "SELECT DISTINCT NEW_SALE_PRICE, ORI_SALE_PRICE FROM DLNG_CARGO_ALLOC_REVISED_DTL WHERE FLSA_NO="+flsaCd+" AND SN_NO="+snCd+" "
							  + " AND  CUSTOMER_CD="+custCd+" AND FLAG='A' AND MODIFICATION_SEQ_NO = (SELECT MAX(MODIFICATION_SEQ_NO) FROM DLNG_CARGO_ALLOC_REVISED_DTL WHERE FLSA_NO="+flsaCd+" AND SN_NO="+snCd+" "
							  + " AND  CUSTOMER_CD="+custCd+" AND FLAG='A' AND NEW_PRICE_EFF_DT >=TO_DATE('"+from_dt+"','DD/MM/YYYY'))"
							  + " AND NEW_PRICE_EFF_DT >=TO_DATE('"+from_dt+"','DD/MM/YYYY')";
//					System.out.println("QRY-02: Variable Sales Rate: "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						var_sales_rate = rset.getDouble(1);
						ori_sale_price =  rset.getDouble(2);
						sales_rate =""+ori_sale_price; 
					}
				}
				if(var_sales_rate==0){
					if(contTyp.equalsIgnoreCase("S")) {
						
						rateSql = "select nvl(RATE,0),nvl(DCQ,0) FROM DLNG_SN_MST WHERE"
								+ " CUSTOMER_CD='"+custCd+"' "
								+ " AND FLSA_NO='"+flsaCd+"' AND FLSA_REV_NO = '"+flsaRev+"'"
								+ " AND SN_NO='"+snCd+"' AND SN_REV_NO='"+snRev+"' ";
						
					}else if(contTyp.equalsIgnoreCase("L")) {
						
						rateSql = "select nvl(RATE,0),nvl(DCQ,0) FROM DLNG_LOA_MST WHERE"
								+ " CUSTOMER_CD='"+custCd+"' "
								+ " AND TENDER_NO='"+flsaCd+"' "
								+ " AND LOA_NO='"+snCd+"' AND LOA_REV_NO='"+snRev+"' ";
					}
				}
				if(!rateSql.equals("")) {
					
					rset = stmt.executeQuery(rateSql);
					if(rset.next()) {
						sales_rate = rset.getString(1)==null?"0":rset.getString(1);
//						dcq = rset.getString(2)==null?"0":rset.getString(2);
					}
				}
				int k = 0;
				if(!end_dt.equals("") && !start_dt.equals("")) {
					
					String compEndDt = "select * from dual where TO_DATE('"+end_dt+"','DD/MM/YYYY') > sysdate ";
//					System.out.println("compEndDt----"+compEndDt);
					rset = stmt.executeQuery(compEndDt);
					if(rset.next()) {
						end_dt = sysdate ; 	
					}
					
//					System.out.println("end_dt------"+end_dt);
					String dateList = "SELECT to_char(to_date('"+start_dt+"', 'dd/mm/yyyy') + LEVEL - 1,'dd/mm/yyyy')"
							+ " FROM DUAL"
							+ " CONNECT BY LEVEL <= to_date('"+end_dt+"', 'dd/mm/yyyy') + 1 - to_date('"+start_dt+"', 'dd/mm/yyyy')";
//					System.out.println("dateList******"+dateList);
					rset = stmt.executeQuery(dateList);
					while (rset.next()) {
						
						String selDate = rset.getString(1)==null?"":rset.getString(1);
						if(!selDate.equals("")) {
							
							/*checking for Advance*/
							if(k == 0) {
								String checkAdvSql = "select nvl(sum(PAY_AMT),0) from DLNG_ADVC_PAY_MST"
										+ " where "
//										+ " PAY_DT = to_date('"+selDate+"','dd/mm/yyyy')"
										+ " CUSTOMER_CD = '"+custCd+"'"
										+ " AND FLSA_NO = '"+flsaCd+"'"
										+ " AND SN_NO = '"+snCd+"'"
										+ " AND CONTRACT_TYPE = '"+contTyp+"' "
										+ " AND APPROVED_FLAG = 'Y' "
										+ " AND (DR_CR_FLAG = 'C' or DR_CR_FLAG is null)";
//								System.out.println("checkAdvSql*********"+checkAdvSql);
								rset1 = stmt1.executeQuery(checkAdvSql);
								if (rset1.next()) {
									closing_bal+=rset1.getDouble(1);
								}
								
								String checkDebAdvSql = "select nvl(sum(PAY_AMT),0) from DLNG_ADVC_PAY_MST"
										+ " where "
										+ " CUSTOMER_CD = '"+custCd+"'"
										+ " AND FLSA_NO = '"+flsaCd+"'"
										+ " AND SN_NO = '"+snCd+"'"
										+ " AND CONTRACT_TYPE = '"+contTyp+"' "
										+ " AND APPROVED_FLAG = 'Y' "
										+ " AND DR_CR_FLAG = 'D' ";
//								System.out.println("checkDebAdvSql*********"+checkDebAdvSql);
								rset1 = stmt1.executeQuery(checkDebAdvSql);
								if (rset1.next()) {
									closing_bal-=rset1.getDouble(1);
								}
							}
							/*
							 * String checkAdvSql = "select nvl(PAY_AMT,0),PAY_TYPE from DLNG_ADVC_PAY_MST"
							 * + " where PAY_DT = to_date('"+selDate+"','dd/mm/yyyy')" +
							 * " AND CUSTOMER_CD = '"+custCd+"'" + " AND FLSA_NO = '"+flsaCd+"'" +
							 * " AND SN_NO = '"+snCd+"'" + " AND CONTRACT_TYPE = '"+contTyp+"' " +
							 * " AND APPROVED_FLAG = 'Y' ";
							 * 
							 * // System.out.println("checkAdvSql*********"+checkAdvSql); rset1 =
							 * stmt1.executeQuery(checkAdvSql); while (rset1.next()) {
							 * closing_bal+=rset1.getDouble(1); }
							 */
							
							/*checking for invoice*/
							String checkInvSql = "select NET_AMT_INR,NEW_INV_SEQ_NO,MAPPING_ID from DLNG_INVOICE_MST"
									+ " where  INVOICE_DT = to_date('"+selDate+"','dd/mm/yyyy')"
									+ " AND CUSTOMER_CD = '"+custCd+"'"
									+ " AND FGSA_NO = '"+flsaCd+"'"
									+ " AND SN_NO = '"+snCd+"'"
									+ " AND CONTRACT_TYPE = '"+contTyp+"' ";
//							System.out.println("checkInvSql-------"+checkInvSql);
							rset1 = stmt1.executeQuery(checkInvSql);
							while (rset1.next()) {
								
								closing_bal-=rset1.getDouble(1);
								/*checking for Invoice HOLD Amount*/
								String map_id = rset1.getString(3)==null?"":rset1.getString(3);
								String temp_arr1[];
								String temp_mapId="";
								if(map_id.contains("-")) {
									
									temp_arr1 = map_id.split("-");
									temp_mapId = temp_arr1[0]+"-"+temp_arr1[1]+"-"+temp_arr1[2]+"-"+temp_arr1[3]+"-"+temp_arr1[4]+"-"+contTyp;
								}else {
									temp_mapId = "%-%-%-%-%-"+contTyp;
								}
//								System.out.println("temp_mapId-------"+temp_mapId);
								String holdAmtSql = "select nvl(HOLD_AMOUNT,0),to_char(PAY_RECV_DT,'dd/mm/yyyy') from FMS8_PAY_RECV_DTL where NEW_INV_SEQ_NO = '"+rset1.getString(2)+"'"
										+ " and MAPPING_ID like '"+temp_mapId+"' "
										+ " and COMMODITY_TYPE='DLNG' "
										+ " and REV_NO = (select max(REV_NO) from FMS8_PAY_RECV_DTL where"
											+ " NEW_INV_SEQ_NO = '"+rset1.getString(2)+"' "
											+ " and MAPPING_ID like '"+temp_mapId+"'  and COMMODITY_TYPE='DLNG' )";
//								System.out.println("holdAmtSql---Invoice---"+holdAmtSql);
								rset2 = stmt2.executeQuery(holdAmtSql);
								if(rset2.next()) {
									if(Double.parseDouble(rset2.getString(1)+"") > 0) {
										closing_bal-=rset2.getDouble(1);
										
										/*checking for C-Form*/
										String CFormSql = "select A.CFORM_NO,to_char(B.CFORM_DT,'dd/mm/yyyy') "
												+ " from FMS7_CFORM_DTL A,FMS7_CFORM_MST B WHERE INVOICE_NO = '"+rset1.getString(2)+"' "
												+ " and A.CFORM_NO = B.CFORM_NO";
//										System.out.println("CFormSql----"+CFormSql);
										rset3 = stmt3.executeQuery(CFormSql);
										if(rset3.next()) {
											closing_bal+=rset2.getDouble(1);
										}
									}
								}
							}
							/*checking for debit/credit note*/
							String checkDrCrSql = "select nvl(DR_CR_NET_AMT_INR,0),DR_CR_DOC_NO,DR_CR_FLAG from "
									+ " DLNG_DR_CR_NOTE where"
									+ " DR_CR_DT = to_date('"+selDate+"','dd/mm/yyyy')"
									+ " AND CUSTOMER_CD = '"+custCd+"'"
									+ " AND FGSA_NO = '"+flsaCd+"'"
									+ " AND SN_NO = '"+snCd+"'"
									+ " AND CONTRACT_TYPE = '"+contTyp+"'"
									+ " AND APRV_BY > 0  ";
//							System.out.println("checkDrCrSql----"+checkDrCrSql);
							rset1 = stmt1.executeQuery(checkDrCrSql);
							while (rset1.next()) {
								String drcr = "";
								if(rset1.getString(3).equalsIgnoreCase("dr")) {
									closing_bal-=rset1.getDouble(1);
								}else {
									closing_bal+=rset1.getDouble(1);
								}
								/*checking for Dr/Cr HOLD Amount*/
								
								String holdAmtSql = "select nvl(HOLD_AMOUNT,0),to_char(PAY_RECV_DT,'dd/mm/yyyy') from FMS8_PAY_RECV_DTL where "
										+ " NEW_INV_SEQ_NO = '"+rset1.getString(2)+"'  and COMMODITY_TYPE='DLNG' "
										+ " and REV_NO = (select max(REV_NO) from FMS8_PAY_RECV_DTL where"
											+ " NEW_INV_SEQ_NO = '"+rset1.getString(2)+"'  and COMMODITY_TYPE='DLNG' )";
//								System.out.println("holdAmtSql--DR/CR----"+holdAmtSql);
								rset2 = stmt2.executeQuery(holdAmtSql);
								if(rset2.next()) {
									if(Double.parseDouble(rset2.getString(1)+"") > 0) {
										closing_bal-=rset2.getDouble(1);
									}
								}
							}
						}
					k++;}
//					System.out.println("last_inv_qty**1**"+last_inv_qty);
					double last_inv_amt = 0;
					String fetchLastInv = "select nvl(NET_AMT_INR,0),nvl(TOTAL_QTY,0) from DLNG_INVOICE_MST where"
							+ " CUSTOMER_CD = '"+custCd+"'"
							+ " and FGSA_NO = '"+flsaCd+"'"
							+ " and FGSA_REV_NO = '"+flsaRev+"'"
							+ " and SN_NO = '"+snCd+"'"
							+ " AND CONTRACT_TYPE = '"+contTyp+"'"
//							+ " and SN_REV_NO = '"+snRev+"'"
							+ " and HLPL_INV_SEQ_NO = (select max(HLPL_INV_SEQ_NO) from DLNG_INVOICE_MST where"
								+ "	CUSTOMER_CD = '"+custCd+"'"
								+ " and FGSA_NO = '"+flsaCd+"'"
								+ " and FGSA_REV_NO = '"+flsaRev+"'"
								+ " and SN_NO = '"+snCd+"'  AND CONTRACT_TYPE = '"+contTyp+"')";
//								+ " and SN_REV_NO = '"+snRev+"' )";
//					System.out.println("fetchLastInv-----"+fetchLastInv);
					rset = stmt.executeQuery(fetchLastInv);
					if(rset.next()) {
						last_inv_amt = rset.getDouble(1);
						if(last_inv_qty == 0) {
							last_inv_qty = rset.getDouble(2);
						}
					}
					if(last_inv_amt > 0) {
						bal_perc = (closing_bal*100)/last_inv_amt;
					}
					if(bal_perc >= 100) {
						
					}else if(bal_perc >=5 && bal_perc < 100 ) {
//						minimum_req_adv = last_inv_amt;
						
					}else if(bal_perc < 5) {
						double top_up = (last_inv_amt*5)/100;
//						minimum_req_adv = last_inv_amt+top_up;
					}
				}
				
					fetchingExchangeRate(contTyp);
					fetchingCustPlantDtl(custCd);
					
				System.out.println("closing_bal---"+nf.format(closing_bal)+"**bal_perc**"+bal_perc+"**minimum_req_adv**"+minimum_req_adv);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private void fetchingExchangeRate(String contTyp) {
		
		try {
			
			/*for exchange Rate*/
			String agrRevNo=flsaRev,exgContType="";
			if(contTyp.equals("L")) {
				
				agrRevNo = "0";
				exgContType = "T";
				
			}else if(contTyp.equals("S")){
				
				exgContType = "F";
			}
			
			queryString1 = "SELECT EXCHNG_RATE_CD FROM DLNG_SN_BILLING_DTL A, FMS7_CONT_EXCHG_RATE_MST B " +
					   "WHERE B.EXC_RATE_CD=A.EXCHNG_RATE_CD and cont_type='"+contTyp+"' AND customer_cd='"+custCd+"' AND " +
					   "flsa_no='"+flsaCd+"' AND flsa_rev_no='"+agrRevNo+"' AND " +
					   "sn_no='"+snCd+"' AND sn_rev_no='"+snRev+"' ";
			//System.out.println("Query To FindOut SN Exchange Rate Note = "+queryString1);
			rset1 = stmt1.executeQuery(queryString1);
			
			if(rset1.next())
			{
				exchg_rate_cd = rset1.getString(1)==null?"0":rset1.getString(1);
			}
			else
			{
				queryString2 = "SELECT EXCHNG_RATE_CD FROM DLNG_FLSA_BILLING_DTL A, FMS7_CONT_EXCHG_RATE_MST B " +
							   "WHERE B.EXC_RATE_CD=A.EXCHNG_RATE_CD and cont_type='"+exgContType+"' AND customer_cd='"+custCd+"' AND " +
							   "flsa_no='"+flsaCd+"' AND flsa_rev_no='"+agrRevNo+"' ";
	//			////System.out.println("Query To FindOut SN Exchange Rate Note = "+queryString2);
				rset2 = stmt2.executeQuery(queryString2);
				
				if(rset2.next())
				{
					exchg_rate_cd = rset2.getString(1)==null?"0":rset2.getString(1);
				}
			}
			
			queryString = "SELECT TO_CHAR(A.eff_dt,'DD/MM/YYYY') FROM FMS7_EXCHG_RATE_ENTRY A " +
			  "WHERE A.exchg_rate_cd="+exchg_rate_cd+" " +
			  "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_EXCHG_RATE_ENTRY B " +
			  "WHERE A.exchg_rate_cd=B.exchg_rate_cd AND " +
			  "B.eff_dt<= sysdate)";
//			System.out.println("Query For Fetching Invoice Previous Available Exchange Day From FMS7_EXCHG_RATE_ENTRY Table = "+queryString);				
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				Previous_Available_Exchg_Date = rset.getString(1);
			}
			if(Previous_Available_Exchg_Date!=null && !Previous_Available_Exchg_Date.equals("") && !Previous_Available_Exchg_Date.equals(" "))
			{
				queryString = "SELECT NVL(A.exchg_val,'0') FROM FMS7_EXCHG_RATE_ENTRY A WHERE A.exchg_rate_cd="+exchg_rate_cd+" " +
				  			  "AND A.eff_dt=TO_DATE('"+Previous_Available_Exchg_Date+"','DD/MM/YYYY')";
//				System.out.println("Query For Fetching Exchange Rate For Invoicing From FMS7_EXCHG_RATE_ENTRY Table = "+queryString);					
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					Previous_Exchg_Rate_Value = nf2.format(Double.parseDouble(rset.getString(1)));
				}
			}
					
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	String plant_no = "";
	String plant_addr = "";
	String plant_state = "";
	String plant_state_cd = "";
	String plant_city = "";
	String plant_pin = "";
	String plant_pan_no = "";
	String plant_gstin_no = "";
	String plant_nm = "";
	public String contact_addr_flag = "";
	public String contact_Customer_Person_Address = "";
	public String contact_Customer_Person_City = "";
	public String contact_Customer_Person_Pin = "";	
	public String contact_Customer_Person_State = "";
	String contact_Customer_Plant_State = "";
	String contact_Customer_State_Code = "";
	String contact_Customer_Name = "";
	Vector plant_seq_no = new Vector();
	Vector plant_name = new Vector();
	
	public String tax_Structure_Dtl = "";
	String tax_struct_cd="0";
	Vector tax_factor = new Vector();
	Vector cform_flag = new Vector();
	Vector taxLiability = new Vector();
	Vector adv_required =  new Vector();
	Vector top_up_per =  new Vector();
	Vector top_up_amt =  new Vector();
	Vector mini_req_amt =  new Vector();
	Vector total_incl_top_up =  new Vector();
	Vector total_req_advance =  new Vector();
	String note= "Note: Nomination is subject to advance payment received.";
	String contact_person_cd="",email_to="",email_cc = "";

	String supplier_cd="1";
	 String supplier_nm="";
	String supplier_pan_no="";
	 String supplier_abbr="";
	 String supplier_addr="";
	 String supplier_city="";
	 String supplier_phone_no="";
	 String supplier_pin_code="";
	 String supplier_cst_no="";
	 String supplier_cst_dt="";
	 String supplier_gst_no="";
	 String supplier_gst_dt="";
	 String cust_nm="",sub_dr_cr_dt="";
	private void fetchingCustPlantDtl(String cust_cd)throws Exception {
		try {
			
			queryString1 = "Select DISTINCT(A.SEQ_NO), to_char(A.EFF_DT,'dd/mm/yyyy'), A.PLANT_TYPE, A.PLANT_NAME, A.PLANT_ABBR, A.PLANT_ADDR,"+  
					" A.PLANT_STATE, A.PLANT_ZONE, A.PLANT_CITY,  A.PLANT_PIN, A.PLANT_SECTOR, A.PLANT_SHORT_ABBR from FMS7_CUSTOMER_PLANT_DTL A"+ 
					" WHERE A.CUSTOMER_CD='"+cust_cd+"' and"+ 
					" A.EFF_DT=(select max(B.eff_dt) from FMS7_CUSTOMER_PLANT_DTL B where A.seq_no=B.seq_no and B.CUSTOMER_CD='"+cust_cd+"')" +
					" order by A.SEQ_NO";
//			System.out.println("customer plant details****"+queryString1);
			rset1 = stmt1.executeQuery(queryString1);
			while(rset1.next()) {
				
				 plant_seq_no.add(rset1.getString(1)==null?"":rset1.getString(1));
				 plant_name.add(rset1.getString(4)==null?"":rset1.getString(4));
				 
				 /*tax stucture details*/
				 queryString = "SELECT A.TAX_STRUCT_DTL,A.TAX_STRUCT_CD FROM FMS7_CUSTOMER_TAX_STRUCT_DTL A WHERE " +
						  "A.customer_cd="+custCd+" AND " +
						  "A.plant_seq_no="+rset1.getString(1)+" AND " +
				 		  "A.tax_struct_dt=(SELECT MAX(B.tax_struct_dt) FROM FMS7_CUSTOMER_TAX_STRUCT_DTL B WHERE " +
				 		  "A.customer_cd=B.customer_cd AND A.plant_seq_no=B.plant_seq_no AND " +
				 		  "B.tax_struct_dt<=sysdate)";
//				System.out.println("...HERE TAX_STRUCT_DTL..."+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					tax_Structure_Dtl = rset.getString(1)==null?"":rset.getString(1);
					tax_struct_cd = rset.getString(2)==null?"0":rset.getString(2);
				}
				else
				{
					tax_Structure_Dtl = "";
					tax_struct_cd = "0";
				}
				double tax_perc = 0; 
				 queryString = "SELECT NVL(A.tax_code,'0'), NVL(A.factor,'0.00'), NVL(A.tax_on,'1'), NVL(A.tax_on_cd,'0'), " +
						  "TO_CHAR(A.app_date,'DD/MM/YYYY') FROM FMS7_TAX_STRUCTURE_DTL A WHERE A.tax_str_cd="+tax_struct_cd+" AND " +
						  "A.app_date=(SELECT MAX(B.app_date) FROM FMS7_TAX_STRUCTURE_DTL B WHERE B.tax_str_cd="+tax_struct_cd+" AND " +
						  "B.app_date<=sysdate) ORDER BY A.tax_code";
//				 System.out.println("queryString----"+queryString);
				rset=stmt.executeQuery(queryString);
				while(rset.next())
				{
					tax_perc+=rset.getDouble(2);  
				}
				tax_factor.add(tax_perc);
				/*C-form Details*/
					String top_up_perc = "";
					double top_up_AMT = 0;
					double hold_amt = 0;
					double maxPerc = 15;
					double diffTax = maxPerc - tax_perc;
					double gross_amt = Double.parseDouble((last_inv_qty+"")) * Double.parseDouble(sales_rate+"") * Double.parseDouble(Previous_Exchg_Rate_Value+"");
//					System.out.println(last_inv_qty+"---"+sales_rate+"-----"+Previous_Exchg_Rate_Value+"---"+tax_perc);
					double tax_amt = (gross_amt * tax_perc) / 100;
					double total_amt_inr = gross_amt + tax_amt;
	//				
//					System.out.println("total_amt_inr---"+total_amt_inr);
					String cFormSql = "select CFORM_FLAG from FMS7_CFORM_CONTRACT_DTL where "
							+ " CUSTOMER_CD = '"+custCd+"'"
							+ " and AGR_NO = '"+flsaCd+"' and AGR_REV_NO = '"+flsaRev+"'"
							+ " and CONTRACT_NO = '"+snCd+"' and CONTRACT_REV_NO = '"+snRev+"'"
							+ " and COMMODITY_TYPE = 'DLNG' and CONTRACT_TYPE = '"+contTyp+"' "
							+ " and PLANT_SEQ_NO = '"+rset1.getString(1)+"'";
//					System.out.println("cFormSql----"+cFormSql);
					rset = stmt.executeQuery(cFormSql); 
					if(rset.next()) {
						cform_flag.add(rset.getString(1)==null?"":rset.getString(1));
						String cFlg = rset.getString(1)==null?"":rset.getString(1);
						if(cFlg.equalsIgnoreCase("Y")) {
							if(diffTax > 0) { //calculate HOLD amount from Gross amount for this condition
								
								hold_amt = (gross_amt*diffTax) / 100;
	//							System.out.println("hold_amt--------"+hold_amt);
							}
						}
						
					}else {
						cform_flag.add("");
					}
				double net_amt = total_amt_inr+hold_amt;
				if(bal_perc <= 100 && last_inv_qty > 0) {
					
					taxLiability.add(nf6.format(hold_amt));
					adv_required.add(nf6.format(net_amt));
					 
					/*for top-Up amount*/
//					System.out.println("bal_perc**"+bal_perc);
					/*if(bal_perc >=5 && bal_perc < 100 ) {
						minimum_req_adv = net_amt;
						top_up_perc = "N/A";
						
					}else if(bal_perc < 5) {*/
						
						minimum_req_adv = net_amt + ((net_amt*10)/100);
						top_up_perc = "10 %";
						top_up_AMT = (net_amt*10)/100;
						System.out.println("closing_bal**"+closing_bal+"***minimum_req_adv***"+minimum_req_adv);
						
						  if(closing_bal > minimum_req_adv) { 
							  total_req_adv = closing_bal - minimum_req_adv;
						  
						  }else if(closing_bal < minimum_req_adv) { 
							  total_req_adv = minimum_req_adv - closing_bal;
						  }
						 
						/* } */
					
					top_up_per.add(top_up_perc);
					top_up_amt.add(nf6.format(top_up_AMT));
					total_incl_top_up.add(nf6.format(minimum_req_adv));
					total_req_advance.add(nf6.format(total_req_adv));
				}else {
					taxLiability.add("N/A");
					adv_required.add("N/A");
					top_up_per.add("N/A");
					top_up_amt.add("N/A");
					total_incl_top_up.add("N/A");
					total_req_advance.add("N/A");
				}
			}
			
				if(!plant_no.equals("")) {
					
					queryString1 = "Select  A.PLANT_ADDR,A.PLANT_STATE,A.PLANT_CITY,A.PLANT_PIN,A.PLANT_NAME"
							+ " from FMS7_CUSTOMER_PLANT_DTL A"+ 
								" WHERE A.CUSTOMER_CD='"+cust_cd+"' "
							+ " AND A.SEQ_NO = '"+plant_no+"' "
							+ " AND A.EFF_DT=(select max(B.eff_dt) from FMS7_CUSTOMER_PLANT_DTL B where A.seq_no=B.seq_no and B.CUSTOMER_CD='"+cust_cd+"' and A.SEQ_NO = '"+plant_no+"')";
	//				System.out.println("selected customer plant details****"+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next()) {
						
						plant_addr = rset1.getString(1)==null?"":rset1.getString(1);
						plant_state = rset1.getString(2)==null?"":rset1.getString(2);
						plant_city = rset1.getString(3)==null?"":rset1.getString(3);
						plant_pin = rset1.getString(4)==null?"":rset1.getString(4);
						plant_nm = rset1.getString(5)==null?"":rset1.getString(5);
						
						String stateCode = "select STATE_CODE from STATE_MST where STATE_NM like '%"+plant_state+"%'";
	//					 System.out.println("stateCode----"+stateCode);
						 rset2 = stmt2.executeQuery(stateCode);
						 if(rset2.next()) {
							 plant_state_cd = rset2.getString(1)==null?"":rset2.getString(1);
						 }else {
							 plant_state_cd = "";
						 }
						 
						 queryString1 = "SELECT STAT_NO, TO_CHAR(EFF_DT,'dd/mm/yyyy'), REMARK " +
								   "FROM FMS7_CUSTOMER_PLANT_TAX_CDS " +
								   "WHERE customer_cd="+cust_cd+" AND " +
								   "plant_seq_no="+plant_no+" AND stat_cd = '1006'";
	//					 System.out.println("PAN *****"+queryString1);
						 rset2 = stmt2.executeQuery(queryString1);
						 if(rset2.next()) {
							 plant_pan_no = rset2.getString(1)==null?"":rset2.getString(1);
						 }else {
							 plant_pan_no = "";
						 }
						 
						 queryString1 = "SELECT STAT_NO, TO_CHAR(EFF_DT,'dd/mm/yyyy'), REMARK " +
								   "FROM FMS7_CUSTOMER_PLANT_TAX_CDS " +
								   "WHERE customer_cd="+cust_cd+" AND " +
								   "plant_seq_no="+plant_no+" AND stat_cd = '1008'";
	//					 System.out.println("GSTIN No *****"+queryString1);
						 rset2 = stmt2.executeQuery(queryString1);
						 if(rset2.next()) {
							 plant_gstin_no = rset2.getString(1)==null?"":rset2.getString(1);
						 }else {
							 plant_gstin_no = "";
						 }
					}
					
					queryString = "SELECT customer_name,gst_tin_no,cst_tin_no,TO_CHAR(gst_tin_dt,'DD-MM-YYYY')," +
							  "TO_CHAR(cst_tin_dt,'DD-MM-YYYY'),addl_no,TO_CHAR(addl_issue_dt,'DD-MM-YYYY') " +
							  "FROM FMS7_CUSTOMER_MST A " +
							  "WHERE A.customer_cd="+cust_cd+" AND " +
							  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_MST B " +
							  "WHERE A.customer_cd=B.customer_cd )";
							////System.out.println("Customer Details Fetch Query = "+queryString);
							rset = stmt.executeQuery(queryString);
							if(rset.next())
							{
								contact_Customer_Name = rset.getString(1)==null?"":rset.getString(1);
							}
							
					fetchInvContactPerson();
					
					if(contact_addr_flag.trim().equalsIgnoreCase("R") || contact_addr_flag.trim().equalsIgnoreCase("C") || contact_addr_flag.trim().equalsIgnoreCase("B"))
					{
						queryString = "SELECT addr,city,pin,state " +
								  "FROM FMS7_CUSTOMER_ADDRESS_MST A " +
								  "WHERE A.customer_cd="+cust_cd+" AND A.address_type='"+contact_addr_flag+"' AND " +
								  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_ADDRESS_MST B " +
								  "WHERE A.customer_cd=B.customer_cd AND B.address_type='"+contact_addr_flag+"')";
	//					System.out.println("Hiren***Customer Address Fetch Query = "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						contact_Customer_Person_Address = rset.getString(1)==null?"":rset.getString(1);
						contact_Customer_Person_City = rset.getString(2)==null?"":rset.getString(2);
						contact_Customer_Person_Pin = rset.getString(3)==null?"":rset.getString(3);
						contact_Customer_Person_State = rset.getString(4)==null?"":rset.getString(4);
					}
					
					queryString = "SELECT PLANT_STATE FROM FMS7_CUSTOMER_PLANT_DTL WHERE CUSTOMER_CD='"+cust_cd+"' "
							+ "AND SEQ_NO = '"+plant_no+"' AND EFF_DT = (SELECT MAX(EFF_DT) FROM FMS7_CUSTOMER_PLANT_DTL B "
							+ "WHERE B.SEQ_NO='"+plant_no+"' AND B.CUSTOMER_CD='"+cust_cd+"' )";
					//System.out.println("Fetchinng State-8888-"+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next()) {
						contact_Customer_Plant_State = rset.getString(1)==null?"":rset.getString(1);
					} else {
						contact_Customer_Plant_State = "";
					}
				}else{
					
					String new_plant_no="";
					if(!contact_addr_flag.equalsIgnoreCase(""))
					new_plant_no = contact_addr_flag.trim().substring(1);
					
					if(new_plant_no.length()>=1)
					{
						queryString = "SELECT plant_addr,plant_city,plant_pin,PLANT_STATE " +
									  "FROM FMS7_CUSTOMER_PLANT_DTL A " +
									  "WHERE A.customer_cd="+cust_cd+" AND A.seq_no='"+new_plant_no+"' AND " +
									  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
									  "WHERE A.customer_cd=B.customer_cd AND B.seq_no='"+new_plant_no+"')";
						
						queryString1 = "SELECT PLANT_STATE FROM FMS7_CUSTOMER_PLANT_DTL WHERE CUSTOMER_CD='"+cust_cd+"' "
								+ "AND SEQ_NO = '"+new_plant_no+"' AND EFF_DT = (SELECT MAX(EFF_DT) FROM FMS7_CUSTOMER_PLANT_DTL B "
								+ "WHERE B.SEQ_NO='"+new_plant_no+"' AND B.CUSTOMER_CD='"+cust_cd+"' )";
					}
					else
					{
						queryString = "SELECT plant_addr,plant_city,plant_pin,PLANT_STATE " +
									  "FROM FMS7_CUSTOMER_PLANT_DTL A " +
									  "WHERE A.customer_cd='"+cust_cd+"' AND A.seq_no='"+plant_no+"' AND " +
									  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
									  "WHERE A.customer_cd=B.customer_cd AND B.seq_no='"+plant_no+"')";
						
						queryString1 = "SELECT PLANT_STATE FROM FMS7_CUSTOMER_PLANT_DTL WHERE CUSTOMER_CD='"+cust_cd+"' "
								+ "AND SEQ_NO = '"+plant_no+"' AND EFF_DT = (SELECT MAX(EFF_DT) FROM FMS7_CUSTOMER_PLANT_DTL B "
								+ "WHERE B.SEQ_NO='"+plant_no+"' AND B.CUSTOMER_CD='"+cust_cd+"' )";
					}
					
					//System.out.println("STEP-1A.6:FMS7_CUSTOMER_PLANT_DTL: "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						contact_Customer_Person_Address = rset.getString(1)==null?"":rset.getString(1);
						contact_Customer_Person_City = rset.getString(2)==null?"":rset.getString(2);
						contact_Customer_Person_Pin = rset.getString(3)==null?"":rset.getString(3);
						contact_Customer_Person_State = rset.getString(4)==null?"":rset.getString(4);
					}
					queryString1 = "SELECT PLANT_STATE FROM FMS7_CUSTOMER_PLANT_DTL WHERE CUSTOMER_CD='"+cust_cd+"' "
							+ "AND SEQ_NO = '"+plant_no+"' AND EFF_DT = (SELECT MAX(EFF_DT) FROM FMS7_CUSTOMER_PLANT_DTL B "
							+ "WHERE B.SEQ_NO='"+plant_no+"' AND B.CUSTOMER_CD='"+cust_cd+"')";
					//System.out.println("Fetchinng State-8888-"+queryString);
	//				rset = stmt.executeQuery(queryString);
					rset1 = stmt.executeQuery(queryString1);
					if(rset1.next()) {
						contact_Customer_Plant_State = rset1.getString(1)==null?"":rset1.getString(1);
					} else {
						contact_Customer_Plant_State = "";
					}
				}
				
				if(contact_Customer_Plant_State!="") {
					queryString = "SELECT STATE_CODE FROM STATE_MST WHERE UPPER(STATE_NM) = '"+contact_Customer_Plant_State.toUpperCase()+"' ";
					rset = stmt.executeQuery(queryString);
					if(rset.next()) {
						contact_Customer_State_Code = rset.getString(1)==null?"":rset.getString(1);
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void fetchInvContactPerson()throws SQLException,IOException {
		try {
			String temp_plant_no = "P"+plant_no.trim();
			queryString = "SELECT NVL(A.seq_no,'0'),NVL(contact_person,' '),NVL(contact_desig,' '),addr_flag " +
						  "FROM FMS7_CUSTOMER_CONTACT_MST A " +
						  "WHERE A.customer_cd="+custCd+" AND A.def_inv_flag='Y' AND " +
						  "A.active_flag='Y' AND (A.addr_flag='B' OR A.addr_flag='R' OR " +
						  "A.addr_flag='C' OR A.addr_flag='"+temp_plant_no+"') AND " +
						  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_CONTACT_MST B " +
						  "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no)"
						  + " and def_inv_to_flag = 'Y' ";
//			System.out.println("Customer Contact Person Fetch Query = "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				customer_contact_cd = rset.getString(1)==null?"":rset.getString(1);
				customer_contact_nm = rset.getString(2)==null?"":rset.getString(2)+" ("+rset.getString(3).trim()+")";
				
				contact_addr_flag = rset.getString(4)==null?"":rset.getString(4);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	Vector Vcust_cd = new Vector();
	Vector Vcust_nm = new Vector();
	Vector mapping_id = new Vector();
	Vector Vcust_abbr = new Vector();

	public void setCallFlag(String callFlag) {
		this.callFlag = callFlag;
	}

	public Vector getVcust_cd() {
		return Vcust_cd;
	}

	public Vector getVcust_nm() {
		return Vcust_nm;
	}

	public Vector getMapping_id() {
		return mapping_id;
	}

	public Vector getVcust_abbr() {
		return Vcust_abbr;
	}

	public String getFrom_dt() {
		return from_dt;
	}

	public String getTo_dt() {
		return to_dt;
	}

	public void setFrom_dt(String from_dt) {
		this.from_dt = from_dt;
	}

	public void setTo_dt(String to_dt) {
		this.to_dt = to_dt;
	}

	public String getSelMapId() {
		return selMapId;
	}

	public String getSysdate() {
		return sysdate;
	}

	public void setSelMapId(String selMapId) {
		this.selMapId = selMapId;
	}

	public void setSysdate(String sysdate) {
		this.sysdate = sysdate;
	}

	public String getContact_Suppl_Name() {
		return contact_Suppl_Name;
	}

	public String getContact_Suppl_Person_Address() {
		return contact_Suppl_Person_Address;
	}

	public String getContact_Suppl_Person_City() {
		return contact_Suppl_Person_City;
	}

	public String getContact_Suppl_Person_Pin() {
		return contact_Suppl_Person_Pin;
	}

	public double getMinimum_req_adv() {
		return minimum_req_adv;
	}

	public double getBal_perc() {
		return bal_perc;
	}

	public Vector getPlant_seq_no() {
		return plant_seq_no;
	}

	public Vector getPlant_name() {
		return plant_name;
	}

	public void setPlant_seq_no(Vector plant_seq_no) {
		this.plant_seq_no = plant_seq_no;
	}

	public String getPlant_no() {
		return plant_no;
	}

	public void setPlant_no(String plant_no) {
		this.plant_no = plant_no;
	}
	public String getCustomer_contact_cd() {
		return customer_contact_cd;
	}

	public String getCustomer_contact_nm() {
		return customer_contact_nm;
	}

	public void setCustomer_contact_cd(String customer_contact_cd) {
		this.customer_contact_cd = customer_contact_cd;
	}

	public void setCustomer_contact_nm(String customer_contact_nm) {
		this.customer_contact_nm = customer_contact_nm;
	}

	public String getContact_Customer_Name() {
		return contact_Customer_Name;
	}

	public String getContact_Customer_Person_Address() {
		return contact_Customer_Person_Address;
	}

	public String getContact_Customer_Person_City() {
		return contact_Customer_Person_City;
	}

	public String getContact_Customer_Person_Pin() {
		return contact_Customer_Person_Pin;
	}

	public String getContact_Customer_Person_State() {
		return contact_Customer_Person_State;
	}

	public String getContact_Customer_Plant_State() {
		return contact_Customer_Plant_State;
	}

	public String getContact_Customer_State_Code() {
		return contact_Customer_State_Code;
	}

	public void setContact_Customer_Person_Address(String contact_Customer_Person_Address) {
		this.contact_Customer_Person_Address = contact_Customer_Person_Address;
	}

	public String getSales_rate() {
		return sales_rate;
	}

	public String getPrevious_Exchg_Rate_Value() {
		return Previous_Exchg_Rate_Value;
	}
	public String getTax_struct_cd() {
		return tax_struct_cd;
	}

	public Vector getTax_factor() {
		return tax_factor;
	}

	public Vector getCform_flag() {
		return cform_flag;
	}

	public Vector getTaxLiability() {
		return taxLiability;
	}

	public Vector getAdv_required() {
		return adv_required;
	}
	public Vector getTop_up_per() {
		return top_up_per;
	}

	public Vector getMini_req_amt() {
		return mini_req_amt;
	}

	public Vector getTop_up_amt() {
		return top_up_amt;
	}

	public String getPrint_pdf_flg() {
		return print_pdf_flg;
	}

	public void setPrint_pdf_flg(String print_pdf_flg) {
		this.print_pdf_flg = print_pdf_flg;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String getTime_gen() {
		return time_gen;
	}

	public void setTime_gen(String time_gen) {
		this.time_gen = time_gen;
	}

	public String getPdf_path() {
		return pdf_path;
	}
	public String getEmail_to() {
		return email_to;
	}

	public String getEmail_cc() {
		return email_cc;
	}
	public String getSupplier_nm() {
		return supplier_nm;
	}

	public String getSupplier_phone_no() {
		return supplier_phone_no;
	}

	public String getSupplier_pin_code() {
		return supplier_pin_code;
	}
	public String getSupplier_abbr() {
		return supplier_abbr;
	}

	public String getSupplier_addr() {
		return supplier_addr;
	}

	public String getSupplier_city() {
		return supplier_city;
	}

	public String getCust_nm() {
		return cust_nm;
	}

	public String getXls_file() {
		return xls_file;
	}
	public String getPdfsignedpath() {
		return pdfsignedpath;
	}

	public double getLast_inv_qty() {
		return last_inv_qty;
	}

	public void setLast_inv_qty(double last_inv_qty) {
		this.last_inv_qty = last_inv_qty;
	}

	public double getClosing_bal() {
		return closing_bal;
	}

	public Vector getTotal_incl_top_up() {
		return total_incl_top_up;
	}

	public Vector getTotal_req_advance() {
		return total_req_advance;
	}
}
