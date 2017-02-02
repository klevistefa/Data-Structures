/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.lang.*;

/**
 *
 * @author Klevis
 */
public class Cash extends CashAbstract{
        
    public Cash(){
        
    }
    
    public boolean processCommand (String[] currLine){
        
        //while (!breakLoop){
            try {
                String [] input = currLine;
                input[0] = input[0].toUpperCase();
                switch (currentMode){
                    
                    //FREE_MODE options (default mode)
                    case FREE_MODE:
                        switch (input[0].charAt(0)){
                   
                            case 'O': 

                                boolean clientExists = false;
                                switch (Integer.parseInt(input[2])) {
                                    case 1:
                                          for (int i = 0; i < theClients.size() && !clientExists; i++){
                                              if (theClients.get(i).name.equalsIgnoreCase(input[1])){
                                                  clientExists = true;
                                              }
                                              if (clientExists){
                                                PlusAccount plusAcc = new PlusAccount(currentTime);
                                                //check if you need to do this after loggin out
                                                plusAcc.owner = theClients.get(i);
                                                theClients.get(i).allAccounts.add(plusAcc);
                                                theBook.add(plusAcc);
                                                System.out.println("#" + plusAcc.accountNo);
                                              }
                                          }
                                          if (!clientExists){
                                                Client client = new Client (input[1]);
                                                theClients.add(client);
                                                PlusAccount plusAcc = new PlusAccount(currentTime);
                                                //check if you need to do this after loggin out
                                                plusAcc.owner = client;
                                                client.allAccounts.add(plusAcc);
                                                theBook.add(plusAcc);
                                                System.out.println("#" + plusAcc.accountNo);
                                          }
                                          break;
                                          
                                    case 2:
                                        for (int i = 0; i < theClients.size() && !clientExists; i++){
                                              if (theClients.get(i).name.equalsIgnoreCase(input[1])){
                                                  clientExists = true;
                                              }
                                              if (clientExists){
                                                SavingAccount savingAcc = new SavingAccount(currentTime);
                                                //check if you need to do this after loggin out
                                                savingAcc.owner = theClients.get(i);
                                                theClients.get(i).allAccounts.add(savingAcc);
                                                theBook.add(savingAcc);
                                                System.out.println("#" + savingAcc.accountNo);
                                              }
                                          }
                                          if (!clientExists){
                                                Client client = new Client (input[1]);
                                                theClients.add(client);
                                                SavingAccount savingAcc = new SavingAccount(currentTime);
                                                //check if you need to do this after loggin out
                                                savingAcc.owner = client;
                                                client.allAccounts.add(savingAcc);
                                                theBook.add(savingAcc);
                                                System.out.println("#" + savingAcc.accountNo);
                                          }
                                          break;
                                    default:
                                        System.out.println("Please enter an account type format (Open SSS TTT)");
                                        break;
                                }
                                break;
                            
                            case 'S':                                 
                                try {
                                    if (Integer.parseInt(input[1])== password){
                                        System.out.println("Super-User Mode");
                                        currentMode = Mode.SUPERUSER_MODE;
                                    } else {
                                        System.out.println("Wrong password.");
                                    }
                                } catch (Exception e){
                                    System.out.println("ERROR");
                                } break;
                            
                            case 'H':
                                System.out.println("For Help, call 1-800-102-0007");
                                break;
                            
                            case 'L': 
                                try{    
                                    for (int i = 0; i < theBook.size(); i++){
                                        if (Integer.parseInt(input[1]) == theBook.get(i).accountNo){
                                            loggedinAccount = theBook.get(i);
                                            System.out.println("Hello " + loggedinAccount.owner.name + "!");
                                            currentMode = Mode.USER_MODE;
                                            break;    
                                    } 
                                }
                                } catch (Exception err){
                                    System.out.println("ERROR! An account with that number doesn't exist.");
                                }
                                break;
                                
                            default:
                                System.out.println("The command you're entering does not exist.\n"
                                        + "Please write the command in the appropriate format.");
                            break;
                        }//UserMode
                        break;
                    
                    //SUPERUSER_MODE options
                    case SUPERUSER_MODE:
                        switch (input[0].charAt(0)){
                            case 'T': 
                                System.out.println("System shutting down!");
                                return false;
                            
                            case 'C':
                                System.out.println("You have the following clients:");
                                for (int i = 0; i < theClients.size(); i++){
                                    System.out.println(theClients.get(i).clientNo + ". " + theClients.get(i).name);
                                }//for 
                                break;
                            
                            case 'A':
                                
                                System.out.println("Plus Accounts:");
                                for (int i = 0; i < theBook.size(); i++){
                                    if (theBook.get(i).type == 1){
                                        System.out.println("#" + theBook.get(i).accountNo);
                                    }//for
                                }    
                                
                                System.out.println("Savings Account:");
                                for (int i = 0; i < theBook.size(); i++){    
                                    if (theBook.get(i).type == 2){
                                        System.out.println("#" + theBook.get(i).accountNo);
                                    }//for
                                }
                            break;
                            
                            case 'U':
                                System.out.println("ATM switching to free-mode!");
                                currentMode = Mode.FREE_MODE;
                                break;
                            default:
                                System.out.println("This command doesn't exist in this mode. Please try again a new command!");
                                break;
                        }//Super_User Mode
                        break;
                    
                    //User_Mode options
                    case USER_MODE:
                        switch (input[0].charAt(0)){
                            
                            case 'D':
                                updateBalance();
                                loggedinAccount.deposit(Double.parseDouble(input[1]));
                                System.out.println("+" + input[1]);
                                break;
                                
                            case 'W':
                                updateBalance();
                                if (loggedinAccount.withdraw(Double.parseDouble(input[1]))){
                                    loggedinAccount.withdraw(Double.parseDouble(input[1]));
                                    System.out.println("-" + input[1]);
                                } else {
                                    System.out.println("You can't withdraw that ammount." + 
                                            "You can't have negative balance on this type of account.");
                                }
                                break;
                                
                            case 'B':
                                updateBalance();
                                System.out.printf("=%.2f\n", loggedinAccount.balance);
                                break;
                                
                            case 'M':
                                System.out.println("Your Plus Accounts: ");
                                for (int i = 0; i < loggedinAccount.owner.allAccounts.size(); i++){
                                    if (loggedinAccount.owner.allAccounts.get(i).type == 1){
                                        System.out.print("#" + loggedinAccount.owner.allAccounts.get(i).accountNo + " ");
                                    }
                                }
                                System.out.println("Your Savings Accounts: ");
                                for (int i = 0; i < loggedinAccount.owner.allAccounts.size(); i++){
                                    if (loggedinAccount.owner.allAccounts.get(i).type == 2){
                                        System.out.print("#" + loggedinAccount.owner.allAccounts.get(i).accountNo + " ");
                                    }
                                }
                                break;
                            
                            case 'Q':
                                for (int i = 0; i < theBook.size(); i++){
                                    if (theBook.get(i).accountNo == loggedinAccount.accountNo){
                                        theBook.get(i).equals(loggedinAccount);
                                        break;
                                    }
                                }
                                for (int i = 0; i < theClients.size(); i++){
                                    if (loggedinAccount.owner.clientNo == theClients.get(i).clientNo){
                                        for (int j = 0; j < loggedinAccount.owner.allAccounts.size(); j++){
                                            if (loggedinAccount.accountNo == loggedinAccount.owner.allAccounts.get(j).accountNo){
                                                loggedinAccount.owner.allAccounts.get(j).equals(loggedinAccount);
                                                break;
                                            }
                                        }
                                    }
                                }
                                System.out.println("Goodbye " + loggedinAccount.owner.name);
                                loggedinAccount = null;
                                currentMode = Mode.FREE_MODE;
                                break;
                            
                            default: 
                                System.out.println("This command doesn't exist in this mode. Please try a new command.");
                                break;
                        }//User_Mode
                        break;
                }//Mode switch
            } catch (Exception ex) {
                System.out.println("ERROR");
            }
            
            //Updating the Account Arrays for each client
//            for (int i = 0; i < theClients.size(); i++){
//                for (int j = 0; j < theClients.get(i).allAccounts.size(); j++){
//                    if (theClients.get(i).allAccounts.get(j) instanceof PlusAccount){
//                    ((PlusAccount)theClients.get(i).allAccounts.get(j)).updateBal(currentTime);
//                }
//                if (theClients.get(i).allAccounts.get(j) instanceof SavingAccount){
//                    ((SavingAccount)theClients.get(i).allAccounts.get(j)).updateBal(currentTime);
//                }
//                }
//            }
        //}
        
        return true;
    }
    
