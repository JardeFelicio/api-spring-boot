package com.jardefelicio.api.modules.job.dtos;

import lombok.Data;

@Data
public class JobCreateDTO {
    private String description;
    private String benefits;
    private String level;
}
