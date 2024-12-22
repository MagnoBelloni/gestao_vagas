package com.example.gestao_vagas.modules.company.controllers;

import com.example.gestao_vagas.modules.company.dto.AuthCompanyDto;
import com.example.gestao_vagas.modules.company.useCases.AuthCompanyUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/company")
public class AuthCompanyController {
    @Autowired
    private AuthCompanyUseCase authCompanyUseCase;

    @PostMapping("/auth")
    public ResponseEntity<Object> create(
            @RequestBody
            AuthCompanyDto authCompanyDto
    ) throws AuthenticationException {
        try {
            var result = this.authCompanyUseCase.execute(authCompanyDto);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
