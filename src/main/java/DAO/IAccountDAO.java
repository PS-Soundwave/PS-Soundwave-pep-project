package DAO;

import Model.Account;

public interface IAccountDAO {
    /*
     * Adds a new account with given username and password.
     * 
     * Returns the added account including its newly assigned id, or null if adding was unsuccessful.
     */
    public Account addAccount(String username, String password);

    /*
     * Gets account by login (username and password).
     * 
     * Returns the account if the login is valid, or null if the login is invalid.
     */
    public Account selectAccountByLogin(String username, String password);
}