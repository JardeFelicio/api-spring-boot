package com.jardefelicio.api.modules.company.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jardefelicio.api.modules.company.dtos.AuthCompanyDTO;
import com.jardefelicio.api.modules.company.services.CompanyAuthService;

@RestController
@RequestMapping("/auth")
public class AuhtCompanyController {

    @Autowired
    CompanyAuthService companyAuthService;

    @PostMapping("/")
    public ResponseEntity<Object> auth(@RequestBody AuthCompanyDTO data) {
        try {
            var result = this.companyAuthService.execute(data);

            return ResponseEntity.ok().body(result);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }   
}
