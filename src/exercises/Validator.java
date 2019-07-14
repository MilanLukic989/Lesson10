package exercises;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Write unit tests and code to check if email addresses and phone numbers are valid.

public class Validator {

	public boolean emailIsValid(String email) {
		Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
		Matcher matcher = pattern.matcher(email);
		return matcher.find();
	}

	public boolean phoneNumberIsValid(String number) {
		Pattern pattern = Pattern.compile("\\d{3}/\\d{7}");
		Matcher matcher = pattern.matcher(number);
		return matcher.find();
	}

}
