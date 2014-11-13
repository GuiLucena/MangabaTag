package mangaba.com.br.mangabatag.controller;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import mangaba.com.br.mangabatag.models.Lesson;
import mangaba.com.br.mangabatag.util.web.ConnectionMethod;
import mangaba.com.br.mangabatag.util.web.interfaces.OnConnectionFinished;

/**
 * Created by GuilhermeLucena on 20/09/2014.
 */
public class DataAdapter implements OnConnectionFinished {

    private List<Lesson> turmas;


    public DataAdapter() {
        ConnectionTask tk = new ConnectionTask(null, this, "http://192.168.25.62:8080/poo/me?token=testemaroto",
                ConnectionMethod.GET);
        turmas = new ArrayList<Lesson>();
        tk.execute();

    }

    @Override
    public void onConnectionFinished(JSONObject response) {
        if (response == null) {
            return;
        }
        try {
            if (response.getString("userType") == UserType.TEACHER.toString()) {
                this.parseTeacher(response.getJSONObject("user"));
            } else if (response.getString("userType") == UserType.STUDENT.toString()) {
                this.parseStudent(response.getJSONObject("user"));
            }
        } catch (Exception e) {
            Log.e("foda-se", e.getMessage(), e);
        }
    }

    private void parseStudent(JSONObject user) throws JSONException {
        //TODO parse student
        try {
            JSONObject history = user.getJSONObject("history");

        }catch(JSONException e){

        }
    }

    private void parseTeacher(JSONObject user) throws JSONException {
        JSONArray classrooms = user.getJSONArray("classrooms");
        this.parseClassrooms(classrooms);
    }

    public void parseClassrooms(JSONArray classrooms) throws JSONException {
        JSONObject turmaJson;
        for (int i = 0; i < classrooms.length(); i++) {
            turmaJson = classrooms.getJSONObject(i);
            // Lesson lesson = Lesson.parseJSON(turmaJson);
            // this.turmas.add(lesson);
        }
    }

    public void parseOtherFuckingThing(JSONObject job) {

    }

    private enum UserType {
        STUDENT,
        TEACHER
    }
}
