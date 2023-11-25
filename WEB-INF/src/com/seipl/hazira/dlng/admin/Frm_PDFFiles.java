// 
// Decompiled by Procyon v0.5.36
// 

package com.seipl.hazira.dlng.admin;

import java.util.Vector;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.io.FileOutputStream;
import java.io.File;

import com.seipl.hazira.dlng.util.RuntimeConf;

import javax.sql.DataSource;
import javax.mail.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServlet;

@WebServlet("/servlet/Frm_PDFFiles")
public class Frm_PDFFiles extends HttpServlet
{
    String FromDate;
    SimpleDateFormat SD;
    Vector unsigned_pdf=new Vector();
    
    
    public Frm_PDFFiles() {
        this.FromDate = "20160501";
        this.SD = new SimpleDateFormat("yyyyMMdd");
    }
    
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        final String option = (request.getParameter("option") == null) ? "" : request.getParameter("option");
        System.out.println("option: " + option);
        if (option.equals("Original")) {
            this.getOriginalFiles(request, response);
        }
        else if (option.equals("Signed")) {
            this.getSignedFiles(request, response);
        }
        else if (option.equalsIgnoreCase("Setup")) {
            this.getSetupValues(request, response);
        }
        else if (option.equalsIgnoreCase("Types")) {
            this.getTypeValues(request, response);
        }
        else if (option.equalsIgnoreCase("UploadFiles")) {
            this.uploadFiles(request, response);
        }
        else if (option.equalsIgnoreCase("DownloadFile")) {
            this.douwnloadFile(request, response);
        }
        else {
            final PrintWriter out = response.getWriter();
            out.println("Please Set 'option' Flag Proper");
        }
    }
    
    private void douwnloadFile(final HttpServletRequest request, final HttpServletResponse response) {
        Connection dbcon = null;
        PreparedStatement pstmt = null;
        Statement stmt = null;
        Statement stmt2 = null;
        ResultSet rset = null;
        ResultSet rset2 = null;
        try {
            final InitialContext initialcontext = new InitialContext();
            if (initialcontext == null) {
                throw new Exception("Boom - No Context");
            }
            final Context context = (Context)initialcontext.lookup("java:/comp/env");
            final DataSource datasource = (DataSource)context.lookup(RuntimeConf.security_database);
            if (datasource != null) {
                dbcon = datasource.getConnection();
                if (dbcon != null) {
                    dbcon.setAutoCommit(false);
                    stmt = dbcon.createStatement();
                    rset = stmt.executeQuery("select PDF_SIGNED from dlng_inv_pdf_dtl where PDF_INV_NM='INVOICE-31072015-MGL-MGL-C-20' and INV_TYPE='T'");
                    while (rset.next()) {
                        final Blob blob = rset.getBlob("PDF_SIGNED");
                        final InputStream in = blob.getBinaryStream();
                        final OutputStream out = new FileOutputStream(new File("D:/INVOICE-31072015-MGL-MGL-C-20.pdf"));
                        final byte[] buff = new byte[4096];
                        int len = 0;
                        while ((len = in.read(buff)) != -1) {
                            out.write(buff, 0, len);
//                            System.out.println("----");
                        }
                        out.close();
                    }
                }
                else {
                    final PrintWriter out2 = response.getWriter();
                    out2.println("Error: Connection is null");
                }
            }
            else {
                final PrintWriter out2 = response.getWriter();
                out2.println("Error: datasource is null");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            PrintWriter out3 = null;
            try {
                out3 = response.getWriter();
            }
            catch (IOException e2) {
                e2.printStackTrace();
            }
            out3.println("Error: " + e.getLocalizedMessage());
            return;
        }
        finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                }
                catch (SQLException e3) {
                    System.out.println("Exception in Frm_admin " + e3);
                }
                pstmt = null;
            }
            if (rset != null) {
                try {
                    rset.close();
                }
                catch (SQLException e3) {
                    System.out.println("Exception in Frm_admin " + e3);
                }
                rset = null;
            }
            if (stmt != null) {
                try {
                    stmt.close();
                }
                catch (SQLException e3) {
                    System.out.println("Exception in Frm_admin " + e3);
                }
                stmt = null;
            }
            if (rset2 != null) {
                try {
                    rset2.close();
                }
                catch (SQLException e3) {
                    System.out.println("Exception in Frm_admin " + e3);
                }
                rset2 = null;
            }
            if (stmt2 != null) {
                try {
                    stmt2.close();
                }
                catch (SQLException e3) {
                    System.out.println("Exception in Frm_admin " + e3);
                }
                stmt2 = null;
            }
            if (dbcon != null) {
                try {
                    dbcon.close();
                }
                catch (SQLException e3) {
                    System.out.println("Exception in Frm_admin " + e3);
                }
                dbcon = null;
            }
        }
        if (pstmt != null) {
            try {
                pstmt.close();
            }
            catch (SQLException e3) {
                System.out.println("Exception in Frm_admin " + e3);
            }
            pstmt = null;
        }
        if (rset != null) {
            try {
                rset.close();
            }
            catch (SQLException e3) {
                System.out.println("Exception in Frm_admin " + e3);
            }
            rset = null;
        }
        if (stmt != null) {
            try {
                stmt.close();
            }
            catch (SQLException e3) {
                System.out.println("Exception in Frm_admin " + e3);
            }
            stmt = null;
        }
        if (rset2 != null) {
            try {
                rset2.close();
            }
            catch (SQLException e3) {
                System.out.println("Exception in Frm_admin " + e3);
            }
            rset2 = null;
        }
        if (stmt2 != null) {
            try {
                stmt2.close();
            }
            catch (SQLException e3) {
                System.out.println("Exception in Frm_admin " + e3);
            }
            stmt2 = null;
        }
        if (dbcon != null) {
            try {
                dbcon.close();
            }
            catch (SQLException e3) {
                System.out.println("Exception in Frm_admin " + e3);
            }
            dbcon = null;
        }
    }
    
    void getSetupValues(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
    	
        String server = "";
        String username = "";
        String password = "";
        String directory = "";
        final File setup = new File(String.valueOf(request.getRealPath("")) + "/Pdfsetup.txt");
        if (setup.exists()) {
            final FileInputStream fis = new FileInputStream(setup.getAbsolutePath());
            final DataInputStream dis = new DataInputStream(fis);
            final BufferedReader br = new BufferedReader(new InputStreamReader(dis));
            String str = "";
            while ((str = br.readLine()) != null) {
                if (str.startsWith("server")) {
                    final String[] temp = str.split("server:");
                    if (temp.length > 1) {
                        server = temp[1];
                    }
                }
                if (str.startsWith("username")) {
                    final String[] temp = str.split("username:");
                    if (temp.length > 1) {
                        username = temp[1];
                    }
                }
                if (str.startsWith("password")) {
                    final String[] temp = str.split("password:");
                    if (temp.length > 1) {
                        password = temp[1];
                    }
                }
                if (str.startsWith("directory")) {
                    final String[] temp = str.split("directory:");
                    if (temp.length > 1) {
                        directory = temp[1];
                    }
                }
                if (str.startsWith("date")) {
                    final String[] temp = str.split("date:");
                    if (temp.length <= 1) {
                        continue;
                    }
                    this.FromDate = temp[1];
                }
            }
            final PrintWriter out = response.getWriter();
            out.println(String.valueOf(server) + "##" + username + "##" + password + "##" + directory);
        }
        else {
            final PrintWriter out2 = response.getWriter();
            out2.println("Pdfsetup.txt, File Not Found..!");
        }
    }
    
    void getTypeValues(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        final File setup = new File(String.valueOf(request.getRealPath("")) + "/Pdftypes.txt");
        if (setup.exists()) {
            final FileInputStream fis = new FileInputStream(setup.getAbsolutePath());
            final DataInputStream dis = new DataInputStream(fis);
            final BufferedReader br = new BufferedReader(new InputStreamReader(dis));
            String str = "";
            String v = "";
            while ((str = br.readLine()) != null) {
                v = String.valueOf(v) + "##" + str;
            }
            final PrintWriter out = response.getWriter();
            out.println(v.replaceFirst("##", ""));
        }
        else {
            final PrintWriter out2 = response.getWriter();
            out2.println("Pdftypes.txt, File Not Found..!");
        }
    }
    
    void uploadFiles(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        Connection dbcon = null;
        PreparedStatement pstmt = null;
        Statement stmt = null;
        Statement stmt2 = null;
        ResultSet rset = null;
        ResultSet rset2 = null;
        try {
            final InitialContext initialcontext = new InitialContext();
            if (initialcontext == null) {
                throw new Exception("Boom - No Context");
            }
            final Context context = (Context)initialcontext.lookup("java:/comp/env");
            final DataSource datasource = (DataSource)context.lookup(RuntimeConf.security_database);
            if (datasource != null) {
                dbcon = datasource.getConnection();
                if (dbcon != null) {
                    dbcon.setAutoCommit(false);
                    stmt = dbcon.createStatement();
                    final String DirectoryPath = (request.getParameter("DirectoryPath") == null) ? "" : request.getParameter("DirectoryPath");
                    final String pathSigned = request.getRealPath(String.valueOf(DirectoryPath) + "/signed");
                    final String PdfFile = request.getParameter("PdfFile");
//                    System.out.println(PdfFile);
                    if (new File(pathSigned).exists()) {
                        String updatedFiles = "";
                        String type = "";
                        String removeType = "";
                        final String inv_ty = PdfFile.replace(".pdf", "").substring(PdfFile.replace(".pdf", "").length() - 1);
                        if (inv_ty.equalsIgnoreCase("O")) {
                            type = "O";
                            removeType = "-O";
                        }
                        else if (inv_ty.equalsIgnoreCase("D")) {
                            type = "D";
                            removeType = "-D";
                        }
                        else if (inv_ty.equalsIgnoreCase("T")) {
                            type = "T";
                            removeType = "-T";
                        }
                        else if (inv_ty.equalsIgnoreCase("C")) {
                            type = "C";
                            removeType = "-C";
                        }
                        
//                        System.out.println("-----" + PdfFile);
                        if (!type.equals("")) {
                            int count = 0;
                            final String query = "select count(*) from dlng_inv_pdf_dtl where PDF_INV_NM='" + PdfFile.trim().replace(".pdf", "").substring(0, PdfFile.replace(".pdf", "").length() - 2) + "' and INV_TYPE='" + type + "'";
                            rset = stmt.executeQuery(query);
                            if (rset.next()) {
                                count = rset.getInt(1);
                            }
                            if (count == 1) {
//                                System.out.println("Update: ");
                                pstmt = dbcon.prepareStatement("update dlng_inv_pdf_dtl   set PDF_SIGNED = ?, SIGNED_DT=sysdate where PDF_INV_NM='" + PdfFile.trim().replace(".pdf", "").substring(0, PdfFile.replace(".pdf", "").length() - 2) + "' and INV_TYPE='" + type + "'");
                                final File blob = new File(String.valueOf(pathSigned) + "/" + PdfFile);
                                final FileInputStream in = new FileInputStream(blob);
                                pstmt.setBinaryStream(1, in, (int)blob.length());
                                if (pstmt.executeUpdate() == 1) {
                                    updatedFiles = PdfFile;
                                }
                                dbcon.commit();
                                final PrintWriter out = response.getWriter();
                                out.println("Completed: " + updatedFiles);
                            }
                            else {
//                                System.out.println("Insert: ");
                                pstmt = dbcon.prepareStatement("insert into dlng_inv_pdf_dtl    (PDF_SIGNED,PDF_INV_NM,SIGNED_DT,INV_TYPE,flag) values (?,'" + PdfFile.trim().replace(".pdf", "").substring(0, PdfFile.replace(".pdf", "").length() - 2) + "',sysdate,'" + type.trim() + "','Y') ");
                                final File blob = new File(String.valueOf(pathSigned) + "/" + PdfFile);
                                final FileInputStream in = new FileInputStream(blob);
                                pstmt.setBinaryStream(1, in, (int)blob.length());
                                if (pstmt.executeUpdate() == 1) {
                                    updatedFiles = PdfFile;
                                }
                                dbcon.commit();
                                final PrintWriter out = response.getWriter();
                                out.println("Completed: " + updatedFiles);
                            }
                        }
                        else {
                            final PrintWriter out2 = response.getWriter();
                            out2.println("Error: " + inv_ty + " Not Proper Invoice Type Mentioned ");
                        }
                    }
                }
                else {
                    final PrintWriter out3 = response.getWriter();
                    out3.println("Error: Connection is null");
                }
            }
            else {
                final PrintWriter out3 = response.getWriter();
                out3.println("Error: datasource is null");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            final PrintWriter out4 = response.getWriter();
            out4.println("Error: " + e.getLocalizedMessage());
            return;
        }
        finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                }
                catch (SQLException e2) {
                    System.out.println("Exception in Frm_admin " + e2);
                }
                pstmt = null;
            }
            if (rset != null) {
                try {
                    rset.close();
                }
                catch (SQLException e2) {
                    System.out.println("Exception in Frm_admin " + e2);
                }
                rset = null;
            }
            if (stmt != null) {
                try {
                    stmt.close();
                }
                catch (SQLException e2) {
                    System.out.println("Exception in Frm_admin " + e2);
                }
                stmt = null;
            }
            if (rset2 != null) {
                try {
                    rset2.close();
                }
                catch (SQLException e2) {
                    System.out.println("Exception in Frm_admin " + e2);
                }
                rset2 = null;
            }
            if (stmt2 != null) {
                try {
                    stmt2.close();
                }
                catch (SQLException e2) {
                    System.out.println("Exception in Frm_admin " + e2);
                }
                stmt2 = null;
            }
            if (dbcon != null) {
                try {
                    dbcon.close();
                }
                catch (SQLException e2) {
                    System.out.println("Exception in Frm_admin " + e2);
                }
                dbcon = null;
            }
        }
        if (pstmt != null) {
            try {
                pstmt.close();
            }
            catch (SQLException e2) {
                System.out.println("Exception in Frm_admin " + e2);
            }
            pstmt = null;
        }
        if (rset != null) {
            try {
                rset.close();
            }
            catch (SQLException e2) {
                System.out.println("Exception in Frm_admin " + e2);
            }
            rset = null;
        }
        if (stmt != null) {
            try {
                stmt.close();
            }
            catch (SQLException e2) {
                System.out.println("Exception in Frm_admin " + e2);
            }
            stmt = null;
        }
        if (rset2 != null) {
            try {
                rset2.close();
            }
            catch (SQLException e2) {
                System.out.println("Exception in Frm_admin " + e2);
            }
            rset2 = null;
        }
        if (stmt2 != null) {
            try {
                stmt2.close();
            }
            catch (SQLException e2) {
                System.out.println("Exception in Frm_admin " + e2);
            }
            stmt2 = null;
        }
        if (dbcon != null) {
            try {
                dbcon.close();
            }
            catch (SQLException e2) {
                System.out.println("Exception in Frm_admin " + e2);
            }
            dbcon = null;
        }
    }
    
    void getOriginalFiles(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        final Vector files = new Vector();
        Connection dbcon = null;
        Statement stmt = null;
        Statement stmt2 = null;
        ResultSet rset = null;
        ResultSet rset2 = null;
        try {
        	System.out.println("******************in original files servlet******************");
            final InitialContext initialcontext = new InitialContext();
            if (initialcontext == null) {
                throw new Exception("Boom - No Context");
            }
            final Context context = (Context)initialcontext.lookup("java:/comp/env");
            final DataSource datasource = (DataSource)context.lookup(RuntimeConf.security_database);
            if (datasource != null) {
                dbcon = datasource.getConnection();
                if (dbcon != null) {
                    dbcon.setAutoCommit(false);
                    stmt = dbcon.createStatement();
                    final String DirectoryPath = (request.getParameter("DirectoryPath") == null) ? "" : request.getParameter("DirectoryPath");
                    final String unsigned_path = (request.getParameter("unsigned_path") == null) ? "" : request.getParameter("unsigned_path");
                    final String path = request.getRealPath(unsigned_path);
                    final String pathSigned = request.getRealPath(String.valueOf(DirectoryPath) + "/signed");
                    if (new File(path).exists()) {
                        if (new File(pathSigned).exists()) {
                            String[] file_bunch_qtr = null;
                            String[] file_bunch_qtr_signed = null;
                            final File lst_qtr = new File(path);
                            final File lst_qtr_signed = new File(pathSigned);
                            file_bunch_qtr = lst_qtr.list();
                            file_bunch_qtr_signed = lst_qtr_signed.list();
                            String file = null;
                            for (int j = 0; j < file_bunch_qtr.length; ++j) {
                                file = file_bunch_qtr[j];
                                boolean flag = true;
                                for (int i = 0; i < file_bunch_qtr_signed.length; ++i) {
                                    if (file_bunch_qtr_signed[i].equalsIgnoreCase(file)) {
                                        flag = false;
                                    }
                                }
                                final String PdfFile = file;
                                final String updatedFiles = "";
                                String type = "";
                                String removeType = "";
                                final String inv_ty = PdfFile.replace(".pdf", "").substring(PdfFile.replace(".pdf", "").length() - 1);
                                if (inv_ty.equalsIgnoreCase("O")) {
                                    type = "O";
                                    removeType = "-O";
                                }
                                else if (inv_ty.equalsIgnoreCase("D")) {
                                    type = "D";
                                    removeType = "-D";
                                }
                                else if (inv_ty.equalsIgnoreCase("-T")) {
                                    type = "T";
                                    removeType = "-T";
                                }
                                else if (inv_ty.equalsIgnoreCase("-C")) {
                                    type = "C";
                                    removeType = "-C";
                                }
                                int count = 0;
                                if (flag && new File(String.valueOf(path) + "/" + file).isFile() && new File(String.valueOf(path) + "/" + file).length() > 0L && Long.parseLong(this.SD.format(new File(String.valueOf(path) + "/" + file).lastModified())) >= Long.parseLong(this.FromDate) && file.endsWith(".pdf")) {
                                    final String query = "select count(*) from dlng_inv_pdf_dtl where PDF_INV_NM='" + PdfFile.trim().replace(".pdf", "").substring(0, PdfFile.replace(".pdf", "").length() - 2) + "' and INV_TYPE='" + type + "' and pdf_signed is not null";
                                    rset = stmt.executeQuery(query);
                                    System.out.println("print query ruchi--"+query);
                                    if (rset.next()) {
                                        count = rset.getInt(1);
                                    }
                                    if (count == 0) {
                                        files.add(file);
                                        unsigned_pdf.add(file);
                                    }
                                }
                            }
                            final PrintWriter out = response.getWriter();
                            out.println(files);
                        }
                        else {
                            final PrintWriter out2 = response.getWriter();
                            out2.println("'Signed' Directory is Not Available At " + DirectoryPath);
                        }
                    }
                    else {
                        final PrintWriter out2 = response.getWriter();
                        out2.println(String.valueOf(DirectoryPath) + " Directory is Not Available");
                    }
                }
            }
        }
        catch (Exception e) {
            final PrintWriter out3 = response.getWriter();
            out3.println("Error: " + e.getLocalizedMessage());
            e.printStackTrace();
            System.out.println(" In Frm_PDFFiles Exception " + e + "\n query :");
            return;
        }
        finally {
            if (rset != null) {
                try {
                    rset.close();
                }
                catch (SQLException e2) {
                    System.out.println("Exception in Frm_admin " + e2);
                }
                rset = null;
            }
            if (stmt != null) {
                try {
                    stmt.close();
                }
                catch (SQLException e2) {
                    System.out.println("Exception in Frm_admin " + e2);
                }
                stmt = null;
            }
            if (rset2 != null) {
                try {
                    rset2.close();
                }
                catch (SQLException e2) {
                    System.out.println("Exception in Frm_admin " + e2);
                }
                rset2 = null;
            }
            if (stmt2 != null) {
                try {
                    stmt2.close();
                }
                catch (SQLException e2) {
                    System.out.println("Exception in Frm_admin " + e2);
                }
                stmt2 = null;
            }
            if (dbcon != null) {
                try {
                    dbcon.close();
                }
                catch (SQLException e2) {
                    System.out.println("Exception in Frm_admin " + e2);
                }
                dbcon = null;
            }
        }
    }
    
    void getSignedFiles(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
    	Connection dbcon = null;
        Statement stmt = null;
        Statement stmt2 = null;
        ResultSet rset = null;
        ResultSet rset2 = null;
        try {
        	String methodName="PDFSigner";
        	String form_id="";
        	String form_nm="";
        	//System.out.println("******************in original files servlet******************");
            final InitialContext initialcontext = new InitialContext();
            if (initialcontext == null) {
                throw new Exception("Boom - No Context");
            }
            final Context context = (Context)initialcontext.lookup("java:/comp/env");
            final DataSource datasource = (DataSource)context.lookup(RuntimeConf.security_database);
            if (datasource != null) {
                dbcon = datasource.getConnection();
                if (dbcon != null) {
                    dbcon.setAutoCommit(false);
                    stmt = dbcon.createStatement();
                
            
	        final Vector files = new Vector();
	        final Vector signed_file = new Vector();
	        HttpSession session=request.getSession();
	        final String alias_nm_sel = (request.getParameter("alias_nm") == null) ? "" : request.getParameter("alias_nm");
	        String unsigned_path=request.getParameter("unsigned_path")== null ? "" : request.getParameter("unsigned_path");
	        System.out.println("alias_nm_sel---------------*****************"+unsigned_path);
            final String DirectoryPath = (request.getParameter("DirectoryPath") == null) ? "" : request.getParameter("DirectoryPath");
            final String pathSigned = request.getRealPath(String.valueOf(DirectoryPath) + "/signed");
            if (new File(pathSigned).exists()) {
                String[] file_bunch_qtr_signed = null;
                final File lst_qtr_signed = new File(pathSigned);
                file_bunch_qtr_signed = lst_qtr_signed.list();
                String file = null;
                final SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
                for (int j = 0; j < file_bunch_qtr_signed.length; ++j) {
                    file = file_bunch_qtr_signed[j];
                   // System.out.println("-------------------------file------signed--------------"+file);
                    if (new File(String.valueOf(pathSigned) + "/" + file).isFile() && file.endsWith(".pdf") && Long.parseLong(this.SD.format(new File(String.valueOf(pathSigned) + "/" + file).lastModified())) >= Long.parseLong(this.FromDate)) {
                    	if(unsigned_pdf.contains(file)){
                    		if(!alias_nm_sel.equals("")){
                    			if(unsigned_path.equals("other_invoice")){
                    				if(file.startsWith("OTHER_")){
                    					files.add(String.valueOf(dt1.format(new File(new StringBuilder(String.valueOf(pathSigned)).append("/").append(file).toString()).lastModified())) + " | " + file);
                    				}
                    				System.out.println("files---"+files);
                    			}else if(unsigned_path.equals("sales_invoice")){
                    				if(file.startsWith("SALES_")){
                    					files.add(String.valueOf(dt1.format(new File(new StringBuilder(String.valueOf(pathSigned)).append("/").append(file).toString()).lastModified())) + " | " + file);
                    				}
                    			}else if(unsigned_path.equals("advance_invoice")){
                    				if(file.startsWith("Advance_")){
                    					files.add(String.valueOf(dt1.format(new File(new StringBuilder(String.valueOf(pathSigned)).append("/").append(file).toString()).lastModified())) + " | " + file);
                    				}
                    			}else if(unsigned_path.equals("sug_invoice")){
                    				if(file.startsWith("SUG_")){
                    					files.add(String.valueOf(dt1.format(new File(new StringBuilder(String.valueOf(pathSigned)).append("/").append(file).toString()).lastModified())) + " | " + file);
                    				}
                    			}else if(unsigned_path.equals("ltcora_invoice")){
                    				if(file.startsWith("LTCORA_")){
                    					files.add(String.valueOf(dt1.format(new File(new StringBuilder(String.valueOf(pathSigned)).append("/").append(file).toString()).lastModified())) + " | " + file);
                    				}
                    			}else if(unsigned_path.equals("loa_invoice")){
                    				if(file.startsWith("LOA_")){
                    					files.add(String.valueOf(dt1.format(new File(new StringBuilder(String.valueOf(pathSigned)).append("/").append(file).toString()).lastModified())) + " | " + file);
                    				}
                    			}
                    			else if(unsigned_path.equals("credit_debit_invoice")){
                    				if(file.startsWith("CREDIT_") || file.startsWith("DEBIT_")){
                    					files.add(String.valueOf(dt1.format(new File(new StringBuilder(String.valueOf(pathSigned)).append("/").append(file).toString()).lastModified())) + " | " + file);
                    				}
                    			}else if(unsigned_path.equals("latepay_invoice")){
                    				if(file.startsWith("LATEPAY_")){
                    					files.add(String.valueOf(dt1.format(new File(new StringBuilder(String.valueOf(pathSigned)).append("/").append(file).toString()).lastModified())) + " | " + file);
                    				}
                    			}else if(unsigned_path.equals("service_invoice")){
                    				if(file.startsWith("SERVICE_")){
                    					files.add(String.valueOf(dt1.format(new File(new StringBuilder(String.valueOf(pathSigned)).append("/").append(file).toString()).lastModified())) + " | " + file);
                    				}
                    			}else if(unsigned_path.equals("latepay_invoice")){
                    				if(file.startsWith("LATEPAY_")){
                    					files.add(String.valueOf(dt1.format(new File(new StringBuilder(String.valueOf(pathSigned)).append("/").append(file).toString()).lastModified())) + " | " + file);
                    				}
                    			}
                    		}
                    	}
                        signed_file.add(file);
                    }
                }
                final PrintWriter out = response.getWriter();
                out.println(files);
                //For updating in table as well as infologger////////
                String pdf_type="",pdf_inv_nm="";
                if(unsigned_pdf.size()>0){
                
	                for(int i=0;i<unsigned_pdf.size();i++){
	                	if(signed_file.contains(unsigned_pdf.elementAt(i)+"")){
	                		//System.out.println("in itttttttttttttttttttttttttttttttttt"+unsigned_pdf.elementAt(i));
	                		String file_nm=unsigned_pdf.elementAt(i)+"";
	                		  if(file_nm.contains(".pdf")){
	                         	 String temp[]=file_nm.split(".pdf");
	                         	 pdf_type=(temp[0].toString().substring(temp[0].toString().length()-1,temp[0].toString().length()));
	                         	String temp1[]=temp[0].split("_");
	                         	pdf_inv_nm=temp1[1].toString().substring(0,temp1[1].toString().length()-2);
	                          }
	                		  String  queryString="select pdf_inv_nm,pdf_signed_flag from dlng_inv_pdf_dtl where pdf_inv_nm like '"+pdf_inv_nm+"%' and inv_type='"+pdf_type+"' ";
	                          rset=stmt.executeQuery(queryString);
	                          //System.out.println("queryString--"+queryString);
	                          if(rset.next()){
	                        	  String temp_str=rset.getString(2)==null?"":rset.getString(2);
	                        	  if(temp_str.equals("") || temp_str.equals("N")){
		                          	String query="update dlng_inv_pdf_dtl set pdf_signed_flag='Y',signed_dt=sysdate,signed_by='"+alias_nm_sel+"' where pdf_inv_nm like '"+pdf_inv_nm+"%' and inv_type='"+pdf_type+"' ";
//		                          	System.out.println("query--"+query);
		                          	stmt.executeUpdate(query);
	                        	  }
	                          }
	                	}
	                }
	                
	                dbcon.commit();
                }
            }
            else {
                final PrintWriter out2 = response.getWriter();
                out2.println("'Signed' Directory is Not Available At " + DirectoryPath);
            }
        }
        }
       }
        catch (Exception e) {
            final PrintWriter out3 = response.getWriter();
            out3.println("Error: " + e.getLocalizedMessage());
            e.printStackTrace();
            System.out.println(" In Frm_PDFFiles Exception " + e + "\n query :");
        }
        finally{

            if (rset != null) {
                try {
                    rset.close();
                }
                catch (SQLException e2) {
                    System.out.println("Exception in Frm_admin " + e2);
                }
                rset = null;
            }
            if (stmt != null) {
                try {
                    stmt.close();
                }
                catch (SQLException e2) {
                    System.out.println("Exception in Frm_admin " + e2);
                }
                stmt = null;
            }
            if (dbcon != null) {
                try {
                    dbcon.close();
                }
                catch (SQLException e2) {
                    System.out.println("Exception in Frm_admin " + e2);
                }
                dbcon = null;
            }
        
        }
    }
}
