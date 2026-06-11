package com.adanext2.dto.response;

import com.adanext2.model.User;

public class UserResponse {

    private Long id;
    private String name;
    private String email;

    public static UserResponse from(User user) {
        UserResponse response = new UserResponse();
        response.id = user.getId();
        response.name = user.getName();
        response.email = user.getEmail();
        return response;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
}
