package bg.softuni._15_modelmapping.employees.dtos;

public class EmployeeDTO {
    private String firstName;
    private String lastName;
    private double salary;

    public EmployeeDTO() {}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format("%s %s %.2f", firstName, lastName, salary);
    }
}
