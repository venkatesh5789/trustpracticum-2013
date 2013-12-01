package edu.cmu.ml;

import java.io.IOException;

import edu.cmu.trustprocessor.TimeScale;
import edu.cmu.trustprocessor.TrustModelWeights;

public class TextFileTrustAndCoauthorshipProcessor {
	private String fileName;
	private TextFilePublicationParser publications;
	private TimeScale timescale;
	
	private double alphaArticle =TrustModelWeights.alphaArticle;
	private double alphaBook = TrustModelWeights.alphaBook;
	private double alphaInCollection = TrustModelWeights.alphaInCollection;
	private double alphaInProceeding = TrustModelWeights.alphaInProceeding;
	private double alphaMasterThesis=TrustModelWeights.alphaMasterThesis;
	private double alphaPhdThesis = TrustModelWeights.alphaPhdThesis;
	private double alphaProceeding = TrustModelWeights.alphaProceeding;
	private double alphaWWW=TrustModelWeights.alphaWWW;

	public TextFileTrustAndCoauthorshipProcessor(String fileName) throws IOException {
		super();
		this.fileName = fileName;
		this.publications = new TextFilePublicationParser(fileName);
		this.timescale = new TimeScale();
	}
	
	/**
	 * Function to be called in order to calculate the coauthor distance before a 
	 * particular year
	 * @param authorName1
	 * @param authorName2
	 * @param year
	 * @return returns 1,2 or 999 if answer is greater than 2
	 */
	public int getCoauthorDistanceBeforeYear (String authorName1, String authorName2, int year) {
		int distance = 0;
		//TODO: Shuai's method to get the coauthors and coauthors of coauthors goes here
		//TODO: need to parse the return of the method in order to find out the 
		return distance;	
	}
	
	/**
	 * Function to be called in order to calculate the trust value for 
	 * an author before a particular year
	 * @param name
	 * @param year
	 * @return
	 */
	public Double calculateTrustBeforeYear(String name, int year) {
		Double publicationTrust = 0.0;
		Double coauthorshipTrust = 0.0;
		
		publicationTrust = calculatePublicationTrust(name, year);
		coauthorshipTrust = calculateCoauthorshipTrust(name, year);
		
		return publicationTrust + coauthorshipTrust;
	}

	private Double calculateCoauthorshipTrust(String authorName, int year) {
		double coauthorshipTrust = 0.0;
		
		int numberOfRecentCoauthorships = getNumberOfCoauthorsInTimeRange(authorName, year - timescale.getRecentYears(), year);
		int numberOfIntermediateCoauthorships = getNumberOfCoauthorsInTimeRange(authorName, year - timescale.getIntermediateYears(), timescale.getRecentYears());
		int numberOfOldCoauthorships = getNumberOfCoauthorsInTimeRange(authorName, 1800 , year - timescale.getIntermediateYears());
		
		coauthorshipTrust = timescale.gettRecent() * numberOfRecentCoauthorships
						  + timescale.gettIntermediate() * numberOfIntermediateCoauthorships
						  + timescale.gettOld() * numberOfOldCoauthorships;
		return coauthorshipTrust;
	}

