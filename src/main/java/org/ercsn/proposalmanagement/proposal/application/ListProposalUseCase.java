package org.ercsn.proposalmanagement.proposal.application;

import org.ercsn.proposalmanagement.proposal.application.list.AccessScope;
import org.ercsn.proposalmanagement.proposal.application.list.Factory;
import org.ercsn.proposalmanagement.proposal.domain.OwnerId;

public class ListProposalUseCase {
    private final Factory factory;

    public ListProposalUseCase(Factory factory) {
        this.factory = factory;
    }

    public void execute(AccessScope scope, OwnerId ownerId) {
        factory.getStrategy(scope).getProposals(ownerId);
    }
}
