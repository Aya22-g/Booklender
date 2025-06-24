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
    // Создаем экземпляр нашего нового сервиса
    private final static BookService bookService = new BookService();

    public BooklenderServer(String host, int port) throws IOException {
        super(host, port);
        // Регистрируем новый обработчик для пути "/books"
        registerGet("/books", this::handleBooksList);
    }

    private static Configuration initFreeMarker() {
        try {
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);
            cfg.setDirectoryForTemplateLoading(new File("data"));
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            cfg.setLogTemplateExceptions(false);
            cfg.setWrapUncheckedExceptions(true);
            return cfg;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Обработчик для страницы со списком книг
    private void handleBooksList(HttpExchange exchange) {
        // 1. Получаем данные из сервиса
        var books = bookService.getBooks();

        // 2. Упаковываем данные в модель
        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("books", books);

        // 3. Рендерим шаблон с этой моделью
        renderTemplate(exchange, "books.html", dataModel);
    }

    protected void renderTemplate(HttpExchange exchange, String templateFile, Object dataModel) {
        try {
            Template temp = freemarker.getTemplate(templateFile);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            try (OutputStreamWriter writer = new OutputStreamWriter(stream)) {
                temp.process(dataModel, writer);
                writer.flush();
                var data = stream.toByteArray();
                sendByteData(exchange, ResponseCodes.OK, ContentType.TEXT_HTML, data);
            }
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
    }
}
