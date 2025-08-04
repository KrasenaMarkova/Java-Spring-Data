package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Attraction;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface AttractionRepository extends JpaRepository<Attraction, Long> {
    Optional<Attraction> findByName(String name);

    @Query(value = "select a from Attraction a where 1=1 " +
            "and (a.type = 'historical site' or a.type = 'archaeological site') " +
            "and a.elevation >= 300 " +
            "order by a.name, a.country.name")
    Set<Attraction> findAllAttractions();

    @Query(value = "select a from Attraction a where 1=1 " +
            "and (a.type = 'historical site' or a.type = 'archaeological site') " +
            "and a.elevation >= 300 " +
            "order by a.name, a.country.name")
    List<Attraction> findAllAttractionss();
}
