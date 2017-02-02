/* Client.java
 *
 * 	Bank clients
 *
 *		For simplicity, they are identified by their name
 *		(no two clients can have the same name).
 *		We assign a client number to each client (starting
 *		from 1, 2, 3 and so on).
 *
 *		A client can open any number of accounts, and
 *		we track them in the member "allAccounts" here.
 *		
 *		This is an abstract class because we defined
 *		a method "listAccounts()" without any body.
 *
 * 	Data Structures, Section 7
 * 	Fall 2016
 * 	Prof.Yap
 *
 * ***************************************************/
import java.util.ArrayList;

class Client {
	static int nextClientNo = 1;

	int clientNo;
	String name;
	ArrayList<Account> allAccounts = new ArrayList<Account>();

	// CONSTRUCTORS:
	//=========================================

	Client(String c) { // constructor
		clientNo = nextClientNo++;
		name = c;
	}
	
	// METHODS:
	//=========================================
	//void listAccounts(); // print all the accounts of this client

}//class Client

