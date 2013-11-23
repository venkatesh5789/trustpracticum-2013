package edu.cmu.DBLPProcessor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import edu.cmu.dataset.DBLPDataSource;
import edu.cmu.dataset.DatasetInterface;

public class FileParser {
	
	public FileParser() {
		super();
	}

	public void parseFile() throws SAXException, ParserConfigurationException, JAXBException, IOException{
		HashMap<String,DBLPUser> dblp;
		DatasetInterface dblpDataset = new DBLPDataSource();
		dblp = dblpDataset.getDataset("Modified_dblp_example.xml");
		
		File file = new File("/Users/ShuaiWang/Desktop/Makedata.txt");		
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		
		Iterator<Map.Entry<String, DBLPUser>> i = dblp.entrySet().iterator(); 
		while(i.hasNext()){
		    String key = i.next().getKey();
		    System.out.println(key);
		    bw.write(key + ",");
		    List<Publication> pubs = dblp.get(key).getPublication();
		    for(Publication pname: pubs){
		    	for(int j= 0; j< pname.getField().size();j++){
		    		bw.write(pname.getField().get(j) + ",");
		    	}
		    	if(pname.getBooktitle()!=null){	
		    		bw.write(pname.getBooktitle() + ",");
	    		}
		    	if(pname.getJournal()!=null){	
		    		bw.write(pname.getJournal() + ",");
	    		}
		    	if(pname.getTitle()!=null){	
		    		bw.write(pname.getTitle());
	    		}
		    }		    
		    bw.write("\n");
		}		
		bw.close();		
	}
	
	public static void main(String[] args) throws SAXException, ParserConfigurationException, JAXBException, IOException{
		FileParser fp = new FileParser();
		fp.parseFile();
	}
}
