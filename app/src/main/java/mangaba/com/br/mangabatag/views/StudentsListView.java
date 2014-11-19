package mangaba.com.br.mangabatag.views;


import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;

import java.util.List;

import mangaba.com.br.mangabatag.controller.ModelController;
import mangaba.com.br.mangabatag.models.Lesson;
import mangaba.com.br.mangabatag.models.Student;

/**
 * Created by GuilhermeLucena on 06/10/2014.
 */
public class StudentsListView extends ListActivity implements OnItemClickListener {

    private ModelController model;
    private List<Student> lessonStudents;
    private Lesson lesson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.model = ModelController.getInstance();
        Intent intent = getIntent();
        this.lesson = getLesson(intent.getStringExtra("lessonName"));
        this.lessonStudents = this.lesson.getStudents();
        getActionBar().setTitle(intent.getStringExtra("lessonName"));
        ArrayAdapter<Student> adapter = new ArrayAdapter<Student>(this, android.R.layout.simple_list_item_1, this.lessonStudents);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);


    }

    private Lesson getLesson(String lessonName) {
        List<Lesson> lessons = this.model.getLessons();
        for (Lesson lesson : lessons) {
            if (lesson.getName().equals(lessonName)) {
                return lesson;
            }
        }
        return null;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Student student = (Student) adapterView.getItemAtPosition(position);
        Intent intent = new Intent(this, StudentProgressView.class);
        intent.putExtra("lessonName", this.lesson.getName());
        intent.putExtra("studentName", student.getName());
        try {
            startActivity(intent);
        } catch (Exception e) {
            Log.e("erro!", e.getMessage(), e);
        }
    }
}
