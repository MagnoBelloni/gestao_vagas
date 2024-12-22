package com.example.gestao_vagas.modules.candidate.useCases;

import com.example.gestao_vagas.exceptions.JobNotFoundException;
import com.example.gestao_vagas.exceptions.UserNotFoundException;
import com.example.gestao_vagas.modules.candidate.entities.ApplyJobEntity;
import com.example.gestao_vagas.modules.candidate.entities.CandidateEntity;
import com.example.gestao_vagas.modules.candidate.repositories.ApplyJobRepository;
import com.example.gestao_vagas.modules.candidate.repositories.CandidateRepository;
import com.example.gestao_vagas.modules.company.entities.JobEntity;
import com.example.gestao_vagas.modules.company.repositories.JobRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ApplyJobCandidateUseCaseTest {

    @InjectMocks
    private ApplyJobCandidateUseCase useCase;

    @Mock
    private CandidateRepository candidateRepository;

    @Mock
    private JobRepository jobRepository;

    @Mock
    private ApplyJobRepository applyJobRepository;

    @Test
    @DisplayName("Should not be able to apply job when candidate not found")
    public void should_not_be_able_to_apply_job_when_candidate_not_found() {

        try {
            useCase.execute(null, null);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(UserNotFoundException.class);
        }
    }

    @Test
    @DisplayName("Should not be able to apply job when job not found")
    public void should_not_be_able_to_apply_job_when_job_not_found() {

        var candidateId = UUID.randomUUID();

        var candidate = new CandidateEntity();
        candidate.setId(candidateId);

        when(candidateRepository.findById(candidateId)).thenReturn(Optional.of(candidate));

        try {
            useCase.execute(candidateId, null);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(JobNotFoundException.class);
        }
    }

    @Test
    @DisplayName("Should be able to apply for a job")
    public void should_be_able_to_apply_for_a_job() {

        when(candidateRepository.findById(Mockito.any(UUID.class)))
                .thenReturn(Optional.of(new CandidateEntity()));

        when(jobRepository.findById(Mockito.any(UUID.class)))
                .thenReturn(Optional.of(new JobEntity()));

        var applyJobId = UUID.randomUUID();
        var applyJobCreated = ApplyJobEntity
                .builder()
                .id(applyJobId)
                .build();

        when(applyJobRepository.save(Mockito.any(ApplyJobEntity.class))).thenReturn(applyJobCreated);

        var result = useCase.execute(UUID.randomUUID(), UUID.randomUUID());

        assertThat(result).hasFieldOrPropertyWithValue("id", applyJobId);
    }
}
