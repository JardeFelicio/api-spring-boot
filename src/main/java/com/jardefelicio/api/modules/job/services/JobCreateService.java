package com.jardefelicio.api.modules.job.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jardefelicio.api.modules.job.entity.JobEntity;
import com.jardefelicio.api.modules.job.repository.JobRepository;

@Service
public class JobCreateService {
    @Autowired
    JobRepository repository;

    public JobEntity execute(JobEntity data) {
        return this.repository.save(data);
    }
}
