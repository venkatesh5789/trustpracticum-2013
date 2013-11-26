package edu.cmu.ml;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

/*
 * Jaccard Similarity is a similarity function which is calculated by 
 * first tokenizing the strings into sets and then taking the ratio of
 * (weighted) intersection to their union 
 */

public class JaccardSimilarity {	
	private static BufferedReader reader;
	
	public static double jaccardSimilarity(String similar1, String similar2){
		HashSet<String> h1 = new HashSet<String>();
		HashSet<String> h2 = new HashSet<String>();
		
		double res = 0;		
		String[] S1 = similar1.split(",");
		for(int i =0;i<S1.length;i++ ){
			h1.add(S1[i]);		
		}
		
		String[] S2 = similar2.split(",");
		for(int i =0;i<S2.length;i++ ){
			h2.add(S2[i]);		
		}
	
		int sizeh1 = h1.size();
		//Retains all elements in h3 that are contained in h2 ie intersection
		h1.retainAll(h2);
		//h1 now contains the intersection of h1 and h2		
		h2.removeAll(h1);
		//h2 now contains unique elements
		//System.out.println("Same elements "+ h1);
		
		//Union 
		int union = sizeh1 + h2.size();
		int intersection = h1.size();			
		res = (double)intersection/union;
		return res;
	}
	
	public static Hashtable<String,String> datapreprocess() throws IOException{
		reader = new BufferedReader(new FileReader("/Users/ShuaiWang/Desktop/Makedata.txt"));
		String line = null;
		Hashtable<String, String> ht = new Hashtable<String, String>();
		while ((line = reader.readLine()) != null) { 
			 //System.out.println(line);
			 String[] k1 = line.split("\\|");
			 String k11 = "";
			 for(int i =1;i<k1.length-1;i++){
				 k11 += k1[i] + ",";
			 }
			 k11 += k1[k1.length-1];
			 ht.put(k1[0],k11);
		}
		//System.out.println(ht.size());
		return ht;
	}

	
	public static void main(String args[]) throws IOException{		
		JaccardSimilarity ja = new JaccardSimilarity();
		Hashtable<String,String> p = ja.datapreprocess();
		System.out.println(JaccardSimilarity.jaccardSimilarity(p.get("James Janisse"),p.get("Julio Gomis-Tena")));
	}
}