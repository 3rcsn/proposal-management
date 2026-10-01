package org.ercsn.proposalmanagement.proposal.application;

import org.ercsn.proposalmanagement.proposal.application.list.AccessScope;
import org.ercsn.proposalmanagement.proposal.application.list.Factory;
import org.ercsn.proposalmanagement.proposal.application.output.ProposalOutput;
import org.ercsn.proposalmanagement.proposal.domain.OwnerId;

import java.util.List;

public class ListProposalUseCase {
    private final Factory factory;

    public ListProposalUseCase(Factory factory) {
        this.factory = factory;
    }

    public List<ProposalOutput> execute(AccessScope scope, OwnerId ownerId) {
        var proposals = factory.getStrategy(scope).getProposals(ownerId);

        return proposals.stream().map(ProposalOutput::from).toList();
    }
}
