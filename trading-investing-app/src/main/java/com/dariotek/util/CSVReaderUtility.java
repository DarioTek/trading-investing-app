package com.dariotek.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.opencsv.CSVReader;

@Component
public class CSVReaderUtility {

	private String filePath;
	private String fileName;
	private String absoluteFileName;
	private List<String> csvLineItems;

	public CSVReaderUtility() {
	}	
	
	public void setCsvLineItems() {
		this.absoluteFileName = this.filePath + "\\" + this.fileName;
		
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		List<String> csvLineItems = new ArrayList<String>();
		
		try {

			br = new BufferedReader(new FileReader(this.absoluteFileName));
			while ((line = br.readLine()) != null) {
				csvLineItems.add(line);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		this.csvLineItems = csvLineItems;

	}


	public List<String> getCsvLineItems() {
		return csvLineItems;
	}


	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
