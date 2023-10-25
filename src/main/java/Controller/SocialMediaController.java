package Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Model.Account;
import Service.AccountService;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class SocialMediaController {
    AccountService service = new AccountService();

    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();

        app.post("/register", this::postRegister);
        app.post("/login", this::postLogin);

        return app;
    }

    private void postRegister(Context ctx) {
        Account account = ctx.bodyAsClass(Account.class);

        account = service.createAccount(account.username, account.password);

        if (account == null) {
            ctx.status(400);
        }
        else {
            ctx.json(account);
        }
    }

    private void postLogin(Context ctx) {
        Account account = ctx.bodyAsClass(Account.class);

        account = service.validateLogin(account.getUsername(), account.getPassword());

        if (account == null) {
            ctx.status(401);
        }
        else {
            ctx.json(account);
        }
    }
}