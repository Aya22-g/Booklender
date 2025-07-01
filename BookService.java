import java.util.ArrayList;

public class BookService {
    private final List<Book> books = new ArrayList<>()

    public List<Book> getBooks() {
        return books
    }

    public Optional<Book> getBookById(long id) {
        return books.stream().filter(b -> b.getId() == id).findFirst();
    }

    public long countBooksByOwner(long employeeId) {
        return books.stream().filter(b -> b.getStatus() == Status.ISSUED && b.getCurrentOwnerId() != null && b.getCurrentOwnerId() == employeeId).count();
    }

    public boolean issueBook(long bookId, long employeeId) {
        if (countBooksByOwner(employeeId) >= 2) {
            return false;
        }

        getBookById(bookId).ifPresent(book -> {
            if (book.getStatus() == Status.AVAILABLE) {
                book.setStatus(Status.ISSUED);
                book.setCurrentOwnerId(employeeId);
            }
        });
        return true;
    }

    public void returnBook(long bookId, long employeeId) {
        getBookById(bookId).ifPresent(book -> {
            if (book.getStatus() == Status.ISSUED && book.getCurrentOwnerId() != null && book.getCurrentOwnerId() == employeeId) {
                book.setStatus(Status.AVAILABLE);
                book.setCurrentOwnerId(null);
            }
        });
    }
}