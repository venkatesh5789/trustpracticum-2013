package edu.cmu.ml;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;
import java.util.Map.Entry;

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
		String[] S1 = similar1.split(";");
		for(int i =0;i<S1.length;i++ ){
			h1.add(S1[i]);		
		}
		
		String[] S2 = similar2.split(";");
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
		reader = new BufferedReader(new FileReader("/Users/ShuaiWang/Desktop/Cloud.txt"));
		String line = null;
		Hashtable<String, String> ht = new Hashtable<String, String>();
		while ((line = reader.readLine()) != null) { 
			 //System.out.println(line);
			 String[] k1 = line.split("\\|");
			 String[] primary = k1[0].split("_");
			 String k11 = ""; 
			 k11 += k1[1] + ";" + k1[10] + ";" +k1[23];
			 if (!(ht.containsKey(primary[0]))){
				 ht.put(primary[0],k11);
			 }else{
				 String a = new StringBuilder().append(ht.get(primary[0])).append(";").append(k11).toString();	
				 ht.put(primary[0],a);
				 System.out.println(a + "\n");
			 }
			 
		}
		//System.out.println(ht.size());
		return ht;
	}

	
	public static void main(String args[]) throws IOException{		
//		JaccardSimilarity ja = new JaccardSimilarity();
//		Hashtable<String,String> p = ja.datapreprocess();
//		PrintWriter writer = new PrintWriter("the-file-name.txt", "UTF-8");
//		String com = p.get("Gordon S. Blair");
//		for (Entry<String, String> entry : p.entrySet()) {
//			String key = entry.getKey();
//		    String value = entry.getValue();
//		    //System.out.println(key + value + "\n");
//		    Double x  = ja.jaccardSimilarity(com, value);
//		    System.out.println(x);
//		}
//		System.out.println("Done!");
	}
}