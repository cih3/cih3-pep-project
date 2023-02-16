package Service;

import java.util.List;

import DAO.MessageDAO;
import Model.Message;

public class MessageService {
        public MessageDAO messageDAO;
    
        public MessageService(){
        messageDAO = new MessageDAO();
    }
    public MessageService(MessageDAO messageDAO){
        this.messageDAO = messageDAO;
    }
    //create a message
    public Message InsertNewMessage(Message message){
        String messageString = message.getMessage_text();
        if(!messageString.isBlank() && messageString.length()< 255){
            return messageDAO.InsertNewMessage(message);  
        }else{
            return null; 
        }
        
    }

    public List <Message> getAllMessage(){
        return messageDAO.GetAllMessages();
    }
    public Message getMessageByid(int message_id){
        
        return messageDAO.getMessageById(message_id);
    }

    public Message deletebyid(int message_id){
        Message message = this.messageDAO.getMessageById(message_id);
        messageDAO.DeleteMessagebyId(message_id);
        if(message == null){
            return null;
        }return message;
    }
    public Message updateMessages(int message_id, Message message){
        if(messageDAO.getMessageById(message_id)!= null){
            return messageDAO.UpdatebyId(message_id, message);
        }
        return null;
    }
    public List <Message>GetMessagesbyAccountid( int posted_by){
        return messageDAO.GetMessagesbyAccountId(posted_by);
    }
}

