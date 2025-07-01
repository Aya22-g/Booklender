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

    public BooklenderServer(String host, int port) throws IOException {
        super(host, port);
        registerGet("/books", this::handleBooksList);
        registerGet("/register", this::handleRegisterGet);
        registerPost("/register", this::handleRegisterPost);
        registerGet("/login", this::handleLoginGet);
        registerPost("/login", this::handleLoginPost);
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
    
    protected void renderTemplate(HttpExchange exchange, String templateFile, Object dataModel) {
        try {
            Template temp = freemarker.getTemplate(templateFile);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            try (OutputStreamWriter writer = new OutputStreamWriter(stream)) {
                temp.process(dataModel, writer);
                var data = stream.toByteArray();
                sendByteData(exchange, ResponseCodes.OK, ContentType.TEXT_HTML, data);
            }
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
    }

    private void handleBooksList(HttpExchange exchange) {
        var books = bookService.getBooks();
        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("books", books);
        renderTemplate(exchange, "books.html", dataModel);
    }

    private void handleRegisterGet(HttpExchange exchange) {
        renderTemplate(exchange, "register.html", null);
    }

    private void handleRegisterPost(HttpExchange exchange) {
        String raw = getBody(exchange);
        Map<String, String> parsed = parseUrlEncoded(raw, "&");
        String email = parsed.get("email");
        String fullName = parsed.get("fullName");
        String password = parsed.get("password");

        boolean success = employeeService.registerEmployee(email, fullName, password);

        if (success) {
            renderTemplate(exchange, "register-success.html", null);
        } else {
            renderTemplate(exchange, "register-fail.html", null);
        }
    }

    private void handleLoginGet(HttpExchange exchange) {
        redirect302(exchange, "/login.html");
    }

    private void handleLoginPost(HttpExchange exchange) {
        String raw = getBody(exchange);
        Map<String, String> parsed = parseUrlEncoded(raw, "&");
        String email = parsed.get("email");
        String password = parsed.get("user-password");

        employeeService.login(email, password)
                .ifPresentOrElse(employee -> {
                    UserSession session = sessionService.createSession(employee);
                    setCookie(exchange, session.getSessionId());
                    redirect302(exchange, "/books");
                }, () -> {
                    renderTemplate(exchange, "login-fail.html", null);
                });
    }

    protected static Map<String, String> parseUrlEncoded(String rawLines, String delimer) {
        return Arrays.stream(rawLines.split(delimer))
                .map(line -> line.split("=", 2))
                .collect(Collectors.toMap(
                        arr -> URLDecoder.decode(arr[0], StandardCharsets.UTF_8),
                        arr -> URLDecoder.decode(arr[1], StandardCharsets.UTF_8)
                ));
    }
    
    private void setCookie(HttpExchange exchange, String sessionId) {
        exchange.getResponseHeaders().add("Set-Cookie", "sessionId=" + sessionId + "; path=/; HttpOnly");
    }

    protected void redirect302(HttpExchange exchange, String path) {
        try {
            exchange.getResponseHeaders().add("Location", path);
            exchange.sendResponseHeaders(302, 0);
            exchange.getResponseBody().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
