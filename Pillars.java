import java.util.*;

public class Pillars {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        System.out.println("Number of pillars: ");
        int n=0;
        while(!(n >= 1)) {
        	try {
        		n = reader.nextInt();
        		if (!(n >= 1)) 
        			System.out.append("Number of pillars has to be >= 1!");
        	} 
        	catch(InputMismatchException e) {
        		System.out.println("Try agin!");
        		String flush = reader.next();
        	}
        }
        
        int d=0;
        System.out.println("Distance between pillars (in meters): ");
        while(!( d >= 10 && d <= 30)) {
        	try {
        		d = reader.nextInt();
        		if (d >= 10 && d <= 30)
        				continue;
        		else
        			System.out.println("Try number between 10 and 30!");
        	}
        	catch(InputMismatchException e) {
        		System.out.println("Try agin!");
        		String flush = reader.next();
        	}	
        	
        }
        
        int w = 0;
        System.out.println("Width of the pillar (in centimeters): ");
        while(!(w >= 10 && w <= 50)) {
        	try {
        		w = reader.nextInt();
        		if (w >= 10 && w <= 50)
    				continue;
    		else
    			System.out.println("Try number between 10 and 50!");
    	}
    	catch(InputMismatchException e) {
    		System.out.println("Try agin!");
    		String flush = reader.next();
    	}	
    	
    }

        int length = (n-2)*w + (n-1)*100*d;

        System.out.println("Number of pillars = " + n);
        System.out.println("Distance between pillars in meters = " + d);
        System.out.println("Width of the pillar in centimeters = " + w);
        System.out.println("Distance between the first and the last pillar in centimeters (without the width of the first and last pillar) = " + length);

        reader.close();
    }
}