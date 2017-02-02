/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package hw1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Klevis
 */
public class HiClass {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        String[] greet = {"Hello", "Hi", "Yo", "Ni Hao", "Pershendetje"};
        Random rg = new Random();
        
        String fileName = "hiStudents.txt";
        String directory = "C:\\Users\\Klevis\\workspace\\hw1_Tefa\\src\\students.txt";
        String[] names = readFileOneByOne(directory);
        
        PrintWriter pw = new PrintWriter(fileName);
        
        for(int i = 0; i < names.length; i++){
            pw.println(greet[rg.nextInt(greet.length)] + " " + names[i]);
        }
        
        pw.close();
        
        
        
    }
    
    private static String[] readFileOneByOne(String file) throws FileNotFoundException{
        Scanner input = new Scanner(new File(file));
        
        String[]names = new String[42];
        int i = 0;
        
        while(input.hasNextLine()){
            
            String name = input.nextLine();
            
            names[i] = name.substring(1, name.length() - 1);
            i++;
        }
        input.close();
        return names;
    }
    
}
