package mangaba.com.br.mangabatag.controller;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.json.JSONObject;

import java.util.List;

import javax.security.auth.login.LoginException;

import mangaba.com.br.mangabatag.models.Student;
import mangaba.com.br.mangabatag.models.StudentCourseProgress;
import mangaba.com.br.mangabatag.models.User;

/**
 * Created by GuilhermeLucena on 27/09/2014.
 */
public class LoginController {

    public LoginController() {
    }

    public boolean loginUser(String enrolment, String password) {
        if (enrolment.equals("2013108226") || password.equals("968217")) {
            TestAdapter adapter = new TestAdapter();
            ModelController model = ModelController.getInstance();
            User user = adapter.studentLogin("guilherme", enrolment);
            model.setUser(user);
            List<StudentCourseProgress> progresses = ((Student) user).getProgresses();
            for (StudentCourseProgress progress : progresses) {
                model.addLesson(progress.getLesson());
            }
            return true;
        }
        return false;
    }

    public void login(Context ctx, String enrolment, String password) throws Exception {

        if (!isConected(ctx)) {
            throw new LoginException("conection error");
        }else{

        }

    }

    private boolean isConected(Context ctx) {
        ConnectivityManager comunication = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo i = comunication.getActiveNetworkInfo();
        if (i == null)
            return false;
        if (!i.isConnected())
            return false;
        if (!i.isAvailable())
            return false;
        return true;
    }

    private JSONObject getUserData(){
        return new JSONObject();
    }

    /*
    String json = "http://minhaurl.com/api/?request=";
    JSONObject job = new JSONObject();
    JSONObject data = new JSONObject();
    job.put("action", "authenticate");
    data.put("enrollment", "123456");
    data.put("password", "123456");
    job.put("parameters", data);
    */


}

