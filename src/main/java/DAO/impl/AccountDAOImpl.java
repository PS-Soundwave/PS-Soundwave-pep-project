package DAO.impl;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
            String sql = "INSERT INTO account(username, password) VALUES (?, ?);";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, account.getUsername());
            statement.setString(2, account.getPassword());

            int res = statement.executeUpdate();
            if (res != 1) {
                return null;
            }

            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            return new Account(rs.getInt(1), account.getUsername(), account.getPassword());
        } catch (SQLException e) {
            StringWriter str = new StringWriter();
            e.printStackTrace(new PrintWriter(str));
            logger.warn(str.toString());
            return null;
        }
    }
}
