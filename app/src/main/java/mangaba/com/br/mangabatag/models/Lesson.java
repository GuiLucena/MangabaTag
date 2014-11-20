package mangaba.com.br.mangabatag.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by GuilhermeLucena on 20/09/2014.
 */
public class Lesson implements Serializable {

    private String name;
    private String teatcher;
    private List<Student> students;
    private String startHour;
    private String endHour;

    public Lesson(String name, String teatcher, List<Student> students, String startHour, String endHour) {
        this.name = name;
        this.teatcher = teatcher;
        this.students = students;
        this.startHour = startHour;
        this.endHour = endHour;
    }

    public Lesson(String name, String teatcher, String startHour, String endHour) {
        this.name = name;
        this.teatcher = teatcher;
        this.startHour = startHour;
        this.endHour = endHour;
    }

    public String getTeatcher() {
        return teatcher;
    }

    public String getName() {
        return name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        if (students == null) {
            students = new ArrayList<Student>();
        }
        students.add(student);
    }

    public Student getStudent(String StudentName) {
        if (students != null) {
            for (Student student : this.students) {
                if (student.getName().equals(StudentName)) {
                    return student;
                }
            }
        }
        return null;
    }

    public String getStartHour() {
        return startHour;
    }

    public String getEndHour() {
        return endHour;
    }

    @Override
    public String toString() {
        return this.name;
    }

   /* public static Lesson parseJSON(JSONObject jsonToParse) throws JSONException {
        Lesson lesson = new Lesson(jsonToParse.getString("discipline"), jsonToParse.getString("teacher"), null,
                jsonToParse.getString("startHour"), jsonToParse.getString("endHour"));

        return lesson;
    }*/
}
