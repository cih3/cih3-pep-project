package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.Message;
import Util.ConnectionUtil;

public class MessageDAO {
    //creating out message
    public Message InsertNewMessage(Message message){
        Connection connection = ConnectionUtil.getConnection();
     try {
    String sql = "Insert into message (posted_by, message_text, time_posted_epoch) Values(?,?,?);";
            
PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
         
    preparedStatement.setInt(1, message.getPosted_by());
    preparedStatement.setString(2, message.getMessage_text());
    preparedStatement.setLong(3, message.getTime_posted_epoch());   
    preparedStatement.executeUpdate();
    
        ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();
        if (pkeyResultSet.next()){
        int generated_message_id = (int) pkeyResultSet.getLong(1);
        return new Message(generated_message_id, message.getPosted_by(), message.getMessage_text(), message.getTime_posted_epoch());
        }
    }
    catch(SQLException e){
        System.out.println(e.getMessage());
    }
    return null;
}
    public List<Message> GetAllMessages(){
    Connection connection = ConnectionUtil.getConnection();
    List <Message> messages = new ArrayList<>();
    try{
        String sql = "Select * From message;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()){
            Message message = new Message(rs.getInt("message_id"),
            rs.getInt("posted_by"),
            rs.getString("message_text"),
            rs.getInt("time_posted_epoch"));
            messages.add(message);
         }
    }catch(SQLException e){
        System.out.println(e.getMessage());
    }
    return messages;
}

    public Message getMessageById(int id){
        Connection connection = ConnectionUtil.getConnection();
        try{
            String sql = "Select * From message Where message_id = ?; ";
         PreparedStatement preparedStatement = connection.prepareStatement(sql);
     preparedStatement.setInt(1, id);

     ResultSet rs = preparedStatement.executeQuery();
     while(rs.next()){
        Message message = new Message(rs.getInt("message_id"),
        rs.getInt("posted_by"),
        rs.getString("message_text"),
        rs.getInt("time_posted_epoch"));
        return message;
     }
}catch(SQLException e){
    System.out.println(e.getMessage());
}
return null;
}
public Message DeleteMessagebyId(int message_id){
    Connection connection = ConnectionUtil.getConnection();
    try{
        String sql = "Delete From message Where message_id = ? ";
     PreparedStatement preparedStatement = connection.prepareStatement(sql);
    preparedStatement.setInt(1, message_id);
        preparedStatement.executeUpdate();
    return getMessageById(message_id);
    }catch(SQLException e){
        System.out.println(e.getMessage());
    }
    return null;

}
public Message UpdatebyId(int message_id, Message message){
    Connection connection = ConnectionUtil.getConnection();

    try{
        String sql = "Update message Set message_text = ? Where message_id = ?; ";
     PreparedStatement preparedStatement = connection.prepareStatement(sql);

     preparedStatement.setString(1, message.getMessage_text());
    preparedStatement.setInt(2, message_id);
        preparedStatement.executeUpdate(); 
        return getMessageById(message_id);
}
catch(SQLException e){
    System.out.println(e.getMessage());
}
return null;
}
public List<Message> GetMessagesbyAccountId(int posted_by){
    Connection connection = ConnectionUtil.getConnection();
    List <Message> messages = new ArrayList<>();
    try{
        String sql = "Select * From message Where posted_by = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        
        preparedStatement.setInt(1, posted_by);

        ResultSet rs = preparedStatement.executeQuery();
        
        while(rs.next()){
            Message message = new Message (rs.getInt("message_id"), 
            rs.getInt("posted_by"),
            rs.getString("message_text"), 
            rs.getLong("time_posted_epoch"));
            messages.add(message);
        }
    }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return messages;
}
}
