public class UserSession {private final String sessionId;
    private final Employee employee;

    public UserSession(String sessionId, Employee employee) {
        this.sessionId = sessionId;
        this.employee = employee;
    }
    public String getSessionId() { return sessionId; }
    public Employee getEmployee() { return employee; }
}
