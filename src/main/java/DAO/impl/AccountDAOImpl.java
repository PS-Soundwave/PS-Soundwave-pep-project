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
    public Account addAccount(String username, String password) {
        Connection conn = ConnectionUtil.getConnection();

        try {
            String sql = "INSERT INTO account(username, password) VALUES (?, ?);";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet rs = statement.getGeneratedKeys();

            if (rs.next()) {
                return new Account(rs.getInt(1), username, password);
            }

            return null;
        } catch (SQLException e) {
            StringWriter str = new StringWriter();
            e.printStackTrace(new PrintWriter(str));
            logger.warn(str.toString());
            return null;
        }
    }

    @Override
    public Account selectAccountByLogin(String username, String password) {
        Connection conn = ConnectionUtil.getConnection();

        try {
            String sql = "SELECT * FROM account WHERE username = ? AND password = ?";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return new Account(rs.getInt(1), username, password);
            }

            return null;
        } catch (SQLException e) {
            StringWriter str = new StringWriter();
            e.printStackTrace(new PrintWriter(str));
            logger.warn(str.toString());
            return null;
        }
    }
}
