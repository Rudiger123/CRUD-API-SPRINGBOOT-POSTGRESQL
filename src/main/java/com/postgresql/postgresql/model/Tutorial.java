package com.postgresql.postgresql.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity //annotation indicates that the class is a persistent java class
@Table(name = "Tutorials") // annotation provides the table that maps this map..
public class Tutorial {

	@Id // annotation is for the primary key
	@GeneratedValue(strategy = GenerationType.AUTO) //annotation is used to define generation strategy for the primary key and Generation.AUTO  means auto increment field...
	private Long id;
	
	@Column(name = "title") //annotation is used to define the column in database that maps annotation field.
	private String title;
	
	@Column(name = "description")
	private String description;
	
	@Column(name="published")
	private boolean published;
	
	
	/**
	 * default constructor
	 * 
	 */
	public Tutorial(){
		
	}
	
	public Tutorial(String title, String description, boolean published)
	{
		this.title = title;
		
		this.description = description;
		
		this.published = published;
	}
	
	
	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}
	
	@Override
	public String toString()
	{
		return "Tutorial(id = "+ id + ", title = "+ title + "description = "+ description + " published = " + published + " )";   
	}
		

}
