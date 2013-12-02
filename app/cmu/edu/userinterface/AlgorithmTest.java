package cmu.edu.userinterface;

import java.io.IOException;

public class AlgorithmTest {
	
	public static void main(String args[]) throws IOException{
		Runtime r = Runtime.getRuntime();
		Process p = r.exec("python BayesNow.py TrA.txt Test.txt Re.txt Feature-NameandValues.txt");
		System.out.println("Done!!!");
	}	
}
