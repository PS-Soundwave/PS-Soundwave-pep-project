package Service;

import DAO.IMessageDAO;
import DAO.impl.MessageDAOImpl;
import Model.Message;

public class MessageService {
    private IMessageDAO dao = new MessageDAOImpl();

    public Message postMessage(int posterId, String message, long timestamp) {
        return dao.insertMessage(posterId, message, timestamp);
    }
}
