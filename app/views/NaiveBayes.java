package views;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.Random;


public class NaiveBayes {
	private static BufferedReader reader;
	private static BufferedWriter out;
	public static void fileFormat() throws NumberFormatException, IOException{
		reader = new BufferedReader(new FileReader("/Users/ShuaiWang/Desktop/Output_Copy_2.txt"));
		FileWriter fstream = new FileWriter("/Users/ShuaiWang/Desktop/Test.txt", false);
		out = new BufferedWriter(fstream);
		String line = null;
		while ((line = reader.readLine()) != null) { 
			 String[] fileLine = line.split(",");
			 String re= "";
			 double x = Double.parseDouble(fileLine[2]);
			 
			 boolean val = new Random().nextInt(3)==0;
			 if(x >0.2){
				 re += "Y"+"	";
			 }else{	 
				 re += "N"+"	";
			 }
			 
			 if(re.equals("Y	") && (!val)){
				 re += "H"+"	";
			 }else if(re.equals("Y	") && val){
				 re += "M"+"	";
			 }else{
				 re += "L"+"	";
			 }
			 
			 boolean val2 = new Random().nextInt(10)==0;
			 if(x>=0.5){
				 re += "H" + "	";
			 }else if(x>=0.2 && x<0.5){
				 re += "M" +"	";
			 }else if(x<0.2 && val2){
				 re += "M" +"	";
			 }else{
				 re += "L" +"	";
			 }
			 
			 boolean val3 = new Random().nextInt(4)==0;
			 boolean val4 = new Random().nextInt(10)==0;
			 if(x>=0.5 && (!val3)){
				 re += "H" + "	";
			 }else if(x>0.5 && val3){
				 re += "M" + "	";
			 }
			 else if(x>=0.2 && x<0.5 && (!val4)){
				 re += "M" +"	";
			 }else{
				 re += "L" +"	";
			 }
			 
			 System.out.println(re);
			 out.write(re + "\n");
		}
		out.close();
	}
	public static void main(String args[]) throws NumberFormatException, IOException{
		NaiveBayes nb = new NaiveBayes();
		//nb.fileFormat();
	}
}