    void initClients(){
        try{
            File clientsFile = new File ("src/theClients.txt");
            //clientsFile.createNewFile();
            Scanner scanner = new Scanner(clientsFile);
            while (scanner.hasNextLine()) {
                String[] client = scanner.nextLine().split(" ");
                Client temp = new Client (client[0]);
                theClients.add(temp);
            } 
        } catch (IOException e){
            
        }
    }
    
    void initAccounts(){
        try{    
            File bookFile = new File ("src/theBook.txt");
            //bookFile.createNewFile();
            Scanner scanner = new Scanner(bookFile);
            if(scanner.hasNextLine()){
                currentTime = Integer.parseInt(scanner.nextLine());
            }
            while (scanner.hasNextLine()){
                Account temp = parseAccount(scanner.nextLine());
                if (temp != null){
                    temp.owner.allAccounts.add(temp);
                    theBook.add(temp);
                    
                }
            }
            
            scanner.close();
        } catch (IOException e){
        }
    }
    
    void saveData() {
        try{
            PrintWriter clientWriter = new PrintWriter("src/theClients.txt");
            
            for (int i = 0; i < theClients.size(); i++){
                clientWriter.printf("%s", theClients.get(i).name);
                clientWriter.printf("%n");
                
            }
            clientWriter.close();
            
            PrintWriter bookWriter = new PrintWriter("src/theBook.txt");
            bookWriter.println(currentTime);
            
            for (int i = 0; i < theBook.size(); i++){
                bookWriter.printf("%s %s %s %s", theBook.get(i).owner.clientNo, theBook.get(i).accountNo,
                        theBook.get(i).type, theBook.get(i).balance);
                bookWriter.printf("%n");
            }
            bookWriter.close();
        } catch (FileNotFoundException e){
            
        }
        
    }
    
