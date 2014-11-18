package mangaba.com.br.mangabatag.controller;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import mangaba.com.br.mangabatag.models.History;
import mangaba.com.br.mangabatag.models.Lesson;
import mangaba.com.br.mangabatag.models.Progress;
import mangaba.com.br.mangabatag.models.Student;
import mangaba.com.br.mangabatag.models.User;

/**
 * Created by GuilhermeLucena on 20/09/2014.
 */
public class JSONAdapter {

    private List<Lesson> turmas;


    public JSONAdapter() {

    }

    public boolean authenticateJSON(JSONObject jsonObject) {
        try {
            if (jsonObject.getString("status").equals("OK")) {
                return true;
            } else {
                return false;
            }
        } catch (JSONException exeption) {

        }
        return false;
    }

    public User parseUser(JSONObject jsonObject) {
        try {
            if (jsonObject.getString("type").equals("br.unicap.projeto.model.Student")) {
                return this.parseStudent(jsonObject.getJSONObject("student"));
            } else if (jsonObject.getString("userType") == UserType.TEACHER.toString()) {
                //TODO criar instanciamento de teatcher
                return this.parseStudent(jsonObject.getJSONObject("teatcher"));
            }
        } catch (Exception e) {

        }
        return null;
    }

    private Student parseStudent(JSONObject user) throws Exception {

        JSONObject histroyJson = user.getJSONObject("history");
        History history = this.parseHistory(histroyJson);
        String name = user.getString("name");
        String enrolment = user.getString("enrollment");
        Student student = new Student(name,enrolment,history);
        ModelController controller = ModelController.getInstance();
        controller.setUser(student);
        return student;
    }

    private History parseHistory(JSONObject history) throws Exception {

        JSONArray progressesJson = history.getJSONArray("progress");
        List<Progress> progresses = new ArrayList<Progress>();
        for (int i = 0; i < progressesJson.length(); i++) {
            progresses.add(this.parseProgress(progressesJson.getJSONObject(i)));
        }
        History historyNew = new History(progresses);
        return historyNew;
    }

    private Progress parseProgress(JSONObject progress) throws Exception {
        JSONObject classRoomJson = progress.getJSONObject("classroom");
        Lesson lesson = this.parseClassroom(classRoomJson);
        int absences = Integer.parseInt(progress.getString("absenceCount"));
        Progress progressNew = new Progress(absences, lesson);
        return progressNew;
    }

    private User parseTeacher(JSONObject user) throws JSONException {
        JSONObject classrooms = user.getJSONObject("classrooms");
        this.parseClassroom(classrooms);
        return null;
    }

    public Lesson parseClassroom(JSONObject classrooms) throws JSONException {
        String name = classrooms.getString("discipline");
        String teatcher = classrooms.getString("teacherName");
        String startHour = classrooms.getString("startHour");
        String endHour = classrooms.getString("endHour");
        Lesson lesson = new Lesson(name, teatcher, startHour, endHour);
        ModelController controller = ModelController.getInstance();
        controller.addLesson(lesson);
        return lesson;
    }

    private enum UserType {
        STUDENT,
        TEACHER
    }
}
