package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Certificate;

public interface CertificateRepository extends JpaRepository<Certificate,Long> {
	Certificate findOneById(Long id);
	Certificate findOneByIdSubject(Long id);
	Certificate findOneByIdIssuer(Long id);

}
