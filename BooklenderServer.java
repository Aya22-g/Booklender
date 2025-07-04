package kg.attractor.java.lesson44;

import com.sun.net.httpserver.HttpExchange;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import kg.attractor.java.server.BasicServer;
import kg.attractor.java.server.ContentType;
import kg.attractor.java.server.ResponseCodes;

import java.io.*;

public class BooklenderServer extends BasicServer {
    private final static Configuration freemarker = initFreeMarker();
    private final static BookService bookService = new BookService();
    private final static EmployeeService employeeService = new EmployeeService();
    private final static SessionService sessionService = new SessionService();

    private final static HistoryService historyService = new HistoryService();

    public BooklenderServe(String host, int port) throws IOException {
        super(host, port);
        registerGet("/books", this::handleBooksList);
        registerGet("/register", this::handleRegisterGet);
        registerPost("/register", this::handleRegisterPost);
        registerGet("/login", this::handleLoginGet);
        registerPost("/login", this::handleLoginPost);
        registerGet("/logout", this::handleLogout);
        registerGet("/issue", this::handleIssueBook);
        registerGet("/return", this::handleReturnBook);
        registerGet("/book", this::handleGetBook);
        registerGet("/profile", this::handleProfile);
    }

    private static Configuration initFreeMarker() {
        try {
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);
            cfg.setDirectoryForTemplateLoading(new File("data"));
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            cfg.setLogTemplateExceptions(false);
            return cfg;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleIssueBook(HttpExchange exchange) {
        Optional<Employee> currentUserOpt = getCurrentUser(exchange);
        if (currentUserOpt.isEmpty()) {
            redirect302(exchange, "/login");
            return;
        }

        String query = exchange.getRequestURI().getQuery();
        long bookId = Long.parseLong(query.substring(query.indexOf("=") + 1));
        bookService.issueBook(bookId, currentUserOpt.get().getId(), historyService);
        redirect302(exchange, "/books");

        private void handleProfile(HttpExchange exchange) {
            Optional<Employee> currentUserOpt = getCurrentUser(exchange);
            if (currentUserOpt.isEmpty()) {
                redirect302(exchange, "/login"); // Доступ только авторизованным
                return;
            }

            Employee currentUser = currentUserOpt.get();


            List<Long> bookIds = historyService.getBookIdsForEmployee(currentUser.getId());


            List<Book> userBooks = bookIds.stream()
                    .map(bookService::getBookById)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toList());

            Map<String, Object> data = new HashMap<>();
            data.put("user", currentUser);
            data.put("books", userBooks);

            renderTemplate(exchange, "profile.html", data);
        }
    }
