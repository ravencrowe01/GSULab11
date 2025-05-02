import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
* File: Lab11Prob01.java
* Class: CSCI 1302
* Author: Raven Crowe
* Created on: Mar 4, 2025
* Last Modified: Mar 4, 2025
* Description: Copy the contents of a data file.
*/
public class Lab11Prob03 {
	public static void main(String[] args) {
		String inPath = "src/people.dat";
		String outPath = "people-copy.dat";
		String outSortedPath = "people-salary-sorted-objects.dat";
		
		File inFile = new File (inPath);
		File outFile = new File (outPath);
		File outSorted = new File (outSortedPath);
		
		if(!inFile.exists()) {
			System.out.println(inFile.getAbsolutePath());
			System.err.println("Input file doesn't exist, exiting...");
			return;
		}
		
		if(!outFile.exists()) {
			try {
				outFile.createNewFile();
			}
			catch (Exception e) {
				System.err.println("Encountered error while trying to create outputfile:\n" + e.getMessage());
				System.err.println("Exiting...");
				return;
				
			}
		}
		
		if(!outSorted.exists()) {
			try {
				outSorted.createNewFile();
			}
			catch (Exception e) {
				System.err.println("Encountered error while trying to create outputfile:\n" + e.getMessage());
				System.err.println("Exiting...");
				return;
				
			}
		}
		
		try (var inStream = new DataInputStream (new FileInputStream(inFile));
				var outStream = new FileOutputStream (outPath);
				var outSortedStream = new ObjectOutputStream (new FileOutputStream (outSortedPath))) {
			ArrayList<Person> people = new ArrayList<Person> ();
			
			try {
				while (true) {
					int age = inStream.readInt ();
					String name = inStream.readUTF ();
					String address = inStream.readUTF ();
					int zip = inStream.readInt();
					double salary = inStream.readDouble();
					
					people.add(new Person (age, name, address, zip, salary));
				}
			}
			catch (EOFException e) {
				// Don't really care, reached eof.
			}
			
			for (Person person : people) {
				outStream.write(person.toString().getBytes());
				outSortedStream.writeObject (person);
			}
		}
		catch (Exception e) {
			System.err.println("Caught exception:\n" + e);
			System.err.println("Exiting...");
			return;
		}
	}
}
