package com.example.gestao_vagas.modules.candidate.useCases;

import com.example.gestao_vagas.exceptions.UserNotFoundException;
import com.example.gestao_vagas.modules.candidate.repositories.CandidateRepository;
import com.example.gestao_vagas.modules.candidate.dto.ProfileCandidateResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProfileCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    public ProfileCandidateResponseDTO execute(UUID idCandidate) {
        var candidate = this.candidateRepository.findById(idCandidate)
                .orElseThrow(UserNotFoundException::new);

        return ProfileCandidateResponseDTO.builder()
                .email(candidate.getEmail())
                .id(candidate.getId())
                .name(candidate.getName())
                .username(candidate.getUsername())
                .description(candidate.getDescription())
                .build();
    }
}
