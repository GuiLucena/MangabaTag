package mangaba.com.br.mangabatag.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import mangaba.com.br.mangabatag.R;
import mangaba.com.br.mangabatag.controller.ModelController;
import mangaba.com.br.mangabatag.models.Lesson;
import mangaba.com.br.mangabatag.models.Progress;
import mangaba.com.br.mangabatag.models.Student;

/**
 * Created by GuilhermeLucena on 06/10/2014.
 */
public class StudentProgressView extends Activity {
    private Progress studentProgress;
    private ModelController model;
    private TextView absences;
    private TextView firstGq;
    private TextView secondGq;
    private TextView studentSituation;
    private TextView finalGq;
    String  studentName;

    @Override
    protected void onCreate(Bundle savedStateInstance) {
        super.onCreate(savedStateInstance);
        setContentView(R.layout.student_progress);
        this.model = ModelController.getInstance();

        this.absences = (TextView) findViewById(R.id.absences);
        this.firstGq = (TextView) findViewById(R.id.firstGQgrad);
        this.secondGq = (TextView) findViewById(R.id.secondGqGQgrad);
        this.finalGq = (TextView) findViewById(R.id.finalGQgrad);
        this.studentSituation = (TextView) findViewById(R.id.studentSituation);

        Intent intent = getIntent();
        this.studentName = intent.getStringExtra("studentName");
        this.studentProgress = getStudentData(intent.getStringExtra("lessonName"), this.studentName);

        this.updateView();

    }


    private Progress getStudentData(String lessonName, String studentName) {
        List<Lesson> lessons = this.model.getLessons();

        for (Lesson lesson : lessons) {
            if (lesson.getName().equals(lessonName)) {
                for (Student studentData : lesson.getStudents()) {
                    if (studentData.getName().equals(studentName)) {
                        return studentData.getSingleProgress(lesson.getName());
                    }
                }
            }
        }
        return null;
    }

    private void updateView() {
        getActionBar().setTitle(this.studentName);
        this.absences.setText(this.studentProgress.getAbsences().toString());
        this.firstGq.setText(this.studentProgress.getFirstTest().toString());
        this.secondGq.setText(this.studentProgress.getSecondTest().toString());
        this.finalGq.setText(this.studentProgress.getFinalTest().toString());
        this.studentSituation.setText(this.studentProgress.getCourseSituation().toString());
    }

}
