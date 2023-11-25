package com.seipl.hazira.dlng.mail;
 
import javax.mail.*;
import javax.activation.*;
import javax.mail.internet.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.Date;
import java.util.Hashtable;
import java.io.*;
import java.sql.*;
import javax.sql.DataSource;

import java.net.UnknownHostException;
import java.net.InetAddress.*;
import javax.naming.*;
//import com.gpecindia.paguthan.fms.util.RuntimeConf;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

 
public class mail extends Thread
{	
    private static Message msg=null;
	private static Statement stmt = null; 
	String combine="";
	String sEmail="";
	String sName="";
	String flg_img="";
	String path_img="";
	String ccadd="";
    
	 public boolean sendMail(String subject,String mBody,
             String rName, String rEmail,
             String sName, String sEmail) throws AddressException, MessagingException
	 {
	
		try
		{
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
				
				
			// Prepare our mail message
				
				
			EmailDelivery ed = new EmailDelivery();

	        //Update the to/cc/bcc/from email addresseses. Note that the to, cc, and bcc
	        //methods can take a command separated list.
	        //ed.setTo("kotecha_rohit@yahoo.com,luhar.m@lycos.com");
	            
	        ed.setTo(rEmail);
	        
	        //ed.setCC("tima@test.com");
	        //ed.setBCC("tima@anotheremailaddress.com");
	        //ed.setFrom("somebody@test.com", "Joe Smith");
	        
	        ed.setFrom(sEmail, sName);
	        //ed.setReplyTo("replytome@test.com", "Reply To Me");
	            
	        //ed.setSubject("Test Message Subject");
	        //ed.setBody("Test message body.");
	            
	        ed.setSubject(subject);
	        ed.setBody(mBody);
	            
	        //ed.setSMTPPort(3000);
	            
	        //Add the file attachment. Make sure this file exists for your testing.
	            
	        //ed.addFileAttachment("c:\\temp\\attachment.txt");
	        //ed.addFileAttachment(path);
	            
	        //Set the SMTP host to use to relay the message, and if it requires
	        //smtp authentication then also specify the username and password.
	        
	        ed.setSMTPHost("webmail.barodainformatics.com", sEmail, "Bipl_123");
	        //ed.setSMTPHost("66.46.50.99", "Samik@eximcan.com", "2008");
	        //ed.setSMTPHost("74.86.97.222", "samikshah@barodainformatics.com", "samikshah123");
	           	            
	        ed.sendMsg();
	            
	        return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}				
	 }//End of sendMail() Method

	 public int sendMail1(String subject,String mBody,
             String rName, String rEmail,
             String sName, String sEmail,String path,
             String smtpHost, String smtpUserName, 
             String smtpPassword, boolean attachmentFlg) throws AddressException 
	{
		try
		{
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
				
			// Prepare our mail message
			EmailDelivery ed = new EmailDelivery();
	        ed.setTo(rEmail);
	        
	        ed.setCC(ccadd);
	        
	        ed.setFrom(sEmail, sName);
	        
	        ed.setSubject(subject);
	        ed.setBody(mBody);
	            
	        if(attachmentFlg) {
		        if(!flg_img.equals("Y"))
		        {
		        	ed.addFileAttachment(path);
		        }
		        else 
		        {
		        	ed.addFileAttachment1(path,path_img);
				}
	        }
	        ed.setSMTPPort(587);
	        ed.setSMTPHost(smtpHost,smtpUserName,smtpPassword);
	        ed.sendMsg();
	        return 1;
		}
		catch (AuthenticationFailedException e) {
			e.printStackTrace();
			return 2;
	    } 
		catch(com.sun.mail.smtp.SMTPSendFailedException st) {
			st.printStackTrace();
			return 4;
		}
		catch(MessagingException mex)
		{
			System.out.println("In send mail 1 = "+mex.getMessage());
			mex.printStackTrace();
			return 3;
		}
		catch(Exception ex)
		{
			System.out.println("In send mail 1 = "+ex.getMessage());
			ex.printStackTrace();
			return 0;
		}
	}//End of sendMail() Method

	public String getFlg_img() {
		return flg_img;
	}

	public void setFlg_img(String flg_img) {
		this.flg_img = flg_img;
	}

	public String getPath_img() {
		return path_img;
	}

	public void setPath_img(String path_img) {
		this.path_img = path_img;
	}

	public String getCcadd() {
		return ccadd;
	}

	public void setCcadd(String ccadd) {
		this.ccadd = ccadd;
	}
	 
	 
	/*public boolean sendMail1(String subject,String mBody,
                          String rName, String rEmail,
                          String sName, String sEmail,String e) throws AddressException, MessagingException
    {
	   try  {
	        Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
            Session session = (Session) envCtx.lookup("mail/Session");
           // session.setDebug(true);
			System.out.println("TO ADDRESS ::::::::::::::::::::::::::::::::"+rEmail);
            // Prepare our mail message
            Message message = new MimeMessage(session);
           // System.out.println(message);
		/*Properties props=System.getProperties();
		props.put("mail.smtp.host",mailhost);
		Session sess=Session.getDefaultInstance(props,null);
		msg=new MimeMessage(sess);*/
	

		/*if(sEmail !=null)
		{
			System.out.println("semail"+sEmail);
			message.setFrom(new InternetAddress(sEmail));
		}
		else
		{
           message.setFrom();
		}
        try
		{
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(rEmail,false));
		}
        catch(Exception e1)
        {
            System.out.println("Message Recipents Exception :"+e1);
        }
             
        message.setSubject(subject);
        
      //  For  Document as Attachment  11sep2008 
       
        
        // create the message part 
        MimeBodyPart messageBodyPart = 
          new MimeBodyPart();
       
        //messageBodyPart.setText(mBody,"text/html");
        
       messageBodyPart.setText(subject);
       Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

        messageBodyPart = new MimeBodyPart();
        SimpleDateFormat sdf=new SimpleDateFormat(" E dd MM yyyy hh.mm.ss  ");
        Calendar cls=Calendar.getInstance();
        
        sdf.format(cls.getTime());
        
     //   String path = request.getRealPath(request.getContextPath());
        FileDataSource ds =new FileDataSource(e);
        //FileDataSource ds =new FileDataSource("C:"+File.separator+"AttachDoc"+File.separator+"Attachment"+sdf.format(cls.getTime())+".html");
        messageBodyPart.setDataHandler( new DataHandler((javax.activation.DataSource) ds));
          
        // Part two is attachment
     
        
        //messageBodyPart.setFileName("C:"+File.separator+"AttachDoc"+File.separator+"Attachment.html");
        messageBodyPart.setFileName("Attachment.html");
        multipart.addBodyPart(messageBodyPart);

        // Put parts in message
        message.setContent(multipart);
      
        
       //message.setContent(mbody,"text/html");
       // message.setContent(multipart,"text/html");
		
        message.setHeader("X-Mailer","PRX_Java-Mail_Custom-System");
        message.setSentDate(new Date());
       // System.out.println(message);
    

		try
		{
           Transport.send(message);
        }
        catch(Exception e)
        {
            System.out.println("Transport Exception :"+e);
            e.printStackTrace();
        } 
		
		} catch (Throwable t) {t.printStackTrace();}
		return true;
	}//End of sendMail() Method*/
	
	
	
} // end of class
