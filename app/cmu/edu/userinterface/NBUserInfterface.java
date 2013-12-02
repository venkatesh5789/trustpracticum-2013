package cmu.edu.userinterface;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import edu.cmu.ml.NaiveBayesFileCreator;

public class NBUserInfterface {
	public static void main(String args[]) throws IOException, ClassNotFoundException, SQLException{
		NaiveBayesFileCreator blah = new NaiveBayesFileCreator("ds.txt");
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("TwoAuthor.txt", true)));
		blah.writeLineForDemo(writer, "James Blythe", "Joseph C", 2013);
		Runtime r = Runtime.getRuntime();
		Process p = r.exec("python TestNBayes.py TrA.txt TwoAuthor.txt Result.txt Feature-NameandValues.txt");
		System.out.println("Done!!!");
	}
}
