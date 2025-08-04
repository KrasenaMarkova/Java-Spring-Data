package bg.softuni.spring.service;

import bg.softuni.spring.entities.Student;

public interface StudentService {

        void register(Student student);

        Student get(int id);
}
