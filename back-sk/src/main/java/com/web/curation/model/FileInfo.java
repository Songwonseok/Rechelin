package com.web.curation.model;

import lombok.Data;

@Data
public class FileInfo {

     private String fileName;
     private long fileSize ;
     private String dir ="C:\\Users\\multicampus\\git\\s02p12a404\\back-sk\\src\\main\\java\\com\\web\\curation\\images";
     
	public String getFileName() {
		return fileName;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public String getDir() {
		return dir;
	}
	
	
     
     
}


