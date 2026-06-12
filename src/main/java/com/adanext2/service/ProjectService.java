package com.adanext2.service;

import com.adanext2.dto.request.CreateProjectRequest;
import com.adanext2.dto.response.OrganizationResponse;
import com.adanext2.exception.ResourceNotFoundException;
import com.adanext2.model.Organization;
import com.adanext2.model.Project;
import com.adanext2.repository.OrganizationRepository;
import com.adanext2.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final OrganizationRepository organizationRepository;

    public ProjectService(ProjectRepository projectRepository,
                          OrganizationRepository organizationRepository) {
        this.projectRepository = projectRepository;
        this.organizationRepository = organizationRepository;
    }

    public OrganizationResponse create(CreateProjectRequest request) {
        Organization org = organizationRepository.findById(request.getOrganizationId())
            .orElseThrow(() -> new ResourceNotFoundException("Organisation introuvable : " + request.getOrganizationId()));

        Project project = new Project();
        project.setName(request.getName());
        project.setDescription(request.getDescription());
        project.setOrganization(org);
        projectRepository.save(project);
        return OrganizationResponse.from(org);
    }

    public List<OrganizationResponse> getByOrganization(Long organizationId) {
        return projectRepository.findByOrganizationId(organizationId)
            .stream()
            .map(p -> OrganizationResponse.from(p.getOrganization()))
            .collect(Collectors.toList());
    }
}
