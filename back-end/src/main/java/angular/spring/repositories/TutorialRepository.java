package angular.spring.repositories;

import angular.spring.entities.TutorialEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TutorialRepository extends JpaRepository<TutorialEntity, Long> {
    List<TutorialEntity> findByPublished(boolean published);

    List<TutorialEntity> findByTitleContaining(String title);
}
