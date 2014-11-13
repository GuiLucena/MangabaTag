package mangaba.com.br.mangabatag.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import mangaba.com.br.mangabatag.R;
import mangaba.com.br.mangabatag.controller.ConnectionExeption;
import mangaba.com.br.mangabatag.controller.InvalidPasswordExeption;
import mangaba.com.br.mangabatag.controller.LoginController;
import mangaba.com.br.mangabatag.controller.ModelController;


public class LoginView extends Activity {

    private EditText matriculaEdit;
    private EditText senhaEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.matriculaEdit = (EditText) findViewById(R.id.MatriculaEditText);
        this.senhaEdit = (EditText) findViewById(R.id.SenhaEditText);
    }

    public void logIn(View V) {
        String senha = senhaEdit.getText().toString();
        String matricula = matriculaEdit.getText().toString();
        ModelController model = ModelController.getInstance();
        LoginController loginController = new LoginController();

        if (matricula.equals("") || senha.equals("")) {
            String erro = getString(R.string.Campo_vazio);
            Toast.makeText(this, erro, Toast.LENGTH_SHORT).show();
        } else {
            try {
                loginController.login(this, matricula, senha);
            } catch (Exception e) {
                if (e instanceof ConnectionExeption) {
                    Toast.makeText(this, "Sem conexão com a internet", Toast.LENGTH_SHORT).show();
                } else if (e instanceof InvalidPasswordExeption) {
                    Toast.makeText(this, "Senha ou matricula invalidas", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Falha ao obter informações", Toast.LENGTH_SHORT).show();
                }
            }
            Toast.makeText(this, "Logado como" + model.getUser().getName(), Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, LessonsListView.class));
            finish();
        }
    }

}
