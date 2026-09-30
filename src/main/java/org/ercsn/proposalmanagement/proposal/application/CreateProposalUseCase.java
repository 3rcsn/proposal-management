package org.ercsn.proposalmanagement.proposal.application;

import org.ercsn.proposalmanagement.proposal.application.input.CreateProposalInput;
import org.ercsn.proposalmanagement.proposal.application.output.ProposalOutput;
import org.ercsn.proposalmanagement.proposal.domain.Owner;
import org.ercsn.proposalmanagement.proposal.domain.Proposal;
import org.ercsn.proposalmanagement.proposal.domain.ProposalRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateProposalUseCase {
    private final ProposalRepository proposalRepository;

    public CreateProposalUseCase(ProposalRepository proposalRepository) {
        this.proposalRepository = proposalRepository;
    }

    public ProposalOutput execute(CreateProposalInput input, Owner owner) {
        var proposal = input.toDomain(owner);
        var saved = proposalRepository.save(proposal);

        return ProposalOutput.from(saved);
    }
}
