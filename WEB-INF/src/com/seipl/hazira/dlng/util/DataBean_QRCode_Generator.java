package com.seipl.hazira.dlng.util;

import java.io.File;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Hashtable;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
 

public class DataBean_QRCode_Generator {
	public static void main(String[] args) throws WriterException, IOException {
		  String qrCodeText = "657a2779947d1325c99bc20affe95d45d7aceea55b763a40ab8605f6122f6611";
		  String filePath = "E:\\JD.png";
		  int size = 45;//125 original
		  String fileType = "png";
		  File qrFile = new File(filePath);
		  createQRImage(qrFile, qrCodeText, size, fileType);
		  System.out.println("DONE");
		 }
		 
		 public static void createQRImage(File qrFile, String qrCodeText, int size,
		   String fileType) throws WriterException, IOException {
		  // Create the ByteMatrix for the QR-Code that encodes the given String
		  Hashtable hintMap = new Hashtable();
		  hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		  QRCodeWriter qrCodeWriter = new QRCodeWriter();
		  BitMatrix byteMatrix = qrCodeWriter.encode(qrCodeText,
		    BarcodeFormat.QR_CODE, size, size, hintMap);
		  // Make the BufferedImage that are to hold the QRCode
		  int matrixWidth = byteMatrix.getWidth();
		  BufferedImage image = new BufferedImage(matrixWidth, matrixWidth,
		    BufferedImage.TYPE_INT_RGB);
		  image.createGraphics();
		 
		  Graphics2D graphics = (Graphics2D) image.getGraphics();
		  graphics.setColor(Color.WHITE);
		  graphics.fillRect(0, 0, matrixWidth, matrixWidth);
		  // Paint and save the image using the ByteMatrix
		  graphics.setColor(Color.BLACK);
		 
		  for (int i = 0; i < matrixWidth; i++) {
		   for (int j = 0; j < matrixWidth; j++) {
		    if (byteMatrix.get(i, j)) {
		     graphics.fillRect(i, j, 1, 1);
		    }
		   }
		  }
		  ImageIO.write(image, fileType, qrFile);
		 }
}
