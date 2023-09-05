package dto;

public class UserDto {
    private final int id;
    private final String name;

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public UserDto(int id, String name){
        this.id = id;
        this.name = name;
    }
}
