/**
 * 
 */
package com.sliit.spm.coremodule.controller;

import com.sliit.spm.coremodule.model.Project;
import com.sliit.spm.coremodule.service.ProjectService;
import com.sliit.spm.scannermodule.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * @author it16166752
 *
 */
@CrossOrigin(origins = "*")
@RestController
public class ProjectController {
	@Autowired
	ProjectService projectService;

	@PostMapping("/projects")
	public ResponseEntity<?> saveProject(@RequestBody Project project) {
		return new ResponseEntity<>(projectService.save(project), HttpStatus.OK);
	}

	@GetMapping("/projects/{key}")
	public ResponseEntity<?> getProject(@PathVariable("key") String key) {
		return new ResponseEntity<>(projectService.getByKey(key), HttpStatus.OK);
	}

	@GetMapping("/projects/{key}/history")
	public ResponseEntity<?> getProjectHistory(@PathVariable("key") String key) {
		return new ResponseEntity<>(projectService.getHistoryByKey(key), HttpStatus.OK);
	}

	@GetMapping("/projects")
	public ResponseEntity<?> getProjects() {
		return new ResponseEntity<>(projectService.getAll(), HttpStatus.OK);
	}

	@GetMapping("/scan")
	public boolean scanProject(@RequestParam("project-key") String projectKey,
							   @RequestParam("source-path") String sourcePath) {
		try {
			Scanner.startScanJob(projectKey, sourcePath);
		} catch (Exception o) {
			return false;
		}
		return true;
	}
}
