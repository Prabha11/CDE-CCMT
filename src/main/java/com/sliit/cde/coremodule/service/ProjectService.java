/**
 *
 */
package com.sliit.cde.coremodule.service;

import com.sliit.cde.coremodule.model.Analysis;
import com.sliit.cde.coremodule.model.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectService {
	public Project save(Project project);

	public Optional<Project> getByKey(String projectKey);

	public List<Project> getAll();

	public List<Analysis> getHistoryByKey(String key);
}
