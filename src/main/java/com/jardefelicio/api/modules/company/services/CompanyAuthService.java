package com.jardefelicio.api.modules.company.services;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.jardefelicio.api.modules.company.dtos.AuthCompanyDTO;
import com.jardefelicio.api.modules.company.dtos.AuthCompanyReponseDTO;
import com.jardefelicio.api.modules.company.repository.CompanyRepository;

@Service
public class CompanyAuthService {

    @Value("${security.token.secret.company}")
    private String secretKey;

    @Autowired
    private CompanyRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthCompanyReponseDTO execute(AuthCompanyDTO data) throws AuthenticationException {
        var company = this.repository.findByUsername(data.getUsername()).orElseThrow(
                () -> {
                    throw new UsernameNotFoundException("Company not found");
                });

        var passwordMatches = this.passwordEncoder.matches(data.getPassword(), company.getPassword());

        if (!passwordMatches) {
            throw new AuthenticationException();
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var expiresIn = Instant.now().plus(Duration.ofDays(1));
        var token = JWT.create()
                .withIssuer("javagas")
                .withExpiresAt(expiresIn)
                .withSubject(company.getId().toString())
                .withClaim("roles", Arrays.asList("COMPANY"))
                .sign(algorithm);

        var authCompany = AuthCompanyReponseDTO
                .builder()
                .token(token)
                .expiresIn(expiresIn.toEpochMilli())
                .build();

        return authCompany;
    }
}
