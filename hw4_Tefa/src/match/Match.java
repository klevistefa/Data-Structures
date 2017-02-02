/* Match.java
 *
 *    String[]	AA = {"pit","spot", "spate", "slaptwo", "respite"},
 *    		BB = {"pt","Pot", "peat", "part"};
 *
 *	automatic testing of patterns for 
 *		-- matching every string in array AA,
 *		-- matching no string in array BB.
 *
 *	This was hw3.  We give 3 possible solutions below:
 *
 *    	  pat0 = "pit|spot|spate|slaptwo|respite"
 *    	  pat1 = "[^pP].*|.*[^t]|pit"
 *    	  pat2 = "pit|s.*|^r.*"
 *
 *	Also, some non-solutions:
 *    	  pat3 = "pit|spot|spate|slaptwo|respite|pt"
 *    	  pat4 = "pit|spot|spate|slaptwo"
 *
 *	In command line, you can
 * Prof Chee Yap
 * Data Structures (CS102)
 * Fall 2016
 */
package match;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class Match{
  public static void main(String[] args){
      // Please keep these default arguments:
      int nn = (args.length>0) ? Integer.parseInt(args[0]) : 0; 
      String pat = (args.length>1) ? args[1] : "pit|s.*|^r.*" ;
      String afile = (args.length>2) ? args[2] : "src/match/afile.txt";
      String bfile = (args.length>3) ? args[3] : "src/match/bfile.txt";
      
      Scanner input = new Scanner(System.in);
      
      ArrayList<String> patList = new ArrayList<>();
      
      if (nn == 0){
          System.out.println("Please enter a pattern: ");
          pat = input.nextLine();
          while(pat.charAt(0) != '"' && pat.charAt(pat.length()-1) != '"'){
              System.out.println("Please write a new pattern starting and finishing with the character' \" ' :");
              pat = input.nextLine();
          }
          patList.add(pat);
      } else if (nn == 1){
          readFile(patList, "src/match/pfile.txt");
      }
      
      // You should create your A-list by reading from the afile.
      ArrayList<String> aList = new ArrayList<>();
      readFile(aList, afile);
      // You should create your B-list by reading from the bfile.
      ArrayList<String> bList = new ArrayList<>();
      readFile(bList, bfile);
      
      for (int j = 0; j < patList.size(); j++){    
          boolean aListMatch = true;
          for (int i = 0; i < aList.size(); i++){
              if (!aList.get(i).matches(pat)){
                  aListMatch = false;
              }
          }

          boolean bListMatch = true;
          for (int i = 0; i < bList.size(); i++){
              if (bList.get(i).matches(pat)){
                  bListMatch = false;
              }
          }
          
          System.out.println("For the pattern: " + patList.get(j));
          if (aListMatch == true && bListMatch == true){
              System.out.println("NO ERROR in both lists!");
          } else if (aListMatch == true && bListMatch == false){
              System.out.println("ERROR in B list only!");
          } else if (aListMatch == false && bListMatch == true){
              System.out.println("ERROR in A list only!");
          } else {
              System.out.println("ERROR in both lists!");
          }
      }

  }//main
  
  //Method that reads a file with strings and 
  public static void readFile (ArrayList<String> list, String filePath){
      File txtFile = new File(filePath);
      String line;
      
      try {
          Scanner scanner = new Scanner(txtFile);
          while (scanner.hasNextLine()){
              line = scanner.nextLine();
              if (!(line.equals("") || line.charAt(0) == '#')){
                  list.add(line);
              }
          }
      } catch (IOException e){
          e.printStackTrace();
          System.out.println("File not found.");
      }
  }

}//class
