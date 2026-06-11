package com.adanext2.dto.response;

import com.adanext2.model.Organization;

public class OrganizationResponse {

    private Long id;
    private String name;
    private String description;

    public static OrganizationResponse from(Organization org) {
        OrganizationResponse response = new OrganizationResponse();
        response.id = org.getId();
        response.name = org.getName();
        response.description = org.getDescription();
        return response;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
}
