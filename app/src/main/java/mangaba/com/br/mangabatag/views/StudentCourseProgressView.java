package mangaba.com.br.mangabatag.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import mangaba.com.br.mangabatag.R;
import mangaba.com.br.mangabatag.controller.ModelController;
import mangaba.com.br.mangabatag.models.Progress;
import mangaba.com.br.mangabatag.models.Student;

/**
 * Created by GuilhermeLucena on 13/09/2014.
 */
public class StudentCourseProgressView extends Activity {

    ModelController model;
    Progress studentData;
    TextView horario;
    TextView faltas;
    TextView firstGq;
    TextView secondGq;
    TextView finalGq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_view);

        this.horario = (TextView) findViewById(R.id.classGrad);
        this.faltas = (TextView) findViewById(R.id.numberFaults);
        this.firstGq = (TextView) findViewById(R.id.firstGq);
        this.secondGq = (TextView) findViewById(R.id.secondGq);
        this.finalGq = (TextView) findViewById(R.id.finalGq);

        Intent intent = getIntent();
        String turmaNome = intent.getStringExtra("lessonName");
        this.model = ModelController.getInstance();
        Student student = (Student) this.model.getUser();
        this.studentData = student.getSingleProgress(turmaNome);

        this.updateView();

    }

    private void updateView() {
        if (studentData != null) {
            getActionBar().setTitle(this.studentData.toString());
            this.horario.setText(this.studentData.getLesson().getStartHour() + "~" + this.studentData.getLesson().getEndHour());
            this.faltas.setText(this.studentData.getAbsences().toString());
            this.firstGq.setText((this.studentData.getFirstTest().toString()));
            this.secondGq.setText(this.studentData.getSecondTest().toString());
            this.finalGq.setText(this.studentData.getFinalTest().toString());
        }
    }

}
