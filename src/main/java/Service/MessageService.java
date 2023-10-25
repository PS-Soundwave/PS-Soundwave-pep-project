package Service;

import java.util.List;

import DAO.IMessageDAO;
import DAO.impl.MessageDAOImpl;
import Model.Message;

public class MessageService {
    private IMessageDAO dao = new MessageDAOImpl();

    public Message postMessage(int posterId, String message, long timestamp) {
        if (message.length() == 0 || message.length() >= 255) {
            return null;
        }

        return dao.insertMessage(posterId, message, timestamp);
    }

    public List<Message> getMessages() {
        return dao.selectMessages();
    }
}
