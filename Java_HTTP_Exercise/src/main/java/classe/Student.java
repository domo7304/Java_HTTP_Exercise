package classe;

import java.util.List;

public class Student {
    private final String name;
    private final String classroom;
    private final List<String> hobby;

    public String getName() {
        return name;
    }

    public String getClassroom() {
        return classroom;
    }

    public List<String> getHobby() {
        return hobby;
    }

    public Student(String name, String classroom, List<String> hobby) {
        this.name = name;
        this.classroom = classroom;
        this.hobby = hobby;
    }
}
