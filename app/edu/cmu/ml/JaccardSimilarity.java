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
	private BufferedReader reader;	
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
		System.out.println("Same elements "+ h1);
		
		//Union 
		int union = sizeh1 + h2.size();
		int intersection = h1.size();			
		res = (double)intersection/union;
		return res;
	}
	
	public LinkedList<String> datapreprocess() throws IOException{
		reader = new BufferedReader(new FileReader("/Users/ShuaiWang/Desktop/Makedata.txt"));
		String line = null;
		LinkedList<String> parts = new LinkedList<String>();
		while ((line = reader.readLine()) != null) { 
			 System.out.println(line);
			 parts.add(line);
		}
		//System.out.println(parts.getFirst());
		return parts;
	}

	
	public static void main(String args[]) throws IOException{
		
		JaccardSimilarity ja = new JaccardSimilarity();
		LinkedList<String> p = ja.datapreprocess();
		
		double totalValue =0;
		for (int i=0; i<p.size();i++){
			for(int j=i+1;j<p.size();j++){
				totalValue += jaccardSimilarity(p.get(i),p.get(j));
			}
		}
		System.out.println("Value" + " " + totalValue);
		
		PrintWriter writer = new PrintWriter("/Users/ShuaiWang/Desktop/Output.txt", "UTF-8");
		for (int i=0; i<p.size();i++){
			for(int j=i+1;j<p.size();j++){		
				String[] k1 = p.get(i).split(",");
				String[] k2 = p.get(j).split(",");
				String k11 = null;
				String k22 = null;
				for(int k =1;k<k1.length;k++){
					k11 += k1[k] + ",";
				}
				for(int l =1;l<k2.length;l++){
					k22 += k2[l] + ",";
				}
				writer.println(k1[0]+ "," + k2[0]+ "," +jaccardSimilarity(k11,k22));
			}
		}

		writer.close();		
//		String rad[];
//		
//		System.out.println("Please enter the author pairs");
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		rad = br.readLine().split(",");
//		System.out.println(rad[0]);
//		System.out.println(rad[1]);
//		
//		double help = jaccardSimilarity(rad[0],rad[1]);
//		System.out.println(help);
//		double percentage = (double) Math.ceil((help) * 100); 
//		System.out.println("Link probability:" + percentage + "%");
	}
}