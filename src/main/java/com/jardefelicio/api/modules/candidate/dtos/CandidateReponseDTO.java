package com.jardefelicio.api.modules.candidate.dtos;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CandidateReponseDTO {

    private UUID id;
    private String name;
    private String username;
    private String email;
    private String description;
}
