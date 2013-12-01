package edu.cmu.ml;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class NaiveBayesFileCreator {
	private TextFileTrustAndCoauthorshipProcessor trustModel;
	private String inputFileName;
	private TextFilePublicationParser fileParser;
	private String outputFileName;
	
	public NaiveBayesFileCreator(String inputFileName, String outputFileName) throws IOException, ClassNotFoundException, SQLException {
		super();
		this.inputFileName = inputFileName;
		this.outputFileName = outputFileName;
		this.trustModel = new TextFileTrustAndCoauthorshipProcessor(inputFileName);
		this.fileParser = trustModel.getPublications();
		createNaiveBayesFile(this.outputFileName);
	}
	
	private void createNaiveBayesFile(String outputFileName) throws IOException, SQLException {
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(outputFileName, true)));
		String authors;
		String[] authorNames;
		int size = 0, year;
		
		for(HashMap<String, String> publication : TextFilePublicationParser.articleList) {
			authors = publication.get("authors");
			authorNames = authors.split(",");
			size = authorNames.length;
			year = Integer.parseInt(publication.get("year"));
			
			for(int i = 0; i<size; i++)
				for(int j = i+1; j<size; j++) {
					if(! authorNames[i].equalsIgnoreCase(authorNames[j]))
						writeLineForAuthors(writer, authorNames[i], authorNames[j], year);
				}			
		}
	}
	
	private void writeLineForAuthors(PrintWriter writer, String authorName1, String authorName2, int year) throws SQLException {
		String lineToBeWritten="Y\t";
		
		int coauthorDistance = trustModel.getCoauthorDistanceBeforeYear(authorName1, authorName2, year);
		Double trustValueDifference = Math.abs(trustModel.calculateTrustBeforeYear(authorName1, year) - trustModel.calculateTrustBeforeYear(authorName2, year));
		Double jaccardSimilarity = JaccardSimilarity.calculateSimilarity(authorName1, authorName2, year);
		
		int coauthorshipHistory1 = trustModel.getNumberOfCoauthorsInTimeRange(authorName1, 1800, year);
		int coauthorshipHistory2 = trustModel.getNumberOfCoauthorsInTimeRange(authorName2, 1800, year);
		
		int effectiveCoauthorship = ((coauthorshipHistory1 - coauthorshipHistory2) < 0)?coauthorshipHistory1 : coauthorshipHistory2;
		
		if(jaccardSimilarity < 0.05)
			lineToBeWritten += "L\t";
		else if(jaccardSimilarity < 0.1)
			lineToBeWritten += "M\t";
		else 
			lineToBeWritten += "H\t";
			
		if(coauthorDistance==1)
			lineToBeWritten+="H\t";
		else if(coauthorDistance == 2)
			lineToBeWritten+="M\t";
		else 
			lineToBeWritten += "L\t";
		
		if(trustValueDifference <=1.0)
			lineToBeWritten+="H\t";
		else if(trustValueDifference <=3.0)
			lineToBeWritten += "M\t";
		else
			lineToBeWritten += "L\t";
		
		if(effectiveCoauthorship > 3)
			lineToBeWritten+="H";
		else if(effectiveCoauthorship > 1)
			lineToBeWritten+="M";
		else 
			lineToBeWritten += "L";
		
		writer.println(lineToBeWritten);
	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
		NaiveBayesFileCreator blah = new NaiveBayesFileCreator("ds.txt", "blah.txt");
	}
}
