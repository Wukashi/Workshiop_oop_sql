package pl.coderslab.workshop;


public class User {
    private int id = 0;
    private String username;
    private String email;
    private String password;

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public User setId(int id) {
        this.id = id;
        return this;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }
    public User(String usern, String mail, String pass){
        username = usern;
        email = mail;
        password = pass;
    }
    public User(int id, String usern, String mail, String pass){
        this.id = id;
        username = usern;
        email = mail;
        password = pass;
    }
}
