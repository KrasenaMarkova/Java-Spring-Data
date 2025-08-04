package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.LibraryMember;

import java.util.List;
import java.util.Optional;

@Repository
public interface LibraryMemberRepository extends JpaRepository<LibraryMember, Long> {

    Optional<Object> findMemberByPhoneNumber(String phoneNumber);

    List<LibraryMember> findMemberById(int memberId);
}
