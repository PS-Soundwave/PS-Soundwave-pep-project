package DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DAO.IMessageDAO;
import Model.Message;
import Util.ConnectionUtil;

public class MessageDAOImpl implements IMessageDAO {
    @Override
    public Message insertMessage(int posterId, String message, long timestamp) {
        Connection conn = ConnectionUtil.getConnection();

        try {
            String sql = "INSERT INTO message(posted_by, message_text, time_posted_epoch) VALUES (?, ?, ?);";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, posterId);
            statement.setString(2, message);
            statement.setLong(3, timestamp);

            statement.executeUpdate();
            
            ResultSet rs = statement.getGeneratedKeys();

            if (rs.next()) {
                return new Message(rs.getInt(1), posterId, message, timestamp);
            }

            return null;
        } catch (SQLException e) {
            return null;
        }
    }
}
