package mangaba.com.br.mangabatag.controller;

import android.content.Context;

import mangaba.com.br.mangabatag.models.User;

/**
 * Created by GuilhermeLucena on 27/09/2014.
 */
public class LoginController implements UserReceiver {

    private observer view;

    public LoginController(observer view) {
        this.view = view;
    }

    public void login(Context ctx, String enrolment, String password) throws Exception {
        if (enrolment.equals("") || password.equals("")) {
            throw new InvalidPasswordExeption("invalid passoword");
        }
        ConnectionAdapter adapter = new ConnectionAdapter(this);
        adapter.requestUser(this, ctx, enrolment, password);

    }

    @Override
    public void onUserReceived(User user) {
        ModelController modelController = ModelController.getInstance();
        this.view.updateView(modelController.getUser());
    }
}

