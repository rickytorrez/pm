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
		try {
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			return _pR.save(project);
		} catch (Exception e) {
			throw new ProjectIdException("Project Id '" + project.getProjectIdentifier().toUpperCase() + "' already exists");
		}
	}
}
