package bg.softuni.playground.repository;

import bg.softuni.playground.entity.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LabelRepository extends JpaRepository<Label, Long> {

    Optional<Label> findByTitle(String title);

    List<Label> findAllBySubTitleOrderByIdDesc(String subTitle);

    @Query(value = "select l from Label l where l.subTitle = :subTitle order by l.id desc")
    List<Label> findAllBySubTitle(@Param("subTitle") String subTitle);
}
