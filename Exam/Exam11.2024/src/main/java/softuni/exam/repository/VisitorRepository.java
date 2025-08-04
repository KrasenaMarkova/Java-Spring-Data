package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Visitor;

import java.util.Optional;

@Repository
public interface VisitorRepository extends JpaRepository<Visitor, Integer> {
    //Object findByFirstNameAndByLastName(String firstName, String lastName);

    Optional<Visitor> findByFirstName(String firstName);

    Optional<Visitor> findByLastName(String lastName);

    Optional<Visitor> findByPersonalData(Long personalData);
}
