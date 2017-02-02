package stringLog;

import java.io.FileNotFoundException;
import java.util.Random;
import zoombinis.NewZoombinis;

public class NewArrayStringLog
	implements StringLogInterface {

	// MEMBERS:
	String name;
	String[] log;
	int lastIndex = -1;

	// CONSTRUCTORS:
	public NewArrayStringLog(String name, int maxSize) {
	    log = new String[maxSize];
	    this.name = name; 
	}
	public NewArrayStringLog(String name) {
	    log = new String[100];
	    this.name = name; 
	}


	public void insert(String e){
	    resize();		// new!
	    lastIndex++;
	    log[lastIndex] = e;
	}

	public void clear() {
	    lastIndex = -1;
	}

	public boolean isFull() {
	    if (lastIndex == log.length -1)
		return true;
	    else
		return false;
	}

	public int size() {
	    return (lastIndex+1);
	}

	// NEW:
	public String toString(int m){
	    String logString = "Log: " + name + "\n\n";

	    if (m>lastIndex/2) m=lastIndex/2;

	    for (int i=0; i < m; i++)
		logString = logString + (i+1) + ". " + log[i] + "\n";

	    logString = logString + " ...\n";
	    for (int i=lastIndex-m+1; i <= lastIndex; i++)
		logString = logString + (i) + ". " + log[i] + "\n";

	    return logString;
	}

	public boolean contains(String e){
	    for (int i=0; i<=lastIndex; i++){
		if (e.equalsIgnoreCase(log[i]))
		    return true;
	    }
	    return false;
	}

	// RESIZE:
	public void resize(){
	    if (isFull()){
		String[] biggerLog = new String[2*log.length];
		for (int i=0; i<= lastIndex; i++)
		    biggerLog[i] = log[i];
		log = biggerLog;
	    }
	}

	// MAIN class
	public static void main(String[] args) throws FileNotFoundException{
	    int ss = (args.length>0)? Integer.parseInt(args[0]) : 0;
	    int nn = (args.length>1)? Integer.parseInt(args[1]) : 600;
	    int mm = (args.length>2)? Integer.parseInt(args[2]) : 5;
            int mode = (args.length>3)? Integer.parseInt(args[3]) : 1;
            String ppath =  (args.length>4)? ppath=args[4] : ".";
           
	    Random rg = (ss>0)? new Random(ss) : new Random();

	    NewArrayStringLog myLog = new NewArrayStringLog("Tefa's Log");

            NewZoombinis zz = new NewZoombinis(ss, nn, mode, ppath);
            
	    for (int i=0; i<nn; i++){
		myLog.insert(zz.randomName());
	    }

	    System.out.printf(" \n --- first and last %d strings are:\n", mm);
	    System.out.println(myLog.toString(mm));
	}
}//class

