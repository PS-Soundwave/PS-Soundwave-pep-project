package DAO;

import Model.Account;

public interface IAccountDAO {
    /*
     * Adds a new account.
     * 
     * Returns the added account including its newly assigned id, or null if adding was unsuccessful.
     */
    public Account addAccount(Account account);
}