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
public class JSONAdapter  {

    private List<Lesson> turmas;


    public JSONAdapter() {

    }

    private boolean authenticateJSON(JSONObject jsonObject){
        try {
            if (jsonObject.getString("status").equals("OK")) {
                return true;
            }
            else{
                return false;
            }
        }catch(JSONException exeption){

        }
        return false;
    }

    public void parseUser(JSONObject jsonObject)throws Exception{
        try {
            if (jsonObject.getString("userType").equals(UserType.STUDENT.toString())){
               this.parseTeacher(jsonObject.getJSONObject("user"));
            }
            else if(jsonObject.getString("userType") == UserType.TEACHER.toString()){
                this.parseStudent(jsonObject.getJSONObject("user"));
            }
        }catch (JSONException e){
            throw new Exception();
        }
    }

    private void parseStudent(JSONObject user) throws JSONException {
        //TODO parse student
        try {
            JSONObject history = user.getJSONObject("history");

        } catch (JSONException e) {

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

    private enum UserType {
        STUDENT,
        TEACHER
    }
}
