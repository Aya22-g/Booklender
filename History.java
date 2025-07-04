public class History {
    private final List<HistoryEntry> history = new ArrayList<>();

    private static class HistoryEntry {
        long employeeId;
        long bookId;

        HistoryEntry(long employeeId, long bookId) {
            this.employeeId = employeeId;
            this.bookId = bookId;
        }
    }

    public void recordIssuance(long employeeId, long bookId) {
        history.add(new HistoryEntry(employeeId, bookId));
    }

    public List<Long> getBookIdsForEmployee(long employeeId) {
        return history.stream()
                .filter(entry -> entry.employeeId == employeeId)
                .map(entry -> entry.bookId)
                .distinct()
                .collect(Collectors.toList());
    }
}
