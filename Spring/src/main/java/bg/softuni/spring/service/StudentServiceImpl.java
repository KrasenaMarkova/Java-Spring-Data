package bg.softuni.spring.service;

import bg.softuni.spring.entities.Student;
import bg.softuni.spring.repositories.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void register(Student student) {
        System.out.println("Register the student");
        studentRepository.save(student);
    }

    @Override
    public Student get(int id) {
        Optional<Student> byId = studentRepository.findById(id);

        return byId.orElseThrow(EntityNotFoundException::new);
    }
}
