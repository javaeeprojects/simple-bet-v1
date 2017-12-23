import javax.persistence.*;

@Entity
@Table(name = "bet_user")
public class BetUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;

    private String password;

    private int points;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type")
    private UserType userType;

    public BetUser() {
    }

    public BetUser(String username, String password, int points, UserType userType) {
        this.username = username;
        this.password = password;
        this.points = points;
        this.userType = userType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BetUser betUser = (BetUser) o;

        if (id != betUser.id) return false;
        if (points != betUser.points) return false;
        if (username != null ? !username.equals(betUser.username) : betUser.username != null) return false;
        if (password != null ? !password.equals(betUser.password) : betUser.password != null) return false;
        return userType == betUser.userType;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + points;
        result = 31 * result + (userType != null ? userType.hashCode() : 0);
        return result;
    }
}
