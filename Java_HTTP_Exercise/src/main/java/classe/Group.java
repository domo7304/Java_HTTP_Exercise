package classe;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Group {
    private final int id;

    @SerializedName("group_name")
    private final String groupName;

    @SerializedName("student_list")
    private final List<Student> students;

    public int getId() {
        return id;
    }

    public String getGroupName() {
        return groupName;
    }

    public List<Student> getStudents() {
        return students;
    }

    public Group(int id, String groupName, List<Student> students) {
        this.id = id;
        this.groupName = groupName;
        this.students = students;
    }
}
