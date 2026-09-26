package org.ercsn.proposalmanagement.auth.infrastructure.http;

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
    String hello(@AuthenticationPrincipal UserDetails user) {
        return "Hello, " + user.getUsername();
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
