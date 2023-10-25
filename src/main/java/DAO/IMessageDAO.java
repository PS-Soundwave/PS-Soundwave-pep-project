package DAO;

import java.util.List;

import Model.Message;

public interface IMessageDAO {
    /*
     * Adds a new message with given poster account id, message contents, and timestamp from epoch.
     * 
     * Returns the message with message id if successful, else null.
     */
    public Message insertMessage(int posterId, String message, long timestamp);

    /*
     * Get all messages.
     */
    public List<Message> selectMessages();
}
