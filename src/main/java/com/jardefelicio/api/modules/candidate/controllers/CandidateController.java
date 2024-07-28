package com.jardefelicio.api.modules.candidate.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jardefelicio.api.modules.candidate.entity.CandidateEntity;
import com.jardefelicio.api.modules.candidate.services.CandidateCreateService;
import com.jardefelicio.api.modules.candidate.services.CandidateFindByIdService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidate")
@PreAuthorize("hasRole('CANDIDATE')")
public class CandidateController {

    @Autowired
    private CandidateCreateService userCreateService;
    @Autowired
    private CandidateFindByIdService candidateFindByIdService;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidateEntity) {
        try {
            var result = userCreateService.execute(candidateEntity);

            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping("/")
    public ResponseEntity<Object> getById(HttpServletRequest req) {
        var candidateId = req.getAttribute("candidate_id");
        try {
            var result = this.candidateFindByIdService.execute(
                    UUID.fromString(
                            candidateId.toString()
                    ));
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }
}
