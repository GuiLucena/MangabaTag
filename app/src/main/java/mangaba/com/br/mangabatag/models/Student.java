package mangaba.com.br.mangabatag.models;

import java.util.List;

/**
 * Created by GuilhermeLucena on 20/09/2014.
 */
public class Student extends User {

    History history;

    public Student(String name, String enrolment, History history) {
        super(name, enrolment);
        this.history = history;
    }

    public List<Progress> getProgresses() {
        return this.history.getCourseProgresses();
    }

    public Progress getSingleProgress(String lessonName) {
        return this.history.getSingleProgress(lessonName);
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
