package com.a00326288.project01;

enum validation {
TOOMANYCHAR,TOOFEWCHAR,NOSPECIALCHAR,NOUPPERCHAR,NOLOWERCHAR,CONTAINSWHITEPACE,NODIGIT,SUCCESS,QUIT,USERALREADYTAKEN;
}

public class InputValidation {


	public static int validateInput(String input) {
		
		
		try {
			
			final int minimum_chars = 10;
		    final int maximum_chars= 15;
		    final int minSpecialChars= 1;
		    final int minDigCount= 1;
		    final int noWhiteSpace=0;
		    
		    int upper_chars=0;
		    int lower_chars=0;
		    int digit_count=0;
		    int whitespace_cnt=0;
		    int specialChar=0;
		    
			int InputLength = input.length();
			int char_count = 0;
			String SpecialChars="!@#$%&*()'+,-./:;<=>?[]^_`{|}";
			
			for(int i = 0;i<InputLength;i++) {
				char inputchar= input.charAt(i);
				String specialCharCheck = Character.toString(input.charAt(i));
				if(Character.isLetterOrDigit(inputchar) && Character.isLowerCase(inputchar) ) {
					char_count++;
					lower_chars++;
				}else if(Character.isLetterOrDigit(inputchar) && Character.isUpperCase(inputchar)) {
					char_count++;
					upper_chars++;	
				}else if(Character.isDigit(inputchar)) {
					digit_count++;
					char_count++;
				}else if(Character.isWhitespace(inputchar)) {
					whitespace_cnt++;
					char_count++;
				}else if(SpecialChars.contains(specialCharCheck)) { 					
					specialChar++;
					char_count++;
				}
					
			}
			
			
			validation Validation = validation.SUCCESS;
			
			
			if(input.contentEquals("Quit")) {
				Validation=validation.QUIT;}
			else if(InputLength < minimum_chars) {
				Validation=validation.TOOFEWCHAR;
			}else if(InputLength > maximum_chars) {
				Validation=validation.TOOMANYCHAR;
			}else if(whitespace_cnt != noWhiteSpace) {
				Validation=validation.CONTAINSWHITEPACE;
			}else if(digit_count < minDigCount) {
				Validation=validation.NODIGIT;
			}else if(specialChar < minSpecialChars) {
				Validation=validation.NOSPECIALCHAR;
			}
			
			else {
				Validation=validation.SUCCESS;
			}
	 
			switch(Validation) {
			case TOOFEWCHAR:
				  System.out.println("Your username has too few characters. Please try again or Type 'Quit' to finish.");
				  return 0;
			case TOOMANYCHAR:
				  System.out.println("Your username has too many characters. Please try again or Type 'Quit' to finish.");
				  return 0;
			case CONTAINSWHITEPACE:
				  System.out.println("Your username cannot contain white space. Please try again or Type 'Quit' to finish.");
				  return 0;
			case NODIGIT:
				  System.out.println("Your username doesn't contain any digits. Please try again or Type 'Quit' to finish.");
				  return 0;
			case NOSPECIALCHAR:
				  System.out.println("Your username doesn't contain any special characters. Please try again or Type 'Quit' to finish.");
				  return 0;
			case QUIT:
				  return -1; 
			case SUCCESS:
				  return 1;
			default:
				  return 0;	  
			}
			
		}catch(Exception e) {
			return 0;
		}
		
	}
	
	
}
