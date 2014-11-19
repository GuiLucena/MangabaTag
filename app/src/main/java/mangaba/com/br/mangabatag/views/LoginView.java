package mangaba.com.br.mangabatag.views;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import mangaba.com.br.mangabatag.R;
import mangaba.com.br.mangabatag.controller.InvalidPasswordExeption;
import mangaba.com.br.mangabatag.controller.LoginController;
import mangaba.com.br.mangabatag.controller.ModelController;
import mangaba.com.br.mangabatag.controller.observer;
import mangaba.com.br.mangabatag.models.User;


public class LoginView extends Activity implements observer{

    private EditText matriculaEdit;
    private EditText senhaEdit;
    private ProgressDialog pDialog;
    private ModelController model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.matriculaEdit = (EditText) findViewById(R.id.MatriculaEditText);
        this.senhaEdit = (EditText) findViewById(R.id.SenhaEditText);
        this.model = ModelController.getInstance();
    }

    public void logIn(View v) {
        String senha = senhaEdit.getText().toString();
        String matricula = matriculaEdit.getText().toString();
        LoginController loginController = new LoginController(this);

        try {
            this.launchProgressDialog(this);
            loginController.login(this, matricula, "teste");
        } catch (Exception e) {
            if (e instanceof InvalidPasswordExeption) {
                Toast.makeText(this, "Senha ou matricula invalidas", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Falha ao obter informações", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void updateView(User user) {
        this.pDialog.dismiss();
        if (user != null) {
            Toast.makeText(this, "Logado como " + model.getUser().getName(), Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, LessonsListView.class));
            finish();
        } else {
            Toast.makeText(this, "Falha ao obter informações", Toast.LENGTH_SHORT).show();
        }
    }

    private void launchProgressDialog(LoginView v){
        this.pDialog = ProgressDialog.show(this,"Aguarde","carregando...",true);
        this.pDialog.setCancelable(false);
    }

}
