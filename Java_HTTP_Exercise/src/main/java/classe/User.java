package classe;

public class User {
    private final int id;
    private final String name;
    private final int score;

    public int getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public int getScore(){
        return this.score;
    }

    public User(int id, String name, int score){
        this.id = id;
        this.name = name;
        this.score = score;
    }
}
