package org.ercsn.proposalmanagement.auth.infrastructure.http;

import org.ercsn.proposalmanagement.auth.infrastructure.persistence.entity.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class Controller {

    @GetMapping
    String hello(@AuthenticationPrincipal User user) {
        return "Hello, " + user.getId();
    }

    @GetMapping("/influencer")
    @PreAuthorize("hasRole('INFLUENCER')")
    public String userEndpoint() {
        return "You are an influencer";
    }

    @GetMapping("/brand")
    @PreAuthorize("hasRole('BRAND')")
    public String adminEndpoint() {
        return "You are a brand";
    }
}
