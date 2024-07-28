package com.jardefelicio.api.modules.candidate.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jardefelicio.api.modules.candidate.entity.CandidateEntity;

public interface CandidateRepository extends JpaRepository<CandidateEntity,UUID> {
    Optional<CandidateEntity> findByUsernameOrEmail(String username, String email);
    Optional<CandidateEntity> findByUsername(String username);
}
