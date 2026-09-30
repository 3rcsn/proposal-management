package org.ercsn.proposalmanagement.proposal.application.input;

import org.ercsn.proposalmanagement.proposal.domain.Owner;
import org.ercsn.proposalmanagement.proposal.domain.Proposal;

import java.util.Optional;

public record CreateProposalInput (String name, Optional<String> description){
    public Proposal toDomain(Owner owner) {
        return new Proposal(name, description, owner);
    }
}
