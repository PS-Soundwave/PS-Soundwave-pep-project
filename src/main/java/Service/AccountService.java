package Service;

import DAO.IAccountDAO;
import DAO.impl.AccountDAOImpl;
import Model.Account;
import Util.PasswordUtil;

/* CM: Like the DAO, services can also benefit from an interface (e.g. for mocking)
In the interest of avoiding excessive architectural flourish, I have refrained. */

public class AccountService {
    private IAccountDAO dao = new AccountDAOImpl();

    /*
    * Creates a new account.
    * 
    * @return the account created including its id, or null if account creation failed.
    */
    public Account createAccount(String username, String password) {
        if (username.length() == 0) {
            return null;
        }

        if (!PasswordUtil.isValid(password)) {
            return null;
        }

        /* CM: We do not check for uniqueness: doing so here could create race conditions,
        better to let the DB reject the insert. This is a good candidate for transaction
        or procedure if fine-grained feedback is desired. */

        return dao.addAccount(username, password);
    }

    /*
    * Validates a login.
    * 
    * @return the account if the login was successful, else null.
    */
    public Account validateLogin(String username, String password) {
        return dao.selectAccountByLogin(username, password);
    }
}