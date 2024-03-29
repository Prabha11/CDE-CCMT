/**
 *
 */
package com.sliit.cde.coremodule.service.impl;

import com.sliit.cde.coremodule.model.Analysis;
import com.sliit.cde.coremodule.model.Project;
import com.sliit.cde.coremodule.service.ProjectService;
import com.sliit.cde.coremodule.repository.AnalysisRepo;
import com.sliit.cde.coremodule.repository.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    ProjectRepo projectRepo;

    @Autowired
    AnalysisRepo analysisRepo;

    @Override
    public Project save(Project project) {

        Analysis analysis = new Analysis();
        analysis.setCreatedTime(Instant.now().getEpochSecond());
        analysis.setProject(project);
        analysis.setProjectKey(project.getProjectKey());
        analysisRepo.save(analysis);

        return projectRepo.save(project);
    }

    @Override
    public Optional<Project> getByKey(String projectKey) {
        return projectRepo.findByKey(projectKey);
    }

    @Override
    public List<Project> getAll() {
        return projectRepo.findAll();
    }



    @Override
    public List<Analysis> getHistoryByKey(String key) {
//        return analysisRepo.findFirst10ByOrderByCreatedTimeDesc(key);
        List<Analysis> li = analysisRepo.findAll();
        if(li.size() > 0) {
        	List<Analysis> al = new ArrayList<>();
        	for(Analysis a : li) {
        		if(a.getProjectKey().equals(key)) {
        			al.add(a);
        		}
        	}
        	return al;
        }
        return null;
    }
}
