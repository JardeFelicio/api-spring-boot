package com.jardefelicio.api.modules.company.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jardefelicio.api.excepetions.UserFoundException;
import com.jardefelicio.api.modules.company.CompanyEntity;
import com.jardefelicio.api.modules.company.repository.CompanyRepository;

@Service
public class CompanyCreateService {

    @Autowired
    CompanyRepository repository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public CompanyEntity execute(CompanyEntity data) {
        this.repository.findByUsernameOrEmail(data.getUsername(), data.getEmail()).ifPresent((company) -> {
            throw new UserFoundException();
        });

        var password = passwordEncoder.encode(data.getPassword());
        data.setPassword(password);

        return this.repository.save(data);
    }
}
