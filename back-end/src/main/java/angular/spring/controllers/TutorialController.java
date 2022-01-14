package angular.spring.controllers;

import angular.spring.entities.TutorialEntity;
import angular.spring.repositories.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class TutorialController {

    @Autowired
    TutorialRepository tutorialRepository;

    @GetMapping("/tutorials")
    public ResponseEntity<List<TutorialEntity>> getAllTutorials(@RequestParam(required = false) String title) {
        return getAllTutorials(title);
    }

    @GetMapping("/tutorials/{id}")
    public ResponseEntity<TutorialEntity> getTutorialById(@PathVariable("id") long id) {
        return getTutorialById(id);
    }

    @PostMapping("/tutorials")
    public ResponseEntity<TutorialEntity> createTutorial(@RequestBody TutorialEntity tutorial) {
        return createTutorial(tutorial);
    }

    @PutMapping("/tutorials/{id}")
    public ResponseEntity<TutorialEntity> updateTutorial(@PathVariable("id") long id, @RequestBody TutorialEntity tutorial) {
        return updateTutorial(id, tutorial);
    }

    @DeleteMapping("/tutorials/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
        return deleteTutorial(id);
    }

    @DeleteMapping("/tutorials")
    public ResponseEntity<HttpStatus> deleteAllTutorials() {
        return deleteAllTutorials();
    }

    @GetMapping("/tutorials/published")
    public ResponseEntity<List<TutorialEntity>> findByPublished() {
        return findByPublished();
    }
}
