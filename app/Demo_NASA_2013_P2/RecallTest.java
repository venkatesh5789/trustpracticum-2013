package Demo_NASA_2013_P2;

import java.io.IOException;

public class RecallTest {
	//This is the recall test of algorithm
	public static void main(String args[]) throws IOException{
		Runtime r = Runtime.getRuntime();
		Process p = r.exec("python BayesNow.py Train.txt Recall_test.txt Recal_result.txt Feature.txt");
		System.out.println("Recall test is done, plese refer to Recall_result.txt to see result.");
	}	
}
