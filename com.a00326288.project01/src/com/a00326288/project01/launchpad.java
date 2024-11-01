package com.a00326288.project01;

import java.util.Scanner; 

public class launchpad {
	
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
	
		int ScannerValue = start();
		System.out.println(ScannerValue );
		
	}
 
	
	public static int start() {

        int selection;
        

        /***************************************************/

        System.out.println("Choose from these choices");
        System.out.println("-------------------------\n");
        System.out.println("1 - Enter an original number");
        System.out.println("2 - Encrypt a number");
        System.out.println("3 - Decrypt a number");
        System.out.println("4 - Quit");

        selection = sc.nextInt();
        return selection;    
    }
}
