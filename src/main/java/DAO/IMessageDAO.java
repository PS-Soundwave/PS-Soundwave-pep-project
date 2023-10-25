package DAO;

import java.util.List;

import Model.Message;

public interface IMessageDAO {
    /**
     * Adds a new message with given poster account id, message contents, and timestamp from epoch.
     * 
     * @return the message with message id if successful, else null.
     */
    public Message insertMessage(int posterId, String message, long timestamp);

    /**
     * Gets all messages.
     */
    public List<Message> selectMessages();

    /*
     * Gets the message with the given id, or null if such message does not exist.
     */
    public Message selectMessage(int id);

    /**
     * Deletes the message with the given id.
     * 
     * @return The message deleted, or null if the message did not exist.
     */
    public Message deleteMessage(int id);

    /**
     * Updates the message with the given id to the given message.
     * 
     * @return The message after being updated, or null if the message could not be updated or doesn't exist.
     */
    public Message updateMessage(int id, String message);

    /**
     * Gets all messages from user with given id.
     */
    public List<Message> selectMessagesByUser(int id);
}
