/* ***************************************************
 * CashAbstract class
 *
 *	Although its name has the word "abstract", this is just an
 *	ordinary class.  But like an abstract class, we have left
 *	some methods unimplemented.   Your Cash class must
 *	extend this CashAbstract class.
 *		We really do not want you to modify CashAbstract.
 *	But if you must, please ask for our permission and report any
 *	changes in the comments in the beginning of your Cash.java file.
 *
 *	OVERALL PROJECT DESCRIPTION:
 *
 *	This is an elaboration of the ATM class in hw1.
 *	"Cash" is the name of this ATM shell!  Like all shell programs,
 *		it sits in an infinite loop (until terminated)
 *		waiting to process commands.
 *
 *	The basic goal is to create a robust shell that does not
 *		crash on wrong user input.  Users can easily issue
 *		wrong commands for various reasons, but we should
 *		not only recover from such errors, but give helpful
 *		error messages.  To help this process, we insist that
 *		you process the user inputs on a line-by-line basis.
 *		Each line contains at most one command
 *		and any needed arguments for that command.
 *		(The alternative is to process
 *		the commands in a token-by-token basis.)
 *
 *	We have 2 kinds of bank accounts:
 *			Type 1: Checking Plus Account (no interest!)
 *			Type 2: Saving Account (interest bearing!)
 *		Both are subclasses of the provided Account class.
 *		Checking Plus Accounts can have negative balance,
 *			but you will pay a STEEP interest
 *			(and you get no interest for positive balance).
 *			A win-win situation for the bank.
 *		Saving Account cannot have negative balance, and you get a
 *			MODEST interest payment on your positive balance.
 *
 *	There are two global data structures:
 *		-- theClients is an ArrayList of Clients
 *		-- theBook is an ArrayList of Accounts (all types)
 *	and a global variable:
 *		-- currentTime is an int that is incremented with each
 *			command (ERROR or not).
 *
 *	This time is used in calculating interest on accounts.
 *		Each account type should have an
 *			updateBal(currentTime)
 *		method that calculates the interest amount to be
 *		added or subtracted from the account.  The
 *		value of currentTime is then stored in an account variable
 *		called "lastUpdateTime".
 *
 *	When Cash is terminated, you must also write out the information
 *		of these these global variables into two files in src:
 *			-- src/theClients.txt
 *			-- src/theBook.txt.
 *		Also, currentTime should be stored in the src/theBook.txt.
 *		Of course, on start-up, Cash will first try to read
 *		these two files (initially both files do not exist).
 *		
 *	The original list of 7 commands are as follows:
 *		Open, Login,
 *		Deposit, Balance, Withdraw, Quit,
 *		Terminate
 *
 *	We must modify the Open command to take an extra argument:
 *
 *		Open SSS TTT:	this opens an account of type TTT
 *				for client SSS.  If SSS does
 *				have an entry in theClients, you must
 *				insert an entry for SSS.  Moreover,
 *				the new account is inserted into the
 *				list of accounts of SSS.
 *
 *			E.g., Open Chee 2	-- to open a savings account
 *			E.g., Open Fred 1	-- to open a plus account
 *		
 *	We introduce the concept of Cash modes.
 *	Cash is always in one of three "modes" called respectively,
 *
 *		free-mode, user-mode, superuser-mode.
 *
 *	We introduce 6 new commands:
 *		SuperUser	-- enters the superuser-mode
 *					THIS REQUIRES A SECRET PASSWORD.
 *					(OK, it is always 314159)
 *		User 		-- enters the user-mode
 *		ClientList	-- list all the current clients
 *					(they are just identified by name)
 *		AccountList	-- list all the checking plus accounts,
 *					then list all savings accounts
 *		MyAccounts	-- to list all of my accounts (a client
 *					can have two or more accounts)
 *		Help		-- print a single line that says
 *					"For Help, call 1-800-102-0007"
 *		
 *	We place these 7+6=13 commands into three groups:
 *		Group (I):	Open, Login, SuperUser, Help
 *		Group (II):	Deposit, Balance, Withdraw, Quit, MyAccount
 *		Group (III):	Terminate, Clients, AllAccounts, User
 *
 *		Group (I) commands are only available in free-mode.
 *		Similarly, Group (II) and Group (III)
 *			commands are only availabe in user-mode and
 *			superuser-mode, respectively.
 *		
 *	Notice that the mode-changing commands are:
 *		SuperUser:	free-mode to superuser-mode
 *		User:		superuser-mode to free-mode
 *		Login:		free-mode to user-mode
 *		Quit:		user-mode to free-mode
 *
 *	The Cash shell begins in the free-mode.
 *		It should initialize the two data structures
 *		theClients and theBook from two files
 *		found in src/theClients.txt and src/theBook.txt.
 *		These two files need not exist at the beginning.
 *
 *	For this homework, we expect you to give more helpful 
 *		ERROR messages.  Each error message is a single line,
 *		which begins with "ERROR!" and is followed by
 *		a short but precise characterization of the error.
 *
 *	OTHER REQUIREMENTS:
 *	1.	We can deposit or withdraw either integer or
 *		double values.  But please do use more than 2 decimal
 *		places.  When you add interest, be sure to truncate
 *		the amounts to 2 decimal places first.
 *		(HINT: use the printf statement with format %.2f)
 *	2.	Any valid command line should still be considered valid if
 *		it is followed by extra tokens in that line.  Just ignore the
 *		extra stuff.  THIS IS USEFUL AS A COMMENT in INPUT FILES!
 *		See our sample Input.txt file.
 *		Moreover, an empty line is not considered an ERROR.
 *		Again, you just ignore an empty line.
 *	3.	On starting up Cash, you will see in the code below,
 *		we will read an optional integer (0,1 or 2):
 *		  0: ignore both theBook.txt and theClients.txt files
 *		  1: read theClient.txt, but not theBook.txt file
 *		  2: read both theBook.txt and theClients.txt files
 *		The default value is 1.
 *
 *  Prof. Chee Yap
 *  Data Structures (CS102, Fall 2016)
 *
 ************************************************** */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class CashAbstract {
    static ArrayList<Account> theBook = new ArrayList<Account>();
    static ArrayList<Client> theClients = new ArrayList<Client>();
    static int currentTime = 0;	// this global time
    static int password = 314159; // to enter SUPERVISOR_MODE

    enum Mode {USER_MODE, SUPERUSER_MODE, FREE_MODE};

    static Mode currentMode = Mode.FREE_MODE; // Initial mode

    // the loggedinAccount variable is non-null
    //	iff
    // Cash is in the user-mode:
    static Account loggedinAccount = null;

    // MAIN METHOD:
    public static void main(String[] args) throws IOException {

		// COMMAND LINE ARGUMENT, args[0] in a int:
		//		0 = clear theBook and theClients,
		//		1 = clear theBook, keep theClients
		//		2 = keep theBook and theClients
		Scanner stdInScanner = new Scanner(System.in);

		int clearFlag = 1;    // default is to clear theBook each time
		System.out.printf(">> ATM at your service!\n");
		if (args.length > 0)
			clearFlag = Integer.parseInt(args[0]);

		// get an instance of Cash:
                
		Cash myCash = new Cash();

		switch (clearFlag) {
			case 0:
				System.out.printf(
						">>>  NOTE: New clients and accounts\n");
				break;
			case 1:
				System.out.printf(
						">>>  NOTE: Old clients but new accounts!\n");
				myCash.initClients(); // no account information is
				// initialized for the clients
				break;
			case 2:
				System.out.printf(
						">>>  NOTE: Old clients and accounts!\n");
				myCash.initClients();
				myCash.initAccounts(); // as accounts are read,
				// they are associated to clients
				break;
		}


		///////////////////////////////////////
		//MAIN LOOP:
		///////////////////////////////////////
		Boolean loopFlag = true;

		while (loopFlag) {
			String[] currentLine = stdInScanner.nextLine().split(" ");

			if (!currentLine[0].isEmpty()) {    // else non-empty line
				currentTime++;        // time ticks on
				loopFlag = myCash.processCommand(currentLine);
			}
		}//while loop
		myCash.saveData();
	}//main


    // METHODS: the following methods must be implemented!

    void initClients(){ }; // read from src/theClients.txt
    void initAccounts(){ }; // read from src/theBook.txt
    boolean processCommand(String[] currLine){
    	return false;}; // returns false only for Terminate command
    void saveData(){ }; // Saving theBook, theClients and currentTime:

}// class CashAbstract
