package Util;
public class PasswordUtil {
    // CM: Would prefer this on Model.Account but placed it here given the instructions
    /**
     * Validates a new password
     * 
     * @return true if and only if the password is valid for a new account.
     */
    public static boolean isValid(String password) {
        return password.length() >= 4;
    }
}
