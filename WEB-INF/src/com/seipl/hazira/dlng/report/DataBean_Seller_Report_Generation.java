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

public class DataBean_Seller_Report_Generation {

	 Connection conn; 
		Statement stmt;
		Statement stmt1;
		Statement stmt2;
		Statement stmt3;
		Statement stmt4;
		Statement stmt5;
		Statement stmt6;
		Statement stmt7;
		ResultSet rset;
		ResultSet rset1;
		ResultSet rset2;
		ResultSet rset3;
		ResultSet rset4;
		ResultSet rset5;
		ResultSet rset6;
		ResultSet rset7;
		String queryString = "";
		String queryString1 = "";
		String queryString2 = "";
		String queryString3 = "";
		String queryString4 = "";
		String queryString5 = "";
		String queryString6 = "";
		String queryString7 = "";
		String callFlag = "";
		String gas_date = "";
		String gen_date = ""; 
		String from_dt = "";
		String to_dt   ="";
		String week = "";
		String customer_cd = "0";
		String customer_contact_cd = "0";
		String methodName = "";
		String cont_type = "";
		String sn_no ="";
		String fgsa_no ="";
		String databeanName = "DataBean_Seller_Report_Generation";
		String trans_cd= "",trans_abbr = "",trans_name = "";
		String print_pdf = "";
		String nom_trans_pdf_path = "";
		public HttpServletRequest request = null;
		String fileName = "";
		boolean flag_DCB = false;
		public String url_start = "";
		String tempcompname="";
		NumberFormat nf = new DecimalFormat("###########0.00");
		HttpSession session = null;
		
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
		    			createTable();
		    			if(callFlag.equalsIgnoreCase("DAILY_SELLER_NOM_CUSTOMER")) 
						{
		    				fetchDailySellerNomDetails();
						}else if(callFlag.equalsIgnoreCase("SELLER_NOM_CUSTOMER"))  // For Seller Daily Nomination for Customer
		    			{
		    				fetchSellerNomDetails();
		    			}else if(callFlag.equalsIgnoreCase("DAILY_SELLER_NOM_TRANSPORTER"))  
		    			{
		    				fetchSellerNomDetailsTrans();
		    			}else if(callFlag.equalsIgnoreCase("PREVIEW_DAILY_SELLER_NOM_TRANSPORTER"))  
		    			{
		    				fetchPreviewSellerNomDetailsTrans();
		    				
		    				if(print_pdf.equalsIgnoreCase("Y")){
		    					
		    					String url_DCB = request.getContextPath(); 
				    		    if(url_DCB.contains("TLU"))
				    		    {
				    		    	flag_DCB=true;
				    		    }
				    		    else
				    		    {
				    		    	flag_DCB=false;
				    		    }
				    		    
		    					nom_trans_pdf_path = createPdfFileForTransporter();
		    					printPdfFileForDailyTransporter();
		    				}
		    			}else if(callFlag.equalsIgnoreCase("fetching_mail_dtl")) {
		    				
		    				fetchPreviewSellerNomDetailsTrans();
		    				sendAll1();
		    				fetchingMailDtl();
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
		    	if(rset4 != null)
		    	{
					try
					{
						rset4.close();
					}
					catch(SQLException e)
					{
						System.out.println("rset4 is not close "+e);
					}
				}
		    	if(rset5 != null)
		    	{
					try
					{
						rset5.close();
					}
					catch(SQLException e)
					{
						System.out.println("rset5 is not close "+e);
					}
				}
		    	if(rset6 != null)
		    	{
					try
					{
						rset6.close();
					}
					catch(SQLException e)
					{
						System.out.println("rset6 is not close "+e);
					}
				}
		    	if(rset7 != null)
		    	{
					try
					{
						rset7.close();
					}
					catch(SQLException e)
					{
						System.out.println("rset7 is not close "+e);
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
				if(stmt5 != null)
				{
					try
					{
						stmt5.close();
					}
					catch(SQLException e)
					{
						System.out.println("stmt5 is not close "+e);
					}
				}
				if(stmt6 != null)
				{
					try
					{
						stmt6.close();
					}
					catch(SQLException e)
					{
						System.out.println("stmt6 is not close "+e);
					}
				}
				if(stmt7 != null)
				{
					try
					{
						stmt7.close();
					}
					catch(SQLException e)
					{
						System.out.println("stmt7 is not close "+e);
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
		private void createTable() {
			
			try {
				int count=0;
				String query="SELECT COUNT(*) FROM COLS WHERE UPPER(TABLE_NAME) LIKE 'DLNG_TRANS_DAILYNOM_REMARK' ";					
				rset=stmt.executeQuery(query);
				if(rset.next())
				{
					count=rset.getInt(1);
				}
				if(count==0)
				{
					query="CREATE TABLE DLNG_TRANS_DAILYNOM_REMARK (TRANS_CD NUMBER(6,0) NOT NULL, "
							+ "GAS_DATE DATE, "
							+ "REMARKS VARCHAR2(100), "
							+ "ENTER_BY NUMBER(6,0), "
							+ "ENTER_DT DATE,"
							+ "FLAG CHAR(1), "
							+ "UPDATE_BY NUMBER(6,0),"
							+ "UPDATE_DATE DATE,"
							+ "CONSTRAINT PK_DLNG_TRANS_DAILYNOM_REMARK PRIMARY KEY "
							+ "(TRANS_CD,GAS_DATE) ) ";
					stmt.executeUpdate(query);
					conn.commit();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		String seller_pdf__path="/pdf_reports/daily_nomination/pdf_files";
		String xls_file = "",bcc_mail="";
		String pdfpath="",pdfsignedpath="";
		String from_mail = "";
		String email_to = "",email_cc="GX-HAZIRA-DLNGOPS@shell.com,";
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
		private void fetchingMailDtl()throws SQLException,IOException {
			try {
				fileName="";
				
				fileName = "TRANSPORTER_SELLER_NOM-"+gas_date.trim().substring(0,2)+gas_date.trim().substring(3,5)+gas_date.trim().substring(6)+"-"+trans_abbr+".pdf";
//				System.out.println("fileName----"+fileName);
				String f1="";
				String f2="";
				File pdf_file;
				String path=request.getRealPath(seller_pdf__path);
//				System.out.println("path****"+path);
				f1=fileName;
				f1=path+"/"+f1;
				f2=seller_pdf__path+"/"+fileName;
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
				
				String transContSql = "select EMAIL from DLNG_TRANSPORTER_CONTACT_MST where"
						+ " TRANS_CD = '"+trans_cd+"'"
						+ " and SEQ_NO = '"+seq_no+"'"
						+ " and EFF_DT = (select max(B.EFF_DT) from DLNG_TRANSPORTER_CONTACT_MST B "
							+ " where B.TRANS_CD = '"+trans_cd+"'"
							+ " and B.SEQ_NO = '"+seq_no+"' and to_date(B.EFF_DT,'dd/mm/yyyy')< = sysdate)";
//				System.out.println("transContSql****"+transContSql);
				rset = stmt.executeQuery(transContSql);
				if(rset.next()) {
					email_to = rset.getString(1)==null?"":rset.getString(1);
					
					String emailCCSql = "select seq_no,eff_dt ,"
							+ " (select EMAIL from DLNG_TRANSPORTER_CONTACT_MST where "
									+ " seq_no = a.seq_no and eff_dt = a.eff_dt "
									+ " and TRANS_CD = '"+trans_cd+"' and SEQ_NO!= '"+seq_no+"'  "
									+ " and DEF_NOM_FLAG = 'Y'  and ACTIVE_FLAG = 'Y'"
									+ " and to_date(EFF_DT,'dd/mm/yyyy')< = sysdate) email"
							+ " from ("
							+ " select seq_no,max(eff_dt) eff_dt"
							+ " from DLNG_TRANSPORTER_CONTACT_MST "
							+ " where "
							+ " TRANS_CD = '"+trans_cd+"' and SEQ_NO!= '"+seq_no+"'  "
							+ " and DEF_NOM_FLAG = 'Y'  and ACTIVE_FLAG = 'Y'"
							+ " and to_date(EFF_DT,'dd/mm/yyyy')< = sysdate"
							+ " group by seq_no ) a";
//					System.out.println("emailCCSql---"+emailCCSql); 
					rset1 = stmt1.executeQuery(emailCCSql);
					while (rset1.next()) {
						email_cc+=rset1.getString(3)==null?"":rset1.getString(3)+",";
					}
				}
				
				if(email_cc.contains(",")) {
					
					email_cc=email_cc.substring(0, email_cc.length()-1);
				}
				
//				System.out.println("email_cc---"+email_cc);
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
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}

		private void printPdfFileForDailyTransporter() throws Exception{
			
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
				PdfWriter writer = PdfWriter.getInstance(document,new FileOutputStream(nom_trans_pdf_path));
				
				//writer.setEncryption(PdfWriter.STRENGTH128BITS, "", "Daily Seller Nomination To Customer", PdfWriter.AllowCopy | PdfWriter.AllowPrinting);
				document.addTitle("Daily Nomination To Transporter");
	            document.addSubject("Daily Nomination To Transporter");
	            document.addKeywords("iText, Daily Nomination To Transporter, Step 2, metadata");
	            document.addCreator("Daily Nomination To Transporter Sheet Generation using iText");
	            document.addAuthor("Hiren");
				
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
	            Image hlpl_logo = Image.getInstance(url_start+"/images/logo/company_Logo.png");
	             
	            hlpl_logo.setBorder(Rectangle.NO_BORDER);
	            //hlpl_logo.scaleAbsolute(200,90);
	            hlpl_logo.scaleAbsolute(48,45);
	            PdfPCell hlpl_logo_cell = new PdfPCell(hlpl_logo,false);
	            hlpl_logo_cell.setBorder(Rectangle.NO_BORDER);
	            
	            Paragraph pp=new Paragraph();
	            pp.add(new Phrase(new Chunk("Shell Energy India Private Limited",black_bold1)));
	            
	            if(!tempcompname.equals("")){
	            	 pp.add(new Phrase(new Chunk("\n  "+tempcompname,small_black_normal)));
	            }
	             
	            float[] ContactAddrWidths1 = {0.30f,0.70f};
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
	            
	            PdfPTable BillingFields = new PdfPTable(1);
	            BillingFields.setWidthPercentage(100);
	            BillingFields.setHorizontalAlignment(Element.ALIGN_LEFT);
	            BillingFields.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	            BillingFields.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	            BillingFields.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
	            BillingFields.addCell(new Phrase(new Chunk("Seller's Daily Nomination to Transporter ("+trans_abbr+") - "+gas_date,black_bold)));
	            BillingFields.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	            BillingFields.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	            BillingFields.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
	            BillingFields.addCell(new Phrase(new Chunk("Electronic Transmission",black_bold)));
	            
	            float[] BillingFieldsDetailsWidths = {0.20f,0.50f,0.11f,0.19f};
	            PdfPTable BillingFieldsInfoTable = new PdfPTable(BillingFieldsDetailsWidths);
	            BillingFieldsInfoTable.setWidthPercentage(100);
	            BillingFieldsInfoTable.setHorizontalAlignment(Element.ALIGN_LEFT);
	            
	            BillingFieldsInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	            BillingFieldsInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
	            BillingFieldsInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
	            BillingFieldsInfoTable.addCell(new Phrase(new Chunk("To: ",small_black_bold)));
	            
	            BillingFieldsInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	            BillingFieldsInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
	            BillingFieldsInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
	            BillingFieldsInfoTable.addCell(new Phrase(new Chunk(temp_con_desig,small_black_normal)));
	            
	            BillingFieldsInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	            BillingFieldsInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
	            BillingFieldsInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
	            BillingFieldsInfoTable.addCell(new Phrase(new Chunk("Sent Date:",small_black_bold)));
	            BillingFieldsInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	            BillingFieldsInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
	            BillingFieldsInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
	            BillingFieldsInfoTable.addCell(new Phrase(new Chunk(gen_date,small_black_normal)));
	                        
	            BillingFieldsInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	            BillingFieldsInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
	            BillingFieldsInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
	            BillingFieldsInfoTable.addCell(new Phrase(new Chunk("Transporter Name:",small_black_bold)));
	            BillingFieldsInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	            BillingFieldsInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
	            BillingFieldsInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
	            BillingFieldsInfoTable.addCell(new Phrase(new Chunk(trans_name,small_black_normal)));
	            
	            BillingFieldsInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	            BillingFieldsInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
	            BillingFieldsInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
	            BillingFieldsInfoTable.addCell(new Phrase(new Chunk("Sent Time:",small_black_bold)));
	            BillingFieldsInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	            BillingFieldsInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
	            BillingFieldsInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
	            BillingFieldsInfoTable.addCell(new Phrase(new Chunk(gen_time,small_black_normal)));            
	            
	            BillingFieldsInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	            BillingFieldsInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
	            BillingFieldsInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
	            BillingFieldsInfoTable.addCell(new Phrase(new Chunk("Fax No:",small_black_bold)));
	            BillingFieldsInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	            BillingFieldsInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
	            BillingFieldsInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
	            BillingFieldsInfoTable.addCell(new Phrase(new Chunk(to_fax_no,small_black_normal)));
	            
	            BillingFieldsInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	            BillingFieldsInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
	            BillingFieldsInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
	            BillingFieldsInfoTable.addCell(new Phrase(new Chunk("",small_black_bold)));
	            BillingFieldsInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	            BillingFieldsInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
	            BillingFieldsInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
	            BillingFieldsInfoTable.addCell(new Phrase(new Chunk("",small_black_normal)));
	            
	            float[] BillingFieldsDetailsWidths_1 = {0.10f,0.90f};
				PdfPTable BillingFieldsDetailsTable_1 = new PdfPTable(BillingFieldsDetailsWidths_1);
				BillingFieldsDetailsTable_1.setWidthPercentage(80);
				BillingFieldsDetailsTable_1.setHorizontalAlignment(Element.ALIGN_LEFT);
				
				BillingFieldsDetailsTable_1.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	            BillingFieldsDetailsTable_1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
	            BillingFieldsDetailsTable_1.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
	            BillingFieldsDetailsTable_1.addCell(new Phrase(new Chunk("From: ",small_black_bold)));			
				BillingFieldsDetailsTable_1.getDefaultCell().setBorder(Rectangle.NO_BORDER);
				BillingFieldsDetailsTable_1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
				BillingFieldsDetailsTable_1.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
				BillingFieldsDetailsTable_1.addCell(new Phrase(new Chunk(supp_con_desig,small_black_normal)));
				
				BillingFieldsDetailsTable_1.getDefaultCell().setBorder(Rectangle.NO_BORDER);
				BillingFieldsDetailsTable_1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
				BillingFieldsDetailsTable_1.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
				BillingFieldsDetailsTable_1.addCell(new Phrase(new Chunk("",small_black_bold)));
				BillingFieldsDetailsTable_1.getDefaultCell().setBorder(Rectangle.NO_BORDER);
				BillingFieldsDetailsTable_1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
				BillingFieldsDetailsTable_1.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
			//RG20190325 For Company change*/	BillingFieldsDetailsTable_1.addCell(new Phrase(new Chunk("Hazira LNG Private Ltd.",small_black_normal)));
				BillingFieldsDetailsTable_1.addCell(new Phrase(new Chunk("Shell Energy India Private Ltd.",small_black_normal)));

				BillingFieldsDetailsTable_1.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	            BillingFieldsDetailsTable_1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
	            BillingFieldsDetailsTable_1.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
	            BillingFieldsDetailsTable_1.addCell(new Phrase(new Chunk("Fax No: ",small_black_bold)));			
				BillingFieldsDetailsTable_1.getDefaultCell().setBorder(Rectangle.NO_BORDER);
				BillingFieldsDetailsTable_1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
				BillingFieldsDetailsTable_1.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
				BillingFieldsDetailsTable_1.addCell(new Phrase(new Chunk(from_fax_no,small_black_normal)));
				
				BillingFieldsDetailsTable_1.getDefaultCell().setBorder(Rectangle.BOTTOM);
				BillingFieldsDetailsTable_1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
				BillingFieldsDetailsTable_1.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
				BillingFieldsDetailsTable_1.addCell(new Phrase(new Chunk("Subject: ",small_black_bold)));
				BillingFieldsDetailsTable_1.getDefaultCell().setBorder(Rectangle.BOTTOM);
				BillingFieldsDetailsTable_1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
				BillingFieldsDetailsTable_1.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
				BillingFieldsDetailsTable_1.addCell(new Phrase(new Chunk("Seller's Daily Nomination to Transporter ("+trans_abbr+") - "+gas_date,small_black_normal)));
	            
				PdfPTable InfoTable = new PdfPTable(1);
				InfoTable.setWidthPercentage(80);
				InfoTable.setHorizontalAlignment(Element.ALIGN_LEFT);
				InfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
				InfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
				InfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
				InfoTable.addCell(new Phrase(new Chunk("\nDear Madam / Sir,",small_black_normal)));
				
				PdfPTable InfoTable1 = new PdfPTable(1);
				InfoTable1.setWidthPercentage(80);
				InfoTable1.setHorizontalAlignment(Element.ALIGN_LEFT);
				InfoTable1.getDefaultCell().setBorder(Rectangle.NO_BORDER);
				InfoTable1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
				InfoTable1.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
				InfoTable1.addCell(new Phrase(new Chunk("    As per the requirements of Clause, we notify as follows: ",small_black_normal)));
				
				float[] detaiHeaderWidth = null;
				detaiHeaderWidth = new float[SUPP_SEQ_NO.size()+1];
				detaiHeaderWidth[0] = 0.20f;
	            
				if(SUPP_SEQ_NO.size() == 1) {
					
					detaiHeaderWidth[1] = 0.20f;
	            	
				}else if(SUPP_SEQ_NO.size() == 2) {
					
					detaiHeaderWidth[1] = 0.20f;
					detaiHeaderWidth[2] = 0.20f;
					
				}
	            
				 PdfPTable detail_header_table = new PdfPTable(detaiHeaderWidth);
		         detail_header_table.setWidthPercentage(50);
		         detail_header_table.setHorizontalAlignment(Element.ALIGN_LEFT);
		         
		         detail_header_table.getDefaultCell().setBorder(Rectangle.BOX );
	             detail_header_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
	             detail_header_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
	             detail_header_table.addCell(new Phrase(new Chunk("Contact Name",small_black_normal)));
		          
	             for(int i = 0 ; i < SUPP_SEQ_NO.size(); i++) {
	            	 
	            	detail_header_table.getDefaultCell().setBorder(Rectangle.BOX );
	             	detail_header_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
	             	detail_header_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
	             	detail_header_table.addCell(new Phrase(new Chunk(SUPP_CONTACT_PERSON.elementAt(i)+"",small_black_normal)));
	             }
	            
	             detail_header_table.getDefaultCell().setBorder(Rectangle.BOX );
	             detail_header_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
	             detail_header_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
	             detail_header_table.addCell(new Phrase(new Chunk("Telephone Number",small_black_normal)));
		          
	             for(int i = 0 ; i < SUPP_SEQ_NO.size(); i++) {
	            	 
	            	detail_header_table.getDefaultCell().setBorder(Rectangle.BOX );
	             	detail_header_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
	             	detail_header_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
	             	detail_header_table.addCell(new Phrase(new Chunk(PHONE.elementAt(i)+"",small_black_normal)));
	             }
	             
	             detail_header_table.getDefaultCell().setBorder(Rectangle.BOX );
	             detail_header_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
	             detail_header_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
	             detail_header_table.addCell(new Phrase(new Chunk("Mobile Number",small_black_normal)));
		          
	             for(int i = 0 ; i < SUPP_SEQ_NO.size(); i++) {
	            	 
	            	detail_header_table.getDefaultCell().setBorder(Rectangle.BOX );
	             	detail_header_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
	             	detail_header_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
	             	detail_header_table.addCell(new Phrase(new Chunk(MOBILE.elementAt(i)+"",small_black_normal)));
	             }
	             
	             float[] BillingFieldsDetailsWidths_20_1 = {0.10f,0.10f,0.10f,0.10f,0.10f,0.10f,0.10f,0.10f,0.10f,0.10f,0.10f};
	 			 PdfPTable BillingFieldsDetailsTable_20_1 = new PdfPTable(BillingFieldsDetailsWidths_20_1);
	 			 BillingFieldsDetailsTable_20_1.setWidthPercentage(100);
	 			 BillingFieldsDetailsTable_20_1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 			 
	             float[] BillingFieldsDetailsWidths_20 = {0.10f,0.10f,0.10f,0.10f,0.10f,0.10f,0.10f,0.10f,0.10f,0.10f,0.10f};
	             PdfPTable BillingFieldsDetailsTable_20 = new PdfPTable(BillingFieldsDetailsWidths_20);
	             BillingFieldsDetailsTable_20.setWidthPercentage(100);
	             BillingFieldsDetailsTable_20.setHorizontalAlignment(Element.ALIGN_LEFT);
	 			
	             BillingFieldsDetailsTable_20.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.TOP);
	             BillingFieldsDetailsTable_20.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	             BillingFieldsDetailsTable_20.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
	             BillingFieldsDetailsTable_20.getDefaultCell().setColspan(1);
	             BillingFieldsDetailsTable_20.addCell(new Phrase(new Chunk("Customer ABBR",small_black_bold))); 
	             
	             BillingFieldsDetailsTable_20.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.TOP);
	             BillingFieldsDetailsTable_20.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	             BillingFieldsDetailsTable_20.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
	             BillingFieldsDetailsTable_20.getDefaultCell().setColspan(1);
	             BillingFieldsDetailsTable_20.addCell(new Phrase(new Chunk("Plant ABBR",small_black_bold))); 
	             
	             BillingFieldsDetailsTable_20.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.TOP);
	             BillingFieldsDetailsTable_20.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	             BillingFieldsDetailsTable_20.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
	             BillingFieldsDetailsTable_20.getDefaultCell().setColspan(1);
	             BillingFieldsDetailsTable_20.addCell(new Phrase(new Chunk("SN/LoA No.",small_black_bold)));
	             
	             BillingFieldsDetailsTable_20.getDefaultCell().setBorder(Rectangle.BOX);
	             BillingFieldsDetailsTable_20.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	             BillingFieldsDetailsTable_20.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
	             BillingFieldsDetailsTable_20.getDefaultCell().setColspan(3);
	             BillingFieldsDetailsTable_20.addCell(new Phrase(new Chunk("Truck Confirmation",small_black_bold)));
	             
	             BillingFieldsDetailsTable_20.getDefaultCell().setBorder(Rectangle.BOX);
	             BillingFieldsDetailsTable_20.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	             BillingFieldsDetailsTable_20.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
	             BillingFieldsDetailsTable_20.getDefaultCell().setColspan(3);
	             BillingFieldsDetailsTable_20.addCell(new Phrase(new Chunk("Nomination",small_black_bold)));
	             
	             BillingFieldsDetailsTable_20.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP);
	             BillingFieldsDetailsTable_20.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	             BillingFieldsDetailsTable_20.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
	             BillingFieldsDetailsTable_20.getDefaultCell().setColspan(1);
	             BillingFieldsDetailsTable_20.addCell(new Phrase(new Chunk("Check Post",small_black_bold)));
	             
	             BillingFieldsDetailsTable_20.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP);
	             BillingFieldsDetailsTable_20.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	             BillingFieldsDetailsTable_20.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
	             BillingFieldsDetailsTable_20.getDefaultCell().setColspan(1);
	             BillingFieldsDetailsTable_20.addCell(new Phrase(new Chunk("Seller's Comments",small_black_bold)));
	             
	             BillingFieldsDetailsTable_20_1.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
	             BillingFieldsDetailsTable_20_1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	             BillingFieldsDetailsTable_20_1.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
	             BillingFieldsDetailsTable_20_1.addCell(new Phrase(new Chunk("",small_black_bold))); 
	             
	             BillingFieldsDetailsTable_20_1.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
	             BillingFieldsDetailsTable_20_1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	             BillingFieldsDetailsTable_20_1.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
	             BillingFieldsDetailsTable_20_1.addCell(new Phrase(new Chunk("",small_black_bold))); 
	             
	             BillingFieldsDetailsTable_20_1.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
	             BillingFieldsDetailsTable_20_1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	             BillingFieldsDetailsTable_20_1.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
	             BillingFieldsDetailsTable_20_1.addCell(new Phrase(new Chunk("",small_black_bold)));
	             
	             BillingFieldsDetailsTable_20_1.getDefaultCell().setBorder(Rectangle.BOX);
	             BillingFieldsDetailsTable_20_1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	             BillingFieldsDetailsTable_20_1.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
	             BillingFieldsDetailsTable_20_1.addCell(new Phrase(new Chunk("No.",small_black_bold)));
	             
	             BillingFieldsDetailsTable_20_1.getDefaultCell().setBorder(Rectangle.BOX);
	             BillingFieldsDetailsTable_20_1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	             BillingFieldsDetailsTable_20_1.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
	             BillingFieldsDetailsTable_20_1.addCell(new Phrase(new Chunk("Scheduled Time",small_black_bold)));
	             
	             BillingFieldsDetailsTable_20_1.getDefaultCell().setBorder(Rectangle.BOX);
	             BillingFieldsDetailsTable_20_1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	             BillingFieldsDetailsTable_20_1.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
	             BillingFieldsDetailsTable_20_1.addCell(new Phrase(new Chunk("Driver Name",small_black_bold)));
	             
	             BillingFieldsDetailsTable_20_1.getDefaultCell().setBorder(Rectangle.BOX);
	             BillingFieldsDetailsTable_20_1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	             BillingFieldsDetailsTable_20_1.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
	             BillingFieldsDetailsTable_20_1.addCell(new Phrase(new Chunk("MMBTU",small_black_bold)));
	             
	             BillingFieldsDetailsTable_20_1.getDefaultCell().setBorder(Rectangle.BOX);
	             BillingFieldsDetailsTable_20_1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	             BillingFieldsDetailsTable_20_1.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
	             BillingFieldsDetailsTable_20_1.addCell(new Phrase(new Chunk("Tonne(s)",small_black_bold)));
	             
	             BillingFieldsDetailsTable_20_1.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
	             BillingFieldsDetailsTable_20_1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	             BillingFieldsDetailsTable_20_1.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
	             BillingFieldsDetailsTable_20_1.addCell(new Phrase(new Chunk("Revision No.",small_black_bold)));
	             
	             BillingFieldsDetailsTable_20_1.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
	             BillingFieldsDetailsTable_20_1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	             BillingFieldsDetailsTable_20_1.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
	             BillingFieldsDetailsTable_20_1.addCell(new Phrase(new Chunk("",small_black_bold)));
	             
	             BillingFieldsDetailsTable_20_1.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
	             BillingFieldsDetailsTable_20_1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	             BillingFieldsDetailsTable_20_1.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
	             BillingFieldsDetailsTable_20_1.addCell(new Phrase(new Chunk("",small_black_bold)));
	             
	             
	             
	             for(int i = 0 ; i < TRUCK_NO.size(); i++) {
	            	 
	            	 BillingFieldsDetailsTable_20_1.getDefaultCell().setBorder(Rectangle.BOX);
		             BillingFieldsDetailsTable_20_1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		             BillingFieldsDetailsTable_20_1.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		             BillingFieldsDetailsTable_20_1.addCell(new Phrase(new Chunk(CUSTOMER_ABBR.elementAt(i)+"",small_black_normal))); 
		             
		             BillingFieldsDetailsTable_20_1.getDefaultCell().setBorder(Rectangle.BOX);
		             BillingFieldsDetailsTable_20_1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		             BillingFieldsDetailsTable_20_1.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		             BillingFieldsDetailsTable_20_1.addCell(new Phrase(new Chunk(PLANT_NM.elementAt(i)+"",small_black_normal))); 
		             String tempCont = "";
		             if(CONTRACT_TYPE.elementAt(i).equals("S")) {
		            	 tempCont = "SN-"+SN_NO.elementAt(i);
					}else{ 
						tempCont = "LoA-"+SN_NO.elementAt(i);
					} 
		             
		             BillingFieldsDetailsTable_20_1.getDefaultCell().setBorder(Rectangle.BOX);
		             BillingFieldsDetailsTable_20_1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		             BillingFieldsDetailsTable_20_1.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		             BillingFieldsDetailsTable_20_1.addCell(new Phrase(new Chunk(tempCont+"",small_black_normal))); 
		             
		             BillingFieldsDetailsTable_20_1.getDefaultCell().setBorder(Rectangle.BOX);
		             BillingFieldsDetailsTable_20_1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		             BillingFieldsDetailsTable_20_1.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		             BillingFieldsDetailsTable_20_1.addCell(new Phrase(new Chunk(TRUCK_NO.elementAt(i)+"",small_black_normal))); 
		             
		             BillingFieldsDetailsTable_20_1.getDefaultCell().setBorder(Rectangle.BOX);
		             BillingFieldsDetailsTable_20_1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		             BillingFieldsDetailsTable_20_1.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		             BillingFieldsDetailsTable_20_1.addCell(new Phrase(new Chunk(TRUCK_SCH_TM.elementAt(i)+"",small_black_normal))); 
		             
		             BillingFieldsDetailsTable_20_1.getDefaultCell().setBorder(Rectangle.BOX);
		             BillingFieldsDetailsTable_20_1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		             BillingFieldsDetailsTable_20_1.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		             BillingFieldsDetailsTable_20_1.addCell(new Phrase(new Chunk(DRIVER_NM.elementAt(i)+"",small_black_normal))); 
		             
		             
		             BillingFieldsDetailsTable_20_1.getDefaultCell().setBorder(Rectangle.BOX);
		             BillingFieldsDetailsTable_20_1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		             BillingFieldsDetailsTable_20_1.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		             BillingFieldsDetailsTable_20_1.addCell(new Phrase(new Chunk(TRUCK_VOL.elementAt(i)+"",small_black_normal))); 
		             
		             BillingFieldsDetailsTable_20_1.getDefaultCell().setBorder(Rectangle.BOX);
		             BillingFieldsDetailsTable_20_1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		             BillingFieldsDetailsTable_20_1.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		             BillingFieldsDetailsTable_20_1.addCell(new Phrase(new Chunk(nf.format(Double.parseDouble(TRUCK_VOL.elementAt(i)+"") / convt_mmbtu_to_mt)+"",small_black_normal))); 
		             
		             BillingFieldsDetailsTable_20_1.getDefaultCell().setBorder(Rectangle.BOX);
		             BillingFieldsDetailsTable_20_1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		             BillingFieldsDetailsTable_20_1.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		             BillingFieldsDetailsTable_20_1.addCell(new Phrase(new Chunk(NOM_REV_NO.elementAt(i)+"",small_black_normal))); 
		             
		             BillingFieldsDetailsTable_20_1.getDefaultCell().setBorder(Rectangle.BOX);
		             BillingFieldsDetailsTable_20_1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		             BillingFieldsDetailsTable_20_1.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		             BillingFieldsDetailsTable_20_1.addCell(new Phrase(new Chunk(CHECKPOST_NM.elementAt(i)+"",small_black_normal)));
		             
		             BillingFieldsDetailsTable_20_1.getDefaultCell().setBorder(Rectangle.BOX);
		             BillingFieldsDetailsTable_20_1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		             BillingFieldsDetailsTable_20_1.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		             BillingFieldsDetailsTable_20_1.addCell(new Phrase(new Chunk(TRUCK_SCH_RMK.elementAt(i)+"",small_black_normal))); 
	             }
	             
	             
	             BillingFieldsDetailsTable_20_1.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
	             BillingFieldsDetailsTable_20_1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	             BillingFieldsDetailsTable_20_1.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
	             BillingFieldsDetailsTable_20_1.getDefaultCell().setColspan(6);
	             BillingFieldsDetailsTable_20_1.addCell(new Phrase(new Chunk("TOTAL",small_black_bold))); 
	             
	             BillingFieldsDetailsTable_20_1.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
	             BillingFieldsDetailsTable_20_1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
	             BillingFieldsDetailsTable_20_1.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
	             BillingFieldsDetailsTable_20_1.getDefaultCell().setColspan(1);
	             BillingFieldsDetailsTable_20_1.addCell(new Phrase(new Chunk(nf.format(tot_mmbtu)+"",small_black_bold)));
	             
	             BillingFieldsDetailsTable_20_1.getDefaultCell().setBorder(Rectangle.BOX);
	             BillingFieldsDetailsTable_20_1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
	             BillingFieldsDetailsTable_20_1.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
	             BillingFieldsDetailsTable_20_1.getDefaultCell().setColspan(1);
	             BillingFieldsDetailsTable_20_1.addCell(new Phrase(new Chunk(nf.format(tot_ton)+"",small_black_bold)));
	             
	             BillingFieldsDetailsTable_20_1.getDefaultCell().setBorder(Rectangle.BOX);
	             BillingFieldsDetailsTable_20_1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	             BillingFieldsDetailsTable_20_1.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
	             BillingFieldsDetailsTable_20_1.getDefaultCell().setColspan(1);
	             BillingFieldsDetailsTable_20_1.addCell(new Phrase(new Chunk("",small_black_bold)));
	             
	             BillingFieldsDetailsTable_20_1.getDefaultCell().setBorder(Rectangle.BOX);
	             BillingFieldsDetailsTable_20_1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	             BillingFieldsDetailsTable_20_1.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
	             BillingFieldsDetailsTable_20_1.getDefaultCell().setColspan(1);
	             BillingFieldsDetailsTable_20_1.addCell(new Phrase(new Chunk("",small_black_bold)));
	             
	             PdfPTable NoteInfoTable1 = new PdfPTable(1);
	 			NoteInfoTable1.setWidthPercentage(100);
	 			NoteInfoTable1.setHorizontalAlignment(Element.ALIGN_LEFT);
	 			NoteInfoTable1.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	 			NoteInfoTable1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
	 			NoteInfoTable1.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
	 			//RG20190325 For Company change*/ NoteInfoTable1.addCell(new Phrase(new Chunk("Thanking You,\n\nFor Hazira LNG Private Limited,\n\n\n\n"+supp_con_desig+"\n\n",small_black_normal)));
	 			NoteInfoTable1.addCell(new Phrase(new Chunk("Thanking You,\n\nFor Shell Energy India Private Limited,\n\n\n\n"+supp_con_desig+"\n\n",small_black_normal)));
	 			
	 			
	 			PdfPTable NoteInfoTable2 = new PdfPTable(1);
	 			NoteInfoTable2.setWidthPercentage(100);
	 			NoteInfoTable2.setHorizontalAlignment(Element.ALIGN_LEFT);
	 			NoteInfoTable2.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	 			NoteInfoTable2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
	 			NoteInfoTable2.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
	 			NoteInfoTable2.addCell(new Phrase(new Chunk("Authorised Signatory",small_black_bold)));
	             
	 			PdfPTable InfoTable3 = new PdfPTable(1);
	 			InfoTable3.setWidthPercentage(100);
	 			InfoTable3.setHorizontalAlignment(Element.ALIGN_LEFT);
	 			InfoTable3.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	 			InfoTable3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
	 			InfoTable3.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
	 			InfoTable3.addCell(new Phrase(new Chunk("Note: In case of late arrival of truck, kindly note that the Truck would be scheduled and accepted on the reasonable endeavour basis.",small_black_normal)));
	 			
	 			
	            PdfPTable InfoTable2 = new PdfPTable(1);
	 			InfoTable2.setWidthPercentage(100);
	 			InfoTable2.setHorizontalAlignment(Element.ALIGN_LEFT);
	 			InfoTable2.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	 			InfoTable2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	 			InfoTable2.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
	 			InfoTable2.addCell(new Phrase(new Chunk("This is a computer generated Report.",small_black_normal)));
	 			
	            document.add(HlplLogoTable);
	            document.add(new Paragraph("              "));
				document.add(BillingFields);
				document.add(new Paragraph("              "));
	            document.add(BillingFieldsInfoTable);
	            document.add(new Paragraph("              "));
	            document.add(BillingFieldsDetailsTable_1);
	            document.add(InfoTable);
	            document.add(new Paragraph("              "));
	            document.add(InfoTable1);
	            document.add(new Paragraph("              "));  
	            document.add(detail_header_table);       
	            document.add(new Paragraph("              "));  
	            document.add(BillingFieldsDetailsTable_20);
	            document.add(BillingFieldsDetailsTable_20_1);
	            document.add(new Paragraph("              "));  
	            document.add(NoteInfoTable1);
	            document.add(NoteInfoTable2);
	            document.add(new Paragraph("              "));  
	            document.add(InfoTable3);
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

		public String createPdfFileForTransporter()throws Exception
		{
			try
			{
//				System.out.println("gas_date----"+gas_date);
				HttpSession sess = request.getSession();
				nom_trans_pdf_path = sess.getAttribute("nom_transporter_pdf_path").toString();
				fileName = "";
				
				fileName = "TRANSPORTER_SELLER_NOM-"+gas_date.trim().substring(0,2)+gas_date.trim().substring(3,5)+gas_date.trim().substring(6)+"-"+trans_abbr+".pdf";
				
				nom_trans_pdf_path = nom_trans_pdf_path+"//"+fileName;
//				System.out.println("fileName = "+fileName+", nom_customer_pdf_path = "+nom_trans_pdf_path);
			}
			catch(Exception e)
			{
				//System.out.println("Exception in createPdfFileForCustomer() Method :\n"+e.getMessage());
				e.printStackTrace();
			}
			return nom_trans_pdf_path;
		}
		
		private void fetchPreviewSellerNomDetailsTrans()throws Exception {
			try {
				
				if(!trans_cd.equals("")) {
					
					String transNmSql = "select TRANS_ABBR,TRANS_NAME from DLNG_TRANS_MST where TRANS_CD = '"+trans_cd+"'";
					rset = stmt.executeQuery(transNmSql);
					if (rset.next()) {
						trans_abbr = rset.getString(1)==null?"":rset.getString(1);
						trans_name = rset.getString(2)==null?"":rset.getString(2);
					}
					String contPersSql = "SELECT FAX_1,CONTACT_PERSON FROM DLNG_TRANSPORTER_CONTACT_MST A " +
							  " WHERE A.TRANS_CD='"+trans_cd+"' "
							+ " AND SEQ_NO = '"+seq_no+"' "
							+ " AND A.ACTIVE_FLAG='Y' AND DEF_NOM_FLAG='Y' ";
//					System.out.println("contPersSql---"+contPersSql);
					rset = stmt.executeQuery(contPersSql);
					if (rset.next()) {
						to_fax_no = rset.getString(1)==null?"":rset.getString(1);
						temp_con_desig = rset.getString(2)==null?"":rset.getString(2);
					}
					
					queryString1 = "SELECT A.CONTACT_PERSON,A.FAX_1 FROM FMS7_SUPPLIER_CONTACT_MST A " +
				  			   "WHERE A.DEF_NOM_FLAG='Y' AND A.SEQ_NO="+supp_seq_no+" AND A.EFF_DT=(SELECT MAX(B.EFF_DT) FROM FMS7_SUPPLIER_CONTACT_MST B WHERE " +
				  			   "A.SEQ_NO=B.SEQ_NO AND B.EFF_DT<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
//					System.out.println("FMS7_SUPPLIER_CONTACT_MST Query hiren= "+queryString1);
					rset = stmt.executeQuery(queryString1);
					if(rset.next())
					{
						supp_con_desig = rset.getString(1)==null?"":rset.getString(1) ;
						from_fax_no  = rset.getString(2)==null?"":rset.getString(2);
					}
					
					String genSql = "select to_char(to_date('"+gas_date+"','dd/mm/yyyy')-1,'dd-mon-yy'),to_char(sysdate,'hh:mm') from dual ";
					rset = stmt.executeQuery(genSql);
					if(rset.next()) {
						
						gen_date = rset.getString(1)==null?"":rset.getString(1);
						gen_time = rset.getString(2)==null?"":rset.getString(2);
					}
					//Query for Contact Name,Telephone,Mobile of Supplier
					queryString1 = "SELECT A.SEQ_NO,A.CONTACT_PERSON,A.CONTACT_DESIG,A.PHONE,A.MOBILE  FROM FMS7_SUPPLIER_CONTACT_MST A " +
					   "WHERE A.EFF_DT=(SELECT MAX(B.EFF_DT) FROM FMS7_SUPPLIER_CONTACT_MST B WHERE " +
					   "A.SEQ_NO=B.SEQ_NO AND B.EFF_DT<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))"
					  //hardcoded by Hiren_20210130 As per mahesh Sir req.
		  			   + " AND A.CONTACT_PERSON like '%Shaily Anand%' ";
//					System.out.println("FMS7_SUPPLIER_CONTACT_MST Query Shaily Anand= "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						SUPP_SEQ_NO.add(rset1.getString(1)==null?"0":rset1.getString(1));
						String con_per = rset1.getString(2)==null?"":rset1.getString(2);
						String con_desig = rset1.getString(3)==null?"":rset1.getString(3);				
						//SUPP_CONTACT_PERSON.add(con_per+"-"+con_desig);
						SUPP_CONTACT_PERSON.add(con_per);
						PHONE.add(rset1.getString(4)==null?"":rset1.getString(4));
						MOBILE.add(rset1.getString(5)==null?"":rset1.getString(5));
					}
					
					queryString1 = "SELECT A.SEQ_NO,A.CONTACT_PERSON,A.CONTACT_DESIG,A.PHONE,A.MOBILE  FROM FMS7_SUPPLIER_CONTACT_MST A " +
							   "WHERE A.EFF_DT=(SELECT MAX(B.EFF_DT) FROM FMS7_SUPPLIER_CONTACT_MST B WHERE " +
							   "A.SEQ_NO=B.SEQ_NO AND B.EFF_DT<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))"
							  //hardcoded by Hiren_20210130 As per mahesh Sir req.
				  			   + " AND A.CONTACT_PERSON like '%Vijayakumar%' ";
//							System.out.println("FMS7_SUPPLIER_CONTACT_MST Query Vijayakumar= "+queryString1);
							rset1 = stmt1.executeQuery(queryString1);
							if(rset1.next())
							{
								SUPP_SEQ_NO.add(rset1.getString(1)==null?"0":rset1.getString(1));
//								con_per = rset1.getString(2)==null?"":rset1.getString(2);
//								con_desig = rset1.getString(3)==null?"":rset1.getString(3);				
								//SUPP_CONTACT_PERSON.add(con_per+"-"+con_desig);
								SUPP_CONTACT_PERSON.add(rset1.getString(2)==null?"":rset1.getString(2));
								PHONE.add(rset1.getString(4)==null?"":rset1.getString(4));
								MOBILE.add(rset1.getString(5)==null?"":rset1.getString(5));
							}
							
					/*for Transporter wise daily seller nomination details*/
					/*
					 * String maxRevSql = "select max(g_val),c_val from" +
					 * " (select TRUCK_NM c_val,REV_NO g_val" + " from DLNG_DAILY_TRUCK_SCH_DTL " +
					 * " WHERE TRANS_CD = '"+trans_cd+"' " +
					 * " AND SCH_DT = TO_DATE('"+gas_date+"','DD/MM/YYYY')) group by c_val";
					 */
					String maxRevSql = "select max(a_val),b_val "
							+ " from(select rev_no a_val,SCH_ID b_val"
							+ " from DLNG_DAILY_TRUCK_SCH_DTL  WHERE TRANS_CD = '"+trans_cd+"'  "
							+ " AND SCH_DT = TO_DATE('"+gas_date+"','DD/MM/YYYY')"
							+ ") group by b_val";
//					System.out.println("maxRevSql---"+maxRevSql);
					rset1 = stmt1.executeQuery(maxRevSql); 
					while (rset1.next()) {
						
						String rev_no = rset1.getString(1)==null?"":rset1.getString(1);
						String sch_id = rset1.getString(2)==null?"":rset1.getString(2);
		
						String nomData = "select SCH_ID,CONTRACT_TYPE,TRUCK_NM,SCH_TIME,nvl(TRUCK_VOL,0),"
								+ " REMARKS from DLNG_DAILY_TRUCK_SCH_DTL WHERE"
								+ " TRANS_CD = '"+trans_cd+"'"
								+ " AND SCH_DT = TO_DATE('"+gas_date+"','DD/MM/YYYY')"
								+ " AND  REV_NO = '"+rev_no+"'"
								+ " AND SCH_ID = '"+sch_id+"'";
//						System.out.println("nomData---"+nomData);
						rset2 = stmt2.executeQuery(nomData); 
						while (rset2.next()) {
							
							String cust_cd = "",agr_no="",agr_rev_no = "",cont_no = "", plant_no = ""; 
							if(sch_id.contains("-")) {
								
								
								CONTRACT_TYPE.add(rset2.getString(2)==null?"":rset2.getString(2));
								TRUCK_NO.add(rset2.getString(3)==null?"":rset2.getString(3));
								TRUCK_SCH_TM.add(rset2.getString(4)==null?"":rset2.getString(4));
								TRUCK_VOL.add(rset2.getString(5)==null?"":rset2.getString(5));
								tot_mmbtu+=rset2.getDouble(5);
								TRUCK_SCH_RMK.add(rset2.getString(6)==null?"":rset2.getString(6));
								String cont_type = rset2.getString(2)==null?"":rset2.getString(2);
								NOM_REV_NO.add(rev_no);
								String tempSch [] = sch_id.split("-");
								cust_cd = tempSch [0];
								agr_no = tempSch [1];
								agr_rev_no = tempSch [2];
								cont_no = tempSch [3];
								plant_no = tempSch [6];
								
								SN_NO.add(cont_no);
								
								String custDtl = "select CUSTOMER_ABBR from FMS7_CUSTOMER_MST where "
										+ " CUSTOMER_CD = '"+cust_cd+"' ";
								rset3 = stmt3.executeQuery(custDtl);
								if(rset3.next()) {
									CUSTOMER_ABBR.add(rset3.getString(1)==null?"":rset3.getString(1));
									CUSTOMER_CD.add(cust_cd);
								}else {
									CUSTOMER_ABBR.add("");
									CUSTOMER_CD.add("");
								}
								
								queryString1 = "SELECT A.PLANT_NAME FROM FMS7_CUSTOMER_PLANT_DTL A " +
										   "WHERE A.CUSTOMER_CD='"+cust_cd+"' AND A.SEQ_NO ='"+plant_no+"' AND " +
										   "A.EFF_DT=(SELECT MAX(B.EFF_DT) FROM FMS7_CUSTOMER_PLANT_DTL B WHERE B.CUSTOMER_CD=A.CUSTOMER_CD AND " +
										   "A.SEQ_NO=B.SEQ_NO AND B.EFF_DT<=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND A.CUSTOMER_CD='"+cust_cd+"' AND A.SEQ_NO ='"+plant_no+"')";
	//							System.out.println("FMS7_CUSTOMER_CONTACT_MST Query = "+queryString1);
								rset3 = stmt3.executeQuery(queryString1);
								if(rset3.next())
								{	
									PLANT_NM.add(rset3.getString(1)==null?"":rset3.getString(1));
								}
								else
								{
									PLANT_NM.add("");
								}
								
								fetchtruckTransDriverDtl(rset2.getString(3));
							
								DRIVER_NM.add(truck_driver);
								TRUCK_LINKED_FLG.add(truckLinkedFlg);
								String temp_chkpost_nm = "";
								
								String fetchLinkedChkSql = "select A.CHKPOST_CD,B.CHKPOST_NAME from"
										+ "  DLNG_LINK_CUST_CHECKPOST A,DLNG_CHECKPOST_MST B where "
										+ " A.CUSTOMER_CD = '"+cust_cd+"'"
										+ " AND A.AGR_NO = '"+agr_no+"'"
										+ " AND A.AGR_REV_NO = '"+agr_rev_no+"'"
										+ " AND A.CONT_NO = '"+cont_no+"'"
										+ " AND A.CONT_TYPE = '"+cont_type+"'"
										+ " AND A.PLANT_CD = '"+plant_no+"'"
										+ " AND A.STATUS = 'Y' "
										+ " AND A.CHKPOST_CD = B.CHKPOST_CD";
//									System.out.println("fetchLinkedChkSql-----"+fetchLinkedChkSql);
									rset3 = stmt3.executeQuery(fetchLinkedChkSql);
									while(rset3.next()) {
										temp_chkpost_nm+=rset3.getString(2)==null?"":rset3.getString(2)+",";
									}
									if(!temp_chkpost_nm.equalsIgnoreCase("")) {
										temp_chkpost_nm = temp_chkpost_nm.substring(0,temp_chkpost_nm.length()-1);
									}
									CHECKPOST_NM.add(temp_chkpost_nm);
								
							}
						}
					}
					tot_ton+=tot_mmbtu/convt_mmbtu_to_mt;
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		Vector Vtrans_cd = new Vector();
		Vector Vtrans_abbr = new Vector();
		Vector Vtrans_nm = new Vector();
		Vector Vtrans_pers_cnt = new Vector();
		Vector Vtrans_nom_cnt = new Vector();
		
		Vector Vcont_pers_nm = new Vector();
		Vector Vcont_pers_seq = new Vector();
		Vector Vcont_pers_desig = new Vector();
		
		private void fetchSellerNomDetailsTrans()throws Exception {
			try {
				
				String transDtlSql = "SELECT DISTINCT(A.TRANS_CD),B.TRANS_ABBR,B.TRANS_NAME FROM DLNG_DAILY_TRUCK_SCH_DTL A,DLNG_TRANS_MST B "
						+ " WHERE A.SCH_DT = TO_DATE('"+gas_date+"','DD/MM/YYYY') "
						+ " AND A.TRANS_CD = B.TRANS_CD "
						+ "ORDER BY A.TRANS_CD";
//				System.out.println("transDtlSql----"+transDtlSql);
				rset = stmt.executeQuery(transDtlSql);
				while (rset.next()) {
					
					int pers_cnt = 0;
					int nom_cnt= 0;
					
					Vtrans_cd.add(rset.getString(1)==null?"":rset.getString(1));
					Vtrans_abbr.add(rset.getString(2)==null?"":rset.getString(2));
					Vtrans_nm.add(rset.getString(3)==null?"":rset.getString(3));
					
					/*for Transporter Contact master*/
					String distSeqSql = "select distinct(seq_no) from DLNG_TRANSPORTER_CONTACT_MST A"
							+ " WHERE A.TRANS_CD='"+rset.getString(1)+"'  AND A.ACTIVE_FLAG='Y' AND DEF_NOM_FLAG='Y'";
					rset1 = stmt1.executeQuery(distSeqSql);
					while (rset1.next()) {
					
						String contPersSql = "SELECT A.CONTACT_PERSON,A.CONTACT_DESIG FROM DLNG_TRANSPORTER_CONTACT_MST A " +
								  " WHERE A.TRANS_CD='"+rset.getString(1)+"'  AND A.ACTIVE_FLAG='Y' AND DEF_NOM_FLAG='Y' "
								+ " AND A.SEQ_NO = '"+rset1.getString(1)+"'"
								+ " AND A.EFF_DT=(SELECT MAX(B.EFF_DT) FROM DLNG_TRANSPORTER_CONTACT_MST B WHERE "
								+ " B.TRANS_CD='"+rset.getString(1)+"'  AND B.ACTIVE_FLAG='Y' AND B.DEF_NOM_FLAG='Y' "
								+ " AND B.EFF_DT<=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND B.SEQ_NO='"+rset1.getString(1)+"') order by A.SEQ_NO";
//						System.out.println("contPersSql---"+contPersSql);
						rset2 = stmt2.executeQuery(contPersSql);
						if (rset2.next()) {
							pers_cnt++;
							Vcont_pers_seq.add(rset1.getString(1)==null?"":rset1.getString(1));
							Vcont_pers_nm.add(rset2.getString(1)==null?"":rset2.getString(1));
							Vcont_pers_desig.add(rset2.getString(2)==null?"":rset2.getString(2));
						}
					}
					Vtrans_pers_cnt.add(pers_cnt);
					
					/*for Transporter wise daily seller nomination details*/
					String nomData = "select DISTINCT(SCH_ID),CONTRACT_TYPE from DLNG_DAILY_TRUCK_SCH_DTL WHERE"
							+ " TRANS_CD = '"+rset.getString(1)+"'"
							+ " AND SCH_DT = TO_DATE('"+gas_date+"','DD/MM/YYYY') ";
//					System.out.println("nomData---"+nomData);
					rset1 = stmt1.executeQuery(nomData); 
					while (rset1.next()) {
						
						nom_cnt++;
						String sch_id = rset1.getString(1)==null?"":rset1.getString(1);
						CONTRACT_TYPE.add(rset1.getString(2)==null?"":rset1.getString(2));
						
						String cust_cd = "",agr_no="",agr_rev_no = "",cont_no = "", plant_no = ""; 
						if(sch_id.contains("-")) {
							
							String tempSch [] = sch_id.split("-");
							cust_cd = tempSch [0];
							agr_no = tempSch [1];
							agr_rev_no = tempSch [2];
							cont_no = tempSch [3];
							plant_no = tempSch [6];
							
							SN_NO.add(cont_no);
							
							String custDtl = "select CUSTOMER_ABBR from FMS7_CUSTOMER_MST where "
									+ " CUSTOMER_CD = '"+cust_cd+"' ";
							rset2 = stmt2.executeQuery(custDtl);
							if(rset2.next()) {
								CUSTOMER_ABBR.add(rset2.getString(1)==null?"":rset2.getString(1));
								CUSTOMER_CD.add(cust_cd);
							}else {
								CUSTOMER_ABBR.add("");
								CUSTOMER_CD.add("");
							}
							
							queryString1 = "SELECT A.PLANT_NAME FROM FMS7_CUSTOMER_PLANT_DTL A " +
									   "WHERE A.CUSTOMER_CD='"+cust_cd+"' AND A.SEQ_NO ='"+plant_no+"' AND " +
									   "A.EFF_DT=(SELECT MAX(B.EFF_DT) FROM FMS7_CUSTOMER_PLANT_DTL B WHERE B.CUSTOMER_CD=A.CUSTOMER_CD AND " +
									   "A.SEQ_NO=B.SEQ_NO AND B.EFF_DT<=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND A.CUSTOMER_CD='"+cust_cd+"' AND A.SEQ_NO ='"+plant_no+"')";
//							System.out.println("FMS7_CUSTOMER_CONTACT_MST Query = "+queryString1);
							rset2 = stmt2.executeQuery(queryString1);
							if(rset2.next())
							{	
								PLANT_NM.add(rset2.getString(1)==null?"":rset2.getString(1));
							}
							else
							{
								PLANT_NM.add("");
							}
						}
					}
					Vtrans_nom_cnt.add(nom_cnt);
				
					String fetchRmkSql = "select REMARKS from DLNG_TRANS_DAILYNOM_REMARK"
							+ " where TRANS_CD = '"+rset.getString(1)+"' "
							+ " and GAS_DATE = to_date('"+gas_date+"','dd/mm/yyyy')";
					rset1 = stmt1.executeQuery(fetchRmkSql);
					if(rset1.next()) {
						REMARKS.add(rset1.getString(1)==null?"":rset1.getString(1));
					}else {
						REMARKS.add("");
					}
				}
				
				queryString1 = "SELECT A.SEQ_NO,A.CONTACT_PERSON,A.CONTACT_DESIG FROM FMS7_SUPPLIER_CONTACT_MST A " +
			  			   " WHERE A.DEF_NOM_FLAG='Y' and A.ACTIVE_FLAG='Y' AND " +
			  			   " A.EFF_DT=(SELECT MAX(B.EFF_DT) FROM FMS7_SUPPLIER_CONTACT_MST B WHERE " +
			  			   " A.SEQ_NO=B.SEQ_NO AND B.EFF_DT<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
//				System.out.println("FMS7_SUPPLIER_CONTACT_MST Query = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				while(rset1.next())
				{
					SUPP_SEQ_NO.add(rset1.getString(1)==null?"0":rset1.getString(1));
					SUPP_CONTACT_PERSON.add(rset1.getString(2)==null?"0":rset1.getString(2));
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}

		public Vector CUSTOMER_CD = new Vector();
		public Vector CUST_CD = new Vector();
		public Vector FGSA_NO = new Vector();
		public Vector SN_NO = new Vector();
		public Vector SN_NO_REF_DISPLAY = new Vector();//MD 20100922
		public Vector DEVIATION_SN_NO_REF_DISPLAY = new Vector();//MD 20100922NOM_REV_NO
		public Vector DEVIATION_NOM_REV_NO = new Vector();//MD 20100922
		public Vector CONTRACT_TYPE = new Vector();
		public Vector CUSTOMER_ABBR = new Vector();
		public Vector CUSTOMER_NAME = new Vector();
		
		public Vector SEQ_NO = new Vector();
		public Vector CONTACT_PERSON = new Vector();
		public Vector SUPP_SEQ_NO = new Vector();
		public Vector SUPP_CONTACT_PERSON = new Vector();
		public Vector PLANT_SEQ_NO = new Vector();
		public Vector MAPPING_ID=new Vector();
		public Vector NOM_REV_NO = new Vector();
		public Vector PLANT_NM = new Vector();
		public Vector PLANT_ABBR = new Vector();
		public Vector REMARKS = new Vector();
		public Vector sn_ref_no = new Vector();
		public Vector CHECKPOST_NM = new Vector();
		
		public String remarks ="";
		String seq_no="";
		String supp_seq_no="";
		String plant_seq_no = "";
		String nom_rev_no = "";
		public String cont_mapping_id="";
		
		private void fetchDailySellerNomDetails()throws SQLException,IOException {

			methodName = "fetchDailySellerNomDetails()";
			String cust_cd="0";
			String fgsa="0";
			String sn="0";
			String cont_type="";
			String plant_no="0";
			String con_per = "";
			String con_desig ="";
			String map_id="";
			try 
			{
				queryString = "SELECT DISTINCT SCH_ID,CONTRACT_TYPE,MAPPING_ID "
						+ " FROM DLNG_DAILY_SCH "
						+ " WHERE SCH_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') "
						+ " ORDER BY SCH_ID ";
//				System.out.println("DLNG_DAILY_SCH Query ===== "+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					String sch_id=rset.getString(1)==null?"0-0-0-0-0-0-0":rset.getString(1);
					
					String tempSch [] = sch_id.split("-");
					
					CUSTOMER_CD.add(tempSch[0]);
					FGSA_NO.add(tempSch[1]);
					SN_NO.add(tempSch[3]);
					CONTRACT_TYPE.add(rset.getString(2)==null?"":rset.getString(2));
					PLANT_SEQ_NO.add(tempSch[6]);
					cust_cd = tempSch[0];
					fgsa = tempSch[1];
					sn = tempSch[3];
					cont_type=rset.getString(2)==null?"":rset.getString(2);
					plant_no=tempSch[6];
					MAPPING_ID.add(rset.getString(3)==null?" ":rset.getString(3));
					map_id=rset.getString(3)==null?" ":rset.getString(3);
					//System.out.println("CUSTOMER_CD   =  "+CUSTOMER_CD);
					//System.out.println("PLANT_SEQ_NO   =  "+PLANT_SEQ_NO);
					
					queryString1 = "SELECT MAX(A.REV_NO) FROM DLNG_DAILY_SCH A " +
								   "WHERE A.SCH_ID='"+sch_id+"' AND A.SCH_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY')";
//					System.out.println("Query for fetching Maximum Nom. Rev. No. From DLNG_DAILY_SCH = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{	
						NOM_REV_NO.add(rset1.getString(1)==null?"":rset1.getString(1));
					}
					else
					{
						NOM_REV_NO.add("");
					}
		
					Vector temp_SEQ_NO = new Vector();
					Vector temp_CONTACT_PERSON = new Vector();
					
					queryString1 = "SELECT A.PLANT_NAME FROM FMS7_CUSTOMER_PLANT_DTL A " +
								   "WHERE A.CUSTOMER_CD='"+cust_cd+"' AND A.SEQ_NO ='"+tempSch[6]+"' AND " +
								   "A.EFF_DT=(SELECT MAX(B.EFF_DT) FROM FMS7_CUSTOMER_PLANT_DTL B WHERE B.CUSTOMER_CD=A.CUSTOMER_CD AND " +
								   "A.SEQ_NO=B.SEQ_NO AND B.EFF_DT<=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND A.CUSTOMER_CD='"+cust_cd+"' AND A.SEQ_NO ='"+tempSch[6]+"')";
//					System.out.println("FMS7_CUSTOMER_CONTACT_MST Query = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{	
						PLANT_NM.add(rset1.getString(1)==null?"":rset1.getString(1));
					}
					else
					{
						PLANT_NM.add("");
					}
					//System.out.println("PLANT_NM   =  "+PLANT_NM);
					
					queryString1 = "SELECT A.SEQ_NO,A.CONTACT_PERSON,A.CONTACT_DESIG FROM FMS7_CUSTOMER_CONTACT_MST A " +
								  "WHERE A.CUSTOMER_CD='"+cust_cd+"'  AND A.ACTIVE_FLAG='Y' AND DEF_NOM_FLAG='Y' AND " +
								  "A.EFF_DT=(SELECT MAX(B.EFF_DT) FROM FMS7_CUSTOMER_CONTACT_MST B WHERE B.CUSTOMER_CD=A.CUSTOMER_CD AND " +
								  "A.SEQ_NO=B.SEQ_NO AND B.EFF_DT<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
//					System.out.println("FMS7_CUSTOMER_CONTACT_MST Query = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					while(rset1.next())
					{					
						temp_SEQ_NO.add(rset1.getString(1)==null?"0":rset1.getString(1));
						con_per = rset1.getString(2)==null?"":rset1.getString(2);
						con_desig = rset1.getString(3)==null?"":rset1.getString(3);
						if(!con_per.trim().equals(""))  //  || !con_desig.trim().equals("")
						{
							//temp_CONTACT_PERSON.add((con_per)+"-"+(con_desig));
							temp_CONTACT_PERSON.add((con_per));
						}
						else
						{
							temp_CONTACT_PERSON.add("--Select--");
						}
					}
					
					if(temp_SEQ_NO.size()<=0)
					{
						temp_SEQ_NO.add("0");
						temp_CONTACT_PERSON.add("--Select--");
					}
					
					SEQ_NO.add(temp_SEQ_NO);
					CONTACT_PERSON.add(temp_CONTACT_PERSON);
				}
							
				for(int i=0;i<CUSTOMER_CD.size();i++)	
				{
					queryString = "SELECT DISTINCT CUSTOMER_ABBR FROM FMS7_CUSTOMER_MST " +
					  			  "WHERE CUSTOMER_CD="+CUSTOMER_CD.elementAt(i)+" ";
//					System.out.println("FMS7_CUSTOMER_MST Query = "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						CUSTOMER_ABBR.add(rset.getString(1)==null?"":rset.getString(1));
					}
					
					queryString = "SELECT REMARKS FROM DLNG_SELLER_DAILYNOM_REMARK WHERE CUST_CD="+CUSTOMER_CD.elementAt(i)+"  AND " +
								  "GAS_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND CONTRACT_TYPE='"+CONTRACT_TYPE.elementAt(i)+"' AND " +
								  "FLSA_NO='"+FGSA_NO.elementAt(i)+"' AND SN_NO='"+SN_NO.elementAt(i)+"' AND " +
								  "PLANT_SEQ_NO='"+PLANT_SEQ_NO.elementAt(i)+"' ";
					if(cont_type.equalsIgnoreCase("T") || cont_type.equalsIgnoreCase("C"))
					{
						queryString+=" AND MAPPING_ID='"+MAPPING_ID.elementAt(i)+"'";
					}
					
					
					
//					System.out.println("REMARKS Query = "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						remarks =  rset.getString(1)==null?"":rset.getString(1);
						REMARKS.add(rset.getString(1)==null?" ":rset.getString(1));					
					}
					else
					{
						REMARKS.add(" ");
						remarks = "";
					}
				}			
				
				/*queryString1 = "SELECT A.SEQ_NO,A.CONTACT_PERSON,A.CONTACT_DESIG FROM FMS7_SUPPLIER_CONTACT_MST A " +
				  			   "WHERE A.DEF_NOM_FLAG='Y' AND A.ACTIVE_FLAG='Y' AND " +
				  			   "A.EFF_DT=(SELECT MAX(B.EFF_DT) FROM FMS7_SUPPLIER_CONTACT_MST B WHERE " +
				  			   "A.SEQ_NO=B.SEQ_NO AND B.EFF_DT<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";*/
				queryString1 = "SELECT A.SEQ_NO,A.CONTACT_PERSON,A.CONTACT_DESIG FROM FMS7_SUPPLIER_CONTACT_MST A " +
			  			   "WHERE A.DEF_NOM_FLAG='Y' and A.ACTIVE_FLAG='Y' AND " +
			  			   "A.EFF_DT=(SELECT MAX(B.EFF_DT) FROM FMS7_SUPPLIER_CONTACT_MST B WHERE " +
			  			   "A.SEQ_NO=B.SEQ_NO AND B.EFF_DT<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
//				System.out.println("FMS7_SUPPLIER_CONTACT_MST Query = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				while(rset1.next())
				{
					SUPP_SEQ_NO.add(rset1.getString(1)==null?"0":rset1.getString(1));
					con_per = rset1.getString(2)==null?"":rset1.getString(2);
					con_desig = rset1.getString(3)==null?"":rset1.getString(3);				
					//SUPP_CONTACT_PERSON.add(con_per+"-"+con_desig);
					SUPP_CONTACT_PERSON.add(con_per);
				}
				
				if(SUPP_SEQ_NO.size()<=0)
				{
					SUPP_SEQ_NO.add("0");
					SUPP_CONTACT_PERSON.add("--Select--");
				}
				
				for(int i=0; i<CONTRACT_TYPE.size(); i++)
				{
					if((""+CONTRACT_TYPE.elementAt(i)).trim().equals("S"))
					{
						queryString2 = "SELECT SN_REF_NO " +
									  "FROM DLNG_SN_MST WHERE " +
						  			  "flsa_no="+FGSA_NO.elementAt(i)+" AND " +
						  			  "sn_no="+SN_NO.elementAt(i)+" AND sn_ref_no IS NOT NULL AND " +
						  			  "customer_cd="+CUSTOMER_CD.elementAt(i)+" ORDER BY sn_rev_no DESC";
//						System.out.println("SELECT SN_REF_NO FROM DLNG_SN_MST WHERE = "+queryString2);
						rset2 = stmt2.executeQuery(queryString2);
						if(rset2.next())
						{
							sn_ref_no.add(rset2.getString(1)==null?" ":rset2.getString(1));
						}
						else
						{
							sn_ref_no.add(" ");
						}
					}
					else if((""+CONTRACT_TYPE.elementAt(i)).trim().equals("L"))
					{

						queryString2 = "SELECT LOA_REF_NO " +
									  "FROM DLNG_LOA_MST WHERE " +
									  "tender_no="+FGSA_NO.elementAt(i)+" AND " +
									  "loa_no="+SN_NO.elementAt(i)+" AND LOA_REF_NO IS NOT NULL AND " +
									  "customer_cd="+CUSTOMER_CD.elementAt(i)+" ORDER BY loa_rev_no DESC";
//						System.out.println("SELECT SN_REF_NO FROM LOA Master = "+queryString2);
						rset2 = stmt2.executeQuery(queryString2);
						if(rset2.next())
						{
							sn_ref_no.add(rset2.getString(1)==null?" ":rset2.getString(1));
						}
						else
						{
							sn_ref_no.add(" ");
						}
					}
					else
					{
						sn_ref_no.add("");
					}
				}
			}
			catch(Exception e)
			{
				System.out.println("EXCEPTION IN Method : "+methodName+" Of "+databeanName+" DataBean = "+e.getMessage());
				e.printStackTrace();
			}
		}
		public String daily_Total_Dcq = "";
		public String temp_con_desig ="";
		public String supp_con_desig ="";
		public String to_fax_no ="";
		public String from_fax_no ="";
		public String cust_nm	 ="";
		public String cust_abbr  ="";
		public String fgsa_dt ="";
		public String contract_dt ="";
		public String seller_nom_clause = "";
		public String tender_seller_nom_clause = "";
		String ltcora_dt="";
		String ltcora_seller_nom_clause="";
		String ltcora_no="";
		public Vector TRANS_CD = new Vector();
		public Vector TRANS_COUNT = new Vector();
		public Vector MOBILE = new Vector();
		public Vector PHONE = new Vector();
		public Vector TRANSPORTER_CD = new Vector();
		public Vector TRANS_NM = new Vector();
		public Vector TRANS_ABBR = new Vector();
		public Vector QTY_MMBTU = new Vector();
		public Vector TRUCK_NO = new Vector();
		public Vector TRUCK_VOL = new Vector();
		public Vector TRUCK_SCH_TM = new Vector();
		public Vector TRUCK_SCH_DT = new Vector();
		public Vector TRUCK_SCH_RMK = new Vector();
		public Vector DRIVER_NM = new Vector();
		public Vector TRUCK_LINKED_FLG = new Vector();
		
		public String gen_time ="";
		public String gas_dt ="";
		double tot_mmbtu=0;
		double tot_ton=0;
		double convt_mmbtu_to_mt=0;
		
		public void fetchSellerNomDetails() throws SQLException,IOException
		{
			methodName = "fetchSellerNomDetails()";		
			String con_per = "";
			String con_desig ="";
			
			//System.out.println("plant_seq_no in DataBean ====== "+plant_seq_no);
			//Vector temp_plant_seq_no =new Vector();
			try 
			{	
				//Query for Contact Name,Fax_No as To Customer getting by selection from 1st Page
				queryString1 = "SELECT A.SEQ_NO,A.CONTACT_PERSON,A.CONTACT_DESIG,A.FAX_1 FROM FMS7_CUSTOMER_CONTACT_MST A " +
							  "WHERE A.CUSTOMER_CD='"+customer_cd+"' AND A.DEF_NOM_FLAG='Y' AND A.SEQ_NO="+seq_no+" AND " +
							  "A.EFF_DT=(SELECT MAX(B.EFF_DT) FROM FMS7_CUSTOMER_CONTACT_MST B WHERE B.CUSTOMER_CD=A.CUSTOMER_CD AND " +
							  "A.SEQ_NO=B.SEQ_NO AND B.EFF_DT<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
//				System.out.println("FMS7_CUSTOMER_CONTACT_MST Query = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				if(rset1.next())
				{					
					seq_no = rset1.getString(1)==null?"0":rset1.getString(1);
					con_per = rset1.getString(2)==null?"":rset1.getString(2);
					con_desig = rset1.getString(3)==null?"":rset1.getString(3);
					//temp_con_desig = con_per+"-"+con_desig;	
					temp_con_desig = con_per;	
					to_fax_no  = rset1.getString(4)==null?"":rset1.getString(4);
				}
				
				
				//Query for Customer company Name,company Abbr as To Customer getting by selection from 1st Page
				queryString1 = "SELECT A.CUSTOMER_NAME, A.CUSTOMER_ABBR FROM FMS7_CUSTOMER_MST A " +
							  "WHERE A.CUSTOMER_CD='"+customer_cd+"' AND A.EFF_DT=(SELECT MAX(B.EFF_DT) FROM FMS7_CUSTOMER_MST B " +
							  "WHERE B.CUSTOMER_CD=A.CUSTOMER_CD AND B.EFF_DT<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
//				System.out.println("FMS7_CUSTOMER_CONTACT_MST Query = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				if(rset1.next())
				{					
					cust_nm = rset1.getString(1)==null?"0":rset1.getString(1);
					cust_abbr = rset1.getString(2)==null?"":rset1.getString(2);				
				}
				
				
				//Query for Contact Name,Fax_No as From Supplier getting by selection from 1st Page
				queryString1 = "SELECT A.SEQ_NO,A.CONTACT_PERSON,A.CONTACT_DESIG,A.FAX_1 FROM FMS7_SUPPLIER_CONTACT_MST A " +
				  			   "WHERE A.DEF_NOM_FLAG='Y' AND A.SEQ_NO="+supp_seq_no+" AND A.EFF_DT=(SELECT MAX(B.EFF_DT) FROM FMS7_SUPPLIER_CONTACT_MST B WHERE " +
				  			   "A.SEQ_NO=B.SEQ_NO AND B.EFF_DT<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
//				System.out.println("FMS7_SUPPLIER_CONTACT_MST Query hiren= "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				if(rset1.next())
				{
					supp_seq_no = rset1.getString(1)==null?"0":rset1.getString(1) ;
					con_per = rset1.getString(2)==null?"":rset1.getString(2);
					con_desig = rset1.getString(3)==null?"":rset1.getString(3);				
					//supp_con_desig = con_per+"-"+con_desig;
					supp_con_desig = con_per;
					from_fax_no  = rset1.getString(4)==null?"":rset1.getString(4);
				}
				
				
				//Query for Contact Name,Telephone,Mobile of Supplier
				queryString1 = "SELECT A.SEQ_NO,A.CONTACT_PERSON,A.CONTACT_DESIG,A.PHONE,A.MOBILE  FROM FMS7_SUPPLIER_CONTACT_MST A " +
				   "WHERE A.EFF_DT=(SELECT MAX(B.EFF_DT) FROM FMS7_SUPPLIER_CONTACT_MST B WHERE " +
				   "A.SEQ_NO=B.SEQ_NO AND B.EFF_DT<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))"
				  //hardcoded by Hiren_20210130 As per mahesh Sir req.
	  			   + " AND A.CONTACT_PERSON like '%Shaily Anand%' ";
//				System.out.println("FMS7_SUPPLIER_CONTACT_MST Query Shaily Anand= "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				if(rset1.next())
				{
					SUPP_SEQ_NO.add(rset1.getString(1)==null?"0":rset1.getString(1));
					con_per = rset1.getString(2)==null?"":rset1.getString(2);
					con_desig = rset1.getString(3)==null?"":rset1.getString(3);				
					//SUPP_CONTACT_PERSON.add(con_per+"-"+con_desig);
					SUPP_CONTACT_PERSON.add(con_per);
					PHONE.add(rset1.getString(4)==null?"":rset1.getString(4));
					MOBILE.add(rset1.getString(5)==null?"":rset1.getString(5));
				}
				
				queryString1 = "SELECT A.SEQ_NO,A.CONTACT_PERSON,A.CONTACT_DESIG,A.PHONE,A.MOBILE  FROM FMS7_SUPPLIER_CONTACT_MST A " +
						   "WHERE A.EFF_DT=(SELECT MAX(B.EFF_DT) FROM FMS7_SUPPLIER_CONTACT_MST B WHERE " +
						   "A.SEQ_NO=B.SEQ_NO AND B.EFF_DT<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))"
						  //hardcoded by Hiren_20210130 As per mahesh Sir req.
			  			   + " AND A.CONTACT_PERSON like '%Vijayakumar%' ";
//						System.out.println("FMS7_SUPPLIER_CONTACT_MST Query Vijayakumar= "+queryString1);
						rset1 = stmt1.executeQuery(queryString1);
						if(rset1.next())
						{
							SUPP_SEQ_NO.add(rset1.getString(1)==null?"0":rset1.getString(1));
							con_per = rset1.getString(2)==null?"":rset1.getString(2);
							con_desig = rset1.getString(3)==null?"":rset1.getString(3);				
							//SUPP_CONTACT_PERSON.add(con_per+"-"+con_desig);
							SUPP_CONTACT_PERSON.add(con_per);
							PHONE.add(rset1.getString(4)==null?"":rset1.getString(4));
							MOBILE.add(rset1.getString(5)==null?"":rset1.getString(5));
						}
				//	Query for Date of Contracted Agreement
				if(cont_type.equalsIgnoreCase("S"))
				{
					queryString = "SELECT TO_CHAR(SIGNING_DT,'DD-Mon-YY') FROM DLNG_SN_MST " +
					  "WHERE CUSTOMER_CD='"+customer_cd+"' AND FLSA_NO='"+fgsa_no+"' AND SN_NO='"+sn_no+"'";
//					System.out.println("SIGNING_DT of SN Query = "+queryString);
					rset = stmt.executeQuery(queryString);			
					if(rset.next())
					{
						contract_dt = rset.getString(1)==null?"":rset.getString(1);
					}
					queryString = "SELECT TO_CHAR(A.SIGNING_DT,'DD-Mon-YY'), A.SELLER_NOM_CLAUSE  FROM DLNG_FLSA_MST A " +
					  "WHERE A.CUSTOMER_CD='"+customer_cd+"' AND A.FLSA_NO='"+fgsa_no+"' " +
					  "AND A.REV_NO=(SELECT max(B.REV_NO) FROM DLNG_FLSA_MST B WHERE A.CUSTOMER_CD=B.CUSTOMER_CD AND A.FLSA_NO=B.FLSA_NO)";
//					System.out.println("SIGNING_DT of FGSA Query = "+queryString);
					rset = stmt.executeQuery(queryString);			
					if(rset.next())
					{
						fgsa_dt = rset.getString(1)==null?"":rset.getString(1);
						seller_nom_clause = rset.getString(2)==null?"":rset.getString(2);
					}
//					System.out.println("seller_nom_clause = "+seller_nom_clause);
				}
				else if(cont_type.equalsIgnoreCase("L"))
				{
					queryString = "SELECT TO_CHAR(SIGNING_DT,'DD-Mon-YY') FROM DLNG_LOA_MST " +
					  "WHERE CUSTOMER_CD='"+customer_cd+"' AND TENDER_NO='"+fgsa_no+"' AND LOA_NO='"+sn_no+"'";
//					System.out.println("SIGNING_DT of SN Query = "+queryString);
					rset = stmt.executeQuery(queryString);			
					if(rset.next())
					{
						contract_dt = rset.getString(1)==null?"":rset.getString(1);
					}
					
					queryString = "SELECT TO_CHAR(SIGNING_DT,'DD-Mon-YY'),SELLER_NOM_CLAUSE FROM DLNG_TENDER_MST " +
					  "WHERE CUSTOMER_CD='"+customer_cd+"' AND TENDER_NO='"+fgsa_no+"' ";
//					System.out.println("SIGNING_DT of TENDER Query = "+queryString);
					rset = stmt.executeQuery(queryString);			
					if(rset.next())
					{
						fgsa_dt = rset.getString(1)==null?"":rset.getString(1);
						tender_seller_nom_clause = rset.getString(2)==null?"":rset.getString(2);
					}	
//					System.out.println("tender_seller_nom_clause = "+tender_seller_nom_clause);
				}
				String trans_cd="";
				//String nom_rev_no="";
				
				
				if(cont_type.equalsIgnoreCase("T") || cont_type.equalsIgnoreCase("C"))//ADDED FOR LTCORA AND CN
				{}
				else
				{
					queryString = "SELECT DISTINCT A.TRANS_CD,A.REV_NO,A.TRUCK_NM,A.TRUCK_VOL,A.SCH_TIME,to_char(A.SCH_DT,'dd/mm/yyyy'),A.REMARKS FROM DLNG_DAILY_TRUCK_SCH_DTL A " +
		  			  "WHERE A.SCH_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') "
		  			  	+ "AND SCH_ID like '"+customer_cd+"-"+fgsa_no+"-%-"+sn_no+"-%-%-"+plant_seq_no+"' "
		  			  	+ " AND A.CONTRACT_TYPE='"+cont_type+"' AND " +
		  			  "A.REV_NO=(SELECT MAX(B.REV_NO) FROM DLNG_DAILY_TRUCK_SCH_DTL B WHERE " +
		  			  "A.SCH_DT=B.SCH_DT AND A.CONTRACT_TYPE=B.CONTRACT_TYPE AND A.TRANS_CD=B.TRANS_CD AND A.SCH_ID = B.SCH_ID)";

				}
//				System.out.println("TRANSPORTER_CD,NOM_REV_NO Query = "+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					TRANSPORTER_CD.add(rset.getString(1)==null?"0":rset.getString(1));
					TRUCK_NO.add(rset.getString(3)==null?"":rset.getString(3));
					TRUCK_VOL.add(rset.getString(4)==null?"0":rset.getString(4));
					TRUCK_SCH_TM.add(rset.getString(5)==null?"00:00":rset.getString(5));
					TRUCK_SCH_DT.add(rset.getString(6)==null?"":rset.getString(6));
					TRUCK_SCH_RMK.add(rset.getString(7)==null?"":rset.getString(7));
					trans_cd= rset.getString(1)==null?"0":rset.getString(1);
					nom_rev_no= rset.getString(2)==null?"0":rset.getString(2);
					NOM_REV_NO.add(nom_rev_no);
					int count = 0;
					tot_mmbtu+=rset.getDouble(4);
					
					queryString3 = "SELECT NVL(A.PLANT_ABBR,'') FROM FMS7_CUSTOMER_PLANT_DTL A " +
							   "WHERE A.customer_cd="+customer_cd+" AND A.seq_no="+plant_seq_no+" " +
							   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
							   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
							   "AND B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
//					System.out.println("plant_ABBR*****"+queryString3);
					rset3 = stmt2.executeQuery(queryString3);
					if(rset3.next())
					{
						PLANT_ABBR.add(rset3.getString(1)==null?"":rset3.getString(1));
					}
					else
					{
						PLANT_ABBR.add("");
					}
					
					String temp_chkpost_nm = "";
					String fetchLinkedChkSql = "select A.CHKPOST_CD,B.CHKPOST_NAME from"
							+ "  DLNG_LINK_CUST_CHECKPOST A,DLNG_CHECKPOST_MST B where "
							+ " A.CUSTOMER_CD = '"+customer_cd+"'"
							+ " AND A.AGR_NO = '"+fgsa_no+"'"
							+ " AND A.CONT_NO = '"+sn_no+"'"
							+ " AND A.CONT_TYPE = '"+cont_type+"'"
							+ " AND A.PLANT_CD = '"+plant_seq_no+"'"
							+ " AND A.STATUS = 'Y' "
							+ " AND A.CHKPOST_CD = B.CHKPOST_CD";
//						System.out.println("fetchLinkedChkSql-----"+fetchLinkedChkSql);
						rset1 = stmt1.executeQuery(fetchLinkedChkSql);
						while(rset1.next()) {
							temp_chkpost_nm+=rset1.getString(2)==null?"":rset1.getString(2)+",";
						}
						if(!temp_chkpost_nm.equalsIgnoreCase("")) {
							temp_chkpost_nm = temp_chkpost_nm.substring(0,temp_chkpost_nm.length()-1);
						}
						CHECKPOST_NM.add(temp_chkpost_nm);
				}
				tot_ton+=tot_mmbtu/convt_mmbtu_to_mt;
				
				//Query for Sent Time
				if(cont_type.equalsIgnoreCase("T") || cont_type.equalsIgnoreCase("C"))//ADDED FOR LTCORA AND CN
				{}
				else
				{
					queryString = "SELECT A.TIME_ST_DAY,TO_CHAR(A.GEN_DT-1,'DD-Mon-YY'),TO_CHAR(A.SCH_DT,'DD-Mon-YY'),A.REV_NO  " +
					"FROM DLNG_DAILY_SCH A"
					+ " WHERE A.SCH_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') "
		  			+ " AND SCH_ID like '"+customer_cd+"-"+fgsa_no+"-%-"+sn_no+"-%-%-"+plant_seq_no+"' "
		  			+ " AND A.CONTRACT_TYPE='"+cont_type+"' AND " +
		  			  " A.REV_NO=(SELECT MAX(B.REV_NO) FROM DLNG_DAILY_SCH B "
		  			+ " WHERE B.SCH_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') "
		  			+ " AND B.SCH_ID like '"+customer_cd+"-"+fgsa_no+"-%-"+sn_no+"-%-%-"+plant_seq_no+"' "
		  			+ " AND B.CONTRACT_TYPE='"+cont_type+"') ";
				
				}
//				System.out.println("GEN_TIME Query = "+queryString);
				rset = stmt.executeQuery(queryString);			
				if(rset.next())
				{
					gen_time  = rset.getString(1)==null?"":rset.getString(1);
					gen_date  = rset.getString(2)==null?"":rset.getString(2);
					gas_dt  = rset.getString(3)==null?"":rset.getString(3);
				}
				
				for(int i=0; i<TRANSPORTER_CD.size(); i++)
				{ 
					//Query for TRANSPORTER_NAME,TRANSPORTER_ABBR
					queryString1 = "SELECT TRANS_NAME,TRANS_ABBR FROM DLNG_TRANS_MST WHERE " +
							       "TRANS_CD="+TRANSPORTER_CD.elementAt(i)+" ";
//					System.out.println("DLNG_TRANS_MST Query = "+queryString1);
					rset1 = stmt.executeQuery(queryString1);
					if(rset1.next())
					{
						TRANS_NM.add(rset1.getString(1)==null?"":rset1.getString(1));
						TRANS_ABBR.add(rset1.getString(2)==null?"":rset1.getString(2));
					}
					else
					{
						TRANS_NM.add("");
						TRANS_ABBR.add("");
					}
				}
				
				for(int i=0;i<TRANSPORTER_CD.size();i++)
				{
					//	Query for Sum of QTY_MMBTU
					queryString1 = "SELECT SUM(TRUCK_VOL) FROM DLNG_DAILY_TRUCK_SCH_DTL WHERE " +
								  "SCH_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY')"
								 + " AND SCH_ID like '"+customer_cd+"-"+fgsa_no+"-%-"+sn_no+"-%-%-"+plant_seq_no+"' "
								 + " AND CONTRACT_TYPE='"+cont_type+"'"
								 + " AND REV_NO ='"+NOM_REV_NO.elementAt(i)+"' "
								 + " AND TRANS_CD='"+TRANSPORTER_CD.elementAt(i)+"' ";
					
					if(cont_type.equalsIgnoreCase("T") || cont_type.equalsIgnoreCase("C"))//ADDED FOR LTCORA AND CN
					{}
					
//					System.out.println("QTY_MMBTU Query = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						QTY_MMBTU.add(rset1.getString(1)==null?"0":rset1.getString(1));
					}
					else
					{
						QTY_MMBTU.add("");
					}
				}			
			}
			catch(Exception e)
			{
				System.out.println("EXCEPTION IN Method : "+methodName+" Of "+databeanName+" DataBean = "+e.getMessage());
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
		String truck_driver = "",truck_driver_lic_no="",truckLinkedFlg="";
		private void fetchtruckTransDriverDtl(String truck_cd)throws SQLException,IOException {
			try {
				truck_driver = "";
				String truck_sql="select a.TRANS_CD,a.LICENSE_NO,b.TRUCK_NM  from DLNG_TRUCK_DRIVER_LINK_MST a, DLNG_TANK_TRUCK_MST b"
						+ " where b.TRUCK_NM='"+truck_cd+"' and a.TRUCK_ID=b.TRUCK_ID and a.STATUS='Y' ";
//				System.out.println("truck_sql*****"+truck_sql);
				rset7 = stmt.executeQuery(truck_sql);
				if(rset7.next()) {
					truck_driver_lic_no= rset7.getString(2)==null?"":rset7.getString(2);
					
					String driver_sql="select a.DRIVER_NAME,a.ADDRESS from DLNG_DRIVER_MST a"
							+ " where LICENSE_NO = '"+rset7.getString(2)+"' ";
//					System.out.println("driver_sql****"+driver_sql);
					rset3 = stmt3.executeQuery(driver_sql);
					if(rset3.next()) {
						truck_driver = rset3.getString(1)==null?"":rset3.getString(1);
//						truck_driver_addrs = rset3.getString(2)==null?"":rset3.getString(2);
//						truck_lic_state = rset1.getString(3)==null?"":rset1.getString(3);
					}else {
						truck_driver = "Not Linked";
					}
					truckLinkedFlg ="Y";
				}else {
					truckLinkedFlg ="N";
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		public String getCallFlag() {
			return callFlag;
		}

		public void setCallFlag(String callFlag) {
			this.callFlag = callFlag;
		}

		public String getGas_date() {
			return gas_date;
		}

		public String getGen_date() {
			return gen_date;
		}

		public void setGen_date(String gen_date) {
			this.gen_date = gen_date;
		}

		public void setGas_date(String gas_date) {
			this.gas_date = gas_date;
		}

		public String getCustomer_cd() {
			return customer_cd;
		}

		public void setCustomer_cd(String customer_cd) {
			this.customer_cd = customer_cd;
		}

		public String getCont_type() {
			return cont_type;
		}

		public void setCont_type(String cont_type) {
			this.cont_type = cont_type;
		}

		public String getSn_no() {
			return sn_no;
		}

		public void setSn_no(String sn_no) {
			this.sn_no = sn_no;
		}

		public String getFgsa_no() {
			return fgsa_no;
		}

		public void setFgsa_no(String fgsa_no) {
			this.fgsa_no = fgsa_no;
		}

		public String getFrom_dt() {
			return from_dt;
		}

		public String getCustomer_contact_cd() {
			return customer_contact_cd;
		}

		public Vector getCUSTOMER_CD() {
			return CUSTOMER_CD;
		}

		public Vector getCUST_CD() {
			return CUST_CD;
		}

		public Vector getFGSA_NO() {
			return FGSA_NO;
		}

		public Vector getCONTRACT_TYPE() {
			return CONTRACT_TYPE;
		}

		public Vector getCUSTOMER_NAME() {
			return CUSTOMER_NAME;
		}

		public Vector getCONTACT_PERSON() {
			return CONTACT_PERSON;
		}

		public Vector getPLANT_SEQ_NO() {
			return PLANT_SEQ_NO;
		}

		public Vector getMAPPING_ID() {
			return MAPPING_ID;
		}

		public Vector getNOM_REV_NO() {
			return NOM_REV_NO;
		}

		public Vector getPLANT_NM() {
			return PLANT_NM;
		}

		public Vector getPLANT_ABBR() {
			return PLANT_ABBR;
		}

		public Vector getSN_NO() {
			return SN_NO;
		}

		public Vector getSN_NO_REF_DISPLAY() {
			return SN_NO_REF_DISPLAY;
		}

		public Vector getSn_ref_no() {
			return sn_ref_no;
		}

		public Vector getCUSTOMER_ABBR() {
			return CUSTOMER_ABBR;
		}

		public Vector getSEQ_NO() {
			return SEQ_NO;
		}

		public Vector getSUPP_SEQ_NO() {
			return SUPP_SEQ_NO;
		}

		public Vector getSUPP_CONTACT_PERSON() {
			return SUPP_CONTACT_PERSON;
		}

		public Vector getREMARKS() {
			return REMARKS;
		}

		public String getSeq_no() {
			return seq_no;
		}

		public void setSeq_no(String seq_no) {
			this.seq_no = seq_no;
		}

		public String getSupp_seq_no() {
			return supp_seq_no;
		}

		public void setSupp_seq_no(String supp_seq_no) {
			this.supp_seq_no = supp_seq_no;
		}

		public String getPlant_seq_no() {
			return plant_seq_no;
		}

		public String getNom_rev_no() {
			return nom_rev_no;
		}

		public void setPlant_seq_no(String plant_seq_no) {
			this.plant_seq_no = plant_seq_no;
		}

		public void setNom_rev_no(String nom_rev_no) {
			this.nom_rev_no = nom_rev_no;
		}

		public String getCont_mapping_id() {
			return cont_mapping_id;
		}

		public void setCont_mapping_id(String cont_mapping_id) {
			this.cont_mapping_id = cont_mapping_id;
		}

		public String getDaily_Total_Dcq() {
			return daily_Total_Dcq;
		}

		public void setDaily_Total_Dcq(String daily_Total_Dcq) {
			this.daily_Total_Dcq = daily_Total_Dcq;
		}

		public String getTo_dt() {
			return to_dt;
		}

		public Vector getDEVIATION_SN_NO_REF_DISPLAY() {
			return DEVIATION_SN_NO_REF_DISPLAY;
		}

		public Vector getDEVIATION_NOM_REV_NO() {
			return DEVIATION_NOM_REV_NO;
		}

		public String getRemarks() {
			return remarks;
		}

		public String getTemp_con_desig() {
			return temp_con_desig;
		}

		public String getSupp_con_desig() {
			return supp_con_desig;
		}

		public String getTo_fax_no() {
			return to_fax_no;
		}

		public String getFrom_fax_no() {
			return from_fax_no;
		}

		public String getCust_nm() {
			return cust_nm;
		}

		public String getCust_abbr() {
			return cust_abbr;
		}

		public String getFgsa_dt() {
			return fgsa_dt;
		}

		public String getContract_dt() {
			return contract_dt;
		}

		public String getSeller_nom_clause() {
			return seller_nom_clause;
		}

		public String getTender_seller_nom_clause() {
			return tender_seller_nom_clause;
		}

		public String getLtcora_dt() {
			return ltcora_dt;
		}

		public String getLtcora_seller_nom_clause() {
			return ltcora_seller_nom_clause;
		}

		public String getLtcora_no() {
			return ltcora_no;
		}

		public Vector getMOBILE() {
			return MOBILE;
		}

		public Vector getPHONE() {
			return PHONE;
		}

		public Vector getTRANSPORTER_CD() {
			return TRANSPORTER_CD;
		}

		public Vector getTRANS_NM() {
			return TRANS_NM;
		}

		public Vector getTRANS_ABBR() {
			return TRANS_ABBR;
		}

		public Vector getQTY_MMBTU() {
			return QTY_MMBTU;
		}

		public String getGen_time() {
			return gen_time;
		}

		public String getGas_dt() {
			return gas_dt;
		}

		public void setFrom_dt(String from_dt) {
			this.from_dt = from_dt;
		}

		public void setTo_dt(String to_dt) {
			this.to_dt = to_dt;
		}

		public void setCustomer_contact_cd(String customer_contact_cd) {
			this.customer_contact_cd = customer_contact_cd;
		}

		public void setCUSTOMER_CD(Vector cUSTOMER_CD) {
			CUSTOMER_CD = cUSTOMER_CD;
		}

		public void setCUST_CD(Vector cUST_CD) {
			CUST_CD = cUST_CD;
		}

		public void setFGSA_NO(Vector fGSA_NO) {
			FGSA_NO = fGSA_NO;
		}

		public void setSN_NO(Vector sN_NO) {
			SN_NO = sN_NO;
		}

		public void setSN_NO_REF_DISPLAY(Vector sN_NO_REF_DISPLAY) {
			SN_NO_REF_DISPLAY = sN_NO_REF_DISPLAY;
		}

		public void setDEVIATION_SN_NO_REF_DISPLAY(Vector dEVIATION_SN_NO_REF_DISPLAY) {
			DEVIATION_SN_NO_REF_DISPLAY = dEVIATION_SN_NO_REF_DISPLAY;
		}

		public void setDEVIATION_NOM_REV_NO(Vector dEVIATION_NOM_REV_NO) {
			DEVIATION_NOM_REV_NO = dEVIATION_NOM_REV_NO;
		}

		public void setCONTRACT_TYPE(Vector cONTRACT_TYPE) {
			CONTRACT_TYPE = cONTRACT_TYPE;
		}

		public void setCUSTOMER_ABBR(Vector cUSTOMER_ABBR) {
			CUSTOMER_ABBR = cUSTOMER_ABBR;
		}

		public void setCUSTOMER_NAME(Vector cUSTOMER_NAME) {
			CUSTOMER_NAME = cUSTOMER_NAME;
		}

		public void setSEQ_NO(Vector sEQ_NO) {
			SEQ_NO = sEQ_NO;
		}

		public void setCONTACT_PERSON(Vector cONTACT_PERSON) {
			CONTACT_PERSON = cONTACT_PERSON;
		}

		public void setSUPP_SEQ_NO(Vector sUPP_SEQ_NO) {
			SUPP_SEQ_NO = sUPP_SEQ_NO;
		}

		public void setSUPP_CONTACT_PERSON(Vector sUPP_CONTACT_PERSON) {
			SUPP_CONTACT_PERSON = sUPP_CONTACT_PERSON;
		}

		public void setPLANT_SEQ_NO(Vector pLANT_SEQ_NO) {
			PLANT_SEQ_NO = pLANT_SEQ_NO;
		}

		public void setMAPPING_ID(Vector mAPPING_ID) {
			MAPPING_ID = mAPPING_ID;
		}

		public void setNOM_REV_NO(Vector nOM_REV_NO) {
			NOM_REV_NO = nOM_REV_NO;
		}

		public void setPLANT_NM(Vector pLANT_NM) {
			PLANT_NM = pLANT_NM;
		}

		public void setPLANT_ABBR(Vector pLANT_ABBR) {
			PLANT_ABBR = pLANT_ABBR;
		}

		public void setREMARKS(Vector rEMARKS) {
			REMARKS = rEMARKS;
		}

		public void setSn_ref_no(Vector sn_ref_no) {
			this.sn_ref_no = sn_ref_no;
		}

		public void setRemarks(String remarks) {
			this.remarks = remarks;
		}

		public void setTemp_con_desig(String temp_con_desig) {
			this.temp_con_desig = temp_con_desig;
		}

		public void setSupp_con_desig(String supp_con_desig) {
			this.supp_con_desig = supp_con_desig;
		}

		public void setTo_fax_no(String to_fax_no) {
			this.to_fax_no = to_fax_no;
		}

		public void setFrom_fax_no(String from_fax_no) {
			this.from_fax_no = from_fax_no;
		}

		public void setCust_nm(String cust_nm) {
			this.cust_nm = cust_nm;
		}

		public void setCust_abbr(String cust_abbr) {
			this.cust_abbr = cust_abbr;
		}

		public void setFgsa_dt(String fgsa_dt) {
			this.fgsa_dt = fgsa_dt;
		}

		public void setContract_dt(String contract_dt) {
			this.contract_dt = contract_dt;
		}

		public void setSeller_nom_clause(String seller_nom_clause) {
			this.seller_nom_clause = seller_nom_clause;
		}

		public void setTender_seller_nom_clause(String tender_seller_nom_clause) {
			this.tender_seller_nom_clause = tender_seller_nom_clause;
		}

		public void setLtcora_dt(String ltcora_dt) {
			this.ltcora_dt = ltcora_dt;
		}

		public void setLtcora_seller_nom_clause(String ltcora_seller_nom_clause) {
			this.ltcora_seller_nom_clause = ltcora_seller_nom_clause;
		}

		public void setLtcora_no(String ltcora_no) {
			this.ltcora_no = ltcora_no;
		}

		public void setMOBILE(Vector mOBILE) {
			MOBILE = mOBILE;
		}

		public void setPHONE(Vector pHONE) {
			PHONE = pHONE;
		}

		public void setTRANSPORTER_CD(Vector tRANSPORTER_CD) {
			TRANSPORTER_CD = tRANSPORTER_CD;
		}

		public void setTRANS_NM(Vector tRANS_NM) {
			TRANS_NM = tRANS_NM;
		}

		public void setTRANS_ABBR(Vector tRANS_ABBR) {
			TRANS_ABBR = tRANS_ABBR;
		}

		public void setQTY_MMBTU(Vector qTY_MMBTU) {
			QTY_MMBTU = qTY_MMBTU;
		}

		public void setGen_time(String gen_time) {
			this.gen_time = gen_time;
		}

		public void setGas_dt(String gas_dt) {
			this.gas_dt = gas_dt;
		}

		public Vector getTRANS_CD() {
			return TRANS_CD;
		}

		public void setTRANS_CD(Vector tRANS_CD) {
			TRANS_CD = tRANS_CD;
		}

		public Vector getTRANS_COUNT() {
			return TRANS_COUNT;
		}

		public void setTRANS_COUNT(Vector tRANS_COUNT) {
			TRANS_COUNT = tRANS_COUNT;
		}

		public Vector getTRUCK_NO() {
			return TRUCK_NO;
		}

		public void setTRUCK_NO(Vector tRUCK_NO) {
			TRUCK_NO = tRUCK_NO;
		}

		public Vector getTRUCK_VOL() {
			return TRUCK_VOL;
		}

		public void setTRUCK_VOL(Vector tRUCK_VOL) {
			TRUCK_VOL = tRUCK_VOL;
		}

		public Vector getTRUCK_SCH_TM() {
			return TRUCK_SCH_TM;
		}

		public void setTRUCK_SCH_TM(Vector tRUCK_SCH_TM) {
			TRUCK_SCH_TM = tRUCK_SCH_TM;
		}

		public Vector getTRUCK_SCH_DT() {
			return TRUCK_SCH_DT;
		}

		public void setTRUCK_SCH_DT(Vector tRUCK_SCH_DT) {
			TRUCK_SCH_DT = tRUCK_SCH_DT;
		}

		public double getTot_mmbtu() {
			return tot_mmbtu;
		}

		public double getTot_ton() {
			return tot_ton;
		}

		public double getConvt_mmbtu_to_mt() {
			return convt_mmbtu_to_mt;
		}

		public void setConvt_mmbtu_to_mt(double convt_mmbtu_to_mt) {
			this.convt_mmbtu_to_mt = convt_mmbtu_to_mt;
		}

		public Vector getTRUCK_SCH_RMK() {
			return TRUCK_SCH_RMK;
		}

		public void setTRUCK_SCH_RMK(Vector tRUCK_SCH_RMK) {
			TRUCK_SCH_RMK = tRUCK_SCH_RMK;
		}

		public Vector getVtrans_cd() {
			return Vtrans_cd;
		}

		public Vector getVtrans_abbr() {
			return Vtrans_abbr;
		}

		public Vector getVcont_pers_nm() {
			return Vcont_pers_nm;
		}

		public Vector getVcont_pers_seq() {
			return Vcont_pers_seq;
		}

		public Vector getVcont_pers_desig() {
			return Vcont_pers_desig;
		}

		public Vector getVtrans_pers_cnt() {
			return Vtrans_pers_cnt;
		}

		public Vector getVtrans_nom_cnt() {
			return Vtrans_nom_cnt;
		}

		public Vector getVtrans_nm() {
			return Vtrans_nm;
		}

		public String getTrans_cd() {
			return trans_cd;
		}

		public void setTrans_cd(String trans_cd) {
			this.trans_cd = trans_cd;
		}
		public String getPrint_pdf() {
			return print_pdf;
		}
		public void setPrint_pdf(String print_pdf) {
			this.print_pdf = print_pdf;
		}

		public void setRequest(HttpServletRequest request) {
			this.request = request;
		}

		public String getNom_trans_pdf_path() {
			return nom_trans_pdf_path;
		}

		public void setNom_trans_pdf_path(String nom_trans_pdf_path) {
			this.nom_trans_pdf_path = nom_trans_pdf_path;
		}

		public String getUrl_start() {
			return url_start;
		}

		public void setUrl_start(String url_start) {
			this.url_start = url_start;
		}

		public String getTrans_name() {
			return trans_name;
		}

		public String getPdfsignedpath() {
			return pdfsignedpath;
		}

		public String getFrom_mail() {
			return from_mail;
		}

		public String getEmail_to() {
			return email_to;
		}

		public String getEmail_cc() {
			return email_cc;
		}

		public String getXls_file() {
			return xls_file;
		}

		public String getSupplier_nm() {
			return supplier_nm;
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

		public String getSupplier_phone_no() {
			return supplier_phone_no;
		}

		public String getSupplier_pin_code() {
			return supplier_pin_code;
		}
		public Vector getDRIVER_NM() {
			return DRIVER_NM;
		}
		public Vector getTRUCK_LINKED_FLG() {
			return TRUCK_LINKED_FLG;
		}
		public String getTrans_abbr() {
			return trans_abbr;
		}
		public Vector getCHECKPOST_NM() {
			return CHECKPOST_NM;
		}
}
