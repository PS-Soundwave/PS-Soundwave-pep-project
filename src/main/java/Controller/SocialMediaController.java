package Controller;

import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class SocialMediaController {
    AccountService accountService = new AccountService();
    MessageService messageService = new MessageService();

    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();

        app.post("/register", this::postRegister);

        app.post("/login", this::postLogin);

        app.post("/messages", this::postMessages);

        return app;
    }

    private void postRegister(Context ctx) {
        Account account = ctx.bodyAsClass(Account.class);

        account = accountService.createAccount(account.username, account.password);

        if (account == null) {
            ctx.status(400);
        }
        else {
            ctx.json(account);
        }
    }

    private void postLogin(Context ctx) {
        Account account = ctx.bodyAsClass(Account.class);

        account = accountService.validateLogin(account.getUsername(), account.getPassword());

        if (account == null) {
            ctx.status(401);
        }
        else {
            ctx.json(account);
        }
    }

    private void postMessages(Context ctx) {
        Message message = ctx.bodyAsClass(Message.class);

        /* CM: *Really* no reason to be trusting the message is posted with a correct timestamp.
         * Just done in case tests expect it to be respected.
         */
        message = messageService.postMessage(message.getPosted_by(), message.getMessage_text(), message.getTime_posted_epoch());

        if (message == null) {
            ctx.status(400);
        }
        else {
            ctx.json(message);
        }
    }
}