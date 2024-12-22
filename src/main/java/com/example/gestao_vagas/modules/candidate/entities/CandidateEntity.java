package com.example.gestao_vagas.modules.candidate.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "candidate")
public class CandidateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "O campo [name] não pode ser vazio")
    @Schema(example = "Daniel de Souza", requiredMode = RequiredMode.REQUIRED, description = "Nome do candidato")
    private String name;

    @NotBlank(message = "O campo [username] não pode ser vazio")
    @Pattern(regexp = "\\S+", message = "O campo [username] não deve conter espaços")
    @Schema(example = "daniel", requiredMode = RequiredMode.REQUIRED, description = "Username do candidato")
    private String username;

    @NotBlank(message = "O campo [email] não pode ser vazio")
    @Email(message = "O campo [email] deve contar um e-mail válido")
    @Schema(example = "daniel@gmail.com", requiredMode = RequiredMode.REQUIRED, description = "E-mail do candidato")
    private String email;

    @NotBlank(message = "O campo [password] não pode ser vazio")
    @Length(min = 10, max = 100, message = "A senha deve conter entre (10) e (100) caracteres")
    @Schema(example = "admin@1234", minLength = 10, maxLength = 100, requiredMode = RequiredMode.REQUIRED, description = "Senha do candidato")
    private String password;

    @Schema(example = "Desenvolvedor Java", requiredMode = RequiredMode.REQUIRED, description = "Breve descrição do candidato")
    private String description;

    private String curriculum;

    @CreationTimestamp
    private LocalDateTime createdAt;
}