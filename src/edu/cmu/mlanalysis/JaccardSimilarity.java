package edu.cmu.mlanalysis;
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
	private static BufferedReader sreader2;
	private static BufferedReader sreader1;
	
	public static double[] jaccardSimilarity(String similar1, String similar2){
		HashSet<String> h1 = new HashSet<String>();
		HashSet<String> h2 = new HashSet<String>();
		double edge = 0;
		double[] res = {0,0};
		String[] S1 = similar1.split(",");
		for(int i =1;i<S1.length;i++ ){
			String s = S1[i];
			if(s != null){
				if((s.matches("\\d+"))){
					edge++;
				}
		    }
			h1.add(s);		
		}
		System.out.println("h1 "+ h1);
		String[] S2 = similar2.split(",");
		for(int i =1;i<S2.length;i++ ){
			String s = S2[i];
			if(s != null){
				if((s.matches("\\d+"))){
					edge++;
				}
		    }
			h2.add(s);		
		}
		System.out.println("h2 "+ h2);		
		int sizeh1 = h1.size();
		//Retains all elements in h3 that are contained in h2 ie intersection
		h1.retainAll(h2);
		//h1 now contains the intersection of h1 and h2
		//System.out.println("Intersection "+ h1);
		
		h2.removeAll(h1);
		//h2 now contains unique elements
		//System.out.println("Unique in h2 "+ h2);
		
		//Union 
		int union = sizeh1 + h2.size();
		int intersection = h1.size();	
			
		res[0] = (double)intersection/union;
		res[1] = edge;
		System.out.println(res[0]);
		System.out.println(res[1]);
		return res;
	}
	
	public static double[] jaccardSimilarityLevel2(String similar1, String similar2){
		HashSet<String> h1 = new HashSet<String>();
		HashSet<String> h2 = new HashSet<String>();
		HashSet<String> h3 = new HashSet<String>();
		HashSet<String> h4 = new HashSet<String>();
		double edge = 0;
		double[] res = {0,0};
		
		String[] sp = similar1.split("\\|");
		String simi1 = sp[0];		
		String[] S1 = simi1.split(",");
		for(int i =1;i<S1.length;i++ ){
			String s = S1[i];
			if(s != null){
				if((s.matches("\\d+"))){
					edge++;
				}
		    }
			h1.add(s);		
		}
		System.out.println("h1 "+ h1);
		
		String simi11 = sp[1];
		String[] S11 = simi11.split(",");
		for(int i =1;i<S11.length;i++ ){
			String s = S11[i];
			if(s != null){
				if((s.matches("\\d+"))){
					edge++;
				}
		    }
			h3.add(s);		
		}
		System.out.println("h3 "+ h3);
				
		String[] sp2 = similar2.split("\\|");
		String simi2 = sp2[0];
		String[] S2 = simi2.split(",");
		for(int i =1;i<S2.length;i++ ){
			String s = S2[i];
			if(s != null){
				if((s.matches("\\d+"))){
					edge++;
				}
		    }
			h2.add(s);		
		}
		System.out.println("h2 "+ h2);	
		
		String simi22 = sp2[1];
		String[] S22 = simi22.split(",");
		for(int i =1;i<S22.length;i++ ){
			String s = S22[i];
			if(s != null){
				if((s.matches("\\d+"))){
					edge++;
				}
		    }
			h4.add(s);		
		}
		System.out.println("h4 "+ h4);
		
		int sizeh1 = h1.size();
		//Retains all elements in h3 that are contained in h2 ie intersection
		h1.retainAll(h2);
		//h1 now contains the intersection of h1 and h2
		//System.out.println("Intersection "+ h1);
		h2.removeAll(h1);
		//h2 now contains unique elements
		//System.out.println("Unique in h2 "+ h2);
		
		//Union 
		int union = sizeh1 + h2.size();
		int intersection = h1.size();	
		
		int sizeh3 = h3.size();
		//Retains all elements in h3 that are contained in h2 ie intersection
		h3.retainAll(h4);
		//h1 now contains the intersection of h1 and h2
		//System.out.println("Intersection "+ h1);
		h4.removeAll(h3);
		//h2 now contains unique elements
		//System.out.println("Unique in h2 "+ h2);
		
		//Union 
		int union2 = sizeh3 + h4.size();
		int intersection2 = h3.size();
			
		res[0] = (double)intersection/union + ((double)intersection2/union2)/2;
		res[1] = edge/2;
		System.out.println(res[0]);
		System.out.println(res[1]);
		return res;
	}
	
	public static double[] inputJaccardSimilarity(String similar1, String similar2){
		HashSet<String> h1 = new HashSet<String>();
		HashSet<String> h2 = new HashSet<String>();
		double edge = 0;
		double[] res = {0,0};
		String[] S1 = similar1.split(",");
		for(int i =0;i<S1.length;i++ ){
			String s = S1[i];
			if(s != null){
				if((s.matches("\\d+"))){
					edge++;
				}
		    }
			h1.add(s);		
		}
		System.out.println("h1 "+ h1);
		String[] S2 = similar2.split(",");
		for(int i =0;i<S2.length;i++ ){
			String s = S2[i];
			if(s != null){
				if((s.matches("\\d+"))){
					edge++;
				}
		    }
			h2.add(s);		
		}
		System.out.println("h2 "+ h2);		
		int sizeh1 = h1.size();
		//Retains all elements in h3 that are contained in h2 ie intersection
		h1.retainAll(h2);
		//h1 now contains the intersection of h1 and h2
		System.out.println("Intersection "+ h1);
		
		h2.removeAll(h1);
		//h2 now contains unique elements
		System.out.println("Unique in h2 "+ h2);
		
		//Union 
		int union = sizeh1 + h2.size();
		int intersection = h1.size();	
			
		res[0] = (double)intersection/union;
		res[1] = edge;
		System.out.println(res[0]);
		System.out.println(res[1]);
		return res;
	}
	public LinkedList<String> datapreprocess() throws IOException{
		reader = new BufferedReader(new FileReader("/Users/ShuaiWang/Desktop/Datal2.txt"));
		String line = null;
		LinkedList<String> parts = new LinkedList<String>();
		while ((line = reader.readLine()) != null) { 
			 //System.out.println(line);
			 parts.add(line);
		}
		//System.out.println(parts.getFirst());
		return parts;
	}
	public LinkedList<String> datapreprocessl1() throws IOException{
		reader = new BufferedReader(new FileReader("/Users/ShuaiWang/Desktop/Datal1.txt"));
		String line = null;
		LinkedList<String> parts = new LinkedList<String>();
		while ((line = reader.readLine()) != null) { 
			 //System.out.println(line);
			 parts.add(line);
		}
		//System.out.println(parts.getFirst());
		return parts;
	}
	
	public static boolean isNumeric(String str)  
	{  
	  try  
	  {  
	    double d = Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}
	
	public static void main(String args[]) throws IOException{
		JaccardSimilarity ja = new JaccardSimilarity();
		LinkedList<String> p = ja.datapreprocess();
		LinkedList<String> p2 = ja.datapreprocessl1();
		double totalValue =0;
		double edges =0;
		for (int i=0; i<p.size();i++){
			for(int j=i+1;j<p.size();j++){
				totalValue += (jaccardSimilarityLevel2(p.get(i),p.get(j))[0]);
				edges += (jaccardSimilarityLevel2(p.get(i),p.get(j))[1]);
			}
		}
		System.out.println("Value" + " " + totalValue);
		System.out.println("Edges" + " " + edges/2 );
		double scale = totalValue/edges;
		
		
		sreader1 = new BufferedReader(new FileReader("/Users/ShuaiWang/Desktop/Datal1.txt"));
		String line;
		String[] ns1 = {};
		
		Hashtable<String, String> lines = new Hashtable<String, String>();
		while ((line = sreader1.readLine()) != null) { 
			ns1 = line.split(",");
			String whole = null;
			whole = (ns1[1] + ",");
			for(int i=2;i<ns1.length-1;i++){
				whole += (ns1[i]+",");
			}
			whole += ns1[ns1.length-1];
			lines.put(ns1[0],whole);
		}
		
		sreader2 = new BufferedReader(new FileReader("/Users/ShuaiWang/Desktop/Datal2.txt"));
		String line2;
		String[] ns2 = {};
		
		Hashtable<String, String> lines2 = new Hashtable<String, String>();
		while ((line2 = sreader2.readLine()) != null) { 
			ns2 = line2.split(",");
			String whole = null;
			whole = (ns2[1] + ",");
			for(int i=2;i<ns2.length-1;i++){
				whole += (ns2[i]+",");
			}
			whole += ns2[ns2.length-1];
			lines2.put(ns2[0],whole);
		}
		
		PrintWriter writer = new PrintWriter("/Users/ShuaiWang/Desktop/Output.txt", "UTF-8");
		for (int i=0; i<p2.size();i++){
			for(int j=i+1;j<p2.size();j++){		
				String[] k1 = p2.get(i).split(",");
				String[] k2 = p2.get(j).split(",");
				writer.println(k1[0]+ "," + k2[0]+ "," +jaccardSimilarity(p2.get(i),p2.get(j))[0]);
			}
		}
		writer.close();
		
		String rad[];
		System.out.println("Enter the author pairs and level");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		rad = br.readLine().split(",");
//		System.out.println(lines.get(rad[0]));
//		System.out.println(lines.get(rad[1]));
//		System.out.println(rad[2]);
		if(rad[2].equals("1")){
			double help = inputJaccardSimilarity((lines.get(rad[0])),(lines.get(rad[1])))[0];
			help = help/(scale*10);
			double percentage = (double) Math.ceil((help) * 100); 
			System.out.println("Link probability:" + percentage + "%");
		}else{
			double help = jaccardSimilarityLevel2((lines2.get(rad[0])),(lines2.get(rad[1])))[0];
			help = help/(scale*10);
			double percentage = (double) Math.ceil((help) * 100); 
			System.out.println("Link probability:" + percentage + "%");
		}
		
	}
}