package relationship;


import java.util.ArrayList;
import java.util.List;

// Loose coupling, depart can exist without a university
class Department {
    private final String name;

    public Department(String name) {
        this.name = name;
    }
}

// Weak Association
class University {
    private final String name;
    private final List<Department> departments; // Aggregation

    public University(String name) {
        this.name = name;
        this.departments = new ArrayList<>();

    }

    public void addDepartment(Department department) {
        departments.add(department);
    }
}

public class Aggregation {
}
