//package hw1;

/* Account.java
 *
 * 	Bank accounts
 *
 * 	Data Structures, Section 7
 * 	Fall 2016
 * 	Prof.Yap
 *
 * ***************************************************/
public class Account {
	public static int NextAcctNo = 1000;

	int accountNo;
	String owner;
	double balance;

	public Account(String s) { // constructor
		balance = 0.0;
		accountNo = NextAcctNo++;
		owner = s;}

	public void Deposit(double amount) { // method
		balance += amount; }
	public void Withdraw(double amount) { // method
		balance -= amount; }
	public double getBalance() { // method
		return balance; }
	public int getAccountNo() { // method
		return accountNo; }

}//class Account


