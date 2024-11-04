package com.a00326288.project01;

enum validation {
TOOMANYCHAR,TOOFEWCHAR,NOSPECIALCHAR,NOUPPERCHAR,NOLOWERCHAR,CONTAINSWHITEPACE,NODIGIT,SUCCESS;
}

public class inputValidation {


	public static int validateInput(String input) {
		
		
		
		int Valid=0;
		
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
			
			System.out.println("Total number of characters = " + InputLength);
			System.out.println("Total number of lowercase = " + lower_chars);
			System.out.println("Total number of uppercase = " + upper_chars);
			System.out.println("Total number of uppercase = " + upper_chars);
			System.out.println("Total number of digits = " + digit_count);
			System.out.println("Total number of special characters = " + specialChar);
			
			validation Validation = validation.SUCCESS;
			
			if(InputLength < minimum_chars) {
				Validation=validation.TOOFEWCHAR;
			}else if(InputLength > maximum_chars) {
				Validation=validation.TOOMANYCHAR;
			}else if(whitespace_cnt != noWhiteSpace) {
				Validation=validation.CONTAINSWHITEPACE;
			}else if(digit_count < minDigCount) {
				Validation=validation.NODIGIT;
			}else if(specialChar < minSpecialChars) {
				Validation=validation.NOSPECIALCHAR;
			}else {
				Validation=validation.SUCCESS;
			}
	 
			
			switch(Validation) {
			  case TOOFEWCHAR :
				  System.out.println("Your username has too few characters");
			    break;
			  case TOOMANYCHAR:
				  System.out.println("Your username has too many characters");
			    break;
			  case CONTAINSWHITEPACE:
				  System.out.println("Your username cannot contain white space");
				  break;
			  case NODIGIT:
				  System.out.println("Your username doesn't contain any digits");
				 break;
			  case NOSPECIALCHAR:
				  System.out.println("Your username doesn't contain any special characters");
				  break;
			  default:
			    // code block
				  System.out.println("Your username looks good!");
				  
			}
			Valid=1;
		}catch(Exception e) {
			Valid=0;
		}
		
		
		
		
		return Valid;
	}
	
	
}
