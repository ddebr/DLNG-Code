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
import java.net.InetAddress.*;
import javax.naming.*;
//import com.gpecindia.paguthan.fms.util.RuntimeConf;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

class EmailMimeMessage extends MimeMessage {
    
    	
    /** The value stored in the Message-ID header tag for the message.*/
    protected String messageID = "";
    
    /**
     * Create a new EmailMimeMessage object.
     * @param session The javax.mail.Session object the mail message is for.
     *
     */        
    public EmailMimeMessage(javax.mail.Session session) {
        super(session);
    }
    
    /**
     * Set the value stored in the Message-ID header tag for the message.
     * @param p_value The value of the Message-ID header tag.
     *
     */        
    void setMessageID(String p_value) {
        messageID = p_value;
    }
    
    /**
     * Calls the super.updateHeaders() method, and also ensures 
     * that the Message-ID header tag
     * gets set if the setMessageID method was called.
     *
     * @throws MessagingException If an error occurs.
     */        
    protected void updateHeaders() throws MessagingException {
        super.updateHeaders();
        if (messageID != null && messageID.length() > 0) {
            setHeader("Message-ID", messageID);
        }
    }
}

