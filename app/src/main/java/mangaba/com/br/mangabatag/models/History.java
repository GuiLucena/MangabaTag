package mangaba.com.br.mangabatag.models;

import java.util.List;

/**
 * Created by GuilhermeLucena on 13/10/2014.
 */
public class History {

    List<Progress> courseProgresses;

    public History(List<Progress> courseProgresses) {
        this.courseProgresses = courseProgresses;
    }

    public List<Progress> getCourseProgresses() {
        return courseProgresses;
    }

    public Progress getSingleProgress(String lessonName) {
        if (this.courseProgresses != null) {
            for (Progress progress : this.courseProgresses) {
                if (progress.toString().equals(lessonName)) {
                    return progress;
                }
            }
        }
        return null;
    }
}
