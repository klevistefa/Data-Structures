/*
 * file: expr/ExprAbstract.java
 *
 *	You are to implement this abstract class 
 *	in a file called expr/Expr.java.
 *
 *	An "(arithmetic) expression" involves a sequence of tokens
 *	where a token could be of these 3 types:
 *
 *		operators:	 +, -, *
 *		operands:	 "[0-9]+"
 *		parenthesis:	 ( )
 *
 *	E.g., here are some valid expressions e's and their values v's:
 *
 *		e0 = "123 - 456 + 789"				v0=456
 *		e1 = "1 - 2 + 3 - 4 + 5 - 6"			v1=-3
 *		e2 = "1 - ( 2 + 3 - 4 ) + 5 - 6"		v2=-1
 *		e3 = "1 - ( 2 + 3 - ( 4 + 5 ) - 6 )"		v3=11
 *		e4 = "1 - 2 + ( 3 - ( 4 + 5 ) - 6 )"		v4=-13
 *		e5 = "1 - ( 2 + 3 ) - 4 + ( 5 - 6 ) )"		v5=-9
 *
 *	For simplicity, we require at least one space any two tokens.
 *	Note that these strings are "balanced" with respect to
 *	the parenthesis "()" and the fact that they only have
 *	digits, operators and parenthesis.   There are 3 ways to 
 *	violate this:
 *	
 *		e6 = "12 + x"		error -1, illegal character x
 *		e7 = "1 - ( 2 +"	error 1, parenthesis not closed
 *		e8 = "1 - 2 )"		error 2, unmatchable parenthesis
 *
 *	We see that these expressions satisfies these 4 "transition rules":
 *
 *	    3: --after an operator, you see ( or operand
 *	    4: --after an operand, you see ) or operator
 *	    5: --after (, you see ( or an operand 
 *	    6: --after ), you see ) an operator
 *
 *	Here are examples of some invalid strings violating these rules:
 *
 *	    e9  = "1 - )"		error 3, operator followed by )
 *	    e10 = "1 - *"		error 3, operator followed by operator
 *	    e11 = "1 ("			error 4, operand followed by (
 *	    e12 = "1 2"			error 4, operand followed by operand
 *	    e13 = "1 - ( )"		error 5, ( followed by )
 *	    e14 = "1 - ( *"		error 5, ( followed by operator
 *	    e15 = "( 1 ) ("		error 6, ) followed by (
 *	    e16 = "( 1 ) 2"		error 5, ( followed by operand
 *
 *
 *	Methods:
 *
 *		int 	bal(String ss)		-- return 0 if ss is balanced
 *		int	valid(String ss)	-- return 0 if ss is a valid
 *		String	expr(int n, Random rg)  -- returns a random expression
 *						   with n operands
 *		int	eval(String ss)		-- evaluates a valid express
 *
 *  Chee Yap
 *  Data Structures
 *  Fall 2016
 *****************************************/
package expr;

import java.util.Random;
import util.*;

public abstract class ExprAbstract {
	
   //========================================
   // MEMBERS:
   //========================================
	static Random rg;

	// SOME BASIC EXPRESSIONS FOR TESTING:
 	static String[] sss = {
	    	////////// FOLLOWING ARE VALID EXPRESSIONS:
		"123 - 456 + 789",
		"1 - 2 * 3 + 4",
 		"1 - 2 + 3 - 4 + 5 - 6",
 		"1 - ( 2 + 3 - 4 ) + 5 - 6",
 		"1 - ( 2 + 3 - ( 4 + 5 ) - 6 )",
 		"1 - 2 + ( 3 - ( 4 + 5 ) - 6 )",
 		"1 - ( 2 + 3 ) - 4 + ( 5 - 6 )",
		////////// FOLLOWING ARE INVALID EXPRESSIONS:
		"12 + x",
		"1 - 2)",
		"1 - 2 )",
		"1 - ( 2 +",
		"1 - )",
		"1 - *",
		"1 (",
		"1 2",
		"1 - ( )",
		"1 - ( *",
		"( 1 ) (",
		"( 1 ) 2"	
	};

   //========================================
   // CONSTRUCTORS:
   //========================================
	 ExprAbstract(int seed){
	     rg = (seed>0)? new Random(seed) : new Random();
	 };	
		
   //========================================
   // METHODS:
   //========================================
	/***************************************************
	 * method bal(ss) checks if ss is balanced with respect to the
	 *	parenthesis "( )" in ss.   It returns
	 *
	 *	-1	if ss does not match "[0-9 ()+-*]+"
	 *	0	else if ss is balanced with respect to "()"
	 *	1	else if ss could be balanced by adding some suffix
	 *	2	else
	 ***************************************************/
	public abstract int bal(String ss);
	
	/***************************************************
	 * method valid(ss) checks if ss represents a valid
	 *	arithmetic expression.  It returns an int value
	 *	between -1 and 6 (inclusive), with 0 representing no error.
	 *
	 *  It first calls bal(ss) to see if the string is balanced.
	 *	If not, it returns the error code from bal(ss),
	 *	i.e., -1,1 or 2.  
	 *	Next, it checks that the string satisfies these
	 *	"transition rules":
	 *	    3:   After an operator, only ( or operand can follow
	 *	    4:   After an operand, only ) or operator can follow
	 *	    5:   After (, only an operand can follow
	 *	    6:   After ), only an operator can follow
	 *	If any of these fail, return the corresponding error code 
	 *	(an integer between 3 and 6 inclusive).
	 *	Otherwise, return 0.
	 ***************************************************/
	public abstract int valid(String ss);
	
	/***************************************************
	 * method expr(n, rg)
	 * 	recursive method to compute
	 *	a balanced arithmetic expression with n operands
	 *	Use rg to generate random operands and operators
	 *	For simplicity, your operands are single digit
	 *	numbers only.
	 *
	 *	USE THIS RECURSIVE RULE:
	 *	  if n=1 then
	 *		expr(n) =  x
	 *	  else
	 *		expr(n) =
	 *		     expr(m) op expr(n-m)
	 *		     | ( expr(m) ) op expr(n-m) 
	 *		     | expr(m) op ( expr(n-m) )
	 * 		where 
	 *			m is an integer in the range [1,2,...,n].
	 *			x  is an operand
	 *			op is an operator
	 *		but they are all randomly generated.
	 *
	 *	Note that when n>1, expr(n) has 3 alternatives, and you
	 *		also have to randomly choose one of these
	 *		3 possibilities.
	 ***************************************************/
	public abstract String expr(int n, Random rg);
	
	
	/***************************************************
	 * eval(ss) returns the value of the expression ss
	 *	assuming ss is valid
	 *	All that this does is to move convert
	 *		the input Queue of tokens (in infix form)
	 *		to the output Queue (in postfix form)
	 *	WE IMPLEMENTED THIS FOR YOU!
	 ***************************************************/
 	public int eval(String ss) throws Exception {
		// precondition: ss is valid
	    return 0;
	}//eval

	/***************************************************
	 * DO NOT IMPLEMENT THIS!!! 
	 * ========================
	 * method badExpr(n, rg)
	 * 	This will first get an expression using expr(n, rg).
	 *	Then it randomly removes a token to "unbalance it".
	 *	We will  implement this to test your bal(..) method.
	 ***************************************************/
	public String unbalGen(int n, Random rg) {
	    return null;
	};
	

}//class
