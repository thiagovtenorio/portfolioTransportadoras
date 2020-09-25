/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gw.portfoliotransportadoras.upload;


// Import required java libraries
import java.io.*;
import java.nio.file.Paths;
import java.util.*;
import javax.servlet.RequestDispatcher;
 
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
 
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.output.*;
import org.json.JSONObject;
/**
 *
 * @author vicente
 */
@WebServlet("/UploadServlet")
@MultipartConfig
public class UploadServlet extends HttpServlet{
    private boolean isMultipart;
   private String filePath;
   private int maxFileSize = 50 * 1024;
   private int maxMemSize = 4 * 1024;
   private File file ;
   
   public void init( ){
      // Get the file location where it would be stored.
      filePath = getServletContext().getInitParameter("file-upload"); 
   }
   
   public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, java.io.IOException {
       
      String logoId=request.getParameter("logoId");
      
      Part filePart = request.getPart("image");
      String fileName1 = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
      InputStream fileContent = filePart.getInputStream();
      byte[] buffer = new byte[fileContent.available()];
      fileContent.read(buffer);
      
      File targetFile = new File("/home/vicente/Documentos/desenvolvimento/apache-tomcat-8.0.27/webapps/data/"+fileName1);
      OutputStream outStream = new FileOutputStream(targetFile);
      outStream.write(buffer);
      
      
      System.err.println("fileName "+fileName1);
      System.err.println("logoId "+logoId);
      
      }
      
      public void doGet(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, java.io.IOException {

         throw new ServletException("GET method used with " +
            getClass( ).getName( )+": POST method required.");
      }
   }

