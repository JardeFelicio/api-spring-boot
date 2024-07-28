package com.jardefelicio.api.modules.candidate.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.jardefelicio.api.modules.candidate.dtos.CandidateReponseDTO;
import com.jardefelicio.api.modules.candidate.repository.CandidateRepository;

public class CandidateFindByIdService {

    @Autowired
    private CandidateRepository repository;

    public CandidateReponseDTO execute(UUID candidateId) {
        var candidate = this.repository.findById(candidateId).orElseThrow(() -> {
            throw new UsernameNotFoundException("User not found");
        });

        var candidateReponse = CandidateReponseDTO.builder()
                .id(candidate.getId())
                .name(candidate.getName())
                .username(candidate.getUsername())
                .email(candidate.getEmail())
                .description(candidate.getDescription())
                .build();

        return candidateReponse;
    }
}
