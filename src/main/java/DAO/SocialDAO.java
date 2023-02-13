package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Util.ConnectionUtil;

public class SocialDAO {
    
    public List<User> getUser(){
        Connection connection = ConnectionUtil.getConnection();
        List<User> Users = new ArrayList<>();
        try {
           
            String sql = "Select * FROM User";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                User author = new User(rs.getInt("id"), rs.getString("name"));
                Users.add(Users);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return Users;
    } 
}




