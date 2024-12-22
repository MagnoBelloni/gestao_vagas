package com.example.gestao_vagas.modules.company.useCases;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.gestao_vagas.modules.company.dto.AuthCompanyDto;
import com.example.gestao_vagas.modules.company.dto.AuthCompanyResponseDTO;
import com.example.gestao_vagas.modules.company.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Service
public class AuthCompanyUseCase {

    @Value("${security.token.secret}")
    private String secretKey;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthCompanyResponseDTO execute(AuthCompanyDto authCompanyDto) throws UsernameNotFoundException, AuthenticationException {
        var company = this.companyRepository.findByUsername(authCompanyDto.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Username/password incorrect"));

        var passwordMatches = this.passwordEncoder.matches(authCompanyDto.getPassword(), company.getPassword());

        if (!passwordMatches) {
            throw new AuthenticationException();
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        var expiresIn = Instant.now().plus(Duration.ofHours(2));

        var token = JWT.create()
                .withIssuer("javagas")
                .withExpiresAt(expiresIn)
                .withSubject(company.getId().toString())
                .withClaim("roles", List.of("COMPANY"))
                .sign(algorithm);

        return AuthCompanyResponseDTO.builder()
                .access_token(token)
                .expires_in(expiresIn.toEpochMilli())
                .build();
    }
}
