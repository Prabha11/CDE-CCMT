package com.sliit.cde.coremodule.repository;

import com.sliit.cde.coremodule.model.Analysis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalysisRepo extends JpaRepository<Analysis, String> {
}
