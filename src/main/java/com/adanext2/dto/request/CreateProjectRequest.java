package com.adanext2.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateProjectRequest {

    @NotBlank(message = "Le nom est obligatoire")
    private String name;

    private String description;

    @NotNull(message = "L'organisation est obligatoire")
    private Long organizationId;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Long getOrganizationId() { return organizationId; }
    public void setOrganizationId(Long organizationId) { this.organizationId = organizationId; }
}