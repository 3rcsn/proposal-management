package org.ercsn.proposalmanagement.proposal.infrastructure.http.request;

import org.ercsn.proposalmanagement.proposal.application.input.CreateProposalInput;

import java.util.Optional;

public record CreateProposalRequest(String title, Optional<String> description) {
    public CreateProposalInput toInput() {
        return new CreateProposalInput(title, description);
    }
}
