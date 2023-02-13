package Controller;

import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        
        app.post("/register", this::registerHandler);
        app.post("/login", this::loginHandler);
        app.post("/messages", this::messageHandler);
        app.get("/messages", this::retrieveHandler);
        app.get("/messages/{message_id}", this::retrieveIDHandler);
        app.delete("/messages/{message_id}", this::deleteIDHandler);
        app.patch("/messages/{message_id}", this::updateIDHandler);
        app.get("/accounts/{account_id}/messages", this::retrieveallHandler);
     

        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    
    private void registerHandler(Context context) {
        context.json("Create register");
    }
    private void loginHandler(Context context) {
        context.json("Create login");
    }
    private void messageHandler(Context context) {
        context.json("Create new message");
    }
    private void retrieveHandler(Context context) {
        context.json("Retrieve message");
    }
    private void retrieveIDHandler(Context context) {
        context.json("Retrieve ID message");
    }
    private void deleteIDHandler(Context context) {
        context.json("Delete message ID");
    }
    private void updateIDHandler(Context context) {
        context.json("Update message ID");
    }
    private void retrieveallHandler(Context context) {
        context.json("Retrieve all message");
    }

}