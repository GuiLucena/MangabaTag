package mangaba.com.br.mangabatag.models;

/**
 * Created by GuilhermeLucena on 20/09/2014.
 */
public abstract class User {

    private String name;
    private String enrollment;

    public User(String name, String enrollment) {
        this.name = name;
        this.enrollment = enrollment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(String enrollment) {
        this.enrollment = enrollment;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
