public class Employee {
    String id,
    String name,
    String login,
    String password

    public  Employee(long id, String fullName, String email, String password)
    this.id = id;
    this.name = name;
    this.login = login;
    this.password = password;
}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
