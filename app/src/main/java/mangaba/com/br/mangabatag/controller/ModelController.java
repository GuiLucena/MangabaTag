package mangaba.com.br.mangabatag.controller;

import java.util.ArrayList;
import java.util.List;

import mangaba.com.br.mangabatag.models.Lesson;
import mangaba.com.br.mangabatag.models.User;

/**
 * Created by GuilhermeLucena on 27/09/2014.
 */
public class ModelController {

    private static ModelController instance;
    private User user;
    private List<Lesson> lessons;

    private ModelController() {
    }

    public static ModelController getInstance() {
        if (instance == null) {
            instance = new ModelController();
        }
        return instance;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void addLesson(Lesson lesson) {
        if (this.lessons == null) {
            this.lessons = new ArrayList<Lesson>();
        }
        lessons.add(lesson);
    }

    public List<Lesson> getLessons() {
        return lessons;
    }
}
