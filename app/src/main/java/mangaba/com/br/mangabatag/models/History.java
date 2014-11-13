package mangaba.com.br.mangabatag.models;

import android.util.Log;

import java.util.List;

/**
 * Created by GuilhermeLucena on 13/10/2014.
 */
public class History {

    List<StudentCourseProgress> courseProgresses;

    public History(List<StudentCourseProgress> courseProgresses) {
        this.courseProgresses = courseProgresses;
    }

    public List<StudentCourseProgress> getCourseProgresses() {
        return courseProgresses;
    }

    public StudentCourseProgress getSingleProgress(String lessonName) {
        if (this.courseProgresses != null) {
            for (StudentCourseProgress progress : this.courseProgresses) {
                if (progress.toString().equals(lessonName)) {
                    return progress;
                }
            }
        }
        return null;
    }
}
