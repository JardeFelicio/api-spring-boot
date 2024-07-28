package com.jardefelicio.api.modules.job.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jardefelicio.api.modules.job.dtos.JobCreateDTO;
import com.jardefelicio.api.modules.job.entity.JobEntity;
import com.jardefelicio.api.modules.job.services.JobCreateService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    JobCreateService jobCreateService;

    @PostMapping("/")
    @PreAuthorize("hasRole('COMPANY')")
    public ResponseEntity<Object> create(@Valid @RequestBody JobCreateDTO data, HttpServletRequest req) {
        try {
            var companyId = req.getAttribute("company_id");

            var jobEntity = JobEntity.builder()
                    .benefits(data.getBenefits())
                    .description(data.getDescription())
                    .level(data.getLevel())
                    .companyId(UUID.fromString(companyId.toString()))
                    .build();

            var result = this.jobCreateService.execute(jobEntity);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
