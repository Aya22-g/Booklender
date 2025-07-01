public class Employee {
    private long id;
    private String fullName;
    private String email;
    private String password;

    public Employee(long id, String fullName, String email, String password) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
    }
    public long getId() { return id; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
