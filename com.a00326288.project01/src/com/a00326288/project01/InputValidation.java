package com.a00326288.project01;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

enum validation {
TOOMANYCHAR,TOOFEWCHAR,NOSPECIALCHAR,NOUPPERCHAR,NOLOWERCHAR,CONTAINSWHITEPACE,NODIGIT,USERALREADYTAKEN,NOUPPERCHARS,NOLOWERCHARS,SUCCESS,QUIT;
}

public class InputValidation {


	public static int validateInput(String input) {
		
		
		try {
			
			//Setting the limits as final because they aren't going to change. 
			
			final int minChars = 10;
		    final int maxChars= 15;
		    final int minSpecialChars= 1;
		    final int minDigCount= 1;
		    final int noWhiteSpace=0;
		    final int minUpperChars=1;
		    final int minLowerChars=1;
		    
		    int upper_chars=0;
		    int lower_chars=0;
		    int digit_count=0;
		    int whitespace_cnt=0;
		    int specialChar=0;
		    
			int InputLength = input.length();

			String SpecialChars="!@#$%&*()'+,-./:;<=>?[]^_`{|}";
			
			for(int i = 0;i<InputLength;i++) {
				char inputchar= input.charAt(i);
				String specialCharCheck = Character.toString(input.charAt(i));
				if(Character.isLetterOrDigit(inputchar) && Character.isLowerCase(inputchar) ) {
					lower_chars++;
				}else if(Character.isLetterOrDigit(inputchar) && Character.isUpperCase(inputchar)) {
					upper_chars++;	
				}else if(Character.isDigit(inputchar)) {
					digit_count++;
				}else if(Character.isWhitespace(inputchar)) {
					whitespace_cnt++;
				}else if(SpecialChars.contains(specialCharCheck)) { 					
					specialChar++;
				}
					
			}
			
			
			validation Validation = validation.SUCCESS;
			
			
			if(input.contentEquals("Quit")) {
				Validation=validation.QUIT;}
			else if(InputLength < minChars) {
				Validation=validation.TOOFEWCHAR;
			}else if(InputLength > maxChars) {
				Validation=validation.TOOMANYCHAR;
			}else if(lower_chars < minLowerChars) {
					Validation=validation.NOLOWERCHARS;
			}else if(upper_chars < minUpperChars) {
				Validation=validation.NOUPPERCHARS;
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
				  System.out.println("Your password has too few characters. Please try again or Type 'Quit' to finish.");
				  return 0;
			case TOOMANYCHAR:
				  System.out.println("Your password has too many characters. Please try again or Type 'Quit' to finish.");
				  return 0;
			case NOUPPERCHARS:
				  System.out.println("Your password has no uppercase characters. Please ensure to use mix-case and try again or Type 'Quit' to finish.");
				  return 0;
			case NOLOWERCHARS:
				  System.out.println("Your password has no lowercase characters. Please ensure to use mix-case and try again or Type 'Quit' to finish.");
				  return 0;
			case CONTAINSWHITEPACE:
				  System.out.println("Your password cannot contain white space. Please try again or Type 'Quit' to finish.");
				  return 0;
			case NODIGIT:
				  System.out.println("Your password doesn't contain any digits. Please try again or Type 'Quit' to finish.");
				  return 0;
			case NOSPECIALCHAR:
				  System.out.println("Your password doesn't contain any special characters. Please try again or Type 'Quit' to finish.");
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
	
	public boolean quit(String input) {
		if(input=="Quit") {
			return true;
		}
		return false;
		
	}
	
	public static boolean cardNumberVerification(String cardNumber) {
		
		if(cardNumber.length()>=12 && cardNumber.matches("[0-9]+")) {
			return true;
		}else {
		return false;
		}
	}
	
	public static Date StringToDate(String dob) throws ParseException {
	      SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	      Date date = formatter.parse(dob);
	      return date;
	   }
	
	
	public static boolean checkfieldNull(String input) {
		if(input.isBlank()||input.isEmpty()||input==null) {
			return true;
		}else {
			return false;
		}
	}
	
	
}
