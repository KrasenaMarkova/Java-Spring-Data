package bg.softuni._15_modelmapping.employees.entities;



import java.time.LocalDate;
import java.util.List;

public class Employee {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private double salary;
    private boolean isPTO;

    private Employee manager;
    private List<Employee> staff;

    public Employee() {}

    public Employee(String firstName, String lastName, double salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
    }

    public Employee(String firstName, String lastName, double salary, List<Employee> staff) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.staff = staff;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public List<Employee> getStaff() {
        return staff;
    }

    public void setStaff(List<Employee> staff) {
        this.staff = staff;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public boolean isPTO() {
        return isPTO;
    }

    public void setPTO(boolean PTO) {
        isPTO = PTO;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
