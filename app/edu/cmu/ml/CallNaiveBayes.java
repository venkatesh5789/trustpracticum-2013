package edu.cmu.ml;

import java.io.IOException; 


public class CallNaiveBayes {  

	public static void main(String args[]) throws IOException{
		Runtime r = Runtime.getRuntime();
		Process p = r.exec("python BayesNow.py Training.txt Test.txt Result.txt Feature-NameandValues.txt");
		System.out.println("Done!!!");
	}	
} 
