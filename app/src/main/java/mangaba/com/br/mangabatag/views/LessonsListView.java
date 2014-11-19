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
import mangaba.com.br.mangabatag.models.User;


/**
 * Created by GuilhermeLucena on 31/08/2014.
 */
public class LessonsListView extends ListActivity implements OnItemClickListener {

    private List<Lesson> lessons;
    private User user;
    private ModelController model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.model = ModelController.getInstance();
        this.user = this.model.getUser();
        getActionBar().setTitle(this.user.getName());
        this.lessons = model.getLessons();
        ArrayAdapter<Lesson> adapter = new ArrayAdapter<Lesson>(this, android.R.layout.simple_list_item_1, this.lessons);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

        Lesson lesson = (Lesson) adapterView.getItemAtPosition(position);
        if (this.user instanceof Student) {
            Intent intent = new Intent(this, StudentCourseProgressView.class);
            intent.putExtra("lessonName", lesson.toString());
            try {
                startActivity(intent);
            } catch (Exception exeption) {
                Log.e("erro aluno classe", exeption.getMessage(), exeption);
            }
        } else {
            Intent intent = new Intent(this, StudentsListView.class);
            intent.putExtra("lessonName", lesson.getName());
            try {
                startActivity(intent);
            } catch (Exception exeption) {
                Log.d("erro professor classe", exeption.getMessage(), exeption);
            }
        }

    }


}
