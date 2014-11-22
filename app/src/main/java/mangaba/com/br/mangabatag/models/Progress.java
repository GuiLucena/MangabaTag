package mangaba.com.br.mangabatag.models;

import java.util.Random;

/**
 * Created by GuilhermeLucena on 20/09/2014.
 */
public class Progress {

    private Integer absences;
    private Integer firstTest;
    private Integer secondTest;
    private Integer finalTest;
    private Integer coeficient;
    private CourseSituation courseSituation;
    private Lesson lesson;

    public Progress(Integer absences, Lesson lesson) {

        this.lesson = lesson;
        this.absences = absences;
        Random random = new Random();
        this.firstTest = random.nextInt(11);
        this.secondTest = random.nextInt(11);
        this.finalTest = 0;
        this.setCoeficient();
        this.setCourseSituation();
    }

    public Lesson getLesson() {
        return lesson;
    }

    public Integer getFirstTest() {
        return firstTest;
    }

    public Integer getSecondTest() {
        return secondTest;
    }

    public Integer getFinalTest() {
        return finalTest;
    }

    public Integer getAbsences() {
        return absences;
    }

    private void setCoeficient() {
        this.coeficient = ((2 * this.firstTest) + (3 * this.secondTest) / 5);
    }

    public int getCoeficient() {
        return this.coeficient;
    }

    private void setCourseSituation() {
        if (this.absences > 18) { //absences reprove
            this.courseSituation = CourseSituation.REPROVED;
        } else {
            if (this.coeficient < 5) {
                if (this.coeficient < 3) {//direct reprove
                    this.courseSituation = CourseSituation.REPROVED;
                } else {
                    if (this.coeficient + this.finalTest >= 10.0) {
                        this.courseSituation = CourseSituation.APROVED;
                    } else {//final reproved
                        this.courseSituation = CourseSituation.REPROVED;
                    }
                }
            } else {//aproved
                this.courseSituation = CourseSituation.APROVED;
            }
        }
    }

    public CourseSituation getCourseSituation() {
        return this.courseSituation;
    }

    @Override
    public String toString() {
        return this.lesson.getName();
    }
}
