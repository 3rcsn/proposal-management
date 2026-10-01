package org.ercsn.proposalmanagement.proposal.infrastructure.persistence.repository;

import jdk.jfr.Registered;
import org.ercsn.proposalmanagement.proposal.domain.OwnerId;
import org.ercsn.proposalmanagement.proposal.domain.Proposal;
import org.ercsn.proposalmanagement.proposal.domain.ProposalRepository;
import org.ercsn.proposalmanagement.proposal.infrastructure.persistence.entity.ProposalEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.StreamSupport;

@Repository
public class JpaProposalRepository implements ProposalRepository {
    private final ProposalEntityRepository proposalEntityRepository;

    public JpaProposalRepository(ProposalEntityRepository proposalEntityRepository) {
        this.proposalEntityRepository = proposalEntityRepository;
    }

    @Override
    public List<Proposal> findAll() {
        var iterable = proposalEntityRepository.findAll();
        return StreamSupport
                .stream(iterable.spliterator(), false)
                .map(ProposalEntity::toDomain)
                .toList();
    }

    @Override
    public List<Proposal> findAllByOwnerId(OwnerId ownerId) {
        return List.of();
    }

    @Override
    public Proposal save(Proposal proposal) {
        return null;
    }
}
