import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
* File: Lab11Prob01.java
* Class: CSCI 1302
* Author: Raven Crowe
* Created on: Mar 4, 2025
* Last Modified: Mar 4, 2025
* Description: Print the contents of a data file and copy it
*/
public class Lab11Prob01 {
	public static void main(String[] args) {
		String inPath = "src/people.dat";
		String outPath = "people-copy.dat";
		
		File inFile = new File (inPath);
		File outFile = new File (outPath);
		
		if(!inFile.exists()) {
			System.out.println(inFile.getAbsolutePath());
			System.err.println("Input file doesn't exist, exiting...");
			return;
		}
		
		if(!outFile.exists()) {
			try {
				inFile.createNewFile();
			}
			catch (Exception e) {
				System.err.println("Encountered error while trying to create outputfile:\n" + e.getMessage());
				System.err.println("Exiting...");
				return;
				
			}
		}
		
		try (var inStream = new DataInputStream (new FileInputStream(inFile));
				var outStream = new FileOutputStream (outPath)) {
			
			try {
				while (true) {
					int age = inStream.readInt ();
					String name = inStream.readUTF ();
					String address = inStream.readUTF ();
					int zip = inStream.readInt();
					double salary = inStream.readDouble();
					
					System.out.printf("%s %s %s %s %.2f%n", age, name, address, zip, salary);
				}
			}
			catch (EOFException e) {
				outStream.write(inStream.readAllBytes());
			}
		}
		catch (Exception e) {
			System.err.println("Caught exception:\n" + e);
			System.err.println("Exiting...");
			return;
		}
	}
}
