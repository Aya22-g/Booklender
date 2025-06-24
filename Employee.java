public class Employee {String id, String name, String login, String password) {
    this.id = id;
    this.name = name;
    this.login = login;
    this.password = password;
    this.booksIssued = new ArrayList<>();
    this.history = new ArrayList<>();
}

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public List<String> getBooksIssued() { return booksIssued; }
    public void setBooksIssued(List<String> booksIssued) { this.booksIssued = booksIssued; }
    public List<String> getHistory() { return history; }
    public void setHistory(List<String> history) { this.history = history; }
}
