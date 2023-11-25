package com.seipl.hazira.dlng.util;
import java.io.FileOutputStream;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
 
 
/**
 *
 *  How to add text as Watermark In Pdf
 *  Example Using iText library - core java tutorial
 *
 */
/*public class AddTextAsWatermarkInPdfExample {
    public static void main(String[] args) {
           try {
                  
                  String pdfFilePath = "E:/FMS9_Workspace/DLNG/unsigned_pdf/sales_invoice/SALES_INVOICE-18122020-MGL-MGL-S-21-D.pdf";
                  
                  Document document = new Document();
                  PdfWriter pdfWriter = PdfWriter
                               .getInstance(document, new FileOutputStream(
                                             pdfFilePath));
                  pdfWriter.setPageEvent(new MyPdfPageEventHelper()); // register the
                                                                                                        // page event helper
 
                  document.open();
 
                  System.out.println("Writing Paragraph to PDF");
 
 
                  for(int i=0; i<40; i++) //For loop -To show text is overlapping the waterMark text
                  document.add(new Paragraph(
                              "This document contains a Watermark text (Background text). "));
 
                  document.close();
                  
                  
                  System.out.println("PDF created in >> "+ pdfFilePath);
 
           } catch (Exception e) {
                  e.printStackTrace();
           }
    }
}
 */
 
/**
 * Extend PdfPageEventHelper class and override onEndPagemethod
 * to create page event helper.
 *
 * onEndPage method gets called as soon as document is closed i.e.
 * immediately after document.close(); is called
 */
class MyPdfPageEventHelper extends PdfPageEventHelper {
 
    @Override
    public void onEndPage(PdfWriter pdfWriter, Document document) {
 
           System.out.println("Creating text as Waterwark in PDF");
           
           PdfContentByte pdfContentByte = pdfWriter.getDirectContentUnder();
 
           String waterMarkText = "JavaMadeSoEasy";
 
  //create Phrase and pass waterMarkText in constructor, 
  //set the waterMarkText’s font name, size, style, colour.  
           Phrase phrase = new Phrase(waterMarkText, new Font(
                        FontFamily.HELVETICA, //Select the Font name of waterMark Text
                        60, //Select the Font type of waterMark Text
                        Font.ITALIC, //Select the Font style of waterMark Text 
                        BaseColor.LIGHT_GRAY)); //Select the Font colour of waterMark Text
                                //Keep colour as Light only, so that it appears
                                //Separately as watermark and  does not obscure text of the page
            
 
           // 300f is x axis,
           // 550f is y axis,
           ColumnText.showTextAligned(pdfContentByte,
                        Element.ALIGN_CENTER, //Keep waterMark center aligned
                        phrase, 300f, 500f,
                        45f); // 45f is the rotation angle
    }
}
 
 
/* OUTPUT of above program/Example -
 
Writing Paragraph to PDF
Creating text as Waterwark in PDF
PDF created in >> E:/Add Text As Watermark text In Pdf.pdf
 
*/