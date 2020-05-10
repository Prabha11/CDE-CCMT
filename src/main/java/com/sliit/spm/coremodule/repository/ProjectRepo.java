/**
 * 
 */
package com.sliit.spm.coremodule.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sliit.spm.coremodule.model.Project;

@Repository
public interface ProjectRepo extends MongoRepository<Project, String> {

	//public List<Project> findByOrderByCreatedTimeAsc();
}
