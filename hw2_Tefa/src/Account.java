/* Account.java
 *
 * 	Bank accounts
 *
 *	Account is an class that needs to be subclassed according
 *		to the following types:
 *			GENERIC_ACCT, PLUS_ACCT, SAVING_ACCT
 *		(see the enum definition in the code)
 *
 *	PLUS_ACCT: a checking account where your balance can be negative,
 *		but this means you are borrow money from the bank at some
 *		(high) interest and penalty each time your
 *		account goes from positive to negative.
 *		You earn no interest for positive balance.
 *
 *	SAVING_ACCT: you get interest but cannot have negative balance.
 *
 *		NOTE:
 *		We use the "default access" for all the
 *		variables and methods.  This means that only
 *		methods in the same package can access them.
 *		(Default access is also known as "Package-Private Access")
 *
 * 	Data Structures, Section 7
 * 	Fall 2016
 * 	Prof.Yap
 *
 * ***************************************************/

class Account {
	static int nextAcctNo = 1000;

	int type = 0;	// 0=generic-type, 1=plus-type, 2=saving-type
	int accountNo;
	double balance;
	Client owner;

	// CONSTRUCTORS:
	//=========================================

	Account() { // constructor
		balance = 0.0;
		accountNo = nextAcctNo++;
		// BE CAREFUL: the owner of this account
		// must be set separately (at a higher level).
	}

	// METHODS:
	//=========================================
	void deposit(double amount) { 
		balance += amount; }
	
	boolean withdraw(double amount) {// return false if cannot withdraw
		balance -= amount;
		return true;
	}

	void print(){
	    System.out.printf(
		"AccountNo: %d, Type = %d, Owner = %s\n    Balance; %.2f\n",
		accountNo, type, owner.name, balance);
	}
}//class Account

