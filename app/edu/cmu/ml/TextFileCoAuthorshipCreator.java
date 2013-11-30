package edu.cmu.ml;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class TextFileCoAuthorshipCreator {
	private String inputFileName;
	private String outputFileName;

	public TextFileCoAuthorshipCreator(String inputFileName, String outputFileName) throws IOException {
		super();
		this.inputFileName = inputFileName;
		this.outputFileName = outputFileName;
		parseFileAndFillInformation(this.inputFileName, this.outputFileName);
	}
	
	private void parseFileAndFillInformation(String inputFileName, String outputFileName) throws IOException {
		InputStream inputStream = new FileInputStream(inputFileName);
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(outputFileName, true)));
		String line;
		String[] fields, authorNames;
		String primary_key, title, type, authorString, year, lineToBeWritten, inputAuthor;
		
		writer.println("Author Name|Coauthor Name|Year|Title|Type");
		while((line = bufferedReader.readLine()) !=null) {
			fields = line.split("\\|");
			primary_key = fields[0];
			title = fields[1];
			type = fields[2];
			authorString = fields[4];
			year = fields[6];
			inputAuthor = primary_key.split("_")[0];
			authorNames = authorString.split(",");
			
			if(authorString != "") {
				for(String author : authorNames) {
					if(! inputAuthor.equals(author)) {
						lineToBeWritten = inputAuthor + "|"
								+ author + "|"
								+ year + "|"
								+ title + "|"
								+ type;
						writer.println(lineToBeWritten);
					}
				}
			}
		
		}
		
		bufferedReader.close();
		writer.close();
	}

	public static void main(String[] args) throws IOException {
		TextFileCoAuthorshipCreator p = new TextFileCoAuthorshipCreator("ds.txt","ds_coauthors.txt");
		System.out.println("sf");
	}
}
