package edu.cmu.DBLPProcessor;

import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import edu.cmu.dataset.DBLPDataSource;
import edu.cmu.dataset.DatasetInterface;

public class FileParser {
	
	public void parseFile() throws SAXException, ParserConfigurationException{
		HashMap<String,DBLPUser> dblp;
		DatasetInterface dblpDataset = new DBLPDataSource();
		dblp = dblpDataset.getDataset("Modified_dblp_example.xml");
	}
	
	public static void main(String[] args) throws SAXException, ParserConfigurationException{
		FileParser fp = new FileParser();
		fp.parseFile();
	}
}
