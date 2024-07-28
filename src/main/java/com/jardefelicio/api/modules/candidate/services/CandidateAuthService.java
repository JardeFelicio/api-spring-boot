package com.jardefelicio.api.modules.candidate.services;

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
import com.jardefelicio.api.modules.candidate.dtos.AuthCandidateRequestDTO;
import com.jardefelicio.api.modules.candidate.dtos.AuthCandidateResponseDTO;
import com.jardefelicio.api.modules.candidate.repository.CandidateRepository;

@Service
public class CandidateAuthService {

    @Value("${security.token.candidate}")
    private String secretKey;

    @Autowired
    private CandidateRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthCandidateResponseDTO execute(AuthCandidateRequestDTO data) throws AuthenticationException {
        var candidate = this.repository.findByUsername(data.username()).orElseThrow(() -> {
            throw new UsernameNotFoundException("Username/password incorrect");
        });

        var passwordMatches = this.passwordEncoder.matches(data.password(), candidate.getPassword());

        if (!passwordMatches) {
            throw new AuthenticationException();
        }

        var expiresIn = Instant.now().plus(Duration.ofDays(1));

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var token = JWT.create()
                .withIssuer("javagas")
                .withExpiresAt(expiresIn)
                .withClaim("roles", Arrays.asList("CANDIDATE"))
                .withSubject(candidate.getId().toString())
                .sign(algorithm);

        var cadidateAuthReponse = AuthCandidateResponseDTO.builder()
                .token(token)
                .expiresIn(expiresIn.toEpochMilli())
                .build();

        return cadidateAuthReponse;
    }
}
