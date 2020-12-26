package com.generic.project.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

public class ReadFile {

	static Logger logger = Logger.getLogger(ReadFile.class);
	CSVReader csvReader;
	FileReader filereader;
	String file;
	
	public static void main(String args[]) throws FileNotFoundException {
		ReadFile r = new ReadFile();
		r.readCsvLineByLine("home_page");
	}

	public Object[] readCsvLineByLine(String fName) throws FileNotFoundException {
		file = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator
				+ "resources" + File.separator + "TestData" + File.separator + fName + ".csv";
		List<String[]> allData = null;
		Object[] dataSet =null;
		try {
			filereader = new FileReader(file);
			csvReader = new CSVReaderBuilder(filereader).withSkipLines(1).build();
			allData = csvReader.readAll();
			dataSet = allData.toArray();
		 } catch (IOException e) {
			e.printStackTrace();
		}
		return dataSet;
	}
}
