public class SessionService {
    private final Map<String, UserSession> sessions = new ConcurrentHashMap<>();

    public UserSession createSession(Employee employee) {
        String sessionId = UUID.randomUUID().toString();
        UserSession session = new UserSession(sessionId, employee);
        sessions.put(sessionId, session);
        return session;
    }

    public Optional<UserSession> findSession(String sessionId) {
        if (sessionId == null || sessionId.isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable(sessions.get(sessionId));
    }

    public void removeSession(String sessionId) {
        if (sessionId != null) {
            sessions.remove(sessionId);
        }
    }
}