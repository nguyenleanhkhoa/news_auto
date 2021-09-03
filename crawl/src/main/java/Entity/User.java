package Entity;

import javax.persistence.*;

@Entity
@Table(name = "User",uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    int id;

    @Column(name = "username")
    String userName;

    @Column(name = "password")
    String passWord;

    @Column(name = "fullname")
    String fullName;

    @Column(name = "email")
    String email;

    @Column(name = "level")
    int level;

    public User() {
    }

    public User(int id, String userName, String passWord, String fullName, String email, int level) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.fullName = fullName;
        this.email = email;
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
