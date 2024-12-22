package com.example.gestao_vagas.modules.company.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

@Entity(name = "company")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "O campo [name] não pode ser vazio")
    private String name;

    @NotBlank(message = "O campo [username] não pode ser vazio")
    @Pattern(regexp = "\\S+", message = "O campo [username] não deve conter espaços")
    private String username;

    @NotBlank(message = "O campo [email] não pode ser vazio")
    @Email(message = "O campo [email] deve contar um e-mail válido")
    private String email;

    @NotBlank(message = "O campo [password] não pode ser vazio")
    @Length(min = 10, max = 100, message = "A senha deve conter entre (10) e (100) caracteres")
    private String password;

    private String website;

    private String description;
}
