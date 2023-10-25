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
      * Create a new account. Returns the account created including its id, or null if account creation failed.
      */
     public Account createAccount(Account account) {
        if (account.getUsername().equals("")) {
            return null;
        }

        if (!PasswordUtil.isValid(account.getPassword())) {
            return null;
        }

        /* CM: We do not check for uniqueness: doing so here could create race conditions,
        better to let the DB reject the insert. This is a good candidate for transaction
        or procedure if fine-grained feedback is desired. */

        return dao.addAccount(account);
     }
}