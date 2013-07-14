package sw.projecteuler.spt;

/**
 * 07/14/2013
 * SPT.java
 * Special Pythagorean triplet
 * 
 * @author Scott Wiedemann
 * 
 */
public class SPT {
	
	public static void main(String[] args) {
		int a = 0;
		int b = 0;
		int c = 0;
        for (a = 1; a < 1000; a++) {
        	b = (500000 - 1000*a)/(1000 - a);
        	if(!(a < b)) {
        		break;
        	}
        	c = (1000 - a) - b;
        	if(a*a + b*b == c*c)	{
        		break;
        	}
		}
        
        System.out.println("a,b,c: " + a +"," + b + "," + c);
		System.out.println("Product abc: " + a*b*c);
	}
}
