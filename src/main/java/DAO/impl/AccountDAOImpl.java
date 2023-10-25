package DAO.impl;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import DAO.IAccountDAO;
import Model.Account;
import Util.ConnectionUtil;

public class AccountDAOImpl implements IAccountDAO {
    private final Logger logger = LoggerFactory.getLogger(AccountDAOImpl.class);

    @Override
    public Account addAccount(Account account) {
        Connection conn = ConnectionUtil.getConnection();

        try {
            PreparedStatement statement = conn.prepareStatement("INSERT INTO account(username, password) VALUES (?, ?);");

            statement.setString(1, account.getUsername());
            statement.setString(2, account.getPassword());

            if (statement.executeUpdate() != 1) {
                return null;
            }

            ResultSet rs = statement.getResultSet();
            rs.next();
            return new Account(rs.getInt(1), rs.getString(2), rs.getString(3));
        } catch (SQLException e) {
            StringWriter str = new StringWriter();
            e.printStackTrace(new PrintWriter(str));
            logger.warn(str.toString());
            return null;
        }
    }
}
