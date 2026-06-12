package com.adanext2.service;

import com.adanext2.dto.request.CreateOrganizationRequest;
import com.adanext2.dto.response.OrganizationResponse;
import com.adanext2.exception.ResourceNotFoundException;
import com.adanext2.model.Organization;
import com.adanext2.repository.OrganizationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrganizationService {

    private final OrganizationRepository organizationRepository;

    public OrganizationService(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    public OrganizationResponse create(CreateOrganizationRequest request) {
        Organization org = new Organization();
        org.setName(request.getName());
        org.setDescription(request.getDescription());
        organizationRepository.save(org);
        return OrganizationResponse.from(org);
    }

    public OrganizationResponse getById(Long id) {
        Organization org = organizationRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Organisation introuvable : " + id));
        return OrganizationResponse.from(org);
    }

    public List<OrganizationResponse> getAll() {
        return organizationRepository.findAll()
            .stream()
            .map(OrganizationResponse::from)
            .collect(Collectors.toList());
    }
}