package mangaba.com.br.mangabatag.controller;

import android.content.Context;

/**
 * Created by GuilhermeLucena on 27/09/2014.
 */
public class LoginController {

    public LoginController() {
    }

    public void login(Context ctx, String enrolment, String password) throws Exception {
        ConnectionAdapter adapter = new ConnectionAdapter();
        adapter.requestUser(ctx,enrolment,password);
        ModelController modelController = ModelController.getInstance();
        if(modelController.getUser() == null){
            throw new InvalidPasswordExeption("login e senha errado");
        }
    }


}

