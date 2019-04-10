package si.fri.apartment.users.models;

import javax.persistence.*;

@Entity(name = "users")
@NamedQueries({
        @NamedQuery(name = "Users.getAll", query = "SELECT u FROM users u"),
})
public class UserEntity {

    private long id;
    private String username;
    private String email;
    private String passwd;
    private String firstname;
    private String lastname;

    public  UserEntity() {}

    public UserEntity(String username, String email, String passwd, String firstname, String lastname) {
        this.username = username;
        this.email = email;
        this.passwd = passwd;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "username", nullable = false, length = 30)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 30)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "passwd", nullable = false, length = 30)
    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    @Basic
    @Column(name = "firstname", nullable = false, length = 30)
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Basic
    @Column(name = "lastname", nullable = false, length = 40)
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String toString() {
        return String.format("%d %s %s %s %s %s \n", id, username, passwd, email, firstname, lastname);
    }
}
