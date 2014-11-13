package mangaba.com.br.mangabatag.models;

import java.util.List;

/**
 * Created by GuilhermeLucena on 20/09/2014.
 */
public class Student extends User {

    History history;

    public Student(String name, String enrolment, List<StudentCourseProgress> studentProgress) {
        super(name, enrolment);
        this.history = new History(studentProgress);
    }

    public List<StudentCourseProgress> getProgresses() {
        return this.history.getCourseProgresses();
    }

    public StudentCourseProgress getSingleProgress(String lessonName) {
        return this.history.getSingleProgress(lessonName);
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
