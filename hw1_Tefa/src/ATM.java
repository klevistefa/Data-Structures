import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Klevis
 */
public class ATM {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException{
        
        Scanner input = new Scanner(System.in);
        
        String command;
        String directory = "C:\\Users\\Klevis\\workspace\\hw1_Tefa\\src\\bankAccounts.txt";
        
        ArrayList<Account> accounts = new ArrayList<Account>();
        readFile(directory, accounts);
        
        PrintWriter pw = new PrintWriter(directory);
        
        
        System.out.println("Press one of the following commands:");
        System.out.println("O SSS - open a new account for a customer named SSS \nL NNN - login to account nr. NNN" +
                "\nT - shut down the ATM");
        
        //Loop that keeps the program running unless terminated by the user
        while (true){
            
            command = input.nextLine();
            
            //Command to terminate the program (shut down the ATM)
            if (getCmd(command) == 't'){
                break;
            }
            
            //Command to open a new account
            if (getCmd(command) == 'o'){
                //In case the input it's not in the Open NNN format
                if (getName(command) == null || getName(command) == "  "){
                    System.out.println("ERROR! Please write a name for the account you're trying to open in the format O NNN:");
                } else {
                    Account account = new Account(getName(command));
                
                    accounts.add(account);
                    System.out.println("#" + account.accountNo);
                }
            }
            boolean logout = false;
            
            //Error message if the user tryes to Deposit, Withdraw, or get Balance if not logged in
            if ((getCmd(command) == 'b') || (getCmd(command) == 'w') || (getCmd(command) == 'd')){
                System.out.println("ERROR! Please log in to an account first to execute that command:");
            }
            //Command to log in to a certain account
            if (getCmd(command) == 'L' | getCmd(command) == 'l'){
                
                int accountNumber = getNum(command);
                
                //Checking if the account exists in the arraylist
                if (accountNumber - 1000 >= accounts.size() | accountNumber < 1000){
                    System.out.println("ERROR! The account you are trying to log in doesn't exist.");
                    System.out.println("Please enter a new command:");
                    
                } else { 
                    
                    //Creates a copy of the account that it's logged in to modify according to the user's inputs 
                    Account currentAccount = accounts.get(accountNumber - 1000);
                    System.out.println("Hello " + currentAccount.owner + ".");
                    
                    //Loop which keeps the current logged in account to do certain allowed commands
                    while (!logout){
                        
                        command = input.nextLine();
                        
                    switch (getCmd(command)){
                    
                        case ('d'): currentAccount.Deposit(getNum(command));
                        System.out.println("+" + getNum(command)); break;
                    
                        case ('w'): currentAccount.Withdraw(getNum(command));
                        System.out.println("-" + getNum(command)); break;
                        
                        case ('b'): System.out.println("=" + currentAccount.getBalance()); break;
                        
                        case ('q'): accounts.set(currentAccount.accountNo - 1000, currentAccount);
                        System.out.println("Goodbye " + currentAccount.owner + ".");
                        logout = true; break;
                        
                        case ('l'): accounts.set(accountNumber - 1000, currentAccount);
                        if (getNum(command) - 1000 < accounts.size()){
                            System.out.print("Goodbye " + currentAccount.owner + ".");
                            accounts.set(currentAccount.accountNo - 1000, currentAccount);
                            currentAccount = accounts.get(getNum(command) - 1000);
                            System.out.println(" Hello " + currentAccount.owner + ".");
                        } else {
                            System.out.println("An account with that number doesn't exist.");
                        } break;
                        
                        case('t'): accounts.set(accountNumber - 1000, currentAccount);
                        logout = true; break;                        
                        
                        case ('o'): System.out.println("ERROR! Please log out of this account" + 
                        " first to open a new account: "); break;
                                
                        default: System.out.println("ERROR! This command doesn't exist. Please enter a new command: ");
                                
                    }
                    }
                
                }
                
            }
            if (getCmd(command) == 't'){
                break;
            }
            }
        //Calling the method that writes all the accounts on the arraylist to bankAccounts.txt.
        writeFile(pw, accounts);   
        
        System.out.println("ATM shutting down.");
        input.close();
    }
    
    public static char getCmd(String command){
        
        return command.toLowerCase().charAt(0);
    }

    //Method that returns just the name part from the user's command
    public static String getName(String command){
        int indexOfSpace = command.indexOf(' ');
        try{
            return command.substring(indexOfSpace + 1, command.length());
        } catch (StringIndexOutOfBoundsException e) {
            return null;
        }       
    }
    
    //Method that returns just the number part as integer from the user's command
    public static int getNum(String command) {
        int indexOfSpace = command.indexOf(' ');
        int defaultValue = 99999;
        try {
            return Integer.parseInt(command.substring(indexOfSpace + 1, command.length()));
        } catch (NumberFormatException e){
            return defaultValue;
        } 
    }
    
    //Method that reads all the accounts from bankAccounts.txt and puts them on the arraylist
    public static void readFile (String directory, ArrayList<Account> oldAccounts) throws FileNotFoundException{
        File file = new File(directory);
        Scanner input = new Scanner(file);
        try{
        	while (input.hasNextLine()){
                String line = input.nextLine();
                String[] splittedLine = line.split(" ");
                
                Account account = new Account(splittedLine[0]);
                account.Deposit(Double.parseDouble(splittedLine[2]));
                
                oldAccounts.add(account);
            }
        } catch (NumberFormatException ex){
        	String line = input.next();
        	line = line.substring(0, line.length()-1);
        }
        
        input.close();
       
    }
    
    //Method that writes all the accounts on the ArrayList on bankAccounts.txt
    public static void writeFile(PrintWriter pw, ArrayList<Account> accounts){
        
        for (int i = 0; i < accounts.size(); i++){
                pw.println(accounts.get(i).owner + " #" + accounts.get(i).accountNo + " " + accounts.get(i).balance);
            }
        
        pw.close();
    }
    
}
