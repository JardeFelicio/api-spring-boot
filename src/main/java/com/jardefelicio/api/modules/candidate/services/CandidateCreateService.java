package com.jardefelicio.api.modules.candidate.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jardefelicio.api.excepetions.UserFoundException;
import com.jardefelicio.api.modules.candidate.entity.CandidateEntity;
import com.jardefelicio.api.modules.candidate.repository.CandidateRepository;

@Service
public class CandidateCreateService {
    @Autowired
    private CandidateRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CandidateEntity execute(CandidateEntity data) {
        this.repository.findByUsernameOrEmail(data.getUsername(), data.getEmail())
                .ifPresent((user) -> {
                    throw new UserFoundException();
                });
        var password = passwordEncoder.encode(data.getPassword());
        data.setPassword(password);
        return repository.save(data);
    }
}
