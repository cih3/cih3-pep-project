package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Model.Account;
import Util.ConnectionUtil;

public class AccountDAO {

    public Account CreateNewUsers(Account account){
        Connection connection = ConnectionUtil.getConnection();
            //this is register
        try {
            String sql = "Insert Into account (username, password) Values (?,?)"; 
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, account.getUsername()); 
            preparedStatement.setString(2, account.getPassword()); 
          
                 preparedStatement.executeUpdate(); 
            ResultSet rs = preparedStatement.getGeneratedKeys(); 
             
            if(rs.next()){
                int generated_account_id = (int) rs.getLong(1);
            return new Account(generated_account_id, account.getUsername(), account.getPassword());
            }
        }catch(SQLException e){
            System.out.println(e.getMessage()); 
           } 
           return null; 

    }

    public Account ProcessUserLogins(String username, String password){
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "Select * From account Where username = ? And password = ?"; 
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username); 
            preparedStatement.setString(2, password); 
            System.out.println(username+password);
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                Account account = new Account(rs.getInt("account_id"),
                rs.getString("username"),
                rs.getString("password"));
                return account;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
          return null;
    }
    


}
