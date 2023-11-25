package com.seipl.hazira.dlng.sales_invoice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class Excel_Export_Form_402 {
	
double total_net_amt_inr = 0;
String contact_Suppl_CST_NO = "";
String contact_Customer_Name = "";
String contact_Customer_Person_City = "";
String contact_Customer_Person_Address = "";
String contact_Customer_Person_Pin = "";
String contact_Customer_Person_State = "";
String new_inv_seq_no = "";
String invoice_date = "";
String total_qty = "";
String taxRate="";
String truck_trans_nm="";
String truck_driver="";
String truck_driver_addrs="";
String truck_nm="";
String truck_trans_addrs="";
String truckLinkedFlg="";
String truck_driver_lic_no="";
String truck_lic_state="";
String check_post="";

Vector vSTAT_CD = new Vector();
Vector vSTAT_NM =  new Vector();
Vector vSTAT_NO =  new Vector();
Vector vSTAT_EFF_DT =  new Vector();

String contact_Customer_CST_NO ="";
String contact_Customer_CST_DT ="";

	void generateExcelForForm402(HttpServletRequest req)throws Exception
	{
		try {
//			System.out.println("new_inv_seq_no****"+new_inv_seq_no);
			String temp=new_inv_seq_no.replace("/", "_");
			String fileName= temp+""+truck_nm+".xls";
			
			String con_path = req.getRealPath("Form402_Xls/");
			fileName = con_path+"/"+fileName;
			////System.out.println("fileName = "+fileName);
			File ft1 = new File(con_path);
			if(!ft1.exists())
			{
				
				if(ft1.mkdirs())
					{
						////System.out.println("DIR CREATED");
					}
				ft1.canExecute();
				ft1.canRead();
				ft1.canWrite();
			}
			
			OutputStream fileOut = new FileOutputStream(fileName);
			
			/*Creating Workbook*/
			Workbook wb = new HSSFWorkbook();  
            Sheet sheet = wb.createSheet("Sheet"); 
    		
            /*Creating CellStyle*/
    		CellStyle stringStyle1 = wb.createCellStyle();
    		CellStyle stringStyle2 = wb.createCellStyle();
    		CellStyle stringStyle3 = wb.createCellStyle();
    		
    		stringStyle1.setAlignment(CellStyle.ALIGN_CENTER);
//    		stringStyle1.setFillBackgroundColor(
    		stringStyle1.setFillBackgroundColor(IndexedColors.AQUA.getIndex());
    		stringStyle1.setFillPattern(CellStyle.BORDER_MEDIUM);
    		
    		stringStyle2.setAlignment(CellStyle.ALIGN_LEFT);
    		stringStyle2.setFillBackgroundColor(IndexedColors.CORAL.getIndex());
    		stringStyle2.setFillPattern(CellStyle.SPARSE_DOTS);
    		
    		stringStyle3.setAlignment(CellStyle.ALIGN_LEFT);
    		
    		/*Creating FontStyle*/
    		Font font1 = wb.createFont();
    		font1.setFontHeightInPoints((short)20); 
    		
    		Font font2 = wb.createFont();
    		font2.setFontHeightInPoints((short)15); 
    		
    		Font font3 = wb.createFont();
    		font3.setFontHeightInPoints((short)9); 
    		
    		/*Row 1*/
    		Row row = sheet.createRow(0);  
            Cell cell = row.createCell(0);  
            cell.setCellValue("Form-402");  
            stringStyle1.setFont(font1); 
            cell.setCellStyle(stringStyle1);
            sheet.addMergedRegion(new CellRangeAddress(0,1,0,15));  
            
            /*Row 2*/
            Row row1 = sheet.createRow(2);  
            Cell cell1 = row1.createCell(0);  
            cell1.setCellValue("Basic Info"); 
            stringStyle2.setFont(font2);
            cell1.setCellStyle(stringStyle2);
            sheet.addMergedRegion(new CellRangeAddress(2,2,0,15)); 
            
            /*Row 3*/
            Row row2 = sheet.createRow(3);  
            Cell cell2 = row2.createCell(0);
            cell2.setCellValue("Check Post Name"); 
            stringStyle3.setFont(font3);
            cell2.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(3,3,0,5));
            
            Cell cell3 = row2.createCell(6);
            cell3.setCellValue(check_post);
            cell3.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(3,3,6,15));
            
            /*Row 4*/
            Row row3 = sheet.createRow(4);  
            Cell cell4 = row3.createCell(0);
            cell4.setCellValue("Place from Which Goods are Dispatched"); 
            stringStyle3.setFont(font3);
            cell4.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(4,4,0,5));
            
            Cell cell5 = row3.createCell(6);
            cell5.setCellValue("Gujarat");
            cell5.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(4,4,6,15));
            
            /*Row 5*/
            Row row5 = sheet.createRow(5);  
            Cell cell6 = row5.createCell(0);
            cell6.setCellValue("District from Which Goods are Dispatched"); 
            stringStyle3.setFont(font3);
            cell6.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(5,5,0,5));
            
            Cell cell7 = row5.createCell(6);
            cell7.setCellValue("");
            cell7.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(5,5,6,15));
            
            /*Row 6*/
            Row row6 = sheet.createRow(6);  
            Cell cell8 = row6.createCell(0);
            cell8.setCellValue("Place to Which Goods are Dispatched");
            stringStyle3.setFont(font3);
            cell8.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(6,6,0,5));
            
            Cell cell9 = row6.createCell(6);
            cell9.setCellValue(contact_Customer_Person_State);
            cell9.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(6,6,6,15));
            
            /*Row 7*/
            Row row7 = sheet.createRow(7);  
            Cell cell10 = row7.createCell(0);
            cell10.setCellValue("District to Which Goods are Dispatched");
            stringStyle3.setFont(font3);
            cell10.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(7,7,0,5));
            
            Cell cell11 = row7.createCell(6);
            cell11.setCellValue("");
            cell11.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(7,7,6,15));
            
            /*Row 8*/
            Row row8 = sheet.createRow(8);  
            Cell cell12 = row8.createCell(0);  
            cell12.setCellValue("Consignor Details"); 
            stringStyle2.setFont(font2);
            cell12.setCellStyle(stringStyle2);
            sheet.addMergedRegion(new CellRangeAddress(8,8,0,15)); 
            
            /*Row 9*/
            Row row9 = sheet.createRow(9);  
            Cell cell13 = row9.createCell(0);
            cell13.setCellValue("CST Certificate Number");
            stringStyle3.setFont(font3);
            cell13.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(9,9,0,5));
            
            Cell cell14 = row9.createCell(6);
            cell14.setCellValue(contact_Suppl_CST_NO);
            cell14.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(9,9,6,15));
            
            /*Row 10*/
            
            String NoT="";
            if(contact_Customer_Person_State.contains("Gujarat")){
            	NoT = "Sale Within Gujarat";
            }else{
            	NoT = "Inter State Sale";
            }
            
            Row row10 = sheet.createRow(10);  
            Cell cell15 = row10.createCell(0);
            cell15.setCellValue("Nature of Transactions");
            stringStyle3.setFont(font3);
            cell15.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(10,10,0,5));
            
            Cell cell16 = row10.createCell(6);
            cell16.setCellValue(NoT);
            cell16.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(10,10,6,15));
            
            
            /*Row 11*/
            Row row11 = sheet.createRow(11);  
            Cell cell17 = row11.createCell(0);  
            cell17.setCellValue("Consignee Details"); 
            stringStyle2.setFont(font2);
            cell17.setCellStyle(stringStyle2);
            sheet.addMergedRegion(new CellRangeAddress(11,11,0,15)); 
            
            /*Row 12*/
            Row row12 = sheet.createRow(12);  
            Cell cell18 = row12.createCell(0);
            cell18.setCellValue("Name");
            stringStyle3.setFont(font3);
            cell18.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(12,12,0,5));
            
            Cell cell19 = row12.createCell(6);
            cell19.setCellValue(contact_Customer_Name);
            cell19.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(12,12,6,15));
            
            /*Row 13*/
            Row row13 = sheet.createRow(13);  
            Cell cell20 = row13.createCell(0);
            cell20.setCellValue("Address");
            stringStyle3.setFont(font3);
            cell20.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(13,13,0,5));
            
            Cell cell21 = row13.createCell(6);
            cell21.setCellValue(contact_Customer_Person_Address +","+contact_Customer_Person_City+" - "+contact_Customer_Person_Pin);
            cell21.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(13,13,6,15));
            
            /*Row 16*/
            String cst_no="",cst_dt="";
            String vat_no="",vat_dt="";
            String gstin_no="",gstin_dt="";
            
        	if(vSTAT_CD.size()>0){ 
        		for(int i=0; i<vSTAT_CD.size(); i++){
        			 if(vSTAT_NM.elementAt(i).toString().contains("CST TIN No.")) {
        				 
        				 cst_no = vSTAT_NO.elementAt(i)+"";
        				 cst_dt = vSTAT_EFF_DT.elementAt(i)+"";
        			}else if(vSTAT_NM.elementAt(i).toString().contains("GVAT TIN No.")){
        			
        				vat_no = vSTAT_NO.elementAt(i)+"";
        				vat_dt = vSTAT_EFF_DT.elementAt(i)+"";
        			}else if(vSTAT_NM.elementAt(i).toString().contains("GSTIN")) {
        				
        				if(!vSTAT_NO.elementAt(i).equals("")) {
        					
	        				gstin_no = vSTAT_NO.elementAt(i)+"";
	        				gstin_dt = vSTAT_EFF_DT.elementAt(i)+"";
        				}
        				
        			}else if(vSTAT_NM.elementAt(i).toString().contains("TAN No.")) {
        				if(gstin_no.equals("")) {
        					
	        				gstin_no = vSTAT_NO.elementAt(i)+"";
	        				gstin_dt = vSTAT_EFF_DT.elementAt(i)+"";
        				}	
        			}     
        		}
        	}else{
        		 cst_no = contact_Customer_CST_NO;
        		 cst_dt = contact_Customer_CST_DT;
        		
        	}
        	
            /*Row 14*/
            Row row14 = sheet.createRow(14);  
            Cell cell22 = row14.createCell(0);
            cell22.setCellValue("VAT Registration Certificate No.");
            stringStyle3.setFont(font3);
            cell22.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(14,14,0,5));
            
            Cell cell23 = row14.createCell(6);
            cell23.setCellValue(vat_no);
            cell23.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(14,14,6,15));
            
            /*Row 15*/
            Row row15 = sheet.createRow(15);  
            Cell cell24 = row15.createCell(0);
            cell24.setCellValue("VAT Registration Effective Date");
            stringStyle3.setFont(font3);
            cell24.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(15,15,0,5));
            
            Cell cell25 = row15.createCell(6);
            cell25.setCellValue(vat_dt);
            cell25.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(15,15,6,15));
            
           
            Row row16 = sheet.createRow(16);  
            Cell cell26 = row16.createCell(0);
            cell26.setCellValue("CST Registration No.");
            stringStyle3.setFont(font3);
            cell26.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(16,16,0,5));
            
            Cell cell27 = row16.createCell(6);
            cell27.setCellValue(cst_no);
            cell27.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(16,16,6,15));
            
            /*Row 17*/
            Row row17 = sheet.createRow(17);  
            Cell cell28 = row17.createCell(0);
            cell28.setCellValue("CST Registration Effective Date");
            stringStyle3.setFont(font3);
            cell28.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(17,17,0,5));
            
            Cell cell29 = row17.createCell(6);
            cell29.setCellValue(cst_dt);
            cell29.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(17,17,6,15));
            
            /*Row 18*/
            Row row18 = sheet.createRow(18);  
            Cell cell82 = row18.createCell(0);
            cell82.setCellValue("GSTIN Registration No.");
            stringStyle3.setFont(font3);
            cell82.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(18,18,0,5));
            
            Cell cell83 = row18.createCell(6);
            cell83.setCellValue(gstin_no);
            cell83.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(18,18,6,15));
            
            
            /*Row 18*/
            Row row19 = sheet.createRow(19);  
            Cell cell84 = row19.createCell(0);
            cell84.setCellValue("GSTIN Effective Date");
            stringStyle3.setFont(font3);
            cell84.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(19,19,0,5));
            
            Cell cell85 = row19.createCell(6);
            cell85.setCellValue(gstin_dt);
            cell85.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(19,19,6,15));
            
            
            /*Row 18*/
            Row row20 = sheet.createRow(20);  
            Cell cell30 = row20.createCell(0);
            cell30.setCellValue("Telephone No.");
            stringStyle3.setFont(font3);
            cell30.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(20,20,0,5));
            
            Cell cell31 = row20.createCell(6);
            cell31.setCellValue("");
            cell31.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(20,20,6,15));
            
            /*Row 19*/
            Row row21 = sheet.createRow(21);  
            Cell cell33 = row21.createCell(0);
            cell33.setCellValue("Consignee Fax No.");
            stringStyle3.setFont(font3);
            cell33.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(21,21,0,5));
            
            Cell cell34 = row21.createCell(6);
            cell34.setCellValue("");
            cell34.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(21,21,6,15));
            
            /*Row 20*/
            Row row22 = sheet.createRow(22);  
            Cell cell35 = row22.createCell(0);
            cell35.setCellValue("Consigned Value Rs. (Total Value of Invoice)");
            stringStyle3.setFont(font3);
            cell35.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(22,22,0,5));
            
            Cell cell36 = row22.createCell(6);
            cell36.setCellValue(total_net_amt_inr);
            cell36.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(22,22,6,15));
            
            /*Row 23*/
            Row row23 = sheet.createRow(23);  
            Cell cell37 = row23.createCell(0);
            cell37.setCellValue("Whether Shipping Address is within the state of Gujarat and shipping \nis to dealer other than consignee, as declared above ?");
            stringStyle3.setFont(font3);
            cell37.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(23,23,0,5));
            
            Cell cell38 = row23.createCell(6);
            cell38.setCellValue("");
            cell38.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(23,23,6,15));
            
            /*Row 22*/
            Row row24 = sheet.createRow(24);  
            Cell cell39 = row24.createCell(0);
            cell39.setCellValue("If yes, give TIN of Local Register dealer to Goods are Shipped to");
            stringStyle3.setFont(font3);
            cell39.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(24,24,0,5));
            
            Cell cell40 = row24.createCell(6);
            cell40.setCellValue("");
            cell40.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(24,24,6,15));
            
            /*Row 23*/
            Row row25 = sheet.createRow(25);  
            Cell cell41 = row25.createCell(0);
            cell41.setCellValue("Whether this is final shipping to original buyer outside the state of Gujarat");
            stringStyle3.setFont(font3);
            cell41.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(25,25,0,5));
            
            Cell cell42 = row25.createCell(6);
            cell42.setCellValue("");
            cell42.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(25,25,6,15));
            
            
            /*Row 26*/
            Row row26 = sheet.createRow(26);  
            Cell cell43 = row26.createCell(0);
            cell43.setCellValue("If yes, provide reference of previous Form-402 \r\n against which goods are received for Job work");
            stringStyle3.setFont(font3);
            cell43.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(26,26,0,5));
            
            Cell cell44 = row26.createCell(6);
            cell44.setCellValue("");
            cell44.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(26,26,6,15));
            
            /*Row 27*/
            Row row27 = sheet.createRow(27);  
            Cell cell47 = row27.createCell(0);  
            cell47.setCellValue("Commodity Details"); 
            stringStyle2.setFont(font2);
            cell47.setCellStyle(stringStyle2);
            sheet.addMergedRegion(new CellRangeAddress(27,27,0,15));
            
            /*Row 28*/
            Row row28 = sheet.createRow(28);  
            Cell cell45 = row28.createCell(0);
            cell45.setCellValue("Invoice number");
            stringStyle3.setFont(font3);
            cell45.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(28,28,0,5));
            
            Cell cell46 = row28.createCell(6);
            cell46.setCellValue(new_inv_seq_no);
            cell46.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(28,28,6,15));
            
            /*Row 29*/
            Row row29 = sheet.createRow(29);  
            Cell cell48 = row29.createCell(0);
            cell48.setCellValue("Invoice Date");
            stringStyle3.setFont(font3);
            cell48.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(29,29,0,5));
            
            Cell cell49 = row29.createCell(6);
            cell49.setCellValue(invoice_date);
            cell49.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(29,29,6,15));
            
            /*Row 28*/
            Row row30 = sheet.createRow(30);  
            Cell cell50 = row30.createCell(0);
            cell50.setCellValue("Commodity Name");
            stringStyle3.setFont(font3);
            cell50.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(30,30,0,5));
            
            Cell cell51 = row30.createCell(6);
            cell51.setCellValue("Liquified Natural Gas (LNG)");
            cell51.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(30,30,6,15));
            
            /*Row 31*/
            Row row31 = sheet.createRow(31);  
            Cell cell52 = row31.createCell(0);
            cell52.setCellValue("Quantity");
            stringStyle3.setFont(font3);
            cell52.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(31,31,0,5));
            
            Cell cell53 = row31.createCell(6);
            cell53.setCellValue(total_qty);
            cell53.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(31,31,6,15));
            
            /*Row 32*/
            Row row32 = sheet.createRow(32);  
            Cell cell54 = row32.createCell(0);
            cell54.setCellValue("Unit of Mesurement");
            stringStyle3.setFont(font3);
            cell54.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(32,32,0,5));
            
            Cell cell55 = row32.createCell(6);
            cell55.setCellValue("Nos.");
            cell55.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(32,32,6,15));
            
            /*Row 33*/
            Row row33 = sheet.createRow(33);  
            Cell cell56 = row33.createCell(0);
            cell56.setCellValue("Rate of tax");
            stringStyle3.setFont(font3);
            cell56.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(33,33,0,5));
            
            Cell cell57 = row33.createCell(6);
            cell57.setCellValue(taxRate);
            cell57.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(33,33,6,15));
            
            /*Row 34*/
            Row row34 = sheet.createRow(34);  
            Cell cell58 = row34.createCell(0);
            cell58.setCellValue("Amount");
            stringStyle3.setFont(font3);
            cell58.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(34,34,0,5));
            
            Cell cell59 = row34.createCell(6);
            cell59.setCellValue(total_net_amt_inr);
            cell59.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(34,34,6,15));
            
            /*Row 35*/
            Row row35 = sheet.createRow(35);  
            Cell cell60 = row35.createCell(0);  
            cell60.setCellValue("Transporter Details"); 
            stringStyle2.setFont(font2);
            cell60.setCellStyle(stringStyle2);
            sheet.addMergedRegion(new CellRangeAddress(35,35,0,15));
            
            
            /*Row 36*/
            Row row36 = sheet.createRow(36);  
            Cell cell61 = row36.createCell(0);
            cell61.setCellValue("Name");
            stringStyle3.setFont(font3);
            cell61.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(36,36,0,5));
            
            Cell cell62 = row36.createCell(6);
            cell62.setCellValue(truck_trans_nm);
            cell62.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(36,36,6,15));
            
            /*Row 37*/
            Row row37 = sheet.createRow(37);  
            Cell cell63 = row37.createCell(0);
            cell63.setCellValue("Address");
            stringStyle3.setFont(font3);
            cell63.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(37,37,0,5));
            
            Cell cell64 = row37.createCell(6);
            cell64.setCellValue(truck_trans_addrs);
            cell64.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(37,37,6,15));
            
            /*Row 38*/
            Row row38 = sheet.createRow(38);  
            Cell cell65 = row38.createCell(0);
            cell65.setCellValue("Owner / Partner Name");
            stringStyle3.setFont(font3);
            cell65.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(38,38,0,5));
            
            Cell cell66 = row38.createCell(6);
            cell66.setCellValue("");
            cell66.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(38,38,6,15));
            
            /*Row 39*/
            Row row39 = sheet.createRow(39);  
            Cell cell67 = row39.createCell(0);
            cell67.setCellValue("Vehicle No.");
            stringStyle3.setFont(font3);
            cell67.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(39,39,0,5));
            
            Cell cell68 = row39.createCell(6);
            cell68.setCellValue(truck_nm);
            cell68.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(39,39,6,15));
            
            
            /*Row 40*/
            Row row40 = sheet.createRow(40);  
            Cell cell69 = row40.createCell(0);
            cell69.setCellValue("LR No.");
            stringStyle3.setFont(font3);
            cell69.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(40,40,0,5));
            
            Cell cell70 = row40.createCell(6);
            cell70.setCellValue("");
            cell70.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(40,40,6,15));
            
            /*Row 41*/
            Row row41 = sheet.createRow(41);  
            Cell cell71 = row41.createCell(0);
            cell71.setCellValue("LR Date");
            stringStyle3.setFont(font3);
            cell71.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(41,41,0,5));
            
            Cell cell72 = row41.createCell(6);
            cell72.setCellValue("");
            cell72.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(41,41,6,15));
            
            
            /*Row 42*/
            Row row42 = sheet.createRow(42);  
            Cell cell75 = row42.createCell(0);  
            cell75.setCellValue("Drivers Details"); 
            stringStyle2.setFont(font2);
            cell75.setCellStyle(stringStyle2);
            sheet.addMergedRegion(new CellRangeAddress(42,42,0,15));
            
            /*Row 41*/
            Row row43 = sheet.createRow(43);  
            Cell cell73 = row43.createCell(0);
            cell73.setCellValue("Name");
            stringStyle3.setFont(font3);
            cell73.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(43,43,0,5));
            
            Cell cell74 = row43.createCell(6);
            cell74.setCellValue(truck_driver);
            cell74.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(43,43,6,15));
            
            /*Row 44*/
            Row row44 = sheet.createRow(44);  
            Cell cell77 = row44.createCell(0);
            cell77.setCellValue("Address");
            stringStyle3.setFont(font3);
            cell77.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(44,44,0,5));
            
            Cell cell76 = row44.createCell(6);
            cell76.setCellValue(truck_driver_addrs);
            cell76.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(44,44,6,15));
            
            /*Row 45*/
            Row row45 = sheet.createRow(45);  
            Cell cell78 = row45.createCell(0);
            cell78.setCellValue("Driving Licence No.");
            stringStyle3.setFont(font3);
            cell78.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(45,45,0,5));
            
            Cell cell79 = row45.createCell(6);
            cell79.setCellValue(truck_driver_lic_no);
            cell79.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(45,45,6,15));
            
            /*Row 46*/
            Row row46 = sheet.createRow(46);  
            Cell cell80 = row46.createCell(0);
            cell80.setCellValue("Licence Issueing State");
            stringStyle3.setFont(font3);
            cell80.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(46,46,0,5));
            
            Cell cell81 = row46.createCell(6);
            cell81.setCellValue(truck_lic_state);
            cell81.setCellStyle(stringStyle3);
            sheet.addMergedRegion(new CellRangeAddress(46,46,6,15));
            
            wb.write(fileOut); 
