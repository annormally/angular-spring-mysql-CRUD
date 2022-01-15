package angular.spring.controllers;

import angular.spring.entities.TutorialEntity;
import angular.spring.repositories.TutorialRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class TutorialController {

    final
    TutorialRepository tutorialRepository;

    public TutorialController(TutorialRepository tutorialRepository) {
        this.tutorialRepository = tutorialRepository;
    }

    @GetMapping("/tutorials")
    public ResponseEntity<List<TutorialEntity>> getAllTutorials(@RequestParam(required = false) String title) {
        try {
            List<TutorialEntity> tutorials = new ArrayList<>();

            if (title == null) {
                tutorials.addAll(tutorialRepository.findAll());
            } else {
                tutorials.addAll(tutorialRepository.findByTitleContaining(title));
            }

            if (tutorials.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(tutorials, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tutorials/{id}")
    public ResponseEntity<TutorialEntity> getTutorialById(@PathVariable("id") long id) {
        Optional<TutorialEntity> tutorialData = tutorialRepository.findById(id);

        return tutorialData.map(tutorialEntity -> new ResponseEntity<>(tutorialEntity, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/tutorials")
    public ResponseEntity<TutorialEntity> createTutorial(@RequestBody TutorialEntity tutorial) {
        try {
            TutorialEntity _tutorialEntity = tutorialRepository.save(new TutorialEntity(tutorial.getTitle(), tutorial.getDescription(), false));
            return new ResponseEntity<>(_tutorialEntity, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/tutorials/{id}")
    public ResponseEntity<TutorialEntity> updateTutorial(@PathVariable("id") long id, @RequestBody TutorialEntity tutorial) {
        Optional<TutorialEntity> tutorialData = tutorialRepository.findById(id);

        if (tutorialData.isPresent()) {
            TutorialEntity _tutorial = tutorialData.get();
            _tutorial.setTitle(tutorial.getTitle());
            _tutorial.setDescription(tutorial.getDescription());
            _tutorial.setPublished(tutorial.isPublished());
            return new ResponseEntity<>(tutorialRepository.save(_tutorial), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/tutorials/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
        try {
            tutorialRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/tutorials")
    public ResponseEntity<HttpStatus> deleteAllTutorials() {
        try {
            tutorialRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tutorials/published")
    public ResponseEntity<List<TutorialEntity>> findByPublished() {
        try {
            List<TutorialEntity> tutorialEntities = tutorialRepository.findByPublished(true);

            if (tutorialEntities.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(tutorialEntities, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
