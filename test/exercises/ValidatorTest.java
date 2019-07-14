package exercises;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ValidatorTest {

	Validator validator = new Validator();

	@Test
	void testEmailValidation() throws Exception {

		assertTrue(validator.emailIsValid("name@words.com"));
		assertFalse(validator.emailIsValid("name@words"));
		assertFalse(validator.emailIsValid("namewords.com"));
		assertFalse(validator.emailIsValid("@words.com"));
	}

	@Test
	void testPhoneNumbersValidation() throws Exception {

		assertTrue(validator.phoneNumberIsValid("064/3456789"));
		assertFalse(validator.phoneNumberIsValid("065-3456789"));
		assertFalse(validator.phoneNumberIsValid("061 3456789"));
		assertFalse(validator.phoneNumberIsValid("0623456789"));

	}

}
