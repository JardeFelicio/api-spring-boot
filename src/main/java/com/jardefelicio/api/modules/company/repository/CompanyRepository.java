package com.jardefelicio.api.modules.company.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.jardefelicio.api.modules.company.CompanyEntity;

public interface CompanyRepository extends JpaRepository<CompanyEntity, UUID> {
    Optional<CompanyEntity> findByUsernameOrEmail(String username, String email);
    
    Optional<CompanyEntity> findByUsername(String username);

}
