package mangaba.com.br.mangabatag.controller;

import java.util.ArrayList;
import java.util.List;

import mangaba.com.br.mangabatag.models.History;
import mangaba.com.br.mangabatag.models.Lesson;
import mangaba.com.br.mangabatag.models.Progress;
import mangaba.com.br.mangabatag.models.Student;
import mangaba.com.br.mangabatag.models.Teatcher;

/**
 * Created by GuilhermeLucena on 02/10/2014.
 */
public class TestAdapter {

    public TestAdapter() {
    }

    private Student generateStudent(String name, String enrolment, List<Progress> progresses) {
        History history = new History(progresses);
        Student student = new Student(name, enrolment, history);
        return student;
    }

    private Teatcher generateTeatcher(String name, String enrolment) {
        Teatcher teatcher = new Teatcher(name, enrolment);
        return teatcher;
    }

    private List<Progress> generateStudentCurseProgress() {

        Lesson lessonAux;
        List<Progress> progresses = new ArrayList<Progress>();
        lessonAux = generateLesson("programação orientada a gambiarra", "TorcidaJoven", "A", "B");
        progresses.add(new Progress(10, lessonAux));
        lessonAux = generateLesson("Arquitetura e organizaçao de pcs", "Sergio Decepcionado", "C", "D");
        progresses.add(new Progress(18, lessonAux));
        lessonAux = generateLesson("metodos numeros", "rubro negro", "E", "F");
        progresses.add(new Progress(1, lessonAux));
        lessonAux = generateLesson("praticas de nada", "almir", "I", "J");
        progresses.add(new Progress(7, lessonAux));
        lessonAux = generateLesson("Plantacao de arvores", "TJ", "K", "L");
        progresses.add(new Progress(8, lessonAux));

        return progresses;
    }

    private Lesson generateLesson(String name, String teatcher, String startHour, String endHour) {
        Lesson lesson = new Lesson(name, teatcher, startHour, endHour);
        return lesson;
    }

    private Progress generateStudentProgress(Lesson lesson, int absences) {
        Progress studentProgress = new Progress(absences, lesson);
        return studentProgress;
    }

    public Student studentLogin(String name, String enrolment) {
        List<Progress> progresses = new ArrayList<Progress>();
        progresses = this.generateStudentCurseProgress();
        Student student = generateStudent(name, enrolment, progresses);
        return student;
    }

    public void LoginTeatcher(UserReceiver userReceiver, String enrollment) {
        Teatcher teatcher = this.generateTeatcher("TorcidaJovem", enrollment);
        Lesson lesson = generateLesson("programação orientada a gambiarra", "TorcidaJoven", "A", "B");
        Progress progress = new Progress(10, lesson);
        History history = new History(progress);
        Student student = new Student("guri", "2013108226", history);
        lesson.addStudent(student);
        ModelController modelController = ModelController.getInstance();
        modelController.setUser(teatcher);
        modelController.addLesson(lesson);
        userReceiver.onUserReceived(teatcher);
    }

}
