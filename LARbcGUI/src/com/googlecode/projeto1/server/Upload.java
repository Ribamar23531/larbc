package com.googlecode.projeto1.server;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class for Servlet: Upload
 *
 */
 public class Upload extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   private final String PATH_SEPARATOR = File.separator;
   private final String FILES_DIRECTORY = "filesUploaded";
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Upload() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			DiskFileItemFactory itemFactory = new DiskFileItemFactory();
			ServletFileUpload servletFileUpload = new ServletFileUpload(itemFactory);
			List<FileItem> items = servletFileUpload.parseRequest(request);
			String idCaso = "";
			FileItem fileToPersist = null;
			String fileExtension = "";
			for (FileItem fileItem : items) {
				if(fileItem.isFormField()){
					idCaso = fileItem.getString();
				}else{
					String[] splitted = fileItem.getName().split("\\."); 
					fileExtension = splitted[splitted.length - 1];
					fileToPersist = fileItem;
				}
			}
			String directory = FILES_DIRECTORY + PATH_SEPARATOR + idCaso;
			File directoryFile = new File(directory);
			directoryFile.mkdirs();
			String fileName = new Integer(numberOfFiles(directory)).toString() + "." + fileExtension;
			File fileToSave = new File(directory + PATH_SEPARATOR + fileName);
			fileToPersist.write(fileToSave);
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private int numberOfFiles(String directoryPath) {
		File toTest = new File(directoryPath);
		if (!toTest.exists()) {
			return 0;
		}
		if (toTest.isDirectory()) {
			return toTest.list().length;
		}
		return 0;
	}
}