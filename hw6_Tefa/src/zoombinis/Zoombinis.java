/*
 * Zoombinis.java
 * 
 * This implements the abstractZoombinis class.
 * 
 * This problem asks you to generate a sequence of random Zoombini names.
 * Zoombinis is a great logical computer game for children to adults, 
 * very popular in the 1990s, recently revived in apps in 2015.  
 * 
 * Basic requirement for a Zoombini name is that it is "pronounceable"
 * (it is a sequence of syllables of varying length).  Call this mode 0.
 * 
 * But we may want to have different ethnic Zoombini names,
 * representing different modes:
 *       0 Simple, 1 American, 2 Chinese, 3 Indian, 4 Hebrew,
 *       5 Italian, 6 Japanese, 7 Korean,  8 Japanese,
 *       9 Hawaiian, 10 Other
 * all with their particular probabilistic distribution of sounds 
 * and number of syllables.  How do we do this?
 * 
 * We achieve this by introducing several probability distributions: 
 *    the number of syllables in a name
 *    the vowels (including diphthongs)
 *    the consonants (including digraphs or trigraphs of consonants)
 *    the terminal consonants in a name
 *    the initial vowels in a name
 * 
 * What is needed for this problem (homework 3):
 *      Random number (java.util.Random)
 *      String manipulation
 *      File handling (java.util.Scanner)
 *      Command line argument processing
 *      ArrayList (java.util.ArrayList)
 * 
 * Chee Yap
 * Data Structures Class (CS 102)
 * Fall 2016
 */

package zoombinis;

import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

public class Zoombinis
       extends ZoombinisAbstract {

	 // MEMBERS:======================
	 static ArrayList<String> 
	 		 allVowels = new ArrayList<String>(),
			 allConsonants = new ArrayList<String>(),
	 		 tConsonants = new ArrayList<String>();
	 static ArrayList<Integer> 
	              LDT = new ArrayList<Integer>();
	 //Per mode 
	 static int[] terminalProbability
	  = { 5,     8,    8,    6,   6,   2,    3,   7,   3,    6,   5};
	 // Simple, Amer, Chin, Ind, Heb, Ital, Jap, Kor, Span, Haw, Other
	 //Per mode
	 static int[] initialProbability
	  = { 5,     8,    9,    4,   4,   7,    9,   4,   4,    4,   5};
	
	 static String[] ethnicity = {
		"Simple", "American", "Chinese", "Hebrew",
		"Indian", "Italian", "Japanese", "Korean",
		"Spanish", "Hawaiian", "Other"};

	 //CONSTRUCTOR:====================
	 public Zoombinis(int ss, int nn, int mm, String ppath)
	 	throws FileNotFoundException {
	     s=ss; n=nn; m=mm; path=ppath;
	     if (s==0) rg = new Random();
	     else rg = new Random(s);
	     syntaxInit(); // read the files
	 }
	 
	 //METHODS:========================
	 String randomVowel () {
		return allVowels.get(rg.nextInt(allVowels.size()));
	 }
	 String randomConsonant() {
		return allConsonants.get(rg.nextInt(allConsonants.size()));
	 }
	 String randomTerminalConsonant() {
		return tConsonants.get(rg.nextInt(tConsonants.size()));
	 } 
	 String randomSyllable() {
		return randomConsonant() + randomVowel();
	 }
	 String randomInitialSyllable(){
		 if (rg.nextInt(10)>= initialProbability[m])
			 return randomVowel(); // i.e., no initial consonant
		 return randomConsonant()+randomVowel();
	 }
	public  String randomName() {
		String name = "";
		int len = (LDT.get(rg.nextInt(LDT.size()))).intValue();
		name = randomInitialSyllable(); 
		for (int i=1; i< len; i++)
		     name = name + randomSyllable();
		// fix the terminal consonant:
		if (rg.nextInt(10) <= terminalProbability[m])
			 name = name + randomTerminalConsonant();
		// Capitalize the first letter of name:
		name = Character.toString(name.charAt(0)).toUpperCase()
		     	+ name.substring(1);
	     return name;
	}//randomName
	
	// File READERS:========================
	
	// Read a LDT file
	public void readLDT(ArrayList<Integer> LDT) 
	    			throws FileNotFoundException {
		File ff = new File(path + "/syntax-" + m + "/LDT");
		Scanner scan = new Scanner(ff);
		//for (int i=0; i<10; i++) 
	        while (scan.hasNext()) 
			LDT.add(new Integer(scan.nextInt()));
		scan.close();
	}//readLDT
	
	// Adds successive tokens from input file "fn" to ArrayList "alist"
	public void readFile(ArrayList<String> alist, String fn) 
					throws FileNotFoundException {	
	    File ff = new File(path + "/syntax-" + m + "/" + fn);  
	    Scanner scan = new Scanner(ff);
	    while (scan.hasNext()) 
			alist.add(scan.next());
	    scan.close();
	 }//
	
	// INITIALIZE!
	public void syntaxInit()
	 	throws FileNotFoundException {
		    readLDT(LDT);
		//
		    readFile(allVowels, "vowels");
		    readFile(allVowels, "diphthongs");
		//  
		    readFile(allConsonants, "consonants");
		    readFile(allConsonants, "digraphs");
		//
		    readFile(tConsonants, "tconsonants");
	}//syntaxInit
	
	// MAIN METHOD:===========================
	public static void main(String[] args) throws FileNotFoundException  {

	        int ss=0, nn=16, mm=0;
		String ppath=".";

		if (args.length>0) ss=Integer.parseInt(args[0]);
		if (args.length>1) nn=Integer.parseInt(args[1]);
		if (args.length>2) mm=Integer.parseInt(args[2]);
		if (args.length>3) ppath=args[3];
	
	    Zoombinis zz = new Zoombinis(ss, nn, mm, ppath);
	    System.out.printf("ss=%d, nn=%d, mode=%d, path=%s\n",
		    			ss, nn, mm, ppath);
		    zz.readFile(allVowels, "vowels");
		    zz.readFile(allVowels, "diphthongs");
		    //System.out.println("allVowels = "
		    //	    + allVowels.toString());
		    
		    zz.readFile(allConsonants, "consonants");
		    zz.readFile(allConsonants, "digraphs");
		    //System.out.println("allConsonants = "
		    //	    + allConsonants.toString());

		    zz.readFile(tConsonants, "tconsonants");
		    //System.out.println("tconsonants = "
		    //	    + tConsonants.toString());

		    zz.readLDT(LDT);

		System.out.printf("LDT = " + LDT.toString());
		System.out.printf(
		    "\n=========================================\n"
		    + "Name Mode: " + ethnicity[zz.m]
		    + "\n=========================================\n");
	
		for (int i = 0; i< zz.n; i++) 
			System.out.println(zz.randomName());
	
	}//main

}//class
