package DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<Message> selectMessages() {
        List<Message> messages = new ArrayList<Message>();

        Connection conn = ConnectionUtil.getConnection();

        try {
            Statement statement = conn.createStatement();

            ResultSet rs = statement.executeQuery("SELECT * FROM message;");

            while (rs.next()) {
                messages.add(new Message(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getLong(4)));
            }
        } catch (SQLException e) {}

        return messages;
    }

    @Override
    public Message selectMessage(int id) {
        Connection conn = ConnectionUtil.getConnection();

        try {
            String sql = "SELECT * FROM message WHERE message_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, id);
            
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return new Message(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getLong(4));
            }

            return null;
        } catch (SQLException e) {
            return null;
        }
    }
}
