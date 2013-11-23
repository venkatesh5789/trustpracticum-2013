package edu.cmu.ml;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;


public class NaiveBayes {
	double pp = 0;
	double tvalue1 = 0;
	double tvalue2 = 0;
	double edgeweight = 0;
	private static BufferedReader sreader1;
	private static BufferedReader sreader2;
	
	public static void main(String args[]) throws IOException{
		NaiveBayes nb = new NaiveBayes();
		nb.tvalue1 = 1;
		nb.tvalue2 = 2;
		String rad[];
		System.out.println("Enter the author pairs");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		rad = br.readLine().split(",");
		String combination1 = rad[0]+ "," +rad[1];
		String combination2 = rad[1]+ "," +rad[0];
		
		sreader1 = new BufferedReader(new FileReader("/Users/ShuaiWang/Desktop/Weight.txt"));
		String line;
		String[] ns1 = {};
		
		Hashtable<String, String> lines = new Hashtable<String, String>();
		while ((line = sreader1.readLine()) != null) { 
			ns1 = line.split(",");
			String whole = null;
			whole = (ns1[0] + "," + ns1[1]);
			lines.put(whole,ns1[2]);
//			System.out.println(whole);
//			System.out.println(ns1[2]);
		}
		
		sreader2 = new BufferedReader(new FileReader("/Users/ShuaiWang/Desktop/Output.txt"));
		String line2;
		String[] ns2 = {};
		
		Hashtable<String, String> lines2 = new Hashtable<String, String>();
		while ((line2 = sreader2.readLine()) != null) { 
			ns2 = line2.split(",");
			String whole = null;
			whole = (ns2[0] + "," + ns2[1]);
			lines2.put(whole,ns2[2]);
//			System.out.println(whole);
//			System.out.println(ns1[2]);
		}
		
		// Get edge weight
		if(!(lines.get(combination1)==null||lines.get(combination2)==null)){
			nb.edgeweight = 0.2;
		}
		if(!(lines.get(combination1)==null)){
			nb.edgeweight = Double.parseDouble(lines.get(combination1));
		}
		if(!(lines.get(combination2)==null)){
			nb.edgeweight = Double.parseDouble(lines.get(combination2));
		}
		
		// Get pp value
		if(!(lines2.get(combination1)==null)){
			nb.pp = Double.parseDouble(lines2.get(combination1));
		}
		if(!(lines2.get(combination2)==null)){
			nb.pp = Double.parseDouble(lines2.get(combination2));
		}
		
		System.out.println(nb.edgeweight);
		System.out.println(nb.pp);
		// Calculate using NB
		double nbvalue = nb.edgeweight * nb.pp * ((nb.tvalue1+nb.tvalue2)/2);
		double percentage = (double) Math.ceil((nbvalue) * 100);
		System.out.println("Helpness value is :" + percentage + "%");
	}
}
