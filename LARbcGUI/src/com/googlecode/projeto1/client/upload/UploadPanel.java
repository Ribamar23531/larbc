package com.googlecode.projeto1.client.upload;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.FileUpload;
import com.gwtext.client.core.Connection;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.Form;
import com.gwtext.client.widgets.form.FormPanel;
import com.gwtext.client.widgets.form.TextField;
import com.gwtext.client.widgets.form.event.FormListener;

/**
 * @author Alcione Pinheiro
 * @author Diego Rodrigues
 * @author João Felipe
 * @version LARbc 1.0
 */
public class UploadPanel extends Panel {

	private FormPanel form;
	private List<String> validExtensions;
	private FileUpload upload;

	public UploadPanel(long idCaso) {
		this.form = new FormPanel();
		this.form.setFileUpload(true);
		this.form.add(this.getFileTextFieldCaso(idCaso));
		this.upload = this.getUploadFile();
		this.form.add(this.upload);
		this.form.add(this.getOkButton());
		this.add(this.form);
		this.validExtensions = this.getExtensionsList();
		this.form.addFormListener(this.getFormListener());
		
	}

	private TextField getFileTextFieldCaso(long idCaso) {
		TextField caso = new TextField();
		caso.setVisible(false);
		caso.setName("caso");
		caso.setValue(new Long(idCaso).toString());
		return caso;
	}
	
	private FileUpload getUploadFile() {
		FileUpload uploader = new FileUpload();
		uploader.setName("upload");
		return uploader;
	}

	private Button getOkButton() {
		Button okButton = new Button("Ok");
		okButton.setType("submit");
		okButton.addListener(new ButtonListenerAdapter() {
			public void onClick(Button button, EventObject e) {
				String address = GWT.getModuleBaseURL() + "upload";
				try {
					validateImage(upload.getFilename());
					form.getForm().submit(address, null, Connection.POST, null, false);
				} catch (Exception exc) {
					System.out.println("Não é imagem");
				}
			}
		});
		return okButton;
	}
	
	private FormListener getFormListener(){
		FormListener formHandler = new FormListener(){
			public boolean doBeforeAction(Form form) {
				return true;
			}

			public void onActionComplete(Form form, int httpStatus,	String responseText) {
				System.out.println("Success");
			}
			public void onActionFailed(Form form, int httpStatus, String responseText) {
				System.out.println("Failure");
			}
			
		};
		return formHandler;
	}
	
	private void validateImage(String path) throws Exception{
		String[] splitted = path.split("\\.");
		String extension = splitted[splitted.length - 1];
		if(!this.isValidExtension(extension)){
			throw new Exception("Este arquivo não é uma imagem e não pode ser inserido");
		}
	}
	
	private boolean isValidExtension(String extension) {
		for (String validExtension : this.validExtensions) {
			if(extension.equalsIgnoreCase(validExtension)){
				return true;
			}
		}
		return false;
	}

	private List<String> getExtensionsList(){
		List<String> result = new ArrayList<String>();
		result.add("jpg");
		result.add("jpeg");
		result.add("bmp");
		result.add("gif");
		result.add("png");
		return result;
	}
}
