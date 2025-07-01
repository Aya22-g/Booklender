public Book(String id, String title, String author, String imagePath, String description) {
    this.id = id;
    this.title = title;
    this.author = author;
    this.status = "available";
    this.imagePath = imagePath;
    this.description = description;
    this.history = new ArrayList<>();
}


public String getId() { return id; }
public void setId(String id) { this.id = id; }
public String getTitle() { return title; }
public void setTitle(String title) { this.title = title; }
public String getAuthor() { return author; }
public void setAuthor(String author) { this.author = author; }
public String getStatus() { return status; }
public void setStatus(String status) { this.status = status; }
public String getImagePath() { return imagePath; }
public void setImagePath(String imagePath) { this.imagePath = imagePath; }
public String getDescription() { return description; }
public void setDescription(String description) { this.description = description; }
public List<String> getHistory() { return history; }
public void setHistory(List<String> history) { this.history = history; }