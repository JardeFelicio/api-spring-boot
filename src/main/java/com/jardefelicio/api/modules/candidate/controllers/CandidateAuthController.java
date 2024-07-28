package com.jardefelicio.api.modules.candidate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jardefelicio.api.modules.candidate.dtos.AuthCandidateRequestDTO;
import com.jardefelicio.api.modules.candidate.services.CandidateAuthService;

@RestController
@RequestMapping("/candidate")
public class CandidateAuthController {

    @Autowired
    private CandidateAuthService candidateAuthService;

    @PostMapping("/auth")
    public ResponseEntity<Object> auth(@RequestBody AuthCandidateRequestDTO data) {
        try {

            var result = this.candidateAuthService.execute(data);
            return ResponseEntity.ok().body(result);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
