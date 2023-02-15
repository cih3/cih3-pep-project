package Controller;

import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class SocialMediaController {

    AccountService accountService;
    MessageService messageService;

    public SocialMediaController(){
        this.accountService = new AccountService();
        this.messageService = new MessageService();
    }
    
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        
        app.post("/register", this::RegisterHandler); //1
        app.post("/login", this::PostLoginHandler); //2
        app.post("/messages", this::CreatemessageHandler); //3
        app.get("/messages", this::GetAllMessageHandler); //4 
        app.get("/messages/{message_id}", this::RetrieveIDHandler); //5
        app.delete("/messages/{message_id}", this::DeleteIDHandler); //6
        app.patch("/messages/{message_id}", this::UpdateIDHandler); //7
        //app.get("/accounts/{account_id}/messages", this::RetrieveallHandler; //8
     

        return app;
    }
     
    
     //#1 create a new user! /register
     private void RegisterHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(), Account.class);
        Account newAccount = accountService.addAccount(account);
       
        if(newAccount!=null){
            ctx.json(mapper.writeValueAsString(newAccount));
            ctx.status(200);
        }else{
            ctx.status(400);
        }
        
        }
        
    
    //#2 Login the user! /login
    private void PostLoginHandler(Context context) throws JsonProcessingException 
    {
        ObjectMapper mapper = new ObjectMapper(); 
        Account account = mapper.readValue(context.body(), Account.class);
        Account newlogin = accountService.PostLogins(account.getUsername(), account.getPassword());  
        System.out.println(newlogin); 
        if (newlogin != null) {
            context.json(mapper.writeValueAsString(newlogin));
            context.status(200); 
        } else {
            context.status(401);
        }
    
      
    }
    //#3 create new message! /messages
    private void CreatemessageHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(ctx.body(), Message.class);
        Message newMessage = messageService.InsertNewMessage(message);
        if(newMessage !=null){
            ctx.json(mapper.writeValueAsString(newMessage));
            ctx.status(200); 
        }else{
            ctx.status(400);
        }
    }
        
    
    //#4 retrieve new message! /messages
    private void GetAllMessageHandler(Context ctx) throws JsonProcessingException{
        List<Message> messages = messageService.getAllMessage(); 
        ctx.json(messages);
        
    }
    //#5 retrieve message by ID. /messages/{message_id}
    private void RetrieveIDHandler(Context ctx)throws JsonProcessingException {
        //ObjectMapper mapper = new ObjectMapper();
        int messages_id = Integer.parseInt(ctx.pathParam("message_id"));
        Message listMessage = messageService.getMessageByid(messages_id);
        if(listMessage != null){
            ctx.json(listMessage);
        }
        

    }
    //#6 delete message by ID! /messages/{message_id}
    private void DeleteIDHandler(Context ctx) throws JsonProcessingException{
    
    int messages_id = Integer.parseInt(ctx.pathParam("message_id"));
    Message message = messageService.deletebyid(messages_id);
        
    if(message != null){
        ctx.json(message);
        }else{
            ctx.status(200);
        }
    }
        //#7 update message by id /messages/{message_id}
    private void UpdateIDHandler(Context ctx)throws JsonProcessingException  {
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(ctx.body(), Message.class);
        int updateMessage = Integer.parseInt(ctx.pathParam("message_id"));
        Message existingMessage = messageService.updateMessages(updateMessage, message);
        //System.out.println(updatedMessage);
        if(existingMessage == null || existingMessage.message_text.isBlank()){
            ctx.status(400);
        }else{
            ctx.json(existingMessage);
        }
    }
//8 retrieve all messages by a user id! /accounts/{account_id}/messages
/*     
private void RetrieveallHandler(Context ctx) {
    List<Message>messages  = messageService.gettingMessagesByAccountId(Integer.parseInt(ctx.pathParam("account_id")));
        ctx.json(messages);
    }*/
}
    