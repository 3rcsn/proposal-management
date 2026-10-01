package org.ercsn.proposalmanagement.proposal.infrastructure.http;

import org.ercsn.proposalmanagement.auth.infrastructure.persistence.entity.User;
import org.ercsn.proposalmanagement.proposal.application.CreateProposalUseCase;
import org.ercsn.proposalmanagement.proposal.application.ListProposalsUseCase;
import org.ercsn.proposalmanagement.proposal.application.output.ProposalOutput;
import org.ercsn.proposalmanagement.proposal.domain.Owner;
import org.ercsn.proposalmanagement.proposal.domain.OwnerId;
import org.ercsn.proposalmanagement.proposal.infrastructure.http.request.CreateProposalRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/proposals")
public class ProposalController {
    private final CreateProposalUseCase createProposalUseCase;
    private final ListProposalsUseCase listProposalsUseCase;


    public ProposalController(CreateProposalUseCase createProposalUseCase,
                              ListProposalsUseCase listProposalsUseCase) {
        this.createProposalUseCase = createProposalUseCase;
        this.listProposalsUseCase = listProposalsUseCase;
    }

    @PostMapping
    @PreAuthorize("hasRole('INFLUENCER')")
    public ResponseEntity<ProposalOutput> createProposal(@RequestBody CreateProposalRequest request,
                                                         @AuthenticationPrincipal User user) {
        var owner = new Owner(new OwnerId(user.getId()), user.getUsername());
        var output = this.createProposalUseCase.execute(request.toInput(), owner);
        return ResponseEntity.ok(output);
    }
}
