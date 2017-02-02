/* ZoombinisAbstract class
 *	to be implemented by Zoombinis class
 */

package zoombinis;
import java.util.Random;
import java.io.FileNotFoundException;


public abstract class ZoombinisAbstract {
	int n;
	int s;
	int m;
	String path;
	Random rg;
	
		abstract String randomVowel ();     // return String, not char
		abstract String randomConsonant();  // return String, not char

		abstract String randomTerminalConsonant();  
		abstract String randomInitialSyllable();

		abstract String randomSyllable();
		public abstract String randomName();

	    public static void main(String[] args)  // throws exception
			throws FileNotFoundException {}
}
