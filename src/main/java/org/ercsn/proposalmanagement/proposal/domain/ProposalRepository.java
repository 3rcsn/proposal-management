package org.ercsn.proposalmanagement.proposal.domain;

import java.util.List;

public interface ProposalRepository {
    List<Proposal> findAll();
    List<Proposal> findByOwner(OwnerId ownerId);
    Proposal save(Proposal proposal);
}
