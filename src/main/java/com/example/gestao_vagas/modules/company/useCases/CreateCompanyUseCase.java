package com.example.gestao_vagas.modules.company.useCases;

import com.example.gestao_vagas.exceptions.UserFoundException;
import com.example.gestao_vagas.modules.company.entities.CompanyEntity;
import com.example.gestao_vagas.modules.company.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateCompanyUseCase {
    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CompanyEntity execute(CompanyEntity companyEntity) {

        this.companyRepository.findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
                .ifPresent((company) -> {
                    throw new UserFoundException();
                });

        var password = passwordEncoder.encode(companyEntity.getPassword());

        companyEntity.setPassword(password);

        return this.companyRepository.save(companyEntity);
    }
}
