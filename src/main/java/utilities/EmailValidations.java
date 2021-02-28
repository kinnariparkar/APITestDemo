package utilities;

import org.apache.commons.validator.routines.EmailValidator;

public class EmailValidations {

	static EmailValidator email_validator = EmailValidator.getInstance();

	public static boolean validateEmailID(String email) {

		return email_validator.isValid(email);

	}

}
