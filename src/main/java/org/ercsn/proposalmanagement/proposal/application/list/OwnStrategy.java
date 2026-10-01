package org.ercsn.proposalmanagement.proposal.application.list;

import org.ercsn.proposalmanagement.proposal.domain.OwnerId;
import org.ercsn.proposalmanagement.proposal.domain.Proposal;
import org.ercsn.proposalmanagement.proposal.domain.ProposalRepository;

import java.util.List;

public class OwnStrategy implements Strategy {
    private final ProposalRepository proposalRepository;

    public OwnStrategy(ProposalRepository proposalRepository) {
        this.proposalRepository = proposalRepository;
    }

    @Override
    public List<Proposal> getProposals(OwnerId ownerId) {
        return proposalRepository.findAllByOwnerId(ownerId);
    }

    @Override
    public AccessScope getScope() {
        return AccessScope.OWN;
    }
}