	private Double calculatePublicationTrust(String authorName, int year) {
		Double publicationTrust = 0.0;
		
		int numOfArticleRecent = getCountPublicationsBetweenYears(authorName, "article", year - timescale.getRecentYears(), year);
		int numOfArticleIntermediate = getCountPublicationsBetweenYears(authorName, "article", year - timescale.getIntermediateYears(), timescale.getRecentYears());
		int numOfArticleOld = getCountPublicationsBetweenYears(authorName, "article", 1800 , year - timescale.getIntermediateYears());
		
		int numOfBooksRecent = getCountPublicationsBetweenYears(authorName, "book", year - timescale.getRecentYears(), year);
		int numOfBooksIntermediate = getCountPublicationsBetweenYears(authorName, "book", year - timescale.getIntermediateYears(), timescale.getRecentYears());
		int numOfBooksOld = getCountPublicationsBetweenYears(authorName, "book", 1800 , year - timescale.getIntermediateYears());
		
		int numOfIncollectionRecent = getCountPublicationsBetweenYears(authorName, "incollections", year - timescale.getRecentYears(), year);
		int numOfIncollectionIntermediate = getCountPublicationsBetweenYears(authorName, "incollections", year - timescale.getIntermediateYears(), timescale.getRecentYears());
		int numOfIncollectionOld = getCountPublicationsBetweenYears(authorName, "incollections", 1800 , year - timescale.getIntermediateYears());
		
		int numOfInProceedingRecent = getCountPublicationsBetweenYears(authorName, "inproceedings", year - timescale.getRecentYears(), year);
		int numOfInProceedingIntermediate = getCountPublicationsBetweenYears(authorName, "inproceedings", year - timescale.getIntermediateYears(), timescale.getRecentYears());
		int numOfInProceedingOld = getCountPublicationsBetweenYears(authorName, "inproceedings", 1800 , year - timescale.getIntermediateYears());
		
		int numOfMasterThesisRecent = getCountPublicationsBetweenYears(authorName, "mastersthesis", year - timescale.getRecentYears(), year);
		int numOfMasterThesisIntermediate = getCountPublicationsBetweenYears(authorName, "mastersthesis", year - timescale.getIntermediateYears(), timescale.getRecentYears());
		int numOfMasterThesisOld = getCountPublicationsBetweenYears(authorName, "mastersthesis", 1800 , year - timescale.getIntermediateYears());
		
		int numOfPhdThesisRecent = getCountPublicationsBetweenYears(authorName, "phdthesis", year - timescale.getRecentYears(), year);
		int numOfPhdThesisIntermediate = getCountPublicationsBetweenYears(authorName, "phdthesis", year - timescale.getIntermediateYears(), timescale.getRecentYears());
		int numOfPhdThesisOld = getCountPublicationsBetweenYears(authorName, "phdthesis", 1800 , year - timescale.getIntermediateYears());
		
		int numOfProceedingRecent = getCountPublicationsBetweenYears(authorName, "proceedings", year - timescale.getRecentYears(), year);
		int numOfProceedingIntermediate = getCountPublicationsBetweenYears(authorName, "proceedings", year - timescale.getIntermediateYears(), timescale.getRecentYears());
		int numOfProceedingOld = getCountPublicationsBetweenYears(authorName, "proceedings", 1800 , year - timescale.getIntermediateYears());
		
		int numOfWWWRecent = getCountPublicationsBetweenYears(authorName, "www", year - timescale.getRecentYears(), year);
		int numOfWWWIntermediate = getCountPublicationsBetweenYears(authorName, "www", year - timescale.getIntermediateYears(), timescale.getRecentYears());
		int numOfWWWOld = getCountPublicationsBetweenYears(authorName, "www", 1800 , year - timescale.getIntermediateYears());
		
		int numberOfCitationsA = getTotalCitationsOfAuthorByType(authorName, "article");
		int numberOfCitationsB = getTotalCitationsOfAuthorByType(authorName, "book");
		int numberOfCitationsIC = getTotalCitationsOfAuthorByType(authorName, "incollections");
		int numberOfCitationsIP = getTotalCitationsOfAuthorByType(authorName, "inproceedings");
		int numberOfCitationsM = getTotalCitationsOfAuthorByType(authorName, "mastersthesis");
		int numberOfCitationsPH = getTotalCitationsOfAuthorByType(authorName, "phdthesis");
		int numberOfCitationsP = getTotalCitationsOfAuthorByType(authorName, "proceedings");
		int numberOfCitationsW = getTotalCitationsOfAuthorByType(authorName, "www");
		
		publicationTrust = alphaArticle * (timescale.gettRecent() * numOfArticleRecent
										 + timescale.gettIntermediate() * numOfArticleIntermediate
										 + timescale.gettOld() * numOfArticleOld
										 + numberOfCitationsA)
						+  alphaBook * (timescale.gettRecent() * numOfBooksRecent
								 		 + timescale.gettIntermediate() * numOfBooksIntermediate
								 		 + timescale.gettOld() * numOfBooksOld
								 		 + numberOfCitationsB)
						+  alphaInCollection * (timescale.gettRecent() * numOfIncollectionRecent
								 		 + timescale.gettIntermediate() * numOfIncollectionIntermediate
								 		 + timescale.gettOld() * numOfIncollectionOld
								 		 + numberOfCitationsIC)
						+  alphaInProceeding * (timescale.gettRecent() * numOfInProceedingRecent
								 		 + timescale.gettIntermediate() * numOfInProceedingIntermediate
								 		 + timescale.gettOld() * numOfInProceedingOld
								 		 + numberOfCitationsIP)
						+  alphaPhdThesis * (timescale.gettRecent() * numOfPhdThesisRecent
								 		 + timescale.gettIntermediate() * numOfPhdThesisIntermediate
								 		 + timescale.gettOld() * numOfPhdThesisOld
								 		 + numberOfCitationsPH)
						+  alphaMasterThesis * (timescale.gettRecent() * numOfMasterThesisRecent
								 		 + timescale.gettIntermediate() * numOfMasterThesisIntermediate
								 		 + timescale.gettOld() * numOfMasterThesisOld
								 		 + numberOfCitationsM)
						+  alphaProceeding * (timescale.gettRecent() * numOfProceedingRecent
								 		 + timescale.gettIntermediate() * numOfProceedingIntermediate
								 		 + timescale.gettOld() * numOfProceedingOld
								 		 + numberOfCitationsP)
						+  alphaWWW * (timescale.gettRecent() * numOfWWWRecent
								 		 + timescale.gettIntermediate() * numOfWWWIntermediate
								 		 + timescale.gettOld() * numOfWWWOld
								 		 + numberOfCitationsW);
		
		return publicationTrust;
	}

	private int getTotalCitationsOfAuthorByType(String authorName, String type) {
		// TODO Shuai's db method goes here.
		return 0;
	}

	private int getCountPublicationsBetweenYears(String authorName, String type, int startingYear, int endingYear) {
		// TODO Shuai's db method goes here.
		return 0;
	}
	
	private int getNumberOfCoauthorsInTimeRange(String authorName, int startingYear, int endingYear) {
		// TODO Shuai's db method goes here.
		return 0;
	}
}
