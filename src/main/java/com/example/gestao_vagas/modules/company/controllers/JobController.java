package com.example.gestao_vagas.modules.company.controllers;

import com.example.gestao_vagas.modules.company.dto.CreateJobDTO;
import com.example.gestao_vagas.modules.company.entities.JobEntity;
import com.example.gestao_vagas.modules.company.useCases.CreateJobUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/company/job")
public class JobController {
    @Autowired
    private CreateJobUseCase createJobUseCase;

    @PostMapping
    @PreAuthorize("hasRole('COMPANY')")
    @Operation(summary = "Cadastro de vaga", description = "Essa função é responsável por cadastrar as vagas dentro da empresa")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = JobEntity.class))
            })
    })
    @SecurityRequirement(name = "jwt_auth")
    ResponseEntity<Object> create(
            @Valid
            @RequestBody
            CreateJobDTO createJobDTO,
            HttpServletRequest request
    ) {
        var companyId = request.getAttribute("company_id");

        try {
            var jobEntity = JobEntity.builder()
                    .benefits(createJobDTO.getBenefits())
                    .description(createJobDTO.getDescription())
                    .level(createJobDTO.getLevel())
                    .companyId(UUID.fromString(companyId.toString()))
                    .build();

            var result = this.createJobUseCase.execute(jobEntity);

            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
