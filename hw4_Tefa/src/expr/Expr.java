/*
 * file: expr/Expr.java
 *
 *	You are to implement this class.
 *
 *	It implements balanced/ExprAbstr class, so read that file
 *		for details first.
 *
 *	We import Queue and Stack classes
 *		-- Stack comes from Java.util,
 *			so it has push and pop as expected.
 *	
 *		-- There is no direct Queue implementation in Java,
 *			and so I "roll my own" to have
 *			enqueue and dequeue operations.
 *
 *	DO NOT REIMPLEMENT THE MAIN METHOD HERE!
 *
 *  Chee Yap
 *  Data Structures
 *  Fall 2016
 *****************************************/
package expr;

import java.lang.Integer;
import java.util.EmptyStackException;
import java.util.regex.*;
import java.util.Random;
import java.util.Stack;
import util.MyQueue; // our own implementation!

public class Expr extends ExprAbstract {
	
   //========================================
   // MEMBERS:
   //========================================
       static int verbose = 0;  // larger value means more verbose

       MyQueue<String> inQ = new MyQueue<String>();
       MyQueue<String> outQ = new MyQueue<String>();
       Stack<String> stk = new Stack<String>();
	
       enum tokenType {OPERATOR, OPERAND, LEFTPAREN, RIGHTPAREN, ERROR};
	
   //========================================
   // CONSTRUCTORS:
   //========================================
	Expr(int seed){
	    super(seed);
	}
		
   //========================================
   // METHODS:
   //========================================
	/***************************************************
	 * method bal(ss) checks if ss is balanced with respect to the
	 *	parenthesis "( )" in ss.   It returns
	 *	-1	if ss does not match "[0-9 ()+-*]+"
	 *	0	else if ss is balanced with respect to "()"
	 *	1	else if ss could be balanced by adding some suffix
	 *	2	else
	 ***************************************************/
	@Override
	public int bal(String ss){
            if (!ss.matches("[0-9 ()+*-]+")){
                return -1;
            }
            try {
                for (int i = 0; i < ss.length(); i++){
                    if (ss.charAt(i) == '('){
                        stk.push(String.valueOf(ss.charAt(i)));
                    }
                    if (ss.charAt(i) == ')'){
                        stk.pop();
                    }
                }
            } catch (EmptyStackException e){
                return 2;
            }
            if (!stk.isEmpty()){
                return 1;
            }
	    return 0;
	}//bal
	
	@Override
	public int valid(String ss){
            if (bal(ss) == -1){
                return bal(ss);
            }
            String[] token = ss.split(" ");
            for (int i = 0; i < token.length - 1; i++){
                if (token[i].matches("[0-9]+") && !(token[i+1].matches("[)]") || token[i+1].matches("[-+*]"))){
                    return 4;
                } else if (token[i].matches("[-+*]") && !(token[i+1].matches("[(]") || token[i+1].matches("[0-9]+"))){
                    return 3;
                } else if (token[i].matches("[(]") && !(token[i+1].matches("[0-9]+"))){
                    return 5;
                } else if (token[i].matches("[)]") && !(token[i+1].matches("[-+*]"))){
                    return 6;
                }
            }
            
            if (token[token.length - 1].matches("[-+*]")) return 3;
	    return bal(ss);
	}//valid


	/***************************************************
	 * method expr(n, rg) generates a random expr with n operands.
	 *	USE THIS RECURSIVE RULE:
	 *	  if n=1 then
	 *		expr(n) =  x
	 *	  else
	 *		expr(n) =
	 *		     expr(m) op expr(n-m)
	 *		     | ( expr(m) ) op expr(n-m) 
	 *		     | expr(m) op ( expr(n-m) )
	 ***************************************************/
	@Override
	public String expr(int n, Random rg){
            String[] operators = {"+", "-", "*"};
            if (n == 1){
                 return String.valueOf(rg.nextInt(10));
            } else {
                int m = 1 + rg.nextInt(n-1);
            switch (rg.nextInt(3)){
                case 0: return expr(m, rg) + " " + operators[rg.nextInt(3)] + " " + expr(n-m, rg); 
                
                case 1: return "( " + expr(m, rg) + " )" + " " + operators[rg.nextInt(3)] + " " + expr(n-m, rg); 
                
                case 2: return expr(m, rg) + " " + operators[rg.nextInt(3)] + " " + "( " + expr(n-m, rg) + " )";
            }  
            }
            return "";
	}//expr
	
	/***************************************************
	 * eval(ss)
	 *	evaluates and returns the value of
	 *	the expression ss.
	 ***************************************************/
	public int eval(String ss) throws Exception {
	    convert(ss);	// first convert it into postfix form 
	    			// storing the result in outQ.
	    return postfixEval();
	}

	/***************************************************
	 * DO NOT HAVE TO IMPLEMENT THIS!!! 
	 * ================================
	 * method badExpr(n, rg)
	 * 	This will initially get an expression using expr(n, rg).
	 *	Then it randomly removes a token to "unbalance it".
	 *	We will  implement this to test your bal(..) method.
	 ***************************************************/
	public String unbalGen(int n, Random rg) {
	    return "";
	};
	
