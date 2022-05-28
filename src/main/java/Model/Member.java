package Model;

public class Member {
    private String username;
    private String password;
    private String nickname;
    private int score;
    private String date;

    public Member(String username, String nickname,String password, int score,String date){
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.score = score;
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }

    public int getScore() {
        return score;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
