public class EmployeeService {private final List<Employee> employees = new ArrayList<>();
    private final AtomicLong lastId = new AtomicLong(0);

    public EmployeeService() {
        registerEmployee("test@test.com", "Тестовый Сотрудник", "123");
    }

    public boolean registerEmployee(String email, String fullName, String password) {
        if (findEmployeeByEmail(email).isPresent()) {
            return false;
        }
        long id = lastId.incrementAndGet();
        employees.add(new Employee(id, fullName, email, password));
        return true;
    }

    public Optional<Employee> findEmployeeByEmail(String email) {
        return employees.stream()
                .filter(e -> e.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    public Optional<Employee> login(String email, String password) {
        return findEmployeeByEmail(email)
                .filter(employee -> employee.getPassword().equals(password));
    }
}
