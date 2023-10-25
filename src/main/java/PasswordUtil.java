public class PasswordUtil {
    /*
     * Returns true if and only if the password is valid for a new account.
     */
    public static boolean isValid(String password) {
        return password.length() >= 4;
    }
    // CM: Would prefer this on Model.Account but placed it here given the instructions
}
