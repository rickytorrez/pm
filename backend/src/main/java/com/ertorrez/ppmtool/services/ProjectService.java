package com.ertorrez.ppmtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ertorrez.ppmtool.exceptions.ProjectIdException;
import com.ertorrez.ppmtool.models.Project;
import com.ertorrez.ppmtool.repositories.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository _pR;
	
	public Project saveOrUpdateProject(Project project) {
		// @todo - auth checks
		
		// try catch block
			// sets projectIdentifier to upperCase
			// save the or update the project into database
		try {
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			return _pR.save(project);
		} catch (Exception e) {
		// catch the errors and send a message with the help of our custom error exception handler	
			throw new ProjectIdException("Project Id '" + project.getProjectIdentifier().toUpperCase() + "' already exists");
		}
	}
	
	public Project findProjectByIdentifier(String projectId) {
		Project project = _pR.findByProjectIdentifier(projectId.toUpperCase());
		if(project == null) {
			throw new ProjectIdException("Project Id '" +projectId+ "' does not exists");
		}
		return project;
	}
	
	public Iterable<Project> findAllProjects(){
		return _pR.findAll();
	}
}
