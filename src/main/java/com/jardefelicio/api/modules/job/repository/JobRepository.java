package com.jardefelicio.api.modules.job.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jardefelicio.api.modules.job.entity.JobEntity;

public interface JobRepository extends JpaRepository<JobEntity, UUID> {

}
