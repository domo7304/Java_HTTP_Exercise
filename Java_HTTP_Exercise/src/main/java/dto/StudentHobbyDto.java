package dto;

import java.util.List;

public class StudentHobbyDto {
    private final String name;
    private final List<String> hobby;

    public String getName() {
        return name;
    }

    public List<String> getHobby() {
        return hobby;
    }

    public StudentHobbyDto(String name, List<String> hobby) {
        this.name = name;
        this.hobby = hobby;
    }
}
