package com.example.gestao_vagas.modules.candidate.useCases;

import com.example.gestao_vagas.exceptions.JobNotFoundException;
import com.example.gestao_vagas.exceptions.UserNotFoundException;
import com.example.gestao_vagas.modules.candidate.entities.ApplyJobEntity;
import com.example.gestao_vagas.modules.candidate.repositories.ApplyJobRepository;
import com.example.gestao_vagas.modules.candidate.repositories.CandidateRepository;
import com.example.gestao_vagas.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ApplyJobCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ApplyJobRepository applyJobRepository;

    public ApplyJobEntity execute(UUID idCandidate, UUID idJob) {
        this.candidateRepository.findById(idCandidate)
                .orElseThrow(UserNotFoundException::new);

        this.jobRepository.findById(idJob)
                .orElseThrow(JobNotFoundException::new);

        var applyJob = ApplyJobEntity.builder()
                .candidateId(idCandidate)
                .jobId(idJob)
                .build();

        applyJob = applyJobRepository.save(applyJob);
        return applyJob;
    }
}
