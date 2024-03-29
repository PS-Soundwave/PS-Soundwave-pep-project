package Service;

import java.util.List;

import DAO.IMessageDAO;
import DAO.impl.MessageDAOImpl;
import Model.Message;

public class MessageService {
    private IMessageDAO dao = new MessageDAOImpl();

    /**
     * Posts a message as the user with id <code>posterId</code>, with contents <code>message</code> and epoch timestamp <code>timestamp</code>.
     */
    public Message postMessage(int posterId, String message, long timestamp) {
        if (message.length() == 0 || message.length() >= 255) {
            return null;
        }

        return dao.insertMessage(posterId, message, timestamp);
    }

    /**
     * Gets all messages.
     */
    public List<Message> getMessages() {
        return dao.selectMessages();
    }

    /**
     * Gets message by id.
     * 
     * @return the message with the given id, or null if a message with the given id does not exist.
     */
    public Message getMessage(int id) {
        return dao.selectMessage(id);
    }

    /**
     * Deletes message by id.
     * 
     * @return the message deleted, or null if a message with the given id does not exist.
     */
    public Message deleteMessage(int id) {
        return dao.deleteMessage(id);
    }

    /**
     * Edits the message of the given id to have the given contents.
     * 
     * @return the message after edited, or null if the edit failed or the message did not exist.
     */
    public Message editMessage(int id, String message) {
        if (message.length() == 0 || message.length() >= 255) {
            return null;
        }

        return dao.updateMessage(id, message);
    }

    /**
     * Gets all messages posted by the user with the given id.
     * 
     * @return the users messages, or an empty list if the user does not exist.
     */
    public List<Message> getMessagesFromUser(int id) {
        return dao.selectMessagesByUser(id);
    }
}
