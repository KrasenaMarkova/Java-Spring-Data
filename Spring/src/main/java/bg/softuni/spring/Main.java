package bg.softuni.spring;

import bg.softuni.spring.entities.Student;
import bg.softuni.spring.service.StudentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Main implements CommandLineRunner {

    private final StudentService studentService;

    public Main(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Working in Spring");

        //studentService.register(new Student("Pesho"));
        Student fromDb = studentService.get(1);
        studentService.get(5);
    }
}
