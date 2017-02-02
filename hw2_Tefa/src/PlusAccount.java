/* PlusAccount.java
 *
 * 	Checking Plus accounts
 *
 * 	PlusAccount is a subclass of Account, of Type 1.
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

public class PlusAccount extends Account {

	static double rate = 25.0;    // rate for borrowing from bank
	// this is a percentage
	static double penalty = 100.0;        // charge for the first time
	// your account becomes negative.
	int lastUpdate;

	// CONSTRUCTORS:
	//=========================================

	PlusAccount(int now) { // constructor
		super();
		this.type = 1;  // 0=generic-type, 1=plus-type, 2=saving-type
		lastUpdate = now;
	}

	// METHODS:
	//=========================================

	void deposit(double amount, int now) {
		updateBal(now);
		super.deposit(amount);
	}

	boolean withdraw(double amount, int now) {
		updateBal(now);
		double oldBal = this.balance;
		super.withdraw(amount);
		// Levy a penalty for the first time your balance
		//    that becomes negative:
		if (balance < 0 && oldBal >= 0)
			super.withdraw(PlusAccount.penalty);
		return true;
	}//withdraw

	void updateBal(int now) {    // pay any interest on negative balance
		if (balance < 0)
			super.withdraw(        // withdraw a positive amount
					0.0 - balance * (now - lastUpdate) * rate / 100.0);
		lastUpdate = now;        // paid up to date
	}

}//class PlusAccount

