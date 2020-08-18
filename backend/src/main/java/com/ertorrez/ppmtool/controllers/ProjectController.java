package com.ertorrez.ppmtool.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ertorrez.ppmtool.models.Project;
import com.ertorrez.ppmtool.services.MapValidationErrorService;
import com.ertorrez.ppmtool.services.ProjectService;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

	@Autowired
	private ProjectService _pS;
	
	@Autowired
	private MapValidationErrorService _vS;
	
	@PostMapping("")
	public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result){
		
		ResponseEntity<?> errorMap = _vS.MapValidationService(result);
		
		if(errorMap != null) return errorMap;
		
		Project _project = _pS.saveOrUpdateProject(project);
		return new ResponseEntity<Project>(_project, HttpStatus.CREATED);
	}
	
}
