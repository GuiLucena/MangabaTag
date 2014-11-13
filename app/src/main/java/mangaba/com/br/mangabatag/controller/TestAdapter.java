package mangaba.com.br.mangabatag.controller;

import java.util.ArrayList;
import java.util.List;

import mangaba.com.br.mangabatag.models.Lesson;
import mangaba.com.br.mangabatag.models.Student;
import mangaba.com.br.mangabatag.models.StudentCourseProgress;
import mangaba.com.br.mangabatag.models.Teatcher;

/**
 * Created by GuilhermeLucena on 02/10/2014.
 */
public class TestAdapter {

    public TestAdapter() {
    }

    private Student generateStudent(String name, String enrolment, List<StudentCourseProgress> history) {
        Student student = new Student(name, enrolment, history);
        return student;
    }

    private Teatcher generateTeatcher(String name, String enrolment) {
        Teatcher teatcher = new Teatcher(name, enrolment);
        return teatcher;
    }

    private List<StudentCourseProgress> generateStudentCurseProgress() {

        Lesson lessonAux;
        List<StudentCourseProgress> studentCourseProgresses = new ArrayList<StudentCourseProgress>();
        lessonAux = generateLesson("programação orientada a gambiarra", "TJ", "A", "B");
        studentCourseProgresses.add(new StudentCourseProgress(10, lessonAux));
        lessonAux = generateLesson("Arquitetura e organizaçao de pcs", "Sergio Decepcionado", "C", "D");
        studentCourseProgresses.add(new StudentCourseProgress(18, lessonAux));
        lessonAux = generateLesson("metodos numeros", "rubro negro", "E", "F");
        studentCourseProgresses.add(new StudentCourseProgress(1, lessonAux));
        lessonAux = generateLesson("praticas de nada", "almir", "I", "J");
        studentCourseProgresses.add(new StudentCourseProgress(7, lessonAux));
        lessonAux = generateLesson("Plantacao de arvores", "TJ", "K", "L");
        studentCourseProgresses.add(new StudentCourseProgress(8, lessonAux));

        return studentCourseProgresses;
    }

    private Lesson generateLesson(String name, String teatcher, String startHour, String endHour) {
        Lesson lesson = new Lesson(name, teatcher, startHour, endHour);
        return lesson;
    }

    private StudentCourseProgress generateStudentProgress(Lesson lesson, int absences) {
        StudentCourseProgress studentProgress = new StudentCourseProgress(absences, lesson);
        return studentProgress;
    }

    public Student studentLogin(String name, String enrolment) {
        List<StudentCourseProgress> studentCourseProgresses = new ArrayList<StudentCourseProgress>();
        studentCourseProgresses = this.generateStudentCurseProgress();
        Student student = generateStudent(name, enrolment, studentCourseProgresses);
        return student;
    }
}
