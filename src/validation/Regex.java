package validation;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {

	/**
	 * first name ,last name
	 * 
	 */

	// first name
	public static boolean RegFName(String str) {
		String rFname = "^[A-Z]{1}[a-z]{2,11}$";
		Pattern p = Pattern.compile(rFname);
		Matcher m = p.matcher(str);
		return m.matches();
	}

	// last name
	public static boolean RegLName(String str) {
		String rLname = "^[A-Z]{1}[a-z]{2,11}$";
		Pattern p = Pattern.compile(rLname);
		Matcher m = p.matcher(str);
		return m.matches();
	}

	// email
	public static boolean RegEmail(String str) {
		String rEmail = "^[a-z]{1}[a-z0-9._]{1,20}[@]{1}[a-z]{1,10}[.]{1}[com]{3}$";
		Pattern p = Pattern.compile(rEmail);
		Matcher m = p.matcher(str);
		return m.matches();
	}

	// phone
	public static boolean RegPhone(String str) {
		String rPhone = "^[9]{1}[0-9]{9}$";
		Pattern p = Pattern.compile(rPhone);
		Matcher m = p.matcher(str);
		return m.matches();
	}

	// username
	public static boolean RegUsername(String str) {
		String rUsername = "[a-z]{1}[a-z0-9]{7,15}$";
		Pattern p = Pattern.compile(rUsername);
		Matcher m = p.matcher(str);
		return m.matches();

	}

	// password
	public static boolean RegPassword(String str) {
		String rPwd = "^[A-Z]{1}[a-zA-Z0-9.,!@#$%^&*_-]{7,15}$"; // min 8 max 16
		Pattern p = Pattern.compile(rPwd);
		Matcher m = p.matcher(str);
		return m.matches();
	}

	// cannot use select in combo box
	public static boolean Gender(String type) {
		boolean result = false;
		if (type != "Select") {
			result = true;
		}
		return result;

	}
	
	// credit card
	public static boolean RegCreditCard(String str) {
		String rCC = "[0-9]{4}[-]{1}[0-9]{4}[-]{1}[0-9]{4}[-]{1}[0-9]{4}$"; 
		Pattern p = Pattern.compile(rCC);
		Matcher m = p.matcher(str);
		return m.matches();
	}
	
	
}
