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
import mangaba.com.br.mangabatag.models.Teatcher;
import mangaba.com.br.mangabatag.models.User;

/**
 * Created by GuilhermeLucena on 20/09/2014.
 */
public class JSONBuilder {

    private List<Lesson> turmas;


    public JSONBuilder() {

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
                return this.parseTeacher(jsonObject.getJSONObject("teatcher"));
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
        Student student = new Student(name, enrolment, history);
        ModelController controller = ModelController.getInstance();
        controller.setUser(student);
        return student;
    }

    private Student parseStudent(JSONObject user, Lesson lesson) throws Exception {

        JSONObject histroyJson = user.getJSONObject("history");
        History history = this.parseHistory(histroyJson, lesson);
        String name = user.getString("name");
        String enrolment = user.getString("enrollment");
        Student student = new Student(name, enrolment, history);
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

    private History parseHistory(JSONObject history, Lesson lesson) throws Exception {

        JSONArray progressesJson = history.getJSONArray("progress");
        List<Progress> progresses = new ArrayList<Progress>();
        for (int i = 0; i < progressesJson.length(); i++) {
            progresses.add(this.parseProgress(progressesJson.getJSONObject(i), lesson));
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

    private Progress parseProgress(JSONObject progress, Lesson lesson) throws Exception {
        JSONObject classRoomJson = progress.getJSONObject("classroom");
        int absences = Integer.parseInt(progress.getString("absenceCount"));
        Progress progressNew = new Progress(absences, lesson);
        return progressNew;
    }

    private Teatcher parseTeacher(JSONObject user) throws Exception {
        String name = user.getString("name");
        String enrollment = user.getString("enrollment");
        Teatcher teatcher = new Teatcher(name, enrollment);
        JSONArray classrooms = user.getJSONArray("classrooms");
        ModelController modelController = ModelController.getInstance();
        for(int i = 0; i < classrooms.length();i++){
            Lesson lesson = this.parseClassroom(classrooms.getJSONObject(i),teatcher);
            modelController.addLesson(lesson);
        }
        ModelController controller = ModelController.getInstance();
        controller.setUser(teatcher);
        return teatcher;
    }

    private Lesson parseClassroom(JSONObject classroom, Teatcher teatcher) throws Exception {
        String name = classroom.getString("discipline");
        String teatcherName = teatcher.getName();
        String startHour = classroom.getString("startHour");
        String endHour = classroom.getString("endHour");
        Lesson lesson = new Lesson(name, teatcherName, startHour, endHour);

        JSONArray students = classroom.getJSONArray("students");
        for (int i = 0; i < students.length(); i++) {
            Student student = this.parseStudent(students.getJSONObject(i), lesson);
            lesson.addStudent(student);
        }
        return lesson;
    }


    private Lesson parseClassroom(JSONObject classroom) throws JSONException {
        String name = classroom.getString("discipline");
        String teatcher = classroom.getString("teacherName");
        String startHour = classroom.getString("startHour");
        String endHour = classroom.getString("endHour");
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
