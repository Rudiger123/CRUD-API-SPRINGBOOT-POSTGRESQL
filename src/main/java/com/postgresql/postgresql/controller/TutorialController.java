package com.postgresql.postgresql.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.postgresql.postgresql.model.Tutorial;
import com.postgresql.postgresql.repository.TutorialRepository;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class TutorialController {

	@Autowired
	TutorialRepository tutorialRepository;
	
	//all tutorials....
	
	@GetMapping("/tutorials")
	public ResponseEntity<List<Tutorial>> getAllTutorials(@RequestParam(required = false) String title)
	{
		try {
			
			List<Tutorial> tutorials = new ArrayList<Tutorial>();
			
			if (title == null) {
				
				tutorialRepository.findAll().forEach(tutorials::add);
				
			} else {
				
				tutorialRepository.findByTitleContaining(title).forEach(tutorials::add);

			}
			
			if (tutorials.isEmpty()) {
				
				return new ResponseEntity<List<Tutorial>>(HttpStatus.NO_CONTENT);
				
			}
			
			return new ResponseEntity<List<Tutorial>>(tutorials, HttpStatus.OK);
			
		} catch (Exception e) {

			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	// show tutorial
	@GetMapping("/tutorials/{id}")
	public ResponseEntity<Tutorial> getTutorialById (@PathVariable("id") Long id) {
		
		Optional<Tutorial> tutorialData = tutorialRepository.findById(id);
		
		if (tutorialData.isPresent()) {
			
			return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
		
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	//create tutorial
	@PostMapping("/tutorials")
	public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {
		
		try {
			
			Tutorial tutorial1 = tutorialRepository.save(new Tutorial(tutorial.getTitle(),tutorial.getDescription(),false));
			
			return new ResponseEntity<>(tutorial1, HttpStatus.CREATED);
			
		} catch (Exception e) {
			
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
	}
	
	
	//updating tutorial
	@PutMapping("/tutorials/{id}")
	public ResponseEntity<Tutorial> updateTutorial(@PathVariable("id") Long id, @RequestBody Tutorial tutorial) {
		
		Optional<Tutorial> tutorialData = tutorialRepository.findById(id);
		
		if (tutorialData.isPresent()) {
			Tutorial tutorial2 = tutorialData.get();
			tutorial2.setTitle(tutorial.getTitle());
			tutorial2.setDescription(tutorial.getDescription());
			tutorial2.setPublished(tutorial.isPublished());
			
			return new ResponseEntity<>(tutorialRepository.save(tutorial2),HttpStatus.OK);
			
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
			
	
	//Deleting tutorial 
	@DeleteMapping("/tutorials/{id}")
	public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") Long id) {
		
		try {
			
			tutorialRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			
		}catch (Exception e) {
			
			return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	//publishing tutorials
	@GetMapping("/tutorials/published")
	public ResponseEntity<List<Tutorial>> findByPublished() {
		
		try {
			
			List<Tutorial> tutorials = tutorialRepository.findByPublished(true);

			if (tutorials.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}else {
				return new ResponseEntity<>(tutorials, HttpStatus.OK);
			}
			
		} catch (Exception e) {
			
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
	}
	
	
}