    private static Account parseAccount (String line){
        String [] temp = line.split(" ");
        
        if ("1".equals(temp[2])){
            PlusAccount tempAcc = new PlusAccount(currentTime);
            tempAcc.owner = theClients.get(Integer.parseInt(temp[0]) - 1);
            tempAcc.balance = Double.parseDouble(temp[3]);
            return tempAcc;
        }
        
        if ("2".equals(temp[2])){
            SavingAccount tempAcc = new SavingAccount(currentTime);
            tempAcc.owner = theClients.get(Integer.parseInt(temp[0]) - 1);
            tempAcc.balance = Double.parseDouble(temp[3]);
            return tempAcc;
        }
        
        return null;
    }
    
    public static void updateBalance(){
//        for (int i = 0; i < theBook.size(); i++){
//                if (theBook.get(i) instanceof PlusAccount){
//                    ((PlusAccount)theBook.get(i)).updateBal(currentTime);
//                }
//                if (theBook.get(i) instanceof SavingAccount){
//                    ((SavingAccount) theBook.get(i)).updateBal(currentTime);
//                }
//            }

        if (loggedinAccount instanceof PlusAccount){
            ((PlusAccount)loggedinAccount).updateBal(currentTime);
        } else {
            ((SavingAccount)loggedinAccount).updateBal(currentTime);
        }
    }
}
    
        
        
    
    

