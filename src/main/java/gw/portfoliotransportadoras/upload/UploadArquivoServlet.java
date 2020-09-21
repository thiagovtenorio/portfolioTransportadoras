package gw.portfoliotransportadoras.upload;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
 
public class UploadArquivoServlet extends HttpServlet {
	
	private File diretorio;
 
	@Override
	public void init(ServletConfig config) throws ServletException {
                super.init(config);
		String path = config.getInitParameter("diretorio");
		diretorio = new File(path);
		diretorio.mkdirs();
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (!isMultipart) {
			out.println("<h1>Voce não enviou um arquivo!</h1>");
			return;
		}
		
		for (String elemento : request.getParameterMap().keySet()) {
			System.out.println(elemento);
		}
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(diretorio);
		
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		try {
			List<FileItem> items = upload.parseRequest(request);
			for (FileItem item : items ) {
			    if (!item.isFormField()) {
			        processUploadedFile(item);
			    } else {
			    	//para inputs que nao sao 'file', isFormField() é verdadeiro 
			    	String nomeDoCampo = item.getFieldName();
				String valorDoCampo = item.getString();
				System.out.println(nomeDoCampo + ": " + valorDoCampo);
			    }
			}
			
			out.println("<h1>Arquivo gravado!</h1>");
			
		} catch (Exception e) {
			out.println("<h1>Erro ao escrever no arquivo!</h1>");
			return;
		}
		
		
	}
 
	private void processUploadedFile(FileItem item) throws Exception {
		String fileName = item.getName();
		File uploadedFile = new File(diretorio, fileName);
	    item.write(uploadedFile);
	}
	
}