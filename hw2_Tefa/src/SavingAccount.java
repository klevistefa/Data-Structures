/* SavingAccount.java
 *
 * 	Saving accounts
 *		subclass of Accounts, of Type 2
 *
 *	Each instance has an interest rate, this is used
 *		and interest is periodically added to balance
 *
 *	Note: time is in terms of the global integer "currentTime"
 *		which is incremented each time a command is
 *		read by Cash.
 *
 * 	Data Structures, Section 7
 * 	Fall 2016
 * 	Prof.Yap
 *
 * ***************************************************/

public class SavingAccount extends Account {

    	static double rate = 10.0;  // percentage interest rate

	int lastUpdate;		// time of last interest update

	// CONSTRUCTORS:
	//=========================================

	SavingAccount(int now) { // constructor
	    super();
	    this.type = 2;  // 0=generic-type, 1=plus-type, 2=saving-type
	    lastUpdate=now;
	}

	// METHODS:
	//=========================================
	
	boolean withdraw(double amount) {
	    if (balance >= amount) {		// ensure nonnegative balance
		super.withdraw(amount);
		return true;
	    }
	    return false;
	}//withdraw


	void updateBal(int now){	// add interest to balance
	    super.deposit(		// 
			balance*(now-lastUpdate)*rate/100.0);
	    lastUpdate = now;		// paid up to date
	}

}//class SavingAccount

