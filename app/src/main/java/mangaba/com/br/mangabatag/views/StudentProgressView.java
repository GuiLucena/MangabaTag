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
    private Progress studentData;
    private ModelController model;

    @Override
    protected void onCreate(Bundle savedStateInstance) {
        super.onCreate(savedStateInstance);
        setContentView(R.layout.student_progress);
        this.model = ModelController.getInstance();

        TextView absences = (TextView) findViewById(R.id.absences);
        TextView firstGq = (TextView) findViewById(R.id.firstGQgrad);
        TextView secondGq = (TextView) findViewById(R.id.secondGqGQgrad);
        TextView finalGq = (TextView) findViewById(R.id.finalGQgrad);
        TextView studentSituation = (TextView) findViewById(R.id.studentSituation);

        Intent intent = getIntent();
        this.studentData = getStudentData(intent.getStringExtra("lessonName"), intent.getStringExtra("studentName"));

        absences.setText(this.studentData.getAbsences().toString());
        firstGq.setText(this.studentData.getFirstTest().toString());
        secondGq.setText(this.studentData.getSecondTest().toString());
        finalGq.setText(this.studentData.getFinalTest().toString());
        studentSituation.setText(this.studentData.getCourseSituation().toString());

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
}
