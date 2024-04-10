package com.postgresql.postgresql.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.postgresql.postgresql.model.Tutorial;

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
	
	/**
	 * @param boolean published (true or false)
	 * 
	 *@return all tutorials with published having value as input pblished. 
	 **/
	List<Tutorial> findByPublished(boolean published);
	
	
	/**
	 * @param String tutorial's tutorial
	 * 
	 * @return all tutorials which title contains input title.
	 * */
	List<Tutorial> findByTitleContaining(String title);
	
}
