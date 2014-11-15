package mangaba.com.br.mangabatag.controller;

import android.content.Context;
import android.util.Log;

import org.json.JSONObject;

import java.util.List;

import mangaba.com.br.mangabatag.models.Student;
import mangaba.com.br.mangabatag.models.StudentCourseProgress;
import mangaba.com.br.mangabatag.models.User;

/**
 * Created by GuilhermeLucena on 27/09/2014.
 */
public class LoginController {

    public LoginController() {
    }

    public void login(Context ctx, String enrolment, String password) throws Exception {
        ConnectionAdapter adapter = new ConnectionAdapter();
        adapter.requestUser(ctx,enrolment,password);
        //adapter.requestUser(ctx,enrolment,password);
       // ModelController modelController = ModelController.getInstance();
       // if(modelController.getUser() == null){
      //      Log.d("aqui","324432sdjlflkjlkjlkjslakdjkdafjlkfdsa");
      //  }
    }


}