   //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
   public static void main(String[] args)
       				throws Exception {
   //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	    int seed =  (args.length>0)? Integer.parseInt(args[0]) : 0;
	    int n =  (args.length>1)? Integer.parseInt(args[1]) : 6; 
	    verbose =  (args.length>2)? Integer.parseInt(args[2]) : 0;
	
	    // NOTE: we use verbosity to allow or prevent diagnostic
	    // 		messages from being printed!
	    //		If you set verbose to a large value (like 10),
	    //		most messages can be eliminated!

	    Expr ex = new Expr(seed);
	
	    // Choose an expression from the String array sss:
	    //
	    System.out.printf("Expression \"%s\"\n",sss[n]);

	    if (n>=sss.length) n = sss.length-1;

	    System.out.printf("   Its value is %d\n",
	    		ex.eval(sss[n])); // evaluate it


	    // MODIFY THIS TEST FOR YOUR EXPRESSIONS...
	    if (verbose>2){
	     for (String ss : sss){
	       if (ex.bal(ss)==0)
	          System.out.printf("String \"%s\" is balanced\n",ss);
	       else
	          System.out.printf("String \"%s\" is unbalanced\n",ss);
	     }//for
	    }//if

	}//main

   //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
   // Helper methods
   //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

   // DETERMINE the type of a token:
   tokenType theType(String tok){
       if (tok.matches("[0-9]+"))
	       return tokenType.OPERAND;
       if (tok.matches("[+-]|\\*"))
	       return tokenType.OPERATOR;
       if (tok.matches("\\("))
	       return tokenType.LEFTPAREN;
       if (tok.matches("\\)"))
	       return tokenType.RIGHTPAREN;
       return tokenType.ERROR;
   }//theType

   // LOAD the expression ss as a sequence of tokens into the input Queue:
   void load(String ss) throws Exception {
       String[] toks = ss.split(" +");
       for (int i = 0; i< toks.length; i++){
   	inQ.enqueue(toks[i]);
	if (theType(toks[i])==tokenType.ERROR)
	    throw new Exception("load: tokenType.ERROR, " + toks[i]);
   	if (verbose>10)
   	    System.out.printf("Token = %s of type %s\n",
		    toks[i], theType(toks[i]));
       }//for
   }//load

   // COMPARE TWO OPERATORS and return true if op1 <= op2 :
   boolean lessOrEqual(String op1, String op2){
       if (verbose>10)
	   System.out.printf("%s <= %s: %s\n", op1, op2,
		   (op2.matches("\\*") || op1.matches("[+-]") ));
       return
	   (op2.matches("\\*") || op1.matches("[+-]") );
   }//

   // THIS CONVERTS the string into a postfix form, storing result in outQ:
   public void convert(String ss) throws Exception {
	// precondition: ss is valid
	load(ss);	// load the tokens in ss into input Q
	
	while (!inQ.isEmpty()){
	    String tok = inQ.dequeue();
	    switch (theType(tok)){
		case OPERAND:
		    	outQ.enqueue(tok);
		    	break;
		case LEFTPAREN:
		    	stk.push(tok);
		    	break;
		case RIGHTPAREN:
			while (!stk.isEmpty()
					&&
				theType(stk.peek())!=tokenType.LEFTPAREN)
				    outQ.enqueue(stk.pop());
			// precondition: there is a LEFTPAREN
			stk.pop();
		    	break;
		case OPERATOR:
			while ((!stk.isEmpty())
				    	&&
				theType(stk.peek())!=tokenType.LEFTPAREN
				    	&&
				lessOrEqual(tok,stk.peek()))
			   outQ.enqueue(stk.pop()); //endwhile
			stk.push(tok);
		    	break;
		default:
			// ERROR
			System.out.println("ERROR in parsing expression!");
	    }//switch
	}//while
	while (!stk.isEmpty())
		outQ.enqueue(stk.pop()); //endwhile
//showQs("End of convert");
   }//convert

   // This evaluates the postfix expression in outQ:
   // precond: stk is empty
   int postfixEval(){
       int val;
       String tok;
       int op1, op2;
       while (!outQ.isEmpty()){
	   tok = outQ.dequeue();
	   switch (theType(tok)){
	       case OPERAND:
		   stk.push(tok);
		   break;
	       case OPERATOR:
		   op2 = Integer.parseInt(stk.pop());
		   op1 = Integer.parseInt(stk.pop());
		   if (tok.matches("\\+"))
		       stk.push((new Integer(op1 + op2)).toString());
		   else if (tok.matches("-"))
		       stk.push((new Integer(op1 - op2)).toString());
		   else 
		       stk.push((new Integer(op1 * op2)).toString());
		   break;
	   }//switch
       }//postfixEval
       return Integer.parseInt(stk.pop());
   }// postfixEval

   // Show both Queues:
   void showQs(String msg){
	if (verbose>0) {
   	    System.out.printf("::: %s\n", msg);
   	    System.out.printf("   Stack: \n");
            for (String s : stk)
   	          System.out.printf(" %s ", s);
   	    System.out.printf("\n");
   	    System.out.printf("   Input Queue: \n");
            for (String s : inQ)
   	          System.out.printf(" %s ", s);
   	    System.out.printf("\n");
   	    System.out.printf("   Output Queue: \n");
            for (String s : outQ)
   	          System.out.printf(" %s ", s);
   	    System.out.printf("\n");
	}
   }
}//class