//            wb.close();  
//			sheet.addMergedRegion(rowFrom,rowTo,colFrom,colTo);
            System.out.println("Your excel file has been generated!");	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public double getTotal_net_amt_inr() {
		return total_net_amt_inr;
	}

	public String getContact_Suppl_CST_NO() {
		return contact_Suppl_CST_NO;
	}

	public String getContact_Customer_Name() {
		return contact_Customer_Name;
	}

	public String getContact_Customer_Person_City() {
		return contact_Customer_Person_City;
	}

	public String getContact_Customer_Person_Address() {
		return contact_Customer_Person_Address;
	}

	public String getContact_Customer_Person_Pin() {
		return contact_Customer_Person_Pin;
	}

	public String getNew_inv_seq_no() {
		return new_inv_seq_no;
	}

	public String getInvoice_date() {
		return invoice_date;
	}

	public String getTotal_qty() {
		return total_qty;
	}

	public String getTaxRate() {
		return taxRate;
	}

	public String getTruck_trans_nm() {
		return truck_trans_nm;
	}

	public String getTruck_driver() {
		return truck_driver;
	}

	public String getTruck_driver_addrs() {
		return truck_driver_addrs;
	}

	public String getTruck_nm() {
		return truck_nm;
	}

	public String getTruck_trans_addrs() {
		return truck_trans_addrs;
	}

	public String getTruckLinkedFlg() {
		return truckLinkedFlg;
	}

	public String getTruck_driver_lic_no() {
		return truck_driver_lic_no;
	}

	public String getTruck_lic_state() {
		return truck_lic_state;
	}

	public void setTotal_net_amt_inr(double total_net_amt_inr) {
		this.total_net_amt_inr = total_net_amt_inr;
	}

	public void setContact_Suppl_CST_NO(String contact_Suppl_CST_NO) {
		this.contact_Suppl_CST_NO = contact_Suppl_CST_NO;
	}

	public void setContact_Customer_Name(String contact_Customer_Name) {
		this.contact_Customer_Name = contact_Customer_Name;
	}

	public void setContact_Customer_Person_City(String contact_Customer_Person_City) {
		this.contact_Customer_Person_City = contact_Customer_Person_City;
	}

	public void setContact_Customer_Person_Address(String contact_Customer_Person_Address) {
		this.contact_Customer_Person_Address = contact_Customer_Person_Address;
	}

	public void setContact_Customer_Person_Pin(String contact_Customer_Person_Pin) {
		this.contact_Customer_Person_Pin = contact_Customer_Person_Pin;
	}

	public void setNew_inv_seq_no(String new_inv_seq_no) {
		this.new_inv_seq_no = new_inv_seq_no;
	}

	public void setInvoice_date(String invoice_date) {
		this.invoice_date = invoice_date;
	}

	public void setTotal_qty(String total_qty) {
		this.total_qty = total_qty;
	}

	public void setTaxRate(String taxRate) {
		this.taxRate = taxRate;
	}

	public void setTruck_trans_nm(String truck_trans_nm) {
		this.truck_trans_nm = truck_trans_nm;
	}

	public void setTruck_driver(String truck_driver) {
		this.truck_driver = truck_driver;
	}

	public void setTruck_driver_addrs(String truck_driver_addrs) {
		this.truck_driver_addrs = truck_driver_addrs;
	}

	public void setTruck_nm(String truck_nm) {
		this.truck_nm = truck_nm;
	}

	public void setTruck_trans_addrs(String truck_trans_addrs) {
		this.truck_trans_addrs = truck_trans_addrs;
	}

	public void setTruckLinkedFlg(String truckLinkedFlg) {
		this.truckLinkedFlg = truckLinkedFlg;
	}

	public void setTruck_driver_lic_no(String truck_driver_lic_no) {
		this.truck_driver_lic_no = truck_driver_lic_no;
	}

	public void setTruck_lic_state(String truck_lic_state) {
		this.truck_lic_state = truck_lic_state;
	}

	public void setCheck_post(String check_post) {
		this.check_post = check_post;
	}

	public String getContact_Customer_Person_State() {
		return contact_Customer_Person_State;
	}

	public void setContact_Customer_Person_State(String contact_Customer_Person_State) {
		this.contact_Customer_Person_State = contact_Customer_Person_State;
	}

	public Vector getvSTAT_CD() {
		return vSTAT_CD;
	}

	public Vector getvSTAT_NM() {
		return vSTAT_NM;
	}

	public Vector getvSTAT_NO() {
		return vSTAT_NO;
	}

	public Vector getvSTAT_EFF_DT() {
		return vSTAT_EFF_DT;
	}

	public void setvSTAT_CD(Vector vSTAT_CD) {
		this.vSTAT_CD = vSTAT_CD;
	}

	public void setvSTAT_NM(Vector vSTAT_NM) {
		this.vSTAT_NM = vSTAT_NM;
	}

	public void setvSTAT_NO(Vector vSTAT_NO) {
		this.vSTAT_NO = vSTAT_NO;
	}

	public void setvSTAT_EFF_DT(Vector vSTAT_EFF_DT) {
		this.vSTAT_EFF_DT = vSTAT_EFF_DT;
	}

	public String getContact_Customer_CST_NO() {
		return contact_Customer_CST_NO;
	}

	public String getContact_Customer_CST_DT() {
		return contact_Customer_CST_DT;
	}

	public void setContact_Customer_CST_NO(String contact_Customer_CST_NO) {
		this.contact_Customer_CST_NO = contact_Customer_CST_NO;
	}

	public void setContact_Customer_CST_DT(String contact_Customer_CST_DT) {
		this.contact_Customer_CST_DT = contact_Customer_CST_DT;
	}
}
