public class Book {
    private long id;
    private String title;
    private String author;
    private Status status;
    private Long currentOwnerId;

    public Book(long id, String title, String author, Status status, Long currentOwnerId) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.status = status;
        this.currentOwnerId = currentOwnerId;
    }
    public long getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
    public Long getCurrentOwnerId() { return currentOwnerId; }
    public void setCurrentOwnerId(Long currentOwnerId) { this.currentOwnerId = currentOwnerId; }
}
